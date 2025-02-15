package ae.pegasus.framework.model;

import java.util.ArrayList;
import java.util.List;

public class CBEntityAssignments {

    private Object common;
    private List<String> designationIds = new ArrayList<>();
    private Integer importance;
    private String state;
    private List<String> categories = new ArrayList<>();
    private Integer priority;
    private List<String> ownerId = new ArrayList<>();
    private List<String> teamIds = new ArrayList<>();
    private List<String> tags = new ArrayList<>();

    public Object getCommon() {
        return common;
    }

    public void setCommon(Object common) {
        this.common = common;
    }

    public List<String> getDesignationIds() {
        return designationIds;
    }

    public void setDesignationIds(List<String> designationIds) {
        this.designationIds = designationIds;
    }

    public Integer getImportance() {
        return importance;
    }

    public void setImportance(Integer importance) {
        this.importance = importance;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public List<String> getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(List<String> ownerId) {
        this.ownerId = ownerId;
    }

    public List<String> getTeamIds() {
        return teamIds;
    }

    public void setTeamIds(List<String> teamIds) {
        this.teamIds = teamIds;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
