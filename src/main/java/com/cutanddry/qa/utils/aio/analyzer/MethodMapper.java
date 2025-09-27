package com.cutanddry.qa.utils.aio.analyzer;

import com.cutanddry.qa.utils.aio.models.TestStep;
import java.util.ArrayList;
import java.util.List;

public class MethodMapper {
    
    public static class ExistingMethod {
        private String methodName;
        private String className;
        private boolean exists;
        private String notes;
        
        public ExistingMethod(String methodName, String className, boolean exists, String notes) {
            this.methodName = methodName;
            this.className = className;
            this.exists = exists;
            this.notes = notes;
        }
        
        public String getMethodName() { return methodName; }
        public String getClassName() { return className; }
        public boolean exists() { return exists; }
        public String getNotes() { return notes; }
    }
    
    public static List<ExistingMethod> mapTestStepsToMethods(List<TestStep> steps) {
        List<ExistingMethod> mappedMethods = new ArrayList<>();
        
        for (TestStep step : steps) {
            String stepText = step.getStep().toLowerCase();
            
            if (stepText.contains("login to the operator portal")) {
                mappedMethods.add(new ExistingMethod("loginAsDistributor", "Login", true, "Standard distributor login"));
            }
            if (stepText.contains("login to the dp portal") || stepText.contains("login-as")) {
                mappedMethods.add(new ExistingMethod("navigateToDistributorPortal", "Login", true, "DP portal login via login-as"));
            }
            
            if (stepText.contains("navigate to customers")) {
                mappedMethods.add(new ExistingMethod("navigateToCustomers", "Dashboard", true, "Navigate to customers section"));
            }
            if (stepText.contains("search for a customer")) {
                mappedMethods.add(new ExistingMethod("searchCustomerByCode", "Customer", true, "Search customer by code"));
            }
            if (stepText.contains("click on order guide")) {
                mappedMethods.add(new ExistingMethod("clickOnOrderGuide", "Customer", true, "Click on order guide for customer"));
            }
            if (stepText.contains("navigate to catalog")) {
                mappedMethods.add(new ExistingMethod("goToCatalog", "Customer", true, "Navigate to catalog page"));
            }
            
            if (stepText.contains("search for an item")) {
                mappedMethods.add(new ExistingMethod("searchItemOnCatalog", "Customer", true, "Search item in catalog"));
            }
            if (stepText.contains("add item to order guide") && stepText.contains("heart icon")) {
                mappedMethods.add(new ExistingMethod("clickOrderGuide", "Customer", true, "Add item to OG via heart icon"));
            }
            if (stepText.contains("remove item from order guide") && stepText.contains("heart icon")) {
                mappedMethods.add(new ExistingMethod("clickRemoveOrderGuide", "Customer", true, "Remove item from OG via heart icon"));
            }
            
            if (stepText.contains("navigate to dashboard")) {
                mappedMethods.add(new ExistingMethod("isUserNavigatedToDashboard", "Dashboard", true, "Verify dashboard navigation"));
            }
            if (stepText.contains("order guide changes section")) {
                mappedMethods.add(new ExistingMethod("isOrderGuideChangesSectionDisplayed", "Dashboard", false, "MISSING: Need to implement"));
            }
            if (stepText.contains("order guide changes data")) {
                mappedMethods.add(new ExistingMethod("isOrderGuideChangesDataDisplayed", "Dashboard", false, "MISSING: Need to implement"));
            }
            
            if (stepText.contains("date range") && stepText.contains("30 days")) {
                mappedMethods.add(new ExistingMethod("selectDateRange", "Dashboard", false, "MISSING: Need to implement"));
            }
            if (stepText.contains("restaurant dropdown")) {
                mappedMethods.add(new ExistingMethod("selectRestaurantFilter", "Dashboard", false, "MISSING: Need to implement"));
            }
            if (stepText.contains("salesperson dropdown")) {
                mappedMethods.add(new ExistingMethod("selectSalespersonFilter", "Dashboard", false, "MISSING: Need to implement"));
            }
            if (stepText.contains("view all")) {
                mappedMethods.add(new ExistingMethod("clickViewAllOrderGuideChanges", "Dashboard", false, "MISSING: Need to implement"));
            }
        }
        
        return mappedMethods;
    }
    
    public static void printMethodMappingReport(List<ExistingMethod> methods) {
        System.out.println("=== Method Mapping Report ===");
        System.out.println("EXISTING METHODS:");
        methods.stream()
            .filter(ExistingMethod::exists)
            .forEach(method -> System.out.println("✓ " + method.getClassName() + "." + method.getMethodName() + " - " + method.getNotes()));
        
        System.out.println("\nMISSING METHODS:");
        methods.stream()
            .filter(method -> !method.exists())
            .forEach(method -> System.out.println("✗ " + method.getClassName() + "." + method.getMethodName() + " - " + method.getNotes()));
    }
}
