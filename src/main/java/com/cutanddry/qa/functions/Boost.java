package com.cutanddry.qa.functions;

import com.cutanddry.qa.pages.BoostPage;

public class Boost {
    static BoostPage boostPage = new BoostPage();

    public static boolean isUserNavigatedToBoost(){
        return boostPage.isBoostTextDisplayed();
    }

    public static void addMessage() {
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
    public static void selectCustomer() {
        boostPage.clickSelectCustomersDropdown();
        boostPage.selectCustomer();
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
    public static void customizeMessage() {
        boostPage.typeMessage();
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
    public static boolean isDeactivatedDisplayed(){
        return boostPage.isDeactivatedDisplayed();
    }
    public static void clickSuggestiveSales(){
        boostPage.clickSuggestiveSales();
    }
    public static boolean isSuggestiveTabDisplayed(){
        return boostPage.isSuggestiveTabDisplayed();
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
    public static void addItem(){
        boostPage.addItem();
        boostPage.clickAdd();
    }
    public static boolean isItemAdded(){
        return boostPage.isItemAdded();
    }
    public static void clickClose(){
        boostPage.clickClose();
    }
    public static void removeItem(){
        boostPage.removeItem();
    }
    public static boolean isItemInCarouselPreview(){
        return boostPage.isItemInCarouselPreview();
    }
    public static void clickCompareSimilarItemsConfig(){
        boostPage.clickCompareSimilarItemsConfig();
    }
    public static boolean isCompareSimilarPopupDisplayed(){
        return boostPage.isCompareSimilarPopupDisplayed();
    }
    public static void toggleCarouselDisplayStatus(){
        boostPage.toggleCarouselDisplayStatus();
    }
    public static void clickRecommendForCustomerConfig(){
        boostPage.clickRecommendForCustomerConfig();
    }
    public static boolean isRecommendForCustomerPopupDisplayed(){
        return boostPage.isRecommendForCustomerPopupDisplayed();
    }
}
