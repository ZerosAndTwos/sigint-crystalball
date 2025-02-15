
package ae.pegasus.framework.model;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "zoom",
        "center",
        "imageId"
})
public class Metadata {

    @JsonProperty("zoom")
    private String zoom;
    @JsonProperty("center")
    private String center;
    @JsonProperty("imageId")
    private String imageId;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("zoom")
    public String getZoom() {
        return zoom;
    }

    @JsonProperty("zoom")
    public void setZoom(String zoom) {
        this.zoom = zoom;
    }

    @JsonProperty("center")
    public String getCenter() {
        return center;
    }

    @JsonProperty("center")
    public void setCenter(String center) {
        this.center = center;
    }

    @JsonProperty("imageId")
    public String getImageId() {
        return imageId;
    }

    @JsonProperty("imageId")
    public void setImageId(String imageId) {
        this.imageId = imageId;
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
