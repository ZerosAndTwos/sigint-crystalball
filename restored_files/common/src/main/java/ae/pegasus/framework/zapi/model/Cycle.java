package ae.pegasus.framework.zapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.text.SimpleDateFormat;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Cycle {

    private String id;
    private String clonedCycleId;
    private String name;
    private String build;
    private String environment;
    private String description;
    private String startDate;
    private String endDate;
    private String projectId;
    private String versionId;
    private String responseMessage;

    public Cycle() {
    }

    public String getId() {
        return id;
    }

    public Cycle setId(String id) {
        this.id = id;
        return this;
    }

    public String getBuild() {
        return build;
    }

    public Cycle setBuild(String build) {
        this.build = build;
        return this;
    }

    public String getEnvironment() {
        return environment;
    }

    public Cycle setEnvironment(String environment) {
        this.environment = environment;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Cycle setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getName() {
        return name;
    }

    public Cycle setName(String name) {
        this.name = name;
        return this;
    }

    public String getProjectId() {
        return projectId;
    }

    public Cycle setProjectId(String projectId) {
        this.projectId = projectId;
        return this;
    }

    public String getStartDate() {
        return startDate;
    }

    @JsonProperty("startDate")
    public Cycle setStartDate(String startDate) {
        this.startDate = startDate;
        return this;
    }

    public Cycle setStartDate(Date startDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("d/MMM/yy");
        this.startDate = dateFormat.format(startDate);
        return this;
    }

    public String getEndDate() {
        return endDate;
    }

    @JsonProperty("endDate")
    public Cycle setEndDate(String endDate) {
        this.endDate = endDate;
        return this;
    }

    public Cycle setEndDate(Date endDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("d/MMM/yy");
        this.endDate = dateFormat.format(endDate);
        return this;
    }

    public String getVersionId() {
        return versionId;
    }

    public Cycle setVersionId(String versionId) {
        this.versionId = versionId;
        return this;
    }

    public String getClonedCycleId() {
        return clonedCycleId;
    }

    public Cycle setClonedCycleId(String clonedCycleId) {
        this.clonedCycleId = clonedCycleId;
        return this;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    @Override
    public String toString() {
        return "{ \"id\" : " + id +
                ", \"name\" : \"" + name +
                "\", \"build\" : \"" + build +
                "\", \"environment\" : \"" + environment +
                "\", \"description\" : \"" + description +
                "\", \"startDate\" : \"" + startDate +
                "\", \"endDate\" : \"" + endDate +
                "\", \"projectId\" : " + projectId +
                ", \"versionId\" : " + versionId +
                "}";
    }

}
