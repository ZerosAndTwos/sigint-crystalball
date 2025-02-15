package http.requests.rfi;

import http.requests.HttpRequest;

/**
 * Created by dm on 5/3/16.
 */
public class RFIDetailsRequest extends HttpRequest {

    private final static String URI = "/api/rfi/";

    public RFIDetailsRequest(String rfiID) {
        super(URI + rfiID);
    }
}
