package ae.pegasus.framework.controllers.reports.form_page;

import ae.pegasus.framework.pages.Pages;
import ae.pegasus.framework.controllers.reports.ReportsFormController;
import ae.pegasus.framework.pages.reports.ReportsCreatePage;

public class ReportsAddRecordController extends ReportsFormController<ReportsCreatePage> {

    public ReportsAddRecordController() {
        super(Pages.reportsCreatePage(), ReportsCreatePage.class);
    }
}
