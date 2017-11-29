package org.spec.research.open.xtrace.adapters.inspectit.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.spec.research.open.xtrace.api.core.callables.Callable;
import org.spec.research.open.xtrace.api.core.callables.SpanMeasurement;
import org.spec.research.open.xtrace.api.utils.StringUtils;

import rocks.inspectit.shared.all.communication.data.InvocationSequenceData;

public class IITSpanCallable extends IITAbstractNestingCallable implements SpanMeasurement {

	/** Serial version id. */
	private static final long serialVersionUID = -4859642073428205883L;

	private Long usecaseID;

	private String usecaseName;

	private String sessionCookie;

	public IITSpanCallable(IITSubTraceImpl containingTrace, IITAbstractNestingCallable parent, Long usecaseID, String usecaseName, String sessionCookie, InvocationSequenceData data) {
		super(data, containingTrace, parent);
		this.setIdentifier(UUID.randomUUID());
		this.usecaseID = usecaseID;
		this.usecaseName = usecaseName;
		this.sessionCookie = sessionCookie;

	}

	/**
	 * Only used for deserialization.
	 * 
	 * @param containingTrace
	 * @param parent
	 * @param usecaseID
	 * @param usecaseName
	 * @param sessionCookie
	 * @param data
	 */
	public IITSpanCallable(IITSubTraceImpl containingTrace, UUID identifier, IITAbstractNestingCallable parent, Long usecaseID, String usecaseName, String sessionCookie, InvocationSequenceData data,
			List<Callable> children) {
		super(data, containingTrace, parent);
		this.setIdentifier(identifier);
		for (Callable child : children) {
			this.addChild(child);
		}
		this.usecaseID = usecaseID;
		this.usecaseName = usecaseName;
		this.sessionCookie = sessionCookie;

	}

	public Optional<Long> getUseCaseID() {
		return Optional.ofNullable(usecaseID);
	}

	public Optional<String> getUseCaseName() {
		return Optional.ofNullable(usecaseName);
	}

	@Override
	public String toString() {
		return StringUtils.getStringRepresentation(this);
	}

	@Override
	public Optional<String> getSessionCookie() {
		return Optional.ofNullable(sessionCookie);
	}
}
