package com.cutanddry.qa.functions;

import com.cutanddry.qa.pages.SettingsPage;

public class Settings {
    static SettingsPage settingsPage = new SettingsPage();

    public static boolean isOrderSettingsTextDisplayed(){
        return settingsPage.isOrderSettingsTextDisplayed();
    }
    public static void enterOrderMinimum(String item) throws InterruptedException {
        settingsPage.enterOrderMinimum(item);
    }
    public static void clickOnSaveChanges() {
        settingsPage.clickOnSaveChanges();
    }

}
