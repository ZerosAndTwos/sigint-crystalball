package ae.pegasus.framework.constants.special.IM;

public enum IMAction {
    CANCEL("Cancel"),
    DISCARD_CHANGES("Discard changes"),
    TAKE_OWNERSHIP("Take Ownership"),
    UNASSIGN("Unassign"),
    ASSIGN("Assign"),
    REJECT("Reject"),
    RETURN_TO_AUTHOR("Return to Author"),
    APPROVE("Approve"),
    SUBMIT_FOR_REVIEW("Submit for Review"),
    SAVE_AS_DRAFT("Save as Draft"),
    SEND("Send"),
    RECALL("Recall"),
    EDIT("Edit"),
    VIEW_HISTORY("View History", true),
    EXPORT("Export", true),
    FULL_REPORT(EXPORT, "Full report", true),
    REPORT_WITHOUT_STAFF_ID(EXPORT, "Without Staff Id", true),
    REPORT_WITHOUT_SOURCES(EXPORT, "Without Sources", true),
    ADD_TO_SANDBOX("Add to Sandbox", true),
    DELETE_REPORT("Delete Document", true),
    DELETE_RFI("Delete RFI", true);

    private final IMAction parentAction;
    private final String actionName;
    private final boolean isInMoreAction;

    IMAction(String actionName) {
        this(null, actionName, false);
    }
    IMAction(String actionName, boolean isInMoreAction) {
        this(null, actionName, isInMoreAction);
    }
    IMAction(IMAction parentAction, String actionName, boolean isInMoreAction) {
        this.parentAction = parentAction;
        this.actionName = actionName;
        this.isInMoreAction = isInMoreAction;
    }

    public String getActionName() {
        return actionName;
    }

    public IMAction getParentAction() {
        return parentAction;
    }

    public boolean isInMoreAction() {
        return isInMoreAction;
    }

}
