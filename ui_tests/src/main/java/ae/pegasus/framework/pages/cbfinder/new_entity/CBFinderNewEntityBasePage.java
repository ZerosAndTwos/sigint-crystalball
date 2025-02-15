package ae.pegasus.framework.pages.cbfinder.new_entity;

import com.codeborne.selenide.SelenideElement;
import ae.pegasus.framework.constants.cbfinder.CBFinderEntityField;
import ae.pegasus.framework.elements.controls.dropdown.DropDown;
import org.apache.commons.lang.NotImplementedException;
import ae.pegasus.framework.pages.cbfinder.CBFinderBasePage;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static ae.pegasus.framework.utils.PageUtils.clearAndType;

public class CBFinderNewEntityBasePage extends CBFinderBasePage {
    protected SelenideElement getButtonByName(String buttonName) {
        for (SelenideElement button : $$x("//pg-form-footer//pg-btn")) {
            if (button.getText().trim().equalsIgnoreCase(buttonName)) {
                return button;
            }
        }
        throw new IllegalArgumentException("Button with name '" + buttonName + "' was not found");
    }

    protected SelenideElement getNewEntityCreationDialog() {
        return $x("//router-view//compose");
    }

    protected SelenideElement getContainer(CBFinderEntityField field) {
        return getNewEntityCreationDialog().$x(".//pg-field[@label='" + field.getFieldName() + "']");
    }

    protected SelenideElement getFieldDataElement(CBFinderEntityField field) {
        return getBasicDataElement(field.getControlType(), getContainer(field));
    }

    public void setSingleValueData(CBFinderEntityField field, String dataToSet) {
        SelenideElement fieldDataElement = getFieldDataElement(field);
        switch (field.getControlType()) {
            case INPUT:
                clearAndType(fieldDataElement, dataToSet);
                break;
            case SIMPLE_DROPDOWN:
                new DropDown(fieldDataElement, false).selectItems(dataToSet);
                break;
            default:
                throw new NotImplementedException("Unexpected control '" + field.getControlType().name() + "'");
        }
    }

    public void setMultiValueData(CBFinderEntityField field, String...dataToSet) {
        SelenideElement fieldDataElement = getFieldDataElement(field);
        switch (field.getControlType()) {
            case TEXT_AREA:
                clearAndType(fieldDataElement, String.join("\n", dataToSet));
                break;
            case DROPDOWN_WITH_SEARCH:
                new DropDown(fieldDataElement, true).searchAndSelectItems(dataToSet);
                break;
            default:
                throw new NotImplementedException("Unexpected control '" + field.getControlType().name() + "'");
        }
    }
}
