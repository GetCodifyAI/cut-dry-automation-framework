package com.cutanddry.qa.functions;

import com.cutanddry.qa.pages.CashAndCarryAppPage;

public class CashAndCarry {
    static CashAndCarryAppPage cashAndCarryAppPage = new CashAndCarryAppPage();

    public static void navigateToCashAndCarryApp(String url) {
        cashAndCarryAppPage.navigateToCashAndCarryApp(url);
    }
    public static boolean isUserNavigatedToCashAndCarry(){
        return cashAndCarryAppPage.isMarketPlaceTextDisplayed();
    }
    public static void enterFullName(String name){
        cashAndCarryAppPage.enterFullName(name);
    }
    public static void enterEmail(String email){
        cashAndCarryAppPage.enterEmail(email);
    }
    public static void enterMobilePhone(String mobile){
        cashAndCarryAppPage.enterMobilePhone(mobile);
    }
    public static void enterStreetAddress(String street){
        cashAndCarryAppPage.enterStreetAddress(street);
    }
    public static void enterCity(String city){
        cashAndCarryAppPage.enterCity(city);
    }
    public static void enterState(String state){
        cashAndCarryAppPage.enterState(state);
    }
    public static void enterZip(String zip){
        cashAndCarryAppPage.enterZip(zip);
    }
    public static void enterCardNum(String card) throws InterruptedException {
        cashAndCarryAppPage.enterCardNum(card);
    }
    public static void enterExpDate(String exp){
        cashAndCarryAppPage.enterExpDate(exp);
    }
    public static void enterCVV(String cvv){
        cashAndCarryAppPage.enterCVV(cvv);
    }
    public static void submitOrder(){
        cashAndCarryAppPage.submitOrder();
    }
    public static boolean isInvalidCardDetailsPopupDisplayed(){
        return cashAndCarryAppPage.isInvalidCardDetailsPopupDisplayed();
    }
    public static void clickOK(){
        cashAndCarryAppPage.clickOK();
    }
    public static double getDicarloPDPPriceUOM(String uom) throws InterruptedException {
        return cashAndCarryAppPage.getDicarloPDPPriceUOM(uom);
    }
    public static void clickDicarloAddToCartPlusIcon(int count, String uom) throws InterruptedException{
        for (int i=0; i<count;i++){
            cashAndCarryAppPage.clickDicarloAddToCartPlusIcon(uom);
        }
    }
    public static boolean isTransactionRejectPopupDisplayed(){
        return cashAndCarryAppPage.isTransactionRejectPopupDisplayed();
    }
    public static boolean isPaymentSucessfullyScreenDisplayed(){
        return cashAndCarryAppPage.isPaymentSucessfullyScreenDisplayed();
    }
    public static boolean isProductDetailsDisplayedDicarlo(){
        return cashAndCarryAppPage.isProductDetailsDisplayedDicarlo();
    }

}
