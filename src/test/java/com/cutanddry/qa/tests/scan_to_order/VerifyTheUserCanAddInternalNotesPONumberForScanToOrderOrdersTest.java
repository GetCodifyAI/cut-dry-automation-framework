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

public class VerifyTheUserCanAddInternalNotesPONumberForScanToOrderOrdersTest extends TestBase {
    static User user;
    static SoftAssert softAssert = new SoftAssert();
    static String featureName = "scan_to_order";
    static String companyID = "46017666";
    static String DP = "Independent Foods Co";
    static String CustomerCode = "21259";
    static String ItemCode1;
    static String special_Instructions = "This is a special Instruction";
    static String PO_Number = "2003";
    static String internal_Note = "This is a Test internal Note";
    static String customer_Note = "This is a Test Customer Note";

    @BeforeMethod
    public void setup(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-1133")
    public static void VerifyTheUserCanAddInternalNotesPONumberForScanToOrderOrders() throws InterruptedException {
        Random random = new Random();
        int randomQuantity = random.nextInt(8) + 3;

        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(), "login error");
        Login.navigateToGateKeeperAdmin();
        Login.updateCompanyIDs(featureName, companyID);

        Login.navigateToDistributorPortal(DP);
        Dashboard.navigateToCustomers();
        softAssert.assertTrue(Customer.isNavigatedToCustomerPage(),"Error in navigating to customer Page");

        Customer.clickOnOrderGuide(CustomerCode);
        ItemCode1 = Customer.getItemCodeFirstRow();
        Dashboard.navigateToCustomers();
        softAssert.assertTrue(Customer.isNavigatedToCustomerPage(),"Error in navigating to customer Page");
        Customer.searchCustomerByCode(CustomerCode);
        softAssert.assertTrue(Customer.isScanToOrderBtnDisplayedInCustomers(CustomerCode),"Customers Screen Scan To order button is not displayed");

        Customer.navigateFromCustomerScreenToScanToOrderScreen(CustomerCode);
        softAssert.assertTrue(ScanToOrder.isNavigatedToScanToOrderPage(),"Error in navigating to scan to order screen");
        ScanToOrder.enterItemCodeToScanToOrderItemInputField(ItemCode1);
        ScanToOrder.AddItemsToCart();
        softAssert.assertTrue(ScanToOrder.isItemAddedToTheCart(ItemCode1),"Item is not added to the cart");
        ScanToOrder.IncreaseItemQty(ItemCode1,randomQuantity);
        ScanToOrder.ReviewAndConfirm();
        softAssert.assertTrue(Customer.isReviewOrderTextDisplayed(), "The user is unable to land on the Review Order page.");
        Customer.typeSpecialInstruction(special_Instructions);
        Customer.typePONumber(PO_Number);
        Customer.typeInternalNote(internal_Note);
        Customer.typeNoteToCustomer(customer_Note);
        Customer.submitOrder();
        softAssert.assertTrue(Customer.isPONumberCorrectlyDisplayed(PO_Number),"Displayed PO Number is incorrect");
        softAssert.assertTrue(Customer.isThankingForOrderPopupDisplayed(),"order not completed");

        Customer.clickOrderSuccessMessageClose();
        Customer.clickOnCustomerCode(CustomerCode);
        Customer.clickOnOrdersTab();
        Customer.clickFirstOrderFrmOrderTab();
        softAssert.assertTrue(Customer.isSpecialInstructionDisplayed(special_Instructions),"Error in displaying special Instructions");
        softAssert.assertTrue(Customer.isInternalNoteDisplayed(internal_Note),"Error in displaying Internal Note");
        softAssert.assertTrue(Customer.isNoteToCustomerDisplayed(customer_Note),"Error in displaying Internal Note");

        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) throws InterruptedException {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }

}
