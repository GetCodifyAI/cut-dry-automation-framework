package com.cutanddry.qa.functions;

import com.cutanddry.qa.pages.DashboardPage;

public class Dashboard {
    static DashboardPage dashboardPage = new DashboardPage();

    public static boolean isUserNavigatedToDashboard(){
        return dashboardPage.isDashboardTextDisplayed();
    }

    public static void navigateToCustomers() {
        dashboardPage.clickOnCustomers();
    }
    public static void navigateToBoost() {
        dashboardPage.clickOnBoost();
    }
    public static void navigateToChat() {
        dashboardPage.clickOnChat();
    }
    public static boolean isUserNavigatedToRestaurantDashboard(){
        return dashboardPage.isRestaurantDashboardTextDisplayed();
    }
    public static void navigateToRestaurantChat() {
        dashboardPage.clickOnRestaurantChat();
    }

    public static void navigateToCatalog() {
        dashboardPage.clickOnCatalog();
    }
    public static void navigateToOrderSettings() {
        dashboardPage.clickOnOrderSettings();
    }
    public static void navigateToTeamSettings() {
        dashboardPage.clickOnTeamSettings();
    }

}
