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

public class VerifyThatTheDistributionCentreFilterIsFilteringRelevantDistributorCentresCustomerCatalogOGEditFlowTest extends TestBase {
    static User user;
    String CustomerCode = "34315";
    static String filter = "Distribution Center";
    static String tag = "Food Service";
    static String itemName = "15  Grain Crunchy";
    String DistributorName ="196858166 - Cut+Dry Agent - Butterfield & Vallis";


    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-1490")
    public void VerifyThatTheDistributionCentreFilterIsFilteringRelevantDistributorCentresCustomerCatalogOGEditFlow() throws InterruptedException {
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
        Customer.goToEdit();
        softAssert.assertTrue(Customer.isEditOrderGuideTextDisplayed(),"navigation error for edit");
        Customer.createOrderFromCatalog();
        softAssert.assertTrue(Customer.isCatalogFilterDisplayed(filter),"catalog filter not display");
        Customer.selectDistributorCenter(tag);
        softAssert.assertTrue(Customer.isCatalogFilterDisplayTag(itemName,tag),"new tag display error");
        Customer.clickCatalogListView();
        softAssert.assertTrue(Customer.isCatalogFilterDisplayTagList(itemName,tag),"new tag display list error");
        Customer.closeEditorCatalog();
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result){
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }

}
