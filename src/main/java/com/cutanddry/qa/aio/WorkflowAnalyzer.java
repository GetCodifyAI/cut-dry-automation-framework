package com.cutanddry.qa.aio;

import java.util.ArrayList;
import java.util.List;

public class WorkflowAnalyzer {
    
    public enum LoginPattern {
        DUAL_LOGIN,      // Both operator and DP portal actions
        DP_PORTAL_ONLY,  // Only DP portal actions
        STANDARD_LOGIN   // Standard customer login
    }
    
    public enum TestType {
        DASHBOARD_VERIFICATION,    // Tests dashboard sections/data display
        ORDER_GUIDE_EDITING,      // Tests OG item management
        CATALOG_INTERACTION,      // Tests catalog browsing/search
        CROSS_PAGE_WORKFLOW,      // Tests spanning multiple pages
        FILTERING_FUNCTIONALITY   // Tests dropdown/filter controls
    }
    
    public static class PageTransition {
        private String page;
        private String method;
        
        public PageTransition(String page, String method) {
            this.page = page;
            this.method = method;
        }
        
        public String getPage() { return page; }
        public String getMethod() { return method; }
    }
    
    public static class WorkflowAnalysis {
        private LoginPattern loginPattern;
        private TestType testType;
        private List<PageTransition> pageTransitions;
        
        public WorkflowAnalysis(LoginPattern loginPattern, TestType testType, List<PageTransition> pageTransitions) {
            this.loginPattern = loginPattern;
            this.testType = testType;
            this.pageTransitions = pageTransitions;
        }
        
        public LoginPattern getLoginPattern() { return loginPattern; }
        public TestType getTestType() { return testType; }
        public List<PageTransition> getPageTransitions() { return pageTransitions; }
    }
    
    public WorkflowAnalysis analyzeWorkflow(TestCaseDetails testCase) {
        LoginPattern loginPattern = analyzeLoginPattern(testCase.getSteps());
        TestType testType = detectTestType(testCase);
        List<PageTransition> pageTransitions = analyzePageFlow(testCase.getSteps());
        
        return new WorkflowAnalysis(loginPattern, testType, pageTransitions);
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
    
    public List<PageTransition> analyzePageFlow(List<TestStep> steps) {
        List<PageTransition> transitions = new ArrayList<>();
        
        for (TestStep step : steps) {
            String stepText = step.getStep().toLowerCase();
            
            if (stepText.contains("catalog") || stepText.contains("heart icon")) {
                transitions.add(new PageTransition("CATALOG", "Customer.goToCatalog()"));
            }
            if (stepText.contains("order guide") && !stepText.contains("changes")) {
                transitions.add(new PageTransition("ORDER_GUIDE", "Customer.clickOnOrderGuide()"));
            }
            if (stepText.contains("dashboard") || stepText.contains("order guide changes")) {
                transitions.add(new PageTransition("DASHBOARD", "Dashboard.isUserNavigatedToDashboard()"));
            }
        }
        
        return transitions;
    }
    
    public TestType detectTestType(TestCaseDetails testCase) {
        String title = testCase.getTitle().toLowerCase();
        String description = testCase.getDescription() != null ? testCase.getDescription().toLowerCase() : "";
        
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
        
        boolean hasOrderGuideAndCatalog = steps.stream()
            .anyMatch(step -> step.getStep().toLowerCase().contains("order guide")) &&
            steps.stream().anyMatch(step -> step.getStep().toLowerCase().contains("catalog"));
        
        if (hasOrderGuideAndCatalog) {
            return TestType.CROSS_PAGE_WORKFLOW;
        }
        
        boolean hasSearchFunctionality = steps.stream()
            .anyMatch(step -> step.getStep().toLowerCase().contains("search") || 
                             step.getStep().toLowerCase().contains("filter") ||
                             step.getStep().toLowerCase().contains("clear"));
        
        if (hasSearchFunctionality) {
            return TestType.FILTERING_FUNCTIONALITY;
        }
        
        return TestType.CATALOG_INTERACTION;
    }
}
