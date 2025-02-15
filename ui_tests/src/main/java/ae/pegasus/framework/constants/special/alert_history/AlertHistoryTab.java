package ae.pegasus.framework.constants.special.alert_history;

public enum AlertHistoryTab {

    TO_DO_LIST("To Do List"),
    ALERTS_HISTORY("Alerts History");

    private final String tabName;

    AlertHistoryTab(String tabName) {
        this.tabName = tabName;
    }

    public String getTabName() {
        return tabName;
    }
}
