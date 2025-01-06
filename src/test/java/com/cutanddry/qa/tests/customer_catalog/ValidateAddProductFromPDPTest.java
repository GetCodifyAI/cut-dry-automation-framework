package com.cutanddry.qa.tests.customer_catalog;

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

public class ValidateAddProductFromPDPTest extends TestBase {
    static User user;
    String CustomerCode = "37631";
    static String itemName = "J. Hungerford Smith Chocolate Cone Coating";


    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-686")
    public void ValidateAddProductFromPDP() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");
        Dashboard.navigateToCustomers();
        softAssert.assertTrue(Customer.isCustomersTextDisplayed(),"customer section not display");
        Customer.searchCustomerByCode(CustomerCode);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(CustomerCode),"customer not found");
        Customer.clickOnOrderGuide(CustomerCode);
        Customer.goToCatalog();
        Customer.searchItemOnCatalog(itemName);
        softAssert.assertTrue(Customer.getFirstElementFrmSearchResults(itemName).toLowerCase().contains(itemName.toLowerCase()), "item not found");
        Customer.clickOnProduct(itemName);
        softAssert.assertTrue(Customer.isProductDetailsDisplayed(),"navigation error");
        Customer.clickAddToCartPDP();
        Customer.clickCheckOutPDP();
        softAssert.assertTrue(Customer.isReviewOrderTextDisplayed(),"navigation error for review order");
        Customer.submitOrder();
        softAssert.assertTrue(Customer.isOrderSubmitSuccessfully(),"order not submit ");
        Customer.clickClose();
        Customer.clickOnBack();
        Customer.clickRemoveOrderGuide();
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result){
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }

}
