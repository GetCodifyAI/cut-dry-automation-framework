package com.cutanddry.qa.tests.draft;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.DraftsData;
import com.cutanddry.qa.functions.*;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

public class VerifyCustomerPortalDraftCountDisplayTest extends TestBase {
    static User user;
    String customerCode = DraftsData.RESTAURANT_USER_CODE;

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-1891")
    public void verifyCustomerPortalDraftCountDisplay() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();

        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(), "login error");
        Login.navigateToRestaurantPortal(customerCode);
        softAssert.assertTrue(Dashboard.isDraftsOptionVisible(), "Drafts menu item should be visible in customer navigation sidebar");
        softAssert.assertTrue(Dashboard.isDraftsBadgeVisible(), "Draft count badge should be displayed next to Drafts menu item");
        int badgeCount = Dashboard.getDraftsBadgeCount();
        softAssert.assertTrue(badgeCount > 0, "Badge count should be greater than 0");
        Dashboard.navigateToDrafts();
        softAssert.assertTrue(Draft.isUserNavigatedToDrafts(), "Drafts page should load successfully");
        softAssert.assertEquals(Draft.getActiveDraftCount(), badgeCount, "Badge count should match the number of active drafts");

        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsersAtOnce();
    }
}
