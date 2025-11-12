package com.cutanddry.qa.tests.orders;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.CatalogData;
import com.cutanddry.qa.functions.*;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyTheFindMoreInCatalogButtonTest extends TestBase {
    SoftAssert softAssert;
    static User user;
    static String customerId = "21259";
    static String itemName = "Lemons 115 CT";
    String searchItemCode = "02345";


    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }


    @Test(groups = "DOT-TC-2707")
    public void VerifyTheFindMoreInCatalogButton() throws InterruptedException {

        softAssert = new SoftAssert();

        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        Assert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "The user is unable to land on the Dashboard page.");

        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        Assert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Unable to find the customer Id");
        Customer.clickOnOrderGuide(customerId);

        Customer.searchItemOnOrderGuide(searchItemCode);
        Thread.sleep(4000);
        softAssert.assertTrue(Customer.getItemNameFirstRow().toLowerCase().contains(itemName.toLowerCase()),"item mismatch");
        Orders.clickFindMoreInCatalog();
        Thread.sleep(6000);
        softAssert.assertTrue(Customer.getFirstElementFrmSearchResults(itemName).contains(itemName.toLowerCase()), "item not found");

    }
    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsersAtOnce();
    }
}
