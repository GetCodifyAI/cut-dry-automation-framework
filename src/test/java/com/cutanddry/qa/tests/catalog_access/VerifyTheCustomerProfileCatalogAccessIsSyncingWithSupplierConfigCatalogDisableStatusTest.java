package com.cutanddry.qa.tests.catalog_access;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.Customer;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.InternalTools;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyTheCustomerProfileCatalogAccessIsSyncingWithSupplierConfigCatalogDisableStatusTest extends TestBase {
    static User user;
    String DistributorName ="47837013 - Brandon IFC Cut+Dry Agent - Independent Foods Co";
    String ManualDisableCatalogOption = "Selected Operators (via Manual Selection)";
    static String restaurantName = "Kafe Layers #4 Test";
    static String customerId = "45897";

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-399")
    public void VerifyTheCustomerProfileCatalogAccessIsSyncingWithSupplierConfigCatalogDisableStatus() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();

        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        Assert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");

        Login.navigateToDistributorPortal(DistributorName);
        Assert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "The user is unable to land on the Dashboard page.");
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Unable to find the customer Id");
        Customer.SelectCustomer(customerId);
        Customer.disableCatalogAccess();
        softAssert.assertTrue(Customer.catalogAccessDisabled(),"Error in catalog access disable displaying");

        Login.navigateToInternalToolsPage();
        InternalTools.navigateToConfigureSupplier();
        InternalTools.navigateToIndependentCompEditDetails();
        InternalTools.navigateToCatalogSettingsTab();
        InternalTools.selectManualSelectionFromDropdown(ManualDisableCatalogOption);
        softAssert.assertTrue(InternalTools.isCatalogDisableRestaurantDisplay(restaurantName),"disable restaurant display error");
        InternalTools.deleteRestaurantInCatalogDisable(restaurantName);
        softAssert.assertFalse(InternalTools.isCatalogDisableRestaurantDisplay(restaurantName),"disable restaurant display not deleted");
        InternalTools.catalogSettingsSave();
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }

}
