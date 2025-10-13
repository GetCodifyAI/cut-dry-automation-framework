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

import java.math.BigDecimal;

public class VerifyEligibleCustomersOfCustomerBaseWidgetInDashboardTest extends TestBase {
    static User user;
    static String useActiveCustomerTag = "Use Active Customer Tag";
    static String useEligibleForCutDryTag = "Use Eligible for Cut+Dry Tag";
    static String useCustomCount = "Use Custom Count";
    static String eligibleForCutDryTag = "Eligible for C+D";
    static String activeCustomersTag = "Active Customers";
    static String eligibleForCD,activeCustomers,eligibleForCD2,eligibleForCD3;
    static String eligibleOption = "Eligible";
    static String notEligibleOption = "Not Eligible";

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

        Dashboard.clickOnDashboard();
        Dashboard.refreshDashBoardPage();
        eligibleForCD3 = new BigDecimal(Dashboard.getCustomerValue(eligibleForCutDryTag)).subtract(new BigDecimal("5")).stripTrailingZeros().toPlainString();
        Dashboard.navigateToCustomers();
        softAssert.assertTrue(Customer.isCustomersTextDisplayed(),"customer section not display");
        Customer.clickOnCustomers(5);

        Customer.clickBulkActions();
        Customer.clickUpdateEligibility();
        softAssert.assertTrue(Customer.isUpdateEligibilityTextDisplay(),"update eligibility text display error");
        Customer.clickEligibilityOption(notEligibleOption);
        Customer.clickSave();
        softAssert.assertTrue(Pay.isSuccessPopUpDisplayed(),"update error");
        Pay.clickOkPopUp();

        Dashboard.clickOnDashboard();
        Dashboard.refreshDashBoardPage();
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"dashboard navigation error");
        eligibleForCD = Dashboard.getCustomerValue(eligibleForCutDryTag);
        softAssert.assertEquals(eligibleForCD,eligibleForCD3,"customer eligibility error ");

        //Reverting the eligibility
        Dashboard.navigateToCustomers();
        Customer.clickOnCustomers(5);
        Customer.clickBulkActions();
        Customer.clickUpdateEligibility();
        Customer.clickEligibilityOption(eligibleOption);
        Customer.clickSave();

        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
