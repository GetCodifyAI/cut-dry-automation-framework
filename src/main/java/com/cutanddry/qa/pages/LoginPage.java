package com.cutanddry.qa.pages;

import com.cutanddry.qa.base.TestBase;
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
        distributorUI.navigateToURL("https://app-uat.staging.cutanddry.com/");
    }
    public void navigateToDistributor() {
        distributorUI.navigateToURL("https://supplier-uat.staging.cutanddry.com/");
    }
}
