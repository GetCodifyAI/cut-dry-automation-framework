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
        distributorUI.click(btn_settings);
        distributorUI.hoverOverElement(btn_orderSettings);
        distributorUI.click(btn_orderSettings);
    }
    public void clickOnTeamSettings(){
        distributorUI.scrollToElement(btn_settings);
        distributorUI.click(btn_settings);
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
}
