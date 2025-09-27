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

public class VerifyOrderSummaryBannerDisplaysOnSimpleListViewTest extends TestBase {
    static User user;
    String customerId = CustomerData.CUSTOMER_CODE3; // 16579 - verified during manual testing
    static String orderSummeryTotalLines = CustomerData.ORDER_SUMMERY_TOTAL_LINES;
    static String orderSummeryTotalPieces = CustomerData.ORDER_SUMMERY_TOTAL_PIECES;

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-1867")
    public void verifyOrderSummaryBannerDisplaysOnSimpleListView() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "Login error - user not navigated to distributor dashboard");

        Dashboard.navigateToCustomers();
        softAssert.assertTrue(Customer.isCustomersTextDisplayed(), "Failed to navigate to customers section");

        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Unable to find the customer ID: " + customerId);
        Customer.clickOnOrderGuide(customerId);
        double totalPrice = Customer.getItemPriceFirstRow();

        Customer.expandMoreOptionsDropdown();
        Customer.clickSimpleListView();
        softAssert.assertTrue(Customer.isSimpleListViewTextDisplay(), "Simple List View not displayed after clicking");

        Customer.increaseFirstRowQtyByOne();
        softAssert.assertTrue(Customer.orderSummeryDisplay("Order Summary"), "Order Summary banner not displayed on Simple List view");

        softAssert.assertTrue(Customer.isOrderSummeryValueDisplayed(orderSummeryTotalLines,"1"),"Order Summery TotalLines value not equal");
        softAssert.assertEquals(Customer.getTotalPriceCart(),totalPrice, "Order Summary total price should be greater than 0 after adding items");

        Customer.checkoutItems();
        softAssert.assertTrue(Customer.isReviewOrderTextDisplayed(), "Review order page not displayed after checkout");

        Customer.submitOrder();
        softAssert.assertTrue(Customer.isThankingForOrderPopupDisplayed(), "Thank you for your order popup not displayed - order submission failed");

        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
