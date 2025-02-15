package ae.pegasus.framework.services;

import ae.pegasus.framework.http.G4Response;
import ae.pegasus.framework.http.OperationResult;
import ae.pegasus.framework.http.requests.GroupsRequest;
import ae.pegasus.framework.model.Group;
import ae.pegasus.framework.model.RequestResult;
import ae.pegasus.framework.model.SearchFilter;
import ae.pegasus.framework.model.entities.Entities;
import ae.pegasus.framework.json.JsonConverter;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;

import java.util.List;

@Deprecated
public class GroupService implements EntityService<Group> {

    private static final Logger log = Logger.getLogger(GroupService.class);
    private static final GroupsRequest request = new GroupsRequest();

    /**
     * ADD new Group
     * API: POST /api/auth/groups
     *
     * @param entity group
     * @return HTTP status code
     */
    @Override
    public OperationResult<Group> add(Group entity) {
        log.info("Creating new Group");

        G4Response response = g4HttpClient.sendRequest(request.add(entity));
        OperationResult<Group> operationResult = new OperationResult<>(response, Group.class);
        if (operationResult.isSuccess()) {
            Entities.getGroups().addOrUpdateEntity(operationResult.getEntity());
        }
        return operationResult;
    }

    @Override
    public OperationResult<RequestResult> remove(Group entity) {
        log.info("Deleting Group, id:" + entity.getId());

        G4Response response = g4HttpClient.sendRequest(request.delete(entity.getId()));

        OperationResult<RequestResult> operationResult = new OperationResult<>(response, RequestResult.class);
        if (operationResult.isSuccess()) {
            Entities.getGroups().removeEntity(entity);
        }
        return operationResult;
    }

    @Override
    public OperationResult<List<Group>> search(SearchFilter filter) {
        throw new NotImplementedException("");
    }

    @Override
    public OperationResult<List<Group>> list() {
        G4Response response = g4HttpClient.sendRequest(request.list());

        List<Group> groups = JsonConverter.jsonToObjectsList(response.getMessage(), Group[].class);
        return new OperationResult<>(response, groups);
    }

    /**
     * UPDATE new Group
     * API: PUT /api/auth/groups
     *
     * @param entity group
     * @return HTTP status code
     */
    @Override
    public OperationResult<Group> update(Group entity) {
        log.info("Updating Group id:" + entity.getId() + " display_name:" + entity.getDisplayName());

        G4Response response = g4HttpClient.sendRequest(request.update(entity));
        OperationResult<Group> operationResult = new OperationResult<>(response, Group.class);
        if (operationResult.isSuccess()) {
            Entities.getGroups().addOrUpdateEntity(entity);
        }
        return operationResult;
    }

    @Override
    public OperationResult<Group> view(String id) {
        throw new NotImplementedException("");
    }
}
