package ae.pegasus.framework.controllers.records.processed_page;

import ae.pegasus.framework.blocks.context.factories.RecordsControllerFactory;
import ae.pegasus.framework.pages.Pages;
import ae.pegasus.framework.pages.records.RecordsProcessedPage;

public class RecordsProcessedController implements RecordsControllerFactory
         {

    private RecordsProcessedPage page = Pages.recordsProcessedPage();

             @Override
             public RecordsProcessedPage getPage() {
                 return page;
             }

             @Override
    public RecordsTableProcessedController getTableController() {
        return new RecordsTableProcessedController(page);
    }

    @Override
    public RecordsProcessedToolbarController getToolbarController() {
        return new RecordsProcessedToolbarController(page);
    }

    @Override
    public RecordsProcessedSearchController getSearchController() {
        return new RecordsProcessedSearchController(page);
    }
}
