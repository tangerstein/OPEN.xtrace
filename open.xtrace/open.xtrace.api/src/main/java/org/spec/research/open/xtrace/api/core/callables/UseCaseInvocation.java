package org.spec.research.open.xtrace.api.core.callables;

/**
 * Description of a user defined use case, which is defined by a name.
 *
 */
public interface UseCaseInvocation extends NestingCallable {

	/**
	 * Returns a name of the use case. The name should describe the context of the use case in a
	 * proper way.
	 * 
	 * @return the name.
	 */
	String getUseCaseName();
}
