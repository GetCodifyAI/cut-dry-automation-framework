package com.cutanddry.qa.tests.order_guide;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.*;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifySimpleListViewMaximumQuantityValidationTest extends TestBase {
    static User user;
    static String distributor = "Independent Foods Co";
    String customerId = "16672";

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-2233")
    public void verifySimpleListViewMaximumQuantityValidation() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();

        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "Login error - user not navigated to distributor dashboard");

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Unable to find the customer Id");

        Customer.clickOnOrderGuide(customerId);
        Customer.expandMoreOptionsDropdown();
        Customer.clickSimpleListView();
        softAssert.assertTrue(Customer.isSimpleListViewTextDisplay(), "Simple List View not displayed after clicking");


        Customer.typeQuantityInFirstRow("1999");
        Customer.clickOutsideQuantityFieldUnderListView();
        softAssert.assertTrue(Customer.isMaximumQuantityExceededModalDisplayed(),
                "Maximum Quantity Reached modal not displayed when equal to 1999");
        Customer.dismissMaximumQuantityExceededModal();
        Customer.typeQuantityInSecondRow("2000");
        Customer.clickOutsideQuantityFieldUnderListView();
        softAssert.assertTrue(Customer.isMaximumQuantityMessageBodyModalDisplayed(),
                "Maximum 1999 Quantity Exceeded modal not displayed when attempting to exceed 1999");
        Customer.clickOkOnMaxQuantityModal();
        Customer.typeQuantityInSecondRow("1200");
        softAssert.assertFalse(Customer.isMaximumQuantityExceededModalDisplayed(),
                "Maximum Quantity Reached modal not displayed when equal to 1999");
        Customer.checkoutItems();
        softAssert.assertTrue(Customer.isReviewOrderTextDisplayed(),
                "Review order page not displayed after checkout");

        double reviewTotalPrice = Customer.getReviewTotalPriceCart();
        softAssert.assertTrue(reviewTotalPrice > 0,
                "Review order total price should be greater than 0");

        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsersAtOnce();
    }


}
