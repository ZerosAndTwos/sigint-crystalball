package model.phonebook;

import model.DuSubscriberEntry;
import model.EntityListResult;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
public class DuSubscriberSearchResult extends EntityListResult<DuSubscriberEntry> {

    @Override
    @JsonProperty("content")
    public List<DuSubscriberEntry> getResult() {
        return super.getResult();
    }

    @Override
    @JsonProperty("content")
    public void setResult(List<DuSubscriberEntry> result) {
        super.setResult(result);
    }
}
