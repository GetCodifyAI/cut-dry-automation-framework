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

    public static String getItemNameInCR() {
        return creditRequestsPage.getItemNameInCR();
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

    public static boolean getItemQtyItemPrice(String CrQty, String CrValue, String itemName){
        String[] ItemResults =  creditRequestsPage.getItemQtyItemPrice(itemName);
        String ItemQty = ItemResults[0].split(" ")[0];
        String ItemValue = ItemResults[1];

        System.out.println(ItemQty);
        System.out.println(CrQty);
        System.out.println(ItemValue);
        System.out.println(CrValue);
//        String ItemQtyWithoutSuffix = ItemQty.substring(0, ItemQty.length() - 6);
// TODO: Need to check the Test step with the feature team and need to work on the Item quantity
        if (CrValue.equals(ItemValue) /*&& CrQty.equals(ItemQty)*/){
            System.out.println("Prices and Quantities are Equal");
            return true;
        }
        return false;
    }

    public static void clickCreditRequest(){
        creditRequestsPage.clickCreditRequest();
    }

    public static void clickBtnProcessCredit(){
        creditRequestsPage.clickBtnProcessCredit();
    }

    public static void clickCheckBox(){
        creditRequestsPage.clickCheckBox();
    }

    public static void clickBtnDeclineDraft(){
        creditRequestsPage.clickBtnDeclineDraft();
    }

    public static void clickSubmit(){
        creditRequestsPage.clickSubmit();
    }

    public static void clickConfirm(){
        creditRequestsPage.clickConfirm();
    }

    public static void clickClose(){
        creditRequestsPage.clickClose();
    }

    public static boolean isTxtCreditDeclinedDisplayed(){
        return creditRequestsPage.isTxtCreditDeclinedDisplayed();
    }

    public static void clickBtnEditCredit(){
        creditRequestsPage.clickBtnEditCredit();
    }

    public static void clickApproveCredit(){
        creditRequestsPage.clickApproveCredit();
    }

    public static boolean isTxtCreditApprovedDisplayed(){
        return creditRequestsPage.isTxtCreditApprovedDisplayed();
    }

    public static void process(){
        if (creditRequestsPage.isBtnProcessCreditVisible()){
            creditRequestsPage.clickBtnProcessCredit();
            creditRequestsPage.clickBtnDeclineDraft();
            creditRequestsPage.clickSubmit();
            creditRequestsPage.clickConfirm();
            creditRequestsPage.clickClose();
        }
        else if(creditRequestsPage.isBtnEditCreditVisible()){
            creditRequestsPage.clickBtnEditCredit();
            creditRequestsPage.clickConfirm();
            creditRequestsPage.clickBtnDeclineDraft();
            creditRequestsPage.clickSubmit();
            creditRequestsPage.clickConfirm();
            creditRequestsPage.clickClose();
        }
    }

    public static void process1(){
        if (creditRequestsPage.isBtnProcessCreditVisible()){
            creditRequestsPage.clickBtnProcessCredit();
            creditRequestsPage.clickCheckBox();
            creditRequestsPage.clickApproveCredit();
            creditRequestsPage.clickSubmit();
            creditRequestsPage.clickConfirm();
            creditRequestsPage.clickClose();
        }
        else if(creditRequestsPage.isBtnEditCreditVisible()){
            creditRequestsPage.clickBtnEditCredit();
            creditRequestsPage.clickConfirm();
            creditRequestsPage.clickCheckBox();
            creditRequestsPage.clickApproveCredit();
            creditRequestsPage.clickSubmit();
            creditRequestsPage.clickConfirm();
            creditRequestsPage.clickClose();
        }

    }

    public static boolean isTimelineDataSimilar(String order_timeStamp, String order_status, String order_organization, String order_user) {
        String[] timelineData = creditRequestsPage.getTimelineData();

        boolean isTimestampEqual = order_timeStamp.equals(timelineData[0]);
        boolean isStatusEqual = order_status.equals(timelineData[1]);
        boolean isOrganizationEqual = order_organization.equals(timelineData[2]);
        boolean isUserEqual = order_user.equals(timelineData[3]);

        if (isTimestampEqual && isStatusEqual && isOrganizationEqual && isUserEqual) {
            System.out.println("Timeline, Status, Organization, and User are equal");
            return true;
        }
        if (!isTimestampEqual) {
            System.out.println("Timestamp is not equal");
        }
        if (!isStatusEqual) {
            System.out.println("Status is not equal");
        }
        if (!isOrganizationEqual) {
            System.out.println("Organization is not equal");
        }
        if (!isUserEqual) {
            System.out.println("User is not equal");
        }
        return false;
    }
}

