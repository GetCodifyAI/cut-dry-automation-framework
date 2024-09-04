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

public class VerifyMoreFromThisBrandTest extends TestBase {
    static User user;
    static String customerId = "16579";
    static String itemCode = "17859";

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }
    @Test(groups = "DOT-TC-41")
    public void verifyMoreFromThisBrand() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        Dashboard.isUserNavigatedToDashboard();
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");
        Dashboard.navigateToBoost();
        softAssert.assertTrue(Boost.isUserNavigatedToBoost(),"navigate to boost error");
        Boost.clickSuggestiveSales();
        softAssert.assertTrue(Boost.isSuggestiveTabDisplayed(),"navigate to suggestive sales error");
        Boost.clickMoreFromThisConfig();
        softAssert.assertTrue(Boost.isMoreFromThisPopupDisplayed(),"more from this brand popup error");
        Boost.toggleCarouselDisplayStatus(); // assuming default inactive
        Boost.clickClose();
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId),"search error");
        Customer.clickOnOrderGuide(customerId);
        Customer.goToCatalog();
        Customer.searchItemOnCatalog(itemCode);
        Customer.selectSearchedCatalogItem(itemCode);
        softAssert.assertTrue(Customer.isSelectedItemDisplayed(),"navigation error");
        softAssert.assertTrue(Customer.isMoreFromThisBrandDisplayed(), "more from this brand missing error");
        Dashboard.navigateToBoost();
        softAssert.assertTrue(Boost.isUserNavigatedToBoost(),"navigate to boost error");
        Boost.clickSuggestiveSales();
        softAssert.assertTrue(Boost.isSuggestiveTabDisplayed(),"navigate to suggestive sales error");
        Boost.clickMoreFromThisConfig();
        softAssert.assertTrue(Boost.isMoreFromThisPopupDisplayed(),"more from this brand popup error");
        Boost.toggleCarouselDisplayStatus();
        softAssert.assertAll();
    }
    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
