package ae.pegasus.framework.constants.search.saved_searches;

public enum SavedSearchesActionButton {
    RUN("Run"),
    EDIT("Edit"),
    MOVE_TO("Move to..."),
    DELETE("Delete...");

    private final String actionName;

    SavedSearchesActionButton(String actionName) {
        this.actionName = actionName;
    }

    public String getActionName() {
        return actionName;
    }
}
