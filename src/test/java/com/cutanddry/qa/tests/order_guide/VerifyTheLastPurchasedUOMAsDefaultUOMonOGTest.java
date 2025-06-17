package com.cutanddry.qa.tests.order_guide;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.Customer;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyTheLastPurchasedUOMAsDefaultUOMonOGTest extends TestBase {
    static User user;
    static String DP = "John Gross";
    static String customerId = "01165";
    static String itemCode = "222206";


    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-107")
    public void verifyTheLastPurchasedUOMAsDefaultUOMonOG() throws InterruptedException {
        String itemName;
        SoftAssert softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");
        Login.navigateToDistributorPortal(DP);
        Dashboard.navigateToCustomers();
        softAssert.assertTrue(Customer.isNavigatedToCustomerPage(),"navigation error");
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId),"search error");
        Customer.clickOnOrderGuide(customerId);
        Customer.searchItemOnOrderGuide(itemCode);
        itemName = Customer.getItemNameFirstRow();
        softAssert.assertTrue(Customer.getItemNameFirstRow().contains(itemName),"item mismatch");
        Customer.clickOnUnitEach(itemCode);
        Customer.increaseFirstRowQtyByOneInDist();
        Customer.checkoutItemsDist();
        Customer.submitOrderWithoutReachMinimum();
        Customer.clickClose();
        Customer.searchCustomerByCode(customerId);
        Customer.clickOnCustomerCode(customerId);
        Customer.clickOnOrderGuideInProfile();
        Customer.searchItemOnOrderGuide(itemCode);
        Customer.ClickOnMultiUomDD(itemCode);
        itemName = Customer.getItemNameFirstRow();
        softAssert.assertTrue(Customer.getItemNameFirstRow().contains(itemName),"item mismatch");
        softAssert.assertEquals(Customer.getUnitType(),"Each","unit mismatch");
        Customer.ClickOnMultiUomDD(itemCode);
        Customer.clickOnUnitCase(itemCode);
        Customer.increaseFirstRowQtyByOneInDist();
        Customer.checkoutItemsDist();
        Customer.submitOrderWithoutReachMinimum();
        Customer.clickClose();
        Customer.searchCustomerByCode(customerId);
        Customer.clickOnCustomerCode(customerId);
        Customer.clickOnOrderGuideInProfile();
        Customer.searchItemOnOrderGuide(itemCode);
        Customer.ClickOnMultiUomDD(itemCode);
        itemName = Customer.getItemNameFirstRow();
        softAssert.assertTrue(Customer.getItemNameFirstRow().contains(itemName),"item mismatch");
        softAssert.assertEquals(Customer.getUnitType(),"Case","unit mismatch");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
