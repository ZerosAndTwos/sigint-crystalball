package ae.pegasus.framework.http.requests;

import ae.pegasus.framework.http.HttpMethod;
import ae.pegasus.framework.model.information_managment.ReportCategory;

public class ReportCategoriesRequest extends HttpRequest {

    private static final String URI = "/api/reports/categories";
    private static final String URI_ADMIN = "/api/reports/categories/admin/";

    /**
     * Build HTTP request.
     * By Default GET MediaType.APPLICATION_JSON Request.
     */
    public ReportCategoriesRequest() {
        super(URI);
    }

    public ReportCategoriesRequest create(ReportCategory reportCategory) {
        this
                .setURI(URI_ADMIN)
                .setHttpMethod(HttpMethod.POST)
                .setPayload(reportCategory);
        return this;
    }

    public ReportCategoriesRequest delete(String id) {
        this
                .setURI(URI_ADMIN + id)
                .setHttpMethod(HttpMethod.DELETE);
        return this;
    }

    public ReportCategoriesRequest update(ReportCategory reportCategory) {
        this
                .setURI(URI_ADMIN + reportCategory.getId())
                .setHttpMethod(HttpMethod.PUT)
                .setPayload(reportCategory);
        return this;
    }

    public ReportCategoriesRequest view(String id) {
        this
            .setURI(URI_ADMIN + id)
            .setHttpMethod(HttpMethod.GET);
        return this;
    }

    public ReportCategoriesRequest list() {
        this
                .setURI(URI)
                .setHttpMethod(HttpMethod.GET);
        return this;
    }
}
