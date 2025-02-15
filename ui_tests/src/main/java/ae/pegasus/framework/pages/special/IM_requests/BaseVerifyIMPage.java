package ae.pegasus.framework.pages.special.IM_requests;

import ae.pegasus.framework.constants.controls.ControlType;
import ae.pegasus.framework.constants.special.IM.IMAction;
import ae.pegasus.framework.constants.special.IM.IMField;
import ae.pegasus.framework.utils.PageUtils;
import com.codeborne.selenide.SelenideElement;

import java.util.List;

import static com.codeborne.selenide.Selenide.$$x;

public abstract class BaseVerifyIMPage extends BaseIMPage {

    protected String getSingleValue(SelenideElement fieldElement, ControlType controlType) {
        switch (controlType) {
            case INPUT:
            case TEXT_AREA:
            case SIMPLE_DROPDOWN:
            case DROPDOWN_WITH_SEARCH:
                return fieldElement.$x("./div").getText().trim();
            default:
                throw new IllegalArgumentException("Control type '" + controlType + "' is not applicable for getting of single value");
        }
    }

    public String getReportOrRequestStatus() {
        return getReportOrRequestStaticInformationBlock().$x(".//div//b").getText().trim();
    }

    public String getSingleValue(IMField field) {
        return getSingleValue(getFieldByLabel(field.getFieldName()), field.getControlType());
    }

    protected List<String> getListOfValues(SelenideElement fieldElement, ControlType controlType) {
        switch (controlType) {
            case FILE_SELECTOR:
                return PageUtils.convertElementsCollectionToStringList(fieldElement.$$x(".//a"), true);
            case DROPDOWN_WITH_SEARCH:
                return PageUtils.convertElementsCollectionToStringList(fieldElement.$$x(".//div/span"), true);
            case ORG_UNIT:
                return PageUtils.convertElementsCollectionToStringList(fieldElement.$$x(".//div/org-unit-tag"), true);
            default:
                throw new IllegalArgumentException("Control type '" + controlType + "' is not applicable for getting of list of values");
        }
    }

    public List<String> getListOfValues(IMField field) {
        return getListOfValues(getFieldByLabel(field.getFieldName()), field.getControlType());
    }

    public boolean checkVisibilityOfReportAction (IMAction action) {
        for (SelenideElement button : getReportOrRequestHeader().$$x(".//pg-btn//span/span[1]")) {
            if(button.getText().trim().equalsIgnoreCase(action.getActionName())
                    && button.isEnabled()) {
                return true;
            }
        }
        return false;
    }

    public boolean checkVisibilityOnReportCreation (IMAction action) {
        for (SelenideElement button : $$x(".//pg-btn//span/span[1]")) {
            if(button.getText().trim().equalsIgnoreCase(action.getActionName())
                    && button.isEnabled()) {
                return true;
            }
        }
        return false;
    }
}
