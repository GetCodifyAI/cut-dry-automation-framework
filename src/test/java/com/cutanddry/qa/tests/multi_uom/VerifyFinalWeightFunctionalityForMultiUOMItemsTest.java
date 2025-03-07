package com.cutanddry.qa.tests.multi_uom;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.*;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.cutanddry.qa.base.TestBase.*;

public class VerifyFinalWeightFunctionalityForMultiUOMItemsTest extends TestBase {
    static User user;
    static String DP = "K&K International";
    static String customerId = "18935";
    static String searchItemCode1 = "20507";
    static String searchItemCode2 = "3865";
    static String UOM1 = "1";
    static String UOM2 = "2";
    static String UOM3 = "3";

    @BeforeMethod
    public void setup(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-1090")
    public static void VerifyFinalWeightFunctionalityForMultiUOMItems () throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login error");
        Login.navigateToDistributorPortal(DP);
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId),"search error");
        Customer.SelectCustomer(customerId);
        Customer.clickOnOrderGuideInProfile();

        Catalog.ClickOnMultiUomDropDownOG(searchItemCode1);
        Catalog.clickOGAddToCartPlusIcon(3,searchItemCode1,UOM1);
        Catalog.clickOGAddToCartPlusIcon(2,searchItemCode1,UOM2);
        Catalog.clickOGAddToCartPlusIcon(4,searchItemCode1,UOM3);

        Catalog.ClickOnMultiUomDropDownOG(searchItemCode2);
        Catalog.clickOGAddToCartPlusIcon(2,searchItemCode2,UOM1);
        Catalog.clickOGAddToCartPlusIcon(1,searchItemCode2,UOM2);
        Catalog.clickOGAddToCartPlusIcon(3,searchItemCode2,UOM3);

        Customer.clickCheckOutOrderGuide();
        Customer.submitOrder();
        softAssert.assertTrue(Customer.isThankingForOrderPopupDisplayed(),"error in submitting order");
        Customer.clickOrderSuccessMessageClose();

        Customer.SelectCustomer(customerId);
        Customer.navigateToOrdersPage();
        Customer.clickFirstOrderFrmOrderTab();
        Orders.clickOnEditOrder();
        softAssert.assertTrue(Orders.isEditOrderPopupDisplayed(),"error in submitting order");
        Orders.clickOnConfirm();
        Customer.editOrderFromReviewScreen();

        Customer.selectFinalWeightFromOG(searchItemCode2, UOM1);
        softAssert.assertTrue(Customer.isEditWeightOverlayDisplayed(),"Error in displaying Edit Weight Overlay");
        softAssert.assertEquals(Customer.getTotalWeight(UOM1)/Customer.getNoOfUOMsOrdered(UOM1),Customer.getWeightPerUOM(UOM1),"Weights per UOM calculation is wrong");
        softAssert.assertEquals(Customer.getTotalWeight(UOM2)/Customer.getNoOfUOMsOrdered(UOM2),Customer.getWeightPerUOM(UOM2),"Weights per UOM calculation is wrong");
        softAssert.assertEquals(Customer.getTotalWeight(UOM3)/Customer.getNoOfUOMsOrdered(UOM3),Customer.getWeightPerUOM(UOM3),"Weights per UOM calculation is wrong");

        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsersAtOnce();
    }
}
