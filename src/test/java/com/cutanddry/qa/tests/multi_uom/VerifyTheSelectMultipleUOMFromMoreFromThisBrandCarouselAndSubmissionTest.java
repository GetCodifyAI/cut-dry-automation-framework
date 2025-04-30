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

public class VerifyTheSelectMultipleUOMFromMoreFromThisBrandCarouselAndSubmissionTest extends TestBase {
    SoftAssert softAssert;
    static User user;
    static String customerId = CatalogData.CUSTOMER_ID;
    String searchItemCode = CatalogData.ITEM_CODE_10;
    String itemName = CatalogData.ITEM_NAME_MORE_FROM_ITEM;
    String itemName2 = CatalogData.ITEM_NAME_MORE_FROM_ITEM_2;
    String uomDropDownOption = CatalogData.UOM_DROPDOWN_OPTION;
    static double itemPriceUOM1 ,itemPriceUOM2,totalPDPItemPrice ,totalItemPriceReviewOrder;
    String uom1 = CatalogData.MULTI_UOM_1;
    String uom2 = CatalogData.MULTI_UOM_2;
    static String orderId,totalItemQuantityReviewOrder;
    boolean inactiveState;

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }


    @Test(groups = "DOT-TC-1002")
    public void VerifyTheSelectMultipleUOMFromMoreFromThisBrandCarouselAndSubmission() throws InterruptedException {

        softAssert = new SoftAssert();

        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        Assert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "The user is unable to land on the Dashboard page.");

        Dashboard.navigateToBoost();
        Assert.assertTrue(Boost.isUserNavigatedToBoost(),"navigate to boost error");
        Boost.clickSuggestiveSales();
        softAssert.assertTrue(Boost.isSuggestiveTabDisplayed(),"navigate to suggestive sales error");
        inactiveState = Boost.checkInactiveState("More from this Brand");
        Boost.clickMoreFromThisConfig();
        softAssert.assertTrue(Boost.isMoreFromThisPopupDisplayed(),"more from this brand popup error");
        Boost.toggleOnCarouselDisplayStatus(inactiveState);
        Boost.clickClose();

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        Assert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Unable to find the customer Id");
        Customer.clickOnOrderGuide(customerId);
        Customer.goToCatalog();

        Customer.searchItemOnCatalog(searchItemCode);
        softAssert.assertTrue(Customer.getFirstElementFrmSearchResults(itemName).contains(itemName.toLowerCase()), "item not found");
        Customer.clickOnProduct(itemName);
        softAssert.assertTrue(Customer.isProductDetailsDisplayed(),"The user is unable to land on the Product Details page.");
        softAssert.assertTrue(Customer.isMoreFromThisBrandDisplayed(), "more from this brand missing error");

        Catalog.ClickOnMultiUomDropDown(itemName2);
        Catalog.ClickOnMultiUomDropDownOption(uomDropDownOption);
        softAssert.assertTrue(Customer.isProductDetailsDisplayed(),"The user is unable to land on the Product Details page.");
        itemPriceUOM1 = Catalog.getPDPPriceUOM(uom1);
        itemPriceUOM2 = Catalog.getPDPPriceUOM(uom2);
        Catalog.clickAddToCartPlusIcon(1, uom1);
        Catalog.clickAddToCartPlusIcon(1, uom2);
        totalPDPItemPrice = Customer.getItemPriceOnCheckoutButtonViaPDP();
        /*softAssert.assertEquals(Math.round(totalPDPItemPrice * 100.0) / 100.0,
                ((Math.round(itemPriceUOM1 * 100.0) / 100.0)+(Math.round(itemPriceUOM2 * 100.0) / 100.0)), "The item has not been selected.");*/
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
        Boost.clickMoreFromThisConfig();
        softAssert.assertTrue(Boost.isMoreFromThisPopupDisplayed(),"more from this brand popup error");
        Boost.toggleOffCarouselDisplayStatus();
        softAssert.assertAll();
    }
    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsersAtOnce();
    }
}
