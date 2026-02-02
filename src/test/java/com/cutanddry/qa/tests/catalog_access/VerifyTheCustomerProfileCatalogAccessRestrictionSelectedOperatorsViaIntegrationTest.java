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

public class VerifyTheCustomerProfileCatalogAccessRestrictionSelectedOperatorsViaIntegrationTest extends TestBase {
    static User user;
    String DistributorName ="Independent Foods Co";
    String ManualDisableCatalogOption = "Selected Operators (via Manual Selection)";
    String selectViaIntegrationOption = "Selected Operators (via Integration)";
    static String customerId = "45897";
    static String customerId2 = "16579";
    static String restaurantName = "Kafe Layers #4 Test";

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-1287")
    public void VerifyTheCustomerProfileCatalogAccessRestrictionSelectedOperatorsViaIntegration() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();

        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        Assert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");

        Login.navigateToInternalToolsPage();
        InternalTools.navigateToConfigureSupplier();
        InternalTools.navigateToIndependentCompEditDetails();
        InternalTools.navigateToCatalogSettingsTab();
        InternalTools.selectManualSelectionFromDropdown(ManualDisableCatalogOption);
        InternalTools.addCustomerToCatalogDisable(restaurantName);
        InternalTools.catalogSettingsSave();
        InternalTools.selectManualSelectionFromDropdown(selectViaIntegrationOption);
        InternalTools.catalogSettingsSave();

        Login.navigateToDistributorPortal(DistributorName);
        Assert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "The user is unable to land on the Dashboard page.");
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Unable to find the customer Id");
        Customer.SelectCustomer(customerId);
        softAssert.assertTrue(Customer.catalogAccessDisabled(),"Error in catalog access disable displaying");
        softAssert.assertFalse(Customer.isEditCatalogAccessDisplay(),"edit catalog access not display");

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId2);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId2), "Unable to find the customer Id");
        Customer.SelectCustomer(customerId2);
        softAssert.assertTrue(Customer.catalogAccessEnabled(),"Error in catalog access disable displaying");
        softAssert.assertFalse(Customer.isEditCatalogAccessDisplay(),"edit catalog access not display");

        Login.navigateToInternalToolsPage();
        InternalTools.navigateToConfigureSupplier();
        InternalTools.navigateToIndependentCompEditDetails();
        InternalTools.navigateToCatalogSettingsTab();
        InternalTools.selectManualSelectionFromDropdown(ManualDisableCatalogOption);
        InternalTools.deleteRestaurantInCatalogDisable(restaurantName);
        InternalTools.catalogSettingsSave();
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }

}
