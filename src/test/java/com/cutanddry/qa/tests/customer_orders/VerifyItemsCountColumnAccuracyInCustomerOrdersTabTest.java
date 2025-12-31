package com.cutanddry.qa.tests.customer_orders;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.Customer;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyItemsCountColumnAccuracyInCustomerOrdersTabTest extends TestBase {
    static User user;
    String customerCode = "16579";

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-3619")
    public void verifyItemsCountColumnAccuracyInCustomerOrdersTab() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();

        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "Login failed - Dashboard not displayed");

        Dashboard.navigateToCustomers();
        softAssert.assertTrue(Customer.isCustomersTextDisplayed(), "Customers section not displayed");

        Customer.searchCustomerByCode(customerCode);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerCode), "Customer not found");

        Customer.SelectCustomer(customerCode);
        softAssert.assertTrue(Customer.isOrdersTabDisplayed(), "Orders tab not displayed in customer profile");

        Customer.clickOnOrdersTab();
        softAssert.assertTrue(Customer.isItemsColumnHeaderDisplayed(), "Items column header not displayed in orders table");

        softAssert.assertTrue(Customer.isOrdersListDisplayed(), "Orders list not displayed");

        String itemsCountFromList = Customer.getFirstOrderItemsCount();
        softAssert.assertNotNull(itemsCountFromList, "Items count not displayed for the first order");
        softAssert.assertFalse(itemsCountFromList.isEmpty(), "Items count is empty for the first order");

        Customer.clickFirstOrderFrmOrderTab();
        softAssert.assertTrue(Customer.isOrderSectionDisplayed(), "Order details section not displayed");

        int actualLineItemsCount = Customer.getOrderDetailsLineItemsCount();
        int expectedItemsCount = Integer.parseInt(itemsCountFromList);
        softAssert.assertEquals(actualLineItemsCount, expectedItemsCount,
                "Items count mismatch - List shows: " + expectedItemsCount + ", Order details has: " + actualLineItemsCount);

        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
