package com.cutanddry.qa.tests.internal_tool;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.CustomerProfileData;
import com.cutanddry.qa.functions.Customer;
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

public class VerifyCreateNewVerifiedVendorGroupTest extends TestBase {
    static User user;
    String name = "Test Group Alpha";
    String description = "Test group for company switching validation";
    String dp1 = "Cheese Importers";
    String dp2 = "A&B Distributors";
    String status = "Enabled";


    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-4496")
    public void VerifyCreateNewVerifiedVendorGroup() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        Assert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");
        Login.navigateToInternalToolsPage();
        InternalTools.navigateToDPGroupManager();
        softAssert.assertTrue(InternalTools.isDPGroupManageTextDisplayed(),"DP Group Manage Text not Displayed");
        InternalTools.clickCreateButton();
        softAssert.assertTrue(InternalTools.isCreateNewDPGroupTextDisplayed(),"Create New DPGroup Text not Displayed");
        InternalTools.enterGroupName(name);
        InternalTools.enterDescription(description);
        InternalTools.clickaAttachedDPs(dp1);
        InternalTools.clickaAttachedDPs(dp2);
        InternalTools.clickAllowCompanySwitching();
        InternalTools.clickGroupCreate();
        softAssert.assertTrue(InternalTools.isSuccessPopUpDisplayed(),"change not save");
        InternalTools.clickOKOnSucessOverlay();
        Thread.sleep(4000);
        softAssert.assertTrue(InternalTools.isCompanyNameDisplay(name), "Company name not displayed correctly (change not saved)");
        softAssert.assertTrue(InternalTools.isCompanyDescriptionDisplay(description), "Company description not displayed correctly (change not saved)");
        softAssert.assertTrue(InternalTools.isCompanySwitchingDisplay(status), "Company switching status not displayed correctly (change not saved)");
        softAssert.assertTrue(InternalTools.isVendorsDisplay(dp1), "Vendor not displayed correctly (change not saved)");
        softAssert.assertTrue(InternalTools.isVendorsDisplay(dp2), "Vendor not displayed correctly (change not saved)");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result){
        takeScreenshotOnFailure(result);
        closeAllBrowsers();

    }



}
