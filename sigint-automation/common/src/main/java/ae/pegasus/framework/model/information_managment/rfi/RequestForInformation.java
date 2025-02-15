package ae.pegasus.framework.model.information_managment.rfi;

import ae.pegasus.framework.model.G4Entity;
import ae.pegasus.framework.model.RequiredPermission;
import ae.pegasus.framework.model.information_managment.*;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

@JsonPropertyOrder({
        "priority",
        "type",
        "timeToResponse",
        "nextOwners",
        "orgUnits",
        "manualNo",
        "objectType",
        "identifiers",
        "internalRequestNo",
        "classification",
        "directFile",
        "subject",
        "required",
        "links"
})

public class RequestForInformation extends G4Entity {

    @JsonProperty("priority")
    private String priority;
    @JsonProperty("type")
    private String type;
    @JsonProperty("timeToResponse")
    private Integer timeToResponse;
    @JsonProperty("nextOwners")
    private List<NextOwners> nextOwners = null;
    @JsonProperty("orgUnits")
    private List<OrgUnit> orgUnits = null;
    @JsonProperty("manualNo")
    private String manualNo;
    @JsonProperty("objectType")
    private String objectType;
    @JsonProperty("identifiers")
    private List<Object> identifiers = null;
    @JsonProperty("internalRequestNo")
    private String internalRequestNo;
    @JsonProperty("classification")
    private String classification;
    @JsonProperty("directFile")
    private DirectCaseFile directFile;
    @JsonProperty("subject")
    private String subject;
    @JsonProperty("required")
    private String required;
    @JsonProperty("links")
    private List<Object> links = null;
    @JsonProperty("allOwners")
    private List<AllOwner> allOwners = null;
    @JsonProperty("requiredPermissions")
    private List<RequiredPermission> requiredPermissions = null;
    @JsonProperty("currentOwners")
    private List<CurrentOwner> currentOwners = null;
    @JsonProperty("modelSources")
    private List<ModelSource> modelSources = null;
    @JsonProperty("createdByName")
    private String createdByName;
    @JsonProperty("createdById")
    private String createdById;
    @JsonProperty("version")
    private Integer version;
    @JsonProperty("wfId")
    private String wfId;
    @JsonProperty("stateId")
    private String stateId;
    @JsonProperty("stateType")
    private String stateType;
    @JsonProperty("state")
    private String state;
    @JsonProperty("comment")
    private String comment;
    @JsonProperty("requestingParty")
    private String requestingParty;
    @JsonProperty("externalRequestNo")
    private String externalRequestNo;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("priority")
    public String getPriority() {
        return priority;
    }

    @JsonProperty("priority")
    public void setPriority(String priority) {
        this.priority = priority;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("timeToResponse")
    public Integer getTimeToResponse() {
        return timeToResponse;
    }

    @JsonProperty("timeToResponse")
    public void setTimeToResponse(Integer timeToResponse) {
        this.timeToResponse = timeToResponse;
    }

    @JsonProperty("nextOwners")
    public List<NextOwners> getNextOwners() {
        return nextOwners;
    }

    @JsonProperty("nextOwners")
    public void setNextOwners(List<NextOwners> nextOwners) {
        this.nextOwners = nextOwners;
    }

    @JsonProperty("orgUnits")
    public List<OrgUnit> getOrgUnits() {
        return orgUnits;
    }

    @JsonProperty("orgUnits")
    public void setOrgUnits(List<OrgUnit> orgUnits) {
        this.orgUnits = orgUnits;
    }

    @JsonProperty("manualNo")
    public String getManualNo() {
        return manualNo;
    }

    @JsonProperty("manualNo")
    public void setManualNo(String manualNo) {
        this.manualNo = manualNo;
    }

    @JsonProperty("objectType")
    public String getObjectType() {
        return objectType;
    }

    @JsonProperty("objectType")
    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    @JsonProperty("identifiers")
    public List<Object> getIdentifiers() {
        return identifiers;
    }

    @JsonProperty("identifiers")
    public void setIdentifiers(List<Object> identifiers) {
        this.identifiers = identifiers;
    }

    @JsonProperty("internalRequestNo")
    public String getInternalRequestNo() {
        return internalRequestNo;
    }

    @JsonProperty("internalRequestNo")
    public void setInternalRequestNo(String internalRequestNo) {
        this.internalRequestNo = internalRequestNo;
    }

    @JsonProperty("classification")
    public String getClassification() {
        return classification;
    }

    @JsonProperty("classification")
    public void setClassification(String classification) {
        this.classification = classification;
    }

    @JsonProperty("directFile")
    public DirectCaseFile getDirectFile() {
        return directFile;
    }

    @JsonProperty("directFile")
    public void setDirectFile(DirectCaseFile directFile) {
        this.directFile = directFile;
    }

    @JsonProperty("subject")
    public String getSubject() {
        return subject;
    }

    @JsonProperty("subject")
    public void setSubject(String subject) {
        this.subject = subject;
    }

    @JsonProperty("required")
    public String getRequired() {
        return required;
    }

    @JsonProperty("required")
    public void setRequired(String required) {
        this.required = required;
    }

    @JsonProperty("links")
    public List<Object> getLinks() {
        return links;
    }

    @JsonProperty("links")
    public void setLinks(List<Object> links) {
        this.links = links;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public List<AllOwner> getAllOwners() {
        return allOwners;
    }

    public void setAllOwners(List<AllOwner> allOwners) {
        this.allOwners = allOwners;
    }

    public List<RequiredPermission> getRequiredPermissions() {
        return requiredPermissions;
    }

    public void setRequiredPermissions(List<RequiredPermission> requiredPermissions) {
        this.requiredPermissions = requiredPermissions;
    }

    public List<CurrentOwner> getCurrentOwners() {
        return currentOwners;
    }

    public void setCurrentOwners(List<CurrentOwner> currentOwners) {
        this.currentOwners = currentOwners;
    }

    public List<ModelSource> getModelSources() {
        return modelSources;
    }

    public void setModelSources(List<ModelSource> modelSources) {
        this.modelSources = modelSources;
    }

    public void setAdditionalProperties(Map<String, Object> additionalProperties) {
        this.additionalProperties = additionalProperties;
    }

    public String getCreatedByName() {
        return createdByName;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
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

    public String getWfId() {
        return wfId;
    }

    public void setWfId(String wfId) {
        this.wfId = wfId;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getRequestingParty() {
        return requestingParty;
    }

    public void setRequestingParty(String requestingParty) {
        this.requestingParty = requestingParty;
    }

    public String getExternalRequestNo() {
        return externalRequestNo;
    }

    public void setExternalRequestNo(String externalRequestNo) {
        this.externalRequestNo = externalRequestNo;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}
