package com.cutanddry.qa.common;

public class Constants {
    public final static String PROJECT_KEY = System.getProperty("project.key","DOT");
    public final static String CYCLE_KEY = System.getProperty("cycle.key","DOT-CY-");
    public static boolean CREATE_CYCLE = Boolean.parseBoolean(System.getProperty("create.cycle","false"));
    public static String BROWSER_NAME = System.getProperty("test.browser", "chrome");
    public static boolean RUN_HEADLESS = Boolean.parseBoolean(System.getProperty("run.headless", "false"));
    public static String TEST_ENV = System.getProperty("test.env", "uat");
    public static String MAIN_URL = baseDomain();
    public static String SEC_URL = secDomain();
    public static String LOGIN_AS = "https://app-uat.staging.cutanddry.com/internaltools/loginas";
    public static String NODE_EXPLORER = "https://app-uat.staging.cutanddry.com/admin";
    public static String BASE_URI = "https://supplier-uat.staging.cutanddry.com/GraphQLController";
    public static String SLACK_WEBHOOK = System.getenv("SLACK_WEBHOOK");
    //  https://hooks.slack.com/services/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 //group - test-alerts
    //  https://hooks.slack.com/services/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 //group - ui-automation-tests


    private static String baseDomain() {
        if (TEST_ENV.equalsIgnoreCase("UAT")){
            MAIN_URL = "https://supplier-"+TEST_ENV+".staging.cutanddry.com/";
        } else if (TEST_ENV.equalsIgnoreCase("")){
            //implement for other env here
        }
        return MAIN_URL;
    }
    private static String secDomain() {
        if (TEST_ENV.equalsIgnoreCase("UAT")){
            SEC_URL = "https://app-"+TEST_ENV+".staging.cutanddry.com/";
        } else if (TEST_ENV.equalsIgnoreCase("")){
            //implement for other env here
        }
        return SEC_URL;
    }

}
