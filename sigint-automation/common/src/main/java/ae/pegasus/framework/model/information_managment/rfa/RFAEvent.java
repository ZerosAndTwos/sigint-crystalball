package ae.pegasus.framework.model.information_managment.rfa;

import ae.pegasus.framework.model.AbstractEntity;
import ae.pegasus.framework.model.Assignments;
import ae.pegasus.framework.model.Entities;
import ae.pegasus.framework.model.Targets;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@com.fasterxml.jackson.annotation.JsonIgnoreProperties(ignoreUnknown = true)

public class RFAEvent extends AbstractEntity {
    @JsonProperty("@type")
    private String type;
    @JsonProperty("id")
    private String id;
    @JsonProperty("sources")
    private String sources;
    @JsonProperty("eventFeed")
    private String eventFeed;
    @JsonProperty("sourceType")
    private String sourceType;
    @JsonProperty("recordType")
    private String recordType;
    @JsonProperty("subSourceType")
    private String subSourceType;
    @JsonProperty("modifiedOn")
    private Date modifiedOn;
    @JsonProperty("eventTime")
    private Date eventTime;
    @JsonProperty("endTime")
    private Date endTime;
    @JsonProperty("attributes")
    private Map<String, Object> attributes = new LinkedHashMap<>();
    @JsonProperty("events")
    private String events;
    @JsonProperty("entities")
    private Entities entities;
    @JsonProperty("assignments")
    private Assignments assignments;
    @JsonProperty("geo")
    private List<Object> geo = null;
    @JsonProperty("reports")
    private Object reports;
    @JsonProperty("targets")
    private Targets targets;
    @JsonProperty("entitiesGrouped")
    private List<Object> entitiesGrouped = null;
    @JsonProperty("objectType")
    private String objectType;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getSources() {
        return sources;
    }

    public void setSources(String sources) {
        this.sources = sources;
    }

    public String getEventFeed() {
        return eventFeed;
    }

    public void setEventFeed(String eventFeed) {
        this.eventFeed = eventFeed;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public String getRecordType() {
        return recordType;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }

    public String getSubSourceType() {
        return subSourceType;
    }

    public void setSubSourceType(String subSourceType) {
        this.subSourceType = subSourceType;
    }

    public Date getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(Date modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    public Date getEventTime() {
        return eventTime;
    }

    public void setEventTime(Date eventTime) {
        this.eventTime = eventTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public String getEvents() {
        return events;
    }

    public void setEvents(String events) {
        this.events = events;
    }

    public Entities getEntities() {
        return entities;
    }

    public void setEntities(Entities entities) {
        this.entities = entities;
    }

    public Assignments getAssignments() {
        return assignments;
    }

    public void setAssignments(Assignments assignments) {
        this.assignments = assignments;
    }

    public List<Object> getGeo() {
        return geo;
    }

    public void setGeo(List<Object> geo) {
        this.geo = geo;
    }

    public Object getReports() {
        return reports;
    }

    public void setReports(Object reports) {
        this.reports = reports;
    }

    public Targets getTargets() {
        return targets;
    }

    public void setTargets(Targets targets) {
        this.targets = targets;
    }

    public List<Object> getEntitiesGrouped() {
        return entitiesGrouped;
    }

    public void setEntitiesGrouped(List<Object> entitiesGrouped) {
        this.entitiesGrouped = entitiesGrouped;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public Map<String, Object> getAdditionalProperties() {
        return additionalProperties;
    }

    public void setAdditionalProperties(Map<String, Object> additionalProperties) {
        this.additionalProperties = additionalProperties;
    }
}
