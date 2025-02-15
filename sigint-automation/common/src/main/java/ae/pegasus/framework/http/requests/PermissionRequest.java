package ae.pegasus.framework.http.requests;

import ae.pegasus.framework.http.HttpMethod;

public class PermissionRequest extends HttpRequest {

    private static final String URI = "/api/auth/permissions";

    public PermissionRequest() {
        super(URI);
    }

    public PermissionRequest list() {
        this
                .setURI(URI)
                .setHttpMethod(HttpMethod.GET);
        return this;
    }
}
