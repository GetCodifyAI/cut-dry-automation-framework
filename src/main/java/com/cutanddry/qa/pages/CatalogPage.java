package com.cutanddry.qa.pages;

import org.openqa.selenium.By;

public class CatalogPage extends LoginPage{
    By txt_catalog = By.xpath("//li[contains(text(),'Catalog')]");
    By txt_catalogFirstItem = By.xpath("//tbody/tr[contains(@class, '_du1frc')][1]");
    By txt_editItem = By.xpath("//li[contains(text(),'Edit Item')]");
    By btn_preview = By.xpath("//a[.//button[contains(text(), 'Preview')]]");
    By txt_preview = By.xpath("//a[contains(text(),'Preview')]");
    By btn_downloadPdf = By.xpath("//div[text()='Export PDP (Pdf)']");
    By btn_dropdown = By.xpath("//button[@aria-haspopup='true']");
    By ItemCatalogSearchBtn = By.xpath("//input[@placeholder=\"Find Item in Catalog\"]");
    String SearchedItemItemCode = "//tr[contains(@class, '_du1frc')][td[1]='ITEMCODE']";
    By PreviewBtn = By.xpath("//button[@class='_xrol5g mx-2 btn btn-primary']");
    String ItemPreviewTxt = "//div[@class='mt-1 _5h4pkd' and contains(text(),'ITEMCODE')]";
    By Manufacturer = By.xpath("//div[contains(text(),'Conagra Foodservice')]");
    By OtherBrandBtn = By.xpath("//img[@class='_kfc3ia img-fluid' and contains(@src,\"2b4b2013cb03bd26957893f39d0783bd.jpg\")]");
    By ConagaraBrandPage= By.xpath("//div[contains(text(),'Conagra Foodservice ') and @class='mt-5 mb-1 _mojmdw']");
    By OtherBrandText = By.xpath("//h2[contains(text(),'Andy Capp’s®')]");
    By ShowCaseBtn = By.xpath("//a[contains(@data-tip,'Cut+Dry Product Showcase')]");
    By btn_previewCat = By.xpath("//button[contains(text(), 'Preview Catalog')]");
    By txt_previewCat = By.xpath("//div[text()='Catalog Preview']");
    By txt_firstItemDetails = By.xpath("//tbody/tr[1]");
    String itemInTheGrid = "//tr[contains(@class,'_du1frc')]//td[text()='ITEMCODE']";
    By ItemCodeInCatalogData = By.xpath("//div[contains(@class, 'form-group') and contains(.//label, 'Item Code')]//div[contains(@class, 'col-sm-8')]");
    By saveChangesBtn = By.xpath("//button[text()='Save']");
    By successOverlay = By.xpath("//div[contains(text(),'successfully saved!')]");
    By additionalAttributesTab = By.xpath("//a[contains(@class,'nav-item nav-link') and contains(text(),'Additional Attributes')]");
    By imagesTab = By.xpath("//a[contains(@class,'nav-item nav-link') and contains(text(),'Images')]");
    By certificationAttribute = By.xpath("//div[contains(text(),'Certifications')]");
    By productItemImage = By.xpath("//img[contains(@class,'_kzpva6 img-fluid') and contains(@src,'img01.jpg')]");
    By priceAndPromotions = By.xpath("//a[contains(@class,'nav-item nav-link') and contains(text(),'Pricing & Promotions')]");
    By unitOfMeasure = By.xpath("//button[contains(text(),'+ Unit of measure')]");
    By uomCount = By.xpath("//table[@class='pt-4 w-auto table table-borderless']//td[contains(@class, 'pl-0')]//label");
    By uomSelectDropdown = By.xpath("//div[contains(text(),'Select UoM')]");
    String unit = "//div[text()='UNIT']";
    By unitPriceTxtField = By.xpath("//div[contains(text(), 'Bag')]/ancestor::td[1]/following-sibling::td[1]//input[@type='number']");
    By salesTypeDropDown =By.xpath("//tr[td//div[contains(text(), 'Bag')]]/td[3]//div[contains(@class, 'cd_themed_select__placeholder css-1wa3eu0-placeholder')]");
    By percentageOption = By.xpath("//div[contains(text(),'Percentage')]");
    By salesValue = By.xpath("//tr[td//div[contains(text(), 'Bag')]]/td[4]//input[@class='_sf843y form-control']");
    By uomDeleteBtn = By.xpath("//tr[td/label[contains(text(),'Bag')]]//td[@class='pr-0 pb-0']");
    By uomDeleteOverlay = By.xpath("//h2[contains(text(),'Delete unit of measure')]");
    By confirmBtn = By.xpath("//button[contains(text(),'Confirm')]");
    By bagUOM = By.xpath("//label[text()='Bag']");
    By substituteTab = By.xpath("//a[contains(text(),'Substitutes')]");
    By addSubstitutionsBtn = By.xpath("//button[contains(text(),'+ Add Substitution')]");
    By selectSubstituteTxtField = By.xpath("//div[@class= ' css-1wa3eu0-placeholder' and text()='Select...']");
    By substituteItemInputField = By.xpath("//div[@class=' css-1wa3eu0-placeholder' and text()='Select...']/following::input[@type='text' and @aria-autocomplete='list']");
    String selectItemFromDropdown = "(//div[contains(text(),'ITEMCODE')])[last()]";
    By substituteAddBtn = By.xpath("//button[contains(text(),'Add')]");
    By substituteCancelBtn = By.xpath("//button[contains(text(),'Cancel')]");
    String substituteItemNameTxt = "//div[contains(text(),'ITEMNAME')]";
    String deleteSubstituteItemBtn = "//div[@class='align-items-center my-1 row']//div[contains(text(),'ITEMCODE')]//./following-sibling::*[3]";
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

    public boolean isCatalogTextDisplayed() {
        try {
            distributorUI.waitForVisibility(txt_catalog);
        } catch (Exception e) {
            return false;
        }
        return distributorUI.isDisplayed(txt_catalog);
    }
    public void clickFirstItem() {
        distributorUI.click(txt_catalogFirstItem);
    }
    public boolean isSelectedProductDisplayed() {
        return distributorUI.isDisplayed(txt_editItem);
    }
    public void clickPreview() {
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

    public boolean isNavigatedtoConagaraBrandPage(){
        distributorUI.waitForVisibility(ConagaraBrandPage);
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
        return distributorUI.getText(txt_firstItemDetails);
    }

    public void clickonItemOnCatalogPage(String itemCode){
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

    public void clickOnSaveChangesBtn(){
        try {
            distributorUI.waitForCustom(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        distributorUI.click(saveChangesBtn);
    }

    public boolean isSuccessOverlayDisplayed(){
        try {
            distributorUI.waitForCustom(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return distributorUI.isDisplayed(successOverlay);
    }

    public void clickOnAdditionalAttributesTab(){
        distributorUI.click(additionalAttributesTab);
    }

    public boolean isCertificationsSectionDisplayed(){
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

    public void clickOnUnitOfMeasure(){
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

    public void clickOnSalesTypeDropDown(){
        distributorUI.click(salesTypeDropDown);
    }

    public void clickOnPercentageOption(){
        distributorUI.click(percentageOption);
    }

    public void typeSaleValue(String saleValue){
        distributorUI.sendKeys(salesValue,saleValue);
    }

    public void deleteUOMinCatalog(){
        distributorUI.click(uomDeleteBtn);
    }

    public boolean isUOMDeleteOverlayDisplayed(){
        return distributorUI.isDisplayed(uomDeleteOverlay);
    }

    public void clickOnConfirmBtn(){
        distributorUI.click(confirmBtn);
    }

    public boolean isBagUOMDisplayed(){
        try {
            distributorUI.waitForCustom(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return distributorUI.isDisplayed(bagUOM);
    }

    public void clickOnSubstituteTab(){
        distributorUI.click(substituteTab);
    }

    public void clickAddSubstitutionBtn(){
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

    public void clickOnDeleteSubstituteItemBtn(String itemCode){
        try {
            distributorUI.waitForCustom(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        distributorUI.click(By.xpath(deleteSubstituteItemBtn.replace("ITEMCODE",itemCode)));
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

}

