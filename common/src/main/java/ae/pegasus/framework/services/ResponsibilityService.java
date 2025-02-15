package ae.pegasus.framework.services;

import ae.pegasus.framework.data_for_entity.RandomEntities;
import ae.pegasus.framework.errors.OperationResultError;
import ae.pegasus.framework.http.G4Response;
import ae.pegasus.framework.http.OperationResult;
import ae.pegasus.framework.http.requests.ResponsibilityRequest;
import ae.pegasus.framework.model.AuthResponseResult;
import ae.pegasus.framework.model.Responsibility;
import ae.pegasus.framework.model.SearchFilter;
import ae.pegasus.framework.model.entities.Entities;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static ae.pegasus.framework.json.JsonConverter.toJsonString;

public class ResponsibilityService implements EntityService<Responsibility> {

    private static Logger log = Logger.getLogger(ResponsibilityService.class);
    private static ResponsibilityRequest request = new ResponsibilityRequest();

    @Override
    public OperationResult<Responsibility> add(Responsibility entity) {
        log.info("Create a new responsibility, name:" + entity.getDisplayName());
        G4Response response = g4HttpClient.sendRequest(request.create(entity));

        OperationResult<Responsibility> operationResult = new OperationResult<>(response, Responsibility.class);
        if (operationResult.isSuccess()) {
            Entities.getResponsibilities().addOrUpdateEntity(operationResult.getEntity());
        }
        return operationResult;
    }

    @Override
    public OperationResult<AuthResponseResult> remove(Responsibility entity) {
        log.info("DELETE responsibility, id:" + entity.getId() + " name:" + entity.getDisplayName());
        G4Response response = g4HttpClient.sendRequest(request.delete(entity.getId()));

        OperationResult<AuthResponseResult> operationResult = new OperationResult<>(response, AuthResponseResult.class);
        if (operationResult.isSuccess()) {
            Entities.getResponsibilities().removeEntity(entity);
        }
        return operationResult;
    }

    @Override
    public OperationResult<List<Responsibility>> search(SearchFilter filter) {
        throw new NotImplementedException("");
    }

    @Override
    public OperationResult<List<Responsibility>> list() {
        log.info("Get list of responsibilities");
        G4Response response = g4HttpClient.sendRequest(request.list());

        OperationResult<Responsibility[]> operationResult = new OperationResult<>(response, Responsibility[].class);
        if (operationResult.isSuccess()) {
            return new OperationResult<>(response, Arrays.asList(operationResult.getEntity()));
        } else {
            log.error("Unable to get list of responsibilities");
            throw new OperationResultError(operationResult);
        }
    }

    @Override
    public OperationResult<Responsibility> update(Responsibility entity) {
        log.info("Update responsibility, id:" + entity.getId() + " name:" + entity.getDisplayName());
        G4Response response = g4HttpClient.sendRequest(request.update(entity));

        OperationResult<Responsibility> operationResult = new OperationResult<>(response, Responsibility.class);
        if (operationResult.isSuccess()) {
            Entities.getResponsibilities().addOrUpdateEntity(operationResult.getEntity());
        }
        return operationResult;
    }

    @Override
    public OperationResult<Responsibility> view(String id) {
        log.info("Getting responsibility by id:" + id);
        G4Response response = g4HttpClient.sendRequest(request.view(id));

        OperationResult<Responsibility> operationResult = new OperationResult<>(response, Responsibility.class);
        if (operationResult.isSuccess()) {
            Entities.getResponsibilities().addOrUpdateEntity(operationResult.getEntity());
        }
        return operationResult;
    }

    public Responsibility createWithPermissions(List<String> permissions) {
        Responsibility responsibility = new RandomEntities().randomEntity(Responsibility.class);
        responsibility.setPermissions(permissions);

        OperationResult<Responsibility> operationResult = add(responsibility);
        if (operationResult.isSuccess()) {
            return operationResult.getEntity();
        } else {
            log.error("Unable create Responsibility: " + toJsonString(responsibility));
            throw new OperationResultError(operationResult);
        }
    }

    public List<OperationResult> removeAll() {
        List<OperationResult> operationResults = new ArrayList<>();

        new ArrayList<>(Entities.getResponsibilities().getEntities()).stream()
                .filter(responsibility -> responsibility.getDisplayName().toLowerCase().startsWith("qe_"))
                .forEach(entity -> operationResults.add(remove(entity)));

        return operationResults;
    }
}
