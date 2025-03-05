package com.cutanddry.qa.functions;

import com.cutanddry.qa.pages.CustomersPage;
import com.cutanddry.qa.pages.DashboardPage;
import com.cutanddry.qa.pages.SettingsPage;

import java.util.Objects;

import static com.cutanddry.qa.functions.Boost.boostPage;

public class Customer {
    static CustomersPage customersPage = new CustomersPage();
    static DashboardPage dashboardPage = new DashboardPage();
    static SettingsPage settingsPage = new SettingsPage();

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
    public static void decreaseFirstRowQtyByThree(int count){
//        customersPage.clickMinusQryFirstRow();
//        customersPage.clickMinusQryFirstRow();
//        customersPage.clickMinusQryFirstRow();
        for (int i = 0; i<count; i++){
            customersPage.clickMinusQryFirstRow();
        }
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
        if (customersPage.isOrderMiniumErrorBannerDisplayed()){
            dashboardPage.clickOnOrderSettings();
            settingsPage.selectOnOrderMinimums();
            settingsPage.clickOnSaveChanges();
            customersPage.clickOnBack();
        }
        if (customersPage.isPreviousDraftOrderNoDisplayed()){
            customersPage.clickPreviousDraftOrderNo();
        }
        if(customersPage.isSubstitutesPopupDisplayed()){
            customersPage.clickDoNotSubstitute();
        }
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
        if (customersPage.isOrderMiniumErrorBannerDisplayed()){
            dashboardPage.clickOnOrderSettings();
            settingsPage.selectOnOrderMinimums();
            try {
                settingsPage.clickOnSaveChanges();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            customersPage.clickOnBack();
        }
        if (customersPage.isDuplicatePopupDisplayed()){
            customersPage.clickYesDuplicatePopup();
        }
    }
    public static boolean isThankingForOrderPopupDisplayed(){
        return customersPage.isThankingForOrderPopupDisplayed();
    }
    public static boolean isBroadcastMessageDisplayed(String message) {
        try {
            if (customersPage.isPreviousDraftOrderNoDisplayed()){
                customersPage.clickPreviousDraftOrderNo();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
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
    public static void uploadFileOnly(String path){
        customersPage.giveFilePath(path);
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
        if(customersPage.isSubstitutesPopupDisplayed()){
            customersPage.clickDoNotSubstitute();
        }
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
    public static void selectDeliveryDateAsLast(){
        customersPage.clickOnRemoveDelivery();
        customersPage.clickOnDropdownDelivery();
        customersPage.clickOnDeliveryDateAsLast();
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
        if (customersPage.isPreviousDraftOrderNoDisplayed()){
            customersPage.clickPreviousDraftOrderNo();
        }
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
    public static boolean isReplacementNotDisplayed(){
        return customersPage.isReplacementNotDisplayed();
    }
    public static boolean isDoNotSubstituteDisplayed(){
        return customersPage.isDoNotSubstituteDisplayed();
    }
    public static void clickOnItem(String code){
        customersPage.clickOnItem(code);
    }
    public static void clickOnSingleItem(){
        customersPage.clickOnSingleItem();
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
    public static int getSubstituteItemsCount(){
        return customersPage.getSubstituteItemsCount();
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
    public static void clickOnAddItems(String code){
        if(customersPage.isExistItemDisplayed(code)){
            customersPage.clickOnRemoveItem(code);
        }
        customersPage.clickOnAddItems();
    }
    public static void clickOnSaveMessage() throws InterruptedException {
        customersPage.clickOnSaveMessage();
    }
    public static void typeBroadcastMessage(String msg){
        customersPage.typeBroadcastMessage(msg);
    }
    public static void selectItem(String code) throws InterruptedException {
        customersPage.selectItem(code);
    }
    public static void clickOnAdd(){
        customersPage.clickOnAdd();
    }
    public static boolean isItemAdded(String code){
        return customersPage.isItemAdded(code);
    }
    public static boolean isItemPercentageAdded(String code){
        return customersPage.isItemPercentageAdded(code);
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
    public static String getItemNamePDPView(String itemName) throws InterruptedException {
        return customersPage.getItemNamePDPView(itemName);
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
        if (!customersPage.isCatalogAccessEnableDisplayed()) {
            customersPage.clickEditCatalogAccess();
            customersPage.clickOnEnableCatalogAccessOption();
            customersPage.saveCatalogAccessChanges();
        }
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

    public static void disableAccHolds(){
        if (!customersPage.isAccountHoldsNoneDisplayed()) {
            customersPage.clickOnEditAccHolds();
            customersPage.clickOnAccDropdown();
            customersPage.clickOnNone();
            customersPage.clickOnSave();
            customersPage.clickOnYes();
        }
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
    public static void clickSignUpStatus() throws InterruptedException {customersPage.clickSignUpStatus();}
    public static void clickSignUpOption(){customersPage.clickSignUpOption();}
    public static void clickApply() throws InterruptedException {customersPage.clickApply();}
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
    public static void selectCustomer() throws InterruptedException {customersPage.selectCustomer();}
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

    public static String getBusinessNameFromCustomers(String CustomerCode){
        return customersPage.getBusinessName(CustomerCode);
    }
    public static boolean isCustomerProfileDisplayed(String CustomerName){
        return customersPage.isCustomerProfileDisplayed(CustomerName);
    }
    public static void clickMoreOption(){customersPage.clickMoreOption();}
    public static void clickEditOrderGuide(){
        customersPage.clickEditOrderGuide();
        try {
            if (customersPage.isPreviousDraftOrderNoDisplayed()){
                customersPage.clickPreviousDraftOrderNo();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
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
    public static void enterMarginPercentage(String val) throws InterruptedException {
        customersPage.enterMarginPercentage(val);
    }
    public static boolean isMarginValuePopupDisplayed(){
        return customersPage.isMarginValuePopupDisplayed();
    }
    public static void increaseFirstRowQtyBysix() throws InterruptedException {
        customersPage.clickPlusQryFirstRowBySix();
    }
    public static void clickPlusQryFifthRowBySix() throws InterruptedException {
        customersPage.clickPlusQryFifthRowBySix();
    }

    public static void selectOneCustomer(String cusCode)throws InterruptedException {
        customersPage.clickOneCustomer(cusCode);}

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
    public static void clickOrderSubmissionOption(){customersPage.clickSubmitOrder();}
    public static void clickOrderDuplicateConfirmation(){customersPage.clickDuplicateOrder();}


    public static boolean isSuccessOrderMessageDisplayed(){
        return customersPage.isOrderSubmissionTextDisplayed();
    }
    public static void clickOrderSuccessMessageClose(){customersPage.clickCloseSuccessMsg();}


    public static double getLastOrderedValue(){
        return customersPage.getTotalOrderValue();
    }

    public static void clickOnAddTagDropdownMenu() throws InterruptedException {
        if (customersPage.isTagExist()) {
            customersPage.clickRemoveTagOption();
        }
        customersPage.clickAddTagsDropdown();}


    public static boolean isDropdownMenuDisplayed(){
        return customersPage.isDropdownListDisplayed();
    }


    public static void selectToAddTagOption() throws InterruptedException {
        if (customersPage.isTagExist()) {
            customersPage.clickRemoveTagOption();
        }
        customersPage.selectTagOption();
    }


    public static boolean isAddedTagNameDisplayed(){
        return customersPage.isAddedTagDisplayed();
    }


    public static void clickRemoveAddedTag(){customersPage.clickRemoveTagOption();}


    public static boolean isAddedTagNameDeleted() throws InterruptedException {
        return customersPage.isAddedTagDeletedStable();
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


    public static void loginAsCustomer(String email,String nameCus)throws InterruptedException{
        customersPage.loginAsCus(email,nameCus);
    }


    public static void clickCustomerPortalOrderIcon()throws InterruptedException{customersPage.clickOrderIcon();}

    public static void logIntoDP()throws InterruptedException{
        customersPage.loginToDistributorPortal();
    }

    public static void selectCusAccountVisibleOption(){
        customersPage.selectVisibleOption();
    }

    public static boolean isAccountVisibleOptionDisplayed(){
        return customersPage.isVisibilityOptionDisplayed();
    }

    public static boolean isOrderGuideVisibleCustomerPortal(){
        return customersPage.isOrderGuideVisible();
    }

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
        return customersPage.isInactiveStatusDisplayed();
    }

    public static void refreshCustomersPage(){customersPage.refreshCustomersPage();}

    public static boolean isSearchedCustomerNotDisplayedText(){
        return customersPage.isCustomerNotMatchTextDisplayed();
    }

    public static void selectCusAccountActiveStatusOption(){customersPage.selectActiveStatusOption();}

    public static void clickCustomersMoreFilter(){customersPage.clickOnMoreFiltersOption();}
    public static void clickOnMoreFilterStatusDropdownMenu(){customersPage.clickOnMoreFilterStatusDropdown();}
    public static void clickCustomersInactiveStatusFilter(){customersPage.clickOnMoreFiltersInactiveOption();}
    public static void clickCustomerFilterApply(){customersPage.clickOnApplyFiltersOption();}

    public static boolean isCusActiveStatusTextDisplayed(){
        return customersPage.isActiveStatusDisplayed();
    }

    public static void clickCustomersActiveStatusFilter(){customersPage.clickOnMoreFiltersActiveOption();}

    public static boolean isErrorOccuredAddingPaymentMethod(){
        return customersPage.isErrorOccuredAddingPaymentMethod();
    }

    public static void clickOnFirstItemOfCustomerRequests() throws InterruptedException {
        customersPage.clickOnFirstItemOfCustomerRequests();
    }

    public static void clickonInvoice(){
        customersPage.clickonInvoice();
        customersPage.isFirstRecordDisplayed();
    }

    public static boolean verifyEnabledStatus(){
        return customersPage.verifyEnabledStatus();

    }

    public static boolean verifyPaymentTermStatus(){
        return customersPage.verifyPaymentTermStatus();
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
    public static void clickOnOrderGuideTab() {
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
    public static void typeSpecialInstruction(String specialInstruction) throws InterruptedException {
        customersPage.typeSpecialInstruction(specialInstruction);
    }
    public static void typeInternalNote(String internalNote) throws InterruptedException {
        customersPage.typeInternalNote(internalNote);
    }
    public static void typeNoteToCustomer(String noteToCustomer) throws InterruptedException {
        customersPage.typeNoteToCustomer(noteToCustomer);
    }
    public static void typePONumber(String poNumber) throws InterruptedException {
        customersPage.typePONumber(poNumber);
    }
    public static boolean isSpecialInstructionDisplayed(String specialInstruction){
        return customersPage.isSpecialInstructionDisplayed(specialInstruction);
    }
    public static boolean isInternalNoteDisplayed(String internalNote){
        return customersPage.isInternalNoteDisplayed(internalNote);
    }
    public static boolean isNoteToCustomerDisplayed(String noteToCustomer){
        return customersPage.isNoteToCustomerDisplayed(noteToCustomer);
    }
    public static String getItemQuantity() throws InterruptedException {
        return customersPage.getItemQuantity();
    }
    public static String getItemTotalQuantity()throws InterruptedException{
        return customersPage.getItemTotalQuantity();
    }
    public static String getItemValue() throws InterruptedException {
        return customersPage.getItemValue();
    }
    public static String getItemTotalValue()throws InterruptedException{
        return customersPage.getItemTotalValue();
    }

    public static boolean isUserNavigatedToDashboard() {
        return customersPage.isReviewPageTextDisplayed();
    }

    public static String getSuccessOrderId() {
        return customersPage.getOrderedId();
    }

    public static boolean isDeliveryOptionSelected() {
        return customersPage.isDeliveryOptionSelected();
    }

    public static boolean isPickUpOptionSelected() {
        return customersPage.isPickUpOptionSelected();
    }

    public static boolean isMailDeliveryOptionSelected() {
        return customersPage.isMailDeliveryOptionSelected();
    }

    public static void editOrderFromReviewScreen() {
        customersPage.clickEditOrderInReviewScreen();
    }

    public static void clickOnAddPaymentMethod() throws InterruptedException {
        if(customersPage.isAddPaymentMethodButtonDisplayed()){
            customersPage.clickOnAddPaymentMethod();
        } else {
            customersPage.editPaymentMethod();
            customersPage.clickOnAddNewPaymentMethod();
        }
    }

    public static void clickOnAddBankAccount() {
        customersPage.clickOnAddBankAccount();
    }

    public static void typeAccountNumber(String accountNumber){
        customersPage.typeAccountNumber(accountNumber);
    }

    public static void typeRoutingNumber(String routingNumber){
        customersPage.typeRoutingNumber(routingNumber);
    }

    public static void selectAccountType(String accountType){
        customersPage.selectAccountType(accountType);
    }

    public static void clickBtnNext(){
        customersPage.clickNext();
    }

    public static boolean isPaymentMethodAddedSuccessfully(){
        return customersPage.isPaymentMethodAddedSuccessfully();
    }

    public static String getItemCodeFirstRow() throws InterruptedException {
        return customersPage.getItemCodeFirstRow();
    }

    public static void editPaymentMethod() throws InterruptedException {
        customersPage.editPaymentMethod();
    }

    public static void clickOnTrashCan(){
        customersPage.clickOnTrashCan();
    }

    public static boolean isPaymentMethodRemovedDisplayed() throws InterruptedException {
        return customersPage.isPaymentMethodRemovedDisplayed();
    }

    public static void clickOnEnable() throws InterruptedException {
        if(customersPage.isAutoPayEnabled()){
            customersPage.editAutoPay();
            customersPage.clickOnCancelAutopay();
            customersPage.clickOnYes();
        }
        customersPage.clickOnEnable();
    }

    public static void clickOnIAgree(){
        customersPage.clickOnIAgree();
    }

    public static void clickOnEnableAutoPay(){
        customersPage.clickOnEnableAutoPlay();
    }

    public static boolean isAutoPayEnabled() throws InterruptedException {
        return customersPage.isAutoPayEnabled();
    }

    public static void clickPlusSearchedSingleItem(int count) {
        for (int i=0; i<count;i++){
            customersPage.clickPlusSearchedSingleItem();
        }
    }

    public static void clickOnPlusIconInCatalogPDP(int count, String name) {
        for (int i=0; i<count;i++){
            customersPage.clickOnPlusIconInCatalogPDP(name);
        }
    }

    public static void clickMinusSearchedSingleItem(int count) {
        for (int i=0; i<count;i++){
            customersPage.clickMinusSearchedSingleItem();
        }
    }

    public static double getActiveItemPriceFirstRow() throws InterruptedException {
        return customersPage.getActiveItemPriceFirstRow();
    }

    public static double getActiveItemPriceFirstRowStable() throws InterruptedException {
        return customersPage.getActiveItemPriceFirstRowStable();
    }

    public static double getItemPriceOnCheckoutButtonViaPDP() throws InterruptedException {
        return customersPage.getItemPriceOnCheckoutButtonViaPDP();
    }

    public static void clickOnDefaultCheckoutButton() throws InterruptedException {
        customersPage.clickOnCheckoutButton();
    }

    public static void editAutoPay(){
        customersPage.editAutoPay();
    }

    public static void clickOnAutoPayScheduleDropdown(){
        customersPage.clickOnDropdownSchedule();
    }

    public static void selectDropdownOption(String optionText){
        customersPage.selectDropdownOption(optionText);
    }

    public static void clickOnUpdate(){
        customersPage.clickOnUpdate();
    }

    public static boolean isAutoPayUpdated(String expectedText){
        return customersPage.isAutoPayUpdated(expectedText);
    }

    public static void clickOnCancelAutopay(){
        customersPage.clickOnCancelAutopay();
    }

    public static boolean isEnableVisible(){
        return customersPage.isEnableVisible();
    }

    public static boolean verifyLastInvoicePaid(){
        return customersPage.verifyLastInvoicePaid();
    }

    public static void clickOnEditCCFees(){
        customersPage.clickOnEditCCFees();
    }

    public static void clickOnDropdownCCFee(){
        customersPage.clickOnDropdownCCFee();
    }

    public static void clickOnCCFeeValue(String CCFeeValue){
        customersPage.clickOnCCFeeValue(CCFeeValue);
    }

    public static boolean isCCFeesValueCorrect(String expectedValue){
        return customersPage.isCCFeesValueCorrect(expectedValue);
    }

    public static void clickOnEditNotes(){
        customersPage.clickOnEditNotes();
    }

    public static void typeNewNote(String note) throws InterruptedException {
        customersPage.typeNewNote(note);
    }

    public static boolean isNoteCorrect(String expectedNote){
         return customersPage.isNoteCorrect(expectedNote);
    }

    public static void clickOnDropDownFilter(){
        customersPage.clickOnDropDownFilter();
    }

    public static void selectFilterDropDown(String FilterOption){
        customersPage.selectFilterDropDown(FilterOption);
    }

    public static boolean isFilterSelectedCorrectly(String expectedFilter){
        return customersPage.isFilterSelectedCorrectly(expectedFilter);
    }

    public static boolean isSearchedRowDisplayed(String CustomerCode){
        return customersPage.isSearchedCustomerDisplayed(CustomerCode);
    }

    public static boolean istxtEmailsSentDisplayed(){
        return customersPage.istxtEmailsSentDisplayed();
    }

    public static void clickOnSendPaymentReminder(){
        customersPage.clickOnSendPaymentReminder();
    }

    public static void clickOnSendEmail(){
        customersPage.clickOnSendEmail();
    }

    public static boolean sendEmail(){
        if (customersPage.isNoDueInvoicesDisplayed()){
            customersPage.clickOK();
            System.out.println("No Due Invoices Found");
            return true;
        }
        else if (customersPage.isAreYouSureTxtDisplayed()){
            System.out.println("Are you sure text displayed");
            customersPage.clickOnYes();

            if (customersPage.istxtEmailsSentDisplayed()){
                customersPage.clickOK();
                return true;
            }
            else{
                return false;
            }

        }
        else if(customersPage.istxtEmailsSentDisplayed()){
            System.out.println("Email Sent text displayed");
            customersPage.clickOK();
            return true;
        }
        else{
            return false;
        }
    }

    public static void clickDropdownMoreActions(){
        customersPage.clickDropdownMoreActions();
    }

    public static void clickManageNotifications(){
        customersPage.clickManageNotifications();
    }

    public static void clickInviteBookKeeper(){
        customersPage.clickInviteBookKeeper();
    }

    public static void fillBookKeeperName(String Name) throws InterruptedException {
        customersPage.fillBookKeeperName(Name);
    }

    public static void fillBookKeeperEmail(String Email) throws InterruptedException {
        customersPage.fillBookKeeperEmail(Email);
    }

    public static void fillBookKeeperMobile(String Mobile) throws InterruptedException {
        customersPage.fillBookKeeperMobile(Mobile);
    }

    public static void clickInviteViaEmail(){
        customersPage.clickInviteViaEmail();
    }

    public static boolean isBookKeeperEmailSentConfirmationDisplayed(String expectedEmail){
        return customersPage.isBookKeeperEmailSentConfirmationDisplayed(expectedEmail);
    }
    public static boolean isProprietaryItemOptionDisplayed()throws InterruptedException{
        return customersPage.isProprietaryItemOptionDisplayed();
    }

    public static void clickEmailStatement(){
        customersPage.clickEmailStatement();
    }

    public static void fillNotificationEmailAddress(String Email) throws InterruptedException {
        customersPage.fillNotificationEmailAddress(Email);
    }

    public static void clickSend(){
        customersPage.clickSend();
    }

    public static void clickDownloadStatement() throws InterruptedException {
        customersPage.clickDownloadStatement();
    }

    public static void clickCreateCreditMemo(){
        customersPage.clickCreateCreditMemo();
    }

    public static void typeCreditMemoNumber(String creditMemoNumber) throws InterruptedException {
        customersPage.typeCreditMemoNumber(creditMemoNumber);
    }

    public static void fillDropdownAssociatedInvoice(String associatedInvoice){
        customersPage.fillDropdownAssociatedInvoice(associatedInvoice);
    }

    public static void typeCreditMemoAmount(String creditMemoAmount) throws InterruptedException {
        customersPage.typeCreditMemoAmount(creditMemoAmount);
    }

    public static void typeCreditMemoDescription(String creditMemoDescription) throws InterruptedException {
        customersPage.typeCreditMemoDescription(creditMemoDescription);
    }

    public static void clickBtnCreateCreditMemo(){
        customersPage.clickBtnCreateCreditMemo();
    }

    public static boolean isCreditMemoFinalized(String memoNumber){
        if (customersPage.isTxtCreditMemoConfirmDisplayed()){
            return true;}
        else if (customersPage.isTxtCreditMemoExistingDisplayed(memoNumber)){
            System.out.println("The credit memo for " +memoNumber+ " already exists");
            return true;
        }
        else return false;
    }

    public static void clickOnCheckBox(int i){
        customersPage.clickOnCheckBox(i);
    }

    public static void clickMarkAsPaid(){
        customersPage.clickMarkAsPaid();
    }

    public static boolean isMarkedAsPaidSuccessfullyDisplayed(){
        return customersPage.isMarkedAsPaidSuccessfullyDisplayed();
    }

    public static void clickOnItemOrderGuideDropDown(String item) throws InterruptedException {
        customersPage.clickOnDropDownOrderGuide();
        customersPage.clickOnItemDropDownOrderGuide(item);
        if (customersPage.isPreviousDraftOrderNoDisplayed()){
            customersPage.clickPreviousDraftOrderNo();
        }
    }

    public static boolean isDisplayedOrderGuideTypeCorrect(String item){
        return customersPage.isDisplayedOrderGuideTypeCorrect(item);
    }

    public static void stableCheckoutItems() throws InterruptedException {
        customersPage.clickOnStableCheckoutButton();
        if (customersPage.isOrderMiniumErrorBannerDisplayed()){
            dashboardPage.clickOnOrderSettings();
            settingsPage.selectOnOrderMinimums();
            settingsPage.clickOnSaveChanges();
            customersPage.clickOnBack();
        }
    }

    public static void clickOnEditMargin(){
        customersPage.clickOnEditMargin();
    }

    public static void editMarginValue(String value){
        customersPage.editMarginValue(value);
    }

    public static void clickBtnResetValues(){
        customersPage.clickBtnResetValues();
    }

    public static void ensureCarouselDisplayStatus(boolean status) throws InterruptedException {
        dashboardPage.clickOnBoost();
        boostPage.clickSuggestiveSales();
        boostPage.clickDontForgetToOrderConfig();
        boostPage.ensureCarouselDisplayStatus(status);
        boostPage.clickClose();
    }

    public static void ifDuplicateOrderDisplayed(){
        customersPage.ifDuplicateOrderDisplayed();
    }

    public static boolean isLastOrderedPoundPriceDisplayed(){
        return customersPage.isLastOrderedPriceDisplayed();
    }

    public static boolean isLastOrderedPriceNotSameAfterToggleOff(){
        return customersPage.isLastOrderedPriceNotSameAfterToggle();
    }
    public static void clickOnCheckoutButtonOperator()throws InterruptedException{
        customersPage.clickOnCheckoutButtonOperator();
        Thread.sleep(4000);
    }
    public static void increaseFirstRowQtyInClassic(int count) throws InterruptedException {
        if (customersPage.isPreviousDraftOrderNoDisplayed()){
            customersPage.clickPreviousDraftOrderNo();
        }
        for(int i=0;i<count;i++){
            customersPage.clickPlusQryFirstRowClassic();
        }
    }
    public static void submitOrderForApproval() throws InterruptedException {
        customersPage.submitOrderForApproval();
        if (customersPage.isOrderMiniumErrorBannerDisplayed()){
            dashboardPage.clickOnOrderSettings();
            settingsPage.selectOnOrderMinimums();
            settingsPage.clickOnSaveChanges();
            customersPage.clickOnBack();
        }
        if (customersPage.isDuplicatePopupDisplayed()){
            customersPage.clickYesDuplicatePopup();
        }
    }
    public static boolean isSentApprovalDisplayed(){
        return customersPage.isSentApprovalDisplayed();
    }
    public static void clickViewOrderInDraft(){
        customersPage.clickViewOrderInDraft();
    }
    public static void clickCheckOutOrderGuide()throws InterruptedException{
        if (customersPage.isPreviousDraftOrderNoDisplayed()){
            customersPage.clickPreviousDraftOrderNo();
        }
        customersPage.clickCheckOutOrderGuide();
    }
    public static String getItemFinalWeight() throws InterruptedException {
        return customersPage.getItemFinalWeight();
    }
    public static String getItemFinalPrice() throws InterruptedException {
        return customersPage.getItemFinalPrice();
    }
    public static void typeOnFinalWeight(String weight) throws InterruptedException {
        customersPage.typeOnFinalWeight(weight);
    }
//    public static String getItemPriceOnEditOrderCheckout() throws InterruptedException {
//        return customersPage.getItemPriceOnEditOrderCheckout();
//    }
    public static void clickEditOrderCheckout()throws InterruptedException{
        if (customersPage.isPreviousDraftOrderNoDisplayed()){
            customersPage.clickPreviousDraftOrderNo();
        }
        customersPage.clickEditOrderCheckout();
    }
    public static String getConfirmFinalPrice() throws InterruptedException {
        return customersPage.getConfirmFinalPrice();
    }
//    public static String getPriceInCustomerOrder() throws InterruptedException {
//        return customersPage.getPriceInCustomerOrder();
//    }
    public static String getPoundPrice() throws InterruptedException {
        return customersPage.getPoundPrice();
    }
    public static void clickPoundPrice(){
        customersPage.clickPoundPrice();
    }
    public static boolean isPoundPricePopUpDisplay(){
        return customersPage.isPoundPricePopUpDisplay();
    }
    public static void typeOnPerLBPrice(String lbPrice) throws InterruptedException {
        customersPage.typeOnPerLBPrice(lbPrice);
    }
    public static void clickSave()throws InterruptedException{
        customersPage.clickSave();
    }
    public static void enterSpotPrice(String num) throws InterruptedException {
        customersPage.enterSpotPrice(num);
    }
    public static boolean isSpotPriceAdded(String code){
        return customersPage.isSpotPriceAdded(code);
    }
    public static String getItemFinalSpotPrice() throws InterruptedException {
        return customersPage.getItemFinalSpotPrice();
    }
    public static String getItemFinalPoundSpotPrice() throws InterruptedException {
        return customersPage.getItemFinalPoundSpotPrice();
    }
    public static void splitWeight(){
        customersPage.splitWeight();
    }
    public static boolean isSplitWeightPopupDisplayed(){
        return customersPage.isSplitWeightPopupDisplayed();
    }
    public static void enterCasesValue(String val) throws InterruptedException {
        customersPage.enterCasesValue(val);
    }
    public static void enterWeightValue(String val) throws InterruptedException {
        customersPage.enterWeightValue(val);
    }
    public static void clickUpdateWeight(){
        customersPage.clickUpdateWeight();
    }
    public static String getItemSplitFinalWeight() throws InterruptedException {
        return customersPage.getItemSplitFinalWeight();
    }
    public static String getEditSplitFinalWeightPrice() throws InterruptedException {
        return customersPage.getEditSplitFinalWeightPrice();
    }

    public static void scrollBottomOfPage()throws InterruptedException{
        customersPage.scrollBottomOfPage();
    }
    public static boolean isItemValueAdded(String code){
        return customersPage.isItemValueAdded(code);
    }
    public static void editSpotPrice(){
        customersPage.editSpotPrice();
    }
    public static String getPriceInCustomerOrder() throws InterruptedException {
        return customersPage.getPriceInCustomerOrder();
    }
    public static double getItemPriceOnEditOrderCheckout() throws InterruptedException {
        return customersPage.getItemPriceOnEditOrderCheckout();
    }
    public static String getItemPriceOnEditOrderReviewCheckout() throws InterruptedException {
        return customersPage.getItemPriceOnEditOrderReviewCheckout();
    }
    public static void clickOnCheckOutReview(){
        customersPage.clickOnCheckOutReview();
    }
    public static double getSplitFinalWeightPrice() throws InterruptedException {
        return customersPage.getSplitFinalWeightPrice();
    }

    public static void clickOnOrderGuideSettings(){
        customersPage.clickOnOrderGuideSettings();
    }



    //----MultiUOM ---//


    public static void selectFinalWeightFromOG(String position){
        customersPage.clickFinalWeight(position);
    }

    public static boolean isEditWeightOverlayDisplayed(){
        return customersPage.EditWeightOverlayDisplayed();
    }

    public static int getTotalWeight(String position){
        return customersPage.getTotalWeight(position);
    }

    public static int getNoOfUOMsOrdered(String position){
        return customersPage.getNoOfUOMsOrdered(position);
    }

    public static int getWeightPerUOM(String position){
        return customersPage.getWeightPerUOM(position);
    }

    public static String getItemNameFirstMultiOUM() throws InterruptedException {
        return customersPage.getItemNameFirstMultiOUM();
    }

    public static String getItemCodeFirstMultiOUM() throws InterruptedException {
        return customersPage.getItemCodeFirstMultiOUM();
    }

    public static double getActiveItemPriceFirstMultiOUMRowStable() throws InterruptedException {
        return customersPage.getActiveItemPriceFirstMultiOUMRowStable();
    }

    public static String getItemNameFirstSingleOUM() throws InterruptedException {
        return customersPage.getItemNameFirstSingleOUM();
    }

    public static String getItemCodeFirstSingleOUM() throws InterruptedException {
        return customersPage.getItemCodeFirstSingleOUM();
    }

    public static double getActiveItemPriceMultiOUM(String position) throws InterruptedException {
        return customersPage.getActiveItemPriceMultiOUM(position);
    }

    public static double getItemPriceOnMultiOUMCheckout() throws InterruptedException {
        return customersPage.getItemPriceOnMultiOUMCheckout();
    }

    public static void checkoutItemsMultiOUM() throws InterruptedException {
        customersPage.clickCheckOutOrderGuide();
        /*if (customersPage.isOrderMiniumErrorBannerDisplayed()){
            dashboardPage.clickOnOrderSettings();
            settingsPage.selectOnOrderMinimums();
            settingsPage.clickOnSaveChanges();
            customersPage.clickOnBack();
        }*/
        if (customersPage.isPreviousDraftOrderNoDisplayed()){
            customersPage.clickPreviousDraftOrderNo();
        }
        /*if(customersPage.isSubstitutesPopupDisplayed()){
            customersPage.clickDoNotSubstitute();
        }*/
    }

    public static void ClickOnMultiUomDropDownOG(String code)throws InterruptedException{
        customersPage.ClickOnMultiUomDropDownOG(code);
    }

    public static void clickOGAddToCartPlusIcon(int count,String code, String uom) throws InterruptedException{
        for (int i=0; i<count;i++){
            customersPage.clickOGAddToCartPlusIcon(code,uom);
        }
    }

    public static boolean isScanToOrderBtnDisplayedInCustomers(String customerId){
        return customersPage.isScanToOrderBtnDisplayed(customerId);
    }

    public static void navigateFromCustomerScreenToScanToOrderScreen(String customerCode){
        customersPage.clickCustomerScreenScanToOrderBtn(customerCode);
    }

}



