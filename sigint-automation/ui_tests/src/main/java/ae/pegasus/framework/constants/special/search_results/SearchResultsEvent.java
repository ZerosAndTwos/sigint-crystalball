package ae.pegasus.framework.constants.special.search_results;

public enum SearchResultsEvent {
    SIGINT("SIGINT"),
    EID("EID"),
    GOVINT("GOVINT"),
    FININT("FININT"),
    TRAFFIC("Traffic"),
    OSINT("OSINT"),
    CIO("CIO");

    private final String displayName;

    SearchResultsEvent(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
