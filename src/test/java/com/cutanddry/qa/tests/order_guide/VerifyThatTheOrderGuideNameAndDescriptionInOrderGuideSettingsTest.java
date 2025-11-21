package com.cutanddry.qa.tests.order_guide;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.Customer;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.functions.OrderGuideSettings;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyThatTheOrderGuideNameAndDescriptionInOrderGuideSettingsTest extends TestBase {
    static User user;
    static String customerId = "34419";
    static String OrderGuideName = "Independent Foods Co";
    static String NewOrderGuideName = "Test Foods OG";
    static String OrderGuideDescription = "Test Description";



    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-2540")
    public void VerifyThatTheOrderGuideNameAndDescriptionInOrderGuideSettings() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        Dashboard.isUserNavigatedToDashboard();
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId),"search error");
        Customer.clickOnOrderGuide(customerId);

        Customer.selectNewlyCreatedOrderGuide(OrderGuideName);
        Customer.expandMoreOptionsDropdown();
        Customer.clickOnOrderGuideSettings();
        OrderGuideSettings.editOrderGuideName(NewOrderGuideName);
        OrderGuideSettings.editOrderGuideDescription(OrderGuideDescription);
        OrderGuideSettings.clickOnSave();
        Thread.sleep(5000);

        softAssert.assertTrue(Customer.isEditOrderGuideTextDisplayed(),"ERROR in navigating to Order Guide Edit View");
        Customer.clickLocationGuide();
        softAssert.assertTrue(Customer.IsLocationOrderGuideDisplay(NewOrderGuideName),"Display edit order guide ");

        Customer.closeEditor();
        Thread.sleep(10000);
        softAssert.assertTrue(Customer.editedOrderGuideNameDisplay(NewOrderGuideName),"order guide name edit error");
        softAssert.assertTrue(Customer.editedOrderGuideDescriptionDisplay(OrderGuideDescription),"order guide Description edit error");

        Customer.expandMoreOptionsDropdown();
        Customer.clickOnOrderGuideSettings();
        OrderGuideSettings.editOrderGuideName(OrderGuideName);
        OrderGuideSettings.editOrderGuideDescription("");
        OrderGuideSettings.clickOnSave();
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
