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
}
