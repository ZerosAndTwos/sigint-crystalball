package model.phonebook;

import model.EntityListResult;
import model.EtisalatSubscriberEntry;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EtisalatSubscriberSearchResult extends EntityListResult<EtisalatSubscriberEntry> {

    @Override
    @JsonProperty("content")
    public List<EtisalatSubscriberEntry> getResult() {
        return super.getResult();
    }

    @Override
    @JsonProperty("content")
    public void setResult(List<EtisalatSubscriberEntry> result) {
        super.setResult(result);
    }
}
