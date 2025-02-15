package ae.pegasus.framework.http.requests;

import ae.pegasus.framework.http.HttpMethod;
import ae.pegasus.framework.model.PegasusMediaType;
import ae.pegasus.framework.model.Role;

public class RoleRequest extends HttpRequest {

    private static final String URI = "/api/auth/roles";

    public RoleRequest() {
        super(URI);
        this.setMediaType(PegasusMediaType.PEGASUS_JSON);
    }

    /**
     * CREATE new role request
     *
     * @param role role entity
     * @return CREATE role request
     */
    public RoleRequest add(Role role) {
        this
                .setHttpMethod(HttpMethod.POST)
                .setPayload(role);
        return this;
    }

    /**
     * DELETE role request by role name
     *
     * @param roleName role name
     * @return DELETE role request by role name
     */
    public RoleRequest delete(String roleName) {
        this
                .setURI(URI + "/" + roleName)
                .setHttpMethod(HttpMethod.DELETE);
        return this;
    }

    /**
     * GET list of roles request
     *
     * GET "/api/auth/roles"
     * @return GET list of roles request
     */
    public RoleRequest list() {
        this
                .setURI(URI)
                .setHttpMethod(HttpMethod.GET);
        return this;
    }

    /**
     * Update role request
     *
     * @param role updated role
     * @return Update role request
     */
    public RoleRequest update(Role role) {
        this
                .setURI(URI)
                .setHttpMethod(HttpMethod.PUT)
                .setPayload(role);
        return this;
    }

}
