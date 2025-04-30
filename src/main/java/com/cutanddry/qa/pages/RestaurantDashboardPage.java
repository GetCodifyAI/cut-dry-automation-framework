package com.cutanddry.qa.pages;

import com.cutanddry.qa.base.TestBase;
import org.openqa.selenium.By;

public class RestaurantDashboardPage extends TestBase {

    By pgTitle_history = By.xpath("//h2[contains(text(),'Order History')]");
    By btn_history = By.xpath("//a[@data-tip='Order History']");

    public boolean isHistoryTitleDisplayed() {
        try {
            distributorUI.waitForVisibility(pgTitle_history);
        } catch (Exception e) {
            return false;
        }
        return distributorUI.isDisplayed(pgTitle_history);
    }

    public void clickOnHistory() throws InterruptedException {
        distributorUI.waitForCustom(3000);
        distributorUI.click(btn_history);
    }

}
