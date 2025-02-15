package ae.pegasus.framework.constants.special.create_event_record;

import ae.pegasus.framework.constants.controls.ControlType;

import static ae.pegasus.framework.constants.controls.ControlType.*;

public enum CreatedRecordField {
    SOURCE_TYPE("Source Type", SIMPLE_DROPDOWN),
    RECORD_TYPE("Record Type", SIMPLE_DROPDOWN),
    LANGUAGE("Language", DROPDOWN_WITH_SEARCH),
    DATE_AND_TIME("Date and Time", DATE_TIME_SELECTOR),
    FROM_NUMBER("From Number", INPUT),
    TO_NUMBER("To Number", INPUT),
    FROM_COUNTRY("From Country", DROPDOWN_WITH_SEARCH),
    TO_COUNTRY("To Country", DROPDOWN_WITH_SEARCH),
    FROM_EMAIL("From E-mail", INPUT),
    TO_EMAIL("To E-mail", INPUT),
    TMSI("TMSI", INPUT),
    IMSI("IMSI", INPUT),
    RECORD_ID("Record ID", INPUT),
    SMS_TEXT("SMS Text", TEXT_AREA),
    AUDIO_DURATION("Audio Duration (seconds)", INPUT),
    ATTACHMENT_DETAILS("Attachment Details (Attachment Transcription)", TEXT_AREA);

    private final String fieldName;

    private final ControlType controlType;

    CreatedRecordField(String fieldName, ControlType controlType) {
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
