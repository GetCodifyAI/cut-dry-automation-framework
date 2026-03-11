package com.cutanddry.qa.tests.customer_activity_log;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.ActivityData;
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

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class VerifyCreateNewActivityInActivityLogTest extends TestBase {
    static User user;
    String customerId = CustomerData.CUSTOMER_CODE2;
    String phoneCallActivityType = ActivityData.ActivityTypePhoneCall;
    String ActivitySummery = ActivityData.ActivitySummery;
    String ActivityDescription = ActivityData.ActivityDescription;


    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-4410")
    public void VerifyCreateNewActivityInActivityLog() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "login error");
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Customer search result not displayed");
        Customer.clickOnCustomerCode(customerId);
        Customer.clickOnActivityLogTab();
        softAssert.assertTrue(Customer.isActivityLogTabSelected(), "Activity Log tab not selected");
        Customer.clickOnActivityButtonInActivityTab();
        softAssert.assertTrue(Customer.isAddActivityModalDisplayed(), "Add Activity modal not displayed");
        Customer.selectActivityType(phoneCallActivityType);

        LocalDate today = LocalDate.now();
        DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("d");
        String userDeliveryDate = customFormatter.format(today);
        Customer.selectActivityDate(userDeliveryDate);

        Customer.enterActivitySummary(ActivitySummery);
        Customer.enterActivityDescription(ActivityDescription);


        Customer.clickAddActivitySubmit();
        softAssert.assertTrue(Customer.isActivityCardDisplayed(), "Activity card not displayed after creation");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
