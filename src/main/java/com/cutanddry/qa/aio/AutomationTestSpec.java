package com.cutanddry.qa.aio;

import java.util.List;

public class AutomationTestSpec {
    private final String testId;
    private final String title;
    private final String description;
    private final String preconditions;
    private final List<AutomationTestStep> steps;
    private final String category;
    private final String priority;
    
    private AutomationTestSpec(Builder builder) {
        this.testId = builder.testId;
        this.title = builder.title;
        this.description = builder.description;
        this.preconditions = builder.preconditions;
        this.steps = builder.steps;
        this.category = builder.category;
        this.priority = builder.priority;
    }
    
    public String getTestId() { return testId; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getPreconditions() { return preconditions; }
    public List<AutomationTestStep> getSteps() { return steps; }
    public String getCategory() { return category; }
    public String getPriority() { return priority; }
    
    public static class Builder {
        private String testId;
        private String title;
        private String description;
        private String preconditions;
        private List<AutomationTestStep> steps;
        private String category;
        private String priority;
        
        public Builder testId(String testId) { this.testId = testId; return this; }
        public Builder title(String title) { this.title = title; return this; }
        public Builder description(String description) { this.description = description; return this; }
        public Builder preconditions(String preconditions) { this.preconditions = preconditions; return this; }
        public Builder steps(List<AutomationTestStep> steps) { this.steps = steps; return this; }
        public Builder category(String category) { this.category = category; return this; }
        public Builder priority(String priority) { this.priority = priority; return this; }
        
        public AutomationTestSpec build() {
            return new AutomationTestSpec(this);
        }
    }
}
