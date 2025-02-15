package ae.pegasus.framework.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
public class RecordSearchResult extends EntityListResult<Record> {

    @Override
    @JsonProperty("content")
    public List<Record> getResult() {
        return super.getResult();
    }

    @Override
    @JsonProperty("content")
    public void setResult(List<Record> result) {
        super.setResult(result);
    }
}
