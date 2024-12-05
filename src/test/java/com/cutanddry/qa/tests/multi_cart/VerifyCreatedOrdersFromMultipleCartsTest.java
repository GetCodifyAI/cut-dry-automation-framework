package com.cutanddry.qa.tests.multi_cart;

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

public class VerifyCreatedOrdersFromMultipleCartsTest extends TestBase {
    static User user;
    static String DP = "Butterfield & Vallis";
    static String customerId = "34315";
    static String itemName_1 = "Beef Bouillon Cubes";
    static String itemName_2 = "Sugar Dark Brown";
    static int orderCount = 2;

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-136")
    public void verifyCreatedOrdersFromMultipleCarts() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");
        Login.navigateToDistributorPortal(DP);
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"navigation error");
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId),"search error");
        Customer.clickOnOrderGuide(customerId);
        Customer.searchItemOnOrderGuide(itemName_1);
        softAssert.assertTrue(Customer.getItemNameFirstRow().contains(itemName_1),"item 1 mismatch");
        Customer.increaseFirstRowQtyByOneInDist();
        Customer.searchItemOnOrderGuide(itemName_2);
        softAssert.assertTrue(Customer.getItemNameFirstRow().contains(itemName_2),"item 2 mismatch");
        Customer.increaseFirstRowQtyByOneInDist();
        Customer.checkoutItemsDist();
        softAssert.assertTrue(Customer.isMultiDistCentersDisplayed(),"multi cart error");
        Customer.submitOrder();
        softAssert.assertTrue(Customer.isThankingForOrderPopupDisplayed(),"order not completed");
        softAssert.assertEquals(Customer.getOrderCount(orderCount), orderCount, "multi order submit error error");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
