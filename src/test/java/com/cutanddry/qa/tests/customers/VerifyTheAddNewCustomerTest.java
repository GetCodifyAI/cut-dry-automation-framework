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

public class VerifyTheAddNewCustomerTest extends TestBase {
    static User user;
    static String customerName = "Nova07";
    static String city = "Chicago";


    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-649")
    public void VerifyTheAddNewCustomer() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");
        Dashboard.navigateToCustomers();
        softAssert.assertTrue(Customer.isCustomersTextDisplayed(),"customer section not display");
        Customer.clickManageCustomers();
        Customer.clickAddNewCustomer();
        softAssert.assertTrue(Customer.isAddNewCustomerPopUpDisplayed(),"Add new customer pop up not display");
        Customer.typeCustomerName(customerName);
        Customer.clickContinue();
        Customer.typeCustomerCity(city);
        Customer.clickContinue();
        Customer.clickCreateCustomer();
        softAssert.assertTrue(Customer.isCreatedCustomerPopUpDisplayed(),"created customer pop up not display");
        Customer.clickClosePopUp();
        Customer.searchCustomerByName(customerName);
        softAssert.assertTrue(Customer.isNewCustomerDisplayed(customerName),"new customer not display");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result){
        takeScreenshotOnFailure(result);
        closeAllBrowsers();

    }

}
