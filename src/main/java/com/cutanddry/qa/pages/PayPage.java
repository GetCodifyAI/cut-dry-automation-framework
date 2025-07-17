package com.cutanddry.qa.pages;

import org.openqa.selenium.By;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PayPage extends LoginPage{
    By txt_pay = By.xpath("//li[contains(text(),'Pay')]");
    By tb_invoices =By.xpath("//a[@role='tab' and text()='Invoices']");
    By tb_paymentsInit =By.xpath("//a[@role='tab' and text()='Payments Initiated']");
    By tb_payouts =By.xpath("//a[@role='tab' and text()='Payouts']");
    By txt_contactDetails = By.xpath("//thead/tr/th[normalize-space()='Contact Details']");
    By txt_invoiceID = By.xpath("//thead/tr/th[normalize-space()='Invoice ID']");
    By txt_paymentID = By.xpath("//thead/tr/th[normalize-space()='Payment ID']");
    By txt_payoutID = By.xpath("//thead/tr/th[normalize-space()='Payout ID']");
    By searchCustomers = By.xpath("//input[@placeholder='Search customers by name or code']");
    String SearchedCustomer = "//td[text()='CUSTOMER']";
    By btn_threeDot = By.xpath("//button//*[contains(@data-icon,'ellipsis-vertical')]");
    String statementOption = "//span[contains(text(),'STATEMENT')]";
    By txt_paymentReminder = By.xpath("//h4[contains(text(),'Past Due Invoice Details')]");
    By btn_sendEmail = By.xpath("//button[contains(text(),'Send Email')]");
    By txt_emailStatement = By.xpath("//div[contains(text(),'Send Pay Notification')]");
    By btn_send = By.xpath("//button[contains(text(),'Send')]");
    By txt_success = By.xpath("//h2[contains(text(),'Success')]");
    By btn_Ok = By.xpath("//button[contains(text(),'OK')]");
    String txt_customerInvoice = "//div[contains(text(),'CUSTOMER')]";

    By btn_invoiceCustomerClearFilter = By.xpath("(//div[text()='Customer']/following-sibling::div//*[name()='svg'])[1]");
    By lbl_emptyInvoiceMsg = By.xpath("//*[text()='Could not find invoices']");
    By dd_invoiceCustomerFilter = By.xpath("(//div[text()='Customer']/following-sibling::div//*[name()='svg'])[2]");
    String sel_invoiceCustomerFilter = "//div[contains(@class, 'themed_select__option') and text()='CUSTOMER']";
    By dd_invoiceStatusFilter = By.xpath("(//div[text()='Status']/following-sibling::div//*[name()='svg'])[1]");
    String sel_invoiceStatusFilter = "//div[contains(@class, 'themed_select__option') and text()='STATUS']";
    By txt_invoiceDateFilter = By.xpath("//div[text()='Invoice Date']/following-sibling::div//*[@placeholder='Select Date Range']");
    String dynamicToXPath = "//div[contains(@class,'react-datepicker__day--keyboard-selected')]/preceding::div[contains(@class, 'react-datepicker__day')][DAY]";
    By toDayXPath = By.xpath("//div[contains(@class,'react-datepicker__day--today')]");
    By dd_invoiceAuthStatusFilter = By.xpath("(//div[text()='Auth Status']/following-sibling::div//*[name()='svg'])[1]");
    String sel_invoiceAuthStatusFilter = "//div[contains(@class, 'themed_select__option') and text()='STATUS']";
    By txt_invoiceIDFilter = By.xpath("//div[text()='Invoice ID']/following-sibling::div//*[@placeholder='Search']");
    By btn_invoiceBulkAction = By.xpath("//button/*[text()='Bulk Actions']");
    String lbl_bulkOption = "//button/*[text()='Bulk Actions']/parent::button/following-sibling::div/a[contains(text(),'OPTION')]";
    By lbl_pastDuePopupTitle = By.xpath("//h4[contains(text(),'Past Due Invoice Details')]");
    By btn_invoiceSendReminders = By.xpath("//button[contains(text(),'Send Reminders')]");
    By txt_invoiceEmailSent = By.xpath("//h2[contains(text(),'Emails Sent!')]");
    By btn_invoiceOk = By.xpath("//button[contains(text(),'OK')]");
    By lbl_markInvoicePaidPopupTitle = By.xpath("//div[contains(text(),'Are you sure you want to mark these invoices as Paid ?')]");
    By btn_markInvoiceYes = By.xpath("//button[contains(text(),'Yes')]");
    By btn_markInvoiceCancel = By.xpath("//button[contains(text(),'Cancel')]");
    By lbl_invoiceCaptureFundTitle = By.xpath("//div[contains(text(),'Are you sure you want capture funds for these invoices?')]");
    By lbl_invoiceCaptureFundPopupTitle = By.xpath("//div[contains(text(),'Capture Funds')]");
    By btn_invoiceCapturePay = By.xpath("//button[contains(text(),'Pay')]");
//    By txt_error = By.xpath("//h2[contains(text(),'An error occurred while trying to charge the customer. Transaction was rejected by gateway.')]");
    By txt_error = By.xpath("//*[contains(text(),'Your transaction was declined due to Error - Transaction was rejected by gateway.\n" +
        "                Please verify your card details and try again or use a different payment method.')]");
    By cbox_invoiceFirstRecord = By.xpath("//th[contains(text(),'Invoice ID')]/ancestor::table/tbody/tr[1]/td[1]//*[name()='svg']");
    By cbox_invoiceFirstRecordSetting = By.xpath("//th[contains(text(),'Invoice ID')]/ancestor::table/tbody/tr[ROW_COUNT]/td[10]//button");
    By elements_invoiceRecords = By.xpath("//th[contains(text(),'Invoice ID')]/ancestor::table/tbody/tr");
    String cbox_invoiceRecord = "//th[contains(text(),'Invoice ID')]/ancestor::table/tbody/tr[ROW_COUNT]/td[1]//*[name()='svg']";
    String lbl_invoiceRecordID = "//th[contains(text(),'Invoice ID')]/ancestor::table/tbody/tr[ROW_COUNT]/td[2]";
    String lbl_invoiceRecordCustomerName = "//th[contains(text(),'Invoice ID')]/ancestor::table/tbody/tr[ROW_COUNT]/td[3]/div/span[1]";
    String lbl_invoiceRecordCustomerStatus = "//th[contains(text(),'Invoice ID')]/ancestor::table/tbody/tr[ROW_COUNT]/td[3]/div/span[2]/span";
    String lbl_invoiceRecordCode = "//th[contains(text(),'Invoice ID')]/ancestor::table/tbody/tr[ROW_COUNT]/td[4]";
    String lbl_invoiceRecordDate = "//th[contains(text(),'Invoice ID')]/ancestor::table/tbody/tr[ROW_COUNT]/td[5]";
    String lbl_invoiceRecordDueDate = "//th[contains(text(),'Invoice ID')]/ancestor::table/tbody/tr[ROW_COUNT]/td[6]";
    String lbl_invoiceRecordStatus = "//th[contains(text(),'Invoice ID')]/ancestor::table/tbody/tr[ROW_COUNT]/td[7]/div";
    String lbl_invoiceRecordAmount = "//th[contains(text(),'Invoice ID')]/ancestor::table/tbody/tr[ROW_COUNT]/td[8]";
    String lbl_invoiceRecordBalanceDue = "//th[contains(text(),'Invoice ID')]/ancestor::table/tbody/tr[ROW_COUNT]/td[9]";
    String btn_invoiceRecordSetting = "//th[contains(text(),'Invoice ID')]/ancestor::table/tbody/tr[ROW_COUNT]/td[10]//button";
    By btn_exportPayout = By.xpath("//a[contains(text(),'Export Payouts')]");
    By dropDown_customer = By.xpath("//div[contains(@class, 'col-sm-2') and contains(., 'Customer')]//div[contains(@class, 'themed_select__control')]");
    String option_customerDropdown = "//div[contains(@class, 'col-sm-2') and contains(., 'Customer')]//div[contains(@class, 'themed_select__menu')]//div[contains(text(), 'OPTION')]";
    By customerNameFirstRow = By.xpath("//table[contains(@class, 'table-hover') and contains(@class, 'my-3')]//tbody/tr[1]/td[4]");
    By txt_noResultsFound = By.xpath("//div[text()='No results found.']");
    By dropDown_paymentStatus = By.xpath("//div[contains(@class, 'col-sm-2') and contains(., 'Payment Status')]//div[contains(@class, 'themed_select__control')]");
    String option_paymentStatusDropdown = "//div[contains(@class, 'col-sm-2') and contains(., 'Payment Status')]//div[contains(@class, 'themed_select__menu')]//div[contains(text(), 'OPTION')]";
    By paymentStatusFirstRow = By.xpath("//table[contains(@class, 'table-hover') and contains(@class, 'my-3')]//tbody/tr[1]/td[6]");
    By dropDown_payoutStatus = By.xpath("//div[contains(@class, 'col-sm-2') and contains(., 'Payout Status')]//div[contains(@class, 'themed_select__control')]");
    String option_payoutStatusDropdown = "//div[contains(@class, 'col-sm-2') and contains(., 'Payout Status')]//div[contains(@class, 'themed_select__menu')]//div[contains(text(), 'OPTION')]";
    By timestampFirstRow = By.xpath("//table[contains(@class, 'table-hover') and contains(@class, 'my-3')]//tbody/tr[1]/td[3]");
    //    By dateRange_Pay = By.xpath("//div[contains(@class, 'col-sm-6')]//div[contains(@class, '_64fwrw') and contains(., 'Date Range')]//following-sibling::div//input[@type='text' and contains(@class, 'form-control')]");
    By dateRange_Pay = By.xpath("//div[contains(., 'Date Range')]//following-sibling::div//div[contains(@class,'react-datepicker__input-container')]/input[contains(@class, 'form-control')]");String datePicker = "//div[@class='react-datepicker']//div[@aria-label='Choose %s, %s %s%s, %s']";
    By btn_previousMonth = By.xpath("//button[@type='button' and @aria-label='Previous Month']");
    By btn_nextMonth = By.xpath("//button[@type='button' and @aria-label='Next Month']");
    By table_paymentInitiated = By.xpath("//table[@class='my-3 table table-hover']");
    String paymentStatusRow = "//table[contains(@class, 'table-hover') and contains(@class, 'my-3')]//tbody/tr[%s]/td[6]";
    By payOutStatusDropDown = By.xpath("//div[contains(@class, 'col-sm-3') and contains(., 'Payout Status')]//div[contains(@class, 'themed_select__control')]");
    String payoutStatusDropdownOption = "//div[contains(@class, 'col-sm-3') and contains(., 'Payout Status')]//div[contains(@class, 'themed_select__menu')]//div[contains(text(), 'OPTION')]";
    By txt_payoutDateFilter = By.xpath("//div[contains(@class, 'col-sm-5')]//div[contains(@class, '_64fwrw') and contains(., 'Date Range')]//following-sibling::div//input[@type='text' and contains(@class, 'form-control')]");
    By payoutStatus = By.xpath("//table[@class='my-3 table table-hover']/tbody/tr/td[text()='Paid']");
    By onePayout = By.xpath("//thead/tr/th[normalize-space()='Payout ID']/../../following-sibling::tbody//tr[1]//td[1]");
    String payoutRecode = "//h2[contains(text(),'CODE')]";
    By btn_threeDotPayout = By.xpath("//thead/tr/th[normalize-space()='Payout ID']/../../following-sibling::tbody//tr[1]//td[7]//*[local-name()='svg' and @data-icon='ellipsis-vertical']");
    By viewDropDownOption = By.xpath("//span[text()='View']");
    By downloadDropDownOption = By.xpath("//span[text()='Download']");
    By invoiceCode = By.xpath("//th[contains(text(),'Invoice')]/ancestor::table/tbody/tr[1]/td[4]");
    By invoiceDate = By.xpath("//th[contains(text(),'Invoice Date')]/ancestor::table/tbody/tr[1]/td[5]");
    String invoiceDetails = "//div[contains(text(),'DETAILS')]";
    String invoiceId = "//h2[contains(text(),'ID')]";
    By btn_downloadInvoice = By.xpath("//div[contains(@class,'justify-content-end col')]//*[name()='svg' and contains(@class,'mr-2')]");
    By btn_printInvoice = By.xpath("//div[contains(@class,'justify-content-end col')]//*[name()='svg' and contains(@class,'mr-5')]");
    By lbl_orderTableColumn = By.xpath("//table/thead/tr/th");
    String lbl_orderTableColumnName = "//table/thead/tr/th[COUNT]";
    String lbl_status = "//th[COUNT][text()='STATUS']";
    By customerName = By.xpath("//th[contains(text(),'Customer Name')]/ancestor::table/tbody/tr[1]/td[5]");
    String payDetails = "//span[contains(text(),'DETAILS')]";
    String payDetailsAmount = "//span[contains(text(),'DETAILS')]/../following-sibling::div[contains(text(),'$')]";
    By paymentProcessingDetailAmount = By.xpath("//div[contains(text(),'Payments Processing')]/following-sibling::div[contains(text(),'$')]");
    By paymentProcessingDetail = By.xpath("//div[contains(text(),'Payments Processing')]");
    By pastDueDetailsAmount = By.xpath("//span[contains(text(),'Past Due')]/preceding-sibling::span[contains(text(),'$')]");
    By lbl_payInvoiceTableColumn = By.xpath("//table/thead/tr/th[contains(text(),'Invoice ID')]/parent::tr/th");
    String lbl_payInvoiceTableColumnName = "//table/thead/tr/th[contains(text(),'Invoice ID')]/parent::tr/th[COUNT]";
    String lbl_payInvoiceDate = "//table/thead/tr/th[contains(text(),'Invoice ID')]/ancestor::table/tbody/tr[ROW]/td[COUNT]";
    By cutAndDryPayToggleStable = By.xpath("//div[contains(text(), 'Cut+Dry Pay')]/following-sibling::div//div[@class='react-switch-bg']/following-sibling::div[@class='react-switch-handle']");
    By cutAndDryPayToggleStable1 = By.xpath("//div[contains(text(), 'Cut+Dry Pay')]/following-sibling::div//div[@class='react-switch-bg']/following-sibling::div[@class='react-switch-handle']/parent::div/div[1]");
    By creditMemoDisplay = By.xpath("//div[text()='Credit Memos (0)']");
    By btn_batchOperation = By.xpath("//button[text()='Batch Operations']");
    String lbl_batchOperation = "//button[text()='Batch Operations']/following-sibling::div/a[contains(text(),'OPTION')]";
    By invoiceFilterDropDown = By.xpath("//div[text()='Customer']/following-sibling::div/div/div//*//input");
    By invoiceFilterOption = By.xpath("(//div[text()='Customer']/following-sibling::div/div/div)[1]/../following-sibling::div");
    By lbl_invoiceDateColumn = By.xpath("//th[contains(text(),'Invoice Date')]");
    By lbl_invoiceDateArrowUp = By.xpath("//th[contains(text(),'Invoice Date')]/*[name()='svg' and contains(@data-icon, 'arrow-up')]");


    public boolean isPaymentStatusCorrect(String expectedPaymentStatus) {
        int numberOfRows = distributorUI.getRowCount(table_paymentInitiated);
        System.out.println("There are " + numberOfRows + " of rows");

        for (int i = 1; i <= numberOfRows; i++) { // Ensure proper loop iteration
            By paymentStatusColumn = By.xpath(String.format(paymentStatusRow, i));
            String paymentStatus = distributorUI.getText(paymentStatusColumn);

            System.out.println("The payment status for row " + i + " is: " + paymentStatus);

            if (!paymentStatus.equals(expectedPaymentStatus) && !paymentStatus.equals("Failed")) {
                return false;
            }
        }
        return true;
    }

    public boolean isTimestampInRange(String startMonth, String startDate, String startYear,
                                      String endMonth, String endDate, String endYear) {
        try {
            // Retrieve the timestamp text and sanitize it
            String rawTimestamp = distributorUI.getText(timestampFirstRow).trim();
            System.out.println("Raw timestamp: " + rawTimestamp);

            // Sanitize the timestamp by removing AM/PM if a 24-hour format is used
            String sanitizedTimestamp = rawTimestamp.replace(" AM", "").replace(" PM", "");

            // Formatters for parsing
            DateTimeFormatter timestampFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm"); // 24-hour format
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d MMMM yyyy");

            // Parse the start and end dates
            LocalDate startDateObj = LocalDate.parse(startDate + " " + startMonth + " " + startYear, dateFormatter);
            LocalDate endDateObj = LocalDate.parse(endDate + " " + endMonth + " " + endYear, dateFormatter);

            // Convert the start and end dates to LocalDateTime for comparison
            LocalDateTime startDateTime = startDateObj.atStartOfDay(); // Start of the day for start date
            LocalDateTime endDateTime = endDateObj.atTime(23, 59, 59); // End of the day for end date

            // Parse the input timestamp
            LocalDateTime targetTimestamp = LocalDateTime.parse(sanitizedTimestamp, timestampFormatter);

            // Check if the timestamp is within the range
            boolean isInRange = (targetTimestamp.isEqual(startDateTime) || targetTimestamp.isAfter(startDateTime)) &&
                    (targetTimestamp.isEqual(endDateTime) || targetTimestamp.isBefore(endDateTime));

            return isInRange;

        } catch (Exception e) {
            System.err.println("Error while parsing timestamp or comparing dates: " + e.getMessage());
            return false;
        }
    }

    private String getDateSuffix(String date) {
        int day = Integer.parseInt(date);
        if (day >= 11 && day <= 13) {
            return "th"; // Special case for 11th, 12th, 13th
        }
        switch (day % 10) {
            case 1: return "st";
            case 2: return "nd";
            case 3: return "rd";
            default: return "th";
        }
    }

    public void clickBtnNextMonth(){
        distributorUI.click(btn_nextMonth);
    }

    public void clickBtnPreviousMonth(){
        distributorUI.click(btn_previousMonth);
    }

    public void selectStartDate(String day, String month, String date, String year) {
        String suffix = getDateSuffix(date);
        String formattedDate = String.format(datePicker, day, month, date, suffix, year);
        By startDate = By.xpath(formattedDate);
        int maxAttempts = 12;
        boolean dateFound = false;

        for (int i = 0; i < maxAttempts; i++) {
            if (distributorUI.isDisplayed(startDate,2)) {
                distributorUI.click(startDate);
                dateFound = true;
                break;
            }

            if (i < 12) {
                clickBtnPreviousMonth(); // Try navigating backward in the first few attempts
            } else {
                clickBtnNextMonth(); // Switch to navigating forward
            }
        }

        if (!dateFound) {
            throw new RuntimeException("Start date could not be found within the allowed attempts.");
        }
    }

    public void selectEndDate(String day, String month, String date, String year) {
        String suffix = getDateSuffix(date);
        String formattedDate = String.format(datePicker, day, month, date, suffix, year);
        By endDate = By.xpath(formattedDate);
        int maxAttempts = 12; //Max attempts
        boolean dateFound = false;

        for (int i = 0; i < maxAttempts; i++) {
            if (distributorUI.isDisplayed(endDate,2)) {
                distributorUI.click(endDate);
                dateFound = true;
                break;
            }

            if (i < 12) {
                clickBtnNextMonth(); // Try navigating forward in the first few attempts
            } else {
                clickBtnPreviousMonth(); // Switch to navigating backward
            }
        }

        if (!dateFound) {
            throw new RuntimeException("End date could not be found within the allowed attempts.");
        }
    }


    public void clickDateRangeSelector(){
        distributorUI.click(dateRange_Pay);
    }

    public void clearDateRangeSelector(){
        distributorUI.clear(dateRange_Pay);
    }

    public void selectOptionPayoutStatusDropdown(String paymentStatus){
        By dropdownOption = By.xpath(option_payoutStatusDropdown.replace("OPTION", paymentStatus));
        distributorUI.click(dropdownOption);
    }

    public void click_payoutStatusDropdown(){
        distributorUI.click(dropDown_payoutStatus);
    }

    public boolean isNoResultTextDisplayed(){
        return distributorUI.isDisplayed(txt_noResultsFound);
    }

    public boolean isCustomerNameCorrect(String expectedCustomerName){
        String customerName = distributorUI.getText(customerNameFirstRow);
        return expectedCustomerName.equals(customerName);
    }

    public void click_customerDropdown(){
        distributorUI.click(dropDown_customer);
    }

    public void selectOptionPaymentStatusDropdown(String paymentStatus){
        By dropdownOption = By.xpath(option_paymentStatusDropdown.replace("OPTION", paymentStatus));
        distributorUI.click(dropdownOption);
    }

    public void click_paymentStatusDropdown(){
        distributorUI.click(dropDown_paymentStatus);
    }

    public void selectOptionCustomerDropdown(String customerOption){
        By dropdownOption = By.xpath(option_customerDropdown.replace("OPTION", customerOption));
        distributorUI.click(dropdownOption);
    }


    public boolean isPayTextDisplayed(){
        try {
            distributorUI.waitForVisibility(txt_pay);
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(txt_pay);
    }
    public void clickOnInvoices() {
        distributorUI.waitForVisibility(tb_invoices);
        distributorUI.click(tb_invoices);
    }
    public void clickOnPaymentsInit() {
        distributorUI.waitForVisibility(tb_paymentsInit);
        distributorUI.click(tb_paymentsInit);
    }
    public void clickOnPayouts() {
        distributorUI.waitForVisibility(tb_payouts);
        distributorUI.click(tb_payouts);
    }
    public boolean isCustomerBtnSelected(){
        distributorUI.waitForVisibility(txt_contactDetails);
        return distributorUI.isDisplayed(txt_contactDetails);
    }
    public boolean isInvoicesBtnSelected() throws InterruptedException {
        distributorUI.waitForVisibility(txt_invoiceID);
        By lbl_invoiceRecord = By.xpath(lbl_invoiceRecordID.replace("ROW_COUNT", "1"));
        distributorUI.waitForClickability(lbl_invoiceRecord);
        distributorUI.waitForCustom(3000);
        return distributorUI.isDisplayed(txt_invoiceID);
    }
    public boolean isPaymentsInitBtnSelected(){
        distributorUI.waitForVisibility(txt_paymentID);
        return distributorUI.isDisplayed(txt_paymentID);
    }
    public boolean isPayoutBtnSelected(){
        distributorUI.waitForVisibility(txt_payoutID);
        return distributorUI.isDisplayed(txt_payoutID);
    }
    public void clickOnSearchCustomers(){
        distributorUI.click(searchCustomers);
    }

    public void typeOnSearchCustomers(String customer) throws InterruptedException {
        distributorUI.clear(searchCustomers);
        distributorUI.waitForCustom(1000);
        distributorUI.sendKeys(searchCustomers, customer);
    }
    public boolean isSearchCustomerDisplayed(String customer){
        distributorUI.waitForVisibility(By.xpath(SearchedCustomer.replace("CUSTOMER",customer)));
        return distributorUI.isDisplayed(By.xpath(SearchedCustomer.replace("CUSTOMER",customer)));
    }
    public void clickThreeButton(){
        distributorUI.click(btn_threeDot);
    }
    public void selectStatement(String statement)throws InterruptedException{
        distributorUI.waitForVisibility(By.xpath(statementOption.replace("STATEMENT",statement)));
        distributorUI.click(By.xpath(statementOption.replace("STATEMENT",statement)));
        distributorUI.waitForCustom(2000);
    }
    public boolean isPaymentReminderDisplayed(){
        try {
            distributorUI.waitForVisibility(txt_paymentReminder);
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(txt_paymentReminder);
    }
    public void clickSendEmail(){
        distributorUI.click(btn_sendEmail);
    }
    public boolean isEmailStatementDisplayed(){
        try {
            distributorUI.waitForVisibility(txt_emailStatement);
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(txt_emailStatement);
    }
    public void clickSend(){
        distributorUI.click(btn_send);
    }
    public boolean isSuccessPopUpDisplayed(){
        try {
            distributorUI.waitForVisibility(txt_success);
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(txt_success);
    }
    public void clickOkPopUp(){
        distributorUI.click(btn_Ok);
    }
    public void clickSearchCustomer(String customer){
        distributorUI.waitForVisibility(By.xpath(SearchedCustomer.replace("CUSTOMER",customer)));
        distributorUI.click(By.xpath(SearchedCustomer.replace("CUSTOMER",customer)));
    }
    public boolean isCustomerInvoiceSectionDisplayed(String customer){
        String result = customer.substring(0, customer.indexOf("Test"));
        distributorUI.waitForVisibility(By.xpath(txt_customerInvoice.replace("CUSTOMER",result)));
        return distributorUI.isDisplayed(By.xpath(txt_customerInvoice.replace("CUSTOMER",result)));
    }

    // Invoice
    public void clickOnInvoiceXIcon() throws InterruptedException {
        distributorUI.waitForVisibility(btn_invoiceCustomerClearFilter);
        distributorUI.click(btn_invoiceCustomerClearFilter);
        distributorUI.waitForCustom(3000);
    }

    public boolean isEmptyInvoiceMsgDisplayed(){
        distributorUI.waitForVisibility(lbl_emptyInvoiceMsg);
        return distributorUI.isDisplayed(lbl_emptyInvoiceMsg);
    }

    public void selectInvoiceCustomerViaFilter(String name) throws InterruptedException {
        distributorUI.click(dd_invoiceCustomerFilter);
        By lbl_invoiceCustomerName = By.xpath(sel_invoiceCustomerFilter.replace("CUSTOMER", name));
        distributorUI.scrollToElement(lbl_invoiceCustomerName);
        distributorUI.waitForVisibility(lbl_invoiceCustomerName);
        distributorUI.click(lbl_invoiceCustomerName);
        distributorUI.waitForCustom(3000);
    }

    public void selectInvoiceStatusViaFilter(String status) throws InterruptedException {
        distributorUI.click(dd_invoiceStatusFilter);
        By lbl_invoiceStatus = By.xpath(sel_invoiceStatusFilter.replace("STATUS", status));
        distributorUI.waitForVisibility(lbl_invoiceStatus);
        distributorUI.click(lbl_invoiceStatus);
        distributorUI.waitForCustom(3000);
    }

    public void selectInvoiceDateViaFilter(int startDayCount) throws InterruptedException {
        distributorUI.click(txt_invoiceDateFilter);
        String startDay = String.valueOf(startDayCount);
        By lbl_selectStartDate = By.xpath(dynamicToXPath.replace("DAY", startDay));
        distributorUI.waitForVisibility(lbl_selectStartDate);
        distributorUI.click(lbl_selectStartDate);
        distributorUI.waitForCustom(5000);

        /*String endDay = String.valueOf(endDayCount);
        By lbl_selectEndDate = By.xpath(dynamicToXPath.replace("DAY", endDay));*/
        distributorUI.waitForVisibility(toDayXPath);
        distributorUI.click(toDayXPath);
        distributorUI.waitForCustom(3000);
    }

    public void selectInvoiceAuthStatusViaFilter(String name) throws InterruptedException {
        distributorUI.click(dd_invoiceAuthStatusFilter);
        By lbl_invoiceAuthStatus = By.xpath(sel_invoiceAuthStatusFilter.replace("STATUS", name));
        distributorUI.waitForVisibility(lbl_invoiceAuthStatus);
        distributorUI.click(lbl_invoiceAuthStatus);
        distributorUI.waitForCustom(3000);
    }

    public void typeInvoiceIDViaFilter(String id) throws InterruptedException {
        distributorUI.clear(txt_invoiceIDFilter);
        distributorUI.waitForCustom(1000);
//        distributorUI.sendKeys(txt_invoiceIDFilter, id);
        distributorUI.sendKeysAndEnter(txt_invoiceIDFilter, id);
    }

    public void clickOnInvoiceBulkActionButton() {
        distributorUI.waitForVisibility(btn_invoiceBulkAction);
        distributorUI.click(btn_invoiceBulkAction);
    }

    public void selectInvoiceBulkOption(String option) {
        By lbl_options = By.xpath(lbl_bulkOption.replace("OPTION", option));
        distributorUI.waitForVisibility(lbl_options);
        distributorUI.click(lbl_options);
    }

    public boolean isPastDueInvoicePopupDisplayed(){
        distributorUI.waitForVisibility(lbl_pastDuePopupTitle);
        return distributorUI.isDisplayed(lbl_pastDuePopupTitle);
    }

    public boolean isMarkPaidInvoicePopupDisplayed(){
        distributorUI.waitForVisibility(lbl_markInvoicePaidPopupTitle);
        return distributorUI.isDisplayed(lbl_markInvoicePaidPopupTitle);
    }

    public boolean isInvoiceCaptureFundDisplayed(){
        distributorUI.waitForVisibility(lbl_invoiceCaptureFundTitle);
        return distributorUI.isDisplayed(lbl_invoiceCaptureFundTitle);
    }

    public boolean isErrorPopUpDisplayed() {
        distributorUI.waitForVisibility(txt_error);
        return distributorUI.isDisplayed(txt_error);
    }

    public boolean isInvoiceCaptureFundPopupDisplayed(){
        distributorUI.waitForVisibility(lbl_invoiceCaptureFundPopupTitle);
        return distributorUI.isDisplayed(lbl_invoiceCaptureFundPopupTitle);
    }

    public void clickOnMarkPaidInvoicePopupYesButton() throws InterruptedException {
        distributorUI.waitForVisibility(btn_markInvoiceYes);
        distributorUI.click(btn_markInvoiceYes);
        distributorUI.waitForCustom(3000);
    }

    public void clickOnInvoiceCaptureFundPayButton() throws InterruptedException {
        distributorUI.waitForVisibility(btn_invoiceCapturePay);
        distributorUI.click(btn_invoiceCapturePay);
        distributorUI.waitForCustom(3000);
    }

    public void clickOnInvoiceSendRemindersButton() throws InterruptedException {
        distributorUI.waitForVisibility(btn_invoiceSendReminders);
        distributorUI.click(btn_invoiceSendReminders);
        distributorUI.waitForCustom(3000);
    }

    public boolean isInvoiceEmailSentPopupDisplayed() {
        distributorUI.waitForVisibility(txt_invoiceEmailSent);
        return distributorUI.isDisplayed(txt_invoiceEmailSent);
    }

    public void clickOnInvoiceOkButton() {
        distributorUI.click(btn_invoiceOk);
    }

    public void clickOnInvoiceFirstRecord() {
        distributorUI.waitForVisibility(cbox_invoiceFirstRecord);
        distributorUI.click(cbox_invoiceFirstRecord);
    }

    public void clickOnInvoiceFirstRecordSetting() {
        distributorUI.waitForVisibility(cbox_invoiceFirstRecordSetting);
        distributorUI.click(cbox_invoiceFirstRecordSetting);
    }

    public void clickOnInvoiceRecord(int rowNo) {
        String row_count = String.valueOf(rowNo);
        By lbl_invoiceRecord = By.xpath(cbox_invoiceRecord.replace("ROW_COUNT", row_count));
        distributorUI.waitForVisibility(lbl_invoiceRecord);
        distributorUI.click(lbl_invoiceRecord);
    }

    public String getInvoiceRecordIDValue(int rowNo) throws InterruptedException {
        String row_count = String.valueOf(rowNo);
        By lbl_invoiceID = By.xpath(lbl_invoiceRecordID.replace("ROW_COUNT", row_count));
        distributorUI.waitForElementEnabledState(lbl_invoiceID,true);
        distributorUI.waitForCustom(3000);
        return distributorUI.getText(lbl_invoiceID);
    }

    public String getInvoiceRecordCustomerNameValue(int rowNo) throws InterruptedException {
        String row_count = String.valueOf(rowNo);
        By lbl_invoiceCustomerName = By.xpath(lbl_invoiceRecordCustomerName.replace("ROW_COUNT", row_count));
        distributorUI.waitForElementEnabledState(lbl_invoiceCustomerName,true);
        distributorUI.waitForCustom(3000);
        return distributorUI.getText(lbl_invoiceCustomerName);
    }

    public String getInvoiceRecordCustomerStatusValue(int rowNo) throws InterruptedException {
        String row_count = String.valueOf(rowNo);
        By lbl_invoiceCustomerStatus = By.xpath(lbl_invoiceRecordCustomerStatus.replace("ROW_COUNT", row_count));
        distributorUI.waitForElementEnabledState(lbl_invoiceCustomerStatus,true);
        distributorUI.waitForCustom(3000);
        return distributorUI.getText(lbl_invoiceCustomerStatus);
    }

    public boolean isInvoiceRecordCustomerStatusExist(String status) {
        try {
            int records_count = distributorUI.countElements(elements_invoiceRecords);
            for (int i = 1; i <= records_count; i++) {
                By lbl_invoiceCustomerStatus = By.xpath(lbl_invoiceRecordCustomerStatus.replace("ROW_COUNT", String.valueOf(i)));
                String statusTxt = distributorUI.getText(lbl_invoiceCustomerStatus).trim();
                if (statusTxt == null || statusTxt.isEmpty()) {
                    System.err.println("Status text is null or empty at row " + i);
                    return false;
                }
                if (!statusTxt.equalsIgnoreCase(status)) {
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            System.err.println("Element is not found");
            return false;
        }
    }

    public String getInvoiceRecordCodeValue(int rowNo) throws InterruptedException {
        String row_count = String.valueOf(rowNo);
        By lbl_invoiceCode = By.xpath(lbl_invoiceRecordCode.replace("ROW_COUNT", row_count));
        distributorUI.waitForElementEnabledState(lbl_invoiceCode,true);
        distributorUI.waitForCustom(3000);
        return distributorUI.getText(lbl_invoiceCode);
    }

    public String getInvoiceRecordDateValue(int rowNo) throws InterruptedException {
        /*String row_count = String.valueOf(rowNo);
        By lbl_invoiceDate = By.xpath(lbl_invoiceRecordDate.replace("ROW_COUNT", row_count));
        distributorUI.waitForElementEnabledState(lbl_invoiceDate,true);
        distributorUI.waitForCustom(3000);
        return distributorUI.getText(lbl_invoiceDate);*/


        String row_count = String.valueOf(rowNo);
        int totalColumnCount = distributorUI.countElements(lbl_payInvoiceTableColumn);

        for (int i = 1; i <= totalColumnCount; i++) {
            String columnName = distributorUI.getText(By.xpath(lbl_payInvoiceTableColumnName.replace("COUNT", String.valueOf(i))));
            if ("Invoice Date".equalsIgnoreCase(columnName)) {
                By invoiceDateLocator = By.xpath(
                        lbl_payInvoiceDate.replace("COUNT", String.valueOf(i)).replace("ROW", row_count)
                );
                return distributorUI.getText(invoiceDateLocator);

            }
        }
        return "Status not found";
    }

    public String getInvoiceRecordDueDateValue(int rowNo) throws InterruptedException {
        String row_count = String.valueOf(rowNo);
        By lbl_invoiceDueDate = By.xpath(lbl_invoiceRecordDueDate.replace("ROW_COUNT", row_count));
        distributorUI.waitForElementEnabledState(lbl_invoiceDueDate,true);
        distributorUI.waitForCustom(3000);
        return distributorUI.getText(lbl_invoiceDueDate);
    }

    public String getInvoiceRecordStatusValue(int rowNo) throws InterruptedException {
        String row_count = String.valueOf(rowNo);
        By lbl_invoiceStatus = By.xpath(lbl_invoiceRecordStatus.replace("ROW_COUNT", row_count));
        distributorUI.waitForElementEnabledState(lbl_invoiceStatus,true);
        distributorUI.waitForCustom(3000);
        return distributorUI.getText(lbl_invoiceStatus);
    }

    public String getInvoiceRecordAmountValue(int rowNo) throws InterruptedException {
        String row_count = String.valueOf(rowNo);
        By lbl_invoiceAmount = By.xpath(lbl_invoiceRecordAmount.replace("ROW_COUNT", row_count));
        distributorUI.waitForElementEnabledState(lbl_invoiceAmount,true);
        distributorUI.waitForCustom(3000);
        return distributorUI.getText(lbl_invoiceAmount);
    }

    public String getInvoiceRecordBalanceDueValue(int rowNo) throws InterruptedException {
        String row_count = String.valueOf(rowNo);
        By lbl_invoiceBalanceDue = By.xpath(lbl_invoiceRecordBalanceDue.replace("ROW_COUNT", row_count));
        distributorUI.waitForElementEnabledState(lbl_invoiceBalanceDue,true);
        distributorUI.waitForCustom(3000);
        return distributorUI.getText(lbl_invoiceBalanceDue);
    }

    public void clickOnInvoiceRecordSettingIcon(int rowNo) throws InterruptedException {
        String row_count = String.valueOf(rowNo);
        By lbl_invoiceSetting = By.xpath(btn_invoiceRecordSetting.replace("ROW_COUNT", row_count));
        distributorUI.waitForVisibility(lbl_invoiceSetting);
        distributorUI.click(lbl_invoiceSetting);
        distributorUI.waitForCustom(3000);
    }

    public void waitForRecordDisplayed(int rowNo) throws InterruptedException {
        String row_count = String.valueOf(rowNo);
        By lbl_invoiceRecord = By.xpath(lbl_invoiceRecordID.replace("ROW_COUNT", row_count));
        distributorUI.waitForVisibility(lbl_invoiceRecord);
    }

    public String getLastWorkingDate() {
        return distributorUI.getLastWorkingDayEST();
    }
    public void clickExportPayout(){
        distributorUI.click(btn_exportPayout);
    }
    public void clickPayoutStatusDropdown(){
        distributorUI.click(payOutStatusDropDown);
    }
    public void selectPayoutStatusDropdownOption(String paymentStatus){
        By dropdownOption = By.xpath(payoutStatusDropdownOption.replace("OPTION", paymentStatus));
        distributorUI.click(dropdownOption);
    }
    public void clickDateRangeFilter(){
        distributorUI.click(txt_payoutDateFilter);
    }
    public boolean isTimestampInDateRange(String startMonth, String startDate, String startYear,
                                      String endMonth, String endDate, String endYear) {
        try {
            // Retrieve the timestamp text and sanitize it
            String rawTimestamp = distributorUI.getText(timestampFirstRow).trim();
            System.out.println("Raw timestamp: " + rawTimestamp);

            // Sanitize the timestamp by removing AM/PM if present
            String sanitizedTimestamp = rawTimestamp.replace(" AM", "").replace(" PM", "");

            // Formatters for parsing
            DateTimeFormatter timestampFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy"); // Date only format
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d MMMM yyyy");

            // Parse the start and end dates
            LocalDate startDateObj = LocalDate.parse(startDate + " " + startMonth + " " + startYear, dateFormatter);
            LocalDate endDateObj = LocalDate.parse(endDate + " " + endMonth + " " + endYear, dateFormatter);

            // Parse the input timestamp
            LocalDate targetDate = LocalDate.parse(sanitizedTimestamp.split(" ")[0], timestampFormatter);

            // Check if the date is within the range
            boolean isInRange = (targetDate.isEqual(startDateObj) || targetDate.isAfter(startDateObj)) &&
                    (targetDate.isEqual(endDateObj) || targetDate.isBefore(endDateObj));

            return isInRange;

        } catch (Exception e) {
            System.err.println("Error while parsing date or comparing dates: " + e.getMessage());
            return false;
        }
    }
    public boolean isPayoutStatusDisplayed() {
        try {
            distributorUI.waitForVisibility(payoutStatus);
        } catch (Exception e) {
            return false;
        }
        return distributorUI.isDisplayed(payoutStatus);
    }
    public String getPayOutCode() throws InterruptedException {
        distributorUI.waitForElementEnabledState(onePayout,true);
        distributorUI.waitForCustom(3000);
        return distributorUI.getText(onePayout);
    }
    public void clickOnePayout(){
        distributorUI.click(onePayout);
    }
    public boolean isPayoutRecordDisplayed(String code) {
        distributorUI.waitForVisibility(By.xpath(payoutRecode.replace("CODE",code)));
        return distributorUI.isDisplayed(By.xpath(payoutRecode.replace("CODE",code)));
    }
    public void clickThreeDotButton(){
        distributorUI.click(btn_threeDotPayout);
    }
    public void clickViewPayout(){
        distributorUI.click(viewDropDownOption);
    }
    public void clickDownloadPayout(){
        distributorUI.click(downloadDropDownOption);
    }
    public String getInvoiceCode()throws InterruptedException{
       return distributorUI.getText(invoiceCode);
    }
    public String getInvoiceDate()throws InterruptedException{
        return distributorUI.getText(invoiceDate);
    }
    public void clickOneInvoice(){
        distributorUI.click(invoiceCode);
    }
    public boolean isInvoiceDisplayed(String id)throws InterruptedException {
        try {
            distributorUI.waitForVisibility(By.xpath(invoiceId.replace("ID",id)));
        } catch (Exception e) {
            return false;
        }
        return distributorUI.isDisplayed(By.xpath(invoiceId.replace("ID",id)));
    }
    public boolean isInvoiceDetailsDisplayed(String details)throws InterruptedException {
        try {
            distributorUI.waitForVisibility(By.xpath(invoiceDetails.replace("DETAILS",details)));
        } catch (Exception e) {
            return false;
        }
        return distributorUI.isDisplayed(By.xpath(invoiceDetails.replace("DETAILS",details)));
    }
    public void downloadInvoice()throws InterruptedException{
        distributorUI.click(btn_downloadInvoice);
    }
    public void clickPrintInvoice()throws InterruptedException{
        distributorUI.click(btn_printInvoice);
    }
    public String getOnFirstOrder(String status) {
        int totalColumnCount = distributorUI.countElements(lbl_orderTableColumn);

        for (int i = 1; i <= totalColumnCount; i++) {
            String columnName = distributorUI.getText(By.xpath(lbl_orderTableColumnName.replace("COUNT", String.valueOf(i))));
            if ("Code".equalsIgnoreCase(columnName)) {
                By creditRequestedLocator = By.xpath(
                        lbl_status.replace("COUNT", String.valueOf(i)).replace("STATUS", status)
                );
                return distributorUI.getText(creditRequestedLocator);

            }
        }
        return "Status not found";

    }
    public String getCustomerNamePaymentInitiated()throws InterruptedException{
        return distributorUI.getText(customerName);
    }
    public void clickOnePaymentInitiate(){
        distributorUI.click(customerName);
    }
    public boolean isPayDetailsDisplayed(String details){
        try {
            distributorUI.waitForVisibility(By.xpath(payDetails.replace("DETAILS", details)));
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(By.xpath(payDetails.replace("DETAILS", details)));
    }
    public boolean isPayDetailsAmountDisplayed(String amount){
        try {
            distributorUI.waitForVisibility(By.xpath(payDetailsAmount.replace("DETAILS", amount)));
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(By.xpath(payDetailsAmount.replace("DETAILS", amount)));
    }
    public boolean isPaymentProcessingDisplayed() {
        try {
            distributorUI.waitForVisibility(paymentProcessingDetail);
        } catch (Exception e) {
            return false;
        }
        return distributorUI.isDisplayed(paymentProcessingDetail);
    }
    public boolean isPaymentProcessingAmountDisplayed() {
        try {
            distributorUI.waitForVisibility(paymentProcessingDetailAmount);
        } catch (Exception e) {
            return false;
        }
        return distributorUI.isDisplayed(paymentProcessingDetailAmount);
    }
    public boolean isPastDueAmountDisplayed() {
        try {
            distributorUI.waitForVisibility(pastDueDetailsAmount);
        } catch (Exception e) {
            return false;
        }
        return distributorUI.isDisplayed(pastDueDetailsAmount);
    }
    public void clickCutAndDryPayToggle(boolean enable) {

        String handlePosition = distributorUI.getElement(cutAndDryPayToggleStable).getAttribute("style");
        boolean isEnabled = handlePosition.contains("translateX(66px)");

        if (enable && !isEnabled) {
            distributorUI.clickWithScrollAndHover(cutAndDryPayToggleStable1);
            distributorUI.click(btn_markInvoiceYes);
        } else if (!enable && isEnabled) {
            distributorUI.clickWithScrollAndHover(cutAndDryPayToggleStable1);
            distributorUI.click(btn_markInvoiceYes);
        }
    }
    public boolean isCutAndDryPayToggleEnabled() {
        return distributorUI.getElement(cutAndDryPayToggleStable)
                .getAttribute("style")
                .contains("translateX(66px)");
    }
    public boolean isCreditMemoDisplayed()throws InterruptedException{
        distributorUI.waitForCustom(3000);
        return distributorUI.isDisplayed(creditMemoDisplay);
    }
    public void clickOnInvoiceBatchOperationButton() {
        distributorUI.waitForVisibility(btn_batchOperation);
        distributorUI.click(btn_batchOperation);
    }
    public void selectTheBatchOperationOption(String option) {
        By lbl_options = By.xpath(lbl_batchOperation.replace("OPTION", option));
        distributorUI.waitForVisibility(lbl_options);
        distributorUI.click(lbl_options);
    }
    public void selectInvoiceCustomerCodeViaFilter(String code) throws InterruptedException {
        distributorUI.click(invoiceFilterDropDown);
        distributorUI.sendKeys(invoiceFilterDropDown,code);
        distributorUI.waitForCustom(3000);
    }
    public void clickInvoiceCodeCustomer(){
        distributorUI.waitForVisibility(invoiceFilterOption);
        distributorUI.click(invoiceFilterOption);
    }

    public void ensureOrderDateSortedDescending() throws InterruptedException {

        distributorUI.waitForVisibility(lbl_invoiceDateColumn);
        distributorUI.click(lbl_invoiceDateColumn);

        if (distributorUI.isDisplayed(lbl_invoiceDateArrowUp)) {
            distributorUI.click(lbl_invoiceDateColumn);
            distributorUI.waitForCustom(2000);
        }

    }




}
