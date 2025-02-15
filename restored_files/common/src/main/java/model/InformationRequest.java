package model;

import data_for_entity.annotations.*;
import data_for_entity.data_providers.rfi.*;
import data_for_entity.data_types.FieldDataType;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.text.SimpleDateFormat;
import java.util.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
@Deprecated
public class InformationRequest extends G4Entity {
    
    @DataIgnore
    private String internalRequestNumber;
    private String externalRequestNumber;
    @DataProvider(RFIPrioritiesProvider.class)
    private Integer priority;
    @DataIgnore
    private String assignedTo;
    private String subject;
    private String description;
    private String goals;
    @WithFieldDataType(FieldDataType.DATE)
    private Date createdDate;
    @DataIgnore
    private Date lastRespondTime;
    @WithDataDependencies(provider = RFIDueDateProvider.class, fields = {"priority"})
    private Date dueDate;
    private String requestSource;
    private String state;
    @DataProvider(RFISearchTypeProvider.class)
    private InformationRequestSearchType searchType;
    @DataProvider(RFIDistributionProvider.class)
    private ArrayList<InformationRequestDistribution> distributionList = new ArrayList<>();
    @DataProvider(RFITaskCategoryProvider.class)
    private ArrayList<InformationRequestTaskCategory> taskCategories = new ArrayList<>();
    @DataIgnore
    private ArrayList<String> previousRequests;
    @DataIgnore
    private ArrayList<String> targets;
    @DataIgnore
    private FileAttachment approvedCopy;
    @DataIgnore
    private FileAttachment originalDocument;
    
    @DataIgnore
    @JsonIgnore
    public static SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:MM:");

    @JsonIgnore
    public FileAttachment getApprovedCopy() {
        return approvedCopy;
    }

    @JsonProperty
    public void setApprovedCopy(FileAttachment approvedCopy) {
        this.approvedCopy = approvedCopy;
    }

    @JsonIgnore
    public FileAttachment getOriginalDocument() {
        return originalDocument;
    }

    @JsonProperty
    public void setOriginalDocument(FileAttachment originalDocument) {
        this.originalDocument = originalDocument;
    }

    public void setSearchType(InformationRequestSearchType searchType) {
        this.searchType = searchType;
    }

    public InformationRequestSearchType getSearchType() {
        return searchType;
    }

    private Calendar getCalendar(){
        return Calendar.getInstance(TimeZone.getTimeZone("UTC"));
    }

    public String getInternalRequestNumber() {
        return internalRequestNumber;
    }

    public void setInternalRequestNumber(String internalRequestNumber) {
        this.internalRequestNumber = internalRequestNumber;
    }

    public String getExternalRequestNumber() {
        return externalRequestNumber;
    }

    public void setExternalRequestNumber(String externalRequestNumber) {
        this.externalRequestNumber = externalRequestNumber;
    }

    public Integer getPriority() {
        return priority;
    }

    @JsonProperty("priority")
    public void setPriority(Integer priority) {
        this.priority = priority;

    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGoals() {
        return goals;
    }

    public void setGoals(String goals) {
        this.goals = goals;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastRespondTime() {
        return lastRespondTime;
    }

    public void setLastRespondTime(Date lastRespondTime) {
        this.lastRespondTime = lastRespondTime;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getRequestSource() {
        return requestSource;
    }

    public void setRequestSource(String requestSource) {
        this.requestSource = requestSource;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<InformationRequestDistribution> getDistributionList() {
        if (distributionList.size()== 0) {
            return null;
        }
        return distributionList;
    }

    public void setDistributionList(ArrayList<InformationRequestDistribution> distributionList) {
        this.distributionList = distributionList;
    }

    public List<InformationRequestTaskCategory> getTaskCategories() {
        if (taskCategories.size() == 0) {
            return null;
        }
        return taskCategories;
    }

    public void setTaskCategories(ArrayList<InformationRequestTaskCategory> taskCategories) {
        this.taskCategories = taskCategories;
    }

    public List<String> getPreviousRequests() {
        return previousRequests;
    }

    public void setPreviousRequests(ArrayList<String> previousRequests) {
        this.previousRequests = previousRequests;
    }

    public List<String> getTargets() {
        return targets;
    }

    public void setTargets(ArrayList<String> targets) {
        this.targets = targets;
    }

    
}
