package com.cutanddry.qa.pages;

import com.cutanddry.qa.base.TestBase;
import org.openqa.selenium.By;

public class OrderGuideSettingsPage extends TestBase {

    By AddAlertButton = By.xpath("//*[name()='svg' and @data-icon='pencil']");
    By AddAlertPrimaryButton = By.xpath("//button[contains(text(),'Add Alert')]");
    By txt_orderReminderAlertPopUp = By.xpath("//div[contains(text(), 'Order Reminder Alert')]");
    By orderCutoffTimeDropDown = By.xpath("//label[contains(text(),'Order Cutoff Time')]/following-sibling::div");
    String orderCutoffTimeDropDownOption = "//div[text()='CUTOFFTIME']";
    By sendAlertDropDown = By.xpath("//label[contains(text(),'Send Alert')]/following-sibling::div");
    String sendAlertDropDownOption = "//div[text()='ALERTTIME']";
    By alertDaysDropDown = By.xpath("//label[contains(text(),'Alert Days')]/following-sibling::div");
    String alertDaysDropDownOption = "//label[text()='Alert Days']/following-sibling::div//*[text()='ALERTDAYS']";
    By btn_orderReminderAlertsave = By.xpath("//div[@class='d-flex flex-column form-group']//button[text()='Save']");
    By btn_save = By.xpath("//button[normalize-space(text())='Save']");

    public void clickOnOrderReminderAlert(){
        if (distributorUI.isDisplayed(AddAlertButton,10)) {
            distributorUI.waitForClickability(AddAlertButton);
            distributorUI.click(AddAlertButton);
        } else {
            distributorUI.click(AddAlertPrimaryButton);
        }

    }

    public boolean isOrderReminderAlertPopDisplayed(){
        try {
            distributorUI.waitForCustom(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        distributorUI.waitForVisibility(txt_orderReminderAlertPopUp);
        return distributorUI.isDisplayed(txt_orderReminderAlertPopUp);
    }

    public void clickOrderCutoffTime(String cutofftime)throws InterruptedException{
        distributorUI.waitForClickability(orderCutoffTimeDropDown);
        distributorUI.click(orderCutoffTimeDropDown);
        distributorUI.click(By.xpath(orderCutoffTimeDropDownOption.replace("CUTOFFTIME",cutofftime)));
    }

    public void clickSendAlert(String alerttime)throws InterruptedException{
        distributorUI.waitForClickability(sendAlertDropDown);
        distributorUI.click(sendAlertDropDown);
        distributorUI.click(By.xpath(sendAlertDropDownOption.replace("ALERTTIME",alerttime)));
    }

    public void clickAlertDays(String alertdays)throws InterruptedException{
        distributorUI.waitForClickability(alertDaysDropDown);
        distributorUI.click(alertDaysDropDown);
        distributorUI.click(By.xpath(alertDaysDropDownOption.replace("ALERTDAYS",alertdays)));
    }

    public void clickOrderRemiderAlertSettingSave() throws InterruptedException{
        distributorUI.waitForClickability(btn_orderReminderAlertsave);
        distributorUI.click(btn_orderReminderAlertsave);
    }

    public void clickOnSave(){
        distributorUI.waitForVisibility(btn_save);
        distributorUI.clickWithFallback(btn_save);
        try {
            distributorUI.waitForCustom(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean isOrderReminderAlertSettingDisplayed()throws InterruptedException{
        return distributorUI.isDisplayed(AddAlertButton);
    }
}
