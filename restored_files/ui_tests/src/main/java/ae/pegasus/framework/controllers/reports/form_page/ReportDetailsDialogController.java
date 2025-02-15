package ae.pegasus.framework.controllers.reports.form_page;

import ae.pegasus.framework.controllers.PageController;
import ae.pegasus.framework.pages.Pages;
import ae.pegasus.framework.pages.reports.ReportDetailsDialog;
import ae.pegasus.framework.pages.reports.ReportRecordRow;

import java.util.List;

public class ReportDetailsDialogController extends PageController<ReportDetailsDialog> {
    public ReportDetailsDialogController() {
        super(Pages.reportDetailsDialog(), ReportDetailsDialog.class);
    }

    public Boolean isRecordAttached(String text) {
        List<ReportRecordRow> records = getPage().getRecords();
        for (ReportRecordRow row: records) {
            if (row.getDetails().equalsIgnoreCase(text)) {
                return true;
            }
        }
        return false;
    }
}
