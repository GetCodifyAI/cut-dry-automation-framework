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

public class ValidateTheFinalWeightWhenSelectingMultipleUOMTest extends TestBase {
    static User user;
    SoftAssert softAssert;

    static String distributor = SplitWeightUOMData.DP_NAME_KK_INT;
    static String customerId = SplitWeightUOMData.CUSTOMER_ID_KK_INT_1;
    static String sortOption = SplitWeightUOMData.SORT_ITEM_BY;
    static String uom1 = CatalogData.MULTI_UOM_1;
    static String uom2 = CatalogData.MULTI_UOM_2;
    static String featureName = GatekeeperData.FEATURE_NAME_FROM_ELASTIC_SEARCH;
    static String companyId = GatekeeperData.COMPONY_ID_KK_INT;
    static String orderId;
    static String singleItemName, singleSearchItemCode, multiItemName, multiSearchItemCode, itemCode;
    static double itemOGPriceUOM1, itemOGPriceUOM2, totalOGItemPrice, multiItemPrice, totalCartAmount,unitWeight1,unitWeight2;

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-1057")
    public void ValidateTheFinalWeightWhenSelectingMultipleUOM() throws InterruptedException {
        softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(), "login error");

        Login.navigateToGateKeeperAdmin();
        Login.updateCompanyIDs(featureName,companyId);

        Login.navigateToDistributorPortal(distributor);
        Customer.ensureCarouselDisplayStatus(false);

        //Place an order with single and Multi OUM Items
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Unable to find the customer Id");
        Customer.clickOnOrderGuide(customerId);
        Customer.selectSortItemByOption(sortOption);

        multiItemName = Customer.getItemNameFirstMultiOUM();
        multiSearchItemCode = Customer.getItemCodeFirstMultiOUM();
        itemCode = multiSearchItemCode.replaceAll("^[A-Za-z]+", "");
        multiItemPrice = Customer.getActiveItemPriceFirstMultiOUMRowStable();

        // Added Multi OUM Item
        Customer.searchItemOnOrderGuide(itemCode);
        Customer.ClickOnMultiUomDropDownOG(multiSearchItemCode);
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

        // Test Flow
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Unable to find the customer Id");
        Customer.SelectCustomer(customerId);
        Customer.clickOnOrdersTab();
        Customer.clickOrder();
        softAssert.assertTrue(Customer.isOrderSectionDisplayed(), "order section not navigate");
        Orders.clickOnEditOrder();
        softAssert.assertTrue(Orders.isEditOrderPopupDisplayed(), "edit popup error");
        Orders.clickOnConfirm();
//        softAssert.assertTrue(Orders.isNavigatedToOrderReviewPage(), "edit error(Review Page)");
        Orders.clickOnEditOrderInReviewStable();
        softAssert.assertTrue(Orders.isNavigatedToEditOrder(), "edit error");
        Customer.searchItemOnOrderGuide(itemCode); //itemCode

        Customer.splitWeightMultiUOM(uom1);
        softAssert.assertTrue(Customer.isSplitWeightPopupDisplayed(), "popup error");

        unitWeight1 = Customer.getUnitPriceMultiUOM(uom1,"1");
        Customer.enterTotalWeightMultiUOM(uom1, "1", String.valueOf(unitWeight1*3));
        unitWeight2 = Customer.getUnitPriceMultiUOM(uom2,"1");
//        Customer.enterWeightValueMultiUOM(uom2, "1", String.valueOf(unitWeight*1));

        Customer.clickUpdateWeight();
        softAssert.assertEquals(Customer.getItemQtyMultiUOM(uom1), "3", "item count error in 1st row");
        softAssert.assertEquals(Customer.getItemQtyMultiUOM(uom2), "1", "item count error in 2nd row");
        softAssert.assertTrue(Customer.getFinalWeightMultiUOM(uom1, "1").contains(formatDouble(unitWeight1*3)), "item weight error in 1st row");
        softAssert.assertTrue(Customer.getFinalWeightMultiUOM(uom2, "1").contains(String.valueOf(unitWeight2)), "item weight error in 2nd row");
        System.out.println(Customer.getFinalWeightMultiUOM(uom1, "1"));
        System.out.println(String.valueOf(unitWeight1*3));
        totalCartAmount = Customer.getTotalPriceCart();


        Customer.clickCheckOutOrderGuide();

        if (Customer.isEditOrderCheckout()) {
            Customer.clickEditOrderCheckout();
            softAssert.assertTrue(Orders.isOrderUpdatedOverlayDisplayed(), "update popup error");
        } else {
            softAssert.assertTrue(Orders.isSubmitPopupDisplayed(), "submit pop up not display");
            Orders.clickOnConfirm();
            softAssert.assertTrue(Orders.isOrderUpdatedOverlayDisplayed(), "update popup error");
        }
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
        softAssert.assertEquals(actualPrice, totalCartAmount, "The total values in the submission and the total displayed in the Customer Profile Orders section do not match.");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
