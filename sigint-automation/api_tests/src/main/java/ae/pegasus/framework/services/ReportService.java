package ae.pegasus.framework.services;

import ae.pegasus.framework.errors.OperationResultError;
import ae.pegasus.framework.http.G4Response;
import ae.pegasus.framework.http.OperationResult;
import ae.pegasus.framework.http.requests.ReportRequest;
import ae.pegasus.framework.json.JsonConverter;
import ae.pegasus.framework.model.*;
import ae.pegasus.framework.model.entities.Entities;
import ae.pegasus.framework.model.information_managment.*;
import ae.pegasus.framework.model.information_managment.report.Report;
import ae.pegasus.framework.model.information_managment.report.ReportEvent;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import static ae.pegasus.framework.utils.RandomGenerator.getRandomItemsFromList;

public class ReportService implements EntityService<Report> {

    private static Logger log = Logger.getLogger(ReportService.class);
    private static ReportRequest reportRequest = new ReportRequest();

    public OperationResult<Report> add(Report entity, String actionId) {
        log.info("Sending create new report request...");

        G4Response response = g4HttpClient.sendRequest(reportRequest.add(entity, actionId));

        OperationResult<Report> operationResult = new OperationResult<>(response, Report.class, "result");
        if (operationResult.isSuccess()) {
            Entities.getReports().addOrUpdateEntity(operationResult.getEntity());
        } else {
            throw new OperationResultError(operationResult);
        }
        return operationResult;
    }

    @Override
    public OperationResult<?> add(Report entity) {
        throw new NotImplementedException("");
    }

    @Override
    public OperationResult remove(Report entity) {
        log.info("Sending delete a report request...");

        G4Response response = g4HttpClient.sendRequest(reportRequest.remove(entity));

        OperationResult<Report> operationResult = new OperationResult<>(response, Report.class, "result");
        if (operationResult.isSuccess()) {
            Entities.getReports().removeEntity(operationResult.getEntity());
        } else {
            throw new OperationResultError(operationResult);
        }
        return operationResult;
    }

    @Override
    public OperationResult<List<Report>> search(SearchFilter filter) {
        throw new NotImplementedException("");
    }

    @Override
    public OperationResult<List<Report>> list() {
        throw new NotImplementedException("");
    }

    @Override
    public OperationResult<Report> update(Report entity) {
        return (OperationResult<Report>) add(entity);
    }

    @Override
    public OperationResult<Report> view(String id) {
        log.info("View report by id:" + id);
        G4Response response = g4HttpClient.sendRequest(reportRequest.view(id));

        OperationResult<Report> operationResult = new OperationResult<>(response, Report.class);
        if (operationResult.isSuccess()) {
            Entities.getReports().addOrUpdateEntity(operationResult.getEntity());
        }
        return operationResult;
    }

    public OperationResult<File> export(String id, Boolean sources, Boolean creator) {
        log.info("Export report by id: " + id);
        G4Response response = g4HttpClient.sendRequest(reportRequest.exportReport(id, sources, creator));

        OperationResult<File> operationResult = new OperationResult<>(response, File.class);
        if (operationResult.isSuccess()) {
            return operationResult;
        } else {
            throw new OperationResultError(operationResult);
        }
    }

    public OperationResult<File> exportBundleReport(ReportsExportModel reportsExportModel) {
        log.info("Export bundle of reports");
        G4Response response = g4HttpClient.sendRequest(reportRequest.exportBundleReport(reportsExportModel));

        OperationResult<File> operationResult = new OperationResult<>(response, File.class);
        if (operationResult.isSuccess()) {
            return operationResult;
        } else {
            throw new OperationResultError(operationResult);
        }
    }

    public OperationResult<Report> submit(Report entity, String actionId) {
        log.info("Sending submit a new report request...");

        G4Response response = g4HttpClient.sendRequest(reportRequest.submit(entity, actionId));

        OperationResult<Report> operationResult = new OperationResult<>(response, Report.class, "result");
        if (operationResult.isSuccess()) {
            Entities.getReports().addOrUpdateEntity(operationResult.getEntity());
        } else {
            throw new OperationResultError(operationResult);
        }
        return operationResult;
    }

    public OperationResult<Report> takeOwnership(Report entity, String actionId) {
        log.info("Sending take ownership a report request...");

        G4Response response = g4HttpClient.sendRequest(reportRequest.takeOwnership(entity, actionId));

        OperationResult<Report> operationResult = new OperationResult<>(response, Report.class, "result");
        if (operationResult.isSuccess()) {
            Entities.getReports().addOrUpdateEntity(operationResult.getEntity());
        } else {
            throw new OperationResultError(operationResult);
        }
        return operationResult;
    }

    public OperationResult<Report> approveReport(Report entity, String actionId) {
        log.info("Sending approve a report request...");

        G4Response response = g4HttpClient.sendRequest(reportRequest.approveReport(entity, actionId));

        OperationResult<Report> operationResult = new OperationResult<>(response, Report.class, "result");
        if (operationResult.isSuccess()) {
            Entities.getReports().addOrUpdateEntity(operationResult.getEntity());
        } else {
            throw new OperationResultError(operationResult);
        }
        return operationResult;
    }

    public OperationResult<Report> returnAuthor(Report entity, String actionId) {
        log.info("Sending return to author report request...");

        G4Response response = g4HttpClient.sendRequest(reportRequest.returnAuthor(entity, actionId));

        OperationResult<Report> operationResult = new OperationResult<>(response, Report.class, "result");
        if (operationResult.isSuccess()) {
            Entities.getReports().addOrUpdateEntity(operationResult.getEntity());
        } else {
            throw new OperationResultError(operationResult);
        }
        return operationResult;
    }

    public OperationResult<Report> rejectReport(Report entity, String actionId) {
        log.info("Sending reject a report request...");

        G4Response response = g4HttpClient.sendRequest(reportRequest.rejectReport(entity, actionId));

        OperationResult<Report> operationResult = new OperationResult<>(response, Report.class, "result");
        if (operationResult.isSuccess()) {
            Entities.getReports().addOrUpdateEntity(operationResult.getEntity());
        } else {
            throw new OperationResultError(operationResult);
        }
        return operationResult;
    }

    public OperationResult<Report> cancelReportNotOwned(Report entity) {
        log.info("Sending reject a report request...");

        G4Response response = g4HttpClient.sendRequest(reportRequest.cancelReportNotOwned(entity));

        OperationResult<Report> operationResult = new OperationResult<>(response, Report.class, "result");
        if (operationResult.isSuccess()) {
            Entities.getReports().addOrUpdateEntity(operationResult.getEntity());
        } else {
            throw new OperationResultError(operationResult);
        }
        return operationResult;
    }

    public OperationResult<Report> cancelReportOwned(Report entity, String actionId) {
        log.info("Sending reject a report request...");

        G4Response response = g4HttpClient.sendRequest(reportRequest.cancelReportOwned(entity, actionId));

        OperationResult<Report> operationResult = new OperationResult<>(response, Report.class, "result");
        if (operationResult.isSuccess()) {
            Entities.getReports().addOrUpdateEntity(operationResult.getEntity());
        }
        return operationResult;
    }

    public OperationResult<Report> addComment(Report entity) {
        log.info("Sending add a comment to report request...");

        G4Response response = g4HttpClient.sendRequest(reportRequest.addComment(entity));

        OperationResult<Report> operationResult = new OperationResult<>(response, Report.class, "result");
        if (operationResult.isSuccess()) {
            Entities.getReports().addOrUpdateEntity(operationResult.getEntity());
        }
        return operationResult;
    }

    public OperationResult<List<CurrentOwner>> possibleOwners(Report entity) {
        log.info("Sending possible owners request...");

        G4Response response = g4HttpClient.sendRequest(reportRequest.possibleOwners(entity));

        OperationResult<CurrentOwner[]> operationResult = new OperationResult<>(response, CurrentOwner[].class);
        if (operationResult.isSuccess()) {
            return new OperationResult<>(response, Arrays.asList(operationResult.getEntity()));
        } else {
            return new OperationResult<>(response);
        }
    }

    public OperationResult<List<NextOwners>> possibleOwner(Report entity, String actionId) {
        log.info("Sending possible owners request...");

        G4Response response = g4HttpClient.sendRequest(reportRequest.possibleOwner(entity, actionId));

        OperationResult<NextOwners[]> operationResult = new OperationResult<>(response, NextOwners[].class);
        if (operationResult.isSuccess()) {
            return new OperationResult<>(response, Arrays.asList(operationResult.getEntity()));
        } else {
            return new OperationResult<>(response);
        }
    }

    public OperationResult<Result> generateNumber() {
        log.info("Sending create new report number request...");

        G4Response response = g4HttpClient.sendRequest(reportRequest.generateNumber());

        OperationResult<Result> operationResult = new OperationResult<>(response, Result.class);
        if (operationResult.isSuccess()) {
            return operationResult;
        } else {
            throw new OperationResultError(operationResult);
        }
    }

    public OperationResult<List<PossibleActions>> allowedactions(String id) {
        log.info("Sending submit a new report request...");

        G4Response response = g4HttpClient.sendRequest(reportRequest.allowedactions(id));

        OperationResult<PossibleActions[]> operationResult = new OperationResult<>(response, PossibleActions[].class);
        if (operationResult.isSuccess()) {
            return new OperationResult<>(response, Arrays.asList(operationResult.getEntity()));
        } else {
            return new OperationResult<>(response);
        }
    }

    public void buildReport(Report report, Result reportNo, List<SearchRecord> entities) {
        fillReportStaticData(report);
        report.setReportNo(reportNo.getResult());
        fillReportEvents(entities, report);
        fillReportOrgUnit(report);
        fillReportFinderFile(report);
    }

    private void fillReportStaticData(Report report) {
        report.setObjectType("OperatorReport");
        report.setLayoutType("CARD_GROUP");
        report.setReportType("GENERAL");
        report.setClassification("TS");
        report.setState("Initial Draft");
        report.setDescription("qe_" + RandomStringUtils.randomAlphabetic(5));
        report.setSubject("qe_" + RandomStringUtils.randomAlphabetic(5));
    }

    private void fillReportOrgUnit(Report report) {
        User user = appContext.get().getLoggedUser().getUser();
        report.setCreatedByName(user.getName());

        String teamID = UserService.getOrCreateDefaultTeamId();
        OrganizationFilter filter = new OrganizationFilter();
        OperationResult<List<Organization>> operationResult = new OrganizationService().search(filter);
        List<Organization> orgUnits = operationResult.getEntity();
        Organization organization = orgUnits.stream()
                .filter(a -> Objects.equals(a.getId(), teamID))
                .findAny().orElse(null);
        if (organization == null) {
            /* TODO */
        }
        OrgUnit orgUnit = new OrgUnit();
        orgUnit.setOrgUnitId("00-" + teamID);
        orgUnit.setOrgUnitName(organization.getFullName());
        report.setOrgUnits(Collections.singletonList(orgUnit));
    }

    private void fillReportEvents(List<SearchRecord> entities, Report report) {
        int randomNum = ThreadLocalRandom.current().nextInt(50, 150 + 1);
        List<SearchRecord> event = getRandomItemsFromList(entities, randomNum);

        List<ReportEvent> reportEvents = new ArrayList<>();

        for (int i = 0; i < event.size(); i++) {
            ReportEvent reportEvent = new ReportEvent();
            reportEvent.setOrder(i);
            reportEvent.setId(event.get(i).getId());
            reportEvent.setType(event.get(i).getType());
            reportEvent.setBody(JsonConverter.toJsonString(event.get(i)));

            SourceType source = new SourceType();
            source.setEventFeed(String.valueOf(event.get(i).getEventFeed()));
            source.setDataSource(event.get(i).getSourceType());
            source.setSubSource(event.get(i).getRecordType());
            source.setSubSourceId(String.valueOf(event.get(i).getAttributes().get("interceptorId")));

            reportEvent.setSourceType(source);
            reportEvents.add(reportEvent);
        }
        report.setReportEvents(reportEvents);
    }

    private void fillReportFinderFile(Report report) {
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
        report.setDirectCaseFiles(Collections.singletonList(directCaseFile));
    }

    public void setNextOwnersTeams(List<CurrentOwner> currentOwner, List<NextOwners> allOwners) {
        for (CurrentOwner owner : currentOwner) {
            NextOwners nextOwner = new NextOwners();
            nextOwner.setOwnerId(owner.getOwnerId());
            nextOwner.setOwnerName(owner.getOwnerName());
            nextOwner.setType(owner.getType());
            allOwners.add(nextOwner);
        }
    }

    public void setModels(List<SearchRecord> entities, ReportsExportModel reportsExportModel) {
        List<ReportModels> reportModels = new ArrayList<>();

        for (int i = 0; i < entities.size(); i++) {
            ReportModels reportModel = new ReportModels();
            Map<String, Object> attributes = entities.get(i).getAttributes();
            reportModel.setId((String) attributes.get("id"));
            reportModel.setWfId((String) attributes.get("wfId"));
            reportModels.add(reportModel);
        }
        reportsExportModel.setModels(reportModels);
    }
}
