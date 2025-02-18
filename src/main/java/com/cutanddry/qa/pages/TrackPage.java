package com.cutanddry.qa.pages;

import org.openqa.selenium.By;

import java.util.Random;

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
    By txt_driverName = By.xpath("//div[@class='text-align-left col']/text()[normalize-space()]");
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
    String addStop = "//td//div[contains(text(), 'CODE')]";
    By lbl_placeBreakAfterStop = By.xpath("//label[text()='Place break after stop...']//following-sibling::div");
    By placeBreakAfterStopOption = By.xpath("//div[contains(text(),'Nova')]");
    By dropdownOptionsContainer = By.xpath("//div[contains(@class, '-menu')]");
    By firstOption = By.xpath("(//div[contains(@id, 'react-select') and contains(@class, '-option')])[1]");
    By btn_addBreak = By.xpath("//button[contains(text(), 'Add Break')]");
    By upload_file = By.xpath("//input[@type='file']");
    By txt_uploadRoute = By.xpath("//h5[contains(text(),'Click here to upload or drag and drop a CSV or Excel file to add routes')]");
    String txt_visibilityMap = "//strong[contains(text(),'STATUS')]";
    By mapHiddenStatus = By.xpath("//span[@data-tip='Hidden on map. Click to show']");
    By mapVisibleStatus = By.xpath("//span[@data-tip='Visible on map. Click to hide']");
    By btn_addStop = By.xpath("//span[contains(text(),'add stops')]");
    String unassignedStop = "//strong[contains(text(),'Unassigned Stops')]/parent::div/following-sibling::*//div[contains(text(),'CODE')]";
    By btn_editUnassignedStop = By.xpath("//strong[contains(text(),'Unassigned Stops')]/parent::div/following-sibling::*//*[name()='svg' and @data-icon='pencil']");
    By btn_deleteStop = By.xpath("//button[text()='Delete Stop']");
    By txt_removeStopPopUp = By.xpath("//div[contains(text(),'remove this stop?')]");
    By btn_yes = By.xpath("//button[contains(text(),'Yes')]");
    By txt_dispatchToDrivers = By.xpath("//div[contains(text(),'Dispatch to Drivers')]");
    By btn_dispatch = By.xpath("//button[contains(text(),'Dispatch')]//*[name()='svg' and @data-icon='paper-plane']");
    By txt_areYouSureToDispatch = By.xpath("//h2[contains(text(),'Are you sure you want to dispatch?')]");
    By txt_dispatchSuccess = By.xpath("//h2[contains(text(),'Drivers Dispatched Successfully')]");
    By lbl_dispatch = By.xpath("//span[text()='Dispatched']");
    By btn_manageRoutes = By.xpath("//button[text()='Manage Routes']");
    By btn_importRoutes = By.xpath("//a[text()='Import Routes (.csv, .xlsx)']");
    By btn_addNewRoute = By.xpath("//a[text()='Add a New Route']");
    By btn_downloadExampleFile= By.xpath("//a[text()='Download Example File']");
    By btn_trackFieldManager= By.xpath("//a[text()='Track Field Manager']");
    By btn_trackSettings= By.xpath("//a[text()='Track Settings']");
    By tbx_routeName = By.xpath("//div[label[text()='Route Name:']]//input[@class='form-control']");
    By dropDown_driver = By.xpath("//div[label[text()='Driver:']]//div[contains(@class, 'css-yk16xz-control')]");
    String option_dropDownDriver = "//div[contains(@class, 'css-2b097c-container')]//div[text()='Item Name']";
    By tbx_startTime = By.xpath("//div[label[text()='Start Time:']]//input[@type='time']");
    By dropDown_truck = By.xpath("//div[label[text()='Truck:']]//div[contains(@class, 'css-yk16xz-control')]");
    String option_dropDownTruck = "//div[contains(@class, 'css-2b097c-container')]//div[text()='Item Name']";
    By txt_thereWasAnError = By.xpath("//h2[text()='There was an error, please try again.']");
    By btn_saveChanges = By.xpath("//button[@type='button' and contains(text(), 'Save Changes')]");
    By map_routes = By.xpath("//div[contains(@style, 'z-index: 3; position: absolute;') and contains(@style, 'height: 100%; width: 100%;')]");
    String eyeMark_FieldManager = "//table[@class='table']/tbody/tr[ROW_NUMBER]/td[5]";
    By txt_settings = By.xpath("//h2[contains(text(), ' Settings')]");
    By btn_Dispatch = By.xpath("//button[contains(@class, 'btn-primary') and contains(text(), 'Dispatch')]");
    By tbx_startTimeDispatch = By.xpath("//input[@type='time' and contains(@class, 'form-control')]");
    By dropDown_truckNameDispatch = By.xpath("//th[contains(@class, '_legy2v')]//div[contains(@class, 'themed_select__control') and .//div[contains(@class, 'themed_select__single-value')]]");
    String optionDropDown_truckNameDispatch = "//div[contains(@class, 'themed_select__menu')]//div[contains(@class, 'themed_select__option') and normalize-space()='ITEM_TEXT']";
    By dropDown_driverDispatched = By.xpath("//th[contains(@class, '_legy2v')]//div[contains(@class, 'themed_select__control') and .//div[contains(@class, 'themed_select__single-value') and normalize-space()='Enlightened Ekadant']]");
    String optionDropDown_driverDispatched = "//div[contains(@class, 'themed_select__menu')]//div[contains(@class, 'themed_select__option') and normalize-space()='ITEM_TEXT']";
    By btn_dispatch1 = By.xpath("//button[contains(@class, 'btn btn-primary') and contains(., 'Dispatch (1)')]");
    By txt_areYouSure= By.xpath("//h2[@class='swal2-title' and contains(text(), 'Are you sure')]");
    By btn_datePicker = By.xpath("//input[@type='text' and contains(@class,'form-control')]");
    String datePicker = "//div[@class='react-datepicker']//div[@aria-label='Choose %s, %s %s%s, %s']";
    By btn_previousMonth = By.xpath("//button[@type='button' and @aria-label='Previous Month']");
    By btn_nextMonth = By.xpath("//button[@type='button' and @aria-label='Next Month']");
    By dropdown_warehouse = By.xpath("//div[contains(@class, 'themed_select__control') and contains(@class, 'css-yk16xz-control')]");
    String dropdownItemXPath = "//div[contains(@class, 'themed_select__menu')]//div[text()='{}']";
    By datePicker_monitoring = By.xpath("//input[@type='text' and contains(@class, 'form-control') and contains(@class, '_rfglfk')]");
    String monitorRouteName = "//div[contains(text(),'NAME')]";
    String customerColumn = "//th[contains(text(),'Customer')]/../../following-sibling::*//td[text()='NAME']";
    String customerName = "//div[contains(text(),'NAME')]";
    By clickClose = By.xpath("//span[contains(text(),'×')]");
    String dateMonitor = "//input[@type='text' and contains(@class,'form-control') and @value='DATE']";
    String orderId = "//th[contains(text(),'Order')]/../../following-sibling::*//td[text()='ID']";
    String customerStop = "//th[contains(text(),'Stop')]/../../following-sibling::*//td[text()='STOP']";
    String stopDisplay = "//h4[contains(text(),'STOP')]";

    public void clickDatePickerMonitoring(){
        distributorUI.click(datePicker_monitoring);
    }

    public void selectDropdownItem(String item) {
        By dropdownItem = By.xpath(dropdownItemXPath.replace("{}", item));
        distributorUI.click(dropdownItem);
    }

    public String getDisplayedWarehouse(){
        String warehouse = distributorUI.getText(dropdown_warehouse);
        return warehouse;
    }

    public void clickDropdownWarehouse(){
        distributorUI.click(dropdown_warehouse);
    }

//    public void selectOptionDropdownWarehouse(String warehouse){
//        By optionWarehouse = By.xpath(option_dropdownWarehouse.replace("ITEM_TEXT",warehouse));
//        distributorUI.click(optionWarehouse);
//    }

    public void getDisplayedDate(){
        String displayedDate = distributorUI.getText(btn_datePicker);
        System.out.println("The date is " + displayedDate);
    }
    public void clickBtnNextMonth(){
        distributorUI.click(btn_nextMonth);
    }

    public void clickBtnPreviousMonth(){
        distributorUI.click(btn_previousMonth);
    }

    private String getDateSuffix(String date) {
        int day = Integer.parseInt(date);
        if (day >= 11 && day <= 13) {
            return "th"; // Special case for 11th, 12th, 13th
        }
        switch (day % 10) {
            case 1: return "st";
            case 2: return "nd";
            case 3: return "rd";
            default: return "th";
        }
    }

    public void selectDate(String day, String month, String date, String year) {
        String suffix = getDateSuffix(date);
        String formattedDate = String.format(datePicker, day, month, date, suffix, year);
        By startDate = By.xpath(formattedDate);
        int maxAttempts = 20;
        boolean dateFound = false;

        for (int i = 0; i < maxAttempts; i++) {
            if (distributorUI.isDisplayed(startDate)) {
                distributorUI.click(startDate);
                dateFound = true;
                break;
            }

            if (i < 12) {
                clickBtnPreviousMonth(); // Try navigating backward in the first few attempts
            } else {
                clickBtnNextMonth(); // Switch to navigating forward
            }
        }

        if (!dateFound) {
            throw new RuntimeException("Start date could not be found within the allowed attempts.");
        }
    }

    public void clickDatePicker(){
        distributorUI.click(btn_datePicker);
    }

    public boolean isAreYouSureDisplayed(){
        return distributorUI.isDisplayed(txt_areYouSure);
    }

    public void clickBtnDispatch1(){
        distributorUI.click(btn_dispatch1);
    }

    public void clickDriverDispatchedDropDown(){
        distributorUI.click(dropDown_driverDispatched);
    }

    public void selectDriverDispatch(String trackName){
        By driverOption = By.xpath(optionDropDown_driverDispatched.replace("ITEM_TEXT",trackName));
        distributorUI.click(driverOption);
    }


    public void clickTruckNameDispatchDropDown(){
        distributorUI.click(dropDown_truckNameDispatch);
    }

    public void selectTruckNameDispatch(String trackName){
        By truckNameOption = By.xpath(optionDropDown_truckNameDispatch.replace("ITEM_TEXT",trackName));
        distributorUI.click(truckNameOption);
    }

    public void typeStartTimeDispatched(String time){
        distributorUI.sendKeys(tbx_startTimeDispatch, time);
    }

    public void clickBtnDispatch(){
        distributorUI.click(btn_Dispatch);
    }
    public boolean isTxtSettingsDisplayed(){
        return distributorUI.isDisplayed(txt_settings);
    }
    

    public void clickUnhideButton() {
        for (int i = 1; i <= 20; i++) { // Fixed loop syntax
            By eyeMark = By.xpath(eyeMark_FieldManager.replace("ROW_NUMBER", String.valueOf(i)));

            try {
                distributorUI.click(eyeMark);
            } catch (Exception e) {
                System.out.println("Failed to click element on row: " + i + ". " + e.getMessage());
            }
        }
    }

    public void clickTrackFieldManager(){
        distributorUI.click(btn_trackFieldManager);
    }

    public void clickTrackSettings(){
        distributorUI.waitForVisibility(btn_trackSettings);
        distributorUI.click(btn_trackSettings);
    }

    public boolean isMapDisplayed(){
        return distributorUI.isDisplayed(map_routes);
    }

    public void clickBtnSaveChanges(){
        distributorUI.click(btn_saveChanges);
    }
    public boolean isErrorTextDisplayed(){
        return distributorUI.isDisplayed(txt_thereWasAnError);
    }

    public void typeRouteName(String routeName){
        distributorUI.sendKeys(tbx_routeName,routeName);
    }

    public void clickDriverDropDown(){
        distributorUI.click(dropDown_driver);
    }

    public void selectOptionDriverDropDown(String driver){
        By optionDriver = By.xpath(option_dropDownDriver.replace("Item Name",driver));
        distributorUI.click(optionDriver);
    }

    public void typeStartTime(String startTime){
        distributorUI.sendKeys(tbx_startTime,startTime);
    }

    public void clickTruckDropDown(){
        distributorUI.click(dropDown_truck);
    }

    public void selectOptionTruckDropDown(String truck){
        By optionTruck = By.xpath(option_dropDownTruck.replace("Item Name",truck));
        distributorUI.click(optionTruck);
    }

    public void clickBtnAddNewRoutes(){
        distributorUI.click(btn_addNewRoute);
    }

    public void clickBtnDownloadExampleFile(){
        distributorUI.click(btn_downloadExampleFile);
    }

    public void clickBtnImportRoutes(){
        distributorUI.click(btn_importRoutes);
    }

    public void clickBtnManageRoutes(){
        distributorUI.click(btn_manageRoutes);
    }

    public boolean isTrackResourcesTextDisplayed(){
        try {
            distributorUI.waitForVisibility(txt_trackResources);
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(txt_trackResources);
    };
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
    public void clickOnTitle() {
        distributorUI.click(txt_trackRoutes);
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
        distributorUI.waitForCustom(3000);
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
//        distributorUI.waitForVisibility(placeBreakAfterStopOption);
//        distributorUI.click(placeBreakAfterStopOption);
        distributorUI.waitForVisibility(dropdownOptionsContainer);
        distributorUI.click(firstOption);
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
        try {
            distributorUI.waitForCustom(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
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
    public void clickToShowMap(){
        distributorUI.click(mapHiddenStatus);
    }
    public void clickToHiddenMap(){
        distributorUI.click(mapVisibleStatus);
    }
    public boolean isMapVisible(){
        try {
            distributorUI.waitForVisibility(mapVisibleStatus);
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(mapVisibleStatus);
    }
    public boolean isMapHidden(){
        try {
            distributorUI.waitForVisibility(mapHiddenStatus);
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(mapHiddenStatus);
    }
    public boolean mapVisibleStatus(String status){
        try {
            distributorUI.waitForVisibility(By.xpath(txt_visibilityMap.replace("STATUS", status)));
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(By.xpath(txt_visibilityMap.replace("STATUS", status)));
    }
    public void clickAddStop(){
        distributorUI.click(btn_addStop);
    }
    public boolean isUnassignedStopAdded(String code){
        try {
            distributorUI.waitForVisibility(By.xpath(unassignedStop.replace("CODE", code)));
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(By.xpath(unassignedStop.replace("CODE", code)));
    }
    public void clickEditUnassignStop(){
        distributorUI.click(btn_editUnassignedStop);
    }
    public void clickDeleteStop(){
        distributorUI.click(btn_deleteStop);
    }
    public boolean isRemoveStopPopUpDisplayed(){
        try {
            distributorUI.waitForVisibility(txt_removeStopPopUp);
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(txt_removeStopPopUp);
    }
    public void clickYesButton(){
        distributorUI.click(btn_yes);
    }
    public boolean isRemoveStopSuccessfully(){
        try {
            distributorUI.waitForVisibility(btn_addStop);
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(btn_addStop);
    }
    public boolean isDispatchRoutePopupDisplayed(){
        try {
            distributorUI.waitForVisibility(txt_dispatchToDrivers);
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(txt_dispatchToDrivers);
    }
    public void clickDispatch(){
        distributorUI.click(btn_dispatch);
    }
    public boolean AreYouSureToDispatchDisplayed(){
        try {
            distributorUI.waitForVisibility(txt_areYouSureToDispatch);
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(txt_areYouSureToDispatch);
    }
    public boolean dispatchSuccessDisplayed(){
        try {
            distributorUI.waitForVisibility(txt_dispatchSuccess);
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(txt_dispatchSuccess);
    }
    public boolean isDispatchedTextDisplayed(){
        try {
            distributorUI.waitForVisibility(lbl_dispatch);
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(lbl_dispatch);
    }

    public String generateRandomCode() {
        Random random = new Random();
        int randomCode = 10000 + random.nextInt(90000); // Generates a number between 10000 and 99999
        return String.valueOf(randomCode);
    }
    public void clickMonitorRouteName(String name)throws InterruptedException{
        distributorUI.waitForVisibility(By.xpath(monitorRouteName.replace("NAME", name)));
        distributorUI.click(By.xpath(monitorRouteName.replace("NAME", name)));
        distributorUI.waitForCustom(5000);
    }
    public boolean isCustomerColumnTextDisplayed(String name)throws InterruptedException{
        try {
            distributorUI.waitForVisibility(By.xpath(customerColumn.replace("NAME", name)));
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(By.xpath(customerColumn.replace("NAME", name)));
    }
    public void clickMonitorCustomer(String name)throws InterruptedException{
        distributorUI.waitForVisibility(By.xpath(customerColumn.replace("NAME", name)));
        distributorUI.click(By.xpath(customerColumn.replace("NAME", name)));
        distributorUI.waitForCustom(5000);
    }
    public boolean isMonitorCustomerNameDisplayed(String name)throws InterruptedException{
        try {
            distributorUI.waitForVisibility(By.xpath(customerName.replace("NAME", name)));
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(By.xpath(customerName.replace("NAME", name)));
    }
    public void clickCloseCustomerDetails()throws InterruptedException{
        distributorUI.click(clickClose);
    }
    public boolean isMonitorDateDisplayed(String date)throws InterruptedException{
        try {
            distributorUI.waitForVisibility(By.xpath(dateMonitor.replace("DATE", date)));
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(By.xpath(dateMonitor.replace("DATE", date)));
    }
    public boolean isOrderIdColumnTextDisplayed(String id)throws InterruptedException{
        try {
            distributorUI.waitForVisibility(By.xpath(orderId.replace("ID", id)));
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(By.xpath(orderId.replace("ID", id)));
    }
    public boolean isCustomerStopColumnTextDisplayed(String stop)throws InterruptedException{
        try {
            distributorUI.waitForVisibility(By.xpath(customerStop.replace("STOP", stop)));
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(By.xpath(customerStop.replace("STOP", stop)));
    }
    public boolean isMonitorCustomerStopDisplayed(String stop)throws InterruptedException{
        try {
            distributorUI.waitForVisibility(By.xpath(stopDisplay.replace("STOP", stop)));
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(By.xpath(stopDisplay.replace("STOP", stop)));
    }



}
