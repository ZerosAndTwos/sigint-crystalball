package ae.pegasus.framework.constants.queue;

import java.util.ArrayList;
import java.util.List;

public enum SearchQueueTab {

    SEARCH_QUEUE_MONITOR("Search Queue Monitor Beta"),
    FUNNEL_QUEUE("Funnel Queue"),
    VIDEO_FETCHING_QUEUE("Video Fetching Queue");

    private final String tabName;

    SearchQueueTab(String tabName) {
        this.tabName = tabName;
    }

    public String getTabName() {
        return tabName;
    }

    public static List<String> getTabsNames() {
        List<String> tabsNames = new ArrayList<>();
        for (SearchQueueTab tab : values()) {
            tabsNames.add(tab.getTabName());
        }
        return tabsNames;
    }
}
