package com.cutanddry.qa.tests.draft;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.*;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

public class VerifySupplierPortalDraftCountDisplayTest extends TestBase {
    static User user;
    String DistributorName ="47837013 - Brandon IFC Cut+Dry Agent - Independent Foods Co";

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-1892")
    public void verifySupplierPortalDraftCountDisplay() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();

        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");
        Login.navigateToDistributorPortal(DistributorName);
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "Login error - user not navigated to supplier dashboard");
        
        softAssert.assertTrue(Dashboard.isDraftsOptionVisible(), "Drafts menu item should be visible in supplier navigation menu");
        Dashboard.navigateToDrafts();
        int badgeCount = Dashboard.getDraftsBadgeCount();
        softAssert.assertEquals(Draft.getActiveDraftCount(),badgeCount, "Draft count mismatch");
        softAssert.assertTrue(Draft.isUserNavigatedToDrafts(), "Drafts page should load successfully");
        
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsersAtOnce();
    }
}
