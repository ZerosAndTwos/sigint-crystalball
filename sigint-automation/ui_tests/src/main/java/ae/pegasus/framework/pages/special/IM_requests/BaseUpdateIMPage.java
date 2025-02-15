package ae.pegasus.framework.pages.special.IM_requests;

import ae.pegasus.framework.constants.controls.ControlType;
import ae.pegasus.framework.constants.special.IM.IMField;
import ae.pegasus.framework.elements.controls.dropdown.DropDown;
import ae.pegasus.framework.elements.controls.dropdown.FileSelector;
import ae.pegasus.framework.utils.PageUtils;
import com.codeborne.selenide.SelenideElement;

public abstract class BaseUpdateIMPage extends BaseIMPage {

    private SelenideElement getBasicDataElement(IMField field) {
        return getBasicDataElement(field.getControlType(), getFieldByLabel(field.getFieldName()));
    }

    protected void setMultiValueData(SelenideElement basicDataElement, ControlType controlType, boolean isWithLoading, String...values) {
        switch (controlType) {
            case TEXT_AREA:
                PageUtils.clearAndType(basicDataElement, String.join("\n", values));
                break;
            case DROPDOWN_WITH_SEARCH:
                new DropDown(basicDataElement, isWithLoading).searchAndSelectItems(values);
                break;
            case FILE_SELECTOR:
                new FileSelector(basicDataElement).selectItems(values);
                break;
            default:
                throw new IllegalArgumentException("Unexpected control type '" + controlType + "' for setting of multi-value data");
        }
    }

    public void setMultiValueData(IMField field, String...values) {
        setMultiValueData(getBasicDataElement(field),
                field.getControlType(),
                field.isWithLoading(),
                values);
    }

    protected void setSingleValueData(SelenideElement basicDataElement, ControlType controlType, boolean isWithLoading, String value) {
        switch (controlType) {
            case INPUT:
                PageUtils.clearAndType(basicDataElement, value);
                break;
            case SIMPLE_DROPDOWN:
                new DropDown(basicDataElement, isWithLoading).selectItems(value);
                break;
            default:
                throw new IllegalArgumentException("Unexpected control type '" + controlType + "' for setting of single value");
        }
    }

    public void setSingleValueData(IMField field, String value) {
        setSingleValueData(getBasicDataElement(field),
                field.getControlType(),
                field.isWithLoading(),
                value);
    }
}
