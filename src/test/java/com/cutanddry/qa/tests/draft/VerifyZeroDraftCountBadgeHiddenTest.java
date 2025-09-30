package com.cutanddry.qa.tests.draft;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.*;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

public class VerifyZeroDraftCountBadgeHiddenTest extends TestBase {
    static User user;
    static String DistributorName = "502475015 - DharshiHaute Grub - Haute Grub";

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-1893")
    public void verifyZeroDraftCountBadgeHidden() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();

        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");
        Login.navigateToDistributorPortal(DistributorName);
        
        softAssert.assertTrue(Dashboard.isDraftsOptionVisible(), "Drafts menu item should be visible in navigation");
        softAssert.assertFalse(Dashboard.isDraftsBadgeVisible(), "No badge should be displayed next to Drafts menu item when no active drafts exist");
        Dashboard.navigateToDrafts();
        softAssert.assertTrue(Draft.isUserNavigatedToDrafts(), "Drafts page should load successfully");
        softAssert.assertTrue(Draft.isEmptyStateDisplayed(), "Drafts page should show empty state with no drafts listed");
        
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsersAtOnce();
    }
}
