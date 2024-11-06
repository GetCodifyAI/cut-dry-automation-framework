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

public class VerifyAdminRoleTest extends TestBase {
    static User user;
    static String customerId = "16579";

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test
    public void VerifyAdminRole() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        //dashboard
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");
        //customer
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId),"customer search error");
        Customer.clickOnCustomerCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId),"search error");
        softAssert.assertTrue(Customer.isSalespersonEditable(),"edit salesperson error");
        softAssert.assertTrue(Customer.isAbleToInviteUsers(),"invite users error");
        Customer.clickOnOrderGuide(customerId);
        Customer.goToEdit();
        softAssert.assertTrue(Customer.isEditOrderGuideTextDisplayed(),"navigation error for edit");
        Customer.clickOnBack();

        //catalog
        Dashboard.navigateToCatalog();
        softAssert.assertTrue(Catalog.isUserNavigatedToCatalog(), "navigation to catalog error");
        Catalog.clickOnPreviewCatalog();
        softAssert.assertTrue(Catalog.isNavigatedToPreviewCatalog(),"navigation to preview catalog error");
        //broadcast
        Dashboard.navigateToBoost();
        softAssert.assertTrue(Boost.isUserNavigatedToBoost(),"navigate to boost error");
        softAssert.assertTrue(Boost.isBroadcastTabDisplayed(),"navigate to broadcast error");
        //promotions
        //pay
        Dashboard.navigateToPay();
        softAssert.assertTrue(Pay.isUserNavigatedToPay(),"navigation to pay error");
        //reports
        Dashboard.navigateToReports();
        softAssert.assertTrue(Reports.isUserNavigatedToReports(),"navigation to reports error");
        //settings
        Dashboard.navigateToBillingSettings();
        softAssert.assertTrue(Settings.isBillingSettingsTextDisplayed(),"navigation to billing settings error");
        Dashboard.navigateToCompanySettings();
        softAssert.assertTrue(Settings.isCompanySettingsTextDisplayed(),"navigation to company settings error");
        Dashboard.navigateToOrderSettings();
        softAssert.assertTrue(Settings.isOrderSettingsTextDisplayed(),"navigation to order settings error");
        Dashboard.navigateToProfileSettings();
        softAssert.assertTrue(Settings.isProfileSettingsTextDisplayed(),"navigation to profile settings error");
        Dashboard.navigateToTeamSettings();
        softAssert.assertTrue(Settings.isTeamSettingsTextDisplayed(),"navigation to team settings error");
        Dashboard.navigateToTrackSettings();
        softAssert.assertTrue(Settings.isTrackSettingsTextDisplayed(),"navigation to track settings error");
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
