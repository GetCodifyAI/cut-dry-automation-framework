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

public class ValidateAddingSpecialInstructionsTest extends TestBase {
    static User user;
    static String customerId = "37631";
    static String specialInstruction="test instruction";
    static String internalNote = "test internal note";
    static String noteToCustomer = "test note to customer";
    static String poNumber = "37";

    @BeforeMethod
    public void setUp(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-695")
    public void ValidateAddingSpecialInstructions() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        Dashboard.isUserNavigatedToDashboard();
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");
        Dashboard.navigateToCustomers();
        Customer.searchCustomerByCode(customerId);
        softAssert.assertTrue(Customer.isCustomerSearchResultByCodeDisplayed(customerId),"search error");
        Customer.clickOnOrderGuide(customerId);
        Customer.increaseFirstRowQtyByOne();
        Customer.checkoutItems();
        softAssert.assertTrue(Customer.isReviewOrderTextDisplayed(),"navigation error for review order");
        Customer.typeSpecialInstruction(specialInstruction);
        Customer.typeInternalNote(internalNote);
        Customer.typeNoteToCustomer(noteToCustomer);
        Customer.typePONumber(poNumber);
        Customer.submitOrder();
        softAssert.assertTrue(Customer.isThankingForOrderPopupDisplayed(),"Error in turning the approval off");
        Customer.clickClose();
        Customer.clickOnOrdersTab();
        Customer.clickOrder();
        softAssert.assertTrue(Customer.isOrderSectionDisplayed(),"order section not navigate");
        softAssert.assertTrue(Customer.isSpecialInstructionDisplayed(specialInstruction),"special instructions not display");
        softAssert.assertTrue(Customer.isInternalNoteDisplayed(internalNote),"internal note not display");
        softAssert.assertTrue(Customer.isNoteToCustomerDisplayed(noteToCustomer),"note to customer not display");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }

}
