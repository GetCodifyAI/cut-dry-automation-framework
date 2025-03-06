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

import java.util.Random;

public class VerifyUserCanIncreaseDecreaseQuantitiesFromScanToOrderFlowTest extends TestBase {
    static User user;
    static String featureName = "scan_to_order";
    static String companyID = "46017666";
    static String DP = "Independent Foods Co";
    static String CustomerCode = "21259";
    static String itemName, searchItemCode;

    @BeforeMethod
    public void setup(){
        initialization();
        user = JsonUtil.readUserLogin();
    }
    

    @Test(groups = "DOT-TC-1116")
    public static void VerifyUserCanIncreaseDecreaseQuantitiesFromScanToOrderFlow() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();

        Random random = new Random();
        int randomQuantity = random.nextInt(10)+1;

        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(), "login error");

        Login.navigateToGateKeeperAdmin();
        Login.updateCompanyIDs(featureName, companyID);
        Login.navigateToDistributorPortal(DP);
        Dashboard.navigateToCustomers();
        softAssert.assertTrue(Customer.isNavigatedToCustomerPage(),"Error in navigating to customer Page");

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



        softAssert.assertAll();

    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
