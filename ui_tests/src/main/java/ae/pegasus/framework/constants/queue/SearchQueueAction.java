package ae.pegasus.framework.constants.queue;

public enum SearchQueueAction {
    STAR("Star"),
    RERUN("Rerun"),
    REMOVE("Remove");

    private final String actionName;

    SearchQueueAction(String actionName) {
        this.actionName = actionName;
    }

    public String getActionName() {
        return actionName;
    }

}
