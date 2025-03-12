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

public class VerifyCompareSimilarItemsTest extends TestBase{
    static User user;
    static String customerId = "16579";
    static String itemCode = "00529";

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }
    @Test(groups = "DOT-TC-37")
    public void verifyCompareSimilarItems() throws InterruptedException {
        boolean inactiveState;
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        Dashboard.isUserNavigatedToDashboard();
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");
        Dashboard.navigateToBoost();
        softAssert.assertTrue(Boost.isUserNavigatedToBoost(),"navigate to boost error");
        Boost.clickSuggestiveSales();
        softAssert.assertTrue(Boost.isSuggestiveTabDisplayed(),"navigate to suggestive sales error");
        inactiveState = Boost.checkInactiveState("Compare Similar Items");
        Boost.clickCompareSimilarItemsConfig();
        softAssert.assertTrue(Boost.isCompareSimilarPopupDisplayed(),"compare similar items popup error");
        Boost.toggleOnCarouselDisplayStatus(inactiveState);
        Boost.clickClose();
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId),"search error");
        Customer.clickOnOrderGuide(customerId);
        Customer.goToCatalog();
        Customer.searchItemOnCatalog(itemCode);
        Customer.selectSearchedCatalogItemStable(itemCode);
        softAssert.assertTrue(Customer.isSelectedItemDisplayed(),"navigation error");
        softAssert.assertTrue(Customer.isCompareSimilarItemsDisplayed(),"similar items missing error");
        Dashboard.navigateToBoost();
        softAssert.assertTrue(Boost.isUserNavigatedToBoost(),"navigate to boost error");
        Boost.clickSuggestiveSales();
        softAssert.assertTrue(Boost.isSuggestiveTabDisplayed(),"navigate to suggestive sales error");
        Boost.clickCompareSimilarItemsConfig();
        softAssert.assertTrue(Boost.isCompareSimilarPopupDisplayed(),"compare similar items popup error");
        Boost.toggleOffCarouselDisplayStatus();
        softAssert.assertAll();
    }
    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
