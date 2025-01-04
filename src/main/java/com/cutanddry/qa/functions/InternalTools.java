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

    public static void TurnOnLastOrderedPoundPrice(){
        internalToolsPage.clickTurnOnLastOrderedPoundPriceToggle();
    }

    public static void SaveLastOrderedPoundPriceTurnOn(){
        internalToolsPage.clickTurnOnLastOrderedPoundPriceSave();
    }

    public static void TurnOffLastOrderedPoundPrice(){
        internalToolsPage.clickTurnOffLastOrderedPoundPriceToggle();
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
}
