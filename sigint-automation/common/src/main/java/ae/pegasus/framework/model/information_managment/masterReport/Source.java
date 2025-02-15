
package ae.pegasus.framework.model.information_managment.masterReport;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "eventFeed",
        "dataSource",
        "subSource"
})
public class Source {

    @JsonProperty("eventFeed")
    private String eventFeed;
    @JsonProperty("dataSource")
    private String dataSource;
    @JsonProperty("subSource")
    private String subSource;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("eventFeed")
    public String getEventFeed() {
        return eventFeed;
    }

    @JsonProperty("eventFeed")
    public void setEventFeed(String eventFeed) {
        this.eventFeed = eventFeed;
    }

    public Source withEventFeed(String eventFeed) {
        this.eventFeed = eventFeed;
        return this;
    }

    @JsonProperty("dataSource")
    public String getDataSource() {
        return dataSource;
    }

    @JsonProperty("dataSource")
    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public Source withDataSource(String dataSource) {
        this.dataSource = dataSource;
        return this;
    }

    @JsonProperty("subSource")
    public String getSubSource() {
        return subSource;
    }

    @JsonProperty("subSource")
    public void setSubSource(String subSource) {
        this.subSource = subSource;
    }

    public Source withSubSource(String subSource) {
        this.subSource = subSource;
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

    public Source withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
