package com.cutanddry.qa.testcases;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.functions.Customer;
import com.cutanddry.qa.functions.Catalog;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class DOT_TC_1644_OrderGuideChangesTest extends TestBase {

    @Test(groups = {"DOT", "regression"})
    public void DOT_TC_1644_VerifyOrderGuideChangesSectionFunctionality() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        
        try {
            Login.navigateToLoginAsPortal("415-505-5531");
            Login.loginAsDistributor("415-505-5531", "NovaN@123");
            softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(), 
                "User should successfully log into the dashboard");

            Dashboard.clickOnPlaceOrderBtn();
            softAssert.assertTrue(Dashboard.isUserNavigatedToOrderGuide(), 
                "Should be able to navigate to the OG successfully");

            Customer.goToCatalog();
            softAssert.assertTrue(Catalog.isUserNavigatedToCatalog(), 
                "Should navigate to catalog successfully");
            
            Catalog.searchItemInCatalog("test item");
            
            Catalog.clickOnAddToCart();
            
            Customer.goToEdit();
            softAssert.assertTrue(Customer.isEditOrderGuideTextDisplayed(), 
                "Should be able to access edit order guide");
            
            Customer.addItemFromCatalog();
            softAssert.assertTrue(Customer.isOrderGuideUpdatedTextDisplayed(), 
                "Item should be added to the OG");

            softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(), 
                "Should be able to verify Order Guide Changes section in dashboard");

        } catch (Exception e) {
            softAssert.fail("Test failed with exception: " + e.getMessage());
        } finally {
            softAssert.assertAll();
        }
    }
}
