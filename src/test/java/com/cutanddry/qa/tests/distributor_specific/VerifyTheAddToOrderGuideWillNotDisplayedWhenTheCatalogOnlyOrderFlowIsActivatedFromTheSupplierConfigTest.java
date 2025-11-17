package com.cutanddry.qa.tests.distributor_specific;

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

public class VerifyTheAddToOrderGuideWillNotDisplayedWhenTheCatalogOnlyOrderFlowIsActivatedFromTheSupplierConfigTest extends TestBase{
    static User user;
    static String distributorTradewell = "445359484 - Michael Gappy - Tradewell Distributors";
    static String CustomerCode = "1792";
    static String DPName = "Tradewell Distributors";
    static String searchItemCode = "00051";
    static String itemName = "101 Ammonia Clear 64OZ";



    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-1497")
    public void VerifyTheAddToOrderGuideWillNotDisplayedWhenTheCatalogOnlyOrderFlowIsActivatedFromTheSupplierConfig() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");
        Login.navigateToInternalToolsPage();
        InternalTools.navigateToConfigureSupplier();
        InternalTools.clickOnInternalToolCompanyEditDetails(DPName);
        InternalTools.navigateToOrderingSettingsTab();
        InternalTools.clickCatalogOnlyOrderFlowToggle(true);
        InternalTools.clickSave();
        softAssert.assertTrue(InternalTools.isSuccessPopUpDisplayed(),"change not save");
        InternalTools.clickOKOnSucessOverlay();

        Login.navigateToDistributorPortal(distributorTradewell);
        Assert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "The user is unable to land on the Dashboard page.");
        Customer.clickMenu();
        Dashboard.navigateToCustomers();
        softAssert.assertTrue(Customer.isCustomersTextDisplayed(),"customer section not display");
        Customer.searchCustomerByCode(CustomerCode);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(CustomerCode),"customer not found");
        softAssert.assertTrue(Customer.isPlaceOrderButtonDisplay(CustomerCode),"place order button display");
        Customer.SelectCustomer(CustomerCode);
        softAssert.assertTrue(Customer.isPlaceOrderButtonVisibleInCustomerProfile(),"place order button not display in customer profile");
        Customer.clickPlaceOrderButtonInCustomerProfile();
        Thread.sleep(4000);
        softAssert.assertFalse(Customer.isCustomerOrderGuideDisplayed(),"order guide displayed");
        softAssert.assertTrue(Customer.isCatalogDisplayed(),"catalog text not displayed");
        softAssert.assertFalse(Customer.isAddToOrderGuideHartIconDisplay(),"add to OG hart icon display");

        Customer.searchItemOnCatalog(searchItemCode);
        Thread.sleep(4000);
        Customer.clickOnProduct(itemName);
        softAssert.assertTrue(Customer.isProductDetailsDisplayed(),"The user is unable to land on the Product Details page.");
        softAssert.assertFalse(Customer.isAddToOrderGuideHartIconDisplay(),"add to OG hart icon display");
        softAssert.assertAll();
    }

   @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
