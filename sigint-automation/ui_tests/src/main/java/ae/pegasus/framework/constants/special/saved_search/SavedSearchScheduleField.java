package ae.pegasus.framework.constants.special.saved_search;

import ae.pegasus.framework.constants.controls.ControlType;

import static ae.pegasus.framework.constants.controls.ControlType.*;

public enum SavedSearchScheduleField {
    RUN(CHECKBOX),
    START_DATE(DATE_TIME_SELECTOR),
    REPEAT_INTERVAL(SIMPLE_DROPDOWN),
    END_DATE(DATE_TIME_SELECTOR);

    private final ControlType controlType;

    SavedSearchScheduleField(ControlType controlType) {
        this.controlType = controlType;
    }

    public ControlType getControlType() {
        return controlType;
    }
}
