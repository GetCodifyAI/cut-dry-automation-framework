package com.cutanddry.qa.tests.credit_request;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.CustomerInvoiceData;
import com.cutanddry.qa.functions.*;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.ITestResult;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ValidateTheTimelineInCreditRequestUsingTimestampStatusOrganizationAndUser extends TestBase {
    static User user;
    String CustomerCode = CustomerInvoiceData.CUSTOMER_CODE;
    String creditStatus = "Submitted";

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-785")
    public void ValidateTheTimelineInCreditRequestUsingTimestampStatusOrganizationAndUser() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        Dashboard.isUserNavigatedToDashboard();
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");
        Dashboard.navigateToCustomers();
        softAssert.assertTrue(Customer.isNavigatedToCustomerPage(),"Error navigating to customer page");
        Customer.searchCustomerByCode(CustomerCode);
        Customer.clickOnOrderGuide(CustomerCode);
        Customer.increaseFirstRowQtyByOne();
        Customer.checkoutItems();
        Customer.submitOrder();
        Customer.clickClose();
        Customer.searchCustomerByCode(CustomerCode);
        Customer.clickOnCustomerCode(CustomerCode);
//        Dashboard.navigateToOrders();
//        softAssert.assertTrue(Orders.isUserNavigatedToOrder(),"Error navigating to orders page");
        Customer.clickOnOrdersTab();
        softAssert.assertTrue(Customer.isStandingOrdersDisplayed(),"navigation error");
//        Orders.clickOnFirstOrder();
        Orders.clickOnFirstOrder(creditStatus);
        Orders.clickTimeline();
        String[] timelineData = Orders.getTimelineData();
        Orders.clickCheckIn();
        Orders.clickReportIssue();
        Orders.clickOnFirstRowTableOrderIssues();
        Orders.clickOnFirstOptionDropDownWhatIsWrong();
        Orders.clickOnBtnContinue();
        Orders.clickOnBtnContinue();
        Orders.clickOnBtnContinue();
        Orders.clickBtnSaveCheckIn();
        Dashboard.navigateToCreditRequests();
        softAssert.assertFalse(CreditRequests.isErrorTextDisplayed(),"An error text displayed");
        CreditRequests.clickOnFirstItemOfCreditRequests();
        softAssert.assertTrue(CreditRequests.isNavigatedToOrderSection(), "Error navigating to order section");
        softAssert.assertFalse(CreditRequests.isErrorTextDisplayed(),"An error text displayed");
        CreditRequests.clickOnTimeline();
        softAssert.assertFalse(CreditRequests.isErrorTextDisplayed(),"An error text displayed");
        softAssert.assertTrue(CreditRequests.isTimelineDataSimilar(timelineData[0],timelineData[1],timelineData[2],timelineData[3]), "Timeline, Status, Organization and User are not equal");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
