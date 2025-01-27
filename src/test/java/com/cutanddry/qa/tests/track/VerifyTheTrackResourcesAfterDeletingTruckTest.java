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

public class VerifyTheTrackResourcesAfterDeletingTruckTest extends TestBase {
    static User user;
    static String name = "Jor Truck"+ generateDynamicValue();

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-530")
    public void VerifyTheTrackResourcesAfterDeletingTruck() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");
        Dashboard.navigateToTrackResources();
        softAssert.assertTrue(Track.isResourcesTextDisplayed(),"navigation to track resources error");
        Track.clickOnTrucks();
        softAssert.assertTrue(Track.isAddTrucksBtnDisplayed(),"navigation to trucks error");
        //Pre-request
        Track.clickOnAddTrucks();
        Track.enterTruckName(name);
        Track.clickOnSaveChanges();

        Track.clickOnEditUser(name);
        softAssert.assertTrue(Track.isEditUserPopupDisplayed(),"edit truck popup error");
        Track.clickOnRemoveUserLabel();
        Track.clickOnRemoveUser();
        softAssert.assertFalse(Track.isUserDisplayed(name),"add driver error");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
