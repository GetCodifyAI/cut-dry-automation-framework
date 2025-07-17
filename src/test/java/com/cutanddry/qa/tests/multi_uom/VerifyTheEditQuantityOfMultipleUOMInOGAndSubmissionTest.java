package com.cutanddry.qa.tests.multi_uom;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.CatalogData;
import com.cutanddry.qa.data.testdata.DistributorOrderData;
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

public class VerifyTheEditQuantityOfMultipleUOMInOGAndSubmissionTest extends TestBase {
    SoftAssert softAssert;
    static User user;
    static String customerId = CatalogData.CUSTOMER_ID;
    String searchItemCode = CatalogData.ITEM_CODE_2;
    static double itemOGPriceUOM1 ,itemOGPriceUOM2,totalOGItemPrice1;
    String uom1 = CatalogData.MULTI_UOM_1;
    String uom2 = CatalogData.MULTI_UOM_2;
    static String orderId;

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }


    @Test(groups = "DOT-TC-745")
    public void VerifyTheEditQuantityOfMultipleUOMInOGAndSubmission() throws InterruptedException {

        softAssert = new SoftAssert();

        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        Assert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "The user is unable to land on the Dashboard page.");
//        Customer.ensureCarouselDisplayStatus(false);

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        Assert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Unable to find the customer Id");
        Customer.clickOnOrderGuide(customerId);
        Customer.clickOnItemOrderGuideDropDown(DistributorOrderData.DEFAULT_ORDER_GUIDE_TYPE);
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
        softAssert.assertEquals(Catalog.getTotalPriceInOrder(),totalOGItemPrice1,"order not successfully submitted");
        softAssert.assertEquals(Catalog.getTotalQuantityInOrder(),"2","order quantity not successfully submitted");
        softAssert.assertAll();
    }
    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsersAtOnce();
    }
}
