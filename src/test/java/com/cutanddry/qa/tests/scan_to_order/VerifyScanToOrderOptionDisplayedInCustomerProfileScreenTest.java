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

public class VerifyScanToOrderOptionDisplayedInCustomerProfileScreenTest extends TestBase{

    static User user;
    static String DP = "QA ONLY : test distributor";
    static String CustomerCode = "21259";
    static String featureName = "scan_to_order";
    static String companyID = "46017666";


    @BeforeMethod
    public void setup() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-1108")
    public static void VerifyScanToOrderOptionDisplayedInCustomerProfileScreen() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");
        Login.navigateToGateKeeperAdmin();
        Login.updateCompanyIDs(featureName,companyID);
        Login.navigateToDistributorPortal(DP);
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(CustomerCode);
        softAssert.assertTrue(Customer.isNavigatedToCustomerPage(),"Error in navigating to customer Page");
        String BusinessName = Customer.getBusinessNameFromCustomers(CustomerCode);
        Customer.SelectCustomer(CustomerCode);
        softAssert.assertTrue(Customer.isCustomerProfileDisplayed(BusinessName),"Error in Navigating to Customer Profile");

        softAssert.assertTrue(Customer.isCustomerProfileScreenScanToOrderButtonDisplayed(),"Customer Profile Screen Scan To order button isn't displayed");
        Customer.navigateFromCustomerProfileScreenToScanToOrderScreen();
        softAssert.assertTrue(ScanToOrder.isNavigatedToScanToOrderPage(),"Error in navigating to scan to order screen");

        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }





}
