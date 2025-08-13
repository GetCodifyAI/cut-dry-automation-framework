package com.cutanddry.qa.tests.data_sync;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.time.Instant;

public class ShortSignature {
    public static void main(String[] args) throws Exception {
        String secret = "3er78151gdsdak506d2a6abf7158909cf4f3"; // Replace with your secret
        long timestamp = Instant.now().getEpochSecond();
        String message = String.valueOf(timestamp);

        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec(secret.getBytes(), "HmacSHA256"));
        byte[] hash = mac.doFinal(message.getBytes());

        StringBuilder hex = new StringBuilder();
        for (byte b : hash) hex.append(String.format("%02x", b));

        System.out.println("Signature: " + hex);
        System.out.println("Timestamp: " + timestamp);
    }
}