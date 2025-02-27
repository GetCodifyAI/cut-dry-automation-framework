package com.cutanddry.qa.tests.customer_orderguide;

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

public class VerifyTheFunctionalityOfEditMarginOfAnItemTest extends TestBase {
    static User user;
    static String customerId = "16579";
    static String itemName = "Carrot";

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-710")
    public void VerifyTheFunctionalityOfEditMarginOfAnItem() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        Dashboard.isUserNavigatedToDashboard();
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId),"search error");
        Customer.clickOnOrderGuide(customerId);
        Customer.searchItemOnOrderGuide(itemName);
        Customer.editMargin();
        softAssert.assertTrue(Customer.isMarginValuePopupDisplayed(),"popup error");
        Customer.enterMarginValue("8.05");
        Customer.updateMarginValues();
        softAssert.assertTrue(Customer.isItemAdded("$8.05"),"update error");
        Customer.editMargin();
        softAssert.assertTrue(Customer.isMarginValuePopupDisplayed(),"popup error");
        Customer.resetMarginValues();
        Customer.updateMarginValues();
        softAssert.assertFalse(Customer.isItemAdded("$8.05 "),"reset error");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }

}
