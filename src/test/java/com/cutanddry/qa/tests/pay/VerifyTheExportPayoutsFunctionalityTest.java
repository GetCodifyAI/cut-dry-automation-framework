package com.cutanddry.qa.tests.pay;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.functions.Pay;
import com.cutanddry.qa.functions.Reports;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.cutanddry.qa.tests.pay.ValidateTheDownloadStatementTest.downloadPath;

public class VerifyTheExportPayoutsFunctionalityTest extends TestBase {
    static User user;

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-900")
    public void VerifyTheExportPayoutsFunctionality() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "The user is unable to land on the Dashboard page.");

        Dashboard.navigateToPay();
        Pay.clickOnPayouts();
        softAssert.assertTrue(Pay.isPayoutBtnSelected(), "The user is unable to land on the payouts tab.");
        Pay.clickExportPayout();
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        Reports.cleanUpDownloads(downloadPath);
        closeAllBrowsers();
    }
}
