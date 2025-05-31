package com.cutanddry.qa.common;

public class Constants {
    public static final String PROJECT_KEY = System.getProperty("project.key", "DOT");
    public static final String CYCLE_KEY = System.getProperty("cycle.key", "DOT-CY-");
    public static final boolean CREATE_CYCLE = Boolean.parseBoolean(System.getProperty("create.cycle", "false"));
    public static final String BROWSER_NAME = System.getProperty("test.browser", "chrome");
    public static final boolean RUN_HEADLESS = Boolean.parseBoolean(System.getProperty("run.headless", "false"));
    public static final String TEST_ENV = System.getProperty("test.env", "uat");
    public static final String TEST_STAG = System.getProperty("stag.domain", "scanned-order");
    public static final String BASE_URI = "https://supplier-uat.staging.cutanddry.com/GraphQLController";
    public static final String SLACK_WEBHOOK = System.getenv("SLACK_WEBHOOK");
    //    public static final String SLACK_WEBHOOK = System.getenv("STAGING_SLACK_WEBHOOK");
    public static final String GATE_KEEPER_ADMIN = "https://app-uat.staging.cutanddry.com/gatekeeperadmin";
    public static final String PROD_LOGIN_AS = "https://internal.cutanddry.com/internaltools/loginas";

    public static final String LOGIN_AS = resolveDomain("internaltools/loginas");
    public static final String NODE_EXPLORER = resolveDomain("admin");
    public static final String CONFIG_SUPPLIER = resolveDomain("internaltools/configure-supplier");
    public static final String MAIN_URL = resolveBaseDomain();
    public static final String SEC_URL = resolveSecDomain();


    private static String resolveBaseDomain() {
        return switch (TEST_ENV.toUpperCase()) {
            case "PROD" -> "https://supplier.cutanddry.com/";
            case "STAG" -> "https://supplier-" + TEST_STAG + ".staging.cutanddry.com/";
            default -> "https://supplier-uat.staging.cutanddry.com/";
        };
    }

    private static String resolveSecDomain() {
        return switch (TEST_ENV.toUpperCase()) {
            case "PROD" -> "https://app.cutanddry.com/";
            case "STAG" -> "https://app-" + TEST_STAG + ".staging.cutanddry.com/";
            default -> "https://app-uat.staging.cutanddry.com/";
        };
    }

    private static String resolveDomain(String path) {
        return switch (TEST_ENV.toUpperCase()) {
            case "PROD" -> "https://app.cutanddry.com/" + path;
            case "STAG" -> "https://app-" + TEST_STAG + ".staging.cutanddry.com/" + path;
            default -> "https://app-uat.staging.cutanddry.com/" + path;
        };
    }


    /*public final static String PROJECT_KEY = System.getProperty("project.key", "DOT");
    public final static String CYCLE_KEY = System.getProperty("cycle.key", "DOT-CY-");
    public static boolean CREATE_CYCLE = Boolean.parseBoolean(System.getProperty("create.cycle", "false"));
    public static String BROWSER_NAME = System.getProperty("test.browser", "chrome");
    public static boolean RUN_HEADLESS = Boolean.parseBoolean(System.getProperty("run.headless", "false"));
    public static String TEST_ENV = System.getProperty("test.env", "uat");
    public static final String TEST_STAG = System.getProperty("stag.domain", "scanned-order");

    public static String LOGIN_AS = loginAsDomain();
    public static String NODE_EXPLORER = nodeExplorerDomain();
    public static String CONFIG_SUPPLIER = configSupplierDomain();
    public static String MAIN_URL = baseDomain();
    public static String SEC_URL = secDomain();
    public static String BASE_URI = "https://supplier-uat.staging.cutanddry.com/GraphQLController";
    public static String SLACK_WEBHOOK = System.getenv("SLACK_WEBHOOK");
    //    public static String SLACK_WEBHOOK = System.getenv("STAGING_SLACK_WEBHOOK");
    public static String GATE_KEEPER_ADMIN = "https://app-uat.staging.cutanddry.com/gatekeeperadmin";
    public static String PROD_LOGIN_AS = "https://internal.cutanddry.com/internaltools/loginas";


    private static String baseDomain() {
        if (TEST_ENV.equalsIgnoreCase("UAT")) {
            MAIN_URL = "https://supplier-uat.staging.cutanddry.com/";
        } else if (TEST_ENV.equalsIgnoreCase("PROD")) {
            MAIN_URL = "https://supplier.cutanddry.com/";
        } else if (TEST_ENV.equalsIgnoreCase("STAG")) {
            MAIN_URL = "https://supplier-" + TEST_STAG + ".staging.cutanddry.com/";
        }
        return MAIN_URL;
    }

    private static String secDomain() {
        if (TEST_ENV.equalsIgnoreCase("UAT")) {
            SEC_URL = "https://app-" + TEST_ENV + ".staging.cutanddry.com/";
        } else if (TEST_ENV.equalsIgnoreCase("PROD")) {
            SEC_URL = "https://app.cutanddry.com/";
        } else if (TEST_ENV.equalsIgnoreCase("STAG")) {
            SEC_URL = "https://app-" + TEST_STAG + ".staging.cutanddry.com/";
        }
        return SEC_URL;
    }

    private static String loginAsDomain() {
        if (TEST_ENV.equalsIgnoreCase("UAT")) {
            LOGIN_AS = "https://app-uat.staging.cutanddry.com/internaltools/loginas";
        } else if (TEST_ENV.equalsIgnoreCase("PROD")) {
            LOGIN_AS = "https://app.cutanddry.com/internaltools/loginas";
        } else if (TEST_ENV.equalsIgnoreCase("STAG")) {
            LOGIN_AS = "https://app-" + TEST_STAG + ".staging.cutanddry.com/internaltools/loginas";
        }
        return LOGIN_AS;
    }

    private static String nodeExplorerDomain() {
        if (TEST_ENV.equalsIgnoreCase("UAT")) {
            NODE_EXPLORER = "https://app-uat.staging.cutanddry.com/admin";
        } else if (TEST_ENV.equalsIgnoreCase("PROD")) {
            NODE_EXPLORER = "https://app.cutanddry.com/admin";
        } else if (TEST_ENV.equalsIgnoreCase("STAG")) {
            NODE_EXPLORER = "https://app-" + TEST_STAG + ".staging.cutanddry.com/admin";
        }
        return NODE_EXPLORER;
    }

    private static String configSupplierDomain() {
        if (TEST_ENV.equalsIgnoreCase("UAT")) {
            CONFIG_SUPPLIER = "https://app-uat.staging.cutanddry.com/internaltools/configure-supplier";
        } else if (TEST_ENV.equalsIgnoreCase("PROD")) {
            CONFIG_SUPPLIER = "https://app.cutanddry.com/internaltools/configure-supplier";
        } else if (TEST_ENV.equalsIgnoreCase("STAG")) {
            CONFIG_SUPPLIER = "https://app-" + TEST_STAG + ".staging.cutanddry.com/internaltools/configure-supplier";
        }
        return CONFIG_SUPPLIER;
    }*/
}
