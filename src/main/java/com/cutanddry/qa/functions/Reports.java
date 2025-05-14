package com.cutanddry.qa.functions;

import com.cutanddry.qa.pages.ReportsPage;

import java.text.ParseException;

public class Reports {
    static ReportsPage reportsPage = new ReportsPage();

    public static boolean isUserNavigatedToReports(){
        return reportsPage.isReportingTextDisplayed();
    }
    public static void clickEmailReport() throws InterruptedException {
        reportsPage.clickEmailReport();
    }
    public static void clickItemMovementReportEmailReport() throws InterruptedException {
        reportsPage.clickItemMovementReportEmailReport();
    }
    public static boolean isGeneratingReportPopupDisplayed(){
        return reportsPage.isGeneratingReportPopupDisplayed();
    }
    public static void clickOkReport(){
        reportsPage.clickOkReport();
    }

    public static boolean isCustomerOrderGuidesDisplayed(){
        return reportsPage.isCustomerOrderGuidesDisplayed();
    }

    public static void typeOnOrderGuidesEmail(String email) throws InterruptedException {
        reportsPage.typeOnOrderGuidesEmail(email);
    }

    public static boolean isCustomerBaseReportDisplayed(){
        return reportsPage.isCustomerBaseReportDisplayed();
    }

    public static void typeOnBaseReportEmail(String email) throws InterruptedException {
        reportsPage.typeOnBaseReportEmail(email);
    }

    public static boolean isCustomerOrdersReportDisplayed(){
        return reportsPage.isCustomerOrdersReportDisplayed();
    }

    public static void selectOrdersReportFromDate(int nextDayCount) throws InterruptedException {
        reportsPage.selectOrdersReportFromDate(nextDayCount);
    }

    public static void selectOrdersReportToDate(int beforeDayCount) throws InterruptedException {
        reportsPage.selectOrdersReportToDate(beforeDayCount);
    }

    public static void typeOnOrdersReportEmail(String email) throws InterruptedException {
        reportsPage.typeOnOrdersReportEmail(email);
    }

    public static boolean isItemMovementReportDisplayed(){
        return reportsPage.isItemMovementReportDisplayed();
    }

    public static void typeOnItemMovementReportEmail(String email) throws InterruptedException {
        reportsPage.typeOnItemMovementReportEmail(email);
    }

    public static void selectItemMovementReportFromDate(int nextDayCount) throws InterruptedException {
        reportsPage.selectItemMovementReportFromDate(nextDayCount);
    }

    public static void selectItemMovementReportToDate(int beforeDayCount) throws InterruptedException {
        reportsPage.selectItemMovementReportToDate(beforeDayCount);
    }

    public static boolean isPickListReportDisplayed(){
        return reportsPage.isPickListReportDisplayed();
    }

    public static void typeOnPickListReportEmail(String email) throws InterruptedException {
        reportsPage.typeOnPickListReportEmail(email);
    }

    public static void selectPickListReportFromDate(int nextDayCount) throws InterruptedException {
        reportsPage.selectPickListReportFromDate(nextDayCount);
    }

    public static String getPickListReportFromDate() {
        return reportsPage.getPickListReportFromDate();
    }

    public static void selectPickListReportToDate(int beforeDayCount) throws InterruptedException {
        reportsPage.selectPickListReportToDate(beforeDayCount);
    }

    public static String getPickListReportToDate() {
        return reportsPage.getPickListReportToDate();
    }

    public static void clickPickListDownloadReport(){
        reportsPage.clickPickListDownloadReport();
    }

    public static boolean isCustomerWiseItemMovementReportDisplayed(){
        return reportsPage.isCustomerWiseItemMovementReportDisplayed();
    }

    public static void selectCustomerWiseItemMovementReportFromDate(int nextDayCount) throws InterruptedException {
        reportsPage.selectCustomerWiseItemMovementReportFromDate(nextDayCount);
    }

    public static String getCustomerWiseItemMovementReportFromDate() {
        return reportsPage.getCustomerWiseItemMovementReportFromDate();
    }

    public static void selectCustomerWiseItemMovementReportToDate(int beforeDayCount) throws InterruptedException {
        reportsPage.selectCustomerWiseItemMovementReportToDate(beforeDayCount);
    }

    public static String getCustomerWiseItemMovementReportToDate() {
        return reportsPage.getCustomerWiseItemMovementReportToDate();
    }

    public static void clickCustomerWiseItemMovementDownloadReport(){
        reportsPage.clickCustomerWiseItemMovementDownloadReport();
    }

    public static boolean isCatalogExportReportDisplayed(){
        return reportsPage.isCatalogExportReportDisplayed();
    }

    public static void selectCatalogExportReportType(String type) throws InterruptedException {
        reportsPage.selectCatalogExportReportType(type);
    }

    public static void selectCatalogExportReportCategory(String category) throws InterruptedException {
        reportsPage.selectCatalogExportReportCategory(category);
    }

    public static void setCatalogExportReportCheckboxStatus(boolean select) {
        reportsPage.setCatalogExportReportCheckboxStatus(select);
    }

    public static void clickCatalogExportDownloadReport(){
        reportsPage.clickCatalogExportDownloadReport();
    }

    public static boolean isPayCustomersReportDisplayed(){
        return reportsPage.isPayCustomersReportDisplayed();
    }

    public static void clickPayCustomersDownloadReport(){
        reportsPage.clickPayCustomersDownloadReport();
    }

    public static boolean isAtRiskAccountsReportDisplayed(){
        return reportsPage.isAtRiskAccountsReportDisplayed();
    }

    public static void clickAtRiskAccountsDownloadReport(){
        reportsPage.clickAtRiskAccountsDownloadReport();
    }

    public static boolean isPaymentTransactionReportDisplayed(){
        return reportsPage.isPaymentTransactionReportDisplayed();
    }

    public static void selectPaymentTransactionReportFromDate(int nextDayCount) throws InterruptedException {
        reportsPage.selectPaymentTransactionReportFromDate(nextDayCount);
    }

    public static void selectPaymentTransactionReportToDate(int beforeDayCount) throws InterruptedException {
        reportsPage.selectPaymentTransactionReportToDate(beforeDayCount);
    }

    public static void clickPaymentTransactionDownloadReport(){
        reportsPage.clickPaymentTransactionDownloadReport();
    }

    public static boolean isPaymentAuthorizationStatusReportDisplayed(){
        return reportsPage.isPaymentAuthorizationStatusReportDisplayed();
    }

    public static void clickPaymentAuthorizationStatusDownloadReport(){
        reportsPage.clickPaymentAuthorizationStatusDownloadReport();
    }

    public static boolean isTrackReadyOrdersReportDisplayed(){
        return reportsPage.isTrackReadyOrdersReportDisplayed();
    }

    public static void selectTrackReadyOrdersReportDeliveryDate(int beforeDayCount) throws InterruptedException {
        reportsPage.selectTrackReadyOrdersReportDeliveryDate(beforeDayCount);
    }

    public static String getTrackReadyOrdersReportDeliveryDate() {
        return reportsPage.getTrackReadyOrdersReportDeliveryDate();
    }

    public static void clickTrackReadyOrdersDownloadReport(){
        reportsPage.clickTrackReadyOrdersDownloadReport();
    }



    public static boolean isFileDownloaded(String downloadPath, String fileName,String fromDate,String toDate) throws InterruptedException, ParseException {
        return reportsPage.isFileDownloaded(downloadPath,fileName,fromDate, toDate);
    }

    public static void cleanUpDownloads(String downloadPath) {
        reportsPage.cleanUpDownloads(downloadPath);
    }

}
