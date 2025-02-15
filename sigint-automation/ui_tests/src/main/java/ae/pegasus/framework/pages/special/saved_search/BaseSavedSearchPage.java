package ae.pegasus.framework.pages.special.saved_search;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ae.pegasus.framework.constants.special.saved_search.*;
import ae.pegasus.framework.elements.controls.datetime_selector.DateSelector;
import ae.pegasus.framework.elements.controls.dropdown.DropDown;
import ae.pegasus.framework.elements.controls.dropdown.FileSelector;
import org.apache.commons.lang.NotImplementedException;
import ae.pegasus.framework.pages.Pages;
import ae.pegasus.framework.pages.basic_pages.api.BaseModalDialogPage;
import ae.pegasus.framework.utils.PageUtils;

import java.time.LocalDateTime;

import static com.codeborne.selenide.Condition.visible;
import static ae.pegasus.framework.constants.special.saved_search.SavedSearchField.*;
import static ae.pegasus.framework.constants.special.saved_search.SavedSearchScheduleField.*;

public abstract class BaseSavedSearchPage extends BaseModalDialogPage {
    private ElementsCollection getFieldsHeader() {
        return getDialogBody().$$x(".//pg-form-head");
    }

    private SelenideElement getFieldBase(SavedSearchField field) {
        int fieldIndex = 1;
        for (SelenideElement fieldHeader : getFieldsHeader()) {
            if (fieldHeader.$x("./h2").getAttribute("innerText").trim().equalsIgnoreCase(field.getFieldName())) {
                return getDialogBody().$x(".//pg-form-group[" + fieldIndex + "]");
            }
            fieldIndex++;
        }
        throw new IllegalArgumentException("Saved Search field '" + field.getFieldName() + "' was not found");
    }

    private SelenideElement getProperty(SavedSearchProperty property) {
        return getFieldBase(PROPERTIES).$x(".//pg-field[@label='" + property.getPropertyName() + "']");
    }

    private SelenideElement getBasicDataElement(SavedSearchProperty property) {
        return getBasicDataElement(property.getControlType(), getProperty(property));
    }

    public void setPropertyMultiValues(SavedSearchProperty property, String...propertyValues) {
        SelenideElement basicDataElement = getBasicDataElement(property);
        switch (property.getControlType()) {
            case TEXT_AREA:
                PageUtils.clearAndType(basicDataElement, String.join("\n", propertyValues));
                break;
            case FILE_SELECTOR:
                new FileSelector(basicDataElement).selectItems(propertyValues);
                break;
            case DROPDOWN_WITH_SEARCH:
                new DropDown(basicDataElement, true).searchAndSelectItems(propertyValues);
                break;
            default:
                throw new IllegalArgumentException("Multi-values are not applicable for " + property.getControlType().name());
        }
    }

    public void setPropertyValue(SavedSearchProperty property, String propertyValue) {
        SelenideElement basicDataElement = getBasicDataElement(property);
        switch (property.getControlType()) {
            case INPUT:
                PageUtils.clearAndType(basicDataElement, propertyValue);
                break;
            case SIMPLE_DROPDOWN:
                new DropDown(basicDataElement, false).selectItems(propertyValue);
                break;
            default:
                throw new NotImplementedException("Unexpected control '" + property.getControlType().name() + "'");
        }
    }

    private String getSearchingCriteriaFieldValue(SelenideElement fieldBase, SearchingCriteriaField field) {
        String result;
        switch (field) {
            case QUERY:
                result = fieldBase.$x("./fieldset/div").getText();
                break;
            default:
                result = fieldBase.$x(".//search-filter-tag//div[contains(., '" + field.getFieldName() + "')]").getText();
                break;
        }
        return result.replace(field.getFieldName(), "").trim();
    }

    public String[] getSearchingCriteria(SearchingCriteriaField field) {

        return getSearchingCriteriaFieldValue(getFieldBase(SEARCHING_CRITERIA), field).split(", ");
    }

    private SelenideElement getButton(SavedSearchButton button) {
        return getDialogFooter().$x(".//span[text()='" + button.getButtonName() + "']//ancestor::button");
    }

    public void clickButton(SavedSearchButton button) {
        SelenideElement buttonElement = getButton(button);
        switch (button) {
            case SAVE_SEARCH:
                closeAllPopUps();
                buttonElement.click();
                getSuccessPopUp().shouldBe(visible);
                break;
            case EDIT_QUERY:
                buttonElement.click();
                Pages.searchPage().waitForPageLoading();
                break;
            default:
                buttonElement.click();
                break;
        }
    }

    private SelenideElement getBasicDataElement(SavedSearchScheduleField field) {
        SelenideElement fieldBase = getFieldBase(SCHEDULED_SEARCH);
        switch (field) {
            case START_DATE:
                fieldBase = fieldBase.$x(".//pg-field[1]");
                break;
            case END_DATE:
                fieldBase = fieldBase.$x(".//pg-field[2]");
                break;
        }
        return getBasicDataElement(field.getControlType(), fieldBase);
    }

    public void setupSchedule(LocalDateTime startDate, SavedSearchScheduleRepeatInterval repeatInterval, LocalDateTime endDate) {
        SelenideElement runElement = getBasicDataElement(RUN);
        if (!runElement.isSelected()) {
            runElement.click();
        }

        new DateSelector(getBasicDataElement(START_DATE), false).setDate(startDate);
        new DropDown(getBasicDataElement(REPEAT_INTERVAL), false).selectItems(repeatInterval.getRepeatIntervalName());
        new DateSelector(getBasicDataElement(END_DATE), false).setDate(endDate);

    }
}
