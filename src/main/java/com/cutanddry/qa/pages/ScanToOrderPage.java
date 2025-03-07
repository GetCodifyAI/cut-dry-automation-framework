package com.cutanddry.qa.pages;

import org.openqa.selenium.By;

public class ScanToOrderPage extends LoginPage{
    By ScanToOrderText = By.xpath("//h1[contains(text(),'Scan to Order')]");
    By ScanToOrderItemInputField = By.xpath("//input[contains(@placeholder,'Scan barcode or search item by name or code')]");
    By ReviewAndConfirmBtn = By.xpath("//button[contains(text(),'Review & Confirm Order')]");
    By AddToCartBtn = By.xpath("//button[contains(normalize-space(.),'Add to Cart')]");
    String ItemInCart = "//div[contains(@class,'cartContainer')]//span[contains(text(),'ITEMCODE')]";
    By customerScreenScanToOrderCancelOrderBtn = By.xpath("//button[contains(text(),'Cancel Order')]");
    By ScanToOrderButton = By.xpath("//a[contains(text(), 'Scan to Order')]");
    String quantityIncreasePlusBtn = "//div[contains(@class,'cartContainer')]//span[contains(text(),'ITEMCODE')]/../following-sibling::div//*[name()='svg' and @data-icon='plus']";
    String itemPrice = "//div[contains(@class,'cartContainer')]//span[contains(text(),'ITEMCODE')]/../following-sibling::div/div[1]";


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
            distributorUI.waitForCustom(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        distributorUI.click(AddToCartBtn);
    }

    public boolean isItemAddedToTheCart(String itemCode){
        return distributorUI.isDisplayed(By.xpath(ItemInCart.replace("ITEMCODE",itemCode)));
    }

    public boolean isScanToOrderCancelBtnDisplayed(String customerCode){
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

    public void clickQuantityIncreasePlusIcon(String ItemCode, int Quantity){
        for(int i = 1; i < Quantity; i++){
            distributorUI.click(By.xpath(quantityIncreasePlusBtn.replace("ITEMCODE",ItemCode)));
            try {
                distributorUI.waitForCustom(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }

    public double getItemPrice(String ItemCode){
        try {
            distributorUI.waitForCustom(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String itemPriceInString = distributorUI.getText(By.xpath(itemPrice.replace("ITEMCODE",ItemCode)));
        return Double.parseDouble(itemPriceInString.replace("$", ""));
    }

    public void clickOnReviewAndConfirm(){
        try {
            distributorUI.waitForCustom(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        distributorUI.click(ReviewAndConfirmBtn);
    }





}
