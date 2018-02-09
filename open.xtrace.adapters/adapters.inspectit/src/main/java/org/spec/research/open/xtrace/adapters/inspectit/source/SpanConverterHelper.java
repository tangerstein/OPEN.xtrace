package org.spec.research.open.xtrace.adapters.inspectit.source;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.diagnoseit.spike.inspectit.trace.impl.IITAbstractCallable;
import org.diagnoseit.spike.inspectit.trace.impl.IITAbstractNestingCallable;
import org.diagnoseit.spike.inspectit.trace.impl.IITRemoteInvocation;
import org.diagnoseit.spike.inspectit.trace.impl.IITSubTraceImpl;
import org.diagnoseit.spike.inspectit.trace.impl.IITTraceImpl;
import org.diagnoseit.spike.inspectit.trace.impl.IITUseCaseInvocation;
import org.spec.research.open.xtrace.api.core.AdditionalInformation;
import org.spec.research.open.xtrace.api.core.callables.RemoteInvocation;

import rocks.inspectit.shared.all.cmr.model.PlatformIdent;
import rocks.inspectit.shared.all.communication.data.HttpTimerData;
import rocks.inspectit.shared.all.communication.data.InvocationSequenceData;
import rocks.inspectit.shared.all.tracing.constants.ExtraTags;
import rocks.inspectit.shared.all.tracing.data.PropagationType;
import rocks.inspectit.shared.all.tracing.data.Span;
import rocks.inspectit.shared.all.tracing.data.SpanIdent;

public class SpanConverterHelper {

	private static final String HTTP_STATUS_CODE = "http.status_code";
	private static final String URL = "http.url";

	public static IITAbstractCallable createCallable(IITSubTraceImpl containingTrace, IITAbstractNestingCallable parent, IITTraceImpl trace, TraceData traceData, Span span) {
		// TraceID is equal usecaseID
		long traceID = span.getSpanIdent().getTraceId();
		containingTrace.getContainingTrace().setIdentifier(traceID);

		InvocationSequenceData data = new InvocationSequenceData();
		data.setTimeStamp(span.getTimeStamp());
		data.setDuration(span.getDuration());
		if (isRemoteInvocation(span, traceData.getSpans())) {
			IITRemoteInvocation rootCallable = createRemoteInvocation(containingTrace, parent, trace, traceData, span);
			return rootCallable;
		} else {
			IITUseCaseInvocation rootCallable = createUseCaseInvocation(containingTrace, parent, span);
			addChildren(trace, traceData, containingTrace, rootCallable, span, traceData.getRootSpan());
			return rootCallable;
		}
	}

	private static void addChildren(IITTraceImpl trace, TraceData traceData, IITSubTraceImpl containingTrace, IITUseCaseInvocation parent, Span parentSpan, Span rootSpan) {

		Timestamp startTime = parentSpan.getTimeStamp();
		if (startTime == null) {
			throw new IllegalArgumentException(String.format("Timestamp of span with id %s is null!", parentSpan.getSpanIdent().getId()));
		}

		List<Span> children = getChildrenOfSpan(traceData.getSpans(), parentSpan.getSpanIdent());

		for (Span child : children) {
			if (isRemoteInvocation(child, traceData.getSpans())) {
				parent.addCallee(createRemoteInvocation(containingTrace, parent, trace, traceData, child));
			} else {
				IITUseCaseInvocation usecaseCallable = createUseCaseInvocation(containingTrace, parent, child);
				parent.addCallee(usecaseCallable);
				addChildren(trace, traceData, containingTrace, usecaseCallable, child, rootSpan);
			}
		}

	}

	/**
	 * Method, which determines, which span has to be converted into a RemoteInvocation. Usually,
	 * all spans, which consist a "http.url" tag should be considered as remote call. However,
	 * inspectIT also uses the URL as informational attribute in the span, which represents the
	 * entrypoint of the remote service. Thus, we have to filter these spans by checking the
	 * {@link PropagationType}
	 * 
	 * @param child
	 * @return boolean, is span a remote invocation
	 */
	private static boolean isRemoteInvocation(Span child, List<Span> spans) {
		if (child.getTags().containsKey(URL)) {
			if (child.isRoot()) {
				return true;
			}
			String childUrl = child.getTags().get(URL);
			Span parentSpan = SpanConverterHelper.getParentOfSpan(spans, child);
			if (parentSpan.getTags().containsKey(URL)) {
				String parentUrl = parentSpan.getTags().get(URL);
				if (childUrl.equals(parentUrl) || ("http://" + child.getTags().get("local.service.name") + childUrl).equals(parentUrl)) {
					return false;
				} else {
					return true;
				}
			} else {
				return true;
			}
		} else {
			return false;
		}
	}

	/**
	 * Creates usecaseInvocation
	 * 
	 * @param containingTrace
	 *            containing sub trace
	 * @param parent
	 *            parent callable
	 * @param span
	 *            current span
	 * @return
	 */
	private static IITUseCaseInvocation createUseCaseInvocation(IITSubTraceImpl containingTrace, IITAbstractNestingCallable parent, Span span) {
		InvocationSequenceData data = new InvocationSequenceData();
		data.setTimeStamp(span.getTimeStamp());
		data.setDuration(span.getDuration());
		IITUseCaseInvocation usecaseCallable = new IITUseCaseInvocation(data, containingTrace, parent, span.getTags().get(ExtraTags.OPERATION_NAME));
		for (String tag : span.getTags().keySet()) {
			usecaseCallable.getAdditionalInformation().get().add(new AdditionalInformation() {

				@Override
				public String getName() {
					return tag;
				}

				@Override
				public String getValue() {
					return span.getTags().get(tag);
				}

			});
		}
		return usecaseCallable;
	}

	/**
	 * Creates RemoteInvocation data
	 * 
	 * @param containingTrace
	 *            containing subtrace
	 * @param parent
	 *            parent span
	 * @param trace
	 *            containing trace
	 * @param traceData
	 *            traceData
	 * @param span
	 *            current span which will be transformed into a RemoteInvocation
	 * @return {@link RemoteInvocation}
	 */
	private static IITRemoteInvocation createRemoteInvocation(IITSubTraceImpl containingTrace, IITAbstractNestingCallable parent, IITTraceImpl trace, TraceData traceData, Span span) {
		IITRemoteInvocation rootCallable = null;
		InvocationSequenceData remoteCall = getInvocationSequenceDataWithSpanID(span.getSpanIdent().getId(), traceData.getInvocationSequenceDatas());


		if (remoteCall == null) {
			for (Span childSpan : SpanConverterHelper.getChildrenOfSpan(traceData.getSpans(), span.getSpanIdent())) {
				if (childSpan.getTags().containsKey(URL) && !SpanConverterHelper.isRemoteInvocation(childSpan, traceData.getSpans())) {
					remoteCall = getInvocationSequenceDataWithSpanID(childSpan.getSpanIdent().getId(), traceData.getInvocationSequenceDatas());

					if (remoteCall != null) {

						// Clone invocationSequence
						List<InvocationSequenceData> nestedInvocationSequences = remoteCall.getNestedSequences();
						remoteCall = remoteCall.getClonedInvocationSequence();
						remoteCall.setNestedSequences(nestedInvocationSequences);

						// span and child span are put together, because they provide the same url
						if (remoteCall.getTimerData() != null && remoteCall.getTimerData() instanceof HttpTimerData) {
							if (SpanConverterHelper.getParentOfSpan(traceData.getSpans(), span).getTags().containsKey(HTTP_STATUS_CODE)) {
								((HttpTimerData) remoteCall.getTimerData()).setHttpResponseStatus(Integer.parseInt(SpanConverterHelper.getParentOfSpan(traceData.getSpans(), span).getTags().get(HTTP_STATUS_CODE)));
							}
							if (((HttpTimerData) remoteCall.getTimerData()).getHttpInfo().getRequestMethod().equalsIgnoreCase("POST")
									|| ((HttpTimerData) remoteCall.getTimerData()).getHttpInfo().getRequestMethod().equalsIgnoreCase("PATCH")) {
								String queryString = convertJsonBodyToQueryString(span);
								((HttpTimerData) remoteCall.getTimerData()).getHttpInfo().setQueryString(queryString);
							}
						}
						// Invoc sequence has to be divided into two invocationSequences, because
						// OPEN.xtrace
						// differs between RemoteInvocations and MethodInvocations
						InvocationSequenceData invocDataMethodInvocation = new InvocationSequenceData();
						invocDataMethodInvocation.setId(remoteCall.getId());
						invocDataMethodInvocation.setTimeStamp(remoteCall.getTimeStamp());
						invocDataMethodInvocation.setApplicationId(remoteCall.getApplicationId());
						invocDataMethodInvocation.setBusinessTransactionId(remoteCall.getBusinessTransactionId());
						invocDataMethodInvocation.setSensorTypeIdent(remoteCall.getSensorTypeIdent());
						invocDataMethodInvocation.setMethodIdent(remoteCall.getMethodIdent());
						invocDataMethodInvocation.setNestedSequences(remoteCall.getNestedSequences());
						remoteCall.setNestedSequences(new ArrayList<InvocationSequenceData>(Arrays.asList(invocDataMethodInvocation)));
						// Set the timestamp of the remote callable to the timestamp of the normal
						// span
						remoteCall.getTimerData().setTimeStamp(span.getTimeStamp());
						remoteCall.getTimerData().setDuration(span.getDuration() + childSpan.getDuration());
						rootCallable = new IITRemoteInvocation(remoteCall, containingTrace, parent);
						rootCallable.setTargetSubTrace(new IITSubTraceImpl(trace, remoteCall, getPlatformIdentWithID(remoteCall.getPlatformIdent(), traceData.getPlatformIdents())));
						return rootCallable;
					}
				}
			}
			InvocationSequenceData invocDataPlaceHolder = new InvocationSequenceData();
			invocDataPlaceHolder.setId(span.getSpanIdent().getTraceId());
			invocDataPlaceHolder.setTimeStamp(span.getTimeStamp());
			invocDataPlaceHolder.setDuration(span.getDuration());
			rootCallable = new IITRemoteInvocation(invocDataPlaceHolder, containingTrace, parent);

			rootCallable.setTargetSubTrace(new IITSubTraceImpl(trace, traceData, span, traceData.getRootSpan()));
		} else {
			// Clone invocationSequence
			List<InvocationSequenceData> nestedInvocationSequences = remoteCall.getNestedSequences();
			remoteCall = remoteCall.getClonedInvocationSequence();
			remoteCall.setNestedSequences(nestedInvocationSequences);

			if (remoteCall.getTimerData() != null && remoteCall.getTimerData() instanceof HttpTimerData) {
				if (SpanConverterHelper.getParentOfSpan(traceData.getSpans(), span).getTags().containsKey(HTTP_STATUS_CODE)) {
					((HttpTimerData) remoteCall.getTimerData()).setHttpResponseStatus(Integer.parseInt(SpanConverterHelper.getParentOfSpan(traceData.getSpans(), span).getTags().get(HTTP_STATUS_CODE)));
				}
				if (((HttpTimerData) remoteCall.getTimerData()).getHttpInfo().getRequestMethod().equalsIgnoreCase("POST")
						|| ((HttpTimerData) remoteCall.getTimerData()).getHttpInfo().getRequestMethod().equalsIgnoreCase("PATCH")) {
					String queryString = convertJsonBodyToQueryString(SpanConverterHelper.getParentOfSpan(traceData.getSpans(), span));
					((HttpTimerData) remoteCall.getTimerData()).getHttpInfo().setQueryString(queryString);
				}
			}

			rootCallable = new IITRemoteInvocation(remoteCall, containingTrace, parent);

			// Invoc sequence has to be divided into two invocationSequences, because OPEN.xtrace
			// differs between RemoteInvocations and MethodInvocations
			InvocationSequenceData invocDataMethodInvocation = new InvocationSequenceData();
			invocDataMethodInvocation.setId(remoteCall.getId());
			invocDataMethodInvocation.setTimeStamp(remoteCall.getTimeStamp());
			invocDataMethodInvocation.setApplicationId(remoteCall.getApplicationId());

			invocDataMethodInvocation.setBusinessTransactionId(remoteCall.getBusinessTransactionId());
			invocDataMethodInvocation.setSensorTypeIdent(remoteCall.getSensorTypeIdent());
			invocDataMethodInvocation.setMethodIdent(remoteCall.getMethodIdent());
			invocDataMethodInvocation.setNestedSequences(remoteCall.getNestedSequences());
			remoteCall.setNestedSequences(new ArrayList<InvocationSequenceData>(Arrays.asList(invocDataMethodInvocation)));

			rootCallable.setTargetSubTrace(new IITSubTraceImpl(trace, remoteCall, getPlatformIdentWithID(remoteCall.getPlatformIdent(), traceData.getPlatformIdents())));
		}
		return rootCallable;

	}

	/**
	 * Converts http body into query string
	 * 
	 * @param span
	 * @return query string
	 */
	public static String convertJsonBodyToQueryString(Span span) {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode bodyJsonNode;
		String queryString = "";

		if (null != span) {
			try {
				bodyJsonNode = mapper.readTree(span.getTags().get("body"));
				Iterator<JsonNode> valueIterator = bodyJsonNode.iterator();
				Iterator<String> tagNameIterator = bodyJsonNode.getFieldNames();
				while (tagNameIterator.hasNext()) {
					queryString += tagNameIterator.next() + "=" + valueIterator.next().toString() + "&";
				}
				if (!queryString.isEmpty()) {
					queryString = queryString.substring(0, queryString.length() - 1);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return queryString;
	}

	public static List<Span> getChildrenOfSpan(List<Span> spans, SpanIdent spanIdent) {
		List<Span> children = new ArrayList<Span>();
		for (Span element : spans) {
			if (element.getParentSpanId() == spanIdent.getId() && element.getParentSpanId() != element.getSpanIdent().getId()) {
				children.add(element);
			}
		}
		return children;
	}

	private static InvocationSequenceData getInvocationSequenceDataWithSpanID(long spanIdentID, List<InvocationSequenceData> listInvocationSequenceData) {
		if (listInvocationSequenceData == null) {
			return null;
		}
		for (InvocationSequenceData sequenceData : listInvocationSequenceData) {
			if (sequenceData.getSpanIdent() != null && sequenceData.getSpanIdent().getId() == spanIdentID) {
				return sequenceData;
			}
		}
		return null;
	}

	private static PlatformIdent getPlatformIdentWithID(long platformIdentID, List<PlatformIdent> listPlatformIdents) {
		if (listPlatformIdents == null) {
			return null;
		}
		for (PlatformIdent platformIdent : listPlatformIdents) {
			if (platformIdent.getId() == platformIdentID) {
				return platformIdent;
			}
		}
		return null;
	}

	public static Collection<Span> filterStaticResources(Collection<? extends Span> spans) {
		Collection<Span> filteredSpans = new ArrayList<Span>();
		for (Span span : spans) {
			if (span.getTags().containsKey("http.url")) {
				if (!span.getTags().get("http.url").matches(".{0,}(.css|.js|/spans|.jpg|.html|.png|.jpeg|.woff|.map|=4.0.3)")) {
					filteredSpans.add(span);
				}
			}
		}
		return filteredSpans;

	}

	public static Span getParentOfSpan(List<Span> spans, Span childSpan) {
		for (Span element : spans) {
			if (childSpan.getParentSpanId() == 0) {
				if (childSpan.getSpanIdent().getTraceId() == element.getSpanIdent().getId()) {
					return element;
				}
			} else {
				if (childSpan.getParentSpanId() == element.getSpanIdent().getId()) {
					return element;
				}
			}
		}
		return null;
	}

	public static Span getSpan(SpanIdent spanIdent, List<Span> spans) {
		for (Span span : spans) {
			if (span.getSpanIdent().equals(spanIdent)) {
				return span;
			}
		}
		return null;
	}
}