package com.cutanddry.qa.tests.customer_profile;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.CustomerProfileData;
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

public class VerifyTheHidePriceFunctionalityTest extends TestBase{
    static User user;
    String customerID = "16672";
    static String itemName,itemPriceStr;
    static String searchItemCode = "01700";
    static double itemPrice,itemPricePDP;
    static String statusVisible = "Visible";
    static String statusHidden = "Hidden";
    static String OperatorName = "465571413";
    String DistributorName = CustomerProfileData.DISTRIBUTOR_NAME_IFC;


    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-1861")
    public void VerifyTheHidePriceFunctionality() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        Assert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");

        Login.navigateToDistributorPortal(DistributorName);
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "login error");
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerID);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerID),"search error");
        Customer.SelectCustomer(customerID);
        softAssert.assertTrue(Customer.isCustomerNameTxtDisplayed(), "text error");
        Customer.editStatusPriceVisibility(statusHidden);

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerID);
        Assert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerID), "Unable to find the customer Id");
        Customer.clickOnOrderGuide(customerID);

        // Add the product via Order Guide
        Customer.searchItemOnOrderGuide(searchItemCode);
        itemName = Customer.getItemNameFirstRow();
        itemPrice = Customer.getActiveItemPriceFirstRow();
        itemPriceStr = String.valueOf(Customer.getActiveItemPriceFirstRow());
        Customer.clickOnPlusIconInCatalogPDP(1, itemName);
        softAssert.assertEquals(Customer.getItemPriceOnCheckoutButton(),itemPrice,"The item has not been selected.");

        Customer.goToCatalog();
        Customer.searchItemOnCatalog(searchItemCode);
        softAssert.assertTrue(Customer.getFirstElementFrmSearchResults(itemName).contains(itemName.toLowerCase()), "item not found");
        softAssert.assertTrue(Customer.getItemPriceOnCatalog(itemName,itemPriceStr),"price display catalog error");
        Customer.clickCatalogListView();
        softAssert.assertTrue(Customer.getItemPriceOnCatalogListView(itemName,itemPriceStr),"price display catalog list view error");
        Customer.clickCatalogGridView();
        Customer.clickOnProduct(itemName);
        softAssert.assertTrue(Customer.isProductDetailsDisplayed(),"The user is unable to land on the Product Details page.");
        itemPricePDP = Catalog.getPDPPriceUOM("1");
        softAssert.assertEquals(itemPricePDP,itemPrice,"The price display in PDP");

        Login.navigateToLoginAs();
        Login.logInToOperatorAsWhiteLabel(OperatorName);
        Customer.clickOnPlaceOrderWhiteLabel();



        softAssert.assertAll();
    }

  @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
