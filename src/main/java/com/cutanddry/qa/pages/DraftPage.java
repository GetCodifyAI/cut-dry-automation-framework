package com.cutanddry.qa.pages;

import org.openqa.selenium.By;
import java.util.NoSuchElementException;


public class DraftPage extends LoginPage{
    By txt_drafts = By.xpath("//li[contains(text(),'Drafts')]");
    By btn_delete = By.xpath("(//button[contains(text(), 'Delete')])[1]");
    String txt_lastDraft = "(//tbody/tr[contains(@href, '/customers/place_order/') and contains(@href, 'draftId')]/td[8][contains(text(), 'TOTAL')])[1]";
    By pendingApprovalOrders = By.xpath("(//td/span[contains(text(),'Pending Approval')])[1]");
    By btn_editOrder = By.xpath("//a[contains(text(),'Edit Order')]");
    String date = "//td[text()='DATE']";
    By draftRowCount = By.xpath("//table/tbody/tr");
    By draftDate = By.xpath("//tr//td[6]");
    By draftStatus = By.xpath("//tr//td[9]");
    String txt_restaurantLastDraft = "(//tbody/tr[contains(@href, 'place-order') and contains(@href, 'draftId')]//td[contains(text(), 'TOTAL')])[1]";
    By referenceNumOP = By.xpath("(//tbody/tr[contains(@href, 'place-order') and contains(@href, 'draftId')]//td[3])[1]");
    By referenceNumDP = By.xpath("(//tbody/tr[contains(@href, '/customers/place_order/') and contains(@href, 'draftId')]/td[2])[1]");
    String draftsColumnDP = "(//tbody/tr[contains(@href, '/customers/place_order/') and contains(@href, 'draftId')]/td[NUM])[1]";
    By customerName = By.xpath("(//tbody/tr[contains(@href, '/customers/place_order/') and contains(@href, 'draftId')]/td[3]//div)[1]");
    By searchDrafts = By.xpath("//input[@placeholder='Search Drafts..']");
    String resultCount = "//span[contains(@class,'text-muted') and contains(text(),'COUNT')]";
    By btn_trash = By.xpath("(//button[@type='button']//*[local-name()='svg' and @data-icon='cdTrash'])[1]");
    By txt_delete = By.xpath("//h2[contains(text(),'Delete Draft Permanently?')]");
    String confirmationModel = "//div[contains(text(),'TEXT')]";
    By btn_yes = By.xpath("//button[contains(text(),'Yes')]");
    String referenceNumberDisplay = "//td[text()='#' and text()='REFERENCE']";
    By txt_noRecord = By.xpath("//div[contains(text(),'No Records Available.')]");
    String dropDownFilter = "//label[contains(text(),'FILTER')]/following-sibling::div";
    String dropDownOption = "//label[contains(text(),'')]/following-sibling::*//div[contains(text(),'OPTION')]";
    By clearFilter = By.xpath("//span[contains(text(),'Clear Filters')]");
    By referenceNum = By.xpath("(//tbody/tr[contains(@href, 'place-order') and contains(@href, 'draftId')]//td[3])[1]");
    String pendingApproval = "(//tbody/tr[contains(@href, '/customers/place_order/') and contains(@href, 'draftId')]/td[9]/span[contains(text(), 'STATUS')])[1]";





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
    public boolean isLastDraftDisplayedVito(String total){
        distributorUI.waitForVisibility(By.xpath(txt_lastDraft.replace("TOTAL", total)));
        return distributorUI.isDisplayed(By.xpath(txt_lastDraft.replace("TOTAL", total)),90);
    }

    public boolean isPendingApprovalOrdersDisplayed(){
        return distributorUI.isDisplayed(pendingApprovalOrders);
    }

    public void clickOnApprovalPendingOrder(){
        distributorUI.click(pendingApprovalOrders);
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
        return distributorUI.isDraftOrdersNotOlder30DaysStable(draftRowCount);
    }
    public boolean isRestaurantLastDraftDisplayed(String total){
        distributorUI.waitForVisibility(By.xpath(txt_restaurantLastDraft.replace("TOTAL", total)));
        return distributorUI.isDisplayed(By.xpath(txt_restaurantLastDraft.replace("TOTAL", total)));
    }
    public String getReferenceNumOP() throws InterruptedException {
        distributorUI.waitForVisibility(referenceNumOP);
        distributorUI.waitForCustom(3000);
        return distributorUI.getText(referenceNumOP);
    }
    public String getReferenceNumDP() throws InterruptedException {
        distributorUI.waitForVisibility(referenceNumDP);
        distributorUI.waitForCustom(3000);
//        return distributorUI.getText(referenceNumDP);
        String referenceNum = distributorUI.getText(referenceNumDP);
        return referenceNum.substring(1);
    }
    public String getDraftsColumn(String num) throws InterruptedException {
        distributorUI.waitForVisibility(By.xpath(draftsColumnDP.replace("NUM", num)));
        distributorUI.waitForCustom(3000);
        return distributorUI.getText(By.xpath(draftsColumnDP.replace("NUM", num)));
    }
    public String getCustomerNameDP() throws InterruptedException {
        distributorUI.waitForVisibility(customerName);
        distributorUI.waitForCustom(3000);
        return distributorUI.getText(customerName);
    }
    public void typeOnSearchDrafts(String draft) throws InterruptedException {
        distributorUI.click(searchDrafts);
        distributorUI.clear(searchDrafts);
        distributorUI.waitForCustom(1000);
        distributorUI.sendKeys(searchDrafts, draft);
    }
    public int getDraftCount()throws InterruptedException{
        distributorUI.waitForVisibility(draftDate);
        distributorUI.waitForCustom(3000);
        return distributorUI.countElements(draftDate);
    }
    public boolean isResultCountDisplayed(String count){
        try {
            distributorUI.waitForVisibility(By.xpath(resultCount.replace("COUNT", count)));
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(By.xpath(resultCount.replace("COUNT", count)));
    }
    public void clickTrashIcon()throws InterruptedException{
        distributorUI.waitForVisibility(btn_trash);
        distributorUI.click(btn_trash);
    }
    public boolean isDraftDeleteConfirmationDisplayed(String text){
        try {
            distributorUI.waitForVisibility(By.xpath(confirmationModel.replace("TEXT", text)));
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(By.xpath(confirmationModel.replace("TEXT", text)));
    }
    public boolean isDraftsDeleteTextDisplayed(){
        try {
            distributorUI.waitForVisibility(txt_delete);
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(txt_delete);
    }
    public void clickYesButton()throws InterruptedException{
        distributorUI.waitForVisibility(btn_yes);
        distributorUI.click(btn_yes);
    }
    public boolean isReferenceNumberDisplayed(String reference){
        try {
            distributorUI.waitForVisibility(By.xpath(referenceNumberDisplay.replace("REFERENCE", reference)));
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(By.xpath(referenceNumberDisplay.replace("REFERENCE", reference)));
    }
    public boolean isDraftsDeleted(){
        try {
            distributorUI.waitForVisibility(txt_noRecord);
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(txt_noRecord);
    }
    public void clickDropDownFilter(String filter)throws InterruptedException{
        distributorUI.waitForVisibility(By.xpath(dropDownFilter.replace("FILTER", filter)));
        distributorUI.click(By.xpath(dropDownFilter.replace("FILTER", filter)));
    }
    public void clickDropDownFilterOption(String option)throws InterruptedException{
        distributorUI.waitForVisibility(By.xpath(dropDownOption.replace("OPTION", option)));
        distributorUI.click(By.xpath(dropDownOption.replace("OPTION", option)));
    }
    public void clickClearFilter()throws InterruptedException{
        distributorUI.waitForVisibility(clearFilter);
        distributorUI.click(clearFilter);
    }
    public String getReferenceNum() throws InterruptedException {
        distributorUI.waitForVisibility(referenceNum);
        distributorUI.waitForCustom(3000);
        String referenceNumOP = distributorUI.getText(referenceNum);
        return referenceNumOP.substring(1);
    }
    public boolean isPendingApprovalDraftDisplayed(String status){
        distributorUI.waitForVisibility(By.xpath(pendingApproval.replace("STATUS", status)));
        return distributorUI.isDisplayed(By.xpath(pendingApproval.replace("STATUS", status)));
    }
    public void pendingApprovalDraftClick(String status){
        distributorUI.waitForVisibility(By.xpath(pendingApproval.replace("STATUS", status)));
        distributorUI.click(By.xpath(pendingApproval.replace("STATUS", status)));
    }
    public boolean isDraftOrderReferenceNotDisplayedInOPSide() {
        try {
            return !distributorUI.isDisplayed(referenceNumDP);
        } catch (NoSuchElementException e) {
            return true;
        }
    }

}
