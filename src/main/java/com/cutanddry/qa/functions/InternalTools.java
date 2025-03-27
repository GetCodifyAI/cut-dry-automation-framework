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
    public static void clickProductAdsSave(){
        internalToolsPage.clickProductAdsSave();
    }
    public static void clickBuyerEdgePlatformRebateToggle(){
        internalToolsPage.clickBuyerEdgePlatformRebateToggle();
    }
    public static void clickBuyerEdgePlatformRebateToggle(boolean enable){
        internalToolsPage.clickBuyerEdgePlatformRebateToggle(enable);
    }
    public static void clickRebateSave(){
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
    public static void clickPayDetailsToggle(boolean status){
        internalToolsPage.clickPayDetailsToggle(status);
    }
    public static boolean isPayEnableRestaurantDisplayed(String name){
        return internalToolsPage.isPayEnableRestaurantDisplayed(name);
    }
    public static boolean isPayDisableRestaurantDisplayed(String name){
        return internalToolsPage.isPayDisableRestaurantDisplayed(name);
    }

}
