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

public class VerifyOrderGuideMoreOptionsCreateNewOrderGuideHappyPathTest extends TestBase {
    static User user;
    static String customerId = "34419";
    static String newOrderGuideName = "Weekly Specials OG";

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-3664")
    public void verifyOrderGuideMoreOptionsCreateNewOrderGuideHappyPath() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();

        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "Login failed - user not navigated to dashboard");

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Customer search failed - customer not found");

        Customer.clickOnOrderGuide(customerId);

        Customer.expandMoreOptionsDropdown();
        softAssert.assertTrue(Customer.isCreateOrderGuideButtonDisplay(), "More Options dropdown does not show Create New Order Guide option");

        Customer.goToCreatePopup();
        Customer.createOrderGuide(newOrderGuideName);
        softAssert.assertTrue(Customer.isOrderGuideCreateSuccessPopupDisplayed(), "Order guide creation success popup not displayed");
        Customer.clickOK();

        Customer.clickOGDropdown();
        softAssert.assertTrue(Customer.isNewlyCreatedOrderGuideDisplay(newOrderGuideName), "Newly created order guide not displayed in dropdown");

        Customer.selectNewlyCreatedOrderGuide(newOrderGuideName);
        Customer.expandMoreOptionsDropdown();
        Customer.clickOnDeleteOrderGuide();
        softAssert.assertTrue(Orders.isAreYouSurePopUpDisplayed(), "Delete confirmation popup not displayed");
        Orders.clickYes();

        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
