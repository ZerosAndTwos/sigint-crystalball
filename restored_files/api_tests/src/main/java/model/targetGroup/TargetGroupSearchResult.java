package model.targetGroup;

import abs.EntityListResult;
import model.TargetGroup;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
public class TargetGroupSearchResult extends EntityListResult<TargetGroup> {

}
