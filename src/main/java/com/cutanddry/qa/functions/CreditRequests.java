package com.cutanddry.qa.functions;

import com.cutanddry.qa.pages.CreditRequestsPage;

public class CreditRequests {

    static CreditRequestsPage creditRequestsPage = new CreditRequestsPage();

    public static void changeRequestDate(String time_range) {
        creditRequestsPage.clickOnRequestDate();
        creditRequestsPage.selectTimeRange(time_range);
    }

    public static void searchOrderID(String orderID) throws InterruptedException{
        creditRequestsPage.clickOnSearch();
        creditRequestsPage.typeOnSearch(orderID);
    }

    public static boolean checkIfSearchedElementVisible(String orderID){
        return creditRequestsPage.checkIfSearchedElementVisible(orderID);
    }

    public static void clickOnFirstItemOfCreditRequests(){
        creditRequestsPage.clickOnFirstItemOfCreditRequests();
    }

    public static void clickOnItems(){
        creditRequestsPage.clickOnItems();
    }

    public static boolean checkIfItemSectionVisible(){
        return creditRequestsPage.checkIfItemSectionVisible();
    }

    public static boolean isErrorTextNotDisplayed(){
        return creditRequestsPage.isErrorTextNotDisplayed();
    }

    public static void clickOnTimeline(){
        creditRequestsPage.clickOnTimeline();
    }

    public static boolean checkIfTimelineSectionVisible(){
        return creditRequestsPage.checkIfTimelineSectionVisible();

    }

    public static void clickOnCreditView(){
        creditRequestsPage.clickOnCreditView();
    }

    public static boolean checkIfCreditViewSectionVisible(){
        return creditRequestsPage.checkIfCreditViewSectionVisible();
    }

    public static void clickOnFirstItemOfCreditView(){
        creditRequestsPage.clickOnFirstItemOfCreditView();
    }

    public static boolean checkIfIssueDetailsModalDisplayed(){
        return creditRequestsPage.checkIfIssueDetailsModalDisplayed();
    }
    //-----------
    public static void clickReportIssue(){
        creditRequestsPage.clickReportIssue();
    }
    public static boolean isReportIssueSectionDisplayed(){
        return creditRequestsPage.isReportIssueSectionDisplayed();
    }
    public static void clickOneItem(){
        creditRequestsPage.clickOneItem();
    }
    public static boolean isIssuePopUpDisplayed(){
        return creditRequestsPage.isIssuePopUpDisplayed();
    }
    public static void clickIssueOption() throws InterruptedException{
        creditRequestsPage.clickIssueOption();
    }
    public static void clickContinue(){
        creditRequestsPage.clickContinue();
    }
    public static boolean isCreditRequestedDisplayed(){
        return creditRequestsPage.isCreditRequestedDisplayed();
    }
    public static void clickSaveCheckIn(){
        creditRequestsPage.clickSaveCheckIn();
    }

}
