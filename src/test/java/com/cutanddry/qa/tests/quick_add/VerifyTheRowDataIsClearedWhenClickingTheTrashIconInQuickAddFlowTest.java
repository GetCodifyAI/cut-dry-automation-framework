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

public class VerifyTheRowDataIsClearedWhenClickingTheTrashIconInQuickAddFlowTest extends TestBase {
    SoftAssert softAssert;
    static User user;
    static String customerId = "16579";
    static String itemQuantity = "2" ;
    static String invalidItemCode = "92345";
    static String itemCode = "02345";


    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }


    @Test(groups = "DOT-TC-1533")
    public void VerifyTheRowDataIsClearedWhenClickingTheTrashIconInQuickAddFlow() throws InterruptedException {

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
        Customer.enterItemCode(invalidItemCode);
        Customer.enterItemQuantity(itemQuantity);
        Customer.clickVerifyItem();
        softAssert.assertTrue(Customer.isItemVerifiedFailedPopUpDisplay(),"item verified failed pop up not display");
        Customer.clickClosePopUp();
        softAssert.assertTrue(Customer.isInvalidItemCodeTextDisplay(),"invalid item code text not display");
        Customer.clickTrashIcon();

        Customer.enterItemCode(itemCode);
        Customer.enterItemQuantity(itemQuantity);
        Customer.clickVerifyItem();
        softAssert.assertTrue(Customer.isItemVerifiedPopUpDisplay(),"item verified pop up not display");
        Customer.clickClosePopUp();
        softAssert.assertAll();
    }
    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsersAtOnce();
    }
}
