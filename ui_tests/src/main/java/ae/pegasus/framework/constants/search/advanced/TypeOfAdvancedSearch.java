package ae.pegasus.framework.constants.search.advanced;

public enum TypeOfAdvancedSearch {
    KEYWORDS("Keyword(s)"),
    FUZZY("Fuzzy"),
    PROXIMITY("Proximity"),
    BOOST_KEYWORD("Boost Keyword"),
    FIELD_SEARCH("Field Search");

    private final String displayName;

    TypeOfAdvancedSearch(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
