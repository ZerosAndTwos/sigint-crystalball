package ae.pegasus.framework.constants.cbfinder;

public enum UpdateAction {
    EDIT("Edit"),
    DELETE("Delete...");

    private final String actionName;

    UpdateAction(String actionName) {
        this.actionName = actionName;
    }

    public String getActionName() {
        return actionName;
    }
}
