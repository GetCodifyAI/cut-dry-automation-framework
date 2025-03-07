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

    public static void IncreaseItemQty(String ItemCode, int Quantity){
       scanToOrderPage.clickQuantityIncreasePlusIcon(ItemCode,Quantity);
    }

    public static double getItemPriceOfItem(String ItemCode){
       return scanToOrderPage.getItemPrice(ItemCode);
    }

}
