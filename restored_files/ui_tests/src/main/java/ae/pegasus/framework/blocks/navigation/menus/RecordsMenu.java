package ae.pegasus.framework.blocks.navigation.menus;

import ae.pegasus.framework.controllers.PageControllerFactory;
import ae.pegasus.framework.blocks.navigation.MainMenu;
import ae.pegasus.framework.controllers.records.all_page.RecordsAllController;
import ae.pegasus.framework.controllers.records.processed_page.RecordsProcessedController;

public class  RecordsMenu extends MainMenu {

    public RecordsMenu(String menuName) {
        super(menuName);
    }

    @Override
    protected PageControllerFactory getController(String name) {
        if (name.equalsIgnoreCase("search")) {
            return new RecordsAllController();
        } else if (name.equalsIgnoreCase("processed")) {
            return new RecordsProcessedController();
        }
        throw new AssertionError(String.format("Page %s is not found!", name));
    }

}
