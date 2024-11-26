package com.cutanddry.qa.functions;

import com.cutanddry.qa.pages.OrdersPage;
import static com.cutanddry.qa.functions.Customer.customersPage;

public class Orders {
    static OrdersPage ordersPage = new OrdersPage();

    public static boolean isUserNavigatedToOrder(){
        return ordersPage.isOrdersTextDisplayed();
    }

    public static void SelectSupplierFromPlaceOrderPage(String supplierName){
        ordersPage.clickOnSupplier(supplierName);
    }

    public static void increaseItemQuantity(String ItemCode, int Quantity){
        ordersPage.clickOnIncreaseQuantityBtnInItem(ItemCode,Quantity);
    }

    public static void checkOutFromOperatorCart(){
        ordersPage.clickOnCheckoutBtnInOperator();
    }

    public static void submitForApproval(){
        ordersPage.clickOnSubmitForApproval();
        if (customersPage.isDuplicatePopupDisplayed()){
            customersPage.clickYesDuplicatePopup();
        }
    }

    public static boolean sendToApprovalOverlayDisplayed(){
        return ordersPage.isSubmitForApprovalOverlayDisplayed();
    }

    public static void viewOrderInDraft(){
        ordersPage.clickOnViewOrderInDrafts();
    }

    public static boolean orderDraftDisplayedForApproval(){
        return ordersPage.isOrderDraftDisplayedForApproval();
    }

    public static void selectOrderGuide(String OrderGuide) {
        ordersPage.clickOnOrderGuide(OrderGuide);
    }
    public static void clickOnFirstOrder(){
        ordersPage.clickOnFirstOrder();
    }
    public static void clickOnEditOrder(){
        ordersPage.clickOnEditOrder();
    }
    public static boolean isEditOrderPopupDisplayed(){
        return ordersPage.isEditOrderPopupDisplayed();
    }
    public static void clickOnConfirm(){
        ordersPage.clickOnConfirm();
    }
    public static boolean isNavigatedToEditOrder(){
        return ordersPage.isNavigatedToEditOrder();
    }
    public static boolean isSubmitPopupDisplayed(){
        return ordersPage.isSubmitPopupDisplayed();
    }
    public static void clickOnClose(){
        ordersPage.clickOnClose();
    }
}
