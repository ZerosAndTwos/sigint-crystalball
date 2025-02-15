package ae.pegasus.framework.constants.special.modal_dialog;

import ae.pegasus.framework.constants.controls.ControlType;

import static ae.pegasus.framework.constants.controls.ControlType.*;

public enum ModalDialogField {
    COMMENTS("Comments", TEXT_AREA);

    private final String fieldName;

    private final ControlType controlType;

    ModalDialogField(String fieldName, ControlType controlType) {
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
