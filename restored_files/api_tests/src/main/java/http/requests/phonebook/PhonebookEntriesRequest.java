package http.requests.phonebook;


import http.requests.HttpRequest;

public class PhonebookEntriesRequest extends HttpRequest {

    private final static String URI = "/api/profile/phonebook/entries";

    public PhonebookEntriesRequest() {
        super(URI);
    }
}
