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


public class VerifyScanToOrderAddedItemDetailsCorrectlyDisplayedInScanToOrderScreenTest extends TestBase{

    static User user;
    static String featureName = "scan_to_order";
    static String companyID = "46017666";
    static String DP = "QA ONLY : test distributor";
    static String CustomerCode = "21259";
    static String ItemCode1 = "87910";
    static String ItemCode2 = "63018";
    static String [] ItemCodes = {"87910","63018"};


    @BeforeMethod
    public void setup(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-1121")
    public static void VerifyScanToOrderAddedItemDetailsCorrectlyDisplayedInScanToOrderScreen() throws InterruptedException{
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

        ScanToOrder.enterItemCodeToScanToOrderItemInputField(ItemCode2);
        softAssert.assertTrue(ScanToOrder.isAddToCartButtonEnabled(),"Add to Cart Button is not enabled");
        ScanToOrder.AddItemsToCart();
        softAssert.assertTrue(ScanToOrder.isItemAddedToTheCart(ItemCode2),"Item is not added to the cart");
        softAssert.assertTrue(ScanToOrder.isReviewAndConfirmBtnEnabled(),"Review and Confirm Button is not enabled");

        softAssert.assertTrue(ScanToOrder.isTotalQuantityTextDisplayed(),"Total quantity text is not displayed");
        int product_Quantity = ScanToOrder.getProductQuantityValue();
        int product_quantityOnly = ScanToOrder.getProducttotalQuantityValues();
        softAssert.assertEquals(product_Quantity, product_quantityOnly, "Wrong quantity");

        softAssert.assertTrue(ScanToOrder.isTotalLineItemsTextDisplayed(),"Total Line Item text is not displayed");
        int cartLineItemCount = ScanToOrder.getCartLineItemCount();
        int cartItemCount = ScanToOrder.getCartItemCountInOrderSummary();
        softAssert.assertEquals(cartLineItemCount,cartItemCount,"Wrong quantity");


        softAssert.assertTrue(ScanToOrder.isTotalEstimatedCostTextDisplayed(),"Total Estimated Cost text is not displayed");
        double totalCostOrderSummary = ScanToOrder.getTotalCostInOrderSummary();
        double totalPrice = ScanToOrder.getTotalCartPrice(ItemCodes);
        softAssert.assertEquals(totalCostOrderSummary,totalPrice,"Wrong Price");

        softAssert.assertTrue(ScanToOrder.isTotalDiscountsTextDisplayed());
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }



}
