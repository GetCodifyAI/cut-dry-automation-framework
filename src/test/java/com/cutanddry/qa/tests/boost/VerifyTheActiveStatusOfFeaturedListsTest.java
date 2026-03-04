package com.cutanddry.qa.tests.boost;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.Boost;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyTheActiveStatusOfFeaturedListsTest extends TestBase {
    static User user;
    static String featuredListName = "Jordan Banana Promo List!";

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-4509")
    public void VerifyTheActiveStatusOfFeaturedLists() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        Dashboard.isUserNavigatedToDashboard();
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");
        Dashboard.navigateToBoost();
        softAssert.assertTrue(Boost.isUserNavigatedToBoost(),"navigate to boost error");
        Boost.navigateToFeaturedListTab();
        Boost.viewAndConfigure(featuredListName);
        softAssert.assertTrue(Boost.itemConfigureOverlayDisplayed(),"Configure items overlay not displayed");
        Boost.selectConfigureStatus("Active");
        Boost.clickCopyPromoUrl();
        softAssert.assertTrue(Boost.isCopiedToClipboardDisplayed(),"Copied to Clipboard popup not displayed");
        Boost.clickOkCopied();
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
