
package ae.pegasus.framework.model.information_managment;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "wfId",
        "actionId",
        "mapper",
        "condition",
        "linkage"
})
public class Trigger {

    @JsonProperty("wfId")
    private String wfId;
    @JsonProperty("actionId")
    private String actionId;
    @JsonProperty("mapper")
    private String mapper;
    @JsonProperty("condition")
    private String condition;
    @JsonProperty("linkage")
    private Boolean linkage;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("wfId")
    public String getWfId() {
        return wfId;
    }

    @JsonProperty("wfId")
    public void setWfId(String wfId) {
        this.wfId = wfId;
    }

    @JsonProperty("actionId")
    public String getActionId() {
        return actionId;
    }

    @JsonProperty("actionId")
    public void setActionId(String actionId) {
        this.actionId = actionId;
    }

    @JsonProperty("mapper")
    public String getMapper() {
        return mapper;
    }

    @JsonProperty("mapper")
    public void setMapper(String mapper) {
        this.mapper = mapper;
    }

    @JsonProperty("condition")
    public String getCondition() {
        return condition;
    }

    @JsonProperty("condition")
    public void setCondition(String condition) {
        this.condition = condition;
    }

    @JsonProperty("linkage")
    public Boolean getLinkage() {
        return linkage;
    }

    @JsonProperty("linkage")
    public void setLinkage(Boolean linkage) {
        this.linkage = linkage;
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
