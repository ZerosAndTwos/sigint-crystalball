package ae.pegasus.framework.blocks.navigation;

import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;
import ae.pegasus.framework.controllers.PageControllerFactory;

public class SubMenu  {

    private MainMenu menu;
    private String subMenuName;
    private PageControllerFactory controller;

    public SubMenu(MainMenu menu, String subMenuName, PageControllerFactory controller) {
        this.controller = controller;
        this.menu = menu;
        this.subMenuName = subMenuName;
    }

    public SelenideElement getSubMenu() {
        return menu.getMenu().$((new Selectors.ByText(subMenuName)));
    }

    public PageControllerFactory click() {
        getSubMenu().click();
        return controller;

    }
}
