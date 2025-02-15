package http.requests.phonebook;


import http.requests.HttpRequest;
import http.HttpMethod;
import model.G4File;
import model.Phonebook;

import javax.ws.rs.core.MediaType;

public class PhonebookRequest extends HttpRequest {

    private final static String URI = "/api/profile/phonebook";

    public PhonebookRequest() {
        super(URI);
    }

    public PhonebookRequest delete(String id) {
        this
                .setURI(URI + "/entries/" + id)
                .setHttpMethod(HttpMethod.DELETE);
        return this;
    }

    public PhonebookRequest add(Phonebook phonebook) {
        this
                .setURI(URI + "/entries")
                .setHttpMethod(HttpMethod.POST)
                .setPayload(phonebook);
        return this;
    }

    public PhonebookRequest get(String id) {
        setURI(URI + "/entries/" + id);
        return this;
    }

    public PhonebookRequest update(Phonebook phonebook) {
        this
                .setURI(URI + "/entries/" + phonebook.getId())
                .setHttpMethod(HttpMethod.POST)
                .setPayload(phonebook);
        return this;
    }

    public PhonebookRequest upload(G4File file) {
        addBodyFile("file", file, MediaType.APPLICATION_JSON_TYPE);
        this
                .setURI(URI + "/upload")
                .setHttpMethod(HttpMethod.POST);
        return this;
    }

}
