package ae.pegasus.framework.steps;

import ae.pegasus.framework.app_context.AppContext;
import ae.pegasus.framework.conditions.Conditions;
import ae.pegasus.framework.conditions.Verify;
import ae.pegasus.framework.controllers.TableController;
import ae.pegasus.framework.data_for_entity.RandomEntities;
import ae.pegasus.framework.errors.NotFoundException;
import ae.pegasus.framework.model.Record;
import ae.pegasus.framework.model.Report;
import ae.pegasus.framework.utils.RandomGenerator;
import org.apache.log4j.Logger;
import org.jbehave.core.annotations.Alias;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

public class UIReportsSteps extends UISteps {

    private static Logger log = Logger.getLogger(UIReportsSteps.class);
    private RandomEntities randomEntities = new RandomEntities();

    @When("I create new manual draft report")
    public void createManualDraftReport() {
        pressCreateManualReport();
        fillOutRequiredFieldsOnCreateManualReportPage();
        iPressSaveAsDraftButton();
        navigateTo("Reports", "All");
        reportAppearsOnReportsDraftScreen("see");
    }

    @When("I press 'Create Manual Report' button")
    public void pressCreateManualReport() {
        getReportsController().getToolbarController().openCreateReportForm();
    }

    @When("I press 'Save As Draft' button")
    public void iPressSaveAsDraftButton() {
        getReportsFormFactory().getDetailsForm().saveAsDraft();
    }

    @When("I press 'Submit' button")
    public void pressSubmitButton() {
        getReportsFormFactory().getDetailsForm().submitReport();
    }

    @When("Fill out required fields on 'Create Report' page based from record")
    public void fillOutRequiredFieldsOnCreateReportScreen() {
        Record record  = getRecordFromContext();
        Report report = randomEntities.randomEntity(Report.class);
        report.setRecordType(record.getType());
        report.setSourceType(record.getSourceName());
        getReportsFormFactory().getRecordBasedForm().fillForm(report);
        context.put("report", report);
    }

    @When("I fill out required fields on 'Create Manual Report' page")
    public void fillOutRequiredFieldsOnCreateManualReportPage() {
        Report report = randomEntities.randomEntity(Report.class);
        report.setRecordType(RandomGenerator.getRandomItemFromList(AppContext.get().getDictionary().getSources()).getRecordType());
        report.setSourceType(RandomGenerator.getRandomItemFromList(AppContext.get().getDictionary().getSources()).getType());

        getReportsFormFactory().getManualForm().fillForm(report);
        context.put("report", report);
    }

    @Then("I should $see report on page")
    public void reportAppearsOnReportsDraftScreen(String see) {
        Report report = getReportFromContext();
        Report reportRow;
        try {
            reportRow = getReportsController().getTableController().findReportBySubject(report.getSubject());
        } catch (NotFoundException e) {
            if (see.startsWith("not")) {
                return;
            } else {
                throw new AssertionError(e.getMessage());
            }
        }
        Verify.shouldBe(Conditions.equals(report.getSubject(), reportRow.getSubject()));
    }

    @Then("report status is '$status' on 'Reports->All' page")
    @Alias("report status is '$status'")
    public void reportStatusIsDraft(String status) {
        Report report = getReportFromContext();
        Report reportRow;
        try {
            reportRow = getReportsController().getTableController().findReportBySubject(report.getSubject());
        } catch (NotFoundException e) {
            throw new AssertionError(e.getMessage());
        }
        Verify.shouldBe(Conditions.equals(reportRow.getStatus(), status));
    }


    @Then("record is attached to the report")
    public void recordIsAttachedToReport() {
        Record record = getRecordFromContext();
        Report report = getReportFromContext();
        getReportsController().getTableController().openDetailsForm(report.getSubject());
        Boolean isExist = getReportsFormFactory().getDetailsDialog().isRecordAttached(record.getText());
        Verify.shouldBe(Conditions.isTrue.element(isExist));
    }


    @When("I select $reportType report in the table")
    public void selectFirstReport(String reportSubject) {
        Report report;
        if (reportSubject.equalsIgnoreCase("any")){
            report = getReportsController().getTableController().selectAnyReport();
        } else {
            try {
                report = getReportsController().getTableController().findReportBySubject(reportSubject);
            } catch (NotFoundException e) {
                throw new AssertionError(e.getMessage());
            }
        }
        context.put("report", report);
    }

    @When("I press 'Edit Report' button against it")
    public void pressEditReportButtonAgainstIt() {
        Report report = getReportFromContext();
        getReportsController().getTableController().openEditForm(report.getSubject());
    }


    @When("I press 'Remove Ownership' button on 'Edit Report' page")
    public void pressRemoveOwnershipButton() {
        getReportsFormFactory().getDetailsForm().removeOwnership(getReportFromContext());
    }


    @Then("report owner should be $owner")
    public void reportOwnerShouldBeEmpty(String owner) {
        Report contextReport = getReportFromContext();
        Report rowReport;
        try {
            rowReport = getReportsController().getTableController().findReportBySubject(contextReport.getSubject());
        } catch (NotFoundException e) {
            throw new AssertionError();
        }
        if (owner.equalsIgnoreCase("empty")) {
            owner = "";
        }
        Verify.shouldBe(Conditions.equals(owner, rowReport.getOwnerName()));
    }

    @When("I select a report in $status status and owner is $owner")
    public void selectReportInStatusAndOwnerIs(String status, String owner){
        if (owner.equalsIgnoreCase("empty")) {
            owner = "";
        }
        Report report = getReportsController().getTableController().selectReportByFilter(
                TableController.filter().set("Status", status).set("Owner", owner));
        context.put("report", report);
    }

    @When("I press 'Take Ownership' button on 'Edit Report' page")
    public void pressTakeOwnershipButtonOnEditReportPage() {
        getReportsFormFactory().getDetailsForm().takeOwnership(getReportFromContext(), getUserFromContext());
    }

    @Then("Edit report form is enabled")
    public void requiredFieldsAreEnabledOnEditReportPage() {
        Verify.shouldBe(Conditions.isTrue.element(getReportsFormFactory().getDetailsForm().isEnabled()));
    }





}
