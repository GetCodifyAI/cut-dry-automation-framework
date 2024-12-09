package com.cutanddry.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CustomersPage extends LoginPage {
    By tbx_searchCustomers = By.xpath("//input[@placeholder='Search Customers']");
    String btnOrderGuide = "//td[text()='CODE']/../td[8]//button";
    By lbl_itemNameList = By.xpath("//td//span/div[@data-tip='View Product Details']");
    By lbl_itemDetails = By.xpath("//tbody/tr[2]");
    By btn_increaseQtyFirstRow = By.xpath("(//div//*[local-name()='svg' and @data-icon='plus' and contains(@class,'_gf457k')])[1]");
    By btn_decreaseQtyFirstRow = By.xpath("//tr[1]/td[8]/div/div/div/div[1]");
    By btn_increaseQtySecondRow = By.xpath("//tr[2]/td[8]/div/div/div/div[3]");
    By btn_decreaseQtySecondRow = By.xpath("//tr[2]/td[8]/div/div/div/div[1]");
    By btn_checkout = By.xpath("//button[text()='$']/../button[2]");
    By btn_catalog = By.xpath("//div[text()='Catalog']");
    By tbx_catalogSearch = By.xpath("//input[@placeholder='Search catalog...']");
    String lbl_catalogSearchItemList = "//div[contains(@class, '_3quvq7') and contains(text(),'NAME')]";
    String btn_addToCart = "//div[contains(@class, '_13kb1gk')]//div[text()= 'ITEMNAME']//ancestor::div[contains(@class, '_13kb1gk')]//div[@class='_btf6h0']//button[contains(@class, 'btn-outline-primary')]";
    By tbx_itemQuantityFirstRow = By.xpath("//tr[1]//td[8]//input");
    By lbl_itemPriceFirstRow = By.xpath("//tr[1]//td[7]/div");
    By lbl_itemPriceSecondRow = By.xpath("//tr[2]//td[7]/span");
    By btn_increaseQtyCatalogSearchValueOne = By.xpath("(//input[@type='number' and @value='1']/../following-sibling::div)[last()]");
    By btn_increaseQtyCatalogSearchValueTwo = By.xpath("(//input[@type='number' and @value='2']/../following-sibling::div)[last()]");
    By btn_decreaseQtyCatalogSearchValueOne = By.xpath("(//input[@type='number' and @value='1']/../preceding-sibling::div)[last()]");
    By btn_decreaseQtyCatalogSearchValueTwo = By.xpath("(//input[@type='number' and @value='2']/../preceding-sibling::div)[last()]");
    By btn_decreaseQtyCatalogSearchValueThree = By.xpath("(//input[@type='number' and @value='3']/../preceding-sibling::div)[last()]");
    By tbx_itemQuantityCatalogSearch = By.xpath("//input[@type='number']");
    By lbl_itemPriceSearchCatalogList = By.xpath("(//div[contains(., 'Artichoke') and not(contains(., '-24ct')) and not(contains(., 'Bottoms'))]//span[contains(text(),'$') and not(contains(text(),' ')) and not(@class='text-muted')])[last()]");
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
    String msg_banner = "//span[text()='TESTMESSAGE']";
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
    By txt_produce = By.xpath("//div[@class='flex-grow-1' and starts-with(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'produce')]");
    By txt_firstItem = By.xpath("//div[text()='artichoke -24ct']");
    By txt_minOrderBanner = By.xpath("//div[contains(text(), 'Add a few more items worth') and contains(text(), 'to meet minimum order amount')]");
    By txt_popupAlertOrderMin = By.xpath("//h2[text()='Order Minimum Not Met']");
    By btn_previousDraftOrderNo = By.xpath("//div[contains(text(),'previous draft order')]/..//div[text()='No']");
    String txt_customerCode = "//td[text()='CODE']";
    By tb_orders = By.xpath("//a[text()='Orders' and @role='tab']");
    By txt_standingOrders = By.xpath("//div[text()='Standing Orders: ']");
    By created_StandingOrders = By.xpath("//div[contains(text(),'Set for ')]");
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
    By OrdersTabTxt = By.xpath("//a[contains(text(),'Orders') and @class='_1n4k2vi text-center nav-item nav-link']");
    By OrderIdTxt = By.xpath("//tr[contains(@href,'/ordersView/')][1]");
    By OrderDateSort = By.xpath("//span[contains(text(),'Order Date')]");
    By DeliveryDate = By.xpath("//span[contains(text(),'Fulfill By')]");
    By OrderDateSortData = By.cssSelector("tr._du1frc td:nth-child(1)");
    By DeliveryDateSortData = By.cssSelector("tr._du1frc td:nth-child(2)");
    By txt_discountDisclaimerOrderReview = By.xpath("//div[contains(text(), 'Case discounts will be reflected on your invoice.')]");
    By txt_discountDisclaimerOrderDetails = By.xpath("//div[normalize-space() = '*Prices are subject to change. Weighed item prices are estimated. Case discounts will be reflected on your invoice.']");
    By lbl_firstRowOrderTab = By.xpath("//tr[contains(@href,'/ordersView/')][1]");
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
    By btn_orderGuide = By.xpath("//button[text()='Order Guide']");
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
    By tb_track = By.xpath("//a[text()='Track' and @role='tab']");
    By txt_customerSpecific = By.xpath("//div[contains(text(),'Customer-specific Broadcast')]");
    By txt_profile = By.xpath("//div[contains(text(),'Profile')]");
    By btn_editMessage = By.xpath("//button[contains(text(), 'Edit Message')]");
    By btn_clearMessage = By.xpath("//button[contains(text(), 'Clear Message')]");
    By btn_saveMessage = By.xpath("//button[contains(text(), 'Save Message')]");
    By btn_addItems = By.xpath("//button[contains(text(), 'Add Items')]");
    By txtArea = By.xpath("//div[contains(@class, 'col-lg-9')]//textarea");
    By input_selectItem = By.xpath("//div[contains(text(),'Select...')]/following-sibling::div//input");
    By btn_add = By.xpath("//button[contains(text(), 'Add')]");
    String btn_removeItem ="//div[text()='ITEMCODE']/following-sibling::div[2]/*";
    By EditCustomerGroupBtn = By.xpath("//div[contains(text(), 'Customer Group')]//following-sibling::div//div[@class='pl-0 col-sm-auto col-auto']");
    By CreateCutomerGroupTextField = By.xpath("//input[@id='react-select-6-input']");
    By Savebtn = By.xpath("//button[contains(text(),'Save')]");
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
    By txt_namePDP = By.xpath("//div[contains(@class, 'd-flex align-items-center mont') and contains(@class, '_1wrelxt') and contains(@class, '_1vlidrf')]");
    By lbl_orders = By.xpath("//li[contains(text(),'Orders')]");
    By txt_allItems = By.xpath("//div[text()='All Items']");
    By txt_priceZero = By.xpath("//tbody//span[contains(text(), '$0.00')]");
    By minimumOrderOverlay = By.xpath("//h2[@id='swal2-title' and contains(text(),'Order Minimum Not Met')]");
    By catalogAccessEnableTxt = By.xpath("//div[@class='list-group-item']//div[text()='Enabled']");
    By catalogAccessDisableTxt = By.xpath("//div[@class='list-group-item']//div[text()='Disabled']");
    By catalogAccessEditBtn = By.xpath("//div[contains(text(), 'Catalog Access')]//following-sibling::div//div[@class='pl-0 col-sm-auto col-auto']");
    By catalogSectionInOrderGuide = By.xpath("//div[contains(text(),'Catalog')]");
    By catalogAccessDisableOption = By.xpath("//div[contains(text(),'Disabled')]");
    By catalogAccessEnableOption = By.xpath("//div[contains(text(),'Enabled')]");
    By orderApprovalTxt = By.xpath("//div[contains(text(),'Order Approval')]");
    By orderApprovalEditBtn = By.xpath("//div[contains(text(), 'Order Approval')]//following-sibling::div//div[@class='pl-0 col-sm-auto col-auto']");
    By orderApprovalSettingsOverlayTxt = By.xpath("//div[contains(text(),'Order Approval Settings')]");
    By orderApprovalSettingsOverlayNewlyCreatedOGOptionsEnabledOrDisabled = By.xpath("//div[contains(@class,'themed_select') and contains(text(),'Enabled') or contains(text(),'Disabled')]");
    String orderApprovalSettingsOverlayOrderGuideTxt = "//div[contains(text(),'Existing Order Guide(s)')]/following-sibling::div//div[contains(text(),'ORDERGUIDE')]";
    By orderApprovalSettingsOverlayCloseBtn = By.xpath("//span[contains(text(),'×')]");
    String orderGuideOrderApprovalDisabledBtn = "//div[contains(text(),'ORDERGUIDE')]/../following-sibling::*//div[contains(@style, 'rgb(204, 204, 204)')]";
    String orderGuideOrderApprovalEnabledBtn = "//div[contains(text(),'ORDERGUIDE')]/../following-sibling::*//div[contains(@style, 'rgb(255, 255, 255)')]";
    String orderGuideOrderApprovalToggle = "//div[contains(text(),'ORDERGUIDE')]/../following-sibling::*//div[2]";
    By editExistingOrderTxt = By.xpath("//h2[contains(text(),'Edit Existing Order')]");
    By cancelBtn = By.xpath("//button[contains(text(),'Cancel')]");
    By btn_editSalesperson = By.xpath("(//*[local-name() = 'svg' and @data-icon='pen-to-square'])[6]");
    By btn_independentFoods = By.xpath("//div[contains(text(), 'Independent Foods Co')]");
    By itemNotFoundTxt = By.xpath("//div[contains(text(),'No matches found')]");
    String catalogCardAddToOGBtn = "//div[contains(text(),'ITEMCODE')]/../..//button[@data-tip='Add to Order Guide']";
    By btn_editAccHold = By.xpath("//div[contains(text(),'Account Holds')]/..//*[local-name() = 'svg' and @data-icon='pen-to-square']");
    By dropdown_acc = By.xpath("//div[text()='Account Holds']/following-sibling::div//div[contains(@class, 'themed_select__value-container')]");
    By txt_hardHold = By.xpath("//div[contains(@class, 'themed_select__option') and  text()='Hard Hold']");
    By lbl_hardHold = By.xpath("//div[text()='Account Holds']/following-sibling::div//span[contains(@class, 'badge') and text()='Hard Hold']");
    By txt_none = By.xpath("//div[contains(@class, 'themed_select__option') and  text()='None']");
    By lbl_none = By.xpath("//div[text()='Account Holds']/following-sibling::div//span[contains(@class, 'badge') and text()='None']");
    By txt_hardHoldPopup = By.xpath("//h2[contains(text(), 'Your order could not be submitted due to a hold on your account')]");
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
    By btn_signUpStatus = By.xpath("(//div[contains(@class, 'themed_select__value-container themed_select__value-container--has-value css-1hwfws3')])[4]");
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
    By btn_bulkAction = By.xpath("//span[contains(text(), 'Bulk Actions')]");
    By btn_inviteUser = By.xpath("//a[contains(text(), 'Invite Users')]");
    By txt_inviteUserPopUp = By.xpath("//div[contains(text(), 'Invite Users')]");
    By btn_allOption = By.xpath("(//div[@class='my-2 _du1frc d-flex align-items-start' ])[1]");
    By btn_inviteEmail = By.xpath("//button[contains(text(), 'invite via email')]");
    By btn_moreOption = By.xpath("//button[contains(text(), 'More Options')]");
    By btn_editOrderGuide = By.xpath("//a[contains(text(), 'Edit Order Guide')]");
    By btn_previewCatalog = By.xpath("//a[contains(text(), 'Preview Catalog')]");
    By txt_orderGuide = By.xpath("//div[contains(text(), 'Kafe Layers')]");
    By txt_customerProfile = By.xpath("//div[contains(@class, 'd-flex') and contains(text(), 'Kafe Layers')]");
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
    By btn_order = By.xpath("(//tr[contains(@class, '_du1frc _du1frc _qy4b979 py-3')])[1]");
    By txt_order = By.xpath("//h2[contains(text(), 'Order')]");
    By tb_drafts = By.xpath("//a[text()='Drafts' and @role='tab']");
    By txt_draftStatus = By.xpath("//tr[2]//td[3][contains(text(), 'just now')]");
    By btn_deleteDraft = By.xpath("(//button[contains(@class, '_47hinf btn btn-link')])[1]");
    By lbl_stopDuration = By.xpath("//div[text()='Stop Duration']/following-sibling::div//input");
    By lbl_keyDropNum = By.xpath("//div[text()='Key Drop Number']/following-sibling::div//input");
    By lbl_deliveryNotes = By.xpath("//div[text()='Delivery Notes']/following-sibling::div/textarea");
    By lbl_DoorDesc = By.xpath("//div[text()='Door Description']/following-sibling::div//input");
    By txt_stopDuration = By.xpath("//div[text()='Stop Duration']/following-sibling::div//input");
    By lbl_orderGuide = By.xpath("//div[text()='Order Guide:']/following-sibling::div//div[@class='cd_themed_select__single-value css-1uccc91-singleValue']");
    By dropdown_testGuide1 =  By.xpath("//div[contains(text(), 'Order Guide:')]//following::div[contains(text(), 'Test_Guide_01')]");
    By dropdown_testAutomation =  By.xpath("//div[contains(text(), 'Order Guide:')]//following::div[contains(text(), 'Test_Automation')]");
    By btn_editMargin = By.xpath("//td/div/button/*[local-name() = 'svg' and @data-icon='cdEdit']");
    By btn_resetValues = By.xpath("//button[contains(text(), 'Reset Values')]");
    By btn_updateValues = By.xpath("//button[contains(text(), 'Update')]");
    By lbl_margin = By.xpath("//label[text()='Margin ($)']/following-sibling::input");
    By sel_customer = By.xpath("//tr[contains(@href,'/customers/140060852')]");
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
    By btn_editNote = By.xpath("(//*[local-name() = 'svg' and @data-icon='pen-to-square'])[10]");
    By txtarea_editNote = By.xpath("(//textarea)[1]");
    By btn_saveNote = By.xpath("//button[contains(@class,'mx-2 my-2 btn btn-outline-primary btn-sm')]");
    By txt_assignSalesperson = By.xpath("//div[contains(text(),'Assign Salesperson')]");
    By dropdown_assignSalesperson = By.xpath("((//*[local-name() = 'svg' and @class='css-19bqh2r']))[2]");
    By sel_salesperson = By.xpath("//div[contains(@class, 'themed_select__option') and contains(text(), 'Ali Loynachan')]");
    By txt_assignedSalesperson = By.xpath("//td[contains(text(),'Ali Loynachan')]");
    By btn_saveChanges = By.xpath("//button[contains(text(),'Save changes')]");
    By txt_cusProfSalesperson = By.xpath("//div[contains(@class,'_vjioml w-100 border') and contains(text(),'Ali Loynachan')]");
    By btn_removeSalesperson = By.xpath("((//*[local-name() = 'svg' and @data-icon='trash-can']))[4]");
    By txt_totalOrderValue = By.xpath("//div[contains(text(),'Total Order Value')]/following-sibling::div");
    By btn_orderGuideCusProf = By.xpath("//button[contains(text(),'Order Guide')]");
    By txt_OrderGuideCusName = By.xpath("//div[contains(@class,'_1hyqzayu mont')]");
    By sel_product = By.xpath("((//*[local-name() = 'svg' and @data-icon='plus']))[8]");
    By txt_reviewPage = By.xpath("//div[contains(text(),'Review Order')]");
    By btn_orderCheckout = By.xpath("//button[contains(@data-tip,'Click here to checkout')]");
    By btn_draftNo =By.xpath("//div[contains(text(),'No')]");
    By txt_orderSuccessMsg = By.xpath("//strong[contains(text(),'Thank you for your order!')]");
    By btn_closeMsg = By.xpath("//span[text()='×']");
    By dropdown_selTags = By.xpath("(//*[local-name() = 'svg' and @class='css-19bqh2r'])");
    By dropdown_list = By.xpath("//div[contains(@class,'themed_select__menu-list themed_select')]");
    By sel_tagOption = By.xpath("//div[contains(text(),'Rep Orders')]");
    By sel_tagName = By.xpath("//div[contains(@class,'css-12jo7m5 theme')]");
    By icon_removeTag = By.xpath("((//*[local-name() = 'svg' and @class='css-19bqh2r']))[1]");
    By txt_lastOrderDate = By.xpath("//div[contains(text(),'Last ordered on')]");
    By customersText = By.xpath("//h2[contains(text(),'Customers')]");
    By btn_accountVisibility = By.xpath("((//*[local-name() = 'svg' and @data-icon='pen-to-square']))[8]");
    By dropdown_visibility = By.xpath("(//*[local-name() = 'svg' and @class='css-19bqh2r'])[1]");
    By sel_hiddenOption = By.xpath("//div[contains(text(),'Hidden')]");
    By btn_visibilitySave = By.xpath("//button[contains(@class,'mr-2 my-2 btn btn-outline')]");
    By txt_hidden = By.xpath("//div[contains(@class,'col')and contains(text(),'Hidden')]");
    By tbx_emailOrMobile = By.xpath("//input[@id='react-select-5-input']");
    By sel_cusOption = By.xpath("//div[contains(@class,' css-yt9ioa-option')and contains(text(),'Acai Bowles')]");
    By btn_loginAsCus = By.xpath("//a[contains(text(),'Login As')]");
    By btn_cusOrderIcon = By.xpath("//div[text()='Order']");
    By btn_cusAddSupplier = By.xpath("//button[contains(text(),'Add Supplier')]");
    By btn_accountStatus = By.xpath("((//*[local-name() = 'svg' and @data-icon='pen-to-square']))[12]");
    By dropdown_status = By.xpath("(//*[local-name() = 'svg' and @class='css-19bqh2r'])[1]");
    By sel_statusOption = By.xpath("//div[contains(@class,'themed_select__option') and contains(text(),'Active')]");
    By btn_statusSave = By.xpath("//button[contains(@class,'mr-2 my-2 btn btn-outline')]");
    By txt_status = By.xpath("//div[contains(text(),'Active')]");
    By ls_status = By.xpath("//div[contains(@class,'themed_select__menu-list css-11unzgr')]");




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
        distributorUI.waitForCustom(4000);
    }
    public void clickOnCatalogButton(){
        distributorUI.waitForClickability(btn_catalog);
        distributorUI.click(btn_catalog);
    }
    public void typeToSearchOnCatalog(String item) throws InterruptedException {
        distributorUI.clear(tbx_catalogSearch);
        distributorUI.waitForCustom(1000);
        distributorUI.sendKeys(tbx_catalogSearch,item);
        distributorUI.waitForCustom(5000);
    }
    public String getFirstItemNameFrmSearchResults(String name){
        return distributorUI.getText(By.xpath(lbl_catalogSearchItemList.replace("NAME", name)), 0);
    }
    public void clickAddToCartCatalog(String ItemName) throws InterruptedException {
        distributorUI.waitForClickability(By.xpath(btn_addToCart.replace("ITEMNAME",ItemName)));
        distributorUI.waitForCustom(4000);
        distributorUI.click(By.xpath(btn_addToCart.replace("ITEMNAME",ItemName)));
        distributorUI.waitForCustom(4000);
        distributorUI.waitForElementEnabledState(btn_checkout,true);
    }
    public String getItemQtyFirstRow(){
        return distributorUI.getText(tbx_itemQuantityFirstRow, "value");
    }
    public Double getItemPriceFirstRow(){
        return Double.valueOf(distributorUI.getText(lbl_itemPriceFirstRow).replace("$",""));
    }
    public String getItemPriceSecondRow(){
        distributorUI.waitForVisibility(lbl_itemPriceSecondRow);
        return distributorUI.getText(lbl_itemPriceSecondRow).replace("$","");
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
        distributorUI.waitForCustom(2000);
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
    public boolean isBroadcastMessageDisplayed(String message) {
        distributorUI.waitForVisibility(By.xpath(msg_banner.replace("TESTMESSAGE",message)));
        return distributorUI.isDisplayed(By.xpath(msg_banner.replace("TESTMESSAGE",message)));
    }
    public void clickMessage(String message){
        distributorUI.click(By.xpath(msg_banner.replace("TESTMESSAGE",message)));
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
        distributorUI.waitForVisibility(By.xpath(txt_item.replace("CODE", code)));
        distributorUI.clickUsingJavaScript(By.xpath(txt_item.replace("CODE", code)));
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
        return distributorUI.isDisplayed(txt_customerSpecific);
    }
    public boolean isProfileTextDisplayed(){
        return distributorUI.isDisplayed(txt_profile);
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
    public void clickOnRemoveItem(String Itemcode) {
        distributorUI.waitForVisibility(By.xpath(btn_removeItem.replace("ITEMCODE",Itemcode)));
        distributorUI.click(By.xpath(btn_removeItem.replace("ITEMCODE",Itemcode)));
        distributorUI.waitForInvisibility(By.xpath(btn_removeItem.replace("ITEMCODE",Itemcode)));
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
    public String getItemNamePDPView() throws InterruptedException {
        distributorUI.waitForCustom(4000);
        return distributorUI.getText(txt_namePDP);
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
        distributorUI.click(Savebtn);
    }

    public boolean isCatalogSectionDisplayInOrderGuide(){
        return distributorUI.isDisplayed(catalogSectionInOrderGuide);
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
        if(distributorUI.isDisplayed(itemNotFoundTxt)){
            distributorUI.click(By.xpath(catalogCardAddToOGBtn.replace("ITEMCODE",itemName)));
        }
        try {
            distributorUI.waitForCustom(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void clickOnEditAccHolds(){
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
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(txt_filterCustomersPopUp);
    }
    public void clickSignUpStatus(){
        distributorUI.click(btn_signUpStatus);
    }
    public void clickSignUpOption(){
        distributorUI.click(btn_signUpOption);
    }
    public void clickApply(){
        distributorUI.click(btn_apply);
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
    public void selectCustomer(){
        distributorUI.click(btn_selectCustomer);
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
    public boolean isCustomerProfileDisplayed(){
        try {
            distributorUI.waitForVisibility(txt_customerProfile);
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(txt_customerProfile);
    }
    public void clickMoreOption(){
        distributorUI.click(btn_moreOption);
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
            distributorUI.waitForVisibility(txt_orderGuide);
        } catch (Exception e){
            return false;
        }
        return distributorUI.isDisplayed(txt_orderGuide);
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
        distributorUI.waitForCustom(2000);
    }
    public boolean isMarginValuePopupDisplayed(){
        return distributorUI.isDisplayed(lbl_margin);
    }
    public void enterMarginValue(String num) throws InterruptedException {
        distributorUI.clear(lbl_margin);
        distributorUI.sendKeys(lbl_margin, num);
        distributorUI.waitForCustom(1000);
    }
    public void clickPlusQryFirstRowBySix() {
        for (int i = 0; i < 6; i++) {
            distributorUI.click(btn_increaseQtyFirstRow);
        }
    }

    public void clickOneCustomer(){
        distributorUI.waitForVisibility(sel_customer);
        distributorUI.click(sel_customer);
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

    private double getTotalOrderValue() {
        String orderValueText = distributorUI.getText(txt_totalOrderValue);
        return parseCurrency(orderValueText);
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

    public boolean isOrderValueUpdated() {
        try {
            double previousOrderValue = getTotalOrderValue();
            double newOrderValue = getTotalOrderValue();
            return newOrderValue > previousOrderValue;
        } catch (NoSuchElementException e) {
            return false;
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

    private double parseCurrency(String currencyText) {
        currencyText = currencyText.replaceAll("[^\\d.]", "");
        return Double.parseDouble(currencyText);
    }

    public void clickAddTagsDropdown(){distributorUI.click(dropdown_selTags);}

    public boolean isDropdownListDisplayed(){
        return distributorUI.isDisplayed(dropdown_list);
    }

    public void selectTagOption(){distributorUI.click(sel_tagOption);}

    public boolean isAddedTagDisplayed(){
        return distributorUI.isDisplayed(sel_tagName);
    }

    public void clickRemoveTagOption(){distributorUI.click(icon_removeTag);}

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
        placeNewOrder();
        LocalDate today = LocalDate.now();
        String updatedOrderDate = today.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        String lastOrderedDate = getLastOrderedDate();
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

    public void loginAsCus(String email)throws InterruptedException{
        distributorUI.click(tbx_emailOrMobile);
        distributorUI.sendKeys(tbx_emailOrMobile,email);
        distributorUI.waitForCustom(800);
        distributorUI.click(sel_cusOption);
        distributorUI.click(btn_loginAsCus);
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

    public void clickEditStatusIcon(){distributorUI.click(btn_accountStatus);}
    public void clickStatusDropdown(){distributorUI.click(dropdown_status);}

    public boolean isStatusDropdownListDisplayed(){
        return distributorUI.isDisplayed(ls_status);
    }

    public void selectStatusOption(){
        distributorUI.click(sel_statusOption);
        distributorUI.click(btn_statusSave);
    }

    public boolean isActiveStatusDisplayed(){
        return distributorUI.isDisplayed(txt_status);
    }


}

