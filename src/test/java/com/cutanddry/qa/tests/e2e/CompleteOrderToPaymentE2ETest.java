package com.cutanddry.qa.tests.e2e;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.testdata.DistributorOrderData;
import com.cutanddry.qa.data.testdata.CustomerInvoiceData;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.*;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.util.ArrayList;

public class CompleteOrderToPaymentE2ETest extends TestBase {
    SoftAssert softAssert;
    static User user;
    static String customerId = DistributorOrderData.RESTAURANT_TEST_HAYES_ID;
    static String itemName, orderId, searchItemCode, invoiceId;
    static double itemPrice;
    static String cardNumber = "4111111111111111";
    static String cvv = "999";
    static String expDate = "10/25";
    static String zipCode = "11500";

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-E2E-001")
    public void completeOrderToPaymentE2EFlow() throws InterruptedException {

        softAssert = new SoftAssert();

        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        Assert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "The user is unable to land on the Dashboard page.");
        Customer.ensureCarouselDisplayStatus(false);

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        Assert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Unable to find the customer Id");
        Customer.clickOnOrderGuide(customerId);

        itemName = Customer.getItemNameFirstRow();
        searchItemCode = Customer.getItemCodeFirstRow();
        itemPrice = Customer.getActiveItemPriceFirstRow();
        Customer.goToCatalog();

        Customer.searchItemOnCatalog(searchItemCode);
        softAssert.assertTrue(Customer.getFirstElementFrmSearchResults(itemName).contains(itemName.toLowerCase()), "item not found");
        Customer.clickOnPlusIconInCatalogPDP(1, itemName);
        softAssert.assertEquals(Customer.getItemPriceOnCheckoutButton(),itemPrice,"The item has not been selected.");
        Customer.checkoutItems();

        softAssert.assertTrue(Customer.isReviewOrderTextDisplayed(), "The user is unable to land on the Review Order page.");
        softAssert.assertEquals(Customer.getItemNameFirstRow(), itemName, "The item selected by the user is different from what is shown on the order review page.");
        softAssert.assertTrue(Customer.isDeliveryOptionSelected(), "The expected fulfillment type is not selected.");
        Customer.submitOrder();
        softAssert.assertTrue(Customer.isThankingForOrderPopupDisplayed(), "The order was not completed successfully.");
        orderId = Customer.getSuccessOrderId();
        Customer.clickClose();

        Login.switchIntoNewTab();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        Assert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(), "The user is unable to land on the Dashboard page.");

        RestaurantDashboard.navigateToHistory();
        Assert.assertTrue(RestaurantDashboard.isUserNavigatedToHistory(), "The user is unable to land on the History page.");
        RestaurantOrderHistory.searchOrderByOrderId(orderId);
        softAssert.assertTrue(RestaurantOrderHistory.isOrderSearchResultByOrderIdDisplayed(orderId), "Unable to find the specific order Id");
        RestaurantOrderHistory.clickOnSpecificRecord(orderId);
        softAssert.assertTrue(RestaurantOrderDetails.isOrderIdDisplayed(orderId), "The newly added order ID does not match the order reference number on the Order History page.");
        RestaurantOrderDetails.clickOnCheckIn();

        softAssert.assertTrue(RestaurantCheckIn.isCheckInOrderIdDisplayed(orderId), "The user is unable to land on the Check In page.");
        RestaurantCheckIn.clickOnCheckInAll();
        softAssert.assertTrue(RestaurantCheckIn.isCheckInAllPopupDisplayed(), "The Check In all confirmation popup message is not displayed as expected.");
        RestaurantCheckIn.clickConfirm();

        softAssert.assertTrue(RestaurantOrderDetails.isOrderIdDisplayed(orderId), "The user is unable to navigate back to the Order Details page.");
        softAssert.assertTrue(RestaurantOrderDetails.isOrderStatusDisplayed(DistributorOrderData.CHECKIN_STATUS), "The order status was not updated to checked in.");

        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(0));
        
        Dashboard.navigateToPay();
        softAssert.assertTrue(Pay.isUserNavigatedToPay(), "The user is unable to navigate to Pay section.");
        
        Pay.clickOnInvoices();
        softAssert.assertTrue(Pay.isInvoicesBtnSelected(), "The user is unable to navigate to Invoices tab.");
        
        Pay.selectInvoiceCustomerViaFilter(customerId);
        Thread.sleep(3000);
        
        Pay.ensureOrderDateSortedDescending();
        invoiceId = Pay.getInvoiceRecordID(1);
        softAssert.assertNotNull(invoiceId, "Invoice was not generated for the checked-in order.");
        
        Pay.clickOnInvoiceFirstRecord();
        softAssert.assertTrue(Pay.navigateInvoice(invoiceId), "Unable to navigate to the invoice details.");
        
        Pay.clickOnInvoiceRecord(1);
        
        Pay.clickOnInvoiceBulkActionButton();
        Pay.selectTheBulkInvoiceOption("Capture Funds");
        
        if (Pay.isInvoiceCaptureFundPopupDisplayed()) {
            Pay.clickOnInvoiceCaptureFundPay();
            softAssert.assertTrue(Pay.isSuccessPopUpDisplayed(), "Payment processing was not successful.");
            Pay.clickOkPopUp();
        }
        
        Pay.selectInvoiceStatusViaFilter("Paid");
        Thread.sleep(2000);
        
        String paidInvoiceStatus = Pay.getInvoiceRecordStatus(1);
        softAssert.assertTrue(paidInvoiceStatus.toLowerCase().contains("paid"), "Invoice status was not updated to paid after payment processing.");

        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsersAtOnce();
    }
}
