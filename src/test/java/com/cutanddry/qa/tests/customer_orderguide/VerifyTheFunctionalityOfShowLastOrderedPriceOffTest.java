package com.cutanddry.qa.tests.customer_orderguide;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.Customer;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.InternalTools;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyTheFunctionalityOfShowLastOrderedPriceOffTest extends TestBase {
    static User user;
    String DistributerName ="47837013 - Brandon IFC Cut+Dry Agent - Independent Foods Co";
    static String customerId = "16579";
    static String itemName = "bacon, uncured maple, e&p, food service";
    static String itemId ="24857";


    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-819")
    public void VerifyTheFunctionalityOfShowLastOrderedPriceOff() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");
        Login.navigateToInternalToolsPage();
        InternalTools.navigateToConfigureSupplier();
        InternalTools.navigateToIndependentCompEditDetails();
        InternalTools.navigateToOrderingSettingsTab();
        InternalTools.TurnOffLastOrderedPoundPrice();
        InternalTools.SaveLastOrderedPoundPriceTurnOn();
        Login.switchIntoNewTab();
        Login.navigateToDistributorPortal(DistributerName);
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"navigation error");
        Dashboard.isUserNavigatedToDashboard();
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId),"search error");
        Customer.clickOnOrderGuide(customerId);
        Customer.searchItemOnOrderGuide(itemId);
        softAssert.assertTrue(Customer.isLastOrderedPriceNotSameAfterToggleOff(),"error");
        softAssert.assertAll();
    }

   @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsersAtOnce();
    }

}
