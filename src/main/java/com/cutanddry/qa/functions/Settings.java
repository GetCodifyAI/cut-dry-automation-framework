package com.cutanddry.qa.functions;

import com.cutanddry.qa.pages.SettingsPage;

public class Settings {
    static SettingsPage settingsPage = new SettingsPage();

    public static boolean isOrderSettingsTextDisplayed(){
        return settingsPage.isOrderSettingsTextDisplayed();
    }
    public static void enterOrderMinimum(String item) throws InterruptedException {
        settingsPage.enterOrderMinimum(item);
    }
    public static void clickOnSaveChanges() {
        settingsPage.clickOnSaveChanges();
    }
    public static boolean isTeamSettingsTextDisplayed(){
        return settingsPage.isTeamSettingsTextDisplayed();
    }
    public static void clickOnAddUser() {
        settingsPage.clickOnAddUser();
    }
    public static void enterName(String name){
        settingsPage.enterName(name);
    }
    public static void enterEmail(String email){
        settingsPage.enterEmail(email);
    }
    public static void enterPhone(String mobile){
        settingsPage.enterPhone(mobile);
    }
    public static void enterUserRef(String ref) throws InterruptedException {
        settingsPage.enterUserRef(ref);
    }
    public static void clickOnInviteUser() {
        settingsPage.clickOnInviteUser();
    }
    public static boolean isUserDisplayed(String user){
        return settingsPage.isUserDisplayed(user);
    }
    public static void clickOnRemoveUserLabel() {
        settingsPage.clickOnRemoveUserLabel();
    }
    public static void clickOnEditUser(String user) {
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
    public static boolean isUserRefErrorDisplayed(){
        return settingsPage.isUserRefErrorDisplayed();
    }
    public static void clickRemoveAddedUserRef(String ref) {
        settingsPage.clickRemoveAddedUserRef(ref);
    }
    public static boolean isUserRefAdded(String ref) throws InterruptedException {
        return settingsPage.isUserRefAdded(ref);
    }
    public static boolean isUserAddingErrorPopupDisplayed(){
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
}
