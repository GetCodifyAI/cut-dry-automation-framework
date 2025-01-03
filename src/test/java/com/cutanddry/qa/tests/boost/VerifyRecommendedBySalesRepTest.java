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

public class VerifyRecommendedBySalesRepTest extends TestBase {
    static User user;
    static String customerId = "16579";
    static String itemCode = "00475";
    static String salesRep = "Steve O";

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }
    @Test(groups = "DOT-TC-39")
    public void verifyRecommendedBySalesRep() throws InterruptedException {
        String itemName;
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        Dashboard.isUserNavigatedToDashboard();
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");
        Dashboard.navigateToBoost();
        softAssert.assertTrue(Boost.isUserNavigatedToBoost(),"navigate to boost error");
        Boost.clickSuggestiveSales();
        softAssert.assertTrue(Boost.isSuggestiveTabDisplayed(),"navigate to suggestive sales error");
        Boost.clickRecommendBySalesRepConfig();
        softAssert.assertTrue(Boost.isRecommendBySalesRepPopupDisplayed(),"recommend by sales rep popup error");
        Boost.clickSalesRepConfig(salesRep);
        softAssert.assertTrue(Boost.isSalesRepConfigPopupDisplayed(),"sales rep config popup error");
        Boost.clickAddItems();
        Boost.addItem(itemCode);
        softAssert.assertTrue(Boost.isItemAdded(itemCode),"item adding error");
        Boost.clickClose();
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId),"search error");
        Customer.clickOnOrderGuide(customerId);
        itemName = Customer.getItemNameFirstRow();
        Customer.increaseFirstRowQtyByOne();
        Customer.checkoutItems();
        softAssert.assertEquals(Customer.getItemNameFirstRow(),itemName,"item mismatch");
        softAssert.assertTrue(Customer.isRecommendedBySalesRepDisplayed(itemCode),"recommended by sales rep item missing error");
        Dashboard.navigateToBoost();
        softAssert.assertTrue(Boost.isUserNavigatedToBoost(),"navigate to boost error");
        Boost.clickSuggestiveSales();
        softAssert.assertTrue(Boost.isSuggestiveTabDisplayed(),"navigate to suggestive sales error");
        Boost.clickRecommendBySalesRepConfig();
        softAssert.assertTrue(Boost.isRecommendBySalesRepPopupDisplayed(),"recommend by sales rep popup error");
        Boost.clickSalesRepConfig(salesRep);
        softAssert.assertTrue(Boost.isSalesRepConfigPopupDisplayed(),"sales rep config popup error");
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
