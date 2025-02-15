package ae.pegasus.framework.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TargetSearchResult extends EntityListResult<Target> {

    @Override
    @JsonProperty("content")
    public List<Target> getResult() {
        return super.getResult();
    }

    @Override
    @JsonProperty("content")
    public void setResult(List<Target> result) {
        super.setResult(result);
    }
}
