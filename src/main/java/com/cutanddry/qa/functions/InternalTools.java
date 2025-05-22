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

    public static void catalogSettingsSave(){
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
    public static void ensureBuyerEdgePlatformRebateStatus(boolean enable) throws InterruptedException {
        internalToolsPage.clickConfigureSupplier();
        internalToolsPage.clickOnIndependentCompEditDetails();
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
}
