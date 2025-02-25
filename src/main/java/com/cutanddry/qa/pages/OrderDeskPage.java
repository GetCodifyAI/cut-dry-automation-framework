package com.cutanddry.qa.pages;

import org.openqa.selenium.By;

public class OrderDeskPage extends LoginPage{
    By Order_Desk_txt = By.xpath("//h2[contains(text(),'Order Desk')]");
    By Submitted_orders_tab = By.xpath("//a[contains(text(),'Submitted Orders')]");
    By DraftOrders_tab = By.xpath("//a[contains(text(),'Draft Orders')]");
    By CustomerName_Draft_orders_text = By.xpath("(//div[contains(text(),'Customer')])[2]");
    By CustomerName_Submitted_orders_text = By.xpath("(//div[contains(text(),'Customer')])[last()]");
    By DraftOrderPageReviewBtn = By.xpath("(//div[contains(@class, '_10q9czs')]//a[text()='Review'])[1]");
    By DraftOrderPageReviewBtn2 = By.xpath("(//div[contains(@class, '_10q9czs')]//a[text()='Review'])[2]");
    By isDateErrorDisplayed = By.xpath("//div[contains(text(),'Delivery date is not valid')]");
    By DraftOrderReviewPageQuantityIncrementBtn = By.xpath("(//*[local-name()='svg' and @data-icon='plus'])[1]");
    By DraftOrderReviewPageQuantityDecrementBtn = By.xpath("(//*[local-name()='svg' and @data-icon='minus'])[1]");
    By DraftOrderReviewPageQuantityEdit = By.xpath("(//input[contains(@data-input,'quantityInput')])[1]");
    By SaveDraftBtn = By.xpath("//button[@class='mr-3 btn btn-outline-primary']");
    By DraftSavedSucessfullyText = By.xpath("//h2[@class='swal2-title']");
    By SucessfullySavedOverlayOkBtn = By.xpath("//button[contains(text(),'OK')]");
    By UploadFile = By.xpath("//div[contains(@class, '_t6r87r')]/input[@type='file']");
    By VoiceUploadProcessingText = By.xpath("//div[text()='New draft order upload is processing, please']");
    By AddLineBtn = By.xpath("//button[@class='btn btn-outline-primary']");
    By DropdownItemSelect = By.xpath("//div[@class='themed_select__placeholder css-1wa3eu0-placeholder']");
    By SelectRandomOption = By.cssSelector(".themed_select__option");
    By AddItemBtn = By.xpath("//button[@class='btn btn-outline-primary']");
    By ItemAddDeleteSucessfulTxt = By.xpath("//h2[contains(text(),'Saved Successfully')]");
//    By DeleteItemBtn = By.xpath("(//td[@class='_pe8div'])[last()]");
By DeleteItemBtn = By.xpath("(//*[local-name()='svg' and @data-icon='trash-can'])[last()]");
    By DeleteConfirmationOverlayYesBtn = By.xpath("//button[contains(text(),'Yes')]");
    By ItemNameTxt = By.xpath("(//div[@class='_12e4m8i'])[1]");
    By lbl_SearchProductPopup = By.xpath("//div[contains(text(),'Update Item')]");
    By SearchProduct = By.xpath("//div[@class='themed_select__placeholder css-1wa3eu0-placeholder']");
    By SelectedswapItem = By.xpath("//div[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'dmab : almond milk barista-6x32oz-califia')]");
    By firstDropdownItem = By.xpath("(//div[contains(@class, 'themed_select__option')])[1]");
    By SwapConfirmBtn = By.xpath("//button[contains(text(),'Confirm')]");
    By CustomerSelectionDropdown = By.xpath(" //div[contains(text(),'Customer')]/..//div[contains(@class,'select__dropdown-indicator')]");
    By CustomerName = By.cssSelector(".cd_themed_select__option.css-yt9ioa-option");
    By LocationSelectionDropdown = By.xpath("//div[contains(text(),'Location')]/..//div[contains(@class,'select__dropdown-indicator')]");
    By LocationName = By.cssSelector(".cd_themed_select__menu .cd_themed_select__option");
    By DeliveryDateInput = By.xpath("//div[text()='Delivery Date']/following-sibling::div//div[contains(@class,'cd_themed_select__control')]");
    String DeliveryDateSelect = "//div[contains(text(),'DELIVERYDATE')]";
    By DeliveryDateNotValidTxt = By.xpath("//div[@class='text-danger']");
//    By ReviewTxt = By.xpath("//h2[contains(text(),'pending_review')]");
//    By ReviewTxt = By.xpath("//*[contains(text(),'PENDING REVIEW')]");
    By anyOrderTxt = By.xpath("//*[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'quantity')]");
    By SubmittedOrderPageReViewBtn = By.xpath("(//div[contains(@class, '_10q9czs row')]//div//a[text()='Review' and contains(@href,'/order-desk')])[last()]");
    By SubmitTxt = By.xpath("//*[contains(text(),'Submit Order')]");

    public boolean isOrderDeskTextDisplayed(){
        try{
            distributorUI.waitForVisibility(Order_Desk_txt);
        }
        catch( Exception e){
            return false;
        }
        return distributorUI.isDisplayed(Order_Desk_txt);
    }

    public void clickOnSubmittedOrders(){
        distributorUI.click(Submitted_orders_tab);
    }

    public void clickOnDraftOrders(){
        distributorUI.click(DraftOrders_tab);
    }

    public boolean isDraftedOrdersCustomerTxtDisplayed(){
        return distributorUI.isDisplayed(CustomerName_Draft_orders_text);
    }

    public void clickOnDraftOrderPageReviewBtn(){
        distributorUI.click(DraftOrderPageReviewBtn);
    }

    public void clickOnDraftOrderPageReviewBtn2(){
        distributorUI.click(DraftOrderPageReviewBtn2);
    }

    public boolean isDateErrorDisplayedInDraftOrder(){
        return distributorUI.isDisplayed(isDateErrorDisplayed);
    }

    public void clickOnAddQuantityBtnOnDraftOrderReviewPage(){
        distributorUI.click(DraftOrderReviewPageQuantityIncrementBtn);
    }

    public void clickOnsubstractQuantityBtnOnDraftOrderReviewPage(){
        distributorUI.click(DraftOrderReviewPageQuantityDecrementBtn);
    }

    public void editItemQuantityOnDraftOrderReviewPage(String itemQuantity) throws InterruptedException {
        distributorUI.waitForCustom(3000);
        distributorUI.clearUsingJavaScript(DraftOrderReviewPageQuantityEdit);
        distributorUI.sendKeys(DraftOrderReviewPageQuantityEdit, itemQuantity);
        distributorUI.waitForCustom(4000);
    }

    public void clickOnSaveDraftBtn(){
        if (distributorUI.isDisplayed(anyOrderTxt)) {
            distributorUI.click(anyOrderTxt);
        } else {
            distributorUI.click(SubmitTxt);
        }
        distributorUI.click(SaveDraftBtn);
        try {
            distributorUI.waitForCustom(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isSaveDraftsucessfullytextisDisplayed(){
        try{
            distributorUI.waitForVisibility(DraftSavedSucessfullyText);
        }
        catch( Exception e){
            return false;
        }
        return distributorUI.isDisplayed(DraftSavedSucessfullyText);
    }

    public void ClickSucessfullySavedOverlayOkBtn(){
        distributorUI.click(SucessfullySavedOverlayOkBtn);
    }

    public void giveFilePath(String path){
        distributorUI.sendKeysToHiddenElements(UploadFile, path);
    }

    public int isVoiceUploadProcssingCountIncreased(){
        try {
            distributorUI.waitForCustom(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return distributorUI.countElements(VoiceUploadProcessingText);
    }

    public void ClickAddLineBtnOnDraftOrderReviewPage(){
        distributorUI.click(AddLineBtn);
    }

    public void SelectItemFromDropdown(){
        distributorUI.click(DropdownItemSelect);
        distributorUI.selectRandomOptionFromDropDown(SelectRandomOption);
    }

    public void ClickAddItemBtn(){
        distributorUI.waitForElementEnabledState(AddItemBtn,true);
        distributorUI.click(AddItemBtn);
    }

    public boolean isItemAddSucessfullytextisDisplayed(){
        return distributorUI.isDisplayed(ItemAddDeleteSucessfulTxt);
    }

    public void ClickOnDeleteBtn(){
        distributorUI.click(DeleteItemBtn);
    }

    public void ClickDeleteConfirmationOverlayYesBtn(){
        distributorUI.click(DeleteConfirmationOverlayYesBtn);
    }

    public boolean isSucessfullySavedtextisDisplayed(){
        return distributorUI.isDisplayed(ItemAddDeleteSucessfulTxt);
    }

    public void ClickOnItemName(){
        distributorUI.waitForVisibility(ItemNameTxt);
        distributorUI.click(ItemNameTxt);
    }

    public void ClickOnProductSearchBtn(){
        distributorUI.waitForVisibility(lbl_SearchProductPopup);
        distributorUI.click(SearchProduct);
    }

    public void ClickProductForSwap(){
//        distributorUI.click(SelectedswapItem);
        distributorUI.click(firstDropdownItem);

    }

    public void ClickSwapConfirmBtn(){
        distributorUI.click(SwapConfirmBtn);
    }

    public void SelectCustomer(){
        distributorUI.click(CustomerSelectionDropdown);
        distributorUI.selectRandomOptionFromDropDown(CustomerName);
    }

    public void SelectLocation(){
        distributorUI.click(LocationSelectionDropdown);
        distributorUI.selectRandomOptionFromDropDown(LocationName);
    }

    public void SelectDeliveryDate(String deliveryDate){
        try {
            distributorUI.waitForCustom(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        distributorUI.click(DeliveryDateInput);
        distributorUI.waitForVisibility(By.xpath(DeliveryDateSelect.replace("DELIVERYDATE",deliveryDate)));
        distributorUI.click(By.xpath(DeliveryDateSelect.replace("DELIVERYDATE",deliveryDate)));
    }

    public boolean isDeliveryDateInvalidTextisDisplayed(){
        return distributorUI.isDisplayed(DeliveryDateNotValidTxt);
    }

    public boolean isSubmittedOrderCustomerTxtDisplayed(){
        return distributorUI.isDisplayed(CustomerName_Submitted_orders_text);
    }

    public void clickOnSubmittedOrderPageReviewBtn(){
        distributorUI.click(SubmittedOrderPageReViewBtn);
    }


}
