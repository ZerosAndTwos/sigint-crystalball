package ae.pegasus.framework.http.requests;

import ae.pegasus.framework.http.HttpMethod;
import ae.pegasus.framework.model.Title;

public class TitleRequest extends HttpRequest {

    public static final String URI = "/api/auth/titles";

    public TitleRequest() {
        super(URI);
    }

    public TitleRequest list() {
        this
                .setURI(URI)
                .setHttpMethod(HttpMethod.GET);
        return this;
    }

    public TitleRequest create(Title title) {
        this
                .setURI(URI)
                .setHttpMethod(HttpMethod.POST)
                .setPayload(title);
        return this;
    }

    public TitleRequest delete(String id) {
        this
                .setURI(URI + "/" + id)
                .setHttpMethod(HttpMethod.DELETE);
        return this;
    }

    public TitleRequest view(String id) {
        this
                .setURI(URI + "/" + id)
                .setHttpMethod(HttpMethod.GET);
        return this;
    }

    public TitleRequest update(Title title) {
        this
                .setURI(URI + "/" + title.getId())
                .setHttpMethod(HttpMethod.PUT)
                .setPayload(title);
        return this;
    }
}
