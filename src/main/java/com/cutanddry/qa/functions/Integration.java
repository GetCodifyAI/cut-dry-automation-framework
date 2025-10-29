package com.cutanddry.qa.functions;

import com.cutanddry.qa.pages.IntegrationPage;

public class Integration {
    static IntegrationPage integrationPage = new IntegrationPage();

    public static void navigateToIntegration() {
        integrationPage.clickOnIntegration();
    }

    public static boolean isUserNavigatedToIntegration() {
        return integrationPage.isIntegrationStatusDisplayed();
    }

    public static boolean is24HourSnapshotDisplayed() {
        return integrationPage.is24HourSnapshotDisplayed();
    }

    public static boolean isCompletedTasksDisplayed() {
        return integrationPage.isCompletedTasksDisplayed();
    }

    public static boolean isInProgressTasksDisplayed() {
        return integrationPage.isInProgressTasksDisplayed();
    }

    public static boolean isSnapshotPercentageDisplayed() {
        return integrationPage.isSnapshotPercentageDisplayed();
    }

    public static String getSnapshotPercentage() {
        return integrationPage.getSnapshotPercentage();
    }

    public static boolean isLastSuccessfulSyncDisplayed() {
        return integrationPage.isLastSuccessfulSyncDisplayed();
    }

    public static boolean isCatalogSyncDisplayed() {
        return integrationPage.isCatalogSyncDisplayed();
    }

    public static boolean isCustomerSyncDisplayed() {
        return integrationPage.isCustomerSyncDisplayed();
    }

    public static boolean isInvoiceSyncDisplayed() {
        return integrationPage.isInvoiceSyncDisplayed();
    }

    public static boolean isTermsSyncDisplayed() {
        return integrationPage.isTermsSyncDisplayed();
    }

    public static boolean isIntegrationTableDisplayed() {
        return integrationPage.isIntegrationTableDisplayed();
    }

    public static String getCatalogSyncTimestamp() {
        return integrationPage.getCatalogSyncTimestamp();
    }

    public static String getCustomerSyncTimestamp() {
        return integrationPage.getCustomerSyncTimestamp();
    }

    public static String getInvoiceSyncTimestamp() {
        return integrationPage.getInvoiceSyncTimestamp();
    }

    public static String getTermsSyncTimestamp() {
        return integrationPage.getTermsSyncTimestamp();
    }

    public static String getLastSyncFromTable(String syncName) {
        return integrationPage.getLastSyncFromTable(syncName);
    }

    public static void selectDate(String Date){
       integrationPage.selectDate(Date);
    }

    public static void selectSyncType(String Type){
        integrationPage.selectSyncType(Type);
    }

    public static void selectSyncStatus(String Status){
        integrationPage.selectSyncStatus(Status);
    }

}
