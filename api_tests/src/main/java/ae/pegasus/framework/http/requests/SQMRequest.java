package ae.pegasus.framework.http.requests;

import ae.pegasus.framework.model.DataSourceCategory;
import ae.pegasus.framework.model.SearchObjectType;
import ae.pegasus.framework.model.SearchQueueRequest;

import static ae.pegasus.framework.http.HttpMethod.GET;
import static ae.pegasus.framework.http.HttpMethod.POST;

public class SQMRequest extends HttpRequest {

    private final static String URI = "/api/search-queue-monitor-api/queue/search/";


    public SQMRequest() {
        super(URI);
    }

    public SQMRequest search(SearchQueueRequest searchQueueRequest) {
        this
                .setURI(URI)
                .setHttpMethod(POST)
                .setPayload(searchQueueRequest);
        return this;
    }

    public SQMRequest view(String id) {
        this
                .setURI(URI + id)
                .setHttpMethod(GET);
        return this;
    }

    public SQMRequest result(String id, DataSourceCategory sourceType, SearchObjectType objectType,
                             Integer pageNumber, Integer pageSize) {
        this
                .setURI(URI + id + "/result" +
                        "?objectType=" + objectType +
                        "&sourceType=" + sourceType +
                        "&page=" + pageNumber +
                        "&pageSize=" + pageSize)
                .setHttpMethod(GET);
        return this;
    }
}
