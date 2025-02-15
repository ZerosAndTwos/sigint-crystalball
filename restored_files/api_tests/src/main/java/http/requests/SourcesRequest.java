package http.requests;

import http.HttpMethod;

public class SourcesRequest extends HttpRequest {

    public static final String URI = "/api/sigint/sources";

    public SourcesRequest() {
        super(URI);
    }

    public SourcesRequest list() {
        this
                .setURI(URI)
                .setHttpMethod(HttpMethod.GET);
        return this;
    }
}
