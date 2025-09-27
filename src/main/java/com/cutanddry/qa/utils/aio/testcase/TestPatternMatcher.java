package com.cutanddry.qa.utils.aio.testcase;

import java.util.*;
import java.util.regex.Pattern;

public class TestPatternMatcher {
    
    private static final Map<String, String> FOLDER_TO_PACKAGE_MAP = new HashMap<>();
    private static final Map<Pattern, String> STEP_TO_FUNCTION_MAP = new HashMap<>();
    
    static {
        FOLDER_TO_PACKAGE_MAP.put("Order Management", "order_guide");
        FOLDER_TO_PACKAGE_MAP.put("Customer Management", "customers");
        FOLDER_TO_PACKAGE_MAP.put("Catalog Management", "catalog");
        FOLDER_TO_PACKAGE_MAP.put("Payment", "pay");
        FOLDER_TO_PACKAGE_MAP.put("Multi UOM", "multi_uom");
        FOLDER_TO_PACKAGE_MAP.put("Settings", "settings");
        FOLDER_TO_PACKAGE_MAP.put("Track", "track");
        FOLDER_TO_PACKAGE_MAP.put("Draft Orders", "draft");
        
        STEP_TO_FUNCTION_MAP.put(Pattern.compile(".*login.*distributor.*", Pattern.CASE_INSENSITIVE), "Login.loginAsDistributor");
        STEP_TO_FUNCTION_MAP.put(Pattern.compile(".*navigate.*dashboard.*", Pattern.CASE_INSENSITIVE), "Dashboard.isUserNavigatedToDashboard");
        STEP_TO_FUNCTION_MAP.put(Pattern.compile(".*navigate.*customer.*", Pattern.CASE_INSENSITIVE), "Dashboard.navigateToCustomers");
        STEP_TO_FUNCTION_MAP.put(Pattern.compile(".*search.*customer.*", Pattern.CASE_INSENSITIVE), "Customer.searchCustomerByCode");
        STEP_TO_FUNCTION_MAP.put(Pattern.compile(".*order.*guide.*", Pattern.CASE_INSENSITIVE), "Customer.clickOnOrderGuide");
        STEP_TO_FUNCTION_MAP.put(Pattern.compile(".*add.*item.*", Pattern.CASE_INSENSITIVE), "Customer.increaseFirstRowQtyByOne");
        STEP_TO_FUNCTION_MAP.put(Pattern.compile(".*checkout.*", Pattern.CASE_INSENSITIVE), "Customer.checkoutItems");
        STEP_TO_FUNCTION_MAP.put(Pattern.compile(".*submit.*order.*", Pattern.CASE_INSENSITIVE), "Customer.submitOrder");
        STEP_TO_FUNCTION_MAP.put(Pattern.compile(".*navigate.*catalog.*", Pattern.CASE_INSENSITIVE), "Dashboard.navigateToCatalog");
        STEP_TO_FUNCTION_MAP.put(Pattern.compile(".*verify.*success.*", Pattern.CASE_INSENSITIVE), "Customer.isThankingForOrderPopupDisplayed");
    }
    
    public static String determineTestPackage(String folder, String title) {
        if (folder != null && FOLDER_TO_PACKAGE_MAP.containsKey(folder)) {
            return FOLDER_TO_PACKAGE_MAP.get(folder);
        }
        
        if (title != null) {
            String lowerTitle = title.toLowerCase();
            if (lowerTitle.contains("order")) return "order_guide";
            if (lowerTitle.contains("customer")) return "customers";
            if (lowerTitle.contains("catalog")) return "catalog";
            if (lowerTitle.contains("payment") || lowerTitle.contains("pay")) return "pay";
            if (lowerTitle.contains("multi") && lowerTitle.contains("uom")) return "multi_uom";
            if (lowerTitle.contains("setting")) return "settings";
            if (lowerTitle.contains("track")) return "track";
            if (lowerTitle.contains("draft")) return "draft";
        }
        
        return "order_guide";
    }
    
    public static List<String> identifyRequiredFunctions(List<AioTestCase.TestStep> steps) {
        List<String> functions = new ArrayList<>();
        Set<String> uniqueFunctions = new HashSet<>();
        
        for (AioTestCase.TestStep step : steps) {
            String stepText = step.getStep();
            for (Map.Entry<Pattern, String> entry : STEP_TO_FUNCTION_MAP.entrySet()) {
                if (entry.getKey().matcher(stepText).matches()) {
                    if (uniqueFunctions.add(entry.getValue())) {
                        functions.add(entry.getValue());
                    }
                    break;
                }
            }
        }
        
        return functions;
    }
    
    public static String generateTestClassName(String testCaseId, String title) {
        String cleanTitle = title.replaceAll("[^a-zA-Z0-9\\s]", "")
                                .replaceAll("\\s+", " ")
                                .trim();
        
        String[] words = cleanTitle.split("\\s+");
        StringBuilder className = new StringBuilder();
        
        for (String word : words) {
            if (!word.isEmpty()) {
                className.append(Character.toUpperCase(word.charAt(0)))
                         .append(word.substring(1).toLowerCase());
            }
        }
        
        return className.toString() + "Test";
    }
    
    public static String generateTestMethodName(String title) {
        String cleanTitle = title.replaceAll("[^a-zA-Z0-9\\s]", "")
                                .replaceAll("\\s+", " ")
                                .trim();
        
        String[] words = cleanTitle.split("\\s+");
        StringBuilder methodName = new StringBuilder();
        
        for (String word : words) {
            if (!word.isEmpty()) {
                methodName.append(Character.toUpperCase(word.charAt(0)))
                         .append(word.substring(1).toLowerCase());
            }
        }
        
        return methodName.toString();
    }
}
