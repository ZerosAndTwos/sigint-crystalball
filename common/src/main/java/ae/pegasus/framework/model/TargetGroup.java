package ae.pegasus.framework.model;


import ae.pegasus.framework.data_for_entity.annotations.DataIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
@Deprecated
public class TargetGroup extends ProfileAndTargetGroup {

    @DataIgnore
    private String description;
    @DataIgnore
    private Integer threatScore;
    @DataIgnore
    private Boolean deleted;

    private TargetGroupProperties properties;
    @DataIgnore
    private int noGroups;
    @DataIgnore
    private int noProfiles;
    @DataIgnore
    private int noSavedSearches;
    @DataIgnore
    private ArrayList<String> profiles = new ArrayList<>();
    @DataIgnore
    private ArrayList<String> groups = new ArrayList<>();
    @DataIgnore
    private TargetGroup parent;
    @DataIgnore
    private ArrayList<ParentChain> parentChain = new ArrayList<>();

    public TargetGroup () {
    }

    @Override
    public String toString() {
        return String.format("name: %s, description: %s, deleted: %s", this.getName(), description, deleted);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getThreatScore() {
        return threatScore;
    }

    public void setThreatScore(Integer threatScore) {
        this.threatScore = threatScore;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public TargetGroupProperties getProperties() {
        return properties;
    }

    public void setProperties(TargetGroupProperties properties) {
        this.properties = properties;
    }

    public int getNoGroups() {
        return noGroups;
    }

    public void setNoGroups(int noGroups) {
        this.noGroups = noGroups;
    }

    public int getNoProfiles() {
        return noProfiles;
    }

    public void setNoProfiles(int noProfiles) {
        this.noProfiles = noProfiles;
    }

    public int getNoSavedSearches() {
        return noSavedSearches;
    }

    public void setNoSavedSearches(int noSavedSearches) {
        this.noSavedSearches = noSavedSearches;
    }

    public ArrayList<String> getProfiles() {
        return profiles;
    }

    public void setProfiles(ArrayList<String> profiles) {
        this.profiles = profiles;
    }

    public ArrayList<String> getGroups() {
        return groups;
    }

    public void setGroups(ArrayList<String> groups) {
        this.groups = groups;
    }

    public TargetGroup getParent() {
        return parent;
    }

    public void setParent(TargetGroup parent) {
        this.parent = parent;
    }

    public ArrayList<ParentChain> getParentChain() {
        return parentChain;
    }

    public void setParentChain(ArrayList<ParentChain> parentChain) {
        this.parentChain = parentChain;
    }


    public static class ParentChain extends AbstractEntity {

        private String name;

        public ParentChain(){
        }

        public ParentChain(String id, String name) {
            this.setId(id);
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
