package ae.pegasus.framework.elements;

import com.codeborne.selenide.SelenideElement;

public abstract class ExpandableElement extends BasicElement {
    private boolean isExpanded = false;

    public ExpandableElement(SelenideElement wrappedElement) {
        super(wrappedElement);
    }

    public void expand() {
        if(!isExpanded) {
            performExpand();
            isExpanded = true;
        }
    }

    public void collapse() {
        if(isExpanded) {
            performCollapse();
            isExpanded = false;
        }
    }

    protected void performCollapse() {
        click();
    }

    protected void performExpand() {
        click();
    }

    protected void setExpanded (boolean expanded) {
        isExpanded = expanded;
    }
}
