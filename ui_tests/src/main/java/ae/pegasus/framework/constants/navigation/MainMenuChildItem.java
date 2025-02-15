package ae.pegasus.framework.constants.navigation;

import java.util.ArrayList;
import java.util.List;

public enum MainMenuChildItem {

    //SEARCH
    SEARCH ("Search", MainMenuRootItem.SEARCH),
    GEO_SEARCH("Geo Search", MainMenuRootItem.SEARCH),
    ADVANCED_SEARCH("Advanced Search", MainMenuRootItem.SEARCH),
    SAVED_SEARCHES("Saved Searches", MainMenuRootItem.SEARCH),

    //PROFILER
    CREATE_TARGET("Create Target", MainMenuRootItem.PROFILER),

    //RECORD ASSESSMENT
    MY_RECORDS("My Records", MainMenuRootItem.RECORD_ASSESSMENT),
    OrgUnit_RECORDS("OrgUnit Records", MainMenuRootItem.RECORD_ASSESSMENT),
    WORKLOADS("Workloads", MainMenuRootItem.RECORD_ASSESSMENT),

    //DASHBOARDS
    PROFILE_DASHBOARD("Profile Dashboard", MainMenuRootItem.DASHBOARDS),
    USER_PERFORMANCE_DASHBOARD("User Performance Dashboard", MainMenuRootItem.DASHBOARDS),
    IM_DASHBOARD("IM Dashboard", MainMenuRootItem.DASHBOARDS),
    CUSTOM_DASHBOARDS("Custom Dashboards", MainMenuRootItem.DASHBOARDS),

    //OSINT ANALYTICS
    SENTIMENT_ANALYSIS("Sentiment Analysis Beta", MainMenuRootItem.OSINT_ANALYTICS),
    TWEET_LIFE("Tweet Life", MainMenuRootItem.OSINT_ANALYTICS),
    FIRST_INSTANCE_IDENTIFICATION("First Instance Identification", MainMenuRootItem.OSINT_ANALYTICS),
    TWITTER_DISCOVERY("Twitter Discovery", MainMenuRootItem.OSINT_ANALYTICS),
    MONITORING_DECK("Monitoring Deck", MainMenuRootItem.OSINT_ANALYTICS),

    //ADMIN_SUITE
    ORG_UNIT_AND_USER("Org Units And Users", MainMenuRootItem.ADMIN_SUITE),

    PERMISSION_MANAGEMENT("Permission Management", MainMenuRootItem.ADMIN_SUITE),
    TITLE_MANAGEMENT("Title Management", PERMISSION_MANAGEMENT),
    RESPONSIBILITIES("Responsibilities", PERMISSION_MANAGEMENT),

    USER_PRIVILEGE_AUDITING("User Privilege Auditing", MainMenuRootItem.ADMIN_SUITE),

    DATA_INGESTION("Data Ingestion", MainMenuRootItem.ADMIN_SUITE),
    UPLOAD_DATA("Upload Data", DATA_INGESTION),
    SEARCH_DATA_FILES("Search Data Files", DATA_INGESTION),
    DATA_SUB_SOURCE_MANAGEMENT("Data Sub-Source Management", DATA_INGESTION),

    IDENTIFIER_MANAGEMENT("Identifier Management", MainMenuRootItem.ADMIN_SUITE),
    DESIGNATIONS_LIST("Designations List", IDENTIFIER_MANAGEMENT),
    DESIGNATIONS_MAPPING("Designations Mapping", IDENTIFIER_MANAGEMENT),
    WHITELIST("Whitelist", IDENTIFIER_MANAGEMENT),

    TAG_MANAGEMENT("Tag Management", MainMenuRootItem.ADMIN_SUITE);

    private final MainMenuRootItem rootMenuItem;
    private final String itemName;
    private final List<MainMenuChildItem> intermediateMenuItems = new ArrayList<>();
    private boolean hasChildren = false;
    private int childrenNumber = 0;

    MainMenuChildItem(String itemName, MainMenuChildItem parent) {
        this(itemName, parent.getRootMenuItem());
        intermediateMenuItems.addAll(parent.getIntermediateMenuItems());
        intermediateMenuItems.add(parent);
        parent.addChildren();
    }

    MainMenuChildItem(String itemName, MainMenuRootItem parent) {
        this.rootMenuItem = parent;
        this.itemName = itemName;
        parent.addChildren();
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

    public MainMenuRootItem getRootMenuItem() {
        return rootMenuItem;
    }

    public List<MainMenuChildItem> getIntermediateMenuItems() {
        return intermediateMenuItems;
    }

    public String getItemName() {
        return itemName;
    }
}
