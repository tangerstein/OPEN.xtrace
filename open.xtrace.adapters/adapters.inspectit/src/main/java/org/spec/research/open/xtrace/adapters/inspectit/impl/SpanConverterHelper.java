package org.spec.research.open.xtrace.adapters.inspectit.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.spec.research.open.xtrace.adapters.inspectit.importer.TraceData;

import rocks.inspectit.shared.all.cmr.model.PlatformIdent;
import rocks.inspectit.shared.all.communication.data.InvocationSequenceData;
import rocks.inspectit.shared.all.tracing.data.Span;

class SpanConverterHelper {

	private static final String URL = "http.url";
	private static final String OPERATIONNAME = "ext.operation.name";
	private static final String COOKIE = "cookie";

	protected IITAbstractCallable createCallable(IITSubTraceImpl containingTrace, IITAbstractNestingCallable parent, IITTraceImpl trace, TraceData traceData) {

		Span rootSpan = getRootSpan(traceData.getSpans());
		if (rootSpan == null) {
			throw new IllegalArgumentException("No root span found!");
		}

		// TraceID is equal usecaseID
		long traceID = rootSpan.getSpanIdent().getTraceId();
		containingTrace.getContainingTrace().setIdentifier(traceID);

		String operationName = rootSpan.getTags().get(OPERATIONNAME);
		String cookie = rootSpan.getTags().get(COOKIE);

		InvocationSequenceData data = new InvocationSequenceData();
		data.setTimeStamp(rootSpan.getTimeStamp());
		data.setDuration(rootSpan.getDuration());
		if (rootSpan.getTags().containsKey(URL)) {
			IITRemoteInvocation rootCallable = null;
			InvocationSequenceData remoteCall = getInvocationSequenceDataWithSpanID(rootSpan.getSpanIdent().getId(), traceData.getInvocationSequenceDatas());
			rootCallable = new IITRemoteInvocation(remoteCall, containingTrace, parent);
			if (remoteCall == null) {
				rootCallable.setTargetSubTrace(null);
			} else {
				rootCallable.setTargetSubTrace(new IITSubTraceImpl(trace, remoteCall, getPlatformIdentWithID(remoteCall.getPlatformIdent(), traceData.getPlatformIdents())));
			}
			return rootCallable;
		} else {
			IITSpanCallable rootCallable = new IITSpanCallable(containingTrace, parent, traceID, operationName, cookie, data);
			addChildren(trace, traceData, containingTrace, rootCallable, rootSpan);

			return rootCallable;
		}
	}

	private void addChildren(IITTraceImpl trace, TraceData traceData, IITSubTraceImpl containingTrace, IITSpanCallable parent, Span parentSpan) {

		Timestamp startTime = parentSpan.getTimeStamp();
		if (startTime == null) {
			throw new IllegalArgumentException(String.format("Timestamp of span with id %s is null!", parentSpan.getSpanIdent().getId()));
		}

		List<Span> children = getChildrenOfSpan(traceData.getSpans(), parentSpan);
		for (Span child : children) {
			if (child.getTags().containsKey(URL)) {
				IITRemoteInvocation remoteInvocation = null;
				InvocationSequenceData remoteCall = getInvocationSequenceDataWithSpanID(child.getSpanIdent().getId(), traceData.getInvocationSequenceDatas());
				remoteInvocation = new IITRemoteInvocation(remoteCall, containingTrace, parent);
				if (remoteCall == null) {
					remoteInvocation.setTargetSubTrace(null);
				} else {
					remoteInvocation.setTargetSubTrace(new IITSubTraceImpl(trace, remoteCall, getPlatformIdentWithID(remoteCall.getPlatformIdent(), traceData.getPlatformIdents())));
				}
				parent.addChild(remoteInvocation);
			} else {
				InvocationSequenceData data = new InvocationSequenceData();
				data.setTimeStamp(child.getTimeStamp());
				data.setDuration(child.getDuration());
				IITSpanCallable usecaseCallable = new IITSpanCallable(containingTrace, parent, parent.getUseCaseID().get(), child.getTags().get(OPERATIONNAME), child.getTags().get(COOKIE), data);
				parent.addChild(usecaseCallable);

				addChildren(trace, traceData, containingTrace, usecaseCallable, child);
			}
		}

	}

	private Span getRootSpan(List<Span> spans) {
		for (Span span : spans) {
			if (span.isRoot()) {
				return span;
			}
		}
		return null;
	}

	private List<Span> getChildrenOfSpan(List<Span> spans, Span span) {
		List<Span> children = new ArrayList<Span>();
		for (Span element : spans) {
			if (element.getParentSpanId() == span.getSpanIdent().getId() && element.getParentSpanId() != element.getSpanIdent().getId()) {
				children.add(element);
			}
		}
		return children;
	}

	private InvocationSequenceData getInvocationSequenceDataWithSpanID(long spanIdentID, List<InvocationSequenceData> listInvocationSequenceData) {
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

	private PlatformIdent getPlatformIdentWithID(long platformIdentID, List<PlatformIdent> listPlatformIdents) {
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
}
