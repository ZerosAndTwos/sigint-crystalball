package model.phonebook;

import model.EntityListResult;
import model.Phonebook;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PhonebookSearchResults extends EntityListResult<Phonebook> {

    @Override
    @JsonProperty("content")
    public List<Phonebook> getResult() {
        return super.getResult();
    }

    @Override
    @JsonProperty("content")
    public void setResult(List<Phonebook> result) {
        super.setResult(result);
    }
}
