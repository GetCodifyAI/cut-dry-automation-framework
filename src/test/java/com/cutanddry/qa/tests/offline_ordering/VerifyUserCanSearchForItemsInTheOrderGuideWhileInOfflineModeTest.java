package com.cutanddry.qa.tests.offline_ordering;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.DistributorOrderData;
import com.cutanddry.qa.data.testdata.OfflineOrderingData;
import com.cutanddry.qa.functions.*;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyUserCanSearchForItemsInTheOrderGuideWhileInOfflineModeTest extends TestBase {
    SoftAssert softAssert;
    static User user;
    static String DistributorName = OfflineOrderingData.DISTRIBUTOR_NAME;
    static String customerId = DistributorOrderData.RESTAURANT_TEST_HAYES_ID;
    static String CompanyName = OfflineOrderingData.SUPPLIER_NAME;
    static String itemName ;
    static String itemCode = "5800";

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }


    @Test(groups = "DOT-TC-1246")
    public void VerifyUserCanSearchForItemsInTheOrderGuideWhileInOfflineMode() throws InterruptedException {

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
        Thread.sleep(3000);

        itemName = Customer.getItemNameFirstRow();
        Customer.searchItemOnOrderGuide(itemName);
        softAssert.assertEquals(Customer.getItemNameFirstRow(), itemName, "search item display error");
        Customer.searchItemOnOrderGuide(itemCode);
        softAssert.assertTrue(Customer.searchItemNotFound(),"not found search item");
        softAssert.assertAll();
    }
    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsersAtOnce();
    }
}
