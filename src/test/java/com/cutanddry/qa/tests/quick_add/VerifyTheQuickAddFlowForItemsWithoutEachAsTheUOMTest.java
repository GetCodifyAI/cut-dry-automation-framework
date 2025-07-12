package com.cutanddry.qa.tests.quick_add;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
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

public class VerifyTheQuickAddFlowForItemsWithoutEachAsTheUOMTest extends TestBase {
    SoftAssert softAssert;
    static User user;
    static String customerId = "16579";
    static String itemQuantity = "2" ;
    static String itemCode = "02345";
    static String itemDescription = "Lemons 115 CT";
    static String packSize = "115 Ct";
    static String UOM = "Pkg";
    static String orderId,totalItemQuantityReviewOrder;
    static double totalItemPriceReviewOrder;

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }


    @Test(groups = "DOT-TC-1534")
    public void VerifyTheQuickAddFlowForItemsWithoutEachAsTheUOM() throws InterruptedException {

        softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        Assert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "The user is unable to land on the Dashboard page.");
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        Assert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Unable to find the customer Id");
        Customer.clickOnOrderGuide(customerId);

        Customer.expandMoreOptionsDropdown();
        softAssert.assertTrue(Customer.isQuickAddOptionDisplay(),"quick add option display error");
        Customer.clickQuickAdd();
        softAssert.assertTrue(Customer.isQuickAddViewDisplay(),"quick add view page display error");
        Customer.enterItemCode(itemCode);
        Customer.enterItemQuantity(itemQuantity);
        Customer.clickCheckBoxEach();
        Customer.clickVerifyItem();
        softAssert.assertTrue(Customer.isItemVerifiedFailedPopUpDisplay(),"item verified failed pop up not display");
        Customer.clickClosePopUp();
        softAssert.assertTrue(Customer.isUnitNotValidTextDisplay(),"invalid item code text not display");

        Customer.clickCheckBoxEach();
        Customer.clickVerifyItem();
        softAssert.assertTrue(Customer.isItemVerifiedPopUpDisplay(),"item verified pop up not display");
        Customer.clickClosePopUp();
        softAssert.assertTrue(Customer.isQuickAddedItemDisplay(itemCode),"quick added item data error");
        softAssert.assertTrue(Customer.isQuickAddedItemDisplay(itemDescription),"quick added item data error");
        softAssert.assertTrue(Customer.isQuickAddedItemDisplay(packSize),"quick added item data error");
        softAssert.assertTrue(Customer.isQuickAddedItemDisplay(UOM),"quick added item data error");
        softAssert.assertTrue(Customer.isQuickAddedItemQuantityDisplay(itemQuantity),"quick added item data error");
        Customer.clickSaveAndReview();

        softAssert.assertTrue(Customer.isReviewOrderTextDisplayed(), "The user is unable to land on the Review Order page.");
        softAssert.assertEquals(Customer.getItemNameFirstRow(), itemDescription, "The item selected by the user is different from what is shown on the order review page.");
        totalItemPriceReviewOrder = Catalog.getTotalPriceInReviewOrder();
        totalItemQuantityReviewOrder = Catalog.getTotalQuantityInReviewOrder();
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
        softAssert.assertEquals(Catalog.getTotalPriceInOrder(),totalItemPriceReviewOrder,"order not successfully submitted");
        softAssert.assertEquals(Catalog.getTotalQuantityInOrder(),totalItemQuantityReviewOrder,"order quantity not successfully submitted");
        softAssert.assertAll();
    }
    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsersAtOnce();
    }
}
