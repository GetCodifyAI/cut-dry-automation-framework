package com.cutanddry.qa.pages;

import org.openqa.selenium.By;

public class CatalogPage extends LoginPage{
    By txt_catalog = By.xpath("//div[contains(text(),'Manage Catalog')]");
    By txt_catalogFirstItem = By.xpath("//tbody/tr[contains(@class, '_du1frc')][1]");
    By txt_editItem = By.xpath("//li[contains(text(),'Edit Item')]");
    By btn_preview = By.xpath("//a[.//button[contains(text(), 'Preview')]]");
    By txt_preview = By.xpath("//a[contains(text(),'Preview')]");
    By btn_downloadPdf = By.xpath("(//div[translate(normalize-space(text()), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ', 'abcdefghijklmnopqrstuvwxyz')='exportpdp(pdf)'])[1]");
    By btn_dropdown = By.xpath("//button[@aria-haspopup='true']");
    By ItemCatalogSearchBtn = By.xpath("//input[@placeholder=\"Find Item in Catalog\"]");
    String SearchedItemItemCode = "//tr[contains(@class, '_du1frc')][td[1]='ITEMCODE']";
    By PreviewBtn = By.xpath("//button[@class='_xrol5g mx-2 btn btn-primary']");
    String ItemPreviewTxt = "//div[@class='mt-1 _5h4pkd' and contains(text(),'ITEMCODE')]";
    By Manufacturer = By.xpath("//div[contains(text(),'Conagra Foodservice')]");
    By OtherBrandBtn = By.xpath("//img[@class='_kfc3ia img-fluid' and contains(@src,\"2b4b2013cb03bd26957893f39d0783bd.jpg\")]");
//    By ConagaraBrandPage= By.xpath("//div[contains(text(),'Conagra Foodservice ') and @class='mt-5 mb-1 _mojmdw']");
By ConagaraBrandPage= By.xpath("(//div[contains(text(),'Conagra Foodservice ')])[1]");
    By OtherBrandText = By.xpath("//h2[contains(text(),'Andy Capp’s®')]");
    By ShowCaseBtn = By.xpath("//*[contains(text(),'Showcase')]");
//    By ShowCaseBtn = By.xpath("//a[contains(@data-tip,'Cut+Dry Product Showcase')]");
    By btn_previewCat = By.xpath("//button[contains(text(), 'Switch to Grid View')]");
    By txt_previewCat = By.xpath("//div[text()='Catalog Preview']");
    By txt_firstItemDetails = By.xpath("//tbody/tr[1]");
    String itemInTheGrid = "//tr[contains(@class,'_du1frc')]//td[text()='ITEMCODE']";
    By ItemCodeInCatalogData = By.xpath("//div[contains(@class, 'form-group') and contains(.//label, 'Item Code')]//div[contains(@class, 'col-sm-8')]");
    By saveChangesBtn = By.xpath("//button[text()='Save']");
    By successOverlay = By.xpath("//div[contains(text(),'successfully saved!')]");
    By additionalAttributesTab = By.xpath("//a[contains(@class,'nav-item nav-link') and contains(text(),'Additional Attributes')]");
    By imagesTab = By.xpath("//a[contains(@class,'nav-item nav-link') and contains(text(),'Images')]");
    By certificationAttribute = By.xpath("//div[contains(text(),'Certifications')]");
    By productItemImage = By.xpath("//div[contains(@class,'justify-content-center')]/img[contains(@class,'img-fluid') and (contains(@src,'.jpg') or contains(@src,'.png'))]");
    By priceAndPromotions = By.xpath("//a[contains(@class,'nav-item nav-link') and contains(text(),'Pricing & Promotions')]");
    By unitOfMeasure = By.xpath("//button[contains(text(),'+ Unit of measure')]");
    By uomCount = By.xpath("//table[@class='pt-4 w-auto table table-borderless']//td[contains(@class, 'pl-0')]//label");
    By uomSelectDropdown = By.xpath("//div[contains(text(),'Select UoM')]");
    String unit = "//div[text()='UNIT']";
    By unitPriceTxtField = By.xpath("//div[contains(text(), 'Bag')]/ancestor::td[1]/following-sibling::td[1]//input[@type='number']");
    String unitPriceTxtFieldMultiUOM = "//div[contains(text(), 'UOM_TYPE')]/ancestor::td[1]/following-sibling::td[1]//input[@type='number']";
    By salesTypeDropDown =By.xpath("//tr[td//div[contains(text(), 'Bag')]]/td[3]//div[contains(@class, 'cd_themed_select__placeholder css-1wa3eu0-placeholder')]");
    String salesTypeDropDownMultiUOM = "//tr[td//div[contains(text(), 'UOM_TYPE')]]/td[3]//div[contains(@class, 'cd_themed_select__placeholder css-1wa3eu0-placeholder')]";
    By percentageOption = By.xpath("//div[contains(text(),'Percentage')]");
    By dollarValueOption = By.xpath("//div[contains(text(),'Dollar Value')]");
    By salesValue = By.xpath("//tr[td//div[contains(text(), 'Bag')]]/td[4]//input[@class='_sf843y form-control']");
    String salesValueMultiUOM = "//tr[td//div[contains(text(), 'UOM_TYPE')]]/td[4]//input[@class='_sf843y form-control']";
    By uomDeleteBtn = By.xpath("//tr[td/label[contains(text(),'Bag')]]//td[@class='pr-0 pb-0']");
    By uomDeleteOverlay = By.xpath("//h2[contains(text(),'Delete unit of measure')]");
    By confirmBtn = By.xpath("//button[contains(text(),'Confirm')]");
    By bagUOM = By.xpath("//label[text()='Bag']");
    String existUOM = "//label[text()='UOM']";
    By substituteTab = By.xpath("//a[contains(text(),'Substitutes')]");
    By addSubstitutionsBtn = By.xpath("//button[contains(text(),'+ Add Substitution')]");
    By selectSubstituteTxtField = By.xpath("//div[@class= ' css-1wa3eu0-placeholder' and text()='Select...']");
    By substituteItemInputField = By.xpath("//div[@class=' css-1wa3eu0-placeholder' and text()='Select...']/following::input[@type='text' and @aria-autocomplete='list']");
    String selectItemFromDropdown = "(//div[contains(text(),'ITEMCODE')])[last()]";
    By substituteAddBtn = By.xpath("//button[contains(text(),'Add')]");
    By substituteCancelBtn = By.xpath("//button[contains(text(),'Cancel')]");
    String substituteItemNameTxt = "//div[contains(text(),\"ITEMNAME\")]";
    String deleteSubstituteItemBtn = "//div[@class='align-items-center my-1 row']//div[contains(text(),'ITEMCODE')]//following-sibling::div[contains(@class,'col-md')]/*";
    By searchField = By.xpath("//div//input[contains(@placeholder,'Find Item in Catalog')]");
    String clearCertificationBtn = "//label[contains(text(),'CERTIFICATIONTYPE')]/..//div[contains(@class,'themed_select__clear-indicato')]";
    String selectCertificationDropdown = "//label[contains(text(),'CERTIFICATIONTYPE')]/..//div[contains(text(),'Select')]";
    String buyAmericanOption  = "//div[contains(text(),'CERTIFICATEOPTION')]";
    By productStatusDropdown = By.xpath("//label[contains(text(), 'Product Status')]/../following-sibling::div//div[contains(@class, 'themed_select__value-container')]");
    String productStatus = "(//div[contains(text(),'PRODSTATUS') and contains(@class,'themed_select__option')])[last()]";
    By searchInCatalogPreview = By.xpath("//div//input[contains(@placeholder,'Search catalog')]");
    String clickOnItemInPreviewCatalog = "//div[contains(@class, ' _du1frc')]//div[contains(@class, '_1evg3oy') and contains(., 'ITEMCODE')]";
    String itemCodeDetails = "//div[contains(text(),'ITEMCODE')]";
    By threeDotBtn = By.xpath("//button//*[contains(@data-icon,'cdDotVertical')]");
    By copyPDPURLTxt = By.xpath("//div[contains(text(),'Copy PDP (url)')]");
    By productLink = By.xpath("//h2[contains(text(),'Product Link')]");
    By publicCatalogAddToCart = By.xpath("//button[contains(text(),'Add to Cart')]");
    By alreadyACustomer = By.xpath("//b[contains(text(),'Already a Customer?')]");
    By lbl_link = By.xpath("//*[local-name() = 'svg' and @data-icon='link']");
    By txt_categoryLink = By.xpath("//h2[contains(text(),'Category Link')]");
    By btn_Ok = By.xpath("//button[contains(text(), 'OK')]");
    By txt_browseCatalog= By.xpath("//div[contains(text(),'Browse Catalog')]");
    By btn_manageCatalog = By.xpath("//button[span[contains(text(), 'Manage Catalog')]]");
    By lbl_createNewItem = By.xpath("//a[text()='Create new item']");
    By lbl_itemName = By.xpath("//input[@placeholder='Enter item name...']");
    By lbl_itemPrice = By.xpath("//input[@placeholder='0.00']");
    By btn_continue = By.xpath("//button[text()='Continue']");
    By txt_itemCreated = By.xpath("//h2[contains(text(),'Item created.')]");
    By btn_close = By.xpath("//button[text()='Close']");
    By txt_getItemCode = By.xpath("//div[contains(text(), 'A new item was successfully created with the item code')]");
    By btn_review = By.xpath("//a[text()='Review']");
    By txt_numRecentAdded = By.xpath("//div[p[text()='Recently Added']]//h6");
    By txt_resultsCount = By.xpath("//div[@class='col-sm-4']//span[contains(text(), 'results')]");
    By btn_updateImages = By.xpath("//a[text()='Update Images']");
    By txt_numImageMissing= By.xpath("//div[p[text()='Products Images Missing']]//h6");
    By lbl_categoriesDropdown = By.xpath("//div[@class='themed_select__single-value css-1uccc91-singleValue' and text()='All Categories']");
    By lbl_snack = By.xpath("//div[@class='themed_select__option css-yt9ioa-option' and text()='Snack']");
    By lbl_nonSnack = By.xpath("//table[contains(@class, 'table-hover')]//tbody//tr/td[4][not(normalize-space()='Snack')]");
    By lbl_statusDropdown = By.xpath("//div[@class='themed_select__single-value css-1uccc91-singleValue' and text()='All']");
    By lbl_active = By.xpath("//div[@class='themed_select__option css-yt9ioa-option' and text()='Active']");
    By lbl_inActive = By.xpath("//div[@class='themed_select__option css-yt9ioa-option' and text()='Inactive']");
    By lbl_nonactive = By.xpath("//table[contains(@class, 'table-hover')]//tbody//tr/td[7][not(normalize-space()='Active')]");
    By btn_moreFilters = By.xpath("//button[contains(., 'More Filters')]");
    By txt_filterCatalog= By.xpath("//div[contains(text(),'Filter Catalog')]");
    By lbl_imageUploaded = By.xpath("//label[contains(text(), 'Image Uploaded')]/following-sibling::div//div[contains(@class, 'themed_select__control')]");
    By lbl_no = By.xpath("(//div[contains(text(), 'No')])[last()]");
    By lbl_yes = By.xpath("(//div[contains(text(), 'Yes')])[last()]");
    By btn_apply = By.xpath("//button[contains(text(), 'Apply')]");
    By lbl_noImage = By.xpath("//div[@class='_hm9gs6 text-center']/img[not(@src='https://d3stps52o2e9nv.cloudfront.net/consumer/placeholder-img-product-v2.svg')]");
//    By firstItem = By.xpath("(//div[contains(@class, 'card')]//div[@class='_3quvq7 _1vlidrf'])[1]");
    By firstItem = By.xpath("(//div[contains(@class, 'card')]//a[contains(text(),'Edit Item')]/../preceding-sibling::div)[1]");

    By txt_productOverview= By.xpath("//div[contains(text(),'Product Overview')]");
    By txt_productDescription= By.xpath("//div[contains(text(),'Description')]");
    By btn_editProduct = By.xpath("//a[text()='Edit Product']");
    By btn_firstEditItem = By.xpath("(//a[text()='Edit Item'])[1]");
    By btn_3dots = By.xpath("//button[contains(@class, 'dropdown-toggle') and @aria-haspopup=\"true\"]");
    By txt_copyPDP= By.xpath("//div[contains(text(),'Copy PDP (url)')]");
    By txt_exportPDP= By.xpath("//div[contains(text(),'Export PDP (pdf)')]");
    By txt_copyPDPPopup = By.xpath("//h2[contains(text(),'Product Link')]");
    By txt_productDetails= By.xpath("//span[contains(text(),'Product Details')]");
    By lbl_exportCatalog = By.xpath("//a[text()='Export catalog (csv)']");
    By lbl_exportPromoFile = By.xpath("//a[text()='Export Promotion File (csv)']");
    By showSubsRadioBtn = By.xpath("//input[@id='show-subs']");
    By btn_back = By.xpath("//button[text()='Back']");
    By proprietaryItemDropDown = By.xpath("//label[contains(text(), 'Proprietary Item')]/../following-sibling::div//div[contains(@class, 'themed_select__value-container')]");
    String proprietaryItemStatus = "(//div[contains(text(),'PROPRIETARYSTATUS') and contains(@class,'themed_select__option')])[last()]";
    By lbl_itemType = By.xpath("//label[contains(text(), 'Item Type')]/following-sibling::div//div[contains(@class, 'themed_select__control')]");
    By lbl_proprietaryItem = By.xpath("(//div[contains(text(), 'Proprietary Items')])[last()]");
    By categoryDropDown = By.xpath("//label[contains(text(), 'Category')]/../following-sibling::div//div[contains(@class, 'themed_select__value-container')]");
    String categoryOption = "(//div[contains(text(),'CATEGORY') and contains(@class,'themed_select__option')])[last()]";
    By lbl_meat = By.xpath("//div[@class='themed_select__option css-yt9ioa-option' and text()='Meat']");
    By subCategoryDropDown = By.xpath("//label[contains(text(), 'Subcategories')]/../following-sibling::div//div[contains(@class, 'themed_select__value-container')]");
    String subCategoryOption = "(//div[contains(text(),'SUBCATEGORY') and contains(@class,'themed_select__option')])[last()]";
    By lbl_subCategoriesDropdown = By.xpath("//label[contains(text(),'Sub-Category:')]/following-sibling::div//div[contains(@class,'themed_select__single-value css-1uccc91-singleValue') and contains(text(),'All')]");
    By lbl_pork = By.xpath("//div[@class='themed_select__option css-yt9ioa-option' and text()='Pork']");
    By storageMethodDropDown = By.xpath("//label[contains(text(), 'Storage Method')]/../following-sibling::div//div[contains(@class, 'themed_select__value-container')]");
    String storageMethodOption = "(//div[contains(text(),'STORAGEMETHOD') and contains(@class,'themed_select__option')])[last()]";
    String txt_storageMethod = "//tr//td//div[contains(text(),'STORAGEMETHOD')]";
    By txt_description = By.xpath("(//textarea[@class='form-control'])[1]");
    String newDescription = " //div[contains(text(),'DESCRIPTION')]";
    By onSaleRadioButton = By.xpath("//label[contains(text(),'On Sale')]/../following-sibling::div/div");
    By newArrivalRadioButton = By.xpath("//label[contains(text(),'New Arrival')]/../following-sibling::div/div");
    By onSaleDropDown = By.xpath("//label[contains(text(), 'On Sale')]/following-sibling::div//div[contains(@class, 'themed_select__control')]");
    By newArrivalDropDown = By.xpath("//label[contains(text(), 'New Arrivals')]/following-sibling::div//div[contains(@class, 'themed_select__control')]");
    String categoryOptionInCatalog = "//div[contains(text(),'Category')]/../../following-sibling::div//div[contains(text(),'CATEGORY')]";
    String categoryName = "(//div[ contains(text(),'CATEGORY')])[1]";
    By lbl_firstRowItemCode = By.xpath("(//tbody/tr[1]/td[1])[1]");
    By lbl_firstRowItemName = By.xpath("(//tbody/tr[1]/td[3])[1]");
    By mediaTypeDropDown = By.xpath("//label[contains(text(), 'Media Type')]/../following-sibling::div//div[contains(@class, 'themed_select__control')]");
    String mediaTypeOption = "(//div[text()='TYPE' and contains(@class,'themed_select__option')])[last()]";
    String addedSubstitutionsCode = "//div[contains(text(),'CODE')]";
    String deleteUom = "//label[text()='UOM']/../following-sibling::td//*[local-name() = 'svg' and @data-icon='trash-can']";

    //multi UOM
    String multiUomDropDownOG = "(//td[text()='CODE']/following-sibling::*//div/*[local-name()='svg'])[1]";
    By multiUomOption =By.xpath("//div[text()='Multiple Units']");
    String getOGPriceUOM ="(//td[text()='CODE']/ancestor::tr/td[7]/div/div/div/span)[UOM]";
    String btn_OGAddToCartPlusQuantity ="(//td[text()='CODE']/following-sibling::*//div/*[local-name()='svg' and @data-icon='plus'])[UOM]";
    String tbx_itemQuantityUOM = "(//td[text()='CODE']/following-sibling::*//div/input[@data-input ='quantityInput'])[UOM]";
    String btn_OGAddToCartMinusQuantity ="(//td[text()='CODE']/following-sibling::*//div/*[local-name()='svg' and @data-icon='minus'])[UOM]";
    String submittedOrder = "//td[contains(text(),'#') and text()='ID']";
    By getTotalOrderPrice = By.xpath("//div[contains(text(),'Total')]/../following-sibling::td");
    By getTotalOrderQuantity = By.xpath("//div[contains(text(),'Items')]/../following-sibling::td");
    String multiUomDropDown = "(//div[text()='NAME']/../../following-sibling::*//div/*[local-name()='svg'])[1]";
    String multiUomDropDownOption ="//div[text()='OPTION']";
    String getPriceUOM = "((//button[contains(@data-for,'add-to-order-guide')]/ancestor::div[2]/following-sibling::div)[1]/following-sibling::*//div//span[contains(text(),'$')])[UOM]";
    String btn_addToCartPlusQuantity = "((//button[contains(@data-for,'add-to-order-guide')]/ancestor::div[2]/following-sibling::div)[1]/following-sibling::*//*[name()='svg' and contains(@data-icon, 'plus')])[UOM]";
    String btn_addToCartMinusQuantity = "((//button[contains(@data-for,'add-to-order-guide')]/ancestor::div[2]/following-sibling::div)[1]/following-sibling::*//*[name()='svg' and contains(@data-icon, 'minus')])[UOM]";
    String btn_editQuantities = "//div[text()='NAME']/../../following-sibling::*//div//button[text()='Edit Quantities']";
    String addQtyDisplay = "//div[text()='NAME']/../../following-sibling::*//div//div[text()='QUANTITY']";
    String txt_catalogProduct = "//div[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), translate('NAME', 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'))]";
    By getTotalPriceReviewOrder = By.xpath("//td[contains(text(),'Total')]/following-sibling::td");
    By getTotalQuantityReviewOrder = By.xpath("//td[contains(text(),'Items')]/following-sibling::td");
    String btn_trash = "//td[text()='CODE']/following-sibling::*//div/*[local-name()='svg' and @data-icon='trash-can']";
    String standingOrder = "//div[text()=' (QUANTITY items for $PRICE)']";
    By orderGuideSearch = By.xpath("//input[@placeholder='Search order guide...']");
    String getUOMOGPrice ="(//td[text()='CODE']/ancestor::tr/td[last()-2]//span)[UOM]";
    By checkOutBtnOG = By.xpath("//button[@data-for='cartCheckoutButton' and contains(text(),'$')]");
    String multiUomDropDownCatalog = "(//div[text()='NAME']/../../following-sibling::*//div/*[local-name()='svg'])[2]";
    By getDeliveryFeesReviewOrder = By.xpath("//td[contains(text(),'Delivery Fee')]/following-sibling::td");
    By getTotalEndlessAislePriceReviewOrder = By.xpath("//td[contains(text(),'Endless Aisle Total')]/following-sibling::td");
    By getTotalEndlessAisleSubTotalPriceReviewOrder = By.xpath("//td[contains(text(),'Endless Aisle Subtotal')]/following-sibling::td");
    By getSubTotalOrderPrice = By.xpath("//div[contains(text(),'Subtotal')]/../following-sibling::td");
    String catalogAddToCart = "(//div[translate(normalize-space(text()), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz') = translate('NAME', 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz')]/following::div//button[contains(text(), 'Add to Cart')])[1]";
    By btn_deleteSubstitute = By.xpath("//div/*[local-name()='svg' and @data-icon='circle-xmark']");
    String multiUomDropDownCatalogStable = "(//div[text()='NAME']/../../following-sibling::*//div/*[local-name()='svg'])[last()]";

    By itemStatusDropdown = By.xpath("//label[contains(text(), 'Item Status')]/following-sibling::div//div[contains(@class, 'themed_select__value-container')]");
    String itemStatusOption = "(//div[contains(text(),'ITEMSTATUS') and contains(@class,'themed_select__option')])[last()]";
    String lastOrderedDate = "//div[text()='1 CS ordered on DATEPLACEHOLDER']";
    By purchaseHistory = By.xpath("//div[text()='Purchase History']");
    String lastOrderDatePDP = "(//div[text()='DATEPLACEHOLDER']/../following-sibling::td//div[.//div[text()='1 Pkg'] and .//div[text()='1 CS']])[1]";


    public boolean isCatalogTextDisplayed() {
        distributorUI.waitForVisibility(txt_catalog);
        return distributorUI.isDisplayed(txt_catalog);
    }
    public void clickFirstItem() {
        distributorUI.click(txt_catalogFirstItem);
    }
    public boolean isSelectedProductDisplayed() {
        return distributorUI.isDisplayed(txt_editItem);
    }
    public void clickPreview() throws InterruptedException {
        distributorUI.waitForCustom(3000);
        String url = distributorUI.getText(btn_preview,"href");
        distributorUI.navigateToURL(url);
    }
    public boolean isNavigatedToPreview() {
        return distributorUI.isDisplayed(txt_preview);
    }
    public void clickExportPdf() {
        distributorUI.click(btn_dropdown);
        distributorUI.waitForVisibility(btn_downloadPdf);
        distributorUI.click(btn_downloadPdf);
        distributorUI.waitForVisibility(btn_downloadPdf);
    }
    public void TypeSearchInCatalogSearch(String ItemName){
        try{
            distributorUI.waitForCustom(4000);
        }catch(InterruptedException e){
            throw new RuntimeException(e);
        }
        distributorUI.sendKeys(ItemCatalogSearchBtn,ItemName);
        distributorUI.waitForVisibility(By.xpath(SearchedItemItemCode.replace("ITEMCODE",ItemName)));
    }
    public void ClickOnItemCode(String ItemCode){
        distributorUI.click(By.xpath(SearchedItemItemCode.replace("ITEMCODE",ItemCode)));
    }
    public void ClickOnPreviewBtn(){
        distributorUI.SwitchToNewTab(PreviewBtn);
    }
    public boolean isNavigateToItemPreview(String itemCode){
        distributorUI.waitForVisibility(By.xpath(ItemPreviewTxt.replace("ITEMCODE",itemCode)));
        return distributorUI.isDisplayed(By.xpath(ItemPreviewTxt.replace("ITEMCODE",itemCode)));
    }
    public void ClickOnManufacture(){
        distributorUI.click(Manufacturer);
    }
    public boolean isNavigatedtoConagaraBrandPage() {
//        distributorUI.waitForVisibility(ConagaraBrandPage);
        return distributorUI.isDisplayed(ConagaraBrandPage);
    }
    public boolean isNavigatedtoOtherBrandPage() throws InterruptedException {
         distributorUI.SwitchToNewTab(OtherBrandBtn);
         distributorUI.waitForCustom(6000);
         distributorUI.waitForVisibility(OtherBrandText);
         return distributorUI.isDisplayed(OtherBrandText);
    }
    public void ClickOnShowCaseBtn(){
        distributorUI.click(ShowCaseBtn);
    }
    public void clickOnPreviewCatalog() {
        distributorUI.click(btn_previewCat);
    }
    public boolean isNavigatedToPreviewCatalog() {
        return distributorUI.isDisplayed(txt_previewCat);
    }
    public String getItemDetailsFirstRow() throws InterruptedException {
        distributorUI.waitForElementEnabledState(txt_firstItemDetails,true);
        distributorUI.waitForCustom(3000);
        distributorUI.waitForVisibility(txt_firstItemDetails);
        return distributorUI.getText(txt_firstItemDetails);
    }
    public void clickonItemOnCatalogPage(String itemCode){
        try {
            distributorUI.waitForCustom(7000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        distributorUI.waitForVisibility(By.xpath(itemInTheGrid.replace("ITEMCODE",itemCode)));
        distributorUI.click(By.xpath(itemInTheGrid.replace("ITEMCODE",itemCode)));
    }
    public String getItemCodeFromCatalogDataPage(){
        distributorUI.waitForVisibility(ItemCodeInCatalogData);
        return distributorUI.getText(ItemCodeInCatalogData);
    }
    public void clickOnInactiveOrInactive(String prodStatus){
        distributorUI.click(productStatusDropdown);
        distributorUI.waitForVisibility(By.xpath(productStatus.replace("PRODSTATUS",prodStatus)));
        distributorUI.click(By.xpath(productStatus.replace("PRODSTATUS",prodStatus)));
    }

    public void clickOnSaveChangesBtn() {
        try {
            distributorUI.waitForCustom(3000);
            distributorUI.waitForVisibility(saveChangesBtn);
            distributorUI.click(saveChangesBtn);
            distributorUI.waitForCustom(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    /*public boolean isSuccessOverlayDisplayed(){
        distributorUI.waitForVisibility(successOverlay);
        return distributorUI.isDisplayed(successOverlay);
    }*/
    public boolean isSuccessOverlayDisplayed(){
        distributorUI.waitForVisibility(successOverlay);
        boolean isDisplayed = distributorUI.isDisplayed(successOverlay);
        if (isDisplayed) {
            distributorUI.waitForInvisibility(successOverlay);
        }
        return isDisplayed;
    }
    public void clickOnAdditionalAttributesTab(){
        distributorUI.click(additionalAttributesTab);
        try {
            distributorUI.waitForCustom(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean isCertificationsSectionDisplayed() {
        return distributorUI.isDisplayed(certificationAttribute);
    }
    public void clickClearCertification(String CertificationType){
        distributorUI.click(By.xpath(clearCertificationBtn.replace("CERTIFICATIONTYPE",CertificationType)));
    }
    public void clickOnCertification(String CertificationType, String certificate){
        distributorUI.click(By.xpath(selectCertificationDropdown.replace("CERTIFICATIONTYPE",CertificationType)));
        distributorUI.click(By.xpath(buyAmericanOption.replace("CERTIFICATEOPTION",certificate)));
    }
    public void clickOnImagesTab(){
        distributorUI.click(imagesTab);
    }
    public boolean isProductImageDisplayed(){
        return distributorUI.isDisplayed(productItemImage);
    }
    public void clickOnPricingAndPromotionsTab(){
        distributorUI.click(priceAndPromotions);
    }
    public void clickOnUnitOfMeasure()throws InterruptedException{
        distributorUI.waitForVisibility(unitOfMeasure);
        distributorUI.waitForCustom(3000);
        distributorUI.click(unitOfMeasure);
    }
    public int getUnitOfMeasureCount(){
        try {
            distributorUI.waitForCustom(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return distributorUI.countElements(uomCount);
    }
    public void clickOnUnit(String uom){
        distributorUI.waitForVisibility(uomSelectDropdown);
        distributorUI.click(uomSelectDropdown);
        distributorUI.waitForVisibility(By.xpath(unit.replace("UNIT",uom)));
        distributorUI.click(By.xpath(unit.replace("UNIT",uom)));
    }
    public void typeUnitPrice(String unitPrice){
        distributorUI.sendKeys(unitPriceTxtField,unitPrice);
    }
    public void typeUnitPrice(String uom, String unitPrice){
        distributorUI.sendKeys(By.xpath(unitPriceTxtFieldMultiUOM.replace("UOM_TYPE",uom)),unitPrice);
    }
    public void clickOnSalesTypeDropDown(){
        distributorUI.click(salesTypeDropDown);
    }
    public void clickOnSalesTypeDropDown(String uom){
        distributorUI.click(By.xpath(salesTypeDropDownMultiUOM.replace("UOM_TYPE",uom)));
    }
    public void clickOnPercentageOption(){
        distributorUI.click(percentageOption);
    }
    public void clickOndollarValueOption(){
        distributorUI.click(dollarValueOption);
    }
    public void typeSaleValue(String saleValue){
        distributorUI.sendKeys(salesValue,saleValue);
    }
    public void typeSaleValue(String uom, String saleValue){
        distributorUI.sendKeys(By.xpath(salesValueMultiUOM.replace("UOM_TYPE",uom)),saleValue);
    }
    public void deleteUOMinCatalog() throws InterruptedException {
        distributorUI.waitForCustom(3000);
        distributorUI.click(uomDeleteBtn);
    }
    public void deleteUOMinCatalog(String uom) throws InterruptedException {
        distributorUI.waitForCustom(3000);
        distributorUI.click(By.xpath(deleteUom.replace("UOM",uom)));
    }
    public boolean isUOMDeleteOverlayDisplayed(){
        return distributorUI.isDisplayed(uomDeleteOverlay);
    }
    public void clickOnConfirmBtn(){
        distributorUI.click(confirmBtn);
    }
    public boolean isBagUOMDisplayed(){
        try {
            distributorUI.waitForCustom(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return distributorUI.isDisplayed(bagUOM);
    }
    public boolean isUOMDisplayed(String uom) throws InterruptedException {
        distributorUI.waitForCustom(2000);
        return distributorUI.isDisplayed(By.xpath(existUOM.replace("UOM",uom)));
    }
    public void clickOnSubstituteTab(){
        distributorUI.click(substituteTab);
    }
    public void clickAddSubstitutionBtn(){
        distributorUI.waitForVisibility(addSubstitutionsBtn);
        distributorUI.scrollToElement(addSubstitutionsBtn);
        distributorUI.click(addSubstitutionsBtn);
    }
    public void searchSubstituteItem(String substituteItem){
        distributorUI.click(selectSubstituteTxtField);
        distributorUI.sendKeysWaitAndSelectDropdownOptionByEnter(substituteItemInputField,substituteItem);
    }
    public void addSubstitutionsBtn(){
        distributorUI.click(substituteAddBtn);
    }
    public String getSubstituteItemName(String substituteItem){
        distributorUI.click(selectSubstituteTxtField);
        distributorUI.sendKeys(substituteItemInputField,substituteItem);
        distributorUI.waitForVisibility(By.xpath(selectItemFromDropdown.replace("ITEMCODE",substituteItem)));
        distributorUI.waitForClickability(By.xpath(selectItemFromDropdown.replace("ITEMCODE",substituteItem)));
        try {
            distributorUI.waitForCustom(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String ItemName = distributorUI.getText(By.xpath(selectItemFromDropdown.replace("ITEMCODE",substituteItem)));
        String cleanedItemName = ItemName.split(":")[1].split("\\(")[0].trim();
        distributorUI.click(substituteCancelBtn);
        distributorUI.click(substituteAddBtn);
        return cleanedItemName;
    }
    public boolean isSubstituteItemDisplayed(String substituteItem){
        try {
            distributorUI.waitForCustom(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return distributorUI.isDisplayed(By.xpath(substituteItemNameTxt.replace("ITEMNAME",substituteItem)));
    }

    public void clickOnShowSubstituteBtn(){
        if(!distributorUI.isCheckboxOrRadioBtnSelected(showSubsRadioBtn)){
            distributorUI.click(showSubsRadioBtn);
        }
    }

    public void clickOnDeleteSubstituteItemBtn(String itemCode){
        try {
            distributorUI.waitForCustom(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        distributorUI.click(By.xpath(deleteSubstituteItemBtn.replace("ITEMCODE",itemCode)));
    }
    public boolean isDeleteSubstituteItemDisplayed(String itemCode){
        return  distributorUI.isDisplayed(By.xpath(deleteSubstituteItemBtn.replace("ITEMCODE",itemCode)));
    }
    public void clickSearchItemInCatalog(String itemName){
        distributorUI.click(searchField);
        distributorUI.sendKeys(searchField,itemName);
    }
    public void searchItemInCatalogPreview(String itemCode){
        distributorUI.sendKeys(searchInCatalogPreview,itemCode);
    }
    public void clickItemOnCatalogPreview(String itemCode){
        distributorUI.click(By.xpath(clickOnItemInPreviewCatalog.replace("ITEMCODE",itemCode)));
    }
    public boolean isItemDetailsDisplayed(String itemCode){
        return distributorUI.isDisplayed(By.xpath(itemCodeDetails.replace("ITEMCODE",itemCode)));
    }
    public void clickCopyPDPUrl(){
        distributorUI.click(threeDotBtn);
        distributorUI.waitForVisibility(copyPDPURLTxt);
        distributorUI.click(copyPDPURLTxt);
    }
    public boolean isLinkCopiedTxtDisplayed(){
        return distributorUI.isDisplayed(productLink);
    }
    public void goToPublicCatalog(){
        distributorUI.OpenNewTabAndSwitchToIt();
        distributorUI.pasteUrlFromClipboard();
    }
    public boolean isPublicCatalogDisplayed(String itemCode){
        distributorUI.isDisplayed(publicCatalogAddToCart);
        return  distributorUI.isDisplayed(By.xpath(itemCodeDetails.replace("ITEMCODE",itemCode)));
    }
    public void clickOnAddToCart(){
        distributorUI.click(publicCatalogAddToCart);
    }
    public boolean isAlreadyACustomerDisplayed(){
       return  distributorUI.isDisplayed(alreadyACustomer);
    }
    public void clickOnGetLink(){
        distributorUI.waitForVisibility(lbl_link);
        distributorUI.click(lbl_link);
    }
    public boolean isCatalogLinkPopupDisplayed() {
        try {
            distributorUI.waitForVisibility(txt_categoryLink);
        } catch (Exception e) {
            return false;
        }
        return distributorUI.isDisplayed(txt_categoryLink);
    }
    public void clickOK(){
        distributorUI.waitForVisibility(btn_Ok);
        distributorUI.click(btn_Ok);
    }
    public void goToCopiedLink(){
        distributorUI.OpenNewTabAndSwitchToIt();
        distributorUI.pasteUrlFromClipboard();
    }
    public boolean isNavigatedToBrowseCatalog() {
        try {
            distributorUI.waitForCustom(10000);
            distributorUI.waitForVisibility(txt_browseCatalog);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return distributorUI.isDisplayed(txt_browseCatalog);
    }
    public void clickOnManageCatalog(){
        distributorUI.waitForVisibility(btn_manageCatalog);
        distributorUI.click(btn_manageCatalog);
    }
    public void selectCreateNewItem(){
        distributorUI.waitForVisibility(lbl_createNewItem);
        distributorUI.click(lbl_createNewItem);
    }
    public void enterItemName(String name){
        distributorUI.clear(lbl_itemName);
        distributorUI.sendKeys(lbl_itemName,name);
    }
    public void enterItemPrice(String price){
        distributorUI.clear(lbl_itemPrice);
        distributorUI.sendKeys(lbl_itemPrice,price);
    }
    public void clickOnContinue(){
        distributorUI.waitForClickability(btn_continue);
        distributorUI.click(btn_continue);
    }
    public boolean isItemCreatedPopupDisplayed(){
        return  distributorUI.isDisplayed(txt_itemCreated);
    }
    public void clickOnClose(){
        distributorUI.waitForClickability(btn_close);
        distributorUI.click(btn_close);
    }
    public String getItemCode() {
        String fullText = distributorUI.getText(txt_getItemCode);
        int startIndex = fullText.indexOf("item code \"") + "item code \"".length();
        int endIndex = fullText.indexOf("\"", startIndex);
        return fullText.substring(startIndex, endIndex);
    }
    public String getRecentlyAddedCount() {
        distributorUI.waitForVisibility(txt_numRecentAdded);
        return distributorUI.getText(txt_numRecentAdded);
    }
    public void clickOnReview(){
        distributorUI.click(btn_review);
    }
    public String getResultsCount() {
        distributorUI.waitForVisibility(txt_resultsCount);
        String resultsText = distributorUI.getText(txt_resultsCount);
        return resultsText.split(" ")[0];
    }
    public void clickOnUpdateImages(){
        distributorUI.click(btn_updateImages);
    }
    public String getMissingImagesCount() {
        distributorUI.waitForVisibility(txt_numImageMissing);
        return distributorUI.getText(txt_numImageMissing);
    }
    public void selectCategorySnack() throws InterruptedException {
        distributorUI.click(lbl_categoriesDropdown);
        distributorUI.click(lbl_snack);
        distributorUI.waitForCustom(2000);
    }
    public boolean areNotSnacksDisplayed(){
        return  distributorUI.isDisplayed(lbl_nonSnack);
    }
    public void selectStatusActive() throws InterruptedException {
        distributorUI.waitForCustom(2000);
        distributorUI.click(lbl_statusDropdown);
        distributorUI.click(lbl_active);
        distributorUI.waitForCustom(2000);
    }
    public void selectStatusInactive() throws InterruptedException {
        distributorUI.click(lbl_statusDropdown);
        distributorUI.click(lbl_inActive);
        distributorUI.waitForCustom(2000);
    }
    public boolean areNotActiveStatusesDisplayed(){
        return  distributorUI.isDisplayed(lbl_nonactive);
    }
    public boolean isFilterCatalogPopupDisplayed(){
        return  distributorUI.isDisplayed(txt_filterCatalog);
    }
    public void clickOnMoreFilters() {
        distributorUI.click(btn_moreFilters);
    }
    public void selectImageUploadedNo() throws InterruptedException {
        distributorUI.click(lbl_imageUploaded);
        distributorUI.hoverOverElement(lbl_no);
        distributorUI.clickUsingJavaScript(lbl_no);
        distributorUI.waitForCustom(1000);
        distributorUI.click(btn_apply);
        distributorUI.waitForCustom(1000);
    }
    public boolean areImagesDisplayed(){
        return distributorUI.isDisplayed(lbl_noImage);
    }
    public void selectFirstItem() {
        distributorUI.click(firstItem);
        try {
            distributorUI.waitForCustom(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean isProductDescriptionDisplayed(){
//        return  distributorUI.isDisplayed(txt_productOverview);
        return  distributorUI.isDisplayed(txt_productDescription);

    }
    public void clickOnEditProduct() {
        distributorUI.click(btn_editProduct);
        try {
            distributorUI.waitForCustom(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void selectFirstEditItem() {
        distributorUI.click(btn_firstEditItem);
    }
    public void selectCopyPDP() {
        distributorUI.click(btn_3dots);
        distributorUI.click(txt_copyPDP);
    }
    public void selectExportPDP() {
        distributorUI.click(btn_3dots);
        distributorUI.click(txt_exportPDP);
    }
    public boolean isPDPLinkCopiedPopupDisplayed(){
        return  distributorUI.isDisplayed(txt_copyPDPPopup);
    }
    public boolean isNavigatedToProductDetails(){
        return  distributorUI.isDisplayed(txt_productDetails);
    }
    public boolean isPDFExported(){
        return  distributorUI.isDisplayed(btn_3dots);
    }
    public void selectExportCatalog(){
        distributorUI.waitForVisibility(lbl_exportCatalog);
        distributorUI.click(lbl_exportCatalog);
    }
    public void selectExportPromoFiles(){
        distributorUI.waitForVisibility(lbl_exportPromoFile);
        distributorUI.click(lbl_exportPromoFile);
    }
    public void clickBack(){
        distributorUI.click(btn_back);
    }
    public void clickOnProprietaryItem(String proprietaryStatus){
        distributorUI.click(proprietaryItemDropDown);
        distributorUI.waitForVisibility(By.xpath(proprietaryItemStatus.replace("PROPRIETARYSTATUS",proprietaryStatus)));
        distributorUI.click(By.xpath(proprietaryItemStatus.replace("PROPRIETARYSTATUS",proprietaryStatus)));
    }
    public void selectProprietaryItem() throws InterruptedException {
        distributorUI.click(lbl_itemType);
        distributorUI.hoverOverElement(lbl_proprietaryItem);
        distributorUI.clickUsingJavaScript(lbl_proprietaryItem);
        distributorUI.waitForCustom(1000);
        distributorUI.click(btn_apply);
        distributorUI.waitForCustom(1000);
    }
    public void clickOnCategory(String category){
        distributorUI.click(categoryDropDown);
        distributorUI.waitForVisibility(By.xpath(categoryOption.replace("CATEGORY",category)));
        distributorUI.click(By.xpath(categoryOption.replace("CATEGORY",category)));
    }
    public void selectCategoryMeat() throws InterruptedException {
        distributorUI.click(lbl_categoriesDropdown);
        distributorUI.click(lbl_meat);
        distributorUI.waitForCustom(2000);
    }
    public void clickOnSubCategory(String subCategory){
        distributorUI.click(subCategoryDropDown);
        distributorUI.waitForVisibility(By.xpath(subCategoryOption.replace("SUBCATEGORY",subCategory)));
        distributorUI.click(By.xpath(subCategoryOption.replace("SUBCATEGORY",subCategory)));
    }
    public void selectSubCategoryPork() throws InterruptedException {
        distributorUI.click(lbl_subCategoriesDropdown);
        distributorUI.click(lbl_pork);
        distributorUI.waitForCustom(2000);
    }
    public void clickOnStorageMethod(String storageMethod) throws InterruptedException {
        distributorUI.waitForCustom(3000);
        distributorUI.click(storageMethodDropDown);
        distributorUI.waitForVisibility(By.xpath(storageMethodOption.replace("STORAGEMETHOD",storageMethod)));
        distributorUI.click(By.xpath(storageMethodOption.replace("STORAGEMETHOD",storageMethod)));
    }
    public boolean isStorageMethodDisplayed(String storageMethod){
        return distributorUI.isDisplayed(By.xpath(txt_storageMethod.replace("STORAGEMETHOD",storageMethod)));
    }
    public void typeNewDescription(String description) throws InterruptedException {
        distributorUI.click(txt_description);
        distributorUI.clear(txt_description);
        distributorUI.waitForCustom(1000);
        distributorUI.sendKeys(txt_description, description);
    }
    public boolean isNewDescriptionDisplayed(String description){
        return distributorUI.isDisplayed(By.xpath(newDescription.replace("DESCRIPTION",description)));
    }
    public void clickOnSale(){
        distributorUI.click(onSaleRadioButton);
    }
    public void clickNewArrival(){
        distributorUI.click(newArrivalRadioButton);
    }
    public void selectOnSaleYes() throws InterruptedException {
        distributorUI.click(onSaleDropDown);
        distributorUI.hoverOverElement(lbl_yes);
        distributorUI.clickUsingJavaScript(lbl_yes);
        distributorUI.waitForCustom(1000);
        distributorUI.click(btn_apply);
        distributorUI.waitForCustom(1000);
    }
    public void selectNewArrivalYes() throws InterruptedException {
        distributorUI.click(newArrivalDropDown);
        distributorUI.hoverOverElement(lbl_yes);
        distributorUI.clickUsingJavaScript(lbl_yes);
        distributorUI.waitForCustom(1000);
        distributorUI.click(btn_apply);
        distributorUI.waitForCustom(1000);
    }
    public void clickOnCategoryOption(String category){
        distributorUI.click(By.xpath(categoryOptionInCatalog.replace("CATEGORY",category)));
    }
    public boolean isCategoryFilteredItem(String category)throws InterruptedException{
       return distributorUI.isDisplayed(By.xpath(categoryName.replace("CATEGORY",category)));
    }

    public String getItemCodeFirstRowInCatalog() throws InterruptedException {
        distributorUI.waitForVisibility(lbl_firstRowItemCode);
        distributorUI.waitForCustom(3000);
        return distributorUI.getText(lbl_firstRowItemCode);
    }

    public String getItemNameFirstRowInCatalog() throws InterruptedException {
        distributorUI.waitForVisibility(lbl_firstRowItemName);
        distributorUI.waitForCustom(3000);
        return distributorUI.getText(lbl_firstRowItemName);
    }

    public String getFirstItemNameFrmSearchResults(String name){
        try {
            distributorUI.waitForCustom(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        distributorUI.waitForVisibility(By.xpath(substituteItemNameTxt.replace("ITEMNAME", name)));
        return distributorUI.getText(By.xpath(substituteItemNameTxt.replace("ITEMNAME", name)));
    }
    public void clickMediaType(String type){
        distributorUI.click(mediaTypeDropDown);
        distributorUI.waitForVisibility(By.xpath(mediaTypeOption.replace("TYPE",type)));
        distributorUI.click(By.xpath(mediaTypeOption.replace("TYPE",type)));
    }
    public boolean isSameUomDisplayed(String uom)throws InterruptedException{
        try {
            distributorUI.waitForVisibility(By.xpath(deleteUom.replace("UOM",uom)));
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(By.xpath(deleteUom.replace("UOM",uom)));
    }
    public void refreshPage(){
        distributorUI.refreshPage();
    }

    // -------------------------------- Multi UOM ---------------------------

    public void ClickOnMultiUomDropDownOG(String code)throws InterruptedException{
        distributorUI.waitForVisibility(By.xpath(multiUomDropDownOG.replace("CODE", code)));
        distributorUI.click(By.xpath(multiUomDropDownOG.replace("CODE", code)));
        distributorUI.click(multiUomOption);
        distributorUI.waitForCustom(3000);
    }
    public double getOGPriceUOM(String code ,String uom) throws InterruptedException {
        try {
            return extractPrice(By.xpath(getOGPriceUOM.replace("CODE", code).replace("UOM", uom)));
        } catch (Exception e) {
            System.out.println("Fallback to alternative price locator due to: " + e.getMessage());
            return extractPrice(By.xpath(getOGPriceUOM.replace("CODE", code).replace("UOM", uom)));
        }
    }
    private double extractPrice(By priceLocator) {
        distributorUI.waitForVisibility(priceLocator);
        String tagName = distributorUI.getElement(priceLocator).getTagName();
        String priceText;

        if (tagName.equals("input")) {
            priceText = distributorUI.getText(priceLocator, "value");
        } else {
            priceText = distributorUI.getText(priceLocator);
        }

        System.out.println("Extracted Price: " + priceText);
        return Double.valueOf(priceText.replace("$", "").replace("/cs", "").replace("/pkg", "").replace("/CS", "").replace("/HCS", "").trim());
    }
    public void clickOGAddToCartPlusIcon(String code,String uom)throws InterruptedException{
        distributorUI.waitForVisibility(By.xpath(btn_OGAddToCartPlusQuantity.replace("CODE", code).replace("UOM", uom)));
        distributorUI.click(By.xpath(btn_OGAddToCartPlusQuantity.replace("CODE", code).replace("UOM", uom)));
        distributorUI.waitForCustom(2000);
    }
    public String getItemUOMQuantity(String code,String uom){
        return distributorUI.getText(By.xpath(tbx_itemQuantityUOM.replace("CODE", code).replace("UOM", uom)), "value");
    }
    public void clickOGAddToCartMinusIcon(String code,String uom)throws InterruptedException{
        distributorUI.waitForVisibility(By.xpath(btn_OGAddToCartMinusQuantity.replace("CODE", code).replace("UOM", uom)));
        distributorUI.click(By.xpath(btn_OGAddToCartMinusQuantity.replace("CODE", code).replace("UOM", uom)));
        distributorUI.waitForCustom(2000);
    }
    public void clickSubmittedOrder(String id){
        distributorUI.click(By.xpath(submittedOrder.replace("ID", id)));
    }
    public double getTotalPriceInOrder() throws InterruptedException {
        try {
            return extractPrice(getTotalOrderPrice);
        } catch (Exception e) {
            System.out.println("Fallback to alternative price locator due to: " + e.getMessage());
            return extractPrice(getTotalOrderPrice);
        }
    }
    public String getTotalQuantityInOrder(){
        return distributorUI.getText(getTotalOrderQuantity);
    }
    public void ClickOnMultiUomDropDown(String name)throws InterruptedException{
        distributorUI.waitForVisibility(By.xpath(multiUomDropDown.replace("NAME", name)));
        distributorUI.click(By.xpath(multiUomDropDown.replace("NAME", name)));
    }
    public void ClickOnMultiUomDropDownOption(String option){
        distributorUI.waitForVisibility(By.xpath(multiUomDropDownOption.replace("OPTION", option)));
        distributorUI.click(By.xpath(multiUomDropDownOption.replace("OPTION", option)));
    }
    public double getPDPPriceUOM(String uom) throws InterruptedException {
        try {
            return extractPrice(By.xpath(getPriceUOM.replace("UOM", uom)));
        } catch (Exception e) {
            System.out.println("Fallback to alternative price locator due to: " + e.getMessage());
            return extractPrice(By.xpath(getPriceUOM.replace("UOM", uom)));
        }
    }
    public void clickAddToCartPlusIcon(String uom)throws InterruptedException{
        distributorUI.waitForVisibility(By.xpath(btn_addToCartPlusQuantity.replace("UOM", uom)));
        distributorUI.click(By.xpath(btn_addToCartPlusQuantity.replace("UOM", uom)));
        distributorUI.waitForCustom(3000);
    }
    public void clickAddToCartMinusIcon(String uom){
        distributorUI.waitForVisibility(By.xpath(btn_addToCartMinusQuantity.replace("UOM", uom)));
        distributorUI.click(By.xpath(btn_addToCartMinusQuantity.replace("UOM", uom)));
    }
    public boolean isEditQuantitiesButtonDisplayed(String name){
        try {
            distributorUI.waitForVisibility(By.xpath(btn_editQuantities.replace("NAME", name)));
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(By.xpath(btn_editQuantities.replace("NAME", name)));
    }
    public boolean isAddedQuantitiesDisplayed(String name ,String qty){
        try {
            distributorUI.waitForVisibility(By.xpath(addQtyDisplay.replace("NAME", name).replace("QUANTITY",qty)));
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(By.xpath(addQtyDisplay.replace("NAME", name).replace("QUANTITY",qty)));
    }
    public void clickOnCatalogProduct(String name){
        distributorUI.waitForVisibility(By.xpath(txt_catalogProduct.replace("NAME", name)));
        distributorUI.clickUsingJavaScript(By.xpath(txt_catalogProduct.replace("NAME", name)));
    }
    public double getTotalPriceInReviewOrder() throws InterruptedException {
        try {
            return extractPrice(getTotalPriceReviewOrder);
        } catch (Exception e) {
            System.out.println("Fallback to alternative price locator due to: " + e.getMessage());
            return extractPrice(getTotalPriceReviewOrder);
        }
    }
    public String getTotalQuantityInReviewOrder(){
        return distributorUI.getText(getTotalQuantityReviewOrder);
    }
    public void clickReviewOrderTrashIcon(String code)throws InterruptedException{
        distributorUI.waitForVisibility(By.xpath(btn_trash.replace("CODE", code)));
        distributorUI.click(By.xpath(btn_trash.replace("CODE", code)));
        distributorUI.waitForCustom(2000);
    }
    public boolean isSubmittedStandingOrderDisplayed(String quantity ,String price) {
        try {
            distributorUI.waitForVisibility(By.xpath(standingOrder.replace("QUANTITY", quantity).replace("PRICE", price)));
        } catch (Exception e) {
            return false;
        }
        return distributorUI.isDisplayed(By.xpath(standingOrder.replace("QUANTITY", quantity).replace("PRICE", price)));
    }
    public void searchOrderGuide(String item) throws InterruptedException {
        distributorUI.clear(orderGuideSearch);
        distributorUI.sendKeys(orderGuideSearch,item);
    }
    public double getUOMOGPrice(String code ,String uom) throws InterruptedException {
        try {
            return extractPrice(By.xpath(getUOMOGPrice.replace("CODE", code).replace("UOM", uom)));
        } catch (Exception e) {
            System.out.println("Fallback to alternative price locator due to: " + e.getMessage());
            return extractPrice(By.xpath(getUOMOGPrice.replace("CODE", code).replace("UOM", uom)));
        }
    }
    public Double getItemPriceOnCheckoutButtonOG() throws InterruptedException {
        distributorUI.waitForVisibility(checkOutBtnOG);
        distributorUI.waitForCustom(4000);
        return Double.valueOf(distributorUI.getText(checkOutBtnOG).replace("$",""));
    }
    public void ClickOnCatalogMultiUomDropDown(String name)throws InterruptedException{
        distributorUI.waitForVisibility(By.xpath(multiUomDropDownCatalog.replace("NAME", name)));
        distributorUI.click(By.xpath(multiUomDropDownCatalog.replace("NAME", name)));
    }
    public double getDeliveryFeesPriceInReviewOrder() throws InterruptedException {
        try {
            return extractPrice(getDeliveryFeesReviewOrder);
        } catch (Exception e) {
            System.out.println("Fallback to alternative price locator due to: " + e.getMessage());
            return extractPrice(getDeliveryFeesReviewOrder);
        }
    }
    public double getTotalEndlessAislePriceInReviewOrder() throws InterruptedException {
        try {
            return extractPrice(getTotalEndlessAislePriceReviewOrder);
        } catch (Exception e) {
            System.out.println("Fallback to alternative price locator due to: " + e.getMessage());
            return extractPrice(getTotalEndlessAislePriceReviewOrder);
        }
    }
    public double getTotalEndlessAisleSubTotalPriceInReviewOrder() throws InterruptedException {
        try {
            return extractPrice(getTotalEndlessAisleSubTotalPriceReviewOrder);
        } catch (Exception e) {
            System.out.println("Fallback to alternative price locator due to: " + e.getMessage());
            return extractPrice(getTotalEndlessAisleSubTotalPriceReviewOrder);
        }
    }
    public double getSubTotalPriceInOrder() throws InterruptedException {
        try {
            return extractPrice(getSubTotalOrderPrice);
        } catch (Exception e) {
            System.out.println("Fallback to alternative price locator due to: " + e.getMessage());
            return extractPrice(getSubTotalOrderPrice);
        }
    }
    public boolean isCatalogAddToCartButtonDisplayed(String name){
        return distributorUI.isDisplayed(By.xpath(catalogAddToCart.replace("NAME", name)));
    }
    public void deleteSubstitute(){
        distributorUI.click(btn_deleteSubstitute);
    }
    public void ClickOnCatalogMultiUomDropDownStable(String name)throws InterruptedException{
        distributorUI.waitForVisibility(By.xpath(multiUomDropDownCatalogStable.replace("NAME", name)));
        distributorUI.click(By.xpath(multiUomDropDownCatalogStable.replace("NAME", name)));
    }
    public void clickOnItemStatus(String itemStatus){
        distributorUI.click(itemStatusDropdown);
        distributorUI.waitForVisibility(By.xpath(itemStatusOption.replace("ITEMSTATUS",itemStatus)));
        distributorUI.click(By.xpath(itemStatusOption.replace("ITEMSTATUS",itemStatus)));
    }
    public boolean isLastOrderDateDisplayed(String date) throws InterruptedException {
        distributorUI.waitForCustom(2000);
        return distributorUI.isDisplayed(By.xpath(lastOrderedDate.replace("DATEPLACEHOLDER",date)));
    }
    public void clickPurchaseHistory(){
        distributorUI.scrollToElement(purchaseHistory);
        distributorUI.click(purchaseHistory);
    }
    public boolean isLastOrderDatePDPDisplayed(String date) throws InterruptedException {
        distributorUI.waitForCustom(2000);
        return distributorUI.isDisplayed(By.xpath(lastOrderDatePDP.replace("DATEPLACEHOLDER",date)));
    }

}

