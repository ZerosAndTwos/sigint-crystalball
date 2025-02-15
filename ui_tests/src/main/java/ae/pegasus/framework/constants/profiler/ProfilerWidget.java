package ae.pegasus.framework.constants.profiler;

import static ae.pegasus.framework.constants.profiler.ProfilerTab.*;

public enum ProfilerWidget {
    TARGET_SUMMARY("Target Summary", "//profile-summary-main-widget", SUMMARY, "Target - Summary - Main"),
    LAST_SEEN("Last Seen", "//profile-summary-map-widget", SUMMARY, "Target - Summary - Last Seen"),
    IDENTIFIERS("Identifiers", "//profile-summary-identifiers-widget", SUMMARY, "Target - Summary - Identifiers"),
    RFIS("RFIs", "//profile-summary-rfis-widget", SUMMARY, "Target - Summary - RFIs"),


    IMAGE("Images", "//profile-biographic-images-widget",PROFILE_DETAILS , "Target - Profile Details - Images"),
    VOICE_ID("Voice ID", "//profile-biographic-voice-id-widget", PROFILE_DETAILS, "Target - Profile Details - Voice ID"),
    THREAT_SCORE("Threat Score", "//profile-biographic-threat-matrix-widget", PROFILE_DETAILS, "Target - Profile Details - Threat Score"),
    KEY_ATTRIBUTES("Key Attributes", "//profile-biographic-key-attributes-widget", PROFILE_DETAILS, "Target - Profile Details - Key Attributes"),
    EID_INFORMATION("EID Information", "//profile-biographic-other-attributes-widget", PROFILE_DETAILS, "Target - Summary - RFIs"),



    COMMUNICATION_IDENTIFIERS("Identifiers", "//profile-communication-identifiers-widget",COMMUNICATION , "Target - Communication - Identifiers"),
    EVENT_TYPES("Event Types", "//profile-communication-event-types-widget", COMMUNICATION, "Target - Communication - Event Types"),
    ACTIVITY_PATTERN("Activity Pattern", "//profile-communication-pattern-widget", COMMUNICATION, "Target - Communication - Activity Pattern"),
    ACTIVITY_STREAM("Activity Stream", "//profile-communication-events-widget", COMMUNICATION, "Target - Communication - Activity Stream"),
    MOST_FREQUENT_CONTACTS("Most Frequent Contacts", "//profile-communication-top-contacts-widget", COMMUNICATION, "Target - Communication - Most Frequent Contacts"),
    MAP("Map", "//profile-communication-map-widget", COMMUNICATION, "Target - Communication - Map"),

    TRAVEL_ACTIVITY_STREAM("Activity Stream", "//profile-movement-events-widget", TRAVEL_AND_MOVEMENT, "Travel & Movement - Activity Stream"),
    TRAVEL_MAP("Map", "//profile-movement-map-widget", TRAVEL_AND_MOVEMENT, "Travel & Movement - Map"),

    THREAT_SCORE_PREDICTION("Threat Score Prediction", "//profile-threat-score-widget", INSIGHTS, "Insights Beta - Threat Score Prediction"),
    SUGGESTED_IDENTIFIERS("Suggested Identifiers", "//profile-suggested-identifiers-widget", INSIGHTS, "Insights Beta - Suggested Identifiers"),
    SIMILAR_TARGETS("Similar Targets", "//profile-similar-targets-widget", INSIGHTS, "Insights Beta - Similar Targets"),




    OPEN_DATA_ACCOUNTS("Accounts", "//od-accounts", OPEN_DATA, "Open Data - Accounts"),
    OPEN_DATA_ACTIVITY_STREAM("Activity Stream", "//od-activity-stream", OPEN_DATA, "Open Data - Activity Stream");





    private final String widgetName;
    private final String baseXPath;
    private final ProfilerTab parentTab;
    private final String reportSectionName;


    ProfilerWidget(String widgetName, String baseXPath, ProfilerTab parentTab, String reportSectionName) {
        this.widgetName = widgetName;
        this.baseXPath = baseXPath;
        this.parentTab = parentTab;
        this.reportSectionName = reportSectionName;
    }

    public String getWidgetName() {
        return widgetName;
    }

    public String getBaseXPath() {
        return baseXPath;
    }

    public ProfilerTab getParentTab() {
        return parentTab;
    }

    public String getReportSectionName() {
        return reportSectionName;
    }
}
