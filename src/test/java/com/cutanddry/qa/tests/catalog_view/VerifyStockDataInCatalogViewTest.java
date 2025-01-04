package com.cutanddry.qa.tests.catalog_view;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.Customer;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.InternalTools;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyStockDataInCatalogViewTest extends TestBase {
    static User user;
    String DistributerName ="47837013 - Brandon IFC Cut+Dry Agent - Independent Foods Co";
    String CustomerCode = "16579";

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-246")
    public void VerifyStockDataInCatalogView() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        // Pre-request
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");
        InternalTools.TurnOnTheDisplayingStockCount(true);

//        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
//        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");
        Login.navigateToDistributorPortal(DistributerName);
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"navigation error");
        Dashboard.navigateToCustomers();
        softAssert.assertTrue(Customer.isNavigatedToCustomerPage(),"ERROR in navigating to customer page");
        Customer.searchCustomerByCode(CustomerCode);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(CustomerCode),"search error");
        Customer.clickOnOrderGuide(CustomerCode);
        Customer.SelectTestAutomationOrderGuide();
        softAssert.assertTrue(Customer.isStockCountDisplayed(),"Not Displaying Stock Count");

        //Turning Off the Displaying Stock Count
       /* Login.navigateToInternalToolsPage();
        InternalTools.navigateToConfigureSupplier();
        InternalTools.navigateToIndependentCompEditDetails();
        InternalTools.navigateToOrderingSettingsTab();
        InternalTools.TurnOffStockLevelInPortal();
        InternalTools.SaveStockLevelTurnOffInPortal();
        InternalTools.AccepSuccessfulOverlayByOK();*/
        InternalTools.TurnOnTheDisplayingStockCount(false);

//        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
//        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");
        Login.navigateToDistributorPortal(DistributerName);
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"navigation error");
        Dashboard.navigateToCustomers();
        softAssert.assertTrue(Customer.isNavigatedToCustomerPage(),"ERROR in navigating to customer page");
        Customer.searchCustomerByCode(CustomerCode);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(CustomerCode),"search error");
        Customer.clickOnOrderGuide(CustomerCode);
        Customer.SelectTestAutomationOrderGuide();
        softAssert.assertFalse(Customer.isStockCountDisplayed(),"Displaying Stock Count");


        softAssert.assertAll();
    }

    @AfterMethod

    public void teardown(ITestResult result) throws InterruptedException {

        //Turning on the Displaying Stock Count
        /*Login.navigateToInternalToolsPage();
        InternalTools.navigateToConfigureSupplier();
        InternalTools.navigateToIndependentCompEditDetails();
        InternalTools.navigateToOrderingSettingsTab();
        InternalTools.TurnOnStockLevelInPortal();
        InternalTools.SaveStockLevelTurnOffInPortal();
        InternalTools.AccepSuccessfulOverlayByOK();*/
        InternalTools.TurnOnTheDisplayingStockCount(true);


        takeScreenshotOnFailure(result);
        closeAllBrowsers();

    }

}
