package com.cutanddry.qa.tests.settings;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.*;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyOperatorPortalOptionInExcludeStockIndicatorsSectionTest extends TestBase {
    static User user;
    static String supplierName = "Bonollo Provisions";

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-1950")
    public void verifyOperatorPortalOptionInExcludeStockIndicatorsSection() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();

        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        Assert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(), "Login error - user not navigated to dashboard");

        Login.navigateToConfigSupplier();
        Assert.assertTrue(ConfigSupplier.isUserNavigatedToConfigSupplier(), "Navigation error - not navigated to Config Supplier");

        InternalTools.clickOnInternalToolCompanyEditDetails(supplierName);
        InternalTools.navigateToOrderingSettingsTab();

        softAssert.assertTrue(ConfigSupplier.isOperatorPortalCheckboxDisplayed(), 
            "Operator Portal checkbox is not displayed in the Exclude Stock indicators section");

        softAssert.assertTrue(ConfigSupplier.isSelectedUOMDisplayed(), 
            "Selected UOM field is not displayed in the Exclude Stock indicators section");

        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
