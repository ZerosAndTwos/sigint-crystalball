package ae.pegasus.framework.constants.special.results_details;

public enum SearchResultsDetailsField {
    DURATION("Duration"),
    LANGUAGE("Language"),
    TO_PHONE_NUMBER("To Phone Number"),
    FROM_PHONE_NUMBER("From Phone Number"),
    IMSI("From IMSI"),
    TMSI("From TMSI"),
    FROM_COUNTRY("From Country"),
    TO_COUNTRY("To Country"),
    TO_EMAIL("Email To"),
    FROM_EMAIL("Email From"),
    SOURCE("Source"),
    SUBSOURCE("Subsource"),
    TYPE("Type"),
    SUBTYPE("Subtype"),
    EVENT_TIME("Event Time"),
    END_TIME("End Time"),
    PRIORITY("Priority"),
    STATUS("Status"),
    OWNER("Owner"),
    DESIGNATIONS("Designations");

    private final String fieldName;

    SearchResultsDetailsField(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }
}
