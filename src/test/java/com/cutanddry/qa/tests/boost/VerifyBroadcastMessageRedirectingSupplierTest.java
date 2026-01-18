package com.cutanddry.qa.tests.boost;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.CatalogData;
import com.cutanddry.qa.functions.Customer;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyBroadcastMessageRedirectingSupplierTest extends TestBase {
    static User user;
    static String DP = CatalogData.DP_IFC;
    static String customerName = "Kafe Layers #3 Test - San Francisco";
    static String broadcastMessage = "Test Broadcast Message";

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-149")
    public void verifyBroadcastMessageRedirectingSupplier() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();

        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(), "login error");

        Login.navigateToLoginAs();
        Login.logInToDP(DP);
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "navigation error to dashboard");

        Dashboard.navigateToCustomers();
        softAssert.assertTrue(Customer.isCustomersTextDisplayed(), "navigation error to customers");

        Customer.searchCustomerByCode(customerName);
        softAssert.assertTrue(Customer.isCustomerSearchResultByNameDisplayed(customerName), "customer search error");

        Customer.clickOnNameOrderGuide(customerName);
        softAssert.assertTrue(Customer.isBroadcastMessageDisplayed(broadcastMessage), "broadcast message not displayed in order guide");

        Customer.clickMessage(broadcastMessage);
        softAssert.assertTrue(Customer.isProductDetailsDisplayed(), "PDP page not displayed after clicking broadcast message");

        Customer.clickOnBack();
        softAssert.assertTrue(Customer.isBroadcastMessageDisplayed(broadcastMessage), "broadcast message not displayed after clicking back");

        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
