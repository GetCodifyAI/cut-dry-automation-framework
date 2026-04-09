package com.cutanddry.qa.tests.catalog;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.data.testdata.CustomerData;
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

public class VerifyBlockPartialQuantityOrderingCatalogCardGridViewTest extends TestBase {
    static User user;
    static String customerCode = CustomerData.CUSTOMER_CODE2;
    static String itemCode = CustomerData.SEARCH_ITEM_CODE;

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-2952")
    public void verifyBlockPartialQuantityOrderingCatalogCardGridView() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();

        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        Assert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(), "The user is unable to land on the Dashboard page.");

        Dashboard.navigateToIndependentFoodsCo();
        Dashboard.navigateToOrderGuide();
        softAssert.assertTrue(Dashboard.isUserNavigatedToOrderGuide(), "The user is unable to land on the Order Guide page.");

        Customer.goToCatalog();
        Customer.clickCatalogGridView();
        softAssert.assertTrue(Customer.isCatalogCardContainerDisplayed(), "Catalog card/grid view is not displayed.");

        Customer.searchItemOnCatalog(itemCode);
        String firstItem = Customer.getFirstElementFrmSearchResults(CustomerData.SEARCH_ITEM_NAME);
        softAssert.assertTrue(firstItem.contains(CustomerData.SEARCH_ITEM_NAME.toLowerCase()), "The searched item was not found in the catalog.");

        Customer.addItemToCartCatalog(CustomerData.SEARCH_ITEM_NAME);

        String qtyAfterAdd = Customer.getCatalogCardQuantityInputValue();
        softAssert.assertNotNull(qtyAfterAdd, "Quantity input value should not be null after adding to cart.");

        Customer.clickCatalogCardPlusBtn();
        String qtyAfterPlus = Customer.getCatalogCardQuantityInputValue();
        softAssert.assertNotNull(qtyAfterPlus, "Quantity input value should not be null after clicking plus.");

        Customer.typeCatalogCardQuantityInput("3.25");
        String qtyAfterDecimal = Customer.getCatalogCardQuantityInputValue();
        softAssert.assertNotEquals(qtyAfterDecimal, "3.25", "Partial/decimal quantity should be blocked in catalog card/grid view.");

        Customer.typeCatalogCardQuantityInput("5");
        String qtyAfterWhole = Customer.getCatalogCardQuantityInputValue();
        softAssert.assertEquals(qtyAfterWhole, "5", "Whole number quantity should be accepted in catalog card/grid view.");

        Customer.clickCatalogCardMinusBtn();
        String qtyAfterMinus = Customer.getCatalogCardQuantityInputValue();
        softAssert.assertEquals(qtyAfterMinus, "4", "Quantity should decrease by 1 after clicking minus button.");

        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsersAtOnce();
    }
}
