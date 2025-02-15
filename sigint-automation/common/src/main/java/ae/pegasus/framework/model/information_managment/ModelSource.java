
package ae.pegasus.framework.model.information_managment;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)

@JsonPropertyOrder({
        "eventFeed",
        "dataSource",
        "subSource"
})
public class ModelSource {

    @JsonProperty("eventFeed")
    private String eventFeed;
    @JsonProperty("dataSource")
    private String dataSource;
    @JsonProperty("subSource")
    private String subSource;
    @JsonProperty("notEmpty")
    private Boolean notEmpty;

    @JsonProperty("eventFeed")
    public String getEventFeed() {
        return eventFeed;
    }

    @JsonProperty("eventFeed")
    public void setEventFeed(String eventFeed) {
        this.eventFeed = eventFeed;
    }

    @JsonProperty("dataSource")
    public String getDataSource() {
        return dataSource;
    }

    @JsonProperty("dataSource")
    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    @JsonProperty("subSource")
    public String getSubSource() {
        return subSource;
    }

    @JsonProperty("subSource")
    public void setSubSource(String subSource) {
        this.subSource = subSource;
    }

    public Boolean getNotEmpty() {
        return notEmpty;
    }

    public void setNotEmpty(Boolean notEmpty) {
        this.notEmpty = notEmpty;
    }
}
