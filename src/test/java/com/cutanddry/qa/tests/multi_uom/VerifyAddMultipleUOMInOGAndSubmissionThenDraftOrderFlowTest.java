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

public class VerifyAddMultipleUOMInOGAndSubmissionThenDraftOrderFlowTest extends TestBase {
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


    @Test(groups = "DOT-TC-775")
    public void VerifyAddMultipleUOMInOGAndSubmissionThenDraftOrderFlow() throws InterruptedException {

        softAssert = new SoftAssert();

        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        Assert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "The user is unable to land on the Dashboard page.");
        Customer.ensureCarouselDisplayStatus(false);

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        Assert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Unable to find the customer Id");
        Customer.clickOnOrderGuide(customerId);
        Customer.searchItemOnOrderGuide(searchItemCode);
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

        Dashboard.navigateToDrafts();
        Assert.assertTrue(Draft.isUserNavigatedToDrafts(),"navigation error");
        softAssert.assertTrue(Draft.isLastDraftDisplayed(String.valueOf(totalOGItemPrice1)),"draft creating error");
        Draft.clickDraft(String.valueOf(totalOGItemPrice1));
        softAssert.assertTrue(Customer.isReviewOrderTextDisplayed(), "The user is unable to land on the Review Order page.");


        totalItemPriceReviewOrder = Catalog.getTotalPriceInReviewOrder();
        totalItemQuantityReviewOrder = Catalog.getTotalQuantityInReviewOrder();
        Catalog.clickOGAddToCartPlusIcon(1,searchItemCode, uom1);
        Catalog.clickOGAddToCartPlusIcon(1,searchItemCode, uom2);
        softAssert.assertEquals(Catalog.getItemUOMQuantity(searchItemCode, uom1),"2", "item count error");
        softAssert.assertEquals(Catalog.getItemUOMQuantity(searchItemCode, uom2),"2", "item count error");
        softAssert.assertEquals(Math.round(Catalog.getTotalPriceInReviewOrder() * 100.0) / 100.0,
                ((Math.round(totalItemPriceReviewOrder *2* 100.0) / 100.0)), "The item has not been selected.");
        softAssert.assertEquals(Catalog.getTotalQuantityInReviewOrder(),"4", "item count error");
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
