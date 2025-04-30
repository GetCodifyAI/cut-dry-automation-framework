package com.cutanddry.qa.tests.price;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.CatalogData;
import com.cutanddry.qa.data.testdata.PriceData;
import com.cutanddry.qa.functions.Customer;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ValidateTheSpotPricingNotFromPricingEngineTest extends TestBase{
    static User user;
    static String distributorVitco = PriceData.DISTRIBUTOR_VITCO;
    static String customerId5 = PriceData.CUSTOMER_ID_5;
    static String searchItemCode,spotPrice1,marginValue1, marginPercentage1;
    static String itemName = PriceData.ITEM_NAME_SPOT_PRICE2;
    static String uom1 = CatalogData.MULTI_UOM_1;
    static double itemPrice;

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-1082")
    public void ValidateTheSpotPricingNotFromPricingEngine() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");

        Login.navigateToDistributorPortal(distributorVitco);
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"navigation error");


        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId5);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId5), "Unable to find the customer Id");
        Customer.clickOnOrderGuide(customerId5);
        Customer.searchItemOnOrderGuide(itemName);
        searchItemCode = Customer.getItemCodeFirstRow();

        Customer.editMargin();
        softAssert.assertTrue(Customer.isMarginValuePopupDisplayed(),"popup error");
        Customer.enterMarginValue("8.05");
        Customer.updateMarginValues();
        Customer.editMargin();
        spotPrice1 = Customer.getSpotPriceMultiUOM(uom1);
        marginValue1 = Customer.getMarginValueMultiUOM(uom1);
        marginPercentage1 = formatStringDouble(Customer.getMarginPercentageMultiUOM(uom1));
        Customer.updateMarginValues();
        softAssert.assertTrue(Customer.isItemValueAdded(marginValue1),"update error");
        softAssert.assertTrue(Customer.isItemPercentageAdded(marginPercentage1),"update error");
        softAssert.assertTrue(Customer.isSpotPriceAdded(spotPrice1),"update error");

        Customer.editMargin();
        softAssert.assertTrue(Customer.isMarginValuePopupDisplayed(),"popup error");
        Customer.enterMarginPercentage("30");
        Customer.updateMarginValues();
        Customer.editMargin();
        spotPrice1 = Customer.getSpotPriceMultiUOM(uom1);
        marginValue1 = Customer.getMarginValueMultiUOM(uom1);
        marginPercentage1 = formatStringDouble(Customer.getMarginPercentageMultiUOM(uom1));
        Customer.updateMarginValues();
        softAssert.assertTrue(Customer.isItemValueAdded(marginValue1),"update error");
        softAssert.assertTrue(Customer.isItemPercentageAdded(marginPercentage1),"update error");
        softAssert.assertTrue(Customer.isSpotPriceAdded(spotPrice1),"update error");

        Customer.editSpotPrice();
        softAssert.assertTrue(Customer.isMarginValuePopupDisplayed(),"popup error");
        Customer.enterSpotPrice("50");
        Customer.updateMarginValues();
        Customer.editMargin();
        spotPrice1 = Customer.getSpotPriceMultiUOM(uom1);
        marginValue1 = Customer.getMarginValueMultiUOM(uom1);
        marginPercentage1 = formatStringDouble(Customer.getMarginPercentageMultiUOM(uom1));
        Customer.updateMarginValues();
        softAssert.assertTrue(Customer.isItemValueAdded(marginValue1),"update error");
        softAssert.assertTrue(Customer.isItemPercentageAdded(marginPercentage1),"update error");
        softAssert.assertTrue(Customer.isSpotPriceAdded(spotPrice1),"update error");

        Customer.increaseFirstRowQtyCustom(1);

        itemPrice = Customer.getActiveItemPriceFirstRowStable();
        softAssert.assertEquals(Customer.getItemPriceOnCheckoutButton(),itemPrice,"The item has not been selected.");
        Customer.checkoutItems();
        softAssert.assertTrue(Customer.isReviewOrderTextDisplayed(), "The user is unable to land on the Review Order page.");
        Customer.submitOrder();
        softAssert.assertTrue(Customer.isThankingForOrderPopupDisplayed(), "The order was not completed successfully.");
        Customer.clickClose();

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId5);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId5), "Unable to find the customer Id");
        Customer.SelectCustomer(customerId5);
        Customer.clickOnOrdersTab();
        String priceText = Customer.getPriceInCustomerOrder().replace("$", "").replace(",", "");
        Double actualPrice = Double.valueOf(priceText);
        softAssert.assertEquals(actualPrice, itemPrice, "The item has not been selected.");
//        softAssert.assertEquals(Customer.getPriceInCustomerOrder(),itemPrice,"The item has not been selected.");
        softAssert.assertAll();
    }

   @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
