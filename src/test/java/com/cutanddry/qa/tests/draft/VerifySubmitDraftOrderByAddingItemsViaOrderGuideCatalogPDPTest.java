package com.cutanddry.qa.tests.draft;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.Customer;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Draft;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifySubmitDraftOrderByAddingItemsViaOrderGuideCatalogPDPTest extends TestBase {
    static User user;
    static String customerId = "21259";
    static String itemName,orderId, searchItemCode;
    static double itemPrice;

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-778")
    public void VerifySubmitDraftOrderByAddingItemsViaOrderGuideCatalogPDP() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        Dashboard.isUserNavigatedToDashboard();
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId),"search error");
        Customer.clickOnOrderGuide(customerId);

        itemName = Customer.getItemNameFirstRow();
        searchItemCode = Customer.getItemCodeFirstRow();
        itemPrice = Customer.getActiveItemPriceFirstRow();
        Customer.searchItemOnOrderGuide(searchItemCode);
        Customer.increaseFirstRowQtyCustom(1);
        System.out.println(Customer.getItemPriceOnCheckoutButton());
        System.out.println(itemPrice);
        softAssert.assertEquals(Customer.getItemPriceOnCheckoutButton(),itemPrice,"The item has not been selected.");
        Customer.checkoutItems();

        softAssert.assertTrue(Customer.isReviewOrderTextDisplayed(), "The user is unable to land on the Review Order page.");
        softAssert.assertEquals(Customer.getItemNameFirstRow(), itemName, "The item selected by the user is different from what is shown on the order review page.");
        Dashboard.navigateToDrafts();
        softAssert.assertTrue(Draft.isUserNavigatedToDrafts(),"navigation error");
        softAssert.assertTrue(Draft.isLastDraftDisplayed(String.valueOf(itemPrice)),"draft creating error");
        Draft.clickDraft(String.valueOf(itemPrice));
        softAssert.assertTrue(Customer.isReviewOrderTextDisplayed(), "The user is unable to land on the Review Order page.");
        Draft.clickEditOrder();
        Customer.increaseFirstRowQtyCustom(1);
        System.out.println(Customer.getItemPriceOnCheckoutButton());
        System.out.println(itemPrice);
        softAssert.assertEquals(Customer.getItemPriceOnCheckoutButton(),itemPrice*2,"The item has not been selected.");

        // Add the product via Catalog
        Customer.goToCatalog();
        Customer.searchItemOnCatalog(searchItemCode);
        softAssert.assertTrue(Customer.getFirstElementFrmSearchResults(itemName).contains(itemName.toLowerCase()), "item not found");
        Customer.clickPlusSearchedSingleItem(1);
        softAssert.assertEquals(Customer.getItemPriceOnCheckoutButton(),itemPrice*3,"The item has not been selected.");

        // Add the product via PDP
        Customer.clickOnProduct(itemName);
        softAssert.assertTrue(Customer.isProductDetailsDisplayed(),"The user is unable to land on the Product Details page.");
        Customer.clickPlusSearchedSingleItem(1);
        softAssert.assertEquals(Customer.getItemPriceOnCheckoutButtonViaPDP(),itemPrice*4,"The item has not been selected.");
        Customer.clickCheckOutPDP();

        softAssert.assertTrue(Customer.isReviewOrderTextDisplayed(), "The user is unable to land on the Review Order page.");
        softAssert.assertEquals(Customer.getItemNameFirstRow(), itemName, "The item selected by the user is different from what is shown on the order review page.");
        softAssert.assertTrue(Customer.isDeliveryOptionSelected(), "The expected fulfillment type is not selected.");
        Customer.submitOrder();
        softAssert.assertTrue(Customer.isThankingForOrderPopupDisplayed(), "The order was not completed successfully.");
        orderId = Customer.getSuccessOrderId();
        Customer.clickClose();
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
