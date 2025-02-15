package ae.pegasus.framework.zapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
public class Execution {

    private int id;
    private int orderId;
    private String executionStatus;
    private String comment;
    private String htmlComment;
    private String cycleName;
    private String versionId;
    private String cycleId;
    private String versionName;
    private String projectId;
    private String createdBy;
    private String modifiedBy;
    private String issueId;
    private String issueKey;
    private String summary;
    private String issueDescription;
    private String label;
    private String component;
    private String projectKey;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIssueId() {
        return issueId;
    }

    public Execution setIssueId(String issueId) {
        this.issueId = issueId;
        return this;
    }

    public String getVersionId() {
        return versionId;
    }

    public Execution setVersionId(String versionId) {
        this.versionId = versionId;
        return this;
    }

    public String getCycleId() {
        return cycleId;
    }

    public Execution setCycleId(String cycleId) {
        this.cycleId = cycleId;
        return this;
    }

    public String getProjectId() {
        return projectId;
    }

    public Execution setProjectId(String projectId) {
        this.projectId = projectId;
        return this;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCycleName() {
        return cycleName;
    }

    public void setCycleName(String cycleName) {
        this.cycleName = cycleName;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getExecutionStatus() {
        return executionStatus;
    }

    public void setExecutionStatus(String executionStatus) {
        this.executionStatus = executionStatus;
    }

    public String getHtmlComment() {
        return htmlComment;
    }

    public void setHtmlComment(String htmlComment) {
        this.htmlComment = htmlComment;
    }

    public String getIssueDescription() {
        return issueDescription;
    }

    public void setIssueDescription(String issueDescription) {
        this.issueDescription = issueDescription;
    }

    public String getIssueKey() {
        return issueKey;
    }

    public void setIssueKey(String issueKey) {
        this.issueKey = issueKey;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    @JsonProperty("issueSummary")
    public String getSummary() {
        return summary;
    }

    @JsonProperty("issueSummary")
    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getProjectKey() {
        return projectKey;
    }

    public void setProjectKey(String projectKey) {
        this.projectKey = projectKey;
    }

}
