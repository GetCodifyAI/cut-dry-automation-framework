package com.cutanddry.qa.tests.order_guide;

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

public class VerifyTheViewMultipleOrderGuidesUnderOrderGuideAndMoveBetweenDifferentOrderGuides extends TestBase {
    static User user;
    static String itemName = "Artichoke -24ct";
    static String itemCode = "01700";
    static String customerId = "16579";
    static String orderGuideType = "Test_Guide_01";
    static String defaultOrderGuideType = "Test_Automation";

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }
    @Test(groups = "DOT-TC-709")
    public void VerifyTheViewMultipleOrderGuidesUnderOrderGuideAndMoveBetweenDifferentOrderGuides() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        Dashboard.isUserNavigatedToDashboard();
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId),"search error");
        Customer.clickOnOrderGuide(customerId);
        Customer.clickOnItemOrderGuideDropDown(orderGuideType);
        softAssert.assertTrue(Customer.isDisplayedOrderGuideTypeCorrect(orderGuideType), "Displayed Order Guide Type is Incorrect");
        Customer.clickOnItemOrderGuideDropDown(defaultOrderGuideType);
        softAssert.assertTrue(Customer.isDisplayedOrderGuideTypeCorrect(defaultOrderGuideType), "Displayed Order Guide Type is Incorrect");
        softAssert.assertAll();
    }
    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
