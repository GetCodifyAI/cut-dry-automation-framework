package com.cutanddry.qa.tests.catalog_data;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.CatalogData;
import com.cutanddry.qa.functions.*;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyThatTheStandingOrderUponEditingWithInactiveItemsTest extends TestBase {
    SoftAssert softAssert;
    static User user;
    static String customerId = "16579";
    String searchItemCode = "01775";
    static String itemName, orderId;
    static double itemPrice;
    String Active = "Active";
    String InActive = "Inactive";
    String All = "All";
    static String deliveryDay = CatalogData.DELIVERY_DAY;



    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }


    @Test(groups = "DOT-TC-2674")
    public void VerifyThatTheStandingOrderUponEditingWithInactiveItems() throws InterruptedException {

        softAssert = new SoftAssert();

        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        Assert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "The user is unable to land on the Dashboard page.");

        Dashboard.navigateToCatalog();
        softAssert.assertTrue(Catalog.isUserNavigatedToCatalog(),"navigation error");
        Catalog.selectItemStatus(All);
        Catalog.searchItemInCatalog(searchItemCode);
        Catalog.selectItemFromGrid(searchItemCode);
        softAssert.assertEquals(Catalog.getItemcodeInCatalogData(),searchItemCode,"Error in getting Item Code");
        Catalog.selectEditFromProductConfig();
        Catalog.selectProductActiveInactiveStatus(Active);
        Catalog.saveChanges();
        Catalog.clickOnCloseProductConfig();

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        Assert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Unable to find the customer Id");
        Customer.SelectCustomer(customerId);
        Customer.clickOnOrdersTab();

        Assert.assertTrue(Customer.isStandingOrdersDisplayed(),"navigation error");
        Customer.removeStandingOrdersIfAvailable();
        Customer.clickOnCreateStandingOrder();
        Customer.selectDeliveryDate(deliveryDay);

        Customer.searchItemOnOrderGuide(searchItemCode);
        itemName = Customer.getItemNameFirstRow();
        itemPrice = Customer.getActiveItemPriceFirstRow();
        Customer.increaseFirstRowQtyCustom(1);
        softAssert.assertEquals(Customer.getItemPriceOnCheckoutButton(),itemPrice,"The item has not been selected.");
        Customer.checkoutItems();

        Customer.checkoutItems();
        softAssert.assertEquals(Customer.getItemNameFirstRow(),itemName,"item mismatch");

        Customer.setStandingOrder();
        softAssert.assertTrue(Customer.isStandingOrderEmailPopupDisplayed(),"pop up display error");
        Customer.selectEmail();
        Customer.scheduleStandingOrder();
        softAssert.assertTrue(Customer.isStandingOrderSuccessPopupDisplayed(),"order creating error");
        Customer.clickOK();

        Dashboard.navigateToCatalog();
        softAssert.assertTrue(Catalog.isUserNavigatedToCatalog(),"navigation error");
        Catalog.selectItemStatus(All);
        Catalog.searchItemInCatalog(searchItemCode);
        Catalog.selectItemFromGrid(searchItemCode);
        softAssert.assertEquals(Catalog.getItemcodeInCatalogData(),searchItemCode,"Error in getting Item Code");
        Catalog.selectEditFromProductConfig();
        Catalog.selectProductActiveInactiveStatus(InActive);
        Catalog.saveChanges();

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        Assert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Unable to find the customer Id");
        Customer.SelectCustomer(customerId);
        Customer.clickOnOrdersTab();
        Customer.clickOnEditStandingOrder();

        softAssert.assertTrue(Orders.isInactiveItemDetectedPopUpDisplay(),"item not inactive");
        Customer.clickOK();
        softAssert.assertTrue(Customer.isReviewStandingOrdersDisplayed(),"navigate error review standing order");
        softAssert.assertEquals(Math.round(Catalog.getTotalPriceInReviewOrder() * 100.0) / 100.0,
                0.00, "Total price in review order is not zero.");

        Dashboard.navigateToCatalog();
        softAssert.assertTrue(Catalog.isUserNavigatedToCatalog(),"navigation error");
        Catalog.selectItemStatus(All);
        Catalog.searchItemInCatalog(searchItemCode);
        Catalog.selectItemFromGrid(searchItemCode);
        softAssert.assertEquals(Catalog.getItemcodeInCatalogData(),searchItemCode,"Error in getting Item Code");
        Catalog.selectEditFromProductConfig();
        Catalog.selectProductActiveInactiveStatus(Active);
        Catalog.saveChanges();
        softAssert.assertAll();
    }
    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsersAtOnce();
    }
}
