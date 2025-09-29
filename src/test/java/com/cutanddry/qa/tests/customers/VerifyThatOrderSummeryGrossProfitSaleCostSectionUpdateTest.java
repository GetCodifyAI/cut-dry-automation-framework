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
import java.math.BigDecimal;
import java.math.RoundingMode;

public class VerifyThatOrderSummeryGrossProfitSaleCostSectionUpdateTest extends TestBase {
    static User user;
    String customerId = CustomerData.CUSTOMER_ID_SW;
    static String distributor = CustomerData.DISTRIBUTOR_SW;
    static String orderSummery = CustomerData.ORDER_SUMMERY;
    static String orderSummerySalesCommission = CustomerData.ORDER_SUMMERY_SALES_COMMISSION;
    static String orderSummeryTotalLines = CustomerData.ORDER_SUMMERY_TOTAL_LINES;
    static String orderSummeryTotalPieces = CustomerData.ORDER_SUMMERY_TOTAL_PIECES;
    static String itemName, searchItemCode;
    static double itemPrice,marginPrice;

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();


    }

    @Test(groups = "DOT-TC-417")
    public void VerifyThatOrderSummeryGrossProfitSaleCostSectionUpdate() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(), "login error");

        Login.navigateToDistributorPortal(distributor);
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Unable to find the customer Id");
        Customer.clickOnOrderGuide(customerId);

        itemName = Customer.getItemNameFirstRow();
        searchItemCode = Customer.getItemCodeFirstRow();
        itemPrice = Customer.getActiveItemPriceFirstRow();
        marginPrice = Customer.getItemMarginPriceFirstRow();
        BigDecimal marginValue = new BigDecimal(Double.toString(marginPrice));
        marginValue = marginValue.setScale(2, RoundingMode.DOWN);
        Customer.increaseFirstRowQtyCustom(1);
//        softAssert.assertTrue(Customer.isOrderSummeryValueDisplayed(orderSummery,marginValue.toPlainString()),"gross profit value not equal");
        Customer.expandShrinkedOrderSummery();
        softAssert.assertTrue(
                Customer.isOrderSummeryValueDisplayed(orderSummery, marginValue.setScale(1, RoundingMode.DOWN).toPlainString()),
                "gross profit value not equal"
        );
        softAssert.assertTrue(Customer.isOrderSummeryValueDisplayed(orderSummerySalesCommission,"0"),"Order Summery Sales Commission value not equal");
        softAssert.assertTrue(Customer.isOrderSummeryValueDisplayed(orderSummeryTotalLines,"1"),"Order Summery TotalLines value not equal");
        softAssert.assertTrue(Customer.isOrderSummeryValueDisplayed(orderSummeryTotalPieces,"1"),"Order Summery TotalPieces value not equal");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
