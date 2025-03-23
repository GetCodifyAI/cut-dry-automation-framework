package com.cutanddry.qa.tests.settings;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.HolidayData;
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

import java.text.ParseException;

public class VerifyOrderSettingCustomerSpecificHolidayTest extends TestBase {
    static User user;
//    static String customerId = "21259";
//    static String itemName = "Artichoke -24ct";
    static String customerId = HolidayData.Agave_Big_Sky_ID;
    static String holidayDate;
    static String DP = HolidayData.DISTRIBUTOR_ECO_MONTANA;
    static String itemName, searchItemCode;
    static String customerPhoneNo = HolidayData.Agave_Big_Sky_PHONE_NO;
    SoftAssert softAssert;

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-636")
    public void VerifyOrderSettingCustomerSpecificHoliday() throws InterruptedException, ParseException {
        softAssert = new SoftAssert();
//        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
//        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");

        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");

        Login.switchIntoNewTab();
        Login.navigateToDistributorPortal(DP);
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"navigation error");

        Dashboard.navigateToOrderSettings();
        softAssert.assertTrue(Settings.isOrderSettingsTextDisplayed(),"navigation to order settings error");
        Settings.setCustomerSpecificDeliveryDays(false);
        Settings.setDeliveryDays(false);
        Settings.clickOnAddHoliday(2);
        softAssert.assertTrue(Settings.isSetHolidayPopupDisplayed(),"holiday settings popup error");
        Settings.selectHolidayDate(2);
        holidayDate = Settings.getHolidayDate();
        Settings.selectCustomerSpecific(customerId);
        Settings.clickOnSaveBtn();
        softAssert.assertEquals(holidayDate,Settings.getAddedDate(),"Added holiday setting error");
//        softAssert.assertEquals(Settings.getDate(),Settings.getAddedDate(),"holiday setting error");
        Settings.clickOnSaveChanges();

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId),"search error");
        Customer.clickOnOrderGuide(customerId);

        Customer.increaseFirstRowQtyByOne();
        Customer.stableCheckoutItems();

//        softAssert.assertFalse(Settings.isHolidayInDeliveryOrPuckUpOrMailDates(),"distributor setting error");
        softAssert.assertFalse(Settings.isHolidayInDelivery(holidayDate),"distributor setting error");

        Login.switchIntoNewTab();
//        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        Login.navigateToRestaurantPortal(customerPhoneNo);
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");
//        Dashboard.navigateToIndependentFoodsCo();
//        Dashboard.navigateToOrderGuide();
        Dashboard.clickOnPlaceOrderBtn();

        softAssert.assertTrue(Dashboard.isUserNavigatedToOrderGuide(),"navigation error");
        itemName = Customer.getItemNameFirstRow();
        searchItemCode = Customer.getItemCodeFirstRow();
        Customer.searchItemOnOrderGuide(searchItemCode);
        softAssert.assertTrue(Customer.getItemNameFirstRow().contains(itemName),"item mismatch");
        Customer.increaseFirstRowQtyByOneInDist();
        Customer.checkoutItemsDist();
//        softAssert.assertFalse(Settings.isHolidayInDeliveryOrPuckUpOrMailDates(),"operator setting error");
        softAssert.assertFalse(Settings.isHolidayInDelivery(holidayDate),"operator setting error");

        Login.navigateToDistributor();
        Dashboard.isUserNavigatedToDashboard();
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");
        Dashboard.navigateToOrderSettings();
        softAssert.assertTrue(Settings.isOrderSettingsTextDisplayed(),"navigation to order settings error");
        Settings.clickOnRemoveHoliday();
//        softAssert.assertNotEquals(Settings.getDate(),Settings.getAddedDate(),"holiday setting error");
        softAssert.assertNotEquals(holidayDate,Settings.getAddedDate(),"holiday setting error after remove");
        Settings.clickOnSaveChanges();
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsersAtOnce();
    }
}
