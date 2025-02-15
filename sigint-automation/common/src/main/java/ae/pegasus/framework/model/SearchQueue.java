package ae.pegasus.framework.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class SearchQueue extends AbstractEntity {

    private String uuid;
    private Boolean allowPartialResults;
    private List<String> sourceType = new ArrayList<>(
            Stream.of("PROFILER", "INFORMATION_MANAGEMENT", "SIGINT", "GOVINT2", "GOVINT", "FININT", "OSINT", "CIO")
                    .collect(Collectors.toList()));
    private Page page = new Page();
    private String query;
    private List<String> authorization; // "File:82bd5800-5cd0-11e8-b4ad-fed1428907c1:alexd group"
    private SearchType searchType = SearchType.BASIC;
    private String highlight; // "0:[mark]:[/mark]"
    private List<String> objectType = new ArrayList<>();
    private String metadata;
    private String owner;
    private String ownerToken;
    private Boolean star;
    private List<DataSourceCategory> sourceTypes;
    private List<SearchObjectType> objectTypes;
    private Integer totalResults;
    private List<String> sorts;
    private String pageParameters; // "0:100"
    private SearchStatus status;
    private Date creationDate;
    private Date startDate;
    private Date completionDate;
    private List<SearchEntry> searchEntries;
    private String justification;
    private Boolean waitingRemoval;

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getOwnerToken() {
        return ownerToken;
    }

    public void setOwnerToken(String ownerToken) {
        this.ownerToken = ownerToken;
    }

    public Boolean getStar() {
        return star;
    }

    public void setStar(Boolean star) {
        this.star = star;
    }

    public List<DataSourceCategory> getSourceTypes() {
        return sourceTypes;
    }

    public void setSourceTypes(List<DataSourceCategory> sourceTypes) {
        this.sourceTypes = sourceTypes;
    }

    public List<SearchObjectType> getObjectTypes() {
        return objectTypes;
    }

    public void setObjectTypes(List<SearchObjectType> objectTypes) {
        this.objectTypes = objectTypes;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public List<String> getSorts() {
        return sorts;
    }

    public void setSorts(List<String> sorts) {
        this.sorts = sorts;
    }

    public String getPageParameters() {
        return pageParameters;
    }

    public void setPageParameters(String pageParameters) {
        this.pageParameters = pageParameters;
    }

    public SearchStatus getStatus() {
        return status;
    }

    public void setStatus(SearchStatus status) {
        this.status = status;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(Date completionDate) {
        this.completionDate = completionDate;
    }

    public List<SearchEntry> getSearchEntries() {
        return searchEntries;
    }

    public void setSearchEntries(List<SearchEntry> searchEntries) {
        this.searchEntries = searchEntries;
    }

    public String getJustification() {
        return justification;
    }

    public void setJustification(String justification) {
        this.justification = justification;
    }

    public Boolean getWaitingRemoval() {
        return waitingRemoval;
    }

    public void setWaitingRemoval(Boolean waitingRemoval) {
        this.waitingRemoval = waitingRemoval;
    }

    public boolean getAllowPartialResults()
    {
        return allowPartialResults;
    }

    public void setAllowPartialResults(boolean allowPartialResults)
    {
        this.allowPartialResults = allowPartialResults;
    }

    public List<String> getSourceType ()
    {
        return sourceType;
    }

    public void setSourceType (List<String> sourceType)
    {
        this.sourceType = sourceType;
    }

    public Page getPage ()
    {
        return page;
    }

    public void setPage (Page page)
    {
        this.page = page;
    }

    public String getQuery ()
    {
        return query;
    }

    public void setQuery (String query)
    {
        this.query = query;
    }

    public SearchType getSearchType ()
    {
        return searchType;
    }

    public void setSearchType (SearchType searchType)
    {
        this.searchType = searchType;
    }

    public List<String> getObjectType ()
    {
        return objectType;
    }

    public void setObjectType (List<String> objectType)
    {
        this.objectType = objectType;
    }

    public String getMetadata ()
    {
        return metadata;
    }

    public void setMetadata (String metadata)
    {
        this.metadata = metadata;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getHighlight() {
        return highlight;
    }

    public void setHighlight(String highlight) {
        this.highlight = highlight;
    }

    public List<String> getAuthorization() {
        return authorization;
    }

    public void setAuthorization(List<String> authorization) {
        this.authorization = authorization;
    }

}
			
			