package ae.pegasus.framework.constants.special.modal_dialog;

public enum ModalDialogButton {
    YES("Yes", ".//span[@id='slot']"),
    NO("No", ".//span[@id='slot']"),
    DELETE("Delete"),
    CANCEL("Cancel"),
    ASSIGN("Assign"),
    OK("OK");

    private final String buttonName;
    private final String xPathToButtonName;

    ModalDialogButton(String buttonName) {
        this(buttonName, "");
    }

    ModalDialogButton(String buttonName, String xPathToButtonName) {
        this.buttonName = buttonName;
        this.xPathToButtonName = xPathToButtonName;
    }


    public String getButtonName() {
        return buttonName;
    }

    public String getXPathToButtonName() {
        return xPathToButtonName;
    }

}
