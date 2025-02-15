package ae.pegasus.framework.constants.special.saved_search;

public enum SavedSearchField {
    SEARCHING_CRITERIA("Searching Criteria"),
    PROPERTIES("Properties"),
    SCHEDULED_SEARCH("Scheduled Search To acquire new OSINT data, please use Scheduled Search"),
    ASSIGNMENT("Assignment");

    private final String fieldName;

    SavedSearchField(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }
}
