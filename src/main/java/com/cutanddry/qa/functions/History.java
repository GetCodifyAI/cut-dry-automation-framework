package com.cutanddry.qa.functions;

import com.cutanddry.qa.pages.HistoryPage;

public class History {
    static HistoryPage historyPage = new HistoryPage();

    public static boolean isUserNavigatedToHistory(){
        return historyPage.isOrderHistoryTextDisplayed();
    }
    public static void clickFirstItemFrmHistory()throws InterruptedException{
         historyPage.clickFirstItemFrmHistory();
    }

    public static void clickOnOrderFromOrderList(){
        historyPage.clickOnOrderFromOrderList();
    }

    public static boolean isUserNavigatedOrder(){
        return historyPage.isOrderTextDisplayed();
    }

    public static void clickBtnEditCheckIn(){
        historyPage.clickBtnEditCheckIn();
    }

    public static boolean isCheckInTextDisplayed(){
        return historyPage.isCheckInTextDisplayed();
    }

    public static void clickBtnReportIssue(){
        historyPage.clickBtnReportIssue();
    }

    public static void clickOnFirstRowTableOrderIssues(){
        historyPage.clickOnFirstRowTableOrderIssues();
    }

    public static boolean isPopupWindowReportIssueDisplayed(){
        return historyPage.isPopupWindowReportIssueDisplayed();
    }

    public static void clickOnFirstOptionDropDownWhatIsWrong(){
        historyPage.clickOnDropDownWhatIsWrong();
        historyPage.clickOnFirstRowDropDownWhatIsWrong();
    }

    public static void clickBtnContinue(){
        historyPage.clickOnBtnContinue();
    }

    public static void clickBtnSaveCheckIn(){
        historyPage.clickBtnSaveCheckIn();
    }

    public static void clickOnYes(){
        historyPage.clickOnYes();
    }

    public static void clickClose(){
        historyPage.clickClose();
    }

    public static boolean isTxtWhichItemsHasError(){
        return historyPage.isTxtWhichItemsHasError();
    }
    public static void clickCheckInOrder(){
        historyPage.clickCheckInOrder();
    }
    public static void ensureOrderDateSortedDescending() throws InterruptedException {
        historyPage.ensureOrderDateSortedDescending();
    }



}
