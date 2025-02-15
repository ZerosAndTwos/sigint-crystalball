package ae.pegasus.framework.model;

import ae.pegasus.framework.data_for_entity.annotations.DataIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class TargetGroupProperties {

    private String description;
    @DataIgnore
    @JsonProperty("__creation_time")
    private Date creationTime;
    @DataIgnore
    @JsonProperty("__creator")
    private String creator;
    @DataIgnore
    @JsonProperty("__last_modified_time")
    private Date lastModifiedTime;
    @DataIgnore
    @JsonProperty("__modifier")
    private String modifier;
    @DataIgnore
    @JsonProperty("__target_group_version")
    private Integer targetGroupVersion;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(Date lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public Integer getTargetGroupVersion() {
        return targetGroupVersion;
    }

    public void setTargetGroupVersion(Integer targetGroupVersion) {
        this.targetGroupVersion = targetGroupVersion;
    }

}
