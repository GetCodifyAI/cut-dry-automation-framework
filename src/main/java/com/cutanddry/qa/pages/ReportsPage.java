package com.cutanddry.qa.pages;

import org.openqa.selenium.By;

public class ReportsPage extends LoginPage{
    By txt_reporting = By.xpath("//li[contains(text(),'Reporting')]");
    By btn_email_report = By.xpath("//button[@class='btn btn-outline-primary btn-block' and text()='Email Report']");
    By txt_generating_report = By.xpath("//h2[@id='swal2-title' and text()='Generating Report']");
    By btn_ok = By.xpath("//button[@class='swal2-confirm swal2-styled' and text()='OK']");
    By btn_download_report = By.xpath("//a[@class='btn btn-outline-primary btn-block' and text()='Download Report']");




    public boolean isReportingTextDisplayed(){
        try {
            distributorUI.waitForVisibility(txt_reporting);
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(txt_reporting);
    }
    public void clickEmailReport(){
        distributorUI.click(btn_email_report);
    }
    public boolean isGeneratingReportPopupDisplayed(){
        try {
            distributorUI.waitForVisibility(txt_generating_report);
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(txt_generating_report);
    }
    public void clickOk(){
        distributorUI.click(btn_ok);
    }
}
