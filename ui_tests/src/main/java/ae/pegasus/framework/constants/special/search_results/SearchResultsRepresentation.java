package ae.pegasus.framework.constants.special.search_results;

public enum SearchResultsRepresentation {
    EVENT("Event"),
    ENTITY("Entity");

    private final String displayName;

    SearchResultsRepresentation(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static SearchResultsRepresentation getEnumByName(String representationName) {
        for (SearchResultsRepresentation item : SearchResultsRepresentation.values()) {
            if (item.getDisplayName().equalsIgnoreCase(representationName)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Search Results Representation with name '" + representationName + "' was not found");
    }
}
