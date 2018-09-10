package org.spec.research.open.xtrace.dflt.impl.serialization.realizations;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.regex.Pattern;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.Version;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.module.SimpleModule;
import org.codehaus.jackson.type.TypeReference;
import org.spec.research.open.xtrace.api.core.AdditionalInformation;
import org.spec.research.open.xtrace.api.core.SubTrace;
import org.spec.research.open.xtrace.api.core.Trace;
import org.spec.research.open.xtrace.api.core.callables.Callable;
import org.spec.research.open.xtrace.api.core.callables.DatabaseInvocation;
import org.spec.research.open.xtrace.api.core.callables.HTTPMethod;
import org.spec.research.open.xtrace.api.core.callables.HTTPRequestProcessing;
import org.spec.research.open.xtrace.api.core.callables.MethodInvocation;
import org.spec.research.open.xtrace.api.core.callables.NestingCallable;
import org.spec.research.open.xtrace.api.core.callables.RemoteInvocation;
import org.spec.research.open.xtrace.api.core.callables.TimedCallable;
import org.spec.research.open.xtrace.api.core.callables.UseCaseInvocation;
import org.spec.research.open.xtrace.dflt.impl.core.LocationImpl;
import org.spec.research.open.xtrace.dflt.impl.core.SubTraceImpl;
import org.spec.research.open.xtrace.dflt.impl.core.TraceImpl;
import org.spec.research.open.xtrace.dflt.impl.core.callables.AbstractCallableImpl;
import org.spec.research.open.xtrace.dflt.impl.core.callables.AbstractNestingCallableImpl;
import org.spec.research.open.xtrace.dflt.impl.core.callables.AbstractTimedCallableImpl;
import org.spec.research.open.xtrace.dflt.impl.core.callables.DatabaseInvocationImpl;
import org.spec.research.open.xtrace.dflt.impl.core.callables.HTTPRequestProcessingImpl;
import org.spec.research.open.xtrace.dflt.impl.core.callables.MethodInvocationImpl;
import org.spec.research.open.xtrace.dflt.impl.core.callables.RemoteInvocationImpl;
import org.spec.research.open.xtrace.dflt.impl.core.callables.UseCaseInvocationImpl;
import org.spec.research.open.xtrace.dflt.impl.serialization.OPENxtraceDeserializer;

/**
 * OPEN.xtrace deserializer.
 * 
 * @author Tobias Angerstein
 *
 */
public class JsonOPENxtraceDeserializer implements OPENxtraceDeserializer {

	/**
	 * Jackson object mapper
	 */
	ObjectMapper objectMapper;

	/**
	 * String list type reference. Used to deserialize a List<String>
	 */
	TypeReference<ArrayList<String>> stringListTypRef = new TypeReference<ArrayList<String>>() {
	};

	/**
	 * String map type reference. Used to deserialize a Map<String, String>
	 */
	TypeReference<HashMap<String, String[]>> mapTypRefStringArray = new TypeReference<HashMap<String, String[]>>() {
	};

	/**
	 * String map type reference. Used to deserialize a Map<String, String>
	 */
	TypeReference<HashMap<String, String>> mapTypRefString = new TypeReference<HashMap<String, String>>() {
	};

	/**
	 * Scanner to scan serialized traces from
	 */
	private Scanner scanner;

	/**
	 * Constructor.
	 */
	public JsonOPENxtraceDeserializer() {
		createObjectMapper();
	}

	/**
	 * Deserializes JSON into a list of {@link Trace}.
	 * 
	 * @param serializedTrace
	 *            JSON string
	 * @return a @{@link ArrayList} containing {@link TraceImpl}}
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public List<Trace> deserialize(String serializedTrace) throws JsonGenerationException, JsonMappingException, IOException {
		TypeReference<ArrayList<Trace>> resultlistTypRef = new TypeReference<ArrayList<Trace>>() {
		};
		return objectMapper.readValue(serializedTrace, resultlistTypRef);
	}

	/**
	 * Deserializes JSON into {@link Trace}.
	 * 
	 * @param serializedTrace
	 *            JSON string
	 * @return a @{@link ArrayList} containing {@link IITTraceImpl}}
	 * @throws JsonParseException
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	private Trace deserializeTrace(String serializedTrace) throws JsonParseException, JsonMappingException, IOException {
		return objectMapper.readValue(serializedTrace, Trace.class);
	}

	/**
	 * Creates Object Mapper and registers Serializers.
	 */
	private void createObjectMapper() {
		objectMapper = new ObjectMapper();

		SimpleModule deserializationModule = new SimpleModule("", new Version(2, 3, 2, "Deserialization open.XTRACE"));
		deserializationModule.addDeserializer(UseCaseInvocation.class, new UseCaseInvocationDeserializer());
		deserializationModule.addDeserializer(Trace.class, new TraceDeserializer());
		deserializationModule.addDeserializer(SubTrace.class, new SubTraceDeserializer());
		deserializationModule.addDeserializer(RemoteInvocation.class, new RemoteInvocationDeserializer());
		deserializationModule.addDeserializer(HTTPRequestProcessing.class, new HTTPRequestProcessingDeserializer());
		deserializationModule.addDeserializer(MethodInvocation.class, new MethodInvocationDeserializer());
		deserializationModule.addDeserializer(DatabaseInvocation.class, new DatabaseInvocationDeserializer());

		objectMapper.registerModule(deserializationModule);
	}

	// Deserializers

	/**
	 * Deserializes {@link IITTraceImpl}
	 * 
	 * @author Tobias Angerstein
	 *
	 */
	class TraceDeserializer extends JsonDeserializer<Trace> {

		public TraceDeserializer() {
			super();
		}

		@Override
		public Trace deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
			JsonNode node = jp.getCodec().readTree(jp);
			TraceImpl traceImpl = new TraceImpl(node.get("traceId").getLongValue());
			JsonParser subTraceImplJsonParser = new JsonFactory().createJsonParser(node.get("rootOfTrace").toString());
			subTraceImplJsonParser.setCodec(jp.getCodec());
			SubTraceImpl subTrace = (SubTraceImpl) new SubTraceDeserializer(traceImpl).deserialize(subTraceImplJsonParser, ctxt);
			traceImpl.setIdentifier(objectMapper.treeToValue(node.get("identifier"), Object.class));
			traceImpl.setRoot(subTrace);
			return traceImpl;
		}
	}

	/**
	 * Deserializes {@link SubTrace}
	 * 
	 * @author Tobias Angerstein
	 *
	 */
	class SubTraceDeserializer extends JsonDeserializer<SubTrace> {
		private TraceImpl containingTrace;

		public SubTraceDeserializer() {
			super();
			containingTrace = null;
		}

		public SubTraceDeserializer(TraceImpl containingTrace) {
			super();
			this.containingTrace = containingTrace;
		}

		@Override
		public SubTrace deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
			JsonNode node = jp.getCodec().readTree(jp);
			JsonNode callableNode = node.get("rootOfSubTrace");
			JsonParser childNodeparser = new JsonFactory().createJsonParser(callableNode.toString());
			childNodeparser.setCodec(jp.getCodec());
			SubTraceImpl subTraceImpl = new SubTraceImpl(node.get("subTraceId").getLongValue(), null, containingTrace);
			Callable callable = null;
			if (callableNode.get("@class").getTextValue().equals("org.spec.research.open.xtrace.api.core.callables.RemoteInvocation")) {
				callable = new RemoteInvocationDeserializer(null, subTraceImpl).deserialize(childNodeparser, ctxt);
			} else if (callableNode.get("@class").getTextValue().equals("org.spec.research.open.xtrace.api.core.callables.HTTPRequestProcessing")) {
				callable = new HTTPRequestProcessingDeserializer(null, subTraceImpl).deserialize(childNodeparser, ctxt);
			} else if (callableNode.get("@class").getTextValue().equals("org.spec.research.open.xtrace.api.core.callables.MethodInvocation")) {
				callable = new MethodInvocationDeserializer(null, subTraceImpl).deserialize(childNodeparser, ctxt);
			} else if (callableNode.get("@class").getTextValue().equals("org.spec.research.open.xtrace.api.core.callables.UseCaseInvocation")) {
				callable = new UseCaseInvocationDeserializer(null, subTraceImpl).deserialize(childNodeparser, ctxt);
			} else if (callableNode.get("@class").getTextValue().equals("org.spec.research.open.xtrace.api.core.callables.DatabaseInvocation")) {
				callable = new DatabaseInvocationDeserializer(null, subTraceImpl).deserialize(childNodeparser, ctxt);
			}
			subTraceImpl.setIdentifier(objectMapper.treeToValue(node.get("identifier"), Object.class));
			TypeReference<ArrayList<SubTrace>> subTraceListType = new TypeReference<ArrayList<SubTrace>>() {
			};

			if (null != node.get("subtraces")) {
				List<SubTraceImpl> childSubTraces = objectMapper.readValue(node.get("subtraces"), subTraceListType);
				// Add SubTraces
				for (SubTraceImpl childSubTrace : childSubTraces) {
					subTraceImpl.addSubTrace(childSubTrace);
				}
			}
			subTraceImpl.setRoot((AbstractCallableImpl) callable);

			// Add location
			LocationImpl location = new LocationImpl(node.get("host").getTextValue(), node.get("port").asInt(), node.get("runtimeEnvironment").getTextValue(), node.get("application").getTextValue(),
					node.get("businessTransaction").getTextValue());
			location.setNodeType(node.get("nodeType").getTextValue());
			location.setServerName(node.get("serverName").getTextValue());
			subTraceImpl.setLocation(location);

			return subTraceImpl;
		}
	}

	/**
	 * Deserializes {@link UseCaseInvocation}
	 * 
	 * @author Tobias Angerstein
	 *
	 */
	class UseCaseInvocationDeserializer extends NestingCallableDeserializer<UseCaseInvocation> {

		public UseCaseInvocationDeserializer() {
			super(null);
		}

		public UseCaseInvocationDeserializer(AbstractNestingCallableImpl parent, SubTraceImpl subtraceImpl) {
			super(new UseCaseInvocationImpl(parent, subtraceImpl));
		}

		@Override
		public UseCaseInvocation deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
			JsonNode node = jp.getCodec().readTree(jp);
			// Execute super method
			UseCaseInvocationImpl useCaseInvocation = (UseCaseInvocationImpl) super.deserialize(node, ctxt);
			useCaseInvocation.setUseCaseName(node.get("useCaseName").getTextValue());
			return useCaseInvocation;
		}
	}

	/**
	 * Deserializes {@link RemoteInvocation}
	 * 
	 * @author Tobias Angerstein
	 *
	 */
	class RemoteInvocationDeserializer extends TimedCallableDeserializer<RemoteInvocation> {

		public RemoteInvocationDeserializer() {
			super(null);
		}

		public RemoteInvocationDeserializer(AbstractNestingCallableImpl parent, SubTraceImpl subtraceImpl) {
			super(new RemoteInvocationImpl(parent, subtraceImpl));
		}

		@Override
		public RemoteInvocation deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
			JsonNode node = jp.getCodec().readTree(jp);

			// Execute super method
			RemoteInvocationImpl remoteInvocation = (RemoteInvocationImpl) super.deserialize(node, ctxt);
			SubTraceImpl targetSubTrace = null;

			if (!node.get("targetSubTrace").isNull()) {
				JsonParser targetSubTraceJsonParser = new JsonFactory(jp.getCodec()).createJsonParser(node.get("targetSubTrace").toString());
				targetSubTrace = (SubTraceImpl) new SubTraceDeserializer((TraceImpl) remoteInvocation.getContainingSubTrace().getContainingTrace()).deserialize(targetSubTraceJsonParser, ctxt);
			}

			remoteInvocation.setTargetSubTrace(targetSubTrace);
			remoteInvocation.setTarget(node.get("target").getTextValue());

			return remoteInvocation;
		}
	}

	/**
	 * Deserializes {@link HTTPRequestProcessing}
	 * 
	 * @author Tobias Angerstein
	 *
	 */
	class HTTPRequestProcessingDeserializer extends NestingCallableDeserializer<HTTPRequestProcessing> {

		public HTTPRequestProcessingDeserializer() {
			super(null);
		}

		public HTTPRequestProcessingDeserializer(AbstractNestingCallableImpl parent, SubTraceImpl subtraceImpl) {
			super(new HTTPRequestProcessingImpl(parent, subtraceImpl));
		}

		@Override
		public HTTPRequestProcessing deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
			JsonNode node = jp.getCodec().readTree(jp);

			// Execute super method.
			HTTPRequestProcessingImpl httpRequestProcessing = (HTTPRequestProcessingImpl) super.deserialize(node, ctxt);

			httpRequestProcessing.setUri(node.get("uri").getTextValue());

			httpRequestProcessing.setResponseHTTPHeaders(objectMapper.readValue(node.get("responseHTTPHeaders"), mapTypRefString));
			httpRequestProcessing.setResponseCode(node.get("responseCode").getLongValue());
			httpRequestProcessing.setRequestMethod(objectMapper.treeToValue(node.get("requestMethod"), HTTPMethod.class));
			httpRequestProcessing.setHTTPAttributes(objectMapper.readValue(node.get("HTTPSessionAttributes"), mapTypRefString));
			httpRequestProcessing.setHTTPParameters(objectMapper.readValue(node.get("HTTPParameters"), mapTypRefStringArray));
			httpRequestProcessing.setHTTPHeaders(objectMapper.readValue(node.get("HTTPHeaders"), mapTypRefString));
			httpRequestProcessing.setHTTPAttributes(objectMapper.readValue(node.get("HTTPAttributes"), mapTypRefString));
			httpRequestProcessing.setRequestBody(node.get("body").getTextValue());

			return httpRequestProcessing;
		}
	}

	/**
	 * Deserializes {@link MethodInvocation}
	 * 
	 * @author Tobias Angerstein
	 *
	 */
	class MethodInvocationDeserializer extends NestingCallableDeserializer<MethodInvocation> {

		public MethodInvocationDeserializer() {
			super(null);
		}

		public MethodInvocationDeserializer(AbstractNestingCallableImpl parent, SubTraceImpl subtraceImpl) {
			super(new MethodInvocationImpl(parent, subtraceImpl));
		}

		@Override
		public MethodInvocation deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
			JsonNode node = jp.getCodec().readTree(jp);
			// Execute super class
			MethodInvocationImpl methodInvocation = (MethodInvocationImpl) super.deserialize(node, ctxt);

			methodInvocation.setSyncTime(node.get("syncTime").getLongValue() == -1l ? Optional.empty() : Optional.of(node.get("syncTime").getLongValue()));
			methodInvocation.setSignature(node.get("signature").getTextValue());
			methodInvocation.setReturnType(node.get("returnType").getTextValue());
			methodInvocation.setReturnValue(objectMapper.treeToValue(node.get("returnValue"), Object.class) == null ? Optional.empty() : Optional.of(objectMapper.treeToValue(node.get("returnValue"), Object.class)));

			// Add parameterValues
			TypeReference<HashMap<Integer, String>> mapTypRef = new TypeReference<HashMap<Integer, String>>() {
			};
			HashMap<Integer, String> parameterValues = objectMapper.readValue(node.get("parameterValues"), mapTypRef);

			if (null != parameterValues) {
				for (Integer key : parameterValues.keySet()) {
					methodInvocation.addParameterValue(key, parameterValues.get(key));
				}
			}
			methodInvocation
					.setParameterTypes(objectMapper.readValue(node.get("parameterTypes"), stringListTypRef) == null ? new ArrayList<String>() : objectMapper.readValue(node.get("parameterTypes"), stringListTypRef));
			methodInvocation.setPackageName(node.get("packageName").getTextValue());
			methodInvocation.setMethodName(node.get("methodName").getTextValue());
			methodInvocation.setGCTime(node.get("GCTime").getLongValue() == -1l ? Optional.empty() : Optional.of(node.get("GCTime").getLongValue()));
			methodInvocation.setCPUTime(node.get("CPUTime").getLongValue() == -1l ? Optional.empty() : Optional.of(node.get("CPUTime").getLongValue()));
			methodInvocation.setClassName(node.get("className").getTextValue());

			return methodInvocation;
		}
	}

	/**
	 * Deserializes {@link DatabaseInvocation}
	 * 
	 * @author Tobias Angerstein
	 *
	 */
	class DatabaseInvocationDeserializer extends TimedCallableDeserializer<DatabaseInvocation> {

		public DatabaseInvocationDeserializer() {
			super(null);
		}

		public DatabaseInvocationDeserializer(AbstractNestingCallableImpl parent, SubTraceImpl subtraceImpl) {
			super(new DatabaseInvocationImpl(parent, subtraceImpl));
		}

		@Override
		public DatabaseInvocation deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
			JsonNode node = jp.getCodec().readTree(jp);

			// Execute super method
			DatabaseInvocationImpl databaseInvocation = (DatabaseInvocationImpl) super.deserialize(node, ctxt);

			databaseInvocation.setDBProductName(Optional.ofNullable(node.get("dbProductName").getTextValue()));
			databaseInvocation.setDBProductVersion(Optional.ofNullable(node.get("dbProductVersion").getTextValue()));
			databaseInvocation.setDBUrl(Optional.ofNullable(node.get("dbURL").getTextValue()));

			// Add parameterValues
			TypeReference<HashMap<Integer, String>> mapTypRef = new TypeReference<HashMap<Integer, String>>() {
			};
			HashMap<Integer, String> parameterBindings = objectMapper.readValue(node.get("parameterBindings"), mapTypRef);

			if (null != parameterBindings) {
				for (Integer key : parameterBindings.keySet()) {
					databaseInvocation.addParameterBinding(key, parameterBindings.get(key));
				}
			}
			databaseInvocation.setPrepared(node.get("prepared").asBoolean());
			databaseInvocation.setSQLStatement(node.get("SQLStatement").getTextValue());

			return databaseInvocation;
		}
	}

	/**
	 * Deserializes TimedCallable
	 * 
	 * @author Tobias Angerstein
	 * @param <T>
	 *            TimedCallable
	 *
	 */
	abstract class TimedCallableDeserializer<T extends TimedCallable> extends JsonDeserializer<T> {
		private AbstractTimedCallableImpl callable;

		public TimedCallableDeserializer(AbstractTimedCallableImpl callable) {
			super();
			this.callable = callable;
		}

		public T deserialize(JsonNode node, DeserializationContext ctxt) throws IOException, JsonProcessingException {
			callable.setTimestamp(node.get("timeStamp").getLongValue());
			Iterator<String> nodeIterator = node.getFieldNames();
			// Add additional information
			while (nodeIterator.hasNext()) {
				String fieldName = nodeIterator.next();
				if (fieldName.contains("additionalInformation")) {
					callable.addAdditionalInformation(new AdditionalInformation() {

						@Override
						public Object getValue() {
							try {
								return objectMapper.treeToValue(node.get(fieldName), Object.class);
							} catch (IOException e) {
								e.printStackTrace();
								return null;
							}
						}

						@Override
						public String getName() {
							return fieldName.substring("additionalInformation.".length(), fieldName.length());
						}
					});
				}
			}
			callable.setResponseTime(node.get("responseTime").getLongValue());
			callable.setThreadID(node.get("threadID").getLongValue());
			callable.setThreadName(node.get("threadName").getTextValue());
			callable.setIdentifier(objectMapper.treeToValue(node.get("identifier"), Object.class));

			// Add labels
			List<String> labels = objectMapper.readValue(node.get("labels"), stringListTypRef);
			if (null != labels) {
				for (String label : labels) {
					callable.addLabel(label);
				}
			}
			return (T) callable;
		}

	}

	/**
	 * Deserializes NestingCallable
	 * 
	 * @author Tobias Angerstein
	 *
	 * @param <T>
	 */
	abstract class NestingCallableDeserializer<T extends NestingCallable> extends TimedCallableDeserializer<T> {

		public NestingCallableDeserializer(AbstractNestingCallableImpl callable) {
			super(callable);
		}

		@Override
		public T deserialize(JsonNode node, DeserializationContext ctxt) throws IOException, JsonProcessingException {
			// Execute super method.
			AbstractNestingCallableImpl callable = (AbstractNestingCallableImpl) super.deserialize(node, ctxt);

			// Add children
			if (null != node.get("children")) {
				for (JsonNode callableJson : node.get("children")) {
					JsonParser callableJsonParser = new JsonFactory(ctxt.getParser().getCodec()).createJsonParser(callableJson.toString());
					if (callableJson.get("@class").getTextValue().equals("org.spec.research.open.xtrace.api.core.callables.RemoteInvocation")) {
						new RemoteInvocationDeserializer(callable, (SubTraceImpl) callable.getContainingSubTrace()).deserialize(callableJsonParser, ctxt);
					} else if (callableJson.get("@class").getTextValue().equals("org.spec.research.open.xtrace.api.core.callables.HTTPRequestProcessing")) {
						new HTTPRequestProcessingDeserializer(callable, (SubTraceImpl) callable.getContainingSubTrace()).deserialize(callableJsonParser, ctxt);
					} else if (callableJson.get("@class").getTextValue().equals("org.spec.research.open.xtrace.api.core.callables.MethodInvocation")) {
						new MethodInvocationDeserializer(callable, (SubTraceImpl) callable.getContainingSubTrace()).deserialize(callableJsonParser, ctxt);
					} else if (callableJson.get("@class").getTextValue().equals("org.spec.research.open.xtrace.api.core.callables.UseCaseInvocation")) {
						new UseCaseInvocationDeserializer(callable, (SubTraceImpl) callable.getContainingSubTrace()).deserialize(callableJsonParser, ctxt);
					} else if (callableJson.get("@class").getTextValue().equals("org.spec.research.open.xtrace.api.core.callables.DatabaseInvocation")) {
						new DatabaseInvocationDeserializer(callable, (SubTraceImpl) callable.getContainingSubTrace()).deserialize(callableJsonParser, ctxt);
					}
				}
			}
			return (T) callable;
		}
	}

	@Override
	public void setSource(InputStream inStream) {
		scanner = new Scanner(inStream);
		scanner.useDelimiter(Pattern.compile("\n"));
	}

	@Override
	public Trace readNext() {
		try {
			return scanner.hasNext() ? deserializeTrace(scanner.next()) : null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void close() {
		scanner.close();
	}
}
