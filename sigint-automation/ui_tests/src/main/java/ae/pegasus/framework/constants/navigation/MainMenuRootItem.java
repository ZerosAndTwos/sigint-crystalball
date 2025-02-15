package ae.pegasus.framework.constants.navigation;

public enum MainMenuRootItem {
    SEARCH("Search"),
    CB_FINDER("CB Finder"),
    PROFILER("Profiler"),
    FUNNEL("Funnel"),
    SANDBOX("Sandbox"),
    RECORD_ASSESSMENT("Record Assessment"),
    DASHBOARDS("Dashboards"),
    OSINT_ANALYTICS("OSINT Analytics"),
    ADMIN_SUITE("Admin Suite");

    private String itemName;
    private boolean hasChildren;
    private int childrenNumber = 0;

    MainMenuRootItem(String itemName) {
        this.itemName = itemName;
    }

    public String getItemName() {
        return itemName;
    }

    public boolean hasChildren() {
        return hasChildren;
    }

    //TODO For future use
    public int getChildrenNumber() {
        return childrenNumber;
    }

    void addChildren() {
        hasChildren = true;
        childrenNumber++;
    }

    public static MainMenuRootItem getEnumByName(String itemName) {
        for (MainMenuRootItem item : MainMenuRootItem.values()) {
            if (item.getItemName().equalsIgnoreCase(itemName)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Main menu item with name '" + itemName + "' was not found");
    }
}
