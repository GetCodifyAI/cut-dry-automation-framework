package com.cutanddry.qa.tests.standing_orders;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.Customer;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Draft;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.pages.CustomersPage;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyStandingOrderFlowWhenSwitchingOrderGuidesTest extends TestBase {
    static User user;
    static String customerId = "16579";
    static String deliveryDay = "Monday";
    static String firstOrderGuideName = "Independent Foods Co";
    static String secondOrderGuideName = "Independent Foods Co";
    String itemName;
    String secondItemName;
    int draftCountBefore;
    int draftCountAfter;
    int orderCount;

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-1546")
    public void verifyStandingOrderFlowWhenSwitchingOrderGuides() throws InterruptedException {

        SoftAssert softAssert = new SoftAssert();

        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "Login error - user not navigated to dashboard");


        Dashboard.navigateToDrafts();
       // draftCountBefore = Draft.getActiveDraftCount();

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Customer search error");

        Customer.clickOnCustomerCode(customerId);
        Customer.clickOnOrdersTab();
        softAssert.assertTrue(Customer.isStandingOrdersDisplayed(), "Navigation to Orders tab error");

        Customer.removeStandingOrdersIfAvailable();
        Customer.clickOnCreateStandingOrder();

        Customer.selectDeliveryDate(deliveryDay);

        itemName = Customer.getItemNameFirstRow();
        Customer.increaseFirstRowQtyByOne();
        softAssert.assertTrue(Integer.parseInt(Customer.getItemQtyFirstRow()) > 0,
                "Item quantity not increased");

        Customer.clickOnItemOrderGuideDropDown(secondOrderGuideName);
        Customer.isAreYouSurePopupDisplayed();
        Customer.clickOnSwitch();

//        Dashboard.navigateToDrafts();
//        softAssert.assertTrue(Draft.isUserNavigatedToDrafts(),
//                "Draft section not displayed");
//        draftCountAfter = Draft.getActiveDraftCount();
//        softAssert.assertEquals(draftCountAfter, draftCountBefore,
//                "Draft not created when switching to new order guide - expected count: " + (draftCountBefore) + ", actual: " + draftCountAfter);

//        Dashboard.navigateToCustomers();
//        Customer.searchCustomerByCode(customerId);
//        Customer.clickOnCustomerCode(customerId);
//        Customer.clickOnOrdersTab();
//        Customer.clickOnCreateStandingOrder();
        Customer.selectDeliveryDate(deliveryDay);

        secondItemName = Customer.getItemNameFirstRow();
        Customer.increaseFirstRowQtyByOne();
        softAssert.assertTrue(Integer.parseInt(Customer.getItemQtyFirstRow()) > 0,
                "Item quantity not increased in second order guide");

        Customer.checkoutItems();

        softAssert.assertTrue(Customer.isReviewStandingOrdersDisplayed(),
                "Review Order screen not displayed");
        softAssert.assertEquals(Customer.getItemNameFirstRow(), secondItemName,
                "Item from Review screen does not match");

        Customer.setStandingOrder();

        softAssert.assertTrue(Customer.isStandingOrderEmailPopupDisplayed(),
                "Standing Order email popup not displayed");
        Customer.selectEmail();
        softAssert.assertTrue(Customer.isStandingOrderEmailPopupDisplayed(),"pop up display error");
        Customer.scheduleStandingOrder();
        Customer.clickOK();
        Customer.clickOnOrdersTab();
        orderCount = Customer.getStandingOrdersCount();
        softAssert.assertTrue(orderCount>0,"Standing order not created");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}