package com.cutanddry.qa.tests.data_sync;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Base64;

public class SignatureGenerator {

    public static void main(String[] args) {
        String secretKey = "3er78151gdsdak506d2a6abf7158909cf4f3"; // Replace with your secret key
        SignatureResult result = generateSignature(secretKey);

        if (result != null) {
            System.out.println("Generated Signature (hex): " + result.signature);
            System.out.println("Timestamp (epoch): " + result.timestamp);
        }
    }

    public static SignatureResult generateSignature(String secretKey) {
        if (secretKey == null || secretKey.isEmpty()) {
            System.err.println("Secret key is missing");
            return null;
        }

        try {
            // Get current timestamp in seconds
            long timestamp = Instant.now().getEpochSecond();
            String message = String.valueOf(timestamp);

            // Create HMAC-SHA256
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
            sha256_HMAC.init(secretKeySpec);

            // Compute hash and convert to hex
            byte[] hashBytes = sha256_HMAC.doFinal(message.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return new SignatureResult(hexString.toString(), timestamp);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Helper class to hold signature and timestamp
    static class SignatureResult {
        String signature;
        long timestamp;

        SignatureResult(String signature, long timestamp) {
            this.signature = signature;
            this.timestamp = timestamp;
        }
    }
}
