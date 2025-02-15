package ae.pegasus.framework.services;

import ae.pegasus.framework.http.G4Response;
import ae.pegasus.framework.http.requests.RoleRequest;
import ae.pegasus.framework.model.RequestResult;
import ae.pegasus.framework.model.SearchFilter;
import ae.pegasus.framework.model.entities.Entities;
import ae.pegasus.framework.http.OperationResult;
import ae.pegasus.framework.json.JsonConverter;
import ae.pegasus.framework.model.Role;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;

import java.util.List;

public class RoleService implements EntityService<Role> {

    private static final Logger log = Logger.getLogger(RoleService.class);
    private static final RoleRequest request = new RoleRequest();

    /**
     * Add new Role
     * <p>
     * API: POST /api/auth/roles
     *
     * @param entity Role entity for payload
     * @return HTTP status code
     */
    @Override
    public OperationResult<Role> add(Role entity) {
        log.info("Creating new Role, name:" + entity.getName());

        G4Response response = g4HttpClient.sendRequest(request.add(entity));

        OperationResult<Role> roleOperationResult = new OperationResult<>(response, Role.class);
        if (roleOperationResult.isSuccess()) {
            Entities.getRoles().addOrUpdateEntity(roleOperationResult.getEntity());
        }
        return roleOperationResult;
    }

    /**
     * Remove role
     *
     * @param entity role entity for deleting
     * @return {@link OperationResult}
     */
    @Override
    public OperationResult<RequestResult> remove(Role entity) {
        log.info("Deleting Role, name:" + entity.getName());

        G4Response response = g4HttpClient.sendRequest(request.delete(entity.getName()));

        OperationResult<RequestResult> operationResult = new OperationResult<>(response, RequestResult.class);
        if (operationResult.isSuccess()) {
            Entities.getRoles().removeEntity(entity);
        }
        return operationResult;
    }

    @Override
    public OperationResult<List<Role>> search(SearchFilter filter) {
        throw new NotImplementedException("");
    }

    @Override
    public OperationResult<List<Role>> list() {
        G4Response response = g4HttpClient.sendRequest(request.list());

        List<Role> roles = JsonConverter.jsonToObjectsList(response.getMessage(), Role[].class);
        return new OperationResult<>(response, roles);
    }

    @Override
    public OperationResult<Role> update(Role entity) {
        log.info("Updating Role, name: " + entity.getName());

        G4Response response = g4HttpClient.sendRequest(request.update(entity));

        OperationResult<Role> operationResult = new OperationResult<>(response, Role.class);
        if (operationResult.isSuccess()) {
            Entities.getRoles().addOrUpdateEntity(operationResult.getEntity());
        } else {
            log.error("Error! Update role was failed");
        }
        return operationResult;
    }

    @Override
    public OperationResult<Role> view(String id) {
        throw new NotImplementedException("");
    }
}
