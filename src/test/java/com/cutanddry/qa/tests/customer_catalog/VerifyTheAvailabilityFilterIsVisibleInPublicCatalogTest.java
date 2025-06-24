package com.cutanddry.qa.tests.customer_catalog;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.Catalog;
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

public class VerifyTheAvailabilityFilterIsVisibleInPublicCatalogTest extends TestBase {
    static User user;
    static String availability = "Availability";


    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-1478")
    public void VerifyTheAvailabilityFilterIsVisibleInPublicCatalog() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");
        Dashboard.navigateToCatalog();
        Assert.assertTrue(Catalog.isUserNavigatedToCatalog(),"navigation error");
        Catalog.clickOnPreviewCatalog();
        Assert.assertTrue(Catalog.isNavigatedToPreviewCatalog(),"navigation to preview catalog error");
        softAssert.assertTrue(Customer.isCatalogFilterDisplayed(availability),"catalog filter not display");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result){
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }

}
