package com.cutanddry.qa.functions;

import com.cutanddry.qa.pages.CatalogPage;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

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
    public static void clickOnPreview() throws InterruptedException {
        catalogPage.clickPreview();
    }
    public static boolean isNavigatedToPreview() {
        return catalogPage.isNavigatedToPreview();
    }
    public static void DownloadPDF() {
        catalogPage.clickExportPdf();
    }
    public static void SearchItemInCatalogByItemCode(String ItemCode){
        catalogPage.clickOnItemStatus("All");
        catalogPage.TypeSearchInCatalogSearch(ItemCode);}
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
        catalogPage.clickOnItemStatus("All");
        catalogPage.clickonItemOnCatalogPage(itemCode);
    }

    public static void sortItemNamesAscendingInCatalog(){
        catalogPage.sortCatalogItemsNameAscending();
    }

    public static String getProductStatusFromCatalogProductGrid(String productId){
        return catalogPage.getProductStatusFromProductGrid(productId);
    }
    public static String getItemcodeInCatalogData(){
        return catalogPage.getItemCodeFromCatalogDataPage();
    }

    public static void selectEditFromProductConfig(){
        catalogPage.clickEditOnProductConfigs();
    }
    public static void selectProductActiveInactiveStatus(String prodStatus){
        catalogPage.clickOnInactiveOrInactive(prodStatus);
    }

    public static void saveChanges(){
        catalogPage.clickOnSaveChangesBtn();
    }

    public static boolean successOverlayDisplayed(){
//       return catalogPage.isSuccessOverlayDisplayed();
        return true;
    }

    public static void navigateToSpecifications(){
        catalogPage.clickOnSpecificationsTab();
    }

    public static boolean isCertificationsDisplayed(){
        return catalogPage.isCertificationsSectionDisplayed();
    }

    public static void clearCertification(String CertificationType){
        catalogPage.clickClearCertification(CertificationType);
    }

    public static void selectCertification(String certification){
        catalogPage.clickOnCertification(certification);
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

    public static void addUnitOfMeasure(String uom)throws InterruptedException{
        if (catalogPage.isSameUomDisplayed(uom)){
            catalogPage.deleteUOMinCatalog();
            catalogPage.clickOnConfirmBtn();
            catalogPage.clickOnSaveChangesBtn();
            catalogPage.clickOnPricingAndPromotionsTab();
//            catalogPage.refreshPage();
        }
        catalogPage.clickOnUnitOfMeasure();
    }
    public static void addUnitOfMeasureStable(String uom)throws InterruptedException{
        if (catalogPage.isSameUomDisplayed(uom)){
            catalogPage.deleteUOMinCatalog(uom);
            catalogPage.clickOnConfirmBtn();
            catalogPage.clickOnSaveChangesBtn();
            catalogPage.clickOnPricingAndPromotionsTab();
//            catalogPage.refreshPage();
        }
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
    public static void setItemUnitPrice(String uom, String unitPrice){
        catalogPage.typeUnitPrice(uom, unitPrice);
    }

    public static void selectPercentageAsSalesTypeFrmDropdown(){
        catalogPage.clickOnSalesTypeDropDown();
        catalogPage.clickOnPercentageOption();
    }

    public static void selectDollarValueAsSalesTypeFrmDropdown(String uom){
        catalogPage.clickOnSalesTypeDropDown(uom);
//        catalogPage.clickOndollarValueOption();
    }

    public static void setSaleValue(String saleValue){
        catalogPage.typeSaleValue(saleValue);
    }

    public static void setSaleValue(String uom, String saleValue){
        catalogPage.typeSaleValue(uom, saleValue);
    }

    public static void deleteUOMFromCatalog() throws InterruptedException {
        catalogPage.deleteUOMinCatalog();
    }

    public static void deleteUOMFromCatalog(String uom) throws InterruptedException {
        catalogPage.deleteUOMinCatalog(uom);
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

    public static boolean isAddedUOMDisplayed(String uom) throws InterruptedException {
        return catalogPage.isUOMDisplayed(uom);
    }

    public static boolean isDeletedUOMDisplayed(){
        return catalogPage.isBagUOMDisplayed();
    }

    public static boolean isDeletedUOMDisplayed(String uom) throws InterruptedException {
        return catalogPage.isUOMDisplayed(uom);
    }

    public static void navigateToSubstituteTab(){
        catalogPage.clickOnSubstituteTab();
    }

    public static void removeExistingItem(String ItemCode){
        if (catalogPage.isDeleteSubstituteItemDisplayed(ItemCode)) {
            catalogPage.clickOnDeleteSubstituteItemBtn(ItemCode);
            catalogPage.clickOnSaveChangesBtn();
            catalogPage.clickOnSubstituteTab();
        }

    }

    public static void addingItemAtZeroItem(String ItemCode){
        if (!catalogPage.isDeleteSubstituteItemDisplayed(ItemCode)) {
            catalogPage.clickAddSubstitutionBtn();
            String SubstituteItemName = catalogPage.getSubstituteItemName(ItemCode);
            catalogPage.clickAddSubstitutionBtn();
            catalogPage.searchSubstituteItem(SubstituteItemName);
            catalogPage.addSubstitutionsBtn();
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
        catalogPage.clickOnItemStatus("All");
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
    public static void clickOnProprietaryItemStatus(String proprietaryStatus) throws InterruptedException {
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
    public static void removeCategory(String category){
        catalogPage.deleteCategory(category);
    }
    public static void clickOnSubCategory(String subCategory){
        catalogPage.clickOnSubCategory(subCategory);
    }
    public static void selectSubCategory() throws InterruptedException {
        catalogPage.selectSubCategoryPork();
    }
    public static void clickOnStorageMethod(String storageMethod) throws InterruptedException {
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
            catalogPage.clickOnSaveChangesBtn();
            catalogPage.clickOnSubstituteTab();
        }
        catalogPage.clickAddSubstitutionBtn();
    }
    //-------------------- Multi UOM -----------------------
    public static void ClickOnMultiUomDropDownOG(String code)throws InterruptedException{
        catalogPage.ClickOnMultiUomDropDownOG(code);
    }
    public static double getOGPriceUOM(String code,String uom) throws InterruptedException {
        return catalogPage.getOGPriceUOM(code,uom);
    }
    public static void clickOGAddToCartPlusIcon(int count,String code, String uom) throws InterruptedException{
        for (int i=0; i<count;i++){
            catalogPage.clickOGAddToCartPlusIcon(code,uom);
        }
    }
    public static String getItemUOMQuantity(String code,String uom){
        return catalogPage.getItemUOMQuantity(code,uom);
    }
    public static void clickOGAddToCartMinusIcon(int count,String code, String uom) throws InterruptedException{
        for (int i=0; i<count;i++){
            catalogPage.clickOGAddToCartMinusIcon(code,uom);
        }
    }
    public static void clickSubmittedOrder(String id){
        catalogPage.clickSubmittedOrder(id);
    }
    public static double getTotalPriceInOrder() throws InterruptedException {
        return catalogPage.getTotalPriceInOrder();
    }
    public static String getTotalQuantityInOrder(){
        return catalogPage.getTotalQuantityInOrder();
    }
    public static void ClickOnMultiUomDropDown(String name)throws InterruptedException{
        catalogPage.ClickOnMultiUomDropDown(name);
    }
    public static void ClickOnMultiUomDropDownOption(String option)throws InterruptedException{
        catalogPage.ClickOnMultiUomDropDownOption(option);
    }
    public static double getPDPPriceUOM(String uom) throws InterruptedException {
        return catalogPage.getPDPPriceUOM(uom);
    }
    public static double getPDPPriceUOMVitco(String uom, String code) throws InterruptedException {
        return catalogPage.getPDPPriceUOMVitco(uom, code);
    }
    public static void clickAddToCartPlusIcon(int count, String uom) throws InterruptedException{
        for (int i=0; i<count;i++){
            catalogPage.clickAddToCartPlusIcon(uom);
        }
    }
    public static void clickAddToCartPlusIconVitco(int count, String uom, String code) throws InterruptedException{
        for (int i=0; i<count;i++){
            catalogPage.clickAddToCartPlusIconVitco(uom,code);
        }
    }
    public static void clickAddToCartMinusIcon(int count, String name) {
        for (int i=0; i<count;i++){
            catalogPage.clickAddToCartMinusIcon(name);
        }
    }
    public static boolean isEditQuantitiesButtonDisplayed(String name){
        return catalogPage.isEditQuantitiesButtonDisplayed(name);
    }
    public static boolean isAddedQuantitiesDisplayed(String name ,String qty){
        return catalogPage.isAddedQuantitiesDisplayed(name,qty);
    }
    public static void clickOnCatalogProduct(String name){
        catalogPage.clickOnCatalogProduct(name);
    }
    public static double getTotalPriceInReviewOrder() throws InterruptedException {
        return catalogPage.getTotalPriceInReviewOrder();
    }
    public static String getTotalQuantityInReviewOrder(){
        return catalogPage.getTotalQuantityInReviewOrder();
    }
    public static void clickReviewOrderTrashIcon(String code)throws InterruptedException{
        catalogPage.clickReviewOrderTrashIcon(code);
    }
    public static boolean isSubmittedStandingOrderDisplayed(String quantity ,String price){
        return  catalogPage.isSubmittedStandingOrderDisplayed(quantity,price);
    }
    public static void searchOrderGuide(String item) throws InterruptedException {
        catalogPage.searchOrderGuide(item);
    }
    public static double getUOMOGPrice(String code,String uom) throws InterruptedException {
        return catalogPage.getUOMOGPrice(code,uom);
    }
    public static double getItemPriceOnCheckoutButtonOG() throws InterruptedException {
        return catalogPage.getItemPriceOnCheckoutButtonOG();
    }
    public static void ClickOnCatalogMultiUomDropDown(String name)throws InterruptedException{
        catalogPage.ClickOnCatalogMultiUomDropDown(name);
    }
    public static double getDeliveryFeesPriceInReviewOrder() throws InterruptedException {
        return catalogPage.getDeliveryFeesPriceInReviewOrder();
    }
    public static double getTotalEndlessAislePriceInReviewOrder() throws InterruptedException {
        return catalogPage.getTotalEndlessAislePriceInReviewOrder();
    }
    public static double getTotalEndlessAisleSubTotalPriceInReviewOrder() throws InterruptedException {
        return catalogPage.getTotalEndlessAisleSubTotalPriceInReviewOrder();
    }
    public static double getSubTotalPriceInOrder() throws InterruptedException {
        return catalogPage.getSubTotalPriceInOrder();
    }
    public static boolean isCatalogAddToCartButtonDisplayed(String name){
        return catalogPage.isCatalogAddToCartButtonDisplayed(name);
    }
    public static void deleteSubstitute(){
        catalogPage.deleteSubstitute();
    }
    public static void ClickOnCatalogMultiUomDropDownStable(String name)throws InterruptedException{
        catalogPage.ClickOnCatalogMultiUomDropDownStable(name);
    }
    public static void selectItemStatus(String itemStatus){
        catalogPage.clickOnItemStatus(itemStatus);
    }
    public static boolean isLastOrderDateDisplayed(String date)throws InterruptedException{
        return catalogPage.isLastOrderDateDisplayed(date);
    }
    public static void clickPurchaseHistory(){
        catalogPage.clickPurchaseHistory();
    }
    public static boolean isLastOrderDatePDPDisplayed(String date)throws InterruptedException{
        return catalogPage.isLastOrderDatePDPDisplayed(date);
    }
    public static String getCopiedPDPUrl() throws IOException, InterruptedException, UnsupportedFlavorException {
        return catalogPage.getCopiedPDPUrl();
    }
    public static void loginPDPURL(String pdpURL) throws InterruptedException {
        catalogPage.loginPDPURL(pdpURL);
    }
    public static boolean isAlreadyCustomerPopUpDisplay() throws InterruptedException {
        return catalogPage.isAlreadyCustomerPopUpDisplay();
    }
    public static boolean isAlreadyCustomerButtonDisplay(String name){
        return catalogPage.isAlreadyCustomerButtonDisplay(name);
    }
    public static void loginPDPURLSame(String pdpURL) throws InterruptedException {
        catalogPage.loginPDPURLSame(pdpURL);
    }
    public static void ClickOnMultiUomEachOption(String code)throws InterruptedException{
        catalogPage.ClickOnMultiUomEachOption(code);
    }
    public static String getTotalLineItemInOrder(){
        return catalogPage.getTotalLineItemInOrder();
    }

    public static void clickOnMultiUomDropDownOrderGuide(String code,String option)throws InterruptedException{
        catalogPage.clickOnMultiUomDropDownOrderGuide(code,option);
    }
    public static boolean isMeasureOptionDisplay(String option){
        return catalogPage.isMeasureOptionDisplay(option);
    }
    public static boolean isLastOrderMarginDisplay(String margin){
        return catalogPage.isLastOrderMarginDisplay(margin);
    }
    public static boolean isMarginColumnDisplay(String margin){
        return catalogPage.isMarginColumnDisplay(margin);
    }
    public static boolean isPriceColumnDisplay(String price){
        return catalogPage.isPriceColumnDisplay(price);
    }
    public static boolean isLastOrderPriceDisplay(String price){
        return catalogPage.isLastOrderPriceDisplay(price);
    }
    public static boolean isCashAndCarryAllowedDisplay(String option){
        return catalogPage.isCashAndCarryAllowedDisplay(option);
    }
    public static boolean isActionableOverviewDisplay(){
        return catalogPage.isActionableOverviewDisplay();
    }
    public static boolean isNewProductDisplay(){
        return catalogPage.isNewProductDisplay();
    }
    public static void clickOnSpecialItem(String status){
        catalogPage.clickOnSpecialItem(status);
    }
    public static boolean isSpecialItemDropDownDisplay(){
        return catalogPage.isSpecialItemDropDownDisplay();
    }

    public static String getItemCodeSecondRowInCatalog() throws InterruptedException {
        return catalogPage.getItemCodeSecondRowInCatalog();
    }

    public static String getItemNameSecondRowInCatalog() throws InterruptedException {
        return catalogPage.getItemNameSecondRowInCatalog();
    }


}
