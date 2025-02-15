package ae.pegasus.framework.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder({
        "data"
})

public class ReportsExportPayload {
    @JsonProperty("data")
    private ReportsExportModel data;

    public ReportsExportModel getData() {
        return data;
    }

    public void setData(ReportsExportModel data) {
        this.data = data;
    }
}
