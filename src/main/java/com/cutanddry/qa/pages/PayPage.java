package com.cutanddry.qa.pages;

import org.openqa.selenium.By;

public class PayPage extends LoginPage{
    By txt_pay = By.xpath("//li[contains(text(),'Pay')]");
    By tb_invoices =By.xpath("//a[@role='tab' and text()='Invoices']");
    By tb_paymentsInit =By.xpath("//a[@role='tab' and text()='Payments Initiated']");
    By tb_payouts =By.xpath("//a[@role='tab' and text()='Payouts']");
    By txt_contactDetails = By.xpath("//thead/tr/th[normalize-space()='Contact Details']");
    By txt_invoiceID = By.xpath("//thead/tr/th[normalize-space()='Invoice ID']");
    By txt_paymentID = By.xpath("//thead/tr/th[normalize-space()='Payment ID']");
    By txt_payoutID = By.xpath("//thead/tr/th[normalize-space()='Payout ID']");
    By searchCustomers = By.xpath("//input[@placeholder='Search customers by name or code']");
    String SearchedCustomer = "//td[text()='CUSTOMER']";
    By btn_threeDot = By.xpath("//button//*[contains(@data-icon,'ellipsis-vertical')]");
    String statementOption = "//span[contains(text(),'STATEMENT')]";
    By txt_paymentReminder = By.xpath("//h4[contains(text(),'Past Due Invoice Details')]");
    By btn_sendEmail = By.xpath("//button[contains(text(),'Send Email')]");
    By txt_emailStatement = By.xpath("//div[contains(text(),'Send Pay Notification')]");
    By btn_send = By.xpath("//button[contains(text(),'Send')]");
    By txt_success = By.xpath("//h2[contains(text(),'Success')]");
    By btn_Ok = By.xpath("//button[contains(text(),'OK')]");
    String txt_customerInvoice = "//div[contains(text(),'CUSTOMER')]";

    By btn_invoiceCustomerClearFilter = By.xpath("(//div[text()='Customer']/following-sibling::div//*[name()='svg'])[1]");
    By lbl_emptyInvoiceMsg = By.xpath("//*[text()='Could not find invoices']");
    By dd_invoiceCustomerFilter = By.xpath("(//div[text()='Customer']/following-sibling::div//*[name()='svg'])[2]");
    String sel_invoiceCustomerFilter = "//div[contains(@class, 'themed_select__option') and text()='CUSTOMER']";
    By dd_invoiceStatusFilter = By.xpath("(//div[text()='Status']/following-sibling::div//*[name()='svg'])[1]");
    String sel_invoiceStatusFilter = "//div[contains(@class, 'themed_select__option') and text()='STATUS']";
    By txt_invoiceDateFilter = By.xpath("//div[text()='Invoice Date']/following-sibling::div//*[@placeholder='Select Date Range']");
    String dynamicToXPath = "//div[contains(@class,'react-datepicker__day--keyboard-selected')]/preceding::div[contains(@class, 'react-datepicker__day')][DAY]";
    By toDayXPath = By.xpath("//div[contains(@class,'react-datepicker__day--today')]");
    By dd_invoiceAuthStatusFilter = By.xpath("(//div[text()='Auth Status']/following-sibling::div//*[name()='svg'])[1]");
    String sel_invoiceAuthStatusFilter = "(//div[text()='Auth Status']/following-sibling::div//*[name()='svg'])[1]//div[text()='STATUS']";
    By txt_invoiceIDFilter = By.xpath("//div[text()='Invoice ID']/following-sibling::div//*[@placeholder='Search']");
    By btn_invoiceBulkAction = By.xpath("//button/*[text()='Bulk Actions']");
    By cbox_invoiceFirstRecord = By.xpath("//th[contains(text(),'Invoice ID')]/ancestor::table/tbody/tr[1]/td[1]//*[name()='svg']");
    By cbox_invoiceFirstRecordSetting = By.xpath("//th[contains(text(),'Invoice ID')]/ancestor::table/tbody/tr[ROW_COUNT]/td[10]//button");
    String cbox_invoiceRecord = "//th[contains(text(),'Invoice ID')]/ancestor::table/tbody/tr[ROW_COUNT]/td[1]//*[name()='svg']";
    String lbl_invoiceRecordID = "//th[contains(text(),'Invoice ID')]/ancestor::table/tbody/tr[ROW_COUNT]/td[2]";
    String lbl_invoiceRecordCustomerName = "//th[contains(text(),'Invoice ID')]/ancestor::table/tbody/tr[ROW_COUNT]/td[3]/div/span[1]";
    String lbl_invoiceRecordCustomerStatus = "//th[contains(text(),'Invoice ID')]/ancestor::table/tbody/tr[ROW_COUNT]/td[3]/div/span[2]/span";
    String lbl_invoiceRecordCode = "//th[contains(text(),'Invoice ID')]/ancestor::table/tbody/tr[ROW_COUNT]/td[4]";
    String lbl_invoiceRecordDate = "//th[contains(text(),'Invoice ID')]/ancestor::table/tbody/tr[ROW_COUNT]/td[5]";
    String lbl_invoiceRecordDueDate = "//th[contains(text(),'Invoice ID')]/ancestor::table/tbody/tr[ROW_COUNT]/td[6]";
    String lbl_invoiceRecordStatus = "//th[contains(text(),'Invoice ID')]/ancestor::table/tbody/tr[ROW_COUNT]/td[7]/div";
    String lbl_invoiceRecordAmount = "//th[contains(text(),'Invoice ID')]/ancestor::table/tbody/tr[ROW_COUNT]/td[8]";
    String lbl_invoiceRecordBalanceDue = "//th[contains(text(),'Invoice ID')]/ancestor::table/tbody/tr[ROW_COUNT]/td[9]";
    String btn_invoiceRecordSetting = "//th[contains(text(),'Invoice ID')]/ancestor::table/tbody/tr[ROW_COUNT]/td[10]//button";


    public boolean isPayTextDisplayed(){
        try {
            distributorUI.waitForVisibility(txt_pay);
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(txt_pay);
    }
    public void clickOnInvoices() {
        distributorUI.waitForVisibility(tb_invoices);
        distributorUI.click(tb_invoices);
    }
    public void clickOnPaymentsInit() {
        distributorUI.waitForVisibility(tb_paymentsInit);
        distributorUI.click(tb_paymentsInit);
    }
    public void clickOnPayouts() {
        distributorUI.waitForVisibility(tb_payouts);
        distributorUI.click(tb_payouts);
    }
    public boolean isCustomerBtnSelected(){
        distributorUI.waitForVisibility(txt_contactDetails);
        return distributorUI.isDisplayed(txt_contactDetails);
    }
    public boolean isInvoicesBtnSelected() throws InterruptedException {
        distributorUI.waitForVisibility(txt_invoiceID);
        By lbl_invoiceRecord = By.xpath(lbl_invoiceRecordID.replace("ROW_COUNT", "1"));
        distributorUI.waitForClickability(lbl_invoiceRecord);
        distributorUI.waitForCustom(3000);
        return distributorUI.isDisplayed(txt_invoiceID);
    }
    public boolean isPaymentsInitBtnSelected(){
        distributorUI.waitForVisibility(txt_paymentID);
        return distributorUI.isDisplayed(txt_paymentID);
    }
    public boolean isPayoutBtnSelected(){
        distributorUI.waitForVisibility(txt_payoutID);
        return distributorUI.isDisplayed(txt_payoutID);
    }
    public void clickOnSearchCustomers(){
        distributorUI.click(searchCustomers);
    }

    public void typeOnSearchCustomers(String customer) throws InterruptedException {
        distributorUI.clear(searchCustomers);
        distributorUI.waitForCustom(1000);
        distributorUI.sendKeys(searchCustomers, customer);
    }
    public boolean isSearchCustomerDisplayed(String customer){
        distributorUI.waitForVisibility(By.xpath(SearchedCustomer.replace("CUSTOMER",customer)));
        return distributorUI.isDisplayed(By.xpath(SearchedCustomer.replace("CUSTOMER",customer)));
    }
    public void clickThreeButton(){
        distributorUI.click(btn_threeDot);
    }
    public void selectStatement(String statement)throws InterruptedException{
        distributorUI.waitForVisibility(By.xpath(statementOption.replace("STATEMENT",statement)));
        distributorUI.click(By.xpath(statementOption.replace("STATEMENT",statement)));
        distributorUI.waitForCustom(2000);
    }
    public boolean isPaymentReminderDisplayed(){
        try {
            distributorUI.waitForVisibility(txt_paymentReminder);
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(txt_paymentReminder);
    }
    public void clickSendEmail(){
        distributorUI.click(btn_sendEmail);
    }
    public boolean isEmailStatementDisplayed(){
        try {
            distributorUI.waitForVisibility(txt_emailStatement);
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(txt_emailStatement);
    }
    public void clickSend(){
        distributorUI.click(btn_send);
    }
    public boolean isSuccessPopUpDisplayed(){
        try {
            distributorUI.waitForVisibility(txt_success);
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(txt_success);
    }
    public void clickOkPopUp(){
        distributorUI.click(btn_Ok);
    }
    public void clickSearchCustomer(String customer){
        distributorUI.waitForVisibility(By.xpath(SearchedCustomer.replace("CUSTOMER",customer)));
        distributorUI.click(By.xpath(SearchedCustomer.replace("CUSTOMER",customer)));
    }
    public boolean isCustomerInvoiceSectionDisplayed(String customer){
        distributorUI.waitForVisibility(By.xpath(txt_customerInvoice.replace("CUSTOMER",customer)));
        return distributorUI.isDisplayed(By.xpath(txt_customerInvoice.replace("CUSTOMER",customer)));
    }

    // Invoice
    public void clickOnInvoiceXIcon() throws InterruptedException {
        distributorUI.waitForVisibility(btn_invoiceCustomerClearFilter);
        distributorUI.click(btn_invoiceCustomerClearFilter);
        distributorUI.waitForCustom(3000);
    }

    public boolean isEmptyInvoiceMsgDisplayed(){
        distributorUI.waitForVisibility(lbl_emptyInvoiceMsg);
        return distributorUI.isDisplayed(lbl_emptyInvoiceMsg);
    }

    public void selectInvoiceCustomerViaFilter(String name) throws InterruptedException {
        distributorUI.click(dd_invoiceCustomerFilter);
        By lbl_invoiceCustomerName = By.xpath(sel_invoiceCustomerFilter.replace("CUSTOMER", name));
        distributorUI.scrollToElement(lbl_invoiceCustomerName);
        distributorUI.waitForVisibility(lbl_invoiceCustomerName);
        distributorUI.click(lbl_invoiceCustomerName);
        distributorUI.waitForCustom(3000);
    }

    public void selectInvoiceStatusViaFilter(String status) throws InterruptedException {
        distributorUI.click(dd_invoiceStatusFilter);
        By lbl_invoiceStatus = By.xpath(sel_invoiceStatusFilter.replace("STATUS", status));
        distributorUI.waitForVisibility(lbl_invoiceStatus);
        distributorUI.click(lbl_invoiceStatus);
        distributorUI.waitForCustom(3000);
    }

    public void selectInvoiceDateViaFilter(int startDayCount) throws InterruptedException {
        distributorUI.click(txt_invoiceDateFilter);
        String startDay = String.valueOf(startDayCount);
        By lbl_selectStartDate = By.xpath(dynamicToXPath.replace("DAY", startDay));
        distributorUI.waitForVisibility(lbl_selectStartDate);
        distributorUI.click(lbl_selectStartDate);
        distributorUI.waitForCustom(5000);

        /*String endDay = String.valueOf(endDayCount);
        By lbl_selectEndDate = By.xpath(dynamicToXPath.replace("DAY", endDay));*/
        distributorUI.waitForVisibility(toDayXPath);
        distributorUI.click(toDayXPath);
        distributorUI.waitForCustom(3000);
    }

    public void selectInvoiceAuthStatusViaFilter(String name) throws InterruptedException {
        distributorUI.click(dd_invoiceAuthStatusFilter);
        By lbl_invoiceAuthStatus = By.xpath(sel_invoiceAuthStatusFilter.replace("STATUS", name));
        distributorUI.waitForVisibility(lbl_invoiceAuthStatus);
        distributorUI.click(lbl_invoiceAuthStatus);
        distributorUI.waitForCustom(3000);
    }

    public void typeInvoiceIDViaFilter(String id) throws InterruptedException {
        distributorUI.clear(txt_invoiceIDFilter);
        distributorUI.waitForCustom(1000);
        distributorUI.sendKeys(txt_invoiceIDFilter, id);
    }

    public void clickOnInvoiceBulkActionButton() {
        distributorUI.waitForVisibility(btn_invoiceBulkAction);
        distributorUI.click(btn_invoiceBulkAction);
    }

    public void clickOnInvoiceFirstRecord() {
        distributorUI.waitForVisibility(cbox_invoiceFirstRecord);
        distributorUI.click(cbox_invoiceFirstRecord);
    }

    public void clickOnInvoiceFirstRecordSetting() {
        distributorUI.waitForVisibility(cbox_invoiceFirstRecordSetting);
        distributorUI.click(cbox_invoiceFirstRecordSetting);
    }

    public void clickOnInvoiceRecord(int rowNo) {
        String row_count = String.valueOf(rowNo);
        By lbl_invoiceRecord = By.xpath(cbox_invoiceRecord.replace("ROW_COUNT", row_count));
        distributorUI.waitForVisibility(lbl_invoiceRecord);
        distributorUI.click(lbl_invoiceRecord);
    }

    public String getInvoiceRecordIDValue(int rowNo) throws InterruptedException {
        String row_count = String.valueOf(rowNo);
        By lbl_invoiceID = By.xpath(lbl_invoiceRecordID.replace("ROW_COUNT", row_count));
        distributorUI.waitForElementEnabledState(lbl_invoiceID,true);
        distributorUI.waitForCustom(3000);
        return distributorUI.getText(lbl_invoiceID);
    }

    public String getInvoiceRecordCustomerNameValue(int rowNo) throws InterruptedException {
        String row_count = String.valueOf(rowNo);
        By lbl_invoiceCustomerName = By.xpath(lbl_invoiceRecordCustomerName.replace("ROW_COUNT", row_count));
        distributorUI.waitForElementEnabledState(lbl_invoiceCustomerName,true);
        distributorUI.waitForCustom(3000);
        return distributorUI.getText(lbl_invoiceCustomerName);
    }

    public String getInvoiceRecordCustomerStatusValue(int rowNo) throws InterruptedException {
        String row_count = String.valueOf(rowNo);
        By lbl_invoiceCustomerStatus = By.xpath(lbl_invoiceRecordCustomerStatus.replace("ROW_COUNT", row_count));
        distributorUI.waitForElementEnabledState(lbl_invoiceCustomerStatus,true);
        distributorUI.waitForCustom(3000);
        return distributorUI.getText(lbl_invoiceCustomerStatus);
    }

    public String getInvoiceRecordCodeValue(int rowNo) throws InterruptedException {
        String row_count = String.valueOf(rowNo);
        By lbl_invoiceCode = By.xpath(lbl_invoiceRecordCode.replace("ROW_COUNT", row_count));
        distributorUI.waitForElementEnabledState(lbl_invoiceCode,true);
        distributorUI.waitForCustom(3000);
        return distributorUI.getText(lbl_invoiceCode);
    }

    public String getInvoiceRecordDateValue(int rowNo) throws InterruptedException {
        String row_count = String.valueOf(rowNo);
        By lbl_invoiceDate = By.xpath(lbl_invoiceRecordDate.replace("ROW_COUNT", row_count));
        distributorUI.waitForElementEnabledState(lbl_invoiceDate,true);
        distributorUI.waitForCustom(3000);
        return distributorUI.getText(lbl_invoiceDate);
    }

    public String getInvoiceRecordDueDateValue(int rowNo) throws InterruptedException {
        String row_count = String.valueOf(rowNo);
        By lbl_invoiceDueDate = By.xpath(lbl_invoiceRecordDueDate.replace("ROW_COUNT", row_count));
        distributorUI.waitForElementEnabledState(lbl_invoiceDueDate,true);
        distributorUI.waitForCustom(3000);
        return distributorUI.getText(lbl_invoiceDueDate);
    }

    public String getInvoiceRecordStatusValue(int rowNo) throws InterruptedException {
        String row_count = String.valueOf(rowNo);
        By lbl_invoiceStatus = By.xpath(lbl_invoiceRecordStatus.replace("ROW_COUNT", row_count));
        distributorUI.waitForElementEnabledState(lbl_invoiceStatus,true);
        distributorUI.waitForCustom(3000);
        return distributorUI.getText(lbl_invoiceStatus);
    }

    public String getInvoiceRecordAmountValue(int rowNo) throws InterruptedException {
        String row_count = String.valueOf(rowNo);
        By lbl_invoiceAmount = By.xpath(lbl_invoiceRecordAmount.replace("ROW_COUNT", row_count));
        distributorUI.waitForElementEnabledState(lbl_invoiceAmount,true);
        distributorUI.waitForCustom(3000);
        return distributorUI.getText(lbl_invoiceAmount);
    }

    public String getInvoiceRecordBalanceDueValue(int rowNo) throws InterruptedException {
        String row_count = String.valueOf(rowNo);
        By lbl_invoiceBalanceDue = By.xpath(lbl_invoiceRecordBalanceDue.replace("ROW_COUNT", row_count));
        distributorUI.waitForElementEnabledState(lbl_invoiceBalanceDue,true);
        distributorUI.waitForCustom(3000);
        return distributorUI.getText(lbl_invoiceBalanceDue);
    }

    public void clickOnInvoiceRecordSettingIcon(int rowNo) throws InterruptedException {
        String row_count = String.valueOf(rowNo);
        By lbl_invoiceSetting = By.xpath(btn_invoiceRecordSetting.replace("ROW_COUNT", row_count));
        distributorUI.waitForVisibility(lbl_invoiceSetting);
        distributorUI.click(lbl_invoiceSetting);
        distributorUI.waitForCustom(3000);
    }

    public void waitForRecordDisplayed(int rowNo) throws InterruptedException {
        String row_count = String.valueOf(rowNo);
        By lbl_invoiceRecord = By.xpath(lbl_invoiceRecordID.replace("ROW_COUNT", row_count));
        distributorUI.waitForVisibility(lbl_invoiceRecord);
    }

    public String getLastWorkingDate() {
        return distributorUI.getLastWorkingDay();
    }


}
