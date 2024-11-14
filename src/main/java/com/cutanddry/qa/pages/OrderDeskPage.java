package com.cutanddry.qa.pages;

import org.openqa.selenium.By;

public class OrderDeskPage extends LoginPage{
    By Order_Desk_txt = By.xpath("//h2[contains(text(),'Order Desk')]");
    By Submitted_orders_tab = By.xpath("//a[contains(text(),'Submitted Orders')]");
    By DraftOrders_tab = By.xpath("//a[contains(text(),'Draft Orders')]");
    By CustomerName_Draft_orders_text = By.xpath("(//div[contains(text(),'Customer')])[2]");
    By DraftOrderPageReviewBtn = By.xpath("(//div[contains(@class, '_10q9czs')]//a[text()='Review'])[4]");
    By DraftOrderReviewPageQuantityIncrementBtn = By.xpath("(//div[@class='p-2 px-sm-3 _du1frc _18jhc3z py-2 ml-2 text-center align-middle'])[1]");
    By DraftOrderReviewPageQuantityDecrementBtn = By.xpath("(//div[@class='p-2 px-sm-3 _du1frc _18jhc3z py-2 mr-2 text-center align-middle'])[1]");
    By DraftOrderReviewPageQuantityEdit = By.xpath("(//input[@class ='_hk3n6z form-control _b1frhss'])[1]");
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
    By DeleteItemBtn = By.xpath("(//td[@class='_pe8div'])[last()]");
    By DeleteConfirmationOverlayYesBtn = By.xpath("//button[@class='swal2-confirm _1fmw5qi order-2 swal2-styled']");
    By ItemNameTxt = By.xpath("(//div[@class='_12e4m8i'])[1]");
    By SearchProduct = By.xpath("//div[@class='themed_select__placeholder css-1wa3eu0-placeholder']");
    By SelectedswapItem = By.xpath("//div[contains(text(),'DMAB : Almond Milk Barista-6x32oz-Califia')]");
    By SwapConfirmBtn = By.xpath("//button[contains(text(),'Confirm')]");
    By CustomerSelectionDropdown = By.xpath(" //div[contains(text(),'Customer')]/..//div[contains(@class,'select__dropdown-indicator')]");
    By CustomerName = By.cssSelector(".cd_themed_select__option.css-yt9ioa-option");
    By LocationSelectionDropdown = By.xpath("(//div[@class='cd_themed_select__value-container css-1hwfws3'])[1]");
    By LocationName = By.cssSelector(".cd_themed_select__option.css-1n7v3ny-option");
    By DeliveryDateInput = By.xpath("//input[contains(@class,'form-control') and @type='text']");
    By DeliveryDateSelect = By.xpath("//input[contains(@class,'form-control react-datepicker')]");
    By DeliveryDateNotValidTxt = By.xpath("//div[@class='text-danger']");
    By ReviewTxt = By.xpath("//h2[contains(text(),'pending_review')]");



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

    public void clickOnAddQuantityBtnOnDraftOrderReviewPage(){
        distributorUI.click(DraftOrderReviewPageQuantityIncrementBtn);
    }

    public void clickOnsubstractQuantityBtnOnDraftOrderReviewPage(){
        distributorUI.click(DraftOrderReviewPageQuantityDecrementBtn);
    }

    public void editItemQuantityOnDraftOrderReviewPage(String itemQuantity) throws InterruptedException {
        distributorUI.clearUsingJavaScript(DraftOrderReviewPageQuantityEdit);
        distributorUI.sendKeys(DraftOrderReviewPageQuantityEdit, itemQuantity);
        distributorUI.waitForCustom(4000);
    }

    public void clickOnSaveDraftBtn(){
        distributorUI.click(ReviewTxt);
        distributorUI.click(SaveDraftBtn);
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
        distributorUI.click(ItemNameTxt);
    }

    public void ClickOnProductSearchBtn(){
        distributorUI.click(SearchProduct);
    }

    public void ClickProductForSwap(){
        distributorUI.click(SelectedswapItem);
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
        distributorUI.clearUsingJavaScript(DeliveryDateInput);
        distributorUI.sendKeys(DeliveryDateSelect,deliveryDate);
    }

    public boolean isDeliveryDateInvalidTextisDisplayed(){
        return distributorUI.isDisplayed(DeliveryDateNotValidTxt);
    }


}
