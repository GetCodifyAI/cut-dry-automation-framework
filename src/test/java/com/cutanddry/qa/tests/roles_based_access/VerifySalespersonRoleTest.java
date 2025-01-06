package com.cutanddry.qa.tests.roles_based_access;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.*;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifySalespersonRoleTest extends TestBase {
    static User user;
    static String userAR = "apptesters@test.com";
    static String customerId = "16579";
    static String itemName = "Artichoke";

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-486")
    public void VerifySalespersonRole() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");
        Login.navigateToLoginAsPortal(userAR);
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");
        //customer
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId),"customer search error");
        Customer.clickOnCustomerCode(customerId);
        softAssert.assertTrue(Customer.isAbleToInviteUsers(),"invite users error");
        Customer.clickOnBack();
        Customer.clickOnOrderGuide(customerId);
        Customer.clickOnIndependentFoods();
        Customer.goToEdit();
        softAssert.assertTrue(Customer.isEditOrderGuideTextDisplayed(),"navigation error for edit");
        Customer.clickOnBack();
        Customer.getItemNameFirstRow();
        Customer.increaseFirstRowQtyByOne();
        Customer.goToCatalog();
        Customer.searchItemOnCatalog(itemName);
        softAssert.assertTrue(Customer.getFirstElementFrmSearchResults(itemName).contains(itemName.toLowerCase()), "item not found");
        Customer.addItemToCartCatalog(itemName);
        Customer.checkoutItems();
        Customer.submitOrder();
        softAssert.assertTrue(Customer.isThankingForOrderPopupDisplayed(),"order not completed");
        Customer.clickClose();
        Customer.clickOnBoostTab();
        softAssert.assertTrue(Customer.isAbleToEditMsg(),"edit boost msg error");
        Dashboard.navigateToOrders();
        softAssert.assertTrue(Orders.isUserNavigatedToOrder(),"navigate to order history error");
        //settings
        Dashboard.navigateToTeamSettings();
        softAssert.assertTrue(Settings.isTeamSettingsTextDisplayed(),"navigation to team settings error");
        Dashboard.navigateToCompanySettings();
        softAssert.assertTrue(Settings.isCompanySettingsTextDisplayed(),"navigation to company settings error");
        Dashboard.navigateToOrderSettings();
        softAssert.assertTrue(Settings.isOrderSettingsTextDisplayed(),"navigation to order settings error");
        Dashboard.navigateToProfileSettings();
        softAssert.assertTrue(Settings.isProfileSettingsTextDisplayed(),"navigation to profile settings error");
        //track
        Dashboard.navigateToTrackMonitoring();
        softAssert.assertTrue(Track.isMonitoringTextDisplayed(),"navigation to track monitoring error");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
