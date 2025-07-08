package com.cutanddry.qa.tests.order_guide;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.*;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

public class CustomerIndexingVerificationTest extends TestBase {
    static User user;

    @BeforeClass
    public void setUp() {
        initialization();
        user = JsonUtil.readUserLogin();
        Login.logIntoRestaurant(user.getEmailOrMobile(), user.getPassword());
        Assert.assertTrue(Dashboard.isUserNavigatedToRestaurantDashboard(),"login failed");
    }

    @Test(dataProvider = "customerData")
    public void DistributorInitialSetUp(String testData) throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(Login.isUserExistInLoginAs(testData),"Customer not found: " + testData);
        softAssert.assertAll();
    }

    @AfterClass
    public void tearDown() {
        closeAllBrowsersAtOnce();
    }

    @DataProvider(name = "customerData")
    public Object[][] getCustomerData() {
        return new Object[][]{
                {"Ayendra IFC WL"},
                {"Amir IFC WL"},
                {"274855811"},
                {"Aselabookkeeper4"},
                {"Brandon IFC Cut+Dry Agent"},
                {"32404837"},
                {"jcoupal@coupacafe"},
                {"katdmejia2@gmail.com"},
                {"(explorer) - Bermuda Biological Station"},
                {"universal@cutanddry"},
                {"manager@cutanddry.com"},
                {"employee@cutanddry.com"},
                {"joey_nutwell@hotmail"},
                {"flattcobr@gmail"},
                {"mesafrozenyogurt@gmail"},
                {"Hadley Dispatcher"},
                {"Isuru Test AR"},
                {"103430764"},
                {"Brandon IFC White"},
                {"sales@jordanpaige.com"},
                {"App Testers"},
                {"13038005947"},
                {"75761700"},
                {"apptesters@test.com"},
                {"226431917"},
                {"280815154"},
                {"14064045769"},
                {"164465588"},
                {"81058255"},
                {"432392672"},
                {"176626422"},
                {"477538372"},
                {"438342864"},
                {"294962910"},
                {"372460856"},
                {"61714031"},
                {"176617582"},
                {"286475302"},
                {"309075134"},
                {"177931291"},
                {"155947343"},
                {"286512123"},
                {"517030659"},
                {"63168995"},
                {"65436339"},
                {"137524915"},
                {"70351601"},
                {"487417810"}
        };
    }
}
