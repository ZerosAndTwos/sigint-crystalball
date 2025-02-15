package ae.pegasus.framework.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.List;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)

public class DataSource extends AbstractEntity {

    private String name;
    private String classification;
    private String displayName;
    private String parentName;
    private List<String> subSources = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public List<String> getSubSources() {
        return subSources;
    }

    public void setSubSources(List<String> subSources) {
        this.subSources = subSources;
    }
}
