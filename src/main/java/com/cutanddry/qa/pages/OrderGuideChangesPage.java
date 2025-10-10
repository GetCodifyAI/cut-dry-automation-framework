package com.cutanddry.qa.pages;

import org.openqa.selenium.By;

public class OrderGuideChangesPage extends LoginPage{
    By txt_orderGuideChangesTitle = By.xpath("//h2[contains(text(),'Order Guide Changes')]");
    By txt_pageDescription = By.xpath("//div[contains(text(),'Items added or removed from customer order guides')]");
    By lbl_timeRangeDropdown = By.xpath("(//input[@type='text' and contains(@class,'select__input')])[1]");
    By lbl_customerDropdown = By.xpath("(//input[@type='text' and contains(@class,'select__input')])[2]");
    By lbl_salespersonDropdown = By.xpath("(//input[@type='text' and contains(@class,'select__input')])[3]");
    String txt_dropdownOption = "//div[text()='OPTION']";
    By tbl_orderGuideChanges = By.xpath("//table");
    By btn_downloadReport = By.xpath("//button[contains(text(),'Download Report')]");
    By txt_changeType = By.xpath("//th[contains(text(),'Change Type')]");

    public boolean isOrderGuideChangesPageDisplayed(){
        try {
            distributorUI.waitForVisibility(txt_orderGuideChangesTitle);
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(txt_orderGuideChangesTitle);
    }

    public boolean isPageDescriptionDisplayed(){
        try {
            return distributorUI.isDisplayed(txt_pageDescription);
        } catch (Exception e){
            return false;
        }
    }

    public void selectTimeRange(String timeRange){
        distributorUI.click(lbl_timeRangeDropdown);
        distributorUI.waitForVisibility(By.xpath(txt_dropdownOption.replace("OPTION", timeRange)));
        distributorUI.click(By.xpath(txt_dropdownOption.replace("OPTION", timeRange)));
    }

    public void selectCustomer(String customer){
        distributorUI.click(lbl_customerDropdown);
        distributorUI.waitForVisibility(By.xpath(txt_dropdownOption.replace("OPTION", customer)));
        distributorUI.click(By.xpath(txt_dropdownOption.replace("OPTION", customer)));
    }

    public void selectSalesperson(String salesperson){
        distributorUI.click(lbl_salespersonDropdown);
        distributorUI.waitForVisibility(By.xpath(txt_dropdownOption.replace("OPTION", salesperson)));
        distributorUI.click(By.xpath(txt_dropdownOption.replace("OPTION", salesperson)));
    }

    public boolean isTableDisplayed(){
        try {
            return distributorUI.isDisplayed(tbl_orderGuideChanges);
        } catch (Exception e){
            return false;
        }
    }

    public boolean isDownloadReportButtonDisplayed(){
        try {
            return distributorUI.isDisplayed(btn_downloadReport);
        } catch (Exception e){
            return false;
        }
    }

    public boolean isChangeTypeColumnDisplayed(){
        try {
            return distributorUI.isDisplayed(txt_changeType);
        } catch (Exception e){
            return false;
        }
    }
}
