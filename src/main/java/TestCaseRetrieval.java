import com.cutanddry.qa.utils.aio.core.rest.AioAPIHelper;
import io.restassured.response.Response;

public class TestCaseRetrieval {
    public static void main(String[] args) {
        String projectKey = "DOT";
        String testCaseKey = "DOT-TC-1861";
        String testCaseDetailPath = "/project/{projectKey}/testcase/{testCaseKey}/detail";
        
        System.out.println("Retrieving test case details for: " + testCaseKey);
        
        try {
            Response response = AioAPIHelper.doGet(testCaseDetailPath, projectKey, testCaseKey);
            System.out.println("Response Status Code: " + response.getStatusCode());
            System.out.println("Response Body:");
            response.prettyPrint();
        } catch (Exception e) {
            System.err.println("Error retrieving test case details: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
