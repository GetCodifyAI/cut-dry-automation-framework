package com.cutanddry.qa.tests.customer_profile;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.CustomerProfileData;
import com.cutanddry.qa.functions.*;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyTheHidePriceFunctionalityInTheWhiteLabelHomeScreenOrderHistorySectionTest extends TestBase {
    static User user;
    String customerID = "16672";
    static String itemName, itemPriceStr;
    static String searchItemCode = "01700";
    static double itemPrice;
    static String statusHidden = "Hidden";
    static String OperatorName = "465571413";
    String DistributorName = CustomerProfileData.DISTRIBUTOR_NAME_IFC;

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-2034")
    public void VerifyTheHidePriceFunctionalityInTheWhiteLabelHomeScreenOrderHistorySection() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();

        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        Assert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(), "login error");

        Login.navigateToDistributorPortal(DistributorName);
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "login error");

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerID);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerID), "search error");
        Customer.SelectCustomer(customerID);
        softAssert.assertTrue(Customer.isCustomerNameTxtDisplayed(), "text error");

        Customer.editStatusPriceVisibility(statusHidden);

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerID);
        Assert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerID), "Unable to find the customer Id");
        Customer.clickOnOrderGuide(customerID);

        Customer.searchItemOnOrderGuide(searchItemCode);
        itemName = Customer.getItemNameFirstRow();
        itemPrice = Customer.getActiveItemPriceFirstRow();
        itemPriceStr = String.valueOf(itemPrice);
        softAssert.assertTrue(itemPrice > 0, "DP side item price should be visible in OG");

        Customer.goToCatalog();
        Customer.searchItemOnCatalog(searchItemCode);
        softAssert.assertTrue(Customer.getFirstElementFrmSearchResults(itemName).contains(itemName.toLowerCase()), "item not found");
        softAssert.assertTrue(Customer.getItemPriceOnCatalog(itemName, itemPriceStr), "DP side price should be visible in catalog grid view");

        Customer.clickCatalogListView();
        softAssert.assertTrue(Customer.getItemPriceOnCatalogListView(itemName, itemPriceStr), "DP side price should be visible in catalog list view");

        Customer.clickCatalogGridView();
        Customer.clickOnProduct(itemName);
        softAssert.assertTrue(Customer.isProductDetailsDisplayed(), "The user is unable to land on the Product Details page.");
        double itemPricePDP = Catalog.getPDPPriceUOM("1");
        softAssert.assertEquals(itemPricePDP, itemPrice, "DP side price should be visible in PDP");

        Login.navigateToLoginAs();
        Login.logInToOperatorAsWhiteLabel(OperatorName);
        Customer.clickOnPlaceOrderWhiteLabel();

        Customer.searchItemOnOrderGuide(searchItemCode);
        Customer.increaseFirstRowQtyByOneInDist();

        softAssert.assertTrue(Customer.isCheckoutTextOnlyDisplayed(), "OP side checkout button should show 'Checkout' text only without price");

        Customer.clickOnCheckoutButtonOperator();
        softAssert.assertTrue(Customer.isReviewOrderTextDisplayed(), "The user is unable to land on the Review Order page.");

        Customer.submitOrder();
        softAssert.assertTrue(Customer.isThankingForOrderPopupDisplayed(), "Order submission failed - thank you popup not displayed");

        Customer.clickOnWLHomeTab();
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboardWhiteLabel(), "Failed to navigate to WL Home screen");

        Customer.scrollToWLOrderHistorySection();
        softAssert.assertTrue(Customer.isWLOrderHistorySectionDisplayed(), "Order History section not displayed on WL Home screen");

        softAssert.assertFalse(Customer.isPriceVisibleInWLOrderHistory(), "OP side Order History should NOT display prices when price visibility is set to Hidden");

        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
