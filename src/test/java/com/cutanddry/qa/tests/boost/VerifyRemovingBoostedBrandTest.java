package com.cutanddry.qa.tests.boost;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.Boost;
import com.cutanddry.qa.functions.Customer;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyRemovingBoostedBrandTest extends TestBase {
    static User user;
    static String customerId = "16579";
    static String brandName = "Fogline Farm";
    static String searchItem = "Chicken";

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-2503")
    public void verifyRemovingBoostedBrand() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "Login error - user not navigated to dashboard");
        
        Dashboard.navigateToBoost();
        softAssert.assertTrue(Boost.isUserNavigatedToBoost(), "Navigate to boost error");
        
        Boost.clickBrandBoostTab();
        softAssert.assertTrue(Boost.isBrandBoostTabDisplayed(), "Brand Boost tab not displayed");
        
        Boost.removeBrand(brandName);
        
        softAssert.assertTrue(Boost.isBrandRemoved(brandName), "Brand not removed from selected brands");
        
        Boost.saveBrandBoost();
        
        Dashboard.navigateToCustomers();
        softAssert.assertTrue(Customer.isCustomersTextDisplayed(), "Navigate to customers error");
        
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Customer search error");
        Customer.clickOnOrderGuide(customerId);
        
        Customer.goToCatalog();
        
        Customer.searchItemOnOrderGuide(searchItem);
        
        String firstItemName = Customer.getItemNameFirstRow();
        System.out.println("First item after removing brand boost: " + firstItemName);
        
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
