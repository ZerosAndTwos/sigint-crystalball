package ae.pegasus.framework.model;

import ae.pegasus.framework.data_for_entity.annotations.DataIgnore;
import ae.pegasus.framework.data_for_entity.annotations.WithFieldDataType;
import ae.pegasus.framework.data_for_entity.data_types.FieldDataType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ProfileProperties {

    @WithFieldDataType(FieldDataType.STRING)
    private String description;
    @DataIgnore
    private String type = "Individual";
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
    @JsonProperty("__profile_version")
    private Integer profileVersion;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public Integer getProfileVersion() {
        return profileVersion;
    }

    public void setProfileVersion(Integer profileVersion) {
        this.profileVersion = profileVersion;
    }
}
