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

    public static void clickDraft(String total){
        draftPage.clickDraft(total);
    }
    public static void clickEditOrder(){
        draftPage.clickEditOrder();
    }
    public static boolean isDraftOrdersNotOlder30Days(){
        return draftPage.isDraftOrdersNotOlder30Days();
    }
    public static boolean isRestaurantLastDraftDisplayed(String total){
        return draftPage.isRestaurantLastDraftDisplayed(total);
    }
    public static String getReferenceNumOP() throws InterruptedException {
        return draftPage.getReferenceNumOP();
    }
    public static String getReferenceNumDP() throws InterruptedException {
        return draftPage.getReferenceNumDP();
    }
    public static String getDraftsColumn(String num) throws InterruptedException {
        return draftPage.getDraftsColumn(num);
    }
    public static String getCustomerNameDP() throws InterruptedException {
        return draftPage.getCustomerNameDP();
    }
    public static void typeOnSearchDrafts(String draft)throws InterruptedException{
        draftPage.typeOnSearchDrafts(draft);
    }
    public static int getDraftCount() throws InterruptedException {
        return draftPage.getDraftCount();
    }
    public static boolean isResultCountDisplayed(String count){
        return draftPage.isResultCountDisplayed(count);
    }
    public static void clickTrashIcon()throws InterruptedException{
        draftPage.clickTrashIcon();
    }
    public static boolean isDraftsDeleteTextDisplayed(){
        return draftPage.isDraftsDeleteTextDisplayed();
    }
    public static void clickYesButton()throws InterruptedException{
        draftPage.clickYesButton();
    }
    public static boolean isDraftDeleteConfirmationDisplayed(String text){
        return draftPage.isDraftDeleteConfirmationDisplayed(text);
    }
    public static boolean isReferenceNumberDisplayed(String reference){
        return draftPage.isReferenceNumberDisplayed(reference);
    }
    public static boolean isDraftsDeleted(){
        return draftPage.isDraftsDeleted();
    }


}
