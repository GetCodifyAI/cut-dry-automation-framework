package com.cutanddry.qa.tests.multi_uom;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.CatalogData;
import com.cutanddry.qa.data.testdata.SplitWeightUOMData;
import com.cutanddry.qa.functions.*;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyCutWeightsAndSplitWeightFunctionalityForMultiUOMSKUsTest extends TestBase{
    static User user;
    static String distributorTarantino = SplitWeightUOMData.DISTRIBUTOR_NAME;
    static String customerId = SplitWeightUOMData.CUSTOMER_ID;
    static String singleItemName, singleSearchItemCode, multiItemName, multiSearchItemCode;
//    static String itemName = PriceData.ITEM_NAME_SPLIT_WEIGHT;
    static double itemOGPriceUOM1 ,itemOGPriceUOM2,totalOGItemPrice, multiItemPrice;
    String uom1 = CatalogData.MULTI_UOM_1;
    String uom2 = CatalogData.MULTI_UOM_2;
    static String orderId;

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-1095")
    public void verifyCutWeightsAndSplitWeightFunctionalityForMultiUOMSKUs() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");

        Login.navigateToDistributorPortal(distributorTarantino);
        Customer.ensureCarouselDisplayStatus(false);

        //Place an order with single and Multi OUM Items
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Unable to find the customer Id");
        Customer.clickOnOrderGuide(customerId);

        singleItemName = Customer.getItemNameFirstSingleOUM();
        singleSearchItemCode = Customer.getItemCodeFirstSingleOUM();

        multiItemName = Customer.getItemNameFirstMultiOUM();
        multiSearchItemCode = Customer.getItemCodeFirstMultiOUM();
        multiItemPrice = Customer.getActiveItemPriceFirstMultiOUMRowStable();

        // Added Multi OUM Item
        Customer.searchItemOnOrderGuide(multiSearchItemCode);
        Customer.ClickOnMultiUomDropDownOG(multiSearchItemCode);
        Customer.clickOGAddToCartPlusIcon(1, multiSearchItemCode, uom1);
        Customer.clickOGAddToCartPlusIcon(1, multiSearchItemCode, uom2);
        softAssert.assertEquals(Catalog.getItemUOMQuantity(multiSearchItemCode, uom1),"1", "item count error");
        softAssert.assertEquals(Catalog.getItemUOMQuantity(multiSearchItemCode, uom2),"1", "item count error");
        itemOGPriceUOM1 = Customer.getActiveItemPriceMultiOUM(uom1);
        itemOGPriceUOM2 = Customer.getActiveItemPriceMultiOUM(uom2);
        totalOGItemPrice = Customer.getItemPriceOnMultiOUMCheckout(); //Customer.getItemPriceOnCheckoutButton();
        softAssert.assertEquals(Math.round(totalOGItemPrice * 100.0) / 100.0,
                ((Math.round(itemOGPriceUOM1 * 100.0) / 100.0)+(Math.round(itemOGPriceUOM2 * 100.0) / 100.0)), "The item has not been selected.");

        // Added Single Item
        Customer.searchItemOnOrderGuide(singleSearchItemCode);
        Customer.increaseFirstRowQtyCustom(1);

        // Checkout
        Customer.checkoutItemsMultiOUM();
        softAssert.assertTrue(Customer.isReviewOrderTextDisplayed(), "The user is unable to land on the Review Order page.");
        Customer.submitOrder();
        softAssert.assertTrue(Customer.isThankingForOrderPopupDisplayed(), "The order was not completed successfully.");
        orderId = Customer.getSuccessOrderId();
        Customer.clickClose();

       /* //test
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Unable to find the customer Id");
        Customer.SelectCustomer(customerId);
        Customer.clickOnOrdersTab();
        Customer.clickOrder();
        softAssert.assertTrue(Customer.isOrderSectionDisplayed(),"order section not navigate");
        Orders.clickOnEditOrder();
        softAssert.assertTrue(Orders.isEditOrderPopupDisplayed(),"edit popup error");
        Orders.clickOnConfirm();
        softAssert.assertTrue(Orders.isNavigatedToOrderReviewPage(),"edit error(Review Page)");
        Orders.clickOnEditOrderInReview();
        softAssert.assertTrue(Orders.isNavigatedToEditOrder(),"edit error");
        Customer.searchItemOnOrderGuide(searchItemCode);

        Customer.splitWeight();
        softAssert.assertTrue(Customer.isSplitWeightPopupDisplayed(),"popup error");
        Customer.enterCasesValue("20");
        Customer.enterWeightValue("10");
        Customer.clickUpdateWeight();
        softAssert.assertEquals(Customer.getItemQtyFirstRow(),"20", "item count error");
        softAssert.assertEquals(Customer.getItemSplitFinalWeight(),"200 LB", "item weight error");
        softAssert.assertEquals(Customer.getEditSplitFinalWeightPrice(),"$520.00", "item price error");

        itemPrice=Customer.getSplitFinalWeightPrice();

        String priceText = Customer.getItemPriceOnEditOrderReviewCheckout().replace("$", "").replace(",", "");
        Double actualPrice = Double.valueOf(priceText);
        softAssert.assertEquals(actualPrice, itemPrice, "The item has not been selected.");
//        softAssert.assertEquals(Customer.getItemPriceOnEditOrderReviewCheckout(),itemPrice,"The item has not been selected.");
        Customer.clickOnCheckOutReview();
        Customer.clickEditOrderCheckout();
//        softAssert.assertEquals(Customer.getItemPriceOnEditOrderCheckout(),itemPrice,"The item has not been selected.");
//        Customer.clickCheckOutOrderGuide();
//        softAssert.assertTrue(Orders.isSubmitPopupDisplayed(),"submit pop up not display");
//        Orders.clickOnConfirm();
        softAssert.assertTrue(Orders.isOrderUpdatedOverlayDisplayed(),"update popup error");
        Orders.clickOnClose();

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Unable to find the customer Id");
        Customer.SelectCustomer(customerId);
        Customer.clickOnOrdersTab();

        String priceText1 = Customer.getPriceInCustomerOrder().replace("$", "");
        Double actualPrice1 = Double.valueOf(priceText);
        softAssert.assertEquals(actualPrice1, itemPrice, "The item has not been selected.");
//        softAssert.assertEquals(Customer.getPriceInCustomerOrder(),itemPrice,"The item has not been selected.");*/
        softAssert.assertAll();
    }

   @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
