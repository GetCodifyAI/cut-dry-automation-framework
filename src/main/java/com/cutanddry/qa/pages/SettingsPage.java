package com.cutanddry.qa.pages;

import org.openqa.selenium.By;

public class SettingsPage extends LoginPage{
    By txt_orderSettings = By.xpath("//li[contains(text(),'Order Settings')]");
    By tbx_orderMinimum = By.xpath("//label[text()='Order Minimum ($)']/following-sibling::div//input");
    By btn_saveChange = By.xpath("//button[text()='Save Changes']");
    By txt_teamSettings = By.xpath("//h2[text()='Team']");
    By btn_addUser = By.xpath("//button[text()='Add User']");
    By lbl_name = By.xpath("//label[text()='Name']/following-sibling::div//input");
    By lbl_email = By.xpath("//label[text()='Email']/following-sibling::div//input");
    By lbl_phone = By.xpath("//label[text()='Phone']/following-sibling::div//input");
    By lbl_userRef = By.xpath("//label[text()='User reference']/following-sibling::div//input");
    By btn_inviteUser = By.xpath("//button[text()='Invite User']");
    String btn_editUser = "//tr[td[text()='USER']]/td/button";
    By txt_editUser = By.xpath("//div[text()='Edit User']");
    By lbl_removeUser = By.xpath("//a[text()='Remove user']");
    By btn_OK = By.xpath("//button[text()='OK']");
    By txt_removeUser = By.xpath("//div[text()='Removing this user will result in the following:']");
    By btn_removeUser = By.xpath("//button[text()='Remove User']");
    By txt_userRefError = By.xpath("//h2[contains(text(), 'already being assigned to a another')]");
    String btn_removeAddedUserRef = "//div[text()='REF']/following-sibling::div[contains(@class, 'themed_select__multi-value__remove')]";
    String txt_addedUserRef = "//div[text()='REF']";
    By txt_userAddingErrorPopup = By.xpath("//h2[text()='Error while creating a new user. Please try again.']");
    By lbl_nameWL = By.xpath("//label[text()='Name']/parent::div//input");
    By lbl_emailWL = By.xpath("//label[contains(text(),'Email')]/parent::div//input");
    By btn_addUserWL = By.xpath("//button[@type='submit' and text()='Add User']");
    By txt_removePopup = By.xpath("//h2[text()='Are you sure you want to remove this user?']");
    By btn_Yes = By.xpath("//button[text()='Yes']");
    String txt_userField = "//td[text()='USER']";

    public boolean isOrderSettingsTextDisplayed(){
        try {
            distributorUI.waitForVisibility(txt_orderSettings);
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(txt_orderSettings);
    }
    public void enterOrderMinimum(String amount) throws InterruptedException {
        distributorUI.clear(tbx_orderMinimum);
        distributorUI.waitForCustom(1000);
        distributorUI.sendKeys(tbx_orderMinimum, amount);
    }
    public void clickOnSaveChanges() {
        distributorUI.waitForVisibility(btn_saveChange);
        distributorUI.click(btn_saveChange);
        distributorUI.waitForVisibility(btn_saveChange);
    }
    public boolean isTeamSettingsTextDisplayed() throws InterruptedException {
        try {
            distributorUI.waitForVisibility(txt_teamSettings);
        } catch (Exception e){
            return false;
        }
        distributorUI.waitForCustom(2000);
        return distributorUI.isDisplayed(txt_teamSettings);

    }
    public void clickOnAddUser() {
        distributorUI.waitForVisibility(btn_addUser);
        try {
            distributorUI.click(btn_addUser);
        } catch (Exception e){
            distributorUI.refreshPage();
            distributorUI.click(btn_addUser);
        }
    }
    public void enterName(String name){
        distributorUI.clear(lbl_name);
        distributorUI.sendKeys(lbl_name,name);
    }
    public void enterEmail(String email){
        distributorUI.clear(lbl_email);
        distributorUI.sendKeys(lbl_email,email);
    }
    public void enterPhone(String mobile){
        distributorUI.clear(lbl_phone);
        distributorUI.sendKeys(lbl_phone,mobile);
    }
    public void enterUserRef(String ref) throws InterruptedException {
        distributorUI.waitForCustom(3000);
        distributorUI.sendKeysAndEnter(lbl_userRef,ref);
    }
    public void clickOnInviteUser() {
        distributorUI.waitForClickability(btn_inviteUser);
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
    public void clickOnRemoveUserLabel() throws InterruptedException {
        distributorUI.waitForCustom(2000);
        distributorUI.waitForClickability(lbl_removeUser);
        distributorUI.click(lbl_removeUser);
    }
    public void clickOnEditUser(String user) {
        distributorUI.scrollToElement(By.xpath(btn_editUser.replace("USER", user)));
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
        distributorUI.waitForCustom(2000);
    }
    public boolean isRemoveUserPopupDisplayed(){
        distributorUI.waitForVisibility(txt_removeUser);
        return distributorUI.isDisplayed(txt_removeUser);
    }
    public void clickOnRemoveUser() {
        distributorUI.waitForClickability(btn_removeUser);
        distributorUI.click(btn_removeUser);
    }
    public boolean isUserRefErrorDisplayed(){
        distributorUI.waitForVisibility(txt_userRefError);
        return distributorUI.isDisplayed(txt_userRefError);
    }
    public void clickRemoveAddedUserRef(String ref){
        distributorUI.waitForVisibility(By.xpath(btn_removeAddedUserRef.replace("REF", ref)));
        distributorUI.click(By.xpath(btn_removeAddedUserRef.replace("REF", ref)));
    }
    public boolean isUserRefAdded(String ref) throws InterruptedException {
        distributorUI.waitForCustom(1000);
        return distributorUI.isDisplayed(By.xpath(txt_addedUserRef.replace("REF", ref)));
    }
    public boolean isUserAddingErrorPopupDisplayed(){
        distributorUI.waitForVisibility(txt_userAddingErrorPopup);
        return distributorUI.isDisplayed(txt_userAddingErrorPopup);
    }
    public void enterNameWL(String name){
        distributorUI.sendKeys(lbl_nameWL,name);
    }
    public void enterEmailWL(String email){
        distributorUI.sendKeys(lbl_emailWL,email);
    }
    public void clickOnAddUserWL() {
        distributorUI.waitForClickability(btn_addUserWL);
        distributorUI.click(btn_addUserWL);
    }
    public boolean isRemovePopupWLUserDisplayed(){
        try {
            distributorUI.waitForVisibility(txt_removePopup);
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(txt_removePopup);
    }
    public void clickYes() throws InterruptedException {
        distributorUI.waitForClickability(btn_Yes);
        distributorUI.click(btn_Yes);
        distributorUI.waitForCustom(1000);
    }
    public boolean isWLUserDisplayed(String user){
        try {
            distributorUI.waitForVisibility(By.xpath(txt_userField.replace("USER", user)));
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(By.xpath(txt_userField.replace("USER", user)));
    }
    public void clickOnUser(String user) {
        distributorUI.waitForClickability(By.xpath(txt_userField.replace("USER", user)));
        distributorUI.click(By.xpath(txt_userField.replace("USER", user)));
    }
}
