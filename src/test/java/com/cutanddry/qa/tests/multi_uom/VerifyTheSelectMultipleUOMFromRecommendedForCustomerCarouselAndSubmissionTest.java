package com.cutanddry.qa.tests.multi_uom;

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

public class VerifyTheSelectMultipleUOMFromRecommendedForCustomerCarouselAndSubmissionTest extends TestBase {
    SoftAssert softAssert;
    static User user;
    static String customerId = CatalogData.CUSTOMER_ID;
    String searchItemCode1 = CatalogData.ITEM_CODE;
    String itemName1 = CatalogData.ITEM_NAME;
    String itemName2 = CatalogData.
            ITEM_NAME_SIMILAR_ITEM_2;
    String uomDropDownOption = CatalogData.UOM_DROPDOWN_OPTION;
    static double itemPriceUOM1 ,itemPriceUOM2,totalPDPItemPrice ,totalItemPriceReviewOrder;
    String uom1 = CatalogData.MULTI_UOM_1;
    String uom2 = CatalogData.MULTI_UOM_2;
    static String orderId,totalItemQuantityReviewOrder,itemName,searchItemCode;
    boolean inactiveState;
    static double itemPrice;

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }


    @Test(groups = "DOT-TC-999")
    public void VerifyTheSelectMultipleUOMFromRecommendedForCustomerCarouselAndSubmission() throws InterruptedException {

        softAssert = new SoftAssert();

        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        Assert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "The user is unable to land on the Dashboard page.");

        Dashboard.navigateToBoost();
        Assert.assertTrue(Boost.isUserNavigatedToBoost(),"navigate to boost error");
        Boost.clickSuggestiveSales();
        softAssert.assertTrue(Boost.isSuggestiveTabDisplayed(),"navigate to suggestive sales error");
        inactiveState = Boost.checkInactiveState("Recommended for Customer");
        Boost.clickRecommendForCustomerConfig();
        softAssert.assertTrue(Boost.isRecommendForCustomerPopupDisplayed(),"recommend for customer popup error");
        Boost.toggleOnCarouselDisplayStatus(inactiveState);
        Boost.clickClose();

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        Assert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Unable to find the customer Id");
        Customer.clickOnOrderGuide(customerId);
        itemName = Customer.getItemNameFirstRow();
        searchItemCode = Customer.getItemCodeFirstRow();
        itemPrice = Customer.getActiveItemPriceFirstRow();
        Customer.increaseFirstRowQtyCustom(1);
        softAssert.assertEquals(Customer.getItemPriceOnCheckoutButton(),itemPrice,"The item has not been selected.");
        Customer.checkoutItems();
        softAssert.assertTrue(Customer.isReviewOrderTextDisplayed(), "The user is unable to land on the Review Order page.");
        softAssert.assertTrue(Customer.isRecommendedForYouItemNameDisplayed(itemName1),"recommended for you item missing error");

        Catalog.ClickOnMultiUomDropDown(itemName1);
        Catalog.ClickOnMultiUomDropDownOption(uomDropDownOption);
        softAssert.assertTrue(Customer.isProductDetailsDisplayed(),"The user is unable to land on the Product Details page.");
        itemPriceUOM1 = Catalog.getPDPPriceUOM(uom1);
        itemPriceUOM2 = Catalog.getPDPPriceUOM(uom2);
        Catalog.clickAddToCartPlusIcon(1, uom1);
        Catalog.clickAddToCartPlusIcon(1, uom2);
        totalPDPItemPrice = Customer.getItemPriceOnCheckoutButtonViaPDP();
        softAssert.assertEquals(Math.round(totalPDPItemPrice * 100.0) / 100.0,
                ((Math.round(itemPriceUOM1 * 100.0) / 100.0)+(Math.round(itemPriceUOM2 * 100.0) / 100.0)+(Math.round(itemPrice * 100.0) / 100.0)), "The item has not been selected.");
        Customer.clickCheckOutPDP();
        softAssert.assertTrue(Customer.isReviewOrderTextDisplayed(), "The user is unable to land on the Review Order page.");
        totalItemPriceReviewOrder = Catalog.getTotalPriceInReviewOrder();
        totalItemQuantityReviewOrder = Catalog.getTotalQuantityInReviewOrder();
        Customer.submitOrder();
        softAssert.assertTrue(Customer.isThankingForOrderPopupDisplayed(), "The order was not completed successfully.");
        orderId = Customer.getSuccessOrderId();
        Customer.clickClose();

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        Assert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Unable to find the customer Id");
        Customer.SelectCustomer(customerId);
        Customer.clickOnOrdersTab();
        Catalog.clickSubmittedOrder(orderId);
        softAssert.assertEquals(Catalog.getTotalPriceInOrder(),totalPDPItemPrice,"order not successfully submitted");
        softAssert.assertEquals(Catalog.getTotalQuantityInOrder(),totalItemQuantityReviewOrder,"order quantity not successfully submitted");

        Dashboard.navigateToBoost();
        Assert.assertTrue(Boost.isUserNavigatedToBoost(),"navigate to boost error");
        Boost.clickSuggestiveSales();
        softAssert.assertTrue(Boost.isSuggestiveTabDisplayed(),"navigate to suggestive sales error");
        Boost.clickRecommendForCustomerConfig();
        softAssert.assertTrue(Boost.isRecommendForCustomerPopupDisplayed(),"recommend for customer popup error");
        Boost.toggleOffCarouselDisplayStatus();
        Boost.clickClose();
        softAssert.assertAll();
    }
    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsersAtOnce();
    }
}
