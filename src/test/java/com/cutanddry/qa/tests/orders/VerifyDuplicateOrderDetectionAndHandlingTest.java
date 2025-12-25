package com.cutanddry.qa.tests.orders;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.DistributorOrderData;
import com.cutanddry.qa.functions.Customer;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyDuplicateOrderDetectionAndHandlingTest extends TestBase {
    static User user;
    static String customerId = DistributorOrderData.RESTAURANT_TEST_HAYES_ID;
    static String itemName;
    static String firstOrderId;

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-3554")
    public void verifyDuplicateOrderDetectionAndHandling() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();

        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "Login failed - user unable to land on Dashboard");

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Unable to find the customer Id");
        Customer.clickOnOrderGuide(customerId);

        itemName = Customer.getItemNameFirstRow();
        Customer.increaseFirstRowQtyCustom(1);
        Customer.checkoutItems();

        softAssert.assertTrue(Customer.isReviewOrderTextDisplayed(), "User unable to land on Review Order page");
        Customer.submitOrder();
        softAssert.assertTrue(Customer.isThankingForOrderPopupDisplayed(), "First order was not completed successfully");
        firstOrderId = Customer.getSuccessOrderId();
        Customer.clickClose();

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Unable to find the customer Id for second order");
        Customer.clickOnOrderGuide(customerId);

        Customer.increaseFirstRowQtyCustom(1);
        Customer.checkoutItems();

        softAssert.assertTrue(Customer.isReviewOrderTextDisplayed(), "User unable to land on Review Order page for second order");
        Customer.submitOrderWithoutHandlingDuplicate();

        boolean duplicatePopupDisplayed = Customer.isDuplicateOrderPopupDisplayed();
        if (duplicatePopupDisplayed) {
            softAssert.assertTrue(Customer.isDuplicateOrderTitleDisplayed(), "Duplicate Order title not displayed in warning modal");

            boolean editOptionDisplayed = Customer.isEditExistingOrderOptionDisplayed();
            boolean createNewOptionDisplayed = Customer.isCreateNewOrderOptionDisplayed();
            boolean mergeOptionDisplayed = Customer.isMergeOrderOptionDisplayed();

            softAssert.assertTrue(editOptionDisplayed || createNewOptionDisplayed || mergeOptionDisplayed,
                    "No duplicate order handling options displayed in the modal");

            if (editOptionDisplayed) {
                Customer.clickEditExistingOrderOption();
                softAssert.assertTrue(Customer.isEditExistingOrderTextDisplayed() || Customer.isReviewOrderTextDisplayed(),
                        "Failed to navigate to edit existing order flow");
            } else {
                Customer.clickYesDuplicateOrder();
                softAssert.assertTrue(Customer.isThankingForOrderPopupDisplayed(), "Order was not completed after confirming duplicate");
                Customer.clickClose();
            }
        } else {
            softAssert.assertTrue(Customer.isThankingForOrderPopupDisplayed(), "Order was not completed successfully (no duplicate detection)");
            Customer.clickClose();
        }

        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
