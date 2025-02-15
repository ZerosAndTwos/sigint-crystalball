package ae.pegasus.framework.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class DesignationSearchResult extends EntityListResult<Designation> {

  @Override
  @JsonProperty("content")
  public List<Designation> getResult() {
    return super.getResult();
  }

  @Override
  @JsonProperty("content")
  public void setResult(List<Designation> result) {
    super.setResult(result);
  }
}