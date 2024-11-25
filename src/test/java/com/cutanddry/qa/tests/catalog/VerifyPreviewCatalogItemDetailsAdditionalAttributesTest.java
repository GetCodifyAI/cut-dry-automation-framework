package com.cutanddry.qa.tests.catalog;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.Catalog;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyPreviewCatalogItemDetailsAdditionalAttributesTest extends TestBase {
    static User user;
    String certificationType = "Provenance Certifications";
    String certificationOption = "Buy American";

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-612")
    public void VerifyThePreviewCatalogItemDetailsAdditionalAttributes() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        Dashboard.isUserNavigatedToDashboard();
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");
        Dashboard.navigateToCatalog();
        softAssert.assertTrue(Catalog.isUserNavigatedToCatalog(),"navigation error");
        Catalog.clickOnPreviewCatalog();
        softAssert.assertTrue(Catalog.isNavigatedToPreviewCatalog(),"navigation to preview catalog error");
        Catalog.selectFirstItem();
        softAssert.assertTrue(Catalog.isProductOverviewDisplayed(),"select product error");
        Catalog.clickOnEditProduct();
        Catalog.navigateToAdditionalAttributes();
        softAssert.assertTrue(Catalog.isAdditionalAttributesTabDisplayed(),"Error in displaying Additional attributes tab");
        Catalog.clearCertification(certificationType);
        Catalog.saveChanges();
        softAssert.assertTrue(Catalog.successOverlayDisplayed(),"Error in saving item data in catalog");
        Catalog.selectCertification(certificationType, certificationOption);
        Catalog.saveChanges();
        softAssert.assertTrue(Catalog.successOverlayDisplayed(),"Error in saving item data in catalog");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
