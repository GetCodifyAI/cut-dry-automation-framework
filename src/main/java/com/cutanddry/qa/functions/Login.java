package com.cutanddry.qa.functions;

import com.cutanddry.qa.pages.LoginPage;
import com.cutanddry.qa.functions.Dashboard;

import static com.cutanddry.qa.pages.DashboardPage.selectIndependentFoodCo;

public class Login {

    static LoginPage loginPage = new LoginPage();

    public static void loginAsDistributor(String emailOrMobile, String password) {
        // Enter email/mobile and password, then submit
        loginPage.typeEmailOrMobile(emailOrMobile);
        loginPage.typePassword(password);
        loginPage.clickSubmit();

        if (!Dashboard.isCompanyIndependentFood()) {
            System.out.println("Company name is incorrect. Changing to 'Independent Foods Co'...");
            Dashboard.selectIndependentFoodCo();
        } else {
            System.out.println("Company name is already 'Independent Foods Co'. No changes needed.");
        }
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
    public static void navigateToLoginAsPortal(String name) {
        loginPage.navigateToLoginAsPortal(name);
    }
    public static void navigateToSupplierPortalVendor(String id) {
        loginPage.navigateToSupplierPortalVendor(id);
    }
    public static void setNode(String DataName){
        loginPage.setNode(DataName);
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

    public static void switchIntoNewTab(){
        loginPage.switchIntoNewTab();
    }
    public static void navigateToRestaurantPortal(String phoneNo){
        loginPage.navigateToRestaurantPortal(phoneNo);
    }

    public static void navigateToGateKeeperAdmin(){
        loginPage.navigateToGateKeeperAdmin();
    }

    public static void updateCompanyIDs(String featureName, String companyID) throws InterruptedException {
        loginPage.updateCompanyIDs(featureName, companyID);
    }

    public static void removeCompanyIDs(String featureName, String companyID){
        loginPage.removeCompanyIDs(featureName,companyID);

    }

    public static void navigateToRestaurant() {
        loginPage.navigateToRestaurant();;
    }
    public static void logInToDP(String DistributorName) throws InterruptedException {
        loginPage.typeToSearchOnDP(DistributorName);
        loginPage.clickOnDP(DistributorName);
        loginPage.clickOnLoginAsSupplierAndSwitchToNewTab();
    }
    public static void navigateToLoginAs() {
        loginPage.navigateToLoginAs();
    }
    public static boolean isUserExistInLoginAs(String name){
        return loginPage.navigateToLoginAsPortalForCustomerIndex(name);
    }
}
