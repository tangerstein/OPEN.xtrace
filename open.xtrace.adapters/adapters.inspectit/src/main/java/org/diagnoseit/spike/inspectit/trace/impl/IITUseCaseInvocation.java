package org.diagnoseit.spike.inspectit.trace.impl;

import org.spec.research.open.xtrace.api.core.callables.UseCaseInvocation;

import rocks.inspectit.shared.all.communication.data.InvocationSequenceData;

public class IITUseCaseInvocation extends IITAbstractNestingCallable implements UseCaseInvocation {

	/**
	 * use case name
	 */
	private String useCaseName;

	/**
	 * Constructor.
	 * 
	 * @param isData
	 *            invocationSequence
	 * @param containingTrace
	 *            containing subtrace
	 * @param parent
	 *            parent callable, can be null
	 * @param useCaseName
	 *            use case name
	 */
	public IITUseCaseInvocation(InvocationSequenceData isData, IITSubTraceImpl containingTrace, IITAbstractNestingCallable parent, String useCaseName) {
		super(isData, containingTrace, parent);
		this.useCaseName = useCaseName;
	}

	/**
	 * Default serialization UID
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String getUseCaseName() {
		return useCaseName;
	}

}
