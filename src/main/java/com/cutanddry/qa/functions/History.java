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
    public static void goToHistory(){
        historyPage.clickHistory();
    }
    public static void searchOrderID(String orderID) throws InterruptedException{
        historyPage.clickOnSearch();
        historyPage.typeOnSearch(orderID);
        if (!historyPage.checkIfSearchedElementVisible(orderID)) {
            historyPage.refreshHistoryPage();
            historyPage.clickOnSearch();
            historyPage.typeOnSearch(orderID);
        }
    }
    public static boolean checkIfSearchedElementVisible(String orderID){
        return historyPage.checkIfSearchedElementVisible(orderID);
    }
    public static void clickOnFirstItemOfOrderHistory(){
        historyPage.clickOnFirstItemOfOrderHistory();
    }
    public static void clickEditOrder(){
        historyPage.clickEditOrder();
    }
    public static boolean isEditOrderPopUpDisplayed(){
        return historyPage.isEditOrderPopUpDisplayed();
    }
    public static void clickConfirmEditOrder(){
        historyPage.clickConfirmEditOrder();
    }
    public static void clickSubmitEditOrder(){
        historyPage.clickSubmitEditOrder();
    }
    public static boolean isInvalidProductTextDisplay(){
        return historyPage.isInvalidProductTextDisplay();
    }
    public static boolean isInvalidProductCodeDisplay(String code){
        return historyPage.isInvalidProductCodeDisplay(code);
    }



}
