package ae.pegasus.framework.steps;

import ae.pegasus.framework.app_context.AppContext;
import ae.pegasus.framework.conditions.Conditions;
import ae.pegasus.framework.conditions.Verify;
import ae.pegasus.framework.controllers.records.RecordAddController;
import ae.pegasus.framework.controllers.records.RecordsDetailsController;
import ae.pegasus.framework.data_for_entity.RandomEntities;
import ae.pegasus.framework.errors.NotFoundException;
import ae.pegasus.framework.model.Record;
import ae.pegasus.framework.model.Report;
import ae.pegasus.framework.utils.RandomGenerator;
import org.apache.log4j.Logger;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

public class UIRecordsSteps extends UISteps {

    private static Logger log = Logger.getLogger(UIRecordsSteps.class);
    private RecordAddController addController;
    private RecordsDetailsController detailsController;
    private RandomEntities randomEntities = new RandomEntities();


    private RecordAddController addController() {
        if (addController==null) {
            addController = new RecordAddController();
        }
        return addController;
    }

    private RecordsDetailsController detailsController() {
        if (detailsController == null) {
            detailsController = new RecordsDetailsController();
        }
        return detailsController;
    }

    @When("I create new $type record")
    public void createRecord(String type) {
        openCreateForm();
        fillTheForm(type);
        navigateTo("Records", "Search");
        filterByDate();
        iShouldSeeNewRecordOnTheTable("see");
    }

    @When("I filter records by current date")
    public void filterByDate() {
        String dateInterval = RandomGenerator.todayDateInterval();
        getRecordsController().getToolbarController().searchByDate(dateInterval);
        getRecordsController().getTableController().waitLoading();
    }

    @When("I open create record form")
    public void openCreateForm() {
        getRecordsController().getToolbarController().openCreateRecordForm();
    }

    @When("I fill the form for $recordType record")
    public void fillTheForm(String recordType) {
        Record record = randomEntities.randomEntity(Record.class);
        record.setType(recordType);
        record.setSourceName(RandomGenerator.getRandomItemFromList(AppContext.get().getDictionary().getSources()).getName());
        addController().fillForm(record);
        context.put("record", record);
    }

    @Then("I should $see new record on the table")
    public void iShouldSeeNewRecordOnTheTable(String see) {
        Record record = getRecordFromContext();
        Record foundRecord;
        try {
            foundRecord = getRecordsController().getTableController().findRecordById(record.getOriginalId());
        } catch (NotFoundException e) {
            if (see.startsWith("not")) {
                return;
            } else {
                log.error(e.getMessage(), e);
                throw new AssertionError(e.getMessage());
            }
        }
        Verify.shouldBe(Conditions.equals(record, foundRecord));
    }

    @When("I checked checkbox on record with $status status")
    public void iCheckFirstRecordCheckbox(String status) {
        Record record;
        if (status.equalsIgnoreCase("non-reported")) {
            record = getRecordsController().getTableController().selectRecordbyStatus("");
        } else {
            record = getRecordsController().getTableController().selectRecordbyStatus(status);
        }
        context.put("record", record);
    }

    @When("I press 'Create Report' button")
    public void iPressCreateReportButton() {
        getRecordsController().getToolbarController().openCreateReportForm();
    }

    @Then("Record status is $status")
    public void chekcRecordStatusOnRecordsProcessedPage(String status) {
        Record record = getRecordFromContext();
        Record recordRow;
        try {
            recordRow = getRecordsController().getTableController().findRecordById(record.getOriginalId());
        } catch (NotFoundException e) {
            log.error(e.getMessage(), e);
            throw new AssertionError(e.getMessage());
        }
        Verify.shouldBe(Conditions.equals(recordRow.getProcessedStatus(), status));
    }

    @When("I press 'Create report' button against it")
    public void pressCreateReportButtonAgainstIt() {
        Record record = getRecordFromContext();
        getRecordsController().getTableController().openCreateReportForm(record.getOriginalId());
    }

    @When("I attach record to draft report from Details dialog")
    public void pressAttachToReportButton() {
        Report report = getReportFromContext();
        detailsController().attachRecordToReport(report.getSubject());
    }

}
