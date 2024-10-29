package com.cutanddry.qa.pages;

import org.openqa.selenium.By;

public class OrdersPage extends LoginPage{
    By txt_orders = By.xpath("//h2[contains(text(),'Orders')]");
    String supplierNameInPlaceOrder = "//div[contains(text(),'SUPPLIERNAME')]";

    public boolean isOrdersTextDisplayed(){
        try {
            distributorUI.waitForVisibility(txt_orders);
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(txt_orders);
    }

    public void clickOnSupplier(String supplierName){
        distributorUI.click(By.xpath(supplierNameInPlaceOrder.replace("SUPPLIERNAME",supplierName)));
    }

}
