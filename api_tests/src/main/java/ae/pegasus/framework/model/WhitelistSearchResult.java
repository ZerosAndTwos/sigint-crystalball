package ae.pegasus.framework.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class WhitelistSearchResult extends EntityListResult<Whitelist> {

  @Override
  @JsonProperty("content")
  public List<Whitelist> getResult() {
    return super.getResult();
  }

  @Override
  @JsonProperty("content")
  public void setResult(List<Whitelist> result) {
    super.setResult(result);
  }
}
