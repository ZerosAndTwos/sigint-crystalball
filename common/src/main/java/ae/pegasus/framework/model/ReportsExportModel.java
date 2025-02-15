package ae.pegasus.framework.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)

@JsonPropertyOrder({
        "models",
        "category",
        "showCreator",
        "showSources"

})

public class ReportsExportModel {
    @JsonProperty("models")
    private List<ReportModels> models = new ArrayList<>();

    @JsonProperty("category")
    private String category;

    @JsonProperty("showCreator")
    private Boolean showCreator;

    @JsonProperty("showSources")
    private Boolean showSources;

    public List<ReportModels> getModels() {
        return models;
    }

    public void setModels(List<ReportModels> models) {
        this.models = models;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Boolean getShowCreator() {
        return showCreator;
    }

    public void setShowCreator(Boolean showCreator) {
        this.showCreator = showCreator;
    }

    public Boolean getShowSources() {
        return showSources;
    }

    public void setShowSources(Boolean showSources) {
        this.showSources = showSources;
    }
}
