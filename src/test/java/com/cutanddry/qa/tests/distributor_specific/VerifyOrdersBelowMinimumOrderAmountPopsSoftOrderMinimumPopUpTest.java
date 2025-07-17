package com.cutanddry.qa.tests.distributor_specific;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.PriceData;
import com.cutanddry.qa.functions.Customer;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.functions.Settings;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyOrdersBelowMinimumOrderAmountPopsSoftOrderMinimumPopUpTest extends TestBase{
    static User user;
    static String distributorCheeseImp = PriceData.DISTRIBUTOR_CHEESE_IMP;
    static String customerId3 = PriceData.CUSTOMER_ID_6;

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-421")
    public void VerifyOrdersBelowMinimumOrderAmountPopsSoftOrderMinimumPopUp() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");

        Login.navigateToDistributorPortal(distributorCheeseImp);
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"navigation error");

        Dashboard.navigateToOrderSettings();
        softAssert.assertTrue(Settings.isOrderSettingsTextDisplayed(),"navigation error");
        Settings.orderMinimumCheckBox(false);

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId3);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId3), "Unable to find the customer Id");
        Customer.clickOnOrderGuide(customerId3);
        Customer.increaseFirstRowQtyCustom(1);
        Customer.clickCheckOutOrderGuide();

        softAssert.assertTrue(Customer.isReviewOrderTextDisplayed(), "The user is unable to land on the Review Order page.");
        Customer.submitOrderMinimum();
        softAssert.assertTrue(Customer.isOrderMinPopupDisplayed(),"popup display error");
        Customer.clickYesOrderMinimum();

        Dashboard.navigateToOrderSettings();
        softAssert.assertTrue(Settings.isOrderSettingsTextDisplayed(),"navigation error");
        Settings.orderMinimumCheckBox(true);
        softAssert.assertAll();
    }

   @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
