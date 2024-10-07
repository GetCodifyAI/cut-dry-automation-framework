package com.cutanddry.qa.tests.order_desk;

import com.cutanddry.qa.base.TestBase;
import com.cutanddry.qa.data.models.User;
import com.cutanddry.qa.functions.Dashboard;
import com.cutanddry.qa.functions.Login;
import com.cutanddry.qa.functions.OrderDesk;
import com.cutanddry.qa.utils.JsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Objects;

public class VerifyVoiceRecordUploadTest extends TestBase {
    User user;

    @BeforeMethod
    public void setup(){
        initialization();
        user = JsonUtil.readUserLogin();
    }

    @Test(groups = "DOT-TC-81")
    public void VerifyVoiceRecordUpload() throws URISyntaxException {
        int ProcessingMessageCount;
        SoftAssert softAssert = new SoftAssert();
        Login.loginAsDistributor(user.getEmailOrMobile(),user.getPassword());
        Dashboard.isUserNavigatedToDashboard();
        softAssert.assertTrue(Dashboard.isUserNavigatedToDashboard(),"Login Error");
        Dashboard.navigateToOrderDesk();
        softAssert.assertTrue(OrderDesk.isUsernavigatedToOrderDeskPage(),"Error in navigating to order desk Page");
        ProcessingMessageCount = OrderDesk.getNumberOfProcessingVoiceMessages();
        OrderDesk.uploadVoiceRecord(Paths.get(Objects.requireNonNull(ClassLoader.getSystemClassLoader().getResource("VoiceMessage/fbc356af-8f8e-443c-94fa-0200a5307ac3.mp3")).toURI()).toString());
        softAssert.assertEquals(OrderDesk.getNumberOfProcessingVoiceMessages(),ProcessingMessageCount+1,"Error uploading voice record");
        softAssert.assertAll();
    }

    @AfterMethod
    public void teardown(ITestResult result){
        takeScreenshotOnFailure(result);
        closeAllBrowsers();

    }


}
