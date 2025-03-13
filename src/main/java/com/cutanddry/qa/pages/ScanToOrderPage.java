package com.cutanddry.qa.pages;

import org.openqa.selenium.By;

public class ScanToOrderPage extends LoginPage{
    By ScanToOrderText = By.xpath("//*[contains(text(),'Scan to Order')]");
    By ScanToOrderItemInputField = By.xpath("//input[contains(@placeholder,'Scan barcode or search item by name or code')]");
    By ReviewAndConfirmBtn = By.xpath("//button[contains(text(),'Review & Confirm Order')]");
    By AddToCartBtn = By.xpath("//button[contains(normalize-space(.),'Add to Cart')]");
    String ItemInCart = "//div[contains(@class,'cartContainer')]//span[contains(text(),'ITEMCODE')]";
    By customerScreenScanToOrderCancelOrderBtn = By.xpath("//button[contains(text(),'Cancel Order')]");
    By ScanToOrderButton = By.xpath("//a[contains(text(), 'Scan to Order')]");
    String quantityIncreasePlusBtn = "//div[contains(@class,'cartContainer')]//span[contains(text(),'ITEMCODE')]/../following-sibling::div//*[name()='svg' and @data-icon='plus']";
    String quantityDecreaseMinusBtn = "//div[contains(@class,'cartContainer')]//span[contains(text(),'ITEMCODE')]/../following-sibling::div//*[name()='svg' and @data-icon='minus']";
    String itemPrice = "//div[contains(@class,'cartContainer')]//span[contains(text(),'ITEMCODE')]/../following-sibling::div/div[1]";

    By totalQuantityText = By.xpath("//span[contains(text(),'Total Quantity')]");
    By totalLineItemsText = By.xpath("//span[contains(text(),'Total Line Items')]");
    String totalQuantityOrderSummary = "//span[contains(text(),'Total Quantity')]/following-sibling::*";
    By cartContainerItems = By.xpath("//div[contains(@class,'cartItem')]");
    String totalLineItemsOrderSummary = "//span[contains(text(),'Total Line Items')]/following-sibling::*";
    By totalEstimatedCostText = By.xpath("//div[contains(text(),'Total Estimated Cost')]");
    String totalPriceOrderSummary = "//div[contains(text(),'Total Estimated Cost')]/following-sibling::*";
    By totalDiscountsText = By.xpath("//span[contains(text(),'Total Discounts')]");
    String productQuantity = "(//div[contains(@class,'cartItem')])[%d]//descendant::input[@data-input='quantityInput']";

    By searchInputField = By.xpath("//input[@placeholder='Scan barcode or search item by name or code']");
    String emptyCartText = "//*[text()='emptyCartText1']/./following-sibling::p[contains(text(), 'emptyCartText2')]";
    String customerNameandLocationInOrderScreen = "//h2[contains(text(), 'CUSTOMER_NAME')]/following-sibling::p[contains(text(), 'CUSTOMER_LOCATION')]";

    public boolean isScanToOrderTextDisplayed(){
        return distributorUI.isDisplayed(ScanToOrderText);
    }


    public boolean isReviewAndConfirmBtnEnabled(){
        return distributorUI.isElementEnabled(ReviewAndConfirmBtn);
    }

    public void clickOnScanToOrderItemInputField(){
        distributorUI.click(ScanToOrderItemInputField);
    }

    public void sendItemCodeToScanToOrderItemInputField(String Item){
        distributorUI.sendKeys(ScanToOrderItemInputField,Item);
    }

    public boolean isAddToCartButtonEnabled(){
        distributorUI.waitForVisibility(AddToCartBtn);
        return distributorUI.isElementEnabled(AddToCartBtn);
    }

    public void clickOnAddToCart(){
        try {
            distributorUI.waitForCustom(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        distributorUI.click(AddToCartBtn);
    }

    public boolean isItemAddedToTheCart(String itemCode){
        return distributorUI.isDisplayed(By.xpath(ItemInCart.replace("ITEMCODE",itemCode)));
    }

    public boolean isScanToOrderCancelBtnDisplayed(String customerCode){
        return distributorUI.isDisplayed(customerScreenScanToOrderCancelOrderBtn);
    }

    public void ClickOnScanToOrderCancelBtn(String customerCode){
        try{
            distributorUI.waitForCustom(2000);
        }catch(InterruptedException e){
            throw new RuntimeException(e);
        }
        distributorUI.click(customerScreenScanToOrderCancelOrderBtn);
    }

    public void clickQuantityIncreasePlusIcon(String ItemCode, int Quantity) throws InterruptedException {
        for(int i = 1; i < Quantity; i++){
            distributorUI.click(By.xpath(quantityIncreasePlusBtn.replace("ITEMCODE",ItemCode)));
            distributorUI.waitForCustom(2000);

        }
    }

    public void clickQuantityDecreaseMinusIcon(String ItemCode, int Quantity) throws InterruptedException {
        for(int i=0;i<Quantity;i++){
            distributorUI.click(By.xpath(quantityDecreaseMinusBtn.replace("ITEMCODE",ItemCode)));
            distributorUI.waitForCustom(2000);
        }
    }

    public double getItemPrice(String ItemCode){
        try {
            distributorUI.waitForCustom(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String itemPriceInString = distributorUI.getText(By.xpath(itemPrice.replace("ITEMCODE",ItemCode)));
        return Double.parseDouble(itemPriceInString.replace("$", ""));
    }

    public void clickOnReviewAndConfirm(){
        try {
            distributorUI.waitForCustom(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        distributorUI.click(ReviewAndConfirmBtn);
    }
    public boolean isTotalQuantityTextDisplayed()
    {
        return distributorUI.isDisplayed(totalQuantityText);
    }

    public boolean isTotalEstimatedCostTextDisplayed()
    {
        return distributorUI.isDisplayed(totalEstimatedCostText);
    }


    public boolean isTotalLineItemsTextDisplayed()
    {
        return distributorUI.isDisplayed(totalLineItemsText);
    }

    public boolean isTotalDiscountsTextDisplayed(){
        return distributorUI.isDisplayed(totalDiscountsText);
    }

    public double getTotalCostInOrderSummary()
    {
        try {
            distributorUI.waitForCustom(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String totalPriceInString = distributorUI.getText(By.xpath(totalPriceOrderSummary));
        return Double.parseDouble(totalPriceInString.replace("$", ""));
    }
    public int getCartLineItemCount(){
        int ItemCount = distributorUI.countElements(cartContainerItems);
        return ItemCount;
    }

    public int getProductQuantityValues(String xpath) {
        String  quantityValue =  distributorUI.getText(By.xpath(xpath), "value");
        return Integer.parseInt(quantityValue);
    }

    public int getProductQuantityValue(){
        int totalProducts = getProducttotalQuantityValues();
        int TotalProductQuantity = 0;

        for(int i = 1; i <= totalProducts; i++){
            String itemQuantity = String.format(productQuantity,i);
            TotalProductQuantity = TotalProductQuantity+ getProductQuantityValues(itemQuantity);
        }
        return TotalProductQuantity;
    }

    public int getProducttotalQuantityValues() {
        String quantityValueInOrderSummary = distributorUI.getText(By.xpath(totalQuantityOrderSummary));
        String quantityOnly = quantityValueInOrderSummary.replaceAll("\\D", "");

        return Integer.parseInt(quantityOnly);
    }

    public double getTotalCartPrice(String[] itemCodes) {
        double totalPrice = 0.0;

        for(String itemCode : itemCodes){
            totalPrice += getItemPrice(itemCode);
        }
        return totalPrice;
    }

    public int getCartItemCountInOrderSummary(){
        String itemCountInOrderSummary = distributorUI.getText(By.xpath(totalLineItemsOrderSummary));
        String itemCountOnly = itemCountInOrderSummary.replaceAll("\\D","");

        return Integer.parseInt(itemCountOnly);
    }

    public boolean isSearchInputFieldTextDisplayed(){
        return distributorUI.isDisplayed(searchInputField);
    }

    public String getSearchInputFieldText(){
        return distributorUI.getText(searchInputField,"placeholder");
    }

    public String getCTAButtonText(){
        String CTAbuttonText = distributorUI.getText(ReviewAndConfirmBtn);
        return CTAbuttonText;
    }

    public boolean isEmptyCartTextDisplayed(String emptyCartText1,String emptyCartText2){
        return distributorUI.isDisplayed(By.xpath(emptyCartText.replace("emptyCartText1",emptyCartText1 ).replace("emptyCartText2",emptyCartText2)));
    }

    public boolean isCustomerNameAndLocationDisplayedInScanToOrderScreen(String CustomerName,String CustomerLocation){
        return distributorUI.isDisplayed(By.xpath(customerNameandLocationInOrderScreen.replace("CUSTOMER_NAME",CustomerName).replace("CUSTOMER_LOCATION",CustomerLocation)));
    }



















}
