package ae.pegasus.framework.model.information_managment.rfi;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder({
        "data"
})

public class RequestForInformationPayload {
    @JsonProperty("data")
    private RequestForInformation data;

    @JsonProperty("data")
    public RequestForInformation getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(RequestForInformation data) {
        this.data = data;
    }
}
