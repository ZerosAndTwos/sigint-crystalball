package ae.pegasus.framework.http.requests;

import ae.pegasus.framework.http.HttpMethod;

public class GenerateReportNumberRequest extends HttpRequest {
    private final static String URI = "/api/reports/workflows/3/generate-number/";

    public GenerateReportNumberRequest() {
        super(URI);
    }

    public GenerateReportNumberRequest generateNumber() {
        this
                .setURI(URI)
                .setHttpMethod(HttpMethod.POST)
                .setPayload(null);
        return this;
    }
}
