package com.cutanddry.qa.functions;

import com.cutanddry.qa.pages.ReportsPage;

import java.text.ParseException;

public class Reports {
    static ReportsPage reportsPage = new ReportsPage();

    public static boolean isUserNavigatedToReports(){
        return reportsPage.isReportingTextDisplayed();
    }
    public static void clickEmailReport(){
        reportsPage.clickEmailReport();
    }
    public static boolean isGeneratingReportPopupDisplayed(){
        return reportsPage.isGeneratingReportPopupDisplayed();
    }
    public static void clickOkReport(){
        reportsPage.clickOkReport();
    }
    public static void clickDownloadReport(){
        reportsPage.clickDownloadReport();
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


    public static boolean isFileDownloaded(String downloadPath, String fileName,String fromDate,String toDate) throws InterruptedException, ParseException {
        return reportsPage.isFileDownloaded(downloadPath,fileName,fromDate, toDate);
    }

    public static void cleanUpDownloads(String downloadPath) {
        reportsPage.cleanUpDownloads(downloadPath);
    }

}
