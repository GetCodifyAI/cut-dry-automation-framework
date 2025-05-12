package com.cutanddry.qa.tests.catalog_access;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.*;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyCatalogAccessRestrictedViaCustomerProfileTest extends TestBase {
    static User user;
    String DistributerName ="456592422 - QA ONLY : test distributor - QA ONLY : test distributor";
    String ManualDisableCatalogOption = "Selected Operators (via Manual Selection)";
    String CustomerCode = "44939";
    String SupplierName = "QA ONLY : test distributor";
    String RestaurantUserCode = "105556864";

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-401")
    public void VerifyCatalogAccessRestrictedViaCustomerProfile() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();

        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        Assert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");

        //Turning Enable manual catalog access for Customers from internal tools
        Login.navigateToInternalToolsPage();
        InternalTools.navigateToConfigureSupplier();
        InternalTools.navigateToIndependentCompEditDetails();
        InternalTools.navigateToCatalogSettingsTab();
        InternalTools.selectManualSelectionFromDropdown(ManualDisableCatalogOption);
        InternalTools.catalogSettingsSave();

        //Verify Catalog access is enabled for Customer
        Login.navigateToDistributorPortal(DistributerName);
        Assert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"navigation error");
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(CustomerCode);
        Customer.SelectCustomer(CustomerCode);
        softAssert.assertTrue(Customer.catalogAccessEnabled(),"Error in catalog access enable displaying");

        //Log in to restaurant and verify whether the catalog is visible
        Login.navigateToLoginAsPortal(RestaurantUserCode);
        Orders.SelectSupplierFromPlaceOrderPage(SupplierName);
        softAssert.assertTrue(Customer.catalogSectionsDisplayed(),"Error in enabling catalog access");

        //Verify Catalog access is disabled for Customer
        Login.navigateToDistributorPortal(DistributerName);
        Assert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"navigation error");
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(CustomerCode);
        Customer.SelectCustomer(CustomerCode);
        Customer.disableCatalogAccess();
        softAssert.assertTrue(Customer.catalogAccessDisabled(),"Error in catalog access disable displaying");

        //Log in to restaurant and verify whether the catalog is not visible
        Login.navigateToLoginAsPortal(RestaurantUserCode);
        Orders.SelectSupplierFromPlaceOrderPage(SupplierName);
        softAssert.assertFalse(Customer.catalogSectionsDisplayed(),"Error in disabling catalog access");

        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) throws InterruptedException {

        //Reverting back the changes
        Login.navigateToDistributorPortal(DistributerName);
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(CustomerCode);
        Customer.SelectCustomer(CustomerCode);
        Customer.enableCatalogAccess();

        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }

}
