package ae.pegasus.framework.constants.special.saved_search;

public enum SavedSearchButton {
    SAVE_SEARCH("Save Search"),
    EDIT_QUERY("Edit Query"),
    CANCEL("Cancel");

    private final String buttonName;

    SavedSearchButton(String buttonName) {
        this.buttonName = buttonName;
    }

    public String getButtonName() {
        return buttonName;
    }
}
