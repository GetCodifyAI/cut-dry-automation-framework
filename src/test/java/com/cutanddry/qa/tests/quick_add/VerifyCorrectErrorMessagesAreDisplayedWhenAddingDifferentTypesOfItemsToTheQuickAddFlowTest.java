package com.cutanddry.qa.tests.quick_add;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.Customer;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.InternalTools;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyCorrectErrorMessagesAreDisplayedWhenAddingDifferentTypesOfItemsToTheQuickAddFlowTest extends TestBase {
    SoftAssert softAssert;
    static User user;
    static String customerId = "110RES";
    static String canonicalProductID = "140480041";
    static String productShopData = "146026040";
    static String disContinued = "discontinued";
    static String stockCount = "stockcount";
    static String stockValue = "0.0";
    static String itemQuantity = "2" ;
    static String outOfStockItemCode = "33925";
    static String outOfStockItemCodeError = "Product out of stock";
    static String discontinuedItemCode = "95337";
    static String discontinuedItemCodeError = "Product discontinued";

    static String CompanyName = "What Chefs Want - Rockies";
    static String DistributorName = "138629491 - Eshan - What Chefs Want - Rockies";
    static String status ="Enabled on DP Portal & Operator App";


    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }


    @Test(groups = "DOT-TC-1539")
    public void VerifyCorrectErrorMessagesAreDisplayedWhenAddingDifferentTypesOfItemsToTheQuickAddFlow() throws InterruptedException {

        softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        Assert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");

        //Set the node values before beginning the test case
        Login.navigateToNode(canonicalProductID);
        Login.setNode(disContinued);
        Login.navigateToNode(productShopData);
        Login.setValueToNode(stockCount,stockValue);

        Login.navigateToInternalToolsPage();
        InternalTools.navigateToConfigureSupplier();
        InternalTools.clickOnInternalToolCompanyEditDetails(CompanyName);
        InternalTools.navigateToOrderingSettingsTab();

        InternalTools.clickOnQuickAddViewDropDown(status);

        InternalTools.clickSave();
        softAssert.assertTrue(InternalTools.isSuccessPopUpDisplayed(),"change not save");
        InternalTools.clickOKOnSucessOverlay();

        Login.navigateToDistributorPortal(DistributorName);
        Assert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "The user is unable to land on the Dashboard page.");
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        Assert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Unable to find the customer Id");
        Customer.clickOnOrderGuide(customerId);

        Customer.expandMoreOptionsDropdown();
        softAssert.assertTrue(Customer.isQuickAddOptionDisplay(),"quick add option display error");
        Customer.clickQuickAdd();
        softAssert.assertTrue(Customer.isQuickAddViewDisplay(),"quick add view page display error");

        Customer.enterItemCode(outOfStockItemCode);
        Customer.enterItemQuantity(itemQuantity);
        Customer.clickVerifyItem();
        softAssert.assertTrue(Customer.isItemVerifiedFailedPopUpDisplay(),"item verified failed pop up not display");
        Customer.clickClosePopUp();
        softAssert.assertTrue(Customer.isErrorTextDisplay(outOfStockItemCodeError),"invalid item code text not display");
        Customer.clickTrashIcon();

        Customer.enterItemCode(discontinuedItemCode);
        Customer.enterItemQuantity(itemQuantity);
        Customer.clickVerifyItem();
        softAssert.assertTrue(Customer.isItemVerifiedFailedPopUpDisplay(),"item verified failed pop up not display");
        Customer.clickClosePopUp();
        softAssert.assertTrue(Customer.isErrorTextDisplay(discontinuedItemCodeError),"invalid item code text not display");
        Customer.clickTrashIcon();


        softAssert.assertAll();
    }
    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsersAtOnce();
    }
}
