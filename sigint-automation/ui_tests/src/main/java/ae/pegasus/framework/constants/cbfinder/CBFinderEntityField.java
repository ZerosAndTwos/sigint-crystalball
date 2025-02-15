package ae.pegasus.framework.constants.cbfinder;

import ae.pegasus.framework.constants.controls.ControlType;

import static ae.pegasus.framework.constants.controls.ControlType.*;

public enum CBFinderEntityField {
    NAME("Name", INPUT),
    DESCRIPTION("Description", TEXT_AREA),
    CLASSIFICATION("Classification", SIMPLE_DROPDOWN),
    ORGANIZATION_UNITS("Organization Unit", DROPDOWN_WITH_SEARCH);

    private final String fieldName;

    private final ControlType controlType;

    CBFinderEntityField(String fieldName, ControlType controlType) {
        this.fieldName = fieldName;
        this.controlType = controlType;
    }

    public String getFieldName() {
        return fieldName;
    }

    public ControlType getControlType() {
        return controlType;
    }
}
