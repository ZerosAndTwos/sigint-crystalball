package ae.pegasus.framework.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.List;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public abstract class SearchFilter<T extends AbstractEntity> {

    @JsonIgnore
    protected SearchFilter<T> activeFilter;

    protected String sortField;
    protected Boolean sortDirection = true; //asc=true, desc=false

    protected Integer page = 0;
    protected Integer pageSize = 1000; //TODO

    protected void setActiveFilter(SearchFilter<T> activeFilter) {
        this.activeFilter = activeFilter;
    }

    public abstract boolean isAppliedToEntity(T entity);


    public String getSortField() {
        return sortField;
    }

    public SearchFilter<T> setSortField(String sortField) {
        this.sortField = sortField;
        return this;
    }

    public Boolean isSortDirection() {
        return sortDirection;
    }

    public SearchFilter<T> setSortDirection(Boolean sortDirection) {
        this.sortDirection = sortDirection;
        return this;
    }

    public Integer getPage() {
        return page;
    }

    public SearchFilter<T> setPage(Integer page) {
        this.page = page;
        return this;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public SearchFilter<T> setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    boolean isAppliedToFilterList(List<String> entityFieldCollection, List<String> filterCollection) {
        if (filterCollection == null || filterCollection.isEmpty()) {
            return true;
        } else if (entityFieldCollection == null || entityFieldCollection.isEmpty()) {
            return false;
        } else {
            entityFieldCollection.retainAll(filterCollection);
            return !entityFieldCollection.isEmpty();
        }
    }
}
