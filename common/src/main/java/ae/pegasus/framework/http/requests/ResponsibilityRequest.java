package ae.pegasus.framework.http.requests;

import ae.pegasus.framework.http.HttpMethod;
import ae.pegasus.framework.model.Responsibility;

public class ResponsibilityRequest extends HttpRequest {

    private static final String URI = "/api/auth/responsibilities";

    /**
     * Build HTTP request.
     * By Default: HttpMethod = GET, MediaType = APPLICATION_JSON.
     */
    public ResponsibilityRequest() {
        super(URI);
    }

    public ResponsibilityRequest list() {
        this
                .setURI(URI)
                .setHttpMethod(HttpMethod.GET);
        return this;
    }

    public ResponsibilityRequest create(Responsibility responsibility) {
        this
                .setURI(URI)
                .setHttpMethod(HttpMethod.POST)
                .setPayload(responsibility);
        return this;
    }

    public ResponsibilityRequest delete(String id) {
        this
                .setURI(URI + "/" + id)
                .setHttpMethod(HttpMethod.DELETE);
        return this;
    }

    public ResponsibilityRequest update(Responsibility responsibility) {
        this
                .setURI(URI + "/" + responsibility.getId())
                .setHttpMethod(HttpMethod.PUT)
                .setPayload(responsibility);
        return this;
    }

    public ResponsibilityRequest view(String id) {
        this
                .setURI(URI + "/" + id)
                .setHttpMethod(HttpMethod.GET);
        return this;
    }
}
