package com.cutanddry.qa.tests.customers;

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

public class VerifyTheFilterFromSalespersonTest extends TestBase {
    static User user;
    String SalespersonName = "Amir Ifc";

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-647")
    public void VerifyTheFilterFromSalesperson() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");
        Dashboard.navigateToCustomers();
        softAssert.assertTrue(Customer.isCustomersTextDisplayed(),"customer section not display");
        Customer.clickSalespersonDropDown();
        Customer.clickSalespersonOption();
        softAssert.assertTrue(Customer.isSalespersonNameDisplayed(SalespersonName),"Error in Displaying salesperson filter");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result){
        takeScreenshotOnFailure(result);
        closeAllBrowsers();

    }

}
