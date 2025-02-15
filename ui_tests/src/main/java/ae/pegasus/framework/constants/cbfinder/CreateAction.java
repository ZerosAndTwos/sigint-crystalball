package ae.pegasus.framework.constants.cbfinder;

public enum CreateAction {
    CREATE_FILE("File", "Create a File"),
    CREATE_CASE("Case", "Create a Case"),
    CREATE_PROFILE("Profile", "Create a Profile"),
    CREATE_RFI("RFI", "Create a RFI");

    private final String actionLabelInReport;

    private final String actionNameInSidePanel;

    CreateAction(String actionNameInSidePanel, String actionLabelInReport) {
        this.actionNameInSidePanel = actionNameInSidePanel;
        this.actionLabelInReport = actionLabelInReport;
    }

    public String getActionLabelInReport() {
        return actionLabelInReport;
    }

    public String getActionNameInSidePanel() {
        return actionNameInSidePanel;
    }
}
