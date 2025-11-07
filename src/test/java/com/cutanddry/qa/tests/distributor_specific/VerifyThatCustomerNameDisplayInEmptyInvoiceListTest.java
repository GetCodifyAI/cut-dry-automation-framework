package com.cutanddry.qa.tests.distributor_specific;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.CustomerInvoiceData;
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

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class VerifyThatCustomerNameDisplayInEmptyInvoiceListTest extends TestBase {
    static User user;
    static String DP = "106701125 - Cut+dry Agent - Cream Co. Meats";
    static String customerId = "CU11177 Azasu Kitchen";
    static String emptyStateMessage ="Could not find invoices for Azasu Kitchen (CU11177 Azasu Kitchen) - Pacifica.";
    String CustomerFilterOption = "- Past due";



    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-2545")
    public void VerifyThatCustomerNameDisplayInEmptyInvoiceList() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        Assert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");
        Login.navigateToDistributorPortal(DP);
        Assert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"navigation error");

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        Assert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId),"search error");
        Customer.SelectCustomer(customerId);
        Customer.clickonInvoice();

        softAssert.assertTrue(Customer.isEmptyStateMessageDisplay(emptyStateMessage),"message not display");

        Customer.clickOnDropDownFilter();
        Customer.selectFilterDropDown(CustomerFilterOption);
        Thread.sleep(4000);
        softAssert.assertTrue(Customer.isEmptyStateMessageDisplay(emptyStateMessage),"message not display");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
