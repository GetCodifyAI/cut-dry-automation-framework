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
}
