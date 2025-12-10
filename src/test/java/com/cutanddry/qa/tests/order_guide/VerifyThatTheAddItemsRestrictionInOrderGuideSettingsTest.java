package com.cutanddry.qa.tests.order_guide;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.*;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyThatTheAddItemsRestrictionInOrderGuideSettingsTest extends TestBase {
    static User user;
    static String customerId = "653";
    String SupplierName = "Independent Foods Co";
    String RestaurantUserCode = "52068374";
    static String AddItemRestrictionDropDownOption1 = "Don’t allow employees to add new items";
    static String AddItemRestrictionDropDownOption2 = "Allow employees to add items";
    String DistributorName ="46505655 - Kevin - Independent Foods Co";



    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-2542")
    public void VerifyThatTheAddItemsRestrictionInOrderGuideSettings() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");

        Login.navigateToDistributorPortal(DistributorName);
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId),"search error");
        Customer.clickOnOrderGuide(customerId);

        Customer.expandMoreOptionsDropdown();
        Customer.clickOnOrderGuideSettings();
        Customer.clickAddItemRestrictionDropDown();
        Customer.clickAddItemRestrictionDropDownOption(AddItemRestrictionDropDownOption1);
        OrderGuideSettings.clickOnSave();
        Customer.closeEditor();
        Login.closePreviousTab();

        Login.navigateToLoginAs();
        Login.logInToOperator(RestaurantUserCode);
        Orders.SelectSupplierFromPlaceOrder(SupplierName);
        softAssert.assertFalse(Customer.catalogSectionsDisplayed(),"Error in disabling catalog access");

        Login.navigateToDistributorPortal(DistributorName);
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId),"search error");
        Customer.clickOnOrderGuide(customerId);

        Customer.expandMoreOptionsDropdown();
        Customer.clickOnOrderGuideSettings();
        Customer.clickAddItemRestrictionDropDown();
        Customer.clickAddItemRestrictionDropDownOption(AddItemRestrictionDropDownOption2);
        OrderGuideSettings.clickOnSave();
        Customer.closeEditor();
        Login.closePreviousTab();

        Login.navigateToLoginAs();
        Login.logInToOperator(RestaurantUserCode);
        Orders.SelectSupplierFromPlaceOrder(SupplierName);
        softAssert.assertTrue(Customer.catalogSectionsDisplayed(),"Error in disabling catalog access");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
