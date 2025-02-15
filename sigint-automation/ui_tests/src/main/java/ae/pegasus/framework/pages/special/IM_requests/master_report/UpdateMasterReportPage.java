package ae.pegasus.framework.pages.special.IM_requests.master_report;

import ae.pegasus.framework.constants.special.IM.IMField;
import ae.pegasus.framework.pages.special.IM_requests.operator_report.UpdateOperatorReportPage;
import com.codeborne.selenide.SelenideElement;

public class UpdateMasterReportPage extends UpdateOperatorReportPage {
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
