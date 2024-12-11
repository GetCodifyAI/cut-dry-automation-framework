package com.cutanddry.qa.pages;

import com.cutanddry.qa.base.TestBase;
import org.openqa.selenium.By;

public class RestaurantCheckInPage extends TestBase {

    String checkInTitle = "//h2[contains(text(),'Check-In Order #ORDER_ID')]";
    By btn_checkInAll = By.xpath("//button[contains(text(),'Check-In All')]");
    By lbl_checkInAllPopup = By.xpath("//h2[contains(text(),'Check-in all items?')]");
    By btn_confirm = By.xpath("//button[contains(text(),'Confirm')]");
    By btn_cancel = By.xpath("//button[contains(text(),'Cancel')]");

    public boolean isCheckInOrderIdDisplayed(String orderId) throws InterruptedException {
        distributorUI.waitForVisibility(By.xpath(checkInTitle.replace("ORDER_ID", orderId)));
        distributorUI.waitForCustom(4000);
        return distributorUI.isDisplayed(By.xpath(checkInTitle.replace("ORDER_ID", orderId)));
    }

    public void clickOnCheckInAll() {
        distributorUI.waitForClickability(btn_checkInAll);
        distributorUI.click(btn_checkInAll);
    }

    public boolean isCheckInAllPopupDisplayed() {
        try {
            distributorUI.waitForVisibility(lbl_checkInAllPopup);
            return distributorUI.isDisplayed(lbl_checkInAllPopup);
        } catch (Exception e) {
            return false;
        }
    }

    public void clickConfirm() {
        distributorUI.waitForClickability(btn_confirm);
        distributorUI.click(btn_confirm);
    }

    public void clickClose() {
        distributorUI.waitForClickability(btn_cancel);
        distributorUI.click(btn_cancel);
    }


}

