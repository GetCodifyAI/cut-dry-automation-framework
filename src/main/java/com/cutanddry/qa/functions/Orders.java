package com.cutanddry.qa.functions;

import com.cutanddry.qa.pages.CustomersPage;
import com.cutanddry.qa.pages.OrdersPage;

public class Orders {
    static OrdersPage ordersPage = new OrdersPage();
    static CustomersPage customersPage = new CustomersPage();

    public static boolean isUserNavigatedToOrder(){
        return ordersPage.isOrdersTextDisplayed();
    }

    public static void SelectSupplierFromPlaceOrderPage(String supplierName) throws InterruptedException {
        ordersPage.clickOnSupplier(supplierName);
        if (ordersPage.isSelectOrderGuidePopUpDisplayed()){
            ordersPage.selectOrderGuide();
        }
        if (customersPage.isPreviousDraftOrderNoDisplayed()){
            customersPage.clickPreviousDraftOrderNo();
        }
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
        if(customersPage.isOrderMinimumOverlayDisplayed()){
            customersPage.clickPlaceOrderSoftOrderMinimum();;
        }
        if (customersPage.isDuplicatePopupDisplayed()){
            customersPage.clickYesDuplicatePopup();
        }
    }

    public static boolean sendToApprovalOverlayDisplayed(){
        return ordersPage.isSubmitForApprovalOverlayDisplayed();
    }
    public static String getApprovalOrderRefID(){
        return ordersPage.getApprovalOrderRefID();
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

    public static boolean isSelectOrderGuideDisplayed(){
       return ordersPage.isSelectOrderGuidePopUpDisplayed();
    }
    public static void clickOnFirstOrder() throws InterruptedException {
        ordersPage.clickOnFirstOrder();
    }
    public static void clickOnFirstOrder(String status){
        ordersPage.clickOnFirstOrder(status);
    }
    public static void clickOnEditOrder() throws InterruptedException {
        ordersPage.clickOnEditOrder();
    }
    public static void clickOnEditOrderInReview() throws InterruptedException {
        ordersPage.clickOnEditOrderInReview();
    }
    public static void clickOnEditOrderInReviewStable() throws InterruptedException {
        if (ordersPage.isNavigatedToReviewOrderScreen()) {
            ordersPage.clickOnEditOrderInReview();
        }
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

    public static boolean validateFilteredOrders(String OrdersDate) throws InterruptedException {
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
    public static void selectCreditReqStatusStable(String type) throws InterruptedException {
        ordersPage.selectCreditReqStatusStable(type);
    }
    public static void selectSalespersonStatus() throws InterruptedException{
        ordersPage.selectSalespersonStatus();
    }
    public static void selectSalespersonStatusStable(String type) throws InterruptedException {
        ordersPage.selectSalespersonStatusStable(type);
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

    public static void clickTimeline(){
        ordersPage.clickTimeline();
    }

    public static void clickItems(){
        ordersPage.clickItems();
    }

    public static boolean isItemsTabActive(){
        return ordersPage.isItemsTabActive();
    }

    public static boolean isTimelineTabActive(){
        return ordersPage.isTimelineTabActive();
    }

    public static boolean isOrderedItemsSectionDisplayed(){
        return ordersPage.isOrderedItemsSectionDisplayed();
    }

    public static boolean isTimelineContentDisplayed(){
        return ordersPage.isTimelineContentDisplayed();
    }

    public static String[] getTimelineData(){
        return ordersPage.getTimelineData();
    }

    public static void clickReportIssue(){
        ordersPage.clickReportIssue();
    }

    public static void clickOnFirstOptionDropDownWhatIsWrong(){
        ordersPage.clickOnDropDownWhatIsWrong();
        ordersPage.clickOnFirstRowDropDownWhatIsWrong();
    }

    public static void clickOnFirstRowTableOrderIssues(){
        ordersPage.clickOnFirstRowTableOrderIssues();
    }

    public static void clickOnBtnContinue(){
        ordersPage.clickOnBtnContinue();
    }

    public static void clickBtnSaveCheckIn(){
        ordersPage.clickBtnSaveCheckIn();
    }

    public static String getLastWorkingDateEST(){
        return ordersPage.getLastWorkingDateEST();
    }

    public static String getLastWorkingDateUST(){
        return ordersPage.getLastWorkingDateUST();
    }
    public static void clickSaveButton(){ordersPage.clickSaveButton();}
    public static boolean isEditColumnDisplay(String option){
        return ordersPage.isEditColumnDisplay(option);
    }
    public static void clickEditColumn(String option){
        ordersPage.clickEditColumn(option);
    }
    public static boolean isEditColumnWindowDisplay(String option){
        return ordersPage.isEditColumnWindowDisplay(option);
    }
    public static boolean isNotCustomizeColumnDisplay(String column){
        return ordersPage.isNotCustomizeColumnDisplay(column);
    }
    public static void setCustomizeColumn(boolean column) {
        ordersPage.setCustomizeColumn(column);
    }
    public static void clickUpdateColumn(){
        ordersPage.clickUpdateColumn();
    }
    public static boolean isColumnSettingUpdatePopUpDisplay(){
        return ordersPage.isColumnSettingUpdatePopUpDisplay();
    }
    public static boolean isColumnUpdateDisplay(String column){
        return ordersPage.isColumnUpdateDisplay(column);
    }
    public static void SelectSupplierFromPlaceOrder(String supplierName) throws InterruptedException {
        ordersPage.clickOnSupplier(supplierName);
        if (ordersPage.isSelectLocationPopUpDisplayed()){
            ordersPage.selectLocation();
        }
        if (customersPage.isPreviousDraftOrderNoDisplayed()){
            customersPage.clickPreviousDraftOrderNo();
        }
    }
    public static boolean isSubmitForApprovalButtonDisplay(){
        return ordersPage.isSubmitForApprovalButtonDisplay();
    }
    public static void clickFindMoreInCatalog()throws InterruptedException{
        ordersPage.clickFindMoreInCatalog();
    }
    public static boolean isInactiveItemDetectedPopUpDisplay()throws InterruptedException{
        return ordersPage.isInactiveItemDetectedPopUpDisplay();
    }
    public static void SelectOneSupplierFromPlaceOrder(String supplierName,String location) throws InterruptedException {
        ordersPage.clickOnSupplier(supplierName);
        if (ordersPage.isSelectLocationPopUpDisplayed()){
            ordersPage.selectLocationSupplier(location);
        }
        if (ordersPage.isSelectOrderGuidePopUpDisplayed()){
            ordersPage.selectOrderGuide();
        }
        if (customersPage.isPreviousDraftOrderNoDisplayed()){
            customersPage.clickPreviousDraftOrderNo();
        }
    }

    public static boolean isOrderInfoCustomerDisplayed(String customerName){
        return ordersPage.isOrderInfoCustomerDisplayed(customerName);
    }

    public static boolean isOrderInfoLocationCodeDisplayed(String customerLocation){
        return ordersPage.isOrderInfoLocationCodeDisplayed(customerLocation);
    }

    public static boolean isOrderInfoDeliveryOnDisplayed(String deliveryDate){
        return ordersPage.isOrderInfoDeliveryOnDisplayed(deliveryDate);
    }

    public static boolean isOrderInfoShipToDisplayed(String shipTo){
        return ordersPage.isOrderInfoShipToDisplayed(shipTo);
    }

    public static boolean isOrderInfoFulfilmentMethodDisplayed(String fulfillmentMethod){
         return ordersPage.isOrderInfoFulfilmentMethodDisplayed(fulfillmentMethod);
    }

    public static boolean isOrderInfoStatusDisplayed(String orderStatus){
        return ordersPage.isOrderInfoStatusDisplayed(orderStatus);
    }

    public static boolean isOrderDateColumnHeaderDisplayed(){
        return ordersPage.isOrderDateColumnHeaderDisplayed();
    }
    public static String getFirstOrderDateText(){
        return ordersPage.getFirstOrderDateText();
    }

    public static boolean isFirstOrderFulfillmentBadgeDisplayed(){
        return ordersPage.isFirstOrderFulfillmentBadgeDisplayed();
    }

    public static String getFirstOrderFulfillmentBadgeText(){
        return ordersPage.getFirstOrderFulfillmentBadgeText();
    }

    public static boolean isTotalColumnHeaderDisplayed() {
        return ordersPage.isTotalColumnHeaderDisplayed();
    }

    public static int getTotalColumnIndex() {
        return ordersPage.getTotalColumnIndex();
    }

    public static String getTotalValueFromRow(int rowIndex, int columnIndex) {
        return ordersPage.getTotalValueFromRow(rowIndex, columnIndex);
    }

    public static boolean isTotalAmountFormattedWithDollarSign(String totalValue) {
        return ordersPage.isTotalAmountFormattedWithDollarSign(totalValue);
    }

    public static boolean isTotalAmountFormattedWithTwoDecimalPlaces(String totalValue) {
        return ordersPage.isTotalAmountFormattedWithTwoDecimalPlaces(totalValue);
    }

    public static boolean isTotalAmountFormattedWithCommaForLargeAmounts(String totalValue) {
        return ordersPage.isTotalAmountFormattedWithCommaForLargeAmounts(totalValue);
    }

    public static boolean isResultsCountDisplayed() {
        return ordersPage.isResultsCountDisplayed();
    }

    public static void clickCancel(){
        ordersPage.clickCancel();
    }

    public static void clickItems(){
        ordersPage.clickItems();
    }

    public static boolean isItemsTabActive(){
        return ordersPage.isItemsTabActive();
    }

    public static boolean isTimelineTabActive(){
        return ordersPage.isTimelineTabActive();
    }

    public static boolean isOrderedItemsSectionDisplayed(){
        return ordersPage.isOrderedItemsSectionDisplayed();
    }

    public static boolean isTimelineContentDisplayed(){
        return ordersPage.isTimelineContentDisplayed();
    }

}
