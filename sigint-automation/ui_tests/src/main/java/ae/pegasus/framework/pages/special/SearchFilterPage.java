package ae.pegasus.framework.pages.special;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ae.pegasus.framework.constants.controls.datetime_selector.DateRangeCalendar;
import ae.pegasus.framework.constants.controls.datetime_selector.DateRangePeriod;
import ae.pegasus.framework.constants.special.search_filter.FilterSetting;
import ae.pegasus.framework.constants.special.search_filter.FilterTab;
import ae.pegasus.framework.elements.controls.datetime_selector.DateRangeSelector;
import ae.pegasus.framework.elements.controls.dropdown.DropDown;
import ae.pegasus.framework.elements.controls.dropdown.FileSelector;
import org.apache.commons.lang.NotImplementedException;
import ae.pegasus.framework.pages.basic_pages.api.BaseSpecialPage;
import ae.pegasus.framework.utils.PageUtils;

import java.time.LocalDateTime;

public class SearchFilterPage extends BaseSpecialPage {

    private final String COMMON_SETTING_NAME_XPATH = ".//div[@class='search-filter__header']";
    private final String CHECK_BOX_SETTING_NAME_XPATH = ".//pg-checkbox//span";

    @Override
    public boolean isPageDisplayed() {
        return getDialogButtonsArea().isDisplayed();
    }

    private ElementsCollection getTabs() {
        return getSpecialPageBase().$$x(".//div[@class='pg-nav-tabs__tabs']/div");
    }

    private SelenideElement getTab(FilterTab filterTab) {
        for(SelenideElement tab : getTabs()) {
            if(tab.getText().trim().equalsIgnoreCase(filterTab.getTabName())) {
                return tab;
            }
        }
        throw new IllegalArgumentException("Search Filter Setting Tab with name '" + filterTab.getTabName() + "' was not found");
    }

    public void selectTab(FilterTab filterTab) {
        getTab(filterTab).click();
    }

    private ElementsCollection getSettings() {
        return getSpecialPageBase().$$x(".//search-filter");
    }
    private SelenideElement getSetting(FilterSetting filterSetting) {
        selectTab(filterSetting.getFilterTab());
        String settingNameXPath = PageUtils.bindXPaths(COMMON_SETTING_NAME_XPATH, CHECK_BOX_SETTING_NAME_XPATH);
        for (SelenideElement setting : getSettings()) {
            if (!(setting.getText().equals(""))) {
                if (setting.$x(settingNameXPath).getText().trim().equalsIgnoreCase(filterSetting.getSettingName())) {
                    scrollWindowTo(setting);
                    return setting;
                }
            }
        }
        throw new IllegalArgumentException("Search Filter Setting with name '" + filterSetting.getSettingName() + "' was not found");
    }

    private SelenideElement getBasicDataElement(FilterSetting filterSetting) {
        return getBasicDataElement(filterSetting.getControlType(), getSetting(filterSetting));
    }

    public void setMultiValueSetting(FilterSetting filterSetting, String...values) {
        SelenideElement settingItem = getBasicDataElement(filterSetting);
        clearSetting(filterSetting);
        switch (filterSetting.getControlType()) {
            case DROPDOWN_WITH_SEARCH:
                new DropDown(settingItem, filterSetting.isWithLoading()).searchAndSelectItems(values);
                break;
            default:
                throw new NotImplementedException();
        }

    }

    public void setSingleValueSetting(FilterSetting filterSetting, String value) {
        SelenideElement settingItem = getBasicDataElement(filterSetting);
        switch (filterSetting.getControlType()) {
            case DROPDOWN_WITH_SEARCH:
                new DropDown(settingItem, filterSetting.isWithLoading()).searchAndSelectItems(value);
                break;
            case RADIO_BUTTON_GROUP:
                getRadioButtons(settingItem).findBy(Condition.text(value)).click();
                break;
            case INPUT:
                PageUtils.clearAndType(settingItem, value);
                break;
            default:
                throw new NotImplementedException();
        }
    }

    public void setNoValueSetting(FilterSetting filterSetting) {
        SelenideElement settingItem = getBasicDataElement(filterSetting);
        switch (filterSetting.getControlType()) {
            case CHECKBOX:
                if (!settingItem.isSelected()) {
                    settingItem.click();
                }
                break;
            default:
                throw new NotImplementedException();
        }
    }

    private DateRangeSelector getDateRangeSelector(FilterSetting filterSetting) {
        SelenideElement settingItem = getBasicDataElement(filterSetting);
        return new DateRangeSelector(settingItem);
    }

    public void setDateRange(FilterSetting filterSetting, LocalDateTime dateTimeFrom, LocalDateTime dateTimeTo) {
        if (dateTimeFrom == null || dateTimeTo == null) {
            throw new IllegalArgumentException("Both dates should be provided there is separate method for setting only one date");
        }
        getDateRangeSelector(filterSetting).setRange(dateTimeFrom, dateTimeTo);
    }

    public void setDateRangePeriod(FilterSetting filterSetting, DateRangePeriod dateRangePeriod) {
        getDateRangeSelector(filterSetting).setDateRangePeriod(dateRangePeriod);
    }

    public void setSingleDateInPeriod(FilterSetting filterSetting, DateRangeCalendar calendar, LocalDateTime dateTime) {
        if (dateTime == null) {
            throw new IllegalArgumentException("Date should be provided");
        }
        getDateRangeSelector(filterSetting).setOneDate(calendar, dateTime);
    }

    public void setDateInPeriod(FilterSetting filterSetting, DateRangeCalendar calendar, LocalDateTime dateTime) {
        if (dateTime == null) {
            throw new IllegalArgumentException("Date should be provided");
        }
        getDateRangeSelector(filterSetting).setOneDate(calendar, dateTime);
    }

    public void clearSetting(FilterSetting filterSetting) {
        SelenideElement settingItem = getBasicDataElement(filterSetting);
        switch (filterSetting.getControlType()) {
            case CHECKBOX:
                if (settingItem.isSelected()) {
                    settingItem.click();
                }
                break;
            case DROPDOWN_WITH_SEARCH:
                new DropDown(settingItem, filterSetting.isWithLoading()).removeAllSelectedItems();
                break;
            case FILE_SELECTOR:
                new FileSelector(settingItem).removeAllSelectedItems();
                break;
            case RADIO_BUTTON_GROUP:
                getRadioButtons(settingItem).get(0).click();
                break;
            default:
                throw new NotImplementedException();
        }
    }

    private SelenideElement getDialogButtonsArea() {
        return getSpecialPageBase().$x(".//pg-row[contains(@class, 'search-filter-dropdown__buttons')]");
    }

    public void applySearch() {
        getDialogButtonsArea().$x(".//pg-btn[@label='Apply']").click();
    }
}
