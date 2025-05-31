package com.cutanddry.qa.pages;

import org.openqa.selenium.By;

public class RestaurantOrderHistoryPage extends LoginPage {

    By tbx_searchOrderHistory = By.xpath("//input[@placeholder='Search']");
    String specificOrderRecord = "//tr/td//*[text()='ORDER_ID']";

    public void clickOnSearchOrder() {
        distributorUI.click(tbx_searchOrderHistory);
    }

    public void typeOnSearchOrder(String code) throws InterruptedException {
        distributorUI.clear(tbx_searchOrderHistory);
        distributorUI.waitForCustom(1000);
        distributorUI.sendKeys(tbx_searchOrderHistory, code);
    }

    public boolean isOrderSearchResultByOrderIdDisplayed(String orderId) throws InterruptedException {
        distributorUI.waitForElementEnabledState(By.xpath(specificOrderRecord.replace("ORDER_ID", orderId)), true);
        return distributorUI.isDisplayed(By.xpath(specificOrderRecord.replace("ORDER_ID", orderId)),30);
    }

    public void clickOnSpecificRecord(String orderId) throws InterruptedException {
        distributorUI.waitForCustom(3000);
        distributorUI.click(By.xpath(specificOrderRecord.replace("ORDER_ID", orderId)));
    }

}

