package com.cutanddry.qa.tests.orders;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.functions.Orders;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Arrays;
import java.util.List;

public class VerifyThatNotLockedColumnsCanBeEditedInTheCustomizeColumnScreenTest extends TestBase {
    static User user;
    static String editColumn = "Edit Columns";
    static String column = "Cut+Dry Reference";

    @BeforeMethod
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-1286")
    public void VerifyThatNotLockedColumnsCanBeEditedInTheCustomizeColumnScreen() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(), user.getPassword());
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"login error");
        Dashboard.navigateToOrders();
        softAssert.assertTrue(Orders.isUserNavigatedToOrder(),"navigation error");
        softAssert.assertTrue(Orders.isEditColumnDisplay(editColumn),"Edit column button not display");
        Orders.clickEditColumn(editColumn);
        Orders.setCustomizeColumn(false);
        Orders.clickUpdateColumn();
        softAssert.assertTrue(Orders.isColumnSettingUpdatePopUpDisplay(),"Column Setting Update Pop Up not Display");
        Orders.clickOkUpdate();
        softAssert.assertTrue(Orders.isColumnUpdateDisplay(column),"colum not updated");

        Orders.clickEditColumn(editColumn);
        Orders.setCustomizeColumn(true);
        Orders.clickUpdateColumn();
        softAssert.assertTrue(Orders.isColumnSettingUpdatePopUpDisplay(),"Column Setting Update Pop Up not Display");
        Orders.clickOkUpdate();
        softAssert.assertTrue(Orders.isColumnUpdateDisplay(column),"colum not updated");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshotOnFailure(result);
        closeAllBrowsers();
    }
}
