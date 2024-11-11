package com.cutanddry.qa.pages;

import org.openqa.selenium.By;

public class ReportsPage extends LoginPage{
    By txt_reporting = By.xpath("//li[contains(text(),'Reporting')]");

    public boolean isReportingTextDisplayed(){
        try {
            distributorUI.waitForVisibility(txt_reporting);
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(txt_reporting);
    }
}
