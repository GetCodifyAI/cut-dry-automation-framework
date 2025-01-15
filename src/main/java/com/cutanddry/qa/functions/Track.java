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
    public static boolean checkMapVisible(String status){
        if (trackPage.isMapVisible()){
            return trackPage.mapVisibleStatus(status);
        }else trackPage.isMapHidden();{
            trackPage.clickToShowMap();
            return trackPage.mapVisibleStatus(status);
        }
    }
    public static boolean checkMapHidden(String status){
        if (trackPage.isMapHidden()){
            return trackPage.mapVisibleStatus(status);
        }else trackPage.isMapVisible();{
            trackPage.clickToHiddenMap();
            return trackPage.mapVisibleStatus(status);
        }
    }
    public static void clickAddStop(){trackPage.clickAddStop();}
    public static boolean isUnassignedStopAdded(String code){
        return trackPage.isUnassignedStopAdded(code);
    }
    public static void clickEditUnassignStop(){
        trackPage.clickEditUnassignStop();
    }
    public static void clickDeleteStop(){
        trackPage.clickDeleteStop();
    }
    public static boolean isRemoveStopPopUpDisplayed(){
        return trackPage.isRemoveStopPopUpDisplayed();
    }
    public static void clickYesButton(){
        trackPage.clickYesButton();
    }
    public static boolean isRemoveStopSuccessfully(){
        return trackPage.isRemoveStopSuccessfully();
    }
    public static boolean isDispatchRoutePopupDisplayed(){
        return trackPage.isDispatchRoutePopupDisplayed();
    }
    public static void clickDispatch(){
        trackPage.clickDispatch();
    }
    public static boolean AreYouSureToDispatchDisplayed(){
        return trackPage.AreYouSureToDispatchDisplayed();
    }
    public static boolean dispatchSuccessDisplayed(){
        return trackPage.dispatchSuccessDisplayed();
    }
    public static boolean isDispatchedTextDisplayed(){
        return trackPage.isDispatchedTextDisplayed();
    }
    public static void clickBtnManageRoutes(){trackPage.clickBtnManageRoutes();}
    public static void clickBtnImportRoutes(){trackPage.clickBtnImportRoutes();}
    public static void clickBtnAddNewRoutes(){trackPage.clickBtnAddNewRoutes();}
    public static void clickBtnDownloadExampleFile(){trackPage.clickBtnDownloadExampleFile();}
    public static void clickTrackFieldManager(){trackPage.clickTrackFieldManager();}
    public static void clickTrackSettings(){trackPage.clickTrackSettings();}

    public static void typeRouteName(String routeName){
        trackPage.typeRouteName(routeName);
    }

    public static void selectOptionDriverDropDown(String driverName){
        trackPage.clickDriverDropDown();
        trackPage.selectOptionDriverDropDown(driverName);
    }

    public static void typeStartTime(String routeName){
        trackPage.typeStartTime(routeName);
    }

    public static void selectOptionTruckDropDown(String truckName){
        trackPage.clickTruckDropDown();
        trackPage.selectOptionTruckDropDown(truckName);
    }

    public static void deleteExistingRoute() throws InterruptedException {
        if (!trackPage.isUploadRouteTextDisplayed()) {
            trackPage.clickDeleteRoute();
        }
    }

    public static void clickOkIfErrorTextDisplayed() throws InterruptedException {
        if (trackPage.isErrorTextDisplayed()){
            trackPage.clickOK();
        }
    }

    public static String generateRandomCode(){
        return trackPage.generateRandomCode();
    }

    public static boolean isMapDisplayed(){
        return trackPage.isMapDisplayed();
    }

    public static void clickUnhideButton(){
        trackPage.clickUnhideButton();
    }

    public static boolean isTxtSettingsDisplayed(){
        return trackPage.isTxtSettingsDisplayed();
    }

    public static void clickBtnDispatch(){
        trackPage.clickBtnDispatch();
    }

    public static void typeStartTimeDispatched(String time){
        trackPage.typeStartTimeDispatched(time);
    }

    public static void selectTruckNameDispatch(String truckName){
        trackPage.clickTruckNameDispatchDropDown();
        trackPage.selectTruckNameDispatch(truckName);
    }

    public static void selectDriverDispatch(String driverName){
        trackPage.clickDriverDispatchedDropDown();
        trackPage.selectDriverDispatch(driverName);
    }

    public static void clickBtnDispatch1(){
        trackPage.clickBtnDispatch1();
    }

    public static void ifAreYouSureDisplayed(){
        if (trackPage.isAreYouSureDisplayed()){
            trackPage.clickYesButton();
        }
    }

    public static void clickBtnSaveChanges(){
        trackPage.clickBtnSaveChanges();
    }

    public static void selectDate(String Day, String Month, String Date, String Year) {
        trackPage.clickDatePicker();
        trackPage.selectDate(Day, Month, Date, Year);
    }

    public static void selectDateMonitoring(String Day, String Month, String Date, String Year) {
        trackPage.clickDatePickerMonitoring();
        trackPage.selectDate(Day, Month, Date, Year);
    }

    public static void getDisplayedDate(){
        trackPage.getDisplayedDate();
    }

    public static void selectOptionWarehouse(String warehouse){
        trackPage.clickDropdownWarehouse();
        trackPage.selectDropdownItem(warehouse);
    }

    public static void clickDatePickerMonitoring(){
        trackPage.clickDatePickerMonitoring();
    }
}
