package com.cutanddry.qa.pages;

import org.openqa.selenium.By;

public class HistoryPage extends LoginPage{
    By txt_orderHistory = By.xpath("//h2[contains(text(),'Order History')]");
    By lbl_firstItemInHistory = By.xpath("//tr[contains(@href,'/orders-revised/view-one')][1]");

    public boolean isOrderHistoryTextDisplayed() {
        try {
            distributorUI.waitForVisibility(txt_orderHistory);
        } catch (Exception e) {
            return false;
        }
        return distributorUI.isDisplayed(txt_orderHistory);
    }
    public void clickFirstItemFrmHistory(){
        distributorUI.waitForClickability(lbl_firstItemInHistory);
        distributorUI.click(lbl_firstItemInHistory);
    }
}
