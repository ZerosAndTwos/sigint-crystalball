package ae.pegasus.framework.pages.profiler;

import com.codeborne.selenide.SelenideElement;
import ae.pegasus.framework.constants.profiler.CreateTargetField;
import ae.pegasus.framework.elements.controls.datetime_selector.DateSelector;
import ae.pegasus.framework.elements.controls.dropdown.DropDown;
import ae.pegasus.framework.elements.controls.dropdown.FileSelector;
import org.apache.commons.lang.NotImplementedException;
import ae.pegasus.framework.pages.basic_pages.api.BasePage;
import ae.pegasus.framework.utils.PageUtils;

import java.time.LocalDateTime;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$x;
import static ae.pegasus.framework.constants.profiler.CreateTargetField.*;
import static ae.pegasus.framework.utils.PageUtils.clearAndType;

public class CreateTargetPage extends BasePage {

    private final String CREATE_BUTTON = "Create";
    private final String CREATE_AND_VIEW_PROFILE_BUTTON = "Create and View Profile";
    private final String CANCEL_BUTTON = "Cancel";

    @Override
    public boolean isPageDisplayed() {
        boolean result = getPageTitle().equalsIgnoreCase("Create Target");
        try {
            result &= getField(NAME).isDisplayed();
            result &= getField(DESCRIPTION).isDisplayed();
        } catch (Throwable ex) {
            result = false;
        }
        return result;
    }

    private SelenideElement getField(CreateTargetField field) {
        for (SelenideElement item : $$x("//pg-field")) {
            if(item.getAttribute("label").equalsIgnoreCase(field.getFieldName())) {
                return item;
            }
        }
        throw new IllegalArgumentException("Field '" + field.getFieldName() + "' was not found");
    }

    private SelenideElement getButton(String buttonName) {
        for (SelenideElement button : $$x("//pg-form-footer//pg-btn")) {
            if (button.getText().trim().equalsIgnoreCase(buttonName)) {
                return button;
            }
        }
        throw new IllegalArgumentException("Button with name '" + buttonName + "' was not found");
    }

    private void selectRadioButton(SelenideElement radioButtonGroup, String buttonName, String fieldName) {
        for (SelenideElement radioButton : getRadioButtons(radioButtonGroup)) {
            if (radioButton.getText().trim().equalsIgnoreCase(buttonName)) {
                radioButton.click();
                return;
            }
        }
        throw new IllegalArgumentException("Radio button '" + buttonName + "' was not found in '" + fieldName + "'");
    }

    private SelenideElement getBasicDataElement(CreateTargetField field) {
        return getBasicDataElement(field.getControlType(), getField(field));
    }

    public void setMultiValueData(CreateTargetField field, String...dataToSet) {
        SelenideElement basicDataElement = getBasicDataElement(field);
        switch (field.getControlType()) {
            case TEXT_AREA:
                clearAndType(basicDataElement, String.join("\n", dataToSet));
                break;
            case DROPDOWN_WITH_SEARCH:
                new DropDown(basicDataElement, true).searchAndSelectItems(dataToSet);
                break;
            case FILE_SELECTOR:
                new FileSelector(basicDataElement).selectItems(dataToSet);
                break;
            default:
                throw new NotImplementedException("Unexpected control '" + field.getControlType().name() + "'");
        }
    }

    public void setSingleValueData(CreateTargetField field, String dataToSet) {
        SelenideElement basicDataElement = getBasicDataElement(field);
        switch (field.getControlType()) {
            case INPUT:
                PageUtils.clearAndType(basicDataElement, dataToSet);
                break;
            case SIMPLE_DROPDOWN:
                new DropDown(basicDataElement, false).selectItems(dataToSet);
                break;
            case RADIO_BUTTON_GROUP:
                selectRadioButton(basicDataElement, dataToSet, field.getFieldName());
                break;
            default:
                throw new NotImplementedException("Unexpected control '" + field.getControlType().name() + "'");
        }
    }

    public void setActiveUntil(LocalDateTime date) {
        new DateSelector(getBasicDataElement(ACTIVE_UNTIL)).setDate(date);
    }

    private void performCreate(String createButtonLabel) {
        closeAllPopUps();
        getButton(createButtonLabel).click();
        getSuccessPopUp().should(visible);
    }

    public void createTarget() {
        performCreate(CREATE_BUTTON);
    }

    public void createTargetAndOpenProfile() {
        performCreate(CREATE_AND_VIEW_PROFILE_BUTTON);
    }

    public void cancelTargetCreation() {
        getButton(CANCEL_BUTTON).click();
    }
}
