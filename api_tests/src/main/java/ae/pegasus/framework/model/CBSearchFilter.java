package ae.pegasus.framework.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class CBSearchFilter {

    private List<DataSourceCategory> sourceType = new ArrayList<>();
    private SearchObjectType objectType = SearchObjectType.event;
    private String query = "";
    private Page page = new Page();

    public CBSearchFilter() {
    }

    public CBSearchFilter(String eventFeed, String objectType, String query, String pageSize, String pageNumber) {
        this.query = query;
        this.setSourceType(eventFeed);
        this.objectType = SearchObjectType.valueOf(objectType);

        // pagination
        if (pageNumber != null && !Objects.equals(pageNumber.toLowerCase(), "null")) {
            setPageNumber(Integer.valueOf(pageNumber));
            setPageSize((pageSize == null || Objects.equals(pageSize.toLowerCase(), "null")) ?
                    null : Integer.valueOf(pageSize));
        }
    }

    public CBSearchFilter(String source, String objectType, String query) {
        this.query = query;
        this.setSourceType(source);
        this.objectType = SearchObjectType.valueOf(objectType);
    }

    public CBSearchFilter(List<DataSourceCategory> sources, String objectType, String query) {
        this.query = query;
        this.setSourceType(sources);
        this.objectType = SearchObjectType.valueOf(objectType);
    }

    public SearchObjectType getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = SearchObjectType.valueOf(objectType);
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public List<DataSourceCategory> getSourceType() {
        return sourceType;
    }

    public void setSourceType(List<DataSourceCategory> sourceTypes) {
        this.sourceType = sourceTypes;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = Collections.singletonList(DataSourceCategory.valueOf(sourceType));
    }

    public void setPageSize(Integer pageSize) {
        this.page.setPageSize(pageSize);
    }

    public void setPageNumber(Integer pageNumber) {
        this.page.setPageNumber(pageNumber);
    }

}
