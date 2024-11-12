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
    By txt_editUser = By.xpath("//div[text()='Edit User']");
    By lbl_removeUser = By.xpath("//a[text()='Remove user']");
    By btn_OK = By.xpath("//button[text()='OK']");
    By txt_removeUser = By.xpath("//div[text()='Removing this user will result in the following:']");
    By btn_removeUser = By.xpath("//button[text()='Remove User']");
    By btn_saveChange = By.xpath("//button[text()='Save Changes']");
    String txt_searchUser = "//tr[1][td[text()='USER']]";

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
        distributorUI.waitForClickability(btn_removeUser);
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
    public boolean isSearchDisplayed(String user) throws InterruptedException {
        try {
            distributorUI.waitForCustom(3000);
            distributorUI.waitForVisibility(By.xpath(txt_searchUser.replace("USER", user)));
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(By.xpath(txt_searchUser.replace("USER", user)));
    }
}
