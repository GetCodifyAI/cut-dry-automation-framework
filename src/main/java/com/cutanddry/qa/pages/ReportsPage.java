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
    public void clickDownloadReport(){
        distributorUI.click(btn_download_report);
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

    public boolean isFileDownloaded(String downloadPath, String fileName,String fromDate,String toDate) throws InterruptedException, ParseException {
        distributorUI.waitForCustom(5000);
        return distributorUI.isFileDownloaded(downloadPath,fileName,fromDate, toDate);
    }

    public void cleanUpDownloads(String downloadPath) {
        distributorUI.cleanUpDownloads(downloadPath);
    }
}
