package com.cutanddry.qa.pages;

import org.openqa.selenium.By;

public class ChatPage extends LoginPage{
    By txt_chat = By.xpath("//h2[contains(text(),'Chat')]");
    By tbx_searchCustomers = By.xpath("//input[@placeholder='Search Customers']");
    String customerName = "//div[text()='NAME']";
    By tbx_Message = By.xpath("//input[@placeholder='Message...']");
    By icon_send = By.xpath("//span[contains(@class, '_1j33wau')]");
    By txt_lastMessage = By.xpath("(//div[contains(@class, 'card-body')])[last()]");
    By btn_chat_restaurant = By.xpath("//div[contains(text(), 'Independent Foods Co')]");
    By btn_hayes_restaurant = By.xpath("//div[contains(text(), 'Hayes')]");
    By txt_defaultMsg = By.xpath("//div[text()=Send us messages through this chat interface to clarify issues with products and orders.");

    public boolean isChatTextDisplayed() {
        try {
            distributorUI.waitForVisibility(txt_chat);
        } catch (Exception e) {
            return false;
        }
        return distributorUI.isDisplayed(txt_chat);
    }
    public void clickOnSearchCustomers(){
        distributorUI.click(tbx_searchCustomers);
    }
    public void typeOnSearchCustomers(String name){
        distributorUI.sendKeys(tbx_searchCustomers, name);
    }
    public boolean isCustomerSearchResultDisplayed(String name) {
        distributorUI.waitForVisibility(By.xpath(customerName.replace("NAME", name)));
        return distributorUI.isDisplayed(By.xpath(customerName.replace("NAME", name)));
    }
    public void clickOnCustomerChat(String name) {
        distributorUI.click(By.xpath(customerName.replace("NAME", name)));
        if (distributorUI.isDisplayed(txt_defaultMsg)) {
            distributorUI.waitForInvisibility(txt_defaultMsg);
        }
    }
    public void clickOnMessage(){
        distributorUI.waitForVisibility(tbx_Message);
        distributorUI.click(tbx_Message);
    }
    public void typeOnMessage(String msg) throws InterruptedException {
        distributorUI.clear(tbx_Message);
        distributorUI.waitForCustom(3000);
        distributorUI.sendKeys(tbx_Message, msg);
    }
    public void clickSendButton(){
        distributorUI.waitForElementEnabledState(icon_send,true);
        distributorUI.waitForClickability(icon_send);
        distributorUI.click(icon_send);
        distributorUI.waitForVisibility(icon_send);
    }
    public String getLastMessageDisplayed() throws InterruptedException {
        distributorUI.waitForCustom(2000);
        return distributorUI.getText(txt_lastMessage);
    }
    public void clickOnRestaurantChat() {
        distributorUI.waitForVisibility(btn_chat_restaurant);
        distributorUI.click(btn_chat_restaurant);
        distributorUI.waitForVisibility(btn_hayes_restaurant);
        distributorUI.click(btn_hayes_restaurant);
    }
}
