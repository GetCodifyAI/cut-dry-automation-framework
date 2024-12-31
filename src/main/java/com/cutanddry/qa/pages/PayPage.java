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
    public boolean isInvoicesBtnSelected(){
        distributorUI.waitForVisibility(txt_invoiceID);
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

}
