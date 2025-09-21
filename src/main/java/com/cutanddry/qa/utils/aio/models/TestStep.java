package com.cutanddry.qa.utils.aio.models;

public class TestStep {
    private String step;
    private String expectedResult;
    private String testData;
    
    public TestStep() {}
    
    public TestStep(String step, String expectedResult, String testData) {
        this.step = step;
        this.expectedResult = expectedResult;
        this.testData = testData;
    }
    
    public String getStep() {
        return step;
    }
    
    public void setStep(String step) {
        this.step = step;
    }
    
    public String getExpectedResult() {
        return expectedResult;
    }
    
    public void setExpectedResult(String expectedResult) {
        this.expectedResult = expectedResult;
    }
    
    public String getTestData() {
        return testData;
    }
    
    public void setTestData(String testData) {
        this.testData = testData;
    }
}
