package com.cutanddry.qa.functions;

import com.cutanddry.qa.pages.ConfigSupplierPage;

public class ConfigSupplier {
    static ConfigSupplierPage configPage = new ConfigSupplierPage();

    public static boolean isUserNavigatedToConfigSupplier(){
        return configPage.isConfigTextDisplayed();
    }
    public static void clickOnEditDetails(String name){
        configPage.clickOnEditDetails(name);
    }
    public static void clickOnCatalogSettings(){
        configPage.clickOnCatalogSettings();
    }
    public static void clickOnOrderSettings(){
        configPage.clickOnOrderSettings();
    }
    public static void clickOnSave(){
        configPage.clickOnSave();
    }
    public static void toggleOnOGSuggestiveTool() throws InterruptedException {
        configPage.toggleOnOGSuggestiveTool();
    }
    public static void toggleOffOGSuggestiveTool(){
        configPage.toggleOffOGSuggestiveTool();
    }
    public static void ensureDefaultSearchFilterStatus(boolean enable) throws InterruptedException {
        configPage.ensureDefaultSearchFilterStatus(enable);
    }

    public static void enableDefaultOrderHistoryAsOrderDate() throws InterruptedException {
        if (!configPage.isDefaultOrderHistoryAsOrderDateDisplayed()) {
            configPage.clickOnDefaultOrderHistoryAsOrderDate();
        }
    }

    public static void clickOnOrderSettingSave() throws InterruptedException {
        configPage.saveOrderSettingChanges();
    }

    public static void enableDefaultViewPortalAsSimpleList() throws InterruptedException {
        if (!configPage.isDefaultViewPortalAsSimpleListDisplayed()) {
            configPage.clickOnDefaultViewPortalAsSimpleList();
            configPage.saveOrderSettingChanges();
        }
    }

    public static void enableDefaultViewPortalAsQuickAdd() throws InterruptedException {
        if (!configPage.isDefaultViewPortalAsQuickAddDisplayed()) {
            configPage.clickOnDefaultViewPortalAsQuickAdd();
            configPage.saveOrderSettingChanges();
        }
    }

    public static void enableDefaultViewPortalAsOrderGuide() throws InterruptedException {
        if (!configPage.isDefaultViewPortalAsOrderGuideDisplayed()) {
            configPage.clickOnDefaultViewPortalAsOrderGuide();
            configPage.saveOrderSettingChanges();
        }
    }

    public static void enableDefaultViewPortalAsCatalog() throws InterruptedException {
        if (!configPage.isDefaultViewPortalAsCatalogDisplayed()) {
            configPage.clickOnDefaultViewPortalAsCatalog();
            configPage.saveOrderSettingChanges();
        }
    }

    public static void enableDefaultViewPortalAsScanToOrder() throws InterruptedException {
        if (!configPage.isDefaultViewPortalAsScanToOrderDisplayed()) {
            configPage.clickOnDefaultViewPortalAsScanToOrder();
            configPage.saveOrderSettingChanges();
        }
    }

    public static void enableQuickAdd() throws InterruptedException {
        if (!configPage.isDefaultQuickAddDisplayed()) {
            configPage.clickOnDefaultQuickAdd();
            configPage.saveOrderSettingChanges();
        }
    }

    public static void enableSimpleList() throws InterruptedException {
        if (!configPage.isDefaultSimpleListDisplayed()) {
            configPage.clickOnDefaultSimpleList();
            configPage.saveOrderSettingChanges();
        }
    }

    public static boolean isSupplierPortalCheckboxDisplayed() {
        return configPage.isSupplierPortalCheckboxDisplayed();
    }

    public static boolean isOperatorPortalCheckboxDisplayed() {
        return configPage.isOperatorPortalCheckboxDisplayed();
    }

    public static boolean isSelectedUOMDisplayed() {
        return configPage.isSelectedUOMDisplayed();
    }

    public static String getSelectedUOMValue() {
        return configPage.getSelectedUOMValue();
    }
}
