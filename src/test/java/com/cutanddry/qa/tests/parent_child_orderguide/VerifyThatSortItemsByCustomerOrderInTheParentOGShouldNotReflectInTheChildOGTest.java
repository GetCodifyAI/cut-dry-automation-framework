package com.cutanddry.qa.tests.parent_child_orderguide;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.ParentChildOGData;
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

public class VerifyThatSortItemsByCustomerOrderInTheParentOGShouldNotReflectInTheChildOGTest extends TestBase {
    static User user;
    static String DP = ParentChildOGData.DISTRIBUTOR_INDIANHEAD;
    static String customerId = ParentChildOGData.CUSTOMER_ID_INDIANHEAD;
    static String customerId2 = ParentChildOGData.CUSTOMER_ID_INDIANHEAD_2;
    static String customOrder = "Custom Order";

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-1222")
    public void VerifyThatSortItemsByCustomerOrderInTheParentOGShouldNotReflectInTheChildOG() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        Assert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");
        Login.navigateToDistributorPortal(DP);
        Assert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"navigation error");

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        Assert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId),"search error");
        Customer.clickOnOrderGuideParentChild(customerId);
        softAssert.assertTrue(Customer.isCustomerOrderGuideDisplayed(),"user has navigated to the Order Guide");
        Customer.clickSortOptionsDropdown();
        Customer.selectCustomOrderSort();
        softAssert.assertTrue(Customer.isSortOptionDisplayed(customOrder),"Custom Order sort not display");

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId2);
        Assert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId2),"search error");
        Customer.clickOnOrderGuideParentChild(customerId2);
        softAssert.assertFalse(Customer.isSortOptionDisplayed(customOrder),"Custom Order sort display");
        Customer.clickSortOptionsDropdown();
        Customer.selectCustomOrderSort();
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
