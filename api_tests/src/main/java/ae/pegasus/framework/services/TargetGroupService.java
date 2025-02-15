package ae.pegasus.framework.services;

import ae.pegasus.framework.errors.OperationResultError;
import ae.pegasus.framework.http.G4Response;
import ae.pegasus.framework.http.OperationResult;
import ae.pegasus.framework.http.requests.TargetGroupRequest;
import ae.pegasus.framework.model.ProfileAndTargetGroup;
import ae.pegasus.framework.model.SearchFilter;
import ae.pegasus.framework.model.TargetGroup;
import ae.pegasus.framework.model.entities.Entities;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.List;

import static ae.pegasus.framework.json.JsonConverter.jsonToObjectsList;

public class TargetGroupService implements EntityService<TargetGroup> {

    private static final Logger log = Logger.getLogger(TargetGroupService.class);
    private static final TargetGroupRequest request = new TargetGroupRequest();

    @Override
    public OperationResult<TargetGroup> add(TargetGroup entity) {
        log.info("Creating new target group");

        G4Response response = g4HttpClient.sendRequest(request.add(entity));

        OperationResult<TargetGroup> operationResult = new OperationResult<>(response, TargetGroup.class, "data");
        if (operationResult.isSuccess()) {
            Entities.getTargetGroups().addOrUpdateEntity(operationResult.getEntity());
        }
        return operationResult;
    }

    @Override
    public OperationResult remove(TargetGroup entity) {
        log.info("Deleting target group id:" + entity.getId() + ", name:" + entity.getName());

        G4Response response = g4HttpClient.sendRequest(request.delete(entity.getId()));

        OperationResult<String> deleteResult = new OperationResult<>(response);
        if (deleteResult.isSuccess()) {
            Entities.getTargetGroups().removeEntity(entity);
        }
        return deleteResult;
    }

    @Override
    public OperationResult<List<TargetGroup>> search(SearchFilter filter) {
        throw new NotImplementedException("");
    }

    public OperationResult<List<TargetGroup>> getTopLevelGroups(SearchFilter filter) {
        log.info("Get top of list of target groups");
        G4Response response = g4HttpClient.sendRequest(request.list(filter));

        List<TargetGroup> targetGroupList =
                jsonToObjectsList(response.getMessage(), TargetGroup[].class, "data");

        return new OperationResult<>(response, targetGroupList);
    }

    @Override
    public OperationResult<List<TargetGroup>> list() {
        throw new NotImplementedException("");
    }

    @Override
    public OperationResult<TargetGroup> update(TargetGroup entity) {
        log.info("Updating target group");

        G4Response response = g4HttpClient.sendRequest(request.update(entity));

        OperationResult<TargetGroup> operationResult = new OperationResult<>(response, TargetGroup.class, "result");
        if (operationResult.isSuccess()) {
            Entities.getTargetGroups().addOrUpdateEntity(operationResult.getEntity());
        } else {
            log.error("Update targetGroup process was failed");
            throw new OperationResultError(operationResult);
        }
        return operationResult;
    }

    @Override
    public OperationResult<TargetGroup> view(String id) {
        log.info("View target group id:" + id);
        G4Response response = g4HttpClient.sendRequest(request.get(id));

        OperationResult<TargetGroup> operationResult = new OperationResult<>(response, TargetGroup.class, "data");
        if (operationResult.isSuccess()) {
            Entities.getTargetGroups().addOrUpdateEntity(operationResult.getEntity());
        }
        return operationResult;
    }


    /**
     * POST /targetGroups/{groupId}/targetGroups createChildGroup
     *
     * @param parentGroupId parent target group id
     * @param childGroup    new child target group
     * @return {@link OperationResult<TargetGroup>}
     */
    public OperationResult<TargetGroup> createChildGroup(String parentGroupId, TargetGroup childGroup) {
        log.info("Create child group, parentID: " + parentGroupId + ", name: " + childGroup.getName());
        G4Response response = g4HttpClient.sendRequest(request.createChildGroup(parentGroupId, childGroup));

        OperationResult<TargetGroup> operationResult =
                new OperationResult<>(response, TargetGroup.class, "data");

        if (operationResult.isSuccess()) {
            Entities.getTargetGroups().addOrUpdateEntity(operationResult.getEntity());
        }
        return operationResult;
    }

    /**
     * GET /targetGroups/{groupId}/contents
     *
     * @param groupID groupID
     * @return {@link OperationResult<>}
     */
    public OperationResult<ProfileAndTargetGroup[]> getContent(String groupID) {
        log.info("Get content of target group id: " + groupID);

        G4Response response = g4HttpClient.sendRequest(request.getContent(groupID));

        return new OperationResult<>(response, ProfileAndTargetGroup[].class, "data");
    }

    public OperationResult<List<ProfileAndTargetGroup>> searchTargetGroupMembers(SearchFilter filter) {
        log.info("Get list of targets and target_groups");
        G4Response response = g4HttpClient.sendRequest(request.search(filter));

        OperationResult<ProfileAndTargetGroup[]> operationResult =
                new OperationResult<>(response, ProfileAndTargetGroup[].class, "data");

        if (operationResult.isSuccess()) {
            return new OperationResult<>(response, Arrays.asList(operationResult.getEntity()));
        } else {
            return new OperationResult<>(response);
        }
    }
}
