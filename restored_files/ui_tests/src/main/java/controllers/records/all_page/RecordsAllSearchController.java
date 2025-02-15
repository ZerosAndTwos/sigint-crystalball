package controllers.records.all_page;

import controllers.PageController;
import pages.SigintPage;
import pages.records.RecordsAllPage;

public class RecordsAllSearchController extends PageController<RecordsAllPage> {

    public RecordsAllSearchController(SigintPage page) {
        super(page, RecordsAllPage.class);
    }
}
