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
    String lbl_enterCardNum = "ccnumber";
    String lbl_enterExpDate = "ccexp";
    String lbl_enterCVV = "cvv";
    By btn_submitOrder = By.xpath("//button[contains(text(), 'Submit Pick Up Order')]");
    By txt_invalidCardPopup = By.xpath("//h2[text()='Invalid Card Detail(s)']");
    By btn_OK = By.xpath("//button[text()='OK']");
    By txt_paymentFailedPopup = By.xpath("//h2[contains(text(), 'Your payment authorization failed.')]");
    String getDicarloPriceUOM = "(//div//span[contains(text(),'$')])[UOM]";
    String btn_addToCartPlusQuantityDicarlo = "(//*[name()='svg' and contains(@data-icon, 'plus')])[UOM]";
    By txt_transactionRejectPopup = By.xpath("//h2[contains(text(),'Transaction was rejected')]");
    By lbl_productDetailsDicarlo = By.xpath("//div[text()='Product Details']");
    By orderSucessfullyPlacedTxt = By.xpath("//div[contains(text(),'Thank you for your DiCarlo Marketplace Order.')]");

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
        distributorUI.waitForCustom(3000);
        distributorUI.sendKeysToField(lbl_enterCardNum,card);
    }
    public void enterExpDate(String exp){
        distributorUI.sendKeysToField(lbl_enterExpDate,exp);
    }
    public void enterCVV(String cvv){
        distributorUI.sendKeysToField(lbl_enterCVV,cvv);
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
    public double getDicarloPDPPriceUOM(String uom) throws InterruptedException {
        try {
            return extractPrice(By.xpath(getDicarloPriceUOM.replace("UOM", uom)));
        } catch (Exception e) {
            System.out.println("Fallback to alternative price locator due to: " + e.getMessage());
            return extractPrice(By.xpath(getDicarloPriceUOM.replace("UOM", uom)));
        }
    }
    public void clickDicarloAddToCartPlusIcon(String uom)throws InterruptedException{
        distributorUI.waitForVisibility(By.xpath(btn_addToCartPlusQuantityDicarlo.replace("UOM", uom)));
        distributorUI.click(By.xpath(btn_addToCartPlusQuantityDicarlo.replace("UOM", uom)));
        distributorUI.waitForCustom(3000);
    }
    private double extractPrice(By priceLocator) {
        distributorUI.waitForVisibility(priceLocator);
        String tagName = distributorUI.getElement(priceLocator).getTagName();
        String priceText;

        if (tagName.equals("input")) {
            priceText = distributorUI.getText(priceLocator, "value");
        } else {
            priceText = distributorUI.getText(priceLocator);
        }

        System.out.println("Extracted Price: " + priceText);
        return Double.valueOf(priceText.replace("$", "").replace("/cs", "").replace("/pkg", "").trim());
    }
    public boolean isTransactionRejectPopupDisplayed(){
        return distributorUI.isDisplayed(txt_transactionRejectPopup);
    }
    public boolean isPaymentSucessfullyScreenDisplayed(){
        return distributorUI.isDisplayed(orderSucessfullyPlacedTxt);
    }
    public boolean isProductDetailsDisplayedDicarlo(){
        try {
            distributorUI.waitForCustom(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return distributorUI.isDisplayed(lbl_productDetailsDicarlo);
    }

}
