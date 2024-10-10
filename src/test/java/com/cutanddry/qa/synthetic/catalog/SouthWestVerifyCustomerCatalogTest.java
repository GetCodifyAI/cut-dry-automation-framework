package com.cutanddry.qa.synthetic.catalog;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.Customer;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SouthWestVerifyCustomerCatalogTest extends TestBase {
    static User user;
    static String DP = "Southwest Traders";
    static String customerId = "113956";

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test
    public void SouthWestVerifyCustomerCatalog() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.logIntoRestaurantProd(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");
        Login.navigateToDistributorPortalProd(DP);
        Dashboard.navigateToCustomers();
        softAssert.assertTrue(Customer.isNavigatedToCustomerPage(),"navigation error");
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId),"search error");
        Customer.clickOnOrderGuide(customerId);
        softAssert.assertTrue(Customer.isNavigatedToOrderGuide(),"order guide navigation error");
        Customer.goToCatalog();
        softAssert.assertTrue(Customer.isNavigatedToCatalog(),"catalog navigation error");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
