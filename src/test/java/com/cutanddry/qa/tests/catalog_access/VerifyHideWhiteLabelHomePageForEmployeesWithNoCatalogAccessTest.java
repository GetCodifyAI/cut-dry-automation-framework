package com.cutanddry.qa.tests.catalog_access;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.CustomerData;
import com.cutanddry.qa.functions.Customer;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.functions.OrderGuideSettings;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyHideWhiteLabelHomePageForEmployeesWithNoCatalogAccessTest extends TestBase {
    static User user;
    String DistributorName = CustomerData.DISTRIBUTOR_HILL_CREST;;
    static String customerId = "71227";;
    static String AddItemRestrictionDropDownOption = "Don’t allow employees to add new items";
    static String orderGuide1 = "Standard Guide";
    static String orderGuide2 = "Order History";
    static String OperatorName = "274973452";

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-1514")
    public void VerifyHideWhiteLabelHomePageForEmployeesWithNoCatalogAccess() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();

        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        Assert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");

        Login.navigateToDistributorPortal(DistributorName);
        Assert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "The user is unable to land on the Dashboard page.");
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Unable to find the customer Id");
        Customer.clickOnOrderGuideParentChild(customerId);
        Customer.clickOGDropdown();
        Customer.selectNewlyCreatedOrderGuide(orderGuide1);
        Customer.expandMoreOptionsDropdown();
        Customer.clickOnOrderGuideSettings();
        Customer.clickAddItemRestrictionDropDown();
        Customer.clickAddItemRestrictionDropDownOption(AddItemRestrictionDropDownOption);
        OrderGuideSettings.clickOnSave();
        Customer.closeEditor();

        Customer.clickOGDropdown();
        Customer.selectNewlyCreatedOrderGuide(orderGuide2);
        Customer.expandMoreOptionsDropdown();
        Customer.clickOnOrderGuideSettings();
        Customer.clickAddItemRestrictionDropDown();
        Customer.clickAddItemRestrictionDropDownOption(AddItemRestrictionDropDownOption);
        OrderGuideSettings.clickOnSave();
        Customer.closeEditor();

        Login.navigateToLoginAs();
        Login.logInToOperatorAsWhiteLabel(OperatorName);
        softAssert.assertFalse(Dashboard.isUserNavigatedToDashboardWhiteLabel(),"login error");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }

}
