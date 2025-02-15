
package ae.pegasus.framework.model.information_managment.report;

import ae.pegasus.framework.model.AbstractEntity;
import ae.pegasus.framework.model.Attachment;
import ae.pegasus.framework.model.Metadata;
import ae.pegasus.framework.model.SourceType;
import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@com.fasterxml.jackson.annotation.JsonIgnoreProperties(ignoreUnknown = true)

@JsonPropertyOrder({
        "id",
        "order",
        "type",
        "source",
        "body",
        "metadata",
        "attachments"
})
public class ReportEvent extends AbstractEntity {

    @JsonProperty("order")
    private Integer order;
    @JsonProperty("type")
    private String type;
    @JsonProperty("source")
    private SourceType source;
    @JsonProperty("body")
    private String body;
    @JsonProperty("metadata")
    private Metadata metadata;
    @JsonProperty("metadata")
    private Attachment attachments;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("order")
    public Integer getOrder() {
        return order;
    }

    @JsonProperty("order")
    public void setOrder(Integer order) {
        this.order = order;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("source")
    public SourceType getSourceType() {
        return source;
    }

    @JsonProperty("source")
    public void setSourceType(SourceType source) {
        this.source = source;
    }

    @JsonProperty("body")
    public String getBody() {
        return body;
    }

    @JsonProperty("body")
    public void setBody(String body) {
        this.body = body;
    }

    @JsonProperty("metadata")
    public Metadata getMetadata() {
        return metadata;
    }

    @JsonProperty("metadata")
    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public Attachment getAttachments() {
        return attachments;
    }

    public void setAttachments(Attachment attachments) {
        this.attachments = attachments;
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
