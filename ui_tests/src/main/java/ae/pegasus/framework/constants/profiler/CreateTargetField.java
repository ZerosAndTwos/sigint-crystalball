package ae.pegasus.framework.constants.profiler;

import ae.pegasus.framework.constants.controls.ControlType;

import static ae.pegasus.framework.constants.controls.ControlType.*;

public enum CreateTargetField {

    NAME("Target Name", INPUT),
    DESCRIPTION("Description", TEXT_AREA),
    TYPE("Type", RADIO_BUTTON_GROUP),
    CLASSIFICATION("Classification", SIMPLE_DROPDOWN),
    ORGANIZATION_UNIT("Organization Unit", DROPDOWN_WITH_SEARCH),
    CATEGORY("Category", RADIO_BUTTON_GROUP),
    ASSIGNED_TEAMS("Assigned Teams", SIMPLE_DROPDOWN),
    ASSIGNMENT_PRIORITY("Assignment Priority", RADIO_BUTTON_GROUP),
    FILE("File", FILE_SELECTOR),
    TARGET_STATUS("Target Status", RADIO_BUTTON_GROUP),
    ACTIVE_UNTIL(TARGET_STATUS.getFieldName(), DATE_TIME_SELECTOR),
    THREAT_SCORE_LIKELIHOOD("Threat Score Likelihood", SIMPLE_DROPDOWN),
    THREAT_SCORE_IMPACT("Threat Score Impact", SIMPLE_DROPDOWN),
    CRIMINAL_RECORD("Criminal Record", RADIO_BUTTON_GROUP),
    RFI("RFI", DROPDOWN_WITH_SEARCH);

    private final String fieldName;

    private final ControlType controlType;

    CreateTargetField(String fieldName, ControlType controlType) {
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
