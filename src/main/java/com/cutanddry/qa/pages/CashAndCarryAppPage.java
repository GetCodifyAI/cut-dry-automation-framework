package com.cutanddry.qa.pages;

import com.cutanddry.qa.base.TestBase;
import org.openqa.selenium.By;

public class CashAndCarryAppPage extends TestBase {
    By txt_marketPlace = By.xpath("//div[contains(text(),'Marketplace')]");
    By lbl_enterFullName = By.xpath("//label[text()='Full Name']/following-sibling::input");
    By lbl_enterEmail = By.xpath("//label[text()='Email']/following-sibling::input");
    By lbl_enterMobilePhone = By.xpath("//label[text()='Mobile Phone']/following-sibling::input");
    By lbl_enterStreetAddress = By.xpath("//label[text()='Billing Address']/following-sibling::input");
    By lbl_enterCity = By.xpath("//input[@placeholder='City']");
    By lbl_enterState = By.xpath("//input[@placeholder='State']");
    By lbl_enterZip = By.xpath("//input[@placeholder='Zip Code']");
    By lbl_enterCardNum = By.xpath("//input[@id='ccnumber']");
    By lbl_enterExpDate = By.xpath("//input[@id='ccexp']");
    By lbl_enterCVV = By.xpath("//input[@id='cvv']");
    By btn_submitOrder = By.xpath("//button[contains(text(), 'Submit Pick Up Order')]");
    By txt_invalidCardPopup = By.xpath("//h2[text()='Invalid Card Detail(s)']");
    By btn_OK = By.xpath("//button[text()='OK']");
    By btn_checkoutCashCarry = By.xpath("//button[@data-tip='Click here to checkout']");

    public void navigateToCashAndCarryApp(String url){
        distributorUI.navigateToURL(url);
    }
    public boolean isMarketPlaceTextDisplayed() {
        try {
            distributorUI.waitForVisibility(txt_marketPlace);
        } catch (Exception e) {
            return false;
        }
        return distributorUI.isDisplayed(txt_marketPlace);
    }
    public void clickOnCheckoutButton() throws InterruptedException {
        distributorUI.waitForCustom(4000);
        distributorUI.waitForElementEnabledState(btn_checkoutCashCarry,true);
        distributorUI.click(btn_checkoutCashCarry);
        distributorUI.waitForCustom(4000);
    }
    public void enterFullName(String name){
        distributorUI.sendKeys(lbl_enterFullName,name);
    }
    public void enterEmail(String email){
        distributorUI.sendKeys(lbl_enterEmail,email);
    }
    public void enterMobilePhone(String mobile){
        distributorUI.sendKeys(lbl_enterMobilePhone,mobile);
    }
    public void enterStreetAddress(String street){
        distributorUI.sendKeys(lbl_enterStreetAddress,street);
    }
    public void enterCity(String city){
        distributorUI.sendKeys(lbl_enterCity,city);
    }
    public void enterState(String state){
        distributorUI.sendKeys(lbl_enterState,state);
    }
    public void enterZip(String zip){
        distributorUI.sendKeys(lbl_enterZip,zip);
    }
    public void enterCardNum(String card) throws InterruptedException {
        distributorUI.sendKeys(lbl_enterCardNum,card);
    }
    public void enterExpDate(String exp){
        distributorUI.sendKeys(lbl_enterExpDate,exp);
    }
    public void enterCVV(String cvv){
        distributorUI.sendKeys(lbl_enterCVV,cvv);
    }
    public void submitOrder(){
        distributorUI.waitForClickability(btn_submitOrder);
        distributorUI.click(btn_submitOrder);
        distributorUI.waitForVisibility(btn_submitOrder);
    }
    public boolean isInvalidCardDetailsPopupDisplayed(){
        return distributorUI.isDisplayed(txt_invalidCardPopup);
    }
    public void clickOK(){
        distributorUI.waitForClickability(btn_OK);
        distributorUI.click(btn_OK);
    }

}
