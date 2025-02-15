package ae.pegasus.framework.model.information_managment.masterReport;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder({
        "data"
})

public class MasterReportPayload {
    @JsonProperty("data")
    private MasterReport data;

    @JsonProperty("data")
    public MasterReport getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(MasterReport data) {
        this.data = data;
    }
}
