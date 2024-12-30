package com.cutanddry.qa.pages;

import org.openqa.selenium.By;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Date;
import java.util.Locale;

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
    String btn_editUser = "//tr[td[text()='USER']]/td/following-sibling::td//*[local-name()='svg']";
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
    By txt_companySettings = By.xpath("//h2[contains(text(),'Company Settings')]");
    By txt_profSettings = By.xpath("//h2[contains(text(),'Profile')]");
    By txt_trackSettings = By.xpath("//h2[contains(text(),'Delivery Settings')]");
    By txt_billingSettings = By.xpath("//h2[contains(text(),'Billing Settings')]");
    By txt_adsSettings = By.xpath("//div[contains(text(),'Ads and Rebates')]");
    By txt_paySettings = By.xpath("//h2[contains(text(),'Pay Settings')]");
    By btn_addPaymentMethod = By.xpath("//button[text()='Add Payment Method']");
    By txt_addPaymentPopup = By.xpath("//h3[text()='Add Payment Method']");
    By lbl_addBank = By.xpath("//span[text()='Add bank account']");
    By lbl_accNum = By.xpath("//div[label[text()='Account Number']]//input");
    By lbl_RoutingNum = By.xpath("//div[label[text()='Routing Number']]//input");
    By btn_next = By.xpath("//button[text()='Next']");
    By txt_paymentMethodAddedPopup = By.xpath("//h2[text()='Payment method has been added successfully.']");
    By txt_paymentMethodRemovedPopup = By.xpath("//h2[text()='Payment method has been removed successfully.']");
    By txt_displayedPaymentMethod = By.xpath("//div[text()='Your monthly bill will be deducted from the bank account x2220.']");
    By btn_removeAcc = By.xpath("//button[text()='Remove Account']");
    By txt_areYouSure = By.xpath("//h2[text()='Are you sure?']");
    By btn_batchActions = By.xpath("//button[contains(text(),'Batch Actions')]");
    By btn_downloadInvoices = By.xpath("//a[@class='dropdown-item' and text()='Download Invoices']");
    By lbl_invoice = By.xpath("//tr[td[text()='10008']]/td/div");
    By btn_linkBank = By.xpath("//button[text()='Link Bank Account']");
    By btn_linkBankManually = By.xpath("//button[text()='Link your account manually']");
    By txt_linkAccPopup = By.xpath("//h3[text()='Add Bank Account']");
    By btn_save= By.xpath("//div[contains(@class, 'modal-content')]//button[contains(text(), 'Save')]");
    By txt_displayedPayout = By.xpath("//div[text()='All payouts will be transferred to bank account x2220.']");
    By text_payOutMethodPresent = By.xpath("//div[contains(text(),'All payouts will be transferred to bank account')]");
    By txt_bankDetailsAddedPopup = By.xpath("//h2[text()='Bank account details have been added successfully.']");
    By txt_bankDetailsRemovedPopup = By.xpath("//h2[text()='The bank account has been successfully removed.']");
    By btn_plus = By.xpath("//button[*[local-name()='svg' and @data-icon='plus']]");
    By txt_setHoliday = By.xpath("//div[text()='Set Holiday']");
    By dropdown_selectType = By.xpath("//div[text()='Select Type']/following-sibling::div[contains(@class, 'css-2b097c-container')]");
    By txt_global = By.xpath("//div[text()='Global']");
    By txt_customerSpecific = By.xpath("//div[text()='Customer Specific']");
    By btn_save_= By.xpath("//button[text()= 'Save']");
    By txt_lastDate = By.xpath("(//div[@class='px-0 _c1780 col'])[last()]");
    By dropdown_items = By.xpath("//div[@class='cd_themed_select__menu css-26l3qy-menu']");
    By dropdown_deliveryDate = By.xpath("//div[@class='text-truncate']");
    By btn_minus = By.xpath("(//button[*[local-name()='svg' and @data-icon='minus']])[last()]");
    By txt_addCustomerCode =    By.xpath("//div[text()='Add Customer Codes']/following::input[@type='text']");
    By lbl_customerSpecDisabled = By.xpath("//label[text()='Customer Specific Delivery Days']/preceding-sibling::input[@type='checkbox' and @disabled]");
    By lbl_deliveryDays = By.xpath("//label[text()='Delivery Days']/preceding-sibling::input[@type='checkbox']");
    By sel_OrderMinimums = By.xpath("//*[contains(text(),'Order Minimums')]/preceding-sibling::input");
    By sel_CustomerSpecificDeliveryDays = By.xpath("//*[contains(text(),'Customer Specific Delivery Days')]/preceding-sibling::input");
    By sel_DeliveryDays = By.xpath("(//*[contains(text(),'Delivery Days')]/preceding-sibling::input)[1]");
    By lbl_HolidayDate = By.xpath("(//button[*[local-name()='svg' and @data-icon='minus']]/following::div[1])[last()]");
    By dropdown_selectHolidayDate = By.xpath("//div[text()='Select Date']/following-sibling::div//input");
    String dynamicXPath = "//div[contains(@class, 'react-datepicker__day--selected')]/following::div[contains(@class, 'react-datepicker__day')][DAY]";
    By lbl_InvalidEmail = By.xpath("//h2[contains(text(),'Invalid Email')]");
    By lbl_addCreditCard = By.xpath("//span[text()='Add credit card']");
    By switchIframeCardNumber = By.xpath("//iframe[contains(@id,'CollectJSInlineccnumber')]");
    By cardNumber = By.xpath("//input[@id='ccnumber']");
    By switchIframeCardExp = By.xpath("//iframe[contains(@id,'CollectJSInlineccexp')]");
    By cardExp = By.xpath("//input[contains(@name,'ccexp')]");
    By switchIframeCardCVV = By.xpath("//iframe[contains(@id,'CollectJSInlinecvv')]");
    By cardCVV = By.xpath("//input[@id='cvv']");
    By streetAddress = By.xpath("//input[@placeHolder='Street Address']");
    By cityName = By.xpath("//input[@placeHolder='City']");
    By btn_pay = By.xpath("//button[text()='Pay']");
    By btn_export = By.xpath("//a[text()='Export']");
    By btn_import = By.xpath("//button[text()='Import']");
    By btn_downloadSample = By.xpath("//a[text()='Download Sample']");
    By txt_importSuccess = By.xpath("//h2[text()='Import Completed!']");
    By btn_close = By.xpath("//button[text()='Close']");
    By btn_addAlert = By.xpath("//button[text()='Add Alert']");
    By txt_orderRemindAlert = By.xpath("//div[text()='Order Reminder Alert']");
    By sendAlertDropDown = By.xpath("//label[text()='Send Alert']//following-sibling::div//div[contains(@class, 'themed_select__control')]");
    String sendAlertOption = "//div[contains(@class,'themed_select__option') and text()='OPTION']";
    By txt_setAlert = By.xpath("//h2[text()= 'Setting Your Order Reminder']");
    By btn_OkAlert = By.xpath("//button[text()='Ok']");
    By btn_deleteAlert = By.xpath("//button[text()='Delete Alert']");







    public boolean isOrderSettingsTextDisplayed() throws InterruptedException {
        try {
            distributorUI.waitForVisibility(txt_orderSettings);
        } catch (Exception e){
            return false;
        }
        distributorUI.waitForCustom(4000);
        return distributorUI.isDisplayed(txt_orderSettings);
    }
    public void enterOrderMinimum(String amount) throws InterruptedException {
        distributorUI.clear(tbx_orderMinimum);
        distributorUI.waitForCustom(1000);
        distributorUI.sendKeys(tbx_orderMinimum, amount);
    }
    public void clickOnSaveChanges() throws InterruptedException {
        distributorUI.waitForVisibility(btn_saveChange);
        distributorUI.click(btn_saveChange);
        distributorUI.waitForVisibility(btn_saveChange);
        distributorUI.waitForCustom(5000);
    }
    public boolean isTeamSettingsTextDisplayed() throws InterruptedException {
        try {
            distributorUI.waitForVisibility(txt_teamSettings);
        } catch (Exception e){
            return false;
        }
        distributorUI.waitForCustom(4000);
        return distributorUI.isDisplayed(txt_teamSettings);
    }
    public boolean isPaySettingsTextDisplayed() throws InterruptedException {
        try {
            distributorUI.waitForVisibility(txt_paySettings);
        } catch (Exception e){
            return false;
        }
        distributorUI.waitForCustom(4000);
        return distributorUI.isDisplayed(txt_paySettings);
    }
    public boolean isAdsSettingsTextDisplayed() throws InterruptedException {
        try {
            distributorUI.waitForVisibility(txt_adsSettings);
        } catch (Exception e){
            return false;
        }
        distributorUI.waitForCustom(4000);
        return distributorUI.isDisplayed(txt_adsSettings);
    }
    public void clickOnAddUser() throws InterruptedException {
        distributorUI.waitForVisibility(btn_addUser);
        distributorUI.waitForCustom(3000);
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
    public void enterUserRef(String ref) throws InterruptedException {
        distributorUI.waitForCustom(4000);
        distributorUI.sendKeysAndEnter(lbl_userRef,ref);
        distributorUI.waitForCustom(2000);
    }
    public void clickOnInviteUser() throws InterruptedException {
        distributorUI.waitForCustom(3000);
        distributorUI.waitForVisibility(btn_inviteUser);
        distributorUI.click(btn_inviteUser);
        distributorUI.clickUsingJavaScript(btn_inviteUser);
        if(distributorUI.isDisplayed(lbl_InvalidEmail)){
            distributorUI.waitForClickability(btn_OK);
            distributorUI.click(btn_OK);
            distributorUI.waitForCustom(3000);
        }
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
        distributorUI.waitForCustom(3000);
        distributorUI.waitForClickability(lbl_removeUser);
        distributorUI.click(lbl_removeUser);
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
    public boolean isUserRefErrorDisplayed() throws InterruptedException {
        distributorUI.waitForCustom(3000);
        distributorUI.waitForVisibility(txt_userRefError);
        return distributorUI.isDisplayed(txt_userRefError);
    }
    public void clickRemoveAddedUserRef(String ref) throws InterruptedException {
        distributorUI.waitForCustom(3000);
        distributorUI.waitForVisibility(By.xpath(btn_removeAddedUserRef.replace("REF", ref)));
        distributorUI.click(By.xpath(btn_removeAddedUserRef.replace("REF", ref)));
    }
    public boolean isUserRefAdded(String ref) throws InterruptedException {
        distributorUI.waitForCustom(3000);
        return distributorUI.isDisplayed(By.xpath(txt_addedUserRef.replace("REF", ref)));
    }
    public boolean isUserAddingErrorPopupDisplayed() throws InterruptedException {
        distributorUI.waitForCustom(2000);
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
    public boolean isCompanySettingsTextDisplayed() throws InterruptedException {
        try {
            distributorUI.waitForVisibility(txt_companySettings);
        } catch (Exception e){
            return false;
        }
        distributorUI.waitForCustom(4000);
        return distributorUI.isDisplayed(txt_companySettings);

    }
    public boolean isBillingSettingsTextDisplayed() throws InterruptedException {
        try {
            distributorUI.waitForVisibility(txt_billingSettings);
        } catch (Exception e){
            return false;
        }
        distributorUI.waitForCustom(4000);
        return distributorUI.isDisplayed(txt_billingSettings);

    }
    public boolean isProfileSettingsTextDisplayed() throws InterruptedException {
        try {
            distributorUI.waitForVisibility(txt_profSettings);
        } catch (Exception e){
            return false;
        }
        distributorUI.waitForCustom(4000);
        return distributorUI.isDisplayed(txt_profSettings);

    }
    public boolean isTrackSettingsTextDisplayed() throws InterruptedException {
        try {
            distributorUI.waitForVisibility(txt_trackSettings);
        } catch (Exception e){
            return false;
        }
        distributorUI.waitForCustom(4000);
        return distributorUI.isDisplayed(txt_trackSettings);
    }
    public void clickOnAddPaymentMethod() {
        distributorUI.click(txt_billingSettings);
        distributorUI.waitForClickability(btn_addPaymentMethod);
        distributorUI.click(btn_addPaymentMethod);
    }
    public void clickOnAddBank() {
        try {
            distributorUI.waitForCustom(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        distributorUI.waitForClickability(lbl_addBank);
        distributorUI.click(lbl_addBank);
    }
    public boolean isAddPaymentPopupDisplayed(){
        return distributorUI.isDisplayed(txt_addPaymentPopup);
    }
    public void enterAccountNumber(String num){
        distributorUI.clear(lbl_accNum);
        distributorUI.sendKeys(lbl_accNum,num);
    }
    public void enterRoutingNumber(String num){
        distributorUI.clear(lbl_RoutingNum);
        distributorUI.sendKeys(lbl_RoutingNum,num);
    }
    public void clickOnNext() {
        try {
            distributorUI.waitForCustom(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        distributorUI.waitForClickability(btn_next);
        distributorUI.click(btn_next);
    }
    public boolean isAddPaymentSuccessPopupDisplayed(){
        return distributorUI.isDisplayed(txt_paymentMethodAddedPopup);
    }
    public boolean isRemovePaymentSuccessPopupDisplayed(){
        return distributorUI.isDisplayed(txt_paymentMethodRemovedPopup);
    }
    public boolean isPaymentMethodAdded(){
        return distributorUI.isDisplayed(txt_displayedPaymentMethod);
    }
    public void clickOnRemoveAcc() {
        distributorUI.waitForClickability(btn_removeAcc);
        distributorUI.click(btn_removeAcc);
    }
    public boolean isAreYouSurePopupDisplayed(){
        return distributorUI.isDisplayed(txt_areYouSure);
    }
    public void selectInvoice() {
        distributorUI.waitForVisibility(lbl_invoice);
        distributorUI.clickUsingJavaScript(lbl_invoice);
    }
    public boolean isDownloadInvoiceClickable(){
        distributorUI.click(btn_batchActions);
        try {
            distributorUI.waitForClickability(btn_downloadInvoices);
        } catch (Exception e){
            return false;
        }
        return  distributorUI.isDisplayed(btn_downloadInvoices);
    }
    public void clickOnDownloadInvoices() {
        distributorUI.click(btn_downloadInvoices);
    }
    public void clickOnLinkBank() {
        distributorUI.click(btn_linkBank);
    }
    public boolean isLinkAccPopupDisplayed(){
        return distributorUI.isDisplayed(txt_linkAccPopup);
    }
    public void clickOnLinkBankManually() {
        distributorUI.click(btn_linkBankManually);
    }
    public void clickOnSave() {
        distributorUI.waitForClickability(btn_save);
        distributorUI.click(btn_save);
    }
    public boolean isPayoutMethodAdded(){
        return distributorUI.isDisplayed(txt_displayedPayout);
    }
    public boolean isBankDetailsAddedPopupDisplayed(){
        return distributorUI.isDisplayed(txt_bankDetailsAddedPopup);
    }
    public boolean isBankDetailsRemovedPopupDisplayed(){
        return distributorUI.isDisplayed(txt_bankDetailsRemovedPopup);
    }
    public void clickOnSaveBtn() {
        distributorUI.waitForClickability(btn_save_);
        distributorUI.click(btn_save_);
    }
    public void clickOnPlusBtn() {
        distributorUI.waitForClickability(btn_plus);
        distributorUI.click(btn_plus);
    }
    public boolean isSetHolidayPopupDisplayed(){
        return distributorUI.isDisplayed(txt_setHoliday);
    }
    public void selectGlobal() {
        distributorUI.click(dropdown_selectType);
        distributorUI.waitForVisibility(txt_global);
        distributorUI.click(txt_global);
    }

    public void selectHolidayDate(int nextDayCount) throws InterruptedException {
        distributorUI.click(dropdown_selectHolidayDate);
        String day = String.valueOf(nextDayCount);
        By lbl_selectHolidayDate = By.xpath(dynamicXPath.replace("DAY", day));
        distributorUI.waitForVisibility(lbl_selectHolidayDate);
        distributorUI.click(lbl_selectHolidayDate);
        distributorUI.waitForCustom(3000);
    }

    public String getHolidayDate() {
        return distributorUI.getText(dropdown_selectHolidayDate,"value");
    }

    public void selectCustomerSpecific(String code) {
        distributorUI.click(dropdown_selectType);
        distributorUI.waitForVisibility(txt_customerSpecific);
        distributorUI.click(txt_customerSpecific);
        distributorUI.waitForVisibility(txt_addCustomerCode);
        distributorUI.sendKeys(txt_addCustomerCode, code);

    }
    public String getDate() {
        LocalDate today = LocalDate.now();
        String dayOfWeek = today.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        String formattedDate = today.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        return dayOfWeek + " " + formattedDate;
    }
    public String getAddedDate() {
        return distributorUI.getText(txt_lastDate);
    }
    public boolean isHolidayInDeliveryOrPuckUpOrMailDates() {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, MMM dd");
        String formattedDate = today.format(formatter);
        distributorUI.click(dropdown_deliveryDate);
        String dates = distributorUI.getText(dropdown_items);
        return dates.contains(formattedDate);
    }

    public boolean isHolidayInDelivery(String date) throws ParseException {
        distributorUI.refreshPage();
        distributorUI.waitForClickability(dropdown_deliveryDate);

        // Parse the input date (e.g., "Friday 12/20/2024")
        SimpleDateFormat inputFormatter = new SimpleDateFormat("EEEE MM/dd/yyyy");
        Date parsedDate = inputFormatter.parse(date);

        // Format the parsed date to match the dropdown format (e.g., "Fri, Dec 20")
        SimpleDateFormat dropdownFormatter = new SimpleDateFormat("EEE, MMM dd");
        String formattedDate = dropdownFormatter.format(parsedDate);

        distributorUI.click(dropdown_deliveryDate);
        String dates = distributorUI.getText(dropdown_items);
        return dates.contains(formattedDate);

    }

    public void clickOnMinusBtn() {
        distributorUI.waitForClickability(btn_minus);
        distributorUI.click(btn_minus);
    }
    public void uncheckDeliveryDays() {
        if (distributorUI.isDisplayed(lbl_customerSpecDisabled)){
            distributorUI.click(lbl_deliveryDays);
        }
    }

    public boolean isPayoutMethodAvailable(){
        return distributorUI.isDisplayed(text_payOutMethodPresent);
    }

    public void selectOnOrderMinimums() {
        distributorUI.waitForVisibility(sel_OrderMinimums);
        distributorUI.click(sel_OrderMinimums);
    }

    public void selectOrderMinimums() {
        distributorUI.waitForVisibility(sel_OrderMinimums);
        if (!distributorUI.isCheckboxOrRadioBtnSelected(sel_OrderMinimums)) {
            distributorUI.click(sel_OrderMinimums);
        }
    }

    public void deSelectOrderMinimums() {
        distributorUI.waitForVisibility(sel_OrderMinimums);
        if (distributorUI.isCheckboxOrRadioBtnSelected(sel_OrderMinimums)) {
            distributorUI.click(sel_OrderMinimums);
        }
    }

    public void setOrderMinimums(boolean select) {
        distributorUI.waitForVisibility(sel_OrderMinimums);
        boolean isSelected = distributorUI.isCheckboxOrRadioBtnSelected(sel_OrderMinimums);

        if (select && !isSelected) {
            distributorUI.click(sel_OrderMinimums); // Select the checkbox
        } else if (!select && isSelected) {
            distributorUI.click(sel_OrderMinimums); // Deselect the checkbox
        }
    }

    public void setCustomerSpecificDeliveryDays(boolean select) {
        distributorUI.waitForVisibility(sel_CustomerSpecificDeliveryDays);
        boolean isSelected = distributorUI.isCheckboxOrRadioBtnSelected(sel_CustomerSpecificDeliveryDays);

        if (select && !isSelected) {
            distributorUI.click(sel_CustomerSpecificDeliveryDays); // Select the checkbox
        } else if (!select && isSelected) {
            distributorUI.click(sel_CustomerSpecificDeliveryDays); // Deselect the checkbox
        }
    }

    public void setDeliveryDays(boolean select) {
        distributorUI.waitForVisibility(sel_DeliveryDays);
        boolean isSelected = distributorUI.isCheckboxOrRadioBtnSelected(sel_DeliveryDays);

        if (select && !isSelected) {
            distributorUI.click(sel_DeliveryDays); // Select the checkbox
        } else if (!select && isSelected) {
            distributorUI.click(sel_DeliveryDays); // Deselect the checkbox
        }
    }

    public void removeDateAsHoliday(int daysToAdd) {
        String currentDate = distributorUI.getDateAfterDaysFormatted(daysToAdd);
        distributorUI.waitForVisibility(lbl_HolidayDate);
        String actualDate = distributorUI.getText(lbl_HolidayDate);
        System.out.println(actualDate + " -" + currentDate);
        if (currentDate.equals(actualDate)) {
            distributorUI.click(btn_minus);
            try {
                distributorUI.waitForCustom(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public boolean isRemoveAccountBtnDisplayed() throws InterruptedException {
        distributorUI.waitForCustom(3000);
        return distributorUI.isDisplayed(btn_removeAcc);
    }
    public boolean isPaymentMethodAvailable(){
        return distributorUI.isDisplayed(btn_removeAcc);
    }
    public boolean isRemovePaymentMethodAvailable(){
        return distributorUI.isDisplayed(btn_removeAcc);
    }
    public void clickCreditCard(){
        distributorUI.click(lbl_addCreditCard);
    }
    public void enterCardNumber(String cardNum)throws InterruptedException{
        distributorUI.switchToFrameByElement(switchIframeCardNumber);
        distributorUI.sendKeys(cardNumber,cardNum);
        distributorUI.switchToDefaultContent();

    }
    public void enterExpDate(String expDate){
        distributorUI.switchToFrameByElement(switchIframeCardExp);
        distributorUI.sendKeys(cardExp,expDate);
        distributorUI.switchToDefaultContent();
    }
    public void enterCVV(String cvv){
        distributorUI.switchToFrameByElement(switchIframeCardCVV);
        distributorUI.sendKeys(cardCVV,cvv);
        distributorUI.switchToDefaultContent();
    }
    public void enterStreetAddress(String address){
        distributorUI.clear(streetAddress);
        distributorUI.sendKeys(streetAddress,address);
    }
    public void enterCity(String city){
        distributorUI.clear(cityName);
        distributorUI.sendKeys(cityName,city);
    }
    public void clickPayButton()throws InterruptedException{
        distributorUI.click(btn_pay);
    }
    public void clickExportButton()throws InterruptedException{
        distributorUI.click(btn_export);
    }
    public void clickImportButton(){
        distributorUI.click(btn_import);
    }
    public void clickDownloadSample(){
        distributorUI.click(btn_downloadSample);
    }
    public boolean isImportSuccessTextDisplayed() throws InterruptedException {
        try {
            distributorUI.waitForVisibility(txt_importSuccess);
        } catch (Exception e){
            return false;
        }
        distributorUI.waitForCustom(4000);
        return distributorUI.isDisplayed(txt_importSuccess);
    }
    public void clickClose(){
        distributorUI.click(btn_close);
    }
    public void clickAddAlert(){
        distributorUI.waitForVisibility(btn_addAlert);
        distributorUI.click(btn_addAlert);
    }
    public boolean isOrderRemindAlertTextDisplayed() throws InterruptedException {
        try {
            distributorUI.waitForVisibility(txt_orderRemindAlert);
        } catch (Exception e){
            return false;
        }
        distributorUI.waitForCustom(4000);
        return distributorUI.isDisplayed(txt_orderRemindAlert);
    }
    public void clickSendAlertDropDown(){
        distributorUI.click(sendAlertDropDown);
    }
    public void clickSendAlertOption(String option) throws InterruptedException {
        distributorUI.waitForCustom(3000);
        distributorUI.waitForVisibility(By.xpath(sendAlertOption.replace("OPTION", option)));
        distributorUI.click(By.xpath(sendAlertOption.replace("OPTION", option)));
    }
    public boolean isSettingOrderReminderTextDisplayed() throws InterruptedException {
        try {
            distributorUI.waitForVisibility(txt_setAlert);
        } catch (Exception e){
            return false;
        }
        distributorUI.waitForCustom(4000);
        return distributorUI.isDisplayed(txt_setAlert);
    }
    public void clickOkAlert(){
        distributorUI.click(btn_OkAlert);
    }
    public void clickDeleteAlert(){
        distributorUI.click(btn_deleteAlert);
    }


}
