package ae.pegasus.framework.services;

import ae.pegasus.framework.http.G4Response;
import ae.pegasus.framework.http.OperationResult;
import ae.pegasus.framework.http.requests.FinderCaseRequest;
import ae.pegasus.framework.model.FinderCase;
import ae.pegasus.framework.model.SearchFilter;
import ae.pegasus.framework.model.entities.Entities;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;

import java.util.List;

import static ae.pegasus.framework.json.JsonConverter.toJsonString;

public class FinderCaseService implements EntityService<FinderCase> {

    private static final Logger log = Logger.getLogger(FinderCaseService.class);
    private static final FinderCaseRequest request = new FinderCaseRequest();

    @Override
    public OperationResult<FinderCase> add(FinderCase entity) {
        log.info("Creating finder case:" + toJsonString(entity));
        G4Response response = g4HttpClient.sendRequest(request.add(entity));

        OperationResult<FinderCase> operationResult = new OperationResult<>(response, FinderCase.class, "data");
        if (operationResult.isSuccess()) {
            Entities.getFinderCases().addOrUpdateEntity(operationResult.getEntity());
        }
        return operationResult;
    }

    @Override
    public OperationResult remove(FinderCase entity) {
        log.info("Deleting finder case id:" + entity.getId() + ", name:" + entity.getName());
        G4Response response = g4HttpClient.sendRequest(request.delete(entity.getId()));

        OperationResult<String> deleteResult = new OperationResult<>(response);
        if (deleteResult.isSuccess()) {
            Entities.getFinderCases().removeEntity(entity);
        }
        return deleteResult;
    }

    @Override
    public OperationResult<List<FinderCase>> search(SearchFilter filter) {
        throw new NotImplementedException("");
    }

    @Override
    public OperationResult<List<FinderCase>> list() {
        throw new NotImplementedException("");
    }

    @Override
    public OperationResult<FinderCase> update(FinderCase entity) {
        log.info("Updating finder case: " + toJsonString(entity));
        G4Response response = g4HttpClient.sendRequest(request.update(entity));

        OperationResult<FinderCase> operationResult = new OperationResult<>(response, FinderCase.class, "data");
        if (operationResult.isSuccess()) {
            Entities.getFinderCases().addOrUpdateEntity(operationResult.getEntity());
        }
        return operationResult;
    }

    @Override
    public OperationResult<FinderCase> view(String id) {
        log.info("View finder case id:" + id);
        G4Response response = g4HttpClient.sendRequest(request.get(id));

        OperationResult<FinderCase> operationResult = new OperationResult<>(response, FinderCase.class, "data");
        if (operationResult.isSuccess()) {
            Entities.getFinderCases().addOrUpdateEntity(operationResult.getEntity());
        }
        return operationResult;
    }
}
