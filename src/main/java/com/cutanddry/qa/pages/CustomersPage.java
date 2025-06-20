package com.cutanddry.qa.pages;

import net.bytebuddy.asm.Advice;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class CustomersPage extends LoginPage {
    By tbx_searchCustomers = By.xpath("//input[@placeholder='Search Customers']");
    String btnOrderGuide = "//td[text()='CODE']/../td[8]//button";
    String btnNameOrderGuide = "//*[text()='NAME']/../../td[8]//button";
    By lbl_itemNameList = By.xpath("//td//span/div[@data-tip='View Product Details']");
    By lbl_itemDetails = By.xpath("//tbody/tr[2]");
    By btn_increaseQtyFirstRow = By.xpath("(//table/tbody/tr//*[local-name()='svg' and @data-icon='plus'])[1]");
    By btn_increaseQtySecondRowStable = By.xpath("(//table/tbody/tr//*[local-name()='svg' and @data-icon='plus'])[2]");
    By btn_increaseQtyFifthRow = By.xpath("(//table/tbody/tr//*[local-name()='svg' and @data-icon='plus'])[5]");
    By btn_decreaseQtyFirstRow = By.xpath("//tr[1]/td[8]/div/div/div/div[1]");
    By btn_increaseQtySecondRow = By.xpath("//tr[2]/td[8]/div/div/div//div[3]");
    By btn_decreaseQtySecondRow = By.xpath("//tr[2]/td[8]/div/div/div/div/div[1]");
    By btn_checkout = By.xpath("//button[text()='$']/../button[2]");
    By btn_catalog = By.xpath("//span[text()='Catalog']");
    By tbx_catalogSearch = By.xpath("//input[@placeholder='Search catalog...']");
//    String lbl_catalogSearchItemList = "//button[contains(@data-for,'tooltipundefined')]/ancestor::div[2]/following-sibling::div[2]/div/div[contains(text(),'NAME')]";
//    String lbl_catalogSearchItemList = "//button[contains(text(), 'Add to Cart')]/ancestor::div//*[contains(text(), 'NAME')]";
//    String lbl_catalogSearchItemList = "(//div[contains(@class,'card-deck')]//div[contains(text(),'NAME')])[last()]";
String lbl_catalogSearchItemList = "(//div[contains(@class,'card-deck')]//div[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), translate(\"NAME\", 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'))])[last()]";
    String lbl_catalogAddToCart = "((//div[contains(@class,'card-deck')]//div[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), translate(\"NAME\", 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'))])[last()]/ancestor::div[contains(@class,'card')])[last()]//button[@data-tip='Add to Order Guide']";
    By txt_thereWasAnError = By.xpath("//*[text()='There was an error, please try again.']");
//    String btn_addToCart = "//div[contains(@class, '_13kb1gk')]//div[text()= 'ITEMNAME']//ancestor::div[contains(@class, '_13kb1gk')]//div[@class='_btf6h0']//button[contains(@class, 'btn-outline-primary')]";
//    String btn_addToCart = "//*[contains(text(), 'ITEMNAME')]/ancestor::div[contains(@class, 'card')]//button[contains(text(), 'Add to Cart')]";
//    String btn_addToCart = "(//div[contains(@class,'card-deck')]//div[contains(text(),'ITEMNAME')])[last()]/ancestor::div[contains(@class, 'card')]//*[name()='svg' and @data-icon='plus']";
//    String btn_addToCart = "(//div[contains(@class,'card-deck')]//div[translate(normalize-space(text()), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz') = translate('ITEMNAME', 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz')])[last()]/ancestor::div[contains(@class, 'card')]//*[name()='svg' and @data-icon='plus']";
String btn_addToCart = "(//div[contains(@class,'card-deck')]//div[contains(translate(normalize-space(text()), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'ITEMNAME')])[last()]/ancestor::div[contains(@class, 'card')]//*[name()='svg' and @data-icon='plus']";
    By tbx_itemQuantityFirstRow = By.xpath("//tr[1]//td[8]//input");
    By lbl_itemPriceFirstRow = By.xpath("//tr[1]//td[7]/span");
    By lbl_itemPriceSecondRow = By.xpath("//tr[2]//td[7]//input");
    By btn_increaseQtyCatalogSearchValueOne = By.xpath("(//input[@type='number' and @value='1']/../following-sibling::div)[last()]");
    By btn_increaseQtyCatalogSearchValueTwo = By.xpath("(//input[@type='number' and @value='2']/../following-sibling::div)[last()]");
    By btn_decreaseQtyCatalogSearchValueOne = By.xpath("(//input[@type='number' and @value='1']/../preceding-sibling::div)[last()]");
    By btn_decreaseQtyCatalogSearchValueTwo = By.xpath("(//input[@type='number' and @value='2']/../preceding-sibling::div)[last()]");
    By btn_decreaseQtyCatalogSearchValueThree = By.xpath("(//input[@type='number' and @value='3']/../preceding-sibling::div)[last()]");
    By tbx_itemQuantityCatalogSearch = By.xpath("//input[@type='number']");
    By lbl_itemPriceSearchCatalogList = By.xpath("(//div[contains(., 'Artichoke') and not(contains(., '-24ct')) and not(contains(., 'Bottoms'))]//span[contains(text(),'$') and not(contains(text(),' ')) and not(@class='text-muted')])[last()]");
    By btn_decreaseQtyCartRowOne = By.xpath("//tr[2]/td//input/../preceding-sibling::div//*[@data-icon='minus']");
    By btn_increaseQtyCartRowOne = By.xpath("//tr[2]/td//input/../following-sibling::div");
    By tbx_itemQuantityCartRowOne = By.xpath("//tr[2]/td//input/");
    By lbl_itemPriceCartRowOne = By.xpath("//tr[2]/td//span[contains(text(),'$')]");
    By lbl_cartTotal = By.xpath("//td[text()='Total']/following-sibling::td");
    By lbl_cartTotalReview = By.xpath("//td[text()='Total:']/following-sibling::td");
    By btn_submitOrder = By.xpath("//button[contains(text(),'Submit Order')]");
    By btn_duplicateOrderYes = By.xpath("//h2[contains(text(),'Duplicate Order')]/../..//button[text()='Yes']");
    By lbl_thankYouForOrder = By.xpath("//*[contains(text(),'Thank you for your order!')]");
    By btn_print = By.xpath("//*[contains(text(),'Print Order Guide') and .//*[local-name()='svg' and @data-icon='cdPrinter']]");
    By lbl_printFriendlyOrderGuide = By.xpath("//h5[contains(text(), 'Print-Friendly Order Guide')]");
    By btn_downloadOrderGuide = By.xpath("//button[contains(text(), 'Download Order Guide')]");
    By tbx_orderGuideSearch = By.xpath("//input[@placeholder='Search order guide...']");
    By btn_create = By.xpath("//*[contains(text(),'Create New Order Guide') and .//*[local-name()='svg' and @data-icon='plus']]");
    By tbx_OrderGuideName = By.xpath("//input[@placeholder='Enter Name']");
    By btn_submitOrderGuide = By.xpath("//button[contains(text(), 'Submit')]");
    By btn_addFromCatalog = By.xpath("//div[contains(text(), 'Add from Catalog')]");
    By btn_uploadFile = By.xpath("//button[contains(text(), 'Upload File')]");
    By btn_uploadFileOG = By.xpath("//button[contains(text(), 'Upload Order')]");
    By btn_addToOrderGuide = By.xpath("//button[@data-tip='Add to Order Guide']");
    By btn_closeEditorCatalog = By.xpath("//button[contains(text(), 'Close Editor')]");
    By btn_closeEditor = By.xpath("//a[contains(text(), 'Close Editor')]");
    By btn_removeFromOrderGuide = By.xpath("//button[@data-tip='Remove from Order Guide']");
    By upload_file = By.xpath("//input[@type='file']");
    By btn_next = By.xpath("//button[text()='Next']");
    By btn_confirm = By.xpath("//button[text()='Confirm']");
    By txt_orderGuideCreateSuccess = By.xpath("//h2[contains(text(), 'Order guide updated successfully')]");
    By btn_OK = By.xpath("//button[text()='OK']");
    String msg_banner = "//span[text()='TESTMESSAGE']";
    By lbl_productDetails = By.xpath("//span[text()='Product Details']");
    By lbl_topCategoryPicks = By.xpath("//div[text()='Top Category Picks']");
    String lbl_itemAdded = "//div[text()='Top Category Picks']//following-sibling::div//div[text()='CODE']";
    String lbl_searchedItem = "//div[text()='CODE']";
    String lbl_searchedItemStable = "//div[text()='CODE']/parent::div/div[1]";
    By section_compareSimilar = By.xpath("//div[text()='Compare Similar Items']");
    String lbl_recommendedForYouItem = "//div[contains(text(), 'Recommended for You')]//following-sibling::div//div[text()='CODE']";
    String lbl_recommendedBySalesRep = "//div[contains(text(), 'Recommended by')]//following-sibling::div//div[contains(text(), 'CODE')]";
    By section_dontForget = By.xpath("//div[text()=\"Don't Forget to Order\"]");
    By section_moreFromThisBrand = By.xpath("//div[contains(text(), 'More From')]");
    By btn_companyDropdown = By.xpath("//button[.//span[text()='Company:']]");
    By txt_companyDropdownText = By.xpath("//a[contains(text(), 'Independent Foods Co')]");
    By btn_edit = By.xpath("//*[local-name()='svg' and @data-icon='cdEdit']");
    By txt_editOrderGuide= By.xpath("//span[contains(text(), 'Edit Order Guide')]");
    By btn_moreOptions = By.xpath("//span[contains(text(), 'More Options')]");
    By btn_exportOrderGuide = By.xpath("//a[contains(text(), 'Export Order Guide (XLSX)')]");
    By btn_importOrderGuide = By.xpath("//a[contains(text(), 'Import Order Guide (XLSX)')]");
    By btn_uploadToOrder = By.xpath("//a[contains(text(), 'Upload to Order')]");
    By txt_reviewOrder = By.xpath("//div[text()='Review Order']");
    By txt_orderGuideUpdated = By.xpath("//h2[text()='Order guide updated successfully']");
//    By dropdown_SortOptions = By.xpath("//div[text()='Sort Items By:']/following::div[contains(@class, 'cd_themed_select__control')][1]");
    By dropdown_SortOptions = By.xpath("(//div[contains(text(),'Sort Items By')])[last()]/following-sibling::div/div");
    By dropdown_customOrder =  By.xpath("//div[contains(text(), 'Sort Items By:')]//following::div[contains(text(), 'Custom Order')]");
    By dropdown_lastOrdered = By.xpath("//div[contains(text(), 'Sort Items By:')]//following::div[contains(text(), 'Last Ordered')]");
    By dropdown_alphabetical = By.xpath("//div[contains(text(), 'Sort Items By:')]//following::div[contains(text(), 'Alphabetical (A-Z)')]");
    By dropdown_itemCategories = By.xpath("//div[contains(text(), 'Sort Items By:')]//following::div[contains(text(), 'Item Categories')]");
    By dropdown_itemCode = By.xpath("//div[contains(text(), 'Sort Items By:')]//following::div[contains(text(), 'Item Code')]");
    By txt_produce = By.xpath("(//div[starts-with(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'produce')])[last()]");
    By txt_firstItem = By.xpath("//div[translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz') = 'artichoke -24ct']");
    By txt_minOrderBanner = By.xpath("//div[contains(text(), 'Add a few more items worth') and contains(text(), 'to meet minimum order amount')]");
    By txt_popupAlertOrderMin = By.xpath("//h2[text()='Order Minimum Not Met']");
    By btn_previousDraftOrderNo = By.xpath("//div[contains(text(),'previous draft order')]/..//div[text()='No']");
    String txt_customerCode = "//td[text()='CODE']";
    By tb_orders = By.xpath("//a[text()='Orders' and @role='tab']");
    By txt_standingOrders = By.xpath("//div[text()='Standing Orders: ']");
    By created_StandingOrders = By.xpath("//div[contains(text(),'Set for ')]");
    By btn_createStandingOrders = By.xpath("//div[text()='Create']");
    By dropdown_delivery = By.xpath("//div[text()='Delivery:']/following-sibling::div//div[text()='Select Days...']");
    By dropdown_pickUp = By.xpath("//div[text()='Pickup Date:']/../following-sibling::div/div");
    String txt_deliveryDay = "//div[text()='DAY']/preceding-sibling::input[@type='checkbox']";
    String txt_deliveryLastDay = "(//div[contains(@class, 'cd_themed_select__option')]//input[@type='checkbox'])[last()]";
    String txt_deliveryLastBeforeDay = "(//div[contains(@class, 'cd_themed_select__option')]//input[@type='checkbox'])[last()-1]";
    String txt_pickUpLastDay = "//div[contains(@class, 'cd_themed_select__option')][last()]";
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
    String btn_PlusQtyFirstRowInDist = "//tbody/tr/td[COLUMN]/div/div/div//div[3]";
    By lbl_itemRowCount = By.xpath("//td[text()='Item']/parent::tr/td");
    String lbl_columnName = "//td[text()='Item']/parent::tr/td[COLUMN]";
    By btn_decreaseQtyFirstRowInDist = By.xpath("//tbody/tr[2]/td[6]/div/div/div/div[1]");
    By btn_checkoutCashCarry = By.xpath("//button[@data-tip='Click here to checkout']");
    By txt_foodServiceDistCenter = By.xpath("//span[text()='Food Service Distribution Centre']");
    By txt_retailDistCenter = By.xpath("//span[text()='Retail Distribution Centre']");
    String txt_orders = "(//div[contains(text(), 'Order Number #')])[NUM]";
    By btn_back = By.xpath("//button[contains(text(), 'Back')]");
    String SelectCustomerByCode = "//td[contains(text(),'CODE')]";
    By OrdersTabTxt = By.xpath("//a[contains(text(),'Orders') and @class='_1n4k2vi text-center nav-item nav-link']");
    By OrderIdTxt = By.xpath("//tr[contains(@href,'/ordersView/')][1]");
    By OrderDateSort = By.xpath("//span[contains(text(),'Order Date')]");
    By DeliveryDate = By.xpath("//span[contains(text(),'Fulfilled By')]");
    By OrderDateSortData = By.cssSelector("tr._du1frc td:nth-child(1)");
    By DeliveryDateSortData = By.cssSelector("tr._du1frc td:nth-child(2)");
    By txt_discountDisclaimerOrderReview = By.xpath("//div[contains(text(), 'Case discounts will be reflected on your invoice.')]");
    By txt_discountDisclaimerOrderDetails = By.xpath("//div[normalize-space() = '*Prices are subject to change. Weighed item prices are estimated. Case discounts will be reflected on your invoice.']");
    By lbl_firstRowOrderTab = By.xpath("//tr[contains(@href,'/ordersView/')][1]");
    By txt_southwest = By.xpath("//div[contains(text(),'Southwest Traders')]");
    By txt_substitutions = By.xpath("//div[contains(normalize-space(text()), 'Set a Substitute')]");
    By txt_substitutionsItem = By.xpath("//div[contains(normalize-space(text()), 'Item Substitution')]");
    By btn_saveSelection = By.xpath("//button[normalize-space(text())='Save Selection']");
    By btn_donotsubs = By.xpath("//div[normalize-space(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'))='do not substitute']");
    By txt_replacement = By.xpath("//div[contains(normalize-space(text()), 'If out of stock, sub with')]");
    By lbl_NotSelected = By.xpath("//*[contains(text(),'Not Selected')]");
    By lbl_doNotSubstitute = By.xpath("//*[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'do not substitute')]");
    String txt_item = "//*[contains(text(), 'CODE')]";
    By lbl_SubstituteItem = By.xpath("//div[contains(text(), 'Substitute with:')]");
    String txt_itemPercentage = "//span[contains(text(), 'CODE')]";
    By btn_increaseQtyFirstRowInCheckout = By.xpath("//tr[2]/td[4]/div/div/div/div/div[3]");
    By btn_decreaseQtyFirstRowInCheckout = By.xpath("//tr[2]/td[4]/div/div/div/div/div[1]");
    String txt_subItems = "(//div[contains(text(), 'Available Substitutes')]/following-sibling::div//div[contains(text(), '1 x $')])[NUM]";
    By subItemsCount = By.xpath("(//div[contains(@data-testid,'sliderList')])[last()]/div");

    By CustomerTxt = By.xpath("//h2[contains(text(),'Customers')]");
    By Test_AutomationOrderGuide = By.xpath("//div[@class='cd_themed_select__single-value css-1uccc91-singleValue' and contains(text(),'Independent Foods Co')]");
    By AutomationGuide = By.xpath("//div[contains(text(),'Test_Automation')]");
    By StockCountTxt = By.xpath("//span[@data-for='cd-label-tooltip' and contains(text(), 'Stock: 50 CS')]");
    By CustomerGroupTxt = By.xpath("//div[contains(text(),'Customer Group')]");
    By txt_outOfStock = By.xpath("//div[contains(text(), 'This item is currently out of stock and may not be shipped')]");
    By txt_unitInDist = By.xpath("//tbody/tr/td[3]/div/div/div/div");
    By txt_eachDropdownItem = By.xpath("//div[contains(@class, 'cd_themed_select__option') and text()='Each']");
    By txt_caseDropdownItem = By.xpath("//div[contains(@class, 'cd_themed_select__option') and text()='Case']");
    By btn_orderGuide = By.xpath("//button[text()='Order Guide']");
    By btn_close = By.xpath("//button[contains(@class, 'close')]/span[text()='×']");
    By btn_addSection = By.xpath("//a[contains(text(), 'Add Section')]");
    By txt_addSection = By.xpath("//div[contains(text(), 'Add Section Header')]");
    By tbx_section = By.xpath("//input[@placeholder='e.g. Dairy']");
    By btn_save = By.xpath("//button[normalize-space(text())='Save']");
    String txt_addedSection = "//div[contains(@data-rbd-draggable-id, 'item')]//div[contains(text(), 'NAME')]";
    By txt_lastID = By.xpath("//div[contains(@data-rbd-draggable-id, 'item-')][last()-2]");
    By txt_firstID = By.xpath("//div[contains(@data-rbd-draggable-id, 'item-')][1]");
    String txt_source = "//div[@data-rbd-draggable-id='ID']";
    String txt_section = "//div[contains(text(), 'NAME')]";
    String btn_editSection = "//div[contains(text(), 'NAME')]/following-sibling::div[@class='col-2 d-flex justify-content-end align-items-center']/*[local-name() = 'svg' and @data-icon='pencil']";
    By btn_delete = By.xpath("//button[contains(text(), 'Delete')]");
    By txt_editSection = By.xpath("//div[contains(text(), 'Edit Section Header')]");
    By txt_areYouSure = By.xpath("//h2[text()='Are you sure?']");
    By btn_deleteIcon = By.xpath("//*[local-name() = 'svg' and @data-icon='trash-alt']");
    By tb_boost = By.xpath("//a[text()='Boost' and @role='tab']");
    By tb_track = By.xpath("//a[text()='Track' and @role='tab']");
    By txt_customerSpecific = By.xpath("//div[contains(text(),'Customer-specific Broadcast')]");
    By txt_profile = By.xpath("//div[contains(text(),'Profile')]");
    By btn_editMessage = By.xpath("//button[contains(text(), 'Edit Message')]");
    By btn_clearMessage = By.xpath("//button[contains(text(), 'Clear Message')]");
    By btn_saveMessage = By.xpath("//button[contains(text(), 'Save Message')]");
    By btn_addItems = By.xpath("//button[contains(text(), 'Add Items')]");
    By txtArea = By.xpath("//div[contains(@class, 'col-lg-9')]//textarea");
//    By input_selectItem = By.xpath("//div[contains(text(),'Select...')]/following-sibling::div//input");
By input_selectItem = By.xpath("//div[contains(text(),'Search items by name or code')]/following-sibling::div//input");
    By btn_add = By.xpath("//button[contains(text(), 'Add')]");
    String btn_removeItem ="//div[text()='ITEMCODE']/following-sibling::div[2]/*";
    By EditCustomerGroupBtn = By.xpath("//div[contains(text(), 'Customer Group')]//following-sibling::div//div[@class='pl-0 col-sm-auto col-auto']//*[name()='svg' and contains(@data-icon, 'pen-to-square')]");
//    By CreateCutomerGroupTextField = By.xpath("//input[@id='react-select-6-input']");
    By CreateCutomerGroupTextField = By.xpath("//div[contains(text(), 'Customer Group')]//following-sibling::div/div/div/div/div");
    By Savebtn = By.xpath("//button[normalize-space(text())='Save']");
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
    By txt_pricePDP = By.xpath("//span[contains(text(), '$')]");
    By img_catalog = By.xpath("//img[contains(@class, 'card-img-top')]");
    String txt_catalogItem ="(//div[contains(text(), 'NAME')])[last()]";
//    By txt_namePDP = By.xpath("//div[contains(@class, 'd-flex align-items-center mont') and contains(@class, '_1wrelxt') and contains(@class, '_1vlidrf')]");
    String txt_namePDP = "(//div[translate(normalize-space(text()), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz') = 'ITEMNAME'])[last()]";
    By lbl_orders = By.xpath("//li[contains(text(),'Orders')]");
    By txt_allItems = By.xpath("//div[text()='All Items']");
    By txt_priceZero = By.xpath("//tbody//span[contains(text(), '$0.00')]");
    By minimumOrderOverlay = By.xpath("//h2[@id='swal2-title' and contains(text(),'Order Minimum Not Met')]");
    By catalogAccessEnableTxt = By.xpath("//div[@class='list-group-item']//div[text()='Enabled']");
    By catalogAccessDisableTxt = By.xpath("//div[@class='list-group-item']//div[text()='Disabled']");
    By catalogAccessEditBtn = By.xpath("//div[contains(text(), 'Catalog Access')]//following-sibling::div//div[@class='pl-0 col-sm-auto col-auto']//*[name()='svg' and contains(@data-icon, 'pen-to-square')]");
    By lbl_catalogAccessEnable = By.xpath("//div[contains(text(), 'Catalog Access')]//following-sibling::div//*[contains(text(),'Enabled')]");
    By catalogSectionInOrderGuide = By.xpath("//span[contains(text(),'Catalog')]");
    By catalogAccessDisableOption = By.xpath("//div[contains(text(),'Disabled')]");
    By catalogAccessEnableOption = By.xpath("//div[contains(text(),'Enabled')]");
    By orderApprovalTxt = By.xpath("//div[contains(text(),'Order Approval')]");
//    By orderApprovalEditBtn = By.xpath("//div[contains(text(), 'Order Approval')]//following-sibling::div//div[@class='pl-0 col-sm-auto col-auto']");
By orderApprovalEditBtn = By.xpath("//div[contains(text(), 'Order Approval')]/following-sibling::div//*[name()='svg' and contains(@data-icon, 'pen-to-square')]");
    By orderApprovalSettingsOverlayTxt = By.xpath("//div[contains(text(),'Order Approval Settings')]");
    By orderApprovalSettingsOverlayNewlyCreatedOGOptionsEnabledOrDisabled = By.xpath("//div[contains(@class,'themed_select') and contains(text(),'Enabled') or contains(text(),'Disabled')]");
    String orderApprovalSettingsOverlayOrderGuideTxt = "//div[contains(text(),'Existing Order Guide(s)')]/following-sibling::div//div[contains(text(),'ORDERGUIDE')]";
    By orderApprovalSettingsOverlayCloseBtn = By.xpath("//span[contains(text(),'×')]");
    String orderGuideOrderApprovalDisabledBtn = "//div[contains(text(),'ORDERGUIDE')]/../following-sibling::*//div[contains(@style, 'rgb(204, 204, 204)')]";
    String orderGuideOrderApprovalEnabledBtn = "//div[contains(text(),'ORDERGUIDE')]/../following-sibling::*//div[contains(@style, 'rgb(255, 255, 255)')]";
    String orderGuideOrderApprovalToggle = "//div[contains(text(),'ORDERGUIDE')]/../following-sibling::*//div[2]";
    By editExistingOrderTxt = By.xpath("//h2[contains(text(),'Edit Existing Order')]");
    By cancelBtn = By.xpath("//button[contains(text(),'Cancel')]");
    By btn_editSalesperson = By.xpath("//div[contains(text(),'Salesperson')]/following-sibling::div//*[contains(@data-icon,'pen-to-square')]");
    By btn_independentFoods = By.xpath("//div[contains(text(), 'Independent Foods Co')]");
    String itemNotFoundTxt = "//div[contains(translate(text(),'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ'),'ITEMCODE')]/following-sibling::div[contains(text(),'0 Results')]";
    String catalogCardAddToOGBtn = "//div[contains(text(),'ITEMCODE')]/../..//button[@data-tip='Add to Order Guide']";
    By lbl_AccountHoldsNone = By.xpath("//div[contains(text(), 'Account Holds')]//following-sibling::div//*[contains(text(),'None')]");
    By btn_editAccHold = By.xpath("//div[contains(text(),'Account Holds')]/..//*[local-name() = 'svg' and @data-icon='pen-to-square']");
    By dropdown_acc = By.xpath("//div[text()='Account Holds']/following-sibling::div//div[contains(@class, 'themed_select__value-container')]");
    By txt_hardHold = By.xpath("//div[contains(@class, 'themed_select__option') and  text()='Hard Hold']");
    By lbl_hardHold = By.xpath("//div[text()='Account Holds']/following-sibling::div//span[contains(@class, 'badge') and text()='Hard Hold']");
    By txt_none = By.xpath("//div[contains(@class, 'themed_select__option') and  text()='None']");
    By lbl_none = By.xpath("//div[text()='Account Holds']/following-sibling::div//span[contains(@class, 'badge') and text()='None']");
    By txt_hardHoldPopup = By.xpath("//h2[contains(text(), 'account is on hold')]");
    By txt_removeHold = By.xpath("//h2[contains(text(), 'Remove hold')]");
    By customer_Holds = By.xpath("//span[contains(text(),'Credit') or contains(text(),'Hard') or contains(text(),'General')]");
    By lbl_pickUp = By.xpath("//span[text()='Pickup/Will Call']");
    By lbl_mailDelivery = By.xpath("//span[text()='Mail Delivery']");
    By txt_customers = By.xpath("//h2[text()='Customers']");
    By btn_salesperson = By.xpath("(//div[contains(@class, 'themed_select__value-container') and contains(@class, 'themed_select__value-container--has-value')])[1]");
    By btn_salespersonOption = By.xpath("//div[@class='themed_select__option css-yt9ioa-option' and normalize-space(text())='Amir IFC']");
    String salespersonName = "//tr//td[6]//div[normalize-space(text())='SALESPERSON']";
    By btn_manageCustomers = By.xpath("//span[contains(text(), 'Manage Customers')]");
    By btn_exportCustomers = By.xpath("//a[contains(text(), 'Export customers(csv)')]");
    By txt_exportCustomersPopUp = By.xpath("//h4[contains(text(), 'Export \"Customer\" File')]");
    By txt_generatingReport = By.xpath("//h2[text()='Generating Report']");
    By btn_exportOrderGuidesCSV = By.xpath("//a[contains(text(), 'Export order guides(csv)')]");
    By txt_exportOrderGuidesCSVPopUp = By.xpath("//h4[contains(text(), 'Export  File')]");
    By btn_moreFilters = By.xpath("//span[contains(text(), 'More Filters')]");
    By txt_filterCustomersPopUp = By.xpath("//div[contains(text(), 'Filter Customers')]");
    By btn_signUpStatus = By.xpath("//label[text()='Sign-up Status']/following-sibling::div//div[contains(@class, '-control')]//div[contains(@class, '-indicatorContainer')]//*[name()='svg']");
    By btn_signUpOption = By.xpath("//div[@class='themed_select__option css-yt9ioa-option' and normalize-space(text())='Signed up']");
    By btn_apply =By.xpath("//button[@class='mx-auto btn btn-primary btn-block' and normalize-space(text())='Apply']");
    String signUpStatus = "//tr//td[4][text()='STATUS']";
    By btn_addNewCustomer = By.xpath("//a[contains(text(), 'Add new customer')]");
    By txt_addNewCustomer = By.xpath("//div[contains(text(), 'Add new customer')]");
    By txt_customerName = By.xpath("//label[contains(text(),'Customer Name*')]/following-sibling::input");
    By btn_continue = By.xpath("//button[contains(text(), 'Continue')]");
    By txt_city = By.xpath("//label[contains(text(),'City')]/following-sibling::input");
    By btn_createCustomer = By.xpath("//button[contains(text(), 'Create Customer')]");
    By txt_customerCreatedPopUp = By.xpath("//h2[contains(text(), 'Customer created.')]");
    By btn_closePopUp = By.xpath("//button[contains(text(), 'Close')]");
    String NewCustomerName = "//tr//td[3][text()='CUSTOMERNAME']";
    By btn_selectCustomer = By.xpath("(//div[@class= '_du1frc'])[2]");
    By lbl_firstRecord = By.xpath("//*[contains(text(),'Code')]/ancestor::table/tbody/tr[2]");
    By btn_bulkAction = By.xpath("//span[contains(text(), 'Bulk Actions')]");
    By btn_inviteUser = By.xpath("//a[contains(text(), 'Invite Users')]");
    By txt_inviteUserPopUp = By.xpath("//div[contains(text(), 'Invite Users')]");
    By btn_allOption = By.xpath("(//div[@class='my-2 _du1frc d-flex align-items-start' ])[1]");
    By btn_inviteEmail = By.xpath("//button[contains(text(), 'invite via email')]");
    By btn_moreOption = By.xpath("//button[contains(text(), 'More Options')]");
    By btn_editOrderGuide = By.xpath("//a[contains(text(), 'Edit Order Guide')]");
    By btn_previewCatalog = By.xpath("//a[contains(text(), 'Preview Catalog')]");
    By txt_orderGuide = By.xpath("//div[contains(text(), 'Kafe Layers')]");
    String businessName = "//td/following-sibling::td[contains(text(),'CUSTOMERID')]/following-sibling::td[1]";
    String txt_customerProfile = "//div[contains(@class, 'd-flex') and contains(text(), 'BUSINESSNAME')]";
    By btn_no = By.xpath("//div[contains(text(), 'No')]");
    By txt_previewCatalog = By.xpath("//div[contains(text(), 'Catalog Preview')]");
    By btn_chat = By.xpath("//button[contains(text(), 'Chat')]");
    By txt_chatArea = By.xpath("//input[@placeholder='Message...']");
    By btn_pauseStandingOrders = By.xpath("//div[contains(text(), 'Pause')]");
    By txt_pausedStandingOrders = By.xpath("//div[contains(text(),'(Paused) ')]");
    By btn_resumeStandingOrders = By.xpath("//div[contains(text(), 'Resume')]");
    By btn_threeDot = By.xpath("(//button[contains(@id, 'dropdown-basic')])[2]");
    By btn_printKitchenReceipt = By.xpath("//span[contains(text(), 'Print Kitchen Receipt')]");
    By btn_orderConfirmation = By.xpath("//span[contains(text(), 'Print Order Confirmation')]");
//    By btn_order = By.xpath("(//tr[contains(@class, '_du1frc _du1frc _qy4b979 py-3')])[1]");
    By btn_order = By.xpath("(//tr[contains(@class, '_du1frc _du1frc')])[1]");
    By txt_order = By.xpath("//h2[contains(text(), 'Order')]");
    By tb_drafts = By.xpath("//a[text()='Drafts' and @role='tab']");
//    By txt_draftStatus = By.xpath("//tr[2]//td[3][contains(text(), 'just now')]");
By txt_draftStatus = By.xpath("//tr[2]//td[3][contains(text(), 'seconds ago')]");
    By btn_deleteDraft = By.xpath("(//button[contains(@class, '_47hinf btn btn-link')])[1]");
    By lbl_stopDuration = By.xpath("//div[text()='Stop Duration']/following-sibling::div//input");
    By lbl_keyDropNum = By.xpath("//div[text()='Key Drop Number']/following-sibling::div//input");
    By lbl_deliveryNotes = By.xpath("//div[text()='Delivery Notes']/following-sibling::div/textarea");
    By lbl_DoorDesc = By.xpath("//div[text()='Door Description']/following-sibling::div//input");
    By txt_stopDuration = By.xpath("//div[text()='Stop Duration']/following-sibling::div//input");
    By lbl_orderGuide = By.xpath("//div[text()='Order Guide:']/following-sibling::div//div[@class='cd_themed_select__single-value css-1uccc91-singleValue']");
    By dropdown_testGuide1 =  By.xpath("//div[contains(text(), 'Order Guide:')]//following::div[contains(text(), 'Test_Guide_01')]");
    By dropdown_testAutomation =  By.xpath("//div[contains(text(), 'Order Guide:')]//following::div[contains(text(), 'Test_Automation')]");
//    By btn_editMargin = By.xpath("//td/div/button/*[local-name() = 'svg' and @data-icon='cdEdit']");
    By btn_editMargin = By.xpath("(//td//span//div[@data-tip='View Product Details']/ancestor::tr/td[6]//span)[1]");
    By btn_resetValues = By.xpath("//button[contains(text(), 'Reset Values')]");
    By btn_updateValues = By.xpath("//button[contains(text(), 'Update')]");
//    By lbl_margin = By.xpath("//div[text()='Margin ($)']/following-sibling::input");
By lbl_margin = By.xpath("//div[contains(text(),'Margin') and contains(text(),'$')]/following-sibling::input");
    By lbl_marginPercentage = By.xpath("//div[text()='Margin (%)']/following-sibling::input");
    String sel_customer ="//td[contains(text(),'CUSTOMERCODE')";
    By txt_cusName = By.xpath("(//div[contains(@class,'d-flex align-items-center')])[1]");
    By btn_editCusName = By.xpath("(//*[local-name() = 'svg' and @data-icon='pen-to-square'])[1]");
    By tbx_editCusName = By.xpath("//input[contains(@class,'form-control')]");
    By btn_saveEditCusName = By.xpath("//button[contains(@class,'btn-outline-primary btn-sm')]");
    By btn_editShipAddress = By.xpath("(//*[local-name() = 'svg' and @data-icon='pen-to-square'])[2]");
    By txt_editShipAddress = By.xpath("//div[contains(text(),'Edit Shipping address')]");
    By tbx_editStreet = By.xpath("//label[contains(text(),'Street Address')]/following-sibling::input");
    By tbx_editCity = By.xpath("//label[contains(text(),'City')]/following-sibling::input");
    By tbx_editState = By.xpath("//label[contains(text(),'State')]/following-sibling::input");
    By tbx_editZipCode = By.xpath("//label[contains(text(),'Zip Code')]/following-sibling::input");
    By btn_saveEditShipAddress = By.xpath("//button[contains(text(),'Save Changes')]");
    By btn_editNote = By.xpath("//div[contains(text(),'Notes')]/following-sibling::*[contains(@data-icon,'pen-to-square')]");
    By txtarea_editNote = By.xpath("(//textarea)[1]");
    By btn_saveNote = By.xpath("//button[contains(@class,'mx-2 my-2 btn btn-outline-primary btn-sm')]");
    By txt_assignSalesperson = By.xpath("//div[contains(text(),'Assign Salesperson')]");
//    By dropdown_assignSalesperson = By.xpath("((//*[local-name() = 'svg' and @class='css-19bqh2r']))[2]");
By dropdown_assignSalesperson = By.xpath("(//div[contains(@class,'themed_select__dropdown-indicator')])[last()]");
    By sel_salesperson = By.xpath("//div[contains(@class, 'themed_select__option') and contains(text(), 'Ali Loynachan')]");
    By txt_assignedSalesperson = By.xpath("//td[contains(text(),'Ali Loynachan')]");
    By btn_saveChanges = By.xpath("//button[contains(text(),'Save changes')]");
    By txt_cusProfSalesperson = By.xpath("//div[contains(@class,'_vjioml w-100 border') and contains(text(),'Ali Loynachan')]");
    By btn_removeSalesperson = By.xpath("(//td/*[contains(@data-icon,'trash-can')])[last()]");
    By txt_totalOrderValue = By.xpath("//div[contains(text(),'Total Order Value')]/following-sibling::div");
    By btn_orderGuideCusProf = By.xpath("//button[contains(text(),'Order Guide')]");
    By txt_OrderGuideCusName = By.xpath("//div[contains(@class,'_1hyqzayu mont')]");
    By sel_product = By.xpath("((//*[local-name() = 'svg' and @data-icon='plus']))[8]");
    By txt_reviewPage = By.xpath("//div[contains(text(),'Review Order')]");
    By btn_orderCheckout = By.xpath("//button[contains(@data-tip,'Click here to checkout')]");
    By btn_submitOrderEdit = By.xpath("//button[contains(@id,'submit-order-button')]");
    By btn_orderCheckoutReview = By.xpath("//button[contains(@data-tip, 'Click here to checkout')][normalize-space()!='']");
    By btn_draftNo =By.xpath("//div[contains(text(),'No')]");
    By txt_orderSuccessMsg = By.xpath("//*[contains(text(),'Thank you for your order!')]");
    By btn_closeMsg = By.xpath("//span[text()='×']");
    By dropdown_selTags = By.xpath("//div[contains(text(),'Tags')]/parent::div//*[contains(@class,'themed_select__dropdown')]");
    By dropdown_list = By.xpath("//div[contains(@class,'themed_select__menu-list themed_select')]");
//    By sel_tagOption = By.xpath("//div[contains(@class, 'themed_select__option') and normalize-space(.)='Rep Orders']");
By sel_tagOption = By.xpath("//div[contains(@class, 'themed_select__option') and not(contains(@class, 'themed_select__option--is-focused')) and normalize-space(.)='Rep Orders']");
    By lbl_tagName = By.xpath("//div[contains(text(),'Tags')]");
    By sel_tagName = By.xpath("//div[contains(@class,'css-12jo7m5 theme')]");
    By icon_removeTag = By.xpath("//div[contains(text(),'Rep Orders')]/following-sibling::div");
    By txt_lastOrderDate = By.xpath("//div[contains(text(),'Last ordered on')]");
    By customersText = By.xpath("//h2[contains(text(),'Customers')]");
    By btn_accountVisibility = By.xpath("//div[contains(text(),'Visibility')]/following-sibling::div//*[@data-icon='pen-to-square']");
    By dropdown_visibility = By.xpath("(//*[local-name() = 'svg' and @class='css-19bqh2r'])[1]");
    By sel_hiddenOption = By.xpath("//div[contains(text(),'Hidden')]");
    By btn_visibilitySave = By.xpath("//button[contains(@class,'mr-2 my-2 btn btn-outline')]");
    By txt_hidden = By.xpath("//div[contains(@class,'col')and contains(text(),'Hidden')]");
    By tbx_emailOrMobile = By.xpath("//input[@id='react-select-5-input']");
    String sel_cusOption ="//div[contains(text(),'CUS_NAME')]";
    By btn_loginAsCus = By.xpath("//a[contains(text(),'Login As')]");
    By btn_cusOrderIcon = By.xpath("//div[text()='Order']");
    By btn_cusAddSupplier = By.xpath("//button[contains(text(),'Add Supplier')]");
    By btn_accountStatus = By.xpath("//div[contains(text(),'Status')]/following-sibling::div//*[@data-icon='pen-to-square']");
    By dropdown_status = By.xpath("(//*[local-name() = 'svg' and @class='css-19bqh2r'])[1]");
    By sel_statusOption = By.xpath("//div[contains(@class,'themed_select__option') and contains(text(),'Inactive')]");
    By btn_statusSave = By.xpath("//button[contains(@class,'mr-2 my-2 btn btn-outline')]");
    By txt_status = By.xpath("//div[contains(text(),'Inactive')]");
    By ls_status = By.xpath("//div[contains(@class,'themed_select__menu-list css-11unzgr')]");
    By txt_error = By.xpath("//*[contains(translate(text(), 'ERROR', 'error'), 'error')]");
    By first_row = By.xpath("//table[@class='table table-hover']//tbody//tr[1]");
    By btn_invoice = By.xpath("//a[text()='Invoices']");
//    By enabledStatusLocator = By.xpath("//div[@class='_jehyy2' and text()='Enabled']");
By enabledStatusLocator = By.xpath("//div[contains(text(),'Cut+Dry Pay')]/following::div[text()='Enabled']");
    By defaultTermStatusLocator = By.xpath("//div[@class='_jehyy2' and text()='Default']");
    By newArrivalsOption = By.xpath("//div[contains(text(), 'New Arrivals (')]");
    By allItemsOption = By.xpath("(//div[contains(text(), 'Category')]/ancestor::div[2]/following-sibling::div//div[contains(text(), 'All Items')])[1]");
    By brandDropDown = By.xpath("//div[contains(text(), 'Brand')]");
    By brandDropDownOption = By.xpath("//div[contains(text(), 'Hungerford Smith')]");
    By itemTypeDropDown = By.xpath("//div[contains(text(), 'Item Type')]");
    By itemTypeDropDownOption = By.xpath("//div[contains(text(), 'Item Type')]/../../following-sibling::div//*[name()='svg' and @data-icon='square']/following-sibling::div[contains(text(), 'Special Order')]");
    By txt_filterByBrand =By.xpath("//button[@data-tip='View Brand Page']//*[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'hungerford smith')]");
    By itemStatusDropDown = By.xpath("//div[contains(text(), 'Item Type')]");
    By itemStatusDropDownOption = By.xpath("//div[contains(text(), 'Stocked')]");
    By storageTypeDropDown = By.xpath("//div[contains(text(), 'Storage Type')]");
    By storageTypeDropDownOption = By.xpath("//div[contains(text(), 'Frozen')]");
    By dietTypeDropDown = By.xpath("//div[contains(text(), 'Diet Type')]");
    By dietTypeDropDownOption = By.xpath("//div[contains(text(), 'Kosher')]");
    By txt_filterItem = By.xpath("//div[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'hungerford smith')]");
    By processingTypeDropDown = By.xpath("//div[contains(text(), 'Processing & Formulation')]");
    By processingTypeDropOption = By.xpath("//div[contains(text(), 'Non-GMO')]");
    By txt_noItems = By.xpath("//div[contains(text(), '0 Results')]");
    By btn_clearAllFilters = By.xpath("//button[contains(text(), 'Clear All Filters')]");
    By radioButton =By.xpath("//div[@class = 'align-middle']");
//    String txt_product = "//div[contains(@class,'_3quvq7 _1vlidrf' ) and contains(text(), 'NAME')]";
String txt_product = "//div[contains(@class,'_3quvq7 _1vlidrf' ) and contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), translate('NAME', 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'))]";
    By btn_addToCartPDP = By.xpath("//button[contains(text(), 'Add to Cart')]");
    By btn_checkOutPDP = By.xpath("//button[@data-for='cartCheckoutButton' and contains(text(),'$')]");
    By txt_orderConfirmationPopUp = By.xpath("//*[contains(text(), 'Thank you for your order!')]");
    By btn_addOrderGuideHeart = By.xpath("//button[@class='d-flex align-items-center justify-content-center cdbutton w-100 _fousr2 fa-stack btn btn-primary btn-sm' and @data-tip='Add to Order Guide']");
    By btn_catalogToOrderGuide = By.xpath("//span[text()='Order Guide']");
//    String lbl_orderGuideItem = "//div[contains(@class, 'text-capitalize _1i69w9z') and contains(text(),'NAME')]";
String lbl_orderGuideItem = "//div[contains(@data-tip, 'View Product Details') and contains(translate(normalize-space(text()), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), translate('NAME', 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'))]";
By btn_removeFromOrderGuideHeart = By.xpath("//button[@class='d-flex align-items-center justify-content-center cdbutton w-100 _fousr2 fa-stack btn btn-primary btn-sm' and @data-tip='Remove from Order Guide']");
    By btn_rightArrow = By.xpath("(//div[@class='_1gmghi1'])[1]");
    By btn_leftArrow = By.xpath("(//div[@class='_35991e'])[1]");
    By btn_exportPDP = By.xpath("//*[contains(text(), 'Export PDP (pdf)')]");
//    By img_first = By.xpath("//div[contains(@class,'justify-content-center')]/img[contains(@src,'extra-large-artichoke-bottoms-main-600.png')]");
//    By img_second = By.xpath("//div[contains(@class,'justify-content-center')]/img[contains(@src,'extra-large-artichoke-bottoms-raw-600.png')]");
//By btn_firstImage = By.xpath("//div[contains(@class,'position-relative')]/img[contains(@src,'extra-large-artichoke-bottoms-main-600.png')]");
//    By btn_secondImage = By.xpath("//div[contains(@class,'position-relative')]/img[contains(@src,'extra-large-artichoke-bottoms-raw-600.png')]");
    By img_first = By.xpath("//div[contains(@class,'justify-content-center')]/img[contains(@src,'anchovy-paste-raw-600.png')]");
    By img_second = By.xpath("//div[contains(@class,'justify-content-center')]/img[contains(@src,'anchovy-paste-main-600.png')]");
    By btn_firstImage = By.xpath("//div[contains(@class,'position-relative')]/img[contains(@src,'anchovy-paste-raw-600.png')]");
    By btn_secondImage = By.xpath("//div[contains(@class,'position-relative')]/img[contains(@src,'anchovy-paste-main-600.png')]");
    By txt_specialInstruction =By.xpath("//div[contains(text(),'Special Instructions')]/following-sibling::textarea");
    By txt_internalNote =By.xpath("//div[contains(text(),'Internal Notes')]/following-sibling::textarea");
    By txt_noteToCustomer =By.xpath("//div[contains(text(),'Note to Customer')]/following-sibling::textarea");
    By txt_poNumber = By.xpath("//div[contains(text(),'PO Number')]/following-sibling::div/input");
    String specialInstructionText = "//*[contains(text(),'SPECIALINSTRUCTION')]";
    String internalNoteText = "//*[contains(text(),'INTERNALNOTE')]";
    String noteToCustomerText = "//*[contains(text(),'NOTETOCUSTOMER')]";
    By quantityValue = By.xpath("(//input[contains(@data-input, 'quantityInput')])[1]");
    By Value = By.xpath("(//td[contains(@class,'py-3 _xigbpq4 border-top border-bottom')]//div[contains(text(),'$')])[last()]");
    By totalQuantity = By.xpath("//td[contains(text(),'Total Quantity')]/following-sibling::td");
    By totalValue=By.xpath("//td[@class='border-0 pt-1' and contains(text(),'$')]");
    By txt_orderId = By.xpath("//div[contains(text(),'Order Number #')]");
    By sel_delivery = By.xpath("//span[text()='Delivery']/preceding-sibling::div//*[contains(@data-icon, 'circle')]");
    By sel_pickup = By.xpath("//span[text()='Pickup/Will Call']/preceding-sibling::div//*[contains(@data-icon, 'circle')]");
    By sel_mailDelivery = By.xpath("//span[text()='Mail Delivery']/preceding-sibling::div//*[contains(@data-icon, 'circle')]");
    By editOrderReviewScreen = By.xpath("//a[contains(text(),'Edit Order')]");
    By lbl_OrderMinimumErrorBanner = By.xpath("//*[contains(text(),'Add a few more items worth') and contains(text(),'to meet minimum order amount')]");
    By addPaymentMethodButton = By.xpath("//button[contains(@class, 'btn-link') and text()='+ Add Payment Method']");
    By addBankAccountButton = By.xpath("//div[contains(@class, 'd-flex') and .//span[text()='Add bank account']]");
    By tbx_account_number = By.xpath("//label[text()='Account Number']/following-sibling::input");
    By tbx_routing_number = By.xpath("//label[text()='Routing Number']/following-sibling::input");
    By dropDownAccountType = By.xpath("//label[text()='Account Type']/following-sibling::div//div[contains(@class, 'themed_select__control')]");
    String btn_accountTypeOption = "//div[contains(@class, 'themed_select__option') and text()='OPTION_TEXT']";
    By txt_paymentMethodAddedSuccessfully = By.xpath("//h2[text()='Payment method added successfully']");
    By txt_errorOccurredAddingPaymentMethod = By.xpath("//h2[text()='An error occurred while trying to add the payment method.']");
    By icon_edit_payment_method = By.xpath("//div[contains(@class, 'font-weight-bold') and normalize-space(text())='Payment methods']/*[name()='svg']");
    By btn_trash_can = By.xpath("//div[@class='mx-0 my-auto col-2']//button[contains(@class, 'btn-link')]/*[name()='svg' and @data-icon='trash-can']");
    By txt_payment_method_removed = By.xpath("//h2[@id='swal2-title' and text()='Payment method has been removed successfully.']");
    By btn_enable = By.xpath("//div[contains(@class, 'col')]//button[contains(text(), 'Enable')]");
    By btn_i_agree = By.xpath("//button[text()='I Agree' and contains(@class, 'btn-primary')]");
    By btn_enable_auto_pay = By.xpath("//button[text()='Enable Auto Pay' and contains(@class, 'btn-primary')]");
    By txt_under_auto_pay = By.xpath("//div[div[contains(@class, 'font-weight-bold') and text()='Auto Pay']]/div[@class='_jehyy2']");
//    By lbl_itemPriceList = By.xpath("(//td//span//div[@data-tip='View Product Details']/ancestor::tr/td[7]//input)[1] | (//td//span//div[@data-tip='View Product Details']/ancestor::tr/td[7]//span)[1]");
    By lbl_itemPriceList = By.xpath("((//td//span//div[@data-tip='View Product Details']/ancestor::tr/td[last()-2]//input)[1] | (//td//span//div[@data-tip='View Product Details']/ancestor::tr/td[last()-2]//span)[1])[1]");
    By lbl_itemPriceList1 = By.xpath("((//td//span//div[@data-tip='View Product Details']/ancestor::tr/td[last()-2]//input)[1] | (//td//span//div[@data-tip='View Product Details']/ancestor::tr/td[last()-2]//span)[1])[2]");
    By lbl_secondItemPriceList = By.xpath("((//td//span//div[@data-tip='View Product Details']/ancestor::tr/td[last()-2]//input)[1] | (//td//span//div[@data-tip='View Product Details']/ancestor::tr/td[last()-2]//span)[2])[1]");
    By lbl_secondItemPriceList1 = By.xpath("((//td//span//div[@data-tip='View Product Details']/ancestor::tr/td[last()-2]//input)[1] | (//td//span//div[@data-tip='View Product Details']/ancestor::tr/td[last()-2]//span)[2])[2]");

    By btn_minusQtyFirstRow = By.xpath("(//*[name()='svg' and @data-icon='minus'])[1]");
    By tbx_itemQuantityinFirstRow = By.xpath("(//*[@data-input ='quantityInput'])[1]");
    By lbl_cartItemUnitPrice = By.xpath("(//td//span//div[@data-tip='View Product Details']/ancestor::tr/td[5]//input)[1] | (//td//span//div[@data-tip='View Product Details']/ancestor::tr/td[5]//span)[1]");
    By icon_edit_auto_pay = By.xpath("//div[contains(@class, 'font-weight-bold') and normalize-space(text())='Auto Pay']/*[name()='svg']");
    By dropdown_schedule = By.xpath("//div[contains(@class, 'themed_select__single-value') and text()='On due date']");
    String option_ScheduleType = "//div[contains(@class, 'themed_select__menu')]//div[contains(@class, 'themed_select__option') and text()='OPTION_TEXT']";
    By btn_update = By.xpath("//button[text()='Update']");
    String txt_auto_pay_details = "//div[contains(@class, '_jehyy2') and contains(text(), 'SCHEDULE_OPTION')]";
    By btn_cancelAutoPay = By.xpath("//button[text()='Cancel Auto Pay' and contains(@class, 'btn-outline-primary')]");
    By lbl_itemCodeList = By.xpath("(//td//span//div[@data-tip='View Product Details']/ancestor::tr/td[2])[1]");
    By btn_catalogPlus = By.xpath("//*[name()='svg' and @data-icon='plus']");
    String btn_catalogPDPPlusStable = "(//div[translate(normalize-space(text()), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz') = translate('NAME', 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz')]/following::div//*[name()='svg' and contains(@data-icon, 'plus')])[1]";


    By btn_catalogMinus = By.xpath("//*[name()='svg' and @data-icon='minus']");
    By sel_visibleOption = By.xpath("//div[contains(text(),'Visible')]");
    By txt_visible = By.xpath("//div[contains(text(),'Visible')]");
    By btn_placeOrder = By.xpath("//button[contains(text(),'Place Order')]");
    By txt_noCusMatch = By.xpath("//td[contains(text(),'No customers matching')]");
    By btn_moreFilter = By.xpath("//span[contains(text(),'More Filters')]");
    By sel_filterStatusDropdown = By.xpath("(//label[contains(text(),'Status')]//following::div[contains(@class,'themed_select__indicator')])[2]");
    By sel_filterInactive = By.xpath("//div[contains(text(),'Inactive')]");
    By btn_filterApply = By.xpath("//button[contains(text(),'Apply')]");
    By sel_statusOptionActive = By.xpath("//div[contains(text(),'Active')]");
    By txt_activeStatus = By.xpath("//div[contains(text(),'Active')]");
    By sel_filterActive = By.xpath("//div[contains(text(),'Active')]");
    By txt_lastInvoicePaid = By.xpath("//div[contains(@class, 'font-weight-bold') and text()='Last invoice paid on']/following-sibling::div");
    By btn_editCCFee = By.xpath("//div[contains(@class, 'font-weight-bold') and text()='CC Fees']/*[local-name()='svg' and @data-icon='pencil']");
    By dropdown_CCFee = By.xpath("//div[contains(@class, 'modal-content')]//div[contains(@class, 'themed_select__control') and contains(@class, 'css-yk16xz-control')]");
    String dropdown_CCFeeOption = "//div[contains(@class, 'themed_select__menu')]//div[text()='PLACEHOLDER']";
    String txt_CCFeeValue = "//div[contains(@class, 'font-weight-bold') and contains(text(), 'CC Fees')]/following-sibling::div[@class='_jehyy2' and contains(text(), 'OPTION_TEXT')]";
    By btn_editNotes = By.xpath("//div[contains(@class, 'font-weight-bold') and text()='Notes']/*[local-name()='svg' and @data-icon='pencil']");
    By tbx_editNotes= By.xpath("//textarea[@class='mr-2 form-control']");
    String txt_Note = "//div[contains(@class, 'font-weight-bold') and contains(text(), 'Notes')]/following-sibling::div[@class='_jehyy2' and contains(text(), 'OPTION_TEXT')]";
    By drodown_Filter = By.xpath("//div[@class='themed_select__value-container themed_select__value-container--has-value css-1hwfws3']//div[@class='themed_select__single-value css-1uccc91-singleValue']");
    String dropdown_FilterOption = "//div[contains(@class, 'themed_select__menu')]//div[text()='PLACEHOLDER']";
    By txt_Filter = By.xpath("//div[@class='themed_select__single-value css-1uccc91-singleValue']");
    String row_searchedCustomer = "//td[text()='CODE']/..";
    By btn_sendPaymentReminder = By.xpath("//button[text()='Send Payment Reminder']");
    By btn_sendEmail = By.xpath("//button[text()='Send Email']");
    By txt_EmailsSent = By.xpath("//h2[@id='swal2-title' and text()='Emails Sent!']");
    By txt_noDueInvoices = By.xpath("//h2[@class='swal2-title' and text()='There are no past due invoices']");
    By dropdown_moreActions = By.xpath("//button[@aria-haspopup='true' and @aria-expanded='false' and contains(text(), 'More Actions')]");
    By dropdown_optionManageNotifications = By.xpath("//a[@class='dropdown-item' and text()='Manage Notifications']");
    By dropdown_optionInviteBookkeeper = By.xpath("//a[@class='dropdown-item' and text()='Invite Bookkeeper']");
    By dropdown_optionEmailStatement = By.xpath("//a[@class='dropdown-item' and text()='Email Statement']");
    By dropdown_optionDownloadStatement = By.xpath("//a[@class='dropdown-item' and text()='Download Statement']");
    By dropdown_optionCreateCreditMemo = By.xpath("//a[@class='dropdown-item' and text()='Create Credit Memo']");
    By dropdown_optionMarkAsPaid = By.xpath("//a[@class='dropdown-item' and text()='Mark As Paid']");
    By tbx_bookKeeperName = By.xpath("//div[@class='form-group']//input[@class='form-control' and @placeholder='Enter contact person name...']");
    By tbx_bookKeeperEmail = By.xpath("//input[@class='form-control' and @placeholder='Enter contact person email...']");
    By tbx_bookKeeperMobile = By.xpath("//input[@class='form-control' and @placeholder='Enter contact person mobile phone number...']");
    By btn_inviteViaEmail = By.xpath("//button[@class='btn btn-primary btn-block' and text()='Invite via email']");
    String txt_BookKeeperEmailSent = "//div[text()='An invitation was sent to EMAIL.']";
    By tbx_enterNotificationEmail = By.xpath("//input[@class='form-control' and @placeholder='Enter email address']");
    By btn_send = By.xpath("//button[@class='btn btn-primary' and text()='Send']");
    By proprietaryItemOption = By.xpath("//div[contains(text(), 'Proprietary Items (')]");
    By tbx_creditMemoNumber = By.xpath("//input[contains(@class, 'form-control') and @placeholder='Enter credit memo number']");
    By dropdown_AssociatedInvoice = By.xpath("//div[@class='form-group' and .//label[text()='Associated Invoice']]//div[contains(@class, 'css-yk16xz-control')]");
    By dropdownOption_AssociatedInvoice = By.xpath("//div[@class='form-group' and .//label[text()='Associated Invoice']]//div[contains(@class, 'css-yk16xz-menu')]//div[1]");
    By tbx_enterTheAmount = By.xpath("//input[@placeholder='Enter the amount']");
    By tbx_description = By.xpath("//input[@placeholder='Enter the description' and @type='text' and @class='form-control']");
    By btn_CreateCreditMemo = By.xpath("//button[@type='button' and contains(@class, 'btn btn-primary') and text()='Create Credit Memo']");
    By txt_CreditMemoConfirm = By.xpath("//h2[@class='swal2-title' and @id='swal2-title' and text()='Created the credit memo successfully.']");
    String errorMessage_CreditMemoAlreadyExists = "//h2[@class='swal2-title' and @id='swal2-title' and text()='Credit memo number: memoNumber already exists.']";
    By txt_markedAsPaidSucessfully = By.xpath("//div[@id='swal2-content' and contains(text(), 'The selected invoice was marked as paid successfully')]");
    String checkBox_inInvoiceTable = "//table/tbody/tr[row]/td[1]//div[contains(@class, '_du1frc')]";
    By dropdown_orderGuide = By.xpath("//div[contains(@class, '_1nxcwl8') and contains(@class, 'col-3') and contains(@class, 'd-none') and contains(@class, 'd-lg-block')][.//div[text()='Order Guide:']]//div[contains(@class, 'cd_themed_select__control')]");
    String dropdownOrderGuideItemXPath = "//div[contains(@class, 'cd_themed_select__menu')]//div[text()='{}']";
    By txt_displayedOrderGuide = By.xpath("//div[contains(@class, '_1nxcwl8') and contains(@class, 'col-3') and contains(@class, 'd-none') and contains(@class, 'd-lg-block')][.//div[text()='Order Guide:']]//div[contains(@class, 'cd_themed_select__single-value')]");
    By btn_checkout_stable = By.xpath("//button[contains(@data-for, 'cartCheckoutButton')]");
    By table_OrderGuide = By.xpath("//table[@class='_6rf0k0 mb-5 table']");
    String item_MarginColumnOrderGuide = ".//tbody/tr[PLACEHOLDER]/td[6]";
    By tbx_editMarginValue = By.xpath("//input[@type='text' and @class='_1dq0frk form-control']");
    By btn_reset = By.xpath("//button[@type='button' and contains(@class, 'btn-primary') and text()='Reset Values']");
    By section_dontForgetToBuy = By.xpath("//div[text()=\"Don't Forget to Buy\"]");
    By txt_duplicateOrder = By.xpath("//h2[@class='swal2-title' and @id='swal2-title' and text()='Duplicate Order']");
    By btn_addNewPaymentMtd = By.xpath("//div[contains(text(),'Add a new payment method')]");
//    By txt_lastOrderedPrice = By.xpath("//td[contains(@class,'font-weight-light py-3 text-nowrap') and contains(text(),'/lb')]");
By txt_lastOrderedPrice = By.xpath("(//td//*[contains(translate(text(), 'abcdefghijklmnopqrstuvwxyz', 'ABCDEFGHIJKLMNOPQRSTUVWXYZ'), '/LB')])[1]/parent::div");
    By txt_lastOrderedPriceOff = By.xpath("//td[contains(@class, 'py-3') and div/div//div[contains(text(), 'CS')]]");
    By btn_checkoutOperator = By.xpath("//button[@data-for='cartCheckoutButton']");
    By btn_increaseQtyFirstRowClassic = By.xpath("(//tr/td//div[contains(@data-tip,'View Product Details')]/following::td//div/*[contains(@data-icon,'plus')])[1]");
    By btn_submitOrderForApproval = By.xpath("//button[contains(text(),'Submit')]");
    By txt_sentApproval = By.xpath("//strong[contains(text(),'Sent for approval!')]");
    By btn_viewOrderInDraft = By.xpath("//button[text()='View Order in Drafts']");
//    By finalWeight = By.xpath("(//td//span//div[@data-tip='View Product Details']/ancestor::tr/td[7]//input)[1]");
    By finalWeight = By.xpath("(//td//span//div[@data-tip='View Product Details']/ancestor::tr/td[7]/div/div/div/div)[1]");
    By btn_finalWeightUpdate = By.xpath("//button[contains(text(),'Update Weight')]");
    By finalWeightInput = By.xpath("(//*[contains(text(),'Edit Weight Details')]/following::div/table/tbody/tr/td[3]/input)[1]");
    By finalItemPrice = By.xpath("(//td//span//div[@data-tip='View Product Details']/ancestor::tr/td[8]/div/div[normalize-space()!=''])[1]");
    By confirmPrice = By.xpath("(//tbody/tr/td[9])[1]");
    By poundPrice = By.xpath("(//td//span//div[@data-tip='View Product Details']/ancestor::tr/td[5]/div/div/div/div)[1]");
    By txt_lbPricePopUP = By.xpath("//div[text()='Update Price']");
    By perLbPrice = By.xpath("//*[text()='Price LB ($)']/following-sibling::input");
    By priceInCustomerOrder = By.xpath("(//tbody/tr/td[6])[1]");
    By editSpotPrice = By.xpath("//td[7]/div/div/div");
   // String spotPriceValue = "//td[7]/div/input[@value='VALUE']";
    String spotPriceValue = "//td[7]/div//span[contains(text(),'VALUE')]";
    By btn_splitWeight = By.xpath("//td[8]/div/div/div/div");
    By txt_splitWeight = By.xpath("//div[contains(text(),'Weight Details')]");
    By lbl_cases = By.xpath("(//th[contains(text(),'No. of')]/../../following-sibling::*//input)[1]");
    By lbl_weight = By.xpath("(//th[text()='Weight / CS']/../../following-sibling::*//input)[2]");
    By btn_updateWeight = By.xpath("//button[text()='Update Weight']");
    By splitFinalWeight = By.xpath("(//td//span//div[@data-tip='View Product Details']/ancestor::tr/td[8]//input)[1]");
    By splitFinalWeightPrice = By.xpath("(//td//span//div[@data-tip='View Product Details']/ancestor::tr/td[9])[1]");
    By cb_inInvoiceTable = By.xpath("//table/tbody/tr[1]/td[1]//div[contains(@class, '_du1frc')]");
//    By lbl_spotPrice = By.xpath("//div[text()='Price ($)']/following-sibling::input");
By lbl_spotPrice = By.xpath("//div[contains(text(),'Price') and contains(text(),'($)')]/following-sibling::input");
    String itemValue = "//span[contains(text(), 'CODE')]";
    By btn_getSplitWeight = By.xpath("(//td[8]/div/div/div/div/div)[1]");
    String finalWeightQuantitySelect = "(//tr//td[contains(text(),'ITEMCODE')]/following-sibling::td//input[contains(@data-input,'quantityInput')])[1]//ancestor::td/following-sibling::td[1]/div/div[POSITION]";
    By editWeightDetailsOverlay = By.xpath("//*[contains(text(),'Edit Weight Details')]");
    String totalWeight = "((//th[contains(text(),'Total Weight')])[POSITION]/ancestor::table//tbody//td/input)[3]";
    String totalNoOfUOMsOrdered = "((//th[contains(text(),'Total Weight')])[POSITION]/ancestor::table//tbody//td/input)[1]";
    String WeightPerUOM = "((//th[contains(text(),'Total Weight')])[POSITION]/ancestor::table//tbody//td/input)[2]";
    By dropdown_option_orderguideSettings = By.xpath("//div[text()='Order Guide Settings']");
    By txt_reviewStandingOrders = By.xpath("//div[text()='Review Standing Order']");
    String customerScreenScanToOrderBtn = "//tr/td[contains(text(),'CUSTOMERCODE')]/..//a[contains(@href,'scan-to-order')]";
    String customerProfileScreenScanToOrderBtn = "//a[contains(@href,'scan-to-order')]";

    By btn_firstMultiOUM = By.xpath("(//*[local-name()='svg' and @data-icon='chevron-down'])[1]");
    By lbl_firstMultiOUMItemName = By.xpath("(//*[local-name()='svg' and @data-icon='chevron-down'])[1]/ancestor::tr/td//span/div[@data-tip='View Product Details']");
    By lbl_firstMultiOUMItemCode = By.xpath("(//*[local-name()='svg' and @data-icon='chevron-down'])[1]/ancestor::tr/td[2]");
    By lbl_itemPriceListMultiOUM = By.xpath("(((//*[local-name()='svg' and @data-icon='chevron-down'])[1]/ancestor::tr/td[last()-2]//input)[1] | ((//*[local-name()='svg' and @data-icon='chevron-down'])[1]/ancestor::tr/td[last()-2]/div/div/div)[1] | ((//*[local-name()='svg' and @data-icon='chevron-down'])[1]/ancestor::tr/td[last()-2]//span)[1])[1]");
    By lbl_itemPriceListMultiOUM1 = By.xpath("(((//*[local-name()='svg' and @data-icon='chevron-down'])[1]/ancestor::tr/td[last()-2]//input)[1] | ((//*[local-name()='svg' and @data-icon='chevron-down'])[1]/ancestor::tr/td[last()-2]//span)[1])[2]");
    /*By btn_firstMultiOUM = By.xpath("(//*[local-name()='svg' and @data-icon='chevron-up'])[1]");
    By lbl_firstMultiOUMItemName = By.xpath("(//*[local-name()='svg' and @data-icon='chevron-up']/ancestor::tr/td//span/div[@data-tip='View Product Details'])[1]");
    By lbl_firstMultiOUMItemCode = By.xpath("(//*[local-name()='svg' and @data-icon='chevron-up']/ancestor::tr/td[2])[1]");
    By lbl_itemPriceListMultiOUM = By.xpath("(((//*[local-name()='svg' and @data-icon='chevron-up']/ancestor::tr/td[last()-2]//input)[1])[1] | ((//*[local-name()='svg' and @data-icon='chevron-up']/ancestor::tr/td[last()-2]/div/div/div/div)[1])[1] | ((//*[local-name()='svg' and @data-icon='chevron-up']/ancestor::tr/td[last()-2]//span)[1])[1])[1]");
    By lbl_itemPriceListMultiOUM1 = By.xpath("(((//*[local-name()='svg' and @data-icon='chevron-up']/ancestor::tr/td[last()-2]//input)[1])[1] | ((//*[local-name()='svg' and @data-icon='chevron-up']/ancestor::tr/td[last()-2]//span)[1])[2])[1]");
*/

    String lbl_firstMultiOUMItemCodeLB = "(//*[local-name()='svg' and @data-icon='chevron-down'])/ancestor::tr/td[COUNT]//div[contains(text(),'LB')]/ancestor::td/parent::tr/td[2]";
    String lbl_firstMultiOUMItemNameLB = "(//*[local-name()='svg' and @data-icon='chevron-down'])/ancestor::tr/td[COUNT]//div[contains(text(),'LB')]/ancestor::td/parent::tr/td//span/div[@data-tip='View Product Details']";
    /*String lbl_firstMultiOUMItemCodeLB = "(//*[local-name()='svg' and @data-icon='chevron-up'])/ancestor::tr/td[COUNT]//div[contains(text(),'LB')]/ancestor::td/parent::tr/td[2]";
    String lbl_firstMultiOUMItemNameLB = "(//*[local-name()='svg' and @data-icon='chevron-up'])/ancestor::tr/td[COUNT]//div[contains(text(),'LB')]/ancestor::td/parent::tr/td//span/div[@data-tip='View Product Details']";
*/

    By lbl_itemCodeLists = By.xpath("//td//span//div[@data-tip='View Product Details']/ancestor::tr/td[2]");
    String lbl_ListsMultiOUMExist = "//td//span//div[@data-tip='View Product Details']/ancestor::tbody/tr[ROW_COUNT]/td//*[local-name()='svg' and @data-icon='chevron-down']";
//    String lbl_ListsMultiOUMExist = "//td//span//div[@data-tip='View Product Details']/ancestor::tbody/tr[ROW_COUNT]/td//*[local-name()='svg' and @data-icon='chevron-up']";
    String lbl_firstSingleOUMItemName = "//td//span//div[@data-tip='View Product Details']/ancestor::tbody/tr[ROW_COUNT]/td//span/div[@data-tip='View Product Details']";
//    String lbl_firstSingleOUMItemCode = "//td//span//div[@data-tip='View Product Details']/ancestor::tbody/tr[ROW_COUNT]/td[2]";
String lbl_firstSingleOUMItemCode = "(//td//span//div[@data-tip='View Product Details']/ancestor::tr[1]/td[2])[ROW_COUNT]";

//    String lbl_itemPriceMultiOUM = "((//button/*[local-name()='svg' and @data-icon='chevron-up'])[1]/ancestor::tr/td[last()-2]//input)[UOM] | ((//button/*[local-name()='svg' and @data-icon='chevron-up'])[1]/ancestor::tr/td[last()-2]/div/div[UOM]/div)[1] | ((//button/*[local-name()='svg' and @data-icon='chevron-up'])[1]/ancestor::tr/td[last()-2]//span)[UOM]";
String lbl_itemPriceMultiOUM = "((//button/*[local-name()='svg' and @data-icon='chevron-up'])[1]/ancestor::tr/td[last()-2]//input)[UOM] | ((//button/*[local-name()='svg' and @data-icon='chevron-up'])[1]/ancestor::tr/td[last()-2]//span)[UOM]";
//    String lbl_itemPriceMultiOUM = "((//button/*[local-name()='svg' and @data-icon='chevron-up']/ancestor::tr/td[last()-2]//input)[UOM])[1] | ((//button/*[local-name()='svg' and @data-icon='chevron-up']/ancestor::tr/td[last()-2]//span)[UOM])[1]";

    String multiUomDropDownOG = "(//td[text()='CODE']/following-sibling::*//div/*[local-name()='svg'])[1]";
    String multiUomDropDownOGExist = "//td//span//div[@data-tip='View Product Details']/ancestor::tbody/tr//td[text()='CODE']/..//*[local-name()='svg' and @data-icon='chevron-down']";
    //    String multiUomDropDownOGExist = "//td//span//div[@data-tip='View Product Details']/ancestor::tbody/tr//td[text()='CODE']/..//*[local-name()='svg' and @data-icon='chevron-up']";
    By multiUomOption =By.xpath("//div[text()='Multiple Units']");
    String btn_OGAddToCartPlusQuantity ="(//td[text()='CODE']/following-sibling::*//div/*[local-name()='svg' and @data-icon='plus'])[UOM]";
    String btn_OGAddToCartMinusQuantity ="(//td[text()='CODE']/following-sibling::*//div/*[local-name()='svg' and @data-icon='minus'])[UOM]";
    String txt_multiOrderId = "(//div[contains(text(),'Order Number #')])[NUM]";

    String lbl_itemPriceMultiUOMEdit = "((//button/*[local-name()='svg' and @data-icon='chevron-up'])[1]/ancestor::tr/td[last()-2]/div/div/div)[UOM]";
//    String lbl_itemPriceMultiUOMEdit = "((//button/*[local-name()='svg' and @data-icon='chevron-up']/ancestor::tr/td[last()-2]/div/div/div)[UOM])[1]";
//    String txt_casesMultiUOMEdit = "((//th[contains(text(),'No. of')])[UOM]/../../following-sibling::*//input)[1]";
//    String txt_weightMultiUOMEdit = "((//th[contains(text(),'Weight /')])[UOM]/../../following-sibling::*//input)[2]";
    String btn_AddWightRowMultiUOMEdit = "((//th[contains(text(),'No. of')])[UOM]/../../following-sibling::*//button)[1]";
    String txt_casesMultiUOMEdit = "((//th[contains(text(),'No. of')])[UOM]/../../following-sibling::*//tr[RECORD]//input)[1]";
    String txt_weightMultiUOMEdit = "((//th[contains(text(),'Weight /')])[UOM]/../../following-sibling::*//tr[RECORD]//input)[2]";
    String lbl_getItemPriceMultiUOM = "((//button/*[local-name()='svg' and @data-icon='chevron-up'])[1]/ancestor::tr/td[last()-2]/div/div[UOM]/div/div)[RECORD]";
//    String lbl_getItemPriceMultiUOM = "((//button/*[local-name()='svg' and @data-icon='chevron-up']/ancestor::tr/td[last()-2]/div/div[UOM]/div/div)[RECORD])[1]";
    String lbl_quantityMultiUOM = "(//input[contains(@data-input, 'quantityInput')])[UOM]";
    By dd_sortItemBy =By.xpath("(//*[contains(text(),'Sort Items By')])[last()]/following-sibling::div");
    String lbl_sortItemByOption = "//div[contains(@class, 'cd_themed_select__option') and contains(text(), 'TYPE')]";
    String PONumber = "//div[contains(text(),'PO Number')]/following-sibling::div[contains(normalize-space(), 'PONUMBER')]";
    String catalogAddToCart = "((//div[translate(normalize-space(text()), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz') = translate('NAME', 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz')])[last()]/following::div//*[name()='svg' and contains(@data-icon, 'plus')])[1]";

    By lbl_orderGuideTableColumn = By.xpath("//table/thead/tr/td");
    String lbl_orderGuideTableColumnName = "//table/thead/tr/td[COUNT]";
    String poundPriceMultiUOM = "(//td//span//div[@data-tip='View Product Details']/ancestor::tr/td[COUNT]/div/div/div)[1]";
    String txt_casesPriceMultiUOMEdit = "((//div[contains(text(),'Price') and contains(text(),'($)')])[UOM]/following-sibling::input)[1]";
    By btn_updatePrice = By.xpath("//button[contains(text(),'Update')]");
    String lbl_getPriceMultiUOM = "(//td//span//div[@data-tip='View Product Details']/ancestor::tr/td[COUNT]/div/div/div[1])[UOM]";
    String txt_totalWeightMultiUOM = "((//th[contains(text(),'No. of')])[UOM]/../../following-sibling::*//tr[RECORD]//input)[3]";
    String btn_editMarginMultiUOM = "((//td//span//div[@data-tip='View Product Details']/ancestor::tr/td[6])[1]//span[2])[UOM]";
    String lbl_spotPriceMultiUOM = "(//div[contains(text(),'Price') and contains(text(),'($)')]/following-sibling::input)[UOM]";
    String lbl_marginMultiUOM = "(//div[contains(text(),'Margin') and contains(text(),'$')]/following-sibling::input)[UOM]";
    String lbl_salesCostMultiUOM = "(//div[contains(text(),'Sales Cost') and contains(text(),'($)')]/following-sibling::input)[UOM]";
    String lbl_marginPercentageMultiUOM = "(//div[text()='Margin (%)']/following-sibling::input)[UOM]";
    String spotPriceValueMultiUOM = "//td[COUNT]/div/div[UOM]//*[contains(text(),'VALUE')]";
    String marginValueMultiUOM = "//td[COUNT]/div/div[UOM]//*[contains(text(),'VALUE')]";
    String marginPercentageMultiUOM = "//td[COUNT]/div/div[UOM]//*[contains(text(),'VALUE')]";
    By txtSubstitution = By.xpath("//div[contains(text(),'Substitution')]");
    By txtSetSubstitution = By.xpath("//div[contains(text(),'Set Substitute Items')]");
    By btn_chooseSub = By.xpath("(//button[contains(text(),'Choose Sub')])[last()]");
    By btn_selectSub = By.xpath("//div[contains(text(), 'Substitute with:')]/preceding-sibling::*[1][local-name()='svg' and @data-icon='circle']");
    By btn_closeSub = By.xpath("//*[local-name()='svg' and @data-icon='xmark']");
    By btn_editSub = By.xpath("(//*[local-name()='svg' and @data-icon='edit'])[last()]");
    By btn_notSelectSub = By.xpath("//div[contains(text(), 'Not Selected')]/preceding-sibling::*[1][local-name()='svg' and @data-icon='circle']");
    By combinedOrderPopUp = By.xpath("//div[contains(text(), 'Do you want to combine your orders?')]");
    By combinedOrderContinue = By.xpath("//button[contains(text(), 'Continue')]");

    By catalogFirstItemItemCode = By.xpath("//div[contains(@class,'card-deck')][1]/div[contains(@class,'card')][1]//button[contains(@data-tip,'View Brand Page')]/../following-sibling::div");
    String unpaidInvoiceName = "//div[text()='NAME']";
    By caseMinimumNotMetText = By.xpath("//*[contains(text(),'Case Minimum Not Met')]");
    By btn_sortCustomOrder = By.xpath("//div[contains(@class, 'cd_themed_select__single-value') and text()='Custom Order']");
    String orderColumn = "//td[text()='COLUMN']";
    String orderSummery = "//div[text()='ORDERSUMMERY']";
    By caseUnit = By.xpath("//label[text()='Unit']/../following-sibling::div[text()='Case']");
    By saveItem = By.xpath("//button[text()='Save Item']");
    By btn_stock = By.xpath("(//span[contains(text(),'Stock')])[1]");
    String stockAvailability = "//div[contains(text(),'STOCK')]";
    By substitutionsAccessEditBtn = By.xpath("//div[contains(text(), 'Substitutions')]//following-sibling::div//div//*[name()='svg' and contains(@data-icon, 'pen-to-square')]");
    By substitutionDropDown = By.xpath("//div[contains(text(), 'Substitutions')]//following-sibling::div/div/div/div");
    String substitutionOption = "//div[contains(text(), 'Substitutions')]//following-sibling::*//div[text()='STATUS']";
    String nodeStatus = "(//small[contains(text(), 'STATUS')])[1]";
    By txt_missingPO = By.xpath("//h2[text()='Missing PO Number!']");
    By marginPriceFirstItem = By.xpath("((//td//span//div[@data-tip='View Product Details']/ancestor::tr/td[last()-2]//input)[1] | (//td//span//div[@data-tip='View Product Details']/ancestor::tr/td[last()-3]//span)[2])[1]");
   // Double orderSummeryGrossProfitValue = "//div[contains(text(),'Gross Profit (Sale Cost)')]/following-sibling::div[contains(text(),'VALUE')]";
    String orderSummeryValue = "//div[contains(text(),'NAME')]/following-sibling::div[contains(text(),'VALUE')]";
    By salesCommissionValue = By.xpath("//div[contains(text(),'Sales Commission')]/following-sibling::div");
    By orderGuideText = By.xpath("//div[text()='Order Guide:']");
    By sortItemText = By.xpath("//div[text()='Sort Items By:']");
    By btn_deliveryDateStable = By.xpath("//div[text()='Delivery Date:']/../following-sibling::div//*[name()='svg' and @data-icon='calendar-date-vect']");
    String dynamicToXPath = "(//div[contains(@class,'react-datepicker__day--highlighted')]/preceding::div[contains(@class, 'react-datepicker__day') and text()='DAY'])[last()]";
    String btn_catalogPDPPlusStableDP = "(//div[contains(@class,'card-deck')]//div[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), translate(\"NAME\", 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'))])[last()]/../../following-sibling::div//*[name()='svg' and @data-icon='plus']";
    By activeDate = By.xpath("(//div[contains(@class,'react-datepicker__day') and @aria-disabled='false'])[1]");
    By lbl_stockInHouse = By.xpath("(//div[contains(text(),'Stocked (in-house)')]/*[name()='svg'])[1]");
    By btn_clearAll = By.xpath("//div[contains(text(),'Clear All')]");
    String hardHoldMessagePopUp = "//h2[contains(text(),'MESSAGE')]";
    By lastOrder =By.xpath("//td[text()='Last Order']");
    By activeDateNext = By.xpath("(//div[contains(@class,'react-datepicker__day') and @aria-disabled='false'])[2]");
    String fullOrderDelayMessage = "//span[contains(text(),'MESSAGE')]";
    By fullyOrderDelay = By.xpath("//strong[text()='Full Order Delay: ']");
    By PartialShipmentNotice = By.xpath("//strong[text()='Partial Shipment Notice: ']");
    By txt_cutOffTime = By.xpath("//span[text()='Order Cutoff:']");
    String txt_avg = "(//div[contains(@class,'card-deck')]//div[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), translate(\"NAME\", 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'))])[last()]/../following-sibling::div[contains(text(),'AVG')]";
    String txt_itemType = "//div[contains(text(), 'Item Type')]/../../following-sibling::div//*[name()='svg' and @data-icon='square']/following-sibling::div[contains(text(), 'NAME')]";
    String txt_specialItem = "//span//div[contains(text(), 'NAME')]";
    String txt_marginPriceError = "//span[contains(text(), 'NAME')]";
    By txt_linkedAccount = By.xpath("//div[contains(text(),'Linked Accounts')]");
    By childAccountEditBtn = By.xpath("//button[contains(text(), 'Child Account')]");
    By txt_manageChildAccounts = By.xpath("//div[contains(text(),'Manage Child Accounts')]");
    String accountStatus = "//div[contains(text(), 'Account Type')]/following-sibling::div[contains(text(),'STATUS')]";
    String childAccount = "//label[contains(text(), 'CHILDACC')]";
    String childAccountDropDown = "(//label[contains(text(), 'CHILDACC')]/../following-sibling::div//*[name()='svg'])[last()]";
    String addedOrderGuide = "(//label[contains(text(), 'CHILDACC')]/../following-sibling::div//div[contains(text(),'NAME')])[last()]";
    String updateChildAccountSettings = "//div[contains(text(),'MESSAGE')]";
    String dropDownOrderGuide =  "(//div[contains(text(), 'Order Guide:')]//following::div[contains(text(), 'NAME')])[last()]";
    By btn_deleteOrderGuide = By.xpath("//a[contains(text(), 'Delete Order Guide')]");
    String deliveryDateCustomerOrder = "//*[contains(text(),'#') and text()='ID']/../../preceding-sibling::td[1][text()='DATE']";
    By btn_pickUpDateStable = By.xpath("//div[text()='Pickup Date:']/../following-sibling::div//*[name()='svg' and @data-icon='calendar-date-vect']");
    String dynamicToXPathStable = "//div[contains(@class,'react-datepicker__day')]/preceding::div[contains(@class, 'react-datepicker__day') and contains(@aria-disabled, 'false') and text()='DAY']";
    String fulfilmentTag = "//*[contains(text(),'#') and text()='ID']/../../preceding-sibling::td[1]//*[text()='TAG']";
    String reviewOrderFulfilment = "//span[contains(text(),'TYPE')]";
    String sortOptionDisplay = "//div[text()='Sort Items By:']/following-sibling::div//div[contains(text(),'OPTION')]";
    String addedItemName = "//td//span/div[@data-tip='View Product Details'][contains(text(),'NAME')]";
    String parentChildTag = "//td[text()='CODE']/following-sibling::td/*//span[text()='TAG']";
    String customerProfileParentChildTag = "//span[text()='TAG']";
    By dropdown_option_SwitchToOfflineMode = By.xpath("//div[text()='Switch to Offline Mode']");
    By txt_offlineMode = By.xpath("//div[text()='Offline Mode']");
    By btn_activeOfflineMode = By.xpath("//button[text()='Activate Offline Mode']");
    By txt_hangTight = By.xpath("//div[contains(text(),'Hang tight')]");
    By btn_activeOnLineMode = By.xpath("//span[text()='Go Online']");
    By txt_catalog = By.xpath("//div[contains(text(), 'Sections')]");
    String btnChat = "//td[text()='CODE']/../td[7]//*[name()='svg' and @data-icon='comments']";
    By btn_ChatCustomerProfile = By.xpath("//button[contains(text(),'Chat')]");
    By btn_nextMonth = By.xpath("//button[contains(@aria-label,'Next Month')]");
    By txt_sameDeliveryDate = By.xpath("//h2[contains(text(),'same delivery date are not allowed')]");
    By icon_deleteSearchItem = By.xpath("(//*[local-name()='svg' and @data-icon='circle-xmark'])[1]");
    By icon_deleteSubstitutionItem = By.xpath("(//*[local-name()='svg' and @data-icon='xmark'])[1]");
    String marginValue = "(//td[text()='CODE']/following-sibling::td[2]//div/div/span)[UOM]";
    String lbl_lastOrderDetails = "(//div[contains(@class,'card-deck')]//div[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), translate(\"NAME\", 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'))])[last()]/../following-sibling::div/div";
    By txt_purchaseHistoryCatalog = By.xpath("//div[text()='Purchase History']");
    String lastOrderDetails = "//div[text()='ORDER']";
    String purchaseHistoryOG = "(//td[text()='CODE']/following-sibling::td[2]/div/div)[1]";
    By btn_OrderCCEmailAlerts = By.xpath("//div[contains(text(), 'Order CC Email Alerts')]/following-sibling::*[name()='svg' and contains(@data-icon, 'pen-to-square')]");
    String txt_OrderCCEmailAlerts = "//div[text()='ALERT']";
    By sendAlertTo = By.xpath("//div[contains(text(), 'Send alerts to:')]/following-sibling::input");
    By AddedAlertToNewOder = By.xpath("//div[normalize-space(text())='Send Alerts For']/following-sibling::*//div[text()='New Orders']");
    String txt_specialOrderNote = "//div[text()='NOTE']";
    String multiUomDropDownOGArrow = "(//td[text()='CODE']/following-sibling::*//button/*[local-name()='svg'])[1]";
    String dataPickerOrderGuide = "//div[translate(normalize-space(text()), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz') = translate(\"NAME\", 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz')]/../../../../../following-sibling::td[last()-1]//div//input";
    String dataPickerCatalog ="(//div[translate(normalize-space(text()), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz') = translate(\"NAME\", 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz')]/following::div//input)[1]";
    String dataPickerReviewOrder = "//div[translate(normalize-space(text()), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz') = translate(\"NAME\", 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz')]/../../../../../following-sibling::td[last()-3]//div//input";
    String btn_reviewPlusStable = "(//div[translate(normalize-space(text()), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz') = translate('NAME', 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz')]/following::div//*[name()='svg' and contains(@data-icon, 'plus')])[2]";




    public void ifDuplicateOrderDisplayed(){
        if (distributorUI.isDisplayed(txt_duplicateOrder)) {
            distributorUI.click(btn_yes);
        }
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
//        distributorUI.waitForCustom(4000);
        return distributorUI.isDisplayed(By.xpath(btnOrderGuide.replace("CODE", code)),20);
    }
    public boolean isCustomerSearchResultByNameDisplayed(String name) throws InterruptedException {
        distributorUI.waitForElementEnabledState(By.xpath(btnNameOrderGuide.replace("NAME", name)), true);
//        distributorUI.waitForCustom(4000);
        return distributorUI.isDisplayed(By.xpath(btnNameOrderGuide.replace("NAME", name)),20);
    }
    public void clickOnOrderGuide(String code) {
        try {
            distributorUI.waitForCustom(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
//        distributorUI.click(By.xpath(btnOrderGuide.replace("CODE", code)));
        distributorUI.clickWithFallback(By.xpath(btnOrderGuide.replace("CODE", code)));
    }
    public void clickOnNameOrderGuide(String code) {
        distributorUI.clickWithFallback(By.xpath(btnNameOrderGuide.replace("NAME", code)));
    }
    public String getItemNameFirstRow() throws InterruptedException {
        distributorUI.waitForElementEnabledState(lbl_itemNameList,true);
        distributorUI.waitForCustom(3000);
        return distributorUI.getText(lbl_itemNameList);
    }
    public void clickPlusQryFirstRow(){
        distributorUI.click(btn_increaseQtyFirstRow);
    }
    public void clickPlusQrySecondRowStable(){
        distributorUI.click(btn_increaseQtySecondRowStable);
    }
    public void clickMinusQryFirstRow(){
        distributorUI.click(btn_minusQtyFirstRow);
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
        distributorUI.waitForElementEnabledState(lbl_itemNameList,true);
       return distributorUI.getText(lbl_itemNameList,1).toLowerCase();
    }
    public void clickPlusQrySecondRow(){
        distributorUI.click(btn_increaseQtySecondRow);
    }
    public void clickOnCheckoutButton() throws InterruptedException {
        distributorUI.waitForCustom(4000);
        distributorUI.waitForElementEnabledState(btn_checkout,true);
        distributorUI.click(btn_checkout);
    }
    public void clickOnCatalogButton(){
        distributorUI.waitForClickability(btn_catalog);
        distributorUI.click(btn_catalog);
    }
    public void typeToSearchOnCatalog(String item) throws InterruptedException {
        if(distributorUI.getValue(tbx_catalogSearch).isEmpty()){
            distributorUI.waitForCustom(1000);
            distributorUI.sendKeys(tbx_catalogSearch,item);
            distributorUI.waitForCustom(5000);
        }
        else {
            distributorUI.click(icon_deleteSearchItem);
            distributorUI.clear(tbx_catalogSearch);
            distributorUI.waitForCustom(1000);
            distributorUI.sendKeys(tbx_catalogSearch, item);
            distributorUI.waitForCustom(5000);
        }
    }
    public boolean isStockInHouseDisplayed() throws InterruptedException {
        return distributorUI.isDisplayed(lbl_stockInHouse);
    }

    public void clickOnStockInHouseCloseBtn() throws InterruptedException {
         distributorUI.click(lbl_stockInHouse);
    }
    public void clickOnClearAllBtn() throws InterruptedException {
        distributorUI.click(btn_clearAll);
    }
    public String getFirstItemNameFrmSearchResults(String name){
        try {
            distributorUI.waitForCustom(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        distributorUI.waitForVisibility(By.xpath(lbl_catalogSearchItemList.replace("NAME", name)));
        return distributorUI.getText(By.xpath(lbl_catalogSearchItemList.replace("NAME", name))).toLowerCase();
    }
    public void clickAddToCartCatalog(String ItemName) throws InterruptedException {
        distributorUI.waitForClickability(By.xpath(btn_addToCart.replace("ITEMNAME",ItemName.toLowerCase())));
        distributorUI.waitForCustom(4000);
        distributorUI.click(By.xpath(btn_addToCart.replace("ITEMNAME",ItemName.toLowerCase())));
        distributorUI.waitForCustom(4000);
        distributorUI.waitForElementEnabledState(btn_checkout_stable,true);
    }
    public String getItemQtyFirstRow(){
//        return distributorUI.getText(tbx_itemQuantityFirstRow, "value");
        return distributorUI.getText(tbx_itemQuantityinFirstRow, "value");
    }
    public Double getItemPriceFirstRow(){
//        return Double.valueOf(distributorUI.getText(lbl_itemPriceFirstRow).replace("$",""));
        distributorUI.waitForVisibility(lbl_itemPriceList);
        String tagName = distributorUI.getElement(lbl_itemPriceList).getTagName();
        String priceText;
        if (tagName.equals("input")) {
            priceText = distributorUI.getText(lbl_itemPriceList, "value");
        } else {
            priceText = distributorUI.getText(lbl_itemPriceList);
        }

        return Double.valueOf(priceText.replace("$", "").trim());
    }
    public String getItemPriceSecondRow(){
        distributorUI.waitForVisibility(lbl_itemPriceSecondRow);
        return distributorUI.getText(lbl_itemPriceSecondRow, "value").replace("$","");
    }
    public Double getItemPriceOnCheckoutButton() throws InterruptedException {
        distributorUI.waitForVisibility(btn_checkout);
        distributorUI.waitForCustom(4000);
        return Double.valueOf(distributorUI.getText(btn_checkout).replace("$",""));
    }
    public void clickPlusQryCatalogSearchValueOne(){
        distributorUI.click(btn_increaseQtyCatalogSearchValueOne);
        distributorUI.waitForClickability(btn_checkout);
        try {
            distributorUI.waitForCustom(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void clickPlusQryCatalogSearchValueTwo() throws InterruptedException {
        distributorUI.click(btn_increaseQtyCatalogSearchValueTwo);
        distributorUI.waitForCustom(4000);
        distributorUI.waitForClickability(btn_checkout);
        distributorUI.waitForCustom(2000);
    }
    public void clickMinusQryCatalogSearchValueOne(){
        distributorUI.click(btn_decreaseQtyCatalogSearchValueOne);
        try {
            distributorUI.waitForCustom(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        distributorUI.waitForElementEnabledState(btn_checkout, false);
    }
    public void clickMinusQryCatalogSearchValueTwo(){
        distributorUI.click(btn_decreaseQtyCatalogSearchValueTwo);
        distributorUI.waitForClickability(btn_checkout);
    }
    public void clickMinusQryCatalogSearchValueThree(){
        distributorUI.click(btn_decreaseQtyCatalogSearchValueThree);
        distributorUI.waitForClickability(btn_checkout);
        try {
            distributorUI.waitForCustom(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
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
//        return Double.valueOf(distributorUI.getText(lbl_itemPriceCartRowOne).split("\\$")[1]);

        distributorUI.waitForVisibility(lbl_cartItemUnitPrice);
        String tagName = distributorUI.getElement(lbl_cartItemUnitPrice).getTagName();
        String priceText;
        if (tagName.equals("input")) {
            priceText = distributorUI.getText(lbl_cartItemUnitPrice, "value");
        } else {
            priceText = distributorUI.getText(lbl_cartItemUnitPrice);
        }

        return Double.valueOf(priceText.replace("$", "").trim());
    }
    public Double getTotalPriceCart() throws InterruptedException {
        distributorUI.waitForCustom(3000);
//        return Double.valueOf(distributorUI.getText(lbl_cartTotal).split("\\$")[1]);
        String priceText = distributorUI.getText(lbl_cartTotal).replace("$", "").replace(",", "").trim();
        return Double.valueOf(priceText);
    }
    public Double getReviewTotalPriceCart() throws InterruptedException {
        distributorUI.waitForCustom(3000);
        String priceText = distributorUI.getText(lbl_cartTotalReview).replace("$", "").replace(",", "").trim();
        return Double.valueOf(priceText);
    }
    public void submitOrder(){
        try {
            distributorUI.waitForCustom(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        distributorUI.waitForClickability(btn_submitOrder);
        distributorUI.click(btn_submitOrder);
    }
    public boolean isDuplicatePopupDisplayed() {
        return distributorUI.isDisplayed(btn_duplicateOrderYes);
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
//        distributorUI.waitForClickability(btn_print);
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
        distributorUI.click(icon_deleteSearchItem);
        distributorUI.clear(tbx_orderGuideSearch);
        distributorUI.waitForCustom(2000);
        distributorUI.sendKeys(tbx_orderGuideSearch,item);
        distributorUI.waitForCustom(2000);
    }
    public void clickOnCreate() throws InterruptedException {
        distributorUI.waitForCustom(1000);
//        distributorUI.waitForClickability(btn_create);
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
        distributorUI.waitForCustom(3000);
        distributorUI.waitForVisibility(btn_addFromCatalog);
        distributorUI.click(btn_addFromCatalog);
        distributorUI.waitForCustom(4000);
    }
    public void clickUploadAList(){
        distributorUI.waitForVisibility(btn_uploadFile);
        distributorUI.click(btn_uploadFile);
    }
    public void clickUploadOrdersOG(){
        distributorUI.waitForVisibility(btn_uploadFileOG);
        distributorUI.click(btn_uploadFileOG);
    }
    public void clickOnAddToOrderGuide()throws InterruptedException{
        distributorUI.waitForCustom(5000);
        distributorUI.waitForVisibility(btn_addToOrderGuide);
        distributorUI.waitForClickability(btn_addToOrderGuide);
        distributorUI.click(btn_addToOrderGuide);
    }
    public void clickOnAddToOrderGuideStable(String name){
        distributorUI.click(By.xpath(lbl_catalogAddToCart.replace("NAME", name)));
    }
    public boolean isAddToOrderGuideDisplayed(String name){
        return distributorUI.isDisplayed(By.xpath(lbl_catalogAddToCart.replace("NAME", name)),10);
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
    public boolean isBroadcastMessageDisplayed(String message) {
//        distributorUI.waitForVisibility(By.xpath(msg_banner.replace("TESTMESSAGE",message)));
        return distributorUI.isDisplayed(By.xpath(msg_banner.replace("TESTMESSAGE",message)));
    }
    public void clickMessage(String message){
        distributorUI.click(By.xpath(msg_banner.replace("TESTMESSAGE",message)));
    }
    public boolean isProductDetailsDisplayed(){
        try {
            distributorUI.waitForCustom(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
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
    public void clickSearchedItemStable(String code){
        distributorUI.click((By.xpath(lbl_searchedItemStable.replace("CODE", '#'+code))));
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
    public boolean isRecommendedForYouItemNameDisplayed(String code){
        return distributorUI.isDisplayed(By.xpath(lbl_recommendedForYouItem.replace("CODE",code )));
    }
    public boolean isRecommendedBySalesRepDisplayed(String code) {
        distributorUI.scrollToElementStable(By.xpath(lbl_recommendedBySalesRep.replace("CODE", '#'+code)),5);
        return distributorUI.isDisplayed(By.xpath(lbl_recommendedBySalesRep.replace("CODE", '#'+code)));
    }
    public boolean isDontForgetToOrderDisplayed(){
        distributorUI.scrollToElementStable(section_dontForget,3);
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
        distributorUI.refreshPage();
        distributorUI.waitForVisibility(tbx_orderGuideSearch);
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
        try {
            distributorUI.waitForCustom(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        distributorUI.clickWithFallback(btn_edit);
    }
    public boolean isEditOrderGuideTextDisplayed(){
       /* try {
            distributorUI.waitForVisibility(txt_editOrderGuide);
        } catch (Exception e){
            return false;
        }*/
//        distributorUI.waitForVisibility(txt_editOrderGuide);
        return distributorUI.isDisplayed(txt_editOrderGuide);
    }
    public void clickOnMoreOptions(){
        distributorUI.waitForClickability(btn_moreOptions);
//        distributorUI.click(btn_moreOptions);
        distributorUI.clickWithScrollAndHover(btn_moreOptions);
        distributorUI.uiScrollTop();
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
        try {
            distributorUI.waitForCustom(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void selectItemCategories(){
        distributorUI.waitForVisibility(dropdown_itemCategories);
        distributorUI.click(dropdown_itemCategories);
        try {
            distributorUI.waitForCustom(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void selectItemCodeSort(){
        distributorUI.waitForVisibility(dropdown_itemCode);
        distributorUI.click(dropdown_itemCode);
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
        distributorUI.waitForVisibility(txt_minOrderBanner,60);
//        distributorUI.waitForVisibility(txt_minOrderBanner);
        distributorUI.refreshPage();
        return distributorUI.isDisplayed(txt_minOrderBanner);
    }
    public boolean isOrderMinPopupDisplayed(){
        distributorUI.waitForVisibility(txt_popupAlertOrderMin);
        return distributorUI.isDisplayed(txt_popupAlertOrderMin);
    }

    public boolean isCaseMinimumPopUpDisplayed(){
        distributorUI.waitForVisibility(caseMinimumNotMetText);
        return distributorUI.isDisplayed(caseMinimumNotMetText);
    }

    public void clickOnCustomerCode(String code) {
        distributorUI.click(By.xpath(txt_customerCode.replace("CODE", code)));
    }
    public void clickOnOrdersTab() {
        if (!distributorUI.isDisplayed(tb_orders)) {
            distributorUI.refreshPage();
        }
        distributorUI.click(tb_orders);
    }
    public boolean isStandingOrdersDisplayed(){
        distributorUI.waitForVisibility(txt_standingOrders);
        return distributorUI.isDisplayed(txt_standingOrders);
    }

    public boolean isAlreadySetStandingOrdersAvailable(){
        return distributorUI.isDisplayed(created_StandingOrders);
    }

    public int getStandingOrdersCount(){
        return distributorUI.countElements(created_StandingOrders);
    }

    public void clickOnCreateStandingOrder() {
        distributorUI.click(btn_createStandingOrders);
    }
    public void clickOnDropdownDelivery() {
        distributorUI.click(dropdown_delivery);
    }
    public void clickOnDropDownPickUp(){
        distributorUI.click(dropdown_pickUp);
    }
    public void clickOnDeliveryDate(String day) {
        distributorUI.waitForVisibility(By.xpath(txt_deliveryDay.replace("DAY", day)));
        distributorUI.click(By.xpath(txt_deliveryDay.replace("DAY", day)));
        distributorUI.waitForElementEnabledState(By.xpath(txt_deliveryDay.replace("DAY", day)),true);
    }
    public void clickOnDeliveryDateAsLast() {
        distributorUI.waitForVisibility(By.xpath(txt_deliveryLastDay));
        distributorUI.click(By.xpath(txt_deliveryLastDay));
        distributorUI.waitForElementEnabledState(By.xpath(txt_deliveryLastDay),true);
    }
    public void clickOnDeliveryDateAsLastBefore() {
        distributorUI.waitForVisibility(By.xpath(txt_deliveryLastBeforeDay));
        distributorUI.click(By.xpath(txt_deliveryLastBeforeDay));
        distributorUI.waitForElementEnabledState(By.xpath(txt_deliveryLastBeforeDay),true);
    }
    public void clickOnPickUpDateAsLast() {
        distributorUI.waitForVisibility(By.xpath(txt_pickUpLastDay));
        distributorUI.click(By.xpath(txt_pickUpLastDay));
        distributorUI.waitForElementEnabledState(By.xpath(txt_pickUpLastDay),true);
    }
    public void setStandingOrder(){
        distributorUI.waitForElementEnabledState(btn_setStandingOrder,true);
        distributorUI.waitForClickability(btn_setStandingOrder);
        try {
            distributorUI.waitForCustom(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        distributorUI.click(btn_setStandingOrder);
    }
    public boolean isStandingOrderEmailPopupDisplayed(){
        try {
            distributorUI.waitForCustom(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
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
        try {
            distributorUI.waitForCustom(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean areStandingOrdersDeleted(){
        return distributorUI.isDisplayed(btn_deleteStandingOrders);
    }
    public void clickPlusQryFirstRowInDist() throws InterruptedException {
        int columnCount = distributorUI.countElements(lbl_itemRowCount);
        for (int i = 1; i <= columnCount ; i++) {
            String actualColumnName = distributorUI.getText(By.xpath(lbl_columnName.replace("COLUMN",String.valueOf(i))));
            if (actualColumnName.equalsIgnoreCase("Quantity")) {
                distributorUI.waitForCustom(1000);
                distributorUI.click(By.xpath(btn_PlusQtyFirstRowInDist.replace("COLUMN",String.valueOf(i))));
            }
        }
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
        try {
            distributorUI.waitForCustom(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        };
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
        distributorUI.waitForClickability(lbl_firstRowOrderTab);
        distributorUI.click(lbl_firstRowOrderTab);
    }
    public void clickSouthwestTraders(){
        distributorUI.waitForClickability(txt_southwest);
        distributorUI.click(txt_southwest);
        if(distributorUI.isDisplayed(editExistingOrderTxt)){
            distributorUI.click(cancelBtn);
        }
    }
    public boolean isSubstitutesPopupDisplayed(){
        return distributorUI.isDisplayed(txt_substitutions);
    }
    public boolean isSubstitutesPopupDisplayedSub(){
        return distributorUI.isDisplayed(txt_substitutions,5);
    }
    public boolean isSubstitutesItemPopupDisplayedSub(){
        return distributorUI.isDisplayed(txt_substitutionsItem,5);
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
    public boolean isReplacementNotDisplayed(){
        return distributorUI.isDisplayed(lbl_NotSelected,5);
    }
    public boolean isDoNotSubstituteDisplayed(){
        return distributorUI.isDisplayed(lbl_doNotSubstitute);
    }
    public void clickOnItem(String code){
        distributorUI.waitForVisibility(By.xpath(txt_item.replace("CODE", code)));
        distributorUI.clickUsingJavaScript(By.xpath(txt_item.replace("CODE", code)));
    }
    public void clickOnSingleItem(){
        distributorUI.waitForVisibility(lbl_SubstituteItem);
        distributorUI.clickUsingJavaScript(lbl_SubstituteItem);
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
    public int getSubstituteItemsCount() {
        return distributorUI.countElements(subItemsCount);
    }
    public boolean isCutomerTxtDisplayed(){
        return distributorUI.isDisplayed(CustomerTxt);
    }
    public void ClickTestAutomationOrderGuide(){
        if (!distributorUI.isDisplayed(dropdown_testAutomation)) {
            distributorUI.waitForVisibility(Test_AutomationOrderGuide);
            distributorUI.click(Test_AutomationOrderGuide);
            distributorUI.click(AutomationGuide);
        }

    }
    public boolean StockCountDisplayed(){
        return distributorUI.isDisplayed(StockCountTxt);
    }
    public boolean isCustomerGroupTxtDisplayed(){
        distributorUI.waitForVisibility(CustomerGroupTxt);
        return distributorUI.isDisplayed(CustomerGroupTxt);
    }
    /*public boolean areOutOfStockItemsDisplayed(){
        try {
            distributorUI.isDisplayed(txt_outOfStock);
        }
        catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(txt_outOfStock);
    }*/
    public boolean areOutOfStockItemsDisplayed() {
        try {
            if (!distributorUI.isDisplayed(txt_outOfStock)) {
                return false;
            }
            int count = distributorUI.countElements(txt_outOfStock);
            return count > 2;
        } catch (Exception e) {
            return false;
        }
    }
    public void clickOnUnitEach(){
        /*distributorUI.waitForVisibility(By.xpath(multiUomDropDownOGExist.replace("CODE", code)));
        distributorUI.click(By.xpath(multiUomDropDownOGExist.replace("CODE", code)));*/
        distributorUI.click(txt_unitInDist);
        distributorUI.waitForVisibility(txt_eachDropdownItem);
        distributorUI.click(txt_eachDropdownItem);
    }
    public void clickOnUnitCase(){
        /*distributorUI.waitForVisibility(By.xpath(multiUomDropDownOGExist.replace("CODE", code)));
        distributorUI.click(By.xpath(multiUomDropDownOGExist.replace("CODE", code)));*/
        distributorUI.click(txt_unitInDist);
        distributorUI.waitForVisibility(txt_caseDropdownItem);
        distributorUI.click(txt_caseDropdownItem);
    }
    public void clickOnOrderGuideInProf(){
//        distributorUI.waitForVisibility(btn_orderGuide);
        distributorUI.waitForClickability(btn_orderGuide);
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
        distributorUI.refreshPage();
    }
    public void clickOnDeleteItem(){
        distributorUI.waitForVisibility(btn_deleteIcon);
        distributorUI.click(btn_deleteIcon);
        distributorUI.waitForInvisibility(btn_deleteIcon);
    }
    public void clickOnBoostTab() {
        distributorUI.click(tb_boost);
    }
    public void clickOnTrackTab() throws InterruptedException {
        distributorUI.click(tb_track);
        distributorUI.waitForCustom(2000);
    }
    public boolean isBroadcastTextDisplayed(){
        distributorUI.waitForVisibility(txt_customerSpecific);
        return distributorUI.isDisplayed(txt_customerSpecific);
    }
    public boolean isProfileTextDisplayed(){
        return distributorUI.isDisplayed(txt_profile);
    }
    public void clickOnEditMessage(){
        distributorUI.waitForVisibility(btn_editMessage);
        distributorUI.click(btn_editMessage);
        try {
            distributorUI.waitForCustom(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void clickOnClearMessage() throws InterruptedException {
        distributorUI.waitForVisibility(btn_clearMessage);
        distributorUI.click(btn_clearMessage);
        distributorUI.waitForCustom(3000);
    }
    public void clickOnAddItems() {
        distributorUI.waitForVisibility(btn_addItems);
        distributorUI.click(btn_addItems);
    }
    public void clickOnSaveMessage() throws InterruptedException {
        distributorUI.waitForVisibility(btn_saveMessage);
        distributorUI.click(btn_saveMessage);
        distributorUI.waitForCustom(3000);
    }
    public void typeBroadcastMessage(String msg) {
        distributorUI.click(txtArea);
        distributorUI.sendKeys(txtArea, msg);
    }
    public void selectItem(String code) throws InterruptedException {
        distributorUI.waitForCustom(3000);
        distributorUI.click(input_selectItem);
        distributorUI.sendKeys(input_selectItem, code);
        distributorUI.hoverOverElement(By.xpath(txt_item.replace("CODE", code+" :")));
        distributorUI.click(By.xpath(txt_item.replace("CODE", code+" :")));
    }
    public void clickOnAdd() {
        distributorUI.waitForVisibility(btn_add);
        distributorUI.click(btn_add);
    }
    public boolean isItemAdded(String code){
        return distributorUI.isDisplayed(By.xpath(txt_item.replace("CODE", code)));
    }
    public boolean isItemValueAdded(String code){
        return distributorUI.isDisplayed(By.xpath(itemValue.replace("CODE", code)));
    }

    public boolean isItemPercentageAdded(String code){
        return distributorUI.isDisplayed(By.xpath(txt_itemPercentage.replace("CODE", code)));
    }
    public void clickOnRemoveItem(String Itemcode) {
        distributorUI.waitForVisibility(By.xpath(btn_removeItem.replace("ITEMCODE",Itemcode)));
        distributorUI.click(By.xpath(btn_removeItem.replace("ITEMCODE",Itemcode)));
        distributorUI.waitForInvisibility(By.xpath(btn_removeItem.replace("ITEMCODE",Itemcode)));
        distributorUI.refreshPage();
    }

    public boolean isExistItemDisplayed(String Itemcode){
        return distributorUI.isDisplayed(By.xpath(btn_removeItem.replace("ITEMCODE",Itemcode)));
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
        distributorUI.clickWithFallback(btn_save);
        try {
            distributorUI.waitForCustom(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void typeSectionName(String name) throws InterruptedException {
        distributorUI.click(tbx_section);
        distributorUI.waitForCustom(1000);
        distributorUI.sendKeys(tbx_section, name);
    }
    public boolean isAddedSectionDisplayed(String name) throws InterruptedException {
        distributorUI.refreshPage();
        distributorUI.waitForCustom(3000);
//        distributorUI.waitForVisibility(By.xpath(txt_addedSection.replace("NAME", name)));
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
        try {
            distributorUI.waitForCustom(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        distributorUI.waitForVisibility(By.xpath(btn_editSection.replace("NAME", name)));
        distributorUI.click(By.xpath(btn_editSection.replace("NAME", name)));
    }
    public boolean isEditSectionPopupDisplayed(){
        distributorUI.waitForVisibility(txt_editSection);
        return distributorUI.isDisplayed(txt_editSection);
    }
    public void clickOnDelete(){
        try {
            distributorUI.waitForCustom(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
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
        distributorUI.click(Savebtn);
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
    public void clickOnEditItem(String name) {
        distributorUI.waitForVisibility(By.xpath(btn_editItem.replace("NAME", name)));
        distributorUI.click(By.xpath(btn_editItem.replace("NAME", name)));
    }
    public String getItemPricePDPView(){
        return distributorUI.getText(txt_pricePDP).replace("$","");
    }
    public boolean isCatalogImageDisplayed(){
        distributorUI.waitForVisibility(img_catalog);
        return distributorUI.isDisplayed(img_catalog);
    }
    public void clickOnCatalogItem(String name){
        distributorUI.waitForVisibility(By.xpath(txt_catalogItem.replace("NAME", name)));
        distributorUI.clickUsingJavaScript(By.xpath(txt_catalogItem.replace("NAME", name)));
    }
    public String getItemNamePDPView(String itemName) throws InterruptedException {
        distributorUI.waitForCustom(4000);
        return distributorUI.getText(By.xpath(txt_namePDP.replace("ITEMNAME",itemName.toLowerCase()))).toLowerCase();
//        return distributorUI.getText(txt_namePDP);
    }
    public boolean isOrdersTxtDisplayed(){
        return distributorUI.isDisplayed(lbl_orders);
    }
    public boolean isAllItemsTxtDisplayed(){
        return distributorUI.isDisplayed(txt_allItems);
    }
    public String getItemDetailsFirstRow() throws InterruptedException {
        distributorUI.waitForElementEnabledState(lbl_itemDetails,true);
        distributorUI.waitForCustom(3000);
        return distributorUI.getText(lbl_itemDetails);
    }
    public int getCountZeroPriceItemsDisplayed(){
        try {
            distributorUI.waitForCustom(4000);
        } catch (InterruptedException e) {
            return 1;
        }
        return distributorUI.countElements(txt_priceZero);
    }

    public boolean isOrderMinimumOverlayDisplayed(){
        return distributorUI.isDisplayed(minimumOrderOverlay);
    }

    public boolean isCatalogAccessEnabled(){
        return distributorUI.isDisplayed(catalogAccessEnableTxt);
    }

    public boolean isCatalogAccessDisabled(){
        distributorUI.refreshPage();
        return distributorUI.isDisplayed(catalogAccessDisableTxt);
    }

    public void saveCatalogAccessChanges(){
        distributorUI.clickWithScrollAndHover(Savebtn);
        try {
            distributorUI.waitForCustom(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isCatalogSectionDisplayInOrderGuide(){
        return distributorUI.isDisplayed(catalogSectionInOrderGuide);
    }

    public boolean isCatalogAccessEnableDisplayed(){
        return distributorUI.isDisplayed(lbl_catalogAccessEnable,10);
    }

    public void clickEditCatalogAccess(){
        distributorUI.click(catalogAccessEditBtn);
    }

    public void clickOnDisableCatalogAccessOption(){
        distributorUI.click(catalogAccessEnableOption);
        distributorUI.waitForVisibility(catalogAccessDisableOption);
        distributorUI.click(catalogAccessDisableOption);
    }

    public void clickOnEnableCatalogAccessOption(){
        distributorUI.click(catalogAccessDisableOption);
        distributorUI.waitForVisibility(catalogAccessEnableOption);
        distributorUI.click(catalogAccessEnableOption);
    }

    public boolean isOrderApprovalOptionDisplayed(){
        return distributorUI.isDisplayed(orderApprovalTxt);
    }

    public void clickOnOrderApprovalEditBtn(){
        distributorUI.click(orderApprovalEditBtn);
    }

    public boolean isOrderApprovalSettingsOverlayDisplayed(){
        return distributorUI.isDisplayed(orderApprovalSettingsOverlayTxt);
    }

    public boolean isNewlyCreatedOrderGuideApprovalStatusDisplayed(){
        return distributorUI.isDisplayed(orderApprovalSettingsOverlayNewlyCreatedOGOptionsEnabledOrDisabled);
    }

    public boolean isExistingOrderGuidesDisplayed(String orderGuideName){
        return distributorUI.isDisplayed(By.xpath(orderApprovalSettingsOverlayOrderGuideTxt.replace("ORDERGUIDE",orderGuideName)));
    }

    public void clickCloseOnOrderApprovalSettingsOverlay(){
        distributorUI.click(orderApprovalSettingsOverlayCloseBtn);
    }

    public void clickTurnOnOrderApprovalForOrderGuide(String OrderGuideName){
        if(distributorUI.isDisplayed(By.xpath(orderGuideOrderApprovalDisabledBtn.replace("ORDERGUIDE",OrderGuideName)))){
            distributorUI.click(By.xpath(orderGuideOrderApprovalToggle.replace("ORDERGUIDE",OrderGuideName)));
        }
    }

    public void clickTurnOffOrderApprovalForOrderGuide(String OrderGuideName){
        if(distributorUI.isDisplayed(By.xpath(orderGuideOrderApprovalEnabledBtn.replace("ORDERGUIDE",OrderGuideName)))){
            distributorUI.click(By.xpath(orderGuideOrderApprovalToggle.replace("ORDERGUIDE",OrderGuideName)));
        }
    }
    public boolean isSalespersonEditable(){
        try {
            distributorUI.click(btn_editSalesperson);
            distributorUI.click(btn_close);
            return true;
        } catch (Exception e){
            return false;
        }
    }
    public boolean isAbleToInviteUsers() {
        try {
            distributorUI.click(InviteNewUsersBtn);
            distributorUI.click(btn_close);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public boolean isAbleToEditMsg() {
        try {
            distributorUI.click(btn_editMessage);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public void clickOnIndependentFoods(){
        distributorUI.waitForVisibility(btn_independentFoods);
        distributorUI.click(btn_independentFoods);
    }

    public void clickItemFromCatalogIfNotAvailableInOG(String itemName){
        if(distributorUI.isDisplayed(By.xpath(itemNotFoundTxt.replace("ITEMCODE",itemName)))){
            distributorUI.click(By.xpath(catalogCardAddToOGBtn.replace("ITEMCODE",itemName)));
        }
        try {
            distributorUI.waitForCustom(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean isAccountHoldsNoneDisplayed(){
        return distributorUI.isDisplayed(lbl_AccountHoldsNone,10);
    }
    public void clickOnEditAccHolds(){
        try {
            distributorUI.waitForCustom(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        distributorUI.waitForVisibility(btn_editAccHold);
        distributorUI.click(btn_editAccHold);
    }
    public void clickOnAccDropdown(){
        distributorUI.waitForVisibility(dropdown_acc);
        distributorUI.click(dropdown_acc);
    }
    public void clickOnHardHold(){
        distributorUI.waitForVisibility(txt_hardHold);
        distributorUI.click(txt_hardHold);
    }
    public boolean isHardHoldSelected(){
        return distributorUI.isDisplayed(lbl_hardHold);
    }
    public boolean isHardHoldPopupDisplayed(){
        return distributorUI.isDisplayed(txt_hardHoldPopup);
    }
    public void clickOnNone(){
        distributorUI.waitForVisibility(txt_none);
        distributorUI.click(txt_none);
    }
    public boolean isNoneSelected(){
        return distributorUI.isDisplayed(lbl_none);
    }
    public boolean isRemoveHoldPopupDisplayed(){
        return distributorUI.isDisplayed(txt_removeHold);
    }
    public boolean isCustomerOnHold(){
        return distributorUI.isDisplayed(customer_Holds);
    }
    public void selectPickUpWillCall(){
        distributorUI.waitForVisibility(lbl_pickUp);
        distributorUI.click(lbl_pickUp);
    }
    public void selectMailDelivery(){
        distributorUI.waitForVisibility(lbl_mailDelivery);
        distributorUI.click(lbl_mailDelivery);
    }

    public boolean isCustomersTextDisplayed(){
        try {
            distributorUI.waitForVisibility(txt_customers);
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(txt_customers);
    }
    public void clickSalespersonDropDown(){
        distributorUI.click(btn_salesperson);
    }
    public void clickSalespersonOption(){
        distributorUI.click(btn_salespersonOption);
    }
    public boolean isSalespersonNameDisplayed(String salesperson){
        return distributorUI.isDisplayed(By.xpath(salespersonName.replace("SALESPERSON",salesperson)));
    }
    public void clickManageCustomers(){
        distributorUI.click(btn_manageCustomers);
    }
    public void clickExportCustomers(){
        distributorUI.click(btn_exportCustomers);
    }
    public boolean isExportCustomersPopUpDisplayed(){
        try {
            distributorUI.waitForVisibility(txt_exportCustomersPopUp);
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(txt_exportCustomersPopUp);
    }
    public boolean isGeneratingReportPopUpDisplayed(){
        try {
            distributorUI.waitForVisibility(txt_generatingReport);
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(txt_generatingReport);
    }
    public void clickExportOrderGuides(){
        distributorUI.click(btn_exportOrderGuidesCSV);
    }
    public boolean isExportOrderGuidesPopUpDisplayed(){
        try {
            distributorUI.waitForVisibility(txt_exportOrderGuidesCSVPopUp);
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(txt_exportOrderGuidesCSVPopUp);
    }
    public void clickMoreFilters(){
        distributorUI.click(btn_moreFilters);
    }
    public boolean isFilterCustomersPopUpDisplayed(){
        try {
            distributorUI.waitForVisibility(txt_filterCustomersPopUp);
            distributorUI.waitForCustom(2000);
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(txt_filterCustomersPopUp);
    }
    public void clickSignUpStatus() throws InterruptedException {
        distributorUI.click(btn_signUpStatus);
        distributorUI.waitForCustom(2000);
    }
    public void clickSignUpOption(){
        distributorUI.click(btn_signUpOption);
    }
    public void clickApply() throws InterruptedException {
        distributorUI.clickWithScrollAndHover(btn_apply);
        distributorUI.waitForCustom(2000);
    }
    public boolean isStatusDisplayed(String status){
        return distributorUI.isDisplayed(By.xpath(signUpStatus.replace("STATUS",status)));
    }
    public void clickAddNewCustomer(){
        distributorUI.click(btn_addNewCustomer);
    }
    public boolean isAddNewCustomerPopUpDisplayed(){
        try {
            distributorUI.waitForVisibility(txt_addNewCustomer);
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(txt_addNewCustomer);
    }
    public void typeCustomerName(String customerName)throws InterruptedException {
        distributorUI.sendKeys(txt_customerName,customerName);
        distributorUI.waitForCustom(800);
    }
    public void clickContinue(){
        distributorUI.click(btn_continue);
    }
    public void typeCustomerCity(String customerCity)throws InterruptedException {
        distributorUI.sendKeys(txt_city,customerCity);
        distributorUI.waitForCustom(800);
    }
    public void clickCreateCustomer(){
        distributorUI.click(btn_createCustomer);
    }
    public boolean isCreatedCustomerPopUpDisplayed(){
        try {
            distributorUI.waitForVisibility(txt_customerCreatedPopUp);
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(txt_customerCreatedPopUp);
    }
    public void clickClosePopUp(){
        distributorUI.click(btn_closePopUp);
    }
    public void typeOnSearchCustomerName(String customerName) throws InterruptedException {
        distributorUI.clear(tbx_searchCustomers);
        distributorUI.waitForCustom(1000);
        distributorUI.sendKeys(tbx_searchCustomers, customerName);
    }
    public boolean isNewCustomerDisplayed(String customerName){
        return distributorUI.isDisplayed(By.xpath(NewCustomerName.replace("CUSTOMERNAME",customerName)));
    }

    public void selectCustomer() throws InterruptedException {
        distributorUI.waitForVisibility(lbl_firstRecord);
        distributorUI.click(btn_selectCustomer);
        distributorUI.waitForCustom(2000);
    }
    public void clickBulkActions(){
        distributorUI.click(btn_bulkAction);
    }
    public void clickInviteUser(){
        distributorUI.click(btn_inviteUser);
    }
    public boolean isInviteUserPopUpDisplayed(){
        try {
            distributorUI.waitForVisibility(txt_inviteUserPopUp);
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(txt_inviteUserPopUp);
    }
    public void clickOptionAll(){
        distributorUI.click(btn_allOption);
    }
    public void clickInviteEmail(){
        distributorUI.click(btn_inviteEmail);
    }

    public String getBusinessName(String customerId){
        distributorUI.waitForVisibility(By.xpath(businessName.replace("CUSTOMERID",customerId)));
        return distributorUI.getText(By.xpath(businessName.replace("CUSTOMERID",customerId))).replace("Child", "").trim();
    }
    public boolean isCustomerProfileDisplayed(String businessName){
        String result = businessName.substring(0, businessName.indexOf("Test"));
        System.out.println(result);
        try {
            distributorUI.waitForCustom(3000);
            distributorUI.waitForVisibility(By.xpath(txt_customerProfile.replace("BUSINESSNAME",result)));
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(By.xpath(txt_customerProfile.replace("BUSINESSNAME",result)));
    }
    public void clickMoreOption(){
        distributorUI.click(btn_moreOption);
        try {
            distributorUI.waitForCustom(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void clickEditOrderGuide(){
        distributorUI.click(btn_editOrderGuide);
    }
    public void clickNo()throws InterruptedException{
        distributorUI.waitForVisibility(btn_no);
        distributorUI.click(btn_no);
    }
    public boolean isCustomerOrderGuideDisplayed(){
        try {
            distributorUI.waitForVisibility(btn_catalogToOrderGuide);
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(btn_catalogToOrderGuide);
    }
    public void clickPreviewCatalog(){
        distributorUI.click(btn_previewCatalog);
    }
    public boolean isCatalogPreviewSectionDisplayed(){
        try {
            distributorUI.waitForVisibility(txt_previewCatalog);
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(txt_previewCatalog);
    }
    public void clickChat(){
        distributorUI.click(btn_chat);
    }
    public boolean isChatSectionDisplayed(){
        try {
            distributorUI.waitForVisibility(txt_chatArea);
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(txt_chatArea);
    }
    public void clickPause(){
        distributorUI.click(btn_pauseStandingOrders);
    }

    public boolean isStandingOrdersPaused(){
        try {
            distributorUI.waitForVisibility(txt_pausedStandingOrders);
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(txt_pausedStandingOrders);
    }
    public void clickResume(){
        distributorUI.click(btn_resumeStandingOrders);
    }
    public void clickThreeDot() throws InterruptedException{
        distributorUI.click(btn_threeDot);
    }
    public void clickPrintReceipt() throws InterruptedException{
        distributorUI.click(btn_printKitchenReceipt);
    }
    public void clickOrderConfirmation() throws InterruptedException{
        distributorUI.click(btn_orderConfirmation);
    }
    public void clickOrder(){
        distributorUI.click(btn_order);
    }
    public boolean isOrderSectionDisplayed(){
        try {
            distributorUI.waitForVisibility(txt_order);
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(txt_order);
    }
    public void clickDraftsTab(){
        distributorUI.click(tb_drafts);
    }
    public boolean isOrderDraftDisplayed(){
        try {
            distributorUI.waitForVisibility(txt_draftStatus);
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(txt_draftStatus);
    }
    public void clickDeleteDraft(){
        distributorUI.click(btn_deleteDraft);
    }

    public void enterStopDuration(String msg) throws InterruptedException {
        distributorUI.clickUsingJavaScript(lbl_stopDuration);
        distributorUI.clear(lbl_stopDuration);
        distributorUI.sendKeys(lbl_stopDuration, msg);
        distributorUI.waitForCustom(4000);
    }
    public void enterKeyDropNum(String msg) throws InterruptedException {
        distributorUI.click(lbl_keyDropNum);
        distributorUI.clear(lbl_keyDropNum);
        distributorUI.sendKeys(lbl_keyDropNum, msg);
        distributorUI.waitForCustom(2000);
    }
    public void enterDeliveryNotes(String msg) throws InterruptedException {
        distributorUI.click(lbl_deliveryNotes);
        distributorUI.clear(lbl_deliveryNotes);
        distributorUI.sendKeys(lbl_deliveryNotes, msg);
        distributorUI.waitForCustom(2000);
    }

    public void enterDoorDesc(String msg) throws InterruptedException {
        distributorUI.click(lbl_DoorDesc);
        distributorUI.clear(lbl_DoorDesc);
        distributorUI.sendKeys(lbl_DoorDesc, msg);
        distributorUI.waitForCustom(2000);
    }
    public String isStopDurationUpdated() throws InterruptedException {
        return distributorUI.getText(txt_stopDuration,"value");
    }
    public void clickOGDropdown(){
        distributorUI.waitForVisibility(lbl_orderGuide);
        distributorUI.click(lbl_orderGuide);
    }
    public void selectTestOrderGuide1(){
        distributorUI.waitForVisibility(dropdown_testGuide1);
        distributorUI.click(dropdown_testGuide1);
    }
    public void selectTestAutomation(){
        distributorUI.waitForVisibility(dropdown_testAutomation);
        distributorUI.click(dropdown_testAutomation);
    }
    public void editMargin(){
        distributorUI.click(btn_editMargin);
    }
    public void resetMarginValues() throws InterruptedException {
        distributorUI.click(btn_resetValues);
        distributorUI.waitForCustom(2000);
    }
    public void updateMarginValues() throws InterruptedException {
        distributorUI.click(btn_updateValues);
        distributorUI.waitForCustom(5000);
    }
    public boolean isMarginValuePopupDisplayed(){
        return distributorUI.isDisplayed(lbl_margin);
    }
    public void enterMarginValue(String num) throws InterruptedException {
        distributorUI.clearUsingJavaScript(lbl_margin);
//        distributorUI.waitForCustom(1000);
        distributorUI.sendKeys(lbl_margin, num);
//        distributorUI.sendKeysAndEnterMac(lbl_margin, num);
        distributorUI.waitForCustom(1000);
    }
    public void enterMarginPercentage(String num) throws InterruptedException {
        distributorUI.clearUsingJavaScript(lbl_marginPercentage);
//        distributorUI.sendKeysAndEnterMac(lbl_marginPercentage, num);
        distributorUI.sendKeys(lbl_marginPercentage, num);
        distributorUI.waitForCustom(1000);
    }
    public void clickPlusQryFirstRowBySix() {
        for (int i = 0; i < 6; i++) {
            distributorUI.click(btn_increaseQtyFirstRow);
        }
    }
    public void clickPlusQryFifthRowBySix() {
        for (int i = 0; i < 6; i++) {
            distributorUI.click(btn_increaseQtyFifthRow);
        }
    }

    public void clickOneCustomer(String cusCode)throws InterruptedException{
        distributorUI.click(By.xpath(sel_customer.replace("CUSTOMERCODE", cusCode)));
    }

    public boolean isCustomerNameDisplayed(){
        return distributorUI.isDisplayed(txt_cusName);
    }
    public void clickCustomerNameEditIcon(){distributorUI.click(btn_editCusName);}

    public void editCustomerName(String editCustomerName)throws InterruptedException{
        distributorUI.waitForClickability(tbx_editCusName);
        distributorUI.clear(tbx_editCusName);
        distributorUI.sendKeys(tbx_editCusName,editCustomerName);
    }

    public void clickEditCusNameSave(){distributorUI.click(btn_saveEditCusName);}
    public void clickShippingAddressEditIcon(){distributorUI.click(btn_editShipAddress);}

    public boolean isEditShippingAddressTextDisplayed(){
        return distributorUI.isDisplayed(txt_editShipAddress);
    }

    public void editStreetName(String editStreet)throws InterruptedException {
        distributorUI.clear(tbx_editStreet);
        distributorUI.sendKeys(tbx_editStreet,editStreet);
        distributorUI.waitForCustom(800);
    }

    public void editCityName(String editCityName)throws InterruptedException {
        distributorUI.clear(tbx_editCity);
        distributorUI.sendKeys(tbx_editCity,editCityName);
        distributorUI.waitForCustom(800);
    }

    public void editStateName(String editState)throws InterruptedException {
        distributorUI.clear(tbx_editState);
        distributorUI.sendKeys(tbx_editState,editState);
        distributorUI.waitForCustom(800);
    }

    public void editZipCode(String editZipNum)throws InterruptedException {
        distributorUI.clear(tbx_editZipCode);
        distributorUI.sendKeys(tbx_editZipCode,editZipNum);
        distributorUI.waitForCustom(800);
    }

    public void clickEditShipAddressDetailsSave(){distributorUI.click(btn_saveEditShipAddress);}

    public void clickEditNoteIcon(){distributorUI.click(btn_editNote);}

    public void addNote(String addNote)throws InterruptedException {
        distributorUI.sendKeys(txtarea_editNote,addNote);
        distributorUI.waitForCustom(800);
    }

    public void clickAddNoteSave(){distributorUI.click(btn_saveNote);}

    public void editNote(String editCusNote)throws InterruptedException {
        distributorUI.sendKeys(txtarea_editNote,editCusNote);
        distributorUI.waitForCustom(800);
    }

    public void clickEditSalespersonIcon(){distributorUI.click(btn_editSalesperson);}

    public boolean isAssignSalespersonsTextDisplayed(){
        return distributorUI.isDisplayed(txt_assignSalesperson);
    }

    public void clickAssignSalespersonDropdownArrow(){distributorUI.click(dropdown_assignSalesperson);}
    public void clickOneSalespersonOption(){distributorUI.click(sel_salesperson);}

    public boolean isAssignedSalespersonDisplayed(){
        return distributorUI.isDisplayed(txt_assignedSalesperson);
    }


    public void clickSalespersonSaveChanges(){distributorUI.click(btn_saveChanges);}

    public boolean isAddedSalespersonDisplayed(){
        String assignedSalespersonName = distributorUI.getText(txt_assignedSalesperson);
        String cusProfileSalespersonName = distributorUI.getText(txt_cusProfSalesperson);
        try {
            return assignedSalespersonName.equals(cusProfileSalespersonName) && assignedSalespersonName.equals("Ali Loynachan");
        } catch (Exception e){
            return false;
        }
    }

    public void clickAssignedSalespersonRemoveIcon(){distributorUI.click(btn_removeSalesperson);}

    public boolean isAssignedSalespersonDeleted() {
        try {
            return !distributorUI.isDisplayed(txt_assignedSalesperson);
        } catch (NoSuchElementException e) {
            return true;
        }
    }
    public void clickNewArrivals()throws InterruptedException{
        distributorUI.click(newArrivalsOption);
        distributorUI.waitForCustom(1000);
    }
    public void clickAllItems()throws InterruptedException{
        distributorUI.click(allItemsOption);
        distributorUI.waitForCustom(1000);
    }
    public void clickBrand()throws InterruptedException{
        distributorUI.click(brandDropDown);
        distributorUI.click(brandDropDownOption);
    }
    public boolean isFilteredBrandDisplayed(){
        try {
            distributorUI.waitForVisibility(txt_filterByBrand);
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(txt_filterByBrand);
    }
    public void clickItemStatus()throws InterruptedException{
        distributorUI.click(itemStatusDropDown);
        distributorUI.click(itemStatusDropDownOption);
    }
    public void clickStorageType()throws InterruptedException{
        distributorUI.click(storageTypeDropDown);
        distributorUI.click(storageTypeDropDownOption);
    }
    public void clickDietType()throws InterruptedException{
        distributorUI.click(dietTypeDropDown);
        distributorUI.click(dietTypeDropDownOption);
    }
    public boolean isFilterItemDisplayed(){
        try {
            distributorUI.waitForVisibility(txt_filterItem);
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(txt_filterItem);
    }
    public void clickProcessingType()throws InterruptedException{
        distributorUI.click(processingTypeDropDown);
        distributorUI.click(processingTypeDropOption);
    }
    public boolean isFilterProcessingTypeWork(){
        try {
            distributorUI.waitForVisibility(txt_noItems);
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(txt_noItems);
    }
    public void clickClearAllFilters(){
        distributorUI.click(btn_clearAllFilters);
    }
    public void clickRadioButton(){
        distributorUI.click(radioButton);
    }
    public void clickOnProduct(String name){
        distributorUI.waitForVisibility(By.xpath(txt_product.replace("NAME", name)));
        distributorUI.clickUsingJavaScript(By.xpath(txt_product.replace("NAME", name)));
    }
    public void clickAddToCart(){
        distributorUI.click(btn_addToCartPDP);
    }
    public void clickCheckOutPDP(){
        distributorUI.isDisplayed(btn_checkOutPDP,30);
        distributorUI.click(btn_checkOutPDP);
    }

    public boolean isOrderSubmitSuccessfully(){
        try {
            distributorUI.waitForVisibility(txt_orderConfirmationPopUp);
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(txt_orderConfirmationPopUp);
    }

    public void clickOrderGuide() {
        if (distributorUI.isDisplayed(btn_addOrderGuideHeart)) {
            distributorUI.click(btn_addOrderGuideHeart);
        } else {
            distributorUI.click(btn_removeFromOrderGuideHeart);
            try {
                distributorUI.waitForCustom(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            distributorUI.click(btn_addOrderGuideHeart);
        }
    }
    public void clickOrderGuideTab(){
        distributorUI.waitForVisibility(btn_catalogToOrderGuide);
        try {
            distributorUI.waitForCustom(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        distributorUI.click(btn_catalogToOrderGuide);
    }
    public boolean addedItemDisplayOnOrderGuide(String name){
        distributorUI.waitForVisibility(By.xpath(lbl_orderGuideItem.replace("NAME", name)));
        return distributorUI.isDisplayed(By.xpath(lbl_orderGuideItem.replace("NAME", name)));
    }
    public void clickOrderGuideProduct(String name){
        distributorUI.click(By.xpath(lbl_orderGuideItem.replace("NAME", name)));
    }
    public void clickRemoveOrderGuide(){
        distributorUI.click(btn_removeFromOrderGuideHeart);
    }

    public double getTotalOrderValue() {
        distributorUI.waitForVisibility(txt_totalOrderValue);
        try {
            distributorUI.waitForCustom(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String orderValueText = distributorUI.getText(txt_totalOrderValue).replace("$", "");
        double orderValue = Double.valueOf(orderValueText);
        return orderValue;
    }

    public void placeNewOrder() {
        distributorUI.click(btn_orderGuideCusProf);
        distributorUI.waitForInvisibility(btn_draftNo);
        distributorUI.doubleClick(btn_draftNo);
        for (int i = 0; i < 4; i++) {
            distributorUI.click(sel_product);
        }
        distributorUI.click(btn_orderCheckout);


        if (isReviewPageTextDisplayed()) {
            if (distributorUI.isDisplayed(txt_reviewPage)) {
                return;
            }
        } else {
            return;
        }
    }

    public boolean isReviewPageTextDisplayed(){
        return distributorUI.isDisplayed(txt_reviewPage);
    }

    public void clickSubmitOrder(){distributorUI.click(btn_submitOrder);}

    public void clickDuplicateOrder(){distributorUI.click(btn_duplicateOrderYes);}

    public boolean isOrderSubmissionTextDisplayed(){
        return distributorUI.isDisplayed(txt_orderSuccessMsg);
    }

    public void clickCloseSuccessMsg(){distributorUI.click(btn_closeMsg);}

    public void clickAddTagsDropdown() throws InterruptedException {
        distributorUI.waitForCustom(3000);
        distributorUI.clickWithScrollAndHover(dropdown_selTags);}

    public boolean isDropdownListDisplayed(){
        return distributorUI.isDisplayed(dropdown_list);
    }

    public void selectTagOption() throws InterruptedException {
//        distributorUI.clickWithScrollAndHover(dropdown_selTags);
//        distributorUI.scrollByPixels(25,25);
        distributorUI.clickWithFallback(dropdown_selTags);
        distributorUI.waitForVisibility(sel_tagOption);
        distributorUI.clickAction(sel_tagOption);
        distributorUI.waitForCustom(3000);
    distributorUI.click(lbl_tagName);}

    public boolean isAddedTagDisplayed(){
        return distributorUI.isDisplayed(sel_tagName);
    }

    public void clickRemoveTagOption(){distributorUI.clickWithScrollAndHover(icon_removeTag);}

    public boolean isTagExist() {
        distributorUI.scrollByPixels(50,50);
        return distributorUI.isDisplayed(icon_removeTag);
    }

    public boolean isAddedTagDeletedStable() throws InterruptedException {
        distributorUI.waitForCustom(3000);
        return distributorUI.isDisplayed(icon_removeTag);
    }

    public boolean isAddedTagDeleted() {
        try {
            return !distributorUI.isDisplayed(sel_tagName);
        } catch (NoSuchElementException e) {
            return true;
        }
    }

    public String getLastOrderedDate() {
        String lastOrderedText = distributorUI.getText(txt_lastOrderDate);
        String dateOnly;
        dateOnly = lastOrderedText.substring(lastOrderedText.indexOf("on") + 3).trim();
        return dateOnly;
    }

    public boolean isLastOrderDateUpdated() {
        LocalDate today = LocalDate.now();
        String updatedOrderDate = today.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        String lastOrderedDate = getLastOrderedDate();
        System.out.println(lastOrderedDate);
        System.out.println(updatedOrderDate);
        return lastOrderedDate.equals(updatedOrderDate);
    }

    public boolean isCustomersDisplayed(){
        return distributorUI.isDisplayed(customersText);
    }

    public void clickEditVisibilityIcon(){distributorUI.click(btn_accountVisibility);}
    public void clickVisibilityDropdown(){distributorUI.click(dropdown_visibility);}

    public void selectHiddenOption(){
        distributorUI.click(sel_hiddenOption);
        distributorUI.click(btn_visibilitySave);
    }

    public boolean isHiddenOptionDisplayed(){
        return distributorUI.isDisplayed(txt_hidden);
    }

    public void loginAsCustomerPortal() throws InterruptedException{
        distributorUI.navigateToURL("https://app-uat.staging.cutanddry.com/internaltools/loginas");
    }

    public void loginAsCus(String email,String nameCus)throws InterruptedException{
        distributorUI.click(tbx_emailOrMobile);
        distributorUI.sendKeys(tbx_emailOrMobile,email);
        distributorUI.waitForCustom(800);
        distributorUI.click(By.xpath(sel_cusOption.replace("CUS_NAME", nameCus)));
        distributorUI.SwitchToNewTab(btn_loginAsCus);
        //distributorUI.click(sel_cusOption);
        //distributorUI.SwitchToNewTab(btn_loginAsCus);
    }


    public void clickOrderIcon()throws InterruptedException{
        distributorUI.waitForCustom(500);
        distributorUI.click(btn_cusOrderIcon);
    }

    public boolean isAddSupplierButtonVisible(){
        try {
            return distributorUI.isDisplayed(btn_cusAddSupplier);
        } catch (Exception e) {
            return false;
        }
    }

    public void loginToDistributorPortal() throws InterruptedException{
        distributorUI.navigateToURL("https://supplier-uat.staging.cutanddry.com/log-in");
    }

    public void selectVisibleOption(){
        distributorUI.click(sel_visibleOption);
        distributorUI.click(btn_visibilitySave);
    }

    public boolean isVisibilityOptionDisplayed(){
        return distributorUI.isDisplayed(txt_visible);
    }

    public boolean isOrderGuideVisible(){
        return distributorUI.isDisplayed(btn_placeOrder);
    }

    public void clickEditStatusIcon(){distributorUI.click(btn_accountStatus);}
    public void clickStatusDropdown(){distributorUI.click(dropdown_status);}

    public boolean isStatusDropdownListDisplayed(){
        return distributorUI.isDisplayed(ls_status);
    }

    public void selectStatusOption(){
        distributorUI.click(sel_statusOption);
        distributorUI.click(btn_statusSave);
    }

    public boolean isInactiveStatusDisplayed(){
        distributorUI.waitForVisibility(txt_status);
        return distributorUI.isDisplayed(txt_status);
    }

    public void refreshCustomersPage(){
        distributorUI.refreshPage();
    }

    public boolean isCustomerNotMatchTextDisplayed(){
        distributorUI.waitForVisibility(txt_noCusMatch);
        return distributorUI.isDisplayed(txt_noCusMatch);
    }

    public void clickOnMoreFiltersOption(){distributorUI.click(btn_moreFilter);}

    public void clickOnMoreFilterStatusDropdown(){distributorUI.click(sel_filterStatusDropdown);}
    public void clickOnMoreFiltersInactiveOption(){distributorUI.click(sel_filterInactive);}
    public void clickOnApplyFiltersOption(){distributorUI.click(btn_filterApply);}


    public void selectActiveStatusOption(){
        distributorUI.click(sel_statusOptionActive);
        distributorUI.click(btn_statusSave);
    }

    public boolean isActiveStatusDisplayed(){
        distributorUI.waitForVisibility(txt_activeStatus);
        return distributorUI.isDisplayed(txt_activeStatus);
    }

    public void clickOnMoreFiltersActiveOption(){distributorUI.click(sel_filterActive);}

    public void clickExportPDP(){
        distributorUI.click(btn_exportPDP);
    }
    public void clickRightArrow(){
        distributorUI.click(btn_rightArrow);
    }
    public boolean isNextImageDisplay(){
        try {
            distributorUI.waitForVisibility(img_second);
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(img_second);
    }
    public void clickLeftArrow(){
        distributorUI.click(btn_leftArrow);
    }
    public boolean isPreviousImageDisplay(){
        try {
            distributorUI.waitForVisibility(img_first);
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(img_first);
    }
    public void clickFirstImage(){
        distributorUI.click(btn_firstImage);
    }
    public void clickSecondImage(){
        distributorUI.click(btn_secondImage);
    }
    public void typeSpecialInstruction(String specialInstruction) throws InterruptedException {
        distributorUI.waitForCustom(3000);
        distributorUI.sendKeys(txt_specialInstruction,specialInstruction);
    }
    public void typeInternalNote(String internalNote) throws InterruptedException {
        distributorUI.waitForCustom(3000);
        distributorUI.sendKeys(txt_internalNote,internalNote);
    }
    public void typeNoteToCustomer(String noteToCustomer) throws InterruptedException {
        distributorUI.waitForCustom(3000);
        distributorUI.sendKeys(txt_noteToCustomer,noteToCustomer);
    }
    public void typePONumber(String poNumber) throws InterruptedException {
        distributorUI.waitForCustom(3000);
        distributorUI.sendKeys(txt_poNumber,poNumber);
    }
    public boolean isSpecialInstructionDisplayed(String specialInstruction) {
        distributorUI.waitForVisibility(By.xpath(specialInstructionText.replace("SPECIALINSTRUCTION",specialInstruction)));
        return distributorUI.isDisplayed(By.xpath(specialInstructionText.replace("SPECIALINSTRUCTION",specialInstruction)));
    }
    public boolean isInternalNoteDisplayed(String internalNote) {
        distributorUI.waitForVisibility(By.xpath(internalNoteText.replace("INTERNALNOTE",internalNote)));
        return distributorUI.isDisplayed(By.xpath(internalNoteText.replace("INTERNALNOTE",internalNote)));
    }
    public boolean isNoteToCustomerDisplayed(String noteToCustomer) {
        distributorUI.waitForVisibility(By.xpath(noteToCustomerText.replace("NOTETOCUSTOMER",noteToCustomer)));
        return distributorUI.isDisplayed(By.xpath(noteToCustomerText.replace("NOTETOCUSTOMER",noteToCustomer)));
    }

    public boolean isPoNumberCorrectlyDisplayed(String POnumber){
        distributorUI.waitForVisibility(By.xpath(PONumber.replace("PONUMBER",POnumber)));
        return distributorUI.isDisplayed(By.xpath(PONumber.replace("PONUMBER",POnumber)));
    }
    public String getItemQuantity() throws InterruptedException {
        distributorUI.waitForElementEnabledState(quantityValue, true);
        return distributorUI.getText(quantityValue, "value");
    }

    public String getItemTotalQuantity() throws InterruptedException {
        distributorUI.waitForElementEnabledState(totalQuantity, true);
        return distributorUI.getText(totalQuantity);
    }
    public String getItemValue() throws InterruptedException {
        distributorUI.waitForElementEnabledState(Value, true);
        return distributorUI.getText(Value);
    }

    public String getItemTotalValue() throws InterruptedException {
        distributorUI.waitForElementEnabledState(totalValue, true);
        return distributorUI.getText(totalValue);
    }

    public void clickEditOrderInReviewScreen(){
        distributorUI.click(editOrderReviewScreen);
    }

    public boolean isErrorTextDisplayed() {
        try {
            // Wait to see if the error text becomes visible
            distributorUI.waitForVisibility(txt_error);
            return true; // "error" text is found
        } catch (TimeoutException e) {
            return false; // No "error" text is found within the timeout
        }
    }

    public void clickOnFirstItemOfCustomerRequests() throws InterruptedException {
        /*WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(first_row));*/
        distributorUI.waitForCustom(5000);
        distributorUI.waitForVisibility(first_row);
        distributorUI.click(first_row);
    }

    public void clickonInvoice(){
        distributorUI.click(btn_invoice);
    }

    public boolean verifyEnabledStatus() {
        distributorUI.waitForVisibility(enabledStatusLocator);

        // Find all matching elements to be sure
        List<WebElement> enabledStatusElements = driver.findElements(enabledStatusLocator);

        if (!enabledStatusElements.isEmpty()) {
            for (WebElement element : enabledStatusElements) {
                // Verify current text
                String currentStatus = element.getText();
                if (currentStatus.equalsIgnoreCase("Enabled")) {
                    System.out.println("Found 'Enabled'.");
                }
            }
            return true;
        } else {
            System.out.println("No element with 'Enabled' text found.");
            return false;
        }
    }

    public boolean verifyPaymentTermStatus() {

        distributorUI.waitForVisibility(enabledStatusLocator);

        // Find all matching elements to be sure
        List<WebElement> enabledStatusElements = driver.findElements(defaultTermStatusLocator);

        if (!enabledStatusElements.isEmpty()) {
            for (WebElement element : enabledStatusElements) {
                // Verify current text
                String currentStatus = element.getText();
                if (currentStatus.equalsIgnoreCase("Enabled")) {
                    System.out.println("Found 'Enabled'.");
                }
            }
            return true;
        } else {
            System.out.println("No element with 'Enabled' text found.");
            return false;
        }
    }

    public String getOrderedId() {
        String orderId = distributorUI.getText(txt_orderId);
        return orderId.substring(orderId.indexOf("#") + 1).trim();
    }

    public boolean isDeliveryOptionSelected() {
        try {
            distributorUI.waitForVisibility(sel_delivery);
            String dataIconValue = distributorUI.getText(sel_delivery, "data-icon").trim(); // Use getAttribute to fetch the attribute value
            return dataIconValue.equals("circle-check");
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isPickUpOptionSelected() {
        try {
            distributorUI.waitForVisibility(sel_pickup);
            String dataIconValue = distributorUI.getText(sel_pickup, "data-icon").trim(); // Use getAttribute to fetch the attribute value
            return dataIconValue.equals("circle-check");
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isMailDeliveryOptionSelected() {
        try {
            distributorUI.waitForVisibility(sel_mailDelivery);
            String dataIconValue = distributorUI.getText(sel_mailDelivery, "data-icon").trim(); // Use getAttribute to fetch the attribute value
            return dataIconValue.equals("circle-check");
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isOrderMiniumErrorBannerDisplayed(){
        return distributorUI.isDisplayed(lbl_OrderMinimumErrorBanner);
    }
    public boolean isOrderMiniumErrorBannerDisplayedSub(){
        return distributorUI.isDisplayed(lbl_OrderMinimumErrorBanner,5);
    }

    public void clickOnAddPaymentMethod() {
        distributorUI.click(addPaymentMethodButton);
    }

    public void clickOnAddBankAccount() {
        distributorUI.click(addBankAccountButton);
    }

    public void typeAccountNumber(String accountNumber) {
        distributorUI.click(tbx_account_number);
        distributorUI.clear(tbx_account_number);
        distributorUI.sendKeys(tbx_account_number, accountNumber);
    }

    public void typeRoutingNumber(String routingNumber) {
        distributorUI.click(tbx_routing_number);
        distributorUI.clear(tbx_routing_number);
        distributorUI.sendKeys(tbx_routing_number, routingNumber);
    }

    public void selectAccountType(String accountType) {
    try {
        distributorUI.click(dropDownAccountType);
        System.out.println("Dropdown opened successfully.");

        By btn_accountType = By.xpath(btn_accountTypeOption.replace("OPTION_TEXT", accountType));

        // Wait for the option to be visible and click it
        distributorUI.waitForVisibility(btn_accountType);
        distributorUI.click(btn_accountType);

        System.out.println("Account type changed to: " + accountType);
    } catch (Exception e) {
        System.err.println("Failed to select account type '" + accountType + "'. Error: " + e.getMessage());
        }
    }

    public boolean isPaymentMethodAddedSuccessfully() {
        try {
            distributorUI.waitForVisibility(txt_paymentMethodAddedSuccessfully);
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isErrorOccuredAddingPaymentMethod() {
        try {
            distributorUI.waitForVisibility(txt_errorOccurredAddingPaymentMethod);

            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public String getItemCodeFirstRow() throws InterruptedException {
        distributorUI.waitForVisibility(lbl_itemCodeList);
        distributorUI.waitForCustom(3000);
        return distributorUI.getText(lbl_itemCodeList);
    }

    public boolean isPaymentMethodRemovedDisplayed() throws InterruptedException {
//        distributorUI.waitForElementEnabledState(txt_payment_method_removed, true);
        distributorUI.waitForCustom(1000);
        return distributorUI.isDisplayed(txt_payment_method_removed);
    }

    public void clickOnTrashCan(){
        distributorUI.click(btn_trash_can);
    }

    public void editPaymentMethod() throws InterruptedException {
        distributorUI.waitForCustom(4000);
        distributorUI.click(icon_edit_payment_method);
    }

    public boolean isAutoPayEnabled() throws InterruptedException {
        distributorUI.waitForElementEnabledState(txt_under_auto_pay, true);
        distributorUI.waitForCustom(2000);
        return distributorUI.isDisplayed(txt_under_auto_pay);
    }

    public void clickOnEnableAutoPlay(){
        distributorUI.click(btn_enable_auto_pay);
    }

    public void clickOnIAgree(){
        distributorUI.click(btn_i_agree);
    }

    public void clickOnEnable(){
        distributorUI.click(btn_enable);
    }

    public void editAutoPay(){
        distributorUI.click(icon_edit_auto_pay);
    }

    public void clickPlusSearchedSingleItem(){
        distributorUI.click(btn_catalogPlus);
    }

    public void clickOnPlusIconInCatalogPDP(String name){
        distributorUI.waitForVisibility(By.xpath(btn_catalogPDPPlusStable.replace("NAME", name)));
        distributorUI.click(By.xpath(btn_catalogPDPPlusStable.replace("NAME", name)));
    }

    public void clickMinusSearchedSingleItem(){
        distributorUI.click(btn_catalogMinus);
    }

    /*public double getActiveItemPriceFirstRow() throws InterruptedException {
        distributorUI.waitForVisibility(lbl_itemPriceList);
        String tagName = distributorUI.getElement(lbl_itemPriceList).getTagName();
        String priceText;
        if (tagName.equals("input")) {
            priceText = distributorUI.getText(lbl_itemPriceList, "value");
        } else {
            priceText = distributorUI.getText(lbl_itemPriceList);
        }

        return Double.valueOf(priceText.replace("$", "").trim());
    }*/

    public double getActiveItemPriceFirstRow() throws InterruptedException {
        try {
            return extractPrice(lbl_itemPriceList);
        } catch (Exception e) {
            System.out.println("Fallback to alternative price locator due to: " + e.getMessage());
            return extractPrice(lbl_itemPriceList1);
        }
    }

    public double getActiveItemPriceFirstRowStable() throws InterruptedException {
        try {
            return extractPriceStable(lbl_itemPriceList);
        } catch (Exception e) {
            System.out.println("Fallback to alternative price locator due to: " + e.getMessage());
            return extractPriceStable(lbl_itemPriceList1);
        }
    }
    public double getActiveItemPriceSecondRowStable() throws InterruptedException {
        try {
            return extractPriceStable(lbl_secondItemPriceList);
        } catch (Exception e) {
            System.out.println("Fallback to alternative price locator due to: " + e.getMessage());
            return extractPriceStable(lbl_secondItemPriceList1);
        }
    }

    private double extractPrice(By priceLocator) throws InterruptedException {
        distributorUI.waitForVisibility(priceLocator);
        String tagName = distributorUI.getElement(priceLocator).getTagName();
        String priceText;

        if (tagName.equals("input")) {
            priceText = distributorUI.getText(priceLocator, "value");
        } else {
            priceText = distributorUI.getText(priceLocator);
        }

        System.out.println("Extracted Price: " + priceText);
        return Double.valueOf(priceText.replace("$", "").replace("/cs", "").trim());
    }

    private double extractPriceStable(By priceLocator) throws InterruptedException {
        distributorUI.waitForVisibility(priceLocator);
        String tagName = distributorUI.getElement(priceLocator).getTagName();
        String priceText;

        if (tagName.equals("input")) {
            priceText = distributorUI.getText(priceLocator, "value");
        } else if (tagName.equals("div")) {
            priceText = distributorUI.getText(priceLocator);
        } else {
            priceText = distributorUI.getText(priceLocator);
        }

        System.out.println("Extracted Price: " + priceText);
        priceText = priceText.replace("$", "").replace(",", "").split("/")[0].trim();

        return Double.valueOf(priceText);
    }

    public Double getItemPriceOnCheckoutButtonViaPDP() throws InterruptedException {
        distributorUI.waitForVisibility(btn_checkOutPDP);
        distributorUI.waitForCustom(4000);
        return Double.valueOf(distributorUI.getText(btn_checkOutPDP).replace("$",""));
    }

    public boolean isEnableVisible(){
        return distributorUI.isDisplayed(btn_enable);
    }

    public void clickOnCancelAutopay(){
        distributorUI.click(btn_cancelAutoPay);
    }

    public boolean isAutoPayUpdated(String expectedText){
        String autoPayDetailsXpath = txt_auto_pay_details.replace("SCHEDULE_OPTION", expectedText);
        By autoPayDetailsCheck = By.xpath(autoPayDetailsXpath);

        return distributorUI.isDisplayed(autoPayDetailsCheck);
    }

    public void clickOnUpdate(){
        distributorUI.click(btn_update);
    }

    public void selectDropdownOption(String optionText) {
        try {
            // Replace placeholder in the option XPath with the actual option text
            String optionXpath = option_ScheduleType.replace("OPTION_TEXT", optionText);
            By optionLocator = By.xpath(optionXpath);

            // Wait for the option to be visible and click it
            distributorUI.waitForVisibility(optionLocator);
            distributorUI.click(optionLocator);

            System.out.println("Option selected: " + optionText);
        } catch (Exception e) {
            System.err.println("Failed to select option '" + optionText + "'. Error: " + e.getMessage());
        }
    }

    public void clickOnDropdownSchedule(){
        distributorUI.click(dropdown_schedule);
    }

    public boolean verifyLastInvoicePaid(){
        return distributorUI.isDisplayed(txt_lastInvoicePaid);
    }
    public boolean isProprietaryItemOptionDisplayed()throws InterruptedException{
        return distributorUI.isDisplayed(proprietaryItemOption);
    }

    public boolean isCCFeesValueCorrect(String expectedValue) {
            By txt_finalCCFeeValue = By.xpath(txt_CCFeeValue.replace("OPTION_TEXT", expectedValue));
            return distributorUI.isDisplayed(txt_finalCCFeeValue);
    }

    public void clickOnCCFeeValue(String CCFeeValue) {
                // Replace placeholder with actual value and locate the dropdown option
        By dropdownValueLocator = By.xpath(dropdown_CCFeeOption.replace("PLACEHOLDER", CCFeeValue));

        // Wait for visibility of the dropdown option and click it
        distributorUI.waitForVisibility(dropdownValueLocator);
        distributorUI.click(dropdownValueLocator);
    }

    public void clickOnDropdownCCFee(){
        distributorUI.click(dropdown_CCFee);
    }

    public void clickOnEditCCFees(){
        distributorUI.click(btn_editCCFee);
    }

    public boolean isNoteCorrect(String expectedNote) {
        By txt_finalNote = By.xpath(txt_Note.replace("OPTION_TEXT", expectedNote));
        return distributorUI.isDisplayed(txt_finalNote);
    }

    public void typeNewNote(String note) throws InterruptedException {
        distributorUI.clear(tbx_editNotes);
        distributorUI.waitForCustom(1000);
        distributorUI.sendKeys(tbx_editNotes, note);
        distributorUI.waitForCustom(3000);
    }

    public void clickOnEditNotes(){
        distributorUI.click(btn_editNotes);
    }

    public boolean isFilterSelectedCorrectly(String expectedFilter){
        String filterType = distributorUI.getText(txt_Filter);
        System.out.println("The type displayed is "+filterType);
//        String expectedFilterDisplay = "Filter: " + expectedFilter;
        return filterType.equals(expectedFilter);
    }

    public void clickOnDropDownFilter(){
        distributorUI.click(drodown_Filter);
    }

    public void selectFilterDropDown(String FilterOption) {
        By dropdownValueLocator = By.xpath(dropdown_FilterOption.replace("PLACEHOLDER", FilterOption));
        distributorUI.waitForVisibility(dropdownValueLocator);
        distributorUI.click(dropdownValueLocator);
        try {
            distributorUI.waitForCustom(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isNoDueInvoicesDisplayed(){
        return distributorUI.isDisplayed(txt_noDueInvoices);
    }

    public boolean isAreYouSureTxtDisplayed(){
        return distributorUI.isDisplayed(txt_areYouSure);
    }

    public boolean istxtEmailsSentDisplayed(){
        return distributorUI.isDisplayed(txt_EmailsSent);
    }

    public void clickOnSendPaymentReminder() throws InterruptedException {
        distributorUI.click(btn_sendPaymentReminder);
        distributorUI.waitForCustom(3000);
    }

    public void clickOnSendEmail(){
        distributorUI.click(btn_sendEmail);
    }

    public boolean isSearchedCustomerDisplayed(String CustomerCode){
        By btnrow_searchedCustomer = By.xpath(row_searchedCustomer.replace("CODE", CustomerCode));
        return distributorUI.isDisplayed(btnrow_searchedCustomer);
    }

    public void clickDropdownMoreActions(){
        distributorUI.waitForVisibility(dropdown_moreActions);
        distributorUI.click(dropdown_moreActions);
    }

    public void clickManageNotifications(){
        distributorUI.click(dropdown_optionManageNotifications);
    }

    public boolean isPreviousDraftOrderNoDisplayed() throws InterruptedException {
        return distributorUI.isDisplayed(btn_previousDraftOrderNo);
    }
    public boolean isPreviousDraftOrderNoDisplayedSub() throws InterruptedException {
        return distributorUI.isDisplayed(btn_previousDraftOrderNo,5);
    }

    public boolean isBookKeeperEmailSentConfirmationDisplayed(String expectedEmail) {
        By txt_confirmEmailSent = By.xpath(txt_BookKeeperEmailSent.replace("EMAIL", expectedEmail));
        distributorUI.waitForVisibility(txt_confirmEmailSent);
        return distributorUI.isDisplayed(txt_confirmEmailSent);
    }

    public void clickInviteViaEmail(){
        distributorUI.click(btn_inviteViaEmail);
    }

    public void fillBookKeeperEmail(String Email) throws InterruptedException {
        distributorUI.clear(tbx_bookKeeperEmail);
        distributorUI.waitForCustom(1000);
        distributorUI.sendKeys(tbx_bookKeeperEmail, Email);
    }
    public void fillBookKeeperMobile(String Mobile) throws InterruptedException {
        distributorUI.clear(tbx_bookKeeperMobile);
        distributorUI.waitForCustom(1000);
        distributorUI.sendKeys(tbx_bookKeeperMobile, Mobile);
    }
    public void fillBookKeeperName(String Name) throws InterruptedException {
        distributorUI.clear(tbx_bookKeeperName);
        distributorUI.waitForCustom(1000);
        distributorUI.sendKeys(tbx_bookKeeperName, Name);
    }

    public void clickInviteBookKeeper(){
        distributorUI.click(dropdown_optionInviteBookkeeper);
    }

    public void clickEmailStatement(){
        distributorUI.click(dropdown_optionEmailStatement);
    }

    public void clickSend(){
        distributorUI.click(btn_send);
    }

    public void fillNotificationEmailAddress(String Email) throws InterruptedException {
        distributorUI.clear(tbx_enterNotificationEmail);
        distributorUI.waitForCustom(1000);
        distributorUI.sendKeys(tbx_enterNotificationEmail, Email);
    }

    public void clickDownloadStatement() throws InterruptedException {
        distributorUI.click(dropdown_optionDownloadStatement);
        distributorUI.waitForCustom(1000);
    }

    public void clickCreateCreditMemo(){
        distributorUI.click(dropdown_optionCreateCreditMemo);
    }

    public boolean isTxtCreditMemoExistingDisplayed(String memoNumber) {
        By txt_CreditMemoAlreadyExists = By.xpath(errorMessage_CreditMemoAlreadyExists.replace("memoNumber", memoNumber));
        return distributorUI.isDisplayed(txt_CreditMemoAlreadyExists);
    }

    public boolean isTxtCreditMemoConfirmDisplayed(){
        return distributorUI.isDisplayed(txt_CreditMemoConfirm);
    }

    public void clickBtnCreateCreditMemo(){
        distributorUI.waitForElementEnabledState(btn_CreateCreditMemo,true);
        distributorUI.click(btn_CreateCreditMemo);
    }

    public void typeCreditMemoDescription(String description) throws InterruptedException {
        distributorUI.clear(tbx_description);
        distributorUI.waitForCustom(1000);
        distributorUI.sendKeys(tbx_description, description);
    }

    public void typeCreditMemoAmount(String amount) throws InterruptedException {
        distributorUI.clear(tbx_enterTheAmount);
        distributorUI.waitForCustom(1000);
        distributorUI.sendKeys(tbx_enterTheAmount, amount);
    }

    public void typeCreditMemoNumber(String creditMemoNumber) throws InterruptedException {
        distributorUI.clear(tbx_creditMemoNumber);
        distributorUI.waitForCustom(1000);
        distributorUI.sendKeys(tbx_creditMemoNumber, creditMemoNumber);
    }

    public void fillDropdownAssociatedInvoice(String associatedInvoice){
        distributorUI.sendKeysAndEnter(dropdown_AssociatedInvoice, associatedInvoice);
    }
    public void selectOptionAssociatedInvoice(){
        distributorUI.waitForVisibility(dropdownOption_AssociatedInvoice);
        distributorUI.click(dropdownOption_AssociatedInvoice);
    }

    public void clickMarkAsPaid(){
        distributorUI.click(dropdown_optionMarkAsPaid);
    }

    public void clickOnCheckBox(int totalCheckboxes) {
        for (int j = 1; j <= totalCheckboxes; j++) {
            String checkBoxXPath = checkBox_inInvoiceTable.replace("row", String.valueOf(j));
            By checkBox_InvoiceTable = By.xpath(checkBoxXPath);
            distributorUI.click(checkBox_InvoiceTable);
        }
    }

    public boolean isMarkedAsPaidSuccessfullyDisplayed(){
        distributorUI.waitForVisibility(txt_markedAsPaidSucessfully);
        if (distributorUI.isDisplayed(txt_markedAsPaidSucessfully)){
            System.out.println("Mark As Paid Successfully Displayed");
            return true;
        }
        else{
            System.out.println("Mark As Paid Successfully is not Displayed");
            return false;
        }
    }

    public boolean isDisplayedOrderGuideTypeCorrect(String item){
        distributorUI.waitForVisibility(txt_displayedOrderGuide);
        String displayedOrderGuide = distributorUI.getText(txt_displayedOrderGuide);
        System.out.println("The Order Guide Type is "+displayedOrderGuide);
        if (displayedOrderGuide.equals(item)){
            System.out.println("Displayed Order Guide Type is Correct");
            return true;
        }
        else{
            System.out.println("Displayed Order Guide Type is Incorrect");
            return false;
        }
    }

    public void clickOnItemDropDownOrderGuide(String item) {
        By dropDownItem = By.xpath(dropdownOrderGuideItemXPath.replace("{}", item));
        System.out.println(dropDownItem);
        distributorUI.click(dropDownItem);
    }
    public void clickOnDropDownOrderGuide() throws InterruptedException {
        distributorUI.waitForVisibility(dropdown_orderGuide);
        distributorUI.click(dropdown_orderGuide);
    }

    public void clickOnStableCheckoutButton() throws InterruptedException {
        distributorUI.waitForCustom(4000);
        distributorUI.waitForElementEnabledState(btn_checkout_stable,true);
        distributorUI.click(btn_checkout_stable);
    }

    public void clickBtnResetValues(){
        distributorUI.click(btn_reset);
    }


    public void editMarginValue(String value){
        distributorUI.waitForVisibility(tbx_editMarginValue);
        distributorUI.clear(tbx_editMarginValue);
        distributorUI.sendKeys(tbx_editMarginValue,value);
    }

    public void clickOnEditMargin() {
        distributorUI.waitForVisibility(table_OrderGuide);
        try {
            distributorUI.waitForVisibility(table_OrderGuide);
            int numberOfRows = distributorUI.getRowCount(table_OrderGuide);
            System.out.println("There are " + numberOfRows + " rows.");

            for (int i = 1; i <= numberOfRows; i++) { // Rows in XPath are 1-based
                By marginColumnItem = By.xpath(item_MarginColumnOrderGuide.replace("PLACEHOLDER", String.valueOf(i)));
                String item = distributorUI.getText(marginColumnItem);

                if (!item.equalsIgnoreCase("N/A")) {
                    By editButton = By.xpath(".//tbody/tr[" + i + "]//button[contains(@class, 'btn-primary')]");
                    distributorUI.click(editButton);
                    System.out.println("Clicked edit button in row: " + i);
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Failed to click on edit button for margin.");
        }
    }

    public boolean isDontForgetToBuyDisplayed() {
        return distributorUI.isDisplayed(section_dontForgetToBuy);
    }

    public boolean isAddPaymentMethodButtonDisplayed() {
        return distributorUI.isDisplayed(addPaymentMethodButton);
    }

    public void clickOnAddNewPaymentMethod() {
        distributorUI.click(btn_addNewPaymentMtd);
    }

    public String getLastOrderedPoundPrice() {
        String lastOrderedPoundPriceText = distributorUI.getText(txt_lastOrderedPrice);
        String lbPriceOnly;
        lbPriceOnly = lastOrderedPoundPriceText.split("\\s+")[0];
        return lbPriceOnly;
    }

    public boolean isLastOrderedPriceDisplayed() {
        String lastOrderedPoundPrice = getLastOrderedPoundPrice();
        System.out.println(lastOrderedPoundPrice);
        return lastOrderedPoundPrice != null && !lastOrderedPoundPrice.isEmpty();
    }

    public boolean isLastOrderedPriceNotSameAfterToggle() {
        distributorUI.waitForVisibility(txt_lastOrderedPriceOff);
        String lastOrderedPoundPriceTextOff = distributorUI.getText(txt_lastOrderedPriceOff);
        System.out.println(lastOrderedPoundPriceTextOff);
        if (distributorUI.isDisplayed(txt_lastOrderedPriceOff)){
            System.out.println("LB price is not displayed");
            return true;
        }
        else{
            System.out.println("LB price is still displaying");
            return false;
        }
    }
    public void clickOnCheckoutButtonOperator() throws InterruptedException {
        distributorUI.waitForElementEnabledState(btn_checkoutOperator, true);
        distributorUI.click(btn_checkoutOperator);
        distributorUI.waitForCustom(4000);
    }
    public void clickPlusQryFirstRowClassic() throws InterruptedException {
        distributorUI.click(btn_increaseQtyFirstRowClassic);
        distributorUI.waitForCustom(4000);
    }
    public void submitOrderForApproval(){
        try {
            distributorUI.waitForCustom(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        distributorUI.waitForClickability(btn_submitOrderForApproval);
        distributorUI.click(btn_submitOrderForApproval);
    }
    public boolean isSentApprovalDisplayed(){
        try {
            distributorUI.waitForVisibility(txt_sentApproval);
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(txt_sentApproval);
    }
    public void clickViewOrderInDraft() {
        distributorUI.waitForVisibility(btn_viewOrderInDraft);
        distributorUI.click(btn_viewOrderInDraft);
    }
    public void clickCheckOutOrderGuide(){
        distributorUI.click(btn_orderCheckout);
    }
    public String getItemFinalPrice() throws InterruptedException {
        distributorUI.waitForElementEnabledState(finalItemPrice,true);
        distributorUI.waitForCustom(3000);
        return distributorUI.getText(finalItemPrice);
    }
    public double getItemFinalPriceStable() throws InterruptedException {
        try {
            return extractPriceStable(finalItemPrice);
        } catch (Exception e) {
            System.out.println("Fallback to alternative price locator due to: " + e.getMessage());
            return extractPriceStable(finalItemPrice);
        }
    }
    public void typeOnFinalWeight(String weight) throws InterruptedException {
        distributorUI.click(finalWeight);
        distributorUI.clearUsingJavaScript(finalWeightInput);
//        distributorUI.waitForCustom(1000);
        distributorUI.sendKeys(finalWeightInput, weight);
        distributorUI.click(btn_finalWeightUpdate);
    }
    public String getItemFinalWeight(){
        distributorUI.click(finalWeight);
        String value = distributorUI.getText(finalWeightInput, "value");
        distributorUI.click(btn_finalWeightUpdate);
        return value;
    }
//    public String getItemPriceOnEditOrderCheckout() throws InterruptedException {
//        distributorUI.waitForVisibility(btn_orderCheckout);
//        distributorUI.waitForCustom(4000);
//        return distributorUI.getText(btn_orderCheckout);
//    }
    public void clickEditOrderCheckout(){
        distributorUI.click(btn_submitOrderEdit);
    }
    public boolean isEditOrderCheckout(){
        return distributorUI.isDisplayed(btn_submitOrderEdit,8);
    }
    public String getConfirmFinalPrice(){
        return distributorUI.getText(confirmPrice);
    }
//    public String getPriceInCustomerOrder(){
//        return distributorUI.getText(priceInCustomerOrder);
//    }
    public String getPoundPrice(){
        return distributorUI.getText(poundPrice);
    }
    public void clickPoundPrice(){
        distributorUI.click(poundPrice);
    }
    public boolean isPoundPricePopUpDisplay(){
        try {
            distributorUI.waitForVisibility(txt_lbPricePopUP);
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(txt_lbPricePopUP);
    }
    public void typeOnPerLBPrice(String lbPrice) throws InterruptedException {
        distributorUI.clearUsingJavaScript(perLbPrice);
//        distributorUI.waitForCustom(1000);
        distributorUI.sendKeys(perLbPrice, lbPrice);
    }
    public void clickSave()throws InterruptedException{
        distributorUI.waitForVisibility(Savebtn);
        distributorUI.click(Savebtn);
        distributorUI.waitForCustom(3000);
    }
    public void enterSpotPrice(String num) throws InterruptedException {
        distributorUI.clearUsingJavaScript(lbl_spotPrice);
//        distributorUI.sendKeysAndEnterMac(lbl_spotPrice, num);
        distributorUI.sendKeys(lbl_spotPrice, num);
        distributorUI.waitForCustom(1000);
    }
    public boolean isSpotPriceAdded(String value){
        return distributorUI.isDisplayed(By.xpath(spotPriceValue.replace("VALUE", value)));
    }
    public String getItemFinalSpotPrice() throws InterruptedException {
        distributorUI.waitForElementEnabledState(lbl_itemPriceList,true);
        distributorUI.waitForCustom(3000);
        return distributorUI.getText(lbl_itemPriceList, "value");
    }

    public String getItemFinalPoundSpotPrice() throws InterruptedException {
        distributorUI.waitForElementEnabledState(lbl_itemPriceList, true);
        distributorUI.waitForCustom(3000);
        String fullPrice = distributorUI.getText(lbl_itemPriceList, "value");
        return fullPrice.replaceAll("/lb", "").trim();
    }
    public void splitWeight(){
        distributorUI.click(btn_splitWeight);
    }
    public boolean isSplitWeightPopupDisplayed(){
        return distributorUI.isDisplayed(txt_splitWeight);
    }
    public void enterCasesValue(String num) throws InterruptedException {
        distributorUI.clearUsingJavaScript(lbl_cases);
        distributorUI.sendKeys(lbl_cases, num);
        distributorUI.waitForCustom(1000);
    }
    public void enterWeightValue(String num) throws InterruptedException {
        distributorUI.clearUsingJavaScript(lbl_weight);
        distributorUI.sendKeys(lbl_weight, num);
        distributorUI.waitForCustom(1000);
    }
    public void clickUpdateWeight(){
        distributorUI.click(btn_updateWeight);
    }
    public String getItemSplitFinalWeight(){
        return distributorUI.getText(btn_getSplitWeight);
    }
    public String getEditSplitFinalWeightPrice(){
        return distributorUI.getText(splitFinalWeightPrice);
    }
//    public String getSplitFinalWeightPrice() throws InterruptedException {
//        distributorUI.waitForElementEnabledState(splitFinalWeightPrice,true);
//        distributorUI.waitForCustom(3000);
//        return distributorUI.getText(splitFinalWeightPrice);
//    }


    public void scrollBottomOfPage() {
        distributorUI.uiScrollBottomOfPage();
    }

    public boolean isFirstRecordDisplayed(){
        return distributorUI.isDisplayed(cb_inInvoiceTable);
    }
    public void editSpotPrice(){
        distributorUI.click(editSpotPrice);
    }
    public String getPriceInCustomerOrder() throws InterruptedException {
        distributorUI.waitForVisibility(priceInCustomerOrder);
        distributorUI.waitForCustom(4000);
//        return Double.valueOf(distributorUI.getText(priceInCustomerOrder).replace("$","").replace(",", ""));
        return distributorUI.getText(priceInCustomerOrder);
    }
    public Double getItemPriceOnEditOrderCheckout() throws InterruptedException {
        distributorUI.waitForVisibility(btn_orderCheckout);
        distributorUI.waitForCustom(4000);
        return Double.valueOf(distributorUI.getText(btn_orderCheckout).replace("$",""));
    }
    public String getItemPriceOnEditOrderReviewCheckout() throws InterruptedException {
        distributorUI.waitForVisibility(btn_orderCheckoutReview);
        distributorUI.waitForCustom(4000);
//        return Double.valueOf(distributorUI.getText(btn_orderCheckoutReview).replace("$","").replace(",", ""));
        return distributorUI.getText(btn_orderCheckoutReview);
    }
    public void clickOnCheckOutReview(){
        distributorUI.waitForClickability(btn_orderCheckoutReview);
        distributorUI.click(btn_orderCheckoutReview);
    }
    public Double getSplitFinalWeightPrice() throws InterruptedException {
        distributorUI.waitForVisibility(splitFinalWeightPrice);
        distributorUI.waitForCustom(4000);
        return Double.valueOf(distributorUI.getText(splitFinalWeightPrice).replace("$",""));
    }

    //----MultiUOM ---//


    public void clickFinalWeight(String itemCode, String position){
        distributorUI.click(By.xpath(finalWeightQuantitySelect.replace("ITEMCODE",itemCode).replace("POSITION",position)));
    }

    public boolean EditWeightOverlayDisplayed(){
        return distributorUI.isDisplayed(editWeightDetailsOverlay);
    }

    public int getTotalWeight(String position){
        String totalWeightString = distributorUI.getText(By.xpath(totalWeight.replace("POSITION",position)),"value");
        return Integer.parseInt(totalWeightString.trim());

    }

    public int getNoOfUOMsOrdered(String position){
        String NoOfUOMsOrderedString = distributorUI.getText(By.xpath(totalNoOfUOMsOrdered.replace("POSITION",position)),"value") ;
        return Integer.parseInt(NoOfUOMsOrderedString.trim());
    }

    public int getWeightPerUOM(String position) throws InterruptedException {
        distributorUI.waitForCustom(3000);
        distributorUI.waitForVisibility(By.xpath(WeightPerUOM.replace("POSITION",position)));
        String WeightPerUOMString = distributorUI.getText(By.xpath(WeightPerUOM.replace("POSITION",position)),"value") ;
        return Integer.parseInt(WeightPerUOMString.trim());
    }

    public void clickOnOrderGuideSettings(){
        distributorUI.waitForClickability(dropdown_option_orderguideSettings);
        distributorUI.click(dropdown_option_orderguideSettings);
    }
    public boolean isReviewStandingOrdersDisplayed(){
        distributorUI.waitForVisibility(txt_reviewStandingOrders);
        return distributorUI.isDisplayed(txt_reviewStandingOrders);
    }

    public boolean isScanToOrderBtnDisplayed(String customerCode){
        return distributorUI.isDisplayed(By.xpath(customerScreenScanToOrderBtn.replace("CUSTOMERCODE",customerCode)));
    }
    public String getItemNameFirstMultiOUM() throws InterruptedException {
        distributorUI.scrollToElementStpByStep(lbl_firstMultiOUMItemName,3);
        distributorUI.waitForElementEnabledState(lbl_firstMultiOUMItemName,true);
        distributorUI.waitForCustom(3000);
        return distributorUI.getText(lbl_firstMultiOUMItemName);
    }


    public String getItemCodeFirstMultiOUM() throws InterruptedException {
        distributorUI.scrollToElementStpByStep(lbl_firstMultiOUMItemCode,3);
        distributorUI.waitForVisibility(lbl_firstMultiOUMItemCode);
        distributorUI.waitForCustom(3000);
        return distributorUI.getText(lbl_firstMultiOUMItemCode);
    }


    public double getActiveItemPriceFirstMultiOUMRowStable() throws InterruptedException {
        try {
            return extractPriceStable(lbl_itemPriceListMultiOUM);
        } catch (Exception e) {
            System.out.println("Fallback to alternative price locator due to: " + e.getMessage());
            return extractPriceStable(lbl_itemPriceListMultiOUM1);
        }
    }

    public String getItemNameFirstSingleOUM() throws InterruptedException {
        int rowCount = distributorUI.countElements(lbl_itemCodeLists);

        for (int i = 1; i <= rowCount; i++) {
            if (!distributorUI.isDisplayed(By.xpath(lbl_ListsMultiOUMExist.replace("ROW_COUNT",String.valueOf(i))),5)) {
                 return distributorUI.getText(By.xpath(lbl_firstSingleOUMItemName.replace("ROW_COUNT",String.valueOf(i))));
            }
        }
        return null;
    }

    public String getItemCodeFirstSingleOUM() throws InterruptedException {
        int rowCount = distributorUI.countElements(lbl_itemCodeLists);

        for (int i = 1; i <= rowCount; i++) {
            if (!distributorUI.isDisplayed(By.xpath(lbl_ListsMultiOUMExist.replace("ROW_COUNT",String.valueOf(i))),8)) {
                return distributorUI.getText(By.xpath(lbl_firstSingleOUMItemCode.replace("ROW_COUNT",String.valueOf(i))));
            }
        }
        return null;
    }

    public double getActiveItemPriceMultiOUM(String position) throws InterruptedException {
        try {
            return extractPriceStable(By.xpath(lbl_itemPriceMultiOUM.replace("UOM",position)));
        } catch (Exception e) {
            System.out.println("Fallback to alternative price locator due to: " + e.getMessage());
            return extractPriceStable(lbl_itemPriceListMultiOUM);
        }
    }

    public Double getItemPriceOnMultiOUMCheckout() throws InterruptedException {
        distributorUI.waitForVisibility(btn_orderCheckoutReview);
        distributorUI.waitForCustom(4000);
        String priceText = distributorUI.getText(btn_orderCheckoutReview).replace("$", "").replace(",", "");
        return Double.valueOf(priceText);
    }

    public void ClickOnMultiUomDropDownOG(String code)throws InterruptedException{
        distributorUI.waitForVisibility(By.xpath(multiUomDropDownOG.replace("CODE", code)));
        distributorUI.click(By.xpath(multiUomDropDownOG.replace("CODE", code)));
        distributorUI.click(multiUomOption);
        distributorUI.waitForCustom(3000);
    }

    public void clickOGAddToCartPlusIcon(String code,String uom)throws InterruptedException{
        distributorUI.waitForVisibility(By.xpath(btn_OGAddToCartPlusQuantity.replace("CODE", code).replace("UOM", uom)));
        distributorUI.click(By.xpath(btn_OGAddToCartPlusQuantity.replace("CODE", code).replace("UOM", uom)));
        distributorUI.waitForCustom(2000);
    }

    public void clickOGAddToCartMinusIcon(String code,String uom)throws InterruptedException{
        distributorUI.waitForCustom(2000);
        distributorUI.waitForVisibility(By.xpath(btn_OGAddToCartMinusQuantity.replace("CODE", code).replace("UOM", uom)));
        distributorUI.click(By.xpath(btn_OGAddToCartMinusQuantity.replace("CODE", code).replace("UOM", uom)));
        distributorUI.waitForCustom(2000);
    }
    public String getMultiOrderedId(String num) {
        String orderId = distributorUI.getText(By.xpath(txt_multiOrderId.replace("NUM", num)));
        return orderId.substring(orderId.indexOf("#") + 1).trim();
    }
    public void clickCustomerScreenScanToOrderBtn(String customerCode) throws InterruptedException {
        distributorUI.waitForCustom(2000);
        distributorUI.click(By.xpath(customerScreenScanToOrderBtn.replace("CUSTOMERCODE",customerCode)));
    }

    public boolean isCustomerProfileScreenScanToOrderButtonDisplayed() throws InterruptedException {
        distributorUI.waitForCustom(2000);
        return distributorUI.isDisplayed(By.xpath(customerProfileScreenScanToOrderBtn));
    }

    public void clickCustomerProfileScreenScanToOrderBtn() throws InterruptedException{
        distributorUI.waitForCustom(2000);
        distributorUI.click(By.xpath(customerProfileScreenScanToOrderBtn));
    }

    public void splitWeightMultiUOM(String position){
        distributorUI.click(By.xpath(lbl_itemPriceMultiUOMEdit.replace("UOM",position)));
    }

    /*public void enterCasesValueMultiUOM(String position, String num) throws InterruptedException {
        distributorUI.clearUsingJavaScript(By.xpath(txt_casesMultiUOMEdit.replace("UOM",position)));
        distributorUI.sendKeys(By.xpath(txt_casesMultiUOMEdit.replace("UOM",position)), num);
        distributorUI.waitForCustom(1000);
    }
    public void enterWeightValueMultiUOM(String position, String num) throws InterruptedException {
        distributorUI.clearUsingJavaScript(By.xpath(txt_weightMultiUOMEdit.replace("UOM",position)));
        distributorUI.sendKeys(By.xpath(txt_weightMultiUOMEdit.replace("UOM",position)), num);
        distributorUI.waitForCustom(1000);
    }*/

    public void clickAddWightRowMultiUOMIcon(String position)throws InterruptedException{
        distributorUI.waitForVisibility(By.xpath(btn_AddWightRowMultiUOMEdit.replace("UOM", position)));
        distributorUI.clickUsingJavaScript(By.xpath(btn_AddWightRowMultiUOMEdit.replace("UOM", position)));
        distributorUI.waitForCustom(2000);
    }

    public void enterCasesValueMultiUOM(String position, String anotherPosition, String num) throws InterruptedException {
        distributorUI.clearUsingJavaScript(By.xpath(txt_casesMultiUOMEdit.replace("UOM",position).replace("RECORD",anotherPosition)));
        distributorUI.sendKeys(By.xpath(txt_casesMultiUOMEdit.replace("UOM",position).replace("RECORD",anotherPosition)), num);
        distributorUI.waitForCustom(1000);
    }

    public void enterWeightValueMultiUOM(String position, String anotherPosition, String num) throws InterruptedException {
        distributorUI.clearUsingJavaScript(By.xpath(txt_weightMultiUOMEdit.replace("UOM",position).replace("RECORD",anotherPosition)));
        distributorUI.sendKeys(By.xpath(txt_weightMultiUOMEdit.replace("UOM",position).replace("RECORD",anotherPosition)), num);
        distributorUI.waitForCustom(1000);
    }
    public void clickItemType()throws InterruptedException{
        distributorUI.click(itemTypeDropDown);
        distributorUI.click(itemTypeDropDownOption);
    }

    public String getFinalWeightMultiUOM(String position, String anotherPosition){
        return distributorUI.getText(By.xpath(lbl_getItemPriceMultiUOM.replace("UOM",position).replace("RECORD",anotherPosition)));
    }

    public String getItemQtyMultiUOM(String position){
        return distributorUI.getText(By.xpath(lbl_quantityMultiUOM.replace("UOM",position)), "value");
    }

    public void selectSortItemBy(String sortBy) throws InterruptedException {
        distributorUI.click(dd_sortItemBy);
        distributorUI.waitForVisibility(By.xpath(lbl_sortItemByOption.replace("TYPE", sortBy)));
        distributorUI.click(By.xpath(lbl_sortItemByOption.replace("TYPE", sortBy)));
        distributorUI.waitForCustom(3000);
    }
    public void clickOnPlusIconInCatalog(String name){
        distributorUI.waitForVisibility(By.xpath(catalogAddToCart.replace("NAME", name)));
        distributorUI.click(By.xpath(catalogAddToCart.replace("NAME", name)));
    }

    public void clickPoundPriceMultiUOM() {
        int totalColumnCount = distributorUI.countElements(lbl_orderGuideTableColumn);

        for (int i = 1; i <= totalColumnCount; i++) {
            String columnName = distributorUI.getText(By.xpath(lbl_orderGuideTableColumnName.replace("COUNT", String.valueOf(i))));
            if ("price".equalsIgnoreCase(columnName)) {
                By priceLocator = By.xpath(poundPriceMultiUOM.replace("COUNT", String.valueOf(i)));
                distributorUI.click(priceLocator);
                break;
            }
        }
    }

    public void enterCasesPriceValueMultiUOM(String position, String num) throws InterruptedException {
        distributorUI.clearUsingJavaScript(By.xpath(txt_casesPriceMultiUOMEdit.replace("UOM",position)));
        distributorUI.sendKeys(By.xpath(txt_casesPriceMultiUOMEdit.replace("UOM",position)), num);
        distributorUI.waitForCustom(1000);
    }

    public void clickUpdatePriceMultiUOM(){
        distributorUI.click(btn_updatePrice);
    }

    public Double getPriceMultiUOM(String position) throws InterruptedException {
        int totalColumnCount = distributorUI.countElements(lbl_orderGuideTableColumn);

        for (int i = 1; i <= totalColumnCount; i++) {
            String columnName = distributorUI.getText(By.xpath(lbl_orderGuideTableColumnName.replace("COUNT", String.valueOf(i))));
            if ("price".equalsIgnoreCase(columnName)) {
                By priceLocator = By.xpath(lbl_getPriceMultiUOM.replace("COUNT", String.valueOf(i)).replace("UOM",position));
//                distributorUI.getText(priceLocator);
                return extractPriceStable(priceLocator);
            }
        }
        return null;
    }

    public double getUnitPriceMultiUOM(String position, String anotherPosition) throws InterruptedException {
        By unitWeightLocator = By.xpath(txt_weightMultiUOMEdit.replace("UOM",position).replace("RECORD",anotherPosition));
            return extractPriceStable(unitWeightLocator);
    }

    public void enterTotalWeightMultiUOM(String position, String anotherPosition, String num) throws InterruptedException {
        distributorUI.clearUsingJavaScript(By.xpath(txt_totalWeightMultiUOM.replace("UOM",position).replace("RECORD",anotherPosition)));
        distributorUI.sendKeys(By.xpath(txt_totalWeightMultiUOM.replace("UOM",position).replace("RECORD",anotherPosition)), num);
        distributorUI.waitForCustom(1000);
    }

    public void editMarginMultiUOM(String position){
        distributorUI.click(By.xpath(btn_editMarginMultiUOM.replace("UOM",position)));
    }

    public String getSalesCostMultiUOM(String position){
        return distributorUI.getText(By.xpath(lbl_salesCostMultiUOM.replace("UOM",position)), "value");
    }

    public void enterSpotPriceMultiUOM(String position, String num) throws InterruptedException {
        distributorUI.clearUsingJavaScript(By.xpath(lbl_spotPriceMultiUOM.replace("UOM",position)));
        distributorUI.sendKeys(By.xpath(lbl_spotPriceMultiUOM.replace("UOM",position)), num);
        distributorUI.waitForCustom(1000);
    }

    public void enterMarginValueMultiUOM(String position, String num) throws InterruptedException {
        distributorUI.clearUsingJavaScript(By.xpath(lbl_marginMultiUOM.replace("UOM",position)));
        distributorUI.sendKeys(By.xpath(lbl_marginMultiUOM.replace("UOM",position)), num);
        distributorUI.waitForCustom(1000);
    }
    public void enterMarginPercentageMultiUOM(String position, String num) throws InterruptedException {
        distributorUI.clearUsingJavaScript(By.xpath(lbl_marginPercentageMultiUOM.replace("UOM",position)));
        distributorUI.sendKeys(By.xpath(lbl_marginPercentageMultiUOM.replace("UOM",position)), num);
        distributorUI.waitForCustom(1000);
    }

    public String getSpotPriceMultiUOM(String position){
        return distributorUI.getText(By.xpath(lbl_spotPriceMultiUOM.replace("UOM",position)), "value");
    }

    public String getMarginValueMultiUOM(String position){
        return distributorUI.getText(By.xpath(lbl_marginMultiUOM.replace("UOM",position)), "value");
    }

    public String getMarginPercentageMultiUOM(String position){
        return distributorUI.getText(By.xpath(lbl_marginPercentageMultiUOM.replace("UOM",position)), "value");
    }
    public boolean isSubstitutionTextDisplayed()throws InterruptedException{
        distributorUI.waitForCustom(2000);
        return distributorUI.isDisplayed(txtSubstitution);
    }

    public String getItemCodeFirstMultiOUMLB() throws InterruptedException {
        int totalColumnCount = distributorUI.countElements(lbl_orderGuideTableColumn);

        for (int i = 1; i <= totalColumnCount; i++) {
            String columnName = distributorUI.getText(By.xpath(lbl_orderGuideTableColumnName.replace("COUNT", String.valueOf(i))));
            if ("price".equalsIgnoreCase(columnName)) {
//                distributorUI.scrollToElementStable(By.xpath(lbl_firstMultiOUMItemCodeLB.replace("COUNT", String.valueOf(i))),3);
                distributorUI.waitForVisibility(By.xpath(lbl_firstMultiOUMItemCodeLB.replace("COUNT", String.valueOf(i))));
                distributorUI.waitForCustom(3000);
                return distributorUI.getText(By.xpath(lbl_firstMultiOUMItemCodeLB.replace("COUNT", String.valueOf(i))));
            }
        }
        return null;
    }

    public String getItemNameFirstMultiOUMLB() throws InterruptedException {
        int totalColumnCount = distributorUI.countElements(lbl_orderGuideTableColumn);

        for (int i = 1; i <= totalColumnCount; i++) {
            String columnName = distributorUI.getText(By.xpath(lbl_orderGuideTableColumnName.replace("COUNT", String.valueOf(i))));
            if ("price".equalsIgnoreCase(columnName)) {
                distributorUI.scrollToElementStable(By.xpath(lbl_firstMultiOUMItemNameLB.replace("COUNT", String.valueOf(i))),3);
                distributorUI.waitForVisibility(By.xpath(lbl_firstMultiOUMItemNameLB.replace("COUNT", String.valueOf(i))));
                distributorUI.waitForCustom(3000);
                return distributorUI.getText(By.xpath(lbl_firstMultiOUMItemNameLB.replace("COUNT", String.valueOf(i))));
            }
        }
        return null;
    }

    public boolean isSpotPriceAdded(String position, String value){
        int totalColumnCount = distributorUI.countElements(lbl_orderGuideTableColumn);

        for (int i = 1; i <= totalColumnCount; i++) {
            String columnName = distributorUI.getText(By.xpath(lbl_orderGuideTableColumnName.replace("COUNT", String.valueOf(i))));
            if ("price".equalsIgnoreCase(columnName)) {
                return distributorUI.isDisplayed(By.xpath(spotPriceValueMultiUOM.replace("VALUE", value).replace("COUNT", String.valueOf(i)).replace("UOM",position)));
            }
        }
        return false;
    }

    public boolean isItemValueAdded(String position, String value){
        int totalColumnCount = distributorUI.countElements(lbl_orderGuideTableColumn);

        for (int i = 1; i <= totalColumnCount; i++) {
            String columnName = distributorUI.getText(By.xpath(lbl_orderGuideTableColumnName.replace("COUNT", String.valueOf(i))));
            if ("margin".equalsIgnoreCase(columnName)) {
                return distributorUI.isDisplayed(By.xpath(marginValueMultiUOM.replace("VALUE", value).replace("COUNT", String.valueOf(i)).replace("UOM",position)));
            }
        }
        return false;
    }

    public boolean isItemPercentageAdded(String position, String value){
        int totalColumnCount = distributorUI.countElements(lbl_orderGuideTableColumn);

        for (int i = 1; i <= totalColumnCount; i++) {
            String columnName = distributorUI.getText(By.xpath(lbl_orderGuideTableColumnName.replace("COUNT", String.valueOf(i))));
            if ("margin".equalsIgnoreCase(columnName)) {
                return distributorUI.isDisplayed(By.xpath(marginPercentageMultiUOM.replace("VALUE", value).replace("COUNT", String.valueOf(i)).replace("UOM",position)));
            }
        }
        return false;
    }

    public boolean isMultiUomDropDownOGDisplayed()throws InterruptedException{
       return distributorUI.isDisplayed(By.xpath(lbl_ListsMultiOUMExist.replace("ROW_COUNT","1")),5);

    }
    public void clickSubstitution()throws InterruptedException{
         distributorUI.click(txtSubstitution);
    }
    public boolean isSetSubstitutionTextDisplayed()throws InterruptedException{
        distributorUI.waitForCustom(2000);
        return distributorUI.isDisplayed(txtSetSubstitution);
    }
    public void clickChooseSub(){
        distributorUI.click(btn_chooseSub);
    }
    public void clickSelectSub(){
        distributorUI.click(btn_selectSub);
    }
    public void clickCloseSub(){
        distributorUI.click(btn_closeSub);
    }
    public void clickEditSub(){
        distributorUI.click(btn_editSub);
    }
    public void clickRemovePreviousSub(){
        distributorUI.click(btn_notSelectSub);
    }
    public boolean isCombinedPopupDisplayed() {
        return distributorUI.isDisplayed(combinedOrderPopUp);
    }
    public void clickContinueCombined(){
        distributorUI.click(combinedOrderContinue);
    }

    public String getFirstItemItemCodeFromCatalog(){
        String gotText = distributorUI.getText(catalogFirstItemItemCode);
        return gotText.split("#")[1];
    }
    public boolean isUnpaidInvoiceNamDisplayed(String name)throws InterruptedException{
        return distributorUI.isDisplayed(By.xpath(unpaidInvoiceName.replace("NAME",name)));

    }

    public String IsCustomOrderTextDisplayed() throws InterruptedException {
        distributorUI.waitForVisibility(btn_sortCustomOrder);
        distributorUI.waitForCustom(4000);
        String dropdownText = distributorUI.getText(btn_sortCustomOrder);
        return dropdownText;

    }
    public void clickF12HotKey()throws InterruptedException{
        distributorUI.clickF12Mac();
    }
    public boolean isOrderColumnDisplay(String column)throws InterruptedException{
        return distributorUI.isDisplayed(By.xpath(orderColumn.replace("COLUMN",column)),2);
    }
    public boolean orderSummeryDisplay(String summery)throws InterruptedException{
        return distributorUI.isDisplayed(By.xpath(orderSummery.replace("ORDERSUMMERY",summery)),2);
    }
    public void clickOnCaseUnit()throws InterruptedException{
        distributorUI.click(caseUnit);
    }
    public void saveItem()throws InterruptedException{
        distributorUI.click(saveItem);
        distributorUI.waitForCustom(15000);
    }
    public boolean isMultiUomDropDownExistDisplayed(String code)throws InterruptedException{
       return distributorUI.isDisplayed(By.xpath(multiUomDropDownOGExist.replace("CODE", code)));
    }
    public void clickStock()throws InterruptedException{
        distributorUI.click(btn_stock);
    }
    public boolean isStockAvailabilityDisplayed(String stock)throws InterruptedException{
        return distributorUI.isDisplayed(By.xpath(stockAvailability.replace("STOCK", stock)));
    }
    public boolean isMultiUOMStockAvailabilityDisplayed(String stock, WebDriver driver) throws InterruptedException {
        // Replace STOCK with the actual text to find
        String updatedXpath = stockAvailability.replace("STOCK", stock);

        // Find all matching elements
        List<WebElement> stockElements = driver.findElements(By.xpath(updatedXpath));

        // Return true only if at least two occurrences exist
        return stockElements.size() >= 2;
    }
    public void loginNodeExplorerPortal() throws InterruptedException{
        distributorUI.navigateToURL("https://app-uat.staging.cutanddry.com/admin/nodeType/397749147765731276");
    }
    public void clickEditSubstitutionsAccess(){
        distributorUI.isDisplayed(substitutionsAccessEditBtn);
        distributorUI.click(substitutionsAccessEditBtn);
    }
    public void editSubstitutionStatus(String status){
        distributorUI.click(substitutionDropDown);
        distributorUI.waitForVisibility(By.xpath(substitutionOption.replace("STATUS", status)));
        distributorUI.click(By.xpath(substitutionOption.replace("STATUS", status)));
    }
    public boolean isAddedPaymentMethodDisplayed(String status)throws InterruptedException{
        return distributorUI.isDisplayed(By.xpath(nodeStatus.replace("STATUS", status)));
    }
    public boolean isMissingPODisplayed()throws InterruptedException{
        return distributorUI.isDisplayed(txt_missingPO);
    }
    public boolean isOrderSummeryValueDisplayed(String name ,String value)throws InterruptedException{
        distributorUI.waitForCustom(3000);
        return distributorUI.isDisplayed(By.xpath(orderSummeryValue.replace("NAME", name).replace("VALUE",value)));
    }
    public double getItemMarginPriceFirstRow() throws InterruptedException {
        try {
            return extractPriceStable(marginPriceFirstItem);
        } catch (Exception e) {
            System.out.println("Fallback to alternative price locator due to: " + e.getMessage());
            return extractPriceStable(marginPriceFirstItem);
        }
    }
    public boolean isSubstitutesPopupDisplayedSub1(){
        return distributorUI.isDisplayed(txt_substitutions,5);
    }
    public String getSaleCommissionValue()throws InterruptedException{
        return distributorUI.getText(salesCommissionValue);
    }
    public boolean isOrderGuideTextDisplay()throws InterruptedException{
        return distributorUI.isDisplayed(orderGuideText);
    }
    public boolean isSortItemTextDisplay()throws InterruptedException{
        return distributorUI.isDisplayed(sortItemText);
    }
    public boolean isEditOrderGuideButtonDisplay()throws InterruptedException{
        return distributorUI.isDisplayed(btn_edit);
    }
    public boolean isCreateOrderGuideButtonDisplay()throws InterruptedException{
        return distributorUI.isDisplayed(btn_create);
    }
    public boolean isUploadOrderGuideButtonDisplay()throws InterruptedException{
        return distributorUI.isDisplayed(btn_uploadFileOG);
    }
    public boolean isPrintOrderGuideButtonDisplay()throws InterruptedException{
        return distributorUI.isDisplayed(btn_print);
    }
    public void clickOnDeliveryDateStable() throws InterruptedException{
        distributorUI.waitForCustom(4000);
        distributorUI.click(btn_deliveryDateStable);
    }
    public void selectDeliveryDateLineStable(String date) throws InterruptedException {
        By lbl_selectStartDate = By.xpath(dynamicToXPath.replace("DAY", date));
        distributorUI.waitForVisibility(lbl_selectStartDate);
        distributorUI.click(lbl_selectStartDate);
        distributorUI.waitForCustom(5000);
    }
    public void clickOnPlusIconInCatalogDP(String name) throws InterruptedException {
        distributorUI.waitForVisibility(By.xpath(btn_catalogPDPPlusStableDP.replace("NAME", name)));
        distributorUI.click(By.xpath(btn_catalogPDPPlusStableDP.replace("NAME", name)));
        distributorUI.waitForCustom(3000);
    }
    public void selectActiveDeliveryDate() throws InterruptedException {
        distributorUI.waitForVisibility(activeDate);
        distributorUI.click(activeDate);
        distributorUI.waitForCustom(5000);
    }
    public boolean isHardHoldPopupMessageDisplayed(String message){
        return distributorUI.isDisplayed(By.xpath(hardHoldMessagePopUp.replace("MESSAGE", message)));
    }
    public boolean isLastOrderColumnDisplayed(){
        return distributorUI.isDisplayed(lastOrder);
    }
    public void selectActiveDeliveryDateNext() throws InterruptedException {
        distributorUI.waitForVisibility(activeDateNext);
        distributorUI.click(activeDateNext);
        distributorUI.waitForCustom(5000);
    }
    public boolean isFullOrderDelayMessageDisplayed(String message){
        return distributorUI.isDisplayed(By.xpath(fullOrderDelayMessage.replace("MESSAGE", message)));
    }
    public boolean isFullOrderDelayDisplayed(){
        return distributorUI.isDisplayed(fullyOrderDelay);
    }
    public boolean isPartialShipmentNoticeDisplayed(){
        return distributorUI.isDisplayed(PartialShipmentNotice);
    }
    public boolean isCutOffTimeDisplay(){
        return distributorUI.isDisplayed(txt_cutOffTime);
    }
    public boolean isAVGTagDisplayed(String name){
        return distributorUI.isDisplayed(By.xpath(txt_avg.replace("NAME", name)));
    }
    public void clickItemTypeInCatalog()throws InterruptedException{
        distributorUI.click(itemTypeDropDown);
    }
    public boolean isItemTypeDisplayed(String name){
        return distributorUI.isDisplayed(By.xpath(txt_itemType.replace("NAME", name)));
    }
    public void clickItemTypeOption()throws InterruptedException{
        distributorUI.scrollToElement(itemTypeDropDownOption);
        distributorUI.click(itemTypeDropDownOption);
    }
    public boolean isSpecialItemDisplayed(String name)throws InterruptedException{
        return distributorUI.isDisplayed(By.xpath(txt_specialItem.replace("NAME", name)));
    }
    public boolean isUpdateMarginValueErrorDisplayed(String name)throws InterruptedException{
        return distributorUI.isDisplayed(By.xpath(txt_marginPriceError.replace("NAME", name)));
    }

    public int existingOGSection(String name) throws InterruptedException {
        return distributorUI.countElements(By.xpath(txt_addedSection.replace("NAME", name)));
    }
    public boolean isLinkedAccountDisplayed()throws InterruptedException{
        return distributorUI.isDisplayed(txt_linkedAccount);
    }
    public void clickEditChildAccount(){
        distributorUI.isDisplayed(childAccountEditBtn);
        distributorUI.click(childAccountEditBtn);
    }
    public boolean isManageChildAccountPopUpDisplayed()throws InterruptedException{
        return distributorUI.isDisplayed(txt_manageChildAccounts);
    }
    public boolean isAccountStatusDisplayed(String status)throws InterruptedException{
        return distributorUI.isDisplayed(By.xpath(accountStatus.replace("STATUS",status)));
    }
    public boolean isChildAccountDisplayed(String account){
        return distributorUI.isDisplayed(By.xpath(childAccount.replace("CHILDACC",account)));
    }
    public boolean isChildAccountEditDisplayed()throws InterruptedException{
        return distributorUI.isDisplayed(childAccountEditBtn);
    }
    public void childAccountDropDown(String account){
         distributorUI.click(By.xpath(childAccountDropDown.replace("CHILDACC",account)));
    }
    public boolean isAddedOrderGuideDisplayed(String account , String name){
        return distributorUI.isDisplayed(By.xpath(addedOrderGuide.replace("CHILDACC",account).replace("NAME",name)));
    }
    public void selectOrderGuide(String account , String name){
         distributorUI.waitForClickability(By.xpath(addedOrderGuide.replace("CHILDACC",account).replace("NAME",name)));
        distributorUI.click(By.xpath(addedOrderGuide.replace("CHILDACC",account).replace("NAME",name)));
    }
    public boolean isChildSettingUpdated(String message)throws InterruptedException{
        return distributorUI.isDisplayed(By.xpath(updateChildAccountSettings.replace("MESSAGE",message)));
    }
    public void selectNewlyCreatedOrderGuide(String name){
        distributorUI.waitForVisibility(By.xpath(dropDownOrderGuide.replace("NAME",name)));
        distributorUI.click(By.xpath(dropDownOrderGuide.replace("NAME",name)));
    }
    public void clickOnDeleteOrderGuide(){
        distributorUI.waitForClickability(btn_deleteOrderGuide);
        distributorUI.click(btn_deleteOrderGuide);
    }
    public boolean isChildAccountOGDisplayed(String account , String name){
        return distributorUI.isDisplayed(By.xpath(addedOrderGuide.replace("CHILDACC",account).replace("NAME",name)));
    }
    public boolean isDeliveryDateCustomerOrderDisplayed(String id , String date){
        return distributorUI.isDisplayed(By.xpath(deliveryDateCustomerOrder.replace("ID", id).replace("DATE",date)));
    }
    public void clickOnPickUpDateStable() throws InterruptedException{
        distributorUI.waitForCustom(4000);
        distributorUI.click(btn_pickUpDateStable);
    }
    public void selectPickUpDateLineStable(String day, boolean isNextMonth) throws InterruptedException {
        if (isNextMonth) {
            distributorUI.click(btn_nextMonth);
            distributorUI.waitForCustom(1000); // wait after clicking next month
        }

        By lbl_selectStartDate = By.xpath(dynamicToXPathStable.replace("DAY", day));
        distributorUI.waitForVisibility(lbl_selectStartDate);
        distributorUI.click(lbl_selectStartDate);
        distributorUI.waitForCustom(5000);
    }
    public void selectMailDeliveryDateLineStable(String day, boolean isNextMonth) throws InterruptedException {
        if (isNextMonth) {
            distributorUI.click(btn_nextMonth);
            distributorUI.waitForCustom(1000); // wait after clicking next month
        }

        By lbl_selectStartDate = By.xpath(dynamicToXPathStable.replace("DAY", day));
        distributorUI.waitForVisibility(lbl_selectStartDate);
        distributorUI.click(lbl_selectStartDate);
        distributorUI.waitForCustom(5000);
    }
    public boolean isFulfilmentTagDisplayed(String id , String tag){
        return distributorUI.isDisplayed(By.xpath(fulfilmentTag.replace("ID", id).replace("TAG",tag)));
    }
    public boolean isReviewOrderFulfilmentTypeDisplayed(String type){
        return distributorUI.isDisplayed(By.xpath(reviewOrderFulfilment.replace("TYPE",type)));
    }
    public boolean isSortOptionDisplayed(String option){
        return distributorUI.isDisplayed(By.xpath(sortOptionDisplay.replace("OPTION",option)));
    }
    public boolean isAddedItemDisplayed(String name){
        return distributorUI.isDisplayed(By.xpath(addedItemName.replace("NAME",name)));
    }
    public boolean isNewlyCreatedOrderGuideDisplay(String name){
        return distributorUI.isDisplayed(By.xpath(dropDownOrderGuide.replace("NAME",name)));
    }
    public boolean isParentChildTagDisplay(String code,String tag){
        return distributorUI.isDisplayed(By.xpath(parentChildTag.replace("CODE",code).replace("TAG",tag)));
    }
    public boolean isCustomerProfileParentChildTagDisplay(String tag){
        return distributorUI.isDisplayed(By.xpath(customerProfileParentChildTag.replace("TAG",tag)));
    }
    public boolean isCatalogAccessDisplay(){
        return distributorUI.isDisplayed(catalogAccessEditBtn);
    }

    public void clickSwitchToOfflineMode(){
        distributorUI.waitForClickability(dropdown_option_SwitchToOfflineMode);
        distributorUI.click(dropdown_option_SwitchToOfflineMode);
    }
    public boolean isOfflineModePopUpDisplay(){
        return distributorUI.isDisplayed(txt_offlineMode);
    }
    public void clickActiveOfflineMode() throws InterruptedException {
        distributorUI.waitForClickability(btn_activeOfflineMode);
        distributorUI.click(btn_activeOfflineMode);
        distributorUI.waitForCustom(10000);
    }
    public boolean isHangTightPopUpDisplay()throws InterruptedException{
        return distributorUI.isDisplayed(txt_hangTight);
    }
    public boolean isCatalogButtonClickable()throws InterruptedException{
        return distributorUI.isElementEnabled(btn_catalog);
    }
    public void clickGoOnline()throws InterruptedException{
        distributorUI.click(btn_activeOnLineMode);
    }
    public boolean isMoreOptionDisplay()throws InterruptedException{
        return distributorUI.isDisplayed(btn_moreOptions);
    }
    public boolean isOfflineModeOptionDisplay()throws InterruptedException{
        return distributorUI.isDisplayed(dropdown_option_SwitchToOfflineMode);
    }
    public boolean isCatalogTextDisplayed() {
        return distributorUI.isDisplayed(txt_catalog);
    }
    public boolean isChatIconDisplay(String code){
       return distributorUI.isDisplayed(By.xpath(btnChat.replace("CODE",code)));
    }
    public void clickChatIcon(String code){
        distributorUI.click(By.xpath(btnChat.replace("CODE",code)));
    }
    public boolean isChatButtonDisplayed()throws InterruptedException{
        return distributorUI.isDisplayed(btn_ChatCustomerProfile);
    }
    public void clickChatButtonInCustomerProfile()throws InterruptedException{
         distributorUI.click(btn_ChatCustomerProfile);
    }
    public boolean isEditCatalogAccessDisplay(){
       return distributorUI.isDisplayed(catalogAccessEditBtn);
    }

    public void selectDeliveryDateLineStablePick(String day, boolean isNextMonth) throws InterruptedException {
        if (isNextMonth) {
            distributorUI.click(btn_nextMonth);
            distributorUI.waitForCustom(1000); // wait after clicking next month
        }

        By lbl_selectStartDate = By.xpath(dynamicToXPathStable.replace("DAY", day));
        distributorUI.waitForVisibility(lbl_selectStartDate);
        distributorUI.click(lbl_selectStartDate);
        distributorUI.waitForCustom(5000);
    }
    public boolean isAddToOrderGuideHartIconDisplay(){
        return distributorUI.isDisplayed(btn_addToOrderGuide);
    }
    public boolean isCustomerProfileDisplayedStable(String businessName, String customerId){
        try {
            distributorUI.waitForCustom(3000);
            String nameOnly = businessName.split(" - ")[0];
            String location = businessName.split(" - ")[1];
            String fullText = nameOnly + " (" + customerId + ") - " + location;
            String xpath = "//div[contains(text(), '" + fullText + "')]";
            distributorUI.waitForVisibility(By.xpath(xpath));
            return distributorUI.isDisplayed(By.xpath(xpath));
        } catch (Exception e){
            return false;
        }
    }

    public boolean isCustomerNameDisplayed(String businessName) {
        try {
            distributorUI.waitForCustom(3000);
            String nameOnly = businessName.split(" - ")[0];
            String xpath = "//div[contains(text(), '" + nameOnly + "')]";

            distributorUI.waitForVisibility(By.xpath(xpath));
            return distributorUI.isDisplayed(By.xpath(xpath));
        } catch (Exception e) {
            return false;
        }
    }
    public boolean isCustomerIDAndLocationDisplayed(String businessName, String customerId) {
        try {
            distributorUI.waitForCustom(3000);
            String location = businessName.split(" - ")[1];
            String fullText = "(" + customerId + ") " + location;

            String xpath = "//div[contains(text(), '" + fullText + "')]";

            distributorUI.waitForVisibility(By.xpath(xpath));
            return distributorUI.isDisplayed(By.xpath(xpath));
        } catch (Exception e) {
            return false;
        }
    }
    public boolean isSameDeliveryDateErrorPopUpDisplay(){
        return distributorUI.isDisplayed(txt_sameDeliveryDate);
    }
    public String getItemMarginPercentage(String code, String uom) throws InterruptedException {
        distributorUI.waitForVisibility(By.xpath(marginValue.replace("CODE",code).replace("UOM",uom)));
        distributorUI.waitForCustom(3000);
        return distributorUI.getText(By.xpath(marginValue.replace("CODE",code).replace("UOM",uom)));
    }
    public void clickLastOrderDetailsCatalog(String name)throws InterruptedException{
        distributorUI.click(By.xpath(lbl_lastOrderDetails.replace("NAME",name)));
    }
    public boolean isPurchaseHistoryDisplay(){
        return distributorUI.isDisplayed(txt_purchaseHistoryCatalog);
    }
    public boolean isLastOrderDisplay(String order){
        return distributorUI.isDisplayed(By.xpath(lastOrderDetails.replace("ORDER",order)));
    }
    public void clickLastOrderOG(String code)throws InterruptedException{
        distributorUI.click(By.xpath(purchaseHistoryOG.replace("CODE",code)));
    }

    public void clickCloseSubstituteItemPopup(){
        distributorUI.waitForVisibility(icon_deleteSubstitutionItem);
        distributorUI.click(icon_deleteSubstitutionItem);
    }
    public void clickOrderCCEmailAlert()throws InterruptedException{
        distributorUI.waitForCustom(3000);
        distributorUI.click(btn_OrderCCEmailAlerts);
    }
    public boolean isOrderCCEmailAlertDisplay(String alert){
        return distributorUI.isDisplayed(By.xpath(txt_OrderCCEmailAlerts.replace("ALERT",alert)));
    }
    public void addEmailToSendAlertTo(String mail)throws InterruptedException{
        distributorUI.clear(sendAlertTo);
        distributorUI.sendKeys(sendAlertTo,mail);
        distributorUI.waitForCustom(3000);
    }
    public boolean isSendAlertForNewOrderDisplay()throws InterruptedException{
        return distributorUI.isDisplayed(AddedAlertToNewOder);
    }
    public boolean isSpecialOrderNoteDisplay(String note)throws InterruptedException{
        return distributorUI.isDisplayed(By.xpath(txt_specialOrderNote.replace("NOTE",note)));
    }

    public boolean isErrorPopupDisplayed(){
        return distributorUI.isDisplayed(txt_thereWasAnError);
    }

    public void ClickOnMultiUomDD(String code)throws InterruptedException{
        distributorUI.waitForVisibility(By.xpath(multiUomDropDownOGArrow.replace("CODE", code)));
        distributorUI.click(By.xpath(multiUomDropDownOGArrow.replace("CODE", code)));
        distributorUI.waitForCustom(3000);
    }
    public void clickOrderGuideItem(){
        distributorUI.click(lbl_itemNameList);
    }
    public void addAmountUsingDataPickerOG(String name,String quantity)throws InterruptedException{
        distributorUI.clearUsingJavaScript(By.xpath(dataPickerOrderGuide.replace("NAME",name)));
        distributorUI.sendKeys(By.xpath(dataPickerOrderGuide.replace("NAME",name)),quantity);
    }
    public String getAmountUsingDataPickerOG(String name){
        distributorUI.waitForVisibility(By.xpath(dataPickerOrderGuide.replace("NAME",name)));
        return distributorUI.getText(By.xpath(dataPickerOrderGuide.replace("NAME",name)), "value");
    }
    public void addAmountUsingDataPickerCatalog(String name,String quantity)throws InterruptedException{
        distributorUI.clearUsingJavaScript(By.xpath(dataPickerCatalog.replace("NAME",name)));
        distributorUI.sendKeys(By.xpath(dataPickerCatalog.replace("NAME",name)),quantity);
    }
    public String getAmountUsingDataPickerCatalogAndPDP(String name){
        distributorUI.waitForVisibility(By.xpath(dataPickerCatalog.replace("NAME",name)));
        return distributorUI.getText(By.xpath(dataPickerCatalog.replace("NAME",name)), "value");
    }
    public void addAmountUsingDataPickerReviewOrder(String name,String quantity)throws InterruptedException{
        distributorUI.clearUsingJavaScript(By.xpath(dataPickerReviewOrder.replace("NAME",name)));
        distributorUI.sendKeys(By.xpath(dataPickerReviewOrder.replace("NAME",name)),quantity);
    }
    public String getAmountUsingDataPickerReviewOrder(String name){
        distributorUI.waitForVisibility(By.xpath(dataPickerReviewOrder.replace("NAME",name)));
        return distributorUI.getText(By.xpath(dataPickerReviewOrder.replace("NAME",name)), "value");
    }
    public void clickOnPlusIconInReviewOrder(String name){
        distributorUI.waitForVisibility(By.xpath(btn_reviewPlusStable.replace("NAME", name)));
        distributorUI.click(By.xpath(btn_reviewPlusStable.replace("NAME", name)));
    }


}
