package ae.pegasus.framework.steps;

import ae.pegasus.framework.http.OperationResult;
import ae.pegasus.framework.model.FinderFile;
import ae.pegasus.framework.model.Result;
import ae.pegasus.framework.model.SearchRecord;
import ae.pegasus.framework.model.entities.Entities;
import ae.pegasus.framework.model.information_managment.*;
import ae.pegasus.framework.model.information_managment.masterReport.MasterReport;
import ae.pegasus.framework.services.MasterReportService;
import org.apache.commons.lang3.RandomStringUtils;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class APIMasterReportSteps extends APISteps {
    private static MasterReportService serviceMasterReport = new MasterReportService();
    private static APIReportSteps serviceReport = new APIReportSteps();



    @When("I send generate master report number request")
    public void sendGenerateMasterReportNumberRequest() {
        Result reportNo = new Result();
        OperationResult<Result> operationResult = serviceMasterReport.generateNumber();
        reportNo.setResult(operationResult.getEntity().getResult());
        MasterReport reportClean = Entities.getMasterReports().getLatest();
        if (reportClean != null) {
            Entities.getMasterReports().removeEntity(reportClean);
        }
        context.put("reportNo", reportNo);
    }

    @When("I get allowed master report actions")
    public void sendGetAllowedMRActions() {
        String imId = Entities.getMasterReports().getLatest() == null ? "" : Entities.getMasterReports().getLatest().getId();
        OperationResult<List<PossibleActions>> operationResult = serviceMasterReport.possibleaction(imId);
        List<PossibleActions> possibleActions = operationResult.getEntity();
        context.put("possibleActions", possibleActions);
    }

    @When("I send $state a master report request")
    public void sendMoveToStateRequest(String state) {
        switch (state) {
            case "Save as Draft":
                saveAsDraft(state);
                break;
            case "View":
                view();
                break;
            case "Delete":
                delete();
                break;
            case "Save":
                saveAsDraft(state);
                break;
            case "Submit for Review":
                submit(state);
                break;
            case "Cancel":
                postMR(state);
                break;
            case "Take Ownership":
                submit(state);
                break;
            case "Assign":
                submit(state);
                break;
            case "Unassign":
                submit(state);
                break;
            case "Re-assign":
                submit(state);
                break;
            case "Return to Author":
                submit(state);
                break;
            case "Approve":
                postMR(state);
                break;
            case "Reject":
                postMR(state);
                break;
            default:
                log.error("State is not found");
        }
    }

    private void submit(String state) {
        MasterReport lastreport = Entities.getMasterReports().getLatest();
        MasterReport report = context.get("masterReport", MasterReport.class);
        String actionId = serviceReport.getRequestAdress(state);
        List<NextOwners> nextOwnersList = context.get("nextOwner", List.class);

        NextOwners nextOwner = nextOwnersList.get(0);
        List<NextOwners> nextOwners = new ArrayList<>();
        nextOwners.add(nextOwner);

        if (lastreport == null) {
            report.setNextOwners(nextOwners);
            report.setComment("comment");
            serviceMasterReport.add(report, actionId);
        } else {
            lastreport.setNextOwners(nextOwners);
            lastreport.setComment("comment");
            serviceMasterReport.add(lastreport, actionId);
        }

    }

    private void postMR(String state) {
        MasterReport lastreport = Entities.getMasterReports().getLatest();
        String actionId = serviceReport.getRequestAdress(state);
        lastreport.setComment("comment");
        serviceMasterReport.add(lastreport, actionId);
    }


    private void view() {
        String id = context.get("reportID", String.class);
        serviceMasterReport.view(id);
    }

    private void delete() {
        MasterReport lastReport = Entities.getMasterReports().getLatest();
        serviceMasterReport.remove(lastReport);
    }

    private void saveAsDraft(String state) {
        MasterReport masterReport = new MasterReport();

        if (state.equals("Save as Draft")) {
            buildMasterReport(masterReport);
            String actionId = serviceReport.getRequestAdress(state);
            context.put("masterReport", masterReport);
            OperationResult<MasterReport> operationResult = serviceMasterReport.add(masterReport, actionId);
            MasterReport reportResult = operationResult.getEntity();
            context.put("reportID", reportResult.getId());

        } else if (state.equals("Save")) {
            MasterReport report = Entities.getMasterReports().getLatest();
            String actionId = serviceReport.getRequestAdress(state);
            report.setSubject("qe_" + RandomStringUtils.randomAlphabetic(10));
            OperationResult<MasterReport> operationResult = serviceMasterReport.add(report, actionId);
            MasterReport reportResult = operationResult.getEntity();
            context.put("expectedReport", reportResult);
        }
    }

    private void buildMasterReport(MasterReport masterReport) {
        setReportNumber(masterReport);
        fillMasterReportStaticData(masterReport);
        fillRecords(masterReport);
        fillOrgUnit(masterReport);
        fillCaseFile(masterReport);

    }

    private void fillCaseFile(MasterReport masterReport) {
        FinderFile finderFile = Entities.getFinderFiles().getLatest();
        DirectCaseFile directCaseFile = new DirectCaseFile();
        directCaseFile.setLinkId(finderFile.getId());
        directCaseFile.setLinkNo(finderFile.getId());
        directCaseFile.setLinkName(finderFile.getName());
        directCaseFile.setLinkType("FILE");
        directCaseFile.setRelationType("RELATED");
        Attributes attributes = new Attributes();
        attributes.setType("File");
        attributes.setIcon("far fa-folder");
        directCaseFile.setAttributes(attributes);
        masterReport.setDirectCaseFiles(Collections.singletonList(directCaseFile));
    }


    private void fillOrgUnit(MasterReport masterReport) {
        OperationResult<List<Lookup>> operationResult = serviceMasterReport.lookupOrgUnits();
        List<Lookup> listLookup = operationResult.getEntity();
        ArrayList<OrgUnit> orgUnits = new ArrayList<>();

        for (Lookup lookup : listLookup) {
            if (lookup.getName().equals("orgUnit")) {
                for (Entries entrie : lookup.getEntries()) {
                    OrgUnit orgUnit = new OrgUnit();
                    orgUnit.setOrgUnitName(entrie.getValue());
                    orgUnit.setOrgUnitId(entrie.getKey());
                    orgUnits.add(orgUnit);
                }
            }
        }

        OrgUnit orgUnit = orgUnits
                .stream()
                .filter(w -> w.getOrgUnitName().equals("QE auto team")).findAny().orElse(null);

        ArrayList<OrgUnit> orgUnitsSet = new ArrayList<>();
        orgUnitsSet.add(orgUnit);

        masterReport.setOrgUnits(orgUnitsSet);

    }

    private void fillRecords(MasterReport masterReport) {
        List<SearchRecord> entities = context.get("searchEntities", List.class);
        List<Map<String, Object>> reportList = new ArrayList<>();

        for (SearchRecord searchRecord : entities) {
            Map<String, Object> report = searchRecord.getAttributes();
            report.put("reportId", report.get("id"));
            reportList.add(report);
        }
        masterReport.setOriginatingReports(reportList);
    }

    private void fillMasterReportStaticData(MasterReport masterReport) {
        masterReport.setObjectType("MasterReport");
        masterReport.setLayoutType("CARD_GROUP");
        masterReport.setReportType("GENERAL");
        masterReport.setClassification("TS");
        masterReport.setState("Initial Draft");
        masterReport.setSubject("qe_" + RandomStringUtils.randomAlphabetic(5));
    }

    private void setReportNumber(MasterReport masterReport) {
        Result reportNo = context.get("reportNo", Result.class);
        masterReport.setReportNo(reportNo.getResult());
    }

    @When("I send get owner a master report in $state request")
    public void sendGetOwnerReportRequest(String state) {
        MasterReport lastreport = Entities.getMasterReports().getLatest();
        MasterReport masterReport = new MasterReport();
        String actionId = serviceReport.getRequestAdress(state);
        OperationResult<List<NextOwners>> nextOwner;
        if (lastreport == null) {
            buildMasterReport(masterReport);
            context.put("masterReport", masterReport);
        }
        if (lastreport != null) {
            nextOwner = serviceMasterReport.possibleOwner(lastreport, actionId);
        } else {
            nextOwner = serviceMasterReport.possibleOwner(masterReport, actionId);
        }
        context.put("nextOwner", nextOwner.getEntity());
    }

    @Then("Master report is $state and $stateType")
    public void masterReportIsReturned(String state, String stateType) {
        MasterReport lastreport = Entities.getMasterReports().getLatest();
        MasterReport createdReport = context.get("masterReport", MasterReport.class);
        assertEquals(lastreport.getClassification(), createdReport.getClassification());
        assertEquals(lastreport.getReportNo(), createdReport.getReportNo());
        assertEquals(lastreport.getState(), state);
        assertEquals(lastreport.getStateType(), stateType);
    }


    @Then("Master report is edited")
    public void masterReportIsEdited() {
        MasterReport lastreport = Entities.getMasterReports().getLatest();
        MasterReport expectedReport = context.get("expectedReport", MasterReport.class);
        assertEquals(lastreport.getSubject(), expectedReport.getSubject());

    }


}
