package ae.pegasus.framework.elements.controls.dropdown;

import com.codeborne.selenide.SelenideElement;
import ae.pegasus.framework.elements.ExpandableElement;

public abstract class CommonDropDown extends ExpandableElement {

    public CommonDropDown(SelenideElement wrappedElement) {
        super(wrappedElement);
    }

    protected String getDropDownItemText(SelenideElement item) {
        return item.$x(".//text-highlight-decorator/span").getText().trim();
    }
}
