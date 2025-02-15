package ae.pegasus.framework.blocks.navigation;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class Sidebar {

    private Logger log = Logger.getLogger(Sidebar.class);

    public SelenideElement getSidebar() {
        return $(By.xpath(".//div[@class='pg-sidebar au-target pg-sidebar-expanded']"));
    }

    private ElementsCollection getMenusWebElements() {
        return getSidebar()
                .$$(By.xpath(".//div[contains(@class, 'pg-sidebar__sub-menu')]"));
    }

    public SelenideElement  getMainMenuByName(String menuName) {
        for (SelenideElement menu : getMenusWebElements()) {
            SelenideElement mainMenu = menu.$(".pg-sidebar__item");
            if (mainMenu.$("div.pg-sidebar__item__name").getText().toLowerCase().contentEquals(menuName.toLowerCase())) {
                return menu;
            }
        }
        log.error("Sidebar subMenu by name: " + menuName + " doesn't found");
        throw new AssertionError("Sidebar subMenu by name: " + menuName + " doesn't found");
    }

    public ElementsCollection getSubMenuItems(String subMenuName) {
        return getMainMenuByName(subMenuName)
                .$$(By.xpath(".//div[@class='pg-sidebar__sub-items']/a"));
    }

    public SelenideElement getSubMenuItemByName(String subMenuName, String itemName) {
        return getMainMenuByName(subMenuName)
                .$(new Selectors.ByText(itemName));
    }

    public SelenideElement getSubMenuItemByName(String itemName) {
        return getSidebar()
                .$(new Selectors.ByText(itemName));
    }
}
