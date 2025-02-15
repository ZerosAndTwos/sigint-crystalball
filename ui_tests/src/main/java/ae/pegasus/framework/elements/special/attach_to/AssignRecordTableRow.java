package ae.pegasus.framework.elements.special.attach_to;

import com.codeborne.selenide.SelenideElement;
import ae.pegasus.framework.elements.BasicElement;

public class AssignRecordTableRow extends BasicElement {

    private final int CHECKBOX_COLON_NUMBER = 1;
    private final int NAME_COLON_NUMBER = 2;
    private final int TO_BE_ASSIGNED_COLON_NUMBER = 5;

    public AssignRecordTableRow(SelenideElement wrappedElement) {
        super(wrappedElement);
    }

    private SelenideElement getColon(int colonNumber) {
        return $x("./div[" + colonNumber + "]");
    }

    private SelenideElement getCheckbox() {
        return getColon(CHECKBOX_COLON_NUMBER).$x(".//pg-checkbox");
    }

    public String getUserName() {
        return getColon(NAME_COLON_NUMBER).getText();
    }

    public String getToBeAssigned() {
        return getColon(TO_BE_ASSIGNED_COLON_NUMBER).getText();
    }

    @Override
    public boolean isSelected() {
        return !getToBeAssigned().isEmpty();
    }

    @Override
    public SelenideElement setSelected(boolean selected) {
        if (selected != isSelected()) {
            getCheckbox().click();
        }
        return this;
    }
}
