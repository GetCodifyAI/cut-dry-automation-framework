package com.cutanddry.qa.tests.boost;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.Boost;
import com.cutanddry.qa.functions.Customer;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyTheInactiveStatusOfFeaturedListsTest extends TestBase {
    static User user;
    static String featuredListName = "Jordan Banana Promo List!";

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-836")
    public void VerifyTheInactiveStatusOfFeaturedLists() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        Dashboard.isUserNavigatedToDashboard();
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");
        Thread.sleep(3000);

        Dashboard.navigateToBoost();
        softAssert.assertTrue(Boost.isUserNavigatedToBoost(),"navigate to boost error");
        Boost.navigateToFeaturedListTab();
        Boost.viewAndConfigure(featuredListName);
        softAssert.assertTrue(Boost.itemConfigureOverlayDisplayed(),"Configure items overlay not displayed");
        Boost.selectConfigureStatus("Inactive");
        Boost.clickCopyPromoUrl();
        softAssert.assertTrue(Boost.isCopiedToClipboardDisplayed(),"Copied to Clipboard popup not displayed");
        Boost.clickOkCopied();
        Boost.goToPromoUrl();
        softAssert.assertFalse(Customer.isCatalogFilterSectionDisplayed(featuredListName),"catalog filter not display");
        softAssert.assertFalse(Boost.isCatalogFilterSectionResultDisplayed(featuredListName),"Featured List promo page not displayed with items");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
