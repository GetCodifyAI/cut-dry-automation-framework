package com.cutanddry.qa.tests.settings;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.DistributorOrderData;
import com.cutanddry.qa.data.testdata.SettingData;
import com.cutanddry.qa.functions.Customer;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.functions.Settings;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class VerifyTheGlobalDeliveryDatesOverrideFeatureTest extends TestBase {
    static User user;
    static String customerId = "16579";
    static String itemName, orderId, searchItemCode;
    static double itemPrice;


    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-1565")
    public void VerifyTheGlobalDeliveryDatesOverrideFeature() throws InterruptedException, URISyntaxException {
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");
        Dashboard.navigateToOrderSettings();
        softAssert.assertTrue(Settings.isOrderSettingsTextDisplayed(),"navigation to order settings error");
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
        Customer.checkoutItems();

        softAssert.assertTrue(Customer.isReviewOrderTextDisplayed(), "The user is unable to land on the Review Order page.");
        softAssert.assertTrue(Customer.isDeliveryOptionSelected(), "The expected fulfillment type is not selected.");

        // User Delivery Date
        LocalDate today = LocalDate.now();
        DateTimeFormatter fullFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("d");

        for (int i = 2; i <= 8; i++) {
            LocalDate deliveryDate = today.plusDays(i);
            String deliveryDay = dayFormatter.format(deliveryDate);

            int todayMonth = today.getMonthValue();
            int deliveryMonth = deliveryDate.getMonthValue();
            boolean isNextMonth = deliveryMonth != todayMonth;

            softAssert.assertTrue(Customer.isDeliveryDatesDisplay(deliveryDay, isNextMonth),"Display delivery dates error for day: " + deliveryDay + " (isNextMonth=" + isNextMonth + ")");
        }
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
