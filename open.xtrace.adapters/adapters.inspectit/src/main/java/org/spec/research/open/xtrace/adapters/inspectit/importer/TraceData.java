package org.spec.research.open.xtrace.adapters.inspectit.importer;

import java.util.List;

import rocks.inspectit.shared.all.cmr.model.PlatformIdent;
import rocks.inspectit.shared.all.communication.data.InvocationSequenceData;
import rocks.inspectit.shared.all.tracing.data.Span;


public class TraceData {
	
	private List<InvocationSequenceData> invocationSequenceDatas;
	private List<PlatformIdent> platformIdents;
	private List<Span> spans;
	
	public TraceData(List<InvocationSequenceData> invocationSequenceDatas,
			List<PlatformIdent> platformIdents, List<Span> spans) {
		this.invocationSequenceDatas = invocationSequenceDatas;
		this.spans = spans;
		this.platformIdents = platformIdents;
	}
	/**
	 * @return the invocationSequenceDatas
	 */
	public List<InvocationSequenceData> getInvocationSequenceDatas() {
		return invocationSequenceDatas;
	}
	/**
	 * @param invocationSequenceDatas the invocationSequenceDatas to set
	 */
	public void setInvocationSequenceDatas(
			List<InvocationSequenceData> invocationSequenceDatas) {
		this.invocationSequenceDatas = invocationSequenceDatas;
	}
	/**
	 * @return the spans
	 */
	public List<Span> getSpans() {
		return spans;
	}
	/**
	 * @param spans the spans to set
	 */
	public void setSpans(List<Span> spans) {
		this.spans = spans;
	}
	/**
	 * @return the platformIdents
	 */
	public List<PlatformIdent> getPlatformIdents() {
		return platformIdents;
	}
	/**
	 * @param platformIdents the platformIdents to set
	 */
	public void setPlatformIdents(List<PlatformIdent> platformIdents) {
		this.platformIdents = platformIdents;
	}
}