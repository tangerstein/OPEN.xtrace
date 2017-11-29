package org.spec.research.open.xtrace.adapters.inspectit.impl;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.spec.research.open.xtrace.api.core.AdditionalInformation;
import org.spec.research.open.xtrace.api.core.SubTrace;
import org.spec.research.open.xtrace.api.core.callables.Callable;
import org.spec.research.open.xtrace.api.core.callables.NestingCallable;

import rocks.inspectit.shared.all.communication.data.InvocationSequenceData;

public class IITAbstractCallable extends IITAbstractIdentifiableImpl implements Callable {

	/** Serial version id. */
	private static final long serialVersionUID = -7053659087635453623L;

	protected final InvocationSequenceData isData;

	protected IITAbstractNestingCallable parent = null;

	protected IITSubTraceImpl containingTrace;

	private String threadName;

	private long threadID;

	public IITAbstractCallable(InvocationSequenceData isData, IITSubTraceImpl containingTrace, IITAbstractNestingCallable parent) {
		super((isData == null) ? 0 : isData.getId());
		this.isData = isData;
		this.containingTrace = containingTrace;
		this.parent = parent;
	}

	@Override
	public Optional<Collection<AdditionalInformation>> getAdditionalInformation() {
		return Optional.empty();
	}

	@Override
	public <T extends AdditionalInformation> Optional<Collection<T>> getAdditionalInformation(Class<T> arg0) {
		return Optional.empty();
	}

	/**
	 * Sets containing subTrace
	 * 
	 * @param subTrace
	 *            The containing subtrace
	 */
	public void setContainingSubTrace(SubTrace subTrace) {
		this.containingTrace = (IITSubTraceImpl) subTrace;
	}

	@Override
	public SubTrace getContainingSubTrace() {
		return containingTrace;
	}

	@Override
	public Optional<List<String>> getLabels() {
		return Optional.empty();
	}

	@Override
	public NestingCallable getParent() {
		return parent;
	}

	/**
	 * Sets parent.
	 * 
	 * @param parent
	 *            the parent callable
	 */
	public void setParent(IITAbstractNestingCallable parent) {
		this.parent = parent;
	}

	@Override
	public long getTimestamp() {
		if (null != isData) {
			return isData.getTimeStamp().getTime();
		} else {
			return -1;
		}
	}

	public InvocationSequenceData getInvocationSequenceData() {
		return isData;
	}

	@Override
	public Optional<String> getThreadName() {
		return Optional.ofNullable(threadName);
	}

	@Override
	public Optional<Long> getThreadID() {
		return Optional.of(threadID);
	}

}
