package com.cutanddry.qa.tests.integration;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.Chat;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyChatFeatureTest extends TestBase {
    static User user;
    static String customerName = "Restaurant (Test) - Hayes";

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-42")
    public void verifyChatFeature() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        Dashboard.isUserNavigatedToDashboard();
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");
        Dashboard.navigateToChat();
        softAssert.assertTrue(Chat.isUserNavigatedToChat(),"navigation error");
        Chat.searchCustomerByName(customerName);
        softAssert.assertTrue(Chat.isCustomerSearchResultDisplayed(customerName),"search error");
        Chat.clickOnCustomerChat(customerName);
        Chat.sendDistributorMessage();
        softAssert.assertEquals(Chat.getLastMessageDisplayed(),"Test Message Distributor","messaging sending error");
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");
        Dashboard.navigateToRestaurantChat();
        Chat.clickOnRestaurantChat();
        softAssert.assertEquals(Chat.getLastMessageDisplayed(),"Test Message Distributor","messaging receiving error");
        Chat.sendRestaurantMessage();
        softAssert.assertEquals(Chat.getLastMessageDisplayed(),"Test Message Restaurant","messaging sending error");
        Login.navigateToDistributor();
        Dashboard.isUserNavigatedToDashboard();
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");
        Dashboard.navigateToChat();
        softAssert.assertTrue(Chat.isUserNavigatedToChat(),"navigation error");
        Chat.searchCustomerByName(customerName);
        softAssert.assertTrue(Chat.isCustomerSearchResultDisplayed(customerName),"search error");
        Chat.clickOnCustomerChat(customerName);
        softAssert.assertEquals(Chat.getLastMessageDisplayed(),"Test Message Restaurant","messaging receiving error");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
