package ae.pegasus.framework.controllers.records.all_page;

import ae.pegasus.framework.pages.SigintPage;
import ae.pegasus.framework.pages.records.RecordsAllPage;
import ae.pegasus.framework.controllers.PageController;

public class RecordsAllSearchController extends PageController<RecordsAllPage> {

    public RecordsAllSearchController(SigintPage page) {
        super(page, RecordsAllPage.class);
    }
}
