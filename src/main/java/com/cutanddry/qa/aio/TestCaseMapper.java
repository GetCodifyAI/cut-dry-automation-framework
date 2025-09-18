package com.cutanddry.qa.aio;

import java.util.Map;
import java.util.stream.Collectors;

public class TestCaseMapper {
    
    public AutomationTestSpec mapToAutomationSpec(TestCaseDetails aioTestCase) {
        return new AutomationTestSpec.Builder()
            .testId(aioTestCase.getKey())
            .title(aioTestCase.getTitle())
            .description(aioTestCase.getDescription())
            .preconditions(aioTestCase.getPrecondition())
            .steps(mapSteps(aioTestCase.getSteps()))
            .category(determineTestCategory(aioTestCase.getFolder() != null ? aioTestCase.getFolder().getName() : ""))
            .priority(aioTestCase.getPriority() != null ? aioTestCase.getPriority().getName() : "Medium")
            .build();
    }
    
    private java.util.List<AutomationTestStep> mapSteps(java.util.List<TestStep> aioSteps) {
        if (aioSteps == null) return java.util.Collections.emptyList();
        
        return aioSteps.stream()
            .map(step -> new AutomationTestStep(
                step.getStep(),
                step.getData(),
                step.getExpectedResult()
            ))
            .collect(Collectors.toList());
    }
    
    private String determineTestCategory(String folderName) {
        if (folderName == null || folderName.isEmpty()) {
            return "general";
        }
        
        Map<String, String> categoryMapping = Map.of(
            "Order Management", "orders",
            "Customer Management", "customers", 
            "Payment Processing", "pay",
            "Catalog", "catalog",
            "Order Guide", "order_guide",
            "Multi UOM", "multi_uom",
            "Credit Request", "credit_request",
            "E2E", "e2e",
            "Boost", "boost",
            "Track", "track"
        );
        
        return categoryMapping.entrySet().stream()
            .filter(entry -> folderName.toLowerCase().contains(entry.getKey().toLowerCase()))
            .map(Map.Entry::getValue)
            .findFirst()
            .orElse("general");
    }
}
