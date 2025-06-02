package com.cutanddry.qa.functions;

import com.cutanddry.qa.pages.DashboardPage;

import static com.cutanddry.qa.functions.Customer.customersPage;

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
    public static boolean isUserNavigatedToDashboardWhiteLabel(){
        return dashboardPage.isWhiteLabelDashboardTextDisplayed();
    }
    public static void navigateToUsersWhiteLabel() {
        dashboardPage.clickOnUsers();
    }
    public static void navigateToOrders(){
        dashboardPage.clickOnOrders();
    }
    public static void navigateToOrderDesk(){
        dashboardPage.clickOnOrderDesk();
    }
    public static boolean isDashboardDefaultValuesDisplayed(){
        return dashboardPage.isDashboardDefaultValuesDisplayed();
    }
    public static void selectSalesperson(String name) {
        dashboardPage.selectSalesperson(name);
    }
    public static void selectDuration(String days) {
        dashboardPage.selectDuration(days);
    }
    public static boolean isDashboardSalespersonChanged(String name){
        return dashboardPage.isDashboardSalespersonChanged(name);
    }
    public static boolean isDashboardDurationChanged(String days){
        return dashboardPage.isDashboardDurationChanged(days);
    }
    public static boolean isTeamStandingsDisplayed(){
        return dashboardPage.isTeamStandingsDisplayed();
    }
    public static String[] getTotalSumDisplayed() {
        return dashboardPage.getTotalSumDisplayed();
    }
    public static void navigateToHistory() {
        dashboardPage.clickOnHistory();
    }
    public static void navigateToDrafts() {
        dashboardPage.clickOnDrafts();
    }
    public static void navigateToTrackResources() {
        dashboardPage.clickOnTrackResources();
    }
    public static void navigateToTrackRoutes() {
        dashboardPage.clickOnTrackRoutes();
    }
    public static void navigateToTrackMonitoring() {
        dashboardPage.clickOnTrackMonitoring();
    }
    public static void navigateToTrackNotifications() {
        dashboardPage.clickOnTrackNotifications();
    }
    public static void navigateToPay() {
        dashboardPage.clickOnPay();
    }
    public static void navigateToReports() {
        dashboardPage.clickOnReports();
    }
    public static void navigateToCompanySettings() {
        dashboardPage.clickOnCompanySettings();
    }
    public static void navigateToBillingSettings() {
        dashboardPage.clickOnBillingSettings();
    }
    public static void navigateToTrackSettings() {
        dashboardPage.clickOnTrackSettings();
    }
    public static void navigateToProfileSettings() {
        dashboardPage.clickOnProfileSettings();
    }
    public static void navigateToPaySettings() {
        dashboardPage.clickOnPaySettings();
    }
    public static void navigateToAdsSettings() {
        dashboardPage.clickOnAdsSettings();
    }
    public static void navigateToSupport() {
        dashboardPage.clickOnSupport();
    }
    public static void navigateToTracker() {
        dashboardPage.clickOnTracker();
    }
    public static void navigateToEndlessAisle() {
        dashboardPage.clickOnEndlessAisle();
    }
    public static void navigateToCreditRequest() {
        dashboardPage.clickOnCreditRequest();
    }
    public static void navigateToShowCasePage(){
        dashboardPage.clickOnShowCase();
    }
    public static void navigateToCreditRequests(){
        dashboardPage.clickOnCreditRequests();
    }
    public static void navigateToIndependentFoodsCo()throws InterruptedException{
        dashboardPage.clickLocationFilter();
        dashboardPage.clickOnLocationOption();
        dashboardPage.clickOnPlaceOrder();
    }
    public static void navigateToOrderGuide() throws InterruptedException {
        dashboardPage.clickOnHayes();
        if (dashboardPage.isTestAutomationPopupDisplayed()){
            dashboardPage.clickOnTestAutomationPopup();
        }
        if (customersPage.isPreviousDraftOrderNoDisplayedSub()){
            customersPage.clickPreviousDraftOrderNo();
        }
    }
    public static boolean isUserNavigatedToOrderGuide(){
        return dashboardPage.isOrderGuideTextDisplayed();
    }


    public static void navigateToOrderPageWhiteLabel(){
        dashboardPage.navigateToWhiteLabelOrdersPage();
    }

    public static void selectIndependantFoodCo(){
        DashboardPage.selectIndependentFoodCo();
    }

    public static boolean isCompanyIndependentFood() {
        String companyName = DashboardPage.getTextAfterCompany();
        System.out.println("The current company name is "+ companyName);
        return "Independent Foods Co".equals(companyName);
    }

    public static void selectIndependentFoodCo(){
        DashboardPage.selectIndependentFoodCo();
    }

    public static void clickOnPlaceOrderBtn(){
        dashboardPage.clickOnPlaceOrderBtn();
    }

    public static void navigateToApprovalsTab(){
        dashboardPage.clickOnApproval();
    }
    public static void navigateToOrder() throws InterruptedException {
        dashboardPage.clickOnOrder();
        if (customersPage.isPreviousDraftOrderNoDisplayedSub()){
            customersPage.clickPreviousDraftOrderNo();
        }
    }
    public static boolean isCustomerDisplayed(){
        return dashboardPage.isCustomerDisplayed();
    }
    public static boolean isOrderIndicatorDisplay(){
        return dashboardPage.isOrderIndicatorDisplay();
    }
    public static boolean isChatSectionDisplay() throws InterruptedException {
        return dashboardPage.isChatSectionDisplay();
    }

    public static void refreshPage() {
        dashboardPage.refreshPage();
    }

}
