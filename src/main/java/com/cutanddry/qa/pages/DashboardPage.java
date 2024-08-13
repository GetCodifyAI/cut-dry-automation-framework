package com.cutanddry.qa.pages;

import org.openqa.selenium.By;

public class DashboardPage extends LoginPage{
    By txt_dashboard = By.xpath("//li[contains(text(),'Dashboard')]");
    By btn_customers = By.xpath("//a[@data-tip='Customers']");


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
}
