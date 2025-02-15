package ae.pegasus.framework.constants.special.search_filter;

public enum FilterMatchType {
    ALL("All"),
    HITS_ONLY("Hits Only"),
    MENTIONS_ONLY("Mentions Only");

    private final String matchType;

    FilterMatchType(String matchType) {
        this.matchType = matchType;
    }

    public String getMatchType() {
        return matchType;
    }
}
