package ae.pegasus.framework.model;

import java.util.List;

public class AdvancedSearch {
    private String sourceType;
    private List<SearchGroup> groups;

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public List<SearchGroup> getGroups() {
        return groups;
    }

    public void setGroups(List<SearchGroup> groups) {
        this.groups = groups;
    }
}
