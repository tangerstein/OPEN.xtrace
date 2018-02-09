package org.spec.research.open.xtrace.dflt.impl.serialization;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.spec.research.open.xtrace.dflt.impl.core.LocationImpl;
import org.spec.research.open.xtrace.dflt.impl.core.SubTraceImpl;
import org.spec.research.open.xtrace.dflt.impl.core.TraceImpl;
import org.spec.research.open.xtrace.dflt.impl.core.callables.DatabaseInvocationImpl;
import org.spec.research.open.xtrace.dflt.impl.core.callables.HTTPRequestProcessingImpl;
import org.spec.research.open.xtrace.dflt.impl.core.callables.MethodInvocationImpl;
import org.spec.research.open.xtrace.dflt.impl.core.callables.RemoteInvocationImpl;

/**
 * Tests the {@link OPENxtraceSerializer} class.
 * 
 * @author Tobias Angerstein
 */
public class JSONOPENxtraceSerializerTest {

	/**
	 * Class under test.
	 */
	OPENxtraceSerializer serializer;

	/**
	 * Tests the {@link OPENxtraceSerializer#serialize(java.util.List)} method.
	 *
	 * @author Tobias Angerstein
	 *
	 */
	public static class Serialize extends JSONOPENxtraceSerializerTest {
		private static final String LABEL = "label";
		private static final String METHOD_NAME = "methodName";
		private static final long RESPONSE_CODE = 200l;
		private static final long SUBTRACE_ID_1 = 2736489592873l;
		private static final long SUBTRACE_ID_2 = 273648111873l;

		private static final long TRACE_ID = 69786786986l;
		private TraceImpl sampleTrace;

		@Before
		public void prepareTestData() {
			sampleTrace = new TraceImpl(TRACE_ID);
			SubTraceImpl subTraceImpl1 = new SubTraceImpl(SUBTRACE_ID_1, null, sampleTrace);
			subTraceImpl1.setLocation(new LocationImpl());
			sampleTrace.setRoot(subTraceImpl1);

			SubTraceImpl subTraceImpl2 = new SubTraceImpl(SUBTRACE_ID_2, null, sampleTrace);
			subTraceImpl2.setLocation(new LocationImpl());

			RemoteInvocationImpl remoteInvocationImpl = new RemoteInvocationImpl(null, subTraceImpl1);
			remoteInvocationImpl.setTargetSubTrace(subTraceImpl2);

			subTraceImpl1.setRoot(remoteInvocationImpl);

			HTTPRequestProcessingImpl httpRequestProcessingImpl = new HTTPRequestProcessingImpl(null, subTraceImpl2);
			httpRequestProcessingImpl.setResponseCode(RESPONSE_CODE);

			subTraceImpl2.setRoot(httpRequestProcessingImpl);

			MethodInvocationImpl methodInvocationImpl = new MethodInvocationImpl(httpRequestProcessingImpl, subTraceImpl2);
			methodInvocationImpl.setMethodName(METHOD_NAME);

			DatabaseInvocationImpl databaseInvocationImpl = new DatabaseInvocationImpl(methodInvocationImpl, subTraceImpl2);
			databaseInvocationImpl.addLabel(LABEL);

			serializer = OPENxtraceSerializationFactory.getInstance().getSerializer(OPENxtraceSerializationFormat.JSON);

		}

		@Test
		public void nullCheck() throws JsonGenerationException, JsonMappingException, IOException {
			OutputStream outputStream = new ByteArrayOutputStream();
			serializer.prepare(outputStream);
			serializer.writeTrace(null);
			serializer.close();

			// line break has to be deleted (Line break is used as separator between different
			// traces)
			Assert.assertEquals(outputStream.toString().substring(0, outputStream.toString().length() - 1), "null");
		}

		@Test
		public void serializeTestData() throws JsonGenerationException, JsonMappingException, IOException {
			OutputStream outputStream = new ByteArrayOutputStream();
			serializer.prepare(outputStream);
			serializer.writeTrace(sampleTrace);
			serializer.close();
			// line break has to be deleted (Line break is used as separator between different
			// traces)
			Assert.assertEquals(JSONTestTemplates.sampleJSONTrace, outputStream.toString().substring(0, outputStream.toString().length() - 1));
		}
	}

}
