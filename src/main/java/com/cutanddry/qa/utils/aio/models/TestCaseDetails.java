package com.cutanddry.qa.utils.aio.models;

import java.util.List;

public class TestCaseDetails {
    private String key;
    private String title;
    private String description;
    private List<TestStep> steps;
    
    public TestCaseDetails() {}
    
    public TestCaseDetails(String key, String title, String description, List<TestStep> steps) {
        this.key = key;
        this.title = title;
        this.description = description;
        this.steps = steps;
    }
    
    public String getKey() {
        return key;
    }
    
    public void setKey(String key) {
        this.key = key;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public List<TestStep> getSteps() {
        return steps;
    }
    
    public void setSteps(List<TestStep> steps) {
        this.steps = steps;
    }
}
