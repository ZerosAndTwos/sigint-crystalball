
package ae.pegasus.framework.model.information_managment.masterReport;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "performerId",
        "performerName",
        "performedOn"
})
public class ActionsLog {

    @JsonProperty("performerId")
    private String performerId;
    @JsonProperty("performerName")
    private String performerName;
    @JsonProperty("performedOn")
    private Integer performedOn;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("performerId")
    public String getPerformerId() {
        return performerId;
    }

    @JsonProperty("performerId")
    public void setPerformerId(String performerId) {
        this.performerId = performerId;
    }

    public ActionsLog withPerformerId(String performerId) {
        this.performerId = performerId;
        return this;
    }

    @JsonProperty("performerName")
    public String getPerformerName() {
        return performerName;
    }

    @JsonProperty("performerName")
    public void setPerformerName(String performerName) {
        this.performerName = performerName;
    }

    public ActionsLog withPerformerName(String performerName) {
        this.performerName = performerName;
        return this;
    }

    @JsonProperty("performedOn")
    public Integer getPerformedOn() {
        return performedOn;
    }

    @JsonProperty("performedOn")
    public void setPerformedOn(Integer performedOn) {
        this.performedOn = performedOn;
    }

    public ActionsLog withPerformedOn(Integer performedOn) {
        this.performedOn = performedOn;
        return this;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public ActionsLog withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
