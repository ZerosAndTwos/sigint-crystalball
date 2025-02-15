package ae.pegasus.framework.jira.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
public class IssueLink {
    
    private String id;
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public Issue getInnerIssue() {
        if (outwardIssue !=null) {
            return  outwardIssue;
        }
        else return inwardIssue;
    }
    
    public Issue getOutwardIssue() {
        return outwardIssue;
    }
    
    public void setOutwardIssue(Issue outwardIssue) {
        this.outwardIssue = outwardIssue;
    }
    
    private Issue outwardIssue;
    
    
    private Issue inwardIssue;
    
    public Issue getInwardIssue() {
        return inwardIssue;
    }
    
    public void setInwardIssue(Issue inwardIssue) {
        this.inwardIssue = inwardIssue;
    }
}
