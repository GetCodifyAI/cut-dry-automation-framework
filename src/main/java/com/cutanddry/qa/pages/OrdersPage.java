package com.cutanddry.qa.pages;

import org.openqa.selenium.By;

public class OrdersPage extends LoginPage{
    By txt_orders = By.xpath("//h2[contains(text(),'Orders')]");
    String supplierNameInPlaceOrder = "//div[contains(text(),'SUPPLIERNAME')]";
    String quantityIncreaseBtn = "//td[text()='ITEMCODE']/following-sibling::td//div[contains(@class, '_du1frc') and contains(@class, 'py-2') and contains(@class, 'ml-2')]/*";
    By checkOutBtn = By.xpath("//button[@data-for='cartCheckoutButton' and contains(text(),'$')]");
    By submitForApproval = By.xpath("//button[contains(text(),'Submit for Approval')]");
    By sendForApprovaltext = By.xpath("//strong[contains(text(),'Sent for approval!')]");
    By approvalOrderRefID = By.xpath("//div[contains(normalize-space(text()),'Ref #')]/following-sibling::div");
    By viewOrderInDraft = By.xpath("//button[contains(text(),'View Order in Drafts')]");
    By pendingApprovalText = By.xpath("//span[contains(text(),'Pending Approval')]");
    By selectOrderGuide = By.xpath("//div[contains(text(),'Select Order Guide')]");
    String orderGuide ="//div[contains(text(),'ORDERGUIDE')]";
    By ratingOverlayIframe = By.xpath("//iframe[contains(@aria-label,'NPS Survey')]");
    By ratingOverlayCloseBtn = By.xpath("//div[contains(text(),'✕')]");
    By lbl_firstOrderTickBox = By.xpath("//tbody/tr[2]/td[1]");
    By lbl_firstOrder = By.xpath("//tbody/tr[2]/td[2]");
    By btn_editOrder = By.xpath("//*[contains(text(),'Edit Order')]");
    By lbl_editOrderTitle = By.xpath("//h2[contains(text(),'Order')]");
    By lbl_editOrderTitleReview = By.xpath("(//*[contains(@data-tip,'Edit Order')]/div)[1]");
    By txt_editOrderPopup = By.xpath("//h2[contains(text(),'Edit Order?')]");
    By btn_confirm= By.xpath("//button[contains(text(),'Confirm')]");
    By txt_editOrder = By.xpath("//span/div[contains(text(),'Edit Order')]");
    By link_editOrder = By.xpath("//*[contains(text(),'Edit Order')]");
    By reviewOrderText = By.xpath("//div[contains(text(),'Review Order')]");
    By orderUpdatedText = By.xpath("//*[contains(text(),'Order edit requested')]");
    By txt_submitPopup = By.xpath("//h2[contains(text(),'Submit Changes?')]");
    By btn_close = By.xpath("//button[contains(text(),'Close')]");
    By btn_bulkActions =    By.xpath("//button[span[contains(., 'Bulk Actions')]]");
    By txt_printConfirm = By.xpath("//a[contains(text(), 'Print Order Confirmations')]");
    By txt_printKitchenReceipt = By.xpath("//a[contains(text(), 'Print Kitchen Receipt')]");
    By tbx_searchOrders = By.xpath("//input[@placeholder='Search']");
    By tbx_firstOrder = By.xpath("//tbody/tr[2]/td[5]");
    By lbl_orderDateDropdown = By.xpath("(//div[contains(@class, 'css-1uccc91-singleValue')])[1]");
    By lbl_statusDropdown = By.xpath("(//div[contains(@class, 'css-1uccc91-singleValue')])[2]");
    By txt_date = By.xpath("(//td[2])[1]");
    By txt_status = By.xpath("(//td[10])[1]/span");
    By moreFilterStatus = By.xpath("(//td[10])[1]/div[1]/following-sibling::div[1]");
    String days = "//div[text()='DATE']";
    String sts = "//div[text()='STATUS']";
    String date = "//td[text()='DATE']";
    String status = "//td/span[text()='STATUS']";
    String lbl_status = "//td[COUNT]/span[text()='STATUS']";
    By btn_nextArrowNavigation = By.xpath("(//*[local-name()='svg' and @data-icon='chevron-right'])[last()]");
    By txt_resultsCount = By.xpath("//div[contains(text(), 'results')]");
    By btn_moreFilters = By.xpath("//button[contains(., 'More Filters')]");
    By txt_filterOrders= By.xpath("//div[contains(text(),'Filter Orders')]");
//    By lbl_credReqStat = By.xpath("//label[contains(text(), 'Credit Request Status')]/following-sibling::div//div[contains(@class, 'themed_select__control')]");
    By lbl_credReqStat = By.xpath("(//label[contains(text(), 'Credit Request Status')]/following-sibling::div//div)[1]");
    By lbl_req = By.xpath("//div[text()='Requested']");
    String sel_CreditRequestStatus = "//div[text()='TYPE']";
    String sel_SalesPersonStatus = "//div[text()='TYPE']";
    By btn_save = By.xpath("//button[contains(text(),'Save')]");
    String lbl_credReq = "//div[contains(text(),'MOREFILTERSTATUS')]";
    By lbl_salesperson = By.xpath("//label[contains(text(), 'Salesperson')]/following-sibling::div//div[contains(@class, 'themed_select__control')]");
    By txt_salesperson = By.xpath("//div[contains(text(),'Chamika IFC')]");
    By first_row_order_details = By.xpath("//tr[2]/td[4]");
    By txt_order_section = By.xpath("//div[starts-with(text(), 'Order #')]");
    By btn_checkin = By.xpath("//span[text() = 'Check-In Order']");
    By txt_checkin_order_section = By.xpath("//h2[@class='mb-0 _1vx3fhy' and starts-with(text(), 'Check-In Order #')]");
    By btn_threeDot = By.xpath("(//button[@id='dropdown-basic'])[1]");
    By txt_threeDotPrintOrderConfirmation = By.xpath("//span[contains(text(), 'Print Order Confirmation')]");
    By txt_threeDotPrintKitchenReceipt = By.xpath("//span[contains(text(), 'Print Kitchen Receipt')]");
    By txt_updateStatus = By.xpath("//div[@class = 'mont modal-title h4' and text() = 'Update Status']");
    By bulkActionStatusDropDown = By.xpath("//label[contains(text(), 'Status')]/following-sibling::div//div[contains(@class, 'themed_select__value-container css-1hwfws3')]");
    By bulkActionsStatusOption = By.xpath("//div[contains(@class, 'themed_select__option css-yt9ioa-option') and contains(text(),'Invoiced')]");
    By bulkActionsUpdateStatus = By.xpath("//a[contains(text(), 'Update Status')]");
    By areYouSurePopUp = By.xpath("//h2[@class = 'swal2-title' and text() = 'Are you sure?']");
    By btn_yes = By.xpath("//button[contains(@class, 'swal2-confirm _1fmw5qi order-2 swal2-styled') and text() = 'Yes']");
    By btn_cancel = By.xpath("//button[contains(@class, 'swal2-cancel') and text() = 'Cancel']");
    By orderStatusUpdatedPopUp = By.xpath("//h2[contains(text(),'Order Status Updated!')]");
    By btn_ok = By.xpath("//button[contains(@class, 'swal2-confirm swal2-styled') and text() = 'OK']");
    By lbl_orderTableColumn = By.xpath("//table/thead/tr/th");
    String lbl_orderTableColumnName = "//table/thead/tr/th[COUNT]/span";
    String lbl_creditRequested = "//tr//td[COUNT]//div[contains(text(),'STATUS')]";
    String lbl_orderStatus = "//span[normalize-space()='Status:']/following-sibling::span[contains(text(),'STATUSVALUE')]";
    By btn_orderStatus = By.xpath("//span[normalize-space()='Status:']/../..");
    String lbl_orderStatusOption = "//a[contains(text(),'STATUS')]";
    By txt_description = By.xpath("(//textarea[@class='form-control'])[1]");
    By btn_timeline = By.xpath("//a[text()='Timeline' and @role='tab']");
    By timestampTimeline = By.xpath("//table[@class='table table-hover']/tbody/tr/td[1]");
    By statusTimeline = By.xpath("//table[@class='table table-hover']/tbody/tr/td[2]");
    By organizationTimeline = By.xpath("//table[@class='table table-hover']/tbody/tr/td[3]");
    By userTimeline = By.xpath("//table[@class='table table-hover']/tbody/tr/td[4]");
    By btnReportIssue = By.xpath("//button[@type='button' and @class='mr-3 btn btn-outline-danger' and contains(., 'Report Issue')]");
    By firstCheckBox_tbleOrderIssues = By.xpath("//table[@class='mt-3 table table-hover']/tbody/tr[1]");
    By dropDown_whatIsWrong = By.xpath("//div[contains(@class, 'themed_select__control')]");
    By dropDownFirstOption_whatIsWrong = By.xpath("//div[contains(@class, 'themed_select__control')]//div[@class='themed_select__option'][1]");
    String dropDownWhatIsWrongOption = "//div[contains(@class, 'themed_select__option') and text()='Missing']";
    By btn_continue = By.xpath("//button[@type='submit' and @class='btn btn-primary btn-block' and contains(text(), 'Continue')]");
    By btn_saveCheckIn = By.xpath("//button[contains(text(), 'Save Check-In')]");
    By btn_selectOrderGuide = By.xpath("//div[contains(text(),'Select Order Guide')]/following-sibling::div/div[1]");
    String btn_editColumn = "//button[text()='OPTION']";
    String txt_editColumn = "//div[text()='OPTION']";
    String customizeColumn = "(//span[text()='COLUMN'])[2]/../following-sibling::*[name()='svg' and @data-icon='cdLock']";
    By sel_customizeColumn = By.xpath("//*[contains(text(),'Cut+Dry Reference')]/preceding-sibling::div//*[name()='svg' and (@data-icon='square-check' or @data-icon='square')]");
    By btn_updateColumn = By.xpath("//button[text()='Update']");
    By txt_columnSettingUpdate= By.xpath("//h2[text()='Column settings updated!']");
    String columnSettingUpdated= "//span[text()='COLUMN']";
    By selectLocation = By.xpath("//div[contains(text(),'Select Location')]");
    By btn_selectLocation = By.xpath("//div[contains(text(),'Select Location')]/following-sibling::div/div[1]");
    By btn_FindMoreInCatalog = By.xpath("//button[text()='Find More in Catalog']");
    By lbl_inactiveItemDetected = By.xpath("//div[text()='Inactive Items Removed']");
    String selectLocationSupplier = "//div[contains(text(),'Select Location')]/following-sibling::div//div[text()='LOCATION']";

    public void clickBtnSaveCheckIn(){
        distributorUI.click(btn_saveCheckIn);
        try {
            distributorUI.waitForCustom(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void clickOnBtnContinue(){
        distributorUI.click(btn_continue);
    }

    public void clickOnDropDownWhatIsWrong(){
        distributorUI.waitForVisibility(dropDown_whatIsWrong);
        distributorUI.click(dropDown_whatIsWrong);
    }

    public void clickOnFirstRowDropDownWhatIsWrong(){
        By dropDownOption = By.xpath(dropDownWhatIsWrongOption);
        distributorUI.click(dropDownOption);
    }

    public void clickOnFirstRowTableOrderIssues(){
        distributorUI.waitForVisibility(firstCheckBox_tbleOrderIssues);
        distributorUI.click(firstCheckBox_tbleOrderIssues);
    }


    public void clickReportIssue(){
        distributorUI.click(btnReportIssue);
    }

    public String[] getTimelineData(){
        String timeStamp = distributorUI.getText(timestampTimeline);
        String status = distributorUI.getText(statusTimeline);
        String organization = distributorUI.getText(organizationTimeline);
        String user = distributorUI.getText(userTimeline);

        return new String[]{timeStamp, status, organization, user};
    }

    public void clickTimeline(){
        distributorUI.click(btn_timeline);
    }



    public boolean isOrdersTextDisplayed(){
        try {
            distributorUI.waitForVisibility(txt_orders);
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(txt_orders);
    }

    public void clickOnSupplier(String supplierName){
        distributorUI.waitForVisibility(By.xpath(supplierNameInPlaceOrder.replace("SUPPLIERNAME",supplierName)));
        distributorUI.click(By.xpath(supplierNameInPlaceOrder.replace("SUPPLIERNAME",supplierName)));
    }

    public void clickOnIncreaseQuantityBtnInItem(String ItemCode, int Quantity){
        for (int i = 0; i < Quantity; i++) {
            distributorUI.click(By.xpath(quantityIncreaseBtn.replace("ITEMCODE", ItemCode)));
        }
    }

    public void clickCloseRatingOverlay(){
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

    public String getApprovalOrderRefID(){
        return distributorUI.getText(approvalOrderRefID);
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

    public boolean isSelectOrderGuidePopUpDisplayed(){
        return distributorUI.isDisplayed(selectOrderGuide);
    }
    public void clickOnFirstOrder() throws InterruptedException {
        distributorUI.click(lbl_firstOrder);
        distributorUI.waitForCustom(3000);
    }

    public void clickOnFirstOrder(String status) {
        int totalColumnCount = distributorUI.countElements(lbl_orderTableColumn);

        for (int i = 1; i <= totalColumnCount; i++) {
            String columnName = distributorUI.getText(By.xpath(lbl_orderTableColumnName.replace("COUNT", String.valueOf(i))));
            if ("Status".equalsIgnoreCase(columnName)) {
                By creditRequestedLocator = By.xpath(
                        lbl_status.replace("COUNT", String.valueOf(i)).replace("STATUS", status)
                );
               distributorUI.click(creditRequestedLocator);
               break;
            }
        }
    }

    public void clickOnEditOrder() throws InterruptedException {
        distributorUI.click(btn_editOrder);
        distributorUI.waitForVisibility(lbl_editOrderTitle);
        System.out.println("Order Ref No: "+distributorUI.getText(lbl_editOrderTitle));
    }
    public void clickOnEditOrderInReview() throws InterruptedException {
        distributorUI.click(link_editOrder);
        distributorUI.waitForVisibility(lbl_editOrderTitleReview);
        System.out.println("Order Ref No: "+distributorUI.getText(lbl_editOrderTitleReview));
    }
    public boolean isEditOrderPopupDisplayed(){
        return distributorUI.isDisplayed(txt_editOrderPopup);
    }
    public void clickOnConfirm(){
        distributorUI.click(btn_confirm);
    }
    public boolean isNavigatedToEditOrder(){
        return distributorUI.isDisplayed(txt_editOrder);
    }

    public boolean isNavigatedToReviewOrderScreen(){
        return distributorUI.isDisplayed(reviewOrderText);
    }

    public boolean isOrderUpdatedTxtDisplayed(){
        return distributorUI.isDisplayed(orderUpdatedText);
    }

    public boolean isSubmitPopupDisplayed(){
        return distributorUI.isDisplayed(txt_submitPopup);
    }
    public void clickOnClose(){
        distributorUI.click(btn_close);
    }
    public void selectFirstOrder(){
        distributorUI.click(lbl_firstOrderTickBox);
    }
    public void clickBulkActions(){
        distributorUI.click(btn_bulkActions);
    }
    public void clickPrintKitchenReceipt(){
        distributorUI.click(txt_printConfirm);
    }
    public void clickPrintOrderConfirmation(){
        distributorUI.click(txt_printKitchenReceipt);
    }
    public void typeOnSearch(String code) throws InterruptedException {
        distributorUI.click(tbx_searchOrders);
        distributorUI.clear(tbx_searchOrders);
        distributorUI.waitForCustom(1000);
        distributorUI.sendKeys(tbx_searchOrders, code);
    }
    public String isCustomerSearchResultDisplayed() throws InterruptedException {
        distributorUI.waitForCustom(4000);
        return distributorUI.getText(tbx_firstOrder);
    }
    public void selectOrderDate(String day){
        distributorUI.click(lbl_orderDateDropdown);
        distributorUI.waitForVisibility(By.xpath(days.replace("DATE", day)));
        distributorUI.click(By.xpath(days.replace("DATE", day)));
    }
    public void selectOrderStatus(String stat){
        distributorUI.click(lbl_statusDropdown);
        distributorUI.waitForVisibility(By.xpath(sts.replace("STATUS", stat)));
        distributorUI.click(By.xpath(sts.replace("STATUS", stat)));
    }
    public boolean isOrderStatusChanged(String stat){
        try {
            distributorUI.isDisplayed(By.xpath(sts.replace("STATUS", stat)));
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(By.xpath(sts.replace("STATUS", stat)));
    }
    public boolean isOrderDateChanged(String day){
        try {
            distributorUI.isDisplayed(By.xpath(days.replace("DATE", day)));
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(By.xpath(days.replace("DATE", day)));
    }
    public String getResultsCount() throws InterruptedException {
        distributorUI.waitForCustom(4000);
        distributorUI.waitForVisibility(txt_resultsCount);
        String resultsText = distributorUI.getText(txt_resultsCount);
        return resultsText.split(" ")[0];
    }
    public String getCountDates() {
        String d = distributorUI.getText(txt_date);
        return String.valueOf(distributorUI.countElements(By.xpath(date.replace("DATE", d))));
    }

    public Boolean isFilteredOrdersCorrect(String OrdersDate) throws InterruptedException {
        distributorUI.waitForCustom(2000);
        for (int i = 0; i < 5; i++) {
            distributorUI.scrollToElementStable(By.xpath("(" + date.replace("DATE", OrdersDate) + ")" + "[last()]"),5);
            boolean status = distributorUI.isDisplayed(By.xpath("(" + date.replace("DATE", OrdersDate) + ")" + "[last()]"),5);
            if (status) {
                return true;
            } else {
                distributorUI.clickWithScrollAndHover(btn_nextArrowNavigation);
            }

        }
        return false;
    }

    /*public Boolean isFilteredOrderStatusCorrect(String OrdersStatus){
        try {
            distributorUI.waitForCustom(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String s = distributorUI.getText(txt_status);
        distributorUI.scrollToElement(By.xpath("("+ status.replace("STATUS", s) + ")" + "[last()]"));
        return distributorUI.validateFilteredElements(By.xpath(status.replace("STATUS", s)),OrdersStatus);
    }
*/
    public boolean isFilteredOrderStatusCorrect(String status) {
        int totalColumnCount = distributorUI.countElements(lbl_orderTableColumn);

        for (int i = 1; i <= totalColumnCount; i++) {
            String columnName = distributorUI.getText(By.xpath(lbl_orderTableColumnName.replace("COUNT", String.valueOf(i))));
            if ("Status".equalsIgnoreCase(columnName)) {
                By creditRequestedLocator = By.xpath(
                        lbl_status.replace("COUNT", String.valueOf(i)).replace("STATUS", status)
                );
                return distributorUI.isDisplayed(creditRequestedLocator);
            }
        }
        return false;
    }

    public Boolean isMoreFiltersDisplayedCorrect(String OrdersStatus){
        try {
            distributorUI.waitForCustom(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String f = distributorUI.getText(moreFilterStatus);
        distributorUI.scrollToElement(By.xpath("("+ lbl_credReq.replace("MOREFILTERSTATUS", f) + ")" + "[last()]"));
        return distributorUI.validateFilteredElements(By.xpath(lbl_credReq.replace("MOREFILTERSTATUS", f)),OrdersStatus);
    }


    public String getCountStatus() {
        String s = distributorUI.getText(txt_status);
        return String.valueOf(distributorUI.countElements(By.xpath(status.replace("STATUS", s))));
    }
    public void clickOnMoreFilters() {
        distributorUI.click(btn_moreFilters);
    }
    public boolean isFilterOrdersPopupDisplayed(){
        return  distributorUI.isDisplayed(txt_filterOrders);
    }
    public void selectCreditReqStatus() throws InterruptedException {
        distributorUI.click(lbl_credReqStat);
        distributorUI.hoverOverElement(lbl_req);
        distributorUI.waitForVisibility(lbl_req);
        distributorUI.click(lbl_req);
        distributorUI.waitForCustom(1000);
        distributorUI.click(btn_save);
        distributorUI.waitForCustom(3000);
    }

    public void selectCreditReqStatusStable(String type) throws InterruptedException {
        distributorUI.click(lbl_credReqStat);
        By lbl_CatalogExportReportType = By.xpath(sel_CreditRequestStatus.replace("TYPE", type));
        distributorUI.waitForVisibility(lbl_CatalogExportReportType);
        distributorUI.click(lbl_CatalogExportReportType);
        distributorUI.waitForCustom(3000);
        distributorUI.click(btn_save);
        distributorUI.waitForCustom(3000);
    }

    public void selectSalespersonStatus() throws InterruptedException {
        distributorUI.click(lbl_salesperson);
        distributorUI.hoverOverElement(txt_salesperson);
        distributorUI.waitForVisibility(txt_salesperson);
        distributorUI.click(txt_salesperson);
        distributorUI.waitForCustom(1000);
    }

    public void selectSalespersonStatusStable(String type) throws InterruptedException {
        distributorUI.click(lbl_salesperson);
        By lbl_SalespersonType = By.xpath(sel_SalesPersonStatus.replace("TYPE", type));
        distributorUI.waitForVisibility(lbl_SalespersonType);
        distributorUI.clickWithFallback(lbl_SalespersonType);
        distributorUI.sendKeysAndEnter(lbl_SalespersonType,"All");
    }

    /*public boolean checkFiltersCorrectlyDisplayed(String status) {

        return distributorUI.isDisplayed(By.xpath(lbl_creditRequested.replace("STATUS",status)));
    }*/

    public boolean checkFiltersCorrectlyDisplayed(String status) {
        int totalColumnCount = distributorUI.countElements(lbl_orderTableColumn);

        for (int i = 1; i <= totalColumnCount; i++) {
            String columnName = distributorUI.getText(By.xpath(lbl_orderTableColumnName.replace("COUNT", String.valueOf(i))));
            if ("Status".equalsIgnoreCase(columnName)) {
                By creditRequestedLocator = By.xpath(
                        lbl_creditRequested.replace("COUNT", String.valueOf(i)).replace("STATUS", status)
                );
                return distributorUI.isDisplayed(creditRequestedLocator);
            }
        }
        return false;
    }

    public void clickOrder(){
        distributorUI.click(first_row_order_details);
    }
    public void clickCheckIn(){
        distributorUI.click(btn_checkin);
    }
    public boolean isCheckInOrderSectionDisplayed(){
        try {
            distributorUI.waitForVisibility(txt_checkin_order_section);
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(txt_checkin_order_section);
    }
    public void clickOrderPrintKitchenReceipt(){
        distributorUI.click(btn_threeDot);
        distributorUI.click(txt_threeDotPrintKitchenReceipt);
    }
    public void clickOrderPrintOrderConfirmation(){
        distributorUI.click(btn_threeDot);
        distributorUI.click(txt_threeDotPrintOrderConfirmation);
    }
    public void clickUpdateStatus(){
        distributorUI.click(bulkActionsUpdateStatus);
    }
    public boolean isUpdateStatusDropDownDisplayed(){
        try {
            distributorUI.waitForVisibility(txt_updateStatus);
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(txt_updateStatus);
    }
    public void clickStatusDropDown(){
        distributorUI.click(bulkActionStatusDropDown);
    }
    public void clickStatusOption()throws InterruptedException{
        distributorUI.waitForVisibility(bulkActionsStatusOption);
        distributorUI.click(bulkActionsStatusOption);
        distributorUI.waitForCustom(1000);
        distributorUI.click(btn_save);
    }
    public boolean isAreYouSurePopUpDisplayed(){
        try {
            distributorUI.waitForVisibility(areYouSurePopUp);
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(areYouSurePopUp);
    }
    public void clickYes(){
        distributorUI.click(btn_yes);
    }
    public void clickCancel(){
        distributorUI.click(btn_cancel);
    }
    public boolean isOrderStatusUpdatedPopUpDisplayed(){
        try {
            distributorUI.waitForVisibility(orderStatusUpdatedPopUp);
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(orderStatusUpdatedPopUp);
    }
    public void clickOkButton(){
        distributorUI.click(btn_ok);
        try {
            distributorUI.waitForCustom(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void clickOrderStatus(){
        distributorUI.click(btn_orderStatus);
    }
    public boolean isOrderSectionDisplayed(){
        try {
            distributorUI.waitForVisibility(txt_order_section);
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(txt_order_section);
    }
    public void selectOrderStatusOption(String status) {

        distributorUI.click(By.xpath(lbl_orderStatusOption.replace("STATUS",status)));
        try {
            distributorUI.waitForCustom(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean isOrderStatusUpdatedDisplayed(String status){
        try {
            distributorUI.waitForVisibility(By.xpath(lbl_orderStatus.replace("STATUSVALUE",status)));
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(By.xpath(lbl_orderStatus.replace("STATUSVALUE",status)));
    }
    public String getLastWorkingDateEST() {
        return distributorUI.getLastWorkingDayEST();
    }

    public String getLastWorkingDateUST() {
        return distributorUI.getLastWorkingDayUST();
    }

    public void clickSaveButton(){
        distributorUI.click(btn_save);
    }
    public void selectOrderGuide(){
        distributorUI.click(btn_selectOrderGuide);
    }
    public boolean isEditColumnDisplay(String option){
        return distributorUI.isDisplayed(By.xpath(btn_editColumn.replace("OPTION",option)));
    }
    public void clickEditColumn(String option){
        distributorUI.click(By.xpath(btn_editColumn.replace("OPTION",option)));
    }
    public boolean isEditColumnWindowDisplay(String option){
        return distributorUI.isDisplayed(By.xpath(txt_editColumn.replace("OPTION",option)));
    }
    public boolean isNotCustomizeColumnDisplay(String column){
        return distributorUI.isDisplayed(By.xpath(customizeColumn.replace("COLUMN",column)));
    }
    public void setCustomizeColumn(boolean select) {
        distributorUI.waitForVisibility(sel_customizeColumn);
        boolean isSelected = distributorUI.isCheckboxBtnSelected(sel_customizeColumn);

        if (select && !isSelected) {
            distributorUI.click(sel_customizeColumn); // Select the checkbox
        } else if (!select && isSelected) {
            distributorUI.click(sel_customizeColumn); // Deselect the checkbox
        }
        try {
            distributorUI.waitForCustom(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void  clickUpdateColumn(){
        distributorUI.scrollToElement(btn_updateColumn);
        distributorUI.click(btn_updateColumn);
    }
    public boolean isColumnSettingUpdatePopUpDisplay(){
        return distributorUI.isDisplayed(txt_columnSettingUpdate);
    }
    public boolean isColumnUpdateDisplay(String column){
        return distributorUI.isDisplayed(By.xpath(columnSettingUpdated.replace("COLUMN",column)));
    }
    public boolean isSelectLocationPopUpDisplayed(){
        return distributorUI.isDisplayed(selectLocation);
    }
    public void selectLocation(){
        distributorUI.click(btn_selectLocation);
    }
    public boolean isSubmitForApprovalButtonDisplay(){
        return distributorUI.isDisplayed(submitForApproval);
    }
    public void  clickFindMoreInCatalog()throws InterruptedException{
        distributorUI.scrollToElement(btn_FindMoreInCatalog);
        distributorUI.click(btn_FindMoreInCatalog);
    }
    public boolean isInactiveItemDetectedPopUpDisplay()throws InterruptedException{
        distributorUI.waitForVisibility(lbl_inactiveItemDetected);
       return distributorUI.isDisplayed(lbl_inactiveItemDetected);
    }
    public void selectLocationSupplier(String location){
        distributorUI.click(By.xpath(selectLocationSupplier.replace("LOCATION",location)));
    }


}
