package com.cutanddry.qa.tests.settings;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.functions.Settings;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyTheFlowOfEditingTheConfigurationSettingsForTheNewlyAddedWarehouse extends TestBase{
    static User user;
    static String nickname = "Test Nickname";

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-856")
    public void VerifyTheFlowOfAddingTheConfigurationSettingsForTheNewlyAddedWarehouse() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        Dashboard.isUserNavigatedToDashboard();
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");
        Dashboard.navigateToTrackSettings();
        softAssert.assertTrue(Settings.isTrackSettingsTextDisplayed(),"navigation error");
        String warehouseName = Settings.generateWarehouseCode();
        Settings.clickBtnManageWarehouse();
        Settings.clickBtnAddNew();
        Settings.typeNewWarehouseName(warehouseName);
        Settings.clickBtnSaveChanges();
        Settings.clickOnWarehouseLocationDropDown();
        Settings.selectWarehouseLocation(warehouseName);
        String lastFiveDigits = warehouseName.substring(warehouseName.length() - 5);
        softAssert.assertTrue(Settings.isWarehouseNameDisplayed(lastFiveDigits),"The warehouse name is not displayed");

        Login.switchIntoNewTab();
        Login.navigateToDistributor();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");
        Dashboard.navigateToTrackSettings();
        softAssert.assertTrue(Settings.isTrackSettingsTextDisplayed(),"navigation error");
        Settings.clickOnWarehouseLocationDropDown();
        Settings.selectWarehouseLocation(warehouseName);
        softAssert.assertTrue(Settings.isWarehouseNameDisplayed(lastFiveDigits),"The warehouse name is not displayed");
        Settings.fillWarehouseName(warehouseName);
        Settings.fillWarehouseNickName(nickname);
        Settings.clickOnSaveChanges();
//        softAssert.assertTrue(Settings.isUpdatedSuccessfullyDisplayed(),"Message not displayed");
        softAssert.assertAll();
    }


    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }

}
