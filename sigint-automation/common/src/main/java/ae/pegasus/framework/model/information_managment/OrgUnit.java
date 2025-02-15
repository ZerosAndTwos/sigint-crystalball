
package ae.pegasus.framework.model.information_managment;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)

@JsonPropertyOrder({
        "orgUnitId",
        "orgUnitName"
})
public class OrgUnit {

    @JsonProperty("orgUnitId")
    private String orgUnitId;
    @JsonProperty("orgUnitName")
    private String orgUnitName;
    @JsonProperty("key")
    private String key;
    @JsonProperty("value")
    private String value;
    @JsonProperty("label")
    private String label;

    @JsonProperty("orgUnitId")
    public String getOrgUnitId() {
        return orgUnitId;
    }

    @JsonProperty("orgUnitId")
    public void setOrgUnitId(String orgUnitId) {
        this.orgUnitId = orgUnitId;
    }

    @JsonProperty("orgUnitName")
    public String getOrgUnitName() {
        return orgUnitName;
    }

    @JsonProperty("orgUnitName")
    public void setOrgUnitName(String orgUnitName) {
        this.orgUnitName = orgUnitName;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
