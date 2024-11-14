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

public class VerifyTheTrackResourcesAfterAddingNewDriverTest extends TestBase {
    static User user;
    static String name = "Jordan Harper";
    static String phone = "14155321770";

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-524")
    public void VerifyTheTrackResourcesAfterAddingNewDriver() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");
        Dashboard.navigateToTrackResources();
        softAssert.assertTrue(Track.isResourcesTextDisplayed(),"navigation to track resources error");
        Track.clickOnDrivers();
        softAssert.assertTrue(Track.isAddDriversBtnDisplayed(),"navigation to drivers error");
        Track.clickOnAddDrivers();
        softAssert.assertTrue(Track.isAddDriverPopupDisplayed(),"add driver popup error");
        Track.enterName(name);
        Track.enterPhone(phone);
        Track.clickOnInviteUser();
        softAssert.assertTrue(Track.isUserDisplayed(name),"add driver error");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
