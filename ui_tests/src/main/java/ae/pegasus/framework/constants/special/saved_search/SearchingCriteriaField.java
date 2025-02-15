package ae.pegasus.framework.constants.special.saved_search;

public enum SearchingCriteriaField {
    QUERY("Query:"),
    SOURCE_TYPE("Source Type:"),
    DATA_SOURCE("Data Source:"),
    OBJECT_TYPE("Object Type:"),
    RECORD_TYPE("Record Type:");

    private final String fieldName;

    SearchingCriteriaField(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }
}
