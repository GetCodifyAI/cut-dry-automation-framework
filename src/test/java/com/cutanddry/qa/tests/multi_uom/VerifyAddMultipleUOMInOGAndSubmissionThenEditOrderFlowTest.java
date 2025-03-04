package com.cutanddry.qa.tests.multi_uom;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.CatalogData;
import com.cutanddry.qa.functions.*;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyAddMultipleUOMInOGAndSubmissionThenEditOrderFlowTest extends TestBase {
    SoftAssert softAssert;
    static User user;
    static String customerId = CatalogData.CUSTOMER_ID;
    String searchItemCode = CatalogData.ITEM_CODE;
    static double itemOGPriceUOM1 ,itemOGPriceUOM2,totalOGItemPrice1,totalItemPriceReviewOrder;
    String uom1 = CatalogData.MULTI_UOM_1;
    String uom2 = CatalogData.MULTI_UOM_2;
    static String orderId,totalItemQuantityReviewOrder;

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }


    @Test(groups = "DOT-TC-773")
    public void VerifyAddMultipleUOMInOGAndSubmissionThenEditOrderFlow() throws InterruptedException {

        softAssert = new SoftAssert();

        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "The user is unable to land on the Dashboard page.");
        Customer.ensureCarouselDisplayStatus(false);

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Unable to find the customer Id");
        Customer.clickOnOrderGuide(customerId);
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
        softAssert.assertTrue(Customer.isReviewOrderTextDisplayed(), "The user is unable to land on the Review Order page.");
        Customer.submitOrder();
        softAssert.assertTrue(Customer.isThankingForOrderPopupDisplayed(), "The order was not completed successfully.");
        orderId = Customer.getSuccessOrderId();
        Customer.clickClose();

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Unable to find the customer Id");
        Customer.SelectCustomer(customerId);
        Customer.clickOnOrdersTab();
        Catalog.clickSubmittedOrder(orderId);
        softAssert.assertEquals(Catalog.getTotalPriceInOrder(),totalOGItemPrice1,"order not successfully submitted");
        softAssert.assertEquals(Catalog.getTotalQuantityInOrder(),"2","order quantity not successfully submitted");

        Orders.clickOnEditOrder();
        softAssert.assertTrue(Orders.isEditOrderPopupDisplayed(),"edit popup error");
        Orders.clickOnConfirm();

        softAssert.assertTrue(Customer.isReviewOrderTextDisplayed(), "The user is unable to land on the Review Order page.");
        totalItemPriceReviewOrder = Catalog.getTotalPriceInReviewOrder();
        totalItemQuantityReviewOrder = Catalog.getTotalQuantityInReviewOrder();
        Catalog.clickOGAddToCartPlusIcon(2,searchItemCode, uom1);
        Catalog.clickOGAddToCartPlusIcon(2,searchItemCode, uom2);
        softAssert.assertEquals(Catalog.getItemUOMQuantity(searchItemCode, uom1),"3", "item count error");
        softAssert.assertEquals(Catalog.getItemUOMQuantity(searchItemCode, uom2),"3", "item count error");
        softAssert.assertEquals(Math.round(Catalog.getTotalPriceInReviewOrder() * 100.0) / 100.0,
                ((Math.round(totalItemPriceReviewOrder *3* 100.0) / 100.0)), "The item has not been selected.");
        softAssert.assertEquals(Catalog.getTotalQuantityInReviewOrder(),"6", "item count error");

        Catalog.clickOGAddToCartMinusIcon(1,searchItemCode, uom1);
        Catalog.clickOGAddToCartMinusIcon(1,searchItemCode, uom2);
        softAssert.assertEquals(Math.round(Catalog.getTotalPriceInReviewOrder() * 100.0) / 100.0,
                ((Math.round(totalItemPriceReviewOrder *2* 100.0) / 100.0)), "The item has not been selected.");
        softAssert.assertEquals(Catalog.getTotalQuantityInReviewOrder(),"4", "item count error");
        softAssert.assertAll();

        Customer.submitOrder();
        softAssert.assertTrue(Orders.isOrderUpdatedOverlayDisplayed(),"update popup error");
        Orders.clickOnClose();

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Unable to find the customer Id");
        Customer.SelectCustomer(customerId);
        Customer.clickOnOrdersTab();
        Catalog.clickSubmittedOrder(orderId);
        softAssert.assertEquals(Math.round(Catalog.getTotalPriceInOrder() * 100.0) / 100.0,
                ((Math.round(totalItemPriceReviewOrder *2* 100.0) / 100.0)), "The item has not been selected.");
        softAssert.assertEquals(Catalog.getTotalQuantityInOrder(),"4","order quantity not successfully submitted");





    }
    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsersAtOnce();
    }
}
