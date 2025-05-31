package com.cutanddry.qa.pages;

import com.cutanddry.qa.base.TestBase;
import org.openqa.selenium.By;

public class RestaurantOrderDetailsPage extends TestBase {

    String orderTitle = "//h2[contains(text(),'Order #ORDER_ID')]";
    By btn_checkIn = By.xpath("//button[contains(text(),'Check-In Order')]");
    String lbl_orderStatus = "//div[contains(text(),'Order Status: STATUS')]";

    public boolean isOrderIdDisplayed(String orderId) throws InterruptedException {
        return distributorUI.isDisplayed(By.xpath(orderTitle.replace("ORDER_ID", orderId)),45);
    }

    public void clickOnCheckIn() throws InterruptedException {
        distributorUI.waitForCustom(5000);
        distributorUI.waitForClickability(btn_checkIn);
        distributorUI.click(btn_checkIn);
    }

    public boolean isOrderStatusDisplayed(String status) throws InterruptedException {
        distributorUI.waitForVisibility(By.xpath(lbl_orderStatus.replace("STATUS", status)));
        distributorUI.waitForCustom(4000);
        return distributorUI.isDisplayed(By.xpath(lbl_orderStatus.replace("STATUS", status)));
    }

}

