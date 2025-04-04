package com.cutanddry.qa.tests.multi_uom;

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

public class VerifyStockAvailabilityModalForMultiUOMSKUsTest extends TestBase {
    static User user;
    String customerId = CustomerData.CUSTOMER_CODE;
    static String distributor = CustomerData.DISTRIBUTOR_NAME;
    static String itemCode = CustomerData.ITEM_CODE_2;
    static String stockAvailability = CustomerData.STOCK_AVAILABILITY;
    static String currentStock = CustomerData.CURRENT_STOCK;
    static String onOrderQty = CustomerData.ON_ORDER_QTY;
    static String nextPoQty = CustomerData.NEXT_PO_QTY;
    static String nextPoDate = CustomerData.NEXT_PO_DATE;

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();


    }

    @Test(groups = "DOT-TC-1089")
    public void VerifyStockAvailabilityModalForMultiUOMSKUs() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(), "login error");

        Login.navigateToDistributorPortal(distributor);
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Unable to find the customer Id");
        Customer.clickOnOrderGuide(customerId);
        Customer.searchItemOnOrderGuide(itemCode);
        Customer.clickStock();
        softAssert.assertTrue(Customer.isStockAvailabilityDisplayed(stockAvailability),"stock availability not display");
        softAssert.assertTrue(Customer.isStockAvailabilityDisplayed(currentStock),"current Stock not display");
        softAssert.assertTrue(Customer.isStockAvailabilityDisplayed(onOrderQty),"On Order Qty not display");
        softAssert.assertTrue(Customer.isStockAvailabilityDisplayed(nextPoQty),"Next Po Qty not display");
        softAssert.assertTrue(Customer.isStockAvailabilityDisplayed(nextPoDate),"Next Po Date not display");
        Customer.ClickOnMultiUomDropDownOG(itemCode);
        Customer.clickStock();
        softAssert.assertTrue(Customer.isStockAvailabilityDisplayed(stockAvailability),"stock availability not display");
        softAssert.assertTrue(Customer.isMultiUOMStockAvailabilityDisplayed(currentStock),"current Stock not display");
        softAssert.assertTrue(Customer.isMultiUOMStockAvailabilityDisplayed(onOrderQty),"On Order Qty not display");
        softAssert.assertTrue(Customer.isMultiUOMStockAvailabilityDisplayed(nextPoQty),"Next Po Qty not display");
        softAssert.assertTrue(Customer.isMultiUOMStockAvailabilityDisplayed(nextPoDate),"Next Po Date not display");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }


}
