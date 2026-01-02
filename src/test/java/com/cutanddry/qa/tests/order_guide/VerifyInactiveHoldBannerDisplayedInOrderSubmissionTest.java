package com.cutanddry.qa.tests.order_guide;

import com.cutanddry.qa.data.models.User;
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

import static com.cutanddry.qa.base.TestBase.*;

public class VerifyInactiveHoldBannerDisplayedInOrderSubmissionTest {
    static User user;
    String DistributorName ="46505655 - Kevin - Independent Foods Co";
    static String customerId = "30275";
    static String inactiveHoldMessage1 = "There is an issue with your account. Please reach out to your supplier sales rep.";

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-3472")
    public void VerifyInactiveHoldBannerDisplayInOrderSubmission() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        Assert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(), "login error");
        Login.navigateToDistributorPortal(DistributorName);
        Dashboard.isUserNavigatedToDashboard();
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "login error");

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "search error");
        Customer.clickOnCustomerCode(customerId);
        Customer.clickOnEditAccHolds();
        Customer.clickOnAccDropdown();
        Customer.clickOnInactiveHold();
        Customer.clickOnSave();
        softAssert.assertTrue(Customer.isInactiveHoldSelected(), "acc select error");

        Customer.clickOnOrderGuideInCustomerProfile();
        Customer.increaseFirstRowQtyCustom(1);
        Customer.checkoutItems();

        softAssert.assertTrue(Customer.isReviewOrderTextDisplayed(), "The user is unable to land on the Review Order page.");
        Customer.submitOrder();
        softAssert.assertTrue(Customer.isAccountHoldPopUpDisplay(), "account not hold");
        softAssert.assertTrue(Customer.isAccountHoldMessageDisplay(inactiveHoldMessage1), "account inactive hold message not display");
        softAssert.assertAll();

    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
