
package ae.pegasus.framework.model.information_managment;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "creatorWithPermissions"
})
public class WhoCanPerformAction {

    @JsonProperty("creatorWithPermissions")
    private List<String> creatorWithPermissions = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("creatorWithPermissions")
    public List<String> getCreatorWithPermissions() {
        return creatorWithPermissions;
    }

    @JsonProperty("creatorWithPermissions")
    public void setCreatorWithPermissions(List<String> creatorWithPermissions) {
        this.creatorWithPermissions = creatorWithPermissions;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
