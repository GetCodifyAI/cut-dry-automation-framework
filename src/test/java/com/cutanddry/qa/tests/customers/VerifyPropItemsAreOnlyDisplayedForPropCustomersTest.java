package com.cutanddry.qa.tests.customers;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.Catalog;
import com.cutanddry.qa.functions.Customer;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyPropItemsAreOnlyDisplayedForPropCustomersTest extends TestBase {
    SoftAssert softAssert;
    static User user;
    static String DistributorName = "138629491 - Eshan - What Chefs Want - Rockies";
    static String PropCustomerId = "MOUSTA";
    static String NoncustomerId = "110RES";
    static String propItem = "04352";
    static String propCustomerLoginCode = "198422230";
    static String nonPropCustomerLoginCode = "209163801";


    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-3647")
    public void VerifyPropItemsAreOnlyDisplayedForPropCustomers() throws InterruptedException {
        softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");

        Login.navigateToDistributorPortal(DistributorName);
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "The user is unable to land on the Dashboard page.");
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(PropCustomerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(PropCustomerId), "Unable to find the customer Id");
        Customer.clickOnOrderGuide(PropCustomerId);
        Customer.goToCatalog();
        Customer.searchItemOnCatalog(propItem);
        softAssert.assertTrue(Customer.isItemDisplayedInCatalog(propItem),"Prop Item not displayed in catalog");

        Login.switchIntoNewTab();
        Login.navigateToLoginAsPortal(propCustomerLoginCode);
        Customer.clickOnPlaceOrderWhiteLabel();
        Customer.goToCatalog();
        Customer.searchItemOnCatalog(propItem);
        softAssert.assertTrue(Customer.isItemDisplayedInCatalog(propItem),"Prop Item not displayed in catalog");

        Login.navigateToLoginAsPortal(nonPropCustomerLoginCode);
        Customer.clickOnPlaceOrderWhiteLabel();
        Customer.goToCatalog();
        Customer.searchItemOnCatalog(propItem);
        softAssert.assertFalse(Customer.isItemDisplayedInCatalog(propItem),"Prop Item not displayed in catalog");

        Login.switchBetweenTabsUsingIndex(0);
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(NoncustomerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(NoncustomerId), "Unable to find the customer Id");
        Customer.clickOnOrderGuide(NoncustomerId);
        Customer.goToCatalog();
        Customer.searchItemOnCatalog(propItem);
        softAssert.assertFalse(Customer.isItemDisplayedInCatalog(propItem),"Prop Item not displayed in catalog");

        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsersAtOnce();
    }

}
