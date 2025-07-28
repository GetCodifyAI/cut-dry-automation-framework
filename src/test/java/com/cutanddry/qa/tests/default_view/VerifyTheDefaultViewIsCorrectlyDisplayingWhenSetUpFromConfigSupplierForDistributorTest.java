package com.cutanddry.qa.tests.default_view;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.CustomerData;
import com.cutanddry.qa.data.testdata.PurchaseHistoryData;
import com.cutanddry.qa.functions.*;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyTheDefaultViewIsCorrectlyDisplayingWhenSetUpFromConfigSupplierForDistributorTest extends TestBase {
    static User user;
    static String DP = PurchaseHistoryData.SUPPLIER_NAME;
    static String customerId = PurchaseHistoryData.CUSTOMER_ID;
    SoftAssert softAssert;

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-1544")
    public void verifyTheDefaultViewIsCorrectlyDisplayingWhenSetUpFromConfigSupplierForDistributor() throws InterruptedException {
        softAssert = new SoftAssert();

        // Order Guide
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        Assert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");

        // Enable Quick Add and Simple List view
        Login.navigateToConfigSupplier();
        Assert.assertTrue(ConfigSupplier.isUserNavigatedToConfigSupplier(),"navigation error");
        ConfigSupplier.clickOnEditDetails(DP);
        ConfigSupplier.clickOnOrderSettings();
        ConfigSupplier.enableQuickAdd();
        ConfigSupplier.enableSimpleList();

        // Order Guide
        Login.navigateToConfigSupplier();
        Assert.assertTrue(ConfigSupplier.isUserNavigatedToConfigSupplier(),"navigation error");
        ConfigSupplier.clickOnEditDetails(DP);
        ConfigSupplier.clickOnOrderSettings();
        ConfigSupplier.enableDefaultViewPortalAsOrderGuide();

        Login.navigateToDistributorPortal(DP);
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        Assert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId),"search error");
        Customer.clickOnOrderGuide(customerId);
        softAssert.assertTrue(Customer.isOrderGuideTextDisplay(),"Order Guide text is not displayed as expected when default view is set from Config Supplier.");

        // Catalog
        Login.navigateToConfigSupplier();
        Assert.assertTrue(ConfigSupplier.isUserNavigatedToConfigSupplier(),"navigation error");
        ConfigSupplier.clickOnEditDetails(DP);
        ConfigSupplier.clickOnOrderSettings();
        ConfigSupplier.enableDefaultViewPortalAsCatalog();

        Login.navigateToDistributorPortal(DP);
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        Assert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId),"search error");
        Customer.clickOnOrderGuide(customerId);
        softAssert.assertTrue(Customer.isUserNavigatedToCatalog(),"Catalog view is not displayed as expected when default view is set to Catalog from Config Supplier.");

        // Quick Add
        Login.navigateToConfigSupplier();
        Assert.assertTrue(ConfigSupplier.isUserNavigatedToConfigSupplier(),"navigation error");
        ConfigSupplier.clickOnEditDetails(DP);
        ConfigSupplier.clickOnOrderSettings();
        ConfigSupplier.enableDefaultViewPortalAsQuickAdd();

        Login.navigateToDistributorPortal(DP);
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        Assert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId),"search error");
        Customer.clickOnOrderGuide(customerId);
        softAssert.assertTrue(Customer.isQuickAddViewDisplay(),"Quick Add view is not displayed as expected when default view is set to Quick Add from Config Supplier.");

        // Simple List
        Login.navigateToConfigSupplier();
        Assert.assertTrue(ConfigSupplier.isUserNavigatedToConfigSupplier(),"navigation error");
        ConfigSupplier.clickOnEditDetails(DP);
        ConfigSupplier.clickOnOrderSettings();
        ConfigSupplier.enableDefaultViewPortalAsSimpleList();

        Login.navigateToDistributorPortal(DP);
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        Assert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId),"search error");
        Customer.clickOnOrderGuide(customerId);
        softAssert.assertTrue(Customer.isSimpleListViewTextDisplay(),"Simple List view is not displayed as expected when default view is set to Simple List from Config Supplier.");

//        TODO: Flow is not implemented in dev end
        // Scan To Order
       /* Login.navigateToConfigSupplier();
        Assert.assertTrue(ConfigSupplier.isUserNavigatedToConfigSupplier(),"navigation error");
        ConfigSupplier.clickOnEditDetails(DP);
        ConfigSupplier.clickOnOrderSettings();
        ConfigSupplier.enableDefaultViewPortalAsScanToOrder();

        Login.navigateToDistributorPortal(DP);
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        Assert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId),"search error");
        Customer.clickOnOrderGuide(customerId);
        softAssert.assertTrue(Customer.isUserNavigatedToCatalog(),"Scan To Order view is not displayed as expected when default view is set to Scan To Order from Config Supplier.");

*/

        Login.navigateToConfigSupplier();
        Assert.assertTrue(ConfigSupplier.isUserNavigatedToConfigSupplier(),"navigation error");
        ConfigSupplier.clickOnEditDetails(DP);
        ConfigSupplier.clickOnOrderSettings();
        ConfigSupplier.enableDefaultViewPortalAsOrderGuide();

        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
