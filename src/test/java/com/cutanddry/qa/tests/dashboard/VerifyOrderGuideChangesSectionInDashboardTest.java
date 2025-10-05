package com.cutanddry.qa.tests.dashboard;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.*;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyOrderGuideChangesSectionInDashboardTest extends TestBase {
    static User user;

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-2075")
    public void VerifyOrderGuideChangesSectionInDashboard() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();

        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "Login error - user not navigated to distributor dashboard");

        softAssert.assertTrue(Dashboard.isOrderGuideChangesSectionDisplayed(), "Order Guide Changes section is not displayed on dashboard");

        softAssert.assertTrue(Dashboard.isAddedItemDisplayed(), "Added Item changes are not displayed");
        softAssert.assertTrue(Dashboard.isRemovedItemDisplayed(), "Removed Item changes are not displayed");

        softAssert.assertTrue(Dashboard.isOrderGuideChangesTooltipDisplayed(), "Tooltip icon is not displayed for Order Guide Changes");

        Dashboard.selectDuration("Last 7 Days");
        softAssert.assertTrue(Dashboard.isDashboardDurationChanged("Last 7 Days"), "Duration not changed to Last 7 Days");

        Dashboard.clickOrderGuideChangesViewAll();
        softAssert.assertTrue(Dashboard.isOrderGuideChangesPageDisplayed(), "Order Guide Changes detail page not displayed after clicking View All");

        Dashboard.selectTimeRangeInOrderGuideChanges("Last 30 Days");
        Thread.sleep(2000);

        Dashboard.selectCustomerInOrderGuideChanges("Restaurant(Test)");
        Thread.sleep(2000);

        Dashboard.selectSalespersonInOrderGuideChanges("All Salespersons");
        Thread.sleep(2000);

        softAssert.assertTrue(Dashboard.isOrderGuideChangesTableDisplayed(), "Order Guide Changes table is not displayed on detail page");

        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
