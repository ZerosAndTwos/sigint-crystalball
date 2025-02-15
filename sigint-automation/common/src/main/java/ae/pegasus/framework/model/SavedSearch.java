package ae.pegasus.framework.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SavedSearch extends AbstractEntity {

    private String name;
    private String description;
    private String queryString;
    private Boolean active;
    private List<FinderFile> containers = new ArrayList<>();
    private List<String> assignmentTeamIds = new ArrayList<>();
    private List<String> sourceTypeIds = new ArrayList<>();
    private String repeatRange;
    private Date startDate;
    private Integer assignmentPriority;
    private Date expirationDate;
    private String userId;
    private Date lastExecutionTime;
    private String objectType;
    private String type;
    private Date createdAt;
    private List<String> objectTypeIds = new ArrayList<>();
    private Integer numberOfResults;
    private SavedSearchMetadata metadata;
    private Date endDate;
    private Boolean historical;
    private Integer lastExecutionResultsFound;
    private List<ReqPermission> reqPermissions = new ArrayList<>();
    private String orgUnit;

    public List<String> getAssignmentTeamIds() {
        return assignmentTeamIds;
    }

    public void setAssignmentTeamIds(List<String> assignmentTeamIds) {
        this.assignmentTeamIds = assignmentTeamIds;
    }

    public List<String> getSourceTypeIds() {
        return sourceTypeIds;
    }

    public void setSourceTypeIds(List<String> sourceTypeIds) {
        this.sourceTypeIds = sourceTypeIds;
    }

    public String getRepeatRange() {
        return repeatRange;
    }

    public void setRepeatRange(String repeatRange) {
        this.repeatRange = repeatRange;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Integer getAssignmentPriority() {
        return assignmentPriority;
    }

    public void setAssignmentPriority(Integer assignmentPriority) {
        this.assignmentPriority = assignmentPriority;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getLastExecutionTime() {
        return lastExecutionTime;
    }

    public void setLastExecutionTime(Date lastExecutionTime) {
        this.lastExecutionTime = lastExecutionTime;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public List<String> getObjectTypeIds() {
        return objectTypeIds;
    }

    public void setObjectTypeIds(List<String> objectTypeIds) {
        this.objectTypeIds = objectTypeIds;
    }

    public Integer getNumberOfResults() {
        return numberOfResults;
    }

    public void setNumberOfResults(Integer numberOfResults) {
        this.numberOfResults = numberOfResults;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Boolean getHistorical() {
        return historical;
    }

    public void setHistorical(Boolean historical) {
        this.historical = historical;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getQueryString() {
        return queryString;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public SavedSearchMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(SavedSearchMetadata metadata) {
        this.metadata = metadata;
    }

    public Integer getLastExecutionResultsFound() {
        return lastExecutionResultsFound;
    }

    public void setLastExecutionResultsFound(Integer lastExecutionResultsFound) {
        this.lastExecutionResultsFound = lastExecutionResultsFound;
    }

    public List<FinderFile> getContainers() {
        return containers;
    }

    public void setContainers(List<FinderFile> containers) {
        this.containers = containers;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<ReqPermission> getReqPermissions() {
        return reqPermissions;
    }

    public void setReqPermissions(List<ReqPermission> reqPermissions) {
        this.reqPermissions = reqPermissions;
    }

    public String getOrgUnit() {
        return orgUnit;
    }

    public void setOrgUnit(String orgUnit) {
        this.orgUnit = orgUnit;
    }
}