package com.cutanddry.qa.tests.scan_to_order;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.Customer;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.functions.ScanToOrder;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyScanToOrderOptionDisplayedInCustomersScreenTest extends TestBase {
    static User user;
    static String DP = "Independent Foods Co";
    static String CustomerCode = "21259";
    static String featureName = "scan_to_order";
    static String companyID = "46017666";

    @BeforeMethod
    public void setup() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-1104")
    public static void VerifyScanToOrderOptionDisplayedInCustomersScreen() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");
        Login.navigateToGateKeeperAdmin();
        Login.updateCompanyIDs(featureName,companyID);
        Login.navigateToDistributorPortal(DP);
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(CustomerCode);
        softAssert.assertTrue(Customer.isNavigatedToCustomerPage(),"Error in navigating to customer Page");
        softAssert.assertTrue(Customer.isScanToOrderBtnDisplayedInCustomers(CustomerCode),"Customers Screen Scan To order button is not displayed");
        Customer.navigateFromCustomerScreenToScanToOrderScreen(CustomerCode);
        softAssert.assertTrue(ScanToOrder.isNavigatedToScanToOrderPage(),"Error in navigating to scan to order screen");

        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }


}
