package com.cutanddry.qa.tests.settings;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
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

public class VerifyOrderSettingGlobalHolidayTest extends TestBase {
    static User user;
    static String customerId = "16579";
    static String itemName = "Artichoke -24ct";

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-635")
    public void VerifyOrderSettingGlobalHoliday() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");
        Dashboard.navigateToOrderSettings();
        softAssert.assertTrue(Settings.isOrderSettingsTextDisplayed(),"navigation to order settings error");
        Settings.setCustomerSpecificDeliveryDays(false); // Settings.uncheckDeliveryDays();
        Settings.clickOnAddHoliday();
        softAssert.assertTrue(Settings.isSetHolidayPopupDisplayed(),"holiday settings popup error");
        Settings.selectGlobal();
        Settings.clickOnSaveBtn();
        softAssert.assertEquals(Settings.getDate(),Settings.getAddedDate(),"holiday setting error");
        Settings.clickOnSaveChanges();
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId),"search error");
        Customer.clickOnOrderGuide(customerId);
        Customer.increaseFirstRowQtyByOne();
        Customer.checkoutItems();
        softAssert.assertFalse(Settings.isHolidayInDeliveryOrPuckUpOrMailDates(),"distributor setting error");
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");
        Dashboard.navigateToIndependentFoodsCo();
        Dashboard.navigateToOrderGuide();
        softAssert.assertTrue(Dashboard.isUserNavigatedToOrderGuide(),"navigation error");
        Customer.searchItemOnOrderGuide(itemName);
        softAssert.assertTrue(Customer.getItemNameFirstRow().contains(itemName),"item mismatch");
        Customer.increaseFirstRowQtyByOneInDist();
        Customer.checkoutItemsDist();
        softAssert.assertFalse(Settings.isHolidayInDeliveryOrPuckUpOrMailDates(),"operator setting error");
        Login.navigateToDistributor();
        Dashboard.isUserNavigatedToDashboard();
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");
        Dashboard.navigateToOrderSettings();
        softAssert.assertTrue(Settings.isOrderSettingsTextDisplayed(),"navigation to order settings error");
        Settings.clickOnRemoveHoliday();
        softAssert.assertNotEquals(Settings.getDate(),Settings.getAddedDate(),"holiday setting error");
        Settings.clickOnSaveChanges();
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
