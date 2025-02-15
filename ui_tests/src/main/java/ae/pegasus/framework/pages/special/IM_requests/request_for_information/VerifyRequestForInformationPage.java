package ae.pegasus.framework.pages.special.IM_requests.request_for_information;

import ae.pegasus.framework.constants.controls.ControlType;
import ae.pegasus.framework.constants.special.IM.IMField;
import ae.pegasus.framework.pages.special.IM_requests.BaseVerifyIMPage;
import ae.pegasus.framework.utils.PageUtils;
import com.codeborne.selenide.SelenideElement;

import java.util.List;

public class VerifyRequestForInformationPage extends BaseVerifyIMPage {

    public String getSingleValue(IMField field) {
        return getSingleValue(getFieldByLabel(field.getFieldName()), field.getControlType());
    }

    @Override
    protected List<String> getListOfValues(SelenideElement fieldElement, ControlType controlType) {
        switch (controlType) {
            case DROPDOWN_WITH_SEARCH:
                return PageUtils.convertElementsCollectionToStringList(fieldElement.$$x(".//div/div/div"), true);
            default:
                return super.getListOfValues(fieldElement, controlType);
        }
    }

    public List<String> getListOfValues(IMField field) {
        return getListOfValues(getFieldByLabel(field.getFieldName()), field.getControlType());
    }
}
