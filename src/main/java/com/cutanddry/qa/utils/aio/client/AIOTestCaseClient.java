package com.cutanddry.qa.utils.aio.client;

import com.cutanddry.qa.utils.aio.models.TestCaseDetails;
import com.cutanddry.qa.utils.aio.models.TestStep;
import java.util.Arrays;
import java.util.List;

public class AIOTestCaseClient {
    
    public TestCaseDetails getTestCaseDetails(String testCaseId) {
        if ("DOT-TC-1644".equals(testCaseId)) {
            return createDOTTC1644TestCase();
        }
        
        throw new IllegalArgumentException("Test case not found: " + testCaseId);
    }
    
    private TestCaseDetails createDOTTC1644TestCase() {
        List<TestStep> steps = Arrays.asList(
            new TestStep(
                "Login to the operator portal",
                "User should be successfully logged into the operator portal",
                "Use environment credentials"
            ),
            new TestStep(
                "Navigate to customers and search for a customer",
                "Customer search results should be displayed",
                "Customer ID: ${TEST_CUSTOMER_ID}"
            ),
            new TestStep(
                "Click on Order Guide for the customer",
                "Order Guide page should be displayed",
                ""
            ),
            new TestStep(
                "Navigate to catalog by clicking the heart icon",
                "Catalog page should be displayed",
                ""
            ),
            new TestStep(
                "Search for an item in the catalog",
                "Item should be found and displayed in search results",
                "Item code: ${TEST_ITEM_CODE}"
            ),
            new TestStep(
                "Add item to order guide by clicking the heart icon",
                "Item should be added to the order guide successfully",
                ""
            ),
            new TestStep(
                "Remove item from order guide by clicking the heart icon again",
                "Item should be removed from the order guide successfully",
                ""
            ),
            new TestStep(
                "Login to the DP portal using login-as functionality",
                "User should be successfully logged into the DP portal",
                "Distributor name: ${TEST_DISTRIBUTOR_NAME}"
            ),
            new TestStep(
                "Navigate to dashboard and verify Order Guide Changes section is displayed",
                "Order Guide Changes section should be visible on the dashboard",
                ""
            ),
            new TestStep(
                "Verify that Order Guide Changes data is displayed",
                "Order Guide Changes data should be visible in the section",
                ""
            ),
            new TestStep(
                "Change the date range dropdown to 30 days",
                "Date range should be updated to 30 days",
                ""
            ),
            new TestStep(
                "Change the restaurant dropdown filter",
                "Restaurant filter should be applied",
                "Restaurant name: ${TEST_RESTAURANT_NAME}"
            ),
            new TestStep(
                "Change the salesperson dropdown filter",
                "Salesperson filter should be applied",
                "Salesperson name: ${TEST_SALESPERSON_NAME}"
            ),
            new TestStep(
                "Click on View All for Order Guide Changes",
                "View All functionality should work and display all Order Guide Changes",
                ""
            )
        );
        
        return new TestCaseDetails(
            "DOT-TC-1644",
            "Verify Order Guide Changes Section Functionality",
            "This test case verifies the functionality of the Order Guide Changes section in the dashboard, including adding/removing items from order guide and filtering capabilities.",
            steps
        );
    }
}
