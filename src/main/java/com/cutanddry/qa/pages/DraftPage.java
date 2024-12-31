package com.cutanddry.qa.pages;

import org.openqa.selenium.By;

public class DraftPage extends LoginPage{
    By txt_drafts = By.xpath("//li[contains(text(),'Drafts')]");
    By btn_delete = By.xpath("(//button[contains(text(), 'Delete')])[1]");
    String txt_lastDraft = "(//tbody/tr[contains(@href, '/customers/place_order/') and contains(@href, 'draftId')]/td[8][contains(text(), 'TOTAL')])[1]";
    By pendingApprovalOrders = By.xpath("(//td/span[contains(text(),'Pending Approval')])[1]");
    By approveAndSubmitBtn = By.xpath("//button[contains(text(),'Approve & Submit Order')]");
    By btn_editOrder = By.xpath("//a[contains(text(),'Edit Order')]");
    String date = "//td[text()='DATE']";
    By draftDate = By.xpath("//tr//td[1]//div[1]");


    public boolean isDraftsTextDisplayed(){
        try {
            distributorUI.waitForVisibility(txt_drafts);
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(txt_drafts);
    }
    public void clickOnDelete(){
        distributorUI.waitForVisibility(btn_delete);
        distributorUI.click(btn_delete);
        distributorUI.waitForInvisibility(btn_delete);
    }
    public boolean isLastDraftDisplayed(String total){
        distributorUI.waitForVisibility(By.xpath(txt_lastDraft.replace("TOTAL", total)));
        return distributorUI.isDisplayed(By.xpath(txt_lastDraft.replace("TOTAL", total)));
    }

    public boolean isPendingApprovalOrdersDisplayed(){
        return distributorUI.isDisplayed(pendingApprovalOrders);
    }

    public void clickOnApprovalPendingOrder(){
        distributorUI.click(pendingApprovalOrders);
    }

    public void clickOnApproveAndSubmitBtn(){
        distributorUI.click(approveAndSubmitBtn);
    }
    public void clickDraft(String total){
        distributorUI.click(By.xpath(txt_lastDraft.replace("TOTAL", total)));
    }
    public void clickEditOrder(){
        distributorUI.click(btn_editOrder);
    }
    public Boolean isDraftOrdersNotOlder30Days() {
        try {
            distributorUI.waitForCustom(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return distributorUI.isDraftOrdersNotOlder30Days(draftDate);
    }
}
