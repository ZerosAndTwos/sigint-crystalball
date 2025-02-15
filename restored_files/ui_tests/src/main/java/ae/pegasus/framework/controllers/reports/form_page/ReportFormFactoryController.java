package ae.pegasus.framework.controllers.reports.form_page;

public class ReportFormFactoryController {
    private ReportAddManualController addManualController;
    private ReportsAddRecordController addRecordController;
    private ReportDetailsController detailsController;
    private ReportDetailsDialogController detailsDialog;

    public  ReportAddManualController getManualForm() {
        if (addManualController == null) {
            addManualController = new ReportAddManualController();
        }
        return addManualController;
    }

    public  ReportsAddRecordController getRecordBasedForm() {
        if (addRecordController == null) {
            addRecordController = new ReportsAddRecordController();
        }
        return addRecordController;
    }

    public ReportDetailsController getDetailsForm() {
        if (detailsController == null) {
            detailsController = new ReportDetailsController();
        }
        return detailsController;
    }

    public ReportDetailsDialogController getDetailsDialog() {
        if (detailsDialog == null) {
            detailsDialog = new ReportDetailsDialogController();
        }
        return detailsDialog;
    }
}
