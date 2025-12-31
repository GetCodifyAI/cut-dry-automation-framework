package com.cutanddry.qa.tests.customer_orders;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.PriceData;
import com.cutanddry.qa.functions.Customer;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyOrderReferenceColumnDisplayWithErpOrderBadgeTest extends TestBase {
    static User user;
    static String distributorWagner = PriceData.DISTRIBUTOR_WAGNER;
    static String customerCode = "515";

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-3617")
    public void verifyOrderReferenceColumnDisplayWithErpOrderBadge() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();

        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(), "Login failed - Restaurant Dashboard not displayed");

        Login.navigateToDistributorPortal(distributorWagner);
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "Navigation to distributor portal failed");

        Dashboard.navigateToCustomers();
        softAssert.assertTrue(Customer.isCustomersTextDisplayed(), "Customers section not displayed");

        Customer.searchCustomerByCode(customerCode);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerCode), "Customer not found");

        String businessName = Customer.getBusinessNameFromCustomers(customerCode);
        Customer.SelectCustomer(customerCode);
        softAssert.assertTrue(Customer.isCustomerProfileDisplayed(businessName), "Customer profile not displayed");

        Customer.clickOnOrdersTab();
        softAssert.assertTrue(Customer.isOrdersTabDisplayed(), "Orders tab not displayed");

        softAssert.assertTrue(Customer.isOrderReferenceColumnHeaderDisplayed(), "Order Reference column header not displayed");

        softAssert.assertTrue(Customer.isFirstOrderReferenceNumberDisplayed(), "Order reference number not displayed");

        String orderReferenceNumber = Customer.getFirstOrderReferenceNumber();
        softAssert.assertTrue(orderReferenceNumber.startsWith("#"), "Order reference number does not start with # prefix. Actual: " + orderReferenceNumber);

        softAssert.assertTrue(Customer.isFirstErpOrderBadgeDisplayed(), "ERP Order badge not displayed for ERP-synced orders");

        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
