package com.cutanddry.qa.tests.credit_request;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.CustomerInvoiceData;
import com.cutanddry.qa.functions.*;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class ValidateFilterCreditRequestFromRequestDateTest extends TestBase {
    static User user;
    String date = "Today";
    String CustomerCode = CustomerInvoiceData.CUSTOMER_CODE;
    String creditStatus = "Submitted";

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-780")
    public void ValidateFilterCreditRequestFromRequestDate() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        ZonedDateTime todayUTC = ZonedDateTime.now(ZoneOffset.UTC);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String todayDate = todayUTC.format(formatter);

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
        Dashboard.navigateToOrders();
        softAssert.assertTrue(Orders.isUserNavigatedToOrder(),"Error navigating to orders page");
//        Orders.clickOnFirstOrder();
        Orders.clickOnFirstOrder(creditStatus);
        Orders.clickCheckIn();
        Orders.clickReportIssue();
        Orders.clickOnFirstRowTableOrderIssues();
        Orders.clickOnFirstOptionDropDownWhatIsWrong();
        Orders.clickOnBtnContinue();
        Orders.clickOnBtnContinue();
        Orders.clickOnBtnContinue();
        Orders.clickBtnSaveCheckIn();
        Dashboard.navigateToCreditRequests();
        CreditRequests.changeRequestDate(date);
        softAssert.assertTrue(CreditRequests.isRequestDateChanged(date),"dropdown error");
        softAssert.assertTrue(CreditRequests.isFilteredRequestCorrect(todayDate),"Error in filtering request dates");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
