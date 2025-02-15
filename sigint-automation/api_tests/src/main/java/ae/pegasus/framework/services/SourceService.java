package ae.pegasus.framework.services;

import ae.pegasus.framework.errors.OperationResultError;
import ae.pegasus.framework.http.G4Response;
import ae.pegasus.framework.http.OperationResult;
import ae.pegasus.framework.http.requests.SourceRequest;
import ae.pegasus.framework.json.JsonConverter;
import ae.pegasus.framework.model.Result;
import ae.pegasus.framework.model.SearchFilter;
import ae.pegasus.framework.model.Source;
import ae.pegasus.framework.model.entities.Entities;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.List;

public class SourceService implements EntityService<Source> {

    private static Logger log = Logger.getLogger(SourceService.class);
    private static SourceRequest request = new SourceRequest();

    /**
     * API: PUT /api/admin/sources
     *
     * @param entity Source entity
     * @return HTTP status code
     */
    @Override
    public OperationResult<Source> add(Source entity) {
        log.info("Creating new Source:" + JsonConverter.toJsonString(entity));
        G4Response response = g4HttpClient.sendRequest(request.add(entity));

        OperationResult<Source> operationResult = new OperationResult<>(response, Source.class);
        if (operationResult.isSuccess()) {
            Entities.getSources().addOrUpdateEntity(operationResult.getEntity());
        }
        return operationResult;
    }

    /**
     * API: DELETE /api/admin/sources/{id}
     *
     * @param entity Source entity
     * @return HTTP status code
     */
    @Override
    public OperationResult<Result> remove(Source entity) {
        log.info("Deleting Source id:" + entity.getId());
        G4Response response = g4HttpClient.sendRequest(request.delete(entity.getId()));

        OperationResult<Result> operationResult = new OperationResult<>(response, Result.class);
        if (operationResult.isSuccess()) {
            Entities.getSources().removeEntity(entity);
        }
        return operationResult;
    }

    @Override
    public OperationResult<List<Source>> search(SearchFilter filter) {
        throw new NotImplementedException("");
    }

    /**
     * GET list of Sources. Filter {"deleted":false}
     * API: GET /api/admin/sources getSources
     *
     * @return HTTP status code
     */
    @Override
    public OperationResult<List<Source>> list() {
        log.info("GET list of Sources. Filter {\"deleted\":false}");
        G4Response response = g4HttpClient.sendRequest(request.list());

        OperationResult<Source[]> operationResult = new OperationResult<>(response, Source[].class);
        if (operationResult.isSuccess() && operationResult.getEntity() != null) {
            // filter {"deleted":false}
            List<Source> sources = Arrays.asList(operationResult.getEntity());
            sources.removeIf(Source::isDeleted);

            return new OperationResult<>(response, sources);
        } else {
            throw new OperationResultError(operationResult);
        }
    }

    /**
     * API: POST /api/admin/sources
     *
     * @param entity Source entity
     * @return HTTP status code
     */
    @Override
    public OperationResult<Source> update(Source entity) {
        log.info("Updating Source id: " + entity.getId());
        entity.setVersion(entity.getVersion() == null ? 1 : entity.getVersion() + 1);

        log.info(JsonConverter.toJsonString(entity));
        G4Response response = g4HttpClient.sendRequest(request.update(entity));

        OperationResult<Source> operationResult = new OperationResult<>(response);
        if (operationResult.isSuccess()) {
            Entities.getSources().addOrUpdateEntity(entity);
            return new OperationResult<>(response, entity);
        } else {
            log.error("Error! Update source was failed");
            return operationResult;
        }
    }

    /**
     * API: GET /api/admin/sources/{id}
     *
     * @param id id of entity
     * @return Source entity
     */
    @Override
    public OperationResult<Source> view(String id) {
        log.info("View Source details, id:" + id);
        G4Response response = g4HttpClient.sendRequest(request.get(id));

        return new OperationResult<>(response, Source.class);
    }
}
