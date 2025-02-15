package ae.pegasus.framework.pages.special.create_event_record;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ae.pegasus.framework.constants.special.create_event_record.CreatedRecordField;
import ae.pegasus.framework.context.Context;
import ae.pegasus.framework.elements.controls.datetime_selector.DateSelector;
import ae.pegasus.framework.elements.controls.dropdown.DropDown;
import org.apache.commons.lang.NotImplementedException;
import ae.pegasus.framework.pages.basic_pages.ModalDialogPage;
import ae.pegasus.framework.utils.PageUtils;

import java.time.LocalDateTime;

import static com.codeborne.selenide.Condition.visible;

public class ManualRecordPage extends ModalDialogPage {
    @Override
    public boolean isPageDisplayed() {
        return getPageTitle().equalsIgnoreCase("Manual Event Record");
    }

    private ElementsCollection getFields() {
        return getDialogBody().$$x(".//pg-form-group/fieldset//pg-field");
    }

    private SelenideElement getFieldDataElement(CreatedRecordField field) {
        for (SelenideElement fieldElement : getFields()) {
            String labelText = fieldElement.getAttribute("label").trim();
            if (labelText.equalsIgnoreCase(field.getFieldName()) ||
                    labelText.equalsIgnoreCase(field.getFieldName() + ":")) {
                return getBasicDataElement(field.getControlType(), fieldElement);
            }
        }
        throw new IllegalArgumentException("Field with name '" + field.getFieldName() + "' was not found");
    }

    public void setFieldValue(CreatedRecordField field, String fieldValue) {
        SelenideElement fieldDataElement = getFieldDataElement(field);
        switch (field.getControlType()) {
            case INPUT:
            case TEXT_AREA:
                PageUtils.clearAndType(fieldDataElement, fieldValue);
                break;
            case SIMPLE_DROPDOWN:
                new DropDown(fieldDataElement, false).selectItems(fieldValue);
                break;
            case DROPDOWN_WITH_SEARCH:
                new DropDown(fieldDataElement, false).searchAndSelectItems(fieldValue);
                break;
            default:
                throw new NotImplementedException("Support of '" + field.getControlType().toString() + "' control is not implemented");
        }
        if (Context.getContext().isRecordCreationInProgress()) {
            Context.getContext().setFieldValueForCurrentlyCreatedRecord(field, fieldValue);
        }
    }

    public void setDate(CreatedRecordField field, LocalDateTime dateTime) {
        SelenideElement fieldDataElement = getFieldDataElement(field);
        switch (field.getControlType()) {
            case DATE_TIME_SELECTOR:
                new DateSelector(fieldDataElement).setDate(dateTime);
                break;
            default:
                throw new IllegalArgumentException("This method supports only date fields");
        }
        if (Context.getContext().isRecordCreationInProgress()) {
            Context.getContext().setFieldValueForCurrentlyCreatedRecord(field, dateTime);
        }
    }

    public void saveCreatedRecord() {
        closeAllPopUps();
        getDialogFooter().$x("./button[1]").click();
        Context.getContext().endNewRecordCreation();
        waitDialogSaving();
        getSuccessPopUp().shouldBe(visible);
    }
}
