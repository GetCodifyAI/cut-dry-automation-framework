package com.cutanddry.qa.tests.orders;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.PriceData;
import com.cutanddry.qa.functions.Customer;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyThatOrderGuideEditDisableFeatureTest extends TestBase{
    static User user;
    static String distributorWagner = PriceData.DISTRIBUTOR_WAGNER;
    static String customerId = "515";
    static String OrderGuideName = "ORDER GUIDE 2024";
    static String primaryOrderGuideName = "Wagner Foodservice";


    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-1324")
    public void VerifyThatOrderGuideEditDisableFeature() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");

        Login.navigateToDistributorPortal(distributorWagner);
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"navigation error");

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Unable to find the customer Id");
        Customer.clickOnOrderGuideParentChild(customerId);

        Customer.expandMoreOptionsDropdown();
        softAssert.assertFalse(Customer.isEditOrderGuideButtonDisplay(),"edit OG button display");

        Customer.goToCatalog();
        softAssert.assertFalse(Customer.isAddToOrderGuideHartIconDisplay(),"add to OG hart icon display");
        Customer.clickOnOrderGuideTab();

        Customer.clickOGDropdown();
        Customer.selectNewlyCreatedOrderGuide(OrderGuideName);
        Customer.expandMoreOptionsDropdown();
        softAssert.assertTrue(Customer.isEditOrderGuideButtonDisplay(),"edit OG button display");
        Customer.goToCatalog();
        softAssert.assertTrue(Customer.isAddToOrderGuideHartIconDisplay(),"add to OG hart icon display");
        Customer.clickOnOrderGuideTab();

        Customer.clickOGDropdown();
        Customer.selectNewlyCreatedOrderGuide(primaryOrderGuideName);
        softAssert.assertAll();
    }

   @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
