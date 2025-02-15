package ae.pegasus.framework.http.requests;

import ae.pegasus.framework.http.HttpMethod;

public class DictionaryRequest extends HttpRequest {

    private static final String URI = "/api/admin/dictionaries/";

    public DictionaryRequest() {
        super(URI);
    }

    public DictionaryRequest listAvailableSources() {
        this
                .setURI(URI + "sources")
                .setHttpMethod(HttpMethod.GET);
        return this;
    }

    public DictionaryRequest listAvailableManualSources() {
        this
                .setURI(URI + "sources?manual=true")
                .setHttpMethod(HttpMethod.GET);
        return this;
    }
}
