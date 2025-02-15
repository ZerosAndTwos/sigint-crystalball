package ae.pegasus.framework.model.information_managment.rfa;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder({
        "data"
})

public class RequestForApprovePayload {
    @JsonProperty("data")
    private RequestForApprove data;

    @JsonProperty("data")
    public RequestForApprove getData() {
        return data;
    }


    public void setData(RequestForApprove data) {
        this.data = data;
    }
}
