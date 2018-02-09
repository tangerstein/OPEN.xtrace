package org.diagnoseit.spike.inspectit.trace.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import org.spec.research.open.xtrace.adapters.inspectit.source.SpanConverterHelper;
import org.spec.research.open.xtrace.api.core.TreeIterator;
import org.spec.research.open.xtrace.api.core.callables.Callable;
import org.spec.research.open.xtrace.api.core.callables.NestingCallable;
import org.spec.research.open.xtrace.api.utils.CallableIterator;

import rocks.inspectit.shared.all.communication.data.InvocationSequenceData;
import rocks.inspectit.shared.all.tracing.data.SpanIdent;

public class IITAbstractNestingCallable extends IITAbstractTimedCallable implements NestingCallable {

	/** Serial version id. */
	private static final long serialVersionUID = -5881753880225309401L;
	private List<Callable> children = null;

	public IITAbstractNestingCallable(InvocationSequenceData isData, IITSubTraceImpl containingTrace, IITAbstractNestingCallable parent) {
		super(isData, containingTrace, parent);
	}

	@Override
	public TreeIterator<Callable> iterator() {
		return new CallableIterator(this);
	}

	@Override
	/**
	 * Assumption: If there are nested Sequences, we do not have any additional span children.
	 */
	public List<Callable> getCallees() {
		if (children == null || children.isEmpty()) {
			if (isData.getNestedSequences().isEmpty()) {
				children = new ArrayList<Callable>();
				if (null != isData.getSpanIdent() && super.containingTrace.trace.getTraceData().isPresent()) {
					children.add(SpanConverterHelper.createCallable(super.containingTrace, this, super.containingTrace.trace, super.containingTrace.trace.getTraceData().get(),
							SpanConverterHelper.getSpan(isData.getSpanIdent(), super.containingTrace.trace.getTraceData().get().getSpans())));
				}

			} else {
				children = new ArrayList<Callable>(isData.getNestedSequences().size());
				List<InvocationSequenceData> nestedInvocationSequences = isData.getNestedSequences();

				for (InvocationSequenceData isd : nestedInvocationSequences) {
					IITAbstractCallable child = IITTraceImpl.createCallable(isd, containingTrace, this);
					children.add(child);
				}
			}
		}

		return Collections.unmodifiableList(children);
	}

	public void addCallee(Callable callee) {
		if (children == null) {
			getCallees();
		}
		children.add(callee);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends Callable> List<T> getCallees(Class<T> type) {

		List<T> result = null;
		for (Callable callable : getCallees()) {
			if (type.isAssignableFrom(callable.getClass())) {
				if (result == null) {
					result = new ArrayList<T>();
				}
				result.add((T) callable);
			}
		}

		return result == null ? Collections.unmodifiableList(Collections.<T> emptyList()) : Collections.unmodifiableList(result);

	}

	@Override
	public int getChildCount() {
		return (int) isData.getChildCount();
	}

	private boolean invocationSequenceConsistsOfSpanId(InvocationSequenceData invocationSequenceData, SpanIdent spanIdent) {
		Queue<InvocationSequenceData> invocationSequenceDatas = new LinkedBlockingQueue<InvocationSequenceData>();
		invocationSequenceDatas.addAll(invocationSequenceData.getNestedSequences());
		while (!invocationSequenceDatas.isEmpty()) {
			InvocationSequenceData nestenInvocationSequenceData = invocationSequenceDatas.poll();
			if (nestenInvocationSequenceData.getSpanIdent() != null && nestenInvocationSequenceData.getSpanIdent().equals(spanIdent)) {
				return true;
			} else {
				invocationSequenceDatas.addAll(nestenInvocationSequenceData.getNestedSequences());
			}
		}
		return false;
	}

}
