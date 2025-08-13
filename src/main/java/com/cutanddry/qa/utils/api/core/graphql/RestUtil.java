package com.cutanddry.qa.utils.api.core.graphql;

import com.cutanddry.qa.common.Constants;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Base64;
import java.util.Map;

public class RestUtil {

    public static RequestSpecification genralReq;
    public static String baseURL;
    public static int port = 0;


    public RequestSpecification genralRequestSpecification(String baseURL, String logName) throws IOException {
        if (port != 0 && !Constants.PORT.isEmpty()) {
            RestAssured.port = port;
        }
        genralReq = null;
        if (genralReq == null) {
            PrintStream log = new PrintStream(new FileOutputStream(System.getProperty("user.dir") + "//Project_Logs//Project_" + logName + "_log.txt"));

            genralReq = new RequestSpecBuilder()
                    .setBaseUri(baseURL)
                    .setContentType(ContentType.JSON)
                    .addFilter(RequestLoggingFilter.logRequestTo(log))
                    .addFilter(ResponseLoggingFilter.logResponseTo(log))
                    .build();
            return genralReq;
        }
        return genralReq;
    }

    public RequestSpecification requestSpecificationWithHeader(String baseURL, Map<String, String> requestHeaders,
                                                               String logName) throws IOException {
        if (port != 0 && !Constants.PORT.isEmpty()) {
            RestAssured.port = port;
        }
        genralReq = null;
        if (genralReq == null) {
            PrintStream log = new PrintStream(new FileOutputStream(System.getProperty("user.dir") + "//Project_Logs//Project_" + logName + "_log.txt"));

            genralReq = new RequestSpecBuilder()
                    .setBaseUri(baseURL)
                    .addHeaders(requestHeaders)
                    .addFilter(RequestLoggingFilter.logRequestTo(log))
                    .addFilter(ResponseLoggingFilter.logResponseTo(log))
                    .build();
            return genralReq;
        }
        return genralReq;
    }

    public static RequestSpecification requestSpecificationWithHeader(String baseURL, Map<String, String> requestHeaders) {
        if (port != 0 && !Constants.PORT.isEmpty()) {
            RestAssured.port = port;
        }
        genralReq = null;
        if (genralReq == null) {
            genralReq = new RequestSpecBuilder()
                    .setBaseUri(baseURL)
                    .addHeaders(requestHeaders)
                    .build();
            return genralReq;
        }
        return genralReq;
    }

    public static RequestSpecification requestSpecificationMultipleQueryParam(String baseURL, Map<String, String> parameters, Map<String, String> requestHeaders) {
        if (port != 0 && !Constants.PORT.isEmpty()) {
            RestAssured.port = port;
        }
        genralReq = null;
        if (genralReq == null) {

            genralReq = new RequestSpecBuilder()
                    .setBaseUri(baseURL)
                    .addQueryParams(parameters)
                    .addHeaders(requestHeaders)
                    .build();
            return genralReq;
        }
        return genralReq;
    }

    public static RequestSpecification requestSpecificationWithPathParam(String baseURL, Map<String, String> parameters, Map<String, String> requestHeaders) {
        if (port != 0 && !Constants.PORT.isEmpty()) {
            RestAssured.port = port;
        }
        genralReq = null;
        if (genralReq == null) {

            genralReq = new RequestSpecBuilder()
                    .setBaseUri(baseURL)
                    .addPathParams(parameters)
                    .addHeaders(requestHeaders)
                    .build();
            return genralReq;
        }
        return genralReq;
    }

    public RequestSpecification requestSpecificationWithAQueryParam(String baseURL, String key, String keyValue,
                                                                    Map<String, String> requestHeaders) throws IOException {
        if (port != 0 && !Constants.PORT.isEmpty()) {
            RestAssured.port = port;
        }
        genralReq = null;
        if (genralReq == null) {

            genralReq = new RequestSpecBuilder()
                    .setBaseUri(baseURL)
                    .addQueryParam(key, keyValue)
                    .addHeaders(requestHeaders)
                    .build();
            return genralReq;
        }
        return genralReq;
    }

    public RequestSpecification requestSpecificationWithAQueryParam(String baseURL, String key, String keyValue,
                                                                    Map<String, String> requestHeaders, String logName) throws IOException {
        if (port != 0 && !Constants.PORT.isEmpty()) {
            RestAssured.port = port;
        }
        genralReq = null;
        if (genralReq == null) {
            PrintStream log = new PrintStream(new FileOutputStream(System.getProperty("user.dir") + "//Project_Logs//Project_" + logName + "_log.txt"));

            genralReq = new RequestSpecBuilder()
                    .setBaseUri(baseURL)
                    .addQueryParam(key, keyValue)
                    .addHeaders(requestHeaders)
                    .addFilter(RequestLoggingFilter.logRequestTo(log))
                    .addFilter(ResponseLoggingFilter.logResponseTo(log))
                    .build();
            return genralReq;
        }
        return genralReq;
    }

    /*Need to add following map in step definition class
     * Map<String, String> params = new HashMap<String, String>();
     * params.put("param1", "value1");
     *  params.put("param2", "value2");
     */
    public RequestSpecification requestSpecificationMultipleQueryParam(String baseURL, Map<String, String> parameters, Map<String, String> requestHeaders, String logName)
            throws IOException {
        if (port != 0 && !Constants.PORT.isEmpty()) {
            RestAssured.port = port;
        }
        genralReq = null;
        if (genralReq == null) {
            PrintStream log = new PrintStream(new FileOutputStream(System.getProperty("user.dir") + "//Project_Logs//Project_" + logName + "_log.txt"));

            genralReq = new RequestSpecBuilder()
                    .setBaseUri(baseURL)
                    .addQueryParams(parameters)
                    .addHeaders(requestHeaders)
                    .addFilter(RequestLoggingFilter.logRequestTo(log))
                    .addFilter(ResponseLoggingFilter.logResponseTo(log))
                    .build();
            return genralReq;
        }
        return genralReq;
    }

    public RequestSpecification requestSpecificationWithAPathParam(String baseURL, String key, String keyValue,
                                                                   Map<String, String> requestHeaders, String logName) throws IOException {
        if (port != 0 && !Constants.PORT.isEmpty()) {
            RestAssured.port = port;
        }
        genralReq = null;
        if (genralReq == null) {
            PrintStream log = new PrintStream(new FileOutputStream(System.getProperty("user.dir") + "//Project_Logs//Project_" + logName + "_log.txt"));

            genralReq = new RequestSpecBuilder()
                    .setBaseUri(baseURL)
                    .addPathParam(key, keyValue)
                    .addHeaders(requestHeaders)
                    .addFilter(RequestLoggingFilter.logRequestTo(log))
                    .addFilter(ResponseLoggingFilter.logResponseTo(log))
                    .build();
            return genralReq;
        }
        return genralReq;
    }

    public RequestSpecification requestSpecificationWithMultiplePathParam(String baseURL, Map<String, String> parameters, Map<String, String> requestHeaders, String logName) throws IOException {
        if (port != 0 && !Constants.PORT.isEmpty()) {
            RestAssured.port = port;
        }
        genralReq = null;
        if (genralReq == null) {
            PrintStream log = new PrintStream(new FileOutputStream(System.getProperty("user.dir") + "//Project_Logs//Project_" + logName + "_log.txt"));

            genralReq = new RequestSpecBuilder()
                    .setBaseUri(baseURL)
                    .addPathParams(parameters)
                    .addHeaders(requestHeaders)
                    .addFilter(RequestLoggingFilter.logRequestTo(log))
                    .addFilter(ResponseLoggingFilter.logResponseTo(log))
                    .build();
            return genralReq;
        }
        return genralReq;
    }

    public RequestSpecification genralRequestSpecificationWithoutLog(String baseURL, Map<String, String> requestHeaders) {
        if (port != 0 && !Constants.PORT.isEmpty()) {
            RestAssured.port = port;
        }
        genralReq = null;
        if (genralReq == null) {
            genralReq = new RequestSpecBuilder()
                    .setBaseUri(baseURL)
                    .addHeaders(requestHeaders)
                    .build();
            return genralReq;
        }
        return genralReq;
    }

    public RequestSpecification rsWithBasicAuth(String baseURL, Map<String, String> requestHeaders,
                                                String logName, String username, String password) throws IOException {
        if (port != 0 && !Constants.PORT.isEmpty()) {
            RestAssured.port = port;
        }
        genralReq = null;
        if (genralReq == null) {
            PrintStream log = new PrintStream(new FileOutputStream(System.getProperty("user.dir") + "//Project_Logs//Project_" + logName + "_log.txt"));

            String authHeader = "Basic " + Base64.getEncoder().encodeToString((username + ":" + password).getBytes());
            genralReq = new RequestSpecBuilder()
                    .setBaseUri(baseURL)
                    .addHeaders(requestHeaders)
                    .addHeader("Authorization", authHeader)
                    .addFilter(RequestLoggingFilter.logRequestTo(log))
                    .addFilter(ResponseLoggingFilter.logResponseTo(log))
                    .build();
            return genralReq;
        }
        return genralReq;
    }


    // Bearer Token Authentication involves including a token (usually obtained during an OAuth2 or JWT authentication flow) in the Authorization header of your HTTP request
    public RequestSpecification rsWithBearerToken(String baseURL, Map<String, String> requestHeaders,
                                                  String logName, String token) throws IOException {
        if (port != 0 && !Constants.PORT.isEmpty()) {
            RestAssured.port = port;
        }
        genralReq = null;
        if (genralReq == null) {
            PrintStream log = new PrintStream(new FileOutputStream(System.getProperty("user.dir") + "//Project_Logs//Project_" + logName + "_log.txt"));

            genralReq = new RequestSpecBuilder()
                    .setBaseUri(baseURL)
                    .addHeaders(requestHeaders)
                    .addHeader("Authorization", "Bearer " + token)
                    .addFilter(RequestLoggingFilter.logRequestTo(log))
                    .addFilter(ResponseLoggingFilter.logResponseTo(log))
                    .build();
            return genralReq;
        }
        return genralReq;
    }

    //API Key Authentication involves sending an API key in the request header or as a query parameter. The server validates the API key to authenticate the request
    public RequestSpecification rsWithApiKey(String baseURL, Map<String, String> requestHeaders,
                                             String logName, String apiKey) throws IOException {
        if (port != 0 && !Constants.PORT.isEmpty()) {
            RestAssured.port = port;
        }
        genralReq = null;
        if (genralReq == null) {
            PrintStream log = new PrintStream(new FileOutputStream(System.getProperty("user.dir") + "//Project_Logs//Project_" + logName + "_log.txt"));

            genralReq = new RequestSpecBuilder()
                    .setBaseUri(baseURL)
                    .addHeaders(requestHeaders)
                    .addHeader("Api-Key", apiKey)
                    .addFilter(RequestLoggingFilter.logRequestTo(log))
                    .addFilter(ResponseLoggingFilter.logResponseTo(log))
                    .build();
            return genralReq;
        }
        return genralReq;
    }

}
