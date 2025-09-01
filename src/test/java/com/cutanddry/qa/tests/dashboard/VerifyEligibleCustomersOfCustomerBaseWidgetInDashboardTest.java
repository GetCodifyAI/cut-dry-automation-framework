package com.cutanddry.qa.tests.dashboard;

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

public class VerifyEligibleCustomersOfCustomerBaseWidgetInDashboardTest extends TestBase {
    static User user;
    static String useActiveCustomerTag = "Use Active Customer Tag";
    static String useEligibleForCutDryTag = "Use Eligible for Cut+Dry Tag";
    static String useCustomCount = "Use Custom Count";
    static String eligibleForCutDryTag = "Eligible for C+D";
    static String activeCustomersTag = "Active Customers";
    static String eligibleForCD,activeCustomers,eligibleForCD2;

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-1604")
    public void VerifyEligibleCustomersOfCustomerBaseWidgetInDashboard() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        Assert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");
        Dashboard.navigateToCompanySettings();
        softAssert.assertTrue(Settings.isCompanySettingsTextDisplayed(),"navigation to company settings error");
        Settings.clickEligibleCountForCutDry(useCustomCount);
        Settings.enterEligibleCountForCutDry(useCustomCount,"100");
        Settings.clickSaveChanges();
        Dashboard.clickOnDashboard();
        Dashboard.refreshDashBoardPage();
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"dashboard navigation error");
        eligibleForCD = Dashboard.getCustomerValue(eligibleForCutDryTag);
        softAssert.assertEquals(eligibleForCD,"100");

        Dashboard.navigateToCompanySettings();
        softAssert.assertTrue(Settings.isCompanySettingsTextDisplayed(),"navigation to company settings error");
        Settings.clickEligibleCountForCutDry(useActiveCustomerTag);
        Settings.clickSaveChanges();

        Dashboard.clickOnDashboard();
        Dashboard.refreshDashBoardPage();
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"dashboard navigation error");
        eligibleForCD2 = Dashboard.getCustomerValue(eligibleForCutDryTag);
        activeCustomers = Dashboard.getCustomerValue(activeCustomersTag);
        softAssert.assertEquals(eligibleForCD2,activeCustomers,"eligible count and active customer count not equal");

        Dashboard.navigateToCompanySettings();
        softAssert.assertTrue(Settings.isCompanySettingsTextDisplayed(),"navigation to company settings error");
        Settings.clickEligibleCountForCutDry(useEligibleForCutDryTag);
        Settings.clickSaveChanges();

        Dashboard.navigateToCustomers();
        softAssert.assertTrue(Customer.isCustomersTextDisplayed(),"customer section not display");
        Customer.clickOnCustomers(5);

        Customer.clickBulkActions();
        Customer.clickUpdateEligibility();
        softAssert.assertTrue(Customer.isUpdateEligibilityTextDisplay(),"update eligibility text display error");
        Customer.clickEligibilityOption();
        Customer.clickSave();
        softAssert.assertTrue(Pay.isSuccessPopUpDisplayed(),"update error");
        Pay.clickOkPopUp();

        Dashboard.clickOnDashboard();
        Dashboard.refreshDashBoardPage();
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"dashboard navigation error");
        eligibleForCD = Dashboard.getCustomerValue(eligibleForCutDryTag);
        softAssert.assertEquals(eligibleForCD,"5","customer eligibility error ");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
