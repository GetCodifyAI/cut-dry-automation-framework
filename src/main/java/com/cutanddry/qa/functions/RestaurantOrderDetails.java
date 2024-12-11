package com.cutanddry.qa.functions;

import com.cutanddry.qa.pages.RestaurantOrderDetailsPage;

public class RestaurantOrderDetails {
    static RestaurantOrderDetailsPage restaurantOrderDetailsPage = new RestaurantOrderDetailsPage();


    public static boolean isOrderIdDisplayed(String orderId) throws InterruptedException {
        return restaurantOrderDetailsPage.isOrderIdDisplayed(orderId);
    }

    public static void clickOnCheckIn() {
        restaurantOrderDetailsPage.clickOnCheckIn();
    }

    public static boolean isOrderStatusDisplayed(String status) throws InterruptedException {
        return restaurantOrderDetailsPage.isOrderStatusDisplayed(status);
    }

}
