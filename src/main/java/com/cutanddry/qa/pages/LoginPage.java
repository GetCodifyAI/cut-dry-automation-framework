package com.cutanddry.qa.pages;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.common.Constants;
import com.cutanddry.qa.functions.Orders;
import org.openqa.selenium.By;

public class LoginPage extends TestBase {
    By txt_emailOrMobile = By.xpath("//input[@placeholder='Email or mobile']");
    By txt_password = By.xpath("//input[@placeholder='Password']");
    By btn_signIn = By.xpath("//button[@type='submit']");
    By btn_forgotPassword = By.xpath("//div[text()='Forgot Password?']");
    By txt_forgotPassword = By.xpath("//div[text()='Forgot Password']");
    By btn_requestPassword = By.xpath("//button[text()='Request Password']");
    By txt_enterEmailOrMobile = By.xpath("//input[@placeholder='Enter email or phone number']");
    By txt_validEmailOrMobile = By.xpath("//div[text()='Password Reset Request Sent!']");
    By txt_invalidEmailOrMobile = By.xpath("//div[text()='Error']");
    By btn_ok = By.xpath("//button[text()='Ok']");
    By btn_tryAgain = By.xpath("//button[text()='Try Again']");
    By lbl_loginAs = By.xpath("//div[text()='Select...']/following::input[@type='text']");
    String txt_whitelblCustomer = "//div[contains(@class, 'css-1n7v3ny-option') and contains(text(), 'NAME')]";
    By btn_loginAsWLApp = By.xpath("//a[contains(text(), 'Login As')]");
    By lbl_verifiedVendor = By.xpath("//a[text()='verifiedvendor']");
    String txt_verifiedVendor = "//a[contains(text(), 'ID')]";
    By lbl_suuplierPortalVendorData = By.xpath("//a[contains(text(), 'Link') and ancestor::th[contains(., 'SupplierPortalVendorData')]]");
    By txt_key = By.xpath("//input[@name='data_key']");
    By txt_value = By.xpath("//input[@name='data_val']");
    By btn_setData = By.xpath("//button[contains(text(), 'Set data')]");
    By lbl_loginAsDist = By.xpath("//h5[text()='Distributor Portal Quick Links']/following-sibling::div//div[text()='Select User...']/following::input[@type='text'][1]");
    String txt_distributor = "//div[contains(@class, 'themed_select__option') and contains(text(), 'NAME')]";
    By btn_loginAsDis = By.xpath("//a[contains(text(), 'Login As (supplier)')]");
    By InternalToolsBtn = By.xpath("//a[contains(text(),'Internal Tools') and contains(@class,'active nav-link')]");
    By lbl_loginAsCustomer = By.xpath("//button[contains(text(),'Re-Index')]/following-sibling::div//div[contains(text(),'Select')]/following::input[@type='text'][1]");
    String txt_customer = "//div[@id='react-select-5-option-0' and contains(text(), 'PHONE_NO')]";
    By btn_loginAsCustomer = By.xpath("//a[contains(text(), 'Login As (classic)')]");


    public void typeEmailOrMobile(String emailOrMobile){
        distributorUI.sendKeys(txt_emailOrMobile,emailOrMobile);
    }
    public void typePassword(String password){
        distributorUI.sendKeys(txt_password,password);
    }
    public void clickSubmit(){
        distributorUI.click(btn_signIn);
    }
    public void clickForgotPassword(){
        distributorUI.click(btn_forgotPassword);
    }
    public boolean isForgotPasswordPopupDisplayed(){
        return distributorUI.isDisplayed(txt_forgotPassword);
    }
    public void enterEmailOrMobile(String emailOrMobile){
        distributorUI.sendKeys(txt_enterEmailOrMobile,emailOrMobile);
    }
    public void clickRequestPassword() {
        distributorUI.waitForClickability(btn_requestPassword);
        distributorUI.click(btn_requestPassword);
    }
    public boolean validEmailOrMobile() {
        return distributorUI.isDisplayed(txt_validEmailOrMobile);
    }
    public boolean invalidEmailOrMobile() {
        return distributorUI.isDisplayed(txt_invalidEmailOrMobile);
    }
    public void clickOk() {
        distributorUI.waitForVisibility(btn_ok);
        distributorUI.click(btn_ok);
    }
    public void clickTryAgain() {
        distributorUI.waitForVisibility(btn_tryAgain);
        distributorUI.click(btn_tryAgain);
    }
    public void navigateToRestaurant() {
        distributorUI.navigateToURL(Constants.SEC_URL);
    }
    public void navigateToDistributor() {
        distributorUI.navigateToURL(Constants.MAIN_URL);
    }
    public void navigateToLoginAsPortal(String name) {
        distributorUI.navigateToURL(Constants.LOGIN_AS);
        Orders.closeRatingOverlay();
        distributorUI.sendKeys(lbl_loginAs,name);
        distributorUI.isDisplayed(By.xpath(txt_whitelblCustomer.replace("NAME", name)));
        distributorUI.click(By.xpath(txt_whitelblCustomer.replace("NAME", name)));
        distributorUI.navigateToURL(distributorUI.getText(btn_loginAsWLApp, "href"));
    }
    public void navigateToSupplierPortalVendor(String id) {
        distributorUI.navigateToURL(Constants.NODE_EXPLORER);
        distributorUI.navigateToURL(distributorUI.getText(lbl_verifiedVendor, "href"));
        distributorUI.navigateToURL(distributorUI.getText(By.xpath(txt_verifiedVendor.replace("ID", id)), "href"));
        distributorUI.navigateToURL(distributorUI.getText(lbl_suuplierPortalVendorData, "href"));
    }
    public void setNode() {
        distributorUI.sendKeys(txt_key,"allowUpdateSalesPeopleMapData");
        distributorUI.sendKeys(txt_value, String.valueOf(true));
        distributorUI.click(btn_setData);
    }
    public void navigateToDistributorPortal(String name) {
        distributorUI.navigateToURL(Constants.LOGIN_AS);
        distributorUI.waitForElementEnabledState(lbl_loginAsDist,true);
        distributorUI.sendKeys(lbl_loginAsDist,name);
        distributorUI.click(By.xpath(txt_distributor.replace("NAME", name)));
        distributorUI.navigateToURL(distributorUI.getText(btn_loginAsDis, "href"));
    }

    public void ClickOnInternalTools(){
        distributorUI.navigateToURL(Constants.LOGIN_AS);
        distributorUI.waitForElementEnabledState(lbl_loginAsDist,true);
        distributorUI.click(InternalToolsBtn);
    }
    public void navigateToConfigSupplier() {
        distributorUI.navigateToURL(Constants.CONFIG_SUPPLIER);
    }
    public void navigateToRestaurantProd() {
        distributorUI.navigateToURL(Constants.SEC_URL);
    }
    public void navigateToDistributorPortalProd(String name) {
        distributorUI.navigateToURL(Constants.PROD_LOGIN_AS);
        distributorUI.waitForElementEnabledState(lbl_loginAsDist,true);
        distributorUI.sendKeys(lbl_loginAsDist,name);
        distributorUI.click(By.xpath(txt_distributor.replace("NAME", name)));
        distributorUI.navigateToURL(distributorUI.getText(btn_loginAsDis, "href"));
    }

    public void switchIntoNewTab(){
        distributorUI.OpenNewTabAndSwitchToIt();
    }

    public void navigateToRestaurantPortal(String phoneNo) {
        distributorUI.navigateToURL(Constants.LOGIN_AS);
        distributorUI.waitForElementEnabledState(lbl_loginAsCustomer,true);
        distributorUI.sendKeys(lbl_loginAsCustomer,phoneNo);
        distributorUI.click(By.xpath(txt_customer.replace("PHONE_NO", phoneNo)));
        distributorUI.navigateToURL(distributorUI.getText(btn_loginAsCustomer, "href"));
    }
}
