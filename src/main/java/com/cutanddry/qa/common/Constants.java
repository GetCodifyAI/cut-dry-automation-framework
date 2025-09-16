package com.cutanddry.qa.common;

public class Constants {
    public final static String PROJECT_KEY = System.getProperty("project.key","DOT");
    public final static String CYCLE_KEY = System.getProperty("cycle.key","DOT-CY-");
    public static boolean CREATE_CYCLE = Boolean.parseBoolean(System.getProperty("create.cycle","false"));
    public static String BROWSER_NAME = System.getProperty("test.browser", "chrome");
    public static boolean RUN_HEADLESS = Boolean.parseBoolean(System.getProperty("run.headless", "false"));
    public static String TEST_ENV = System.getProperty("test.env", "stag");
    public static String BASE_URI = "https://supplier-uat.staging.cutanddry.com/GraphQLController";
    
    public static String getMainUrl() {
        return baseDomain();
    }
    
    public static String getSecUrl() {
        return secDomain();
    }
    
    public static String getLoginAs() {
        return loginAsDomain();
    }
    
    public static String getNodeExplorer() {
        return nodeExplorerDomain();
    }
    
    public static String getConfigSupplier() {
        return configSupplierDomain();
    }
    public static String GATE_KEEPER_ADMIN = "https://app-uat.staging.cutanddry.com/gatekeeperadmin";
    public static String PROD_LOGIN_AS = "https://internal.cutanddry.com/internaltools/loginas";

    public static String SLACK_TOKEN = System.getProperty("slack.token", "***");
    public static String SLACK_CHANNEL = System.getProperty("slack.channel", "#ui-automation-tests");
    public static String SLACK_WEBHOOK = System.getenv("STAGING_SLACK_WEBHOOK");
    public static final String TEST_STAG = System.getProperty("TEST_STAG", "unit-price");


    //slackToken=***
    //slackChannel=#ui-automation-tests


    private static String baseDomain() {
        String mainUrl;
        if (TEST_ENV.equalsIgnoreCase("UAT")){
            mainUrl = "https://supplier-uat.staging.cutanddry.com/";
        } else if (TEST_ENV.equalsIgnoreCase("PROD")){
            mainUrl = "https://supplier.cutanddry.com/";
        } else if (TEST_ENV.equalsIgnoreCase("STAG")) {
            mainUrl = "https://supplier-"+TEST_STAG+".staging.cutanddry.com/";
        } else {
            mainUrl = "https://supplier-uat.staging.cutanddry.com/";
        }
        return mainUrl;
    }
    private static String secDomain() {
        String secUrl;
        if (TEST_ENV.equalsIgnoreCase("UAT")){
            secUrl = "https://app-"+TEST_ENV+".staging.cutanddry.com/";
        } else if (TEST_ENV.equalsIgnoreCase("PROD")){
            secUrl = "https://app.cutanddry.com/";
        } else if (TEST_ENV.equalsIgnoreCase("STAG")) {
            secUrl = "https://app-"+TEST_STAG+".staging.cutanddry.com/";
        } else {
            secUrl = "https://app-uat.staging.cutanddry.com/";
        }
        return secUrl;
    }

    private static String loginAsDomain() {
        String loginAs;
        if (TEST_ENV.equalsIgnoreCase("UAT")) {
            loginAs = "https://app-uat.staging.cutanddry.com/internaltools/loginas";
        } else if (TEST_ENV.equalsIgnoreCase("PROD")) {
            loginAs = "https://app.cutanddry.com/internaltools/loginas";
        } else if (TEST_ENV.equalsIgnoreCase("STAG")) {
            loginAs = "https://app-"+TEST_STAG+".staging.cutanddry.com/internaltools/loginas";
        } else {
            loginAs = "https://app-uat.staging.cutanddry.com/internaltools/loginas";
        }
        return loginAs;
    }

    private static String nodeExplorerDomain() {
        String nodeExplorer;
        if (TEST_ENV.equalsIgnoreCase("UAT")) {
            nodeExplorer = "https://app-uat.staging.cutanddry.com/admin";
        } else if (TEST_ENV.equalsIgnoreCase("PROD")) {
            nodeExplorer = "https://app.cutanddry.com/admin";
        } else if (TEST_ENV.equalsIgnoreCase("STAG")) {
            nodeExplorer = "https://app-"+TEST_STAG+".staging.cutanddry.com/admin";
        } else {
            nodeExplorer = "https://app-uat.staging.cutanddry.com/admin";
        }
        return nodeExplorer;
    }

    private static String configSupplierDomain() {
        String configSupplier;
        if (TEST_ENV.equalsIgnoreCase("UAT")) {
            configSupplier = "https://app-uat.staging.cutanddry.com/internaltools/configure-supplier";
        } else if (TEST_ENV.equalsIgnoreCase("PROD")) {
            configSupplier = "https://app.cutanddry.com/internaltools/configure-supplier";
        } else if (TEST_ENV.equalsIgnoreCase("STAG")) {
            configSupplier = "https://app-"+TEST_STAG+".staging.cutanddry.com/internaltools/configure-supplier";
        } else {
            configSupplier = "https://app-uat.staging.cutanddry.com/internaltools/configure-supplier";
        }
        return configSupplier;
    }
}
