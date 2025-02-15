package ae.pegasus.framework.elements.navigation;

import ae.pegasus.framework.assertion.Asserter;
import com.codeborne.selenide.SelenideElement;
import ae.pegasus.framework.constants.navigation.MainMenuChildItem;
import ae.pegasus.framework.constants.navigation.MainMenuRootItem;
import ae.pegasus.framework.elements.ExpandableElement;

public class MainMenuRootElement extends ExpandableElement {
    private final MainMenuRootItem item;
    public static final String displayedNameXPath = ".//div[contains(@class, 'pg-sidebar__item__name')]";

    private final String elementHeaderXPath = "./pg-sidebar-item/a";

    private final String childrenXPath = ".//div[@class='pg-sidebar__sub-items']/pg-sidebar-item";

    public MainMenuRootElement(SelenideElement wrappedElement, MainMenuRootItem item) {
        super(wrappedElement);
        this.item = item;
        determineExpandState();
    }

    private void determineExpandState() {
        setExpanded($x(elementHeaderXPath).getAttribute("class").contains("pg-sidebar__item--collapsed"));
    }

    @Override
    public void click() {
        $x(elementHeaderXPath + "/i").hover().click();
        if (item.hasChildren()) {
            //FIXME Think how to wait till click complete
            try {
                Thread.sleep(1800);
            } catch (InterruptedException e) {
            }
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

    private final int MAX_TRIES_COUNT = 3;

    public MainMenuChildElement getChild(MainMenuChildItem item) {
        if (!this.item.hasChildren()) {
            throw new IllegalStateException("It is not expected that root element '" + this.item.getItemName() + "' has children");
        }

        //FIXME This is patch for problem with navigation (some time root element does not expand)
        int tryCount = 0;
        while (tryCount < MAX_TRIES_COUNT) {
            expand();
            for (SelenideElement element : $$x(childrenXPath)) {
                if (element.$x(MainMenuChildElement.displayedNameXPath).getText().trim().equalsIgnoreCase(item.getItemName())) {
                    return new MainMenuChildElement(element, item);
                }
            }
            tryCount++;
            Asserter.getAsserter().softAssertTrue(false, "", "Navigation patch was applied");
        }

        throw new IllegalArgumentException("Root's child element '" + item.getItemName() + "' was not found");
    }
}
