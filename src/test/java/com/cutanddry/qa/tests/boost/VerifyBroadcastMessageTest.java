package com.cutanddry.qa.tests.boost;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.Boost;
import com.cutanddry.qa.functions.Customer;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyBroadcastMessageTest extends TestBase {
    static User user;
    static String customerId = "16579";
    static String customerName = "Kafe Layers #3 Test - San Francisco";
    static String broadcastMessage = "Test Broadcast Message";

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }
    @Test(groups = "DOT-TC-35")
    public void verifyBroadcastMessage() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        Dashboard.isUserNavigatedToDashboard();
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");
        Dashboard.navigateToBoost();
        softAssert.assertTrue(Boost.isUserNavigatedToBoost(),"navigate to boost error");
        Boost.addMessage();
        softAssert.assertTrue(Boost.isStepOneDisplayed(),"add Message display error");
        Boost.selectCustomList();
        softAssert.assertTrue(Boost.isSelectCustomersDisplayed(),"custom List select error");
        Boost.selectCustomer(customerName);
        softAssert.assertTrue(Boost.isSelectionCountDisplayed(),"customer selection error");
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
        softAssert.assertTrue(Customer.isBroadcastMessageDisplayed(),"broadcast error");
        Customer.clickMessage();
        softAssert.assertTrue(Customer.isProductDetailsDisplayed(),"url redirect error");
        Dashboard.navigateToBoost();
        softAssert.assertTrue(Boost.isUserNavigatedToBoost(),"navigate to boost error");
        Boost.clickXButton();
        Boost.clickYes();
        softAssert.assertFalse(Boost.isDeactivated(),"deactivate error");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
