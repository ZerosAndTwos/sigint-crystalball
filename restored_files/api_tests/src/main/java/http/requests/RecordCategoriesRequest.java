package http.requests;

import http.HttpMethod;
import model.RecordCategory;

public class RecordCategoriesRequest extends HttpRequest {

    private static final String URI = "/api/sigint/record-categories/";

    /**
     * Build HTTP request.
     * By Default: HttpMethod - GET, MediaType - APPLICATION_JSON.
     */
    public RecordCategoriesRequest() {
        super(URI);
    }

    /**
     * Create a new one record-category request.
     * HttpMethod: PUT
     * API: /api/sigint/admin/record-categories/
     *
     * @param recordCategory new recordCategory entity for payload
     * @return RecordCategoriesRequest
     */
    public RecordCategoriesRequest add(RecordCategory recordCategory) {
        this
                .setURI(URI)
                .setHttpMethod(HttpMethod.PUT)
                .setPayload(recordCategory);
        return this;
    }

    /**
     * Get list of record-categories request.
     * HttpMethod: GET
     * API: /api/sigint/admin/record-categories/
     *
     * @return RecordCategoriesRequest
     */
    public RecordCategoriesRequest list() {
        this
                .setURI(URI)
                .setHttpMethod(HttpMethod.GET);
        return this;
    }

    /**
     * View details of record-category request.
     * HttpMethod: GET
     * API: /api/sigint/admin/record-categories/{id}
     *
     * @param id id of record-category
     * @return RecordCategoriesRequest
     */
    public RecordCategoriesRequest view(String id) {
        this
                .setURI(URI + id)
                .setHttpMethod(HttpMethod.GET);
        return this;
    }

    /**
     * Update of record-category request.
     * HttpMethod: POST
     * API: /api/sigint/admin/record-categories/{id}
     *
     * @param category updated record-category
     * @return RecordCategoriesRequest
     */
    public RecordCategoriesRequest update(RecordCategory category) {
        this
                .setURI(URI + category.getId())
                .setHttpMethod(HttpMethod.POST)
                .setPayload(category);
        return this;
    }
}
