
package ae.pegasus.framework.model.information_managment.report;

import ae.pegasus.framework.model.Category;
import ae.pegasus.framework.model.G4Entity;
import ae.pegasus.framework.model.RequiredPermission;
import ae.pegasus.framework.model.information_managment.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.ArrayList;
import java.util.List;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

@JsonPropertyOrder({
        "attachments",
        "wfId",
        "objectType",
        "state",
        "classification",
        "layoutType",
        "reportType",
        "reportNo",
        "createdByName",
        "events",
        "reportSources",
        "orgUnits",
        "subject",
        "description",
        "considerations",
        "recommendations",
        "notes",
        "categories",
        "layoutMetadata",
        "directCaseFiles",
        "links"
})
public class Report extends G4Entity {

    @JsonProperty("attachments")
    private List<Object> attachments = new ArrayList<>();
    @JsonProperty("wfId")
    private String wfId;
    @JsonProperty("objectType")
    private String objectType;
    @JsonProperty("state")
    private String state;
    @JsonProperty("classification")
    private String classification;
    @JsonProperty("layoutType")
    private String layoutType;
    @JsonProperty("reportType")
    private String reportType;
    @JsonProperty("reportNo")
    private String reportNo;
    @JsonProperty("createdByName")
    private String createdByName;
    @JsonProperty("events")
    private List<ReportEvent> events = new ArrayList<>();
    @JsonProperty("reportSources")
    private List<ReportSource> reportSources = new ArrayList<>();
    @JsonProperty("orgUnits")
    private List<OrgUnit> orgUnits = new ArrayList<>();
    @JsonProperty("subject")
    private String subject;
    @JsonProperty("description")
    private String description;
    @JsonProperty("considerations")
    private String considerations;
    @JsonProperty("recommendations")
    private String recommendations;
    @JsonProperty("notes")
    private String notes;
    @JsonProperty("categories")
    private List<Category> categories = new ArrayList<>();
    @JsonProperty("layoutMetadata")
    private String layoutMetadata;
    @JsonProperty("directCaseFiles")
    private List<DirectCaseFile> directCaseFiles = new ArrayList<>();
    @JsonProperty("links")
    private List<Object> links = new ArrayList<Object>();
    @JsonProperty("stateId")
    private String stateId;
    @JsonProperty("stateType")
    private String stateType;
    @JsonProperty("allOwners")
    private List<AllOwner> allOwners = null;
    @JsonProperty("currentOwners")
    private List<CurrentOwner> currentOwners = null;
    @JsonProperty("requiredPermissions")
    private List<RequiredPermission> requiredPermissions = null;
    @JsonProperty("modelSources")
    private List<ModelSource> modelSources = null;
    @JsonProperty("createdById")
    private String createdById;
    @JsonProperty("version")
    private Integer version;
    @JsonProperty("nextOwners")
    private List<NextOwners> nextOwners = null;
    @JsonProperty("comment")
    private String comment;

    @JsonProperty("attachments")
    public List<Object> getAttachments() {
        return attachments;
    }

    @JsonProperty("attachments")
    public void setAttachments(List<Object> attachments) {
        this.attachments = attachments;
    }

    @JsonProperty("wfId")
    public String getWfId() {
        return wfId;
    }

    @JsonProperty("wfId")
    public void setWfId(String wfId) {
        this.wfId = wfId;
    }

    @JsonProperty("objectType")
    public String getObjectType() {
        return objectType;
    }

    @JsonProperty("objectType")
    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }


    @JsonProperty("classification")
    public String getClassification() {
        return classification;
    }

    @JsonProperty("classification")
    public void setClassification(String classification) {
        this.classification = classification;
    }

    @JsonProperty("layoutType")
    public String getLayoutType() {
        return layoutType;
    }

    @JsonProperty("layoutType")
    public void setLayoutType(String layoutType) {
        this.layoutType = layoutType;
    }

    @JsonProperty("reportType")
    public String getReportType() {
        return reportType;
    }

    @JsonProperty("reportType")
    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    @JsonProperty("reportNo")
    public String getReportNo() {
        return reportNo;
    }

    @JsonProperty("reportNo")
    public void setReportNo(String reportNo) {
        this.reportNo = reportNo;
    }

    @JsonProperty("createdByName")
    public String getCreatedByName() {
        return createdByName;
    }

    @JsonProperty("createdByName")
    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    @JsonProperty("events")
    public List<ReportEvent> getReportEvents() {
        return events;
    }

    @JsonProperty("events")
    public void setReportEvents(List<ReportEvent> events) {
        this.events = events;
    }

    @JsonProperty("reportSources")
    public List<ReportSource> getReportSources() {
        return reportSources;
    }

    @JsonProperty("reportSources")
    public void setReportSources(List<ReportSource> reportSources) {
        this.reportSources = reportSources;
    }

    @JsonProperty("orgUnits")
    public List<OrgUnit> getOrgUnits() {
        return orgUnits;
    }

    @JsonProperty("orgUnits")
    public void setOrgUnits(List<OrgUnit> orgUnits) {
        this.orgUnits = orgUnits;
    }

    @JsonProperty("subject")
    public String getSubject() {
        return subject;
    }

    @JsonProperty("subject")
    public void setSubject(String subject) {
        this.subject = subject;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("considerations")
    public String getConsiderations() {
        return considerations;
    }

    @JsonProperty("considerations")
    public void setConsiderations(String considerations) {
        this.considerations = considerations;
    }

    @JsonProperty("recommendations")
    public String getRecommendations() {
        return recommendations;
    }

    @JsonProperty("recommendations")
    public void setRecommendations(String recommendations) {
        this.recommendations = recommendations;
    }

    @JsonProperty("notes")
    public String getNotes() {
        return notes;
    }

    @JsonProperty("notes")
    public void setNotes(String notes) {
        this.notes = notes;
    }

    @JsonProperty("categories")
    public List<Category> getCategories() {
        return categories;
    }

    @JsonProperty("categories")
    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    @JsonProperty("layoutMetadata")
    public String getLayoutMetadata() {
        return layoutMetadata;
    }

    @JsonProperty("layoutMetadata")
    public void setLayoutMetadata(String layoutMetadata) {
        this.layoutMetadata = layoutMetadata;
    }

    @JsonProperty("directCaseFiles")
    public List<DirectCaseFile> getDirectCaseFiles() {
        return directCaseFiles;
    }

    @JsonProperty("directCaseFiles")
    public void setDirectCaseFiles(List<DirectCaseFile> directCaseFiles) {
        this.directCaseFiles = directCaseFiles;
    }

    @JsonProperty("links")
    public List<Object> getLinks() {
        return links;
    }

    @JsonProperty("links")
    public void setLinks(List<Object> links) {
        this.links = links;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<ReportEvent> getEvents() {
        return events;
    }

    public void setEvents(List<ReportEvent> events) {
        this.events = events;
    }

    public String getStateId() {
        return stateId;
    }

    public void setStateId(String stateId) {
        this.stateId = stateId;
    }

    public String getStateType() {
        return stateType;
    }

    public void setStateType(String stateType) {
        this.stateType = stateType;
    }

    public List<AllOwner> getAllOwners() {
        return allOwners;
    }

    public void setAllOwners(List<AllOwner> allOwners) {
        this.allOwners = allOwners;
    }

    public List<CurrentOwner> getCurrentOwners() {
        return currentOwners;
    }

    public void setCurrentOwners(List<CurrentOwner> currentOwners) {
        this.currentOwners = currentOwners;
    }

    public List<RequiredPermission> getRequiredPermissions() {
        return requiredPermissions;
    }

    public void setRequiredPermissions(List<RequiredPermission> requiredPermissions) {
        this.requiredPermissions = requiredPermissions;
    }

    public List<ModelSource> getModelSources() {
        return modelSources;
    }

    public void setModelSources(List<ModelSource> modelSources) {
        this.modelSources = modelSources;
    }

    public String getCreatedById() {
        return createdById;
    }

    public void setCreatedById(String createdById) {
        this.createdById = createdById;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public List<NextOwners> getNextOwners() {
        return nextOwners;
    }

    public void setNextOwners(List<NextOwners> nextOwners) {
        this.nextOwners = nextOwners;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
