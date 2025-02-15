package ae.pegasus.framework.constants.special.search_filter;

public enum FilterRecordStatus {
    ALL("All"),
    UNPROCESSED("Unprocessed"),
    REPORTED("Reported"),
    REVIEWED("Reviewed");

    private final String recordStatus;

    FilterRecordStatus(String recordStatus) {
        this.recordStatus = recordStatus;
    }

    public String getRecordStatus() {
        return recordStatus;
    }
}
