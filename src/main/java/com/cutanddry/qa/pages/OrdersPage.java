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
}
