package com.cutanddry.qa.functions;

import com.cutanddry.qa.pages.CreditRequestsPage;
import org.openqa.selenium.devtools.v85.domstorage.model.Item;

import java.lang.ref.SoftReference;

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

    public static boolean isErrorTextDisplayed(){
        return creditRequestsPage.isErrorTextDisplayed();
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
    public static boolean isRequestDateChanged(String days){
        return creditRequestsPage.isRequestDateChanged(days);
    }
    public static boolean isFilteredRequestCorrect(String requestsDate){
        return creditRequestsPage.isFilteredRequestCorrect(requestsDate);
    }
    public static void changeCreditRequestStatus(String status) {
        creditRequestsPage.clickOnCreditStatus();
        creditRequestsPage.clickOnCreditStatusOption(status);
    }
    public static boolean isCreditRequestStatusChanged(String status){
        return creditRequestsPage.isCreditStatusChanged(status);
    }
    public static boolean isFilteredCreditStatusCorrect(String status){
        return creditRequestsPage.isFilteredCreditStatusCorrect(status);
    }
    public static void changeSalesperson(String salesperson) {
        creditRequestsPage.clickOnSalesperson();
        creditRequestsPage.clickOnSalespersonOption(salesperson);
    }
    public static boolean isSalespersonChanged(String salesperson){
        return creditRequestsPage.isSalespersonChanged(salesperson);
    }

    public static boolean isNavigatedToOrderSection(){
        return creditRequestsPage.isNavigatedToOrderSection();
    }

    public static String[] getCrQtyCrValue(){
        return creditRequestsPage.getCrQtyCrValue();
    }

    public static boolean getItemQtyItemPrice(String CrQty, String CrValue){
        String[] ItemResults =  creditRequestsPage.getItemQtyItemPrice();
        String ItemQty = ItemResults[0];
        String ItemValue = ItemResults[1];

        String ItemQtyWithoutSuffix = ItemQty.substring(0, ItemQty.length() - 6);

        if (CrValue.equals(ItemValue) && CrQty.equals(ItemQtyWithoutSuffix)){
            System.out.println("Prices and Quantities are Equal");
            return true;
        }
        return false;
    }}
