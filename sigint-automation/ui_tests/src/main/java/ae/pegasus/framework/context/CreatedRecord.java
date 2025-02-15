package ae.pegasus.framework.context;

import ae.pegasus.framework.constants.special.create_event_record.CreatedRecordField;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static ae.pegasus.framework.constants.special.create_event_record.CreatedRecordField.RECORD_TYPE;
import static ae.pegasus.framework.constants.special.create_event_record.CreatedRecordField.SOURCE_TYPE;

public class CreatedRecord {

    private Map<CreatedRecordField, Object> recordFields = new HashMap<>();

    public CreatedRecord() {
        recordFields.put(SOURCE_TYPE, "Manual");
        recordFields.put(RECORD_TYPE, "Voice");
    }

    public Object getRecordFieldValue(CreatedRecordField field) {
        return recordFields.get(field);
    }

    public Set<CreatedRecordField> getRecordFields() {
        return recordFields.keySet();
    }

    public void setRecordFieldValue(CreatedRecordField field, Object value) {
        recordFields.put(field, value);
    }

    public boolean hasRecordField(CreatedRecordField field) {
        return recordFields.containsKey(field);
    }
}
