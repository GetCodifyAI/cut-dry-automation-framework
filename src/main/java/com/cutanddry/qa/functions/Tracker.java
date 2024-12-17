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
    public static void typeTicketTitle(String title)throws InterruptedException{
        trackerPage.typeTicketTitle(title);
    }
    public static void selectStatus()throws InterruptedException{
        trackerPage.clickStatusDropDown();
        trackerPage.clickStatusOption();
    }
    public static void selectCategory()throws InterruptedException{
        trackerPage.clickCategoryDropDown();
        trackerPage.clickCategoryOption();
    }
    public static void selectPriority()throws InterruptedException{
        trackerPage.clickPriorityDropDown();
        trackerPage.clickPriorityOption();
    }
    public static void clickCreateTicket()throws InterruptedException{trackerPage.clickCreateTicket();}
    public static boolean isTaskTitleDisplayed(String title)throws InterruptedException{
        return trackerPage.isTaskTitleDisplayed(title);
    }
    public static void clickEditStatus(){trackerPage.clickEditStatus();}
    public static void clickEditStatusOption(){trackerPage.clickEditStatusOption();}
    public static void clickCloseEditTicket(){trackerPage.clickCloseEditTicket();}
    public static void clickFirstRow(String title){trackerPage.clickFirstRow(title);}
    public static boolean isEditTicketDisplayed(String status){
        return trackerPage.isEditTicketDisplayed(status);
    }
    public static boolean isEditTicketPopUpDisplayed(String title){
        return trackerPage.isEditTicketPopUpDisplayed(title);
    }


}
