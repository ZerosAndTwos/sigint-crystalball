package ae.pegasus.framework.model.information_managment.rfa;

import ae.pegasus.framework.model.G4Entity;
import ae.pegasus.framework.model.information_managment.*;
import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "objectType",
        "internalRequestNo",
        "orgUnits",
        "classification",
        "directCaseFiles",
        "subject",
        "description",
        "links"
})
public class RequestForApprove extends G4Entity {

    @JsonProperty("objectType")
    private String objectType;
    @JsonProperty("internalRequestNo")
    private String internalRequestNo;
    @JsonProperty("orgUnits")
    private List<OrgUnit> orgUnits = null;
    @JsonProperty("classification")
    private String classification;
    @JsonProperty("directCaseFiles")
    private List<DirectCaseFile> directCaseFiles = null;
    @JsonProperty("subject")
    private String subject;
    @JsonProperty("description")
    private String description;
    @JsonProperty("links")
    private List<Link> links = null;
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
    @JsonProperty("nextOwners")
    private List<NextOwners> nextOwners = null;
    @JsonProperty("allOwners")
    private List<AllOwner> allOwners = null;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("objectType")
    public String getObjectType() {
        return objectType;
    }

    @JsonProperty("objectType")
    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    @JsonProperty("internalRequestNo")
    public String getInternalRequestNo() {
        return internalRequestNo;
    }

    @JsonProperty("internalRequestNo")
    public void setInternalRequestNo(String internalRequestNo) {
        this.internalRequestNo = internalRequestNo;
    }

    @JsonProperty("orgUnits")
    public List<OrgUnit> getOrgUnits() {
        return orgUnits;
    }

    @JsonProperty("orgUnits")
    public void setOrgUnits(List<OrgUnit> orgUnits) {
        this.orgUnits = orgUnits;
    }

    @JsonProperty("classification")
    public String getClassification() {
        return classification;
    }

    @JsonProperty("classification")
    public void setClassification(String classification) {
        this.classification = classification;
    }

    @JsonProperty("directCaseFiles")
    public List<DirectCaseFile> getDirectCaseFiles() {
        return directCaseFiles;
    }

    @JsonProperty("directCaseFiles")
    public void setDirectCaseFiles(List<DirectCaseFile> directCaseFiles) {
        this.directCaseFiles = directCaseFiles;
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

    @JsonProperty("links")
    public List<Link> getLinks() {
        return links;
    }

    @JsonProperty("links")
    public void setLinks(List<Link> links) {
        this.links = links;
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

    public List<NextOwners> getNextOwners() {
        return nextOwners;
    }

    public List<AllOwner> getAllOwners() {
        return allOwners;
    }

    public void setAllOwners(List<AllOwner> allOwners) {
        this.allOwners = allOwners;
    }

    public void setNextOwners(List<NextOwners> nextOwners) {
        this.nextOwners = nextOwners;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}

