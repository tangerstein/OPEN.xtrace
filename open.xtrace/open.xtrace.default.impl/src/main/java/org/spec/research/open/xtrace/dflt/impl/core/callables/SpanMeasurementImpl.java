package org.spec.research.open.xtrace.dflt.impl.core.callables;

import java.io.Serializable;
import java.util.Optional;

import org.spec.research.open.xtrace.api.core.callables.Callable;
import org.spec.research.open.xtrace.api.core.callables.SpanMeasurement;
import org.spec.research.open.xtrace.api.utils.StringUtils;
import org.spec.research.open.xtrace.dflt.impl.core.SubTraceImpl;

public class SpanMeasurementImpl extends AbstractNestingCallableImpl implements SpanMeasurement, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3176266322090859669L;
	
	/**
     * Use case name of the measurement.
     */
    private Optional<String> useCaseName = Optional.empty();
    
    /**
     * Use case ID of the measurement.
     */
    private Optional<Long> useCaseID = Optional.empty();

	/**
	 * SessionCookie
	 */
	private Optional<String> sessionCookie = Optional.empty();
    	
    public SpanMeasurementImpl() {
	}
    
    /**
     * Constructor. Adds the newly created {@link Callable} instance to the passed parent if the parent is not null!
     * 
     * @param parent
     *            {@link AbstractNestingCallableImpl} that called this Callable
     * @param containingSubTrace
     *            the SubTrace containing this Callable
     */
    public SpanMeasurementImpl(AbstractNestingCallableImpl parent,
            SubTraceImpl containingSubTrace) {

        super(parent, containingSubTrace);
    }
	
    /**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<String> getUseCaseName() {
		return useCaseName;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<Long> getUseCaseID() {
		return useCaseID;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<String> getSessionCookie() {
		return sessionCookie;
	}

	/**
	 * Sets the use case name for this measurement.
	 * 
	 * @param useCaseName
	 *            use case name
	 */
	public void setUseCaseName(String useCaseName) {
		this.useCaseName = Optional.ofNullable(useCaseName);
	}
	
	/**
	 * Sets the use case id for this measurement.
	 * 
	 * @param useCaseID use case id
	 */
	public void setUseCaseID(Long useCaseID) {
		this.useCaseID = Optional.ofNullable(useCaseID);
	}

	/**
	 * Sets the session cookie
	 * 
	 * @param useCaseID
	 *            use case id
	 */
	public void setSessionCookie(String sessionCookie) {
		this.sessionCookie = Optional.ofNullable(sessionCookie);
	}


	@Override
	public String toString() {
		return StringUtils.getStringRepresentation(this);
	}

}
