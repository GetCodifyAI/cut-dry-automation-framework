package com.cutanddry.qa.tests.boost;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.Boost;
import com.cutanddry.qa.functions.Customer;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyTheHiddenStatusOfFeaturedListsOperatorTest extends TestBase {
    static User user;
    static String featuredListName = "Jordan Banana Promo List!";

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-4512")
    public void VerifyTheHiddenStatusOfFeaturedListsOperator() throws InterruptedException {
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
        Boost.selectConfigureStatus("Hidden");
        Boost.clickCopyPromoUrl();
        softAssert.assertTrue(Boost.isCopiedToClipboardDisplayed(),"Copied to Clipboard popup not displayed");
        Boost.clickOkCopied();
        Thread.sleep(3000);

        // Restaurant Flows
        Login.switchIntoNewTab();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        Assert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(), "The user is unable to land on the Dashboard page.");

        Dashboard.navigateToIndependentFoodsCo();
        Dashboard.navigateToOrderGuide();
        Assert.assertTrue(Dashboard.isUserNavigatedToOrderGuide(),"navigation error");

        Customer.goToCatalog();
        softAssert.assertFalse(Customer.isCatalogFilterSectionDisplayed(featuredListName),"catalog filter not display");

        Boost.goToPromoUrl();
        softAssert.assertTrue(Customer.isCatalogBrowseDisplayed(),"promo link navigate error");
        softAssert.assertFalse(Customer.isCatalogFilterSectionDisplayed(featuredListName),"catalog filter not display");
        softAssert.assertTrue(Boost.isCatalogFilterSectionResultDisplayed(featuredListName),"Featured List promo page not displayed with items");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
