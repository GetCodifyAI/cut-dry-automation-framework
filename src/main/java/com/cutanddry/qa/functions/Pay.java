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
    public static boolean isInvoicesBtnSelected(){
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
}
