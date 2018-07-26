package org.diagnoseit.spike.inspectit.trace.impl;

import java.util.Optional;

import org.spec.research.open.xtrace.adapters.inspectit.source.TraceData;
import org.spec.research.open.xtrace.api.core.SubTrace;
import org.spec.research.open.xtrace.api.core.Trace;
import org.spec.research.open.xtrace.api.core.TreeIterator;
import org.spec.research.open.xtrace.api.core.callables.Callable;
import org.spec.research.open.xtrace.api.utils.CallableIteratorOnTrace;
import org.spec.research.open.xtrace.api.utils.StringUtils;
import org.spec.research.open.xtrace.api.utils.SubTraceIterator;

import rocks.inspectit.shared.all.cmr.model.PlatformIdent;
import rocks.inspectit.shared.all.cmr.service.ICachedDataService;
import rocks.inspectit.shared.all.communication.data.HttpTimerData;
import rocks.inspectit.shared.all.communication.data.InvocationSequenceData;

public class IITTraceImpl extends IITAbstractIdentifiableImpl implements Trace {

	/** Serial version id. */
	private static final long serialVersionUID = 2768574993119101303L;

	/**
	 * Trace data, which consists of all span data available.
	 */
	private TraceData traceData;

	protected static IITAbstractCallable createCallable(InvocationSequenceData isData, IITSubTraceImpl containingTrace, IITAbstractNestingCallable parent) {
		if (isData.getSqlStatementData() != null) {
			return new IITDatabaseInvocation(isData, containingTrace, parent);
		} else if (isData.getTimerData() != null && isData.getTimerData() instanceof HttpTimerData) {
			return new IITHTTPRequestProcessing(isData, containingTrace, parent);
		} else {
			return new IITMethodInvocation(isData, containingTrace, parent);
		}
	}

	private SubTrace root;
	private ICachedDataService cachedDataService;

	public IITTraceImpl(InvocationSequenceData root, ICachedDataService cachedDataService) {
		super(root.getId());
		this.cachedDataService = cachedDataService;
		this.root = new IITSubTraceImpl(this, root);
	}

	public IITTraceImpl(InvocationSequenceData root, PlatformIdent pIdent) {
		super(root.getId());
		this.root = new IITSubTraceImpl(this, root, pIdent);
	}

	public IITTraceImpl(TraceData traceData, ICachedDataService cachedDataService) {
		super((traceData.getRootSpan().hashCode() * (long) Math.pow(10, String.valueOf(traceData.getRootSpan().getDuration()).length() + 1)) + (int) traceData.getRootSpan().getDuration());
		this.cachedDataService = cachedDataService;
		this.traceData = traceData;
		this.root = new IITSubTraceImpl(this, traceData, null, traceData.getRootSpan());
	}

	@Override
	public TreeIterator<Callable> iterator() {
		return new CallableIteratorOnTrace(root);
	}

	@Override
	public SubTrace getRoot() {
		return root;
	}

	@Override
	public long getTraceId() {
		return root.getSubTraceId();
	}

	@Override
	public String toString() {
		return StringUtils.getStringRepresentation(this);
	}

	@Override
	public int size() {
		return root.size();
	}

	@Override
	public TreeIterator<SubTrace> subTraceIterator() {
		return new SubTraceIterator(root);
	}

	protected ICachedDataService getCachedDataService() {
		return cachedDataService;
	}

	@Override
	public long getExclusiveTime() {
		return getResponseTime();
	}

	@Override
	public long getResponseTime() {
		if (root == null) {
			return 0;
		} else {
			return root.getResponseTime();
		}
	}

	public Optional<TraceData> getTraceData() {
		return Optional.ofNullable(traceData);
	}

}
