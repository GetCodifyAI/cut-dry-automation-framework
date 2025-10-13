package com.cutanddry.qa.common;

public class Constants {
    public final static String PROJECT_KEY = System.getProperty("project.key","DOT");
    public final static String CYCLE_KEY = System.getProperty("cycle.key","DOT-CY-");
    public static boolean CREATE_CYCLE = Boolean.parseBoolean(System.getProperty("create.cycle","false"));
    public static String BROWSER_NAME = System.getProperty("test.browser", "chrome");
    public static boolean RUN_HEADLESS = Boolean.parseBoolean(System.getProperty("run.headless", "false"));
    public static String TEST_ENV = System.getProperty("test.env", "stag");
    public static String MAIN_URL = baseDomain();
    public static String SEC_URL = secDomain();
    public static String LOGIN_AS = loginAsDomain();
    public static String NODE_EXPLORER = nodeExplorerDomain();
    public static String BASE_URI = "https://supplier-uat.staging.cutanddry.com/GraphQLController";
    public static String SLACK_WEBHOOK = System.getenv("STAGING_SLACK_WEBHOOK");
    public static String CONFIG_SUPPLIER = configSupplierDomain();
    public static String GATE_KEEPER_ADMIN = "https://app-uat.staging.cutanddry.com/gatekeeperadmin";

    public static String PROD_LOGIN_AS = "https://internal.cutanddry.com/internaltools/loginas";
    public static final String TEST_STAG ="ddo-758-v2";

    //  https://hooks.slack.com/services/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 //group - test-alerts
    //  https://hooks.slack.com/services/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 //group - ui-automation-tests


    private static String baseDomain() {
        if (TEST_ENV.equalsIgnoreCase("UAT")){
            MAIN_URL = "https://supplier-uat.staging.cutanddry.com/";
        } else if (TEST_ENV.equalsIgnoreCase("PROD")){
            MAIN_URL = "https://supplier.cutanddry.com/";
        } else if (TEST_ENV.equalsIgnoreCase("STAG_1")) {
            MAIN_URL = "https://supplier-guava.staging.cutanddry.com/";
        }
        else if (TEST_ENV.equalsIgnoreCase("STAG")) {
            MAIN_URL = "https://supplier-"+TEST_STAG+".staging.cutanddry.com/";
        }
        return MAIN_URL;
    }
    private static String secDomain() {
        if (TEST_ENV.equalsIgnoreCase("UAT")){
            SEC_URL = "https://app-"+TEST_ENV+".staging.cutanddry.com/";
        } else if (TEST_ENV.equalsIgnoreCase("PROD")){
            SEC_URL = "https://app.cutanddry.com/";
        } else if (TEST_ENV.equalsIgnoreCase("STAG_1")){
            SEC_URL = "https://app-guava.staging.cutanddry.com/";
        }
        else if (TEST_ENV.equalsIgnoreCase("STAG")) {
            SEC_URL = "https://app-"+TEST_STAG+".staging.cutanddry.com/";
        }
        return SEC_URL;
    }

    private static String loginAsDomain() {
        if (TEST_ENV.equalsIgnoreCase("UAT")) {
            LOGIN_AS = "https://app-uat.staging.cutanddry.com/internaltools/loginas";
        } else if (TEST_ENV.equalsIgnoreCase("PROD")) {
            LOGIN_AS = "https://app.cutanddry.com/internaltools/loginas";
        } else if (TEST_ENV.equalsIgnoreCase("STAG_1")) {
            LOGIN_AS = "https://app-guava.staging.cutanddry.com/internaltools/loginas";
        }
        else if (TEST_ENV.equalsIgnoreCase("STAG")) {
            LOGIN_AS = "https://app-"+TEST_STAG+".staging.cutanddry.com/internaltools/loginas";
        }
        return LOGIN_AS;
    }

    private static String nodeExplorerDomain() {
        if (TEST_ENV.equalsIgnoreCase("UAT")) {
            NODE_EXPLORER = "https://app-uat.staging.cutanddry.com/admin";
        } else if (TEST_ENV.equalsIgnoreCase("PROD")) {
            NODE_EXPLORER = "https://app.cutanddry.com/admin";
        } else if (TEST_ENV.equalsIgnoreCase("STAG_1")) {
            NODE_EXPLORER = "https://app-guava.staging.cutanddry.com/admin";
        }
        else if (TEST_ENV.equalsIgnoreCase("STAG")) {
            NODE_EXPLORER = "https://app-"+TEST_STAG+".staging.cutanddry.com/admin";
        }
        return NODE_EXPLORER;
    }

    private static String configSupplierDomain() {
        if (TEST_ENV.equalsIgnoreCase("UAT")) {
            CONFIG_SUPPLIER = "https://app-uat.staging.cutanddry.com/internaltools/configure-supplier";
        } else if (TEST_ENV.equalsIgnoreCase("PROD")) {
            CONFIG_SUPPLIER = "https://app.cutanddry.com/internaltools/configure-supplier";
        } else if (TEST_ENV.equalsIgnoreCase("STAG_1")) {
            CONFIG_SUPPLIER = "https://app-guava.staging.cutanddry.com/internaltools/configure-supplier";
        }
        else if (TEST_ENV.equalsIgnoreCase("STAG")) {
            CONFIG_SUPPLIER = "https://app-"+TEST_STAG+".staging.cutanddry.com/internaltools/configure-supplier";
        }
        return CONFIG_SUPPLIER;
    }
}