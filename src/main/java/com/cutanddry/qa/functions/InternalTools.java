package com.cutanddry.qa.functions;

import com.cutanddry.qa.pages.InternalToolsPage;

public class InternalTools {
    static InternalToolsPage internalToolsPage = new InternalToolsPage();

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
    public static void clickRebateSave(){
        internalToolsPage.clickRebateSave();
    }

}
