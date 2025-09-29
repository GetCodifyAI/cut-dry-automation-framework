import com.cutanddry.qa.utils.aio.core.rest.AioAPIHelper;
import io.restassured.response.Response;

public class TestCaseRetrieval {
    public static void main(String[] args) {
        String projectKey = "DOT";
        String[] testCaseKeys = {"DOT-TC-1891", "DOT-TC-1892", "DOT-TC-1893"};
        
        for (String testCaseKey : testCaseKeys) {
            System.out.println("\n=== Retrieving test case details for: " + testCaseKey + " ===");
            
            try {
                Response response = AioAPIHelper.getTestCaseDetail(projectKey, testCaseKey);
                System.out.println("Response Status Code: " + response.getStatusCode());
                System.out.println("Response Body:");
                response.prettyPrint();
                System.out.println("\n" + "=".repeat(80));
            } catch (Exception e) {
                System.err.println("Error retrieving test case details for " + testCaseKey + ": " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
