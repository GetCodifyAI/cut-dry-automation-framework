package com.cutanddry.qa.aio;

public class AutomationTestStep {
    private final String step;
    private final String data;
    private final String expectedResult;
    
    public AutomationTestStep(String step, String data, String expectedResult) {
        this.step = step;
        this.data = data;
        this.expectedResult = expectedResult;
    }
    
    public String getStep() { return step; }
    public String getData() { return data; }
    public String getExpectedResult() { return expectedResult; }
}
