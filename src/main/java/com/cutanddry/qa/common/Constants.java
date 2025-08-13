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
    public static String CONFIG_SUPPLIER = "https://app-uat.staging.cutanddry.com/internaltools/configure-supplier";
    public static String GATE_KEEPER_ADMIN = "https://app-uat.staging.cutanddry.com/gatekeeperadmin";

    public static String PROD_LOGIN_AS = "https://internal.cutanddry.com/internaltools/loginas";

    public static String BASE_PATH = System.getProperty("product.base.path", getBaseUrl());
    public static final String PORT = System.getProperty("product.port", getPort());
    public final static String IH_API_SECRET_KEY = System.getProperty("ih_api_secret.key","3er78151gdsdak506d2a6abf7158909cf4f3");
    //  https://hooks.slack.com/services/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 //group - test-alerts
    //  https://hooks.slack.com/services/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 //group - ui-automation-tests


    private static String baseDomain() {
        if (TEST_ENV.equalsIgnoreCase("UAT")){
            MAIN_URL = "https://supplier-uat.staging.cutanddry.com/";
        } else if (TEST_ENV.equalsIgnoreCase("PROD")){
            MAIN_URL = "https://supplier.cutanddry.com/";
        }
        return MAIN_URL;
    }
    private static String secDomain() {
        if (TEST_ENV.equalsIgnoreCase("UAT")){
            SEC_URL = "https://app-"+TEST_ENV+".staging.cutanddry.com/";
        } else if (TEST_ENV.equalsIgnoreCase("PROD")){
            SEC_URL = "https://app.cutanddry.com/";
        }
        return SEC_URL;
    }

    public static String getBaseUrl() {
        String baseDomain = "https://app-" + TEST_ENV + ".staging.cutanddry.com";
        if (TEST_ENV.equalsIgnoreCase("UAT")){
            baseDomain = "https://app-"+TEST_ENV+".staging.cutanddry.com/";
        } else if (TEST_ENV.equalsIgnoreCase("PROD")){
            baseDomain = "https://app.cutanddry.com/";
        }
        return baseDomain;
    }

    private static String getPort() {
        String port;
        if (TEST_ENV.equalsIgnoreCase("UAT"))
            port = "443";
        else
            port = "0";
        return port;
    }
}
