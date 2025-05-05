package com.cutanddry.qa.tests.multi_uom;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.CatalogData;
import com.cutanddry.qa.functions.Catalog;
import com.cutanddry.qa.functions.Customer;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyMultiCartItemsSelectMultipleUOMItemFromPDPTest extends TestBase {
    SoftAssert softAssert;
    static User user;
    static String customerId = CatalogData.CUSTOMER_ID_2;
    String itemCode1 = CatalogData.ITEM_CODE_3;
    static double itemPriceUOM1 ,itemPriceUOM2,totalPDPItemPrice1,totalPDPItemPrice2 ;
    String uom1 = CatalogData.MULTI_UOM_1;
    String uom2 = CatalogData.MULTI_UOM_2;
    static String orderId1,totalItemQuantityReviewOrder,orderId2;
    static String DP = CatalogData.DP_BUTTERFIELD;
    String itemName1 = CatalogData.ITEM_NAME_RETAIL;
    String itemName2 = CatalogData.ITEM_NAME_FOOD_;
    String searchItemCode = CatalogData.ITEM_CODE_14;
    static int orderCount = 2;

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }


    @Test(groups = "DOT-TC-761")
    public void VerifyMultiCartItemsSelectMultipleUOMItemFromPDP() throws InterruptedException {

        softAssert = new SoftAssert();

        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        Assert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");
        Login.navigateToDistributorPortal(DP);
        Assert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"navigation error");
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        Assert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId),"search error");
        Customer.clickOnOrderGuide(customerId);

        Customer.goToCatalog();
        Customer.searchItemOnCatalog(itemCode1);
        softAssert.assertTrue(Customer.getFirstElementFrmSearchResults(itemName1).contains(itemName1.toLowerCase()), "item not found");
        Customer.clickOnProduct(itemName1);
        softAssert.assertTrue(Customer.isProductDetailsDisplayed(),"The user is unable to land on the Product Details page.");
        itemPriceUOM1 = Catalog.getPDPPriceUOM(uom1);
        itemPriceUOM2 = Catalog.getPDPPriceUOM(uom2);
        Catalog.clickAddToCartPlusIcon(1, uom1);
        Catalog.clickAddToCartPlusIcon(1, uom2);
        totalPDPItemPrice1 = Customer.getItemPriceOnCheckoutButtonViaPDP();
        softAssert.assertEquals(Math.round(totalPDPItemPrice1 * 100.0) / 100.0,
                ((Math.round(itemPriceUOM1 * 100.0) / 100.0)+(Math.round(itemPriceUOM2 * 100.0) / 100.0)), "The item has not been selected.");

        Catalog.clickBack();

        Customer.searchItemOnCatalog(searchItemCode);
        softAssert.assertTrue(Customer.getFirstElementFrmSearchResults(itemName2).contains(itemName2.toLowerCase()), "item not found");
        Customer.clickOnProduct(itemName2);
        softAssert.assertTrue(Customer.isProductDetailsDisplayed(),"The user is unable to land on the Product Details page.");
        itemPriceUOM1 = Catalog.getPDPPriceUOM(uom1);
        itemPriceUOM2 = Catalog.getPDPPriceUOM(uom2);
        Catalog.clickAddToCartPlusIcon(1, uom1);
        Catalog.clickAddToCartPlusIcon(1, uom2);
        totalPDPItemPrice2 = Customer.getItemPriceOnCheckoutButtonViaPDP();
        softAssert.assertEquals(Math.round(totalPDPItemPrice2 * 100.0) / 100.0,
                ((Math.round(itemPriceUOM1 * 100.0) / 100.0)+(Math.round(itemPriceUOM2 * 100.0) / 100.0)+(Math.round(totalPDPItemPrice1 * 100.0) / 100.0)), "The item has not been selected.");


        Customer.clickCheckOutPDP();
        softAssert.assertTrue(Customer.isReviewOrderTextDisplayed(), "The user is unable to land on the Review Order page.");
        softAssert.assertTrue(Customer.isMultiDistCentersDisplayed(),"multi cart error");
        Customer.submitOrder();
        softAssert.assertTrue(Customer.isThankingForOrderPopupDisplayed(), "The order was not completed successfully.");
        softAssert.assertEquals(Customer.getOrderCount(orderCount), orderCount, "multi order submit error error");
        orderId1 = Customer.getMultiOrderedId(uom1);
        orderId2 = Customer.getMultiOrderedId(uom2);
        Customer.clickClose();

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        Assert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Unable to find the customer Id");
        Customer.SelectCustomer(customerId);
        Customer.clickOnOrdersTab();
        Catalog.clickSubmittedOrder(orderId1);
        softAssert.assertEquals(Catalog.getTotalPriceInOrder(),totalPDPItemPrice1,"order not successfully submitted");
        softAssert.assertEquals(Catalog.getTotalQuantityInOrder(),"2","order quantity not successfully submitted");

        Catalog.clickBack();

        Catalog.clickSubmittedOrder(orderId2);
        softAssert.assertEquals(Math.round(Catalog.getTotalPriceInOrder() * 100.0) / 100.0,
                ((Math.round(itemPriceUOM1 * 100.0) / 100.0)+(Math.round(itemPriceUOM2 * 100.0) / 100.0)), "The item has not been selected.");
        softAssert.assertEquals(Catalog.getTotalQuantityInOrder(),"2","order quantity not successfully submitted");
        softAssert.assertAll();
    }
    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsersAtOnce();
    }
}
