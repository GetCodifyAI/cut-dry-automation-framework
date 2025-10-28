package com.cutanddry.qa.tests.integration;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Integration;
import com.cutanddry.qa.functions.InternalTools;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class Verify24HourSnapshotLastSuccessfulSyncGraphAndDataTest extends TestBase {
    static User user;
    String distributorName = "Independent Foods Co";

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-1576")
    public void verify24HourSnapshotLastSuccessfulSyncGraphAndData() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();

        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(), 
            "Login error - user not navigated to restaurant dashboard");

        Login.navigateToInternalToolsPage();

        Login.navigateToDistributorPortal(distributorName);
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(), 
            "Navigation error - user not navigated to distributor dashboard");

        Integration.navigateToIntegration();
        softAssert.assertTrue(Integration.isUserNavigatedToIntegration(), 
            "Navigation error - user not navigated to Integration page");

        softAssert.assertTrue(Integration.is24HourSnapshotDisplayed(), 
            "24 Hour Snapshot section not displayed");
        softAssert.assertTrue(Integration.isCompletedTasksDisplayed(), 
            "Completed tasks not displayed in 24 Hour Snapshot");
        softAssert.assertTrue(Integration.isInProgressTasksDisplayed(), 
            "In Progress tasks not displayed in 24 Hour Snapshot");

        softAssert.assertTrue(Integration.isSnapshotPercentageDisplayed(), 
            "24 Hour Snapshot percentage graph not displayed");
        String percentage = Integration.getSnapshotPercentage();
        softAssert.assertFalse(percentage.isEmpty(), 
            "24 Hour Snapshot percentage value is empty");

        softAssert.assertTrue(Integration.isLastSuccessfulSyncDisplayed(), 
            "Last Successful Sync section not displayed");
        softAssert.assertTrue(Integration.isCatalogSyncDisplayed(), 
            "Catalog sync data not displayed in Last Successful Sync");
        softAssert.assertTrue(Integration.isCustomerSyncDisplayed(), 
            "Customer sync data not displayed in Last Successful Sync");
        softAssert.assertTrue(Integration.isInvoiceSyncDisplayed(), 
            "Invoice sync data not displayed in Last Successful Sync");
        softAssert.assertTrue(Integration.isTermsSyncDisplayed(), 
            "Terms sync data not displayed in Last Successful Sync");

        softAssert.assertTrue(Integration.isIntegrationTableDisplayed(), 
            "Integration table not displayed");

        String catalogTimestamp = Integration.getCatalogSyncTimestamp();
        String customerTimestamp = Integration.getCustomerSyncTimestamp();
        String invoiceTimestamp = Integration.getInvoiceSyncTimestamp();
        String termsTimestamp = Integration.getTermsSyncTimestamp();

        softAssert.assertFalse(catalogTimestamp.isEmpty(), 
            "Catalog sync timestamp is empty");
        softAssert.assertFalse(customerTimestamp.isEmpty(), 
            "Customer sync timestamp is empty");
        softAssert.assertFalse(invoiceTimestamp.isEmpty(), 
            "Invoice sync timestamp is empty");
        softAssert.assertFalse(termsTimestamp.isEmpty(), 
            "Terms sync timestamp is empty");

        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
