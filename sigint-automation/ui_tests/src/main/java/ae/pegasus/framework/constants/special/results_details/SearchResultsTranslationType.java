package ae.pegasus.framework.constants.special.results_details;

public enum SearchResultsTranslationType {
    UNOFFICIAL_TRANSLATION("Unofficial Translation");

    private final String translationType;

    SearchResultsTranslationType (String translationType) {
        this.translationType = translationType;
    }

    public String getTranslationType() {
        return translationType;
    }
}
