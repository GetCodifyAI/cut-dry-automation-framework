package com.cutanddry.qa.tests.catalog_access;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.Customer;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.functions.Orders;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyCatalogAccessSetToDisableForAllViaCustomerProfileTest extends TestBase {
    static User user;
    String DistributorName ="Independent Foods Co";
    static String customerId = "15285";
    static String salespersonCode = "102460677";
    static String opSideAdminCode = "55743537";
    String SupplierName = "Independent Foods Co";


    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-2912")
    public void VerifyCatalogAccessSetToDisableForAllViaCustomerProfile() throws InterruptedException {

        SoftAssert softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        Assert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(), "login error");
        Login.navigateToDistributorPortal(DistributorName);
        Dashboard.isUserNavigatedToDashboard();
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "login error");

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "search error");
        Customer.clickOnCustomerCode(customerId);
        Customer.disableCatalogAccessForAll();
        softAssert.assertTrue(Customer.CatalogAccessDisabledForAll(),"Error in catalog access enable displaying");

        //Log in to dp portal and verify whether the catalog is visible

        Login.navigateToLoginAsPortal(salespersonCode);
        Dashboard.isUserNavigatedToDashboard();
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "login error");
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "search error");
        Customer.clickOnOrderGuide(customerId);
        softAssert.assertFalse(Customer.catalogSectionsDisplayed(),"Error in enabling catalog access");

        //Log in to restaurant and verify whether the catalog is visible
        Login.navigateToLoginAsPortal(opSideAdminCode);
        Orders.SelectSupplierFromPlaceOrderPage(SupplierName);
        softAssert.assertFalse(Customer.catalogSectionsDisplayed(),"Error in enabling catalog access");

        Login.navigateToDistributorPortal(DistributorName);
        Dashboard.isUserNavigatedToDashboard();
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "login error");

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "search error");
        Customer.clickOnCustomerCode(customerId);
        Customer.enableCatalogAccess();
        softAssert.assertTrue(Customer.catalogAccessEnabled(),"Error in catalog access disable displaying");

        softAssert.assertAll();

    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }


}
