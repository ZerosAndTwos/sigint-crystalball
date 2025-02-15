package http.requests.rfi;

import http.requests.HttpRequest;
import http.HttpMethod;

/**
 * Created by dm on 5/18/16.
 */
public class RFICancelRequest extends HttpRequest {

    private static final String URI = "/api/rfi/";


    public RFICancelRequest(String id) {
        super(URI + id + "/CANCELLED");
        this.setHttpMethod(HttpMethod.POST);
    }
}
