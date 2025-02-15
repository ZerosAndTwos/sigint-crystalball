package ae.pegasus.framework.controllers.reports.form_page;

import ae.pegasus.framework.pages.Pages;
import ae.pegasus.framework.controllers.reports.ReportsFormController;
import ae.pegasus.framework.pages.reports.ReportsEditPage;

import static com.codeborne.selenide.Condition.enabled;

public class ReportDetailsController extends ReportsFormController<ReportsEditPage> {
    public ReportDetailsController() {
        super(Pages.reportsEditPage(), ReportsEditPage.class);
    }


    public boolean isEnabled() {
        try {
            getPage().getSubject().shouldBe(enabled);
            getPage().getActionBar().getRefreshButton().shouldBe(enabled);
            getPage().getActionBar().getSaveDraftButton().shouldBe(enabled);
            getPage().getActionBar().getSubmitButton().shouldBe(enabled);
            getPage().getActionBar().getRemoveOwnership().shouldBe(enabled);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
