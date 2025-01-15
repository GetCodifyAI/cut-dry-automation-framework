package com.cutanddry.qa.pages;
import org.openqa.selenium.By;

public class TrackerPage extends LoginPage{

    By txt_onboarding = By.xpath("//div[@class='_1ducyq8 mont _1npprf5' and text()='Onboarding Docs']");
    By btn_issue_tracker = By.xpath("//a[@class='_zzikwb4 text-center nav-item nav-link' and text()='Issue Tracker']");
    By txt_task_tracker = By.xpath("//div[@class='_1ducyq8 mont mb-2 _xdqbqk6' and text()='Task Tracker']");
    By btn_new_ticket = By.xpath("//button[@class='d-flex align-items-center justify-content-center cdbutton _1c2qb1w btn btn-primary btn-sm' and text()='New Ticket']");
    By txt_create_new_issue = By.xpath("//div[@class='p-0 mx-4 mt-4 pb-4 modal-header']//span[@class='_1tcxgp3 _wfze9d' and text()='Create New Issue']");
    By btn_feature_requests = By.xpath("//a[@class='_zzikwb4 text-center nav-item nav-link' and text()='Feature Requests']");
    By txt_feature_requests = By.xpath("//div[@class='_1ducyq8 mont mb-2 _xdqbqk6' and text()='Feature Requests']");
    By btn_request_feature = By.xpath("//button[@class='d-flex align-items-center justify-content-center cdbutton _1c2qb1w btn btn-primary btn-sm' and text()='Request Feature']");
    By txt_request_new_feature = By.xpath("//div[@class='p-0 mx-4 mt-4 pb-4 modal-header']//span[@class='_1tcxgp3 _wfze9d' and text()='Request New Feature']");
    By txt_title = By.xpath("//input[@placeholder='Type here...']");
    By statusDropDown = By.xpath("(//div[contains(@class, 'cd_themed_select__value-container cd_themed_select__value-container--has-value css-mzcm4s')])[2]");
    By statusOption = By.xpath("//span[contains(@class, '_bzn5z48 px-2 py-0 badge') and text()='Completed']");
    By categoryDropDown = By.xpath("(//div[contains(@class, 'cd_themed_select__placeholder css-1wa3eu0-placeholder') and text()='Select...'])[1]");
    By catalogOption = By.xpath("//div[contains(@class, 'cd_themed_select__option css-yt9ioa-option') and text()='Catalog']");
    By priorityDropDown = By.xpath("(//div[contains(@class, 'cd_themed_select__placeholder css-1wa3eu0-placeholder') and text()='Select...'])[2]");
    By priorityOption = By.xpath("//div[contains(@class, 'd-flex align-items-center') and text()='P1']");
    By btn_createTicket = By.xpath("//button[contains(text(), 'Create Ticket')]");
    String taskTitle = "//tr[2]//td[2][text()='TITLE']";
    By editStatusDropDown = By.xpath("//span[contains(@class, '_bzn5z48 px-2 py-0 _107wcic _e9f0tt badge')]");
    By editStatusOption = By.xpath("//span[contains(@class, '_ktrzlb8 px-2 py-0 badge') and text()='To-do']");
    By firstRowOfTask = By.xpath("(//tr[@class='_7ogv5c _1g2ivyf _du1frc'])[1]");
    By btn_close = By.xpath("//span[text()='×']");
    String editStatus = "//tr[2]//td//span[text()='STATUS']";
    String editPopUp = "//span[text()='TITLE']";


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
        distributorUI.waitForVisibility(btn_new_ticket);
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
    //------------
    public void clickFeatureRequests(){
        distributorUI.click(btn_feature_requests);
    }
    public boolean isFeatureRequestsDisplayed(){
        try {
            distributorUI.waitForVisibility(txt_feature_requests);
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(txt_feature_requests);
    }
    public void clickRequestFeature(){
        distributorUI.click(btn_request_feature);
    }
    public boolean isRequestNewFeatureDisplayed(){
        try {
            distributorUI.waitForVisibility(txt_request_new_feature);
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(txt_request_new_feature);
    }
    public void typeTicketTitle(String title)throws InterruptedException {
        distributorUI.sendKeys(txt_title,title);
    }
    public void clickStatusDropDown(){
        distributorUI.click(statusDropDown);
    }
    public void clickStatusOption()throws InterruptedException{
        distributorUI.waitForVisibility(statusOption);
        distributorUI.click(statusOption);
    }
    public void clickCategoryDropDown(){
        distributorUI.click(categoryDropDown);
    }
    public void clickCategoryOption()throws InterruptedException{
        distributorUI.waitForVisibility(catalogOption);
        distributorUI.click(catalogOption);
    }
    public void clickPriorityDropDown(){
        distributorUI.click(priorityDropDown);
    }
    public void clickPriorityOption()throws InterruptedException{
        distributorUI.waitForVisibility(priorityOption);
        distributorUI.click(priorityOption);
    }
    public void clickCreateTicket()throws InterruptedException{
        distributorUI.waitForVisibility(btn_createTicket);
        distributorUI.click(btn_createTicket);
        distributorUI.waitForCustom(2000);
    }
    public boolean isTaskTitleDisplayed(String title){
        distributorUI.waitForVisibility(By.xpath(taskTitle.replace("TITLE",title)));
        return distributorUI.isDisplayed(By.xpath(taskTitle.replace("TITLE",title)));
    }
    public void clickEditStatus(){
        distributorUI.click(editStatusDropDown);
    }
    public void clickEditStatusOption(){
        distributorUI.click(editStatusOption);
    }
    public void clickCloseEditTicket(){
        distributorUI.click(btn_close);
    }
    public void clickFirstRow(String title){
        distributorUI.click(By.xpath(taskTitle.replace("TITLE",title)));
    }
    public boolean isEditTicketDisplayed(String status){
        return distributorUI.isDisplayed(By.xpath(editStatus.replace("STATUS",status)));
    }
    public boolean isEditTicketPopUpDisplayed(String title){
        distributorUI.waitForVisibility(By.xpath(editPopUp.replace("TITLE",title)));
        return distributorUI.isDisplayed(By.xpath(editPopUp.replace("TITLE",title)));
    }
}
