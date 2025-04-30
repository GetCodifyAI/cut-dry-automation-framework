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

public class VerifyScanToOrderUsersCanRemoveItemsUsingBinIconTest extends TestBase {

    static User user;
    static String featureName = "scan_to_order";
    static String companyID = "46017666";
    static String DP = "Independent Foods Co";
    static String CustomerCode = "21259";
    static String ItemCode1 = "87910";
    static String emptyCartText1 = "Scan to add items to your cart";
    static String emptyCartText2 = "Use the barcode scanner to scan the barcode on the item or";

    @BeforeMethod
    public void setup(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-1120")
    public static void VerifyScanToOrderUsersCanRemoveItemsUsingBinIcon() throws InterruptedException{

        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(),user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(CustomerCode);
        softAssert.assertTrue(Customer.isScanToOrderBtnDisplayedInCustomers(CustomerCode),"Customers Screen Scan To order button is not displayed");
        Customer.navigateFromCustomerScreenToScanToOrderScreen(CustomerCode);
        softAssert.assertTrue(ScanToOrder.isNavigatedToScanToOrderPage(),"Error in navigating to scan to order screen");

        softAssert.assertFalse(ScanToOrder.isReviewAndConfirmBtnEnabled(),"Review and Confirm Button is not disabled");
        softAssert.assertFalse(ScanToOrder.isAddToCartButtonEnabled(),"Add to Cart Button is not disabled");
        ScanToOrder.selectScanToOrderItemInputField();
        ScanToOrder.enterItemCodeToScanToOrderItemInputField(ItemCode1);
        softAssert.assertTrue(ScanToOrder.isAddToCartButtonEnabled(),"Add to Cart Button is not enabled");
        ScanToOrder.AddItemsToCart();
        softAssert.assertTrue(ScanToOrder.isItemAddedToTheCart(ItemCode1),"Item is not added to the cart");
        softAssert.assertTrue(ScanToOrder.isReviewAndConfirmBtnEnabled(),"Review and Confirm Button is not enabled");

        ScanToOrder.deleteScanToOrderItems(ItemCode1);
        softAssert.assertTrue(ScanToOrder.isEmptyCartTextDisplayed(emptyCartText1,emptyCartText2),"Item hasn't deleted successfully");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }


}


