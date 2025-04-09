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

public class VerifyThSelectMultipleUOMSpecialOrderItemAndDraftEditTest extends TestBase {
    SoftAssert softAssert;
    static User user;
    static String customerId = CatalogData.CUSTOMER_ID_3;
    String searchItemCode = CatalogData.ITEM_CODE_7;
    String multiSearchItemCode = CatalogData.ITEM_CODE_12;
    static double itemPriceUOM1 ,itemPriceUOM2,totalPDPItemPrice,itemOGPriceUOM1,itemOGPriceUOM2;
    String uom1 = CatalogData.MULTI_UOM_1;
    String uom2 = CatalogData.MULTI_UOM_2;
    static String orderId;
    static String DP = CatalogData.DP_VICTO;
    String uomDropDownOption = CatalogData.UOM_DROPDOWN_OPTION;
    String itemName = CatalogData.ITEM_NAME_SPECIAL;
    static double totalOGItemPrice;

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }


    @Test(groups = "DOT-TC-1085")
    public void VerifyThSelectMultipleUOMSpecialOrderItemAndDraftEdit() throws InterruptedException {

        softAssert = new SoftAssert();

        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        Assert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");
        Login.navigateToDistributorPortal(DP);
        Assert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"navigation error");
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        Assert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId),"search error");
        Customer.clickOnOrderGuide(customerId);
        Catalog.searchOrderGuide(searchItemCode);

        Customer.goToCatalog();
        Customer.clickItemType();

        Catalog.ClickOnCatalogMultiUomDropDown(itemName);
        Catalog.ClickOnMultiUomDropDownOption(uomDropDownOption);
        softAssert.assertTrue(Customer.isProductDetailsDisplayed(),"The user is unable to land on the Product Details page.");
        itemPriceUOM1 = Catalog.getPDPPriceUOM(uom1);
        itemPriceUOM2 = Catalog.getPDPPriceUOM(uom2);
        Catalog.clickAddToCartPlusIcon(1, uom1);
        Catalog.clickAddToCartPlusIcon(1, uom2);
        totalPDPItemPrice = Customer.getItemPriceOnCheckoutButtonViaPDP();
        softAssert.assertEquals(Math.round(totalPDPItemPrice * 100.0) / 100.0,
                ((Math.round(itemPriceUOM1 * 100.0) / 100.0)+(Math.round(itemPriceUOM2 * 100.0) / 100.0)), "The item has not been selected.");
        Customer.clickCheckOutPDP();

        Dashboard.navigateToDrafts();
        Assert.assertTrue(Draft.isUserNavigatedToDrafts(),"navigation error");

        softAssert.assertTrue(Draft.isLastDraftDisplayedVito(String.valueOf(totalPDPItemPrice)),"draft creating error");
        Draft.clickDraft(String.valueOf(totalPDPItemPrice));
        softAssert.assertTrue(Customer.isReviewOrderTextDisplayed(), "The user is unable to land on the Review Order page.");

        Draft.clickEditOrder();

        // Added Multi OUM Item
        Customer.searchItemOnOrderGuide(multiSearchItemCode);
        Customer.ClickOnMultiUomDropDownOG(multiSearchItemCode);
        Customer.clickOGAddToCartPlusIcon(1, multiSearchItemCode, uom1);
        Customer.clickOGAddToCartPlusIcon(1, multiSearchItemCode, uom2);
        softAssert.assertEquals(Catalog.getItemUOMQuantity(multiSearchItemCode, uom1), "1", "item count error in 1st UOM");
        softAssert.assertEquals(Catalog.getItemUOMQuantity(multiSearchItemCode, uom2), "1", "item count error in 2nd UOM");
        itemOGPriceUOM1 = Customer.getActiveItemPriceMultiOUM(uom1);
        itemOGPriceUOM2 = Customer.getActiveItemPriceMultiOUM(uom2);
        totalOGItemPrice = Customer.getItemPriceOnMultiOUMCheckout(); //Customer.getItemPriceOnCheckoutButton();
        softAssert.assertEquals(totalOGItemPrice, itemOGPriceUOM1 + itemOGPriceUOM2 + totalPDPItemPrice, 0.001, "The item was not selected properly.");
        Customer.checkoutItemsMultiOUM();
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
        softAssert.assertEquals(Catalog.getTotalPriceInOrder(),totalOGItemPrice,"order not successfully submitted");
        softAssert.assertEquals(Catalog.getTotalQuantityInOrder(),"4","order quantity not successfully submitted");
        softAssert.assertAll();
    }
    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsersAtOnce();
    }
}
