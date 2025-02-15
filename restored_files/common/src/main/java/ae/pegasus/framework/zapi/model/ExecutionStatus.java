package ae.pegasus.framework.zapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
public class ExecutionStatus {

    private String status;
    private boolean changeAssignee;

    public String getStatus() {
        return status;
    }

    public ExecutionStatus setStatus(String status) {
        this.status = status;
        return this;
    }

    public boolean isChangeAssignee() {
        return changeAssignee;
    }

    public ExecutionStatus setChangeAssignee(boolean changeAssignee) {
        this.changeAssignee = changeAssignee;
        return this;
    }
}
