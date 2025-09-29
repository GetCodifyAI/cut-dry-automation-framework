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

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-1892")
    public void verifySupplierPortalDraftCountDisplay() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        
        Login.navigateToInternalToolsPage();
        softAssert.assertTrue(Login.isUserNavigatedToInternalTools(), 
            "Failed to navigate to internal tools login page");
        
        Login.selectDistributorFromQuickLinks("Independent Foods Co");
        Login.clickLoginAsSupplierButton();
        softAssert.assertTrue(Dashboard.isUserNavigatedToSupplierDashboard(), 
            "Login error - user not navigated to supplier dashboard");
        
        softAssert.assertTrue(Dashboard.isDraftsMenuItemVisible(), 
            "Drafts menu item should be visible in supplier navigation menu");
        
        int badgeCount = Dashboard.getDraftsBadgeCount();
        softAssert.assertTrue(badgeCount >= 2 && badgeCount <= 4 || badgeCount > 1000, 
            "Draft count badge should display 2-4 customer drafts or show high count like 1K");
        
        softAssert.assertTrue(Dashboard.isDraftsBadgeStyleCorrect(), 
            "Badge should have modernSalmon background, proper dimensions, white text");
        
        softAssert.assertTrue(Dashboard.isSupplierSpecificDraftLogic(), 
            "Badge count should reflect only drafts from customers linked to current supplier");
        
        Dashboard.clickDraftsMenuItem();
        softAssert.assertTrue(Draft.isUserNavigatedToDrafts(), 
            "Drafts page should load successfully");
        
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsersAtOnce();
    }
}
