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

public class VerifyTheDistributorCanHavePickupWillCallAsTheFulfillmentTypeDraftOrderSubmissionTest extends TestBase {
    SoftAssert softAssert;
    static User user;
    static String customerId = DistributorOrderData.RESTAURANT_TEST_HAYES_ID;
    static String itemName, orderId, searchItemCode;
    static String searchItemCode2 = "02095";
    static String itemName2 = "Organic Bananas - 20 LB";
    static double itemPrice,totalItemPriceReviewOrder;
    static String pickUp = "Pick Up";

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-1227")
    public void VerifyTheDistributorCanHavePickupWillCallAsTheFulfillmentTypeDraftOrderSubmission() throws InterruptedException {

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
        Customer.selectPickUpWillCall();
        softAssert.assertTrue(Customer.isPickUpOptionSelected(), "The expected fulfillment type is not selected.");
        // User Delivery Date
        LocalDate today = LocalDate.now();
        LocalDate deliveryDate = today.plusDays(2);
        DateTimeFormatter fullFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String formattedDeliveryDate = fullFormatter.format(deliveryDate);
        DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("d");
        String deliveryDay = dayFormatter.format(deliveryDate);
        int todayMonth = today.getMonthValue();
        int deliveryMonth = deliveryDate.getMonthValue();
        boolean isNextMonth = deliveryMonth != todayMonth;
        Customer.selectPickUpDateLineStable(deliveryDay, isNextMonth);
        totalItemPriceReviewOrder = Catalog.getTotalPriceInReviewOrder();

        Dashboard.navigateToDrafts();
        softAssert.assertTrue(Draft.isUserNavigatedToDrafts(),"navigation error");
        softAssert.assertTrue(Draft.isLastDraftStatusDisplayed(String.valueOf(totalItemPriceReviewOrder),formattedDeliveryDate),"draft creating error");
        softAssert.assertTrue(Draft.isLastDraftStatusDisplayed(String.valueOf(totalItemPriceReviewOrder),pickUp),"fulfilment tag should be not displayed as delivery");
        Draft.clickDraft(String.valueOf(totalItemPriceReviewOrder));
        softAssert.assertTrue(Customer.isReviewOrderTextDisplayed(), "The user is unable to land on the Review Order page.");

        Customer.submitOrder();
        softAssert.assertTrue(Customer.isThankingForOrderPopupDisplayed(), "The order was not completed successfully.");
        orderId = Customer.getSuccessOrderId();
        Customer.clickClose();

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        Assert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Unable to find the customer Id");
        Customer.SelectCustomer(customerId);
        Customer.clickOnOrdersTab();
        softAssert.assertTrue(Customer.isDeliveryDateCustomerOrderDisplayed(orderId,formattedDeliveryDate));
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsersAtOnce();
    }
}
