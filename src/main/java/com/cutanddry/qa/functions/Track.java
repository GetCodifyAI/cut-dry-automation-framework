package com.cutanddry.qa.functions;

import com.cutanddry.qa.pages.TrackPage;

public class Track {
    static TrackPage trackPage = new TrackPage();

    public static boolean isResourcesTextDisplayed(){
        return trackPage.isTrackResourcesTextDisplayed();
    }
    public static void clickOnTrucks(){
        trackPage.clickOnTrucks();
    }
    public static void clickOnDrivers(){
        trackPage.clickOnDrivers();
    }
    public static boolean isAddDriversBtnDisplayed(){
        return trackPage.isAddDriversBtnDisplayed();
    }
    public static boolean isAddTrucksBtnDisplayed(){
        return trackPage.isAddTrucksBtnDisplayed();
    }
    public static boolean isRoutesTextDisplayed(){
        return trackPage.isTrackRoutesTextDisplayed();
    }
    public static boolean isMonitoringTextDisplayed(){
        return trackPage.isTrackMonitoringTextDisplayed();
    }
    public static boolean isNotificationsTextDisplayed(){
        return trackPage.isTrackNotificationsTextDisplayed();
    }
    public static void clickOnAddDrivers(){
        trackPage.clickOnAddDrivers();
    }
    public static boolean isAddDriverPopupDisplayed() {
        return trackPage.isAddDriverPopupDisplayed();
    }
    public static boolean isAddTruckPopupDisplayed() {
        return trackPage.isAddTruckPopupDisplayed();
    }
    public static void clickOnAddTrucks(){
        trackPage.clickOnAddTrucks();
    }
    public static void enterTruckName(String name){
        trackPage.enterTruckName(name);
    }
    public static void enterName(String name){
        trackPage.enterName(name);
    }
    public static void enterPhone(String phone) throws InterruptedException {
        trackPage.enterPhone(phone);
    }
    public static void clickOnInviteUser() throws InterruptedException {
        trackPage.clickOnInviteUser();
    }
    public static boolean isUserDisplayed(String user) throws InterruptedException {
        return trackPage.isUserDisplayed(user);
    }
    public static void clickOnRemoveUserLabel() throws InterruptedException {
        trackPage.clickOnRemoveUserLabel();
    }
    public static void clickOnEditUser(String user) throws InterruptedException {
        trackPage.clickOnEditUser(user);
    }
    public static boolean isEditUserPopupDisplayed(){
        return trackPage.isEditUserPopupDisplayed();
    }
    public static void clickOK() throws InterruptedException {
        trackPage.clickOK();
    }
    public static void clickOnRemoveUser() {
        trackPage.clickOnRemoveUser();
    }
    public static boolean isRemoveUserPopupDisplayed(){
        return trackPage.isRemoveUserPopupDisplayed();
    }
    public static void clickOnSaveChanges() throws InterruptedException {
        trackPage.clickOnSaveChanges();
    }
    public static void searchDrivers(String name) {
        trackPage.searchDriver(name);
    }
    public static void searchTrucks(String name) {
        trackPage.searchTrucks(name);
    }
    public static boolean isSearchDisplayed(String name) throws InterruptedException {
        return trackPage.isSearchDisplayed(name);
    }
    public static void clickOnNotifExample() {
        trackPage.clickOnNotifExample();
    }
    public static boolean isNotificationExamplePopupDisplayed(){
        return trackPage.isNotificationExamplePopupDisplayed();
    }
    public static void clickOnSMS(){
        trackPage.clickOnSMS();
    }
    public static void clickOnEmail(){
        trackPage.clickOnEmail();
    }
    public static void clickOnPush(){
        trackPage.clickOnPush();
    }
}
