package ae.pegasus.framework.constants.search.search;

public enum SearchAuthorizationField {
    FILE_CASE_RFI("File/Case/RFI"),
    JUSTIFICATION("Justification");

    private final String displayName;

    SearchAuthorizationField (String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
