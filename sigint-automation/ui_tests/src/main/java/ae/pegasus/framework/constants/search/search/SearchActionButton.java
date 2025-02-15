package ae.pegasus.framework.constants.search.search;

public enum SearchActionButton {

    VIEW_DETAILS("View Details..."),
    VIEW_REPORT("View"),
    VIEW_IN_FINDER("View in Finder"),
    VIEW_IN_PROFILER("View in Profiler"),
    VIEW_HISTORY("View History"),
    EDIT("Edit"),
    MERGE("Merge..."),
    DELETE("Delete..."),
    ADD_TO_REPORT("Add to Report..."),
    ADD_TO_TARGET("Add to Target..."),
    ASSIGN("Assign..."),
    EXPORT_RECORD("Export Record");

    private final String actionName;

    SearchActionButton(String actionName) {
        this.actionName = actionName;
    }

    public String getActionName() {
        return actionName;
    }
}
