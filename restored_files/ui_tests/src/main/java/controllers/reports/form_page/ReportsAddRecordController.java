package controllers.reports.form_page;

import controllers.reports.ReportsFormController;
import pages.Pages;
import pages.reports.ReportsCreatePage;

public class ReportsAddRecordController extends ReportsFormController<ReportsCreatePage> {

    public ReportsAddRecordController() {
        super(Pages.reportsCreatePage(), ReportsCreatePage.class);
    }
}
