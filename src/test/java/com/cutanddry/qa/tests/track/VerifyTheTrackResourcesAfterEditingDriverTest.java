package com.cutanddry.qa.tests.track;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.functions.Track;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyTheTrackResourcesAfterEditingDriverTest extends TestBase {
    static User user;
    static String name = "Jordan Harper "+ generateDynamicValue();
    static String phone = "14155300000";

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-525")
    public void VerifyTheTrackResourcesAfterEditingDriver() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");
        Dashboard.navigateToTrackResources();
        softAssert.assertTrue(Track.isResourcesTextDisplayed(),"navigation to track resources error");
        Track.clickOnDrivers();
        softAssert.assertTrue(Track.isAddDriversBtnDisplayed(),"navigation to drivers error");

        // Pre-Request
        Track.removeExistUser(name);
        Track.clickOnAddDrivers();
        softAssert.assertTrue(Track.isAddDriverPopupDisplayed(),"add driver popup error");
        Track.enterName(name);
        Track.enterPhone(phone);
        Track.clickOnInviteUser();
        Track.clickOK();

        Track.clickOnEditUser(name);
        softAssert.assertTrue(Track.isEditUserPopupDisplayed(),"pop display error");
        Track.enterPhone(phone);
        Track.clickOnSaveChanges();
        softAssert.assertTrue(Track.isUserDisplayed(name),"add driver error");

        //Post request
        Track.clickOnEditUser(name);
        softAssert.assertTrue(Track.isEditUserPopupDisplayed(),"pop display error");
        Track.clickOnRemoveUserLabel();
        Track.clickOnRemoveUser();
        Track.clickOK();

        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
