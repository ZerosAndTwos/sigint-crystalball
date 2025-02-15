package ae.pegasus.framework.constants.special.saved_search;

import ae.pegasus.framework.constants.controls.ControlType;

import static ae.pegasus.framework.constants.controls.ControlType.*;

public enum SavedSearchProperty {
    NAME("Name", INPUT),
    DESCRIPTION("Description", TEXT_AREA),
    CLASSIFICATION("Classification", SIMPLE_DROPDOWN),
    ORGANIZATION_UNIT("Organization Unit", DROPDOWN_WITH_SEARCH),
    FILES("Files", FILE_SELECTOR);

    private final String propertyName;

    private final ControlType controlType;

    SavedSearchProperty(String propertyName, ControlType controlType) {
        this.propertyName = propertyName;
        this.controlType = controlType;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public ControlType getControlType() {
        return controlType;
    }
}
