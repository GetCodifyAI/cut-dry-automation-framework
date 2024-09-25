package com.cutanddry.qa.pages;

import org.openqa.selenium.By;

public class DashboardPage extends LoginPage{
    By txt_dashboard = By.xpath("//li[contains(text(),'Dashboard')]");
    By btn_customers = By.xpath("//a[@data-tip='Customers']");
    By btn_boost = By.xpath("//a[@data-tip='Boost']");
    By btn_chat = By.xpath("//a[@data-tip='Chat']");
    By txt_dashboard_restaurant = By.xpath("//div[text()='Place Order']");
    By btn_restaurant_chat = By.xpath("//div[contains(text(),'Chat')]");
    By btn_catalog = By.xpath("//a[@data-tip='View Catalog']");
    By btn_settings = By.xpath("//a[@role='button' and contains(text(), 'Settings')]");
    By btn_orderSettings = By.xpath("//div[@arrowprops]//a[text()='Orders']");
    By btn_teamSettings = By.xpath("//div[@arrowprops]//a[text()='Team']");
    By btn_users = By.xpath("//a[@data-tip='Team']");
    By txt_home = By.xpath("//li[contains(text(),'Home')]");
    By txt_all = By.xpath("//div[text()='All']");
    By txt_lastDays = By.xpath("(//div[text()='Last 30 Days'])[1]");
    By lbl_salespersonDropdown = By.xpath("(//div[contains(@class, 'css-1uccc91-singleValue')])[1]");
    By lbl_durationDropdown = By.xpath("(//div[contains(@class, 'css-1uccc91-singleValue')])[2]");
    String txt_salesperson = "//div[text()='NAME']";
    String txt_days = "//div[text()='DAYS']";

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
    public void clickOnBoost(){
        distributorUI.click(btn_boost);
    }
    public void clickOnChat(){
        distributorUI.click(btn_chat);
    }
    public boolean isRestaurantDashboardTextDisplayed(){
        try {
            distributorUI.waitForVisibility(txt_dashboard_restaurant);
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(txt_dashboard_restaurant);
    }
    public void clickOnRestaurantChat(){
        distributorUI.click(btn_restaurant_chat);
    }
    public void clickOnCatalog(){
        distributorUI.click(btn_catalog);
    }
    public void clickOnOrderSettings(){
        distributorUI.scrollToElement(btn_settings);
        distributorUI.clickUsingJavaScript(btn_settings);
        distributorUI.hoverOverElement(btn_orderSettings);
        distributorUI.click(btn_orderSettings);
    }
    public void clickOnTeamSettings(){
        distributorUI.scrollToElement(btn_settings);
        distributorUI.clickUsingJavaScript(btn_settings);
        distributorUI.hoverOverElement(btn_teamSettings);
        distributorUI.click(btn_teamSettings);
    }
    public boolean isWhiteLabelDashboardTextDisplayed(){
        try {
            distributorUI.waitForVisibility(txt_home);
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(txt_home);
    }
    public void clickOnUsers(){
        distributorUI.waitForVisibility(btn_users);
        distributorUI.click(btn_users);
    }
    public boolean isDashboardDefaultValuesDisplayed(){
        try {
            distributorUI.isDisplayed(txt_all);
            distributorUI.isDisplayed(txt_lastDays);
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(txt_all) && distributorUI.isDisplayed(txt_lastDays);
    }
    public void selectSalesperson(String name){
        distributorUI.click(lbl_salespersonDropdown);
        distributorUI.waitForVisibility(By.xpath(txt_salesperson.replace("NAME", name)));
        distributorUI.click(By.xpath(txt_salesperson.replace("NAME", name)));
    }
    public void selectDuration(String days){
        distributorUI.click(lbl_durationDropdown);
        distributorUI.waitForVisibility(By.xpath(txt_days.replace("DAYS", days)));
        distributorUI.click(By.xpath(txt_days.replace("DAYS", days)));
    }
    public boolean isDashboardSalespersonChanged(String name){
        try {
            distributorUI.isDisplayed(By.xpath(txt_salesperson.replace("NAME", name)));
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(By.xpath(txt_salesperson.replace("NAME", name)));
    }
    public boolean isDashboardDurationChanged(String days){
        try {
            distributorUI.isDisplayed(By.xpath(txt_days.replace("DAYS", days)));
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(By.xpath(txt_days.replace("DAYS", days)));
    }
}
