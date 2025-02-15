package ae.pegasus.framework.model.information_managment;

public enum ReportState {
    OPERATOR_REPORT_SAVE_AS_DRAFT("Save as Draft"),
    OPERATOR_REPORT_SUBMIT_FOR_REVIEW("Submit for Review");

    private final String reportState;

    ReportState(String reportState) {
        this.reportState = reportState;
    }

    public String getReportState() {
        return reportState;
    }
}
