package ae.pegasus.framework.services;

import ae.pegasus.framework.http.OperationResult;
import ae.pegasus.framework.http.requests.PermissionRequest;
import ae.pegasus.framework.model.SearchFilter;
import ae.pegasus.framework.http.G4Response;
import ae.pegasus.framework.model.Permission;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.List;

public class PermissionService implements EntityService<Permission> {

    private static Logger log = Logger.getLogger(PermissionService.class);
    private static PermissionRequest request = new PermissionRequest();
    private static List<Permission> permissions;

    public static List<Permission> getPermissions() {
        if (permissions == null) {
            new PermissionService().list();
        }
        return permissions;
    }

    @Override
    public OperationResult<?> add(Permission entity) {
        throw new NotImplementedException("");
    }

    @Override
    public OperationResult remove(Permission entity) {
        throw new NotImplementedException("");
    }

    @Override
    public OperationResult<List<Permission>> search(SearchFilter filter) {
        throw new NotImplementedException("");
    }

    @Override
    public OperationResult<List<Permission>> list() {
        log.info("Get list of permission");
        G4Response response = g4HttpClient.sendRequest(request.list());

        OperationResult<Permission[]> operationResult = new OperationResult<>(response, Permission[].class);
        if (operationResult.isSuccess()) {
            List<Permission> permissions = Arrays.asList(operationResult.getEntity());
            PermissionService.permissions = permissions;
            return new OperationResult<>(response, permissions);
        } else {
            throw new AssertionError("Unable to get list of permissions!");
        }
    }

    @Override
    public OperationResult<Permission> update(Permission entity) {
        throw new NotImplementedException("");
    }

    @Override
    public OperationResult<Permission> view(String id) {
        throw new NotImplementedException("");
    }
}
