package ae.pegasus.framework.services;

import ae.pegasus.framework.data_for_entity.RandomEntities;
import ae.pegasus.framework.http.requests.TitleRequest;
import ae.pegasus.framework.model.Responsibility;
import ae.pegasus.framework.model.entities.Entities;
import ae.pegasus.framework.errors.OperationResultError;
import ae.pegasus.framework.http.G4Response;
import ae.pegasus.framework.http.OperationResult;
import ae.pegasus.framework.model.AuthResponseResult;
import ae.pegasus.framework.model.SearchFilter;
import ae.pegasus.framework.model.Title;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static ae.pegasus.framework.json.JsonConverter.toJsonString;

public class TitleService implements EntityService<Title> {

    private static Logger log = Logger.getLogger(TitleService.class);
    private static TitleRequest request = new TitleRequest();

    @Override
    public OperationResult<Title> add(Title entity) {
        log.info("Create title, name:" + entity.getDisplayName());
        G4Response response = g4HttpClient.sendRequest(request.create(entity));

        OperationResult<Title> operationResult = new OperationResult<>(response, Title.class);
        if (operationResult.isSuccess()) {
            Entities.getTitles().addOrUpdateEntity(operationResult.getEntity());
        }
        return operationResult;
    }

    @Override
    public OperationResult<AuthResponseResult> remove(Title entity) {
        log.info("Delete title id:" + entity.getId() + " name:" + entity.getDisplayName());
        G4Response response = g4HttpClient.sendRequest(request.delete(entity.getId()));

        OperationResult<AuthResponseResult> operationResult = new OperationResult<>(response, AuthResponseResult.class);
        if (operationResult.isSuccess()) {
            Entities.getTitles().removeEntity(entity);
        }
        return operationResult;
    }

    @Override
    public OperationResult<List<Title>> search(SearchFilter filter) {
        throw new NotImplementedException("");
    }

    @Override
    public OperationResult<List<Title>> list() {
        log.info("Get list of titles");
        G4Response response = g4HttpClient.sendRequest(request.list());

        OperationResult<Title[]> operationResult = new OperationResult<>(response, Title[].class);
        if (operationResult.isSuccess()) {
            return new OperationResult<>(response, Arrays.asList(operationResult.getEntity()));
        } else {
            log.error("Unable to get list of titles");
            throw new OperationResultError(operationResult);
        }
    }

    @Override
    public OperationResult<Title> update(Title entity) {
        log.info("Update title id:" + entity.getId() + " name:" + entity.getDisplayName());
        G4Response response = g4HttpClient.sendRequest(request.update(entity));

        OperationResult<Title> operationResult = new OperationResult<>(response, Title.class);
        if (operationResult.isSuccess()) {
            Entities.getTitles().addOrUpdateEntity(operationResult.getEntity());
        }
        return operationResult;
    }

    @Override
    public OperationResult<Title> view(String id) {
        log.info("Get title by id:" + id);
        G4Response response = g4HttpClient.sendRequest(request.view(id));

        OperationResult<Title> operationResult = new OperationResult<>(response, Title.class);
        if (operationResult.isSuccess()) {
            Entities.getTitles().addOrUpdateEntity(operationResult.getEntity());
        }
        return operationResult;
    }

    public Title createWithResponsibility(Responsibility responsibility) {
        Title title = new RandomEntities().randomEntity(Title.class);
        title.setResponsibilities(Collections.singletonList(responsibility.getId()));

        OperationResult<Title> operationResult = add(title);
        if (operationResult.isSuccess()) {
            return operationResult.getEntity();
        } else {
            throw new AssertionError("Unable create Title: " + toJsonString(title));
        }
    }

    public List<OperationResult> removeAll() {
        List<OperationResult> operationResults = new ArrayList<>();

        new ArrayList<>(Entities.getTitles().getEntities()).stream()
                .filter(title -> title.getDisplayName().toLowerCase().startsWith("qe_"))
                .forEach(entity -> operationResults.add(remove(entity)));

        return operationResults;
    }
}
