package ae.pegasus.framework.controllers.records.processed_page;

import ae.pegasus.framework.pages.SigintPage;
import ae.pegasus.framework.controllers.PageController;
import ae.pegasus.framework.pages.records.RecordsProcessedPage;

public class RecordsProcessedSearchController extends PageController<RecordsProcessedPage> {
    public RecordsProcessedSearchController(SigintPage page) {
        super(page, RecordsProcessedPage.class);
    }
}
