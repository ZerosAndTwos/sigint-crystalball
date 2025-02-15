package ae.pegasus.framework.http.requests;

import ae.pegasus.framework.http.HttpMethod;
import ae.pegasus.framework.model.SearchFilter;

public class OrganizationRequest extends HttpRequest {

    private static final String URI = "/api/auth/organizations/";

    /**
     * Build HTTP request.
     * By Default: HttpMethod = GET, MediaType = APPLICATION_JSON.
     */
    public OrganizationRequest() {
        super(URI);
    }

    public OrganizationRequest search(SearchFilter filter) {
        this
                .setURI(URI + "search")
                .setHttpMethod(HttpMethod.POST)
                .setPayload(filter);
        return this;
    }
}
