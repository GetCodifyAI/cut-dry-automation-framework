package com.cutanddry.qa.tests.multi_uom;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.CatalogData;
import com.cutanddry.qa.data.testdata.GatekeeperData;
import com.cutanddry.qa.data.testdata.SplitWeightUOMData;
import com.cutanddry.qa.functions.*;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ValidateThePoundSpotPricingWhenSelectingMultipleUOMTest extends TestBase {
    static User user;
    SoftAssert softAssert;

    // TODO: Need a DP and Customer with Multi UOM for Pond price
    static String distributor = SplitWeightUOMData.DISTRIBUTOR_NAME_FSM;
    static String customerId = SplitWeightUOMData.CUSTOMER_ID_FSM;
    static String sortOption = SplitWeightUOMData.SORT_ITEM_BY;
    static String uom1 = CatalogData.MULTI_UOM_1;
    static String uom2 = CatalogData.MULTI_UOM_2;
    static String featureName = GatekeeperData.FEATURE_NAME_FROM_ELASTIC_SEARCH;
    static String companyId = GatekeeperData.COMPONY_ID_KK_INT;
    static String orderId;
    static String singleItemName, singleSearchItemCode, multiItemName,spotPrice1,marginValue1, marginPercentage1,spotPrice2,marginValue2, marginPercentage2;
    static double itemOGPriceUOM1, itemOGPriceUOM2, totalOGItemPrice, multiItemPrice, totalCartAmount,unitWeight1,unitWeight2;
    static String multiSearchItemCode = "2110";


    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-1061")
    public void ValidateThePoundSpotPricingWhenSelectingMultipleUOM() throws InterruptedException {
        softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(), "login error");

        Login.navigateToDistributorPortal(distributor);
//        Customer.ensureCarouselDisplayStatus(false);

        //Place an order with Multi OUM Items
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Unable to find the customer Id");
        Customer.clickOnOrderGuide(customerId);
        Customer.selectSortItemByOption(sortOption);

//        multiItemName = Customer.getItemNameFirstMultiOUMLB();
//        multiSearchItemCode = Customer.getItemCodeFirstMultiOUMLB();
//        itemCode = multiSearchItemCode.replaceAll("^[A-Za-z]+", "");

        Customer.searchItemOnOrderGuide(multiSearchItemCode);
        Customer.ClickOnMultiUomDropDownOG(multiSearchItemCode);
        Customer.clickPoundPriceMultiUOM();
        softAssert.assertTrue(Customer.isPoundPricePopUpDisplay(),"pound price pop up not display");
        Customer.enterSpotPriceMultiUOM(uom1,"200");
        Customer.enterSpotPriceMultiUOM(uom2,"100");
        Customer.updateMarginValues();

        Customer.clickPoundPriceMultiUOM();;
        spotPrice1 = Customer.getSpotPriceMultiUOM(uom1);
        marginValue1 = Customer.getMarginValueMultiUOM(uom1);
        marginPercentage1 = formatStringDouble(Customer.getMarginPercentageMultiUOM(uom1));
        spotPrice2 = Customer.getSpotPriceMultiUOM(uom2);
        marginValue2 = Customer.getMarginValueMultiUOM(uom2);
        marginPercentage2 = formatStringDouble(Customer.getMarginPercentageMultiUOM(uom2));
        Customer.updateMarginValues();
        softAssert.assertTrue(Customer.isSpotPriceAdded("1",spotPrice1),"update spot price error - "+uom1);
        softAssert.assertTrue(Customer.isItemValueAdded("1",marginValue1),"update error value - "+uom1);
        softAssert.assertTrue(Customer.isItemPercentageAdded("1",marginPercentage1),"update error percentage - "+uom1);
        softAssert.assertTrue(Customer.isSpotPriceAdded("2",spotPrice2),"update spot price error - "+uom1);
        softAssert.assertTrue(Customer.isItemValueAdded("2",marginValue2),"update error value - "+uom1);
        softAssert.assertTrue(Customer.isItemPercentageAdded("2",marginPercentage2),"update error percentage - "+uom1);


        Customer.clickPoundPriceMultiUOM();
        softAssert.assertTrue(Customer.isMarginValuePopupDisplayed(),"popup error");
        Customer.enterMarginValueMultiUOM(uom1,"200");
        Customer.enterMarginValueMultiUOM(uom2,"100");
        Customer.updateMarginValues();

        Customer.clickPoundPriceMultiUOM();
        spotPrice1 = Customer.getSpotPriceMultiUOM(uom1);
        marginValue1 = Customer.getMarginValueMultiUOM(uom1);
        marginPercentage1 = formatStringDouble(Customer.getMarginPercentageMultiUOM(uom1));
        spotPrice2 = Customer.getSpotPriceMultiUOM(uom2);
        marginValue2 = Customer.getMarginValueMultiUOM(uom2);
        marginPercentage2 = formatStringDouble(Customer.getMarginPercentageMultiUOM(uom2));
        Customer.updateMarginValues();
        softAssert.assertTrue(Customer.isSpotPriceAdded("1",spotPrice1),"update spot price error - "+uom1);
        softAssert.assertTrue(Customer.isItemValueAdded("1",marginValue1),"update error value - "+uom1);
        softAssert.assertTrue(Customer.isItemPercentageAdded("1",marginPercentage1),"update error percentage - "+uom1);
        softAssert.assertTrue(Customer.isSpotPriceAdded("2",spotPrice2),"update spot price error - "+uom1);
        softAssert.assertTrue(Customer.isItemValueAdded("2",marginValue2),"update error value - "+uom1);
        softAssert.assertTrue(Customer.isItemPercentageAdded("2",marginPercentage2),"update error percentage - "+uom1);


        Customer.clickPoundPriceMultiUOM();
        softAssert.assertTrue(Customer.isMarginValuePopupDisplayed(),"popup error");
        Customer.enterMarginPercentageMultiUOM(uom1,"75");
        Customer.enterMarginPercentageMultiUOM(uom2,"50");
        Customer.updateMarginValues();

        Customer.clickPoundPriceMultiUOM();
        spotPrice1 = Customer.getSpotPriceMultiUOM(uom1);
        marginValue1 = Customer.getMarginValueMultiUOM(uom1);
        marginPercentage1 = formatStringDouble(Customer.getMarginPercentageMultiUOM(uom1));
        spotPrice2 = Customer.getSpotPriceMultiUOM(uom2);
        marginValue2 = Customer.getMarginValueMultiUOM(uom2);
        marginPercentage2 = formatStringDouble(Customer.getMarginPercentageMultiUOM(uom2));
        Customer.updateMarginValues();
        softAssert.assertTrue(Customer.isSpotPriceAdded("1",spotPrice1),"update spot price error - "+uom1);
        softAssert.assertTrue(Customer.isItemValueAdded("1",marginValue1),"update error value - "+uom1);
        softAssert.assertTrue(Customer.isItemPercentageAdded("1",marginPercentage1),"update error percentage - "+uom1);
        softAssert.assertTrue(Customer.isSpotPriceAdded("2",spotPrice2),"update spot price error - "+uom1);
        softAssert.assertTrue(Customer.isItemValueAdded("2",marginValue2),"update error value - "+uom1);
        softAssert.assertTrue(Customer.isItemPercentageAdded("2",marginPercentage2),"update error percentage - "+uom1);

        Customer.clickOGAddToCartPlusIcon(1, multiSearchItemCode, uom1);
        Customer.clickOGAddToCartPlusIcon(1, multiSearchItemCode, uom2);
        softAssert.assertEquals(Catalog.getItemUOMQuantity(multiSearchItemCode, uom1), "1", "item count error in 1st UOM");
        softAssert.assertEquals(Catalog.getItemUOMQuantity(multiSearchItemCode, uom2), "1", "item count error in 2nd UOM");
        itemOGPriceUOM1 = Customer.getActiveItemPriceMultiOUM(uom1);
        itemOGPriceUOM2 = Customer.getActiveItemPriceMultiOUM(uom2);
        totalOGItemPrice = Customer.getItemPriceOnMultiOUMCheckout(); //Customer.getItemPriceOnCheckoutButton();
        softAssert.assertEquals(Math.round(totalOGItemPrice * 100.0) / 100.0,
                ((Math.round(itemOGPriceUOM1 * 100.0) / 100.0) + (Math.round(itemOGPriceUOM2 * 100.0) / 100.0)),0.01, "The item was not selected properly.");

        // Checkout
        Customer.checkoutItemsMultiOUM();
        softAssert.assertTrue(Customer.isReviewOrderTextDisplayed(), "The user is unable to land on the Review Order page.");
        Customer.submitOrder();
        softAssert.assertTrue(Customer.isThankingForOrderPopupDisplayed(), "The order was not completed successfully.");
        orderId = Customer.getSuccessOrderId();
        Customer.clickClose();

        Dashboard.navigateToCustomers();
        Customer.refreshCustomersPage();
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Unable to find the customer Id");
        Customer.SelectCustomer(customerId);
        Customer.clickOnOrdersTab();
        Customer.OrderDateSort();
        Customer.OrderDateSort();
        double actualPrice = Double.parseDouble(Customer.getPriceInCustomerOrder().replace("$", "").replace(",", "").trim());
        softAssert.assertEquals(actualPrice, totalOGItemPrice,0.01, "The total values in the submission and the total displayed in the Customer Profile Orders section do not match.");


        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
