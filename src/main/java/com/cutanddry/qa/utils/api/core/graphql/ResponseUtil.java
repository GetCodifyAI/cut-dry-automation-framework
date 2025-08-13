package com.cutanddry.qa.utils.api.core.graphql;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ResponseUtil {
    // Response Headers
    public static String getResponseHeader(Response response, String headerName) {
        return response.getHeader(headerName);
    }

    public static boolean validateResponseHeader(Response response, String headerName, String expectedValue) {
        String actualValue = getResponseHeader(response, headerName);
        return actualValue != null && actualValue.equals(expectedValue);
    }

    //Response Time
    public static long getResponseTime(Response response) {
        return response.getTime();
    }

    public static boolean validateResponseTime(Response response, long expectedTimeMillis) {
        long actualTimeMillis = getResponseTime(response);
        return actualTimeMillis <= expectedTimeMillis;
    }


    public static int getResponseCode(Response response) {
        return response.getStatusCode();
    }

    public static boolean validateResponseCode(Response response, int expectedValue) {
        int actualValue = getResponseCode(response);
        return actualValue == expectedValue;
    }

    public static String getResponseLine(Response response) {
        return response.getStatusLine();
    }

    public static String getResponseValue(Response response, String key) {
        try {
            String resp = response.asString();
            JsonPath js = new JsonPath(resp);
            Object value = js.get(key);
            return (value != null) ? value.toString() : "Key not found";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error processing JSON";
        }
    }

    public static String getStringFromArray(Response response, String arrayName, int index, String key) {
        String resp = response.asString();
        JsonPath js = new JsonPath(resp);
        return js.getString(arrayName + "[" + index + "]." + key);
    }

    public static Integer getIntFromArray(Response response, String arrayName, int index, String key) {
        String resp = response.asString();
        JsonPath js = new JsonPath(resp);
        return js.getInt(arrayName + "[" + index + "]." + key);
    }

    // Nested array
    public static String getNestedArrayValue(Response response, String arrayName, int index, String innerArrayName, int innerIndex, String key) {
        String resp = response.asString();
        JsonPath js = new JsonPath(resp);

        // Navigate to the outer array element
        String outerArray = arrayName + "[" + index + "]";

        // Navigate to the inner array element inside the object
        String innerArray = outerArray + "." + innerArrayName + "[" + innerIndex + "]";

        // Navigate to the value under the inner object
        return js.getString(innerArray + "." + key);
    }


    public static String getJSONpathFromArray(Response response, String key) {
        String paramValue = null;
        String resp = response.asString();
        JsonPath js = new JsonPath(resp);

        int resultCount = js.getInt("array.size()");
        for (int i = 0; i < resultCount; i++) {
            paramValue = js.get(key + "[" + i + "]").toString();
            break;
        }
        return paramValue;
    }
}
