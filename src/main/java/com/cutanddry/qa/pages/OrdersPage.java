package com.cutanddry.qa.pages;

import org.openqa.selenium.By;

public class OrdersPage extends LoginPage{
    By txt_orders = By.xpath("//h2[contains(text(),'Orders')]");
    String supplierNameInPlaceOrder = "//div[contains(text(),'SUPPLIERNAME')]";
    String quantityIncreaseBtn = "//td[text()='ITEMCODE']/following-sibling::td//div[contains(@class, '_du1frc') and contains(@class, 'py-2') and contains(@class, 'ml-2')]/*";
    By checkOutBtn = By.xpath("//button[@data-for='cartCheckoutButton' and contains(text(),'$')]");
    By submitForApproval = By.xpath("//button[contains(text(),'Submit for Approval')]");
    By sendForApprovaltext = By.xpath("//strong[contains(text(),'Sent for approval!')]");
    By viewOrderInDraft = By.xpath("//button[contains(text(),'View Order in Drafts')]");
    By pendingApprovalText = By.xpath("//span[contains(text(),'Pending Approval')]");
    By selectOrderGuide = By.xpath("//div[contains(text(),'Select Order Guide')]");
    String orderGuide ="//div[contains(text(),'ORDERGUIDE')]";
    By ratingsOverlay = By.id("nps-modal");
    By ratingOverlayCloseBtn = By.xpath("//div[contains(text(),'✕')]");
    By lbl_firstOrderTickBox = By.xpath("//tbody/tr[2]/td[1]");
    By lbl_firstOrder = By.xpath("//tbody/tr[2]/td[2]");
    By btn_editOrder = By.xpath("//button[contains(text(),'Edit Order')]");
    By txt_editOrderPopup = By.xpath("//h2[contains(text(),'Edit Order?')]");
    By btn_confirm= By.xpath("//button[contains(text(),'Confirm')]");
    By txt_editOrder = By.xpath("//span/div[contains(text(),'Edit Order')]");
    By txt_submitPopup = By.xpath("//h2[contains(text(),'Submit Changes?')]");
    By btn_close = By.xpath("//button[contains(text(),'Close')]");
    By btn_bulkActions =    By.xpath("//button[span[contains(., 'Bulk Actions')]]");
    By txt_printConfirm = By.xpath("//a[contains(text(), 'Print Order Confirmations')]");
    By txt_printKitchenReceipt = By.xpath("//a[contains(text(), 'Print Kitchen Receipt')]");
    By tbx_searchOrders = By.xpath("//input[@placeholder='Search']");
    By tbx_firstOrder = By.xpath("//tbody/tr[2]/td[5]");
    By lbl_orderDateDropdown = By.xpath("(//div[contains(@class, 'css-1uccc91-singleValue')])[1]");
    By lbl_statusDropdown = By.xpath("(//div[contains(@class, 'css-1uccc91-singleValue')])[2]");
    By txt_date = By.xpath("(//td[2])[1]");
    By txt_status = By.xpath("(//td[10])[1]/div[1]");
    String days = "//div[text()='DATE']";
    String sts = "//div[text()='STATUS']";
    String date = "//td[text()='DATE']";
    String status = "//td[text()='STATUS']";
    By txt_resultsCount = By.xpath("//div[contains(text(), 'results')]");

    public boolean isOrdersTextDisplayed(){
        try {
            distributorUI.waitForVisibility(txt_orders);
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(txt_orders);
    }

    public void clickOnSupplier(String supplierName){
        distributorUI.click(By.xpath(supplierNameInPlaceOrder.replace("SUPPLIERNAME",supplierName)));
    }

    public void clickOnIncreaseQuantityBtnInItem(String ItemCode, int Quantity){
        for (int i = 0; i < Quantity; i++) {
            distributorUI.click(By.xpath(quantityIncreaseBtn.replace("ITEMCODE", ItemCode)));
        }
        if(distributorUI.isDisplayed(ratingsOverlay)){
            distributorUI.click(ratingOverlayCloseBtn);
        }
        distributorUI.click(checkOutBtn);
    }

    public void clickOnCheckoutBtnInOperator(){
        distributorUI.click(checkOutBtn);
        try {
            distributorUI.waitForCustom(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void clickOnSubmitForApproval(){
        try {
            distributorUI.waitForCustom(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        distributorUI.click(submitForApproval);
    }

    public boolean isSubmitForApprovalOverlayDisplayed(){
        return distributorUI.isDisplayed(sendForApprovaltext);
    }

    public void clickOnViewOrderInDrafts(){
        distributorUI.click(viewOrderInDraft);
    }

    public boolean isOrderDraftDisplayedForApproval(){
        return distributorUI.isDisplayed(pendingApprovalText);
    }

    public void clickOnOrderGuide(String OrderGuideName){
        if(distributorUI.isDisplayed(selectOrderGuide)){
            distributorUI.click(By.xpath(orderGuide.replace("ORDERGUIDE",OrderGuideName)));
        }
    }
    public void clickOnFirstOrder(){
        distributorUI.click(lbl_firstOrder);
    }
    public void clickOnEditOrder(){
        distributorUI.click(btn_editOrder);
    }
    public boolean isEditOrderPopupDisplayed(){
        return distributorUI.isDisplayed(txt_editOrderPopup);
    }
    public void clickOnConfirm(){
        distributorUI.click(btn_confirm);
    }
    public boolean isNavigatedToEditOrder(){
        return distributorUI.isDisplayed(txt_editOrder);
    }
    public boolean isSubmitPopupDisplayed(){
        return distributorUI.isDisplayed(txt_submitPopup);
    }
    public void clickOnClose(){
        distributorUI.click(btn_close);
    }
    public void selectFirstOrder(){
        distributorUI.click(lbl_firstOrderTickBox);
    }
    public void clickPrintKitchenReceipt(){
        distributorUI.click(btn_bulkActions);
        distributorUI.click(txt_printConfirm);
    }
    public void clickPrintOrderConfirmation(){
        distributorUI.click(btn_bulkActions);
        distributorUI.click(txt_printKitchenReceipt);
    }
    public void typeOnSearch(String code) throws InterruptedException {
        distributorUI.click(tbx_searchOrders);
        distributorUI.clear(tbx_searchOrders);
        distributorUI.waitForCustom(1000);
        distributorUI.sendKeys(tbx_searchOrders, code);
    }
    public String isCustomerSearchResultDisplayed() throws InterruptedException {
        distributorUI.waitForCustom(4000);
        return distributorUI.getText(tbx_firstOrder);
    }
    public void selectOrderDate(String day){
        distributorUI.click(lbl_orderDateDropdown);
        distributorUI.waitForVisibility(By.xpath(days.replace("DATE", day)));
        distributorUI.click(By.xpath(days.replace("DATE", day)));
    }
    public void selectOrderStatus(String stat){
        distributorUI.click(lbl_statusDropdown);
        distributorUI.waitForVisibility(By.xpath(sts.replace("STATUS", stat)));
        distributorUI.click(By.xpath(sts.replace("STATUS", stat)));
    }
    public boolean isOrderStatusChanged(String stat){
        try {
            distributorUI.isDisplayed(By.xpath(sts.replace("STATUS", stat)));
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(By.xpath(sts.replace("STATUS", stat)));
    }
    public boolean isOrderDateChanged(String day){
        try {
            distributorUI.isDisplayed(By.xpath(days.replace("DATE", day)));
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(By.xpath(days.replace("DATE", day)));
    }
    public String getResultsCount() throws InterruptedException {
        distributorUI.waitForCustom(2000);
        distributorUI.waitForVisibility(txt_resultsCount);
        String resultsText = distributorUI.getText(txt_resultsCount);
        return resultsText.split(" ")[0];
    }
    public String getCountDates() {
        String d = distributorUI.getText(txt_date);
        return String.valueOf(distributorUI.countElements(By.xpath(date.replace("DATE", d))));
    }
    public String getCountStatus() {
        String s = distributorUI.getText(txt_status);
        return String.valueOf(distributorUI.countElements(By.xpath(status.replace("STATUS", s))));
    }
}
