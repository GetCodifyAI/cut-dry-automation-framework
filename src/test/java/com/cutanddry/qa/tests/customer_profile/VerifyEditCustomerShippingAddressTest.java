package com.cutanddry.qa.tests.customer_profile;

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

public class VerifyEditCustomerShippingAddressTest extends TestBase{
    static User user;
    String editShipAddStreet = "Test street";
    String editShipAddCity = "Test city";
    String editShipAddState = "Test state";
    String editShipAddZipCode = "28070";



    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-657")
    public void VerifyEditCustomerShippingAddress() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");
        Dashboard.navigateToCustomers();
        Customer.selectOneCustomer();
        softAssert.assertTrue(Customer.isCustomerNameTxtDisplayed(),"text error");
        Customer.clickOnShippingAddressEdit();
        softAssert.assertTrue(Customer.isEditShippingAddressTextDisplayed(),"text error");
        Customer.editShipAddressStreetName(editShipAddStreet);
        Customer.editShipAddressCityName(editShipAddCity);
        Customer.editShipAddressStateName(editShipAddState);
        Customer.editShipAddressZipCode(editShipAddZipCode);
        Customer.clickOnSaveChangesEditShipAddress();
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result){
        takeScreenshotOnFailure(result);
        closeAllBrowsers();

    }

}
