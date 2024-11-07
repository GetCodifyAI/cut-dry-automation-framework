package com.cutanddry.qa.tests.roles_based_access;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.*;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyARRoleTest extends TestBase {
    static User user;
    static String userAR = "Isuru Test AR";
    static String customerId = "16579";
    static String itemName = "Artichoke";

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test
    public void VerifyARRole() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");
        Login.navigateToLoginAsPortal(userAR);
        softAssert.assertTrue(Pay.isUserNavigatedToPay(),"navigation to pay error");
        //customer
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId),"customer search error");
        Customer.clickOnCustomerCode(customerId);
        softAssert.assertTrue(Customer.isSalespersonEditable(),"edit salesperson error");
        softAssert.assertTrue(Customer.isAbleToInviteUsers(),"invite users error");
        Customer.clickOnBack();
        Customer.clickOnOrderGuide(customerId);
        Customer.goToEdit();
        softAssert.assertTrue(Customer.isEditOrderGuideTextDisplayed(),"navigation error for edit");
        Customer.clickOnBack();
        Customer.getItemNameFirstRow();
        Customer.increaseFirstRowQtyByOne();
        Customer.goToCatalog();
        Customer.searchItemOnCatalog(itemName);
        softAssert.assertTrue(Customer.getFirstElementFrmSearchResults(itemName).contains(itemName), "item not found");
        Customer.addItemToCartCatalog(itemName);
        Customer.checkoutItems();
        Customer.submitOrder();
        softAssert.assertTrue(Customer.isThankingForOrderPopupDisplayed(),"order not completed");
        Customer.clickClose();
        Customer.clickOnBoostTab();
        softAssert.assertTrue(Customer.isAbleToEditMsg(),"edit boost msg error");
        Dashboard.navigateToOrders();
        softAssert.assertTrue(Orders.isUserNavigatedToOrder(),"navigate to order history error");
        //catalog
        Dashboard.navigateToCatalog();
        softAssert.assertTrue(Catalog.isUserNavigatedToCatalog(), "navigation to catalog error");
        Catalog.clickOnPreviewCatalog();
        softAssert.assertTrue(Catalog.isNavigatedToPreviewCatalog(),"navigation to preview catalog error");
        //broadcast
        Dashboard.navigateToBoost();
        softAssert.assertTrue(Boost.isUserNavigatedToBoost(),"navigate to boost error");
        softAssert.assertTrue(Boost.isBroadcastTabDisplayed(),"navigate to broadcast error");
        //reports
        Dashboard.navigateToReports();
        softAssert.assertTrue(Reports.isUserNavigatedToReports(),"navigation to reports error");
        //settings
        Dashboard.navigateToTeamSettings();
        softAssert.assertTrue(Settings.isTeamSettingsTextDisplayed(),"navigation to team settings error");
        Dashboard.navigateToCompanySettings();
        softAssert.assertTrue(Settings.isCompanySettingsTextDisplayed(),"navigation to company settings error");
        Dashboard.navigateToBillingSettings();
        softAssert.assertTrue(Settings.isBillingSettingsTextDisplayed(),"navigation to billing settings error");
//        Dashboard.navigateToPaySettings();
//        softAssert.assertTrue(Settings.isPaySettingsTextDisplayed(),"navigation to pay settings error");
        Dashboard.navigateToOrderSettings();
        softAssert.assertTrue(Settings.isOrderSettingsTextDisplayed(),"navigation to order settings error");
        Dashboard.navigateToTrackSettings();
        softAssert.assertTrue(Settings.isTrackSettingsTextDisplayed(),"navigation to track settings error");
        Dashboard.navigateToAdsSettings();
        softAssert.assertTrue(Settings.isAdsSettingsTextDisplayed(),"navigation to ads settings error");
        Dashboard.navigateToProfileSettings();
        softAssert.assertTrue(Settings.isProfileSettingsTextDisplayed(),"navigation to profile settings error");
        //track
        Dashboard.navigateToTrackRoutes();
        softAssert.assertTrue(Track.isRoutesTextDisplayed(),"navigation to track routes error");
        Dashboard.navigateToTrackResources();
        softAssert.assertTrue(Track.isResourcesTextDisplayed(),"navigation to track resources error");
        Dashboard.navigateToTrackNotifications();
        softAssert.assertTrue(Track.isNotificationsTextDisplayed(),"navigation to track notif error");
        Dashboard.navigateToTrackMonitoring();
        softAssert.assertTrue(Track.isMonitoringTextDisplayed(),"navigation to track monitoring error");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
