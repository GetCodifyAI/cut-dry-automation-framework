package com.cutanddry.qa.pages;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.common.Constants;
import com.cutanddry.qa.functions.Orders;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LoginPage extends TestBase {
    By txt_emailOrMobile = By.xpath("//input[@placeholder='Email or mobile']");
    By txt_password = By.xpath("//input[@placeholder='Password']");
    By btn_signIn = By.xpath("//button[@type='submit']");
    By btn_forgotPassword = By.xpath("//div[text()='Forgot Password?']");
    By txt_forgotPassword = By.xpath("//div[text()='Forgot Password']");
    By btn_requestPassword = By.xpath("//button[text()='Request Password']");
    By txt_enterEmailOrMobile = By.xpath("//input[@placeholder='Enter email or phone number']");
    By txt_validEmailOrMobile = By.xpath("//div[text()='Password Reset Request Sent!']");
    By txt_invalidEmailOrMobile = By.xpath("//div[text()='Error']");
    By txt_password_Reset_Request = By.xpath("//div[contains(text(),'Password Reset Request Sent!')]");
    By btn_ok = By.xpath("//button[text()='Ok']");
    By btn_tryAgain = By.xpath("//button[text()='Try Again']");
    By lbl_loginAs = By.xpath("//div[text()='Select...']/following::input[@type='text']");
    String txt_whitelblCustomer = "//div[contains(@class, 'css-1n7v3ny-option') and contains(text(), 'NAME')]";
    By btn_loginAsWLApp = By.xpath("//a[contains(text(), 'Login As')]");
    By lbl_verifiedVendor = By.xpath("//a[text()='verifiedvendor']");
    String txt_verifiedVendor = "//a[contains(text(), 'ID')]";
    By lbl_suuplierPortalVendorData = By.xpath("//a[contains(text(), 'Link') and ancestor::th[contains(., 'SupplierPortalVendorData')]]");
    By txt_key = By.xpath("//input[@name='data_key']");
    By txt_value = By.xpath("//input[@name='data_val']");
    String removeNode = "//*[contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'NODENAME')]/parent::*/following::th[3]//button[translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz')='x']";
    By btn_setData = By.xpath("//button[contains(text(), 'Set data')]");
    By lbl_loginAsDist = By.xpath("//h5[text()='Distributor Portal Quick Links']/following-sibling::div//div[text()='Select User...']/following::input[@type='text'][1]");
    String txt_distributor = "//div[contains(@class, 'themed_select__option') and contains(text(), \"NAME\")]";
    By btn_loginAsDis = By.xpath("//a[contains(text(), 'Login As (supplier)')]");
    By InternalToolsBtn = By.xpath("//a[contains(text(),'Internal Tools') and contains(@class,'active nav-link')]");
    By lbl_loginAsCustomer = By.xpath("//button[contains(text(),'Re-Index')]/following-sibling::div//div[contains(text(),'Select')]/following::input[@type='text'][1]");
//    String txt_customer = "//div[@id='react-select-5-option-0' and contains(text(), 'PHONE_NO')]";
    String txt_customer = "//div[starts-with(@id, 'react-select-') and contains(@id, '-option-0') and contains(text(), 'PHONE_NO')]";
    By btn_loginAsCustomer = By.xpath("//a[contains(text(), 'Login As (classic)')]");
    By lbl_gateKeeper = By.xpath("//h1[contains(text(),'Gatekeeper')]");
    By row_count = By.xpath("//table[@class='table table-striped']/tbody/tr");
    String row_feature ="//table[@class='table table-striped']/tbody/tr[ROW]//td/input[@type='text']";
    String row_Companies ="//table[@class='table table-striped']/tbody/tr[ROW]//textarea[@data-href='/gatekeeperadminajax/edit/companyIDs']";
    String row_Status ="//table[@class='table table-striped']//tbody//tr[ROW]//div[contains(text(),'Verified Vendor')]/following-sibling::ul//input[contains(@value,'active_for_all_vv')]";
    By tbx_Search = By.xpath("//button[contains(text(),'Re-Index')]/following-sibling::*//div/input");
    String txt_dp = "((//div[contains(text(), 'OPERATOR_NAME')])[last()])";
    By btn_LoginAsClassic = By.xpath("//a[contains(text(), 'Login As (classic)')]");
    By tbx_operatorSearch = By.xpath("//button[contains(text(),'Re-Index')]/following-sibling::*//div/input");
    String txt_operator = "((//div[contains(text(), 'OPERATOR_NAME')])[last()])";
    By btn_LoginAsWhiteLabel = By.xpath("//a[contains(text(), 'Login As (white-label)')]");



    public void typeEmailOrMobile(String emailOrMobile){
        distributorUI.sendKeys(txt_emailOrMobile,emailOrMobile);
    }
    public void typePassword(String password){
        distributorUI.sendKeys(txt_password,password);
    }
    public void clickSubmit(){
        distributorUI.click(btn_signIn);
    }
    public void clickForgotPassword(){
        distributorUI.click(btn_forgotPassword);
    }
    public boolean isForgotPasswordPopupDisplayed(){
        return distributorUI.isDisplayed(txt_forgotPassword);
    }
    public void enterEmailOrMobile(String emailOrMobile){
        distributorUI.sendKeys(txt_enterEmailOrMobile,emailOrMobile);
    }
    public void clickRequestPassword() {
        distributorUI.waitForClickability(btn_requestPassword);
        distributorUI.click(btn_requestPassword);
    }
    public boolean validEmailOrMobile() {
        return distributorUI.isDisplayed(txt_validEmailOrMobile);
    }
    public boolean invalidEmailOrMobile() {
        return distributorUI.isDisplayed(txt_invalidEmailOrMobile);
    }
    public boolean isPasswordResetRequestMsgDisplayed(){
        return distributorUI.isDisplayed(txt_password_Reset_Request);
    }
    public void clickOk() {
        distributorUI.waitForVisibility(btn_ok);
        distributorUI.click(btn_ok);
    }
    public void clickTryAgain() {
        distributorUI.waitForVisibility(btn_tryAgain);
        distributorUI.click(btn_tryAgain);
    }
    public void navigateToRestaurant() {
        distributorUI.navigateToURL(Constants.SEC_URL);
    }
    public void navigateToDistributor() {
        distributorUI.navigateToURL(Constants.MAIN_URL);
    }
    public void navigateToLoginAsPortal(String name) {
        distributorUI.navigateToURL(Constants.LOGIN_AS);
        Orders.closeRatingOverlay();
        distributorUI.sendKeys(lbl_loginAs,name);
        distributorUI.isDisplayed(By.xpath(txt_whitelblCustomer.replace("NAME", name)));
        distributorUI.click(By.xpath(txt_whitelblCustomer.replace("NAME", name)));
        distributorUI.navigateToURL(distributorUI.getText(btn_loginAsWLApp, "href"));
    }
    public void navigateToSupplierPortalVendor(String id) {
        distributorUI.navigateToURL(Constants.NODE_EXPLORER);
        distributorUI.navigateToURL(distributorUI.getText(lbl_verifiedVendor, "href"));
        distributorUI.navigateToURL(distributorUI.getText(By.xpath(txt_verifiedVendor.replace("ID", id)), "href"));
        distributorUI.navigateToURL(distributorUI.getText(lbl_suuplierPortalVendorData, "href"));
    }
    public void navigateToNode(String Node){
        distributorUI.navigateToURL(Constants.NODE_EXPLORER+ "/node/" + Node);
    }
    public void setNode(String dataName) {
        distributorUI.sendKeys(txt_key,dataName);
        distributorUI.sendKeys(txt_value, String.valueOf(true));
        distributorUI.click(btn_setData);
    }
    public void setValueToNode(String keyName, String valueName) throws InterruptedException {
        distributorUI.sendKeys(txt_key,keyName);
        distributorUI.sendKeys(txt_value,valueName);
        distributorUI.click(btn_setData);
        distributorUI.waitForCustom(2000);
    }
    public void removeSetNodeKey(String nodeKey){
        distributorUI.click(By.xpath(removeNode.replace("NODENAME",nodeKey)));
        distributorUI.acceptAlert();
    }
    public void navigateToDistributorPortal(String name) {
        distributorUI.navigateToURL(Constants.LOGIN_AS);
        distributorUI.waitForElementEnabledState(lbl_loginAsDist,true);
        distributorUI.sendKeys(lbl_loginAsDist,name);
        distributorUI.click(By.xpath(txt_distributor.replace("NAME", name)));
        distributorUI.navigateToURL(distributorUI.getText(btn_loginAsDis, "href"));
    }

    public void ClickOnInternalTools(){
        distributorUI.navigateToURL(Constants.LOGIN_AS);
        distributorUI.waitForElementEnabledState(lbl_loginAsDist,true);
        distributorUI.click(InternalToolsBtn);
    }
    public void navigateToConfigSupplier() {
        distributorUI.navigateToURL(Constants.CONFIG_SUPPLIER);
    }
    public void navigateToRestaurantProd() {
        distributorUI.navigateToURL(Constants.SEC_URL);
    }
    public void navigateToDistributorPortalProd(String name) {
        distributorUI.navigateToURL(Constants.PROD_LOGIN_AS);
        distributorUI.waitForElementEnabledState(lbl_loginAsDist,true);
        distributorUI.sendKeys(lbl_loginAsDist,name);
        distributorUI.click(By.xpath(txt_distributor.replace("NAME", name)));
        distributorUI.navigateToURL(distributorUI.getText(btn_loginAsDis, "href"));
    }

    public void switchIntoNewTab(){
        distributorUI.OpenNewTabAndSwitchToIt();
    }

    public void closeCurrentTab(){
        distributorUI.closeCurrentTab();
    }

    public void navigateToRestaurantPortal(String phoneNo) {
        distributorUI.navigateToURL(Constants.LOGIN_AS);
        distributorUI.waitForElementEnabledState(lbl_loginAsCustomer,true);
        distributorUI.sendKeys(lbl_loginAsCustomer,phoneNo);
        distributorUI.click(By.xpath(txt_customer.replace("PHONE_NO", phoneNo)));
        distributorUI.navigateToURL(distributorUI.getText(btn_loginAsCustomer, "href"));
    }

    public void navigateToGateKeeperAdmin(){
        distributorUI.navigateToURL(Constants.GATE_KEEPER_ADMIN);
        distributorUI.waitForVisibility(lbl_gateKeeper);
    }

    public void updateCompanyIDs(String featureName, String newCompanyID) throws InterruptedException {
        int rowCount = distributorUI.countElements(row_count);

        for (int i = 1; i <= rowCount; i++) {
            String featureValue = distributorUI.getText(By.xpath(row_feature.replace("ROW", String.valueOf(i))), "value");
            if (featureValue.equalsIgnoreCase(featureName)) {

                // Ensure checkbox/radio is selected (don’t deselect it if already selected)
                By statusLocator = By.xpath(row_Status.replace("ROW", String.valueOf(i)));
                if (!distributorUI.isCheckboxOrRadioBtnSelected(statusLocator)) {
                    distributorUI.click(statusLocator);
                    System.out.println("Checkbox was not selected. Now selected.");
                }

                // Handle company IDs
                By companiesLocator = By.xpath(row_Companies.replace("ROW", String.valueOf(i)));
                String existingCompanyIDs = distributorUI.getText(companiesLocator);

                if (existingCompanyIDs == null || existingCompanyIDs.isEmpty()) {
                    distributorUI.sendKeysCharByChar(companiesLocator, newCompanyID);
                } else {
                    String[] companyIDArray = existingCompanyIDs.split(",");
                    boolean idExists = false;

                    for (String id : companyIDArray) {
                        if (id.trim().equalsIgnoreCase(newCompanyID.trim())) {
                            idExists = true;
                            break;
                        }
                    }

                    if (!idExists) {
                        String updatedCompanyIDs = existingCompanyIDs.trim() + "," + newCompanyID.trim();
                        distributorUI.sendKeysCharByChar(companiesLocator, updatedCompanyIDs);
                    }
                }

                distributorUI.waitForCustom(3000);
                break; // stop after the target feature row
            }
        }
    }


    public void removeCompanyIDs(String featureName, String RemovedCompanyID) {
        int rowCount = distributorUI.countElements(row_count);

        for (int i = 1; i <= rowCount; i++) {
            String featureValue = distributorUI.getText(By.xpath(row_feature.replace("ROW", String.valueOf(i))), "value");

            if (featureValue.equalsIgnoreCase(featureName)) {

                String existingCompanyIDs = distributorUI.getText(By.xpath(row_Companies.replace("ROW", String.valueOf(i))));

                String[] companyIDArray = existingCompanyIDs.split(",");

                List<String> companyIDList = new ArrayList<>(Arrays.asList(companyIDArray));

                if (companyIDList.contains(RemovedCompanyID)) {
                    companyIDList.remove(RemovedCompanyID);
                    String updatedCompanyIDs = String.join(",", companyIDList);
                    distributorUI.sendKeysCharByChar(By.xpath(row_Companies.replace("ROW", String.valueOf(i))), updatedCompanyIDs);
                }

            }
        }


    }
    public void typeToSearchOnDP(String operator) throws InterruptedException {
        distributorUI.sendKeys(tbx_Search, operator);
        distributorUI.waitForCustom(4000);
    }
    public void clickOnDP(String operator){
        distributorUI.waitForVisibility(By.xpath(txt_dp.replace("OPERATOR_NAME",operator)));
        distributorUI.click(By.xpath(txt_dp.replace("OPERATOR_NAME",operator)));
    }
    public void clickOnLoginAsSupplierAndSwitchToNewTab() throws InterruptedException {
        distributorUI.click(btn_loginAsDis);
        distributorUI.switchToNewTab();
        distributorUI.waitForCustom(3000);
    }
    public void navigateToLoginAs() {
        distributorUI.navigateToURL(Constants.LOGIN_AS);
    }
    public boolean navigateToLoginAsPortalForCustomerIndex(String name) throws InterruptedException {
        distributorUI.navigateToURL(Constants.LOGIN_AS);
//        Orders.closeRatingOverlay();
        distributorUI.waitForCustom(5000);
        distributorUI.sendKeys(lbl_loginAs,name);
        return distributorUI.isDisplayed(By.xpath(txt_whitelblCustomer.replace("NAME", name)));
    }
    public void typeToSearchOnOperator(String operator) throws InterruptedException {
        distributorUI.waitForCustom(5000);
        distributorUI.sendKeys(tbx_operatorSearch, operator);
        distributorUI.waitForCustom(4000);
    }
    public void clickOperator(String operator){
        distributorUI.waitForVisibility(By.xpath(txt_operator.replace("OPERATOR_NAME",operator)));
        distributorUI.click(By.xpath(txt_operator.replace("OPERATOR_NAME",operator)));
    }
    public void clickOnLoginAsWhiteLabelAndSwitchToNewTab() throws InterruptedException {
        distributorUI.click(btn_LoginAsWhiteLabel);
        distributorUI.switchToNewTab();
        distributorUI.waitForCustom(3000);
    }
    public void closePreviousTab()throws InterruptedException{
        distributorUI.CloseAllPreviousTabAndSwitchToNew();
    }
    public void clickOnLoginAsClassicAndSwitchToNewTab() throws InterruptedException {
        distributorUI.click(btn_LoginAsClassic);
        distributorUI.switchToNewTab();
        distributorUI.waitForCustom(3000);
    }
    public void closeCurrentTabAndSwitchToNew(){
        distributorUI.openNewTabAndClosePreviousTabs();
    }

    public void switchBetweenTabsUsingIndex(int index ){
        distributorUI.switchTabUsingIndex(index);
    }
}
