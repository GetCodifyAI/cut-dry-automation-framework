package com.cutanddry.qa.pages;

import org.openqa.selenium.By;

public class ApprovalsPage extends LoginPage{
    By approvalText = By.xpath("//a[contains(text(),'Pending Approvals')]/../../..//div[contains(text(),'Approvals')]");
    By approvalOrder = By.xpath("//tr[contains(@href,'/place-order')][1]");
    By approveAndSubmitBtn = By.xpath("//button[contains(text(),'Approve & Submit Order')]");

    public boolean isNavigatedToApprovalPage(){
        return distributorUI.isDisplayed(approvalText);
    }

    public void clickOnFirstApprovalOrder(){
        distributorUI.click(approvalOrder);
    }

    public void clickOnApproveAndSubmitBtn(){
        distributorUI.click(approveAndSubmitBtn);
    }
}
