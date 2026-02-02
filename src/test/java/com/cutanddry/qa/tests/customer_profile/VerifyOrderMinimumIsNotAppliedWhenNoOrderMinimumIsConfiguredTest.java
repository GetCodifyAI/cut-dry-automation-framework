package com.cutanddry.qa.tests.customer_profile;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.*;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyOrderMinimumIsNotAppliedWhenNoOrderMinimumIsConfiguredTest extends TestBase {
    SoftAssert softAssert;
    static User user;
    static String customerId = "97071";
    String DistributorName ="Independent Foods Co";
    static String defaultOrderMin = "0";
    static String orderMinimumType = "Hard Order Minimum";
    static String orderMinInternal = "5000000";
    static String orderMinimumSetting = "Exempt from Order Minimum";
    static String orderMinimumSettingUpdate = "Exempt from order minimum";

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }


    @Test(groups = "DOT-TC-1612")
    public void VerifyOrderMinimumIsNotAppliedWhenNoOrderMinimumIsConfigured() throws InterruptedException {

        softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");
        Login.navigateToInternalToolsPage();
        InternalTools.navigateToConfigureSupplier();
        InternalTools.navigateToIndependentCompEditDetails();
        InternalTools.navigateToOrderingSettingsTab();
        InternalTools.TurnOnOrderMinimumGloballyToggle(true);
        InternalTools.TurnOnAllowSupplierToSetMinimumToggle(true);
        InternalTools.clickOnOrderMinimumDropdown(orderMinimumType);
        InternalTools.enterOrderMinimumAmount(orderMinInternal);
        Thread.sleep(4000);
        InternalTools.clickSave();
        softAssert.assertTrue(InternalTools.isSuccessPopUpDisplayed(),"change not save");
        InternalTools.clickOKOnSucessOverlay();

        Login.navigateToDistributorPortal(DistributorName);
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "The user is unable to land on the Dashboard page.");

        Dashboard.navigateToOrderSettings();
        softAssert.assertTrue(Settings.isOrderSettingsTextDisplayed(),"navigation error");
        Settings.setOrderMinimums(false);
        Settings.clickOnSaveChanges();

        //set global order minimum from the profile
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Unable to find the customer Id");
        Customer.SelectCustomer(customerId);
        Customer.SelectOrderMinimumFromProfile(orderMinimumSetting);
        softAssert.assertTrue(Customer.isOrderMinimumExemptCustomerProfileDisplayed(orderMinimumSettingUpdate),"order setting not selected");
        Customer.ifHasHoldsRemoveHoldsFromCustomer();
        Customer.clickOnOrderGuideInProfile();

        Customer.increaseFirstRowQtyCustom(1);
        Customer.checkoutItems();

        softAssert.assertTrue(Customer.isReviewOrderTextDisplayed(), "The user is unable to land on the Review Order page.");
        Customer.submitOrderMinimum();
        softAssert.assertFalse(Customer.isOrderMinPopupDisplayed(),"popup display error");
        Customer.clickOkOrderMinimum();
        softAssert.assertTrue(Customer.isThankingForOrderPopupDisplayed(), "The order was not completed successfully.");
        Customer.clickClose();

        Dashboard.navigateToOrderSettings();
        softAssert.assertTrue(Settings.isOrderSettingsTextDisplayed(),"navigation error");
        Settings.enterOrderMinimum(defaultOrderMin);
        Settings.setOrderMinimums(true);
        Settings.clickOnSaveChanges();
        softAssert.assertAll();
    }
    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsersAtOnce();
    }
}
