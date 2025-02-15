package ae.pegasus.framework.http.requests;

import ae.pegasus.framework.http.HttpMethod;
import ae.pegasus.framework.model.SearchFilter;
import ae.pegasus.framework.model.SortDirection;

public class SavedSearchRequest extends HttpRequest {

    private static final String URI = "/api/saved-search-api/saved-searches";

    public SavedSearchRequest() {
        super(URI);
    }

    public SavedSearchRequest search(SearchFilter filter) {
        String query = "?page=" + filter.getPage()
                + "&pageSize=" + filter.getPageSize()
                + "&sortKey=" + filter.getSortField()
                + "&sortOrder=" + (filter.isSortDirection() ? SortDirection.ASC.name() : SortDirection.DESC.name());
        this
                .setHttpMethod(HttpMethod.GET)
                .setURI(URI + query);
        return this;
    }
}
