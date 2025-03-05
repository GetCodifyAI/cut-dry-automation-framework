package com.cutanddry.qa.pages;

import org.openqa.selenium.By;

public class ScanToOrderPage extends LoginPage{
    By ScanToOrderText = By.xpath("//h1[contains(text(),'Scan to Order')]");
    By ScanToOrderItemInputField = By.xpath("//input[contains(@placeholder,'Scan barcode or search item by name or code')]");
    By ReviewAndConfirmBtn = By.xpath("//button[contains(text(),'Review & Confirm Order')]");
    By AddToCartBtn = By.xpath("//button[contains(normalize-space(.),'Add to Cart')]");
    String ItemInCart = "//div[contains(@class,'cartContainer')]//span[contains(text(),'ITEMCODE')]";
    By customerScreenScanToOrderCancelOrderBtn = By.xpath("//button[@type='button' and contains(@class, 'btn-outline') and text()='Cancel Order']");

    By ScanToOrderButton = By.xpath("//a[contains(text(), 'Scan to Order')]");

    public boolean isScanToOrderTextDisplayed(){
        return distributorUI.isDisplayed(ScanToOrderText);
    }


    public boolean isReviewAndConfirmBtnEnabled(){
        return distributorUI.isElementEnabled(ReviewAndConfirmBtn);
    }

    public void clickOnScanToOrderItemInputField(){
        distributorUI.click(ScanToOrderItemInputField);
    }

    public void sendItemCodeToScanToOrderItemInputField(String Item){
        distributorUI.sendKeys(ScanToOrderItemInputField,Item);
    }

    public boolean isAddToCartButtonEnabled(){
        distributorUI.waitForVisibility(AddToCartBtn);
        return distributorUI.isElementEnabled(AddToCartBtn);
    }

    public void clickOnAddToCart(){
        try {
            distributorUI.waitForCustom(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        distributorUI.click(AddToCartBtn);
    }

    public boolean isItemAddedToTheCart(String itemCode){
        return distributorUI.isDisplayed(By.xpath(ItemInCart.replace("ITEMCODE",itemCode)));
    }

    public boolean isScanToOrderCancelBtnDisplayed(String customerCode){
        distributorUI.waitForVisibility(customerScreenScanToOrderCancelOrderBtn);
        return distributorUI.isDisplayed(customerScreenScanToOrderCancelOrderBtn);
    }

    public void ClickOnScanToOrderCancelBtn(String customerCode){
        try{
            distributorUI.waitForCustom(2000);
        }catch(InterruptedException e){
            throw new RuntimeException(e);
        }
        distributorUI.click(customerScreenScanToOrderCancelOrderBtn);
    }






}
