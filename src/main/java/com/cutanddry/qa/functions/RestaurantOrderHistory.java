package com.cutanddry.qa.functions;

import com.cutanddry.qa.pages.RestaurantOrderHistoryPage;

public class RestaurantOrderHistory {
    static RestaurantOrderHistoryPage restaurantOrderHistoryPage = new RestaurantOrderHistoryPage();


    public static void searchOrderByOrderId(String code) throws InterruptedException {
        restaurantOrderHistoryPage.clickOnSearchOrder();
        restaurantOrderHistoryPage.typeOnSearchOrder(code);
    }

    public static boolean isOrderSearchResultByOrderIdDisplayed(String orderId) throws InterruptedException {
        return restaurantOrderHistoryPage.isOrderSearchResultByOrderIdDisplayed(orderId);
    }

    public static void clickOnSpecificRecord(String orderId) throws InterruptedException {
        restaurantOrderHistoryPage.clickOnSpecificRecord(orderId);

    }

}
