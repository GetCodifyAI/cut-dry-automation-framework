package com.cutanddry.qa.tests.order_guide;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.CustomerData;
import com.cutanddry.qa.functions.*;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyOrderSummaryBannerDisplaysOnSimpleListViewTest extends TestBase {
    static User user;
    static String operatorName = "Independent Foods Co";
    static String orderSummerySalesCommission = CustomerData.ORDER_SUMMERY_SALES_COMMISSION;
    static String orderSummeryTotalLines = CustomerData.ORDER_SUMMERY_TOTAL_LINES;
    static String orderSummeryTotalPieces = CustomerData.ORDER_SUMMERY_TOTAL_PIECES;
    static String orderSummery = CustomerData.ORDER_SUMMERY;
    static String distributor = "Independent Foods Co";
    String customerId = "16579";

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-1867")
    public void verifyOrderSummaryBannerDisplaysOnSimpleListView() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(), "Login error - user not navigated to operator dashboard");

        Login.navigateToDistributorPortal(distributor);
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Unable to find the customer Id");
        Customer.clickOnOrderGuide(customerId);
        String itemName = Customer.getItemNameFirstRow();

        Customer.expandShrinkedOrderSummery();
        Customer.expandMoreOptionsDropdown();
        Customer.clickSimpleListView();
        softAssert.assertTrue(Customer.isSimpleListViewTextDisplay(), "Simple List View not displayed after clicking");
        softAssert.assertTrue(Customer.orderSummeryDisplay(orderSummery),"order summery not display");
        Customer.increaseFirstRowQtyByOne();

        softAssert.assertTrue(Customer.isOrderSummeryValueDisplayed(orderSummerySalesCommission,"0"),"Order Summery Sales Commission value not equal");
        softAssert.assertTrue(Customer.isOrderSummeryValueDisplayed(orderSummeryTotalLines,"1"),"Order Summery TotalLines value not equal");
        softAssert.assertTrue(Customer.isOrderSummeryValueDisplayed(orderSummeryTotalPieces,"1"),"Order Summery TotalPieces value not equal");

        Customer.checkoutItems();
        softAssert.assertTrue(Customer.isReviewOrderTextDisplayed(), "Review order page not displayed after checkout");
        
        double reviewTotalPrice = Customer.getReviewTotalPriceCart();
        softAssert.assertTrue(reviewTotalPrice > 0, "Review order total price should be greater than 0");
        softAssert.assertEquals(Customer.getItemNameFirstRow(), itemName, "Item name mismatch between cart and review sections");
        
        Customer.submitOrder();
        softAssert.assertTrue(Customer.isThankingForOrderPopupDisplayed(), "Thank you for your order popup not displayed - order submission failed");
        
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsersAtOnce();
    }
}
