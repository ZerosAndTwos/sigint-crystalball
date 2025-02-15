
package ae.pegasus.framework.model.information_managment;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "actionName",
        "actionPermission",
        "whoCanPerformAction",
        "nextOwner",
        "actionType",
        "actionStyle",
        "order",
        "spawnStateNo",
        "nextStateNo",
        "fields",
        "disabled",
        "system",
        "modelLogEnabled",
        "triggers"
})
public class PossibleActions {

    @JsonProperty("id")
    private String id;
    @JsonProperty("actionName")
    private String actionName;
    @JsonProperty("actionPermission")
    private String actionPermission;
    @JsonProperty("whoCanPerformAction")
    private List<WhoCanPerformAction> whoCanPerformAction = null;
    @JsonProperty("nextOwner")
    private NextOwner nextOwner;
    @JsonProperty("actionType")
    private String actionType;
    @JsonProperty("actionStyle")
    private String actionStyle;
    @JsonProperty("order")
    private Integer order;
    @JsonProperty("spawnStateNo")
    private Integer spawnStateNo;
    @JsonProperty("nextStateNo")
    private Integer nextStateNo;
    @JsonProperty("fields")
    private List<Field> fields = null;
    @JsonProperty("disabled")
    private Boolean disabled;
    @JsonProperty("system")
    private Boolean system;
    @JsonProperty("modelLogEnabled")
    private Boolean modelLogEnabled;
    @JsonProperty("triggers")
    private List<Trigger> triggers = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("actionName")
    public String getActionName() {
        return actionName;
    }

    @JsonProperty("actionName")
    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    @JsonProperty("actionPermission")
    public String getActionPermission() {
        return actionPermission;
    }

    @JsonProperty("actionPermission")
    public void setActionPermission(String actionPermission) {
        this.actionPermission = actionPermission;
    }

    @JsonProperty("whoCanPerformAction")
    public List<WhoCanPerformAction> getWhoCanPerformAction() {
        return whoCanPerformAction;
    }

    @JsonProperty("whoCanPerformAction")
    public void setWhoCanPerformAction(List<WhoCanPerformAction> whoCanPerformAction) {
        this.whoCanPerformAction = whoCanPerformAction;
    }

    @JsonProperty("nextOwner")
    public NextOwner getNextOwner() {
        return nextOwner;
    }

    @JsonProperty("nextOwner")
    public void setNextOwner(NextOwner nextOwner) {
        this.nextOwner = nextOwner;
    }

    @JsonProperty("actionType")
    public String getActionType() {
        return actionType;
    }

    @JsonProperty("actionType")
    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    @JsonProperty("actionStyle")
    public String getActionStyle() {
        return actionStyle;
    }

    @JsonProperty("actionStyle")
    public void setActionStyle(String actionStyle) {
        this.actionStyle = actionStyle;
    }

    @JsonProperty("order")
    public Integer getOrder() {
        return order;
    }

    @JsonProperty("order")
    public void setOrder(Integer order) {
        this.order = order;
    }

    @JsonProperty("spawnStateNo")
    public Integer getSpawnStateNo() {
        return spawnStateNo;
    }

    @JsonProperty("spawnStateNo")
    public void setSpawnStateNo(Integer spawnStateNo) {
        this.spawnStateNo = spawnStateNo;
    }

    @JsonProperty("nextStateNo")
    public Integer getNextStateNo() {
        return nextStateNo;
    }

    @JsonProperty("nextStateNo")
    public void setNextStateNo(Integer nextStateNo) {
        this.nextStateNo = nextStateNo;
    }

    @JsonProperty("fields")
    public List<Field> getFields() {
        return fields;
    }

    @JsonProperty("fields")
    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    @JsonProperty("disabled")
    public Boolean getDisabled() {
        return disabled;
    }

    @JsonProperty("disabled")
    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    @JsonProperty("system")
    public Boolean getSystem() {
        return system;
    }

    @JsonProperty("system")
    public void setSystem(Boolean system) {
        this.system = system;
    }

    @JsonProperty("modelLogEnabled")
    public Boolean getModelLogEnabled() {
        return modelLogEnabled;
    }

    @JsonProperty("modelLogEnabled")
    public void setModelLogEnabled(Boolean modelLogEnabled) {
        this.modelLogEnabled = modelLogEnabled;
    }

    @JsonProperty("triggers")
    public List<Trigger> getTriggers() {
        return triggers;
    }

    @JsonProperty("triggers")
    public void setTriggers(List<Trigger> triggers) {
        this.triggers = triggers;
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
