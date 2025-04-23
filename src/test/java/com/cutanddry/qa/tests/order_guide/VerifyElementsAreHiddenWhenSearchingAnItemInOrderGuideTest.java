package com.cutanddry.qa.tests.order_guide;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.CatalogData;
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

public class VerifyElementsAreHiddenWhenSearchingAnItemInOrderGuideTest extends TestBase {
    static User user;
    static String customerId = CatalogData.CUSTOMER_ID;
    String searchItemCode = CatalogData.ITEM_CODE;

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();


    }

    @Test(groups = "DOT-TC-431")
    public void VerifyElementsAreHiddenWhenSearchingAnItemInOrderGuide() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        Assert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId), "Unable to find the customer Id");
        Customer.clickOnOrderGuide(customerId);
        Customer.searchItemOnOrderGuide(searchItemCode);
        softAssert.assertFalse(Customer.isOrderGuideTextDisplay(),"order guide text display");
        softAssert.assertFalse(Customer.isSortItemTextDisplay(),"sort item text display");
        softAssert.assertFalse(Customer.isEditOrderGuideButtonDisplay(),"edit OG button display");
        softAssert.assertFalse(Customer.isCreateOrderGuideButtonDisplay(),"create OG button display");
        softAssert.assertFalse(Customer.isUploadOrderGuideButtonDisplay(),"upload OG button display");
        softAssert.assertFalse(Customer.isPrintOrderGuideButtonDisplay(),"print OG button display");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }


}
