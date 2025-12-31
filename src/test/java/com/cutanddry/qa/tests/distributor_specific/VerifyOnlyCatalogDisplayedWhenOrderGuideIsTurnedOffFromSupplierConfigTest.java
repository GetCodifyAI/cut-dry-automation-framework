package com.cutanddry.qa.tests.distributor_specific;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.CustomerData;
import com.cutanddry.qa.functions.*;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyOnlyCatalogDisplayedWhenOrderGuideIsTurnedOffFromSupplierConfigTest extends TestBase {
    static User user;
    static String distributorIFC = CustomerData.DISTRIBUTOR_NAME_IFC;
    static String CustomerCode = CustomerData.CUSTOMER_CODE3;
    static String DPName = "Independent Foods Co";
    static String searchItemCode = "01762";
    static String itemName = "Broccolini 18 CT";

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-1474")
    public void VerifyOnlyCatalogDisplayedWhenOrderGuideIsTurnedOffFromSupplierConfig() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(), "login error");
        Login.navigateToInternalToolsPage();
        InternalTools.navigateToConfigureSupplier();
        InternalTools.clickOnInternalToolCompanyEditDetails(DPName);
        InternalTools.navigateToOrderingSettingsTab();
        InternalTools.clickCatalogOnlyOrderFlowToggle(true);
        InternalTools.clickSave();
        softAssert.assertTrue(InternalTools.isSuccessPopUpDisplayed(), "change not save");
        InternalTools.clickOKOnSucessOverlay();

        Login.navigateToDistributorPortal(distributorIFC);
        Assert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "The user is unable to land on the Dashboard page.");
        Customer.clickMenu();
        Dashboard.navigateToCustomers();
        softAssert.assertTrue(Customer.isCustomersTextDisplayed(), "customer section not display");
        Customer.searchCustomerByCode(CustomerCode);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(CustomerCode), "customer not found");
        softAssert.assertTrue(Customer.isPlaceOrderButtonDisplay(CustomerCode), "place order button display");
        Customer.SelectCustomer(CustomerCode);
        softAssert.assertTrue(Customer.isPlaceOrderButtonVisibleInCustomerProfile(), "place order button not display in customer profile");
        Customer.clickPlaceOrderButtonInCustomerProfile();
        Thread.sleep(4000);
        softAssert.assertFalse(Customer.isCustomerOrderGuideDisplayed(), "order guide displayed");
        softAssert.assertTrue(Customer.isCatalogDisplayed(), "catalog text not displayed");

        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
