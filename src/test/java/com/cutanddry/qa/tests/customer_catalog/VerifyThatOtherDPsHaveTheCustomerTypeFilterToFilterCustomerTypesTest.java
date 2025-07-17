package com.cutanddry.qa.tests.customer_catalog;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.Customer;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyThatOtherDPsHaveTheCustomerTypeFilterToFilterCustomerTypesTest extends TestBase {
    static User user;
    String CustomerCode = "10000000";
    String DistributorName ="120984267 - Cut+Dry Agent - BiRite Foodservice Distributors";
    String[] itemCodes = {"600703", "605402", "980001","980019","980020"};


    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-1421")
    public void VerifyThatOtherDPsHaveTheCustomerTypeFilterToFilterCustomerTypes() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        Assert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");

        Login.navigateToDistributorPortal(DistributorName);
        Assert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"navigation error");

        Dashboard.navigateToCustomers();
        softAssert.assertTrue(Customer.isCustomersTextDisplayed(),"customer section not display");
        Customer.searchCustomerByCode(CustomerCode);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(CustomerCode),"customer not found");
        Customer.clickOnOrderGuide(CustomerCode);
        Customer.goToCatalog();
        for (String itemCode : itemCodes) {
            Customer.searchItemOnCatalog(itemCode);
            Thread.sleep(4000);
            softAssert.assertTrue(Customer.isCatalogSearchItemCodeDisplay(itemCode), "Item not found: " + itemCode
            );}

       softAssert.assertAll();}


    @AfterMethod
    public void tearDown(ITestResult result){
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }

}
