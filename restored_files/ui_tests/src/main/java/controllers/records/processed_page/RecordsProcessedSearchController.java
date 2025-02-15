package controllers.records.processed_page;

import controllers.PageController;
import pages.SigintPage;
import pages.records.RecordsProcessedPage;

public class RecordsProcessedSearchController extends PageController<RecordsProcessedPage> {
    public RecordsProcessedSearchController(SigintPage page) {
        super(page, RecordsProcessedPage.class);
    }
}
