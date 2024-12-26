package com.cutanddry.qa.functions;

import com.cutanddry.qa.pages.SettingsPage;

import java.text.ParseException;

public class Settings {
    static SettingsPage settingsPage = new SettingsPage();

    public static boolean isOrderSettingsTextDisplayed() throws InterruptedException {
        return settingsPage.isOrderSettingsTextDisplayed();
    }
    public static void enterOrderMinimum(String item) throws InterruptedException {
        settingsPage.enterOrderMinimum(item);
    }
    public static void clickOnSaveChanges() throws InterruptedException {
        settingsPage.clickOnSaveChanges();
    }
    public static boolean isTeamSettingsTextDisplayed() throws InterruptedException {
        return settingsPage.isTeamSettingsTextDisplayed();
    }
    public static boolean isAdsSettingsTextDisplayed() throws InterruptedException {
        return settingsPage.isAdsSettingsTextDisplayed();
    }
    public static boolean isPaySettingsTextDisplayed() throws InterruptedException {
        return settingsPage.isPaySettingsTextDisplayed();
    }
    public static void clickOnAddUser() throws InterruptedException {
        settingsPage.clickOnAddUser();
    }
    public static void enterName(String name){
        settingsPage.enterName(name);
    }
    public static void enterEmail(String email){
        settingsPage.enterEmail(email);
    }
    public static void enterUserRef(String ref) throws InterruptedException {
        settingsPage.enterUserRef(ref);
    }
    public static void clickOnInviteUser() throws InterruptedException {
        settingsPage.clickOnInviteUser();
    }
    public static boolean isUserDisplayed(String user) throws InterruptedException {
        return settingsPage.isUserDisplayed(user);
    }
    public static void clickOnRemoveUserLabel() throws InterruptedException {
        settingsPage.clickOnRemoveUserLabel();
    }
    public static void clickOnEditUser(String user) throws InterruptedException {
        settingsPage.clickOnEditUser(user);
    }
    public static boolean isEditUserPopupDisplayed(){
        return settingsPage.isEditUserPopupDisplayed();
    }
    public static void clickOK() throws InterruptedException {
        settingsPage.clickOK();
    }
    public static void clickOnRemoveUser() {
        settingsPage.clickOnRemoveUser();
    }
    public static boolean isRemoveUserPopupDisplayed(){
        return settingsPage.isRemoveUserPopupDisplayed();
    }
    public static boolean isUserRefErrorDisplayed() throws InterruptedException {
        return settingsPage.isUserRefErrorDisplayed();
    }
    public static void clickRemoveAddedUserRef(String ref) throws InterruptedException {
        settingsPage.clickRemoveAddedUserRef(ref);
    }
    public static boolean isUserRefAdded(String ref) throws InterruptedException {
        return settingsPage.isUserRefAdded(ref);
    }
    public static boolean isUserAddingErrorPopupDisplayed() throws InterruptedException {
        return settingsPage.isUserAddingErrorPopupDisplayed();
    }
    public static void enterNameWL(String name){
        settingsPage.enterNameWL(name);
    }
    public static void enterEmailWL(String email){
        settingsPage.enterEmailWL(email);
    }
    public static void clickOnAddUserWL() {
        settingsPage.clickOnAddUserWL();
    }
    public static boolean isRemovePopupWLUserDisplayed(){
        return settingsPage.isRemovePopupWLUserDisplayed();
    }
    public static boolean isWLUserDisplayed(String user){
        return settingsPage.isWLUserDisplayed(user);
    }
    public static void clickOnUser(String user) {
        settingsPage.clickOnUser(user);
    }
    public static void clickYes() throws InterruptedException {
        settingsPage.clickYes();
    }
    public static boolean isCompanySettingsTextDisplayed() throws InterruptedException {
        return settingsPage.isCompanySettingsTextDisplayed();
    }
    public static boolean isProfileSettingsTextDisplayed() throws InterruptedException {
        return settingsPage.isProfileSettingsTextDisplayed();
    }
    public static boolean isTrackSettingsTextDisplayed() throws InterruptedException {
        return settingsPage.isTrackSettingsTextDisplayed();
    }
    public static boolean isBillingSettingsTextDisplayed() throws InterruptedException {
        return settingsPage.isBillingSettingsTextDisplayed();
    }
    public static boolean isAddPaymentPopupDisplayed() throws InterruptedException {
        return settingsPage.isAddPaymentPopupDisplayed();
    }
    public static void clickOnAddPaymentMethod() throws InterruptedException {
        if(settingsPage.isRemoveAccountBtnDisplayed()){
            settingsPage.clickOnRemoveAcc();
            settingsPage.clickYes();
            Settings.clickOK();
        }
        settingsPage.clickOnAddPaymentMethod();
    }
    public static void clickOnAddBank() {
        settingsPage.clickOnAddBank();
    }
    public static void enterAccountNumber(String num){
        settingsPage.enterAccountNumber(num);
    }
    public static void enterRoutingNumber(String num){
        settingsPage.enterRoutingNumber(num);
    }
    public static void clickOnNext() {
        settingsPage.clickOnNext();
    }
    public static boolean isAddPaymentSuccessPopupDisplayed() {
        return settingsPage.isAddPaymentSuccessPopupDisplayed();
    }
    public static boolean isPaymentMethodAdded() {
        return settingsPage.isPaymentMethodAdded();
    }
    public static void clickOnRemoveAcc() {
        settingsPage.clickOnRemoveAcc();
    }
    public static boolean isAreYouSurePopupDisplayed() {
        return settingsPage.isAreYouSurePopupDisplayed();
    }
    public static void selectInvoice() {
        settingsPage.selectInvoice();
    }
    public static void clickOnDownloadInvoices() {
        settingsPage.clickOnDownloadInvoices();
    }
    public static boolean isDownloadInvoiceClickable() {
        return settingsPage.isDownloadInvoiceClickable();
    }
    public static void clickOnLinkBank() {
        settingsPage.clickOnLinkBank();
    }
    public static void clickOnLinkBankManually() {
        settingsPage.clickOnLinkBankManually();
    }
    public static boolean isLinkAccPopupDisplayed() {
        return settingsPage.isLinkAccPopupDisplayed();
    }
    public static void clickOnSave() {
        settingsPage.clickOnSave();
    }
    public static boolean isPayoutMethodAdded() {
        return settingsPage.isPayoutMethodAdded();
    }
    public static boolean isBankDetailsAddedPopupDisplayed() {
        return settingsPage.isBankDetailsAddedPopupDisplayed();
    }
    public static boolean isBankDetailsRemovedPopupDisplayed() {
        return settingsPage.isBankDetailsRemovedPopupDisplayed();
    }

    public static void removeExistingBankAccount() throws InterruptedException {
        if(settingsPage.isPayoutMethodAvailable()){
            Settings.clickOnRemoveAcc();
            Settings.clickYes();
            Settings.clickOK();
        }
    }
    public static boolean isSetHolidayPopupDisplayed() {
        return settingsPage.isSetHolidayPopupDisplayed();
    }
    public static void clickOnAddHoliday(int daysToAdd) {
        settingsPage.removeDateAsHoliday(daysToAdd);
        settingsPage.clickOnPlusBtn();
    }
    public static void selectGlobal() {
        settingsPage.selectGlobal();
    }

    public static void selectHolidayDate(int nextDayCount) throws InterruptedException {
        settingsPage.selectHolidayDate(nextDayCount);
    }

    public static String getHolidayDate() {
        return settingsPage.getHolidayDate();
    }
    public static void selectCustomerSpecific(String code) {
        settingsPage.selectCustomerSpecific(code);
    }
    public static void clickOnSaveBtn() {
        settingsPage.clickOnSaveBtn();
    }
    public static String getDate() {
        return settingsPage.getDate();
    }
    public static String getAddedDate() {
        return settingsPage.getAddedDate();
    }
    public static boolean isHolidayInDeliveryOrPuckUpOrMailDates() {
        return settingsPage.isHolidayInDeliveryOrPuckUpOrMailDates();
    }
    public static void clickOnRemoveHoliday() {
        settingsPage.clickOnMinusBtn();
    }
    public static void uncheckDeliveryDays() {
        settingsPage.uncheckDeliveryDays();
    }
    public static void selectOnOrderMinimums() {
        settingsPage.selectOnOrderMinimums();
    }

    public static void setOrderMinimums(boolean selection) {
        settingsPage.setOrderMinimums(selection);
    }

    public static void setCustomerSpecificDeliveryDays(boolean selection) {
        settingsPage.setCustomerSpecificDeliveryDays(selection);
    }

    public static void setDeliveryDays(boolean selection) {
        settingsPage.setDeliveryDays(selection);
    }

    public static boolean isHolidayInDelivery(String date) throws ParseException {
        return settingsPage.isHolidayInDelivery(date);
    }

    public static boolean userCleanUp(String user) throws InterruptedException {
        settingsPage.clickOnEditUser(user);
        settingsPage.clickOnRemoveUserLabel();
        settingsPage.clickOnRemoveUser();
        settingsPage.clickOK();
        return settingsPage.isUserDisplayed(user);
    }
}
