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

public class ValidateAddProductToOrderGuideByClickPDPHeartIconTest extends TestBase {
    static User user;
    String CustomerCode = "37631";
    static String itemName = "J. Hungerford Smith Chocolate Cone Coating";
    static String OrderGuideProductName = "j. hungerford smith chocolate cone coating";
    // TODO: Temporary solution for item name change
    static String itemNameTemp = "Chocolate Cone Coating";

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-690")
    public void ValidateAddProductToOrderGuideByClickPDPHeartIcon() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");
        Dashboard.navigateToCustomers();
        softAssert.assertTrue(Customer.isCustomersTextDisplayed(),"customer section not display");
        Customer.searchCustomerByCode(CustomerCode);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(CustomerCode),"customer not found");
        Customer.clickOnOrderGuide(CustomerCode);
        Customer.goToCatalog();
        Customer.searchItemOnCatalog(itemNameTemp);
        softAssert.assertTrue(Customer.getFirstElementFrmSearchResults(itemNameTemp).contains(itemNameTemp.toLowerCase()), "item not found");
        Customer.clickOnProduct(itemNameTemp);
        softAssert.assertTrue(Customer.isProductDetailsDisplayed(),"navigation error");
        Customer.clickOrderGuide();
//        Customer.goToOrderGuide();
        Dashboard.navigateToCustomers();
        softAssert.assertTrue(Customer.isCustomersTextDisplayed(),"customer section not display");
        Customer.searchCustomerByCode(CustomerCode);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(CustomerCode),"customer not found");
        Customer.clickOnOrderGuide(CustomerCode);

        softAssert.assertTrue(Customer.addedItemDisplayOnOrderGuide(itemNameTemp),"product not found"); // OrderGuideProductName
        Customer.clickOrderGuideProduct(itemNameTemp); // OrderGuideProductName
        Customer.clickRemoveOrderGuide();
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result){
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }

}
