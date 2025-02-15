package ae.pegasus.framework.controllers.reports.ready_for_review;

import ae.pegasus.framework.blocks.context.factories.ReportsControllerFactory;
import ae.pegasus.framework.controllers.reports.ReportsTableController;
import ae.pegasus.framework.pages.Pages;
import ae.pegasus.framework.pages.reports.ReportsReadyPage;
import ae.pegasus.framework.controllers.PageController;
import ae.pegasus.framework.controllers.reports.ReportsToolbarController;

public class ReportsReadyController implements ReportsControllerFactory {

    ReportsReadyPage page = Pages.reportsReadyPage();

    @Override
    public ReportsReadyPage getPage() {
        return page;
    }

    @Override
    public ReportsTableController getTableController() {
        return new ReportsTableController(page);
    }

    @Override
    public ReportsToolbarController getToolbarController() {
        return new ReportsToolbarController(page);
    }

    @Override
    public PageController getSearchController() {
        return null;
    }
}
