package org.diagnoseit.spike.inspectit.trace.impl;

import java.net.MalformedURLException;
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

	private static final String HTTP_URL_KEY = "http.url";

	/** Serial version id. */
	private static final long serialVersionUID = -8069154836545294257L;

	public static final String UNKNOWN = "UNKNOWN";

	/**
	 * Application name is not specified per default in the traces.
	 */
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

	/**
	 * Create SubTraceImpl using inspectIT spans.
	 * 
	 * @param containingTrace
	 * @param spans
	 * @throws MalformedURLException
	 */
	public IITSubTraceImpl(IITTraceImpl containingTrace, TraceData traceData, Span span) {
		super((span.hashCode() * (long) Math.pow(10, String.valueOf(span.getDuration()).length() + 1)) + (int) span.getDuration());
		this.trace = containingTrace;
		// Check, whether we have to set a http request processing callable as root
		if (!span.isCaller() && span.getTags().containsKey(HTTP_URL_KEY)) {
			URL urlObject = null;
			List<Span> childrenSpans = SpanConverterHelper.getChildrenOfSpan(traceData.getSpans(), span.getSpanIdent());
			Span parentSpan = SpanConverterHelper.getParentOfSpan(traceData.getSpans(), span);
			try {
				String url = span.getTags().getOrDefault(HTTP_URL_KEY, UNKNOWN);
				urlObject = new URL(url);

			} catch (MalformedURLException e) {
				if (null != parentSpan) {
					String url = parentSpan.getTags().getOrDefault(HTTP_URL_KEY, UNKNOWN);
					try {
						urlObject = new URL(url);
					} catch (MalformedURLException e1) {
						e1.printStackTrace();
					}
				}
			}

			if (!urlObject.getHost().isEmpty()) {
				this.host = urlObject.getHost();
			}
			if (urlObject.getPort() != 0) {
				this.port = urlObject.getPort();
			} else {
				this.port = 80;
			}

			IITHTTPRequestProcessing httpRequestProcessing = generateHTTPRequestProcessingCallable(span, parentSpan, urlObject, this);
			for (Span childSpan : childrenSpans) {
				httpRequestProcessing.addCallee(SpanConverterHelper.createCallable(this, httpRequestProcessing, containingTrace, traceData, childSpan));
			}
			this.root = httpRequestProcessing;

		} else {
			this.host = span.getTags().getOrDefault("local.service.name", "unkown");
			this.root = SpanConverterHelper.createCallable(this, null, containingTrace, traceData, span);
			this.businessTransactionName = span.getTags().getOrDefault("businessTransaction", span.getTags().getOrDefault(ExtraTags.OPERATION_NAME, "unkown transaction"));

		}
		this.isData = null;
		this.subTraceID = span.getSpanIdent().getTraceId();
		this.responseTime = Math.round(traceData.getSpans().get(0).getDuration() * Trace.MILLIS_TO_NANOS_FACTOR);
		this.applicationName = span.getTags().getOrDefault("application", "unkown application");
		this.platformID = -1;

	}

	/**
	 * Generates a IITHTTPRequestProcessing object based on the given span.
	 * 
	 * @param span
	 * @param url
	 * @return
	 */
	private static IITHTTPRequestProcessing generateHTTPRequestProcessingCallable(Span span, Span parentSpan, URL url, IITSubTraceImpl containingSubtrace) {

		HttpInfo httpInfo = new HttpInfo();
		if (span.getTags().containsKey("http.method")) {
			httpInfo.setRequestMethod(span.getTags().get("http.method"));
		} else if (span.getTags().containsKey("http.request.method")) {
			httpInfo.setRequestMethod(span.getTags().get("http.request.method"));

		} else {
			throw new InvalidParameterException("HTTP method is missing in the span tags. Please make sure, that each remote span contains a proper HTTP method by using the tag http.method or http.request.method");
		}
		httpInfo.setUri(url.getPath());
		httpInfo.setScheme(url.getProtocol());

		httpInfo.setServerPort(url.getPort());

		HttpTimerData httpTimerData = new HttpTimerData(span.getTimeStamp(), -1, -1, -1);
		httpTimerData.setId(span.getSpanIdent().getId());
		httpTimerData.setHttpInfo(httpInfo);

		Map<String, String> headers = new HashMap<String, String>();

		if (null != span.getTags().get("cookie")) {
			headers.put("cookie", span.getTags().get("cookie"));
		}
		httpTimerData.setHeaders(headers);

		Map<String, String[]> parameters = new HashMap<String, String[]>();


		// Check if body is set in in current or caller span
		if (span.getTags().containsKey("body")) {
			parameters.put("_BODY", new String[] { span.getTags().get("body") });
		} else if (null != parentSpan && parentSpan.getTags().containsKey("body")) {
			parameters.put("_BODY", new String[] { parentSpan.getTags().get("body") });
		}

		httpTimerData.setParameters(parameters);
		httpInfo.setQueryString(url.getQuery());
		if (null != parentSpan) {
			httpTimerData.setHttpResponseStatus(Integer.parseInt(parentSpan.getTags().getOrDefault("http.status_code", "-1")));
		} else {
			httpTimerData.setHttpResponseStatus(Integer.parseInt(span.getTags().getOrDefault("http.status_code", "-1")));
		}
		InvocationSequenceData invocationSequenceData = new InvocationSequenceData();
		invocationSequenceData.setId(span.getSpanIdent().getId());
		invocationSequenceData.setTimerData(httpTimerData);
		invocationSequenceData.setTimeStamp(span.getTimeStamp());
		invocationSequenceData.setDuration(span.getDuration());
		// FIXME: Dirty hack to provide multiple Spans on the same level.
		IITHTTPRequestProcessing httpRequestProcessing = new IITHTTPRequestProcessing(invocationSequenceData, containingSubtrace, null);
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
