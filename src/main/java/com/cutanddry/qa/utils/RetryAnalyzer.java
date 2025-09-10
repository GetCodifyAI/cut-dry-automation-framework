    package com.cutanddry.qa.utils;

    import org.testng.IRetryAnalyzer;
    import org.testng.ITestResult;

    public class RetryAnalyzer implements IRetryAnalyzer {
        private int retryCount = 0;
        private static final int maxRetryCount = 1; // Number of retries

        @Override
        public boolean retry(ITestResult result) {
            String testName = result.getMethod().getMethodName();
            String className = result.getTestClass().getName();
            System.out.println("Retrying test " + className + "." + testName + " with status " + getResultStatusName(result.getStatus()) + " for the " + (retryCount + 1) + " time(s).");
            
            if (retryCount < maxRetryCount) {
                retryCount++;
                System.out.println("Retry attempt " + retryCount + " of " + maxRetryCount + " for " + testName);
                return true;
            }
            System.out.println("Max retry attempts reached for " + testName + ". Test will be marked as failed.");
            return false;
        }

        private String getResultStatusName(int status) {
            switch (status) {
                case ITestResult.SUCCESS:
                    return "SUCCESS";
                case ITestResult.FAILURE:
                    return "FAILURE";
                case ITestResult.SKIP:
                    return "SKIP";
                default:
                    return "UNKNOWN";
            }
        }
    }
