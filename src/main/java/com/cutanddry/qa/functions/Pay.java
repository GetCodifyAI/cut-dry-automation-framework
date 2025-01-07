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

    public static void selectTheBulkInvoiceOption(String option) {
        payPage.selectInvoiceBulkOption(option);
    }

    public static boolean isPastDueInvoicePopupDisplayed(){
        return payPage.isPastDueInvoicePopupDisplayed();
    }

    public static boolean isMarkPaidInvoicePopupDisplayed(){
       return payPage.isMarkPaidInvoicePopupDisplayed();
    }

    public static boolean isInvoiceCaptureFundDisplayed(){
        return payPage.isInvoiceCaptureFundDisplayed();
    }

    public static boolean isErrorPopUpDisplayed(){
        return payPage.isErrorPopUpDisplayed();
    }

    public static boolean isInvoiceCaptureFundPopupDisplayed(){
        return payPage.isInvoiceCaptureFundPopupDisplayed();
    }


    public static void clickOnYes() throws InterruptedException {
        payPage.clickOnMarkPaidInvoicePopupYesButton();
    }

    public static void clickOnInvoiceCaptureFundPay() throws InterruptedException {
        payPage.clickOnInvoiceCaptureFundPayButton();
    }

    public static void clickOnInvoiceSendReminders() throws InterruptedException {
        payPage.clickOnInvoiceSendRemindersButton();
    }

    public static boolean isInvoiceEmailSentPopupDisplayed() {
        return payPage.isInvoiceEmailSentPopupDisplayed();
    }

    public static void clickOnInvoiceOkButton() {
        payPage.clickOnInvoiceOkButton();
    }

    public static void clickOnInvoiceFirstRecord() {
        payPage.clickOnInvoiceFirstRecord();
    }

    public static void clickOnInvoiceFirstRecordSetting() {
        payPage.clickOnInvoiceFirstRecordSetting();
    }

    public static void clickOnInvoiceRecord(int rowCount) {
        for (int i = 1; i <= rowCount; i++) {
            payPage.clickOnInvoiceRecord(i);
        }
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

    public static boolean isInvoiceRecordCustomerStatusExist(String status) {
        return payPage.isInvoiceRecordCustomerStatusExist(status);
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
    public static void clickExportPayout(){
        payPage.clickExportPayout();
    }

    public static void selectOptionCustomerDropdown(String customer){
        payPage.click_customerDropdown();
        payPage.selectOptionCustomerDropdown(customer);
    }

    public static boolean isCustomerNameCorrect(String expectedCustomer){
        if (payPage.isNoResultTextDisplayed()){
            return true;
        }
        else return payPage.isCustomerNameCorrect(expectedCustomer);
    }

    public static void selectOptionPaymentStatusDropdown(String paymentStatus){
        payPage.click_paymentStatusDropdown();
        payPage.selectOptionPaymentStatusDropdown(paymentStatus);
    }

    public static boolean isPaymentStatusCorrect(String expectedStatus){
        if (payPage.isNoResultTextDisplayed()){
            return true;
        }
        else return payPage.isPaymentStatusCorrect(expectedStatus);
    }

    public static void selectOptionPayoutStatusDropdown(String paymentStatus){
        payPage.click_payoutStatusDropdown();
        payPage.selectOptionPayoutStatusDropdown(paymentStatus);
    }

    public static void selectTimeRange(String startDay, String startMonth, String startDate, String startYear,
                                       String endDay, String endMonth, String endDate, String endYear) {
        payPage.clickDateRangeSelector();
        payPage.selectStartDate(startDay, startMonth, startDate, startYear);
        payPage.selectEndDate(endDay, endMonth, endDate, endYear);
    }

    public static boolean isDateRangeCorrect(String startMonth, String startDate, String startYear, String endMonth, String endDate, String endYear){
        if (payPage.isTimestampInRange(startMonth, startDate, startYear, endMonth, endDate, endYear)){
            return true;
        }
        else {
            return payPage.isNoResultTextDisplayed();
        }
    }

    public static void clearDateRangeSelector(){
        payPage.clearDateRangeSelector();
    }
    public static void selectPayoutStatusDropdown(String paymentStatus){
        payPage.clickPayoutStatusDropdown();
        payPage.selectPayoutStatusDropdownOption(paymentStatus);
    }
    public static void selectDateRange(String startDay, String startMonth, String startDate, String startYear,
                                       String endDay, String endMonth, String endDate, String endYear) {
        payPage.clickDateRangeFilter();
        payPage.selectStartDate(startDay, startMonth, startDate, startYear);
        payPage.selectEndDate(endDay, endMonth, endDate, endYear);
    }
    public static boolean isPayOutDateRangeCorrect(String startMonth, String startDate, String startYear, String endMonth, String endDate, String endYear){
        if (payPage.isTimestampInDateRange(startMonth, startDate, startYear, endMonth, endDate, endYear)){
            return true;
        }
        else {
            return payPage.isNoResultTextDisplayed();
        }
    }
    public static boolean isPayoutStatusDisplayed(){
        return payPage.isPayoutStatusDisplayed();
    }
    public static String getPayOutCode() throws InterruptedException {
        return payPage.getPayOutCode();
    }
    public static void clickOnePayout(){
        payPage.clickOnePayout();
    }
    public static boolean isPayoutRecordDisplayed(String code){
        return payPage.isPayoutRecordDisplayed(code);
    }
    public static void clickThreeDotButton(){
        payPage.clickThreeDotButton();
    }
    public static void clickViewPayout(){
        payPage.clickViewPayout();
    }
    public static void clickDownloadPayout(){
        payPage.clickDownloadPayout();
    }
}
