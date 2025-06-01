package com.cutanddry.qa.tests.orders;

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

public class VerifyThatDisplayingCustomerIDInHeaderSectionTest extends TestBase {
    SoftAssert softAssert;
    static User user;
    static String customerId = "16672";
    static String itemName, orderId, searchItemCode;

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-1278")
    public void VerifyThatDisplayingCustomerIDInHeaderSection() throws InterruptedException {

        softAssert = new SoftAssert();

        // Distributor Flows
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        Assert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "The user is unable to land on the Dashboard page.");

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        Assert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Unable to find the customer Id");
        String BusinessName = Customer.getBusinessNameFromCustomers(customerId);
        Customer.SelectCustomer(customerId);
        softAssert.assertTrue(Customer.isCustomerProfileDisplayedStable(BusinessName,customerId),"Error in navigation to customer page");

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        Assert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Unable to find the customer Id");
        Customer.clickOnOrderGuide(customerId);
        softAssert.assertTrue(Customer.isCustomerNameDisplayed(BusinessName),"Error in navigation to OG customer name display error");
        softAssert.assertTrue(Customer.isCustomerIDAndLocationDisplayed(BusinessName,customerId),"Error in navigation to OG customer ID and location display error");

        itemName = Customer.getItemNameFirstRow();
        Customer.goToCatalog();
        softAssert.assertTrue(Customer.isCustomerNameDisplayed(BusinessName),"Error in navigation to catalog customer name display error");
        softAssert.assertTrue(Customer.isCustomerIDAndLocationDisplayed(BusinessName,customerId),"Error in navigation to catalog customer ID and location display error");

        Customer.searchItemOnCatalog(searchItemCode);
        Customer.clickOnProduct(itemName);
        softAssert.assertTrue(Customer.isProductDetailsDisplayed(),"The user is unable to land on the Product Details page.");
        softAssert.assertTrue(Customer.isCustomerNameDisplayed(BusinessName),"Error in navigation to PDP customer name display error");
        softAssert.assertTrue(Customer.isCustomerIDAndLocationDisplayed(BusinessName,customerId),"Error in navigation to PDP customer ID and location display error");

        Customer.clickAddToCartPDP();
        Customer.clickCheckOutPDP();

        softAssert.assertTrue(Customer.isReviewOrderTextDisplayed(), "The user is unable to land on the Review Order page.");
        softAssert.assertTrue(Customer.isCustomerNameDisplayed(BusinessName),"Error in navigation to review page customer name display error");
        softAssert.assertTrue(Customer.isCustomerIDAndLocationDisplayed(BusinessName,customerId),"Error in navigation to review page customer ID and location display error");
        Customer.submitOrder();
        softAssert.assertTrue(Customer.isThankingForOrderPopupDisplayed(), "The order was not completed successfully.");
        orderId = Customer.getSuccessOrderId();
        Customer.clickClose();
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsersAtOnce();
    }
}
