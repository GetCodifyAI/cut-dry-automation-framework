package com.cutanddry.qa.tests.offline_ordering;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.DistributorOrderData;
import com.cutanddry.qa.data.testdata.OfflineOrderingData;
import com.cutanddry.qa.data.testdata.PayInvoiceData;
import com.cutanddry.qa.data.testdata.PriceData;
import com.cutanddry.qa.functions.*;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyUserCanSwitchOfflineModeFromTheMoreOptionsOnTheOGWhenTheDeviceHasNetworkConnectivityTest extends TestBase {
    SoftAssert softAssert;
    static User user;
    String DistributorName ="47837013 - Brandon IFC Cut+Dry Agent - Independent Foods Co";
    static String customerId = DistributorOrderData.RESTAURANT_TEST_HAYES_ID;
    static String CompanyName = OfflineOrderingData.SUPPLIER_NAME;


    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }


    @Test(groups = "DOT-TC-1242")
    public void VerifyUserCanSwitchOfflineModeFromTheMoreOptionsOnTheOGWhenTheDeviceHasNetworkConnectivity() throws InterruptedException {

        softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");
        Login.navigateToInternalToolsPage();
        InternalTools.navigateToConfigureSupplier();
        InternalTools.clickOnInternalToolCompanyEditDetails(CompanyName);
        InternalTools.navigateToOrderingSettingsTab();
        InternalTools.clickOfflineOrderingToggle(true);
        InternalTools.clickSave();

        Login.navigateToDistributorPortal(DistributorName);
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "The user is unable to land on the Dashboard page.");
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        Assert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Unable to find the customer Id");
        Customer.clickOnOrderGuide(customerId);

        Customer.expandMoreOptionsDropdown();
        Customer.clickSwitchToOfflineMode();
        softAssert.assertTrue(Customer.isOfflineModePopUpDisplay(),"offline mode pop up error");
        Customer.clickActiveOfflineMode();
        softAssert.assertTrue(Customer.isHangTightPopUpDisplay(),"hang tight pop up not display");
        softAssert.assertAll();
    }
    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsersAtOnce();
    }
}
