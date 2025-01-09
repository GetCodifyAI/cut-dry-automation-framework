package com.cutanddry.qa.functions;

import com.cutanddry.qa.pages.CatalogPage;

public class Catalog {
    static CatalogPage catalogPage = new CatalogPage();

    public static boolean isUserNavigatedToCatalog(){
        return catalogPage.isCatalogTextDisplayed();
    }
    public static void selectFirstProduct() {
        catalogPage.clickFirstItem();
    }
    public static boolean isSelectedProductDisplayed() {
        return catalogPage.isSelectedProductDisplayed();
    }
    public static void clickOnPreview() {
        catalogPage.clickPreview();
    }
    public static boolean isNavigatedToPreview() {
        return catalogPage.isNavigatedToPreview();
    }
    public static void DownloadPDF() {
        catalogPage.clickExportPdf();
    }
    public static void SearchItemInCatalogByItemCode(String ItemCode){catalogPage.TypeSearchInCatalogSearch(ItemCode);}
    public static void SelectItemAfterSearch(String ItemCode){catalogPage.ClickOnItemCode(ItemCode);}
    public static void ClickOnPreview(){
        catalogPage.ClickOnPreviewBtn();
    }
    public static boolean isItemPreviewDisplayed(String itemCode){
        return catalogPage.isNavigateToItemPreview(itemCode);
    }
    public static void SelectManufacturer(){
        catalogPage.ClickOnManufacture();
    }

    public static boolean isCongaraBrandPageDisplayed(){
        return catalogPage.isNavigatedtoConagaraBrandPage();

    }
    public static boolean isOtherBrandsPageDisplayed() throws InterruptedException {
        return catalogPage.isNavigatedtoOtherBrandPage();
    }
    public static void NavigateToShowCasePage(){
        catalogPage.ClickOnShowCaseBtn();
    }
    public static void clickOnPreviewCatalog() {
        catalogPage.clickOnPreviewCatalog();
    }
    public static boolean isNavigatedToPreviewCatalog() {
        return catalogPage.isNavigatedToPreviewCatalog();
    }
    public static String getItemDetailsFirstRow() throws InterruptedException {
        return catalogPage.getItemDetailsFirstRow();
    }

    public static void selectItemFromGrid(String itemCode){
        catalogPage.clickonItemOnCatalogPage(itemCode);
    }

    public static String getItemcodeInCatalogData(){
        return catalogPage.getItemCodeFromCatalogDataPage();
    }

    public static void selectProductActiveInactiveStatus(String prodStatus){
        catalogPage.clickOnInactiveOrInactive(prodStatus);
    }

    public static void saveChanges(){
        catalogPage.clickOnSaveChangesBtn();
    }

    public static boolean successOverlayDisplayed(){
       return catalogPage.isSuccessOverlayDisplayed();
    }

    public static void navigateToAdditionalAttributes(){
        catalogPage.clickOnAdditionalAttributesTab();
    }

    public static boolean isAdditionalAttributesTabDisplayed(){
        return catalogPage.isCertificationsSectionDisplayed();
    }

    public static void clearCertification(String CertificationType){
        catalogPage.clickClearCertification(CertificationType);
    }

    public static void selectCertification(String CertificationType,String certification){
        catalogPage.clickOnCertification(CertificationType,certification);
    }

    public static void navigateToImages(){
        catalogPage.clickOnImagesTab();
    }

    public static boolean ProductImageDisplayed(){
        return catalogPage.isProductImageDisplayed();
    }

    public static void navigateToPricingAndPromotions(){
        catalogPage.clickOnPricingAndPromotionsTab();
    }

    public static void addUnitOfMeasure(){
        catalogPage.clickOnUnitOfMeasure();
    }

    public static int getUnitOfMeasureCount(){
        return catalogPage.getUnitOfMeasureCount();
    }

    public static void selectUnitFromDropdown(String uom){
        catalogPage.clickOnUnit(uom);
    }

    public static void setItemUnitPrice(String unitPrice){
        catalogPage.typeUnitPrice(unitPrice);
    }

    public static void selectPercentageAsSalesTypeFrmDropdown(){
        catalogPage.clickOnSalesTypeDropDown();
        catalogPage.clickOnPercentageOption();
    }

    public static void setSaleValue(String saleValue){
        catalogPage.typeSaleValue(saleValue);
    }

    public static void deleteUOMFromCatalog() throws InterruptedException {
        catalogPage.deleteUOMinCatalog();
    }

    public static boolean deleteUOMOverlayDisplayed(){
        return catalogPage.isUOMDeleteOverlayDisplayed();
    }

    public static void DeleteConfirm(){
        catalogPage.clickOnConfirmBtn();
    }

    public static boolean isAddedUOMDisplayed(){
        return catalogPage.isBagUOMDisplayed();
    }

    public static boolean isDeletedUOMDisplayed(){
        return catalogPage.isBagUOMDisplayed();
    }

    public static void navigateToSubstituteTab(){
        catalogPage.clickOnSubstituteTab();
    }

    public static void removeExistingItem(String ItemCode){
        if (catalogPage.isDeleteSubstituteItemDisplayed(ItemCode)) {
            catalogPage.clickOnDeleteSubstituteItemBtn(ItemCode);
            catalogPage.clickOnSaveChangesBtn();
        }

    }

    public static void addSubstitutions(){
        catalogPage.clickAddSubstitutionBtn();
    }

    public static void searchAndAddSubstituteItem(String ItemCode){
        catalogPage.searchSubstituteItem(ItemCode);
        catalogPage.addSubstitutionsBtn();
    }

    public static String getSubstituteItemName(String ItemCode){
        return catalogPage.getSubstituteItemName(ItemCode);
    }

    public static boolean isAddedSubstituteItemDisplayedInPage(String ItemCode){
        return catalogPage.isSubstituteItemDisplayed(ItemCode);
    }

    public static boolean isDeletedSubstituteItemDisplayedInPage(String ItemCode){
        return catalogPage.isSubstituteItemDisplayed(ItemCode);
    }

    public static void deleteSubstituteItem(String itemCode){
        catalogPage.clickOnDeleteSubstituteItemBtn(itemCode);
    }

    public static void searchItemInCatalog(String itemName){
        catalogPage.clickSearchItemInCatalog(itemName);
    }

    public static void checkAndAddNecessarySubstituteItems(String ItemName){
        if(!catalogPage.isSubstituteItemDisplayed(ItemName)){
            catalogPage.clickAddSubstitutionBtn();
            catalogPage.searchSubstituteItem(ItemName);
            catalogPage.addSubstitutionsBtn();
        }
    }

    public static void showSubstituteBtnIfNotSelected(){
        catalogPage.clickOnShowSubstituteBtn();
    }

    public static void searchItemInCatalogPreview(String itemCode){
        catalogPage.searchItemInCatalogPreview(itemCode);
    }

    public static void clickOnItemInCatalogPreview(String itemCode){
        catalogPage.clickItemOnCatalogPreview(itemCode);
    }

    public static boolean isItemDetailsDisplayedInPDP(String itemCode){
        return catalogPage.isItemDetailsDisplayed(itemCode);
    }

    public static void copyPDPUrl(){
        catalogPage.clickCopyPDPUrl();
    }

    public static boolean linkCopiedOverlayDisplayed(){
        return catalogPage.isLinkCopiedTxtDisplayed();
    }

    public static void navigateToPublicCatalog(){
        catalogPage.goToPublicCatalog();
    }

    public static boolean PublicCatalogDisplayed(String itemCode){
        return catalogPage.isPublicCatalogDisplayed(itemCode);
    }

    public static void clickOnAddToCart(){
        catalogPage.clickOnAddToCart();
    }

    public static boolean alreadyACustomerOrJoinOverlayDisplayed(){
        return catalogPage.isAlreadyACustomerDisplayed();
    }
    public static void clickOnGetLink(){
        catalogPage.clickOnGetLink();
    }
    public static void clickOK(){
        catalogPage.clickOK();
    }
    public static boolean isCatalogLinkPopupDisplayed(){
        return catalogPage.isCatalogLinkPopupDisplayed();
    }
    public static void goToCopiedLink(){
        catalogPage.goToCopiedLink();
    }
    public static boolean isNavigatedToBrowseCatalog(){
        return catalogPage.isNavigatedToBrowseCatalog();
    }
    public static void clickOnManageCatalog(){
        catalogPage.clickOnManageCatalog();
    }
    public static void selectCreateNewItem(){
        catalogPage.selectCreateNewItem();
    }
    public static void enterItemName(String name){
        catalogPage.enterItemName(name);
    }
    public static void enterItemPrice(String price){
        catalogPage.enterItemPrice(price);
    }
    public static void clickOnContinue(){
        catalogPage.clickOnContinue();
    }
    public static boolean isItemCreatedPopupDisplayed(){
        return catalogPage.isItemCreatedPopupDisplayed();
    }
    public static void clickOnClose(){
        catalogPage.clickOnClose();
    }
    public static String  getItemCode(){
        return catalogPage.getItemCode();
    }
    public static String getRecentlyAddedCount(){
        return catalogPage.getRecentlyAddedCount();
    }
    public static void clickOnReview(){
        catalogPage.clickOnReview();
    }
    public static String getResultsCount(){
        return catalogPage.getResultsCount();
    }
    public static String getMissingImagesCount(){
        return catalogPage.getMissingImagesCount();
    }
    public static void clickOnUpdateImages(){
        catalogPage.clickOnUpdateImages();
    }
    public static void selectCategorySnack() throws InterruptedException {
        catalogPage.selectCategorySnack();
    }
    public static boolean areNotSnacksDisplayed(){
        return catalogPage.areNotSnacksDisplayed();
    }
    public static void selectStatusActive() throws InterruptedException {
        catalogPage.selectStatusActive();
    }
    public static void selectStatusInactive() throws InterruptedException {
        catalogPage.selectStatusInactive();
    }
    public static boolean areNotActiveStatusesDisplayed(){
        return catalogPage.areNotActiveStatusesDisplayed();
    }
    public static void clickOnMoreFilters(){
        catalogPage.clickOnMoreFilters();
    }
    public static boolean isFilterCatalogPopupDisplayed(){
        return catalogPage.isFilterCatalogPopupDisplayed();
    }
    public static void selectImageUploadedNo() throws InterruptedException {
        catalogPage.selectImageUploadedNo();
    }
    public static boolean areImagesDisplayed(){
        return catalogPage.areImagesDisplayed();
    }
    public static void selectFirstItem(){
        catalogPage.selectFirstItem();
    }
    public static boolean isProductDescriptionDisplayed(){
        return catalogPage.isProductDescriptionDisplayed();
    }
    public static void clickOnEditProduct(){
        catalogPage.clickOnEditProduct();
    }
    public static void selectFirstEditItem(){
        catalogPage.selectFirstEditItem();
    }
    public static void selectCopyPDP(){
        catalogPage.selectCopyPDP();
    }
    public static void selectExportPDP(){
        catalogPage.selectExportPDP();
    }
    public static boolean isPDPLinkCopiedPopupDisplayed(){
        return catalogPage.isPDPLinkCopiedPopupDisplayed();
    }
    public static boolean isNavigatedToProductDetails(){
        return catalogPage.isNavigatedToProductDetails();
    }
    public static boolean isPDFExported(){
        return catalogPage.isPDFExported();
    }
    public static void selectExportCatalog(){
        catalogPage.selectExportCatalog();
    }
    public static void selectExportPromoFiles(){
        catalogPage.selectExportPromoFiles();
    }
    public static void clickBack(){catalogPage.clickBack();}
    public static void clickOnProprietaryItemStatus(String proprietaryStatus){
        catalogPage.clickOnProprietaryItem(proprietaryStatus);
    }
    public static void selectProprietaryItem() throws InterruptedException {
        catalogPage.selectProprietaryItem();
    }
    public static void clickOnCategory(String category){
        catalogPage.clickOnCategory(category);
    }
    public static void selectCategory() throws InterruptedException {
        catalogPage.selectCategoryMeat();
    }
    public static void clickOnSubCategory(String subCategory){
        catalogPage.clickOnSubCategory(subCategory);
    }
    public static void selectSubCategory() throws InterruptedException {
        catalogPage.selectSubCategoryPork();
    }
    public static void clickOnStorageMethod(String storageMethod){
        catalogPage.clickOnStorageMethod(storageMethod);
    }
    public static boolean isStorageMethodDisplayed(String storageMethod){
        return catalogPage.isStorageMethodDisplayed(storageMethod);
    }
    public static void typeNewDescription(String description)throws InterruptedException{
        catalogPage.typeNewDescription(description);
    }
    public static boolean isNewDescriptionDisplayed(String description){
        return catalogPage.isNewDescriptionDisplayed(description);
    }
    public static void clickOnSale(){catalogPage.clickOnSale();}
    public static void clickNewArrival(){catalogPage.clickNewArrival();}
    public static void selectOnSaleYes()throws InterruptedException{
        catalogPage.selectOnSaleYes();
    }
    public static void selectNewArrivalYes()throws InterruptedException{
        catalogPage.selectNewArrivalYes();
    }
    public static void clickOnCategoryOption(String category){
        catalogPage.clickOnCategoryOption(category);
    }
    public static boolean isCategoryFilteredItem(String category)throws InterruptedException{
        return catalogPage.isCategoryFilteredItem(category);
    }

    public static String getItemCodeFirstRowInCatalog() throws InterruptedException {
        return catalogPage.getItemCodeFirstRowInCatalog();
    }

    public static String getItemNameFirstRowInCatalog() throws InterruptedException {
        return catalogPage.getItemNameFirstRowInCatalog();
    }

    public static String getFirstElementFrmSearchResults(String name){
        return catalogPage.getFirstItemNameFrmSearchResults(name);
    }
    public static void clickMediaType(String type){
        catalogPage.clickMediaType(type);
    }

    public static void addSubstitutionsStable(String itemCode){
        if(catalogPage.isSubstituteItemDisplayed(itemCode)){
            catalogPage.clickOnDeleteSubstituteItemBtn(itemCode);
        }
        catalogPage.clickAddSubstitutionBtn();
    }

}
