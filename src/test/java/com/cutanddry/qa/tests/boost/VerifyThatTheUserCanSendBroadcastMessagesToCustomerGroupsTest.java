package com.cutanddry.qa.tests.boost;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.*;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyThatTheUserCanSendBroadcastMessagesToCustomerGroupsTest extends TestBase {
    static User user;
    static String customerId = "97071";
    static String customerId2 = "16579";
    static String OperatorName = "235564457";
    static String broadcastMessage = "Test Broadcast Message";
    static String customerGroupName,salespersonName,deliveryDate;
    String DistributorName ="Independent Foods Co";

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }
    @Test(groups = "DOT-TC-1571")
    public void VerifyThatTheUserCanSendBroadcastMessagesToCustomerGroups() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        Assert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");
        Login.navigateToDistributorPortal(DistributorName);
        Dashboard.isUserNavigatedToDashboard();
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");
        Dashboard.navigateToBoost();
        softAssert.assertTrue(Boost.isUserNavigatedToBoost(),"navigate to boost error");
        Boost.clearExistingBoostMessageIfExists();

        Boost.addMessage();
        softAssert.assertTrue(Boost.isStepOneDisplayed(),"add Message display error");
        Boost.selectFilteredGroup();
        softAssert.assertTrue(Boost.isCustomerGroupsLabelDisplayed(),"filtered groups select error");
        customerGroupName = Boost.selectFirstCustomerGroup();
        salespersonName = Boost.selectFirstSalesPerson();
        deliveryDate = Boost.selectDeliveryDates();

        Boost.clickContinue();

        softAssert.assertTrue(Boost.isStepTwoDisplayed(),"continuation error");
        Boost.customizeMessage(broadcastMessage);
        Boost.clickSubmit();
        softAssert.assertTrue(Boost.isBroadcastSuccessDisplayed(),"broadcast error");
        Boost.clickOk();

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId),"search error");
        Customer.clickOnOrderGuide(customerId);

        softAssert.assertTrue(Customer.isBroadcastMessageDisplayed(broadcastMessage),"broadcast error");

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId2);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId2),"search error");
        Customer.clickOnOrderGuide(customerId2);

        softAssert.assertFalse(Customer.isBroadcastMessageDisplayed(broadcastMessage),"broadcast error");

        Login.closePreviousTab();

        Login.navigateToLoginAs();
        Login.logInToOperatorAsWhiteLabel(OperatorName);
        Dashboard.navigateToOrder();
        softAssert.assertTrue(Dashboard.isUserNavigatedToOrderGuide(),"navigation error");
        softAssert.assertTrue(Customer.isBroadcastMessageDisplayed(broadcastMessage),"broadcast error");

        Login.navigateToRestaurant();
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(), "The user is unable to land on the Dashboard page.");
        Dashboard.navigateToIndependentFoodsCo();
        Dashboard.navigateToOrderGuide();
        softAssert.assertFalse(Customer.isBroadcastMessageDisplayed(broadcastMessage),"broadcast error");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
