package com.cutanddry.qa.tests.standing_orders;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
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

public class VerifyTheStandingOrderForChildOrderGuideAccountsTest extends TestBase {
    static User user;
    static String DP = "189234630 - Cut+Dry Agent - Indianhead Foodservice Distributor Sandbox";
    static String childCustomerId = "74927";

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-1603")
    public void verifyTheStandingOrderForChildOrderGuideAccounts() throws InterruptedException {
        String itemName;
        SoftAssert softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        Assert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");
        Login.navigateToDistributorPortal(DP);
        Assert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"navigation error");

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(childCustomerId);
        Assert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(childCustomerId), "Unable to find the customer Id");
        Customer.clickOnOrderGuide(childCustomerId);

        Customer.expandMoreOptionsDropdown();
        Customer.clickOnManageStandingOrders();
        Customer.deleteTheExistingStandingOrdersInManageIFAvailable();
        Customer.clickOnManageCreateStandingOrder();

        Customer.selectDeliveryDateAsLastBefore();
        itemName = Customer.getItemNameFirstRow();
        Customer.increaseFirstRowQtyByOne();
        Customer.checkoutItems();
        softAssert.assertEquals(Customer.getItemNameFirstRow(), itemName, "item mismatch");
        Customer.setStandingOrder();
        softAssert.assertTrue(Customer.isStandingOrderEmailPopupDisplayed(), "pop up display error");
        Customer.selectEmail();
        Customer.scheduleStandingOrder();
        softAssert.assertTrue(Customer.isStandingOrderSuccessPopupDisplayed(), "order creating error");
        Customer.clickOK();

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(childCustomerId);
        Assert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(childCustomerId), "Unable to find the customer Id");
        Customer.clickOnCustomerCode(childCustomerId);
        Customer.clickOnOrdersTab();
        softAssert.assertTrue(Customer.isStandingOrdersDisplayed(), "standing orders not displayed in orders tab");

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(childCustomerId);
        Assert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(childCustomerId), "Unable to find the customer Id");
        Customer.clickOnOrderGuide(childCustomerId);

        Customer.expandMoreOptionsDropdown();
        Customer.clickOnManageStandingOrders();
        softAssert.assertTrue(Customer.isStandingOrdersDeletedIconDisplay(), "standing order not displayed in manage standing orders overlay");

        Customer.clickOnStandingOrderDeleteIcon();
        softAssert.assertFalse(Customer.isStandingOrdersDeletedIconDisplay(), "delete error");

        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
