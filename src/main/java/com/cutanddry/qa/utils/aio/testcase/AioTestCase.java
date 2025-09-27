package com.cutanddry.qa.utils.aio.testcase;

import lombok.Data;
import java.util.List;

@Data
public class AioTestCase {
    private String key;
    private String title;
    private String description;
    private String precondition;
    private List<TestStep> steps;
    private String folder;
    private String priority;
    private String status;
    private String type;

    @Data
    public static class TestStep {
        private String step;
        private String data;
        private String expectedResult;
        private int stepNumber;
    }
}
