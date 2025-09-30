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

public class VerifyThatOrderSummerySalesCommissionSectionUpdateTest extends TestBase {
    static User user;
    String customerId = CustomerData.CUSTOMER_ID_SW;
    static String distributor = CustomerData.DISTRIBUTOR_SW;
    static String orderSummerySalesCommission = CustomerData.ORDER_SUMMERY_SALES_COMMISSION;
    static String orderSummeryTotalLines = CustomerData.ORDER_SUMMERY_TOTAL_LINES;
    static String orderSummeryTotalPieces = CustomerData.ORDER_SUMMERY_TOTAL_PIECES;
    static String itemName ,saleCommission ;
    static double itemPrice;
    static String searchItemCode = "44686";

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();


    }

    @Test(groups = "DOT-TC-419")
    public void VerifyThatOrderSummerySalesCommissionSectionUpdate() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(), "login error");

        Login.navigateToDistributorPortal(distributor);
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Unable to find the customer Id");
        Customer.clickOnOrderGuide(customerId);

        Customer.searchItemOnOrderGuide(searchItemCode);
        Customer.increaseFirstRowQtyCustom(10);
        itemName = Customer.getItemNameFirstRow();
        itemPrice = Customer.getActiveItemPriceFirstRow();
        Customer.expandShrinkedOrderSummery();
        saleCommission = Customer.getSaleCommissionValue();
        softAssert.assertNotNull(Customer.getSaleCommissionValue(),"commission error");
        softAssert.assertNotEquals(Customer.getSaleCommissionValue(),"$0 | 0%","commission not add");
        softAssert.assertTrue(Customer.isOrderSummeryValueDisplayed(orderSummerySalesCommission,saleCommission),"Order Summery Sales Commission value not equal");
        softAssert.assertTrue(Customer.isOrderSummeryValueDisplayed(orderSummeryTotalLines,"1"),"Order Summery TotalLines value not equal");
        softAssert.assertTrue(Customer.isOrderSummeryValueDisplayed(orderSummeryTotalPieces,"10"),"Order Summery TotalPieces value not equal");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
