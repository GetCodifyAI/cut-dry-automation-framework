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

public class ValidateTheSpotPricingWhenSelectingMultipleUOMTest extends TestBase {
    static User user;
    SoftAssert softAssert;

    static String distributor = SplitWeightUOMData.DISTRIBUTOR_NAME_IFC;
    static String customerId = SplitWeightUOMData.CUSTOMER_ID_IFC;
    static String sortOption = SplitWeightUOMData.SORT_ITEM_BY;
    static String uom1 = CatalogData.MULTI_UOM_1;
    static String uom2 = CatalogData.MULTI_UOM_2;
    static String orderId;
    static String multiItemName, multiSearchItemCode,spotPrice1,marginValue1, marginPercentage1,spotPrice2,marginValue2, marginPercentage2;
    static double itemOGPriceUOM1, itemOGPriceUOM2, totalOGItemPrice, multiItemPrice, totalCartAmount;


    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-1060")
    public void ValidateTheSpotPricingWhenSelectingMultipleUOM() throws InterruptedException {
        softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(), "login error");

        Login.navigateToDistributorPortal(distributor);
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"navigation error");

        //Place an order with single and Multi OUM Items
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Unable to find the customer Id");
        Customer.clickOnOrderGuide(customerId);
        Customer.selectSortItemByOption(sortOption);

        multiItemName = Customer.getItemNameFirstMultiOUM();
        multiSearchItemCode = Customer.getItemCodeFirstMultiOUM();
        multiItemPrice = Customer.getActiveItemPriceFirstMultiOUMRowStable();

        Customer.searchItemOnOrderGuide(multiSearchItemCode);
        Customer.ClickOnMultiUomDropDownOG(multiSearchItemCode);


        Customer.editMarginMultiUOM(uom1);
        softAssert.assertTrue(Customer.isMarginValuePopupDisplayed(),"popup error");
        Customer.enterSpotPriceMultiUOM(uom1,"700");
        Customer.enterSpotPriceMultiUOM(uom2,"200");
        Customer.updateMarginValues();

        Customer.editMarginMultiUOM(uom1);
        spotPrice1 = Customer.getSpotPriceMultiUOM(uom1);
        marginValue1 = Customer.getMarginValueMultiUOM(uom1);
        marginPercentage1 = formatStringDouble(Customer.getMarginPercentageMultiUOM(uom1));
        spotPrice2 = Customer.getSpotPriceMultiUOM(uom2);
        marginValue2 = Customer.getMarginValueMultiUOM(uom2);
        marginPercentage2 = formatStringDouble(Customer.getMarginPercentageMultiUOM(uom2));
        Customer.updateMarginValues();
        softAssert.assertTrue(Customer.isSpotPriceAdded(spotPrice1),"update spot price error - "+uom1);
        softAssert.assertTrue(Customer.isItemValueAdded(marginValue1),"update error value - "+uom1);
        softAssert.assertTrue(Customer.isItemPercentageAdded(marginPercentage1),"update error percentage - "+uom1);
        softAssert.assertTrue(Customer.isSpotPriceAdded(spotPrice2),"update spot price error - "+uom2);
        softAssert.assertTrue(Customer.isItemValueAdded(marginValue2),"update error value - "+uom2);
        softAssert.assertTrue(Customer.isItemPercentageAdded(marginPercentage2),"update error percentage - "+uom2);


        Customer.editMarginMultiUOM(uom1);
        softAssert.assertTrue(Customer.isMarginValuePopupDisplayed(),"popup error");
        Customer.enterMarginValueMultiUOM(uom1,"200");
        Customer.enterMarginValueMultiUOM(uom2,"200");
        Customer.updateMarginValues();

        Customer.editMarginMultiUOM(uom1);
        spotPrice1 = Customer.getSpotPriceMultiUOM(uom1);
        marginValue1 = Customer.getMarginValueMultiUOM(uom1);
        marginPercentage1 = formatStringDouble(Customer.getMarginPercentageMultiUOM(uom1));
        spotPrice2 = Customer.getSpotPriceMultiUOM(uom2);
        marginValue2 = Customer.getMarginValueMultiUOM(uom2);
        marginPercentage2 = formatStringDouble(Customer.getMarginPercentageMultiUOM(uom2));
        Customer.updateMarginValues();
        softAssert.assertTrue(Customer.isSpotPriceAdded(spotPrice1),"update spot price error - "+uom1);
        softAssert.assertTrue(Customer.isItemValueAdded(marginValue1),"update error value - "+uom1);
        softAssert.assertTrue(Customer.isItemPercentageAdded(marginPercentage1),"update error percentage - "+uom1);
        softAssert.assertTrue(Customer.isSpotPriceAdded(spotPrice2),"update spot price error - "+uom1);
        softAssert.assertTrue(Customer.isItemValueAdded(marginValue2),"update error value - "+uom1);
        softAssert.assertTrue(Customer.isItemPercentageAdded(marginPercentage2),"update error percentage - "+uom1);


        Customer.editMarginMultiUOM(uom1);
        softAssert.assertTrue(Customer.isMarginValuePopupDisplayed(),"popup error");
        Customer.enterMarginPercentageMultiUOM(uom1,"75");
        Customer.enterMarginPercentageMultiUOM(uom1,"75");
        Customer.updateMarginValues();

        Customer.editMarginMultiUOM(uom1);
        spotPrice1 = Customer.getSpotPriceMultiUOM(uom1);
        marginValue1 = Customer.getMarginValueMultiUOM(uom1);
        marginPercentage1 = formatStringDouble(Customer.getMarginPercentageMultiUOM(uom1));
        spotPrice2 = Customer.getSpotPriceMultiUOM(uom2);
        marginValue2 = Customer.getMarginValueMultiUOM(uom2);
        marginPercentage2 = formatStringDouble(Customer.getMarginPercentageMultiUOM(uom2));
        Customer.updateMarginValues();
        softAssert.assertTrue(Customer.isSpotPriceAdded(spotPrice1),"update spot price error - "+uom1);
        softAssert.assertTrue(Customer.isItemValueAdded(marginValue1),"update error value - "+uom1);
        softAssert.assertTrue(Customer.isItemPercentageAdded(marginPercentage1),"update error percentage - "+uom1);
        softAssert.assertTrue(Customer.isSpotPriceAdded(spotPrice2),"update spot price error - "+uom1);
        softAssert.assertTrue(Customer.isItemValueAdded(marginValue2),"update error value - "+uom1);
        softAssert.assertTrue(Customer.isItemPercentageAdded(marginPercentage2),"update error percentage - "+uom1);

        // Added Multi OUM Item
//        Customer.searchItemOnOrderGuide(multiSearchItemCode);
        //       Customer.ClickOnMultiUomDropDownOG(multiSearchItemCode);
        Customer.clickOGAddToCartPlusIcon(1, multiSearchItemCode, uom1);
        Customer.clickOGAddToCartPlusIcon(1, multiSearchItemCode, uom2);
        softAssert.assertEquals(Catalog.getItemUOMQuantity(multiSearchItemCode, uom1), "1", "item count error in 1st UOM");
        softAssert.assertEquals(Catalog.getItemUOMQuantity(multiSearchItemCode, uom2), "1", "item count error in 2nd UOM");
        itemOGPriceUOM1 = Customer.getActiveItemPriceMultiOUM(uom1);
        itemOGPriceUOM2 = Customer.getActiveItemPriceMultiOUM(uom2);
        totalOGItemPrice = Customer.getItemPriceOnMultiOUMCheckout(); //Customer.getItemPriceOnCheckoutButton();
        softAssert.assertEquals(Math.round(totalOGItemPrice * 100.0) / 100.0,
                ((Math.round(itemOGPriceUOM1 * 100.0) / 100.0) + (Math.round(itemOGPriceUOM2 * 100.0) / 100.0)), "The item was not selected properly.");

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
        softAssert.assertEquals(actualPrice, totalOGItemPrice, "The total values in the submission and the total displayed in the Customer Profile Orders section do not match.");


        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}

