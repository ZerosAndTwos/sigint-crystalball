
package ae.pegasus.framework.model.information_managment.masterReport;

import ae.pegasus.framework.model.G4Entity;
import ae.pegasus.framework.model.information_managment.DirectCaseFile;
import ae.pegasus.framework.model.information_managment.NextOwners;
import ae.pegasus.framework.model.information_managment.OrgUnit;
import ae.pegasus.framework.model.information_managment.report.Report;
import com.fasterxml.jackson.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "state",
        "stateType",
        "type",
        "createdById",
        "createdByName",
        "createdAt",
        "wfId",
        "objectType",
        "nextOwners",
        "reportType",
        "STATE_TYPE",
        "DESCRIPTION",
        "approvals",
        "FULL_NAME",
        "layoutType",
        "links",
        "modifiedBy",
        "id",
        "categories",
        "reportNo",
        "events",
        "directCaseFiles",
        "orgUnitsToShare",
        "orgUnits",
        "stateId",
        "reportSources",
        "actionsLog",
        "CLASSIFICATION",
        "classification",
        "version",
        "currentOwners",
        "sourceType",
        "entities",
        "CREATED_ON",
        "modelSources",
        "reportIdentifiers"
})
public class MasterReport extends G4Entity {

    @JsonProperty("state")
    private String state;
    @JsonProperty("stateType")
    private String stateType;
    @JsonProperty("type")
    private String type;
    @JsonProperty("createdById")
    private String createdById;
    @JsonProperty("createdByName")
    private String createdByName;
    @JsonProperty("createdAt")
    private Date createdAt;
    @JsonProperty("wfId")
    private String wfId;
    @JsonProperty("objectType")
    private String objectType;
    @JsonProperty("nextOwners")
    private List<NextOwners> nextOwners = null;
    @JsonProperty("reportType")
    private String reportType;
    @JsonProperty("STATE_TYPE")
    private List<String> sTATETYPE = null;
    @JsonProperty("subject")
    private String subject = null;
    @JsonProperty("DESCRIPTION")
    private List<Object> dESCRIPTION = null;
    @JsonProperty("approvals")
    private List<Object> approvals = null;
    @JsonProperty("FULL_NAME")
    private List<String> fULLNAME = null;
    @JsonProperty("layoutType")
    private String layoutType;
    @JsonProperty("links")
    private List<Object> links = null;
    @JsonProperty("modifiedBy")
    private String modifiedBy;
    @JsonProperty("id")
    private String id;
    @JsonProperty("categories")
    private List<Object> categories = null;
    @JsonProperty("reportNo")
    private String reportNo;
    @JsonProperty("events")
    private List<Event> events = null;
    @JsonProperty("directCaseFiles")
    private List<DirectCaseFile> directCaseFiles = null;
    @JsonProperty("orgUnitsToShare")
    private List<OrgUnitsToShare> orgUnitsToShare = null;
    @JsonProperty("orgUnits")
    private List<OrgUnit> orgUnits = null;
    @JsonProperty("stateId")
    private String stateId;
    @JsonProperty("reportSources")
    private List<ReportSource> reportSources = null;
    @JsonProperty("actionsLog")
    private List<ActionsLog> actionsLog = null;
    @JsonProperty("CLASSIFICATION")
    private List<String> cLASSIFICATION = null;
    @JsonProperty("classification")
    private String classification;
    @JsonProperty("version")
    private Integer version;
    @JsonProperty("currentOwners")
    private List<Object> currentOwners = null;
    @JsonProperty("sourceType")
    private String sourceType;
    @JsonProperty("entities")
    private List<Object> entities = null;
    @JsonProperty("CREATED_ON")
    private List<String> cREATEDON = null;
    @JsonProperty("modelSources")
    private List<ModelSource> modelSources = null;
    @JsonProperty("reportIdentifiers")
    private List<Object> reportIdentifiers = null;
    @JsonProperty("records")
    private List<Report> records = null;
    @JsonProperty("originatingReports")
    private List<Map<String, Object>> originatingReports = null;
    @JsonProperty("comment")
    private String comment;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("state")
    public String getState() {
        return state;
    }

    @JsonProperty("state")
    public void setState(String state) {
        this.state = state;
    }

    public MasterReport withState(String state) {
        this.state = state;
        return this;
    }

    @JsonProperty("stateType")
    public String getStateType() {
        return stateType;
    }

    @JsonProperty("stateType")
    public void setStateType(String stateType) {
        this.stateType = stateType;
    }

    public MasterReport withStateType(String stateType) {
        this.stateType = stateType;
        return this;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    public MasterReport withType(String type) {
        this.type = type;
        return this;
    }

    @JsonProperty("createdById")
    public String getCreatedById() {
        return createdById;
    }

    @JsonProperty("createdById")
    public void setCreatedById(String createdById) {
        this.createdById = createdById;
    }

    public MasterReport withCreatedById(String createdById) {
        this.createdById = createdById;
        return this;
    }

    @JsonProperty("createdByName")
    public String getCreatedByName() {
        return createdByName;
    }

    @JsonProperty("createdByName")
    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public MasterReport withCreatedByName(String createdByName) {
        this.createdByName = createdByName;
        return this;
    }

    @JsonProperty("createdAt")
    public Date getCreatedAt() {
        return createdAt;
    }

    @JsonProperty("createdAt")
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public MasterReport withCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    @JsonProperty("wfId")
    public String getWfId() {
        return wfId;
    }

    @JsonProperty("wfId")
    public void setWfId(String wfId) {
        this.wfId = wfId;
    }

    public MasterReport withWfId(String wfId) {
        this.wfId = wfId;
        return this;
    }

    @JsonProperty("objectType")
    public String getObjectType() {
        return objectType;
    }

    @JsonProperty("objectType")
    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public MasterReport withObjectType(String objectType) {
        this.objectType = objectType;
        return this;
    }

    @JsonProperty("nextOwners")
    public List<NextOwners> getNextOwners() {
        return nextOwners;
    }

    @JsonProperty("nextOwners")
    public void setNextOwners(List<NextOwners> nextOwners) {
        this.nextOwners = nextOwners;
    }

    public MasterReport withNextOwners(List<NextOwners> nextOwners) {
        this.nextOwners = nextOwners;
        return this;
    }

    @JsonProperty("reportType")
    public String getReportType() {
        return reportType;
    }

    @JsonProperty("reportType")
    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public MasterReport withReportType(String reportType) {
        this.reportType = reportType;
        return this;
    }

    @JsonProperty("STATE_TYPE")
    public List<String> getSTATETYPE() {
        return sTATETYPE;
    }

    @JsonProperty("STATE_TYPE")
    public void setSTATETYPE(List<String> sTATETYPE) {
        this.sTATETYPE = sTATETYPE;
    }

    public MasterReport withSTATETYPE(List<String> sTATETYPE) {
        this.sTATETYPE = sTATETYPE;
        return this;
    }

    @JsonProperty("DESCRIPTION")
    public List<Object> getDESCRIPTION() {
        return dESCRIPTION;
    }

    @JsonProperty("DESCRIPTION")
    public void setDESCRIPTION(List<Object> dESCRIPTION) {
        this.dESCRIPTION = dESCRIPTION;
    }

    public MasterReport withDESCRIPTION(List<Object> dESCRIPTION) {
        this.dESCRIPTION = dESCRIPTION;
        return this;
    }

    @JsonProperty("approvals")
    public List<Object> getApprovals() {
        return approvals;
    }

    @JsonProperty("approvals")
    public void setApprovals(List<Object> approvals) {
        this.approvals = approvals;
    }

    public MasterReport withApprovals(List<Object> approvals) {
        this.approvals = approvals;
        return this;
    }

    @JsonProperty("FULL_NAME")
    public List<String> getFULLNAME() {
        return fULLNAME;
    }

    @JsonProperty("FULL_NAME")
    public void setFULLNAME(List<String> fULLNAME) {
        this.fULLNAME = fULLNAME;
    }

    public MasterReport withFULLNAME(List<String> fULLNAME) {
        this.fULLNAME = fULLNAME;
        return this;
    }

    @JsonProperty("layoutType")
    public String getLayoutType() {
        return layoutType;
    }

    @JsonProperty("layoutType")
    public void setLayoutType(String layoutType) {
        this.layoutType = layoutType;
    }

    public MasterReport withLayoutType(String layoutType) {
        this.layoutType = layoutType;
        return this;
    }

    @JsonProperty("links")
    public List<Object> getLinks() {
        return links;
    }

    @JsonProperty("links")
    public void setLinks(List<Object> links) {
        this.links = links;
    }

    public MasterReport withLinks(List<Object> links) {
        this.links = links;
        return this;
    }

    @JsonProperty("modifiedBy")
    public String getModifiedBy() {
        return modifiedBy;
    }

    @JsonProperty("modifiedBy")
    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public MasterReport withModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
        return this;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    public MasterReport withId(String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("categories")
    public List<Object> getCategories() {
        return categories;
    }

    @JsonProperty("categories")
    public void setCategories(List<Object> categories) {
        this.categories = categories;
    }

    public MasterReport withCategories(List<Object> categories) {
        this.categories = categories;
        return this;
    }

    @JsonProperty("reportNo")
    public String getReportNo() {
        return reportNo;
    }

    @JsonProperty("reportNo")
    public void setReportNo(String reportNo) {
        this.reportNo = reportNo;
    }

    public MasterReport withReportNo(String reportNo) {
        this.reportNo = reportNo;
        return this;
    }

    @JsonProperty("events")
    public List<Event> getEvents() {
        return events;
    }

    @JsonProperty("events")
    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public MasterReport withEvents(List<Event> events) {
        this.events = events;
        return this;
    }

    @JsonProperty("directCaseFiles")
    public List<DirectCaseFile> getDirectCaseFiles() {
        return directCaseFiles;
    }

    @JsonProperty("directCaseFiles")
    public void setDirectCaseFiles(List<DirectCaseFile> directCaseFiles) {
        this.directCaseFiles = directCaseFiles;
    }

    public MasterReport withDirectCaseFiles(List<DirectCaseFile> directCaseFiles) {
        this.directCaseFiles = directCaseFiles;
        return this;
    }

    @JsonProperty("orgUnitsToShare")
    public List<OrgUnitsToShare> getOrgUnitsToShare() {
        return orgUnitsToShare;
    }

    @JsonProperty("orgUnitsToShare")
    public void setOrgUnitsToShare(List<OrgUnitsToShare> orgUnitsToShare) {
        this.orgUnitsToShare = orgUnitsToShare;
    }

    public MasterReport withOrgUnitsToShare(List<OrgUnitsToShare> orgUnitsToShare) {
        this.orgUnitsToShare = orgUnitsToShare;
        return this;
    }

    @JsonProperty("orgUnits")
    public List<OrgUnit> getOrgUnits() {
        return orgUnits;
    }

    @JsonProperty("orgUnits")
    public void setOrgUnits(List<OrgUnit> orgUnits) {
        this.orgUnits = orgUnits;
    }

    public MasterReport withOrgUnits(List<OrgUnit> orgUnits) {
        this.orgUnits = orgUnits;
        return this;
    }

    @JsonProperty("stateId")
    public String getStateId() {
        return stateId;
    }

    @JsonProperty("stateId")
    public void setStateId(String stateId) {
        this.stateId = stateId;
    }

    public MasterReport withStateId(String stateId) {
        this.stateId = stateId;
        return this;
    }

    @JsonProperty("reportSources")
    public List<ReportSource> getReportSources() {
        return reportSources;
    }

    @JsonProperty("reportSources")
    public void setReportSources(List<ReportSource> reportSources) {
        this.reportSources = reportSources;
    }

    public MasterReport withReportSources(List<ReportSource> reportSources) {
        this.reportSources = reportSources;
        return this;
    }

    @JsonProperty("actionsLog")
    public List<ActionsLog> getActionsLog() {
        return actionsLog;
    }

    @JsonProperty("actionsLog")
    public void setActionsLog(List<ActionsLog> actionsLog) {
        this.actionsLog = actionsLog;
    }

    public MasterReport withActionsLog(List<ActionsLog> actionsLog) {
        this.actionsLog = actionsLog;
        return this;
    }

    @JsonProperty("CLASSIFICATION")
    public List<String> getCLASSIFICATION() {
        return cLASSIFICATION;
    }

    @JsonProperty("CLASSIFICATION")
    public void setCLASSIFICATION(List<String> cLASSIFICATION) {
        this.cLASSIFICATION = cLASSIFICATION;
    }

    public MasterReport withCLASSIFICATION(List<String> cLASSIFICATION) {
        this.cLASSIFICATION = cLASSIFICATION;
        return this;
    }

    @JsonProperty("classification")
    public String getClassification() {
        return classification;
    }

    @JsonProperty("classification")
    public void setClassification(String classification) {
        this.classification = classification;
    }

    public MasterReport withClassification(String classification) {
        this.classification = classification;
        return this;
    }

    @JsonProperty("version")
    public Integer getVersion() {
        return version;
    }

    @JsonProperty("version")
    public void setVersion(Integer version) {
        this.version = version;
    }

    public MasterReport withVersion(Integer version) {
        this.version = version;
        return this;
    }

    @JsonProperty("currentOwners")
    public List<Object> getCurrentOwners() {
        return currentOwners;
    }

    @JsonProperty("currentOwners")
    public void setCurrentOwners(List<Object> currentOwners) {
        this.currentOwners = currentOwners;
    }

    public MasterReport withCurrentOwners(List<Object> currentOwners) {
        this.currentOwners = currentOwners;
        return this;
    }

    @JsonProperty("sourceType")
    public String getSourceType() {
        return sourceType;
    }

    @JsonProperty("sourceType")
    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public MasterReport withSourceType(String sourceType) {
        this.sourceType = sourceType;
        return this;
    }

    @JsonProperty("entities")
    public List<Object> getEntities() {
        return entities;
    }

    @JsonProperty("entities")
    public void setEntities(List<Object> entities) {
        this.entities = entities;
    }

    public MasterReport withEntities(List<Object> entities) {
        this.entities = entities;
        return this;
    }

    @JsonProperty("CREATED_ON")
    public List<String> getCREATEDON() {
        return cREATEDON;
    }

    @JsonProperty("CREATED_ON")
    public void setCREATEDON(List<String> cREATEDON) {
        this.cREATEDON = cREATEDON;
    }

    public MasterReport withCREATEDON(List<String> cREATEDON) {
        this.cREATEDON = cREATEDON;
        return this;
    }

    @JsonProperty("modelSources")
    public List<ModelSource> getModelSources() {
        return modelSources;
    }

    @JsonProperty("modelSources")
    public void setModelSources(List<ModelSource> modelSources) {
        this.modelSources = modelSources;
    }

    public MasterReport withModelSources(List<ModelSource> modelSources) {
        this.modelSources = modelSources;
        return this;
    }

    @JsonProperty("reportIdentifiers")
    public List<Object> getReportIdentifiers() {
        return reportIdentifiers;
    }

    @JsonProperty("reportIdentifiers")
    public void setReportIdentifiers(List<Object> reportIdentifiers) {
        this.reportIdentifiers = reportIdentifiers;
    }

    public MasterReport withReportIdentifiers(List<Object> reportIdentifiers) {
        this.reportIdentifiers = reportIdentifiers;
        return this;
    }

    public List<Report> getRecords() {
        return records;
    }

    public void setRecords(List<Report> records) {
        this.records = records;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public List<Map<String, Object>> getOriginatingReports() {
        return originatingReports;
    }

    public void setOriginatingReports(List<Map<String, Object>> originatingReports) {
        this.originatingReports = originatingReports;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public MasterReport withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
