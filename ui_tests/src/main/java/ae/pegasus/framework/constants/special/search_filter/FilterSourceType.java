package ae.pegasus.framework.constants.special.search_filter;

public enum FilterSourceType {
    ALL("All"),
    PROFILER("Profiler"),
    DOCUMENTS("Documents"),
    SIGINT("SIGINT"),
    EID("EID"),
    GOVINT("GOVINT"),
    FININT("FININT"),
    TRAFFIC("Traffic"),
    OSINT("OSINT"),
    CIO("CIO");

    private final String sourceName;

    FilterSourceType(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getSourceName() {
        return sourceName;
    }
}
