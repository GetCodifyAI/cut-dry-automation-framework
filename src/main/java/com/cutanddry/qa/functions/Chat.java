package com.cutanddry.qa.functions;

import com.cutanddry.qa.pages.ChatPage;

public class Chat {
    static ChatPage chatPage = new ChatPage();

    public static boolean isUserNavigatedToChat(){
        return chatPage.isChatTextDisplayed();
    }
    public static void searchCustomerByName(String name){
        chatPage.clickOnSearchCustomers();
        chatPage.typeOnSearchCustomers(name);
    }
    public static boolean isCustomerSearchResultDisplayed(String name) {
        return chatPage.isCustomerSearchResultDisplayed(name);
    }
    public static void clickOnCustomerChat(String name){
        chatPage.clickOnCustomerChat(name);
    }
    public static void sendDistributorMessage(String msg) throws InterruptedException {
        chatPage.clickOnMessage();
        chatPage.typeOnMessage(msg);
        chatPage.clickSendButton();
    }
    public static String getLastMessageDisplayed() throws InterruptedException {
        return chatPage.getLastMessageDisplayed();
    }

    public static void clickOnRestaurantChat() {
        chatPage.clickOnRestaurantChat();
    }
    public static void sendRestaurantMessage(String msg) throws InterruptedException {
        chatPage.clickOnMessage();
        chatPage.typeOnMessage(msg);
        chatPage.clickSendButton();
    }
}
