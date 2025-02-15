package ae.pegasus.framework.model;

import ae.pegasus.framework.data_for_entity.annotations.DataIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
public class Organization extends G4Entity {

    @DataIgnore
    private String parentTeamId;
    @DataIgnore
    private List<String> parentTeamIds = new ArrayList<>();
    @DataIgnore
    private OrganizationType organizationType;
    private String fullName;
    @DataIgnore
    private UserPermission defaultPermission;

    public String getParentTeamId() {
        return parentTeamId;
    }

    public void setParentTeamId(String parentTeamId) {
        this.parentTeamId = parentTeamId;
    }

    public List<String> getParentTeamIds() {
        return parentTeamIds;
    }

    public void setParentTeamIds(List<String> parentTeamIds) {
        this.parentTeamIds = parentTeamIds;
    }

    public OrganizationType getOrganizationType() {
        return organizationType;
    }

    public void setOrganizationType(OrganizationType organizationType) {
        this.organizationType = organizationType;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public UserPermission getDefaultPermission() {
        return defaultPermission;
    }

    public void setDefaultPermission(UserPermission defaultPermission) {
        this.defaultPermission = defaultPermission;
    }
}
