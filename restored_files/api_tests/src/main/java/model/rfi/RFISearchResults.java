package model.rfi;

import model.EntityListResult;
import model.InformationRequest;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RFISearchResults extends EntityListResult<InformationRequest> {

    @Override
    @JsonProperty("content")
    public List<InformationRequest> getResult() {
        return super.getResult();
    }

    @Override
    @JsonProperty("content")
    public void setResult(List<InformationRequest> result) {
        super.setResult(result);
    }
}
