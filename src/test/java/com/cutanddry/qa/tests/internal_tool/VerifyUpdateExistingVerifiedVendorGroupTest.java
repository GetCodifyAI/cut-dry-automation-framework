package com.cutanddry.qa.tests.internal_tool;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.InternalTools;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyUpdateExistingVerifiedVendorGroupTest extends TestBase {
    static User user;
    String name = "Updated Test Group";
    String description = "Updated description for testing";
    String dp1 = "Cheese Importers";
    String dp2 = "A&B Distributors";
    String dp3 = "Ace Frozen Fruit Distribution";
    String status = "Enabled";


    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-4497")
    public void VerifyUpdateExistingVerifiedVendorGroup() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        Assert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");
        Login.navigateToInternalToolsPage();
        InternalTools.navigateToDPGroupManager();
        softAssert.assertTrue(InternalTools.isDPGroupManageTextDisplayed(),"DP Group Manage Text not Displayed");
        InternalTools.clickGroupEdit();
        softAssert.assertTrue(InternalTools.isEditDPGroupTextDisplayed(),"Edit DPGroup Text not Displayed");
        InternalTools.enterGroupName(name);
        InternalTools.enterDescription(description);
        InternalTools.clickaAttachedDPs(dp3);
        InternalTools.clickUpdateDPGroup();
        softAssert.assertTrue(InternalTools.isSuccessPopUpDisplayed(),"change not save");
        InternalTools.clickOKOnSucessOverlay();
        Thread.sleep(4000);
        softAssert.assertTrue(InternalTools.isCompanyNameDisplay(name), "Company name not displayed correctly (change not saved)");
        softAssert.assertTrue(InternalTools.isCompanyDescriptionDisplay(description), "Company description not displayed correctly (change not saved)");
        softAssert.assertTrue(InternalTools.isCompanySwitchingDisplay(status), "Company switching status not displayed correctly (change not saved)");
        softAssert.assertTrue(InternalTools.isVendorsDisplay(dp1), "Vendor not displayed correctly (change not saved)");
        softAssert.assertTrue(InternalTools.isVendorsDisplay(dp2), "Vendor not displayed correctly (change not saved)");
        softAssert.assertTrue(InternalTools.isVendorsDisplay(dp3), "Vendor not displayed correctly (change not saved)");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result){
        takeScreenshotOnFailure(result);
        closeAllBrowsers();

    }



}
