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

public class VerifyUserCanOrderJITAndPropItemsViaScanToOrderTest extends TestBase {
    static SoftAssert softAssert = new SoftAssert();
    static User user;
    static String featureName = "scan_to_order";
    static String companyID = "152858455";
    static String DP = "Wagner Foodservice";
    static String CustomerCode = "29052";
    static String JITItemKeyword = "JIT";
    static String PropItemCode = "625214";
    static String JITItemItemCode ;
    static String DataName = "proprietaryitemsfilter";

    @BeforeMethod
    public void setup(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-1124")
    public static void VerifyUserCanOrderJITAndPropItemsViaScanToOrder() throws InterruptedException {
        Random random = new Random();
        int randomQuantity = random.nextInt(8) + 3;

        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(), "login error");
        Login.navigateToGateKeeperAdmin();
        Login.updateCompanyIDs(featureName, companyID);
        Login.navigateToSupplierPortalVendor(companyID);
        Login.setNode(DataName);

        Login.navigateToDistributorPortal(DP);
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(CustomerCode);
        softAssert.assertTrue(Customer.isNavigatedToCustomerPage(),"Error in navigating to customer Page");
        Customer.clickOnOrderGuide(CustomerCode);
        Orders.selectOrderGuide(DP);
        Customer.goToCatalog();
        Customer.searchItemOnCatalog(JITItemKeyword);
        JITItemItemCode = Customer.getCatalogFirstItemItemCode();

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(CustomerCode);
        softAssert.assertTrue(Customer.isNavigatedToCustomerPage(),"Error in navigating to customer Page");
        softAssert.assertTrue(Customer.isScanToOrderBtnDisplayedInCustomers(CustomerCode),"Customers Screen Scan To order button is not displayed");
        Customer.navigateFromCustomerScreenToScanToOrderScreen(CustomerCode);
        softAssert.assertTrue(ScanToOrder.isNavigatedToScanToOrderPage(),"Error in navigating to scan to order screen");
        ScanToOrder.enterItemCodeToScanToOrderItemInputField(JITItemItemCode);
        ScanToOrder.AddItemsToCart();
        softAssert.assertTrue(ScanToOrder.isItemAddedToTheCart(JITItemItemCode),"Item is not added to the cart");
        ScanToOrder.IncreaseItemQty(JITItemItemCode,randomQuantity);

        ScanToOrder.enterItemCodeToScanToOrderItemInputField(PropItemCode);
        ScanToOrder.AddItemsToCart();
        softAssert.assertTrue(ScanToOrder.isItemAddedToTheCart(PropItemCode),"Item is not added to the cart");
        ScanToOrder.IncreaseItemQty(PropItemCode,randomQuantity);

        ScanToOrder.ReviewAndConfirm();
        softAssert.assertTrue(Customer.isReviewOrderTextDisplayed(), "The user is unable to land on the Review Order page.");
        Customer.selectPickUpDateAsLast();
        Customer.submitOrder();
        String OrderId = Customer.getSuccessOrderId();
        softAssert.assertTrue(Customer.isThankingForOrderPopupDisplayed(),"order not completed");

        Customer.clickOrderSuccessMessageClose();
        Customer.clickOnCustomerCode(CustomerCode);
        Customer.clickOnOrdersTab();
        softAssert.assertTrue(RestaurantOrderHistory.isOrderSearchResultByOrderIdDisplayed(OrderId), "Unable to find the specific order Id");

        softAssert.assertAll();

    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }

}
