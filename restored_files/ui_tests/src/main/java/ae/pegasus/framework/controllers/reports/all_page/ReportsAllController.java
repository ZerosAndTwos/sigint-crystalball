package ae.pegasus.framework.controllers.reports.all_page;

import ae.pegasus.framework.blocks.context.factories.ReportsControllerFactory;
import ae.pegasus.framework.pages.Pages;
import ae.pegasus.framework.controllers.PageController;
import ae.pegasus.framework.controllers.reports.ReportsTableController;
import ae.pegasus.framework.controllers.reports.ReportsToolbarController;
import ae.pegasus.framework.pages.reports.ReportsAllPage;

public class ReportsAllController implements ReportsControllerFactory {

    ReportsAllPage allPage = Pages.reportsAllPage();

    @Override
    public ReportsAllPage getPage() {
        return allPage;
    }

    @Override
    public ReportsTableController getTableController() {
        return new ReportsTableAllController(allPage);
    }

    @Override
    public ReportsToolbarController getToolbarController() {
        return new ReportsToolbarController(allPage);
    }

    @Override
    public PageController getSearchController() {
        return null;
    }
}
