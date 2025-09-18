package com.cutanddry.qa.aio;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TestStep {
    private String step;
    private String data;
    private String expectedResult;
    private String stepType;
    
    public String getStep() { return step; }
    public void setStep(String step) { this.step = step; }
    
    public String getData() { return data; }
    public void setData(String data) { this.data = data; }
    
    public String getExpectedResult() { return expectedResult; }
    public void setExpectedResult(String expectedResult) { this.expectedResult = expectedResult; }
    
    public String getStepType() { return stepType; }
    public void setStepType(String stepType) { this.stepType = stepType; }
}
