package services;

import errors.OperationResultError;
import model.SearchFilter;
import model.entities.Entities;
import file_generator.FileGenerator;
import http.G4Response;
import json.JsonConverter;
import http.OperationResult;
import http.requests.targets.TargetRequest;
import model.*;
import org.apache.log4j.Logger;

import java.util.List;

public class TargetService implements EntityService<Target> {

    private static final Logger log = Logger.getLogger(TargetService.class);
    private static final TargetRequest request = new TargetRequest();
    /*
        TODO
        POST /targets/pageable getTargets
        POST /targets/{id}/groups updateTargetGroupsForTarget
     */

    /**
     * PUT /targets addTarget
     *
     * @param entity entity
     * @return HTTP status code
     */
    @Override
    public OperationResult<Target> add(Target entity) {
        log.info("Creating new target");
        G4Response response = g4HttpClient.sendRequest(request.add(entity));

        OperationResult<Target> operationResult = new OperationResult<>(response, Target.class, "id");
        if (operationResult.isSuccess()) {
            Entities.getTargets().addOrUpdateEntity(operationResult.getEntity());
        } else {
            log.error("Add new target process was failed");
            throw new OperationResultError(operationResult);
        }
        return operationResult;
    }

    /**
     * POST /targets/upload importTargets, multipartUpload
     *
     * @param targets list of Targets
     * @return HTTP status code
     */
    public OperationResult<UploadResult> upload(List<Target> targets) {
        log.info("Writing Targets to file");
        G4File file = new FileGenerator(Target.class).write(targets);

        log.info("Upload file " + file.getAbsolutePath() + " with " + targets.size() + " targets");
        G4Response response = g4HttpClient.sendRequest(request.upload(file));

        return new OperationResult<>(response, UploadResult.class, "result");
    }

    /**
     * DELETE /targets/{id} removeTarget
     *
     * @param entity entity
     * @return HTTP status code
     */
    @Override
    public OperationResult remove(Target entity) {
        log.info("Deleting target id:" + entity.getId() + " name:" + entity.getName());
        G4Response response = g4HttpClient.sendRequest(request.delete(entity.getId()));

        OperationResult operationResult = new OperationResult(response);
        if (operationResult.isSuccess()) {
            Entities.getTargets().removeEntity(entity);
        }
        return operationResult;
    }

    /**
     * Search targets by filter
     * POST /targets/search search
     *
     * @param filter search filter for payload
     * @return List of Targets
     */
    @SuppressWarnings("unchecked")
    @Override
    public OperationResult<List<Target>> search(SearchFilter filter) {
        log.info("Search targets by filter:" + JsonConverter.toJsonString(filter));
        G4Response response = g4HttpClient.sendRequest(request.search(filter));

        OperationResult<TargetSearchResult> operationResult = new OperationResult<>(response, TargetSearchResult.class, "result");

        if (operationResult.isSuccess()) {
            return new OperationResult<>(response, operationResult.getEntity().getResult());
        } else {
            log.error("Unable to read list of target from response");
            throw new OperationResultError(operationResult);
        }
    }

    /**
     * Get list of targets
     *
     * @return {@link OperationResult<List<Target>>}
     */
    @Override
    public OperationResult<List<Target>> list() {
        log.info("Get list of targets");
        G4Response response = g4HttpClient.sendRequest(request.list());

        List<Target> targets = JsonConverter.jsonToObjectsList(response.getMessage(), Target[].class, "result");
        log.info("received " + targets.size() + " targets");
        return new OperationResult<>(response, targets);
    }

    /**
     * POST /targets updateTarget
     *
     * @param entity entity
     * @return HTTP status code
     */
    @Override
    public OperationResult<Target> update(Target entity) {
        log.info("Updating target id: " + entity.getId());
        G4Response response = g4HttpClient.sendRequest(request.update(entity));

        OperationResult<Target> operationResult = new OperationResult<>(response, entity);
        if (operationResult.isSuccess()) {
            Entities.getTargets().addOrUpdateEntity(entity);
        } else {
            log.error("Error! Update target process was failed");
        }
        return operationResult;
    }

    /**
     * GET /targets/{id}/details getTargetDetails
     *
     * @param id id of entity
     * @return Target entity
     */
    @Override
    public OperationResult<Target> view(String id) {
        log.info("View target entry id:" + id);
        G4Response response = g4HttpClient.sendRequest(request.get(id));

        return new OperationResult<>(response, Target.class, "result");
    }

    /**
     * GET /targets/{id}/groups findTargetGroups
     *
     * @param id Target id
     * @return List of Target groups
     */
    public OperationResult<List<TargetGroup>> getTargetGroups(String id) {
        log.info("Get targetGroups of target id:" + id);
        G4Response response = g4HttpClient.sendRequest(request.findTargetGroups(id));

        List<TargetGroup> targetGroups = JsonConverter.jsonToObjectsList(response.getMessage(), TargetGroup[].class);
        return new OperationResult<>(response, targetGroups);
    }

}
