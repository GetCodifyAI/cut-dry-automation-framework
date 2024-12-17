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
        ordersPage.clickCloseRatingOverlay();
    }

    public static void closeRatingOverlay(){
        ordersPage.clickCloseRatingOverlay();
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
    public static boolean isNavigatedToOrderReviewPage(){
        return ordersPage.isNavigatedToReviewOrderScreen();
    }
    public static boolean isSubmitPopupDisplayed(){
        return ordersPage.isSubmitPopupDisplayed();
    }
    public static boolean isOrderUpdatedOverlayDisplayed(){
        return ordersPage.isOrderUpdatedTxtDisplayed();
    }
    public static void clickOnClose(){
        ordersPage.clickOnClose();
    }
    public static void selectFirstOrder(){
        ordersPage.selectFirstOrder();
    }
    public static void clickBulkActions(){
        ordersPage.clickBulkActions();
    }
    public static void clickPrintKitchenReceipt(){
        ordersPage.clickPrintKitchenReceipt();
    }
    public static void clickPrintOrderConfirmation(){
        ordersPage.clickPrintOrderConfirmation();
    }
    public static void searchOrder(String code) throws InterruptedException {
        ordersPage.typeOnSearch(code);
    }
    public static String isCustomerSearchResultDisplayed() throws InterruptedException {
        return ordersPage.isCustomerSearchResultDisplayed();
    }
    public static void selectOrderDate(String days) {
        ordersPage.selectOrderDate(days);
    }
    public static void selectOrderStatus(String sts) {
        ordersPage.selectOrderStatus(sts);
    }
    public static boolean isOrderDateChanged(String days){
        return ordersPage.isOrderDateChanged(days);
    }
    public static boolean isOrderStatusChanged(String days){
        return ordersPage.isOrderStatusChanged(days);
    }
    public static String getCountDates() {
        return ordersPage.getCountDates();
    }

    public static boolean validateFilteredOrders(String OrdersDate){
        return ordersPage.isFilteredOrdersCorrect(OrdersDate);
    }

    public static boolean validateOrdersStatus(String OrdersState){
        return ordersPage.isFilteredOrderStatusCorrect(OrdersState);
    }

    public static boolean validateMoreFilterResults(String moreFilterStatus){
        return ordersPage.isMoreFiltersDisplayedCorrect(moreFilterStatus);
    }

    public static String getCountStatus() {
        return ordersPage.getCountStatus();
    }
    public static String getResultsCount() throws InterruptedException {
        return ordersPage.getResultsCount();
    }
    public static void clickOnMoreFilters(){
        ordersPage.clickOnMoreFilters();
    }
    public static boolean isFilterOrdersPopupDisplayed(){
        return ordersPage.isFilterOrdersPopupDisplayed();
    }
    public static void selectCreditReqStatus() throws InterruptedException {
        ordersPage.selectCreditReqStatus();
    }
    public static void selectSalespersonStatus() throws InterruptedException{
        ordersPage.selectSalespersonStatus();
    }
    public static void clickOrder(){ordersPage.clickOrder();}
    public static void clickCheckIn(){
        ordersPage.clickCheckIn();
    }
    public static boolean isCheckInOrderSectionDisplayed(){
        return ordersPage.isCheckInOrderSectionDisplayed();
    }
    public static void clickOrderPrintKitchenReceipt(){
        ordersPage.clickOrderPrintKitchenReceipt();
    }
    public static void clickOrderPrintOrderConfirmation(){
        ordersPage.clickOrderPrintOrderConfirmation();
    }
    public static void clickUpdateStatus(){
        ordersPage.clickUpdateStatus();
    }
    public static boolean isUpdateStatusDropDownDisplayed(){
        return ordersPage.isUpdateStatusDropDownDisplayed();
    }
    public static void clickStatusDropDown(){
        ordersPage.clickStatusDropDown();
    }
    public static void clickStatusOption()throws InterruptedException{
        ordersPage.clickStatusOption();
    }
    public static boolean isAreYouSurePopUpDisplayed(){
        return ordersPage.isAreYouSurePopUpDisplayed();
    }
    public static void clickYes(){
        ordersPage.clickYes();
    }
    public static boolean isOrderStatusUpdatedPopUpDisplayed(){
        return ordersPage.isOrderStatusUpdatedPopUpDisplayed();
    }
    public static void clickOkUpdate(){
        ordersPage.clickOkButton();
    }
    public static boolean checkFiltersCorrectlyDisplayed(String status){
        return ordersPage.checkFiltersCorrectlyDisplayed(status);
    }
    public static boolean isOrderSectionDisplayed(){
        return ordersPage.isOrderSectionDisplayed();
    }
    public static void clickOrderStatus(){ordersPage.clickOrderStatus();}
    public static void selectOrderStatusOption(String status){
        ordersPage.selectOrderStatusOption(status);
    }
    public static boolean isOrderStatusUpdatedDisplayed(String status){
        return ordersPage.isOrderStatusUpdatedDisplayed(status);
    }



}
