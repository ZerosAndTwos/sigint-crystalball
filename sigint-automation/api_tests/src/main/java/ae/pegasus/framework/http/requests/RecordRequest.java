package ae.pegasus.framework.http.requests;

import ae.pegasus.framework.http.HttpMethod;
import ae.pegasus.framework.model.Record;
import ae.pegasus.framework.model.SearchFilter;

public class RecordRequest extends HttpRequest {

    private final static String URI = "/api/sigint/record";

    public RecordRequest() {
        super(URI);
    }

    /**
     * Build HTTP request for create new manual record.
     *
     * @param record new manual record
     * @return add new manual record request
     */
    public RecordRequest manual(Record record) {
        this
                .setURI(URI + "/manual")
                .setHttpMethod(HttpMethod.POST)
                .setPayload(record);
        return this;
    }

    /**
     * Build HTTP request for records search.
     *
     * API: /api/sigint/records/search?withTargets=true
     * HttpMethod: POST
     * MediaType = APPLICATION_JSON
     *
     * @param filter search filter
     * @return search request with search filter
     */
    public RecordRequest search(SearchFilter filter) {
        this
                .setURI("/api/sigint/records/search?withTargets=true")
                .setHttpMethod(HttpMethod.POST)
                .setPayload(filter);
        return this;
    }
}
