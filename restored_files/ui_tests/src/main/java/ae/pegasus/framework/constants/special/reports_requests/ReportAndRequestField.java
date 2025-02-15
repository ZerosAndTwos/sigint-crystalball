package ae.pegasus.framework.constants.special.reports_requests;

import ae.pegasus.framework.constants.controls.ControlType;

import static ae.pegasus.framework.constants.controls.ControlType.*;

public enum ReportAndRequestField {
    CLASSIFICATION("Classification", SIMPLE_DROPDOWN),
    SUBJECT("Subject", INPUT),
    NOTES("Notes", TEXT_AREA),
    RELATED_CASES_OR_FILES("Related Cases/Files", FILE_SELECTOR);

    private final String fieldName;
    private final ControlType controlType;
    private final boolean isWithLoading;

    ReportAndRequestField(String fieldName, ControlType controlType) {
        this(fieldName, controlType, false);
    }

    ReportAndRequestField(String fieldName, ControlType controlType, boolean isWithLoading) {
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
