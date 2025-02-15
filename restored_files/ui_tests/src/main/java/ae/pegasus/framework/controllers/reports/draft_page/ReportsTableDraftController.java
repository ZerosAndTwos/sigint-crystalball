package ae.pegasus.framework.controllers.reports.draft_page;

import ae.pegasus.framework.blocks.context.tables.Row;
import ae.pegasus.framework.controllers.reports.ReportsTableController;
import ae.pegasus.framework.model.Report;
import ae.pegasus.framework.pages.SigintPage;

public class ReportsTableDraftController extends ReportsTableController {


    public ReportsTableDraftController(SigintPage page) {
        super(page);
    }

    @Override
    public Report initFromRow(Row reportRow) {
        return super.initFromRow(reportRow);
    }
}
