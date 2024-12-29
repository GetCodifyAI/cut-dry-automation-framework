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
    }

    public void clickBtnSaveCheckIn(){
        distributorUI.click(btn_saveCheckIn);
    }

    public void clickOnBtnContinue(){
        distributorUI.click(btn_continue);
    }

    public void clickOnDropDownWhatIsWrong(){
        distributorUI.waitForVisibility(dropDown_whatIsWrong);
        distributorUI.click(dropDown_whatIsWrong);
    }

    public void clickOnFirstRowDropDownWhatIsWrong(){
        By dropDownOption = By.xpath(dropDownWhatIsWrongOption);
        distributorUI.click(dropDownOption);
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
    public void clickFirstItemFrmHistory(){
        distributorUI.waitForClickability(lbl_firstItemInHistory);
        distributorUI.click(lbl_firstItemInHistory);
    }
}
