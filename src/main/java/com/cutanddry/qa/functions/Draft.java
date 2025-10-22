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
    public static boolean isLastDraftDisplayedVito(String total){
        return draftPage.isLastDraftDisplayedVito(total);
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
    public static void clickDropDownFilter(String filter)throws InterruptedException{
        draftPage.clickDropDownFilter(filter);
    }
    public static void clickDropDownFilterOption(String option)throws InterruptedException{
        draftPage.clickDropDownFilterOption(option);
    }
    public static void clickClearFilter()throws InterruptedException{
        draftPage.clickClearFilter();
    }
    public static String getReferenceNum() throws InterruptedException {
        return draftPage.getReferenceNum();
    }
    public static boolean isPendingApprovalDraftDisplayed(String status){
        return draftPage.isPendingApprovalDraftDisplayed(status);
    }
    public static void pendingApprovalDraftClick(String status){
       draftPage.pendingApprovalDraftClick(status);
    }

    public static boolean isOrderDraftDisplayed(String refID){
        return draftPage.isOrderDraftDisplayed(refID);
    }

    public static boolean isDraftOrderReferenceNotDisplayedInOPSide(){
        return draftPage.isDraftOrderReferenceNotDisplayedInOPSide();
    }
    public static boolean isLastDraftStatusDisplayed(String total,String date){
        return draftPage.isLastDraftStatusDisplayed(total,date);
    }

    public static int getActiveDraftCount(){
        return draftPage.getActiveDraftCount();
    }

    public static boolean isEmptyStateDisplayed(){
        return draftPage.isEmptyStateDisplayed();
    }

}
