package com.cutanddry.qa.functions;

import com.cutanddry.qa.pages.TrackerPage;

public class Tracker {
    static TrackerPage trackerPage = new TrackerPage();

    public static boolean isOnboardingDocsDisplayed() throws InterruptedException{
        return trackerPage.isOnboardingDocsDisplayed();
    }
}
