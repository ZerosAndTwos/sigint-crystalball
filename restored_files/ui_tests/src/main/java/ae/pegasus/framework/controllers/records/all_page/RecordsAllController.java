package ae.pegasus.framework.controllers.records.all_page;

import ae.pegasus.framework.blocks.context.factories.RecordsControllerFactory;
import ae.pegasus.framework.controllers.records.RecordsToolbarController;
import ae.pegasus.framework.pages.Pages;
import ae.pegasus.framework.pages.records.RecordsAllPage;

public  class RecordsAllController implements RecordsControllerFactory {

    private static RecordsAllPage page = Pages.recordsAllPage();

    @Override
    public RecordsAllPage getPage() {
        return page;
    }

    @Override
    public RecordsTableAllController getTableController() {
        return new RecordsTableAllController(page);
    }

    @Override
    public RecordsToolbarController getToolbarController() {
        return new RecordsToolbarController(page);
    }

    @Override
    public RecordsAllSearchController getSearchController() {
        return new RecordsAllSearchController(page);
    }
}
