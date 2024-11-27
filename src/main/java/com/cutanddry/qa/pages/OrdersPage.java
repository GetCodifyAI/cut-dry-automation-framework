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
    By ratingOverlayIframe = By.xpath("//iframe[contains(@aria-label,'NPS Survey')]");
    By ratingOverlayCloseBtn = By.cssSelector(".ask-me-later .close-icon");


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
        if(distributorUI.isDisplayed(ratingOverlayIframe)){
            distributorUI.switchToFrameByElement(ratingOverlayIframe);
            distributorUI.click(ratingOverlayCloseBtn);
            try {
                distributorUI.waitForCustom(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
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
}
