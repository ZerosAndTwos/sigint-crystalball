package ae.pegasus.framework.constants.special.results_details;

public enum SearchResultsDetailsAction {
    ADD_TO_TARGET("Add to Target..."),
    ADD_TO_SANDBOX("Add to Sandbox"),
    ADD_TO_NEW_COLLECTION(ADD_TO_SANDBOX, "New Collection"),
    ADD_TO_EXISTING_COLLECTION(ADD_TO_SANDBOX, "Existing Collection"),
    ADD_TO_REPORT("Add to Report..."),
    ASSIGN("Assign..."),
    Export_Record("Export Record"),
    TRANSLATE_TO("(Beta) Translate to..."),
    TRANSLATE_TO_ENGLISH(TRANSLATE_TO, "English");

    private final SearchResultsDetailsAction parentAction;
    private final String actionName;

    SearchResultsDetailsAction(String actionName) {
        this(null, actionName);
    }
    SearchResultsDetailsAction(SearchResultsDetailsAction parentAction, String actionName) {
        this.parentAction = parentAction;
        this.actionName = actionName;
    }

    public String getActionName() {
        return actionName;
    }

    public SearchResultsDetailsAction getParentAction() {
        return parentAction;
    }

}
