package com.cutanddry.qa.tests.offline_ordering;

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

public class VerifyThatTheOfflineModeIsAvailableInStandingOrdersTest extends TestBase {
    static User user;
    static String customerId = "16579";
    static String deliveryDay = "Monday";

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-1251")
    public void VerifyThatTheOfflineModeIsAvailableInStandingOrders() throws InterruptedException {
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
        Customer.removeStandingOrdersIfAvailable();
        Customer.clickOnCreateStandingOrder();

        Customer.expandMoreOptionsDropdown();
        Customer.clickSwitchToOfflineMode();
        softAssert.assertTrue(Customer.isOfflineModePopUpDisplay(),"offline mode pop up error");
        Customer.clickActiveOfflineMode();
        softAssert.assertTrue(Customer.isHangTightPopUpDisplay(),"hang tight pop up not display");
        Thread.sleep(3000);

        Customer.selectDeliveryDate(deliveryDay);
        itemName = Customer.getItemNameFirstRow();
        Customer.increaseFirstRowQtyByOne();

        Customer.clickGoOnline();

        Customer.checkoutItems();
        softAssert.assertEquals(Customer.getItemNameFirstRow(),itemName,"item mismatch");
        Customer.setStandingOrder();
        softAssert.assertTrue(Customer.isStandingOrderEmailPopupDisplayed(),"pop up display error");
        Customer.selectEmail();
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
