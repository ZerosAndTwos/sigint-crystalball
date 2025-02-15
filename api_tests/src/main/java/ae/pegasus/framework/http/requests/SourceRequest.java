package ae.pegasus.framework.http.requests;

import ae.pegasus.framework.http.HttpMethod;
import ae.pegasus.framework.model.Source;

public class SourceRequest extends HttpRequest {

    private final static String URI = "/api/admin/sources/";

    public SourceRequest() {
        super(URI);
    }

    public SourceRequest add(Source source) {
        this
                .setURI(URI)
                .setHttpMethod(HttpMethod.POST)
                .setPayload(source);
        return this;
    }

    public SourceRequest get(String id) {
        this
                .setURI(URI + id)
                .setHttpMethod(HttpMethod.GET);
        return this;
    }

    public SourceRequest delete(String id) {
        this
                .setURI(URI + id)
                .setHttpMethod(HttpMethod.DELETE);
        return this;
    }

    public SourceRequest update(Source source) {
        this
                .setURI(URI + source.getId())
                .setHttpMethod(HttpMethod.PUT)
                .setPayload(source);
        return this;
    }

    public SourceRequest list() {
        this
            .setURI(URI)
            .setHttpMethod(HttpMethod.GET);
        return this;
    }
}
