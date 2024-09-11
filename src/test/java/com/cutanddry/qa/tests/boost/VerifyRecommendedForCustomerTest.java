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

public class VerifyRecommendedForCustomerTest extends TestBase {
    static User user;
    static String customerId = "16579";
    static String itemCode = "00475";

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }
    @Test(groups = "DOT-TC-38")
    public void verifyRecommendedForCustomer() throws InterruptedException {
        boolean inactiveState;
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        Dashboard.isUserNavigatedToDashboard();
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");
        Dashboard.navigateToBoost();
        softAssert.assertTrue(Boost.isUserNavigatedToBoost(),"navigate to boost error");
        Boost.clickSuggestiveSales();
        softAssert.assertTrue(Boost.isSuggestiveTabDisplayed(),"navigate to suggestive sales error");
        inactiveState = Boost.checkInactiveState("Recommended for Customer");
        Boost.clickRecommendForCustomerConfig();
        softAssert.assertTrue(Boost.isRecommendForCustomerPopupDisplayed(),"recommend for customer popup error");
        Boost.toggleOnCarouselDisplayStatus(inactiveState);
        Boost.clickAddItems();
        Boost.addItem(itemCode);
        softAssert.assertTrue(Boost.isItemAdded(itemCode),"item adding error");
        Boost.clickClose();
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId),"search error");
        Customer.clickOnOrderGuide(customerId);
        Customer.increaseFirstRowQtyByOne();
        Customer.checkoutItems();
        softAssert.assertTrue(Customer.isRecommendedForYouItemDisplayed(itemCode),"recommended for you item missing error");
        Dashboard.navigateToBoost();
        softAssert.assertTrue(Boost.isUserNavigatedToBoost(),"navigate to boost error");
        Boost.clickSuggestiveSales();
        softAssert.assertTrue(Boost.isSuggestiveTabDisplayed(),"navigate to suggestive sales error");
        Boost.clickRecommendForCustomerConfig();
        softAssert.assertTrue(Boost.isRecommendForCustomerPopupDisplayed(),"recommend for customer popup error");
        Boost.removeItem(itemCode);
        softAssert.assertFalse(Boost.isItemInCarouselPreview(itemCode),"item remove error");
        softAssert.assertAll();
    }
    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
