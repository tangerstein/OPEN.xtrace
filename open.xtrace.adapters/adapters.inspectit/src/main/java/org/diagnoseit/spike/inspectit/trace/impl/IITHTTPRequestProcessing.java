package org.diagnoseit.spike.inspectit.trace.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.StringTokenizer;

import org.spec.research.open.xtrace.api.core.callables.HTTPMethod;
import org.spec.research.open.xtrace.api.core.callables.HTTPRequestProcessing;
import org.spec.research.open.xtrace.api.utils.StringUtils;

import rocks.inspectit.shared.all.communication.data.HttpTimerData;
import rocks.inspectit.shared.all.communication.data.InvocationSequenceData;

public class IITHTTPRequestProcessing extends IITAbstractNestingCallable implements HTTPRequestProcessing {

	/** Serial version id. */
	private static final long serialVersionUID = 6077981592141469965L;
	private HttpTimerData httpData;

	public IITHTTPRequestProcessing(InvocationSequenceData isData, IITSubTraceImpl containingTrace, IITAbstractNestingCallable parent) {
		super(isData, containingTrace, parent);
		if (((HttpTimerData) this.isData.getTimerData()).getHeaders().containsKey("host")) {
			containingTrace.setHost(((HttpTimerData) this.isData.getTimerData()).getHeaders().get("host"));
		}
		httpData = (HttpTimerData) isData.getTimerData();
	}

	@Override
	public Optional<Map<String, String>> getHTTPAttributes() {
		return Optional.ofNullable(httpData.getSessionAttributes() == null ? null : Collections.unmodifiableMap(httpData.getAttributes()));
	}

	@Override
	public Optional<Map<String, String>> getHTTPHeaders() {
		return Optional.ofNullable(httpData.getHeaders() == null ? null : Collections.unmodifiableMap(httpData.getHeaders()));
	}

	@Override
	public Optional<Map<String, String[]>> getHTTPParameters() {
		Map<String, String[]> httpParameters = null;
		if (null != httpData.getHttpInfo().getQueryString()) {
			StringTokenizer tokenizer = new StringTokenizer(httpData.getHttpInfo().getQueryString(), "&");
			httpParameters = new HashMap<String, String[]>();
			while (tokenizer.hasMoreTokens()) {
				String parameter = tokenizer.nextToken();
				String[] entry = parameter.split("=");
				if (entry.length >= 2) {
					httpParameters.put(entry[0], new String[] { entry[1] });
				}
			}
		}
		return Optional.ofNullable(httpParameters);
	}

	@Override
	public Optional<Map<String, String>> getHTTPSessionAttributes() {
		return Optional.ofNullable(httpData.getSessionAttributes() == null ? null : Collections.unmodifiableMap(httpData.getSessionAttributes()));
	}

	@Override
	public Optional<HTTPMethod> getRequestMethod() {
		return Optional.ofNullable(httpData.getHttpInfo().getRequestMethod() == null ? null : HTTPMethod.valueOf(httpData.getHttpInfo().getRequestMethod().toUpperCase()));
	}

	@Override
	public String getUri() {
		return httpData.getHttpInfo().getUri();
	}

	@Override
	public String toString() {
		return StringUtils.getStringRepresentation(this);
	}

	@Override
	public Optional<Long> getResponseCode() {
		return Optional.ofNullable((long) httpData.getHttpResponseStatus());
	}

	@Override
	public Optional<Map<String, String>> getResponseHTTPHeaders() {
		// Not supported
		return Optional.empty();
	}

	@Override
	public Optional<String> getRequestBody() {
		if (null != httpData.getParameters() && httpData.getParameters().containsKey("_BODY")) {
			return Optional.ofNullable(httpData.getParameters().get("_BODY")[0]);
		} else {
			return Optional.empty();
		}
	}
}
