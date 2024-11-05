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
}
