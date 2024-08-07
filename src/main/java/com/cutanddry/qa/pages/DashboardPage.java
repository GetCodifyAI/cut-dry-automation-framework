package com.cutanddry.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DashboardPage extends LoginPage{
    By txt_dashboard = By.xpath("//li[contains(text(),'Dashboard')]");
    By btn_customers = By.xpath("//a[@data-tip='Customers']");


    public boolean isDashboardTextDisplayed(){
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(txt_dashboard));
        } catch (Exception e){
            return false;
        }
        return driver.findElement(txt_dashboard).isDisplayed();
    }
    public void clickOnCustomers(){
        driver.findElement(btn_customers).click();
    }
}
