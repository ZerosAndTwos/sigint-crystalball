
package ae.pegasus.framework.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

@JsonPropertyOrder({
        "classification",
        "dataSources",
        "orgUnit"
})
public class RequiredPermission {

    @JsonProperty("classification")
    private String classification;
    @JsonProperty("dataSources")
    private List<String> dataSources = null;
    @JsonProperty("orgUnit")
    private String orgUnit;

    @JsonProperty("classification")
    public String getClassification() {
        return classification;
    }

    @JsonProperty("classification")
    public void setClassification(String classification) {
        this.classification = classification;
    }

    @JsonProperty("dataSources")
    public List<String> getDataSources() {
        return dataSources;
    }

    @JsonProperty("dataSources")
    public void setDataSources(List<String> dataSources) {
        this.dataSources = dataSources;
    }

    @JsonProperty("orgUnit")
    public String getOrgUnit() {
        return orgUnit;
    }

    @JsonProperty("orgUnit")
    public void setOrgUnit(String orgUnit) {
        this.orgUnit = orgUnit;
    }
}
