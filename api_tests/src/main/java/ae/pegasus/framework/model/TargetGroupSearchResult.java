package ae.pegasus.framework.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TargetGroupSearchResult extends EntityListResult<TargetGroup> {

    @Override
    @JsonProperty("content")
    public List<TargetGroup> getResult() {
        return super.getResult();
    }

    @Override
    @JsonProperty("content")
    public void setResult(List<TargetGroup> result) {
        super.setResult(result);
    }
}
