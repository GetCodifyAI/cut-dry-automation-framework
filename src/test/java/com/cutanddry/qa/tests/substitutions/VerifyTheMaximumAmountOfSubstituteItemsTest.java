package com.cutanddry.qa.tests.substitutions;

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

public class VerifyTheMaximumAmountOfSubstituteItemsTest extends TestBase {
    static User user;
    static String customer = "32404837";
    static String itemCode = "F43495";
    static String itemCodeSub = "43137";

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-218")
    public void verifyTheMaximumAmountOfSubstituteItems() throws InterruptedException {
        String itemName;
        SoftAssert softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");
        Login.navigateToWhiteLabelPortal(customer);
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");
        Customer.clickSouthwestTraders();
        Customer.searchItemOnOrderGuide(itemCode);
        itemName = Customer.getItemNameFirstRow();
        softAssert.assertTrue(Customer.getItemNameFirstRow().contains(itemName),"item mismatch");
        Customer.increaseFirstRowQtyByOneInDist();
        Customer.checkoutItemsDist();
        softAssert.assertTrue(Customer.isSubstitutesPopupDisplayed(),"substitutes popup error");
        softAssert.assertEquals(Customer.getSubstituteItemsCount(4), 4, "max sub items count error");
        Customer.clickOnItem(itemCodeSub);
        Customer.clickSaveSelection();
        softAssert.assertTrue(Customer.isReplacementDisplayed(),"replace error");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
