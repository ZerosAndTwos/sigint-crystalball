package ae.pegasus.framework.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
public class MatchingResult extends G4Entity {

    private SearchResultType searchResultType;
    private TargetResultType targetResultType;
    private Date searchTime;
    private int searchDuration;
    private String processId;
    private int numRecords;
    private int numVoiceRecords;
    private int numSmsRecords;
    private int totalVoiceDuration;
    private int workload;
    private String objectId;
    private String recordsFilter;
    private Target target;
    private TargetGroup targetGroup;
    private String recordIds;
    private String assignedUserId;
    private String searchResultIds;

    public SearchResultType getSearchResultType() {
        return searchResultType;
    }

    public void setSearchResultType(SearchResultType searchResultType) {
        this.searchResultType = searchResultType;
    }

    public TargetResultType getTargetResultType() {
        return targetResultType;
    }

    public void setTargetResultType(TargetResultType targetResultType) {
        this.targetResultType = targetResultType;
    }

    public Date getSearchTime() {
        return searchTime;
    }

    public void setSearchTime(Date searchTime) {
        this.searchTime = searchTime;
    }

    public int getSearchDuration() {
        return searchDuration;
    }

    public void setSearchDuration(int searchDuration) {
        this.searchDuration = searchDuration;
    }

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public int getNumRecords() {
        return numRecords;
    }

    public void setNumRecords(int numRecords) {
        this.numRecords = numRecords;
    }

    public int getNumVoiceRecords() {
        return numVoiceRecords;
    }

    public void setNumVoiceRecords(int numVoiceRecords) {
        this.numVoiceRecords = numVoiceRecords;
    }

    public int getNumSmsRecords() {
        return numSmsRecords;
    }

    public void setNumSmsRecords(int numSmsRecords) {
        this.numSmsRecords = numSmsRecords;
    }

    public int getTotalVoiceDuration() {
        return totalVoiceDuration;
    }

    public void setTotalVoiceDuration(int totalVoiceDuration) {
        this.totalVoiceDuration = totalVoiceDuration;
    }

    public int getWorkload() {
        return workload;
    }

    public void setWorkload(int workload) {
        this.workload = workload;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getRecordsFilter() {
        return recordsFilter;
    }

    public void setRecordsFilter(String recordsFilter) {
        this.recordsFilter = recordsFilter;
    }

    public Target getTarget() {
        return target;
    }

    public void setTarget(Target target) {
        this.target = target;
    }

    public TargetGroup getTargetGroup() {
        return targetGroup;
    }

    public void setTargetGroup(TargetGroup targetGroup) {
        this.targetGroup = targetGroup;
    }

    public String getRecordIds() {
        return recordIds;
    }

    public void setRecordIds(String recordIds) {
        this.recordIds = recordIds;
    }

    public String getAssignedUserId() {
        return assignedUserId;
    }

    public void setAssignedUserId(String assignedUserId) {
        this.assignedUserId = assignedUserId;
    }

    public String getSearchResultIds() {
        return searchResultIds;
    }

    public void setSearchResultIds(String searchResultIds) {
        this.searchResultIds = searchResultIds;
    }

   
}
