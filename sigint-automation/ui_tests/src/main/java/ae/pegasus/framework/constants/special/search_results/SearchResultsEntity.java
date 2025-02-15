package ae.pegasus.framework.constants.special.search_results;

public enum SearchResultsEntity {

    PROFILER("Profiler"),
    DOCUMENTS("Documents"),
    SIGINT("SIGINT"),
    EID("EID"),
    GOVINT("GOVINT"),
    OSINT("OSINT"),
    CIO("CIO");

    private final String displayName;

    SearchResultsEntity(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
