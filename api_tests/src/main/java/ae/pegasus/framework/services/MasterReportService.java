package ae.pegasus.framework.services;

import ae.pegasus.framework.errors.OperationResultError;
import ae.pegasus.framework.http.G4Response;
import ae.pegasus.framework.http.OperationResult;
import ae.pegasus.framework.http.requests.MasterReportRequest;
import ae.pegasus.framework.model.FinderFile;
import ae.pegasus.framework.model.Result;
import ae.pegasus.framework.model.SearchFilter;
import ae.pegasus.framework.model.entities.Entities;
import ae.pegasus.framework.model.information_managment.Lookup;
import ae.pegasus.framework.model.information_managment.NextOwners;
import ae.pegasus.framework.model.information_managment.PossibleActions;
import ae.pegasus.framework.model.information_managment.masterReport.MasterReport;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.List;

public class MasterReportService implements EntityService<MasterReport> {

    private static Logger log = Logger.getLogger(MasterReportService.class);
    private static MasterReportRequest masterReportRequest = new MasterReportRequest();


    @Override
    public OperationResult<?> add(MasterReport entity) {
        throw new NotImplementedException("");
    }

    @Override
    public OperationResult remove(MasterReport entity) {
        log.info("Sending create new report request...");

        G4Response response = g4HttpClient.sendRequest(masterReportRequest.remove(entity));

        OperationResult<MasterReport> operationResult = new OperationResult<>(response, MasterReport.class, "result");
        if (operationResult.isSuccess()) {
            Entities.getMasterReports().addOrUpdateEntity(operationResult.getEntity());
        } else {
            throw new OperationResultError(operationResult);
        }
        return operationResult;
    }

    @Override
    public OperationResult<List<MasterReport>> search(SearchFilter filter) {
        return null;
    }

    @Override
    public OperationResult<List<MasterReport>> list() {
        return null;
    }

    @Override
    public OperationResult<MasterReport> update(MasterReport entity) {
        return null;
    }

    @Override
    public OperationResult<MasterReport> view(String id) {
        log.info("View report by id:" + id);
        G4Response response = g4HttpClient.sendRequest(masterReportRequest.view(id));

        OperationResult<MasterReport> operationResult = new OperationResult<>(response, MasterReport.class);
        if (operationResult.isSuccess()) {
            Entities.getMasterReports().addOrUpdateEntity(operationResult.getEntity());
        }
        return operationResult;
    }


    public OperationResult<Result> generateNumber() {
        log.info("Sending generate report number...");

        G4Response response = g4HttpClient.sendRequest(masterReportRequest.generateNumber());

        OperationResult<Result> operationResult = new OperationResult<>(response, Result.class);
        if (operationResult.isSuccess()) {
            return operationResult;
        } else {
            throw new OperationResultError(operationResult);
        }
    }

    public OperationResult<List<PossibleActions>> possibleaction(String id) {
        log.info("Sending submit a possible actions request...");

        G4Response response = g4HttpClient.sendRequest(masterReportRequest.allowedactions(id));

        OperationResult<PossibleActions[]> operationResult = new OperationResult<>(response, PossibleActions[].class);
        if (operationResult.isSuccess()) {
            return new OperationResult<>(response, Arrays.asList(operationResult.getEntity()));
        } else {
            return new OperationResult<>(response);
        }
    }

    public OperationResult<List<Lookup>> lookupOrgUnits() {
        log.info("Sending submit a possible actions request...");

        G4Response response = g4HttpClient.sendRequest(masterReportRequest.lookupOrgUnits());

        OperationResult<Lookup[]> operationResult = new OperationResult<>(response, Lookup[].class);
        if (operationResult.isSuccess()) {
            return new OperationResult<>(response, Arrays.asList(operationResult.getEntity()));
        } else {
            return new OperationResult<>(response);
        }
    }

    public OperationResult<List<FinderFile>> findFilesCases() {
        log.info("Sending generate report number...");

        G4Response response = g4HttpClient.sendRequest(masterReportRequest.findFilesCases());

        OperationResult<FinderFile[]> operationResult = new OperationResult<>(response, FinderFile[].class, "data");
        if (operationResult.isSuccess()) {
            return new OperationResult<>(response, Arrays.asList(operationResult.getEntity()));
        } else {
            return new OperationResult<>(response);
        }
    }

    public OperationResult<MasterReport> add(MasterReport entity, String actionId) {
        log.info("Sending create new report request...");

        G4Response response = g4HttpClient.sendRequest(masterReportRequest.add(entity, actionId));

        OperationResult<MasterReport> operationResult = new OperationResult<>(response, MasterReport.class, "result");
        if (operationResult.isSuccess()) {
            Entities.getMasterReports().addOrUpdateEntity(operationResult.getEntity());
        } else {
            throw new OperationResultError(operationResult);
        }
        return operationResult;
    }

    public OperationResult<List<NextOwners>> possibleOwner(MasterReport entity, String actionId) {
        log.info("Sending possible owners request...");

        G4Response response = g4HttpClient.sendRequest(masterReportRequest.possibleOwners(entity, actionId));

        OperationResult<NextOwners[]> operationResult = new OperationResult<>(response, NextOwners[].class);
        if (operationResult.isSuccess()) {
            return new OperationResult<>(response, Arrays.asList(operationResult.getEntity()));
        } else {
            return new OperationResult<>(response);
        }
    }

}
