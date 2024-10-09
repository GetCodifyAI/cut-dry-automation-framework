package com.cutanddry.qa.pages;

import org.openqa.selenium.By;

public class CustomersPage extends LoginPage {
    By tbx_searchCustomers = By.xpath("//input[@placeholder='Search Customers']");
    String btnOrderGuide = "//td[text()='CODE']/../td[8]//button";
    By lbl_itemNameList = By.xpath("//td//span/div[@data-tip='View Product Details']");
    By btn_increaseQtyFirstRow = By.xpath("//tr[1]/td[8]/div/div/div/div[3]");
    By btn_decreaseQtyFirstRow = By.xpath("//tr[1]/td[8]/div/div/div/div[1]");
    By btn_increaseQtySecondRow = By.xpath("//tr[2]/td[8]/div/div/div/div[3]");
    By btn_decreaseQtySecondRow = By.xpath("//tr[2]/td[8]/div/div/div/div[1]");
    By btn_checkout = By.xpath("//button[text()='$']/../button[2]");
    By btn_catalog = By.xpath("//div[text()='Catalog']");
    By tbx_catalogSearch = By.xpath("//input[@placeholder='Search catalog...']");
    String lbl_catalogSearchItemList = "//div[contains(@class, '_3quvq7') and text()='NAME']";
    By btn_addToCart = By.xpath("//div[contains(@class, '_3quvq7') and text()='Alfalfa Sprouts']/ancestor::div[contains(@class, 'card')]//button[contains(text(), 'Add to Cart')]");
    By tbx_itemQuantityFirstRow = By.xpath("//tr[1]//td[8]//input");
    By lbl_itemPriceFirstRow = By.xpath("//tr[1]//td[7]/div");
    By btn_increaseQtyCatalogSearchValueOne = By.xpath("//input[@type='number' and @value='1']/../following-sibling::div");
    By btn_increaseQtyCatalogSearchValueTwo = By.xpath("//input[@type='number' and @value='2']/../following-sibling::div");
    By btn_decreaseQtyCatalogSearchValueOne = By.xpath("//input[@type='number' and @value='1']/../preceding-sibling::div");
    By btn_decreaseQtyCatalogSearchValueTwo = By.xpath("//input[@type='number' and @value='2']/../preceding-sibling::div");
    By btn_decreaseQtyCatalogSearchValueThree = By.xpath("//input[@type='number' and @value='3']/../preceding-sibling::div");
    By tbx_itemQuantityCatalogSearch = By.xpath("//input[@type='number']");
    By lbl_itemPriceSearchCatalogList = By.xpath("//div[contains(., 'Artichoke') and not(contains(., '-24ct')) and not(contains(., 'Bottoms'))]//span[contains(text(),'$') and not(contains(text(),' ')) and not(@class='text-muted')]");
    By btn_decreaseQtyCartRowOne = By.xpath("//tr[2]/td//input/../preceding-sibling::div");
    By btn_increaseQtyCartRowOne = By.xpath("//tr[2]/td//input/../following-sibling::div");
    By tbx_itemQuantityCartRowOne = By.xpath("//tr[2]/td//input/");
    By lbl_itemPriceCartRowOne = By.xpath("//tr[2]/td//span[contains(text(),'$')]");
    By lbl_cartTotal = By.xpath("//td[contains(text(),'Total')]/following-sibling::td");
    By btn_submitOrder = By.xpath("//button[contains(text(),'Submit Order')]");
    By btn_duplicateOrderYes = By.xpath("//h2[contains(text(),'Duplicate Order')]/../..//button[text()='Yes']");
    By lbl_thankYouForOrder = By.xpath("//*[contains(text(),'Thank you for your order!')]");
    By btn_print = By.xpath("//button[contains(text(), 'Print')]");
    By lbl_printFriendlyOrderGuide = By.xpath("//h5[contains(text(), 'Print-Friendly Order Guide')]");
    By btn_downloadOrderGuide = By.xpath("//button[contains(text(), 'Download Order Guide')]");
    By tbx_orderGuideSearch = By.xpath("//input[@placeholder='Search order guide...']");
    By btn_create = By.xpath("//button[contains(text(), 'Create')]");
    By tbx_OrderGuideName = By.xpath("//input[@placeholder='Enter Name']");
    By btn_submitOrderGuide = By.xpath("//button[contains(text(), 'Submit')]");
    By btn_addFromCatalog = By.xpath("//div[contains(text(), 'Add from Catalog')]");
    By btn_uploadFile = By.xpath("//button[contains(text(), 'Upload File')]");
    By btn_addToOrderGuide = By.xpath("//button[@data-tip='Add to Order Guide']");
    By btn_closeEditorCatalog = By.xpath("//button[contains(text(), 'Close Editor')]");
    By btn_closeEditor = By.xpath("//a[contains(text(), 'Close Editor')]");
    By btn_removeFromOrderGuide = By.xpath("//button[@data-tip='Remove from Order Guide']");
    By upload_file = By.xpath("//input[@type='file']");
    By btn_next = By.xpath("//button[text()='Next']");
    By btn_confirm = By.xpath("//button[text()='Confirm']");
    By txt_orderGuideCreateSuccess = By.xpath("//h2[contains(text(), 'Order guide updated successfully')]");
    By btn_OK = By.xpath("//button[text()='OK']");
    By msg_banner = By.xpath("//span[text()='Test Broadcast Message']");
    By lbl_productDetails = By.xpath("//span[text()='Product Details']");
    By lbl_topCategoryPicks = By.xpath("//div[text()='Top Category Picks']");
    String lbl_itemAdded = "//div[text()='Top Category Picks']//following-sibling::div//div[text()='CODE']";
    String lbl_searchedItem = "//div[text()='CODE']";
    By section_compareSimilar = By.xpath("//div[text()='Compare Similar Items']");
    String lbl_recommendedForYouItem = "//div[text()='Recommended for You']//following-sibling::div//div[text()='CODE']";
    String lbl_recommendedBySalesRep = "//div[contains(text(), 'Recommended by')]//following-sibling::div//div[contains(text(), 'CODE')]";
    By section_dontForget = By.xpath("//div[text()=\"Don't Forget to Order\"]");
    By section_moreFromThisBrand = By.xpath("//div[contains(text(), 'More From')]");
    By btn_companyDropdown = By.xpath("//button[.//span[text()='Company:']]");
    By txt_companyDropdownText = By.xpath("//a[contains(text(), 'Independent Foods Co')]");
    By btn_edit = By.xpath("//button[contains(text(), 'Edit')]");
    By txt_editOrderGuide= By.xpath("//span[contains(text(), 'Edit Order Guide')]");
    By btn_moreOptions = By.xpath("//span[contains(text(), 'More Options')]");
    By btn_exportOrderGuide = By.xpath("//a[contains(text(), 'Export Order Guide (XLSX)')]");
    By btn_importOrderGuide = By.xpath("//a[contains(text(), 'Import Order Guide (XLSX)')]");
    By btn_uploadToOrder = By.xpath("//a[contains(text(), 'Upload to Order')]");
    By txt_reviewOrder = By.xpath("//div[text()='Review Order']");
    By txt_orderGuideUpdated = By.xpath("//h2[text()='Order guide updated successfully']");
    By dropdown_SortOptions = By.xpath("//div[text()='Sort Items By:']/following::div[contains(@class, 'cd_themed_select__control')][1]");
    By dropdown_customOrder =  By.xpath("//div[contains(text(), 'Sort Items By:')]//following::div[contains(text(), 'Custom Order')]");
    By dropdown_lastOrdered = By.xpath("//div[contains(text(), 'Sort Items By:')]//following::div[contains(text(), 'Last Ordered')]");
    By dropdown_alphabetical = By.xpath("//div[contains(text(), 'Sort Items By:')]//following::div[contains(text(), 'Alphabetical (A-Z)')]");
    By dropdown_itemCategories = By.xpath("//div[contains(text(), 'Sort Items By:')]//following::div[contains(text(), 'Item Categories')]");
    By txt_produce = By.xpath("//div[text()='produce']");
    By txt_firstItem = By.xpath("//div[text()='artichoke -24ct']");
    By txt_minOrderBanner = By.xpath("//div[contains(text(), 'Add a few more items worth') and contains(text(), 'to meet minimum order amount')]");
    By txt_popupAlertOrderMin = By.xpath("//h2[text()='Order Minimum Not Met']");
    By btn_previousDraftOrderNo = By.xpath("//div[contains(text(),'previous draft order')]/..//div[text()='No']");
    String txt_customerCode = "//td[text()='CODE']";
    By tb_orders = By.xpath("//a[text()='Orders' and @role='tab']");
    By txt_standingOrders = By.xpath("//div[text()='Standing Orders: ']");
    By btn_createStandingOrders = By.xpath("//div[text()='Create']");
    By dropdown_delivery = By.xpath("//div[text()='Delivery:']/following-sibling::div//div[text()='Select Days...']");
    String txt_deliveryDay = "//div[text()='DAY']/preceding-sibling::input[@type='checkbox']";
    By btn_setStandingOrder = By.xpath("//button[text()='Set Standing Order ']");
    By txt_EmailPopup = By.xpath("//div[text()='Standing Order CC Emails']");
    By dropdown_email = By.xpath("//div[text()='Select...']");
    By txt_testEmail = By.xpath("//div[text()='Test_Automation_QA (quinn-bins-sd9lph1ucd@e.rainforestqa.com)']");
    By btn_schedule = By.xpath("//button[text()='Schedule Standing Order']");
    By txt_success = By.xpath("//h2[text()='Success']");
    By btn_editStandingOrders = By.xpath("//div[text()='Edit']");
    By btn_removeDelivery = By.xpath("(//div[contains(@class, 'cd_themed_select__clear-indicator')])[1]");
    By btn_addAnotherStandingOrder = By.xpath("//button[contains(text(), 'Add another')]");
    By btn_deleteStandingOrders = By.xpath("//div[text()='Delete']");
    By txt_deletePopup = By.xpath("//h2[text()='Are you sure?']");
    By btn_yes = By.xpath("//button[text()='Yes']");
    By btn_increaseQtyFirstRowInDist = By.xpath("//tbody/tr/td[6]/div/div/div/div[3]");
    By btn_decreaseQtyFirstRowInDist = By.xpath("//tbody/tr[2]/td[6]/div/div/div/div[1]");
    By btn_checkoutCashCarry = By.xpath("//button[@data-tip='Click here to checkout']");
    By txt_foodServiceDistCenter = By.xpath("//span[text()='Food Service Distribution Centre']");
    By txt_retailDistCenter = By.xpath("//span[text()='Retail Distribution Centre']");
    String txt_orders = "(//div[contains(text(), 'Order #')])[NUM]";
    By btn_back = By.xpath("//button[contains(text(), 'Back')]");
    String SelectCustomerByCode = "//td[contains(text(),'CODE')]";
    By OrdersTabTxt = By.xpath("//a[contains(text(),'Orders') and @class='_ngcfan text-center nav-item nav-link']");
    By OrderIdTxt = By.xpath("//th[contains(text(),'Order ID')]");
    By OrderDateSort = By.xpath("//div[contains(text(),'Order Date')]");
    By DeliveryDate = By.xpath("//div[contains(text(),'Delivery Date')]");
    By OrderDateSortData = By.cssSelector("tr._du1frc td:nth-child(1)");
    By DeliveryDateSortData = By.cssSelector("tr._du1frc td:nth-child(2)");
    By txt_discountDisclaimerOrderReview = By.xpath("//div[contains(text(), 'Case discounts will be reflected on your invoice.')]");
    By txt_discountDisclaimerOrderDetails = By.xpath("//div[normalize-space() = '*Prices are subject to change. Weighed item prices are estimated. Case discounts will be reflected on your invoice.']");
    By lbl_firstRowOrderTab = By.xpath("//table//th[contains(text(), 'Order ID')]/ancestor::table//tr[1]/td[1]");
    By txt_southwest = By.xpath("//div[contains(text(),'Southwest Traders')]");
    By txt_substitutions = By.xpath("//div[contains(normalize-space(text()), 'Substitutions')]");
    By btn_saveSelection = By.xpath("//button[normalize-space(text())='Save Selection']");
    By btn_donotsubs = By.xpath("//button[normalize-space(text())='Do Not Substitute']");
    By txt_replacement = By.xpath("//div[contains(normalize-space(text()), 'If out of stock, sub with')]");
    String txt_item = "//div[contains(text(), 'CODE')]";
    By btn_increaseQtyFirstRowInCheckout = By.xpath("//tr[2]/td[4]/div/div/div/div[3]");
    By btn_decreaseQtyFirstRowInCheckout = By.xpath("//tr[2]/td[4]/div/div/div/div[1]");
    String txt_subItems = "(//div[contains(text(), 'Available Substitutes')]/following-sibling::div//div[contains(text(), '1 x $')])[NUM]";
    By CustomerTxt = By.xpath("//h2[contains(text(),'Customers')]");
    By Test_AutomationOrderGuide = By.xpath("//div[@class='cd_themed_select__single-value css-1uccc91-singleValue' and contains(text(),'Independent Foods Co')]");
    By AutomationGuide = By.xpath("//div[contains(text(),'Test_Automation')]");
    By StockCountTxt = By.xpath("//span[@data-for='cd-label-tooltip' and contains(text(), 'Stock: 50 Pkg')]");
    By CustomerGroupTxt = By.xpath("//div[contains(text(),'Customer Group')]");
    By txt_outOfStock = By.xpath("//div[contains(text(), 'This item is currently out of stock and may not be shipped')]");
    By txt_unitInDist = By.xpath("//tbody/tr/td[3]/div/div/div/div");
    By txt_eachDropdownItem = By.xpath("//div[contains(@class, 'cd_themed_select__option') and text()='Each']");
    By txt_caseDropdownItem = By.xpath("//div[contains(@class, 'cd_themed_select__option') and text()='Case']");
    By btn_orderGuide = By.xpath("//button[normalize-space(text())='Order Guide']");
    By btn_close = By.xpath("//button[contains(@class, 'close')]/span[text()='×']");
    By btn_addSection = By.xpath("//a[contains(text(), 'Add Section')]");
    By txt_addSection = By.xpath("//div[contains(text(), 'Add Section Header')]");
    By tbx_section = By.xpath("//input[@placeholder='e.g. Dairy']");
    By btn_save = By.xpath("//button[normalize-space(text())='Save']");
    String txt_addedSection = "//div[contains(@data-rbd-draggable-id, 'item')]//div[contains(text(), 'NAME')]";
    By txt_lastID = By.xpath("//div[contains(@data-rbd-draggable-id, 'item-')][last()]");
    By txt_firstID = By.xpath("//div[contains(@data-rbd-draggable-id, 'item-')][1]");
    String txt_source = "//div[@data-rbd-draggable-id='ID']";
    String txt_section = "//div[contains(text(), 'NAME')]";
    String btn_editSection = "//div[contains(text(), 'NAME')]/following-sibling::div[@class='col-2 d-flex justify-content-end align-items-center']/*[local-name() = 'svg' and @data-icon='pencil']";
    By btn_delete = By.xpath("//button[contains(text(), 'Delete')]");
    By txt_editSection = By.xpath("//div[contains(text(), 'Edit Section Header')]");
    By txt_areYouSure = By.xpath("//h2[text()='Are you sure?']");
    By btn_deleteIcon = By.xpath("//*[local-name() = 'svg' and @data-icon='trash-alt']");
    By tb_boost = By.xpath("//a[text()='Boost' and @role='tab']");
    By txt_customerSpecific = By.xpath("//div[contains(text(),'Customer-specific Broadcast')]");
    By btn_editMessage = By.xpath("//button[contains(text(), 'Edit Message')]");
    By btn_clearMessage = By.xpath("//button[contains(text(), 'Clear Message')]");
    By btn_saveMessage = By.xpath("//button[contains(text(), 'Save Message')]");
    By btn_addItems = By.xpath("//button[contains(text(), 'Add Items')]");
    By txtArea = By.xpath("//div[contains(@class, 'col-lg-9')]//textarea");
    By input_selectItem = By.xpath("//div[contains(text(),'Select...')]/following-sibling::div//input");
    By btn_add = By.xpath("//button[contains(text(), 'Add')]");
    By btn_removeItem = By.xpath("(//*[local-name() = 'svg' and @data-icon='times-circle'])[2]");
    By EditCustomerGroupBtn = By.xpath("//div[contains(text(), 'Customer Group')]//following-sibling::div//div[@class='pl-0 col-sm-auto col-auto']");
    By CreateCutomerGroupTextField = By.xpath("//input[@id='react-select-6-input']");
    By CustomerGroupSavebtn = By.xpath("//button[contains(text(),'Save')]");
    String CustomerGroupName = "//div[contains(text(),'Customer Group')]/following-sibling::div//div[contains(text(),'GROUPNAME')]";
    By ClearAllCustomerGroupBtn = By.xpath("//div[contains(@class,'themed_select__indicator themed_select__clear-indicator css-tlfecz-indicatorContainer')]");
    By InviteNewUsersBtn = By.xpath("//button[contains(text(),'Invite New Users')]");
    By AddUserText = By.xpath("//div[contains(text(),'Add User')]");
    By UserNameInputField = By.xpath("//label[text()='Name']/following-sibling::input");
    By UserEmailInputField = By.xpath("//label[text()='Email']/following-sibling::input");
    By SaveChangeswithoutSendingInviteBtn = By.xpath("//span[text()='Save changes without sending an invite']/parent::button");
    By SuccessfulUpdatedMsg = By.xpath("//div[contains(text(),'Successfully updated user details.')]");
    By SuccessfulRemovedMsg = By.xpath("//div[contains(text(),'Successfully removed the user')]");
    String UserName = "//span[text()='USERNAME']";
    String UserDetailsEditBtn = "//div[@class='_du1frc list-group-item']//span[text()='USERNAME']/../following-sibling::div[@class='col-2']";
    By RemoveUserTxt = By.xpath("//span[contains(text(),'Remove user')]");
    By DeleteCnfrmOverlay = By.xpath("//h2[contains(text(),'Are you sure you want to remove this user')]");
    By DeleteCnfrmYesBtn = By.xpath("//button[contains(text(),'Yes')]");
    By txt_pkgDropdownItem = By.xpath("//div[contains(@class, 'cd_themed_select__option') and text()='Pkg']");
    By btn_hide = By.xpath("//button[contains(text(), 'Hide Item')]");
    By txt_editItem = By.xpath("//div[contains(text(), 'Edit Item')]");
    By btn_unhide = By.xpath("//button[contains(text(), 'Save and Unhide Item')]");
    By show_dropdown = By.xpath("//div[text()='Show:']//following-sibling::div//div[@class='cd_themed_select__control css-yk16xz-control']");
    By txt_activeAndHidden = By.xpath("//div[contains(@class, 'cd_themed_select__option') and text()='Active & Hidden Items']");
    String btn_editItem = "//div[contains(text(), 'NAME')]/ancestor::div[contains(@class, 'list-group-item')]//div[contains(@class, 'd-flex') and contains(@class, 'justify-content-end')]/*[local-name() = 'svg' and @data-icon='pencil']";
    By lbl_orders = By.xpath("//li[contains(text(),'Orders')]");
    By txt_allItems = By.xpath("//div[text()='All Items']");

    public boolean isPreviousDraftOrderNoDisplayed() throws InterruptedException {
        distributorUI.waitForElementEnabledState(btn_previousDraftOrderNo, true);
        distributorUI.waitForCustom(2000);
        return distributorUI.isDisplayed(btn_previousDraftOrderNo);
    }
    public void clickPreviousDraftOrderNo() throws InterruptedException {
        distributorUI.click(btn_previousDraftOrderNo);
    }
    public void clickOnSearchCustomers(){
        distributorUI.click(tbx_searchCustomers);
    }
    public void typeOnSearchCustomers(String code) throws InterruptedException {
        distributorUI.clear(tbx_searchCustomers);
        distributorUI.waitForCustom(1000);
        distributorUI.sendKeys(tbx_searchCustomers, code);
    }
    public boolean isCustomerSearchResultByCodeDisplayed(String code) throws InterruptedException {
        distributorUI.waitForElementEnabledState(By.xpath(btnOrderGuide.replace("CODE", code)), true);
        distributorUI.waitForCustom(4000);
        return distributorUI.isDisplayed(By.xpath(btnOrderGuide.replace("CODE", code)));
    }
    public void clickOnOrderGuide(String code) {
        distributorUI.click(By.xpath(btnOrderGuide.replace("CODE", code)));
    }
    public String getItemNameFirstRow() throws InterruptedException {
        distributorUI.waitForElementEnabledState(lbl_itemNameList,true);
        distributorUI.waitForCustom(3000);
        return distributorUI.getText(lbl_itemNameList);
    }
    public void clickPlusQryFirstRow(){
        distributorUI.click(btn_increaseQtyFirstRow);
    }
    public void clickMinusQryFirstRow(){
        distributorUI.click(btn_decreaseQtyFirstRow);
        try {
            distributorUI.waitForCustom(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void clickMinusQrySecondRow(){
        distributorUI.click(btn_decreaseQtySecondRow);
    }
    public String getItemNameSecondRow(){
       return distributorUI.getText(lbl_itemNameList,1);
    }
    public void clickPlusQrySecondRow(){
        distributorUI.click(btn_increaseQtySecondRow);
    }
    public void clickOnCheckoutButton() throws InterruptedException {
        distributorUI.waitForCustom(4000);
        distributorUI.waitForElementEnabledState(btn_checkout,true);
        distributorUI.click(btn_checkout);
        distributorUI.waitForCustom(4000);
    }
    public void clickOnCatalogButton(){
        distributorUI.waitForClickability(btn_catalog);
        distributorUI.click(btn_catalog);
    }
    public void typeToSearchOnCatalog(String item) throws InterruptedException {
        distributorUI.clear(tbx_catalogSearch);
        distributorUI.waitForCustom(2000);
        distributorUI.sendKeys(tbx_catalogSearch,item);
        distributorUI.waitForCustom(4000);
    }
    public String getFirstItemNameFrmSearchResults(String name){
        return distributorUI.getText(By.xpath(lbl_catalogSearchItemList.replace("NAME", name)), 0);
    }
    public void clickAddToCartCatalog() throws InterruptedException {
        distributorUI.waitForClickability(btn_addToCart);
        distributorUI.waitForCustom(4000);
        distributorUI.click(btn_addToCart);
        distributorUI.waitForCustom(4000);
        distributorUI.waitForElementEnabledState(btn_checkout,true);
    }
    public String getItemQtyFirstRow(){
        return distributorUI.getText(tbx_itemQuantityFirstRow, "value");
    }
    public Double getItemPriceFirstRow(){
        return Double.valueOf(distributorUI.getText(lbl_itemPriceFirstRow).replace("$",""));
    }
    public Double getItemPriceOnCheckoutButton() throws InterruptedException {
        distributorUI.waitForVisibility(btn_checkout);
        distributorUI.waitForCustom(4000);
        return Double.valueOf(distributorUI.getText(btn_checkout).replace("$",""));
    }
    public void clickPlusQryCatalogSearchValueOne(){
        distributorUI.click(btn_increaseQtyCatalogSearchValueOne);
        distributorUI.waitForClickability(btn_checkout);
    }
    public void clickPlusQryCatalogSearchValueTwo() throws InterruptedException {
        distributorUI.click(btn_increaseQtyCatalogSearchValueTwo);
        distributorUI.waitForCustom(2000);
        distributorUI.waitForClickability(btn_checkout);
        distributorUI.waitForCustom(4000);
    }
    public void clickMinusQryCatalogSearchValueOne(){
        distributorUI.click(btn_decreaseQtyCatalogSearchValueOne);
        distributorUI.waitForElementEnabledState(btn_checkout, false);
    }
    public void clickMinusQryCatalogSearchValueTwo(){
        distributorUI.click(btn_decreaseQtyCatalogSearchValueTwo);
        distributorUI.waitForClickability(btn_checkout);
    }
    public void clickMinusQryCatalogSearchValueThree(){
        distributorUI.click(btn_decreaseQtyCatalogSearchValueThree);
        distributorUI.waitForClickability(btn_checkout);
    }
    public String getItemQryCatalogSearch(){
       return distributorUI.getText(tbx_itemQuantityCatalogSearch, "value");
    }
    public Double getItemPriceCatalogSearch() {
        //driver.findElements(lbl_itemPriceSearchCatalogList).get(2).getText()
        return Double.parseDouble(distributorUI.getText(lbl_itemPriceSearchCatalogList,0).replace("$", ""));
    }
    public void clickMinusQryCartRowOne(){
        distributorUI.click(btn_decreaseQtyCartRowOne);
    }
    public void clickPlusQryCartRowOne(){
        distributorUI.click(btn_increaseQtyCartRowOne);
    }
    public Double getUnitPriceFirstRowCart(){
        return Double.valueOf(distributorUI.getText(lbl_itemPriceCartRowOne).split("\\$")[1]);
    }
    public Double getTotalPriceCart() throws InterruptedException {
        distributorUI.waitForCustom(3000);
        distributorUI.waitForVisibility(lbl_cartTotal);
        return Double.valueOf(distributorUI.getText(lbl_cartTotal).split("\\$")[1]);
    }
    public void submitOrder(){
        distributorUI.waitForClickability(btn_submitOrder);
        distributorUI.click(btn_submitOrder);
    }
    public boolean isDuplicatePopupDisplayed(){
        try {
            return distributorUI.isDisplayed(btn_duplicateOrderYes);
        } catch (Exception e){
            return false;
        }
    }
    public void clickYesDuplicatePopup(){
        distributorUI.waitForClickability(btn_duplicateOrderYes);
        distributorUI.click(btn_duplicateOrderYes);
        distributorUI.waitForInvisibility(btn_duplicateOrderYes);
    }
    public boolean isThankingForOrderPopupDisplayed(){
        try {
            distributorUI.waitForVisibility(lbl_thankYouForOrder);
            return distributorUI.isDisplayed(lbl_thankYouForOrder);
        } catch (Exception e){
            return false;
        }
    }
    public void clickOnPrint(){
        distributorUI.waitForClickability(btn_print);
        distributorUI.click(btn_print);
    }
    public boolean isPrintFriendlyPopupDisplayed(){
        return distributorUI.isDisplayed(lbl_printFriendlyOrderGuide);
    }
    public void clickOnDownloadOrderGuide(){
        distributorUI.waitForClickability(btn_downloadOrderGuide);
        distributorUI.click(btn_downloadOrderGuide);
    }
    public void typeToSearchOnOrderGuide(String item) throws InterruptedException {
        distributorUI.clear(tbx_orderGuideSearch);
        distributorUI.waitForCustom(2000);
        distributorUI.sendKeys(tbx_orderGuideSearch,item);
    }
    public void clickOnCreate() throws InterruptedException {
        distributorUI.waitForCustom(1000);
        distributorUI.waitForClickability(btn_create);
        distributorUI.click(btn_create);
    }
    public void typeOrderGuideName(String orderGuideName) throws InterruptedException {
        distributorUI.clear(tbx_orderGuideSearch);
        distributorUI.waitForCustom(2000);
        distributorUI.sendKeys(tbx_OrderGuideName,orderGuideName);
    }
    public void clickSubmitOrderGuide(){
        distributorUI.waitForClickability(btn_submitOrderGuide);
        distributorUI.click(btn_submitOrderGuide);
    }
    public void clickOnAddFromCatalog() throws InterruptedException {
        distributorUI.waitForVisibility(btn_addFromCatalog);
        distributorUI.click(btn_addFromCatalog);
        distributorUI.waitForCustom(4000);
    }
    public void clickUploadAList(){
        distributorUI.waitForVisibility(btn_uploadFile);
        distributorUI.click(btn_uploadFile);
    }
    public void clickOnAddToOrderGuide(){
        distributorUI.waitForVisibility(btn_addToOrderGuide);
        distributorUI.waitForClickability(btn_addToOrderGuide);
        distributorUI.click(btn_addToOrderGuide);
    }
    public void giveFilePath(String path){
        distributorUI.sendKeysToHiddenElements(upload_file, path);
    }
    public void clickOnCloseEditorCatalog(){
        distributorUI.waitForClickability(btn_closeEditorCatalog);
        distributorUI.click(btn_closeEditorCatalog);
    }
    public void clickOnRemoveFromOrderGuide(){
        distributorUI.waitForVisibility(btn_removeFromOrderGuide);
        distributorUI.click(btn_removeFromOrderGuide);
    }
    public boolean isBroadcastMessageDisplayed() {
        return distributorUI.isDisplayed(msg_banner);
    }
    public void clickMessage(){
        distributorUI.click(msg_banner);
    }
    public boolean isProductDetailsDisplayed(){
        return distributorUI.isDisplayed(lbl_productDetails);
    }
    public boolean isTopCategoryPicksDisplayed(){
        return distributorUI.isDisplayed(lbl_topCategoryPicks);
    }
    public boolean isItemInTopCategoryPicks(String code){
        return distributorUI.isDisplayed(By.xpath(lbl_itemAdded.replace("CODE", '#'+code)));
    }
    public void clickSearchedItem(String code){
        distributorUI.click((By.xpath(lbl_searchedItem.replace("CODE", '#'+code))));
    }
    public boolean isSelectedItemDisplayed(){
        return distributorUI.isDisplayed(lbl_productDetails);
    }
    public boolean isCompareSimilarItemsDisplayed(){
        return distributorUI.isDisplayed(section_compareSimilar);
    }
    public boolean isRecommendedForYouItemDisplayed(String code){
        return distributorUI.isDisplayed(By.xpath(lbl_recommendedForYouItem.replace("CODE", '#'+code)));
    }
    public boolean isRecommendedBySalesRepDisplayed(String code) {
        return distributorUI.isDisplayed(By.xpath(lbl_recommendedBySalesRep.replace("CODE", '#'+code)));
    }
    public boolean isDontForgetToOrderDisplayed(){
        return distributorUI.isDisplayed(section_dontForget);
    }
    public boolean isMoreFromThisBrandDisplayed(){
        return distributorUI.isDisplayed(section_moreFromThisBrand);
    }
    public void clickNext(){
        distributorUI.waitForClickability(btn_next);
        distributorUI.click(btn_next);
    }
    public void clickConfirm(){
        distributorUI.waitForClickability(btn_confirm);
        distributorUI.click(btn_confirm);
    }
    public void clickOK(){
        distributorUI.waitForClickability(btn_OK);
        distributorUI.click(btn_OK);
    }
    public void closeEditor(){
        distributorUI.waitForClickability(btn_closeEditor);
        // distributorUI.click(btn_closeEditor);
        distributorUI.clickUsingJavaScript(btn_closeEditor);
    }
    public boolean isOrderGuideCreateSuccessPopupDisplayed(){
        return distributorUI.isDisplayed(txt_orderGuideCreateSuccess);
    }
    public void clickCompanyDropdown(){
        distributorUI.waitForClickability(btn_companyDropdown);
        distributorUI.click(btn_companyDropdown);
    }
    public boolean isCompanyDropdownTextDisplayed(){
        return distributorUI.isDisplayed(txt_companyDropdownText);
    }
    public void clickOnEdit(){
        distributorUI.waitForClickability(btn_edit);
        distributorUI.click(btn_edit);
    }
    public boolean isEditOrderGuideTextDisplayed(){
        try {
            distributorUI.waitForVisibility(txt_editOrderGuide);
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(txt_editOrderGuide);
    }
    public void clickOnMoreOptions(){
        distributorUI.waitForClickability(btn_moreOptions);
        distributorUI.click(btn_moreOptions);
    }
    public void clickOnExportOrderGuide(){
        distributorUI.waitForClickability(btn_exportOrderGuide);
        distributorUI.click(btn_exportOrderGuide);
    }
    public void clickOnImportOrderGuide(){
        distributorUI.waitForClickability(btn_importOrderGuide);
        distributorUI.click(btn_importOrderGuide);
    }
    public void clickOnUploadToOrder(){
        distributorUI.waitForClickability(btn_uploadToOrder);
        distributorUI.click(btn_uploadToOrder);
    }
    public boolean isReviewOrderTextDisplayed(){
        distributorUI.waitForVisibility(txt_reviewOrder);
        return distributorUI.isDisplayed(txt_reviewOrder);
    }
    public boolean isOrderGuideUpdatedTextDisplayed(){
        distributorUI.waitForVisibility(txt_orderGuideUpdated);
        return distributorUI.isDisplayed(txt_orderGuideUpdated);
    }
    public void selectSortOptions(){
        distributorUI.waitForVisibility(dropdown_SortOptions);
        distributorUI.click(dropdown_SortOptions);
    }
    public void selectCustomOrder(){
        distributorUI.waitForVisibility(dropdown_customOrder);
        distributorUI.click(dropdown_customOrder);
    }
    public void selectLastOrdered(){
        distributorUI.waitForVisibility(dropdown_lastOrdered);
        distributorUI.click(dropdown_lastOrdered);
    }
    public void selectAlphabetical(){
        distributorUI.waitForVisibility(dropdown_alphabetical);
        distributorUI.click(dropdown_alphabetical);
    }
    public void selectItemCategories(){
        distributorUI.waitForVisibility(dropdown_itemCategories);
        distributorUI.click(dropdown_itemCategories);
    }
    public boolean isProduceTextDisplayed(){
        distributorUI.waitForVisibility(txt_produce);
        return distributorUI.isDisplayed(txt_produce);
    }
    public boolean isFirstAlphabeticalItemDisplayed(){
        distributorUI.waitForVisibility(txt_firstItem);
        return distributorUI.isDisplayed(txt_firstItem);
    }
    public boolean isFirstCustomItemDisplayed(){
        distributorUI.waitForVisibility(txt_firstItem);
        return distributorUI.isDisplayed(txt_firstItem);
    }
    public boolean isMinOrderBannerDisplayed(){
        distributorUI.waitForVisibility(txt_minOrderBanner);
        return distributorUI.isDisplayed(txt_minOrderBanner);
    }
    public boolean isOrderMinPopupDisplayed(){
        distributorUI.waitForVisibility(txt_popupAlertOrderMin);
        return distributorUI.isDisplayed(txt_popupAlertOrderMin);
    }
    public void clickOnCustomerCode(String code) {
        distributorUI.click(By.xpath(txt_customerCode.replace("CODE", code)));
    }
    public void clickOnOrdersTab() {
        distributorUI.click(tb_orders);
    }
    public boolean isStandingOrdersDisplayed(){
        distributorUI.waitForVisibility(txt_standingOrders);
        return distributorUI.isDisplayed(txt_standingOrders);
    }
    public void clickOnCreateStandingOrder() {
        distributorUI.click(btn_createStandingOrders);
    }
    public void clickOnDropdownDelivery() {
        distributorUI.click(dropdown_delivery);
    }
    public void clickOnDeliveryDate(String day) {
        distributorUI.waitForVisibility(By.xpath(txt_deliveryDay.replace("DAY", day)));
        distributorUI.click(By.xpath(txt_deliveryDay.replace("DAY", day)));
        distributorUI.waitForElementEnabledState(By.xpath(txt_deliveryDay.replace("DAY", day)),true);
    }
    public void setStandingOrder(){
        distributorUI.waitForClickability(btn_setStandingOrder);
        distributorUI.click(btn_setStandingOrder);
    }
    public boolean isStandingOrderEmailPopupDisplayed(){
        distributorUI.waitForVisibility(txt_EmailPopup);
        return distributorUI.isDisplayed(txt_EmailPopup);
    }
    public void clickOnDropdownEmail() {
        distributorUI.click(dropdown_email);
    }
    public void clickOnEmail() {
        distributorUI.waitForVisibility(txt_testEmail);
        distributorUI.click(txt_testEmail);
        distributorUI.click(txt_EmailPopup);
    }
    public void scheduleStandingOrder() {
        distributorUI.waitForClickability(btn_schedule);
        distributorUI.click(btn_schedule);
    }
    public boolean isStandingOrderSuccessPopupDisplayed(){
        distributorUI.waitForVisibility(txt_success);
        return distributorUI.isDisplayed(txt_success);
    }
    public void clickOnEditStandingOrder() {
        distributorUI.click(btn_editStandingOrders);
    }
    public void clickOnRemoveDelivery() {
        if (distributorUI.isDisplayed(btn_removeDelivery)){
            distributorUI.click(btn_removeDelivery);
        }
    }
    public void clickOnAddAnotherStandingOrder() {
        distributorUI.click(btn_addAnotherStandingOrder);
    }
    public void clickOnDeleteStandingOrders() {
        distributorUI.click(btn_deleteStandingOrders);
        distributorUI.waitForVisibility(txt_deletePopup);
        distributorUI.waitForClickability(btn_yes);
        distributorUI.click(btn_yes);
    }
    public boolean areStandingOrdersDeleted(){
        return distributorUI.isDisplayed(btn_deleteStandingOrders);
    }
    public void clickPlusQryFirstRowInDist() throws InterruptedException {
        distributorUI.waitForCustom(1000);
        distributorUI.click(btn_increaseQtyFirstRowInDist);
    }
    public void clickOnCheckoutButtonInDist() throws InterruptedException {
        distributorUI.waitForCustom(3000);
        distributorUI.waitForElementEnabledState(btn_checkoutCashCarry,true);
        distributorUI.click(btn_checkoutCashCarry);
        distributorUI.waitForCustom(3000);
    }
    public boolean isMultiDistCentersDisplayed() {
        try {
            distributorUI.waitForVisibility(txt_foodServiceDistCenter);
            distributorUI.isDisplayed(txt_foodServiceDistCenter);
            distributorUI.waitForVisibility(txt_retailDistCenter);
            distributorUI.isDisplayed(txt_retailDistCenter);
        } catch (Exception e){
            return false;
        }
        return true;
    }
    public int getOrderCount(int num) {
        int count = 0;
        for (int i = 1; i <= num; i++) {
            if (distributorUI.isDisplayed(By.xpath(txt_orders.replace("NUM", String.valueOf(i))))){
                count += 1;
            };
        }
        return count;
    }
    public void clickOnBack(){
        distributorUI.waitForClickability(btn_back);
        distributorUI.click(btn_back);
    }
    public void ClickOnCustomer(String code){
        distributorUI.click(By.xpath(SelectCustomerByCode.replace("CODE", code)));
    }
    public boolean isOrdersTabDisplayed(){
        distributorUI.waitForVisibility(OrdersTabTxt);
        return distributorUI.isDisplayed(OrdersTabTxt);
    }
    public boolean isOrderIdTxtDisplayed(){
        distributorUI.waitForVisibility(OrderIdTxt);
        return distributorUI.isDisplayed(OrderIdTxt);
    }
    public void ClickOrderDateToSort(){
        distributorUI.click(OrderDateSort);
    }
    public void ClickDeliveryDateSort(){
        distributorUI.click(DeliveryDate);
    }
    public boolean OrderDateSort(){
        return distributorUI.isDatesSorted(OrderDateSortData);
    }
    public boolean DeliveryDateSort(){
        return distributorUI.isDatesSorted(DeliveryDateSortData);
    }
    public boolean isDiscountDisclaimerOrderReviewMsgDisplayed(){
        distributorUI.waitForVisibility(txt_discountDisclaimerOrderReview);
        return distributorUI.isDisplayed(txt_discountDisclaimerOrderReview);
    }
    public boolean isDiscountDisclaimerOrderDetailsMsgDisplayed(){
        distributorUI.waitForVisibility(txt_discountDisclaimerOrderDetails);
        return distributorUI.isDisplayed(txt_discountDisclaimerOrderDetails);
    }
    public void clickFirstOrderFrmOrderTab(){
        distributorUI.waitForClickability(txt_discountDisclaimerOrderDetails);
        distributorUI.click(lbl_firstRowOrderTab);
    }
    public void clickSouthwestTraders(){
        distributorUI.waitForClickability(txt_southwest);
        distributorUI.click(txt_southwest);
    }
    public boolean isSubstitutesPopupDisplayed(){
        distributorUI.waitForVisibility(txt_substitutions);
        return distributorUI.isDisplayed(txt_substitutions);
    }
    public void clickSaveSelection(){
        distributorUI.waitForVisibility(btn_saveSelection);
        distributorUI.click(btn_saveSelection);
    }
    public void clickDoNotSubstitute(){
        distributorUI.waitForVisibility(btn_donotsubs);
        distributorUI.click(btn_donotsubs);
    }
    public boolean isReplacementDisplayed(){
        distributorUI.waitForVisibility(txt_replacement);
        return distributorUI.isDisplayed(txt_replacement);
    }
    public void clickOnItem(String code){
        distributorUI.click(By.xpath(txt_item.replace("CODE", code)));
    }
    public void clickPlusQryFirstRowInCheckout(){
        distributorUI.click(btn_increaseQtyFirstRowInCheckout);
    }
    public void clickMinusQryFirstRowInCheckout(){
        distributorUI.click(btn_decreaseQtyFirstRowInCheckout);
    }
    public int getSubstituteItemsCount(int num) {
        int count = 0;
        for (int i = 1; i <= num; i++) {
            if (distributorUI.isDisplayed(By.xpath(txt_subItems.replace("NUM", String.valueOf(i))))){
                count += 1;
            };
        }
        return count;
    }
    public boolean isCutomerTxtDisplayed(){
        return distributorUI.isDisplayed(CustomerTxt);
    }
    public void ClickTestAutomationOrderGuide(){
        distributorUI.waitForVisibility(Test_AutomationOrderGuide);
        distributorUI.click(Test_AutomationOrderGuide);
        distributorUI.click(AutomationGuide);
    }
    public boolean StockCountDisplayed(){
        return distributorUI.isDisplayed(StockCountTxt);
    }
    public boolean isCustomerGroupTxtDisplayed(){
        distributorUI.waitForVisibility(CustomerGroupTxt);
        return distributorUI.isDisplayed(CustomerGroupTxt);
    }
    public boolean areOutOfStockItemsDisplayed(){
        try {
            distributorUI.isDisplayed(txt_outOfStock);
        }
        catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(txt_outOfStock);
    }
    public void clickOnUnitEach(){
        distributorUI.click(txt_unitInDist);
        distributorUI.waitForVisibility(txt_eachDropdownItem);
        distributorUI.click(txt_eachDropdownItem);
    }
    public void clickOnUnitCase(){
        distributorUI.click(txt_unitInDist);
        distributorUI.waitForVisibility(txt_caseDropdownItem);
        distributorUI.click(txt_caseDropdownItem);
    }
    public void clickOnOrderGuideInProf(){
        distributorUI.waitForVisibility(btn_orderGuide);
        distributorUI.click(btn_orderGuide);
    }
    public String getUnitType(){
        distributorUI.waitForVisibility(txt_unitInDist);
        return distributorUI.getText(txt_unitInDist);
    }
    public void clickClose(){
        distributorUI.waitForVisibility(btn_close);
        distributorUI.click(btn_close);
        distributorUI.waitForInvisibility(btn_close);
    }
    public void clickOnDeleteItem(){
        distributorUI.waitForVisibility(btn_deleteIcon);
        distributorUI.click(btn_deleteIcon);
        distributorUI.waitForInvisibility(btn_deleteIcon);
    }
    public void clickOnBoostTab() {
        distributorUI.click(tb_boost);
    }
    public boolean isBroadcastTextDisplayed(){
        return distributorUI.isDisplayed(txt_customerSpecific);
    }
    public void clickOnEditMessage(){
        distributorUI.waitForVisibility(btn_editMessage);
        distributorUI.click(btn_editMessage);
    }
    public void clickOnClearMessage() throws InterruptedException {
        distributorUI.waitForVisibility(btn_clearMessage);
        distributorUI.click(btn_clearMessage);
        distributorUI.waitForCustom(1000);
    }
    public void clickOnAddItems() {
        distributorUI.waitForVisibility(btn_addItems);
        distributorUI.click(btn_addItems);
    }
    public void clickOnSaveMessage() throws InterruptedException {
        distributorUI.waitForVisibility(btn_saveMessage);
        distributorUI.click(btn_saveMessage);
        distributorUI.waitForCustom(1000);
    }
    public void typeBroadcastMessage(String msg) {
        distributorUI.click(txtArea);
        distributorUI.sendKeys(txtArea, msg);
    }
    public void selectItem(String code) {
        distributorUI.click(input_selectItem);
        distributorUI.sendKeys(input_selectItem, code);
        distributorUI.hoverOverElement(By.xpath(txt_item.replace("CODE", code+':')));
        distributorUI.click(By.xpath(txt_item.replace("CODE", code+':')));
    }
    public void clickOnAdd() {
        distributorUI.waitForVisibility(btn_add);
        distributorUI.click(btn_add);
    }
    public boolean isItemAdded(String code){
        return distributorUI.isDisplayed(By.xpath(txt_item.replace("CODE", code)));
    }
    public void clickOnRemoveItem() {
        distributorUI.waitForVisibility(btn_removeItem);
        distributorUI.click(btn_removeItem);
        distributorUI.waitForInvisibility(btn_removeItem);
    }
    public void addSection(){
        distributorUI.waitForClickability(btn_addSection);
        distributorUI.click(btn_addSection);
    }
    public boolean isAddSectionPopupDisplayed(){
        distributorUI.waitForVisibility(txt_addSection);
        return distributorUI.isDisplayed(txt_addSection);
    }
    public void clickOnSave(){
        distributorUI.waitForVisibility(btn_save);
        distributorUI.click(btn_save);
    }
    public void typeSectionName(String name) throws InterruptedException {
        distributorUI.click(tbx_section);
        distributorUI.waitForCustom(1000);
        distributorUI.sendKeys(tbx_section, name);
    }
    public boolean isAddedSectionDisplayed(String name) throws InterruptedException {
        distributorUI.refreshPage();
        distributorUI.waitForCustom(1000);
        distributorUI.waitForVisibility(By.xpath(txt_addedSection.replace("NAME", name)));
        return distributorUI.isDisplayed(By.xpath(txt_addedSection.replace("NAME", name)));
    }
    public void dragToTop(){
        String id_first = distributorUI.getText(txt_firstID,"data-rbd-draggable-id");
        String id_last = distributorUI.getText(txt_lastID,"data-rbd-draggable-id");
        distributorUI.dragAndDrop(By.xpath(txt_source.replace("ID", id_last)), By.xpath(txt_source.replace("ID", id_first)));
    }
    public boolean isSectionDisplayed(String name){
        distributorUI.waitForVisibility(By.xpath(txt_section.replace("NAME", name)));
        return distributorUI.isDisplayed(By.xpath(txt_section.replace("NAME", name)));
    }
    public void clickOnEditSection(String name){
        distributorUI.waitForVisibility(By.xpath(btn_editSection.replace("NAME", name)));
        distributorUI.click(By.xpath(btn_editSection.replace("NAME", name)));
    }
    public boolean isEditSectionPopupDisplayed(){
        distributorUI.waitForVisibility(txt_editSection);
        return distributorUI.isDisplayed(txt_editSection);
    }
    public void clickOnDelete(){
        distributorUI.waitForVisibility(btn_delete);
        distributorUI.click(btn_delete);
    }
    public void clickOnYes(){
        distributorUI.waitForVisibility(btn_yes);
        distributorUI.click(btn_yes);
    }
    public boolean isAreYouSurePopupDisplayed(){
        distributorUI.waitForVisibility(txt_areYouSure);
        return distributorUI.isDisplayed(txt_areYouSure);
    }

    public boolean isCustomerGroupEditBtnDisplayed(){
        return distributorUI.isDisplayed(EditCustomerGroupBtn);
    }
    public void editCustomerGroup(){
        try {
            distributorUI.waitForCustom(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        distributorUI.click(EditCustomerGroupBtn);
    }
    public void sendTextToCustomerGroup(String groupname){
        distributorUI.isDisplayed(CreateCutomerGroupTextField);
        distributorUI.sendKeysAndEnter(CreateCutomerGroupTextField,groupname);
    }
    public void clickCustomerGroupSaveBtn(){
        distributorUI.click(CustomerGroupSavebtn);
    }
    public boolean customerGroupNameDisplayed(String groupname){
        return distributorUI.isDisplayed(By.xpath(CustomerGroupName.replace("GROUPNAME",groupname)));
    }
    public void clickClearAllCustomerGroupsBtn(){
        distributorUI.isDisplayed(ClearAllCustomerGroupBtn);
        distributorUI.click(ClearAllCustomerGroupBtn);
    }
    public void clickInviteNewUsers(){
        distributorUI.click(InviteNewUsersBtn);
    }
    public boolean isAddUserOverlayDisplayed(){
        return distributorUI.isDisplayed(AddUserText);
    }
    public void sendTextToAddUserOverlayNameField(String Username){
        distributorUI.sendKeys(UserNameInputField,Username);
    }
    public void sendTextToAddUserOverlayEmailField(String Useremail){
        distributorUI.sendKeys(UserEmailInputField,Useremail);
    }
    public void clickSaveChangesWithoutSendingInvite(){
        distributorUI.waitForElementEnabledState(SaveChangeswithoutSendingInviteBtn,true);
        distributorUI.click(SaveChangeswithoutSendingInviteBtn);
    }
    public boolean isSuccessfullyUpdatedMsgDisplayed(){
        return distributorUI.isDisplayed(SuccessfulUpdatedMsg);
    }
    public boolean isSuccessfullyRemovedMsgDisplayed(){
        return distributorUI.isDisplayed(SuccessfulRemovedMsg);
    }
    public boolean isAddedUserDisplayed(String Username){
        return distributorUI.isDisplayed(By.xpath(UserName.replace("USERNAME",Username)));
    }
    public boolean isRemovedUserNotDisplayed(String Username){
        try {
            distributorUI.waitForCustom(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return distributorUI.isDisplayed(By.xpath(UserName.replace("USERNAME",Username)));
    }
    public void clickOnUserDetailsEditBtn(String Username){
        distributorUI.isDisplayed(By.xpath(UserDetailsEditBtn.replace("USERNAME",Username)));
        distributorUI.click(By.xpath(UserDetailsEditBtn.replace("USERNAME",Username)));
    }
    public void clickOnRemoveUser(){
        distributorUI.waitForVisibility(RemoveUserTxt);
        distributorUI.click(RemoveUserTxt);
    }
    public boolean isRemovalConfirmationOverlayDisplayed(){
        return distributorUI.isDisplayed(DeleteCnfrmOverlay);
    }
    public void ClickYesOnRemovalConfirmationOverlay(){
        distributorUI.click(DeleteCnfrmYesBtn);
    }
    public void clickOnUnitPkg(){
        distributorUI.click(txt_unitInDist);
        distributorUI.waitForVisibility(txt_pkgDropdownItem);
        distributorUI.click(txt_pkgDropdownItem);
    }
    public void clickOnHideItem(){
        distributorUI.waitForVisibility(btn_hide);
        distributorUI.click(btn_hide);
    }
    public boolean isEditItemPopupDisplayed(){
        distributorUI.waitForVisibility(txt_editItem);
        return distributorUI.isDisplayed(txt_editItem);
    }
    public void clickOnSaveAndUnhide(){
        distributorUI.waitForVisibility(btn_unhide);
        distributorUI.click(btn_unhide);
        distributorUI.waitForInvisibility(btn_unhide);
    }
    public void selectActiveAndHiddenItems(){
        distributorUI.waitForVisibility(show_dropdown);
        distributorUI.click(show_dropdown);
        distributorUI.click(txt_activeAndHidden);
    }
    public void clickOnEditItem(String name){
        distributorUI.waitForVisibility(By.xpath(btn_editItem.replace("NAME", name)));
        distributorUI.click(By.xpath(btn_editItem.replace("NAME", name)));
    }
    public boolean isOrdersTxtDisplayed(){
        return distributorUI.isDisplayed(lbl_orders);
    }
    public boolean isAllItemsTxtDisplayed(){
        return distributorUI.isDisplayed(txt_allItems);
    }
}
