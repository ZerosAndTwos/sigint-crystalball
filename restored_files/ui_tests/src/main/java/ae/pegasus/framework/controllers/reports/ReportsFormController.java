package ae.pegasus.framework.controllers.reports;

import ae.pegasus.framework.pages.SigintPage;
import ae.pegasus.framework.pages.reports.ReportsPage;
import ae.pegasus.framework.controllers.PageController;
import ae.pegasus.framework.model.Report;
import ae.pegasus.framework.model.User;

public class ReportsFormController<T extends ReportsPage> extends PageController<T> {

    public ReportsFormController(SigintPage page, Class<T> userClass) {
        super(page, userClass);
    }

    public Report fillForm(Report report) {
        getPage().setSubject(report.getSubject());
        return report;
    }

    public void saveAsDraft() {
        getPage().getActionBar().clickSaveDraft();
    }

    public void submitReport() {getPage().getActionBar().clickSubmitButton();}


    public void takeOwnership(Report report, User user) {
        getPage().getActionBar().clickTakeOwnershipButton();
        report.setOwnerName(user.getName());
    }


    public void removeOwnership(Report report) {
        getPage().getActionBar().clickRemoveOwnership();
        report.setOwnerName("");
    }
}
