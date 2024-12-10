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

public class VerifyAddSalespersonTest extends TestBase {
    static User user;

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-658")
    public void VerifyValidateAddSalesperson() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "login error");
        Dashboard.navigateToCustomers();
        Customer.selectOneCustomer();
        softAssert.assertTrue(Customer.isCustomerNameTxtDisplayed(), "text error");
        Customer.clickOnEditSalespersonIcon();
        softAssert.assertTrue(Customer.isAssignSalespersonTextDisplayed(), "text error");
        Customer.clickOnAssignSalespersonDropdown();
        Customer.selectOneAssignSalesperson();
        softAssert.assertTrue(Customer.isAssignedSalespersonNameDisplayed(), "selected salesperson not added");
        Customer.clickOnAssignSalespersonSave();
        softAssert.assertTrue(Customer.isCustomerNameTxtDisplayed(), "text error");
        softAssert.assertTrue(Customer.isAddedSalespersonNameDisplayed(), "text error");
        softAssert.assertAll();
    }

   @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
