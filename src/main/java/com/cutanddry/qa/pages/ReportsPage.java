package com.cutanddry.qa.pages;

import org.openqa.selenium.By;

import java.text.ParseException;

public class ReportsPage extends LoginPage{
    By txt_reporting = By.xpath("//li[contains(text(),'Reporting')]");
    By btn_email_report = By.xpath("//button[@class='btn btn-outline-primary btn-block' and text()='Email Report']");
    By txt_generating_report = By.xpath("//h2[@id='swal2-title' and text()='Generating Report']");
    By btn_ok = By.xpath("//button[@class='swal2-confirm swal2-styled' and text()='OK']");
    By btn_download_report = By.xpath("//a[@class='btn btn-outline-primary btn-block' and text()='Download Report']");
    By lbl_OrderGuides = By.xpath("//*[contains(text(),'Customer Order Guides')]");
    By txt_OrderGuidesEmail = By.xpath("//*[contains(text(),'Customer Order Guides')]/following-sibling::div/input");
    By lbl_BaseReport = By.xpath("//*[contains(text(),'Customer Base Report')]");
    By txt_BaseReportEmail = By.xpath("//*[contains(text(),'Customer Base Report')]/following-sibling::div/input");

    By lbl_OrdersReport = By.xpath("//*[contains(text(),'Customer Orders Report')]");
    By txt_OrdersReportEmail = By.xpath("//*[contains(text(),'Customer Orders Report')]/following-sibling::div/input");
    By dd_selectOrdersReportFromDate = By.xpath("//*[contains(text(),'Customer Orders Report')]/following-sibling::div/label[contains(text(),'From')]/following-sibling::div//input");
    String dynamicFromXPath = "//div[contains(@class, 'react-datepicker__day--selected')]/following::div[contains(@class, 'react-datepicker__day')][DAY]";
    By dd_selectOrdersReportToDate = By.xpath("//*[contains(text(),'Customer Orders Report')]/following-sibling::div/label[contains(text(),'To')]/following-sibling::div//input");
    String dynamicToXPath = "//div[contains(@class, 'react-datepicker__day--selected')]/preceding::div[contains(@class, 'react-datepicker__day')][DAY]";

    By lbl_ItemMovementReport = By.xpath("//*[contains(text(),'Item Movement Report')]");
    By txt_ItemMovementReportEmail = By.xpath("//*[contains(text(),'Item Movement Report')]/following-sibling::div/input");
    By dd_selectItemMovementReportFromDate = By.xpath("//*[contains(text(),'Item Movement Report')]/following-sibling::div/label[contains(text(),'From')]/following-sibling::div//input");
    By dd_selectItemMovementReportToDate = By.xpath("//*[contains(text(),'Item Movement Report')]/following-sibling::div/label[contains(text(),'To')]/following-sibling::div//input");

    By lbl_PickListReport = By.xpath("//*[contains(text(),'Pick List Report')]");
    By txt_PickListReportEmail = By.xpath("//*[contains(text(),'Pick List Report')]/following-sibling::div/input");
    By dd_selectPickListReportFromDate = By.xpath("//*[contains(text(),'Pick List Report')]/following-sibling::div/label[contains(text(),'From')]/following-sibling::div//input");
    By dd_selectPickListReportToDate = By.xpath("//*[contains(text(),'Pick List Report')]/following-sibling::div/label[contains(text(),'To')]/following-sibling::div//input");
    By btn_PickListDownload = By.xpath("//*[contains(text(),'Pick List Report')]/parent::div/following-sibling::div/a[text()='Download Report']");

    By lbl_CustomerWiseItemMovementReport = By.xpath("//*[contains(text(),'Customer-wise Item Movement Report')]");
    By dd_CustomerWiseItemMovementFromDate = By.xpath("//*[contains(text(),'Customer-wise Item Movement Report')]/following-sibling::div/label[contains(text(),'From')]/following-sibling::div//input");
    By dd_CustomerWiseItemMovementToDate = By.xpath("//*[contains(text(),'Customer-wise Item Movement Report')]/following-sibling::div/label[contains(text(),'To')]/following-sibling::div//input");
    By btn_CustomerWiseItemMovementDownload = By.xpath("//*[contains(text(),'Customer-wise Item Movement Report')]/parent::div/following-sibling::div/a[text()='Download Report']");

    By lbl_CatalogExportReport = By.xpath("//*[contains(text(),'Catalog Export Report')]");
    By dd_CatalogExportReportType = By.xpath("//*[contains(text(),'Catalog Export Report')]/following-sibling::div/label[contains(text(),'Product Type')]/following-sibling::div");
    String sel_CatalogExportReportType = "//*[contains(text(),'Catalog Export Report')]/following-sibling::div/label[contains(text(),'Product Type')]/following-sibling::div//div[text()='TYPE']";
    By dd_CatalogExportReportCategory = By.xpath("//*[contains(text(),'Catalog Export Report')]/following-sibling::div/label[contains(text(),'Product Category')]/following-sibling::div");
    String sel_CatalogExportReportCategory = "//*[contains(text(),'Catalog Export Report')]/following-sibling::div/label[contains(text(),'Product Category')]/following-sibling::div//div[text()='CAT']";
    By checkbox_CatalogExportExportOnly = By.xpath("//*[contains(text(),'Catalog Export Report')]/parent::div//label[contains(text(),'Export only the items with $0 pricing')]/preceding-sibling::input[@type='checkbox']");
    By btn_CatalogExportReportDownload = By.xpath("//*[contains(text(),'Catalog Export Report')]/parent::div/following-sibling::div/a[text()='Download Report']");

    By lbl_PayCustomersReport = By.xpath("//*[contains(text(),'Pay Customers Report')]");
    By btn_PayCustomersDownload = By.xpath("//*[contains(text(),'Pay Customers Report')]/parent::div/following-sibling::div/a[text()='Download Report']");

    By lbl_AtRiskAccountsReport = By.xpath("//*[contains(text(),'At-risk Accounts Report')]");
    By btn_AtRiskAccountsDownload = By.xpath("//*[contains(text(),'At-risk Accounts Report')]/parent::div/following-sibling::div/a[text()='Download Report']");

    By lbl_PaymentTransactionReport = By.xpath("//*[contains(text(),'Payment Transaction Report')]");
    By dd_PaymentTransactionFromDate = By.xpath("//*[contains(text(),'Payment Transaction Report')]/following-sibling::div/label[contains(text(),'From')]/following-sibling::div//input");
    By dd_PaymentTransactionToDate = By.xpath("//*[contains(text(),'Payment Transaction Report')]/following-sibling::div/label[contains(text(),'To')]/following-sibling::div//input");
    By btn_PaymentTransactionDownload = By.xpath("//*[contains(text(),'Payment Transaction Report')]/parent::div/following-sibling::div/a[text()='Download Report']");

    By lbl_PaymentAuthorizationStatusReport = By.xpath("//*[contains(text(),'Payment Authorization Status Report')]");
    By btn_PaymentAuthorizationStatusDownload = By.xpath("//*[contains(text(),'Payment Authorization Status Report')]/parent::div/following-sibling::div/a[text()='Download Report']");

    By lbl_TrackReadyOrdersReport = By.xpath("//*[contains(text(),'Track-Ready Orders Report')]");
    By dd_TrackReadyOrdersDeliveryDate = By.xpath("//*[contains(text(),'Track-Ready Orders Report')]/following-sibling::div/label[contains(text(),'Delivery date')]/following-sibling::div//input");
    By dd_selectTrackReadyOrdersDeliveryDate = By.xpath("//*[contains(text(),'Track-Ready Orders Report')]/following-sibling::div/label[contains(text(),'Delivery date')]/following-sibling::div//input");
    By btn_TrackReadyOrdersDownload = By.xpath("//*[contains(text(),'Track-Ready Orders Report')]/parent::div/following-sibling::div/a[text()='Download Report']");


    public boolean isReportingTextDisplayed(){
        try {
            distributorUI.waitForVisibility(txt_reporting);
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(txt_reporting);
    }
    public void clickEmailReport(){
        distributorUI.click(btn_email_report);
    }
    public boolean isGeneratingReportPopupDisplayed(){
        try {
            distributorUI.waitForVisibility(txt_generating_report);
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(txt_generating_report);
    }
    public void clickOkReport(){
        distributorUI.click(btn_ok);
    }

    public boolean isCustomerOrderGuidesDisplayed() {
        return distributorUI.isDisplayed(lbl_OrderGuides);
    }

    public void typeOnOrderGuidesEmail(String email) throws InterruptedException {
        distributorUI.clear(txt_OrderGuidesEmail);
        distributorUI.waitForCustom(1000);
        distributorUI.sendKeys(txt_OrderGuidesEmail, email);
    }

    public boolean isCustomerBaseReportDisplayed() {
        return distributorUI.isDisplayed(lbl_BaseReport);
    }

    public void typeOnBaseReportEmail(String email) throws InterruptedException {
        distributorUI.clear(txt_BaseReportEmail);
        distributorUI.waitForCustom(1000);
        distributorUI.sendKeys(txt_BaseReportEmail, email);
    }

    public boolean isCustomerOrdersReportDisplayed() {
        return distributorUI.isDisplayed(lbl_OrdersReport);
    }

    public void typeOnOrdersReportEmail(String email) throws InterruptedException {
        distributorUI.clear(txt_OrdersReportEmail);
        distributorUI.waitForCustom(1000);
        distributorUI.sendKeys(txt_OrdersReportEmail, email);
    }

    public void selectOrdersReportFromDate(int nextDayCount) throws InterruptedException {
        distributorUI.click(dd_selectOrdersReportFromDate);
        String day = String.valueOf(nextDayCount);
        By lbl_selectOrdersReportFromDate = By.xpath(dynamicFromXPath.replace("DAY", day));
        distributorUI.waitForVisibility(lbl_selectOrdersReportFromDate);
        distributorUI.click(lbl_selectOrdersReportFromDate);
        distributorUI.waitForCustom(3000);
    }

    public void selectOrdersReportToDate(int beforeDayCount) throws InterruptedException {
        distributorUI.click(dd_selectOrdersReportToDate);
        String day = String.valueOf(beforeDayCount);
        By lbl_selectOrdersReportToDate = By.xpath(dynamicToXPath.replace("DAY", day));
        distributorUI.waitForVisibility(lbl_selectOrdersReportToDate);
        distributorUI.click(lbl_selectOrdersReportToDate);
        distributorUI.waitForCustom(3000);
    }

    public boolean isItemMovementReportDisplayed() {
        return distributorUI.isDisplayed(lbl_ItemMovementReport);
    }

    public void typeOnItemMovementReportEmail(String email) throws InterruptedException {
        distributorUI.clear(txt_ItemMovementReportEmail);
        distributorUI.waitForCustom(1000);
        distributorUI.sendKeys(txt_ItemMovementReportEmail, email);
    }

    public void selectItemMovementReportFromDate(int nextDayCount) throws InterruptedException {
        distributorUI.click(dd_selectItemMovementReportFromDate);
        String day = String.valueOf(nextDayCount);
        By lbl_selectOrdersReportFromDate = By.xpath(dynamicFromXPath.replace("DAY", day));
        distributorUI.waitForVisibility(lbl_selectOrdersReportFromDate);
        distributorUI.click(lbl_selectOrdersReportFromDate);
        distributorUI.waitForCustom(3000);
    }

    public void selectItemMovementReportToDate(int beforeDayCount) throws InterruptedException {
        distributorUI.click(dd_selectItemMovementReportToDate);
        String day = String.valueOf(beforeDayCount);
        By lbl_selectOrdersReportToDate = By.xpath(dynamicToXPath.replace("DAY", day));
        distributorUI.waitForVisibility(lbl_selectOrdersReportToDate);
        distributorUI.click(lbl_selectOrdersReportToDate);
        distributorUI.waitForCustom(3000);
    }

    public boolean isPickListReportDisplayed() {
        return distributorUI.isDisplayed(lbl_PickListReport);
    }

    public void typeOnPickListReportEmail(String email) throws InterruptedException {
        distributorUI.clear(txt_PickListReportEmail);
        distributorUI.waitForCustom(1000);
        distributorUI.sendKeys(txt_PickListReportEmail, email);
    }

    public void selectPickListReportFromDate(int nextDayCount) throws InterruptedException {
        distributorUI.click(dd_selectPickListReportFromDate);
        String day = String.valueOf(nextDayCount);
        By lbl_selectOrdersReportFromDate = By.xpath(dynamicFromXPath.replace("DAY", day));
        distributorUI.waitForVisibility(lbl_selectOrdersReportFromDate);
        distributorUI.click(lbl_selectOrdersReportFromDate);
        distributorUI.waitForCustom(3000);
    }

    public String getPickListReportFromDate() {
        return distributorUI.getText(dd_selectPickListReportFromDate,"value");
    }

    public void selectPickListReportToDate(int beforeDayCount) throws InterruptedException {
        distributorUI.click(dd_selectPickListReportToDate);
        String day = String.valueOf(beforeDayCount);
        By lbl_selectOrdersReportToDate = By.xpath(dynamicToXPath.replace("DAY", day));
        distributorUI.waitForVisibility(lbl_selectOrdersReportToDate);
        distributorUI.click(lbl_selectOrdersReportToDate);
        distributorUI.waitForCustom(3000);
    }

    public String getPickListReportToDate() {
        return distributorUI.getText(dd_selectPickListReportToDate,"value");
    }

    public void clickPickListDownloadReport(){
        distributorUI.click(btn_PickListDownload);
    }

    public boolean isCustomerWiseItemMovementReportDisplayed() {
        return distributorUI.isDisplayed(lbl_CustomerWiseItemMovementReport);
    }

    public void selectCustomerWiseItemMovementReportFromDate(int nextDayCount) throws InterruptedException {
        distributorUI.click(dd_CustomerWiseItemMovementFromDate);
        String day = String.valueOf(nextDayCount);
        By lbl_selectOrdersReportFromDate = By.xpath(dynamicFromXPath.replace("DAY", day));
        distributorUI.waitForVisibility(lbl_selectOrdersReportFromDate);
        distributorUI.click(lbl_selectOrdersReportFromDate);
        distributorUI.waitForCustom(3000);
    }

    public String getCustomerWiseItemMovementReportFromDate() {
        return distributorUI.getText(dd_CustomerWiseItemMovementFromDate,"value");
    }

    public void selectCustomerWiseItemMovementReportToDate(int beforeDayCount) throws InterruptedException {
        distributorUI.click(dd_CustomerWiseItemMovementToDate);
        String day = String.valueOf(beforeDayCount);
        By lbl_selectOrdersReportToDate = By.xpath(dynamicToXPath.replace("DAY", day));
        distributorUI.waitForVisibility(lbl_selectOrdersReportToDate);
        distributorUI.click(lbl_selectOrdersReportToDate);
        distributorUI.waitForCustom(3000);
    }

    public String getCustomerWiseItemMovementReportToDate() {
        return distributorUI.getText(dd_CustomerWiseItemMovementToDate,"value");
    }

    public void clickCustomerWiseItemMovementDownloadReport(){
        distributorUI.click(btn_CustomerWiseItemMovementDownload);
    }

    public boolean isCatalogExportReportDisplayed() {
        return distributorUI.isDisplayed(lbl_CatalogExportReport);
    }

    public void selectCatalogExportReportType(String type) throws InterruptedException {
        distributorUI.click(dd_CatalogExportReportType);
        By lbl_CatalogExportReportType = By.xpath(sel_CatalogExportReportType.replace("TYPE", type));
        distributorUI.waitForVisibility(lbl_CatalogExportReportType);
        distributorUI.click(lbl_CatalogExportReportType);
        distributorUI.waitForCustom(3000);
    }

    public void selectCatalogExportReportCategory(String category) throws InterruptedException {
        distributorUI.click(dd_CatalogExportReportCategory);
        By lbl_CatalogExportReportCategory = By.xpath(sel_CatalogExportReportCategory.replace("CAT", category));
        distributorUI.waitForVisibility(lbl_CatalogExportReportCategory);
        distributorUI.click(lbl_CatalogExportReportCategory);
        distributorUI.waitForCustom(3000);
    }

    public void setCatalogExportReportCheckboxStatus(boolean select) {
        boolean isChecked = distributorUI.isCheckboxOrRadioBtnSelected(checkbox_CatalogExportExportOnly);

        if (select) {
            if (!isChecked) {
                distributorUI.click(checkbox_CatalogExportExportOnly);
            }
        } else {
            if (isChecked) {
                distributorUI.click(checkbox_CatalogExportExportOnly);
            }
        }
    }

    public void clickCatalogExportDownloadReport(){
        distributorUI.click(btn_CatalogExportReportDownload);
    }

    public boolean isPayCustomersReportDisplayed() {
        return distributorUI.isDisplayed(lbl_PayCustomersReport);
    }

    public void clickPayCustomersDownloadReport(){
        distributorUI.click(btn_PayCustomersDownload);
    }

    public boolean isAtRiskAccountsReportDisplayed() {
        return distributorUI.isDisplayed(lbl_AtRiskAccountsReport);
    }

    public void clickAtRiskAccountsDownloadReport(){
        distributorUI.click(btn_AtRiskAccountsDownload);
    }

    public boolean isPaymentTransactionReportDisplayed() {
        return distributorUI.isDisplayed(lbl_PaymentTransactionReport);
    }

    public void selectPaymentTransactionReportFromDate(int nextDayCount) throws InterruptedException {
        distributorUI.click(dd_PaymentTransactionFromDate);
        String day = String.valueOf(nextDayCount);
        By lbl_selectOrdersReportFromDate = By.xpath(dynamicFromXPath.replace("DAY", day));
        distributorUI.waitForVisibility(lbl_selectOrdersReportFromDate);
        distributorUI.click(lbl_selectOrdersReportFromDate);
        distributorUI.waitForCustom(3000);
    }

    public void selectPaymentTransactionReportToDate(int beforeDayCount) throws InterruptedException {
        distributorUI.click(dd_PaymentTransactionToDate);
        String day = String.valueOf(beforeDayCount);
        By lbl_selectOrdersReportToDate = By.xpath(dynamicToXPath.replace("DAY", day));
        distributorUI.waitForVisibility(lbl_selectOrdersReportToDate);
        distributorUI.click(lbl_selectOrdersReportToDate);
        distributorUI.waitForCustom(3000);
    }

    public void clickPaymentTransactionDownloadReport(){
        distributorUI.click(btn_PaymentTransactionDownload);
    }

    public boolean isPaymentAuthorizationStatusReportDisplayed() {
        return distributorUI.isDisplayed(lbl_PaymentAuthorizationStatusReport);
    }

    public void clickPaymentAuthorizationStatusDownloadReport(){
        distributorUI.click(btn_PaymentAuthorizationStatusDownload);
    }

    public boolean isTrackReadyOrdersReportDisplayed() {
        return distributorUI.isDisplayed(lbl_TrackReadyOrdersReport);
    }

    public void selectTrackReadyOrdersReportDeliveryDate(int beforeDayCount) throws InterruptedException {
        distributorUI.click(dd_TrackReadyOrdersDeliveryDate);
        String day = String.valueOf(beforeDayCount);
        By lbl_selectOrdersReportToDate = By.xpath(dynamicToXPath.replace("DAY", day));
        distributorUI.waitForVisibility(lbl_selectOrdersReportToDate);
        distributorUI.click(lbl_selectOrdersReportToDate);
        distributorUI.waitForCustom(3000);
    }

    public String getTrackReadyOrdersReportDeliveryDate() {
        return distributorUI.getText(dd_selectTrackReadyOrdersDeliveryDate,"value");
    }

    public void clickTrackReadyOrdersDownloadReport(){
        distributorUI.click(btn_TrackReadyOrdersDownload);
    }

    public boolean isFileDownloaded(String downloadPath, String fileName,String fromDate,String toDate) throws InterruptedException, ParseException {
        distributorUI.waitForCustom(5000);
        return distributorUI.isFileDownloaded(downloadPath,fileName,fromDate, toDate);
    }

    public void cleanUpDownloads(String downloadPath) {
        distributorUI.cleanUpDownloads(downloadPath);
    }
}
