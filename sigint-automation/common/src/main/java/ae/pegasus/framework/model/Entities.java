
package ae.pegasus.framework.model;

import ae.pegasus.framework.model.information_managment.report.ReportEvent;
import com.fasterxml.jackson.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "from",
        "to"
})
public class Entities {

    @JsonProperty("from")
    private List<ReportEvent> from = new ArrayList<>();
    @JsonProperty("to")
    private List<ReportEvent> to = new ArrayList<>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public List<ReportEvent> getFrom() {
        return from;
    }

    public void setFrom(List<ReportEvent> from) {
        this.from = from;
    }

    public List<ReportEvent> getTo() {
        return to;
    }

    public void setTo(List<ReportEvent> to) {
        this.to = to;
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
