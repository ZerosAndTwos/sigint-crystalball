package ae.pegasus.framework.steps;

import ae.pegasus.framework.http.OperationResult;
import ae.pegasus.framework.model.ReportsExportModel;
import ae.pegasus.framework.model.Result;
import ae.pegasus.framework.model.SearchRecord;
import ae.pegasus.framework.model.entities.Entities;
import ae.pegasus.framework.model.information_managment.CurrentOwner;
import ae.pegasus.framework.model.information_managment.NextOwners;
import ae.pegasus.framework.model.information_managment.PossibleActions;
import ae.pegasus.framework.model.information_managment.report.Report;
import ae.pegasus.framework.services.ReportService;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import static org.junit.Assert.assertEquals;

public class APIReportSteps extends APISteps {

    private static ReportService serviceReport = new ReportService();

    @When("I get allowed actions")
    public void sendGetAllowedActions() {
        String imId = Entities.getReports().getLatest() == null ? "" : Entities.getReports().getLatest().getId();
        OperationResult<List<PossibleActions>> operationResult = serviceReport.allowedactions(imId);
        List<PossibleActions> possibleActions = operationResult.getEntity();
        context.put("possibleActions", possibleActions);
    }

    @When("I send generate report number request")
    public void sendGenerateReportNumberRequest() {
        Result reportNo = new Result();
        OperationResult<Result> operationResult = serviceReport.generateNumber();
        reportNo.setResult(operationResult.getEntity().getResult());
        Report reportClean = Entities.getReports().getLatest();
        if (reportClean != null) {
            Entities.getReports().removeEntity(reportClean);
        }
        context.put("reportNo", reportNo);
    }

    @When("I send delete a report request")
    public void sendDeleteReportRequest() {
        Report lastReport = Entities.getReports().getLatest();
        serviceReport.remove(lastReport);
    }

    @When("I send view a report request")
    public void sendViewReportRequest() {
        String id = context.get("reportID", String.class);
        serviceReport.view(id);
    }

    @When("I send get owners a report request")
    public void sendGetOwnersReportRequest() {
        Report lastreport = Entities.getReports().getLatest();
        OperationResult<List<CurrentOwner>> currentOwner = serviceReport.possibleOwners(lastreport);
        context.put("currentOwner", currentOwner.getEntity());
    }

    @When("I send get owner a operator report in $state request")
    public void sendGetOwnerOperatorReportRequest(String state) {
        Report lastreport = Entities.getReports().getLatest();
        Report report = new Report();
        String actionId = getRequestAdress(state);
        OperationResult<List<NextOwners>> nextOwner;
        if (lastreport == null) {
            Result reportNo = context.get("reportNo", Result.class);
            List<SearchRecord> entities = context.get("searchEntities", List.class);
            serviceReport.buildReport(report, reportNo, entities);
            context.put("report", report);
        }
        if (lastreport != null) {
            nextOwner = serviceReport.possibleOwner(lastreport, actionId);
        } else {
            nextOwner = serviceReport.possibleOwner(report, actionId);
        }
        context.put("nextOwner", nextOwner.getEntity());
    }


    @When("I send $state a report request")
    public void sendMoveToStateRequest(String state) {
        switch (state) {
            case "Take Ownership":
            case "Return to Author":
            case "Submit for Review":
            case "Re-assign":
            case "Unassign":
            case "Reject":
            case "Assign":
            case "Cancel":
                submit(state);
                break;
            case "Save as Draft":
                saveAsDraft(state);
                break;
            case "Approve":
                approveReport(state);
            case "Save":
                editReport(state);
                break;
            default:
                log.error("State is not found");
        }

    }

    private void submit(String state) {
        Report lastreport = Entities.getReports().getLatest();
        Report report = context.get("report", Report.class);
        String actionId = getRequestAdress(state);
        List<NextOwners> nextOwnersList = context.get("nextOwner", List.class);

        NextOwners nextOwner = nextOwnersList.get(0);
        List<NextOwners> nextOwners = new ArrayList<>();
        nextOwners.add(nextOwner);

        if (lastreport == null) {
            report.setNextOwners(nextOwners);
            report.setComment("comment");
            serviceReport.add(report, actionId);
        } else {
            lastreport.setNextOwners(nextOwners);
            lastreport.setComment("comment");
            serviceReport.add(lastreport, actionId);
        }
    }

    private void returnToAuthor(String state) {
        Report report = Entities.getReports().getLatest();
        List<NextOwners> nextOwners = context.get("nextOwner", List.class);
        report.setNextOwners(nextOwners);
        report.setComment("qe_" + RandomStringUtils.randomAlphabetic(5));
        String actionId = getRequestAdress(state);
        serviceReport.returnAuthor(report, actionId);
    }

    private void saveAsDraft(String state) {
        Report report = new Report();

        if (state.equals("Save as Draft")) {
            Result reportNo = context.get("reportNo", Result.class);
            List<SearchRecord> entities = context.get("searchEntities", List.class);
            serviceReport.buildReport(report, reportNo, entities);
            context.put("report", report);
            String actionId = getRequestAdress(state);
            OperationResult<Report> operationResult = serviceReport.add(report, actionId);
            Report reportResult = operationResult.getEntity();
            context.put("reportID", reportResult.getId());

        } else if (state.equals("Save")) {
            report = Entities.getReports().getLatest();
            String actionId = getRequestAdress(state);
//            report.setSubject("qe_" + RandomStringUtils.randomAlphabetic(10));
            OperationResult<Report> operationResult = serviceReport.add(report, actionId);
            Report reportResult = operationResult.getEntity();
            context.put("reportID", reportResult.getId());
        }
    }

    private void approveReport(String state) {
        Report report = Entities.getReports().getLatest();
        String actionId = getRequestAdress(state);
        serviceReport.approveReport(report, actionId);
    }

    private void rejectReport(String state) {
        Report report = Entities.getReports().getLatest();
        report.setComment("qe_" + RandomStringUtils.randomAlphabetic(5));
        String actionId = getRequestAdress(state);
        serviceReport.rejectReport(report, actionId);
    }

    private void cancelReportWithOwner(String state) {
        Report report = Entities.getReports().getLatest();
        report.setComment("qe_auto_" + RandomStringUtils.randomAlphabetic(5));
        String actionId = getRequestAdress(state);
        serviceReport.cancelReportOwned(report, actionId);
    }


    public void editReport(String state) {
        Report report = Entities.getReports().getLatest();
        String actionId = getRequestAdress(state);
        report.setSubject("qe_" + RandomStringUtils.randomAlphabetic(10));
        report.setDescription("qe_" + RandomStringUtils.randomAlphabetic(10));
        context.put("report", Report.class);
        serviceReport.add(report, actionId);
    }

    @When("I send add a comment to report request")
    public void sendAddACommentRequest() {
        Report report = Entities.getReports().getLatest();
        String comment = "qe_" + RandomStringUtils.randomAlphabetic(10);
        context.put("reportComment", comment);
        report.setComment(comment);
        serviceReport.addComment(report);
    }

    @When("I send export with sources:$sources and without creator:$creator a report request")
    public void sendExportReportRequest(Boolean sources, Boolean creator) {
        Report lastReport = Entities.getReports().getLatest();
        String reportName = "Operator Report #" + lastReport.getReportNo();
        context.put("reportName", reportName);
        OperationResult<File> operationResult = serviceReport.export(lastReport.getId(), sources, creator);
        context.put("file", operationResult.getEntity());
    }

    @When("I send export report bundle with sources:$sources and creator:$creator of reports request")
    public void sendExportBundleOfReportsRequest(Boolean sources, Boolean creator) {
        ReportsExportModel reportsExportModel = new ReportsExportModel();
        fillConstantsOfExportBundle(sources, creator, reportsExportModel);

        List<SearchRecord> entities = context.get("searchEntities", List.class);
        serviceReport.setModels(entities, reportsExportModel);

        OperationResult<File> operationResult = serviceReport.exportBundleReport(reportsExportModel);

        context.put("file", operationResult.getEntity());
    }

    @Then("Check content of bundle of reports")
    public void checkBundleArchive() throws IOException {
        File file = context.get("file", File.class);
        int reportsExpected = context.get("searchEntities", List.class).size();
        int reportsActual = 0;
        ZipFile zipFile = new ZipFile(file.getName());

        Assert.assertTrue("Zip file has no elements!", zipFile.entries().hasMoreElements());
        for (Enumeration e = zipFile.entries(); e.hasMoreElements(); ) {
            ZipEntry entry = (ZipEntry) e.nextElement();
            if (!entry.isDirectory()) {
                if (FilenameUtils.getExtension(entry.getName()).equals("pdf")) {
                    reportsActual++;
                }
            }
        }
        Assert.assertEquals(reportsExpected, reportsActual);
    }

    @Then("Check check that archive is not empty")
    public void checkSizeArchive() throws IOException {
        File file = context.get("file", File.class);
        if (file.length() == 0) {
            throw new AssertionError("Report archive is empty");
        }
    }

    @Then("Check content of archive one report")
    public void checkArchive() throws IOException {
        String reportName = context.get("reportName", String.class);
        String reportNameInArchive = reportName + "/" + reportName + ".pdf";
        File file = context.get("file", File.class);
        ZipFile zipFile = new ZipFile(file.getName());

        Assert.assertTrue("Zip file has no elements!", zipFile.entries().hasMoreElements());
        for (Enumeration e = zipFile.entries(); e.hasMoreElements(); ) {
            ZipEntry entry = (ZipEntry) e.nextElement();
            if (!entry.isDirectory()) {
                if (FilenameUtils.getExtension(entry.getName()).equals("pdf")) {
                    Assert.assertEquals(reportNameInArchive, entry.getName());
                }
            }
            if (entry.isDirectory()) {
                Assert.assertEquals(FilenameUtils.getName(entry.getName()), "attachments");
            }
        }
    }

    @Then("Delete exported reports")
    public void deleteExportedReport() {
        File file = context.get("file", File.class);
        if (file.delete()) {
            log.info(file + " report export is deleted");
        } else log.error(file + " report export file doesn't exists");
    }

    @Then("Report is created")
    public void reportIsCreated() {
        Report lastreport = Entities.getReports().getLatest();
        Report createdReport = context.get("report", Report.class);
        assertEquals(lastreport.getClassification(), createdReport.getClassification());
        assertEquals(lastreport.getReportNo(), createdReport.getReportNo());
        assertEquals(lastreport.getDescription(), createdReport.getDescription());
        assertEquals(lastreport.getState(), "Initial Draft");
        assertEquals(lastreport.getStateId(), "1");
        assertEquals(lastreport.getStateType(), "INITIAL");
    }

    @Then("Operator report is $state and $stateType")
    public void masterReportIsReturned(String state, String stateType) {
        Report lastreport = Entities.getReports().getLatest();
        Report createdReport = context.get("report", Report.class);
        assertEquals(lastreport.getClassification(), createdReport.getClassification());
        assertEquals(lastreport.getReportNo(), createdReport.getReportNo());
        assertEquals(lastreport.getState(), state);
        assertEquals(lastreport.getStateType(), stateType);
    }


    @Then("Report is updated")
    public void reportIsUpdated() {
        Report updatedReport = Entities.getReports().getLatest();
        Report contextReport = context.get("report", Report.class);
        reportCheck(updatedReport, contextReport);
    }

    @Then("Comment is added")
    public void reportCommentIsAdded() {
        Report actualComment = Entities.getReports().getLatest();
        String expectComment = context.get("reportComment", String.class);
        assertEquals(actualComment.getComment(), expectComment);
    }

    private void reportCheck(Report updatedReport, Report contextReport) {
        assertEquals(updatedReport.getSubject(), contextReport.getSubject());
        assertEquals(updatedReport.getDescription(), contextReport.getDescription());
    }

    public String getRequestAdress(String state) {
        List<PossibleActions> possibleActions = context.get("possibleActions", List.class);
        PossibleActions possibleAction = possibleActions.stream()
                .filter(w -> state.equals(w.getActionName()))
                .findAny()
                .orElse(null);
        if (possibleAction == null) {
            log.error("Permission for " + state + " action is missed");
        }
        return possibleAction.getId();
    }

    private void fillConstantsOfExportBundle(Boolean sources, Boolean creator, ReportsExportModel reportsExportModel) {
        reportsExportModel.setShowSources(sources);
        reportsExportModel.setShowCreator(creator);
        reportsExportModel.setCategory("");
    }
}
