package com.cutanddry.qa.tests.parent_child_orderguide;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.ParentChildOGData;
import com.cutanddry.qa.functions.Customer;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.functions.Orders;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyThatTheParentCustomerEditAnOrderGuidesThenChangesAreSyncedToChildAccountsTest extends TestBase {
    static User user;
    static String DP = ParentChildOGData.DISTRIBUTOR_INDIANHEAD;
    static String customerId = ParentChildOGData.CUSTOMER_ID_INDIANHEAD;
    static String customerId2 = ParentChildOGData.CUSTOMER_ID_INDIANHEAD_2;
    static String OrderGuideName = ParentChildOGData.ORDER_GUIDE_NAME_6;
    static String itemName2 = "Aptz Mozz Stick Battered";
    static String status = "Parent Account";
    static String childSettingMessage = "Child account settings updated successfully";


    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-1237")
    public void VerifyThatTheParentCustomerEditAnOrderGuidesThenChangesAreSyncedToChildAccounts() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        Assert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");
        Login.navigateToDistributorPortal(DP);
        Assert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"navigation error");

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        Assert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId),"search error");
        Customer.clickOnOrderGuideParentChild(customerId);
        Customer.clickOGDropdown();
        Customer.selectNewlyCreatedOrderGuide(OrderGuideName);
        Customer.goToEdit();
        softAssert.assertTrue(Customer.isEditOrderGuideTextDisplayed(),"navigation error for edit");
        Customer.createOrderFromCatalog();
        Customer.searchItemOnCatalog(itemName2);
        Customer.addItemFromCatalog();
        Customer.closeEditorCatalog();
        Customer.refreshCustomersPage();

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        Assert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId),"search error");
        Customer.SelectCustomer(customerId);
        softAssert.assertTrue(Customer.isLinkedAccountDisplayed(),"linked account section not displayed");
        softAssert.assertTrue(Customer.isAccountStatusDisplayed(status),"parent account status not displayed");
        Customer.clickEditChildAccount();
        softAssert.assertTrue(Customer.isManageChildAccountPopUpDisplayed(),"manage child account pop up not displayed");
        Customer.selectNewlyAddedOrderGuide(customerId2,OrderGuideName);
        Orders.clickSaveButton();
        softAssert.assertTrue(Customer.isChildSettingUpdated(childSettingMessage),"child setting not updated");
        Customer.clickOK();

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId2);
        Assert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId2),"search error");
        Customer.clickOnOrderGuideParentChild(customerId2);
        Customer.clickOGDropdown();
        Customer.selectNewlyCreatedOrderGuide(OrderGuideName);
        softAssert.assertTrue(Customer.isAddedItemDisplayed(itemName2), "The item edit in parent account not display");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
