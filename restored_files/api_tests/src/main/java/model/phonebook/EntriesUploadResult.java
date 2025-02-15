package model.phonebook;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EntriesUploadResult {

    private int numEntries;
    private int failedEntries;

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

}
