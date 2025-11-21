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

public class VerifyThSelectMultipleUOMJustInTimeItemAndDraftEditTest extends TestBase {
    SoftAssert softAssert;
    static User user;
    static String customerId = CatalogData.CUSTOMER_ID_3;
    String searchItemCode = CatalogData.ITEM_CODE_5;
    static double itemPriceUOM1 ,itemPriceUOM2,totalPDPItemPrice,itemOGPriceUOM1,itemOGPriceUOM2;
    String uom1 = CatalogData.MULTI_UOM_1;
    String uom2 = CatalogData.MULTI_UOM_2;
    static String orderId;
    static String DP = CatalogData.DP_VICTO;
    String itemName = CatalogData.ITEM_NAME_JIT;
    String uomDropDownOption = CatalogData.UOM_DROPDOWN_OPTION;
    static double totalOGItemPrice;
    String multiSearchItemCode = CatalogData.ITEM_CODE_12;
    static String orderGuide = CatalogData.VITCO_OG;

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }


    @Test(groups = "DOT-TC-1086")
    public void VerifyThSelectMultipleUOMJustInTimeItemAndDraftEdit() throws InterruptedException {

        softAssert = new SoftAssert();

        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        Assert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");
        Login.navigateToLoginAs();
        Login.logInToDP(DP);
        Assert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"navigation error");
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        Assert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId),"search error");
        Customer.clickOnOrderGuide(customerId);
        Customer.selectOrderGuideIfOverlayDisplayed(orderGuide);

        Customer.goToCatalog();
        Customer.searchItemOnCatalog(searchItemCode);
        softAssert.assertTrue(Customer.getFirstElementFrmSearchResults(itemName).contains(itemName.toLowerCase()), "item not found");
        Catalog.ClickOnCatalogMultiUomDropDown(itemName);
        Catalog.ClickOnMultiUomDropDownOption(uomDropDownOption);
        softAssert.assertTrue(Customer.isProductDetailsDisplayed(),"The user is unable to land on the Product Details page.");
        itemPriceUOM1 = Catalog.getPDPPriceUOMVitco(uom1,searchItemCode);
        itemPriceUOM2 = Catalog.getPDPPriceUOMVitco(uom2,searchItemCode);
        Catalog.clickAddToCartPlusIconVitco(1, uom1,searchItemCode);
        Catalog.clickAddToCartPlusIconVitco(1, uom2,searchItemCode);
        totalPDPItemPrice = Customer.getItemPriceOnCheckoutButtonViaPDP();
        /*softAssert.assertEquals(Math.round(totalPDPItemPrice * 10.0) / 10.0,
                (Math.round((itemPriceUOM1 + itemPriceUOM2) * 10.0) / 10.0), "The item has not been selected.");*/
        Customer.clickCheckOutPDP();
        softAssert.assertTrue(Customer.isReviewOrderTextDisplayed(), "The user is unable to land on the Review Order page.");

        Dashboard.navigateToDrafts();
        Assert.assertTrue(Draft.isUserNavigatedToDrafts(),"navigation error");
        softAssert.assertTrue(Draft.isLastDraftDisplayedVito(String.valueOf(totalPDPItemPrice)),"draft creating error");
        Draft.clickDraft(String.valueOf(totalPDPItemPrice));
        softAssert.assertTrue(Customer.isReviewOrderTextDisplayed(), "The user is unable to land on the Review Order page.");

        Draft.clickEditOrder();

        // Added Multi UOM Item
        Customer.searchItemOnOrderGuide(multiSearchItemCode);
        Customer.addItemFromCatalogIfNotAvailableInOG(multiSearchItemCode);
        Customer.ClickOnMultiUomDropDownOG(multiSearchItemCode);
        Customer.clickOGAddToCartPlusIcon(1, multiSearchItemCode, uom1);
        Customer.clickOGAddToCartPlusIcon(1, multiSearchItemCode, uom2);
        softAssert.assertEquals(Catalog.getItemUOMQuantity(multiSearchItemCode, uom1), "1", "item count error in 1st UOM");
        softAssert.assertEquals(Catalog.getItemUOMQuantity(multiSearchItemCode, uom2), "1", "item count error in 2nd UOM");
        itemOGPriceUOM1 = Customer.getActiveItemPriceMultiOUM(uom1);
        itemOGPriceUOM2 = Customer.getActiveItemPriceMultiOUM(uom2);
        totalOGItemPrice = Customer.getItemPriceOnMultiOUMCheckout();
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
        softAssert.assertEquals(Catalog.getTotalPriceInOrder(), totalOGItemPrice ,0.1, "The item has not been selected.");
        softAssert.assertEquals(Catalog.getTotalQuantityInOrder(),"4","order quantity not successfully submitted");
        softAssert.assertAll();
    }
    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsersAtOnce();
    }
}
