package ae.pegasus.framework.model;

public class SearchEntry {

    private String uuid;
    private String groupId;
    private SearchStatus status;
    private String query;
    private DataSourceCategory sourceType;
    private SearchObjectType objectType;
    private String results;
    private Integer resultCount;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public SearchStatus getStatus() {
        return status;
    }

    public void setStatus(SearchStatus status) {
        this.status = status;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public DataSourceCategory getSourceType() {
        return sourceType;
    }

    public void setSourceType(DataSourceCategory sourceType) {
        this.sourceType = sourceType;
    }

    public SearchObjectType getObjectType() {
        return objectType;
    }

    public void setObjectType(SearchObjectType objectType) {
        this.objectType = objectType;
    }

    public String getResults() {
        return results;
    }

    public void setResults(String results) {
        this.results = results;
    }

    public Integer getResultCount() {
        return resultCount;
    }

    public void setResultCount(Integer resultCount) {
        this.resultCount = resultCount;
    }
}
