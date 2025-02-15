
package ae.pegasus.framework.model.information_managment.report;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder({
        "data"
})
public class ReportPayload {

    @JsonProperty("data")
    private Report data;

    @JsonProperty("data")
    public Report getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(Report data) {
        this.data = data;
    }
}
