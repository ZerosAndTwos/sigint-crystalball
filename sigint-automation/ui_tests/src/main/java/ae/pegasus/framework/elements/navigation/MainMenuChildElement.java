package ae.pegasus.framework.elements.navigation;

import com.codeborne.selenide.SelenideElement;
import ae.pegasus.framework.constants.navigation.MainMenuChildItem;
import ae.pegasus.framework.elements.ExpandableElement;

public class MainMenuChildElement extends ExpandableElement {
    //TODO For future use
    private final MainMenuChildItem item;
    public static final String displayedNameXPath = ".//div[contains(@class, 'pg-sidebar__item__name')]";

    private final String elementHeaderXPath = "./a";
    private final String childrenXPath = ".//div[@class='pg-sidebar__sub-items']/pg-sidebar-item";

    public MainMenuChildElement(SelenideElement wrappedElement, MainMenuChildItem item) {
        super(wrappedElement);
        this.item = item;
        determineExpandState();
    }

    private void determineExpandState() {
        setExpanded($x(elementHeaderXPath).getAttribute("class").contains("pg-sidebar__item--collapsed"));
    }

    @Override
    public void click() {
        $x(elementHeaderXPath).click();
        //FIXME Think how to wait till click complete
        try {
            Thread.sleep(600);
        } catch (InterruptedException e) {
        }
    }

    @Override
    public void expand() {
        if (item.hasChildren()) {
            determineExpandState();
            super.expand();
        }
    }

    @Override
    public void collapse() {
        if (item.hasChildren()) {
            determineExpandState();
            super.collapse();
        }
    }

    public MainMenuChildElement getChild(MainMenuChildItem item){
        if (!this.item.hasChildren()) {
            throw new IllegalStateException("It is not expected that child element '" + this.item.getItemName() + "' has children");
        }
        expand();
        for (SelenideElement element : $$x(childrenXPath)) {
            if(element.$x(displayedNameXPath).getText().trim().equalsIgnoreCase(item.getItemName())) {
                return new MainMenuChildElement(element, item);
            }
        }
        throw new IllegalArgumentException("Sub-child element '" + item.getItemName() + "' was not found");
    }
}
