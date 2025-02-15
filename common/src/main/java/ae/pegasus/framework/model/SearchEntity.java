package ae.pegasus.framework.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class SearchEntity extends AbstractEntity {

    @JsonProperty("@type")
    private String type;
    private DataSourceCategory eventFeed;
    private ArrayList<DataSourceType> sources;
    private String sourceType;
    private String recordType;
    private String subSourceType;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public DataSourceCategory getEventFeed() {
        return eventFeed;
    }

    public void setEventFeed(DataSourceCategory eventFeed) {
        this.eventFeed = eventFeed;
    }

    public ArrayList<DataSourceType> getSources() {
        return sources;
    }

    public void setSources(ArrayList<DataSourceType> sources) {
        this.sources = sources;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public String getRecordType() {
        return recordType;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }

    public String getSubSourceType() {
        return subSourceType;
    }

    public void setSubSourceType(String subSourceType) {
        this.subSourceType = subSourceType;
    }
}
