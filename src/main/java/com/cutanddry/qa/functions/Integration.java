package com.cutanddry.qa.functions;

import com.cutanddry.qa.pages.IntegrationPage;

public class Integration {
    static IntegrationPage integrationPage = new IntegrationPage();

    /**
     * Navigate to Integration page from sidebar
     */
    public static void navigateToIntegration() {
        integrationPage.clickOnIntegration();
    }

    /**
     * Check if user is navigated to Integration Status page
     * @return true if Integration Status page is displayed
     */
    public static boolean isUserNavigatedToIntegration() {
        return integrationPage.isIntegrationStatusDisplayed();
    }

    /**
     * Check if 24 Hour Snapshot section is displayed
     * @return true if 24 Hour Snapshot is visible
     */
    public static boolean is24HourSnapshotDisplayed() {
        return integrationPage.is24HourSnapshotDisplayed();
    }

    /**
     * Check if Completed tasks are displayed in 24 Hour Snapshot
     * @return true if Completed tasks text is visible
     */
    public static boolean isCompletedTasksDisplayed() {
        return integrationPage.isCompletedTasksDisplayed();
    }

    /**
     * Check if In Progress tasks are displayed in 24 Hour Snapshot
     * @return true if In Progress tasks text is visible
     */
    public static boolean isInProgressTasksDisplayed() {
        return integrationPage.isInProgressTasksDisplayed();
    }

    /**
     * Check if snapshot percentage graph is displayed
     * @return true if percentage graph is visible
     */
    public static boolean isSnapshotPercentageDisplayed() {
        return integrationPage.isSnapshotPercentageDisplayed();
    }

    /**
     * Get the snapshot percentage value
     * @return percentage value as string
     */
    public static String getSnapshotPercentage() {
        return integrationPage.getSnapshotPercentage();
    }

    /**
     * Check if Last Successful Sync section is displayed
     * @return true if Last Successful Sync is visible
     */
    public static boolean isLastSuccessfulSyncDisplayed() {
        return integrationPage.isLastSuccessfulSyncDisplayed();
    }

    /**
     * Check if Catalog sync data is displayed
     * @return true if Catalog sync is visible
     */
    public static boolean isCatalogSyncDisplayed() {
        return integrationPage.isCatalogSyncDisplayed();
    }

    /**
     * Check if Customer sync data is displayed
     * @return true if Customer sync is visible
     */
    public static boolean isCustomerSyncDisplayed() {
        return integrationPage.isCustomerSyncDisplayed();
    }

    /**
     * Check if Invoice sync data is displayed
     * @return true if Invoice sync is visible
     */
    public static boolean isInvoiceSyncDisplayed() {
        return integrationPage.isInvoiceSyncDisplayed();
    }

    /**
     * Check if Terms sync data is displayed
     * @return true if Terms sync is visible
     */
    public static boolean isTermsSyncDisplayed() {
        return integrationPage.isTermsSyncDisplayed();
    }

    /**
     * Check if integration table is displayed
     * @return true if integration table is visible
     */
    public static boolean isIntegrationTableDisplayed() {
        return integrationPage.isIntegrationTableDisplayed();
    }

    /**
     * Get Catalog sync timestamp
     * @return Catalog sync timestamp as string
     */
    public static String getCatalogSyncTimestamp() {
        return integrationPage.getCatalogSyncTimestamp();
    }

    /**
     * Get Customer sync timestamp
     * @return Customer sync timestamp as string
     */
    public static String getCustomerSyncTimestamp() {
        return integrationPage.getCustomerSyncTimestamp();
    }

    /**
     * Get Invoice sync timestamp
     * @return Invoice sync timestamp as string
     */
    public static String getInvoiceSyncTimestamp() {
        return integrationPage.getInvoiceSyncTimestamp();
    }

    /**
     * Get Terms sync timestamp
     * @return Terms sync timestamp as string
     */
    public static String getTermsSyncTimestamp() {
        return integrationPage.getTermsSyncTimestamp();
    }
}
