package com.cutanddry.qa.tests.catalog;


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

public class VerifyTheCashBackValueTagIsDisplayedWithOtherItemTagsTest extends TestBase {
    static User user;
    String searchItemCode = "200510";
    String CUSTOMER_CODE = "21259";

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-1314")
    public void VerifyTheCashBackValueTagIsDisplayedWithOtherItemTags() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        Dashboard.isUserNavigatedToDashboard();
        Assert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");
        Dashboard.navigateToCatalog();
        Assert.assertTrue(Catalog.isUserNavigatedToCatalog(),"navigation error");
        Catalog.clickOnPreviewCatalog();
        Assert.assertTrue(Catalog.isNavigatedToPreviewCatalog(),"navigation to preview catalog error");
        Catalog.selectFirstEditItem();
        Catalog.navigateToPricingAndPromotions();
        Catalog.clickOnSale();
        Catalog.clickNewArrival();
        Catalog.saveChanges();
        softAssert.assertTrue(Catalog.successOverlayDisplayed(),"Error in saving changes");
        // Navigate to Customers page
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(CUSTOMER_CODE);
        Customer.clickOnOrderGuide(CUSTOMER_CODE);
        Customer.searchItemOnOrderGuide(searchItemCode);
        softAssert.assertTrue(Catalog.isOnSaleTagDisplayed(),"On sale tag not displayed");
        softAssert.assertTrue(Catalog.isNewTagDisplayed(),"New tag not displayed");
        softAssert.assertTrue(Catalog.areAllTagsDisplayedWithProperAlignment(),"Tags are not aligned properly");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}