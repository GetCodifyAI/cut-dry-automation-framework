package com.cutanddry.qa.api;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Arrays;

public class JacksonUtil {
    public JacksonUtil() {
    }

    // Converts an object (RequestBase) to a JSON string, ignoring null values by default.
    public static String getAsString(RequestBase requestBase) {
        return getAsString(requestBase, false);
    }

    // Converts an object (RequestBase) to a JSON string. If ignoreNull is true, null values are excluded from the JSON string.
    public static final String getAsString(RequestBase body, boolean ignoreNull) {
        ObjectMapper mapper = new ObjectMapper();
        if (ignoreNull) {
            mapper.setSerializationInclusion(Include.NON_NULL);
        }

        String jsonInString = "";

        try {
            jsonInString = mapper.writeValueAsString(body);
        } catch (JsonProcessingException var5) {
            jsonInString = Arrays.toString(var5.getStackTrace());
        }

        return jsonInString;
    }

    // Converts an array of objects (RequestBase...) to a JSON array string.
    public static String getAsArrayString(RequestBase... requestBase) {
        return getAsArrayString(true, requestBase);
    }

    // Converts an array of objects (RequestBase...) to a JSON array string. If ignoreNull is true, null values are excluded from the JSON string.
    public static String getAsArrayString(boolean ignoreNull, RequestBase... requestBase) {
        ObjectMapper mapper = new ObjectMapper();
        if (ignoreNull) {
            mapper.setSerializationInclusion(Include.NON_NULL);
        }

        String jsonInString = "";

        try {
            jsonInString = mapper.writeValueAsString(requestBase);
        } catch (JsonProcessingException var5) {
            jsonInString = Arrays.toString(var5.getStackTrace());
        }

        return jsonInString;
    }

    // Deserializes a JSON string (jsonString) to a RequestBase object.
    public static RequestBase getRequestAsObject(String jsonString, boolean ignoreNull) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        if (ignoreNull) {
            mapper.setSerializationInclusion(Include.NON_NULL);
        }

        return (RequestBase)mapper.readValue(jsonString, RequestBase.class);
    }

    // Deserializes a JSON string (jsonString) to a ResponseBase object.
    public static ResponseBase getResponseAsObject(String jsonString, boolean ignoreNull) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        if (ignoreNull) {
            mapper.setSerializationInclusion(Include.NON_NULL);
        }

        return (ResponseBase)mapper.readValue(jsonString, ResponseBase.class);
    }

    // Deserializes a JSON string (jsonString) to an object of the specified class (cls).
    public static ResponseBase getResponseAsObject(String jsonString, boolean ignoreNull, Class<?> cls) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        if (ignoreNull) {
            mapper.setSerializationInclusion(Include.NON_NULL);
        }

        return (ResponseBase)mapper.readValue(jsonString, cls);
    }

    // Deserializes a JSON string (response) to an object of the specified class (classType). This method ignores unknown properties in the JSON that are not present in the target class.
    public static <R> Object getResponseAsObject(String response, Class<R> classType) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {
            return mapper.readValue(response, classType);
        } catch (JsonProcessingException var4) {
            return null;
        } catch (IOException var5) {
            return null;
        }
    }
}
