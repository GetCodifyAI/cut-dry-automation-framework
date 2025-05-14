package com.cutanddry.qa.tests.fulfillment_type;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.DistributorOrderData;
import com.cutanddry.qa.functions.*;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class VerifyTheDistributorCanHaveMailDeliveryAsTheFulfillmentTypeEditOrderSubmissionTest extends TestBase {
    SoftAssert softAssert;
    static User user;
    static String customerId = DistributorOrderData.RESTAURANT_TEST_HAYES_ID;
    static String itemName, orderId, searchItemCode;
    static String searchItemCode2 = "02095";
    static String itemName2 = "Organic Bananas - 20 LB";
    static double itemPrice;
    static String mailDelivery = "Mail Delivery";

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-1231")
    public void VerifyTheDistributorCanHaveMailDeliveryAsTheFulfillmentTypeEditOrderSubmission() throws InterruptedException {

        softAssert = new SoftAssert();

        // Distributor Flows
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        Assert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "The user is unable to land on the Dashboard page.");

        Dashboard.navigateToOrderSettings();
        softAssert.assertTrue(Settings.isOrderSettingsTextDisplayed(),"navigation error");
        Settings.deliveryDateCheckBox(true);

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        Assert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Unable to find the customer Id");
        Customer.clickOnOrderGuide(customerId);

        // Add the product via Order Guide
        itemName = Customer.getItemNameFirstRow();
        searchItemCode = Customer.getItemCodeFirstRow();
        itemPrice = Customer.getActiveItemPriceFirstRow();
        Customer.clickOnPlusIconInCatalogPDP(1, itemName);
        softAssert.assertEquals(Customer.getItemPriceOnCheckoutButton(),itemPrice,"The item has not been selected.");

        // Add the product via Catalog
        Customer.goToCatalog();
        Customer.searchItemOnCatalog(searchItemCode2);
        softAssert.assertTrue(Customer.getFirstElementFrmSearchResults(itemName2).contains(itemName2.toLowerCase()), "item not found");
        Customer.clickOnPlusIconInCatalogDP(1, itemName2);
        Customer.checkoutItems();

        softAssert.assertTrue(Customer.isReviewOrderTextDisplayed(), "The user is unable to land on the Review Order page.");
        Customer.selectMailDelivery();
        softAssert.assertTrue(Customer.isMailDeliveryOptionSelected(), "The expected fulfillment type is not selected.");
        //User Delivery Date
        LocalDate today = LocalDate.now();
        LocalDate DeliveryDate = today.plusDays(2);
        DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("d");
        String userDeliveryDate = customFormatter.format(DeliveryDate);
        Customer.selectMailDeliveryDateLine(userDeliveryDate);

        // Format full date as MM/dd/yyyy
        DateTimeFormatter fullFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String formattedDeliveryDate = fullFormatter.format(DeliveryDate);

        Customer.submitOrder();
        softAssert.assertTrue(Customer.isThankingForOrderPopupDisplayed(), "The order was not completed successfully.");
        orderId = Customer.getSuccessOrderId();
        Customer.clickClose();

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        Assert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Unable to find the customer Id");
        Customer.SelectCustomer(customerId);
        Customer.clickOnOrdersTab();
        softAssert.assertTrue(Customer.isDeliveryDateCustomerOrderDisplayed(orderId,formattedDeliveryDate),"The expected fulfillment type is not selected.");
        Catalog.clickSubmittedOrder(orderId);

        Orders.clickOnEditOrder();
        softAssert.assertTrue(Orders.isEditOrderPopupDisplayed(),"edit popup error");
        Orders.clickOnConfirm();

        softAssert.assertTrue(Customer.isReviewOrderTextDisplayed(), "The user is unable to land on the Review Order page.");
        Customer.increaseFirstRowQtyCustom(1);
        Customer.submitOrder();
        softAssert.assertTrue(Orders.isOrderUpdatedOverlayDisplayed(),"update popup error");
        Orders.clickOnClose();

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        Assert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Unable to find the customer Id");
        Customer.SelectCustomer(customerId);
        Customer.clickOnOrdersTab();
        softAssert.assertTrue(Customer.isDeliveryDateCustomerOrderDisplayed(orderId,formattedDeliveryDate),"delivery date not correct");
        softAssert.assertTrue(Customer.isFulfilmentTagDisplayed(orderId,mailDelivery),"The expected fulfillment type is not selected.");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsersAtOnce();
    }
}
