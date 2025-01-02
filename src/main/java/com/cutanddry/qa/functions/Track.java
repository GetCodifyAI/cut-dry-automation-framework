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
    public static boolean isSMSTemplateAndPreviewDisplayed(){
        return trackPage.isSMSTemplateAndPreviewDisplayed();
    }
    public static boolean isEmailTemplateAndPreviewDisplayed(){
        return trackPage.isEmailTemplateAndPreviewDisplayed();
    }
    public static boolean isPushTemplateAndPreviewDisplayed(){
        return trackPage.isPushTemplateAndPreviewDisplayed();
    }
    public static void clickRouteCode(){
        trackPage.clickRouteCode();
    }
    public static boolean isRouteCodePopupDisplayed(){
        return trackPage.isRouteCodePopupDisplayed();
    }
    public static void clickEditRouteFunction(String editFunction){
        trackPage.clickEditRouteFunction(editFunction);
    }
    public static boolean isEditRoutePopupDisplayed(){
        return trackPage.isEditRoutePopupDisplayed();
    }
    public static void editRouteName(String name){
        trackPage.editRouteName(name);
    }
    public static boolean isRouteEditedPopupDisplayed (){
        return trackPage.isRouteEditedPopupDisplayed();
    }
    public static boolean isEditRouteNameDisplayed(String name){
        return trackPage.isEditRouteNameDisplayed(name);
    }
    public static boolean isAddStopPopupDisplayed(){
        return trackPage.isAddStopPopupDisplayed();
    }
    public static void addCustomerName(String name){
        trackPage.addCustomerName(name);
    }
    public static void addCustomerCode(String code){
        trackPage.addCustomerCode(code);
    }
    public static void addAddressStreet(String street){
        trackPage.addAddressStreet(street);
    }
    public static void addAddressCity(String city){
        trackPage.addAddressCity(city);
    }
    public static void addAddressState(String state){
        trackPage.addAddressState(state);
    }
    public static void addAddressZipCode(String zipCode){
        trackPage.addAddressZipCode(zipCode);
    }
    public static boolean isRouteStopAdded(String code){
        return trackPage.isRouteStopAdded(code);
    }
    public static void clickRouteName(String name){
        trackPage.clickRouteName(name);
    }
    public static void addBreak(){
        trackPage.addBreak();
    }
    public static boolean isPlaceBreakAfterStopAdded(String name){
        return trackPage.isPlaceBreakAfterStopAdded(name);
    }
    public static boolean validateStops(){
        return trackPage.validateStops();
    }
    public static boolean validateStartDateAndTime(){
        return trackPage.validateStartDateAndTime();
    }
    public static boolean validateDriver(){
        return trackPage.validateDriver();
    }
    public static boolean validateTruckDistanceAndValue(){
        return trackPage.validateTruckDistanceAndValue();
    }
    public static void uploadRoute(String path)throws InterruptedException{
        if (trackPage.isUploadRouteTextDisplayed()){
        trackPage.giveFilePath(path);
        }else {
            trackPage.clickDeleteRoute();
            trackPage.giveFilePath(path);
        }
    }

}
