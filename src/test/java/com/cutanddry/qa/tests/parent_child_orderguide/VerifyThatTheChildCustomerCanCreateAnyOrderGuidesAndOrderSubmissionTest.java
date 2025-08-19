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

public class VerifyThatTheChildCustomerCanCreateAnyOrderGuidesAndOrderSubmissionTest extends TestBase {
    static User user;
    static String DP = ParentChildOGData.DISTRIBUTOR_INDIANHEAD;
    static String customerId2 = ParentChildOGData.CUSTOMER_ID_INDIANHEAD_2;
    static String OrderGuideName = ParentChildOGData.ORDER_GUIDE_NAME_4;
    static String itemName = "Appetizer Egg Roll Vegetable";
    static String  searchItemName,orderId, searchItemCode;
    static double itemPrice;


    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-1236")
    public void VerifyThatTheChildCustomerCanCreateAnyOrderGuidesAndOrderSubmission() throws InterruptedException {
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
        Customer.searchCustomerByCode(customerId2);
        Assert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId2),"search error");
        Customer.clickOnOrderGuideParentChild(customerId2);
        Customer.clickOGDropdown();
        Customer.selectNewlyCreatedOrderGuide(OrderGuideName);

        searchItemName = Customer.getItemNameFirstRow();
        searchItemCode = Customer.getItemCodeFirstRow();
        itemPrice = Customer.getActiveItemPriceFirstRow();
        Customer.increaseFirstRowQtyCustom(15);
        Customer.clickCheckOutPDP();

        softAssert.assertTrue(Customer.isReviewOrderTextDisplayed(), "The user is unable to land on the Review Order page.");
        softAssert.assertEquals(Customer.getItemNameFirstRow(), itemName, "The item selected by the user is different from what is shown on the order review page.");
        Customer.submitOrder();
        softAssert.assertTrue(Customer.isThankingForOrderPopupDisplayed(), "The order was not completed successfully.");
        orderId = Customer.getSuccessOrderId();
        Customer.clickClose();

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId2);
        Assert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId2),"search error");
        Customer.clickOnOrderGuideParentChild(customerId2);
        Customer.clickOGDropdown();
        Customer.selectNewlyCreatedOrderGuide(OrderGuideName);
//        Customer.goToEdit();
//        softAssert.assertTrue(Customer.isEditOrderGuideTextDisplayed(),"navigation error for edit");
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
