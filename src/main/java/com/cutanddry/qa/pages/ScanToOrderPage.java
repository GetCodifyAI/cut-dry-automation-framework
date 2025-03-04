package com.cutanddry.qa.pages;

import com.cutanddry.qa.functions.ScanToOrder;
import org.openqa.selenium.By;

public class ScanToOrderPage extends LoginPage{
    By ScanToOrderText = By.xpath("//h1[contains(text(),'Scan to Order')]");



    public boolean isScanToOrderTextDisplayed(){
        return distributorUI.isDisplayed(ScanToOrderText);
    }

}
