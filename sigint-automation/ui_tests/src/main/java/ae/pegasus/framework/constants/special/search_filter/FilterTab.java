package ae.pegasus.framework.constants.special.search_filter;

public enum FilterTab {

    GENERAL("General"),
    FILES("Files"),
    RECORD_ASSESSMENT("Record Assessment");

    private final String tabName;

    FilterTab(String tabName) {
        this.tabName = tabName;
    }

    public String getTabName() {
        return tabName;
    }
}
