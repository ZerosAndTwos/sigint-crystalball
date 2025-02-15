package http.requests.phonebook;


import model.SearchFilter;
import http.requests.HttpRequest;
import http.HttpMethod;
import model.G4File;

import javax.ws.rs.core.MediaType;

public class DuSubscriberRequest extends HttpRequest {

    private final static String URI = "/api/profile/du-subscribers";

    public DuSubscriberRequest() {
        super(URI);
    }

    public DuSubscriberRequest search(SearchFilter filter) {
        this
                .setURI(URI + "/search")
                .setHttpMethod(HttpMethod.POST)
                .setPayload(filter);
        return this;
    }

    public DuSubscriberRequest upload(G4File file) {
        addBodyFile("file", file, MediaType.APPLICATION_JSON_TYPE);
        this
                .setURI(URI + "/upload")
                .setHttpMethod(HttpMethod.POST);
        return this;
    }

    public DuSubscriberRequest get(String id) {
        this.setURI(URI + "/entries/" + id);
        return this;
    }
}
