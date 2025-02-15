package ae.pegasus.framework.constants.profiler;

import java.util.ArrayList;
import java.util.List;

public enum ProfilerTab {

    SUMMARY("Summary"),
    PROFILE_DETAILS("Profile Details"),
    COMMUNICATION("Communication"),
    OPEN_DATA("Open Data"),
    TRAVEL_AND_MOVEMENT("Travel & Movement"),
    MENTIONS("Mentions"),
    NETWORK("Network"),
    ATTACHMENTS("Attachments"),
    AUDIT_TRAIL("Audit Trail"),
    INSIGHTS("Insights Beta");

    private final String tabName;

    ProfilerTab(String tabName) {
        this.tabName = tabName;
    }

    public String getTabName() {
        return tabName;
    }

    public static List<String> getTabsNames() {
        List<String> tabsNames = new ArrayList<>();
        for (ProfilerTab tab : values()) {
            tabsNames.add(tab.getTabName());
        }
        return tabsNames;
    }
}
