package com.cutanddry.qa.functions;

import com.cutanddry.qa.pages.DashboardPage;
import com.cutanddry.qa.pages.OrderDeskPage;

public class OrderDesk {
    static OrderDeskPage orderDeskPage = new OrderDeskPage();
    static DashboardPage dashboardPage = new DashboardPage();

    public static boolean isUsernavigatedToOrderDeskPage(){
        return orderDeskPage.isOrderDeskTextDisplayed();
    }

    public static void navigateToSubmittedOrders(){
        orderDeskPage.clickOnSubmittedOrders();
    }

    public static void navigateToDraftOrders(){
        orderDeskPage.clickOnDraftOrders();
    }

    public static boolean iscustomerTxtDisplayInDraftOrders(){
        return orderDeskPage.isDraftedOrdersCustomerTxtDisplayed();
    }

    public static void navigateToDraftOrderReviewPage(){
        orderDeskPage.clickOnDraftOrderPageReviewBtn();
        if(orderDeskPage.isDateErrorDisplayedInDraftOrder()){
            dashboardPage.clickOnOrderDesk();
            orderDeskPage.clickOnDraftOrderPageReviewBtn2();
        }
    }

    public static void AddItemQuantityDraftOrderReviewPage(){
        orderDeskPage.clickOnAddQuantityBtnOnDraftOrderReviewPage();
    }

    public static void SubstractItemQuantityDraftOrderReviewPage(){
        orderDeskPage.clickOnsubstractQuantityBtnOnDraftOrderReviewPage();
    }

    public static void EditItemQuantityDraftOrderReviewPage(String itemQuantity) throws InterruptedException {
        orderDeskPage.editItemQuantityOnDraftOrderReviewPage(itemQuantity);
    }

    public static void AddLineItemDraftOrderReviewPage(){
        orderDeskPage.ClickAddLineBtnOnDraftOrderReviewPage();
    }

    public static void SaveDraftOrder(){
        orderDeskPage.clickOnSaveDraftBtn();
    }

    public static boolean isSaveDraftSucessful(){
        return orderDeskPage.isSaveDraftsucessfullytextisDisplayed();
    }

    public static void CloseTheSucessfulOverlayByOK(){
        orderDeskPage.ClickSucessfullySavedOverlayOkBtn();
    }

    public static void uploadVoiceRecord(String path){
        orderDeskPage.giveFilePath(path);
    }

    public static int getNumberOfProcessingVoiceMessages(){
        return orderDeskPage.isVoiceUploadProcssingCountIncreased();
    }

    public static void SearchItemByname(){
        orderDeskPage.SelectItemFromDropdown();
    }

    public static void AddItem(){
        orderDeskPage.ClickAddItemBtn();
    }

    public static boolean isItemAddedSucessfullySaved(){
        return orderDeskPage.isItemAddSucessfullytextisDisplayed();
    }

    public static void DeleteItem(){
        orderDeskPage.ClickOnDeleteBtn();
    }

    public static void ConfirmDeleteItem(){
         orderDeskPage.ClickDeleteConfirmationOverlayYesBtn();
    }

    public static boolean isItemDeleteSucessfullySaved(){
        return orderDeskPage.isSucessfullySavedtextisDisplayed();
    }

    public static void ClickOnLineItem(){
        orderDeskPage.ClickOnItemName();
    }

    public static void ClickOnProductSearch(){
        orderDeskPage.ClickOnProductSearchBtn();
    }

    public static void SelectProductForSwap(){
        orderDeskPage.ClickProductForSwap();
    }

    public static void ConfirmItemSwap(){
        orderDeskPage.ClickSwapConfirmBtn();
    }

    public static boolean isItemSwapSucessfullySaved(){
        return orderDeskPage.isSucessfullySavedtextisDisplayed();
    }

    public static void SelectCustomerFromDropdown(){
        orderDeskPage.SelectCustomer();
    }

    public static void SelectLocationFromDropdown(){
        orderDeskPage.SelectLocation();
    }

    public static boolean isCustomerLocationSucessfullySaved(){
        return orderDeskPage.isSucessfullySavedtextisDisplayed();
    }

    public static void SelectDeliveryDate(String deliveryDate){
        orderDeskPage.SelectDeliveryDate(deliveryDate);

    }

    public static boolean isInvalidDeliveryDateTextDisplayed(){
        return orderDeskPage.isDeliveryDateInvalidTextisDisplayed();
    }

    public static boolean navigatedToSubmittedOrders(){
        return orderDeskPage.isSubmittedOrderCustomerTxtDisplayed();
    }

    public static void navigateToSubmitOrdersReviewPage(){
        orderDeskPage.clickOnSubmittedOrderPageReviewBtn();
    }
}
