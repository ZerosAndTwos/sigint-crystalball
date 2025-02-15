package ae.pegasus.framework.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
public class SavedSearchMetadata {

    private String query;
    private String type;
    private Object filters;
    private List<AdvancedSearch> advancedSearch;

    public Object getFilters() {
        return filters;
    }

    public void setFilters(Object filters) {
        this.filters = filters;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<AdvancedSearch> getAdvancedSearch() {
        return advancedSearch;
    }

    public void setAdvancedSearch(List<AdvancedSearch> advancedSearch) {
        this.advancedSearch = advancedSearch;
    }
}
