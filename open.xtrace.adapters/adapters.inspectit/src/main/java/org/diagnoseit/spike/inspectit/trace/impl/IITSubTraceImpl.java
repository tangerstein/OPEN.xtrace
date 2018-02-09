package org.diagnoseit.spike.inspectit.trace.impl;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.InvalidParameterException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.spec.research.open.xtrace.adapters.inspectit.source.SpanConverterHelper;
import org.spec.research.open.xtrace.adapters.inspectit.source.TraceData;
import org.spec.research.open.xtrace.api.core.Location;
import org.spec.research.open.xtrace.api.core.SubTrace;
import org.spec.research.open.xtrace.api.core.Trace;
import org.spec.research.open.xtrace.api.core.TreeIterator;
import org.spec.research.open.xtrace.api.core.callables.Callable;
import org.spec.research.open.xtrace.api.utils.CallableIterator;
import org.spec.research.open.xtrace.api.utils.StringUtils;

import rocks.inspectit.shared.all.cmr.model.PlatformIdent;
import rocks.inspectit.shared.all.communication.data.HttpInfo;
import rocks.inspectit.shared.all.communication.data.HttpTimerData;
import rocks.inspectit.shared.all.communication.data.InvocationSequenceData;
import rocks.inspectit.shared.all.tracing.constants.ExtraTags;
import rocks.inspectit.shared.all.tracing.data.Span;

public class IITSubTraceImpl extends IITAbstractIdentifiableImpl implements SubTrace, Location {

	/** Serial version id. */
	private static final long serialVersionUID = -8069154836545294257L;

	public static final String UNKNOWN = "UNKNOWN";
	/**
	 * Application name is not specified per default in the traces.
	 */
	private static final String APPLICATION_NAME = "sock-shop";

	private final InvocationSequenceData isData;
	private final Callable root;
	private PlatformIdent pIdent;
	protected IITTraceImpl trace;
	private long platformID;
	private long subTraceID;
	private long responseTime;

	private String applicationName;

	private String businessTransactionName;

	private String host = UNKNOWN;

	public void setHost(String host) {
		this.host = host;
	}

	private int port = 80;

	public IITSubTraceImpl(IITTraceImpl containingTrace, InvocationSequenceData isData, PlatformIdent pIdent) {

		super(isData.getId());
		this.root = IITTraceImpl.createCallable(isData, this, null);
		this.isData = isData;
		this.pIdent = pIdent;
		this.subTraceID = isData.getId();
		this.responseTime = Math.round(isData.getDuration() * Trace.MILLIS_TO_NANOS_FACTOR);
		if (null != containingTrace.getCachedDataService()) {
			this.applicationName = containingTrace.getCachedDataService().getApplicationForId(isData.getApplicationId()).getName();
			this.businessTransactionName = containingTrace.getCachedDataService().getBusinessTransactionForId(isData.getApplicationId(), isData.getBusinessTransactionId()).getName();
		}

		if (null != getPlatformIdent()) {
			if (!getPlatformIdent().getDefinedIPs().isEmpty()) {
				for (String ip : getPlatformIdent().getDefinedIPs()) {
					if (!ip.contains(":")) {
						if (host.isEmpty()) {
							this.host = ip;
						}
						this.port = 80;
						break;
					} else {
						int i = ip.indexOf(":");
						if (host.isEmpty()) {
							this.host = ip.substring(0, i);
						}
						this.port = Integer.parseInt(ip.substring(i + 1));
					}
				}
			}
		}
		platformID = pIdent.getId();
		trace = containingTrace;
	}

	public IITSubTraceImpl(IITTraceImpl containingTrace, InvocationSequenceData isData) {

		super(isData.getId());
		this.root = IITTraceImpl.createCallable(isData, this, null);
		this.isData = isData;
		this.subTraceID = isData.getId();
		this.responseTime = Math.round(isData.getDuration() * Trace.MILLIS_TO_NANOS_FACTOR);
		if (null != containingTrace.getCachedDataService()) {
			this.applicationName = containingTrace.getCachedDataService().getApplicationForId(isData.getApplicationId()).getName();
			this.businessTransactionName = containingTrace.getCachedDataService().getBusinessTransactionForId(isData.getApplicationId(), isData.getBusinessTransactionId()).getName();
		}

		if (null != getPlatformIdent()) {
			if (!getPlatformIdent().getDefinedIPs().isEmpty()) {
				for (String ip : getPlatformIdent().getDefinedIPs()) {
					if (!ip.contains(":")) {
						this.host = ip;
						this.port = 80;
						break;
					} else {
						int i = ip.indexOf(":");
						this.host = ip.substring(0, i);
						this.port = Integer.parseInt(ip.substring(i + 1));
					}
				}
			}
		}

		this.platformID = isData.getPlatformIdent();
		trace = containingTrace;
		System.out.println("Inner: " + pIdent);
	}

	/**
	 * Create SubTraceImpl using inspectIT spans.
	 * 
	 * @param containingTrace
	 * @param spans
	 */
	public IITSubTraceImpl(IITTraceImpl containingTrace, TraceData traceData, Span span, Span rootSpan) {
		super((rootSpan.hashCode() * (long) Math.pow(10, String.valueOf(rootSpan.getDuration()).length() + 1)) + (int) rootSpan.getDuration());
		this.trace = containingTrace;

		// Check, whether this subtrace is part of an existing RemoteInvocation.
		if (null != span) {
			String url = span.getTags().getOrDefault("http.url", UNKNOWN);
			if (!url.contains("://")) {
				url = "unkownSchema://" + url;
			}
			List<Span> childrenSpans = SpanConverterHelper.getChildrenOfSpan(traceData.getSpans(), span.getSpanIdent());
			Span parentSpan = SpanConverterHelper.getParentOfSpan(traceData.getSpans(), span);

			try {
				URL urlObject = new URL(url);
				if (!urlObject.getHost().isEmpty()) {
					this.host = urlObject.getHost();
				}
				if(urlObject.getPort() != 0) {
					this.port = urlObject.getPort();
				} else {
					this.port = 80;
				}
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (childrenSpans.isEmpty()) {
				// Remote system is not tracked, but HTTPRequestProcessing can be provided.
				this.root = generateHTTPRequestProcessingCallable(span, parentSpan, url);
			} else {
				IITHTTPRequestProcessing httpRequestProcessing = generateHTTPRequestProcessingCallable(span, parentSpan, url);
				for (Span childSpan : childrenSpans) {
					httpRequestProcessing.addCallee(SpanConverterHelper.createCallable(this, httpRequestProcessing, containingTrace, traceData, childSpan));
				}
				this.root = httpRequestProcessing;
			}

		} else {
			this.host = rootSpan.getTags().get("local.service.name");
			this.root = SpanConverterHelper.createCallable(this, null, containingTrace, traceData, rootSpan);
			this.businessTransactionName = rootSpan.getTags().getOrDefault("businessTransaction", rootSpan.getTags().getOrDefault(ExtraTags.OPERATION_NAME, "unkown transaction"));

		}
		this.isData = null;
		this.subTraceID = rootSpan.getSpanIdent().getTraceId();
		this.responseTime = Math.round(traceData.getSpans().get(0).getDuration() * Trace.MILLIS_TO_NANOS_FACTOR);
		this.applicationName = rootSpan.getTags().getOrDefault("application", APPLICATION_NAME);
		this.platformID = -1;

	}

	/**
	 * Generates a IITHTTPRequestProcessing object based on the given span.
	 * 
	 * @param span
	 * @param url
	 * @return
	 */
	private IITHTTPRequestProcessing generateHTTPRequestProcessingCallable(Span span, Span parentSpan, String url) {

		HttpInfo httpInfo = new HttpInfo();
		try {
			URI uri = new URI(url);
			if (span.getTags().containsKey("http.method")) {
				httpInfo.setRequestMethod(span.getTags().get("http.method"));
			} else if (span.getTags().containsKey("http.request.method")) {
				httpInfo.setRequestMethod(span.getTags().get("http.request.method"));

			} else {
				throw new InvalidParameterException("HTTP method is missing in the span tags. Please make sure, that each remote span contains a proper HTTP method by using the tag http.method or http.request.method");
			}
			httpInfo.setUri(uri.getRawPath());
			httpInfo.setScheme(uri.getScheme());
			if (httpInfo.getRequestMethod().equalsIgnoreCase("POST") || httpInfo.getRequestMethod().equalsIgnoreCase("PATCH")) {
				httpInfo.setQueryString(SpanConverterHelper.convertJsonBodyToQueryString(span));
			} else {
				httpInfo.setQueryString(uri.getQuery());
			}
			httpInfo.setServerPort(uri.getPort());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		HttpTimerData httpTimerData = new HttpTimerData(span.getTimeStamp(), -1, -1, -1);
		httpTimerData.setId(span.getSpanIdent().getId());
		httpTimerData.setHttpInfo(httpInfo);
		Map<String, String> headers = new HashMap<String, String>();

		if (null != span.getTags().get("cookie")) {
			headers.put("cookie", span.getTags().get("cookie"));
		}
		httpTimerData.setHeaders(headers);

		Map<String, String[]> parameters = new HashMap<String, String[]>();

		// TODO: Has to be filled
		httpTimerData.setParameters(parameters);

		if (null != parentSpan) {
		httpTimerData.setHttpResponseStatus(Integer.parseInt(parentSpan.getTags().getOrDefault("http.status_code", "-1")));
		}
		InvocationSequenceData invocationSequenceData = new InvocationSequenceData();
		invocationSequenceData.setId(span.getSpanIdent().getId());
		invocationSequenceData.setTimerData(httpTimerData);
		invocationSequenceData.setTimeStamp(span.getTimeStamp());
		invocationSequenceData.setDuration(span.getDuration());
		// FIXME: Dirty hack to provide multiple Spans on the same level.
		IITHTTPRequestProcessing httpRequestProcessing = new IITHTTPRequestProcessing(invocationSequenceData, this, null);
		// TODO
		return httpRequestProcessing;
	}

	@Override
	public Callable getRoot() {

		return root;
	}

	@Override
	public SubTrace getParent() {

		return null;
	}

	@Override
	public List<SubTrace> getSubTraces() {

		return Collections.unmodifiableList(Collections.emptyList());
	}

	@Override
	public Location getLocation() {

		return this;
	}

	@Override
	public int size() {

		return (int) (isData == null ? -1 : isData.getChildCount() + 1);
	}

	@Override
	public TreeIterator<Callable> iterator() {

		return new CallableIterator(root);
	}

	@Override
	public String getHost() {
		return host;
	}

	@Override
	public Optional<String> getRuntimeEnvironment() {

		return Optional.empty();
		// return Optional.ofNullable(getPlatformIdent().getAgentName() +
		// (getPlatformIdent().getId() == null ? "" : "-" + getPlatformIdent().getId()));
	}

	@Override
	public Optional<String> getApplication() {
		return Optional.ofNullable(applicationName);
	}

	@Override
	public Optional<String> getBusinessTransaction() {
		return Optional.ofNullable(businessTransactionName);

	}

	@Override
	public String toString() {

		return StringUtils.getStringRepresentation((SubTrace) this);
	}

	@Override
	public Trace getContainingTrace() {

		return trace;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + ((isData == null) ? 0 : (int) isData.getId());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		IITSubTraceImpl other = (IITSubTraceImpl) obj;
		if (isData == null) {
			if (other.isData != null) {
				return false;
			}
		} else if (isData.getId() != other.isData.getId()) {
			return false;
		}
		return true;
	}

	@Override
	public Optional<String> getNodeType() {
		return Optional.ofNullable(getPlatformIdent() == null ? null : getPlatformIdent().getAgentName());
	}

	protected PlatformIdent getPlatformIdent() {

		if (pIdent == null) {
			pIdent = trace.getCachedDataService().getPlatformIdentForId(platformID);
		}
		return pIdent;
	}

	@Override
	public long getExclusiveTime() {

		long exclTime = getResponseTime();
		for (SubTrace child : getSubTraces()) {
			exclTime -= child.getResponseTime();
		}
		return exclTime;
	}

	@Override
	public long getResponseTime() {
		return responseTime;
	}

	@Override
	public long getSubTraceId() {
		return subTraceID;
	}

	@Override
	public Optional<String> getServerName() {
		// Not supported
		return Optional.empty();
	}

	@Override
	public int getPort() {
		return this.port;
	}

}
