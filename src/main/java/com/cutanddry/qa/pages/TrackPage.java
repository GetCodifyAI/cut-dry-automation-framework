package com.cutanddry.qa.pages;

import org.openqa.selenium.By;

public class TrackPage extends LoginPage{
    By txt_trackResources = By.xpath("//li[contains(text(),'Resources')]");
    By btn_addDriver = By.xpath("//button[@type='button' and contains(text(), 'Add Driver')]");
    By btn_addTrucks = By.xpath("//button[@type='button' and contains(text(), 'Add Truck')]");
    By tb_drivers =By.xpath("//a[@role='tab' and @data-rb-event-key='Drivers' and text()='Drivers']");
    By tb_trucks =By.xpath("//a[@role='tab' and @data-rb-event-key='Trucks' and text()='Trucks']");
    By txt_trackRoutes = By.xpath("//li[contains(text(),'Routes')]");
    By txt_trackMon = By.xpath("//li[contains(text(),'Monitoring')]");
    By txt_trackNotif = By.xpath("//li[contains(text(),'Notifications')]");
    By txt_addDriver = By.xpath("//div[contains(text(), 'Add Driver')]");
    By txt_addTruck = By.xpath("//div[contains(text(), 'Add Truck')]");
    By lbl_name = By.xpath("//input[@placeholder='First Last']");
    By lbl_nameTruck = By.xpath("//input[@placeholder='Truck Name/ID']");
    By lbl_phone = By.xpath("//input[@placeholder='12223334444']");
    By lbl_searchDriver = By.xpath("//input[@placeholder='Search Drivers']");
    By lbl_searchTruck = By.xpath("//input[@placeholder='Search Trucks']");
    By lbl_loc = By.xpath("//input[@placeholder='First Last']");
    By btn_inviteUser = By.xpath("//button[text()='Invite User']");
    String btn_editUser = "//tr[td[text()='USER']]/td/following-sibling::td//*[local-name()='svg']";
    By txt_editUser = By.xpath("//div[contains(text(), 'Edit')]");
    By lbl_removeUser = By.xpath("//a[contains(text(), 'Remove')]");
    By btn_OK = By.xpath("//button[text()='OK']");
    By txt_removeUser = By.xpath("//div[text()='Removing this user will result in the following:']");
    By btn_removeUser = By.xpath("//button[contains(text(), 'Remove')]");
    By btn_saveChange = By.xpath("//button[text()='Save Changes']");
    String txt_searchUser = "//tr[1][td[text()='USER']]";
    By btn_notifExample = By.xpath("(//button[contains(text(), 'Notification Example')])[1]");
    By txt_exPopup = By.xpath("//div[text()='Track Welcome Message']");
    By tb_SMS =By.xpath("//a[@role='tab' and text()='SMS']");
    By tb_email =By.xpath("//a[@role='tab' and text()='Email']");
    By tb_push =By.xpath("//a[@role='tab' and text()='Push']");
    By txt_smsTemp = By.xpath("//h6[contains(text(),'SMS Template')]");
    By txt_emailTemp = By.xpath("//h6[contains(text(),'Email Template')]");
    By txt_pushTemp = By.xpath("//h6[contains(text(),'Push Template')]");
    By txt_smsPrev = By.xpath("//h6[contains(text(),'SMS Preview')]");
    By txt_emailPrev = By.xpath("//h6[contains(text(),'Email Preview')]");
    By txt_pushPrev = By.xpath("//h6[contains(text(),'Push Preview')]");
    By manageRoutesDropDown = By.xpath("//button[@type='button' and contains(text(), 'Manage Routes')]");
    By importRoutesOption = By.xpath("//a[contains(text(), 'Import Routes (.csv, .xlsx)')]");
    By deleteRoutes = By.xpath("//a[contains(text(), 'Delete Routes')]");
    By btn_deleteRoutes = By.xpath("//button[contains(text(), 'Delete Routes ')]");
    By btn_delete =By.xpath("//button[text()= 'Delete']");
    By txt_stops = By.xpath("//div//span[text()='2' and contains(@class,'font-weight-bold')]");
    By txt_startDateAndTime =By.xpath("//div/span[contains(text(), '4:30 AM')]");
    By txt_driverName = By.xpath("//div[contains(@class, 'text-align-left') and contains(@class, 'col')]//text()[contains(., 'Enlightened Ekadant')]");
    By txt_truckDistanceAndValue = By.xpath("//text()[preceding-sibling::text()[contains(., 'Enlightened Ekadant')]]");
    String btn_editRoute = "(//*[name()='svg' and @data-icon='EDIT_FUNCTION'])[1]";
    By btn_routeCode = By.xpath("//button[contains(text(), '123')]");
    By txt_routeCodePopUp = By.xpath("//div[contains(text(), 'Code copied to clipboard!')]");
    By lbl_routeName = By.xpath("//label[text()='Route Name:']//following-sibling::input[contains(@class, 'form-control')]");
    By txt_editRoute = By.xpath("//div[contains(text(), 'Edit Route')]");
    String editRouteName = "//strong[text()='NAME']";
    By txt_routeEditedPopup = By.xpath("//h2[contains(text(), 'Route Edited!')]");
    By txt_addStop = By.xpath("//div[contains(text(), 'Add Stop')]");
    By lbl_customerName = By.xpath("//label[text()='Customer Name:']//following-sibling::input[contains(@class, 'form-control')]");
    By lbl_customerCode = By.xpath("//label[text()='Customer Code:']//following-sibling::input[contains(@class, 'form-control')]");
    By lbl_addressStreet = By.xpath("//label[text()='Address:']//following-sibling::input[contains(@class, 'form-control')]");
    By lbl_city = By.xpath("//label[text()='Address:']//following-sibling::div//input[contains(@placeholder, 'City')]");
    By lbl_state = By.xpath("//label[text()='Address:']//following-sibling::div//input[contains(@placeholder, 'State')]");
    By lbl_zipCode = By.xpath("//label[text()='Address:']//following-sibling::div//input[contains(@placeholder, 'Zip')]");
    String addStop = "//td[2]//div[contains(text(), 'CODE')]";
    By lbl_placeBreakAfterStop = By.xpath("//label[text()='Place break after stop...']//following-sibling::div");
    By placeBreakAfterStopOption = By.xpath("//div[contains(text(),'Nova')]");
    By btn_addBreak = By.xpath("//button[contains(text(), 'Add Break')]");
    By upload_file = By.xpath("//input[@type='file']");
    By txt_uploadRoute = By.xpath("//h5[contains(text(),'Click here to upload or drag and drop a CSV or Excel file to add routes')]");



    public boolean isTrackResourcesTextDisplayed(){
        try {
            distributorUI.waitForVisibility(txt_trackResources);
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(txt_trackResources);
    }
    public boolean isAddDriversBtnDisplayed(){
        distributorUI.waitForVisibility(btn_addDriver);
        return distributorUI.isDisplayed(btn_addDriver);
    }
    public boolean isAddTrucksBtnDisplayed(){
        distributorUI.waitForVisibility(btn_addTrucks);
        return distributorUI.isDisplayed(btn_addTrucks);
    }
    public void clickOnTrucks() {
        distributorUI.waitForVisibility(tb_trucks);
        distributorUI.click(tb_trucks);
    }
    public void clickOnDrivers() {
        distributorUI.waitForVisibility(tb_drivers);
        distributorUI.click(tb_drivers);
    }
    public boolean isTrackRoutesTextDisplayed(){
        try {
            distributorUI.waitForVisibility(txt_trackRoutes);
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(txt_trackRoutes);
    }
    public boolean isTrackMonitoringTextDisplayed(){
        try {
            distributorUI.waitForVisibility(txt_trackMon);
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(txt_trackMon);
    }
    public boolean isTrackNotificationsTextDisplayed(){
        try {
            distributorUI.waitForVisibility(txt_trackNotif);
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(txt_trackNotif);
    }
    public boolean isAddDriverPopupDisplayed(){
        try {
            distributorUI.waitForVisibility(txt_addDriver);
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(txt_addDriver);
    }
    public boolean isAddTruckPopupDisplayed(){
        try {
            distributorUI.waitForVisibility(txt_addTruck);
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(txt_addTruck);
    }
    public void clickOnAddDrivers() {
        distributorUI.waitForVisibility(btn_addDriver);
        distributorUI.click(btn_addDriver);
    }
    public void clickOnAddTrucks() {
        distributorUI.waitForVisibility(btn_addTrucks);
        distributorUI.click(btn_addTrucks);
    }
    public void enterTruckName(String name){
        distributorUI.clear(lbl_nameTruck);
        distributorUI.sendKeys(lbl_nameTruck,name);
    }
    public void enterName(String name){
        distributorUI.clear(lbl_name);
        distributorUI.sendKeys(lbl_name,name);
    }
    public void enterPhone(String phone) throws InterruptedException {
        distributorUI.waitForCustom(2000);
        distributorUI.clear(lbl_phone);
        distributorUI.sendKeysAndEnter(lbl_phone,phone);
        distributorUI.waitForCustom(2000);
    }
    public void clickOnInviteUser() throws InterruptedException {
        distributorUI.waitForCustom(3000);
        distributorUI.waitForVisibility(btn_inviteUser);
        distributorUI.click(btn_inviteUser);
        distributorUI.clickUsingJavaScript(btn_inviteUser);
    }
    public boolean isUserDisplayed(String user) throws InterruptedException {
        try {
            distributorUI.waitForCustom(4000);
            distributorUI.waitForVisibility(By.xpath(btn_editUser.replace("USER", user)));
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(By.xpath(btn_editUser.replace("USER", user)));
    }
    public void clickOnEditUser(String user) throws InterruptedException {
        distributorUI.scrollToElement(By.xpath(btn_editUser.replace("USER", user)));
        distributorUI.waitForCustom(3000);
        try {
            distributorUI.click(By.xpath(btn_editUser.replace("USER", user)));
        } catch (Exception e) {
            distributorUI.refreshPage();
            distributorUI.click(By.xpath(btn_editUser.replace("USER", user)));
        }
    }
    public boolean isEditUserPopupDisplayed(){
        distributorUI.waitForVisibility(txt_editUser);
        return distributorUI.isDisplayed(txt_editUser);
    }
    public void clickOK() throws InterruptedException {
        distributorUI.waitForClickability(btn_OK);
        distributorUI.click(btn_OK);
        distributorUI.waitForCustom(4000);
    }
    public boolean isRemoveUserPopupDisplayed(){
        distributorUI.waitForVisibility(txt_removeUser);
        return distributorUI.isDisplayed(txt_removeUser);
    }
    public void clickOnRemoveUser() {
        try {
            distributorUI.waitForCustom(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        distributorUI.click(btn_removeUser);
    }
    public void clickOnRemoveUserLabel() throws InterruptedException {
        distributorUI.waitForCustom(3000);
        distributorUI.waitForClickability(lbl_removeUser);
        distributorUI.click(lbl_removeUser);
    }
    public void clickOnSaveChanges() throws InterruptedException {
        distributorUI.waitForVisibility(btn_saveChange);
        distributorUI.click(btn_saveChange);
        distributorUI.waitForVisibility(btn_saveChange);
        distributorUI.waitForCustom(1000);
    }
    public void searchDriver(String name){
        distributorUI.clear(lbl_searchDriver);
        distributorUI.sendKeys(lbl_searchDriver,name);
    }
    public void searchTrucks(String name){
        distributorUI.clear(lbl_searchTruck);
        distributorUI.sendKeys(lbl_searchTruck,name);
    }
    public boolean isSearchDisplayed(String user) throws InterruptedException {
        try {
            distributorUI.waitForCustom(3000);
            distributorUI.waitForVisibility(By.xpath(txt_searchUser.replace("USER", user)));
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(By.xpath(txt_searchUser.replace("USER", user)));
    }
    public void clickOnNotifExample() {
        distributorUI.waitForVisibility(btn_notifExample);
        distributorUI.click(btn_notifExample);
    }
    public boolean isNotificationExamplePopupDisplayed(){
        try {
            distributorUI.waitForVisibility(txt_exPopup);
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(txt_exPopup);
    }
    public void clickOnSMS() {
        distributorUI.waitForVisibility(tb_SMS);
        distributorUI.click(tb_SMS);
    }
    public void clickOnEmail() {
        distributorUI.waitForVisibility(tb_email);
        distributorUI.click(tb_email);
    }
    public void clickOnPush() {
        distributorUI.waitForVisibility(tb_push);
        distributorUI.click(tb_push);
    }
    public boolean isSMSTemplateAndPreviewDisplayed(){
        distributorUI.waitForVisibility(txt_smsTemp);
        distributorUI.waitForVisibility(txt_smsPrev);
        return distributorUI.isDisplayed(txt_smsTemp) && distributorUI.isDisplayed(txt_smsPrev);
    }
    public boolean isEmailTemplateAndPreviewDisplayed(){
        distributorUI.waitForVisibility(txt_emailTemp);
        distributorUI.waitForVisibility(txt_emailPrev);
        return distributorUI.isDisplayed(txt_emailTemp) && distributorUI.isDisplayed(txt_emailPrev);
    }
    public boolean isPushTemplateAndPreviewDisplayed(){
        distributorUI.waitForVisibility(txt_pushTemp);
        distributorUI.waitForVisibility(txt_pushPrev);
        return distributorUI.isDisplayed(txt_pushTemp) && distributorUI.isDisplayed(txt_pushPrev);
    }
    public void clickRouteCode(){
        distributorUI.click(btn_routeCode);
    }
    public boolean isRouteCodePopupDisplayed(){
        try {
            distributorUI.waitForVisibility(txt_routeCodePopUp);
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(txt_routeCodePopUp);
    }
    public void clickEditRouteFunction(String editFunction){
        distributorUI.waitForVisibility(By.xpath(btn_editRoute.replace("EDIT_FUNCTION", editFunction)));
        distributorUI.click(By.xpath(btn_editRoute.replace("EDIT_FUNCTION", editFunction)));
    }
    public boolean isEditRoutePopupDisplayed(){
        try {
            distributorUI.waitForVisibility(txt_editRoute);
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(txt_editRoute);
    }
    public void editRouteName(String name){
        distributorUI.click(lbl_routeName);
        distributorUI.clear(lbl_routeName);
        distributorUI.sendKeys(lbl_routeName,name);
    }
    public boolean isRouteEditedPopupDisplayed(){
        try {
            distributorUI.waitForVisibility(txt_routeEditedPopup);
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(txt_routeEditedPopup);
    }
    public boolean isEditRouteNameDisplayed(String name){
        try {
            distributorUI.waitForVisibility(By.xpath(editRouteName.replace("NAME", name)));
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(By.xpath(editRouteName.replace("NAME", name)));
    }
    public boolean isAddStopPopupDisplayed(){
        try {
            distributorUI.waitForVisibility(txt_addStop);
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(txt_addStop);
    }
    public void addCustomerName(String name){
        distributorUI.click(lbl_customerName);
        distributorUI.clear(lbl_customerName);
        distributorUI.sendKeys(lbl_customerName,name);
    }
    public void addCustomerCode(String code){
        distributorUI.click(lbl_customerCode);
        distributorUI.clear(lbl_customerCode);
        distributorUI.sendKeys(lbl_customerCode,code);
    }
    public void addAddressStreet(String street){
        distributorUI.click(lbl_addressStreet);
        distributorUI.clear(lbl_addressStreet);
        distributorUI.sendKeys(lbl_addressStreet,street);
    }
    public void addAddressCity(String city){
        distributorUI.click(lbl_city);
        distributorUI.clear(lbl_city);
        distributorUI.sendKeys(lbl_city,city);
    }
    public void addAddressState(String state){
        distributorUI.click(lbl_state);
        distributorUI.clear(lbl_state);
        distributorUI.sendKeys(lbl_state,state);
    }
    public void addAddressZipCode(String zipCode){
        distributorUI.click(lbl_zipCode);
        distributorUI.clear(lbl_zipCode);
        distributorUI.sendKeys(lbl_zipCode,zipCode);
    }
    public boolean isRouteStopAdded(String code){
        try {
            distributorUI.waitForVisibility(By.xpath(addStop.replace("CODE", code)));
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(By.xpath(addStop.replace("CODE", code)));
    }
    public void clickRouteName(String name){
        distributorUI.refreshPage();
        distributorUI.waitForVisibility(By.xpath(editRouteName.replace("NAME", name)));
        distributorUI.click(By.xpath(editRouteName.replace("NAME", name)));
    }
    public void addBreak(){
        distributorUI.click(lbl_placeBreakAfterStop);
        distributorUI.waitForVisibility(placeBreakAfterStopOption);
        distributorUI.click(placeBreakAfterStopOption);
        distributorUI.click(btn_addBreak);
        distributorUI.refreshPage();
    }
    public boolean isPlaceBreakAfterStopAdded(String name){
        try {
            distributorUI.waitForVisibility(By.xpath(btn_editRoute.replace("EDIT_FUNCTION", name)));
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(By.xpath(btn_editRoute.replace("EDIT_FUNCTION", name)));
    }
    public void clickManageRoute(){
        distributorUI.click(manageRoutesDropDown);
    }
    public void clickImportRoute(){
        distributorUI.click(importRoutesOption);
    }
    public boolean validateStops(){
        try {
            distributorUI.waitForVisibility(txt_stops);
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(txt_stops);
    }
    public boolean validateStartDateAndTime(){
        try {
            distributorUI.waitForVisibility(txt_startDateAndTime);
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(txt_startDateAndTime);
    }
    public boolean validateDriver(){
        try {
            distributorUI.waitForVisibility(txt_driverName);
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(txt_driverName);
    }
    public boolean validateTruckDistanceAndValue(){
        try {
            distributorUI.waitForVisibility(txt_truckDistanceAndValue);
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(txt_truckDistanceAndValue);
    }
    public void giveFilePath(String path){
        distributorUI.sendKeysToHiddenElements(upload_file, path);
    }
    public boolean isUploadRouteTextDisplayed(){
        try {
            distributorUI.waitForVisibility(txt_uploadRoute);
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(txt_uploadRoute);
    }
    public void clickDeleteRoute()throws InterruptedException{
        distributorUI.click(deleteRoutes);
        distributorUI.waitForVisibility(btn_deleteRoutes);
        distributorUI.click(btn_deleteRoutes);
        distributorUI.waitForVisibility(btn_delete);
        distributorUI.click(btn_delete);
        distributorUI.waitForCustom(2000);
    }



}
