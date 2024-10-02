package com.cutanddry.qa.tests.order_guide;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.ConfigSupplier;
import com.cutanddry.qa.functions.Customer;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyOutOfStockItemsShowingAndNotOnSuggestiveSalesTest extends TestBase {
    static User user;
    static String DP = "John Gross";
    static String customerId = "10891";
    static String itemCode = "125902";


    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-152")
    public void verifyOutOfStockItemsShowingAndNotOnSuggestiveSales() throws InterruptedException {
        String itemName;
        SoftAssert softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");
        Login.navigateToConfigSupplier();
        softAssert.assertTrue(ConfigSupplier.isUserNavigatedToConfigSupplier(),"navigation error");
        ConfigSupplier.clickOnEditDetails(DP);
        ConfigSupplier.clickOnCatalogSettings();
        ConfigSupplier.toggleOffOGSuggestiveTool();
        ConfigSupplier.clickOnSave();
        //
        Login.navigateToDistributorPortal(DP);
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId),"search error");
        Customer.clickOnOrderGuide(customerId);
        Customer.searchItemOnOrderGuide(itemCode);
        itemName = Customer.getItemNameFirstRow();
        softAssert.assertTrue(Customer.getItemNameFirstRow().contains(itemName),"item mismatch");
        softAssert.assertTrue(Customer.areOutOfStockItemsDisplayed(),"toggle error");
        //
        Login.navigateToConfigSupplier();
        softAssert.assertTrue(ConfigSupplier.isUserNavigatedToConfigSupplier(),"navigation error");
        ConfigSupplier.clickOnEditDetails(DP);
        ConfigSupplier.clickOnCatalogSettings();
        ConfigSupplier.toggleOnOGSuggestiveTool();
        ConfigSupplier.clickOnSave();
        //
        Login.navigateToDistributorPortal(DP);
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId),"search error");
        Customer.clickOnOrderGuide(customerId);
        Customer.searchItemOnOrderGuide(itemCode);
        itemName = Customer.getItemNameFirstRow();
        softAssert.assertTrue(Customer.getItemNameFirstRow().contains(itemName),"item mismatch");
        softAssert.assertFalse(Customer.areOutOfStockItemsDisplayed(),"toggle error");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
