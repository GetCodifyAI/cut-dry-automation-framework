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

    public static void deleteUOMFromCatalog(){
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

}
