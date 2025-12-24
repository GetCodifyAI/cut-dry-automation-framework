package com.cutanddry.qa.tests.scan_to_order;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.Customer;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.functions.ScanToOrder;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyScanItemsToOrderFunctionalityInMoreOptionsMenuTest extends TestBase {
    static User user;
    static String featureName = "scan_to_order";
    static String companyID = "46017666";
    static String DP = "Independent Foods Co";
    static String CustomerCode = "21259";
    static String ItemCode;

    @BeforeMethod
    public void setup() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-3467")
    public void VerifyScanItemsToOrderFunctionalityInMoreOptionsMenu() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();

        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(), "Login error - user not navigated to dashboard");

        Login.navigateToGateKeeperAdmin();
        Login.updateCompanyIDs(featureName, companyID);

        Login.navigateToDistributorPortal(DP);
        Dashboard.navigateToCustomers();
        Customer.clickOnOrderGuide(CustomerCode);
        ItemCode = Customer.getItemCodeFirstRow();

        Customer.expandMoreOptionsDropdown();
        softAssert.assertTrue(Customer.isScanItemsToOrderDisplayedInMoreOptions(), "Scan Items to Order option is not displayed in More Options menu");

        Customer.clickOnScanItemsToOrder();
        softAssert.assertTrue(ScanToOrder.isNavigatedToScanToOrderPage(), "Error in navigating to Scan to Order screen");

        softAssert.assertFalse(ScanToOrder.isReviewAndConfirmBtnEnabled(), "Review and Confirm Button should be disabled initially");
        softAssert.assertFalse(ScanToOrder.isAddToCartButtonEnabled(), "Add to Cart Button should be disabled initially");

        ScanToOrder.selectScanToOrderItemInputField();
        ScanToOrder.enterItemCodeToScanToOrderItemInputField(ItemCode);
        softAssert.assertTrue(ScanToOrder.isAddToCartButtonEnabled(), "Add to Cart Button is not enabled after entering item code");

        ScanToOrder.AddItemsToCart();
        softAssert.assertTrue(ScanToOrder.isItemAddedToTheCart(ItemCode), "Item is not added to the cart");
        softAssert.assertTrue(ScanToOrder.isReviewAndConfirmBtnEnabled(), "Review and Confirm Button is not enabled after adding item");

        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
