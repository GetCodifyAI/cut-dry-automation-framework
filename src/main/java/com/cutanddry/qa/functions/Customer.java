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
    public static boolean isCustomersTextDisplayed(){
        return customersPage.isCustomersTextDisplayed();
    }
    public static void clickSalespersonDropDown(){customersPage.clickSalespersonDropDown();}
    public static void clickSalespersonOption(){customersPage.clickSalespersonOption();}
    public static boolean isSalespersonNameDisplayed(String salesperson){
        return customersPage.isSalespersonNameDisplayed(salesperson);
    }
    public static void clickManageCustomers(){customersPage.clickManageCustomers();}
    public static void clickExportCustomers(){customersPage.clickExportCustomers();}
    public static boolean isExportCustomersPopUpDisplayed(){
        return customersPage.isExportCustomersPopUpDisplayed();
    }
    public static void clickConfirm(){customersPage.clickConfirm();}
    public static boolean isGeneratingReportPopUpDisplayed(){
        return customersPage.isGeneratingReportPopUpDisplayed();
    }
    public static void clickExportOrderGuides(){customersPage.clickExportOrderGuides();}
    public static boolean isExportOrderGuidesPopUpDisplayed(){
        return customersPage.isExportOrderGuidesPopUpDisplayed();
    }
    public static void clickMoreFilters(){customersPage.clickMoreFilters();}
    public static boolean isFilterCustomersPopUpDisplayed(){
        return customersPage.isFilterCustomersPopUpDisplayed();
    }
    public static void clickSignUpStatus(){customersPage.clickSignUpStatus();}
    public static void clickSignUpOption(){customersPage.clickSignUpOption();}
    public static void clickApply(){customersPage.clickApply();}
    public static boolean isStatusDisplayed(String status){
        return customersPage.isStatusDisplayed(status);
    }
    public static void clickAddNewCustomer(){customersPage.clickAddNewCustomer();}
    public static boolean isAddNewCustomerPopUpDisplayed(){
        return customersPage.isAddNewCustomerPopUpDisplayed();
    }
    public static void typeCustomerName(String customerName)throws InterruptedException{
        customersPage.typeCustomerName(customerName);
    }
    public static void clickContinue(){customersPage.clickContinue();}
    public static void typeCustomerCity(String customerCity)throws InterruptedException{
        customersPage.typeCustomerCity(customerCity);
    }
    public static void clickCreateCustomer(){customersPage.clickCreateCustomer();}
    public static boolean isCreatedCustomerPopUpDisplayed(){
        return customersPage.isCreatedCustomerPopUpDisplayed();
    }
    public static void clickClosePopUp(){customersPage.clickClosePopUp();}
    public static void searchCustomerByName(String customerName) throws InterruptedException {
        customersPage.clickOnSearchCustomers();
        customersPage.typeOnSearchCustomerName(customerName);
    }
    public static boolean isNewCustomerDisplayed(String customerName)throws InterruptedException{
        return customersPage.isNewCustomerDisplayed(customerName);
    }
    public static void selectCustomer(){customersPage.selectCustomer();}
    public static void clickBulkActions(){customersPage.clickBulkActions();}
    public static void clickInviteUser(){customersPage.clickInviteUser();}
    public static boolean isInviteUserPopUpDisplayed(){
        return customersPage.isInviteUserPopUpDisplayed();
    }
    public static void clickOptionAll(){customersPage.clickOptionAll();}
    public static void clickInviteEmail(){customersPage.clickInviteEmail();}
    public static boolean isSuccessPopUpDisplayed(){
        return customersPage.isStandingOrderSuccessPopupDisplayed();
    }
    public static boolean isCustomerProfileDisplayed(){
        return customersPage.isCustomerProfileDisplayed();
    }
    public static void clickMoreOption(){customersPage.clickMoreOption();}
    public static void clickEditOrderGuide(){customersPage.clickEditOrderGuide();}
    public static void clickNo()throws InterruptedException{customersPage.clickNo();}
    public static boolean isCustomerOrderGuideDisplayed(){
        return customersPage.isCustomerOrderGuideDisplayed();
    }
    public static void clickPreviewCatalog(){customersPage.clickPreviewCatalog();}
    public static boolean isCatalogPreviewSectionDisplayed(){
        return customersPage.isCatalogPreviewSectionDisplayed();
    }
    public static void clickChat(){customersPage.clickChat();}
    public static boolean isChatSectionDisplayed(){
        return customersPage.isChatSectionDisplayed();
    }
    public static void clickPause(){customersPage.clickPause();}
    public static boolean isStandingOrdersPaused(){
        return customersPage.isStandingOrdersPaused();
    }
    public static void clickResume(){customersPage.clickResume();}
    public static void clickPrintReceipt() throws InterruptedException{
        customersPage.clickThreeDot();
        customersPage.clickPrintReceipt();
    }
    public static void clickOrderConfirmation() throws InterruptedException{
        customersPage.clickThreeDot();
        customersPage.clickOrderConfirmation();
    }
    public static void clickOrder(){customersPage.clickOrder();}
    public static boolean isOrderSectionDisplayed(){
        return customersPage.isOrderSectionDisplayed();
    }
    public static void clickDraftsTab(){customersPage.clickDraftsTab();}
    public static boolean isOrderDraftDisplayed(){
        return customersPage.isOrderDraftDisplayed();
    }
    public static void clickDeleteDraft(){customersPage.clickDeleteDraft();}


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
    public static void clickOGDropdown(){
        customersPage.clickOGDropdown();
    }
    public static void selectTestOrderGuide1(){
        customersPage.selectTestOrderGuide1();
    }
    public static void selectTestAutomation(){
        customersPage.selectTestAutomation();
    }
    public static void editMargin(){
        customersPage.editMargin();
    }
    public static void resetMarginValues() throws InterruptedException {
        customersPage.resetMarginValues();
    }
    public static void updateMarginValues() throws InterruptedException {
        customersPage.updateMarginValues();
    }
    public static void enterMarginValue(String val) throws InterruptedException {
        customersPage.enterMarginValue(val);
    }
    public static boolean isMarginValuePopupDisplayed(){
        return customersPage.isMarginValuePopupDisplayed();
    }
    public static void increaseFirstRowQtyBysix() throws InterruptedException {
        customersPage.clickPlusQryFirstRowBySix();
    }

    public static void selectOneCustomer(){customersPage.clickOneCustomer();}
    public static boolean isCustomerNameTxtDisplayed(){
        return customersPage.isCustomerNameDisplayed();
    }
    public static void clickOnCustomerNameEdit(){customersPage.clickCustomerNameEditIcon();}


    public static void editCustomerProfileName(String editCustomerName)throws InterruptedException {
        customersPage.editCustomerName(editCustomerName);
    }


    public static void clickOnSaveEditedCustomerName(){customersPage.clickEditCusNameSave();}
    public static void clickOnShippingAddressEdit(){customersPage.clickShippingAddressEditIcon();}


    public static boolean isEditShippingAddressTextDisplayed(){
        return customersPage.isEditShippingAddressTextDisplayed();
    }


    public static void editShipAddressStreetName(String editStreet)throws InterruptedException{
        customersPage.editStreetName(editStreet);
    }


    public static void editShipAddressCityName(String editCity)throws InterruptedException{
        customersPage.editCityName(editCity);
    }


    public static void editShipAddressStateName(String editState)throws InterruptedException{
        customersPage.editStateName(editState);
    }


    public static void editShipAddressZipCode(String editZipNum)throws InterruptedException{
        customersPage.editZipCode(editZipNum);
    }


    public static void clickOnSaveChangesEditShipAddress(){customersPage.clickEditShipAddressDetailsSave();}


    public static void clickOnAddNoteEditIcon(){customersPage.clickEditNoteIcon();}




    public static void addCustomerNote(String addNote)throws InterruptedException{
        customersPage.addNote(addNote);
    }


    public static void clickOnSaveChangesAddNote(){customersPage.clickAddNoteSave();}


    public static void editCustomerNote(String editCusNote)throws InterruptedException{
        customersPage.editNote(editCusNote);
    }


    public static void clickOnEditSalespersonIcon(){customersPage.clickEditSalespersonIcon();}


    public static boolean isAssignSalespersonTextDisplayed(){
        return customersPage.isAssignSalespersonsTextDisplayed();
    }


    public static void clickOnAssignSalespersonDropdown(){customersPage.clickAssignSalespersonDropdownArrow();}
    public static void selectOneAssignSalesperson(){customersPage.clickOneSalespersonOption();}


    public static boolean isAssignedSalespersonNameDisplayed(){
        return customersPage.isAssignedSalespersonDisplayed();
    }


    public static void clickOnAssignSalespersonSave(){customersPage.clickSalespersonSaveChanges();}


    public static boolean isAddedSalespersonNameDisplayed(){
        return customersPage.isAddedSalespersonDisplayed();
    }


    public static void clickAssignedSalespersonRemove(){customersPage.clickAssignedSalespersonRemoveIcon();}


    public static boolean isAddedSalespersonNameDeleted(){
        return customersPage.isAssignedSalespersonDeleted();
    }


    public static void placeAnOrder(){customersPage.placeNewOrder();}


    public static boolean isTotalOrderValueUpdated(){
        return customersPage.isOrderValueUpdated();
    }


    public static void clickOrderSubmissionOption(){customersPage.clickSubmitOrder();}
    public static void clickOrderDuplicateConfirmation(){customersPage.clickDuplicateOrder();}


    public static boolean isSuccessOrderMessageDisplayed(){
        return customersPage.isOrderSubmissionTextDisplayed();
    }
    public static void clickOrderSuccessMessageClose(){customersPage.clickCloseSuccessMsg();}


    public static void clickOnAddTagDropdownMenu(){customersPage.clickAddTagsDropdown();}


    public static boolean isDropdownMenuDisplayed(){
        return customersPage.isDropdownListDisplayed();
    }


    public static void selectToAddTagOption(){customersPage.selectTagOption();}


    public static boolean isAddedTagNameDisplayed(){
        return customersPage.isAddedTagDisplayed();
    }


    public static void clickRemoveAddedTag(){customersPage.clickRemoveTagOption();}


    public static boolean isAddedTagNameDeleted(){
        return customersPage.isAddedTagDeleted();
    }


    public static boolean isLastOrderDateToday(){
        return customersPage.isLastOrderDateUpdated();
    }


    public static boolean isCustomerListTextDisplayed(){
        return customersPage.isCustomersTextDisplayed();
    }


    public static void clickCusAccountVisibilityOption(){customersPage.clickEditVisibilityIcon();}
    public static void clickCusAccountVisibilityDropdown(){customersPage.clickVisibilityDropdown();}


    public static void selectCusAccountHiddenOption(){
        customersPage.selectHiddenOption();
    }


    public static boolean isAccountHiddenOptionDisplayed(){
        return customersPage.isHiddenOptionDisplayed();
    }


    public static void logIntoCustomer()throws InterruptedException{
        customersPage.loginAsCustomerPortal();
    }


    public static void loginAsCustomer(String email)throws InterruptedException{
        customersPage.loginAsCus(email);
    }


    public static void clickCustomerPortalOrderIcon()throws InterruptedException{customersPage.clickOrderIcon();}


    public static boolean isVisibleAddSupplierButton(){
        return customersPage.isAddSupplierButtonVisible();
    }


    public static void clickCusAccountStatusOption(){customersPage.clickEditStatusIcon();}
    public static void clickCusAccountStatusDropdown(){customersPage.clickStatusDropdown();}


    public static boolean isAccountStatusDropdownMenuDisplayed(){
        return customersPage.isStatusDropdownListDisplayed();
    }


    public static void selectCusAccountStatusOption(){customersPage.selectStatusOption();}


    public static boolean isAccountStatusTextDisplayed(){
        return customersPage.isActiveStatusDisplayed();
    }


    public static void clickSection()throws InterruptedException{customersPage.clickNewArrivals();}
    public static void clickCategory()throws InterruptedException{customersPage.clickAllItems();}
    public static void clickBrand()throws InterruptedException{customersPage.clickBrand();}
    public static boolean isFilteredBrandDisplayed(){
        return customersPage.isFilteredBrandDisplayed();
    }
    public static void clickItemStatus()throws InterruptedException{customersPage.clickItemStatus();}
    public static void clickStorageType()throws InterruptedException{customersPage.clickStorageType();}
    public static void clickDietType()throws InterruptedException{customersPage.clickDietType();}
    public static boolean isFilterItemDisplayed(){
        return customersPage.isFilterItemDisplayed();
    }
    public static void clickProcessingFormulation()throws InterruptedException{customersPage.clickProcessingType();}
    public static boolean isFilterProcessingTypeWork(){
        return customersPage.isFilterProcessingTypeWork();
    }
    public static void clickClearAllFilters(){customersPage.clickClearAllFilters();}
    public static void clickViewCatalogAsCustomer(){customersPage.clickRadioButton();}
    public static void clickOnProduct(String name){
        customersPage.clickOnProduct(name);
    }
    public static void clickAddToCartPDP(){customersPage.clickAddToCart();}
    public static void clickCheckOutPDP(){customersPage.clickCheckOutPDP();}
    public static boolean isOrderSubmitSuccessfully(){
        return customersPage.isOrderSubmitSuccessfully();}
    public static void clickOrderGuide(){customersPage.clickOrderGuide();}
    public static void goToOrderGuide()throws InterruptedException{
        customersPage.clickOnBack();
        customersPage.clickOrderGuideTab();
    }
    public static boolean addedItemDisplayOnOrderGuide(String name){
         return customersPage.addedItemDisplayOnOrderGuide(name);
    }
    public static void clickOrderGuideProduct(String name){
        customersPage.clickOrderGuideProduct(name);
    }
    public static void clickRemoveOrderGuide(){customersPage.clickRemoveOrderGuide();}
    public static void clickExportPDP(){customersPage.clickExportPDP();}
    public static void clickRightArrow(){customersPage.clickRightArrow();}
    public static boolean isNextImageDisplay(){
        return customersPage.isNextImageDisplay();
    }
    public static void clickLeftArrow(){customersPage.clickLeftArrow();}
    public static boolean isPreviousImageDisplay(){
        return customersPage.isPreviousImageDisplay();
    }
    public static void clickFirstImage(){customersPage.clickFirstImage();}
    public static void clickSecondImage(){customersPage.clickSecondImage();}

}
