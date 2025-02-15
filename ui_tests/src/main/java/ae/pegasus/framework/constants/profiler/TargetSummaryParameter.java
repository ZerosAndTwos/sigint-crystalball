package ae.pegasus.framework.constants.profiler;

public enum TargetSummaryParameter {
    PHOTO("Photo", 1),
    TARGET_NAME("Target Name", 2),
    DESCRIPTION("Description", 2),
    FILES("Files", 2),
    CATEGORY("Category", 3),
    THREAT_SCORE("Threat Score", 3),
    VOICE_ID("Voice ID", 3),
    TARGET_STATUS("Target Status", 3),
    CLASSIFICATION("Classification", 3),
    CRIMINAL_RECORD("Criminal Record", 3);

    private final String summaryParameterName;

    private final int summaryPanelColonNumber;

    TargetSummaryParameter(String summaryParameterName, int summaryPanelColonNumber) {
        this.summaryParameterName = summaryParameterName;
        this.summaryPanelColonNumber = summaryPanelColonNumber;
    }

    public String getSummaryParameterName() {
        return summaryParameterName;
    }

    public int getSummaryPanelColonNumber() {
        return summaryPanelColonNumber;
    }
}
