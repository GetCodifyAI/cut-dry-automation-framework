package com.cutanddry.qa.functions;

import com.cutanddry.qa.pages.ScanToOrderPage;

public class ScanToOrder {
    static ScanToOrderPage scanToOrderPage = new ScanToOrderPage();


   public static boolean isNavigatedToScanToOrderPage(){
        return scanToOrderPage.isScanToOrderTextDisplayed();
   }

}
