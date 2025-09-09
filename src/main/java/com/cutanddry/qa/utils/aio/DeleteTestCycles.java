package com.cutanddry.qa.utils.aio;

import com.cutanddry.qa.utils.aio.core.rest.AioAPIHelper;
import com.cutanddry.qa.common.Constants;

public class DeleteTestCycles {
    public static void main(String[] args) {
        String projectKey = Constants.PROJECT_KEY;
        
        String[] cyclesToDelete = {
            "DOT-CY-6392", "DOT-CY-6393", "DOT-CY-6394", "DOT-CY-6395", 
            "DOT-CY-6396", "DOT-CY-6397", "DOT-CY-6398", "DOT-CY-6399", 
            "DOT-CY-6400", "DOT-CY-6401", "DOT-CY-6402", "DOT-CY-6403", 
            "DOT-CY-6404"
        };
        
        System.out.println("Starting deletion of " + cyclesToDelete.length + " test cycles...");
        
        int successCount = 0;
        int failureCount = 0;
        
        for (String cycleKey : cyclesToDelete) {
            try {
                boolean success = AioAPIHelper.deleteCycle(projectKey, cycleKey);
                if (success) {
                    successCount++;
                    System.out.println("✓ Successfully deleted: " + cycleKey);
                } else {
                    failureCount++;
                    System.err.println("✗ Failed to delete: " + cycleKey);
                }
            } catch (Exception e) {
                failureCount++;
                System.err.println("✗ Error deleting " + cycleKey + ": " + e.getMessage());
            }
        }
        
        System.out.println("\nDeletion Summary:");
        System.out.println("Successfully deleted: " + successCount + " cycles");
        System.out.println("Failed to delete: " + failureCount + " cycles");
        System.out.println("Total processed: " + cyclesToDelete.length + " cycles");
    }
}
