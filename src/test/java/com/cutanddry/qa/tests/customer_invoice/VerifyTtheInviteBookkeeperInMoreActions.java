package com.cutanddry.qa.tests.customer_invoice;

import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.CustomerInvoiceData;
import com.cutanddry.qa.functions.Customer;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.ITestResult;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.cutanddry.qa.base.TestBase;

public class VerifyTtheInviteBookkeeperInMoreActions extends TestBase {
    static User user;
    String CustomerCode = CustomerInvoiceData.CUSTOMER_CODE;
    String BookKeeperName = CustomerInvoiceData.BOOK_KEEPER_NAME;
    String BookKeeperEmail = CustomerInvoiceData.BOOK_KEEPER_EMAIL;
    String BookKeeperMobile = CustomerInvoiceData.BOOK_KEEPER_MOBILE;

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-699")
    public void VerifyTtheInviteBookkeeperInMoreActions() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        Dashboard.isUserNavigatedToDashboard();
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"The user is unable to land on the Dashboard page.");
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(CustomerCode);
        softAssert.assertTrue(Customer.isSearchedRowDisplayed(CustomerCode),"The searched customer is not displayed");
        Customer.clickOnFirstItemOfCustomerRequests();
        Customer.clickonInvoice();
        Customer.clickDropdownMoreActions();
        Customer.clickInviteBookKeeper();
        Customer.fillBookKeeperName(BookKeeperName);
        Customer.fillBookKeeperEmail(BookKeeperEmail);
//        Customer.fillBookKeeperMobile(BookKeeperMobile);
        Customer.clickInviteViaEmail();
        softAssert.assertTrue(Customer.isBookKeeperEmailSentConfirmationDisplayed(BookKeeperEmail), "There has been an issue sending the email");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}