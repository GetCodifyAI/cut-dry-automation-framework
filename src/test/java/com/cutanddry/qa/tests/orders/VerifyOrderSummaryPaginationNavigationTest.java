package com.cutanddry.qa.tests.orders;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.testdata.CustomerData;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.functions.Orders;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyOrderSummaryPaginationNavigationTest extends TestBase {

    @BeforeMethod
    public void setUp() {
        initialization();
    }

    @Test(groups = "DOT-TC-1836")
    public void verifyOrderSummaryPaginationNavigation() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();

        Login.navigateToDistributorPortal(CustomerData.DISTRIBUTOR_AFFILIATED);
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "Login failed - Dashboard not displayed");

        Dashboard.navigateToOrders();
        softAssert.assertTrue(Orders.isUserNavigatedToOrder(), "Navigation to Orders failed");

        softAssert.assertTrue(Orders.isOrderListDisplayed(), "Order list is not displayed");

        boolean paginationDisplayed = Orders.isPaginationBarDisplayed();
        if (paginationDisplayed) {
            softAssert.assertTrue(Orders.isPaginationNextButtonDisplayed(), "Pagination next button not displayed");

            String firstPageFirstOrder = Orders.getFirstOrderReferenceNumber();

            Orders.clickPaginationNextButton();
            softAssert.assertTrue(Orders.isOrderListDisplayed(), "Page 2 orders not loaded");

            String secondPageFirstOrder = Orders.getFirstOrderReferenceNumber();
            softAssert.assertNotEquals(firstPageFirstOrder, secondPageFirstOrder, "Orders did not change after navigating to next page");

            Orders.clickPaginationPreviousButton();
            softAssert.assertTrue(Orders.isOrderListDisplayed(), "Page 1 orders not loaded after going back");

            String backToFirstPageOrder = Orders.getFirstOrderReferenceNumber();
            softAssert.assertEquals(firstPageFirstOrder, backToFirstPageOrder, "Did not return to original page 1 orders");
        }

        Orders.selectOrderDate("Today");
        softAssert.assertTrue(Orders.isOrderDateChanged("Today"), "Date filter not applied");

        softAssert.assertTrue(Orders.isOrderListDisplayed() || Orders.getOrderTableRowCount() == 0, "Order list state is invalid after filtering");

        int filteredOrderCount = Orders.getOrderTableRowCount();
        if (filteredOrderCount > 0 && filteredOrderCount <= 10) {
            boolean paginationAfterFilter = Orders.isPaginationBarDisplayed();
            softAssert.assertFalse(paginationAfterFilter, "Pagination should not be displayed when orders fit on single page");
        }

        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
