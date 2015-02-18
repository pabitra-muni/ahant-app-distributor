package com.ahant.mobile.application;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import org.codehaus.jackson.map.AnnotationIntrospector;
import org.codehaus.jackson.map.AnnotationIntrospector.Pair;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.codehaus.jackson.map.introspect.JacksonAnnotationIntrospector;
import org.codehaus.jackson.xc.JaxbAnnotationIntrospector;

@Provider
public class CustomJsonMapperProvider implements ContextResolver<ObjectMapper> {
	final ObjectMapper combinedObjectMapper;

	public CustomJsonMapperProvider() {
		combinedObjectMapper = createCombinedObjectMapper();
	}

	public ObjectMapper getContext(Class<?> type) {
		return combinedObjectMapper;
	}

	private static ObjectMapper createCombinedObjectMapper() {

		Pair combinedIntrospector = createJaxbJacksonAnnotationIntrospector();
		ObjectMapper result = new ObjectMapper();
		result.configure(SerializationConfig.Feature.WRAP_ROOT_VALUE, true);
		result.configure(DeserializationConfig.Feature.UNWRAP_ROOT_VALUE, true);
		result.setDeserializationConfig(result.getDeserializationConfig()
				.withAnnotationIntrospector(combinedIntrospector));
		result.setSerializationConfig(result.getSerializationConfig()
				.withAnnotationIntrospector(combinedIntrospector));
		result.setSerializationInclusion(Inclusion.NON_NULL);
				
		return result;
	}

	private static Pair createJaxbJacksonAnnotationIntrospector() {

		AnnotationIntrospector jaxbIntrospector = new JaxbAnnotationIntrospector();
		AnnotationIntrospector jacksonIntrospector = new JacksonAnnotationIntrospector();

		return new AnnotationIntrospector.Pair(jacksonIntrospector,
				jaxbIntrospector);
	}
}
