package com.cutanddry.qa.tests.parent_child_orderguide;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
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

public class VerifyThatTheImportOrderGuideWillReflectForTheChildAccountTest extends TestBase {
    static User user;
    static String DP = "189234630 - Cut+Dry Agent - Indianhead Foodservice Distributor Sandbox";
    static String parentCustomerId = "13778";
    static String childCustomerId = "13777";
    static String status = "Parent Account";
    static String OrderGuideName = "TEST Order Guide import";
    static String childSettingMessage = "Child account settings updated successfully";

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-1589")
    public void VerifyThatTheImportOrderGuideWillReflectForTheChildAccount() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        Assert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");
        Login.navigateToDistributorPortal(DP);
        Assert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"navigation error");

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(parentCustomerId);
        Assert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(parentCustomerId),"search error");
        Customer.clickOnOrderGuideParentChild(parentCustomerId);
        softAssert.assertTrue(Customer.isCustomerOrderGuideDisplayed(),"user has navigated to the Order Guide");

        Customer.goToCreatePopup();
        Customer.createOrderByUploadingOG();
        Customer.createOrderGuide(OrderGuideName);
        softAssert.assertTrue(Customer.isOrderGuideCreateSuccessPopupDisplayed(),"order guide create success popup not displayed");
        Customer.clickOK();

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(parentCustomerId);
        Assert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(parentCustomerId),"search error");
        Customer.SelectCustomer(parentCustomerId);
        softAssert.assertTrue(Customer.isLinkedAccountDisplayed(),"linked account section not displayed");
        softAssert.assertTrue(Customer.isAccountStatusDisplayed(status),"parent account status not displayed");
        Customer.clickEditChildAccount();
        softAssert.assertTrue(Customer.isManageChildAccountPopUpDisplayed(),"manage child account pop up not displayed");

        softAssert.assertTrue(Customer.isOrderGuideAdded(childCustomerId, OrderGuideName),"order guide not added to child account dropdown");
        Customer.selectNewlyAddedOrderGuide(childCustomerId, OrderGuideName);
        Orders.clickSaveButton();
        softAssert.assertTrue(Customer.isChildSettingUpdated(childSettingMessage),"child setting not updated");
        Customer.clickOK();

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(childCustomerId);
        Assert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(childCustomerId),"search error for child customer");
        Customer.clickOnOrderGuideParentChild(childCustomerId);
        Customer.clickOGDropdown();
        softAssert.assertTrue(Customer.isNewlyCreatedOrderGuideDisplay(OrderGuideName),"newly created order guide not displayed in child account dropdown");

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(parentCustomerId);
        Assert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(parentCustomerId),"search error");
        Customer.clickOnOrderGuideParentChild(parentCustomerId);
        softAssert.assertTrue(Customer.isCustomerOrderGuideDisplayed(),"user has navigated to the Order Guide");
        Customer.clickOGDropdown();
        Customer.selectNewlyCreatedOrderGuide(OrderGuideName);
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
