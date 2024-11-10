package com.cutanddry.qa.tests.customer_specific;

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

public class VerifyCustomerSpecificBroadcastMessageTest extends TestBase {
    static User user;
    static String customerId = "16579";
    static String broadcastMessage = "Broadcast Message Test";

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }
    @Test(groups = "DOT-TC-267")
    public void verifyCustomerSpecificBroadcastMessage() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId),"search error");
        Customer.clickOnCustomerCode(customerId);
        Customer.clickOnBoostTab();
        softAssert.assertTrue(Customer.isBroadcastTextDisplayed(),"navigation error");
        Customer.clickOnEditMessage();
        Customer.typeBroadcastMessage(broadcastMessage);
        Customer.clickOnSaveMessage();
        Customer.clickOnOrderGuideInProfile();
        softAssert.assertTrue(Customer.isBroadcastMessageDisplayed(broadcastMessage),"broadcast error");
        Customer.clickOnBack();
        Customer.clickOnClearMessage();
        Customer.clickOnOrderGuideInProfile();
        softAssert.assertFalse(Customer.isBroadcastMessageDisplayed(broadcastMessage),"broadcast clear error");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
