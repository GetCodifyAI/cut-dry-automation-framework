package com.cutanddry.qa.functions;

import com.cutanddry.qa.pages.ScanToOrderPage;
import org.openqa.selenium.By;

public class ScanToOrder {
    static ScanToOrderPage scanToOrderPage = new ScanToOrderPage();


   public static boolean isNavigatedToScanToOrderPage(){
        return scanToOrderPage.isScanToOrderTextDisplayed();
   }

   public static boolean isReviewAndConfirmBtnEnabled(){
       return scanToOrderPage.isReviewAndConfirmBtnEnabled();
   }

   public static void selectScanToOrderItemInputField(){
       scanToOrderPage.clickOnScanToOrderItemInputField();
   }

    public static void enterItemCodeToScanToOrderItemInputField(String Item){
       scanToOrderPage.sendItemCodeToScanToOrderItemInputField(Item);
    }

    public static boolean isAddToCartButtonEnabled(){
        return scanToOrderPage.isAddToCartButtonEnabled();
    }

    public static void AddItemsToCart(){
        scanToOrderPage.clickOnAddToCart();
    }

    public static boolean isItemAddedToTheCart(String itemCode){
        return scanToOrderPage.isItemAddedToTheCart(itemCode);
    }

   public static boolean isScanToOrderCancelBtnDisplayed(String customerCode){
       return scanToOrderPage.isScanToOrderCancelBtnDisplayed(customerCode);
   }

    public static void ClickOnScanToOrderCancelBtn(String customerCode){
         scanToOrderPage.ClickOnScanToOrderCancelBtn(customerCode);
    }

    public static void IncreaseItemQty(String ItemCode, int Quantity) throws InterruptedException {
       scanToOrderPage.clickQuantityIncreasePlusIcon(ItemCode,Quantity);
    }

    public static double getItemPriceOfItem(String ItemCode){
       return scanToOrderPage.getItemPrice(ItemCode);
    }

    public static void ReviewAndConfirm(){
        scanToOrderPage.clickOnReviewAndConfirm();
    }
    public static void DecreaseItemQty(String ItemCode, int Quantity) throws InterruptedException {
       scanToOrderPage.clickQuantityDecreaseMinusIcon(ItemCode,Quantity);
    }

    public static boolean isTotalQuantityTextDisplayed(){
        return scanToOrderPage.isTotalQuantityTextDisplayed();
    }

    public static boolean isTotalLineItemsTextDisplayed(){
        return scanToOrderPage.isTotalLineItemsTextDisplayed();
    }

    public static boolean isTotalEstimatedCostTextDisplayed(){
        return scanToOrderPage.isTotalEstimatedCostTextDisplayed();
    }

    public static boolean isTotalDiscountsTextDisplayed(){
        return scanToOrderPage.isTotalDiscountsTextDisplayed();
    }

    public static int getProductQuantityValue(){
        return scanToOrderPage.getProductQuantityValue();
    }

    public static int getProducttotalQuantityValues(){
        return scanToOrderPage.getProducttotalQuantityValues();
    }

    public static int getCartLineItemCount(){
        return scanToOrderPage.getCartLineItemCount();
    }

    public static int getCartItemCountInOrderSummary(){
        return scanToOrderPage.getCartItemCountInOrderSummary();
    }

    public static double getTotalCostInOrderSummary(){
        return scanToOrderPage.getTotalCostInOrderSummary();
    }

    public static boolean isSearchInputFieldTextDisplayed(){
        return scanToOrderPage.isSearchInputFieldTextDisplayed();
    }

    public static String getSearchInputFieldText(){
        return scanToOrderPage.getSearchInputFieldText();
    }

    public static String getCTAButtonText(){
        return scanToOrderPage.getCTAButtonText();
    }

    public static boolean isEmptyCartTextDisplayed(String emptyCartText){
        return scanToOrderPage.isEmptyCartTextDisplayed(emptyCartText);
    }

}
