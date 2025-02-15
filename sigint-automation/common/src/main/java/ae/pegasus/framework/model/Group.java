package ae.pegasus.framework.model;

import ae.pegasus.framework.data_for_entity.annotations.DataIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
@Deprecated
public class Group extends G4Entity {
    
    @DataIgnore
    private List<String> roles = new ArrayList<>();
    @DataIgnore
    private List<String> permissions = new ArrayList<>();
    @DataIgnore
    private List<String> users = new ArrayList<>();
    @JsonProperty("display_name")
    private String displayName;

    public List<String> getRoles() {
        return roles;
    }

    public Group setRoles(List<String> roles) {
        this.roles = roles;
        return this;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public Group setPermissions(List<String> permissions) {
        this.permissions = permissions;
        return this;
    }

    public List<String> getUsers() {
        return users;
    }

    public Group setUsers(List<String> users) {
        this.users = users;
        return this;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
    
    
}
