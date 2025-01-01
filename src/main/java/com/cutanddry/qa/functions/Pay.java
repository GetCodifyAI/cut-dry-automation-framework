package com.cutanddry.qa.functions;

import com.cutanddry.qa.pages.PayPage;

public class Pay {
    static PayPage payPage = new PayPage();

    public static boolean isUserNavigatedToPay(){
        return payPage.isPayTextDisplayed();
    }
    public static void clickOnInvoices(){
        payPage.clickOnInvoices();
    }
    public static void clickOnPaymentsInit(){
        payPage.clickOnPaymentsInit();
    }
    public static void clickOnPayouts(){
        payPage.clickOnPayouts();
    }
    public static boolean isCustomerBtnSelected(){
        return payPage.isCustomerBtnSelected();
    }
    public static boolean isInvoicesBtnSelected() throws InterruptedException {
        return payPage.isInvoicesBtnSelected();
    }
    public static boolean isPaymentsInitBtnSelected(){
        return payPage.isPaymentsInitBtnSelected();
    }
    public static boolean isPayoutBtnSelected(){
        return payPage.isPayoutBtnSelected();
    }
    public static void searchCustomer(String customer)throws InterruptedException{
        payPage.clickOnSearchCustomers();
        payPage.typeOnSearchCustomers(customer);
    }
    public static boolean isSearchCustomerDisplayed(String name){
        return payPage.isSearchCustomerDisplayed(name);
    }
    public static void sendPaymentReminder(String statement)throws InterruptedException{
        payPage.clickThreeButton();
        payPage.selectStatement(statement);
    }
    public static boolean isPaymentReminderDisplayed(){
        return payPage.isPaymentReminderDisplayed();
    }
    public static void clickSendEmail(){payPage.clickSendEmail();}
    public static void sendEmailStatement(String statement)throws InterruptedException{
        payPage.clickThreeButton();
        payPage.selectStatement(statement);
    }
    public static boolean isEmailStatementDisplayed(){
        return payPage.isEmailStatementDisplayed();
    }
    public static void clickSend(){payPage.clickSend();}
    public static boolean isSuccessPopUpDisplayed(){
        return payPage.isSuccessPopUpDisplayed();
    }
    public static void clickOkPopUp(){payPage.clickOkPopUp();}
    public static void downloadStatement(String statement)throws InterruptedException{
        payPage.clickThreeButton();
        payPage.selectStatement(statement);
    }
    public static void clickSearchCustomer(String name){
        payPage.clickSearchCustomer(name);
    }
    public static boolean isCustomerInvoiceSectionDisplayed(String name){
        return payPage.isCustomerInvoiceSectionDisplayed(name);
    }

    public static void clickOnInvoiceCustomerClearViaFilter() throws InterruptedException {
        payPage.clickOnInvoiceXIcon();
    }

    public static boolean isEmptyInvoiceMsgDisplayed(){
        return payPage.isEmptyInvoiceMsgDisplayed();
    }

    public static void selectInvoiceCustomerViaFilter(String name) throws InterruptedException {
        payPage.selectInvoiceCustomerViaFilter(name);
        payPage.waitForRecordDisplayed(1);
    }

    public static void selectInvoiceStatusViaFilter(String status) throws InterruptedException {
        payPage.selectInvoiceStatusViaFilter(status);
        payPage.waitForRecordDisplayed(1);
    }

    public static void selectInvoiceDateViaFilter(int startDayCount) throws InterruptedException {
        payPage.selectInvoiceDateViaFilter(startDayCount);
        payPage.waitForRecordDisplayed(1);
    }

    public static void selectInvoiceAuthStatusViaFilter(String name) throws InterruptedException {
        payPage.selectInvoiceAuthStatusViaFilter(name);
        payPage.waitForRecordDisplayed(1);
    }

    public static void typeInvoiceIDViaFilter(String id) throws InterruptedException {
        payPage.typeInvoiceIDViaFilter(id);
        payPage.waitForRecordDisplayed(1);
    }

    public static void clickOnInvoiceBulkActionButton() {
        payPage.clickOnInvoiceBulkActionButton();
    }

    public static void clickOnInvoiceFirstRecord() {
        payPage.clickOnInvoiceFirstRecord();
    }

    public static void clickOnInvoiceFirstRecordSetting() {
        payPage.clickOnInvoiceFirstRecordSetting();
    }

    public static void clickOnInvoiceRecord(int rowNo) {
        payPage.clickOnInvoiceRecord(rowNo);
    }

    public static String getInvoiceRecordID(int rowNo) throws InterruptedException {
        return payPage.getInvoiceRecordIDValue(rowNo);
    }

    public static String getInvoiceRecordCustomerName(int rowNo) throws InterruptedException {
        return payPage.getInvoiceRecordCustomerNameValue(rowNo);
    }

    public static String getInvoiceRecordCustomerStatus(int rowNo) throws InterruptedException {
        return payPage.getInvoiceRecordCustomerStatusValue(rowNo);
    }

    public static String getInvoiceRecordCode(int rowNo) throws InterruptedException {
        return payPage.getInvoiceRecordCodeValue(rowNo);
    }

    public static String getInvoiceRecordDate(int rowNo) throws InterruptedException {
        return payPage.getInvoiceRecordDateValue(rowNo);
    }

    public static String getInvoiceRecordDueDate(int rowNo) throws InterruptedException {
        return payPage.getInvoiceRecordDueDateValue(rowNo);
    }

    public static String getInvoiceRecordStatus(int rowNo) throws InterruptedException {
        return payPage.getInvoiceRecordStatusValue(rowNo);
    }

    public static String getInvoiceRecordAmount(int rowNo) throws InterruptedException {
        return payPage.getInvoiceRecordAmountValue(rowNo);
    }

    public static String getInvoiceRecordBalanceDue(int rowNo) throws InterruptedException {
        return payPage.getInvoiceRecordBalanceDueValue(rowNo);
    }

    public static void clickOnInvoiceRecordSetting(int rowNo) throws InterruptedException {
        payPage.clickOnInvoiceRecordSettingIcon(rowNo);
    }

    public static String getLastWorkingDate(){
        return payPage.getLastWorkingDate();
    }

}
