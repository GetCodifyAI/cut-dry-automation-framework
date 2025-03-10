package com.cutanddry.qa.tests.scan_to_order;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.*;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Random;

public class VerifyThatTheAccountsReceivableUsersCanPlaceTheScanToOrdersTest extends TestBase {
    static User user;
    static String featureName = "scan_to_order";
    static String userAR = "Isuru Test AR";
    static String companyID = "46017666";
    static String DP = "Independent Foods Co";
    static String CustomerCode = "21259";
    static String itemName, searchItemCode;

    @BeforeMethod
    public void setup(){
        initialization();
        user = JsonUtil.readUserLogin();
    }
    

    @Test(groups = "DOT-TC-1146")
    public static void VerifyThatTheAccountsReceivableUsersCanPlaceTheScanToOrders() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();

        Random random = new Random();
        int randomQuantity = random.nextInt(10)+1;

        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");

        Login.navigateToGateKeeperAdmin();
        Login.updateCompanyIDs(featureName,companyID);

        Login.navigateToLoginAsPortal(userAR);
        softAssert.assertTrue(Pay.isUserNavigatedToPay(),"navigation to pay error");
        Dashboard.navigateToCustomers();
        Customer.clickOnOrderGuide(CustomerCode);
        searchItemCode = Customer.getItemCodeFirstRow();
        Dashboard.navigateToCustomers();
        softAssert.assertTrue(Customer.isNavigatedToCustomerPage(),"Error in navigating to customer Page");

        Customer.searchCustomerByCode(CustomerCode);
        softAssert.assertTrue(Customer.isScanToOrderBtnDisplayedInCustomers(CustomerCode),"Customers Screen Scan To order button is not displayed");
        Customer.navigateFromCustomerScreenToScanToOrderScreen(CustomerCode);
        softAssert.assertTrue(ScanToOrder.isNavigatedToScanToOrderPage(),"Error in navigating to scan to order screen");
        ScanToOrder.enterItemCodeToScanToOrderItemInputField(searchItemCode);
        ScanToOrder.AddItemsToCart();
        softAssert.assertTrue(ScanToOrder.isItemAddedToTheCart(searchItemCode),"Item is not added to the cart");
        double itemPriceBefore = ScanToOrder.getItemPriceOfItem(searchItemCode);
        ScanToOrder.IncreaseItemQty(searchItemCode,randomQuantity);
        double itemPriceAfter = ScanToOrder.getItemPriceOfItem(searchItemCode);
        softAssert.assertEquals(itemPriceAfter,itemPriceBefore*randomQuantity);
        ScanToOrder.ReviewAndConfirm();
        softAssert.assertTrue(Customer.isReviewOrderTextDisplayed(), "The user is unable to land on the Review Order page.");
        Customer.submitOrder();
        softAssert.assertTrue(Customer.isThankingForOrderPopupDisplayed(),"order not completed");


        softAssert.assertAll();

    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
