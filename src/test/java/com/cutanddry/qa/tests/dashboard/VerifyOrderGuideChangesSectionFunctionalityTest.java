package com.cutanddry.qa.tests.dashboard;

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

public class VerifyOrderGuideChangesSectionFunctionalityTest extends TestBase {
    static User user;
    String customerId = CustomerData.CUSTOMER_CODE3;

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-1644")
    public void verifyOrderGuideChangesSectionFunctionality() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        
        Login.loginAsOperator(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(), "Unable to login to operator portal.");
        
        Dashboard.navigateToOrder();
        softAssert.assertTrue(Dashboard.isUserNavigatedToOrderGuide(), "Unable to navigate to Order Guide.");
        
        Customer.addItemToOrderGuideFromCatalog();
        softAssert.assertTrue(Customer.isItemAddedToOrderGuide(), "Item was not added to Order Guide.");
        
        Customer.removeItemFromOrderGuide();
        softAssert.assertTrue(Customer.isItemRemovedFromOrderGuide(), "Item was not removed from Order Guide.");
        
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "Unable to login to distributor portal.");
        
        softAssert.assertTrue(Dashboard.isOrderGuideChangesSectionDisplayed(), "Order Guide Changes section is not displayed in DP portal.");
        
        softAssert.assertTrue(Dashboard.areOrderGuideChangesDisplayed(), "Item added and removed changes are not displayed in Order Guide Changes section.");
        
        Dashboard.selectOrderGuideChangesDateRange("Last 30 Days");
        softAssert.assertTrue(Dashboard.isOrderGuideChangesDataUpdated(), "Data did not update for the selected date range.");
        
        Dashboard.clickOrderGuideChangesViewAll();
        softAssert.assertTrue(Dashboard.isOrderGuideChangesFullScreenDisplayed(), "Order Guide Changes full screen is not displayed.");
        
        Dashboard.selectOrderGuideChangesDateRange("Last 7 Days");
        softAssert.assertTrue(Dashboard.isOrderGuideChangesDataUpdated(), "Date range change did not update data accordingly.");
        
        Dashboard.selectOrderGuideChangesRestaurant("Test Restaurant");
        softAssert.assertTrue(Dashboard.isOrderGuideChangesFilteredByRestaurant(), "OG changes are not filtered by selected restaurant.");
        
        Dashboard.selectOrderGuideChangesSalesperson("Test Salesperson");
        softAssert.assertTrue(Dashboard.isOrderGuideChangesFilteredBySalesperson(), "OG changes are not filtered by selected salesperson.");
        
        Dashboard.clickOnDashboard();
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(), "Unable to navigate back to dashboard.");
        
        Dashboard.selectOrderGuideChangesDateRange("Last 30 Days");
        Dashboard.selectOrderGuideChangesSalesperson("All Salespersons");
        softAssert.assertTrue(Dashboard.isOrderGuideChangesDataUpdated(), "Dashboard filters did not work correctly.");
        
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            takeScreenshotOnFailure(result);
            System.out.println("Screenshot captured for failed test case.");
        }
        closeAllBrowsers();
        System.out.println("Browser closed successfully.");
    }
}
