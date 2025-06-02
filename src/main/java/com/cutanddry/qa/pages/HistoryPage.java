package com.cutanddry.qa.pages;

import org.openqa.selenium.By;

public class HistoryPage extends LoginPage{
    By txt_orderHistory = By.xpath("//h2[contains(text(),'Order History')]");
    By lbl_firstItemInHistory = By.xpath("//tr[contains(@href,'/orders-revised/view-one')][1]");
    By table_Orders = By.xpath("//table[@class='_6rf0k0 mt-lg-4 table']");
    String statusColumnXPathTemplate = "//table[@class='_6rf0k0 mt-lg-4 table']//tr['ROW_NUMBER']/td[9]";
    By txt_order = By.xpath("//h2[contains(text(),'Order')]");
    By txt_checkIn = By.xpath("//h2[contains(text(),'Check-In')]");
    By btn_editCheckIn = By.xpath("//button[contains(@class, 'btn') and contains(., 'Edit Check-In')]");
    By btn_reportIssue = By.xpath("//button[contains(@class, 'btn') and contains(@class, 'btn-outline-danger') and contains(., 'Report Issue')]");
    By firstCheckBox_tbleOrderIssues = By.xpath("//table[@class='mt-3 table table-hover']/tbody/tr[1]");
    By popupWindow_reportIssue =  By.xpath("//div[@class='modal-content']");
    By dropDown_whatIsWrong = By.xpath("//div[contains(@class, 'themed_select__control')]");
    By dropDownFirstOption_whatIsWrong = By.xpath("//div[contains(@class, 'themed_select__control')]//div[@class='themed_select__option'][1]");
    String dropDownWhatIsWrongOption = "//div[contains(@class, 'themed_select__option') and text()='Missing']";
    By btn_continue = By.xpath("//button[@type='submit' and @class='btn btn-primary btn-block' and contains(text(), 'Continue')]");
    By btn_saveCheckIn = By.xpath("//button[@type='button' and contains(@class, 'btn-primary')][contains(., 'Save Check-In')]");
    By btn_yes = By.xpath("//button[text()='Yes']");
    By btn_close = By.xpath("//button[text()='Close']");
    By txt_whichItemsHasError = By.xpath("//h2[contains(text(),'Which items had an issue')]");
    By btn_checkInOrder = By.xpath("//button[contains(@class, 'btn') and contains(., 'Check-In Order')]");
    By lbl_orderDateColumn = By.xpath("//span[contains(text(),'Order Date')]");
    By lbl_orderDateArrowUp = By.xpath("//span[contains(text(),'Order Date')]/*[name()='svg' and contains(@data-icon, 'arrow-up')]");
    By btn_history = By.xpath("//a[contains(., 'History')]");
    By btn_search = By.xpath("//input[@placeholder='Search' and contains(@class, 'form-control')]");
    String search_result = "//tr[contains(@href,'/orders-revised/view-one')][1]//following-sibling::td[contains(.,'ORDERID')]";
    By first_row_order_details = By.xpath("//tr[2]/td[4]");
    By btn_edit_order = By.xpath("//button[text() = 'Edit Order']");
    By txt_edit_order = By.xpath("//h2[text() = 'Edit Order?']");
    By btn_confirm_order = By.xpath("//button[text()='Confirm']");
    By btn_submit_edit_order = By.xpath("//button[@id='submit-order-button' and text()='Submit Order Edits']");
    By txt_invalidProduct = By.xpath("//h2[text() = 'Invalid Product']");
    String itemCode = "//div[contains(text(),'CODE')]";


    public boolean isTxtWhichItemsHasError() {
        try {
            distributorUI.waitForVisibility(txt_whichItemsHasError);
        } catch (Exception e) {
            return false;
        }
        return distributorUI.isDisplayed(txt_whichItemsHasError);
    }

    public void clickClose(){
        distributorUI.waitForVisibility(btn_close);
        distributorUI.click(btn_close);
    }

    public void clickOnYes(){
        distributorUI.click(btn_yes);
        try {
            distributorUI.waitForCustom(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void clickBtnSaveCheckIn(){
        distributorUI.click(btn_saveCheckIn);
        try {
            distributorUI.waitForCustom(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void clickOnBtnContinue(){
        distributorUI.click(btn_continue);
        try {
            distributorUI.waitForCustom(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void clickOnDropDownWhatIsWrong(){
        distributorUI.waitForVisibility(dropDown_whatIsWrong);
        distributorUI.click(dropDown_whatIsWrong);
    }

    public void clickOnFirstRowDropDownWhatIsWrong(){
        By dropDownOption = By.xpath(dropDownWhatIsWrongOption);
        distributorUI.click(dropDownOption);
        try {
            distributorUI.waitForCustom(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isPopupWindowReportIssueDisplayed() {
        try {
            System.out.println("Pop Up Window Displayed");
            distributorUI.waitForVisibility(popupWindow_reportIssue);
        } catch (Exception e) {
            System.out.println("Pop Up Window Not Displayed");
            return false;
        }
        return distributorUI.isDisplayed(popupWindow_reportIssue);
    }

    public void clickOnFirstRowTableOrderIssues(){
        distributorUI.waitForVisibility(firstCheckBox_tbleOrderIssues);
        distributorUI.click(firstCheckBox_tbleOrderIssues);
    }

    public void clickBtnReportIssue(){
        distributorUI.click(btn_reportIssue);
    }

    public boolean isCheckInTextDisplayed() {
        try {
            distributorUI.waitForVisibility(txt_checkIn);
        } catch (Exception e) {
            return false;
        }
        return distributorUI.isDisplayed(txt_checkIn);
    }

    public void clickBtnEditCheckIn(){
        distributorUI.click(btn_editCheckIn);
    }

    public boolean isOrderTextDisplayed() {
        try {
            distributorUI.waitForVisibility(txt_order);
        } catch (Exception e) {
            return false;
        }
        return distributorUI.isDisplayed(txt_order);
    }

    public void clickOnOrderFromOrderList() {
        int numOfRows = distributorUI.getRowCount(table_Orders);
        System.out.println("There are " + numOfRows +" rows.");
        for (int i = 1; i <= numOfRows; i++) {
            System.out.println(i);
            By textStatus = By.xpath(statusColumnXPathTemplate.replace("ROW_NUMBER",String.valueOf(i)));
            try{
                distributorUI.waitForVisibility(textStatus);
                String status = distributorUI.getText(textStatus);
                System.out.println(i);
                System.out.println("The status is " +status);
                if (status.contains("Checked In")) {
                    System.out.println("Status is Correct");
                    distributorUI.click(textStatus);
                    break;
                }
            }
            catch (Exception e){
                System.out.println("ERROR");
            }
        }
    }

    public boolean isOrderHistoryTextDisplayed() {
        try {
            distributorUI.waitForVisibility(txt_orderHistory);
        } catch (Exception e) {
            return false;
        }
        return distributorUI.isDisplayed(txt_orderHistory);
    }
    public void clickFirstItemFrmHistory()throws InterruptedException{
        distributorUI.waitForCustom(4000);
        distributorUI.waitForClickability(lbl_firstItemInHistory);
        distributorUI.click(lbl_firstItemInHistory);
    }
    public void clickCheckInOrder(){
        distributorUI.click(btn_checkInOrder);
    }

    public void ensureOrderDateSortedDescending() throws InterruptedException {

        distributorUI.waitForVisibility(lbl_orderDateColumn);
        distributorUI.click(lbl_orderDateColumn);

        if (distributorUI.isDisplayed(lbl_orderDateArrowUp)) {
            distributorUI.click(lbl_orderDateColumn);
            distributorUI.waitForCustom(2000);
        }

    }
    public void clickHistory(){
        distributorUI.click(btn_history);
        try {
            distributorUI.waitForCustom(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void clickOnSearch(){
        distributorUI.click(btn_search);
    }
    public void typeOnSearch(String orderID) throws InterruptedException {
        distributorUI.clear(btn_search);
        distributorUI.sendKeys(btn_search, orderID);
        distributorUI.waitForCustom(400);
    }

    public boolean checkIfSearchedElementVisible(String orderID) {
        distributorUI.waitForVisibility(By.xpath(search_result.replace("ORDERID",orderID)));
        if (!distributorUI.isDisplayed(By.xpath(search_result.replace("ORDERID",orderID)))) {
            distributorUI.refreshPage();
        }
        return distributorUI.isDisplayed(By.xpath(search_result.replace("ORDERID",orderID)));
    }
    public void refreshHistoryPage(){
        distributorUI.refreshPage();
    }
    public void clickOnFirstItemOfOrderHistory(){
        distributorUI.click(first_row_order_details);
    }
    public void clickEditOrder(){
        distributorUI.click(btn_edit_order);
    }
    public boolean isEditOrderPopUpDisplayed(){
        return distributorUI.isDisplayed(txt_edit_order);
    }
    public void clickConfirmEditOrder(){
        distributorUI.click(btn_confirm_order);
    }
    public void clickSubmitEditOrder(){
        distributorUI.click(btn_submit_edit_order);
    }
    public boolean isInvalidProductTextDisplay(){
        return distributorUI.isDisplayed(txt_invalidProduct);
    }
    public boolean isInvalidProductCodeDisplay(String code){
        return distributorUI.isDisplayed(By.xpath(itemCode.replace("CODE",code)));
    }
}
