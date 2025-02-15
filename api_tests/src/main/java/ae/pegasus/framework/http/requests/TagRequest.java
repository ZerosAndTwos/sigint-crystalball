package ae.pegasus.framework.http.requests;

import ae.pegasus.framework.http.HttpMethod;
import ae.pegasus.framework.model.SearchFilter;
import ae.pegasus.framework.model.Tag;

public class TagRequest extends HttpRequest {

    private static final String URI = "/api/admin/record-tags";

    /**
     * Build HTTP request.
     * By Default: HttpMethod = GET, MediaType = APPLICATION_JSON.
     */
    public TagRequest() {
        super(URI);
    }

    public TagRequest create(Tag entity) {
        this
                .setURI(URI)
                .setHttpMethod(HttpMethod.POST)
                .setPayload(entity);
        return this;
    }

    public TagRequest list() {
        this
                .setURI(URI)
                .setHttpMethod(HttpMethod.GET);
        return this;
    }

    public TagRequest search(SearchFilter filter) {
        this
                .setURI(URI + "/search")
                .setHttpMethod(HttpMethod.POST)
                .setPayload(filter);
        return this;
    }

    public TagRequest delete(String id) {
        this
                .setURI(URI + "/" + id)
                .setHttpMethod(HttpMethod.DELETE);
        return this;
    }
}
