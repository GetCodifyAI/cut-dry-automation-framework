package com.cutanddry.qa.tests.distributor_specific;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.DistributorSpecificData;
import com.cutanddry.qa.functions.Customer;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyGenericAccountHardHoldMessageTest extends TestBase {
    static User user;
    static String customerId = "25553";
    static String itemName;
    static String hardHoldMessage = "Your account is on hold by your supplier. Your order could not be submitted, but has been saved as a Draft. Please contact your supplier about your account status and resubmit your order.";

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-471")
    public void VerifyGenericAccountHardHoldMessage() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        Assert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "The user is unable to land on the Dashboard page.");

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId),"search error");
        Customer.clickOnCustomerCode(customerId);
        Customer.clickOnEditAccHolds();
        Customer.clickOnAccDropdown();
        Customer.clickOnHardHold();
        Customer.clickOnSave();
        softAssert.assertTrue(Customer.isHardHoldSelected(),"acc select error");

        Customer.clickOnBack();
        Customer.clickOnOrderGuide(customerId);
        itemName = Customer.getItemNameFirstRow();
        Customer.increaseFirstRowQtyByOne();
        Customer.clickCheckOutPDP();

        softAssert.assertEquals(Customer.getItemNameFirstRow(),itemName,"item mismatch");
        Customer.submitOrder();
        softAssert.assertTrue(Customer.isHardHoldPopupMessageDisplayed(hardHoldMessage),"hard hold set error");
        Customer.clickOK();
        softAssert.assertTrue(Customer.isReviewOrderTextDisplayed(),"order hard hold error");

        //revert Hard hold
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId),"search error");
        Customer.clickOnCustomerCode(customerId);
        Customer.clickOnEditAccHolds();
        Customer.clickOnAccDropdown();
        Customer.clickOnNone();
        Customer.clickOnSave();
        softAssert.assertTrue(Customer.isRemoveHoldPopupDisplayed(),"remove hard hold popup error");
        Customer.clickOnYes();
        softAssert.assertTrue(Customer.isNoneSelected(),"acc none select error");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
