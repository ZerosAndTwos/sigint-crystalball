
package ae.pegasus.framework.model.information_managment.masterReport;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "orgUnitId",
        "orgUnitName"
})
public class OrgUnitsToShare {

    @JsonProperty("orgUnitId")
    private String orgUnitId;
    @JsonProperty("orgUnitName")
    private String orgUnitName;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("orgUnitId")
    public String getOrgUnitId() {
        return orgUnitId;
    }

    @JsonProperty("orgUnitId")
    public void setOrgUnitId(String orgUnitId) {
        this.orgUnitId = orgUnitId;
    }

    public OrgUnitsToShare withOrgUnitId(String orgUnitId) {
        this.orgUnitId = orgUnitId;
        return this;
    }

    @JsonProperty("orgUnitName")
    public String getOrgUnitName() {
        return orgUnitName;
    }

    @JsonProperty("orgUnitName")
    public void setOrgUnitName(String orgUnitName) {
        this.orgUnitName = orgUnitName;
    }

    public OrgUnitsToShare withOrgUnitName(String orgUnitName) {
        this.orgUnitName = orgUnitName;
        return this;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public OrgUnitsToShare withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
