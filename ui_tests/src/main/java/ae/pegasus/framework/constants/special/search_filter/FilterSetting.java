package ae.pegasus.framework.constants.special.search_filter;

import ae.pegasus.framework.constants.controls.ControlType;

import static ae.pegasus.framework.constants.controls.ControlType.*;
import static ae.pegasus.framework.constants.special.search_filter.FilterTab.*;

public enum FilterSetting {
    SOURCE_TYPE("Source Type", RADIO_BUTTON_GROUP, GENERAL),
    DATA_SOURCE("Data Source", DROPDOWN_WITH_SEARCH, GENERAL),
    OBJECT_TYPE("Object Type", RADIO_BUTTON_GROUP, GENERAL),
    RECORD_TYPE("Record Type", DROPDOWN_WITH_SEARCH, GENERAL),
    DATA_SUB_SOURCE("Data Sub-Source", DROPDOWN_WITH_SEARCH, GENERAL, true),
    INCLUDE_SPAM("Include Spam", CHECKBOX, GENERAL),
    EVENT_TIME("Event Time", DATE_TIME_RANGE_SELECTOR, GENERAL),
    HAS_EVENT_LOCATION("Has Event Locatio", CHECKBOX, GENERAL),
    PHONE_NUMBER("Phone Number", INPUT, GENERAL),
    FROM_PHONE_NUMBER("From Phone Number", INPUT, GENERAL),
    TO_PHONE_NUMBER("To Phone Number", INPUT, GENERAL),
    IMSI("IMSI", INPUT, GENERAL),
    FROM_IMSI("From IMSI", INPUT, GENERAL),
    TO_IMSI("To IMSI", INPUT, GENERAL),
    LANGUAGE("Language", DROPDOWN_WITH_SEARCH, GENERAL),
    FROM_COUNTRY("From Country", DROPDOWN_WITH_SEARCH, GENERAL),
    TO_COUNTRY("To Country", DROPDOWN_WITH_SEARCH, GENERAL),
    TARGET("Target",DROPDOWN_WITH_SEARCH, FILES, true),
    FILE("File", DROPDOWN_WITH_SEARCH, FILES, true),
    MATCH_TYPE("Match Type", RADIO_BUTTON_GROUP, FILES),
    INCLUDE_FILE_CHILDREN("Include File Children", CHECKBOX, FILES),
    RECORD_STATUS("Record Status", RADIO_BUTTON_GROUP, RECORD_ASSESSMENT),
    ONLY_UNASSIGNED_RECORDS("Only Unassigned Records", CHECKBOX, RECORD_ASSESSMENT),
    OWNER("Owner", DROPDOWN_WITH_SEARCH, RECORD_ASSESSMENT, true),
    TEAM("Team", DROPDOWN_WITH_SEARCH, RECORD_ASSESSMENT, true),
    PRIORITY("Priority", RADIO_BUTTON_GROUP, RECORD_ASSESSMENT),
    DESIGNATION("Designation", DROPDOWN_WITH_SEARCH, RECORD_ASSESSMENT, true),
    TAGS("Tags", DROPDOWN_WITH_SEARCH, RECORD_ASSESSMENT, true),
    STATUS("Status",DROPDOWN_WITH_SEARCH, GENERAL),
    FILECASE("File / Case",DROPDOWN_WITH_SEARCH, GENERAL);



    private final String settingName;
    private final ControlType controlType;
    private final FilterTab filterTab;
    private final boolean withLoading;


    FilterSetting(String settingName, ControlType controlType, FilterTab filterTab) {
        this(settingName, controlType, filterTab, false);
    }

    FilterSetting(String settingName, ControlType controlType, FilterTab filterTab, boolean withLoading) {
        this.settingName = settingName;
        this.controlType = controlType;
        this.filterTab = filterTab;
        this.withLoading = withLoading;
    }

    public String getSettingName() {
        return settingName;
    }

    public ControlType getControlType() {
        return controlType;
    }

    public FilterTab getFilterTab() {
        return filterTab;
    }

    public boolean isWithLoading() {
        return withLoading;
    }
}
