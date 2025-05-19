package com.cutanddry.qa.tests.catalog_access;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.CustomerData;
import com.cutanddry.qa.functions.*;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyTheCatalogAccessFeatureOnUITest extends TestBase {
    static User user;
    SoftAssert softAssert;
    String DistributorName = CustomerData.DISTRIBUTOR_NAME_IFC;
    String CustomerCode = CustomerData.CUSTOMER_CODE2;

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-396")
    public void VerifyTheCatalogAccessFeatureOnUI() throws InterruptedException {
        softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        Assert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");
        Login.navigateToDistributorPortal(DistributorName);

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(CustomerCode);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(CustomerCode),"customer not found");
        Customer.SelectCustomer(CustomerCode);
        softAssert.assertTrue(Customer.isCatalogAccessDisplay(),"catalog access not display");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) throws InterruptedException {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }

}
