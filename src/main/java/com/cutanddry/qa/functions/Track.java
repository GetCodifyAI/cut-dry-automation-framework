package com.cutanddry.qa.functions;

import com.cutanddry.qa.pages.SettingsPage;
import com.cutanddry.qa.pages.TrackPage;

public class Track {
    static TrackPage trackPage = new TrackPage();

    public static boolean isResourcesTextDisplayed(){
        return trackPage.isTrackResourcesTextDisplayed();
    }
    public static void clickOnTrucks(){
        trackPage.clickOnTrucks();
    }
    public static void clickOnDrivers(){
        trackPage.clickOnDrivers();
    }
    public static boolean isAddDriversBtnDisplayed(){
        return trackPage.isAddDriversBtnDisplayed();
    }
    public static boolean isAddTrucksBtnDisplayed(){
        return trackPage.isAddTrucksBtnDisplayed();
    }
    public static boolean isRoutesTextDisplayed(){
        return trackPage.isTrackRoutesTextDisplayed();
    }
    public static boolean isMonitoringTextDisplayed(){
        return trackPage.isTrackMonitoringTextDisplayed();
    }
    public static boolean isNotificationsTextDisplayed(){
        return trackPage.isTrackNotificationsTextDisplayed();
    }

}
