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

public class VerifyRemovingFeaturedListTest extends TestBase {
    static User user;
    String FeaturedListName = "TestList";


    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-378")
    public void VerifyRemovingFeaturedList() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        Dashboard.isUserNavigatedToDashboard();
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");
        Dashboard.navigateToBoost();
        softAssert.assertTrue(Boost.isUserNavigatedToBoost(),"navigate to boost error");
        Boost.clickSuggestiveSales();
        Boost.navigateToFeaturedListTab();

        // Pre-request
        Boost.createNewFeaturedList();
        softAssert.assertTrue(Boost.isCrateListOverlayDisplayed(),"Error in displaying create list overlay");
        Boost.enterFeaturedListName(FeaturedListName);
        Boost.submitEnteredListName();
        softAssert.assertTrue(Boost.addedListNameDisplayed(FeaturedListName),"Error in displaying added list name");

        Boost.deleteFeaturedList(FeaturedListName);
        softAssert.assertTrue(Boost.deleteFeaturedListOverlayDisplayed(),"Erro in displaying delete List Overlay");
        Boost.deleteFeaturedListFromOverlay();
        softAssert.assertFalse(Boost.deletedListDisplayed(FeaturedListName),"Error in displaying added list name");

        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }


}
