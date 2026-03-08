package com.cutanddry.qa.tests.order_guide;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyPaginationLargeDatasetHandlingTest extends TestBase {
    static User user;

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-1802")
    public void verifyPaginationLargeDatasetHandling() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();

        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "login error - user not navigated to dashboard");

        Dashboard.navigateToOrderGuideChangesDetailPage();
        softAssert.assertTrue(Dashboard.isOrderGuideChangesDetailPageDisplayed(), "Order Guide Changes detail page not displayed");
        softAssert.assertTrue(Dashboard.isOrderGuideChangesTableDisplayed(), "Order Guide Changes table not displayed");
        softAssert.assertTrue(Dashboard.isPaginationDisplayed(), "Pagination controls not displayed");

        String initialPage = Dashboard.getCurrentPageNumber();
        softAssert.assertEquals(initialPage, "1", "Initial page is not page 1");
        int initialRowCount = Dashboard.getTableRowCount();
        softAssert.assertTrue(initialRowCount > 0, "No data rows displayed on the first page");

        if (Dashboard.isNextPageEnabled()) {
            Dashboard.clickNextPage();
            String nextPage = Dashboard.getCurrentPageNumber();
            softAssert.assertNotEquals(nextPage, "1", "Page did not change after clicking next");
            int nextPageRowCount = Dashboard.getTableRowCount();
            softAssert.assertTrue(nextPageRowCount > 0, "No data rows displayed after navigating to next page");

            Dashboard.clickPreviousPage();
            String prevPage = Dashboard.getCurrentPageNumber();
            softAssert.assertEquals(prevPage, "1", "Did not navigate back to page 1 after clicking previous");
            int prevPageRowCount = Dashboard.getTableRowCount();
            softAssert.assertTrue(prevPageRowCount > 0, "No data rows displayed after navigating to previous page");

            Dashboard.clickPageNumber("1");
            String directPage = Dashboard.getCurrentPageNumber();
            softAssert.assertEquals(directPage, "1", "Direct page navigation did not navigate to page 1");
            int directPageRowCount = Dashboard.getTableRowCount();
            softAssert.assertTrue(directPageRowCount > 0, "No data rows displayed after direct page navigation");
        } else {
            softAssert.assertFalse(Dashboard.isPreviousPageEnabled(), "Previous page should be disabled on single page");
            softAssert.assertEquals(initialPage, "1", "Single page dataset should show page 1");
        }

        Dashboard.selectDateRange("Last 7 Days");
        if (Dashboard.isPaginationDisplayed()) {
            String filteredPage = Dashboard.getCurrentPageNumber();
            softAssert.assertNotEquals(filteredPage, "0", "Pagination state invalid after applying filter");
        }
        int filteredRowCount = Dashboard.getTableRowCount();
        softAssert.assertTrue(filteredRowCount >= 0, "Table row count should be non-negative after applying filter");

        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
