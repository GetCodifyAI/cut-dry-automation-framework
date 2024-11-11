package com.cutanddry.qa.pages;

import org.openqa.selenium.By;

public class TrackPage extends LoginPage{
    By txt_trackResources = By.xpath("//li[contains(text(),'Resources')]");
    By btn_addDriver = By.xpath("//button[@type='button' and contains(text(), 'Add Driver')]");
    By btn_addTrucks = By.xpath("//button[@type='button' and contains(text(), 'Add Truck')]");
    By tb_drivers =By.xpath("//a[@role='tab' and @data-rb-event-key='Drivers' and text()='Drivers']");
    By tb_trucks =By.xpath("//a[@role='tab' and @data-rb-event-key='Trucks' and text()='Trucks']");
    By txt_trackRoutes = By.xpath("//li[contains(text(),'Routes')]");
    By txt_trackMon = By.xpath("//li[contains(text(),'Monitoring')]");
    By txt_trackNotif = By.xpath("//li[contains(text(),'Notifications')]");

    public boolean isTrackResourcesTextDisplayed(){
        try {
            distributorUI.waitForVisibility(txt_trackResources);
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(txt_trackResources);
    }
    public boolean isAddDriversBtnDisplayed(){
        distributorUI.waitForVisibility(btn_addDriver);
        return distributorUI.isDisplayed(btn_addDriver);
    }
    public boolean isAddTrucksBtnDisplayed(){
        distributorUI.waitForVisibility(btn_addTrucks);
        return distributorUI.isDisplayed(btn_addTrucks);
    }
    public void clickOnTrucks() {
        distributorUI.waitForVisibility(tb_trucks);
        distributorUI.click(tb_trucks);
    }
    public void clickOnDrivers() {
        distributorUI.waitForVisibility(tb_drivers);
        distributorUI.click(tb_drivers);
    }
    public boolean isTrackRoutesTextDisplayed(){
        try {
            distributorUI.waitForVisibility(txt_trackRoutes);
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(txt_trackRoutes);
    }
    public boolean isTrackMonitoringTextDisplayed(){
        try {
            distributorUI.waitForVisibility(txt_trackMon);
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(txt_trackMon);
    }
    public boolean isTrackNotificationsTextDisplayed(){
        try {
            distributorUI.waitForVisibility(txt_trackNotif);
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(txt_trackNotif);
    }

}
