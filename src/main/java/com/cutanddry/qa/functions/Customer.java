package com.cutanddry.qa.functions;

import com.cutanddry.qa.pages.CustomersPage;

public class Customer {
    static CustomersPage customersPage = new CustomersPage();

    public static void searchCustomerByCode(String code) throws InterruptedException {
        customersPage.clickOnSearchCustomers();
        customersPage.typeOnSearchCustomers(code);
    }
    public static boolean isCustomerSearchResultByCodeDisplayed(String code) throws InterruptedException {
        return customersPage.isCustomerSearchResultByCodeDisplayed(code);
    }
    public static void clickOnOrderGuide(String code) throws InterruptedException {
        customersPage.clickOnOrderGuide(code);
        if (customersPage.isPreviousDraftOrderNoDisplayed()){
            customersPage.clickPreviousDraftOrderNo();
        }
    }
    public static void increaseFirstRowQtyByOne() throws InterruptedException {
//        if (customersPage.isPreviousDraftOrderNoDisplayed()){
//            customersPage.clickPreviousDraftOrderNo();
//        }
        customersPage.clickPlusQryFirstRow();
    }
    public static void increaseFirstRowQtyCustom(int count) throws InterruptedException {
//        if (customersPage.isPreviousDraftOrderNoDisplayed()){
//            customersPage.clickPreviousDraftOrderNo();
//        }
        for (int i=0; i<count;i++){
            customersPage.clickPlusQryFirstRow();
        }
    }
    public static void decreaseFirstRowQtyByOne(){
        customersPage.clickMinusQryFirstRow();
    }
    public static void increaseFirstRowQtyByThree() throws InterruptedException {
//        if (customersPage.isPreviousDraftOrderNoDisplayed()){
//            customersPage.clickPreviousDraftOrderNo();
//        }
        customersPage.clickPlusQryFirstRow();
        customersPage.clickPlusQryFirstRow();
        customersPage.clickPlusQryFirstRow();
    }
    public static void decreaseFirstRowQtyByThree(){
        customersPage.clickMinusQryFirstRow();
        customersPage.clickMinusQryFirstRow();
        customersPage.clickMinusQryFirstRow();
    }
    public static void increaseSecondRowQtyByOne(){
        customersPage.clickPlusQrySecondRow();
    }
    public static void decreaseSecondRowQtyByOne(){
        customersPage.clickMinusQrySecondRow();
    }
    public static String getItemNameFirstRow() throws InterruptedException {
        return customersPage.getItemNameFirstRow();
    }
    public static String getItemNameSecondRow(){
        return customersPage.getItemNameSecondRow();
    }
    public static void checkoutItems() throws InterruptedException {
        customersPage.clickOnCheckoutButton();
    }
    public static void goToCatalog() throws InterruptedException {
        if (customersPage.isPreviousDraftOrderNoDisplayed()){
            customersPage.clickPreviousDraftOrderNo();
        }
        customersPage.clickOnCatalogButton();
    }
    public static void searchItemOnCatalog(String item) throws InterruptedException {
        customersPage.typeToSearchOnCatalog(item);
    }
    public static String getFirstElementFrmSearchResults(String name){
       return customersPage.getFirstItemNameFrmSearchResults(name);
    }
    public static void addItemToCartCatalog() throws InterruptedException {
        customersPage.clickAddToCartCatalog();
    }
    public static String getItemQtyFirstRow(){
        return customersPage.getItemQtyFirstRow();
    }
    public static double getItemPriceFirstRow(){
        return customersPage.getItemPriceFirstRow();
    }
    public static double getItemPriceOnCheckoutButton() throws InterruptedException {
        return customersPage.getItemPriceOnCheckoutButton();
    }
    public static void increaseQtyByOneCatalogSearchValueOne(){
        customersPage.clickPlusQryCatalogSearchValueOne();
    }
    public static void increaseQtyByOneCatalogSearchValueTwo(){
        customersPage.clickPlusQryCatalogSearchValueOne();
    }
    public static void decreaseQtyByOneCatalogSearchValueOne() throws InterruptedException {
        customersPage.clickMinusQryCatalogSearchValueOne();
    }
    public static void decreaseQtyByOneCatalogSearchValueTwo(){
        customersPage.clickMinusQryCatalogSearchValueTwo();
    }
    public static void decreaseQtyByOneCatalogSearchValueThree(){
        customersPage.clickMinusQryCatalogSearchValueThree();
    }

    public static void increaseQtyUpToThreeCatalogSearch() throws InterruptedException {
        customersPage.clickPlusQryCatalogSearchValueOne();
        customersPage.clickPlusQryCatalogSearchValueTwo();
    }
    public static void decreaseQtyByThreeCatalogSearch() throws InterruptedException {
        customersPage.clickMinusQryCatalogSearchValueThree();
        customersPage.clickMinusQryCatalogSearchValueTwo();
        customersPage.clickMinusQryCatalogSearchValueOne();
    }
    public static String getItemQryCatalogSearch(){
        return customersPage.getItemQryCatalogSearch();
    }
    public static Double getItemPriceCatalogSearch(){
        return customersPage.getItemPriceCatalogSearch();
    }
    public static void increaseQtyUpToThreeFirstRowCart(){
        customersPage.clickPlusQryCartRowOne();
        customersPage.clickPlusQryCartRowOne();
    }
    public static void decreaseQtyUpToZeroFirstRowCart(){
        customersPage.clickMinusQryCartRowOne();
        customersPage.clickMinusQryCartRowOne();
        customersPage.clickMinusQryCartRowOne();
    }
    public static double getUnitPriceFirstRowCart(){
        return customersPage.getUnitPriceFirstRowCart();
    }
    public static double getTotalPriceCart() throws InterruptedException {
        return customersPage.getTotalPriceCart();
    }
    public static void submitOrder(){
        customersPage.submitOrder();
        if (customersPage.isDuplicatePopupDisplayed()){
            customersPage.clickYesDuplicatePopup();
        }
        customersPage.clickClose();
    }
    public static boolean isThankingForOrderPopupDisplayed(){
        return customersPage.isThankingForOrderPopupDisplayed();
    }
    public static boolean isBroadcastMessageDisplayed() {
        return customersPage.isBroadcastMessageDisplayed();
    }
    public static void clickMessage() {
        customersPage.clickMessage();
    }
    public static boolean isProductDetailsDisplayed(){
        return customersPage.isProductDetailsDisplayed();
    }
    public static boolean isTopCategoryPicksDisplayed(){
        return customersPage.isTopCategoryPicksDisplayed();
    }
    public static boolean isItemInTopCategoryPicks(String code){
        return customersPage.isItemInTopCategoryPicks(code);
    }
    public static void selectSearchedCatalogItem(String code) {
        customersPage.clickSearchedItem(code);
    }
    public static boolean isSelectedItemDisplayed(){
        return customersPage.isSelectedItemDisplayed();
    }
    public static boolean isCompareSimilarItemsDisplayed(){
        return customersPage.isCompareSimilarItemsDisplayed();
    }
    public static boolean isRecommendedForYouItemDisplayed(String code){
        return customersPage.isRecommendedForYouItemDisplayed(code);
    }
    public static boolean isRecommendedBySalesRepDisplayed(String code){
        return customersPage.isRecommendedBySalesRepDisplayed(code);
    }
    public static boolean isDontForgetToOrderDisplayed(){
        return customersPage.isDontForgetToOrderDisplayed();
    }
    public static boolean isMoreFromThisBrandDisplayed(){
        return customersPage.isMoreFromThisBrandDisplayed();
    }
    public static void clickOnPrint(){
        customersPage.clickOnPrint();
    }
    public static boolean isPrintFriendlyPopupDisplayed(){
        return customersPage.isPrintFriendlyPopupDisplayed();
    }
    public static void printOrderGuide(){
        customersPage.clickOnDownloadOrderGuide();
    }
    public static void searchItemOnOrderGuide(String item) throws InterruptedException {
        if (customersPage.isPreviousDraftOrderNoDisplayed()){
            customersPage.clickPreviousDraftOrderNo();
        }
        customersPage.typeToSearchOnOrderGuide(item);
    }
    public static void goToCreatePopup() throws InterruptedException {
        customersPage.clickOnCreate();
    }
    public static void createOrderGuide(String orderGuideName) throws InterruptedException {
        customersPage.typeOrderGuideName(orderGuideName);
        customersPage.clickSubmitOrderGuide();
    }
    public static void createOrderFromCatalog(){
        customersPage.clickOnAddFromCatalog();
    }
    public static void addItemFromCatalog(){
        customersPage.clickOnAddToOrderGuide();
    }
    public static void closeEditorCatalog(){
        customersPage.clickOnCloseEditorCatalog();
    }
    public static void createOrderByUploading(){
        customersPage.clickUploadAList();
    }
    public static void uploadFile(String path){
        customersPage.giveFilePath(path);
        customersPage.clickNext();
        customersPage.clickConfirm();
    }
    public static boolean isOrderGuideCreateSuccessPopupDisplayed(){
        return customersPage.isOrderGuideCreateSuccessPopupDisplayed();
    }
    public static void clickOK(){
        customersPage.clickOK();
    }
    public static void closeEditor(){
        customersPage.closeEditor();
    }
    public static void removeItemFromCatalog(){
        customersPage.clickOnRemoveFromOrderGuide();
    }
    public static void clickCompanyDropdown(){
        customersPage.clickCompanyDropdown();
    }
    public static boolean isCompanyDropdownTextDisplayed(){
        return customersPage.isCompanyDropdownTextDisplayed();
    }
    public static void goToEdit(){
        customersPage.clickOnEdit();
    }
    public static boolean isEditOrderGuideTextDisplayed(){
        return customersPage.isEditOrderGuideTextDisplayed();
    }
    public static void expandMoreOptionsDropdown(){
        customersPage.clickOnMoreOptions();
    }
    public static void exportOrderGuide(){
        customersPage.clickOnExportOrderGuide();
    }
    public static void importOrderGuide(){
        customersPage.clickOnImportOrderGuide();
    }
    public static void uploadToOrder(){
        customersPage.clickOnUploadToOrder();
    }
    public static boolean isReviewOrderTextDisplayed(){
        return customersPage.isReviewOrderTextDisplayed();
    }
    public static boolean isOrderGuideUpdatedTextDisplayed(){
        return customersPage.isOrderGuideUpdatedTextDisplayed();
    }
    public static void clickSortOptionsDropdown(){
        customersPage.selectSortOptions();
    }
    public static void selectItemCategoriesSort(){
        customersPage.selectItemCategories();
    }
    public static void selectAlphabeticalSort(){
        customersPage.selectAlphabetical();
    }
    public static void selectCustomOrderSort(){
        customersPage.selectCustomOrder();
    }
    public static void selectLastOrderedSort(){
        customersPage.selectLastOrdered();
    }
    public static boolean isProduceTextDisplayed(){
        return customersPage.isProduceTextDisplayed();
    }
    public static boolean isFirstAlphabeticalItemDisplayed(){
        return customersPage.isFirstAlphabeticalItemDisplayed();
    }
    public static boolean isFirstCustomItemDisplayed(){
        return customersPage.isFirstCustomItemDisplayed();
    }
    public static boolean isMinOrderBannerDisplayed(){
        return customersPage.isMinOrderBannerDisplayed();
    }
    public static boolean isOrderMinPopupDisplayed(){
        return customersPage.isOrderMinPopupDisplayed();
    }
    public static void clickOnCustomerCode(String code){
        customersPage.clickOnCustomerCode(code);
    }
    public static void clickOnOrdersTab(){
        customersPage.clickOnOrdersTab();
    }
    public static boolean isStandingOrdersDisplayed(){
        return customersPage.isStandingOrdersDisplayed();
    }
    public static void clickOnCreateStandingOrder(){
        customersPage.clickOnCreateStandingOrder();
    }
    public static void selectDeliveryDate(String day){
        customersPage.clickOnRemoveDelivery();
        customersPage.clickOnDropdownDelivery();
        customersPage.clickOnDeliveryDate(day);
    }
    public static void setStandingOrder(){
        customersPage.setStandingOrder();
    }
    public static boolean isStandingOrderEmailPopupDisplayed(){
        return customersPage.isStandingOrderEmailPopupDisplayed();
    }
    public static void selectEmail(){
        customersPage.clickOnDropdownEmail();
        customersPage.clickOnEmail();
    }
    public static void scheduleStandingOrder(){
        customersPage.scheduleStandingOrder();
    }
    public static boolean isStandingOrderSuccessPopupDisplayed(){
        return customersPage.isStandingOrderSuccessPopupDisplayed();
    }
    public static void clickOnEditStandingOrder(){
        customersPage.clickOnEditStandingOrder();
    }
    public static void clickOnAddAnotherStandingOrder(){
        customersPage.clickOnAddAnotherStandingOrder();
    }
    public static void clickOnDeleteStandingOrders(){
        while (customersPage.areStandingOrdersDeleted()){
            customersPage.clickOnDeleteStandingOrders();
        }
    }
    public static boolean areStandingOrdersDeleted(){
        return customersPage.areStandingOrdersDeleted();
    }
    public static void increaseFirstRowQtyByOneInDist() throws InterruptedException {
        customersPage.clickPlusQryFirstRowInDist();
    }
    public static void checkoutItemsDist() throws InterruptedException {
        customersPage.clickOnCheckoutButtonInDist();
    }
    public static boolean isMultiDistCentersDisplayed(){
        return customersPage.isMultiDistCentersDisplayed();
    }
    public static int getOrderCount(int num){
        return customersPage.getOrderCount(num);
    }
    public static void clickOnBack(){
        customersPage.clickOnBack();
    }
    public static void SelectCustomer(String code){
        customersPage.ClickOnCustomer(code);
    }
    public static boolean isOrdersTabDisplayed(){
        return customersPage.isOrdersTabDisplayed();
    }
    public static void navigateToOrdersPage(){
        customersPage.clickOnOrdersTab();
    }
    public static boolean isOrderIdTxtDisplayed(){
        return customersPage.isOrderIdTxtDisplayed();
    }
    public static void OrderDateSort(){
        customersPage.ClickOrderDateToSort();
    }
    public static void DeliveryDateSort(){
        customersPage.ClickDeliveryDateSort();
    }
    public static boolean isOrderDateSorted(){
        return customersPage.OrderDateSort();
    }
    public static boolean isDeliveryDateSorted(){
        return customersPage.DeliveryDateSort();
    }
    public static boolean isDiscountDisclaimerOrderDetailsMsgDisplayed(){
        return customersPage.isDiscountDisclaimerOrderDetailsMsgDisplayed();
    }
    public static boolean isDiscountDisclaimerOrderReviewMsgDisplayed(){
        return customersPage.isDiscountDisclaimerOrderReviewMsgDisplayed();
    }
    public static void clickFirstOrderFrmOrderTab(){
        customersPage.clickFirstOrderFrmOrderTab();
    }
    public static void clickSouthwestTraders() throws InterruptedException {
        customersPage.clickSouthwestTraders();
    }
    public static boolean isSubstitutesPopupDisplayed(){
        return customersPage.isSubstitutesPopupDisplayed();
    }
    public static void clickDoNotSubstitute(){
        customersPage.clickDoNotSubstitute();
    }
    public static void clickSaveSelection(){
        customersPage.clickSaveSelection();
    }
    public static boolean isReplacementDisplayed(){
        return customersPage.isReplacementDisplayed();
    }
    public static void clickOnItem(String code){
        customersPage.clickOnItem(code);
    }
    public static void increaseFirstRowQtyByOneInCheckout(){
        customersPage.clickPlusQryFirstRowInCheckout();
    }
    public static void decreaseFirstRowQtyByOneInCheckout(){
        customersPage.clickMinusQryFirstRowInCheckout();
    }
    public static int getSubstituteItemsCount(int num){
        return customersPage.getSubstituteItemsCount(num);
    }

    public static boolean isNavigatedToCustomerPage(){
        return customersPage.isCutomerTxtDisplayed();
    }

    public static void SelectTestAutomationOrderGuide(){
        customersPage.ClickTestAutomationOrderGuide();
    }

    public static boolean isStockCountDisplayed(){
        return customersPage.StockCountDisplayed();
    }
    public static boolean areOutOfStockItemsDisplayed(){
        return customersPage.areOutOfStockItemsDisplayed();
    }
    public static void clickOnUnitDEach(){
        customersPage.clickOnUnitDEach();
    }
    public static void clickOnUnitCase(){
        customersPage.clickOnUnitCase();
    }
    public static void clickOnOrderGuideInProfile(){
        customersPage.clickOnOrderGuideInProf();
    }
    public static String getUnitType(){
        return customersPage.getUnitType();
    }
}
