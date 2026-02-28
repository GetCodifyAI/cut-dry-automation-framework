package com.cutanddry.qa.tests.orders;

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

public class VerifyEditOrderButtonFunctionalityInDistributorMainStandingEditOrderFlowsTest extends TestBase {
    static User user;
    static String customerId = "16579";

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-3602")
    public void verifyEditOrderButtonFunctionalityInDistributorMainStandingEditOrderFlows() throws InterruptedException {
        String itemName;
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "login error");

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        Assert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Unable to find the customer Id");
        Customer.clickOnOrderGuide(customerId);

        itemName = Customer.getItemNameFirstRow();
        Customer.increaseFirstRowQtyByOne();
        Customer.checkoutItems();
        softAssert.assertTrue(Customer.isReviewOrderTextDisplayed(), "review order page not displayed");
        softAssert.assertEquals(Customer.getItemNameFirstRow(), itemName, "item mismatch in review screen");

        Customer.editOrderFromReviewScreen();
        softAssert.assertTrue(Customer.isOrderGuideDisplayed(), "user not navigated back to order guide after clicking edit order");

        Customer.increaseFirstRowQtyByOne();
        Customer.checkoutItems();
        softAssert.assertTrue(Customer.isReviewOrderTextDisplayed(), "review order page not displayed after edit");
        Customer.submitOrder();
        softAssert.assertTrue(Customer.isThankingForOrderPopupDisplayed(), "thank you popup not displayed");
        Customer.clickOK();

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        Assert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Unable to find the customer Id");
        Customer.SelectCustomer(customerId);
        Customer.clickOnOrdersTab();
        Orders.clickOnFirstOrder();
        Orders.clickOnEditOrder();
        softAssert.assertTrue(Customer.isOrderGuideDisplayed(), "user not navigated to order guide in edit order flow");

        Customer.increaseFirstRowQtyByOne();
        Customer.checkoutItems();
        softAssert.assertTrue(Customer.isReviewOrderTextDisplayed(), "review order page not displayed in edit order flow");

        Customer.editOrderFromReviewScreen();
        softAssert.assertTrue(Customer.isOrderGuideDisplayed(), "user not navigated back to order guide after clicking edit order in edit flow");

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        Assert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Unable to find the customer Id");
        Customer.clickOnOrderGuide(customerId);

        Customer.expandMoreOptionsDropdown();
        Customer.clickOnManageStandingOrders();
        Customer.deleteTheExistingStandingOrdersInManageIFAvailable();
        Customer.clickOnManageCreateStandingOrder();

        Customer.selectDeliveryDateAsLastBefore();
        Customer.increaseFirstRowQtyByOne();
        Customer.checkoutItems();
        softAssert.assertTrue(Customer.isReviewOrderTextDisplayed(), "review order page not displayed in standing order flow");

        Customer.editOrderFromReviewScreen();
        softAssert.assertTrue(Customer.isOrderGuideDisplayed(), "user not navigated back to order guide after clicking edit order in standing order flow");

        Customer.increaseFirstRowQtyByOne();
        Customer.checkoutItems();
        Customer.setStandingOrder();
        softAssert.assertTrue(Customer.isStandingOrderEmailPopupDisplayed(), "standing order email popup not displayed");
        Customer.selectEmail();
        Customer.scheduleStandingOrder();
        softAssert.assertTrue(Customer.isStandingOrderSuccessPopupDisplayed(), "standing order success popup not displayed");
        Customer.clickOK();

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        Assert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Unable to find the customer Id");
        Customer.clickOnOrderGuide(customerId);
        Customer.expandMoreOptionsDropdown();
        Customer.clickOnManageStandingOrders();
        Customer.clickOnStandingOrderDeleteIcon();

        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
