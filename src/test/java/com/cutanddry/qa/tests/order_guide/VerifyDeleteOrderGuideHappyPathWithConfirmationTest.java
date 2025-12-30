package com.cutanddry.qa.tests.order_guide;


import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.Customer;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.functions.Orders;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.concurrent.ThreadLocalRandom;

public class VerifyDeleteOrderGuideHappyPathWithConfirmationTest extends TestBase {
    static User user;
    static String customerId = "16579";
    static String orderGuideName = "Test_OG_Delete_" + ThreadLocalRandom.current().nextInt(100000, 1_000_000);
    static String itemName = "Fuji Apples 125 CT";

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-3668")
    public void verifyDeleteOrderGuideHappyPathWithConfirmation() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();

        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "Login failed - user not navigated to dashboard");

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Customer search failed - customer not found");

        Customer.clickOnOrderGuide(customerId);

        Customer.goToCreatePopup();
        Customer.createOrderGuide(orderGuideName);
        Customer.createOrderFromCatalog();
        Customer.searchItemOnCatalog(itemName);
        Customer.addItemFromCatalog();
        Customer.closeEditorCatalog();
        Customer.refreshCustomersPage();

        Customer.clickOGDropdown();
        Customer.selectNewlyCreatedOrderGuide(orderGuideName);
        softAssert.assertTrue(Customer.isNewlyCreatedOrderGuideDisplay(orderGuideName), "Order guide not displayed after selection");

        Customer.expandMoreOptionsDropdown();
        softAssert.assertTrue(Customer.isDeleteOrderGuideOptionDisplayed(), "Delete Order Guide option not visible in More Options dropdown");

        Customer.clickOnDeleteOrderGuide();
        softAssert.assertTrue(Orders.isAreYouSurePopUpDisplayed(), "Confirmation dialog not displayed after clicking Delete Order Guide");

        Orders.clickYes();

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Customer search failed - customer not found");

        Customer.clickOnOrderGuide(customerId);
        Customer.clickOGDropdown();
        softAssert.assertFalse(Customer.isNewlyCreatedOrderGuideDisplay(orderGuideName), "Deleted order guide still appears in the dropdown list");

        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
