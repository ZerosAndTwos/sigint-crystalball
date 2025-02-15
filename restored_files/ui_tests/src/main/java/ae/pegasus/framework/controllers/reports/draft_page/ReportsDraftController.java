package ae.pegasus.framework.controllers.reports.draft_page;

import ae.pegasus.framework.blocks.context.factories.ReportsControllerFactory;
import ae.pegasus.framework.controllers.reports.ReportsTableController;
import ae.pegasus.framework.pages.Pages;
import ae.pegasus.framework.controllers.PageController;
import ae.pegasus.framework.controllers.reports.ReportsToolbarController;
import ae.pegasus.framework.pages.reports.ReportsDraftPage;

public class ReportsDraftController implements ReportsControllerFactory {

    ReportsDraftPage page = Pages.reportsDraftPage();

    @Override
    public ReportsDraftPage getPage() {
        return page;
    }

    @Override
    public ReportsTableController getTableController() {
        return new ReportsTableDraftController(page);
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
