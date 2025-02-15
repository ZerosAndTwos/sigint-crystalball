package http.requests.rfi;

import model.SearchFilter;
import http.requests.HttpRequest;
import http.HttpMethod;


public class RFISearchRequest extends HttpRequest {

    private static final String URI = "/api/rfi/search";

    public RFISearchRequest(SearchFilter filter) {
        super(URI);
        this
                .setHttpMethod(HttpMethod.POST)
                .setPayload(filter);
    }


}
