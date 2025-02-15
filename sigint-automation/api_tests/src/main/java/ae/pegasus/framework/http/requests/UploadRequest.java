package ae.pegasus.framework.http.requests;

import ae.pegasus.framework.http.HttpMethod;
import ae.pegasus.framework.model.SearchFilter;

public class UploadRequest extends HttpRequest {

    private final static String URI = "/api/sigint/upload/";

    public UploadRequest() {
        super(URI);
    }

    public UploadRequest search(SearchFilter filter) {
        this
                .setURI(URI + "/search")
                .setHttpMethod(HttpMethod.POST)
                .setPayload(filter);

        return this;
    }

    public UploadRequest details(String id) {
        setURI(URI + id + "/details")
                .setHttpMethod(HttpMethod.GET);

        return this;
    }
}
