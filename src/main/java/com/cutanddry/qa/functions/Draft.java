package com.cutanddry.qa.functions;

import com.cutanddry.qa.pages.DraftPage;
import static com.cutanddry.qa.functions.Customer.customersPage;

public class Draft {
    static DraftPage draftPage = new DraftPage();

    public static boolean isUserNavigatedToDrafts(){
        return draftPage.isDraftsTextDisplayed();
    }
    public static void clickOnDelete(){
        draftPage.clickOnDelete();
    }
    public static boolean isLastDraftDisplayed(String total){
        return draftPage.isLastDraftDisplayed(total);
    }

    public static boolean isApprovalRequestedOrderDisplayed(){
        return draftPage.isPendingApprovalOrdersDisplayed();
    }

    public static void selectApprovalRequestedOrder(){
        draftPage.clickOnApprovalPendingOrder();
    }

    public static void approveAndSubmitOrder(){
        draftPage.clickOnApproveAndSubmitBtn();
        if (customersPage.isDuplicatePopupDisplayed()){
            customersPage.clickYesDuplicatePopup();
        }
    }

}
