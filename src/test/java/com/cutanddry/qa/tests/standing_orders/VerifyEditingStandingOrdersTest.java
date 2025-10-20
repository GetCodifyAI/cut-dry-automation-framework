package com.cutanddry.qa.tests.standing_orders;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.Customer;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyEditingStandingOrdersTest extends TestBase {
    static User user;
    static String customerId = "16579";
    static String deliveryDay = "Tuesday";

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-201")
    public void verifyEditingStandingOrders() throws InterruptedException {
        String itemName;
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId),"search error");
        Customer.clickOnCustomerCode(customerId);
        Customer.clickOnOrdersTab();
        softAssert.assertTrue(Customer.isStandingOrdersDisplayed(),"navigation error");
        Customer.clickOnEditStandingOrder();
        Customer.editOrderFromReviewScreen();
//        Customer.selectDeliveryDate(deliveryDay);
        Customer.selectDeliveryDateAsLast();
        itemName = Customer.getItemNameFirstRow();
        Customer.increaseFirstRowQtyByOne();
        Customer.checkoutItems();
        softAssert.assertEquals(Customer.getItemNameFirstRow(),itemName,"item mismatch");
        Customer.resetStandingOrder();
        softAssert.assertTrue(Customer.isStandingOrderEmailPopupDisplayed(),"pop up display error");
        Customer.scheduleStandingOrder();
        softAssert.assertTrue(Customer.isStandingOrderSuccessPopupDisplayed(),"order creating error");
        Customer.clickOK();
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
