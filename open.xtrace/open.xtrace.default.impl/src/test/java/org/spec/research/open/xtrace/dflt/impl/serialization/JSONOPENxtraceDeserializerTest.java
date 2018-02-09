package org.spec.research.open.xtrace.dflt.impl.serialization;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.spec.research.open.xtrace.api.core.Trace;
import org.spec.research.open.xtrace.dflt.impl.core.LocationImpl;
import org.spec.research.open.xtrace.dflt.impl.core.SubTraceImpl;
import org.spec.research.open.xtrace.dflt.impl.core.TraceImpl;
import org.spec.research.open.xtrace.dflt.impl.core.callables.DatabaseInvocationImpl;
import org.spec.research.open.xtrace.dflt.impl.core.callables.HTTPRequestProcessingImpl;
import org.spec.research.open.xtrace.dflt.impl.core.callables.MethodInvocationImpl;
import org.spec.research.open.xtrace.dflt.impl.core.callables.RemoteInvocationImpl;

public class JSONOPENxtraceDeserializerTest {

	/**
	 * Class under test
	 */
	OPENxtraceDeserializer deserializer;

	/**
	 * Tests the {@link OPENxtraceSerializer#serialize(java.util.List)} method.
	 *
	 * @author Tobias Angerstein
	 *
	 */
	public static class Deserialize extends JSONOPENxtraceDeserializerTest {

		private static final String LABEL = "label";
		private static final String METHOD_NAME = "methodName";
		private static final long RESPONSE_CODE = 200l;
		private static final long SUBTRACE_ID_1 = 2736489592873l;
		private static final long SUBTRACE_ID_2 = 273648111873l;

		private static final long TRACE_ID = 69786786986l;
		private static final String OPEN_XTRACE_JSON_STRING = JSONTestTemplates.sampleJSONTrace;
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

			// This getter changes the target and has to be done to be similar to the serialized
			// trace
			remoteInvocationImpl.getTarget();

			subTraceImpl1.setRoot(remoteInvocationImpl);


			HTTPRequestProcessingImpl httpRequestProcessingImpl = new HTTPRequestProcessingImpl(null, subTraceImpl2);
			httpRequestProcessingImpl.setResponseCode(RESPONSE_CODE);

			subTraceImpl2.setRoot(httpRequestProcessingImpl);

			MethodInvocationImpl methodInvocationImpl = new MethodInvocationImpl(httpRequestProcessingImpl, subTraceImpl2);
			methodInvocationImpl.setMethodName(METHOD_NAME);

			DatabaseInvocationImpl databaseInvocationImpl = new DatabaseInvocationImpl(methodInvocationImpl, subTraceImpl2);
			databaseInvocationImpl.addLabel(LABEL);
			
			deserializer = OPENxtraceSerializationFactory.getInstance().getDeserializer(OPENxtraceSerializationFormat.JSON);
		}

		@Test
		public void deserializeTestData() throws JsonGenerationException, JsonMappingException, IOException {
			deserializer.setSource(new ByteArrayInputStream(OPEN_XTRACE_JSON_STRING.getBytes()));
			Trace deserializedTrace = deserializer.readNext();
			try {
				Assert.assertEquals(comp(sampleTrace, deserializedTrace, 0), 0);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}

		private static List<Object> seen = new ArrayList<>();

		/**
		 * Compares two objects.
		 *
		 * @param o1
		 *            first object
		 * @param o2
		 *            second object
		 * @param numberOfDifferences
		 *            the initial number of differences
		 * @return the total number of differences
		 * @throws IllegalAccessException
		 */
		private int comp(Object o1, Object o2, int numberOfDifferences) throws IllegalAccessException {
			if (o1 == null && o2 != null) {
				numberOfDifferences++;
			} else if (o1 != null && o2 == null) {
				numberOfDifferences++;
			}
			ArrayList<Field> fields = new ArrayList<Field>();

			getAllFields(fields, o1.getClass());

			for (Field f : fields) {
				f.setAccessible(true);
				Object r1 = f.get(o1);
				Object r2 = f.get(o2);
				if (r1 == null && r2 != null) {
					numberOfDifferences++;
					continue;
				} else if (r1 != null && r2 == null) {
					numberOfDifferences++;
					continue;
				} else if (r1 == null && r2 == null) {
					continue;
				}
				if (seen.contains(r1) || seen.contains(r2)) {
					continue;
				} else {
					seen.add(r1);
					seen.add(r2);
				}
				if (isPrimitiveOrPrimitiveWrapperOrString(r1.getClass())) {
					if (!r1.equals(r2)) {
						numberOfDifferences++;
					}
				} else {

					numberOfDifferences += comp(r1, r2, numberOfDifferences);
				}
			}
			return numberOfDifferences;
		}

		/**
		 * Returns all fields including the fields of the superclass.
		 *
		 * @param fields
		 *            the list, which will be filled.
		 * @param type
		 *            the class type
		 * @return the filled list
		 */
		private static List<Field> getAllFields(List<Field> fields, Class<?> type) {
			fields.addAll(Arrays.asList(type.getDeclaredFields()));
			if (type.getSuperclass() != null) {
				getAllFields(fields, type.getSuperclass());
			}
			return fields;
		}

		/**
		 * Checks whether a type is a primitive
		 *
		 * @param type
		 *            the class type
		 * @return is primitive
		 */
		public static boolean isPrimitiveOrPrimitiveWrapperOrString(Class<?> type) {
			return (type.isPrimitive() && type != void.class) || type == Double.class || type == Float.class || type == Long.class || type == Integer.class || type == Short.class || type == Character.class
					|| type == Byte.class || type == Boolean.class || type == String.class;
		}
	}

}