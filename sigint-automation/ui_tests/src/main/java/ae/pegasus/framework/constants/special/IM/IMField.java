package ae.pegasus.framework.constants.special.IM;

import ae.pegasus.framework.constants.controls.ControlType;

import static ae.pegasus.framework.constants.controls.ControlType.*;

public enum IMField {
    ORGANIZATION_UNITS("Responsible Org. Unit", DROPDOWN_WITH_SEARCH),
    ORGANIZATION_UNITS_CB("Responsible Org. Unit", ORG_UNIT),
    FILE_OR_CASE_NAME("Case/File name", FILE_SELECTOR),
    REQUEST_NUMBER("Request Number", DROPDOWN_WITH_SEARCH, true),
    CREATED_FOR("Created For", INPUT),
    DESCRIPTION("Description", TEXT_AREA),
    CONSIDERATIONS("Considerations", TEXT_AREA),
    RECOMMENDATIONS("Recommendations", TEXT_AREA),
    OVERVIEW("Overview", TEXT_AREA),
    RESULT("Results", TEXT_AREA),
    ATTACHEDREPORTHEADER("Originating Reports" , TEXT_AREA),
    VIEW("View", DROPDOWN_WITH_SEARCH),
    CLASSIFICATION("Classification", SIMPLE_DROPDOWN),
    SUBJECT("Subject", INPUT),
    NOTES("Notes", TEXT_AREA),
    RELATED_CASES_OR_FILES("Related Cases/Files", FILE_SELECTOR),
    PRIORITY("Priority", DROPDOWN_WITH_SEARCH),
    MANUAL_RFI_NUMBER("Manual RFI number", INPUT),
    RFI_TYPE("RFI Type", DROPDOWN_WITH_SEARCH),
    REQUIRED("Required", TEXT_AREA),
    RELATED_RFIS("Related RFIs", DROPDOWN_WITH_SEARCH),
    RELATED_PROFILES("Related Profiles", DROPDOWN_WITH_SEARCH);


    private final String fieldName;
    private final ControlType controlType;
    private final boolean isWithLoading;

    IMField(String fieldName, ControlType controlType) {
        this(fieldName, controlType, false);
    }

    IMField(String fieldName, ControlType controlType, boolean isWithLoading) {
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
