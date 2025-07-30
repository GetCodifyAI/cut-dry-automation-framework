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

public class VerifyThSelectMultipleUOMAvailableRebateItemAndSubmissionTest extends TestBase {
    SoftAssert softAssert;
    static User user;
    static String customerId = CatalogData.CUSTOMER_ID_4;
    String searchItemCode = CatalogData.ITEM_CODE_4;
    static double itemOGPriceUOM1 ,itemOGPriceUOM2,totalOGItemPrice1;
    String uom1 = CatalogData.MULTI_UOM_1;
    String uom2 = CatalogData.MULTI_UOM_2;
    static String orderId;
    static String DP = CatalogData.DP_BIRITE;

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }


    @Test(groups = "DOT-TC-1028")
    public void VerifyThSelectMultipleUOMAvailableRebateItemAndSubmission() throws InterruptedException {

        softAssert = new SoftAssert();

        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        Assert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");
        Login.navigateToDistributorPortal(DP);
        Assert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"navigation error");
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        Assert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId),"search error");
        Customer.clickOnOrderGuideBiRite(customerId);

        Customer.searchItemOnOrderGuide(searchItemCode);
        Catalog.searchOrderGuide(searchItemCode);
        Catalog.ClickOnMultiUomDropDownOG(searchItemCode);
        Catalog.clickOGAddToCartPlusIcon(1,searchItemCode, uom1);
        Catalog.clickOGAddToCartPlusIcon(1,searchItemCode, uom2);
        softAssert.assertEquals(Catalog.getItemUOMQuantity(searchItemCode, uom1),"1", "item count error");
        softAssert.assertEquals(Catalog.getItemUOMQuantity(searchItemCode, uom2),"1", "item count error");
        itemOGPriceUOM1 = Catalog.getUOMOGPrice(searchItemCode,uom1);
        itemOGPriceUOM2 = Catalog.getUOMOGPrice(searchItemCode,uom2);
        totalOGItemPrice1 = Catalog.getItemPriceOnCheckoutButtonOG();
        softAssert.assertEquals(Math.round(totalOGItemPrice1 * 100.0) / 100.0,
                (Math.round((itemOGPriceUOM1 + itemOGPriceUOM2) * 100.0) / 100.0), "The item has not been selected.");

        Customer.checkoutItemsMultiOUM();
        softAssert.assertTrue(Customer.isReviewOrderTextDisplayed(), "The user is unable to land on the Review Order page.");
        Customer.submitOrderRebate();
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
