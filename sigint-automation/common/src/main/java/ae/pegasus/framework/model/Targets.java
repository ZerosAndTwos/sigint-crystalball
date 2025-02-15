
package ae.pegasus.framework.model;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "targetHitIds",
        "targetMentionIds"
})
public class Targets {

    @JsonProperty("targetHitIds")
    private List<Object> targetHitIds = null;
    @JsonProperty("targetMentionIds")
    private Object targetMentionIds;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("targetHitIds")
    public List<Object> getTargetHitIds() {
        return targetHitIds;
    }

    @JsonProperty("targetHitIds")
    public void setTargetHitIds(List<Object> targetHitIds) {
        this.targetHitIds = targetHitIds;
    }

    @JsonProperty("targetMentionIds")
    public Object getTargetMentionIds() {
        return targetMentionIds;
    }

    @JsonProperty("targetMentionIds")
    public void setTargetMentionIds(Object targetMentionIds) {
        this.targetMentionIds = targetMentionIds;
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
