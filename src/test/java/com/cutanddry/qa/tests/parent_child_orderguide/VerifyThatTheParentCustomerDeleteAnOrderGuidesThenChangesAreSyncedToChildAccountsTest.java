package com.cutanddry.qa.tests.parent_child_orderguide;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.ParentChildOGData;
import com.cutanddry.qa.functions.*;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.concurrent.ThreadLocalRandom;

public class VerifyThatTheParentCustomerDeleteAnOrderGuidesThenChangesAreSyncedToChildAccountsTest extends TestBase {
    static User user;
    static String DP = ParentChildOGData.DISTRIBUTOR_INDIANHEAD;
    static String customerId = ParentChildOGData.CUSTOMER_ID_INDIANHEAD;
    static String customerId2 = ParentChildOGData.CUSTOMER_ID_INDIANHEAD_2;
    static String OrderGuideName = ParentChildOGData.ORDER_GUIDE_NAME_6 + "_" + ThreadLocalRandom.current().nextInt(100000, 1_000_000);
    static String status = "Parent Account";
    static String itemName = "Egg Roll Pork & Vegetable";
    static String childSettingMessage = "Child account settings updated successfully";
    static String formID;


    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-1239")
    public void VerifyThatTheParentCustomerDeleteAnOrderGuidesThenChangesAreSyncedToChildAccounts() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        Assert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");
        Login.navigateToDistributorPortal(DP);
        Assert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"navigation error");

        //creating new order guide before editing
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        Assert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId),"search error");
        Customer.clickOnOrderGuideParentChild(customerId);
        Customer.goToCreatePopup();
        Customer.createOrderGuide(OrderGuideName);
        Customer.createOrderFromCatalog();
        Customer.searchItemOnCatalog(itemName);
        Customer.addItemFromCatalog();
        Customer.closeEditorCatalog();
        Customer.refreshCustomersPage();
        formID = Customer.getOrderGuideFormID();
        System.out.println(formID);

        Login.switchIntoNewTab();
        InternalTools.navigateToTaskManagement();
        InternalTools.runParentChildTask(formID);
        softAssert.assertTrue(InternalTools.isPCTaskAttemptedDisplayed(),"Parent child task not run successfully");
        Customer.clickOK();
        Login.closeCurrentTabAndSwitchBack();

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        Assert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId),"search error");
        Customer.clickOnOrderGuideParentChild(customerId);
        Customer.clickOGDropdown();
        Customer.selectNewlyCreatedOrderGuide(OrderGuideName);
//        Customer.goToEdit();
//        softAssert.assertTrue(Customer.isEditOrderGuideTextDisplayed(),"navigation error for edit");
        Customer.expandMoreOptionsDropdown();
        Customer.clickOnDeleteOrderGuide();
        softAssert.assertTrue(Orders.isAreYouSurePopUpDisplayed(),"Are you sure pop up not displayed");
        Orders.clickYes();
        Customer.refreshCustomersPage();

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        Assert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId),"search error");
        Customer.SelectCustomer(customerId);
        softAssert.assertTrue(Customer.isLinkedAccountDisplayed(),"linked account section not displayed");
        softAssert.assertTrue(Customer.isAccountStatusDisplayed(status),"parent account status not displayed");
        Customer.clickEditChildAccount();
        softAssert.assertTrue(Customer.isManageChildAccountPopUpDisplayed(),"manage child account pop up not displayed");
        Orders.clickSaveButton();
        softAssert.assertTrue(Customer.isChildSettingUpdated(childSettingMessage),"child setting not updated");
        Customer.clickOK();

        Login.switchIntoNewTab();
        InternalTools.navigateToTaskManagement();
        InternalTools.clickRunLocallyOnParentChildRelationshipTask();
        softAssert.assertTrue(InternalTools.isPCTaskAttemptedDisplayed(),"Parent child task not run successfully");
        Customer.clickOK();
        Login.closeCurrentTabAndSwitchBack();

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId2);
        Assert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId2),"search error");
        Customer.clickOnOrderGuideParentChild(customerId2);
        Customer.clickOGDropdown();
        softAssert.assertFalse(Customer.isNewlyCreatedOrderGuideDisplay(OrderGuideName),"order guide displayed");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
