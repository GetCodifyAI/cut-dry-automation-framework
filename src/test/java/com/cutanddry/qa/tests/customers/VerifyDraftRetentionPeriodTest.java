package com.cutanddry.qa.tests.customers;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.CustomerData;
import com.cutanddry.qa.functions.Customer;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyDraftRetentionPeriodTest extends TestBase {
    static User user;
    String customerId = "16672";
    static String distributor = "Independent Foods Co";

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-1279")
    public void verifyDraftRetentionPeriod() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(), "Login error - user not navigated to restaurant dashboard");
        
        Login.navigateToDistributorPortal(distributor);
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "Login error - user not navigated to distributor dashboard");
        
        Dashboard.navigateToCustomers();
        softAssert.assertTrue(Customer.isCustomersTextDisplayed(), "Failed to navigate to customers section");
        
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Unable to find customer " + customerId);
        
        Customer.clickOnCustomerCode(customerId);
        
        Customer.clickDraftsTab();
        
        softAssert.assertTrue(Customer.isDraftRetentionMessageDisplayed(), "Draft retention message not displayed in customer profile Drafts tab");
        
        String customerDraftsMessage = Customer.getDraftRetentionMessageText();
        softAssert.assertTrue(customerDraftsMessage.contains("30 days"), 
            "Draft retention message in customer profile does not contain '30 days'. Actual message: " + customerDraftsMessage);
        
        Dashboard.navigateToDrafts();
        
        softAssert.assertTrue(Customer.isDraftRetentionMessageDisplayed(), "Draft retention message not displayed on global Drafts page");
        
        String globalDraftsMessage = Customer.getDraftRetentionMessageText();
        softAssert.assertTrue(globalDraftsMessage.contains("30 days"), 
            "Draft retention message on global Drafts page does not contain '30 days'. Actual message: " + globalDraftsMessage);
        
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
