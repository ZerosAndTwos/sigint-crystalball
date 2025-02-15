package ae.pegasus.framework.controllers.records;

import ae.pegasus.framework.app_context.RunContext;
import ae.pegasus.framework.pages.Pages;
import ae.pegasus.framework.controllers.PageController;
import ae.pegasus.framework.controllers.reports.form_page.ReportFormFactoryController;
import ae.pegasus.framework.errors.NullReturnException;
import org.apache.log4j.Logger;
import ae.pegasus.framework.pages.records.RecordDetailsDialog;

public class RecordsDetailsController extends PageController<RecordDetailsDialog> {

    private Logger log = Logger.getLogger(RecordsDetailsController.class);


    public RecordsDetailsController() {
        super(Pages.recordDetailsDialog(), RecordDetailsDialog.class);
    }


    public void attachRecordToReport(String reportSubject) {
        getPage().clickAttachButton();
        try {
            getPage().selectReportInAttatchmentsList(reportSubject);
            RunContext.get().put("controller", new ReportFormFactoryController());
        } catch (NullReturnException e) {
            log.error(e.getMessage());
            throw new AssertionError("Cannot find report with subject "+ reportSubject);
        }
    }



}
