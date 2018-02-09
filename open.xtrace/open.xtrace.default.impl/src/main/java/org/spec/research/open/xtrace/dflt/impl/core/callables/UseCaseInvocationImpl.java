package org.spec.research.open.xtrace.dflt.impl.core.callables;

import java.io.Serializable;

import org.spec.research.open.xtrace.api.core.callables.Callable;
import org.spec.research.open.xtrace.api.core.callables.UseCaseInvocation;
import org.spec.research.open.xtrace.dflt.impl.core.SubTraceImpl;

public class UseCaseInvocationImpl extends AbstractNestingCallableImpl implements UseCaseInvocation, Serializable {
	/**
	 * use case name
	 */
	private String useCaseName;

	/**
	 * Constructor. Adds the newly created {@link Callable} instance to the passed parent if the
	 * parent is not null!
	 * 
	 * @param parent
	 *            {@link AbstractNestingCallableImpl} that called this Callable
	 * @param containingSubTrace
	 *            the SubTrace containing this Callable
	 * @param usecaseName
	 *            the name of the usecase
	 */
	public UseCaseInvocationImpl(AbstractNestingCallableImpl parent, SubTraceImpl containingSubTrace) {
        super(parent, containingSubTrace);
    }

	/**
	 * Default serialization UID
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String getUseCaseName() {
		return useCaseName;
	}

	/**
	 * Sets usecaseName
	 * 
	 * @param usecaseName
	 *            usecase name
	 */
	public void setUseCaseName(String usecaseName) {
		this.useCaseName = usecaseName;
	}
}
