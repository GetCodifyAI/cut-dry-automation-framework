package com.cutanddry.qa.pages;
import org.openqa.selenium.By;

public class TrackerPage extends LoginPage{

    By txt_onboarding = By.xpath("//div[@class='_1ducyq8 mont _1npprf5' and text()='Onboarding Docs']");
    By btn_issue_tracker = By.xpath("//a[@class='_zzikwb4 text-center nav-item nav-link' and text()='Issue Tracker']");
    By txt_task_tracker = By.xpath("//div[@class='_1ducyq8 mont mb-2 _xdqbqk6' and text()='Task Tracker']");
    By btn_new_ticket = By.xpath("//button[@class='d-flex align-items-center justify-content-center cdbutton _1c2qb1w btn btn-primary btn-sm' and text()='New Ticket']");
    By txt_create_new_issue = By.xpath("//div[@class='p-0 mx-4 mt-4 pb-4 modal-header']//span[@class='_1tcxgp3 _wfze9d' and text()='Create New Issue']");


    public boolean isOnboardingDocsDisplayed(){
        try {
            distributorUI.waitForVisibility(txt_onboarding);
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(txt_onboarding);
    }
    public void clickIssueTracker(){
        distributorUI.click(btn_issue_tracker);
    }
    public boolean isIssueTrackerDisplayed(){
        try {
            distributorUI.waitForVisibility(txt_task_tracker);
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(txt_task_tracker);
    }
    public void clickNewTicket(){
        distributorUI.click(btn_new_ticket);
    }
    public boolean isCreateNewIssueDisplayed(){
        try {
            distributorUI.waitForVisibility(txt_create_new_issue);
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(txt_create_new_issue);
    }

}
