package http.requests.phonebook;

import model.SearchFilter;
import http.requests.HttpRequest;
import http.HttpMethod;
import model.G4File;

import javax.ws.rs.core.MediaType;

public class EtisalatSubscriberRequest extends HttpRequest {

    private final static String URI = "/api/profile/etisalat-subscriber-data";

    public EtisalatSubscriberRequest() {
        super(URI);
    }

    public EtisalatSubscriberRequest search(SearchFilter filter) {
        this
                .setURI(URI + "/search")
                .setHttpMethod(HttpMethod.POST)
                .setPayload(filter);
        return this;
    }

    public EtisalatSubscriberRequest upload(G4File file) {
        addBodyFile("file", file, MediaType.APPLICATION_JSON_TYPE);
        this
                .setURI(URI + "/upload")
                .setHttpMethod(HttpMethod.POST);
        return this;
    }

    public EtisalatSubscriberRequest get(String id) {
        this.setURI(URI + "/entries/" + id);
        return this;
    }

}
