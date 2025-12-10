package com.cutanddry.qa.pages;

import com.cutanddry.qa.base.TestBase;
import org.openqa.selenium.By;

public class RestaurantOrderDetailsPage extends TestBase {

    String orderTitle = "//*[contains(text(),'Order #ORDER_ID')]";
    By btn_checkIn = By.xpath("//button//span[contains(text(),'Check-In Order')]");
    String lbl_orderStatus = "//span[normalize-space()='Status:']/following-sibling::span[contains(text(),'STATUS')]";

    public boolean isOrderIdDisplayed(String orderId) throws InterruptedException {
        distributorUI.waitForVisibility(By.xpath(orderTitle.replace("ORDER_ID", orderId)));
        distributorUI.waitForCustom(4000);
        return distributorUI.isDisplayed(By.xpath(orderTitle.replace("ORDER_ID", orderId)));
    }

    public void clickOnCheckIn() {
        distributorUI.waitForClickability(btn_checkIn);
        distributorUI.click(btn_checkIn);
    }

    public boolean isOrderStatusDisplayed(String status) throws InterruptedException {
        distributorUI.waitForVisibility(By.xpath(lbl_orderStatus.replace("STATUS", status)));
        distributorUI.waitForCustom(4000);
        return distributorUI.isDisplayed(By.xpath(lbl_orderStatus.replace("STATUS", status)));
    }

}

