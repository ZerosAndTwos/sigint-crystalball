package ae.pegasus.framework.blocks.navigation.menus;

import ae.pegasus.framework.controllers.PageControllerFactory;
import ae.pegasus.framework.blocks.navigation.MainMenu;
import ae.pegasus.framework.controllers.reports.all_page.ReportsAllController;
import ae.pegasus.framework.controllers.reports.draft_page.ReportsDraftController;
import ae.pegasus.framework.controllers.reports.ready_for_review.ReportsReadyController;

public class ReportsMenu extends MainMenu {

    public ReportsMenu(String menuName) {
        super(menuName);
    }

    @Override
    protected PageControllerFactory getController(String name) {
        if (name.equalsIgnoreCase("draft")) {
            return new ReportsDraftController();
        } else if (name.equalsIgnoreCase("all")) {
            return new ReportsAllController();
        } else  if (name.equalsIgnoreCase("ready")) {
            return new ReportsReadyController();
        }

        throw new AssertionError("There is no menu Reports -> "+name);
    }


}
