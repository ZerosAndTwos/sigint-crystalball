package ae.pegasus.framework.http.requests;

import ae.pegasus.framework.http.HttpMethod;
import ae.pegasus.framework.model.CBSearchFilter;

public class SearchRequest extends HttpRequest {

    private final static String URI = "/api/search/";

    /**
     * Build HTTP request.
     * By Default: HttpMethod = GET, MediaType = APPLICATION_JSON.
     */
    public SearchRequest() {
        super(URI);
    }

    /**
     * CB Search
     * API: POST /api/search/search
     * payload: {"keywords":"67576","pageSize":20,"page":0}
     */
    public SearchRequest search(CBSearchFilter filter) {
        this
                .setURI(URI + "search")
                .setHttpMethod(HttpMethod.POST)
                .setPayload(filter);
        return this;
    }

    /**
     * POST /api/search/count
     *
     * @param filter Search filter.
     *               <br>Example: {"sourceType":["SIGINT"],"objectType":"event","query":"(445539262923) AND (includeSpam:\"false\")"}
     * @return {@link SearchRequest} instance
     */
    public SearchRequest count(CBSearchFilter filter) {
        this
                .setURI(URI + "count")
                .setHttpMethod(HttpMethod.POST)
                .setPayload(filter);
        return this;
    }
}
