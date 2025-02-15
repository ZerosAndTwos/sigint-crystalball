
package ae.pegasus.framework.model.information_managment;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "editableFields",
        "requiredFields"
})
public class Field {

    @JsonProperty("editableFields")
    private List<String> editableFields = null;
    @JsonProperty("requiredFields")
    private List<String> requiredFields = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("editableFields")
    public List<String> getEditableFields() {
        return editableFields;
    }

    @JsonProperty("editableFields")
    public void setEditableFields(List<String> editableFields) {
        this.editableFields = editableFields;
    }

    @JsonProperty("requiredFields")
    public List<String> getRequiredFields() {
        return requiredFields;
    }

    @JsonProperty("requiredFields")
    public void setRequiredFields(List<String> requiredFields) {
        this.requiredFields = requiredFields;
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
