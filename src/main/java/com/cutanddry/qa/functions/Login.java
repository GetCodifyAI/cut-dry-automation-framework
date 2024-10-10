package com.cutanddry.qa.functions;

import com.cutanddry.qa.pages.LoginPage;

public class Login {

    static LoginPage loginPage = new LoginPage();

    public static void loginAsDistributor(String emailOrMobile, String password){
        loginPage.typeEmailOrMobile(emailOrMobile);
        loginPage.typePassword(password);
        loginPage.clickSubmit();
    }
    public static boolean forgotPassword(){
        loginPage.clickForgotPassword();
        return loginPage.isForgotPasswordPopupDisplayed();
    }
    public static void passwordResetRequest(String emailOrMobile){
        loginPage.enterEmailOrMobile(emailOrMobile);
        loginPage.clickRequestPassword();
    }
    public static boolean validEmailOrMobileForgotPassword(){
        return loginPage.validEmailOrMobile();
    }
    public static boolean invalidEmailOrMobileForgotPassword(){
        return loginPage.invalidEmailOrMobile();
    }
    public static void clickOk(){
        loginPage.clickOk();
    }
    public static void clickTryAgain(){
        loginPage.clickTryAgain();
    }
    public static void logIntoRestaurant(String emailOrMobile, String password){
        loginPage.navigateToRestaurant();
        loginPage.typeEmailOrMobile(emailOrMobile);
        loginPage.typePassword(password);
        loginPage.clickSubmit();
    }
    public static void navigateToDistributor() {
        loginPage.navigateToDistributor();
    }
    public static void navigateToWhiteLabelPortal(String name) {
        loginPage.navigateToWhiteLabelPortal(name);
    }
    public static void navigateToSupplierPortalVendor(String id) {
        loginPage.navigateToSupplierPortalVendor(id);
    }
    public static void setNode(){
        loginPage.setNode();
    }
    public static void navigateToDistributorPortal(String name) {
        loginPage.navigateToDistributorPortal(name);
    }

    public static void navigateToInternalToolsPage(){
        loginPage.ClickOnInternalTools();
    }
    public static void navigateToConfigSupplier(){
        loginPage.navigateToConfigSupplier();
    }
    public static void navigateToDistributorPortalProd(String name) {
        loginPage.navigateToDistributorPortalProd(name);
    }
    public static void logIntoRestaurantProd(String emailOrMobile, String password){
        loginPage.navigateToRestaurantProd();
        loginPage.typeEmailOrMobile(emailOrMobile);
        loginPage.typePassword(password);
        loginPage.clickSubmit();
    }

}
