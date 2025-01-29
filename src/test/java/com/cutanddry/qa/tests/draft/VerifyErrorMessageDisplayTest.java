package com.cutanddry.qa.tests.draft;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.DraftsData;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Draft;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyErrorMessageDisplayTest extends TestBase {
    static User user;
    static String testData = DraftsData.SEARCH_DATA;
    static String createdByDropDown = DraftsData.CREATED_BY_DROP_DOWN;
    static String typeDropDown = DraftsData.TYPE_DROP_DOWN;
    static String typeDropDownOption = DraftsData.TYPE_DROP_DOWN_OPTION;
    static String createdByDropDownOption2 = DraftsData.CREATED_BY_DROP_DOWN_OPTION2;

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-987")
    public void VerifyErrorMessageDisplay() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        Dashboard.isUserNavigatedToDashboard();
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");
        Dashboard.navigateToDrafts();
        softAssert.assertTrue(Draft.isUserNavigatedToDrafts(),"navigation error");

        Draft.clickDropDownFilter(createdByDropDown);
        Draft.clickDropDownFilterOption(createdByDropDownOption2);
        Draft.clickDropDownFilter(typeDropDown);
        Draft.clickDropDownFilterOption(typeDropDownOption);
        softAssert.assertTrue(Draft.isDraftsDeleted(),"No recode available message error");
        softAssert.assertTrue(Draft.isResultCountDisplayed(String.valueOf(0)),"result count error");
        Draft.clickClearFilter();

        Draft.typeOnSearchDrafts(testData);
        softAssert.assertTrue(Draft.isDraftsDeleted(),"No recode available message error");
        softAssert.assertTrue(Draft.isResultCountDisplayed(String.valueOf(0)),"result count error");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
