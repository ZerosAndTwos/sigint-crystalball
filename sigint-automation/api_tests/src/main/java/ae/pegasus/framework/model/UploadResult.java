package ae.pegasus.framework.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UploadResult {

    private int numEntries;
    private int failedEntries;
    private int rowsFailed;
    private int rowsAdded;
    private int rowsUpdated;
    private List<String> errors;

    public int getFailedEntries() {
        return failedEntries;
    }

    public void setFailedEntries(int failedEntries) {
        this.failedEntries = failedEntries;
    }

    public int getNumEntries() {
        return numEntries;
    }

    public void setNumEntries(int numEntries) {
        this.numEntries = numEntries;
    }

    public int getRowsFailed() {
        return rowsFailed;
    }

    public void setRowsFailed(int rowsFailed) {
        this.rowsFailed = rowsFailed;
    }

    public int getRowsAdded() {
        return rowsAdded;
    }

    public void setRowsAdded(int rowsAdded) {
        this.rowsAdded = rowsAdded;
    }

    public int getRowsUpdated() {
        return rowsUpdated;
    }

    public void setRowsUpdated(int rowsUpdated) {
        this.rowsUpdated = rowsUpdated;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

}
