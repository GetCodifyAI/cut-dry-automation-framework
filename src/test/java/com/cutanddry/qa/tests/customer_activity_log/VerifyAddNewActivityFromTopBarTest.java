package com.cutanddry.qa.tests.customer_activity_log;

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

public class VerifyAddNewActivityFromTopBarTest extends TestBase {
    static User user;
    String customerId = CustomerData.CUSTOMER_CODE2;

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-4413")
    public void VerifyAddNewActivityFromTopBar() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "login error");
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Customer search result not displayed");
        Customer.clickOnCustomerCode(customerId);
        Customer.clickOnActivityLogTab();
        softAssert.assertTrue(Customer.isActivityLogTabSelected(), "Activity Log tab not selected");
        Customer.clickOnActivityButtonTopBar();
        softAssert.assertTrue(Customer.isAddActivityModalDisplayed(), "Add Activity modal not displayed from top bar");
        Customer.selectActivityType("In Person Meeting");
        Customer.enterActivitySummary("Top bar activity summary");
        Customer.enterActivityDescription("Activity created from top bar button");
        Customer.clickAddActivitySubmit();
        softAssert.assertTrue(Customer.isActivityCardDisplayed(), "Activity card not displayed after creation from top bar");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
