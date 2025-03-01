package com.cutanddry.qa.tests.order_guide;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.GatekeeperData;
import com.cutanddry.qa.functions.Customer;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class DistributorInitialSetUpTest extends TestBase {
    static User user;
    static String featureName = GatekeeperData.FEATURE_NAME_VOICE_ORDER;
    static String companyId = GatekeeperData.COMPONY_ID;
    String CustomerCode = "37631";


    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test
    public void DistributorInitialSetUp() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");
        Login.navigateToGateKeeperAdmin();
        Login.updateCompanyIDs(featureName,companyId);

        Login.switchIntoNewTab();
        Login.navigateToDistributor();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(CustomerCode);
        Customer.SelectCustomer(CustomerCode);
        Customer.enableCatalogAccess();
        softAssert.assertTrue(Customer.catalogAccessEnabled(),"Error in catalog access enable displaying");

        /*Customer.clickOnEditAccHolds();
        Customer.clickOnAccDropdown();
        Customer.clickOnNone();
        Customer.clickOnSave();*/
        Customer.disableAccHolds();
        softAssert.assertTrue(Customer.isNoneSelected(),"acc none select error");

        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsersAtOnce();
    }
}
