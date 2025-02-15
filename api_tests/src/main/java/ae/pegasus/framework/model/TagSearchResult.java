package ae.pegasus.framework.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TagSearchResult extends EntityListResult<Tag> {

    @Override
    @JsonProperty("content")
    public List<Tag> getResult() {
        return super.getResult();
    }

    @Override
    @JsonProperty("content")
    public void setResult(List<Tag> result) {
        super.setResult(result);
    }
}
