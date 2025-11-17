package com.cutanddry.qa.functions;

import com.cutanddry.qa.pages.InternalToolsPage;
import com.cutanddry.qa.pages.LoginPage;

public class InternalTools {
    static InternalToolsPage internalToolsPage = new InternalToolsPage();
    static LoginPage loginPage = new LoginPage();

    public static void navigateToConfigureSupplier() {
        internalToolsPage.clickConfigureSupplier();
    }

    public static void navigateToIndependentCompEditDetails(){
        internalToolsPage.clickOnIndependentCompEditDetails();
    }

    public static void navigateToOrderingSettingsTab(){
        internalToolsPage.clickOnOrderingSettings();
    }

    public static void TurnOffStockLevelInPortal(){
        internalToolsPage.clickSTurnOffPortalStockLevelToggle();
    }

    public static void TurnOnStockLevelInPortal(){
        internalToolsPage.clickSTurnOffPortalStockLevelToggle();
    }

    public static void ensurePortalStockLevelToggle(boolean status) throws InterruptedException {
        internalToolsPage.ensurePortalStockLevelToggle(status);
    }

    public static void SaveStockLevelTurnOffInPortal(){
        internalToolsPage.clickTurnOffPortalStockSave();
    }

    public static void AccepSuccessfulOverlayByOK(){
        internalToolsPage.clickOKOnSucessOverlay();
    }

    public static void navigateToCatalogSettingsTab(){
        internalToolsPage.clickOnCatalogSettingsTab();
    }

    public static void selectManualSelectionFromDropdown(String CatalogDisableOption){
        internalToolsPage.clickOnManualSelectionFromDropdown(CatalogDisableOption);
    }

    public static void catalogSettingsSave() throws InterruptedException {
        internalToolsPage.clickCatalogSettingsSaveBtn();
    }
    public static void navigateToSponsoredAdsRebatesTab(){
        internalToolsPage.clickOnSponsoredAdsRebates();
    }
    public static void clickSponsoredProductAdsToggle(){
        internalToolsPage.clickSponsoredProductAdsToggle();
    }
    public static void clickProductAdsSave() throws InterruptedException {
        internalToolsPage.clickProductAdsSave();
    }
    public static void clickBuyerEdgePlatformRebateToggle(){
        internalToolsPage.clickBuyerEdgePlatformRebateToggle();
    }
    public static void clickBuyerEdgePlatformRebateToggle(boolean enable){
        internalToolsPage.clickBuyerEdgePlatformRebateToggle(enable);
    }
    public static void clickRebateSave() throws InterruptedException {
        internalToolsPage.clickRebateSave();
    }

    public static void TurnOnLastOrderedPoundPrice(){
//        internalToolsPage.clickTurnOnLastOrderedPoundPriceToggle();
        internalToolsPage.clickTurnOnLastOrderedPoundPriceToggle(true);
    }

    public static void SaveLastOrderedPoundPriceTurnOn(){
        internalToolsPage.clickTurnOnLastOrderedPoundPriceSave();
    }

    public static void TurnOffLastOrderedPoundPrice(){
//        internalToolsPage.clickTurnOffLastOrderedPoundPriceToggle();
        internalToolsPage.clickTurnOnLastOrderedPoundPriceToggle(false);
    }

    public static void TurnOnTheDisplayingStockCount(boolean status) throws InterruptedException {
        loginPage.ClickOnInternalTools();
        internalToolsPage.clickConfigureSupplier();
        internalToolsPage.clickOnIndependentCompEditDetails();
        internalToolsPage.clickOnOrderingSettings();
        internalToolsPage.ensurePortalStockLevelToggle(status);
        internalToolsPage.clickTurnOffPortalStockSave();
        internalToolsPage.clickOKOnSucessOverlay();

    }
    public static void TurnOnOrderMinimumGloballyToggle(boolean status){
        internalToolsPage.clickTurnOnOrderMinimumGloballyToggle(status);
    }
    public static void clickOnOrderMinimumDropdown(String type){
        internalToolsPage.clickOnOrderMinimumDropdown(type);
    }
    public static void enterOrderMinimum(String min){
        internalToolsPage.enterOrderMinimum(min);
    }
    public static void clickSave(){
        internalToolsPage.clickSave();
    }
    public static void clickOKOnSucessOverlay(){
        internalToolsPage.clickOKOnSucessOverlay();
    }
    public static boolean isSuccessPopUpDisplayed(){
        return internalToolsPage.isSuccessPopUpDisplayed();
    }
    public static void navigateToPayDetailsTab(){
        internalToolsPage.navigateToPayDetailsTab();
    }
    public static void clickPayEnabledToggle(boolean status){
        internalToolsPage.clickPayEnabledToggle(status);
    }
    public static boolean isPayEnableRestaurantDisplayed(String name){
        return internalToolsPage.isPayEnableRestaurantDisplayed(name);
    }
    public static boolean isPayDisableRestaurantDisplayed(String name){
        return internalToolsPage.isPayDisableRestaurantDisplayed(name);
    }
    public static void deleteRestaurantInPayEnable(String name){
        internalToolsPage.deleteRestaurantInPayEnable(name);
    }
    public static void addCustomerToPayDisable(String name) throws InterruptedException {
        internalToolsPage.addCustomerToPayDisable(name);
    }
    public static void deleteRestaurantInPayDisable(String name){
        internalToolsPage.deleteRestaurantInPayDisable(name);
    }
    public static void addCustomerToPayEnable(String name) throws InterruptedException {
        internalToolsPage.addCustomerToPayEnable(name);
    }
    public static void clickCreditMemoCheckbox(boolean status){
        internalToolsPage.clickCreditMemoCheckbox(status);
    }
    public static void clickUserDeletionEmailNotificationsToggle(boolean status){
        internalToolsPage.clickUserDeletionEmailNotificationsToggle(status);
    }
    public static void clickNotificationSave() throws InterruptedException {
        internalToolsPage.clickNotificationSave();
    }
    public static void ensureSponsoredProductAdsStatus(boolean enable) throws InterruptedException {
        internalToolsPage.clickConfigureSupplier();
        internalToolsPage.clickOnIndependentCompEditDetails();
        internalToolsPage.clickOnSponsoredAdsRebates();
        internalToolsPage.clickSponsoredProductAdsToggle(enable);
        internalToolsPage.clickProductAdsSave();
    }
    public static void ensureInstacartSponsoredProductAdsStatus(boolean enable) throws InterruptedException {
        internalToolsPage.clickInstacartSponsoredProductAdsToggle(enable);
        internalToolsPage.clickProductAdsSave();
    }
    public static void ensureBuyerEdgePlatformRebateStatus(boolean enable, String distributor) throws InterruptedException {
        internalToolsPage.clickConfigureSupplier();
        internalToolsPage.clickOnInternalToolCompanyEditDetails(distributor);
        internalToolsPage.clickOnSponsoredAdsRebates();
        internalToolsPage.clickBuyerEdgePlatformRebateToggle(enable);
        internalToolsPage.clickRebateSave();
    }
    public static void ensurePayEnabledStatus(boolean enable) throws InterruptedException {
        internalToolsPage.clickConfigureSupplier();
        internalToolsPage.clickOnIndependentCompEditDetails();
        internalToolsPage.navigateToPayDetailsTab();
        internalToolsPage.clickPayEnabledToggle(enable);
        internalToolsPage.clickSave();
        internalToolsPage.clickOKOnSucessOverlay();
    }
    public static void ensureCreditMemoCheckboxStatus(boolean enable) throws InterruptedException {
        internalToolsPage.clickConfigureSupplier();
        internalToolsPage.clickOnIndependentCompEditDetails();
        internalToolsPage.navigateToPayDetailsTab();
        internalToolsPage.clickCreditMemoCheckbox(enable);
        internalToolsPage.clickSave();
        internalToolsPage.clickOKOnSucessOverlay();
    }
    public static void clickOnInternalToolCompanyEditDetails(String name){
        internalToolsPage.clickOnInternalToolCompanyEditDetails(name);
    }
    public static void restrictSpotPricesToggle(boolean status){
        internalToolsPage.restrictSpotPricesToggle(status);
    }
    public static boolean isRestrictSpotPricesToggleOn(){
        return internalToolsPage.isRestrictSpotPricesToggleOn();
    }
    public static void clickOfflineOrderingToggle(boolean status){
        internalToolsPage.clickOfflineOrderingToggle(status);
    }
    public static void clickChatCheckbox(boolean status){
        internalToolsPage.clickChatCheckbox(status);
    }
    public static void clickSaveChanges() throws InterruptedException {
        internalToolsPage.clickSaveChanges();
    }
    public static void addCustomerToCatalogDisable(String name) throws InterruptedException {
        internalToolsPage.addCustomerToCatalogDisable(name);
    }
    public static void deleteRestaurantInCatalogDisable(String name){
        internalToolsPage.deleteRestaurantInCatalogDisable(name);
    }
    public static boolean isCatalogDisableRestaurantDisplay(String name){
        return internalToolsPage.isCatalogDisableRestaurantDisplay(name);
    }
    public static void displayMarginOnPortalToggle(boolean status){
        internalToolsPage.displayMarginOnPortalToggle(status);
    }
    public static void restrictMarginOnPortalToggle(boolean status){
        internalToolsPage.restrictMarginOnPortalToggle(status);
    }
    public static void spotPricingOnPortalToggle(boolean status){
        internalToolsPage.spotPricingOnPortalToggle(status);
    }
    public static void displayPurchaseHistoryToggle(boolean status){
        internalToolsPage.displayPurchaseHistoryToggle(status);
    }
    public static void displayPurchasePriceToggle(boolean status){
        internalToolsPage.displayPurchasePriceToggle(status);
    }
    public static void selectSpecialItemsDropdown(String SpecialItemsOption){
        internalToolsPage.selectSpecialItemsDropdown(SpecialItemsOption);
    }
    public static void clickHideOutOfStockToggle(boolean status){
        internalToolsPage.clickHideOutOfStockToggle(status);
    }
    public static void clickOnSimpleListViewDropdown(String type){
        internalToolsPage.clickOnSimpleListViewDropdown(type);
    }
    public static void fetchPricesFromOrderForEditOrderFlowToggle(boolean status){
        internalToolsPage.fetchPricesFromOrderForEditOrderFlowToggle(status);
    }
    public static void clickOnQuickAddViewDropDown(String type){
        internalToolsPage.clickOnQuickAddViewDropDown(type);
    }
    public static void clickOnDefaultViewForOperatorDropDown(String type){
        internalToolsPage.clickOnDefaultViewForOperatorDropDown(type);
    }
    public static void clickOnDefaultViewForPortalUsersDropDown(String type){
        internalToolsPage.clickOnDefaultViewForPortalUsersDropDown(type);
    }
    public static void manualOrderQuantityCalculationToggle(boolean status){
        internalToolsPage.manualOrderQuantityCalculationToggle(status);
    }
    public static void setEnableAccountHoldAlerts(boolean status) throws InterruptedException {
        internalToolsPage.enableAccountHoldAlerts(status);
        internalToolsPage.clickSave();
        internalToolsPage.clickOKOnSucessOverlay();
        internalToolsPage.scrollToConfigureSupplierPageHeader();
    }
    public static void setEnablePreAuthFeature(boolean status){
        internalToolsPage.enablePreAuth(status);
        internalToolsPage.clickSave();
        internalToolsPage.clickOKOnSucessOverlay();
    }
    public static void navigateToDPGroupManager() {
        internalToolsPage.navigateToDPGroupManager();
    }
    public static boolean isDPGroupManageTextDisplayed(){
        return internalToolsPage.isDPGroupManageTextDisplayed();
    }
    public static void clickCreateButton() {
        internalToolsPage.clickCreateButton();
    }
    public static boolean isCreateNewDPGroupTextDisplayed(){
        return internalToolsPage.isCreateNewDPGroupTextDisplayed();
    }
    public static void enterGroupName(String name) {
        internalToolsPage.enterGroupName(name);
    }
    public static void enterDescription(String description) {
        internalToolsPage.enterDescription(description);
    }
    public static void clickaAttachedDPs(String dp) {
        internalToolsPage.clickaAttachedDPs(dp);
    }
    public static void clickAllowCompanySwitching() {
        internalToolsPage.clickAllowCompanySwitching();
    }
    public static void clickGroupCreate() {
        internalToolsPage.clickGroupCreate();
    }
    public static boolean isCompanyNameDisplay(String name){
        return internalToolsPage.isCompanyNameDisplay(name);
    }

    public static boolean isCompanyDescriptionDisplay(String description){
        return internalToolsPage.isCompanyDescriptionDisplay(description);
    }

    public static boolean isCompanySwitchingDisplay(String status){
        return internalToolsPage.isCompanySwitchingDisplay(status);
    }

    public static boolean isVendorsDisplay(String vendor){
        return internalToolsPage.isVendorsDisplay(vendor);
    }
    public static boolean isEditDPGroupTextDisplayed(){
        return internalToolsPage.isEditDPGroupTextDisplayed();
    }
    public static void clickGroupEdit() {
        internalToolsPage.clickGroupEdit();
    }
    public static void clickUpdateDPGroup() {
        internalToolsPage.clickUpdateDPGroup();
    }
    public static void clickGroupDPDelete() {
        internalToolsPage.clickGroupDPDelete();
    }
    public static void navigateToTaskManagementTab(){
        internalToolsPage.clickOnTaskManagement();
    }
    public static void runParentChildTask(String formID){
        if(internalToolsPage.isParentChildTaskDisplayed(formID)){
            internalToolsPage.clickRunLocallyOnParentChildTask(formID);
        }else{
            internalToolsPage.refreshPage();
            internalToolsPage.clickRunLocallyOnParentChildTask(formID);
        }
    }
    public static boolean isPCTaskAttemptedDisplayed(){
        return internalToolsPage.isTaskAttemptedDisplayed();
    }
    public static void clickRunLocallyOnParentChildRelationshipTask(){
        internalToolsPage.clickRunLocallyOnParentChildRelationshipTask();
    }
    public static void clickCatalogOnlyOrderFlowToggle(boolean status){
        internalToolsPage.clickCatalogOnlyOrderFlowToggle(status);
    }

}
