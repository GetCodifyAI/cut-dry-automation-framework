package com.cutanddry.qa.functions;

import com.cutanddry.qa.pages.CustomersPage;
import com.cutanddry.qa.pages.OrdersPage;

import java.util.Objects;

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
    public static void addItemToCartCatalog(String ItemName) throws InterruptedException {
        customersPage.clickAddToCartCatalog(ItemName);
    }
    public static String getItemQtyFirstRow(){
        return customersPage.getItemQtyFirstRow();
    }
    public static double getItemPriceFirstRow(){
        return customersPage.getItemPriceFirstRow();
    }
    public static String getItemPriceSecondRow(){
        return customersPage.getItemPriceSecondRow();
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
    }
    public static boolean isThankingForOrderPopupDisplayed(){
        return customersPage.isThankingForOrderPopupDisplayed();
    }
    public static boolean isBroadcastMessageDisplayed(String message) {
        return customersPage.isBroadcastMessageDisplayed(message);
    }
    public static void clickMessage(String message) {
        customersPage.clickMessage(message);
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
    public static void createOrderFromCatalog() throws InterruptedException {
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
    public static void closeEditor() throws InterruptedException {
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

    public static void removeStandingOrdersIfAvailable(){
        if(customersPage.isAlreadySetStandingOrdersAvailable()) {
            int standingOrdersCount = customersPage.getStandingOrdersCount();
            for (int i = 0; i < standingOrdersCount; i++) {
                customersPage.clickOnDeleteStandingOrders();
            }
        }
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
    public static void clickOnBack() throws InterruptedException {
        customersPage.clickOnBack();
        if (customersPage.isPreviousDraftOrderNoDisplayed()){
            customersPage.clickPreviousDraftOrderNo();
        }
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
    public static boolean isCustomerGroupOptionAvailable(){
        return customersPage.isCustomerGroupTxtDisplayed();
    }
    public static boolean areOutOfStockItemsDisplayed(){
        return customersPage.areOutOfStockItemsDisplayed();
    }
    public static void clickOnUnitEach(){
        customersPage.clickOnUnitEach();
    }
    public static void clickOnUnitCase(){
        customersPage.clickOnUnitCase();
    }
    public static void clickOnOrderGuideInProfile() throws InterruptedException {
        customersPage.clickOnOrderGuideInProf();
        if (customersPage.isPreviousDraftOrderNoDisplayed()){
            customersPage.clickPreviousDraftOrderNo();
        }
    }
    public static String getUnitType(){
        return customersPage.getUnitType();
    }
    public static void clickClose(){
        customersPage.clickClose();
    }
    public static void clickOnDeleteItem(){
        customersPage.clickOnDeleteItem();
    }
    public static boolean isItemDisplayed(String item) throws InterruptedException {
        return Objects.equals(customersPage.getItemNameFirstRow(), item);
    }
    public static void clickOnBoostTab(){
        customersPage.clickOnBoostTab();
    }
    public static void clickOnTrackTab() throws InterruptedException {
        customersPage.clickOnTrackTab();
    }
    public static boolean isBroadcastTextDisplayed(){
        return customersPage.isBroadcastTextDisplayed();
    }
    public static boolean isProfileTextDisplayed(){
        return customersPage.isProfileTextDisplayed();
    }
    public static void clickOnEditMessage(){
        customersPage.clickOnEditMessage();
    }
    public static void clickOnClearMessage() throws InterruptedException {
        customersPage.clickOnClearMessage();
    }
    public static void clickOnAddItems(){
        customersPage.clickOnAddItems();
    }
    public static void clickOnSaveMessage() throws InterruptedException {
        customersPage.clickOnSaveMessage();
    }
    public static void typeBroadcastMessage(String msg){
        customersPage.typeBroadcastMessage(msg);
    }
    public static void selectItem(String code){
        customersPage.selectItem(code);
    }
    public static void clickOnAdd(){
        customersPage.clickOnAdd();
    }
    public static boolean isItemAdded(String code){
        return customersPage.isItemAdded(code);
    }
    public static void clickOnRemoveItem(String Itemcode){
        customersPage.clickOnRemoveItem(Itemcode);
    }
    public static void addSection(){
        customersPage.addSection();
    }
    public static boolean isAddSectionPopupDisplayed(){
        return customersPage.isAddSectionPopupDisplayed();
    }
    public static void clickOnSave(){
        customersPage.clickOnSave();
    }
    public static void typeSectionName(String name) throws InterruptedException {
        customersPage.typeSectionName(name);
    }
    public static boolean isAddedSectionDisplayed(String name) throws InterruptedException {
        return customersPage.isAddedSectionDisplayed(name);
    }
    public static void dragToTop() {
        customersPage.dragToTop();
    }
    public static boolean isSectionDisplayed(String name){
        return customersPage.isSectionDisplayed(name);
    }
    public static void editSection(String name){
        customersPage.clickOnEditSection(name);
    }
    public static boolean isEditSectionPopupDisplayed(){
        return customersPage.isEditSectionPopupDisplayed();
    }
    public static void clickOnDelete(){
        customersPage.clickOnDelete();
    }
    public static void clickOnYes(){
        customersPage.clickOnYes();
    }
    public static boolean isAreYouSurePopupDisplayed(){
        return customersPage.isAreYouSurePopupDisplayed();
    }

    public static boolean isCustomerGroupEditBtnAvailable(){
        return customersPage.isCustomerGroupEditBtnDisplayed();
    }
    public static void editCustomerGroups(){
        customersPage.editCustomerGroup();
    }
    public static void creatCustomerGroup(String groupname){
        customersPage.sendTextToCustomerGroup(groupname);
    }
    public static void customerGroupSave(){
        customersPage.clickCustomerGroupSaveBtn();
    }
    public static boolean isCustomerGroupNameDisplayed(String groupname){
        return customersPage.customerGroupNameDisplayed(groupname);
    }
    public static void clearAllCustomerGroups(){
        customersPage.clickClearAllCustomerGroupsBtn();
    }
    public static void InviteNewUsers(){
        customersPage.clickInviteNewUsers();
    }
    public static boolean AddUserOverlayDisplayed(){
        return customersPage.isAddUserOverlayDisplayed();
    }
    public static void FillNameInAddUserOverlay(String username){
        customersPage.sendTextToAddUserOverlayNameField(username);
    }
    public static void FillEmailInAddUserOverlay(String useremail){
        customersPage.sendTextToAddUserOverlayEmailField(useremail);
    }
    public static void SaveChangesWithoutSendingInvite(){
        customersPage.clickSaveChangesWithoutSendingInvite();
    }
    public static boolean UserDetailsSuccessfullyUpdatedMsgDisplayed(){
        return customersPage.isSuccessfullyUpdatedMsgDisplayed();
    }
    public static boolean UserSuccessfullyRemovedMsgDisplayed(){
        return customersPage.isSuccessfullyRemovedMsgDisplayed();
    }
    public static void CloseSuccessOverlayByOkBtn(){
        customersPage.clickOK();
    }
    public static boolean IsAddedUserSuccessfullyDisplayed(String username){
        return customersPage.isAddedUserDisplayed(username);
    }
    public static boolean RemovedUserNotDisplayed(String username){
        return customersPage.isRemovedUserNotDisplayed(username);
    }
    public static void EditUserDetails(String username){
        customersPage.clickOnUserDetailsEditBtn(username);
    }
    public static void RemoveUser(){
        customersPage.clickOnRemoveUser();
    }
    public static boolean RemovalConfirmationOverlayDisplayed(){
        return customersPage.isRemovalConfirmationOverlayDisplayed();
    }
    public static void ClickRemovalConfirmationOverlayYesBtn(){
        customersPage.ClickYesOnRemovalConfirmationOverlay();
    }
    public static void clickOnUnitPkg(){
        customersPage.clickOnUnitPkg();
    }
    public static void clickOnHideItem(){
        customersPage.clickOnHideItem();
    }
    public static boolean isEditItemPopupDisplayed(){
        return customersPage.isEditItemPopupDisplayed();
    }
    public static void clickOnSaveAndUnhide(){
        customersPage.clickOnSaveAndUnhide();
    }
    public static void selectActiveAndHiddenItems(){
        customersPage.selectActiveAndHiddenItems();
    }
    public static void editItem(String name){
        customersPage.clickOnEditItem(name);
    }
    public static String getItemPricePDPView(){
        return customersPage.getItemPricePDPView();
    }
    public static boolean isCatalogImageDisplayed(){
        return customersPage.isCatalogImageDisplayed();
    }
    public static void clickOnCatalogItem(String name){
        customersPage.clickOnCatalogItem(name);
    }
    public static String getItemNamePDPView() throws InterruptedException {
        return customersPage.getItemNamePDPView();
    }
    public static boolean isNavigatedToOrderGuide(){
        return customersPage.isOrdersTxtDisplayed();
    }
    public static boolean isNavigatedToCatalog(){
        return customersPage.isAllItemsTxtDisplayed();
    }
    public static String getItemDetailsFirstRow() throws InterruptedException {
        return customersPage.getItemDetailsFirstRow();
    }
    public static int getCountZeroPriceItemsDisplayed() throws InterruptedException {
        return customersPage.getCountZeroPriceItemsDisplayed();
    }

    public static void submitOrderWithoutReachMinimum(){
        customersPage.submitOrder();
        if(customersPage.isOrderMinimumOverlayDisplayed()){
            customersPage.clickOnYes();
        }
        if (customersPage.isDuplicatePopupDisplayed()){
            customersPage.clickYesDuplicatePopup();
        }
    }

    public static boolean catalogAccessEnabled(){
        return customersPage.isCatalogAccessEnabled();
    }

    public static boolean catalogAccessDisabled(){
        return customersPage.isCatalogAccessDisabled();
    }

    public static boolean catalogSectionsDisplayed(){
        return customersPage.isCatalogSectionDisplayInOrderGuide();
    }

    public static void disableCatalogAccess(){
        customersPage.clickEditCatalogAccess();
        customersPage.clickOnDisableCatalogAccessOption();
        customersPage.saveCatalogAccessChanges();
    }

    public static void enableCatalogAccess(){
        customersPage.clickEditCatalogAccess();
        customersPage.clickOnEnableCatalogAccessOption();
        customersPage.saveCatalogAccessChanges();
    }

    public static boolean orderApprovalTxtDisplayed(){
        return customersPage.isOrderApprovalOptionDisplayed();
    }

    public static void orderApprovalEdit(){
        customersPage.clickOnOrderApprovalEditBtn();
    }

    public static boolean orderApprovalSettingsOverlayDisplayed(){
        return customersPage.isOrderApprovalSettingsOverlayDisplayed();
    }

    public static boolean NewlyCreatedOrderGuideApprovalStatusDisplayed(){
        return customersPage.isNewlyCreatedOrderGuideApprovalStatusDisplayed();
    }

    public static boolean existingOrderGuideDisplayed(String orderGuideName){
        return customersPage.isExistingOrderGuidesDisplayed(orderGuideName);
    }

    public static void closeOrderApprovalSettingsOverlay(){
        customersPage.clickCloseOnOrderApprovalSettingsOverlay();
    }

    public static void orderApprovalTurnOnForTheOrderGuide(String OrderGuideName){
        customersPage.clickTurnOnOrderApprovalForOrderGuide(OrderGuideName);
    }

    public static void orderApprovalTurnOffForTheOrderGuide(String OrderGuideName){
        customersPage.clickTurnOffOrderApprovalForOrderGuide(OrderGuideName);
    }

    public static void saveOrderApprovalSettings(){
        customersPage.clickOnSave();
    }

    public static boolean isSalespersonEditable(){
        return customersPage.isSalespersonEditable();
    }
    public static boolean isAbleToInviteUsers(){
        Orders.closeRatingOverlay();
        return customersPage.isAbleToInviteUsers();
    }
    public static boolean isAbleToEditMsg(){
        return customersPage.isAbleToEditMsg();
    }
    public static void clickOnIndependentFoods(){
        customersPage.clickOnIndependentFoods();
    }

    public static void addItemFromCatalogIfNotAvailableInOG(String itemCode){
        customersPage.clickItemFromCatalogIfNotAvailableInOG(itemCode);
    }

    public static void clickOnEditAccHolds(){
        customersPage.clickOnEditAccHolds();
    }
    public static void clickOnAccDropdown(){
        customersPage.clickOnAccDropdown();
    }
    public static void clickOnHardHold(){
        customersPage.clickOnHardHold();
    }
    public static boolean isHardHoldSelected(){
        return customersPage.isHardHoldSelected();
    }
    public static boolean isHardHoldPopupDisplayed(){
        return customersPage.isHardHoldPopupDisplayed();
    }
    public static void clickOnNone(){
        customersPage.clickOnNone();
    }
    public static boolean isNoneSelected(){
        return customersPage.isNoneSelected();
    }
    public static boolean isRemoveHoldPopupDisplayed(){
        return customersPage.isRemoveHoldPopupDisplayed();
    }

    public static void ifHasHoldsRemoveHoldsFromCustomer(){
        if(customersPage.isCustomerOnHold()){
            customersPage.clickOnEditAccHolds();
            customersPage.clickOnAccDropdown();
            customersPage.clickOnNone();
            customersPage.clickOnSave();
            customersPage.clickOnYes();
        }
    }
    public static void selectPickUpWillCall(){
        customersPage.selectPickUpWillCall();
    }
    public static void selectMailDelivery(){
        customersPage.selectMailDelivery();
    }
    public static void enterStopDuration(String num) throws InterruptedException {
        customersPage.enterStopDuration(num);
    }
    public static void enterDeliveryNotes(String name) throws InterruptedException {
        customersPage.enterDeliveryNotes(name);
    }
    public static void enterDoorDesc(String name) throws InterruptedException {
        customersPage.enterDoorDesc(name);
    }
    public static void enterKeyDropNum(String num) throws InterruptedException {
        customersPage.enterKeyDropNum(num);
    }
    public static String isStopDurationUpdated() throws InterruptedException {
        return customersPage.isStopDurationUpdated();
    }
}
