package com.cutanddry.qa.tests.multi_uom;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.CatalogData;
import com.cutanddry.qa.functions.Catalog;
import com.cutanddry.qa.functions.Customer;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyTheEditStandingOrderWithMultipleUOMTest extends TestBase {
    SoftAssert softAssert;
    static User user;
    static String customerId = CatalogData.CUSTOMER_ID;
    String searchItemCode = CatalogData.ITEM_CODE;
    static double itemOGPriceUOM1 ,itemOGPriceUOM2,totalOGItemPrice1,totalItemPriceReviewOrder,totalFinalItemPriceReviewOrder;
    String uom1 = CatalogData.MULTI_UOM_1;
    String uom2 = CatalogData.MULTI_UOM_2;
    static String deliveryDay = CatalogData.DELIVERY_DAY;
    String itemName = CatalogData.ITEM_NAME;
    static String StandingPrice ,totalItemQuantityReviewOrder,EditStandingPrice,totalQuantity ,totalQuantity2;

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }


    @Test(groups = "DOT-TC-783")
    public void VerifyTheEditStandingOrderWithMultipleUOM() throws InterruptedException {

        softAssert = new SoftAssert();

        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "The user is unable to land on the Dashboard page.");
        Customer.ensureCarouselDisplayStatus(false);

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Unable to find the customer Id");
        Customer.SelectCustomer(customerId);
        Customer.clickOnOrdersTab();

        softAssert.assertTrue(Customer.isStandingOrdersDisplayed(),"navigation error");
        Customer.removeStandingOrdersIfAvailable();
        Customer.clickOnCreateStandingOrder();
        Customer.selectDeliveryDate(deliveryDay);

        Catalog.ClickOnMultiUomDropDownOG(searchItemCode);
        Catalog.clickOGAddToCartPlusIcon(1,searchItemCode, uom1);
        Catalog.clickOGAddToCartPlusIcon(1,searchItemCode, uom2);
        softAssert.assertEquals(Catalog.getItemUOMQuantity(searchItemCode, uom1),"1", "item count error");
        softAssert.assertEquals(Catalog.getItemUOMQuantity(searchItemCode, uom2),"1", "item count error");
        itemOGPriceUOM1 = Catalog.getOGPriceUOM(searchItemCode,uom1);
        itemOGPriceUOM2 = Catalog.getOGPriceUOM(searchItemCode,uom2);
        totalOGItemPrice1 = Customer.getItemPriceOnCheckoutButton();
        softAssert.assertEquals(Math.round(totalOGItemPrice1 * 100.0) / 100.0,
                ((Math.round(itemOGPriceUOM1 * 100.0) / 100.0)+(Math.round(itemOGPriceUOM2 * 100.0) / 100.0)), "The item has not been selected.");


        Customer.checkoutItems();
        softAssert.assertEquals(Customer.getItemNameFirstRow(),itemName,"item mismatch");
        totalQuantity = Catalog.getTotalQuantityInReviewOrder();

        Customer.setStandingOrder();
        softAssert.assertTrue(Customer.isStandingOrderEmailPopupDisplayed(),"pop up display error");
        Customer.selectEmail();
        Customer.scheduleStandingOrder();
        softAssert.assertTrue(Customer.isStandingOrderSuccessPopupDisplayed(),"order creating error");
        Customer.clickOK();

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        Customer.SelectCustomer(customerId);
        Customer.clickOnOrdersTab();
        StandingPrice = String.valueOf((int) totalOGItemPrice1);
        softAssert.assertTrue(Catalog.isSubmittedStandingOrderDisplayed(totalQuantity,StandingPrice),"standing order submit error");

        // test

        Customer.clickOnEditStandingOrder();
        softAssert.assertTrue(Customer.isReviewStandingOrdersDisplayed(),"navigate error review standing order");
        totalItemPriceReviewOrder = Catalog.getTotalPriceInReviewOrder();
        totalItemQuantityReviewOrder = Catalog.getTotalQuantityInReviewOrder();
        Catalog.clickOGAddToCartPlusIcon(1,searchItemCode, uom1);
        Catalog.clickOGAddToCartPlusIcon(1,searchItemCode, uom2);
        softAssert.assertEquals(Catalog.getItemUOMQuantity(searchItemCode, uom1),"2", "item count error");
        softAssert.assertEquals(Catalog.getItemUOMQuantity(searchItemCode, uom2),"2", "item count error");
        totalFinalItemPriceReviewOrder = Catalog.getTotalPriceInReviewOrder();
        softAssert.assertEquals(Math.round(Catalog.getTotalPriceInReviewOrder() * 100.0) / 100.0,
                ((Math.round(totalItemPriceReviewOrder *2* 100.0) / 100.0)), "The item has not been selected.");
        softAssert.assertEquals(Catalog.getTotalQuantityInReviewOrder(),"4", "item count error");
        totalQuantity2 = Catalog.getTotalQuantityInReviewOrder();

        Customer.setStandingOrder();
        softAssert.assertTrue(Customer.isStandingOrderEmailPopupDisplayed(),"pop up display error");
        Customer.scheduleStandingOrder();
        softAssert.assertTrue(Customer.isStandingOrderSuccessPopupDisplayed(),"order creating error");
        Customer.clickOK();

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        Customer.SelectCustomer(customerId);
        Customer.clickOnOrdersTab();
        EditStandingPrice = String.valueOf((int) totalFinalItemPriceReviewOrder);
        softAssert.assertTrue(Catalog.isSubmittedStandingOrderDisplayed(totalQuantity2,EditStandingPrice)," edit standing order submit error");
        softAssert.assertAll();
    }
    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsersAtOnce();
    }
}
