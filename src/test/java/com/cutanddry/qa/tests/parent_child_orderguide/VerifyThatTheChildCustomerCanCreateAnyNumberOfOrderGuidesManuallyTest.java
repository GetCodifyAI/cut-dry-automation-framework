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

public class VerifyThatTheChildCustomerCanCreateAnyNumberOfOrderGuidesManuallyTest extends TestBase {
    static User user;
    static String DP = ParentChildOGData.DISTRIBUTOR_INDIANHEAD;
    static String customerId1 = ParentChildOGData.CUSTOMER_ID_INDIANHEAD;
    static String customerId2 = ParentChildOGData.CUSTOMER_ID_INDIANHEAD_2;
    static String status = "Parent Account";
    static String OrderGuideName = ParentChildOGData.ORDER_GUIDE_NAME_3;
    static String itemName = "Appetizer Egg Roll Vegetable";
    static String[] childAccounts = { "59195", "59130", "53175", "33602", "33601"  };


    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-1219")
    public void VerifyThatTheChildCustomerCanCreateAnyNumberOfOrderGuidesManually() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        Assert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");
        Login.navigateToDistributorPortal(DP);
        Assert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"navigation error");

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId2);
        Assert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId2),"search error");
        Customer.clickOnOrderGuideParentChild(customerId2);
        Customer.goToCreatePopup();
        Customer.createOrderGuide(OrderGuideName);
        Customer.createOrderFromCatalog();
        Customer.searchItemOnCatalog(itemName);
        Customer.addItemFromCatalog();
        Customer.closeEditorCatalog();

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId1);
        Assert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId1),"search error");
        Customer.SelectCustomer(customerId1);
        softAssert.assertTrue(Customer.isLinkedAccountDisplayed(),"linked account section not displayed");
        softAssert.assertTrue(Customer.isAccountStatusDisplayed(status),"parent account status not displayed");
        Customer.clickEditChildAccount();
        softAssert.assertTrue(Customer.isManageChildAccountPopUpDisplayed(),"manage child account pop up not displayed");
        for (String childAccount : childAccounts) {
            softAssert.assertFalse(Customer.isOrderGuideAdded(childAccount,OrderGuideName),"order guide added child account not successfully");
            Customer.clickChildAccountDropDown(childAccount);
        }
        Orders.clickSaveButton();
        Customer.clickOK();
        Customer.clickClose();


        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId2);
        Assert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId2),"search error");
        Customer.clickOnOrderGuideParentChild(customerId2);
        Customer.clickOGDropdown();
        Customer.selectNewlyCreatedOrderGuide(OrderGuideName);
        Customer.goToEdit();
        softAssert.assertTrue(Customer.isEditOrderGuideTextDisplayed(),"navigation error for edit");
        Customer.expandMoreOptionsDropdown();
        Customer.clickOnDeleteOrderGuide();
        softAssert.assertTrue(Orders.isAreYouSurePopUpDisplayed(),"Are you sure pop up not displayed");
        Orders.clickYes();
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
