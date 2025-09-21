package com.cutanddry.qa.utils.aio.analyzer;

import com.cutanddry.qa.utils.aio.models.TestCaseDetails;
import com.cutanddry.qa.utils.aio.models.TestStep;
import java.util.List;

public class WorkflowAnalyzer {
    
    public enum LoginPattern {
        DUAL_LOGIN,
        DP_PORTAL_ONLY,
        STANDARD_LOGIN
    }
    
    public enum TestType {
        DASHBOARD_VERIFICATION,
        ORDER_GUIDE_EDITING,
        CATALOG_INTERACTION,
        CROSS_PAGE_WORKFLOW,
        FILTERING_FUNCTIONALITY
    }
    
    public LoginPattern analyzeLoginPattern(List<TestStep> steps) {
        boolean hasOperatorLogin = steps.stream()
            .anyMatch(step -> step.getStep().toLowerCase().contains("operator portal") || 
                             step.getStep().toLowerCase().contains("loginas"));
        
        boolean hasDPLogin = steps.stream()
            .anyMatch(step -> step.getStep().toLowerCase().contains("dp portal") || 
                             step.getStep().toLowerCase().contains("distributor portal"));
        
        if (hasOperatorLogin && hasDPLogin) {
            return LoginPattern.DUAL_LOGIN;
        } else if (hasDPLogin) {
            return LoginPattern.DP_PORTAL_ONLY;
        } else {
            return LoginPattern.STANDARD_LOGIN;
        }
    }
    
    public TestType detectTestType(TestCaseDetails testCase) {
        String title = testCase.getTitle().toLowerCase();
        String description = testCase.getDescription().toLowerCase();
        
        if (title.contains("dashboard") || title.contains("section functionality")) {
            return TestType.DASHBOARD_VERIFICATION;
        }
        if (title.contains("order guide changes") && title.contains("section")) {
            return TestType.DASHBOARD_VERIFICATION;
        }
        if (title.contains("hiding") || title.contains("edit") && title.contains("order guide")) {
            return TestType.ORDER_GUIDE_EDITING;
        }
        
        List<TestStep> steps = testCase.getSteps();
        boolean hasMultipleLogins = steps.stream()
            .filter(step -> step.getStep().toLowerCase().contains("login") || 
                           step.getStep().toLowerCase().contains("log into"))
            .count() > 1;
        
        if (hasMultipleLogins) {
            return TestType.CROSS_PAGE_WORKFLOW;
        }
        
        return TestType.CATALOG_INTERACTION;
    }
    
    public boolean isOperatorPortalStep(TestStep step) {
        String stepText = step.getStep().toLowerCase();
        return stepText.contains("place order") || 
               stepText.contains("add") || 
               stepText.contains("remove") ||
               stepText.contains("heart icon") ||
               (stepText.contains("catalog") && !stepText.contains("verify")) ||
               stepText.contains("operator portal");
    }
    
    public boolean isDPPortalStep(TestStep step) {
        String stepText = step.getStep().toLowerCase();
        return stepText.contains("dashboard") || 
               stepText.contains("order guide changes") ||
               stepText.contains("verify") ||
               stepText.contains("check if") ||
               stepText.contains("change the") ||
               stepText.contains("dropdown") ||
               stepText.contains("dp portal") ||
               stepText.contains("login-as");
    }
}
