package ae.pegasus.framework.elements.controls.datetime_selector;

import com.codeborne.selenide.SelenideElement;
import ae.pegasus.framework.elements.ExpandableElement;

import static ae.pegasus.framework.constants.CommonXPaths.DROP_CONTENT_BASE_XPATH;

public abstract class CommonDateSelector extends ExpandableElement {
    private final String APPLY_DATE_BUTTON = "Apply";
    private final String CLEAR_DATE_BUTTON = "Clear";

    public CommonDateSelector(SelenideElement wrappedElement) {
        super(wrappedElement);
    }

    protected SelenideElement getDatePickerBase() {
        return $x(DROP_CONTENT_BASE_XPATH + "//div[contains(@class,'daterangepicker')]");
    }

    @Override
    public void expand() {
        if(!getDatePickerBase().isDisplayed()) {
            performExpand();
        }
    }

    @Override
    public void collapse() {
        if(getDatePickerBase().isDisplayed()) {
            performCollapse();
        }
    }

    protected final SelenideElement getDateManipulationButton(String buttonName) {
        return getDatePickerBase().$x(".//div[@class ='drp-buttons']//button[text()='" + buttonName + "']");
    }

    protected final void applyDate() {
        getDateManipulationButton(APPLY_DATE_BUTTON).click();
    }

    protected final void clearDate() {
        getDateManipulationButton(CLEAR_DATE_BUTTON).click();
    }
}
