package ae.pegasus.framework.controllers.reports.form_page;

import ae.pegasus.framework.controllers.reports.ReportsFormController;
import ae.pegasus.framework.model.Report;
import ae.pegasus.framework.pages.Pages;
import ae.pegasus.framework.pages.reports.ReportsCreateManualPage;

public class ReportAddManualController extends ReportsFormController<ReportsCreateManualPage> {


    public ReportAddManualController() {
        super(Pages.reportsCreateManualPage(), ReportsCreateManualPage.class);
    }

    public Report fillForm(Report report) {
        getPage()
                .selectRecordType(report.getRecordType())
                .selectSourceType(report.getSourceType());
        super.fillForm(report);
        return report;
    }
}
