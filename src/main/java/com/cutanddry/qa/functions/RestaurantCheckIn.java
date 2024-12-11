package com.cutanddry.qa.functions;

import com.cutanddry.qa.pages.RestaurantCheckInPage;
import com.cutanddry.qa.pages.RestaurantOrderDetailsPage;

public class RestaurantCheckIn {
    static RestaurantCheckInPage restaurantCheckInPage = new RestaurantCheckInPage();


    public static boolean isCheckInOrderIdDisplayed(String orderId) throws InterruptedException {
        return restaurantCheckInPage.isCheckInOrderIdDisplayed(orderId);
    }

    public static void clickOnCheckInAll() {
        restaurantCheckInPage.clickOnCheckInAll();
    }

    public static boolean isCheckInAllPopupDisplayed() {
        return restaurantCheckInPage.isCheckInAllPopupDisplayed();
    }

    public static void clickConfirm() {
        restaurantCheckInPage.clickConfirm();

    }

    public static void clickClose() {
        restaurantCheckInPage.clickClose();

    }

}
