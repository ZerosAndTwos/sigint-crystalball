package http.requests.rfi;

import model.InformationRequest;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RFIDetailsResponse {

    private InformationRequest result;

    public InformationRequest getResult() {
        return result;
    }

    public void setResult(InformationRequest result) {
        this.result = result;
    }

}
