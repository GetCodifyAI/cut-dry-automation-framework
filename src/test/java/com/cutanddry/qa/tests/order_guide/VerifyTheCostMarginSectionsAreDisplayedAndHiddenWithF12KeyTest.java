package com.cutanddry.qa.tests.order_guide;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.CustomerData;
import com.cutanddry.qa.functions.Customer;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyTheCostMarginSectionsAreDisplayedAndHiddenWithF12KeyTest extends TestBase {
    static User user;
    String customerId = CustomerData.CUSTOMER_ID;
    static String distributor = CustomerData.DISTRIBUTOR_AFFILIATED;
    static String costColumn = CustomerData.COST_COLUMN;
    static String marginColumn = CustomerData.MARGIN_COLUMN;

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();


    }

    @Test(groups = "DOT-TC-403")
    public void VerifyTheCostMarginSectionsAreDisplayedAndHiddenWithF12Key() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(), "login error");

        Login.navigateToDistributorPortal(distributor);
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Unable to find the customer Id");
        Customer.clickOnOrderGuide(customerId);
        Customer.clickF12HotKey();
        softAssert.assertFalse(Customer.isOrderColumnDisplay(costColumn),"column not hidden");
        softAssert.assertFalse(Customer.isOrderColumnDisplay(marginColumn),"column not hidden");
        Customer.clickF12HotKey();
        softAssert.assertTrue(Customer.isOrderColumnDisplay(costColumn),"column not display");
        softAssert.assertTrue(Customer.isOrderColumnDisplay(marginColumn),"column not display");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }


}
