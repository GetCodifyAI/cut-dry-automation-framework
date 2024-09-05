package com.cutanddry.qa.pages;

import org.openqa.selenium.By;

public class DashboardPage extends LoginPage{
    By txt_dashboard = By.xpath("//li[contains(text(),'Dashboard')]");
    By btn_customers = By.xpath("//a[@data-tip='Customers']");
    By txt_draftOrder = By.xpath("//div[contains(text(), 'Do you want to continue your previous draft order?')]");
    By btn_noDraftOrder = By.xpath("//div[contains(text(), 'No')]");

    public boolean isDashboardTextDisplayed(){
        try {
            distributorUI.waitForVisibility(txt_dashboard);
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(txt_dashboard);
    }
    public void clickOnCustomers(){
        distributorUI.click(btn_customers);
    }
    public boolean isDraftOrderPopUpDisplayed(){
        try {
            return distributorUI.isDisplayed(txt_draftOrder);
        } catch (Exception e){
            return false;
        }
    }
    public void clickOnNoDraftOrder(){
        distributorUI.click(btn_noDraftOrder);
        distributorUI.waitForInvisibility(btn_noDraftOrder);
    }
}
