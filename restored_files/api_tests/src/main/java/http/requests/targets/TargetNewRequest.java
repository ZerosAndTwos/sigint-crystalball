package http.requests.targets;

import http.HttpRequest;

public class TargetNewRequest extends HttpRequest {

    private final static String URI = "/api/sigint/targets";

    public TargetNewRequest() {
        super(URI);
    }
}
