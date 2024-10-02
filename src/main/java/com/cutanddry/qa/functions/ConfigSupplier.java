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
    public static void clickOnSave(){
        configPage.clickOnSave();
    }
    public static void toggleOnOGSuggestiveTool(){
        configPage.toggleOnOGSuggestiveTool();
    }
    public static void toggleOffOGSuggestiveTool(){
        configPage.toggleOffOGSuggestiveTool();
    }
}
