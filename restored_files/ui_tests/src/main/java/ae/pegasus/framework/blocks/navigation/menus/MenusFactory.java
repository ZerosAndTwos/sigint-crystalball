package ae.pegasus.framework.blocks.navigation.menus;

import ae.pegasus.framework.blocks.navigation.MainMenu;
import ae.pegasus.framework.blocks.navigation.Sidebar;

import static com.codeborne.selenide.Selenide.page;

public class MenusFactory {

    private static Sidebar sidebar = page(Sidebar.class);

    public static MainMenu getMenu(String name) {
        if (name.equalsIgnoreCase("records")) {
            return new RecordsMenu(name);
        } else if (name.equalsIgnoreCase("reports")) {
            return new ReportsMenu(name);
        }
        throw new AssertionError(String.format("Menu with name %s is not found!", name));

    }
}
