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
}

