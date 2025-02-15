
package ae.pegasus.framework.model.information_managment.report;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "dataSource",
        "recordType",
        "subSourceId",
        "subSourceName"
})
public class ReportSource {

    @JsonProperty("dataSource")
    private String dataSource;
    @JsonProperty("recordType")
    private String recordType;
    @JsonProperty("subSourceId")
    private String subSourceId;
    @JsonProperty("subSourceName")
    private String subSourceName;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("dataSource")
    public String getDataSource() {
        return dataSource;
    }

    @JsonProperty("dataSource")
    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    @JsonProperty("recordType")
    public String getRecordType() {
        return recordType;
    }

    @JsonProperty("recordType")
    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }

    @JsonProperty("subSourceId")
    public String getSubSourceId() {
        return subSourceId;
    }

    @JsonProperty("subSourceId")
    public void setSubSourceId(String subSourceId) {
        this.subSourceId = subSourceId;
    }

    @JsonProperty("subSourceName")
    public String getSubSourceName() {
        return subSourceName;
    }

    @JsonProperty("subSourceName")
    public void setSubSourceName(String subSourceName) {
        this.subSourceName = subSourceName;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
