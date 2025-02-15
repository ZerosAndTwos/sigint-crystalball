package ae.pegasus.framework.pages.special.IM_requests.operator_report;

import com.codeborne.selenide.SelenideElement;
import ae.pegasus.framework.constants.special.IM.IMField;
import ae.pegasus.framework.pages.special.IM_requests.BaseUpdateIMPage;

public class UpdateOperatorReportPage extends BaseUpdateIMPage {
    private SelenideElement getBasicDataElement(IMField field) {
        return getBasicDataElement(field.getControlType(), getFieldByLabel(field.getFieldName()));
    }

    public void setMultiValueData(IMField field, String...values) {
        setMultiValueData(getBasicDataElement(field),
                field.getControlType(),
                field.isWithLoading(),
                values);
    }

    public void setSingleValueData(IMField field, String value) {
        setSingleValueData(getBasicDataElement(field),
                field.getControlType(),
                field.isWithLoading(),
                value);
    }
}
