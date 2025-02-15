package model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ProfileEntity extends AbstractEntity {

    private String nodeId;
    private String key; //"[TWEET]:Unknown:833467341514092546"
    private String name; // 833467341514092546
    private Object properties;
    private List<ProfileSourceType> sources; //["TWEET"]
    private ProfileEntityType type; // unknown
    private ValidationStatus validationStatus;
    private Object entities;
    private String attributes;
    private Boolean manualSource;

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public Object getProperties() {
        return properties;
    }

    public void setProperties(Object properties) {
        this.properties = properties;
    }

    public List<ProfileSourceType> getSources() {
        return sources;
    }

    public void setSources(List<ProfileSourceType> sources) {
        this.sources = sources;
    }

    public Object getEntities() {
        return entities;
    }

    public void setEntities(Object entities) {
        this.entities = entities;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProfileEntityType getType() {
        return type;
    }

    public void setType(ProfileEntityType type) {
        this.type = type;
    }

    public ValidationStatus getValidationStatus() {
        return validationStatus;
    }

    public void setValidationStatus(ValidationStatus validationStatus) {
        this.validationStatus = validationStatus;
    }

    public String getAttributes() {
        return attributes;
    }

    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }

    public Boolean getManualSource() {
        return manualSource;
    }

    public void setManualSource(Boolean manualSource) {
        this.manualSource = manualSource;
    }
}
