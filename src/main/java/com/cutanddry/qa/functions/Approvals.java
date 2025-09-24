package com.cutanddry.qa.functions;

import com.cutanddry.qa.pages.ApprovalsPage;
import static com.cutanddry.qa.functions.Customer.customersPage;

public class Approvals {
    static ApprovalsPage approvalsPage = new ApprovalsPage();

    public static boolean isNavigatedToApprovalPage(){
        return approvalsPage.isNavigatedToApprovalPage();
    }

    public static void selectFirstApprovalOrder(){
        approvalsPage.clickOnFirstApprovalOrder();
    }

    public static void approveAndSubmitOrder(){
        approvalsPage.clickOnApproveAndSubmitBtn();
        if (customersPage.isDuplicatePopupDisplayed()){
            customersPage.clickYesDuplicatePopup();
        }
    }

    public static String getOrderApprovalCountWidgetDetails() throws InterruptedException {
        return approvalsPage.getApprovalCountTextFromOrderApprovalWidget();
    }

    public static boolean isOrderApprovalCountWidgetDetailsViewAllDisplayed() throws InterruptedException {
        return approvalsPage.getApprovalCountTextDisplayedFromOrderApprovalWidget();
    }

    public static String getOrderApprovalAccountCountWidgetDetails() throws InterruptedException {
        return approvalsPage.getApprovalAccountCountTextFromOrderApprovalWidget();
    }

    public static boolean isOrderApprovalAccountCountWidgetDetailsViewAllDisplayed() throws InterruptedException {
        return approvalsPage.getApprovalCountTextDisplayedFromOrderApprovalWidget();
    }

    public static void clearAllApprovalOrder(){
        approvalsPage.clearAllOrderApprovals();
    }

    public static void selectViewAll(){
        approvalsPage.clickViewAll();
    }

    public static String TotalPendingCount(String restuarentName){
        return approvalsPage.getTotalPending(restuarentName);
    }

    public static boolean isPendingOrdersDisplayed(String restuarentName){
        return approvalsPage.isPendingOrdersDisplayed(restuarentName);
    }

    public static void closeSummeryOverlay(){
        approvalsPage.clickCloseSummeryOverlay();
    }
}

