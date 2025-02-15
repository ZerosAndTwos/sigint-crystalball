package ae.pegasus.framework.blocks.navigation;

import ae.pegasus.framework.controllers.PageControllerFactory;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.page;


public abstract class MainMenu {

    private static Sidebar sidebar = Selenide.page(Sidebar.class);
    private String menuName;
    private Logger log = Logger.getLogger(MainMenu.class);
    private Map<String, SubMenu> submenues = new HashMap<>();


    public MainMenu(String menuName) {
        this.menuName = menuName;

    }

    public SelenideElement getMenu() {
        return sidebar.getMainMenuByName(menuName);
    }

    public void click() {
        getMenu().click();
    }

    protected abstract PageControllerFactory getController(String pageName);

    public Boolean isExpanded() {
        return getMenu().$(".pg-sidebar__item").has(Condition.cssClass("pg-sidebar__item--collapsed"));
    }

    public SubMenu getSubMenuByName(String name) {
        if (!submenues.containsKey(name)) {
            submenues.put(name, new SubMenu(this, name, getController(name)));
        }
        return submenues.get(name);
    }
}
