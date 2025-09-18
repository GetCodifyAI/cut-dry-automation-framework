package com.cutanddry.qa.tests.dashboard;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyDownloadReportButtonFunctionalityInFirstTimeOrdersTest extends TestBase {
    static User user;
    String testEmail = "test@cutanddry.com";

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-1815")
    public void verifyDownloadReportButtonFunctionalityInFirstTimeOrders() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "The user is unable to land on the Dashboard page.");
        
        Dashboard.navigateToFirstTimeOrdersTableView();
        softAssert.assertTrue(Dashboard.isFirstTimeOrdersTableViewDisplayed(), "First Time Orders table view is not displayed.");
        
        softAssert.assertTrue(Dashboard.isDownloadReportButtonVisible(), "Download Report button is not visible next to the Salesperson filter.");
        
        Dashboard.selectDateRange("Last 30 Days");
        softAssert.assertTrue(Dashboard.isFirstTimeOrdersDataUpdated(), "Table data did not update for the selected date range.");
        
        Dashboard.selectSalesperson("All Salespersons");
        softAssert.assertTrue(Dashboard.isFirstTimeOrdersDataFiltered(), "Table data did not filter for the selected salesperson.");
        
        Dashboard.clickDownloadReportButton();
        softAssert.assertTrue(Dashboard.isExportModalDisplayed(), "Export modal did not open with title 'Export First Time Orders File'.");
        
        Dashboard.inputEmailInExportModal(testEmail);
        Dashboard.clickConfirmInExportModal();
        softAssert.assertTrue(Dashboard.isGeneratingReportPopupDisplayed(), "Generating Report popup is not displayed.");
        
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            takeScreenshotOnFailure(result);
            System.out.println("Screenshot captured for failed test case.");
        }
        closeAllBrowsers();
        System.out.println("Browser closed successfully.");
    }
}
