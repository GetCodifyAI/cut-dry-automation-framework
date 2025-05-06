package com.cutanddry.qa.functions;

import com.cutanddry.qa.pages.ChatPage;
import com.cutanddry.qa.pages.OrderGuideSettingsPage;

public class OrderGuideSettings {
    static OrderGuideSettingsPage orderGuideSettingsPage = new OrderGuideSettingsPage();

    public static void clickOnOrderReminderAlert(){
        orderGuideSettingsPage.clickOnOrderReminderAlert();
    }

    public static boolean isOrderReminderAlertPopDisplayed(){
        return orderGuideSettingsPage.isOrderReminderAlertPopDisplayed();
    }

    public static void clickOrderCutoffTime(String cutofftime) throws InterruptedException {
        orderGuideSettingsPage.clickOrderCutoffTime(cutofftime);
    }

    public static void clickSendAlert(String alerttime) throws InterruptedException {
        orderGuideSettingsPage.clickSendAlert(alerttime);
    }

    public static void clickAlertDays(String alertdays) throws InterruptedException {
        orderGuideSettingsPage.clickAlertDays(alertdays);
    }

    public static void clickOrderRemiderAlertSettingSave() throws InterruptedException{
        orderGuideSettingsPage.clickOrderRemiderAlertSettingSave();
    }

    public static void clickOnSave(){
        orderGuideSettingsPage.clickOnSave();

    }
    public static boolean isOrderReminderAlertSettingDisplayed() throws InterruptedException {
        return orderGuideSettingsPage.isOrderReminderAlertSettingDisplayed();
    }
    

}
