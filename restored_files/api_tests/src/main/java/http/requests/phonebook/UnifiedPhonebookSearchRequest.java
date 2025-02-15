package http.requests.phonebook;

import model.SearchFilter;
import http.requests.HttpRequest;
import http.HttpMethod;

public class UnifiedPhonebookSearchRequest extends HttpRequest {

    private final static String URI = "/api/profile/unified-phonebook/search";

    public UnifiedPhonebookSearchRequest(SearchFilter filter) {
        super(URI);
        this
                .setHttpMethod(HttpMethod.POST)
                .setPayload(filter);
    }

}
