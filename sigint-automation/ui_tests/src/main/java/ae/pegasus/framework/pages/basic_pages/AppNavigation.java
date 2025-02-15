package ae.pegasus.framework.pages.basic_pages;

import ae.pegasus.framework.assertion.Asserter;
import ae.pegasus.framework.app_context.properties.G4Properties;
import com.codeborne.selenide.SelenideElement;
import ae.pegasus.framework.constants.navigation.MainMenuChildItem;
import ae.pegasus.framework.constants.navigation.MainMenuRootItem;
import ae.pegasus.framework.elements.navigation.MainMenuChildElement;
import ae.pegasus.framework.elements.navigation.MainMenuRootElement;
import ae.pegasus.framework.pages.basic_pages.api.BasePage;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Selenide.$$x;

public class AppNavigation extends BasePage {

    private static ThreadLocal<AppNavigation> navigationThreadLocal = ThreadLocal.withInitial(AppNavigation::new);

    public static AppNavigation getAppNavigation() {
        return navigationThreadLocal.get();
    }

    private Map<MainMenuRootItem, MainMenuRootElement> navigationElements = new HashMap<>();

    @Override
    public String getPageTitle() {
        return "Navigation";
    }

    @Override
    public boolean isPageDisplayed() {
        return !navigationElements.isEmpty();
    }

    private void initializeNavigation() {
        int emptyMainMenuElements = 0;
        //It is expected that number of displayed root menu items same as defined by enum
        for(SelenideElement element : $$x("//div[contains(@class, 'nav')]/div[@class='pg-sidebar__row']").shouldBe(sizeGreaterThanOrEqual(MainMenuRootItem.values().length))) {
            String itemName = "";
            if (!element.getText().trim().isEmpty()) {
                itemName = element.$x(MainMenuRootElement.displayedNameXPath).getText().trim();
            }

            if (itemName.isEmpty()) {
                emptyMainMenuElements++;
            } else {
                MainMenuRootItem mainMenuItem = MainMenuRootItem.getEnumByName(itemName);
                navigationElements.put(mainMenuItem, new MainMenuRootElement(element, mainMenuItem));
            }
        }

        if (!G4Properties.getRunProperties().isSuppressKnownIssues()) {
            Asserter.getAsserter().softAssertTrue((emptyMainMenuElements > 0),
                    "Elements with empty names were not found in main menu",
                    emptyMainMenuElements + " element(s) with empty name(s) was found in main menu");
        }
    }

    public void resetNavigation() {
        navigationElements.clear();
    }

    private MainMenuRootElement getRootNavigationElement(MainMenuRootItem destinationItem) {
        if (navigationElements.isEmpty()) {
            initializeNavigation();
        }

        MainMenuRootElement navItem = navigationElements.get(destinationItem);
        if (navItem != null) {
            return navItem;
        } else {
            throw new IllegalStateException("Main menu item '" + destinationItem.getItemName() + "' is not displayed");
        }
    }

    public void navigateTo(MainMenuRootItem destinationItem) {
        getRootNavigationElement(destinationItem).click();
    }

    public void navigateTo(MainMenuChildItem destinationItem) {
        MainMenuRootElement rootElement = getRootNavigationElement(destinationItem.getRootMenuItem());
        MainMenuChildElement childElement = null;
        if (destinationItem.getIntermediateMenuItems().isEmpty()) {
            childElement = rootElement.getChild(destinationItem);
        } else {
            for (MainMenuChildItem item : destinationItem.getIntermediateMenuItems()) {
                if (childElement == null) {
                    childElement = rootElement.getChild(item);
                } else {
                    childElement = childElement.getChild(item);
                }
            }
            childElement = childElement.getChild(destinationItem);
        }
        childElement.click();
    }
}
