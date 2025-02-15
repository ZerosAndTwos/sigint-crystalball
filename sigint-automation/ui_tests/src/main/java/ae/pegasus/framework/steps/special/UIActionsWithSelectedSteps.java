package ae.pegasus.framework.steps.special;

import org.jbehave.core.annotations.When;
import ae.pegasus.framework.pages.Pages;

import static com.codeborne.selenide.Selenide.sleep;

public class UIActionsWithSelectedSteps {
    @When("I create new report for selected items")
    public void iCreateNewReport() {
        Pages.actionsWithSelected().addSelectedToReport();
        Pages.attachToReportPage().createNewReport();
    }


    @When("I create new master report for selected items")
    public void iCreateNewMasterReport() {
        Pages.actionsWithSelected().addSelectedToMasterReport();
        Pages.attachToReportPage().createNewMasterReport();
    }

    @When("I add selected items to the existing report ($reportName)")
    public void iAddSelectedToExistingReport(String reportName) {
        Pages.actionsWithSelected().addSelectedToReport();
        Pages.attachToReportPage().addToExistingReport(reportName);
    }


    @When("I create new target for selected items")
    public void iCreateNewTarget() {
        Pages.actionsWithSelected().addSelectedToTarget();
        Pages.attachToTargetPage().waitDialogLoading();
        Pages.attachToTargetPage().createNewTarget();
    }

    @When("I add selected items to the existing target ($targetName)")
    public void iAddSelectedToExistingTarget(String targetName) {
        Pages.actionsWithSelected().addSelectedToTarget();
        Pages.attachToTargetPage().waitDialogLoading();
        Pages.attachToTargetPage().addToExistingTarget(targetName);
    }

    @When("I take ownership on selected items")
    public void iAssignSelectedToMe() {
        Pages.actionsWithSelected().assignSelectedToMe();
    }

    @When("I assign selected items to ($userName)")
    public void iAssignSelectedOther(String userName) {
        Pages.actionsWithSelected().assignSelectedToOther();
        Pages.assignRecordsPage().waitDialogLoading();
        Pages.assignRecordsPage().assignRecordToUser(userName);
    }

    @When("I unassign selected items")
    public void iUnassignSelected() {
        Pages.actionsWithSelected().unassignSelected();
    }

    @When("I unassign selected items on OrgUnit Records page")
    public void iUnassignSelectedOnOrgUnit() {
        Pages.actionsWithSelected().unassignSelectedOrgPage();
    }


    @When("I export selected items")
    public void iExportSelected() {
        Pages.actionsWithSelected().exportSelectedRecord();
    }


    @When("I export selected Document")
    public void iExportSelectedReports() {
        Pages.actionsWithSelected().exportSelectedDocumnet();
        //FIXME Seems there is no loading when details opened but such loading is required
        sleep(7000);
    }



    @When("I reassign selected items to ($userName)")
    public void iReassignSelectedOther(String userName) {
        Pages.actionsWithSelected().reassignSelectedToOther();
        Pages.assignRecordsPage().waitDialogLoading();
        Pages.assignRecordsPage().assignRecordToUser(userName);
    }

    @When("I mark selected items on OrgUnit Records page as reviewed")
    public void imarkSelectedAsReivewed() {
        Pages.actionsWithSelected().markReivewedSelected();
    }

    @When("I mark selected items on OrgUnit Records page as unreviewed")
    public void imarkSelectedAsUnReivewed() {
        Pages.actionsWithSelected().unMarkReivewedSelected();
    }


}
