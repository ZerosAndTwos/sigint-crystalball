package ae.pegasus.framework.constants.cbfinder;

public enum TargetAction {
    ADD_TO_REPORT("Add to Report...");

    private final String actionName;

    TargetAction(String actionName) {
        this.actionName = actionName;
    }

    public String getActionName() {
        return actionName;
    }
}
