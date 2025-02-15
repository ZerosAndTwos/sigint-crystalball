package ae.pegasus.framework.constants.special.reports_requests.request_for_information;

import ae.pegasus.framework.constants.controls.ControlType;

import static ae.pegasus.framework.constants.controls.ControlType.*;

public enum RequestForInformationField {
    ORGANIZATION_UNITS("Organization Units", DROPDOWN_WITH_SEARCH),
    FILE_OR_CASE_NAME("File name/Case name", FILE_SELECTOR),
    PRIORITY("Priority", DROPDOWN_WITH_SEARCH),
    MANUAL_RFI_NUMBER("Manual RFI number", INPUT),
    RFI_TYPE("RFI Type", DROPDOWN_WITH_SEARCH),
    REQUIRED("Required", TEXT_AREA),
    RELATED_RFIS("Related RFIs", DROPDOWN_WITH_SEARCH),
    RELATED_PROFILES("Related Profiles", DROPDOWN_WITH_SEARCH);

    private final String fieldName;
    private final ControlType controlType;
    private final boolean isWithLoading;

    RequestForInformationField(String fieldName, ControlType controlType) {
        this(fieldName, controlType, false);
    }

    RequestForInformationField(String fieldName, ControlType controlType, boolean isWithLoading) {
        this.fieldName = fieldName;
        this.controlType = controlType;
        this.isWithLoading = isWithLoading;
    }

    public String getFieldName() {
        return fieldName;
    }

    public ControlType getControlType() {
        return controlType;
    }

    public boolean isWithLoading() {
        return isWithLoading;
    }
}
