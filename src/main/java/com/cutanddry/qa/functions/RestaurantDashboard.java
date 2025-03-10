package com.cutanddry.qa.functions;

import com.cutanddry.qa.pages.RestaurantDashboardPage;

public class RestaurantDashboard {
    static RestaurantDashboardPage restaurantDashboard = new RestaurantDashboardPage();

    public static boolean isUserNavigatedToHistory() {
        return restaurantDashboard.isHistoryTitleDisplayed();
    }

    public static void navigateToHistory() throws InterruptedException {
        restaurantDashboard.clickOnHistory();
    }

}
