package ae.pegasus.framework.controllers.reports.all_page;

import ae.pegasus.framework.blocks.context.tables.Row;
import ae.pegasus.framework.blocks.context.tables.reports.ReportAllRow;
import ae.pegasus.framework.controllers.reports.ReportsTableController;
import ae.pegasus.framework.model.Report;
import ae.pegasus.framework.model.ReportStatus;
import ae.pegasus.framework.pages.SigintPage;

public class ReportsTableAllController extends ReportsTableController {

    public ReportsTableAllController(SigintPage page) {
        super(page);
    }

    @Override
    public Report initFromRow(Row reportRow) {
        Report report = super.initFromRow(reportRow);
        ReportAllRow row = (ReportAllRow)reportRow;
        report.setStatus(ReportStatus.valueOf(row.getStatus()));
        report.setOwnerName(row.getOwner());
        return report;
    }


}
