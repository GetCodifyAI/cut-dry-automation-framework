package com.cutanddry.qa.functions;

import com.cutanddry.qa.pages.TrackerPage;

public class Tracker {
    static TrackerPage trackerPage = new TrackerPage();

    public static boolean isOnboardingDocsDisplayed() throws InterruptedException{
        return trackerPage.isOnboardingDocsDisplayed();
    }

    public static void clickIssueTracker(){trackerPage.clickIssueTracker();}
    public static boolean isIssueTrackerDisplayed() throws InterruptedException{
        return trackerPage.isIssueTrackerDisplayed();
    }
    public static void clickNewTicket(){trackerPage.clickNewTicket();}
    public static boolean isCreateNewIssueDisplayed() throws InterruptedException{
        return trackerPage.isCreateNewIssueDisplayed();
    }
    public static void clickFeatureRequests(){trackerPage.clickFeatureRequests();}
    public static boolean isFeatureRequestsDisplayed() throws InterruptedException{
        return trackerPage.isFeatureRequestsDisplayed();
    }
    public static void clickRequestFeature(){trackerPage.clickRequestFeature();}
    public static boolean isRequestNewFeatureDisplayed() throws InterruptedException{
        return trackerPage.isRequestNewFeatureDisplayed();
    }

}
