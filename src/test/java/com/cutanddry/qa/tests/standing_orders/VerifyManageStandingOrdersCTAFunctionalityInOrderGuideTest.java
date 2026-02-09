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

public class VerifyManageStandingOrdersCTAFunctionalityInOrderGuideTest extends TestBase {
    static User user;
    static String customerId = "16579";
    static String deliveryDay = "Wednesday";
    static String title = "Test Order 3";

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-1561")
    public void verifyManageStandingOrdersCTAFunctionalityInOrderGuide() throws InterruptedException {
        String itemName;
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        Assert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Unable to find the customer Id");
        Customer.clickOnOrderGuide(customerId);

        Customer.expandMoreOptionsDropdown();
        Customer.clickOnManageStandingOrders();
        Customer.deleteTheExistingStandingOrdersInManageIFAvailable();
//        softAssert.assertTrue(Customer.isManageStandingOrdersPopupDisplayed()," add section popup error");
        Customer.clickOnManageCreateStandingOrder();

//        Customer.selectDeliveryDate(deliveryDay);
        itemName = Customer.getItemNameFirstRow();
        Customer.increaseFirstRowQtyByOne();
        Customer.checkoutItems();
        Customer.typeOnStandingOrderTitle(title);
        Customer.selectDeliveryDateAsLastBefore();
        softAssert.assertEquals(Customer.getItemNameFirstRow(),itemName,"item mismatch");
        Customer.setStandingOrder();
        Customer.selectEmail();
        softAssert.assertTrue(Customer.isStandingOrderEmailPopupDisplayed(),"pop up display error");
        Customer.scheduleStandingOrder();
        softAssert.assertTrue(Customer.isStandingOrderSuccessPopupDisplayed(),"order creating error");
        Customer.clickOK();

        // Edit the standing order
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        Assert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Unable to find the customer Id");
        Customer.clickOnOrderGuide(customerId);

        Customer.expandMoreOptionsDropdown();
        Customer.clickOnManageStandingOrders();
//        softAssert.assertTrue(Customer.isManageStandingOrdersPopupDisplayed()," add section popup error");
        Customer.clickOnStandingOrderEditIcon();

        Customer.editOrderFromReviewScreen();
        Customer.increaseFirstRowQtyByOne();
        Customer.checkoutItems();
//        Customer.selectDeliveryDate(deliveryDay);
        Customer.selectDeliveryDateAsLast();
        itemName = Customer.getItemNameFirstRow();
        softAssert.assertEquals(Customer.getItemNameFirstRow(),itemName,"item mismatch");
        Customer.resetStandingOrder();
        softAssert.assertTrue(Customer.isStandingOrderEmailPopupDisplayed(),"pop up display error");
        Customer.scheduleStandingOrder();
        softAssert.assertTrue(Customer.isStandingOrderSuccessPopupDisplayed(),"order creating error");
        Customer.clickOK();

        // Delete the standing order
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        Assert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Unable to find the customer Id");
        Customer.clickOnOrderGuide(customerId);

        Customer.expandMoreOptionsDropdown();
        Customer.clickOnManageStandingOrders();
//        softAssert.assertTrue(Customer.isManageStandingOrdersPopupDisplayed()," add section popup error");
        Customer.clickOnStandingOrderDeleteIcon();
        softAssert.assertFalse(Customer.isStandingOrdersDeletedIconDisplay(),"delete error");

        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
