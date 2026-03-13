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

public class VerifyActivityTypeDropdownAndDateRangeFilterTest extends TestBase {
    static User user;
    String customerId = CustomerData.CUSTOMER_CODE2;

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-4411")
    public void VerifyActivityTypeDropdownAndDateRangeFilter() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "login error");
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Customer search result not displayed");
        Customer.clickOnCustomerCode(customerId);
        Customer.clickOnActivityLogTab();
        softAssert.assertTrue(Customer.isActivityLogTabSelected(), "Activity Log tab not selected");
        Customer.clickActivityTypeFilter();
        softAssert.assertTrue(Customer.isActivityTypeFilterOptionDisplayed("All"), "Activity Type 'All' option not displayed");
        softAssert.assertTrue(Customer.isActivityTypeFilterOptionDisplayed("Note"), "Activity Type 'Note' option not displayed");
        softAssert.assertTrue(Customer.isActivityTypeFilterOptionDisplayed("Phone Call"), "Activity Type 'Phone Call' option not displayed");
        softAssert.assertTrue(Customer.isActivityTypeFilterOptionDisplayed("In Person Meeting"), "Activity Type 'In Person Meeting' option not displayed");
        softAssert.assertTrue(Customer.isActivityTypeFilterOptionDisplayed("Email"), "Activity Type 'Email' option not displayed");
        softAssert.assertTrue(Customer.isActivityTypeFilterOptionDisplayed("Text Message"), "Activity Type 'Text Message' option not displayed");
        softAssert.assertTrue(Customer.isActivityTypeFilterOptionDisplayed("Other"), "Activity Type 'Other' option not displayed");
        Customer.selectActivityTypeFilterOption("Note");
        Customer.clickDateRangeFilter();
        softAssert.assertTrue(Customer.isDateRangeFilterOptionDisplayed("Last 7 Days"), "Date Range 'Last 7 Days' option not displayed");
        softAssert.assertTrue(Customer.isDateRangeFilterOptionDisplayed("Last 30 Days"), "Date Range 'Last 30 Days' option not displayed");
        softAssert.assertTrue(Customer.isDateRangeFilterOptionDisplayed("Last 90 Days"), "Date Range 'Last 90 Days' option not displayed");
        softAssert.assertTrue(Customer.isDateRangeFilterOptionDisplayed("Last 12 Months"), "Date Range 'Last 12 Months' option not displayed");
        Customer.selectDateRangeFilterOption("Last 7 Days");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
