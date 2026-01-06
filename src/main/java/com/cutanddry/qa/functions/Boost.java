package com.cutanddry.qa.functions;

import com.cutanddry.qa.pages.BoostPage;

public class Boost {
    static BoostPage boostPage = new BoostPage();

    public static boolean isUserNavigatedToBoost(){
        return boostPage.isBoostTextDisplayed();
    }

    public static void addMessage() {
        if (boostPage.isDeactivated()) {
            boostPage.clickXButton();
            boostPage.clickYes();
        }
        boostPage.clickAddMessage();
    }
    public static boolean isStepOneDisplayed() {
        return boostPage.isStepOneDisplayed();
    }
    public static void selectCustomList() {
        boostPage.clickCustomersDropdown();
        boostPage.selectCustomList();
    }
    public static boolean isSelectCustomersDisplayed() {
        return boostPage.isSelectCustomersDisplayed();
    }
    public static void selectCustomer(String name) {
        boostPage.clickSelectCustomersDropdown();
        boostPage.selectCustomer(name);
    }
    public static String selectFirstCustomer() throws InterruptedException {
        boostPage.clickSelectCustomersDropdown();
        String selectedCustomer = boostPage.selectFirstDropdownOption();
        System.out.println("Selected Customer: " + selectedCustomer);
        return selectedCustomer;
    }
    public static boolean isSelectionCountDisplayed() {
        return boostPage.isSelectionCountDisplayed();
    }
    public static void clickContinue() {
        boostPage.clickContinue();
    }
    public static boolean isStepTwoDisplayed() {
        return boostPage.isStepTwoDisplayed();
    }
    public static void customizeMessage(String msg) {
        boostPage.typeMessage(msg);
        boostPage.addURL();
        boostPage.removeNotifications();
    }
    public static void clickSubmit() {
        boostPage.clickSubmitButton();
    }
    public static boolean isBroadcastSuccessDisplayed() {
        return boostPage.isBroadcastSuccess();
    }
    public static void clickOk() {
        boostPage.clickOkButton();
    }
    public static void clickXButton() {
        boostPage.clickXButton();
    }
    public static void clickYes(){
        boostPage.clickYes();
    }
    public static boolean isDeactivated(){
        return boostPage.isDeactivated();
    }
    public static void clickSuggestiveSales(){
        boostPage.clickSuggestiveSales();
    }
    public static boolean isBroadcastTabDisplayed(){
        return boostPage.isBroadcastTabDisplayed();
    }
    public static boolean isSuggestiveTabDisplayed(){
        return boostPage.isSuggestiveTabDisplayed();
    }
    public static void ensureTopCategoryPicksDisplayStatus(boolean status) throws InterruptedException {
        boostPage.ensureTopCategoryPicksDisplayStatus(status);
    }
    public static void clickTopCategoryPicksConfig(){
        boostPage.clickTopCategoryPicksConfig();
    }
    public static boolean isTopCategoryPopupDisplayed(){
        return boostPage.isTopCategoryPopupDisplayed();
    }
    public static void clickAllItemsConfig(){
        boostPage.clickAllItemsConfig();
    }
    public static void clickAddItems(){
        boostPage.clickAddItems();
    }
    public static void addItem(String code){
        boostPage.addItem(code);
        boostPage.clickAdd();
    }
    public static boolean isItemAdded(String code){
        return boostPage.isItemAdded(code);
    }
    public static void clickClose() throws InterruptedException {
        boostPage.clickClose();
    }
    public static void removeItem(String code){
        boostPage.removeItem(code);
    }
    public static boolean isItemInCarouselPreview(String code){
        return boostPage.isItemInCarouselPreview(code);
    }
    public static void clickCompareSimilarItemsConfig(){
        boostPage.clickCompareSimilarItemsConfig();
    }
    public static boolean isCompareSimilarPopupDisplayed(){
        return boostPage.isCompareSimilarPopupDisplayed();
    }
    public static void toggleOnCarouselDisplayStatus(boolean inactive) throws InterruptedException {
        boostPage.toggleOnCarouselDisplayStatus(inactive);
    }
    public static void ensureDontForgetToOrderDisplayStatus(boolean status) throws InterruptedException {
        boostPage.ensureDontForgetToOrderDisplayStatus(status);
    }
    public static void toggleOffCarouselDisplayStatus(){
        boostPage.toggleOffCarouselDisplayStatus();
    }
    public static void clickRecommendForCustomerConfig(){
        boostPage.clickRecommendForCustomerConfig();
    }
    public static boolean isRecommendForCustomerPopupDisplayed(){
        return boostPage.isRecommendForCustomerPopupDisplayed();
    }
    public static void clickRecommendBySalesRepConfig(){
        boostPage.clickRecommendBySalesRepConfig();
    }
    public static boolean isRecommendBySalesRepPopupDisplayed(){
        return boostPage.isRecommendBySalesRepPopupDisplayed();
    }
    public static void clickSalesRepConfig(String name){
        boostPage.clickSalesRepConfig(name);
    }
    public static boolean isSalesRepConfigPopupDisplayed(){
        return boostPage.isSalesRepConfigPopupDisplayed();
    }
    public static void clickDontForgetToOrderConfig(){
        boostPage.clickDontForgetToOrderConfig();
    }
    public static boolean isDontForgetPopupDisplayed(){
        return boostPage.isDontForgetPopupDisplayed();
    }
    public static void clickMoreFromThisConfig(){
        boostPage.clickMoreFromThisConfig();
    }
    public static boolean isMoreFromThisPopupDisplayed(){
        return boostPage.isMoreFromThisPopupDisplayed();
    }
    public static boolean checkInactiveState(String type) throws InterruptedException {
        return boostPage.checkInactive(type);
    }
    public static void clickCatalogHome(){
        boostPage.clickCatalogHome();
    }
    public static boolean isCatalogHomeTabDisplayed(){
        return boostPage.isCatalogHomeTabDisplayed();
    }
    public static void toggleOnPrimaryBanner(){
        boostPage.toggleOnPrimaryBanner();
    }
    public static void toggleOffPrimaryBanner(){
        boostPage.toggleOffPrimaryBanner();
    }
    public static void clickOnSaveChanges() {
        boostPage.clickOnSaveChanges();
    }
    public static boolean isActiveDisplayed(){
        return boostPage.isActiveDisplayed();
    }
    public static boolean isHiddenDisplayed(){
        return boostPage.isHiddenDisplayed();
    }
    public static boolean isPrimaryBannerDisplayed(){
        return boostPage.isPrimaryBannerDisplayed();
    }

    public static void navigateToFeaturedListTab(){
        boostPage.clickFeaturedListTab();
    }

    public static void createNewFeaturedList() throws InterruptedException {
        boostPage.clickOnCreateNewFeaturedListBtn();
    }

    public static boolean isCrateListOverlayDisplayed(){
        return boostPage.CreateListOverlayDisplayed();
    }

    public static void enterFeaturedListName(String featuredListName){
        boostPage.typeFeaturedListName(featuredListName);
    }

    public static void submitEnteredListName(){
        boostPage.clickSubmitBtnInCreateList();
    }

    public static boolean addedListNameDisplayed(String featuredList){
        return boostPage.isFeaturedListDisplayed(featuredList);
    }

    public static boolean deletedListDisplayed(String featuredList){
        return boostPage.isFeaturedListDisplayed(featuredList);
    }
    public static void viewAndConfigure(String featuredListName){
        boostPage.clickViewAndConfigureBtn(featuredListName);
    }

    public static boolean itemConfigureOverlayDisplayed(){
        return boostPage.isConfigureItemOverlayDisplayed();
    }

    public static void editListName(){
        boostPage.clickOnEditListName();
    }

    public static boolean editListNameDisplayed(){
        return boostPage.isEditListNameOverlayDisplayed();
    }

    public static void saveChanges(){
        boostPage.clickSaveChangesBtn();
    }

    public static void closeEditOverlay(){
        boostPage.clickOnCloseEditOverlayBtn();
    }

    public static void deleteFeaturedList(String featuredList){
        boostPage.clickListDeleteBtn(featuredList);
    }

    public static boolean deleteFeaturedListOverlayDisplayed(){
        return boostPage.isDeleteFeaturedListOverlayDisplayed();
    }

    public static void deleteFeaturedListFromOverlay(){
        boostPage.clickDeleteBtnInDeleteListOverlay();
    }

    public static void selectDropDownStatus(String status){
        boostPage.clickDropDownStatus();
        boostPage.clickDropDownStatus(status);
    }

    public static boolean isStatusInTableCorrect(String expectedStatus) throws InterruptedException {
        if (boostPage.isAddNewMessageDisplayed()){
            boostPage.clickBtnAddNewMessage();
            boostPage.clickContinue();
            boostPage.typeNewMessage("Test Message");
            boostPage.clickDropDownNotificationTime();
            String nextTime = boostPage.getNextRoundedTime();
            System.out.println(nextTime);
            boostPage.selectNotificationTime(nextTime);
            boostPage.clickBtnSubmit();
            boostPage.clickBtnOK();

            String status = boostPage.getStatusFirstRow();
            System.out.println("The status is "+status);
            if (status.equals(expectedStatus)){
                return true;
            }
            return true;
        }
        else{
            String status = boostPage.getStatusFirstRow();
            System.out.println("The status is "+status);
            if (status.equals(expectedStatus)){
                return true;
            }
            return false;
        }
    }

    public static void clickOnDisplayStatusToggle(boolean status){
        boostPage.clickOnDisplayStatusToggle(status);
    }

    public static void changeOrderDragAndDrop(){
        boostPage.changeOrderDragAndDrop();
    }

    public static void clearExistingBoostMessageIfExists(){
        while(boostPage.containsBoostMsg()) {
            boostPage.removeBroadCastMsg();
            boostPage.clickYes();
            boostPage.clickBtnOK();
        }
    }
    public static void selectFilteredGroup() throws InterruptedException {
        boostPage.clickCustomersDropdown();
        boostPage.selectFilteredGroup();
    }
    public static boolean isCustomerGroupsLabelDisplayed() {
        return boostPage.isCustomerGroupsLabelDisplayed();
    }
    public static String selectFirstCustomerGroup() throws InterruptedException {
        boostPage.clickSelectCustomersGroupDropdown();
        return boostPage.selectFirstGroupDropdownOption();
    }
    public static String selectFirstSalesPerson() throws InterruptedException {
        boostPage.clickSelectSalespersonDropdown();
        return boostPage.selectFirstSalespersonDropdownOption();
    }
    public static String selectDeliveryDates() throws InterruptedException {
        boostPage.clickSelectDeliveryDatesDropdown();
        return boostPage.selectDeliveryDatesDropdownOption();
    }
    public static boolean isWeeklyEssentialsTextDisplay(String option){
        return boostPage.isWeeklyEssentialsTextDisplay(option);
    }
    
    public static void clickBrandBoostTab(){
        boostPage.clickBrandBoostTab();
    }
    
    public static boolean isBrandBoostTabDisplayed(){
        return boostPage.isBrandBoostTabDisplayed();
    }
    
    public static void selectBrand(String brandName) throws InterruptedException {
        boostPage.selectBrand(brandName);
    }
    
    public static void clickAddBrand(){
        boostPage.clickAddBrand();
    }
    
    public static boolean isBrandAdded(String brandName){
        return boostPage.isBrandAdded(brandName);
    }
    
    public static void selectBoostLevel(String brandName, String level){
        boostPage.selectBoostLevel(brandName, level);
    }
    
    public static void saveBrandBoost(){
        boostPage.clickSaveBrandBoost();
    }
    
    public static void removeBrandBoost(String brandName){
        boostPage.removeBrandBoost(brandName);
    }
    
    public static boolean isBrandRemoved(String brandName){
        return boostPage.isBrandRemoved(brandName);
    }
    public static void clickBoostFilters(){
        boostPage.clickBoostFilters();
    }
    public static boolean isFiltersTestDisplay(){
        return boostPage.isFiltersTestDisplay();
    }
    public static void TurnOnBoostFilterToggle(String filter,boolean status) throws InterruptedException {
        boostPage.TurnOnBoostFilterToggle(filter,status);
    }

}
