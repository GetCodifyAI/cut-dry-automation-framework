package com.cutanddry.qa.pages;

import org.openqa.selenium.By;

public class ApprovalsPage extends LoginPage{
    By approvalText = By.xpath("//a[contains(text(),'Pending Approvals')]/../../..//div[contains(text(),'Approvals')]");
    By approvalOrder = By.xpath("//tr[contains(@href,'/place-order')][1]");
    By approveAndSubmitBtn = By.xpath("//button[contains(text(),'Approve & Submit Order')]");
    By btn_close = By.xpath("//button[contains(@class, 'close')]/span[text()='×']");
    By rejectApprovalsBtn = By.xpath("(//button[contains(text(),'Reject')])[last()]");
    By approvalCountFromSummeryWidgetTxt = By.xpath("//button[contains(text(),'View All')]/parent::div/div/div[1]");
    By AccountCountFromSummeryWidgetTxt = By.xpath("//button[contains(text(),'View All')]/parent::div/div/div[2]");
    By viewAllButton = By.xpath("//button[contains(text(),'View All')]");
    String pendingCountText = "//div[contains(text(),'RESTUARENTNAME')]/following-sibling::div/div[contains(text(),\"Total Pending\")]";
    By closeApprovalSummeryOverlay = By.xpath("//button//*[local-name()='svg' and @data-icon='xmark']");
    String approvalOrderElement = "//tbody//td[contains(normalize-space(.),'REFID')]";
    By rejectApprovalOverlay = By.xpath("//*[contains(text(),'Do you want to reject')]");
    By disableApproveAndSubmitBtn = By.xpath("//button[normalize-space()='Approve & Submit Order' and @disabled]");
    By disableSubmitForApproveBtn = By.xpath("//button[normalize-space()='Submit for Approval' and @disabled]");

    static DashboardPage dashboardPage = new DashboardPage();
    static CustomersPage customersPage = new CustomersPage();

    public boolean isNavigatedToApprovalPage(){
        return distributorUI.isDisplayed(approvalText);
    }

    public void clickOnFirstApprovalOrder(){
        distributorUI.click(approvalOrder);
    }

    public void clickOnApproveAndSubmitBtn(){
        distributorUI.click(approveAndSubmitBtn);
    }

    public String getApprovalCountTextFromOrderApprovalWidget() throws InterruptedException {
        distributorUI.waitForCustom(2000);
        return distributorUI.getText(approvalCountFromSummeryWidgetTxt);
    }

    public boolean getApprovalCountTextDisplayedFromOrderApprovalWidget() throws InterruptedException {
        distributorUI.waitForCustom(2000);
        return distributorUI.isDisplayed(approvalCountFromSummeryWidgetTxt);
    }

    public String getApprovalAccountCountTextFromOrderApprovalWidget() throws InterruptedException {
        distributorUI.waitForCustom(2000);
        return distributorUI.getText(AccountCountFromSummeryWidgetTxt);
    }

    public void clearAllOrderApprovals(){
        while(distributorUI.isDisplayed(approvalOrder)){
            distributorUI.click(approvalOrder);
            distributorUI.click(approveAndSubmitBtn);
            distributorUI.waitForVisibility(btn_close);
            distributorUI.click(btn_close);
            distributorUI.click(approvalText);
            dashboardPage.clickOnApproval();
            if(customersPage.isDuplicatePopupDisplayed()){
                customersPage.clickYesDuplicatePopup();
            }if(customersPage.isSubstitutesPopupDisplayedSub()){
                customersPage.clickDoNotSubstitute();
                customersPage.clickSaveSelection();
            }
            if(customersPage.isSubstitutesItemPopupDisplayedSub()){
                customersPage.clickCloseSubstituteItemPopup();
            }
            if(customersPage.isOrderMinPopupDisplayed()){
                customersPage.clickOnYes();
            }
        }
    }

    public void clickViewAll(){
        distributorUI.click(viewAllButton);
    }

    public String getTotalPending(String RestaurantName){
        return distributorUI.getText(By.xpath(pendingCountText.replace("RESTUARENTNAME",RestaurantName)));
    }

    public boolean isPendingOrdersDisplayed(String RestaurantName){
        return distributorUI.isDisplayed(By.xpath(pendingCountText.replace("RESTUARENTNAME",RestaurantName)));
    }

    public void clickCloseSummeryOverlay(){
        distributorUI.click(closeApprovalSummeryOverlay);
    }

    public boolean isApprovalOrderDisplayed(String refID){
        return distributorUI.isDisplayed(By.xpath(approvalOrderElement.replace("REFID",refID)));
    }

    public void clickApprovalOrder(String refID){
        distributorUI.clickWithScrollAndHover(By.xpath(approvalOrderElement.replace("REFID",refID)));
    }

    public void clickRejectBtn(){
        distributorUI.click(rejectApprovalsBtn);
    }

    public boolean isRejectApprovalOverlayDisplayed(){
        return distributorUI.isDisplayed(rejectApprovalOverlay);
    }
    public boolean isDisableApproveAndSubmitBtnDisplayed(){
        return distributorUI.isDisplayed(disableApproveAndSubmitBtn);
    }
    public boolean isDisableSubmitForApprovalBtnDisplayed(){
        return distributorUI.isDisplayed(disableSubmitForApproveBtn);
    }
    public boolean isApproveAndSubmitBtnDisplayed(){
        return distributorUI.isDisplayed(approveAndSubmitBtn);
    }
    public boolean isRejectApprovalsBtnDisplayed(){
        return distributorUI.isDisplayed(rejectApprovalsBtn);
    }



}
