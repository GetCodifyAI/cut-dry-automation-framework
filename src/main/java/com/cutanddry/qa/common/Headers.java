package com.cutanddry.qa.common;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.HashMap;

public class Headers {

    public static HashMap<String, String> getCreateTaskHeaders(String secretKey) {
        HashMap<String, String> headers = new HashMap<>();

        // Step 1: Generate timestamp
        long timestamp = Instant.now().getEpochSecond();
        String timestampStr = String.valueOf(timestamp);

        // Step 2: Generate HMAC-SHA256 Signature
        String signature = generateHmacSHA256(timestampStr, secretKey);

        // Step 3: Set headers
        headers.put("Content-Type", "application/json");
        headers.put("Accept", "*/*");
        headers.put("HTTP_X_SIGNATURE", signature);
        headers.put("HTTP_X_TIMESTAMP", timestampStr);

        return headers;
    }

    private static String generateHmacSHA256(String message, String secret) {
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
            byte[] bytes = mac.doFinal(message.getBytes(StandardCharsets.UTF_8));

            StringBuilder hash = new StringBuilder();
            for (byte b : bytes) hash.append(String.format("%02x", b));
            return hash.toString();

        } catch (Exception e) {
            throw new RuntimeException("Error generating HMAC SHA256", e);
        }
    }

    public static HashMap<String, String> getPetHeaders(){
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("accept", ContentTypes.APPLICATION_JSON);
        headers.put("Content-Type", ContentTypes.APPLICATION_JSON);
        return headers;
    }

    public static HashMap<String, String> getAuthBookingHeaders(){
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", ContentTypes.APPLICATION_JSON);
        return headers;
    }

    public static HashMap<String, String> getBookingHeaders(String token){
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Cookie", "token=" + token );
        headers.put("accept", ContentTypes.APPLICATION_JSON);
        headers.put("Content-Type", ContentTypes.APPLICATION_JSON);
        return headers;
    }

    public static HashMap<String, String> getHeaders(String clientId, String clientSecret){
        HashMap<String, String> headers = new HashMap<String, String>();
        String base64 = getBase64(clientId+":"+clientSecret);
        headers.put("Content-type", ContentTypes.APPLICATION_FORM_URL);
        headers.put("Authorization", "Basic " + base64);
        headers.put("Accept", ContentTypes.OTHER_HEADERS);
        return headers;
    }

    public static HashMap<String, String> getHeadersToken(){
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Content-type", ContentTypes.APPLICATION_FORM_URL);
        headers.put("Authorization", "Basic ZGF2bzpYbnROUHVhUWJMaVhFYnh3NHdZcmR2RTJLdFAyREtlVw==");
        headers.put("Accept", ContentTypes.OTHER_HEADERS);
        return headers;
    }

    public static HashMap<String, String> getHeaders(String token){
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Content-type", ContentTypes.APPLICATION_JSON);
        headers.put("Authorization", "Bearer " + token + "");
        headers.put("Accept", ContentTypes.OTHER_HEADERS);
        headers.put("Accept-Encoding", ContentTypes.ACCEPT_ENCODING);
        return headers;
    }

    public static HashMap<String, String> getHeadersUAReports(String cookie){
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", ContentTypes.APPLICATION_JSON);
        headers.put("Cookie", "" + cookie + "");
        headers.put("Accept", ContentTypes.OTHER_HEADERS);
        headers.put("Accept-Encoding", ContentTypes.ACCEPT_ENCODING);
        headers.put("Cache-Control", ContentTypes.CACHE_CONTROL);
        headers.put("Accept-Language", ContentTypes.ACCEPT_LANGUAGE);
        return headers;
    }

    public static HashMap<String, String> getMAPIAuthorizationHeaders(String username, String password){
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("username", ""+ username + "");
        headers.put("password", ""+ password + "");
        return headers;
    }

    public static HashMap<String, String> getHeaders(){
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", ContentTypes.APPLICATION_JSON);
        headers.put("Cache-Control", ContentTypes.CACHE_CONTROL);
        return headers;
    }

    public static HashMap<String, String> getHeadersMenu(String MD5RequestBody, String userName, String signature, String expires){
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Content-type", ContentTypes.APPLICATION_FORM_TEXT_XML);
        headers.put("Content-MD5", MD5RequestBody);
        headers.put("Authorization", "MWS=" + userName +":"+signature +":"+ expires);
        return headers;
    }

    public static HashMap<String, String> getHeadersWithoutEncoding(String token){
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Authorization", "Bearer " + token + "");
        return headers;
    }

    public static HashMap<String, String> getMAPIHeaders(String clientId, String clientSecret){
        HashMap<String, String> headers = new HashMap<String, String>();
        String base64 = getBase64(clientId+":"+clientSecret);
        headers.put("Content-type", ContentTypes.APPLICATION_FORM_URL);
        headers.put("Authorization", "Basic " + base64);
        headers.put("Accept", ContentTypes.APPLICATION_FORM_URL);
        return headers;
    }

    public static String getBase64(String originalString) {

        return Base64.encodeBase64String((originalString).getBytes());

    }
    public static HashMap<String, String> getIDMHeaders(String token){
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", ContentTypes.APPLICATION_JSON);
        headers.put("Cookie", "" + token + "");
        headers.put("Accept", ContentTypes.OTHER_HEADERS);
        headers.put("Accept-Encoding", ContentTypes.ACCEPT_ENCODING);
        headers.put("Cache-Control", ContentTypes.CACHE_CONTROL);
        headers.put("Accept-Language", ContentTypes.ACCEPT_LANGUAGE);
        return headers;
    }
}
