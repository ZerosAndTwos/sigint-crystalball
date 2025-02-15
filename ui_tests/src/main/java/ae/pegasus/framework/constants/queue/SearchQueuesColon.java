package ae.pegasus.framework.constants.queue;

public enum SearchQueuesColon {
    QUERY("Query"),
    TYPE("Type"),
    STATUS("Status"),
    CREATED_AT("Created At"),
    ACTIONS("Actions");

    private final String colonName;

    SearchQueuesColon(String colonName) {
        this.colonName = colonName;
    }

    public String getColonName() {
        return colonName;
    }
}
