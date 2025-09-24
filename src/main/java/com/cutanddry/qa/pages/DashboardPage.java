package com.cutanddry.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class DashboardPage extends LoginPage{
    By txt_dashboard = By.xpath("//li[contains(text(),'Dashboard')]");
    By btn_customers = By.xpath("//a[@data-tip='Customers']");
    By btn_boost = By.xpath("//a[@data-tip='Boost']");
    By btn_chat = By.xpath("//a[@data-tip='Chat']");
    By txt_dashboard_restaurant = By.xpath("//div[text()='Place Order']");
    By btn_restaurant_chat = By.xpath("//div[contains(text(),'Chat')]");
    By btn_catalog = By.xpath("//a[@data-tip='View Catalog']");
    By btn_settings = By.xpath("//a[@role='button' and contains(text(), 'Settings')]");
    By btn_orderSettings = By.xpath("//div[@arrowprops]//a[text()='Orders']");
    By btn_teamSettings = By.xpath("//div[@arrowprops]//a[text()='Team']");
    By btn_users = By.xpath("//a[@data-tip='Team']");
    By txt_home = By.xpath("//li[contains(text(),'Home')]");
    By btn_orderDesk = By.xpath("//a[contains(text(),'Order Desk')]");
    By btn_viewOrders = By.xpath("//a[contains(text(),'Orders')]");
    By txt_all = By.xpath("//div[text()='All Salespersons']");
    By txt_lastDays = By.xpath("(//div[text()='Last 30 Days'])[1]");
    By lbl_salespersonDropdown = By.xpath("(//div[contains(@class, 'css-1uccc91-singleValue')])[1]");
    By lbl_durationDropdown = By.xpath("(//div[contains(@class, 'css-1uccc91-singleValue')])[2]");
    String txt_salesperson = "//div[text()='NAME']";
    String txt_days = "//div[text()='DAYS']";
    By txt_teamStanding = By.xpath("//h4[contains(text(), 'Team Standings')]");
    By txt_totalOnlineOrders = By.xpath("//tr[td[contains(text(), 'Total')]]/td[3]");
    By txt_totalOnlineRev = By.xpath("//tr[td[contains(text(), 'Total')]]/td[4]");
    By txt_totalTimeSaved = By.xpath("//tr[td[contains(text(), 'Total')]]/td[5]");
    By btn_history = By.xpath("//a[@data-tip='Order History']");
    By btn_drafts  =By.xpath("//a[@data-tip='View Drafts']");
    By btn_track = By.xpath("//a[@role='button' and contains(text(), 'Track')]");
    By btn_trackResources = By.xpath("//div[@arrowprops]//a[text()='Resources']");
    By btn_trackRoutes = By.xpath("//div[@arrowprops]//a[text()='Routes']");
    By btn_trackMonitoring = By.xpath("//div[@arrowprops]//a[text()='Monitoring']");
    By btn_trackNotifications = By.xpath("//div[@arrowprops]//a[text()='Notifications']");
    By btn_pay  =By.xpath("//a[@data-tip='Payments']");
    By btn_reports  =By.xpath("//a[@data-tip='Reports']");
    By btn_trackSettings = By.xpath("//div[@arrowprops]//a[text()='Track']");
    By btn_profSettings = By.xpath("//div[@arrowprops]//a[text()='Profile']");
    By btn_companySettings = By.xpath("//div[@arrowprops]//a[text()='Company']");
    By btn_billingSettings = By.xpath("//div[@arrowprops]//a[text()='Billing']");
    By btn_support = By.xpath("//a[@data-tip='Support']");
    By btn_tracker  =By.xpath("//a[@data-tip='Tracker']");
    By btn_endlAisle  =By.xpath("//a[@data-tip='Endless Aisle']");
    By btn_creditReq  =By.xpath("//a[@data-tip='Credit Requests']");
    By btn_showcase = By.xpath("//a[contains(@data-tip,'Cut+Dry Product Showcase')]");
    By btn_adsSettings = By.xpath("//div[@arrowprops]//a[text()='Influence']");
    By btn_paySettings = By.xpath("//div[@arrowprops]//a[text()='Pay']");
    By btn_credit_requests = By.xpath("//a[@data-for='sideNavSupplierMenuOptions' and @href='/credit-requests']");
    By btn_placeOrder = By.xpath("//div[contains(text(), 'Independent Foods Co')]");
    By btn_hayes = By.xpath("//div[contains(text(), 'Hayes')]");
    By txt_OrderGuide = By.xpath("//span[contains(text(), 'Order Guide')]");
    By btn_TestAutomation = By.xpath("//div[contains(text(), 'Test_Automation') and contains(@class, 'w-100')]");
    By txt_draftOrder = By.xpath("//div[contains(@class, 'text-center') and contains(text(), 'continue your previous draft order')]");
    By btn_noDraftOrder = By.xpath("//span[text()='×']");
    By ordersTab = By.xpath("//div[text()='Order']");
    By btn_company = By.xpath("//span[@class='_5h4pkd' and text()='Company:']");
    By txt_independant_food_co = By.xpath("//a[contains(text(), 'Independent Foods Co')]");
    static By wordAfterCompanyLocator = By.xpath("//span[text()='Company:']/following-sibling::span");
    static By dropdownToggle = By.xpath("//span[text()='Company:']/ancestor::button[contains(@class, 'dropdown-toggle')]");
    static By independentFoodOption = By.xpath("//a[text()='Independent Foods Co']");
    By btn_PlaceOrder = By.xpath("(//button[text()='Place Order'])[1]");
    By approvalsBtn = By.xpath("//a[contains(@data-tip,'Approvals')]");
    By locationFilter = By.xpath("(//div[text()='Place Order']/following-sibling::div//div)[1]");
    By locationOption = By.xpath("//div[text()='Place Order']/following-sibling::*//div[contains(@id,'react-select') and contains(text(), 'All Locations')]");
    By btn_order = By.xpath("//a[@data-tip='Place Order']");
    By orderIndicator = By.xpath("//a[contains(text(),'Order Desk')]/div/span");
    By txt_endlessAisle  =By.xpath("//div[text()='Endless Aisle Catalog']");
    By btn_dashboard = By.xpath("//a[@data-tip='Dashboard']");
    String getCustomerBaseValue = "(//*[name()='tspan' and contains(., 'CUSTOMER')]/following-sibling::*[name()='tspan'])[1]";
    
    By lbl_orderGuideChangesTitle = By.xpath("//h4[text()='Order Guide Changes']");
    By svg_orderGuideChangesTooltip = By.xpath("//h4[text()='Order Guide Changes']/following-sibling::svg[1]");
    By section_orderGuideChangesData = By.xpath("//div[@devinid='34']");
    By lbl_orderGuideChangesDescription = By.xpath("//h4[text()='Order Guide Changes']/following-sibling::*[contains(text(), 'Items added or removed')]");
    String xpath_orderGuideChangeItem = "//h4[text()='Order Guide Changes']/parent::div//div[contains(text(), 'Added') or contains(text(), 'Removed') or contains(text(), 'Item')]";


    public boolean isDashboardTextDisplayed(){
        try {
            distributorUI.waitForVisibility(txt_dashboard);
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(txt_dashboard);
    }
    public void clickOnCustomers(){
        distributorUI.click(btn_customers);
    }
    public void clickOnBoost(){
        distributorUI.click(btn_boost);
    }
    public void clickOnChat(){
        distributorUI.click(btn_chat);
    }
    public boolean isRestaurantDashboardTextDisplayed(){
        try {
            distributorUI.waitForVisibility(txt_dashboard_restaurant);
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(txt_dashboard_restaurant);
    }
    public void clickOnRestaurantChat(){
        distributorUI.click(btn_restaurant_chat);
    }
    public void clickOnCatalog(){
        distributorUI.click(btn_catalog);
    }
    public void clickOnOrderSettings(){
        distributorUI.scrollToElement(btn_settings);
        distributorUI.clickUsingJavaScript(btn_settings);
        distributorUI.hoverOverElement(btn_orderSettings);
        distributorUI.clickWithFallback(btn_orderSettings);
    }
    public void clickOnTeamSettings(){
        distributorUI.scrollToElement(btn_settings);
        distributorUI.clickUsingJavaScript(btn_settings);
        distributorUI.hoverOverElement(btn_teamSettings);
        distributorUI.clickWithFallback(btn_teamSettings);
    }
    public boolean isWhiteLabelDashboardTextDisplayed(){
        try {
            distributorUI.waitForVisibility(txt_home);
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(txt_home);
    }
    public void clickOnUsers(){
        distributorUI.waitForVisibility(btn_users);
        distributorUI.clickWithFallback(btn_users);
    }
    public void clickOnOrders(){
        distributorUI.clickWithFallback(btn_viewOrders);
    }
    public void clickOnOrderDesk(){
        distributorUI.clickWithFallback(btn_orderDesk);
    }
    public boolean isDashboardDefaultValuesDisplayed(){
        try {
            distributorUI.isDisplayed(txt_all);
            distributorUI.isDisplayed(txt_lastDays);
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(txt_all) && distributorUI.isDisplayed(txt_lastDays);
    }
    public void selectSalesperson(String name){
        distributorUI.click(lbl_salespersonDropdown);
        distributorUI.waitForVisibility(By.xpath(txt_salesperson.replace("NAME", name)));
        distributorUI.clickWithFallback(By.xpath(txt_salesperson.replace("NAME", name)));
    }
    public void selectDuration(String days){
        distributorUI.click(lbl_durationDropdown);
        distributorUI.waitForVisibility(By.xpath(txt_days.replace("DAYS", days)));
        distributorUI.click(By.xpath(txt_days.replace("DAYS", days)));
    }
    public boolean isDashboardSalespersonChanged(String name){
        try {
            distributorUI.isDisplayed(By.xpath(txt_salesperson.replace("NAME", name)));
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(By.xpath(txt_salesperson.replace("NAME", name)));
    }
    public boolean isDashboardDurationChanged(String days){
        try {
            distributorUI.isDisplayed(By.xpath(txt_days.replace("DAYS", days)));
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(By.xpath(txt_days.replace("DAYS", days)));
    }
    public boolean isTeamStandingsDisplayed(){
        try {
            distributorUI.waitForVisibility(txt_teamStanding);
            distributorUI.isDisplayed(txt_teamStanding);
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(txt_teamStanding);
    }
    public String[] getTotalSumDisplayed(){
        String[] totals = new String[3];

        totals[0] = distributorUI.getText(txt_totalOnlineOrders);
        totals[1] = distributorUI.getText(txt_totalOnlineRev);
        totals[2] = distributorUI.getText(txt_totalTimeSaved);

        return totals;
    }
    public void clickOnHistory(){
        distributorUI.clickWithFallback(btn_history);
    }
    public void clickOnDrafts(){
        distributorUI.clickWithFallback(btn_drafts);
    }

    public void clickOnTrackResources(){
        distributorUI.scrollToElement(btn_track);
        distributorUI.clickUsingJavaScript(btn_track);
        distributorUI.hoverOverElement(btn_trackResources);
        distributorUI.clickWithFallback(btn_trackResources);
    }
    public void clickOnTrackRoutes(){
        distributorUI.scrollToElement(btn_track);
        distributorUI.clickUsingJavaScript(btn_track);
        distributorUI.hoverOverElement(btn_trackRoutes);
        distributorUI.clickWithFallback(btn_trackRoutes);
    }
    public void clickOnTrackMonitoring(){
        distributorUI.scrollToElement(btn_track);
        distributorUI.clickUsingJavaScript(btn_track);
        distributorUI.hoverOverElement(btn_trackMonitoring);
        distributorUI.clickWithFallback(btn_trackMonitoring);
    }
    public void clickOnTrackNotifications(){
        distributorUI.scrollToElement(btn_track);
        distributorUI.clickUsingJavaScript(btn_track);
        distributorUI.hoverOverElement(btn_trackNotifications);
        distributorUI.clickWithFallback(btn_trackNotifications);
    }
    public void clickOnPay(){
        distributorUI.clickWithFallback(btn_pay);
    }
    public void clickOnReports(){
        distributorUI.clickWithFallback(btn_reports);
    }
    public void clickOnBillingSettings(){
        distributorUI.scrollToElement(btn_settings);
        distributorUI.clickUsingJavaScript(btn_settings);
        distributorUI.hoverOverElement(btn_billingSettings);
        distributorUI.clickWithFallback(btn_billingSettings);
    }
    public void clickOnCompanySettings(){
        distributorUI.scrollToElement(btn_settings);
        distributorUI.clickUsingJavaScript(btn_settings);
        distributorUI.hoverOverElement(btn_companySettings);
        distributorUI.clickWithFallback(btn_companySettings);
    }
    public void clickOnTrackSettings(){
        distributorUI.scrollToElement(btn_settings);
        distributorUI.clickUsingJavaScript(btn_settings);
        distributorUI.hoverOverElement(btn_trackSettings);
        distributorUI.clickWithFallback(btn_trackSettings);
    }
    public void clickOnProfileSettings(){
        distributorUI.scrollToElement(btn_settings);
        distributorUI.clickUsingJavaScript(btn_settings);
        distributorUI.hoverOverElement(btn_profSettings);
        distributorUI.clickWithFallback(btn_profSettings);
    }
    public void clickOnAdsSettings(){
        distributorUI.scrollToElement(btn_settings);
        distributorUI.clickUsingJavaScript(btn_settings);
        distributorUI.hoverOverElement(btn_adsSettings);
        distributorUI.clickWithFallback(btn_adsSettings);
    }
    public void clickOnPaySettings(){
        distributorUI.scrollToElement(btn_settings);
        distributorUI.clickUsingJavaScript(btn_settings);
        distributorUI.hoverOverElement(btn_paySettings);
        distributorUI.clickWithFallback(btn_paySettings);
    }
    public void clickOnSupport(){
        String url = distributorUI.getText(btn_support,"href");
        distributorUI.navigateToURL(url);
    }
    public void clickOnTracker(){
        distributorUI.click(btn_tracker);
    }
    public void clickOnEndlessAisle(){
        distributorUI.click(btn_endlAisle);
    }
    public void clickOnCreditRequest(){
        distributorUI.click(btn_creditReq);
    }
    public void clickOnShowCase(){
        distributorUI.click(btn_showcase);
    }
    public void clickOnCreditRequests(){
        distributorUI.waitForElementEnabledState(btn_credit_requests,true);
        distributorUI.clickWithFallback(btn_credit_requests);}
    public void clickOnPlaceOrder() {
        distributorUI.click(btn_placeOrder);}
    public void clickOnHayes() {
        distributorUI.click(btn_hayes);}
    public boolean isOrderGuideTextDisplayed(){
        return distributorUI.isDisplayed(txt_OrderGuide);
    }
    public boolean isTestAutomationPopupDisplayed(){
        try {
            return distributorUI.isDisplayed(btn_TestAutomation);
        } catch (Exception e){
            return false;
        }
    }
    public void clickOnTestAutomationPopup(){
        distributorUI.waitForClickability(btn_TestAutomation);
        distributorUI.click(btn_TestAutomation);
        distributorUI.waitForInvisibility(btn_TestAutomation);
    }

    public void navigateToWhiteLabelOrdersPage(){
        distributorUI.click(ordersTab);
    }

    public static String getTextAfterCompany() {
        distributorUI.waitForVisibility(wordAfterCompanyLocator);
        return distributorUI.getText(wordAfterCompanyLocator);
    }

    public static void selectIndependentFoodCo() {
        // Click the dropdown
        driver.findElement(dropdownToggle).click();
        System.out.println("Dropdown clicked.");

        // Wait for the 'Independent Foods Co' option to be visible and click it
        distributorUI.waitForVisibility(independentFoodOption);
        driver.findElement(independentFoodOption).click();

        System.out.println("Company name changed to 'Independent Foods Co'.");
    }

    public void clickOnPlaceOrderBtn(){
        distributorUI.click(btn_PlaceOrder);
    }

    public void clickOnApproval(){
        distributorUI.click(approvalsBtn);
    }
    public void clickLocationFilter(){
        distributorUI.click(locationFilter);
    }
    public void clickOnLocationOption() throws InterruptedException {
        distributorUI.waitForCustom(2000);
        distributorUI.waitForVisibility(locationOption);
        distributorUI.click(locationOption);
    }
    public void clickOnOrder() throws InterruptedException {
        distributorUI.click(btn_order);
        distributorUI.waitForCustom(2000);
    }
    public boolean isCustomerDisplayed(){
        return distributorUI.isDisplayed(btn_customers);
    }
    public boolean isOrderIndicatorDisplay(){
        return distributorUI.isDisplayed(orderIndicator);
    }
    public boolean isChatSectionDisplay()throws InterruptedException{
        return distributorUI.isDisplayed(btn_chat);
    }
    public boolean isEndlessAisleCatalogDisplay()throws InterruptedException{
        return distributorUI.isDisplayed(txt_endlessAisle);
    }
    public void clickOnDashboard(){
        distributorUI.click(btn_dashboard);
    }
    public String getCustomerValue(String customer) throws InterruptedException {
        distributorUI.waitForCustom(3000);
        String rawValue = distributorUI.getText(By.xpath(
                getCustomerBaseValue.replace("CUSTOMER", customer)
        )).trim();

        return rawValue.split(" ")[0].trim();
    }
    public void refreshDashBoardPage(){
        distributorUI.refreshPage();
    }

    public boolean isOrderGuideChangesModuleDisplayed() {
        try {
            distributorUI.waitForVisibility(lbl_orderGuideChangesTitle);
            return distributorUI.isDisplayed(lbl_orderGuideChangesTitle);
        } catch (Exception e) {
            return false;
        }
    }

    public void hoverOverOrderGuideChangesTooltip() {
        try {
            distributorUI.hoverOverElement(svg_orderGuideChangesTooltip);
        } catch (Exception e) {
            System.err.println("Failed to hover over Order Guide Changes tooltip: " + e.getMessage());
        }
    }

    public String getOrderGuideChangesTooltipText() {
        try {
            hoverOverOrderGuideChangesTooltip();
            distributorUI.waitForCustom(1000);
            WebElement tooltip = driver.findElement(By.xpath("//*[@role='tooltip' or contains(@class, 'tooltip')]"));
            return tooltip.getText();
        } catch (Exception e) {
            return "";
        }
    }

    public boolean isOrderGuideChangesDataSectionDisplayed() {
        try {
            return driver.findElements(section_orderGuideChangesData).size() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public int getOrderGuideChangesItemCount() {
        try {
            return driver.findElements(By.xpath(xpath_orderGuideChangeItem)).size();
        } catch (Exception e) {
            return 0;
        }
    }

    public boolean areDistributorPortalChangesExcluded() {
        try {
            distributorUI.waitForVisibility(section_orderGuideChangesData);
            return distributorUI.isDisplayed(section_orderGuideChangesData) && getOrderGuideChangesItemCount() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public String getOrderGuideChangesDescription() {
        try {
            distributorUI.waitForVisibility(lbl_orderGuideChangesDescription);
            return distributorUI.getText(lbl_orderGuideChangesDescription);
        } catch (Exception e) {
            return "";
        }
    }

}
